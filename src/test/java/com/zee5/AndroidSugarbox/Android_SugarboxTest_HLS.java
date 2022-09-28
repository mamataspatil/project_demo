package com.zee5.AndroidSugarbox;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_SugarboxTest_HLS {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws Exception {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 0)		// Need to Pass the valid Phone Number to get OTP
	@Parameters({"SugarBoxPhoneNum"})
	public void OnboardingToSugarBoxNetwork(String PhoneNumber) throws Exception { 
		ZEE5ApplicasterBusinessLogic.onboardingToSugarboxNetwork(PhoneNumber);
	}
		
	@Test(priority = 1)
	@Parameters({ "userType" })
	public void LoginToApp(String pUserType) throws Exception { 
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(pUserType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType" })
	public void ContentPlaybackCheckInSugarBox(String pUserType) throws Exception { 
		ZEE5ApplicasterBusinessLogic.contentPlaybackVerificationInSugarboxNetwork(pUserType);
	}
	
	@Test(priority = 3)
	public void DiconnectFromSugarBoxNetwork() throws Exception { 
		ZEE5ApplicasterBusinessLogic.disconnectSugarBoxNetwork();
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
}
