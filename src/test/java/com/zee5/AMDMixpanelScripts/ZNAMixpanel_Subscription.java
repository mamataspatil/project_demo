package com.zee5.AMDMixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_Subscription {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		System.out.println("\nLogin");
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType","freeContentID","freeContent"})
	public void SubscriptionPageEvent(String userType,String freeContentID,String freeContent) throws Exception {
		System.out.println("\n Subscription Page event of free content");
		Zee5ApplicasterMixPanelBusinessLogic.SubscriptionPageEvent(userType);
	}
	
//	@Test(priority = 3)
	@Parameters({ "userType","trailerContentID","trailerContent"})
	public void SubscriptionSelectedEvent(String userType,String trailerContentID,String trailerContent) throws Exception {
		System.out.println("\nSubscription Selected event of trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		
	}
	
//	@Test(priority = 4)
	@Parameters({ "userType","premiumContentID","premiumContent"})
	public void SubscriptionCallInitiatedEvent(String userType,String premiumContentID,String premiumContent) throws Exception {
		System.out.println("\nSubscription Call Initiated event of premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.VideoExitEvent(userType,"Premium",premiumContentID,premiumContent);
	}
		
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
