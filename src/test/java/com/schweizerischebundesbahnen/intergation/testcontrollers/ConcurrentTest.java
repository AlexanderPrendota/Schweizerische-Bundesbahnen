package com.schweizerischebundesbahnen.intergation.testcontrollers;

import com.google.zxing.WriterException;
import com.schweizerischebundesbahnen.exceptions.SeatAlreadyExistException;
import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.repository.RideRepository;
import com.schweizerischebundesbahnen.repository.SeatRepository;
import com.schweizerischebundesbahnen.repository.TicketRepository;
import com.schweizerischebundesbahnen.restcontroller.PurchaseController;
import com.schweizerischebundesbahnen.service.api.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by aleksandrprendota on 20.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CuncurrentTest {

    private Schedule schedule;
    private Seat seat;
    private User customer1;
    private User customer2;
    private Ride ride;
    private Ticket ticket;

    @Autowired
    private PurchaseController purchaseController;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RideService rideService;

    @Autowired
    private TicketService ticketService;

    @Before
    public void setup(){

        customer1 = userService.getUserById(212L);
        customer2 = userService.getUserById(228L);
        seat = seatRepository.findOne(953L);
        schedule = scheduleService.findScheduleById(1699L);

    }


    @Test
    public void cuncurrentTest() throws BrokenBarrierException, InterruptedException {

        // We want to start just 2 threads at the same time, but let's control that
        // timing from the main thread. That's why we have 3 "parties" instead of 2.
        final CyclicBarrier gate = new CyclicBarrier(3);

        Thread t1 = new Thread(() -> {
            try {
                gate.await();
                System.out.println("*********");
                System.out.println("Customer 1 try to buy...");
                System.out.println("*********");

                try{
                    ride = purchaseController.buyTheRide(customer1,schedule,seat);
                    ticket = ride.getTicket();

                    System.out.println("*********");
                    System.out.println("Customer 1 DONE...");
                    System.out.println("*********");

                } catch (SeatAlreadyExistException e){
                    System.out.println("Customer 1 cannot buy the ride :(");
                }


            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        });
        Thread t2 = new Thread(() -> {
            try {

                gate.await();
                System.out.println("*********");
                System.out.println("Customer 2 try to buy...");
                System.out.println("*********");

                try{
                    ride = purchaseController.buyTheRide(customer2,schedule,seat);
                    ticket = ride.getTicket();

                    System.out.println("*********");
                    System.out.println("Customer 2 DONE...");
                    System.out.println("*********");

                } catch (SeatAlreadyExistException e){
                    System.out.println("Customer 2 cannot buy the ride :(");
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
    public void cleanTest(){
        rideService.delete(ride);
        ticketService.delete(ticket);
    }
}
