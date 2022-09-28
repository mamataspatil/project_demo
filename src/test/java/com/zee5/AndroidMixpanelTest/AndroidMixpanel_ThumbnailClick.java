package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_ThumbnailClick {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)
	@Parameters({"userType","pTabName"})
	public void VerifyThumbnailClickEvent_LandingPage(String userType,String pTabName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.ThumbnailClickEventValidationFromLandingPage(userType,pTabName);
	}
	
	@Test(priority = 2)
	@Parameters({"userType","clipContent", "clipID", "clipContentID"})
	public void VerifyThumbnailClickEvent_ConsumptionPage(String userType,String clipContent, String clipID, String clipContentID) throws Exception {
		System.out.println("Thumbnail Click event through consumption page rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.ThumbnailClickEventValidationFromConsumptionPage(userType,  clipContent, clipID, clipContentID);
	}
	
	
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
