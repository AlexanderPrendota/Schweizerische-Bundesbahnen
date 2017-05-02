package com.schweizerischebundesbahnen.junittest.testservice;

import com.schweizerischebundesbahnen.model.Schedule;
import com.schweizerischebundesbahnen.model.Station;
import com.schweizerischebundesbahnen.model.Train;
import com.schweizerischebundesbahnen.service.api.ScheduleService;
import com.schweizerischebundesbahnen.service.api.StationService;
import com.schweizerischebundesbahnen.service.api.TrainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 04.04.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Test
    public void testFindScheduleById(){
        Assert.assertEquals(scheduleService.findScheduleById(1011L).getId(),1011L);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindScheduleByNullId(){
        scheduleService.findScheduleById(null);
    }

    @Test
    public void testFundScheduleByWrongId(){
        Schedule schedule = scheduleService.findScheduleById(1L);
        Assert.assertNull(schedule);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFundScheduleByNull(){
        Schedule schedule = scheduleService.findScheduleById(null);
        Assert.assertNull(schedule);
    }

    @Test
    public void checkScheduleFindByTrainNull(){
        List<Schedule> schedules = scheduleService.findByTrain(null);
        Assert.assertTrue(schedules.size() == 0);

    }

    @Test
    public void checkRideFindByTrain(){
        Train train = trainService.findTrainByName("F01");
        List<Schedule> schedules = scheduleService.findByTrain(train);
        Assert.assertTrue(schedules.size() > 0);
    }

    @Test
    public void checkRideByIncorrectTrain(){
        Train train = trainService.findTrainByName("A");
        List<Schedule> schedules = scheduleService.findByTrain(train);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void checkRideByEmptyStringTrain(){
        Train train = trainService.findTrainByName("");
        List<Schedule> schedules = scheduleService.findByTrain(train);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void checkRideFindByEmpryObjectTrain(){
        List<Schedule> schedules = scheduleService.findByTrain(new Train());
        Assert.assertTrue(schedules.size() == 0);

    }

    @Test
    public void testFindScheduleByStationDeparture(){
        Station station  = stationService.findStationByName("London");
        List<Schedule> schedules = scheduleService.findByStationDeparture(station);
        Assert.assertTrue(schedules.size() > 0);
    }

    @Test
    public void testFindScheduleByEmptyStationDeparture(){
        Station station  = stationService.findStationByName("");
        List<Schedule> schedules = scheduleService.findByStationDeparture(station);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testFindScheduleByIncorrectStationDeparture(){
        Station station  = stationService.findStationByName("A3");
        List<Schedule> schedules = scheduleService.findByStationDeparture(station);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindScheduleByEmptyObjectStationDeparture(){
        List<Schedule> schedules = scheduleService.findByStationDeparture(new Station());
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testFindScheduleByNullStationDeparture(){
        List<Schedule> schedules = scheduleService.findByStationDeparture(null);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testFindScheduleByStationArrival(){
        Station station  = stationService.findStationByName("Basel");
        List<Schedule> schedules = scheduleService.findByStationArrival(station);
        Assert.assertTrue(schedules.size() > 0);
    }

    @Test
    public void testFindScheduleByEmptyStationArrival(){
        Station station  = stationService.findStationByName("");
        List<Schedule> schedules = scheduleService.findByStationArrival(station);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testFindScheduleByIncorrectStationArrival(){
        Station station  = stationService.findStationByName("A4");
        List<Schedule> schedules = scheduleService.findByStationArrival(station);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindScheduleByEmptyObjectStationArrival(){
        List<Schedule> schedules = scheduleService.findByStationArrival(new Station());
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testFindScheduleByNullStationArrival(){
        List<Schedule> schedules = scheduleService.findByStationArrival(null);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testAllScheduled(){
        List<Schedule> schedules = scheduleService.findAllSchedules();
        Assert.assertTrue(schedules.size() > 0);
    }

    @Test
    public void testScheduleFindUserRides(){
        Station stationD  = stationService.findStationByName("London");
        Station stationA  = stationService.findStationByName("Basel");
        Date startDate = new Date(1494460800000L);
        Date endDate = new Date(startDate.getTime() + 86399L * 1000);
        List<Schedule> schedules = scheduleService.findUserRides(stationD,stationA,startDate,endDate);
        Assert.assertTrue(schedules.size() > 0);
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testScheduleFindUserRidesWithNullParams(){
        List<Schedule> schedules = scheduleService.findUserRides(null,null,null,null);
        Assert.assertTrue(schedules.size() == 0);

    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testScheduleFindUserRidesWithAllEmptyObjects(){
        List<Schedule> schedules = scheduleService.findUserRides(new Station(),new Station(),new Date(),new Date());
        Assert.assertTrue(schedules.size() == 0);

    }

    @Test
    public void testScheduleFindUserRidesWithWrongParams(){
        Station stationD  = stationService.findStationByName("Moscow");
        Station stationA  = stationService.findStationByName("Reinach");
        Date startDate = new Date(1493253300000L);
        Date endDate = new Date(startDate.getTime() + 86399L * 1000);
        List<Schedule> schedules = scheduleService.findUserRides(stationD,stationA,startDate,endDate);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testFindScheduleByUserStationMoreToday(){
        Station station  = stationService.findStationByName("Yaroslavl");
        List<Schedule> schedules = scheduleService.findByUserStation(station);
        Assert.assertTrue(schedules.size() > 0);

    }

    @Test
    public void testFindScheduleByUserStationMoreTodayIncorectStation(){
        Station station  = stationService.findStationByName("Incorrect");
        List<Schedule> schedules = scheduleService.findByUserStation(station);
        Assert.assertTrue(schedules.size() == 0);

    }

    @Test
    public void testFindScheduleByUserNullStationMoreToday(){
        List<Schedule> schedules = scheduleService.findByUserStation(null);
        Assert.assertTrue(schedules.size() == 0);

    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindScheduleByUserEmptyStationStationMoreToday(){
        List<Schedule> schedules = scheduleService.findByUserStation(new Station());
        Assert.assertTrue(schedules.size() == 0);

    }

    @Test
    public void testTransferSchedule() throws ParseException{
        Station firstStation  = stationService.findStationByName("Yaroslavl");
        Station secondStation  = stationService.findStationByName("Basel");
        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatData.parse("2017-06-01");
        List<Schedule> schedulesTransfer = scheduleService.findTransferSchedule(firstStation,secondStation,date);
        Assert.assertTrue(schedulesTransfer.size() > 0);
    }

    @Test
    public void testTransferScheduleWithWrongDate() throws ParseException{
        Station firstStation  = stationService.findStationByName("Yaroslavl");
        Station secondStation  = stationService.findStationByName("Basel");
        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatData.parse("2017-06-02");
        List<Schedule> schedulesTransfer = scheduleService.findTransferSchedule(firstStation,secondStation,date);
        Assert.assertTrue(schedulesTransfer.size() == 0);
    }

    @Test
    public void testFindScheduleByTimeDepartureMore(){
        Date date = new Date(1494460800000L);
        List<Schedule> schedules = scheduleService.findByTimeDepartureMoreThan(date);
        Assert.assertTrue(schedules.size() > 0);
    }

    @Test
    public void testFindScheduleByTimeDepartureMoreTODAY(){
        Date date = Calendar.getInstance().getTime();
        List<Schedule> schedules = scheduleService.findByTimeDepartureMoreThan(date);
        Assert.assertTrue(schedules.size() > 0);
    }

    @Test
    public void testFindScheduleByTimeDepartureMoreThanWrongDate(){
        Date date = new Date(4106332800000L);
        List<Schedule> schedules = scheduleService.findByTimeDepartureMoreThan(date);
        Assert.assertTrue(schedules.size() == 0);
    }

    @Test
    public void testFindScheduleByTimeDepartureMoreWithEmptyObject(){
        System.out.println(new Date());
        List<Schedule> schedules = scheduleService.findByTimeDepartureMoreThan(new Date());
        Assert.assertTrue(schedules.size() > 0);
    }


    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testFindScheduleByTimeDepartureMoreWithNull(){
        List<Schedule> schedules = scheduleService.findByTimeDepartureMoreThan(null);
        Assert.assertNull(schedules);
    }




}

