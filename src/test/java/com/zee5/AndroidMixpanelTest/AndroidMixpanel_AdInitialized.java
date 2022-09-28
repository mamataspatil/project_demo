package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_AdInitialized {
	
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
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType", "clipContent" })
	public void AdInitializedEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nAd initialized event of content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
//		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "pTabName" })
	public void AdInitializedEvent_Carousal(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventForCarouselContent(usertype, pTabName);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "clipContent", "clipID", "clipContentID" })
	public void AdInitializedEvent_ConsumptionPageRail(String usertype, String clipContent, String clipID, String clipContentID) throws Exception {
		System.out.println("\nAd initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventOfcontentFromConsumptionPageRail(usertype, clipContent, clipID, clipContentID);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "pTabName" })
	public void AdInitializedEvent_ContinueWatchingRail(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd Initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedForContinueWatchingTray(usertype, pTabName);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType", "pTabName" })
	public void AdInitializedEvent_TrayNavigation(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd Initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventForTrayNavigation(usertype, pTabName);
	}

	//###############-------END OF TEST-------###############
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
