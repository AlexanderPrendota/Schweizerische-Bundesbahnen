package com.schweizerischebundesbahnen.intergation.testcontrollers;

import com.schweizerischebundesbahnen.model.*;
import com.schweizerischebundesbahnen.repository.RideRepository;
import com.schweizerischebundesbahnen.repository.SeatRepository;
import com.schweizerischebundesbahnen.restcontroller.PurchaseController;
import com.schweizerischebundesbahnen.service.api.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    private Ride ride;
    private Ride ride1;
    private Ticket ticket;
    private Ticket ticket1;
    @Autowired
    private PurchaseController purchaseController;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RideRepository rideRepository;


    @Before
    public void setup(){
        User customer1 = userService.getUserById(212L);
        User customer2 = userService.getUserById(228L);
        seat = seatRepository.findOne(953L);
        schedule = scheduleService.findScheduleById(1699L);
        ticket = new Ticket();
        ticket.setId(100000L);
        ticket1 = new Ticket();
        ticket1.setId(100001L);
    }


    @Test
    public void cuncurrentTest() throws BrokenBarrierException, InterruptedException {

        // We want to start just 2 threads at the same time, but let's control that
        // timing from the main thread. That's why we have 3 "parties" instead of 2.
        final CyclicBarrier gate = new CyclicBarrier(3);

        Thread t1 = new Thread(() -> {
            try {
                gate.await();
                System.out.println("Customer 1 try buy...");
                ride1 = new Ride();
                ride1.setId(100000001L);
                ride1.setSeat(seat);
                ride1.setStationArrival(schedule.getStationArrival());
                ride1.setStationDeparture(schedule.getStationDeparture());
                ride1.setTicket(ticket1);
                ride1.setTimeArrival(schedule.getTimeArrival());
                ride1.setTimeDeparture(schedule.getTimeDeparture());
                rideRepository.save(ride1);
                Boolean isCust1Buy = purchaseController.checkValidSeat(String.valueOf(seat.getCabine()),
                        String.valueOf(seat.getNumber()),schedule.getTrain(),schedule.getTimeDeparture());
                System.out.println("Customer 1 done...");
                System.out.println("Result is " + isCust1Buy.toString());
                rideRepository.delete(ride1);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        });
        Thread t2 = new Thread(() -> {
            try {
                gate.await();
                System.out.println("Customer 2 try buy...");
                ride = new Ride();
                ride.setId(100000000L);
                ride.setSeat(seat);
                ride.setStationArrival(schedule.getStationArrival());
                ride.setStationDeparture(schedule.getStationDeparture());
                ride.setTicket(ticket);
                ride.setTimeArrival(schedule.getTimeArrival());
                ride.setTimeDeparture(schedule.getTimeDeparture());
                rideRepository.save(ride);
                Boolean isCust2Buy = purchaseController.checkValidSeat(String.valueOf(seat.getCabine()),
                        String.valueOf(seat.getNumber()),schedule.getTrain(),schedule.getTimeDeparture());
                System.out.println("Customer 2 done...");
                System.out.println("Result is " + isCust2Buy.toString());
                rideRepository.delete(ride);
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
    }
}
