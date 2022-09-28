package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Vi {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws Exception {
		System.out.println("Launching Android App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("Vodafone");
	}

	// @Test(priority = 0)
	@Parameters({ "userType" })
	public void accessDeviceLocation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void Vi_Validation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.loginVi(userType, "14 Phere");
		ZEE5ApplicasterBusinessLogic.vi_ZEEMoreMenuValidation(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void Vi_Zee_Register(String userType) throws Exception {
		// ZEE5ApplicasterBusinessLogic.loginVi(userType,"14 Phere");
		ZEE5ApplicasterBusinessLogic.zee_Register(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void Vi_PackValidation(String userType) throws Exception {
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("Vodafone");
		ZEE5ApplicasterBusinessLogic.vi_PackOverride(userType);
	}

//		@Test(priority = 4)
	@Parameters({ "userType" })
	public void Vi_ParentalValidation(String userType) throws Exception {
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("Vodafone");
		ZEE5ApplicasterBusinessLogic.loginVi(userType, "14 Phere");
		ZEE5ApplicasterBusinessLogic.vi_ParentalControlPopupPinValidation(userType);
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void Vi_DeeplinkValidation(String userType) throws Exception {
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("Vodafone");
		ZEE5ApplicasterBusinessLogic.loginVi(userType, "14 Phere");
		ZEE5ApplicasterBusinessLogic.vi_Deeplink(userType);
	}

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void Vi(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.zee5ViValidation(userType);
		ZEE5ApplicasterBusinessLogic.ViClassicuser(userType);
	}

//		@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}