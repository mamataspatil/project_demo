package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;


public class Android_AppLaunch {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" }) 	// Hitesh
	public void accessDeviceLocation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
	}

	
	@Test(priority = 1)
	@Parameters({ "userType" })		// Manasa
	public void ApplicasterLogin(String userType) throws Exception {
		System.out.println("\nVerify Display Language Screen and login flow for various usertypes");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 2)		// Manasa
	public void offlineScreenValidation() throws Exception {
		System.out.println("\nVerify App Screen in Offline Mode");
		ZEE5ApplicasterBusinessLogic.offlineScreenValidation();
	}
	
	@Test(priority = 3)
	@Parameters({"userType"})  //Shreenidhi==Scenarios only for NonSub and Sub user
	public void ValidateFieldsAfterRelaunch(String userType) throws Exception {
		System.out.println("\nVerify App relaunch functionality");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.checkScreenAfterRelaunch(userType, "Content language screen");
	}
	
	@Test(priority = 4)
	@Parameters({ "userType"})
	public void TapVideoInOfflineMode(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
	    ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.tapOnVideoInOfflineMode();
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
}
