package com.schweizerischebundesbahnen.intergation.testcontrollers;

import com.schweizerischebundesbahnen.exceptions.ScheduleNotFoundException;
import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.repository.SeatRepository;
import com.schweizerischebundesbahnen.restcontroller.PurchaseController;
import com.schweizerischebundesbahnen.service.api.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by aleksandrprendota on 24.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConcurrentBuyingDeletedRide {

    private Schedule schedule;
    private User customer;
    private Seat seat;
    private Long ticketID;
    private Ride ride;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private StationService stationService;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private PurchaseController purchaseController;

    @Autowired
    private RideService rideService;

    @Autowired
    private TicketService ticketService;

    @Before
    public void setup(){
        Train train = trainService.findTrainByName("F01");
        Station station = stationService.findStationByName("Baden");
        customer = userService.getUserById(212L);
        seat = seatRepository.findOne(731L);

        Schedule tmpSchedule = new Schedule();
        tmpSchedule.setTrain(train);
        tmpSchedule.setStationArrival(station);
        tmpSchedule.setTimeArrival(new Date());
        tmpSchedule.setTimeDeparture(new Date());
        tmpSchedule.setStationDeparture(station);
        schedule = scheduleService.saveSchedule(tmpSchedule);
    }

    @Test
    public void concurrentBuyDeletedRide() throws BrokenBarrierException, InterruptedException {
        // We want to start just 2 threads at the same time, but let's control that
        // timing from the main thread. That's why we have 3 "parties" instead of 2.
        final CyclicBarrier gate = new CyclicBarrier(3);

        Thread t1 = new Thread(() -> {
            try {
                gate.await();
                System.out.println("*********");
                System.out.println("Admin is deleting schedule...");
                scheduleService.delete(schedule);
                System.out.println("*********");
                System.out.println("Admin deleted schedule...");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        });
        Thread t2 = new Thread(() -> {
            try {
                gate.await();
                try{
                    System.out.println("*********");
                    System.out.println("Customer try to buy...");
                    ride = purchaseController.buyTheRide(customer,schedule,seat);
                    ticketID = ride.getTicket().getId();

                    System.out.println("*********");
                    System.out.println("Customer bought successfully...");
                } catch (ScheduleNotFoundException e){
                    System.out.println("*********");
                    System.out.println("Customer cannot buy the ride");
                    System.out.println("*********");
                }

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        // At this point, t1 and t2 are blocking on the gate.
        // Since we gave "3" as the argument, gate is not opened yet.
        // Now if we block on the gate from the main thread, it will open
        // and all threads will start to do stuff!
        gate.await();
        t1.join();
        t2.join();

        System.out.println("*********");
        System.out.println("TEST DONE");
        System.out.println("*********");
    }

    @After
    public void deleteSchedule(){
        if (scheduleService.findScheduleById(schedule.getId()) != null){
            scheduleService.delete(schedule);
        }
        if (ride != null){
            rideService.delete(ride);
            Ticket ticket = ticketService.findById(ticketID);
            ticketService.delete(ticket);
        }

    }
}
