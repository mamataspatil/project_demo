package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_ScreenView {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		System.out.println("\nLogin");
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)
	@Parameters({"userType", "BottomNavigation"})
	public void verifyScreenViewEvent_BottomNavigation(String userType, String pTabName) throws Exception {
		System.out.println("Screen view event for Bottom Navigation");
		//Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_BottomNavigation(userType, pTabName);
	}
	
	@Test(priority = 2)
	@Parameters({"userType", "MoreMenuOptions"})
	public void verifyScreenViewEvent_MoreMenuOptions(String userType, String page) throws Exception {
		System.out.println("Screen view event for More menu options");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_MoreMenuOptions(userType, page);
	}
	
	@Test(priority = 3)
	@Parameters({"userType"})
	public void verifyScreenViewEvent_Search(String userType) throws Exception {
		System.out.println("Screen view event for Search");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_SearchPage(userType);
	}
	
	@Test(priority = 4)
	@Parameters({"userType"})
	public void verifyScreenViewEvent_SplashPage(String userType) throws Exception {
		System.out.println("Screen view event for Splash page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_SplashPage(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}


}
