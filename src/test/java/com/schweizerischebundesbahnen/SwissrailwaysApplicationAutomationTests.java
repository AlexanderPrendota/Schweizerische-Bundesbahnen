package com.schweizerischebundesbahnen;

import com.schweizerischebundesbahnen.automationtest.admin.*;
import com.schweizerischebundesbahnen.automationtest.user.*;
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
		AutomationFindScheduleInAdminPage.class,
		AutomationFindUserInAdminPage.class,
		AutomationCheckAdminParInAccountPage.class,
		AutomationCheckSupportInAdminPage.class,
		AutomationCheckNotifyInAdminPage.class,
		AutomationCheckAdminParInAccountPage.class,
		AutomationCheckNotSupportInAccountPageAdmin.class,
		AutomationCheckNewDialogInAdminPageOnSchedule.class,
		AutomationCheckNewDialogInAdminPageOnStation.class,
		AutomationCheckNewDialogInAdminPageOnTrain.class,
		AutomationSmartSearch.class,
		AutomationSmartSearchNot.class,
		AutomationFindSupportChat.class,
		AutomationFindSupportButton.class
		})
public class SwissrailwaysApplicationAutomationTests {


}
