package com.schweizerischebundesbahnen.restcontroller;

import com.google.zxing.WriterException;
import com.schweizerischebundesbahnen.exceptions.PurchaseAlreadyExistExceprion;
import com.schweizerischebundesbahnen.exceptions.PurchaseTimeOutException;
import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.service.api.RideService;
import com.schweizerischebundesbahnen.service.api.SeatService;
import com.schweizerischebundesbahnen.service.api.TicketService;
import com.schweizerischebundesbahnen.service.api.UserService;
import com.schweizerischebundesbahnen.service.imp.MailService;
import com.schweizerischebundesbahnen.service.imp.QRService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by aleksandrprendota on 30.03.17.
 */

@RestController
@Log4j
@RequestMapping(value = "/purchase")
public class PurchaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RideService rideService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MailService mailService;

    @Autowired
    private SeatService seatService;

    private static final long UNIX_TIME = System.currentTimeMillis() / 1000L;

    /**
     *  Saving user purchase.
     *  If the purchase was created successfully
     *  then send the QR-Code to user email with info about ride
     * @param auth current user
     * @param id schedule entity
     * @return
     * @throws WriterException
     * @throws IOException
     * @throws MessagingException
     */
    @RequestMapping(value = "/ride/{id}/carriage/{carriage}/seat/{number}", method = RequestMethod.POST)
    public ResponseEntity<?> buyUserRide(Authentication auth,
                                         @PathVariable Schedule id,
                                         @PathVariable String carriage,
                                         @PathVariable String number)
            throws WriterException, IOException, MessagingException{

        User currentUser = userService.findUserByEmail(auth.getName());
        // Try to find the same rides from user
        int alreadyExistRide = 0;

        // if time to departure > 10 min throw PurchaseTimeOutException
        if (id.getTimeDeparture().getTime()/1000L + 600L < UNIX_TIME ) {
            log.warn("The Unsuccessfully purchase 'cause time departure less than 10 minutes");
            return new ResponseEntity<>("Time Departure less than 10 minutes"
                    ,HttpStatus.BAD_REQUEST);
        }

        if (!checkValidSeat(carriage,number,id.getTrain(),id.getTimeDeparture())){
            return new ResponseEntity<>("Please choose another seats!"
                    ,HttpStatus.BAD_REQUEST);
        }

        List<Ticket> usersTikets = ticketService.findTicketsByUser(currentUser);
        int seatNumber = Integer.parseInt(number);
        int cabineNumber = Integer.parseInt(carriage);

        if (seatNumber <= 0 && cabineNumber <= 0){
            return new ResponseEntity<>("Please choose another seats!"
                    ,HttpStatus.BAD_REQUEST);
        }
        Seat seat = seatService.findByTrainAndNumberAndCarriage(id.getTrain(), seatNumber, cabineNumber);

        if (usersTikets.size() == 0) {
            Ride ride;
            ride = buyTheRide(currentUser,id, seat);
            sendBoughtTicket(ride,currentUser);
            return ResponseEntity.ok(ride);
        } else {
            for (Ticket usersTiket : usersTikets) {
                    Ride tmpRide = rideService.findByTicket(usersTiket);
                    if((tmpRide.getTimeDeparture().getTime() / 1000L == id.getTimeDeparture().getTime() / 1000L) &&
                    ((tmpRide.getTrain()).equals(id.getTrain()))) {
                        alreadyExistRide += 1;
                        log.warn("User = " + auth.getName() + " has already got ride = " + id.getId());
                    }
            }
            // If user has got 2 equal ride — PurchaseAlreadyExistExceprion
            if (alreadyExistRide > 0){
                return new ResponseEntity<>("User has already got this ride!",
                        HttpStatus.BAD_REQUEST);
            } else {
                Ride ride;
                ride = buyTheRide(currentUser,id, seat);
                sendBoughtTicket(ride,currentUser);
                return ResponseEntity.ok(ride);
            }
        }
    }


    private Ride buyTheRide(User currentUser, Schedule schedule, Seat seat){

        Ticket ticket = new Ticket();
        Ride ride = new Ride();
        ride.setStationArrival(schedule.getStationArrival());
        ride.setTimeDeparture(schedule.getTimeDeparture());
        ride.setStationDeparture(schedule.getStationDeparture());
        ride.setTimeArrival(schedule.getTimeArrival());
        ride.setTrain(schedule.getTrain());
        ride.setSeat(seat);

        if ( ticket.getRide() == null) {
            Set<Ride> rides = new HashSet<>();
            rides.add(ride);
            ticket.setRide(rides);
        } else {
            ticket.getRide().add(ride);
        }

        ticket.setUser(currentUser);
        ticketService.addTicket(ticket);
        ride.setTicket(ticket);
        rideService.addRide(ride);
        log.info(currentUser.getEmail() + " has already bought the ride");

        return ride;
    }

    private void sendBoughtTicket(Ride ride, User user) throws WriterException, IOException, MessagingException {

        Mail mail = new Mail();
        mail.setTo(user.getEmail());
        mail.setSubject("Поздравляем с покупкой");
        mail.setText(String.format("Привет %s, спасибо, что купили билет! Счастливого пути!", user.getFirstname()));

        QRService qr = new QRService();
        // Inrormation from QR - CODE
        String qrCodeText = String.valueOf(ride.hashCode());
		String filePath = "images/QR.jpg";
		String newImagePath = "images/" + String.valueOf(ride.getId()) + ".jpg";
		int size = 125;
		String fileType = "jpg";
		File qrFile = new File(filePath);
        File newFile = new File(newImagePath);
        copy(qrFile,newFile);
		qr.createQRImage(newFile, qrCodeText, size, fileType);
        log.info("QR-CODE has made seccessfully! Hash: " + qrCodeText);
		mailService.sendTicket(mail,newFile);
		log.info("QR-CODE has send seccessfully");

    }

    private void copy(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    private Boolean checkValidSeat(String carriage, String number, Train train, Date timeDeparture){

        List<Ride> rides = rideService.findByTrainAndTime(train,timeDeparture);
        if (rides.size() == 0) { return true; }

        boolean seatAlreadyExist = false;

        for (Ride ride : rides) {
            Seat seat = ride.getSeat();
            if ((Integer.parseInt(number)) == seat.getNumber() && Integer.parseInt(carriage) == seat.getCabine()){
                seatAlreadyExist = true;
            }
        }
        return !seatAlreadyExist;

    }


}


