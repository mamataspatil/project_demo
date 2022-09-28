package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.business.zee.Zee5PWASanityAndroidBusinessLogic;
import com.utility.Utilities;

public class Android_Conviva {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	private Zee5PWASanityAndroidBusinessLogic Zee5PWASanityBusinessLogic;
	String ipaddress = "";
	
	@BeforeTest
	public void AppLaunch() throws Exception {
		
		//Get IP address
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Chrome");
		ipaddress = Zee5PWASanityBusinessLogic.getYourPublicIP();
		Zee5PWASanityBusinessLogic.tearDown();
		
		System.out.println("Launching Android App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
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
	@Parameters({ "userType" })
	public void Conviva(String userType) throws Exception {
		System.out.println("\nVerify Conviva");
		ZEE5ApplicasterBusinessLogic.convivaVerification(userType,ipaddress);
	}
	

	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
}
