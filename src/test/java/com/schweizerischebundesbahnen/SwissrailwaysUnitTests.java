package com.schweizerischebundesbahnen;

import com.schweizerischebundesbahnen.intergation.DIServiceTests;
import com.schweizerischebundesbahnen.intergation.DIDaoTest;
import com.schweizerischebundesbahnen.junittest.testcontrollers.SecurityControllerTest;
import com.schweizerischebundesbahnen.junittest.testcontrollers.ViewControllerTest;
import com.schweizerischebundesbahnen.junittest.testservice.*;
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
        DIDaoTest.class})
public class SwissrailwaysUnitTests {
}
