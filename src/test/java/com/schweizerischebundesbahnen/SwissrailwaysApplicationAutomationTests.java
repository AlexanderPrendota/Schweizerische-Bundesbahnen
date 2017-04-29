package com.schweizerischebundesbahnen;

import com.schweizerischebundesbahnen.automationtest.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
		AutomationAccountValidParamsTest.class,
		AutomationChangeUserParams.class,
        AutomationAuthenticationTest.class,
		AutomationLogoutTest.class,
		AutomationAdminAuthTest.class,
		AutomationCheckTrainInAdminPageTest.class,
		AutomationFindUserOnTrainOnAdminPage.class,
		AutomationCheckValidUserPurchaseTest.class,
		AutomationFindRidesTestByTwoStationsAndDate.class,
		AutomationMakePurchaseByTwoStationAndDate.class,
		AutomationFindRidesTestByOneStation.class,
		AutomationNoWaysByOneStation.class,
		AutomationMakePurchaseByOneStation.class,
		AutomocaticNoWaysByTwoStationAndDate.class,
		AutomationFindStationOnAdminPage.class,
		AutomationFindScheduleInAdminPage.class
		})
public class SwissrailwaysApplicationAutomationTests {


}
