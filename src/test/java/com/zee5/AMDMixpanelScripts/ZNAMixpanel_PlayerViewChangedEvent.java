package com.zee5.AMDMixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_PlayerViewChangedEvent {
	
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
	
	//###############  PLAYER VIEW CHANGED EVENT FROM SEARCH TAB  ###############
	
	@Test(priority = 2)
	@Parameters({ "userType","freeContentID","freeContent"})
	public void PlayerViewChangedEventForFreeContentFromSearchPage(String userType,String freeContentID,String freeContent) throws Exception {
		System.out.println("\n Player View Changed event of free content");
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEvent(userType,"Free",freeContentID,freeContent);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType","trailerContentID","trailerContent"})
	public void PlayerViewChangedEventForTrailerContentFromSearchPage(String userType,String trailerContentID,String trailerContent) throws Exception {
		System.out.println("\nPlayer View Changed event of trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEvent(userType,"Trailer",trailerContentID,trailerContent);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType","premiumContentID","premiumContent"})
	public void PlayerViewChangedEventForPremiumContentFromSearchPage(String userType,String premiumContentID,String premiumContent) throws Exception {
		System.out.println("\nPlayer View Changed event of premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEvent(userType,"Premium",premiumContentID,premiumContent);
	}

	//###############-------END OF TEST-------###############
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}
