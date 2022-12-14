package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Player_LandscapeMode {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Android App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void accessDeviceLocation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		System.out.println("\nVerify Display Language Screen and login flow for various usertypes");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "searchKeyword1", "searchKeyword3", "searchKeyword4", "searchKeyword5", "searchKeyword8",
			"searchKeyword9" }) // Manasa
	public void verifyPlayerScreenInLandscapeMode(String userType, String searchKeyword1, String searchKeyword3,
			String searchKeyword4, String searchKeyword5, String searchKeyword8, String searchKeyword9)
			throws Exception {
		System.out.println("\nVerify Player Functionality in Landscape Mode");
		ZEE5ApplicasterBusinessLogic.nextAndPreviousIconValidation(searchKeyword8);
		ZEE5ApplicasterBusinessLogic.skipIntroValidationInLandscapeMode(searchKeyword3, userType);
		ZEE5ApplicasterBusinessLogic.subtitleAndPlaybackRateValidation(searchKeyword4, userType);
		ZEE5ApplicasterBusinessLogic.premiumContentWithoutTrailerInLandscapeMode(userType, searchKeyword5);
		ZEE5ApplicasterBusinessLogic.upnextRailValidationInLandscapeMode(searchKeyword8);
//		ZEE5ApplicasterBusinessLogic.playerValidationInFullScreenMode(userType, searchKeyword1);
		ZEE5ApplicasterBusinessLogic.watchCreditsValidationInLandscapeMode(searchKeyword9, userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType", "searchKeyword1" })
	public void parentalPinPopUpValidationInLandscapeMode(String userType, String searchKeyword1) throws Exception {
		System.out.println("\nParental Pin PopUp Validation in Landscape Mode");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLoginForSettings(userType);
		ZEE5ApplicasterBusinessLogic.parentalPinValidationInLandscapeMode(userType, searchKeyword1);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Quiting the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
