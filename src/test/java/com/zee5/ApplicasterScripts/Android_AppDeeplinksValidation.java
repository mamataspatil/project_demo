package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_AppDeeplinksValidation {
	
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws Exception {
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority=0)
	@Parameters({"userType"})
	public void LoginToApp(String pUserType) throws Exception {
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(pUserType);
	}
	
	@Test(priority=1)
	@Parameters({"DeepLink_Home"})
	public void DeeplinkToHomePage(String pDeeplink) throws Exception {
		ZEE5ApplicasterBusinessLogic.quitZEE5App();
		ZEE5ApplicasterBusinessLogic.executeDeeplink(pDeeplink);
		ZEE5ApplicasterBusinessLogic.homeScreenViaDeeplink();
	}
	
	@Test(priority=2)
	@Parameters({"DeepLink_Settings"})
	public void DeeplinkToSettings(String pDeeplink) throws Exception {
		ZEE5ApplicasterBusinessLogic.quitZEE5App();
		ZEE5ApplicasterBusinessLogic.executeDeeplink(pDeeplink);
		ZEE5ApplicasterBusinessLogic.settingsScreenViaDeeplink();
	}
	
	@Test(priority=3)
	@Parameters({"userType","DeepLink_Watchlist"})
	public void DeeplinkToWatchlistScreen(String pUserType, String pDeeplink) throws Exception {
		ZEE5ApplicasterBusinessLogic.quitZEE5App();
		ZEE5ApplicasterBusinessLogic.executeDeeplink(pDeeplink);
		ZEE5ApplicasterBusinessLogic.watchlistScreenViaDeeplink(pUserType);
	}
	
	@Test(priority=4)
	@Parameters({"DeepLink_Subscription"})
	public void DeeplinkToSubscriptionScreen(String pDeeplink) throws Exception {
		ZEE5ApplicasterBusinessLogic.quitZEE5App();
		ZEE5ApplicasterBusinessLogic.executeDeeplink(pDeeplink);
		ZEE5ApplicasterBusinessLogic.subscriptionScreenViaDeeplink();
	}
	
	@Test(priority=5)
	@Parameters({"userType","DeepLink_Plans"})
	public void DeeplinkToMySubscriptionScreen(String pUserType, String pDeeplink) throws Exception {
		ZEE5ApplicasterBusinessLogic.quitZEE5App();
		ZEE5ApplicasterBusinessLogic.executeDeeplink(pDeeplink);
		ZEE5ApplicasterBusinessLogic.mySubscriptionScreenViaDeeplink(pUserType);
	}
	
	@Test(priority=6)
	@Parameters({"userType","DeepLink_Payments"})
	public void DeeplinkToMyTransactionsScreen(String pUserType, String pDeeplink) throws Exception {
		ZEE5ApplicasterBusinessLogic.quitZEE5App();
		ZEE5ApplicasterBusinessLogic.executeDeeplink(pDeeplink);
		ZEE5ApplicasterBusinessLogic.myTransactionsScreenViaDeeplink(pUserType);
	}
	
	@Test(priority=7)
	@Parameters({"userType","DeepLink_Edit"})
	public void DeeplinkToEditProfileScreen(String pUserType, String pDeeplink) throws Exception {
		ZEE5ApplicasterBusinessLogic.quitZEE5App();
		ZEE5ApplicasterBusinessLogic.executeDeeplink(pDeeplink);
		ZEE5ApplicasterBusinessLogic.editProfilePageViaDeeplink(pUserType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
