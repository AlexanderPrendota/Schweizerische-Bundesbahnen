package com.schweizerischebundesbahnen;

import com.schweizerischebundesbahnen.intergation.DIServiceTests;
import com.schweizerischebundesbahnen.intergation.DIDaoTest;
import com.schweizerischebundesbahnen.intergation.testcontrollers.SecurityControllerTest;
import com.schweizerischebundesbahnen.intergation.testcontrollers.ViewControllerTest;
import com.schweizerischebundesbahnen.junittest.testcontrollersmock.*;
import com.schweizerischebundesbahnen.junittest.testmock.*;
import com.schweizerischebundesbahnen.junittest.testservice.*;
import com.schweizerischebundesbahnen.restcontroller.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by aleksandrprendota on 17.04.17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ViewControllerTest.class,
        SecurityControllerTest.class,
        AuthServiceTest.class,
        UserServiceTest.class,
        TrainServiceTest.class,
        TicketServiceTest.class,
        StorageServiceTest.class,
        StatisticServiceTest.class,
        ScheduleServiceTest.class,
        RideServiceTest.class,
        StationServiceTest.class,
        SeatServiceTest.class,
        MessageServiceTest.class,
        PriceServiceTest.class,
        DIServiceTests.class,
        DIDaoTest.class,
        UserServiceMockTest.class,
        TrainServiceMockTest.class,
        MessageServiceMockTest.class,
        StationServiceMockTest.class,
        ChatServiceMockTest.class,
        TicketServiceMockTest.class,
        SeatServiceMockTest.class,
        UserChatServiceMockTest.class,
        RideServiceTestMock.class,
        ScheduleServiceTestMock.class,
        StationControllerTest.class,
        PriceControllerTest.class,
        StationControllerTest.class,
        ScheduleControllerTest.class
     })
public class SwissrailwaysUnitTests {
}
