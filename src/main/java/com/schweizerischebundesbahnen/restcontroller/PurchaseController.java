package com.schweizerischebundesbahnen.restcontroller;

import com.google.zxing.WriterException;
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
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

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

        if (seatNumber <= 0 || cabineNumber <= 0){
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

    @RequestMapping(value = "/multi/c/{carriage}/n/{number}/c1/{carriage1}/n1/{number1}",method = RequestMethod.POST)
    public ResponseEntity<?> makeMultiPurchase(Authentication auth,
                                               @RequestBody List<Schedule> schedules,
                                               @PathVariable String carriage,
                                               @PathVariable String number,
                                               @PathVariable String carriage1,
                                               @PathVariable String number1) throws IOException, MessagingException, WriterException {

        User currentUser = userService.findUserByEmail(auth.getName());
        // Try to find the same rides from user

        int alreadyExistRide = 0;

        for (Schedule schedule : schedules) {

            // if time to departure > 10 min throw PurchaseTimeOutException
            if (schedule.getTimeDeparture().getTime()/1000L + 600L < UNIX_TIME ) {
                log.warn("The Unsuccessfully purchase 'cause time departure less than 10 minutes");
                return new ResponseEntity<>("Time Departure less than 10 minutes"
                        ,HttpStatus.BAD_REQUEST);
            }
        }

        // seat validation
        if (!checkValidSeat(carriage,number,schedules.get(0).getTrain(),schedules.get(0).getTimeDeparture())){
            return new ResponseEntity<>("Please choose another seats! on train:" + schedules.get(0).getTrain().getId()
                    ,HttpStatus.BAD_REQUEST);
        }


        // seat validation
        if (!checkValidSeat(carriage1,number1,schedules.get(1).getTrain(),schedules.get(1).getTimeDeparture())){
            return new ResponseEntity<>("Please choose another seats! on train:" + schedules.get(1).getTrain().getId()
                    ,HttpStatus.BAD_REQUEST);
        }


        List<Ticket> usersTickets = ticketService.findTicketsByUser(currentUser);
        int seatNumber = Integer.parseInt(number);
        int cabineNumber = Integer.parseInt(carriage);
        int seatNumber1 = Integer.parseInt(number1);
        int cabineNumber1 = Integer.parseInt(carriage1);

        // seats number validation
        if (seatNumber <= 0 || cabineNumber <= 0 || seatNumber1 <= 0 || cabineNumber1 <= 0){
            return new ResponseEntity<>("Please choose another seats!"
                    ,HttpStatus.BAD_REQUEST);
        }

        // Only 2 ride in ticket!
        Seat seat = seatService.findByTrainAndNumberAndCarriage(schedules.get(0).getTrain(), seatNumber, cabineNumber);
        Seat seat1 = seatService.findByTrainAndNumberAndCarriage(schedules.get(1).getTrain(), seatNumber, cabineNumber);

        if (usersTickets.size() == 0) {

            List<Ride> rides = buyTwoRide(schedules,seat,seat1,currentUser);
            return ResponseEntity.ok(rides);
        } else {
            for (Schedule schedule : schedules) {
                for (Ticket usersTicket : usersTickets) {
                    Ride tmpRide = rideService.findByTicket(usersTicket);
                    if((tmpRide.getTimeDeparture().getTime() / 1000L == schedule.getTimeDeparture().getTime() / 1000L)) {
                        if  ((tmpRide.getTrain().getId()).equals(schedule.getTrain().getId())){
                            alreadyExistRide += 1;
                            log.warn("User = " + auth.getName() + " has already got the ride = " + schedule.getId());
                        }
                    }
                }
            }
            // If user has got 2 equal ride — PurchaseAlreadyExistExceprion
            if (alreadyExistRide > 0){
                return new ResponseEntity<>("User has already got ride!",
                        HttpStatus.BAD_REQUEST);
            } else {
                List<Ride> rides = buyTwoRide(schedules,seat,seat1,currentUser);
                return ResponseEntity.ok(rides);
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

    public Boolean checkValidSeat(String carriage, String number, Train train, Date timeDeparture){

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

    private List<Ride> buyTwoRide(List<Schedule> schedules, Seat seat1, Seat seat2 , User user) throws IOException, MessagingException, WriterException {
        List<Ride> rides = new ArrayList<>();
        // make first ride
        Ride rideOne;
        rideOne = buyTheRide(user,schedules.get(0), seat1);
        sendBoughtTicket(rideOne,user);
        // make second ride
        Ride rideTwo;
        rideTwo = buyTheRide(user,schedules.get(1), seat2);
        sendBoughtTicket(rideTwo,user);
        // send ride to client
        rides.add(rideOne);
        rides.add(rideTwo);
        return rides;
    }
}


