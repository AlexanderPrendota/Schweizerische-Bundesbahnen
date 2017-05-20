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
		AutomationCheckCorrectMoveToMultiPurchase.class,
		AutomationFindRidesTestByOneStation.class,
		AutomationNoWaysByOneStation.class,
		AutomationMakePurchaseByOneStation.class,
		AutomocaticNoWaysByTwoStationAndDate.class,
		AutomationFindStationOnAdminPage.class,
		AutomationFindScheduleInAdminPage.class,
		AutomationFindUserInAdminPage.class,
		AutomationCheckAdminParInAccountPage.class,
		AutomationCheckSupportInAdminPage.class,
		AutomationCheckStatistics.class,
		AutomationCheckBusySeats.class,
		AutomationCheckNotifyInAdminPage.class,
		AutomationCheckAdminParInAccountPage.class,
		AutomationCheckNotSupportInAccountPageAdmin.class,
		AutomationCheckNewDialogInAdminPageOnSchedule.class,
		AutomationCheckNewDialogInAdminPageOnStation.class,
		AutomationCheckNewDialogInAdminPageOnTrain.class,
		AutomationSmartSearch.class,
		AutomationSmartSearchNot.class,
		AutomationFindSupportChat.class,
		AutomationFindSupportButton.class,
		AutomationFindButtonsLoadImage.class,
		AutomationCheckSVGImgOnSchedule.class,
		AutomationCheckSVGImgOnHome.class,
		AutomationCheckRideDetail.class,
		AutomationBuyTicketAfterSmartSearch.class,
		AutomationViewSittingInPurchase.class
		})
public class SwissrailwaysApplicationAutomationTests {

	// Use for locale tests

//	public static final String ADMIN_URL = "http://localhost:8080/admin";
//	public static final String ACCOUNT_URL = "http://localhost:8080/account";
//	public static final String HOME_URL = "http://localhost:8080/home";

	// Use for main tests

	public static final String ADMIN_URL = "http://37.139.26.89:8080/admin";
	public static final String ACCOUNT_URL = "http://37.139.26.89:8080/account";
	public static final String HOME_URL = "http://37.139.26.89:8080/home";
}
