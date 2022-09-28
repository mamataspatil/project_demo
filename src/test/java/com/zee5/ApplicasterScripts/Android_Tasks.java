package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Tasks {
	
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Android App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)
	public void ValidationOfHipiLogo_BottomNavigationBar() throws Exception {
		ZEE5ApplicasterBusinessLogic.hipLogo_BottomNavigationBar();
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void WatchTrailerIcon(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.watchTrailer(userType);
	}
	
	@Test(priority = 3)
	public void EduauraaTab_includedIn_TopNavigationBar() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.eduauraa_TopNavBar();
	}
	
	@Test(priority = 4)
	@Parameters({ "userType" })
	public void Removal_MandatoryRegistrationPopUp_NewsContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.mandatoryRegistration_NewsContent(userType);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType" })
	public void EduauraaIntegrationFlow(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.eduauraa(userType);
	}
	
	@Test(priority = 6)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfHipiLogoAndDurationOnSubscriptionPlan(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.hipiLogoAndDurationOnSubscriptionPlan(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
	

}
