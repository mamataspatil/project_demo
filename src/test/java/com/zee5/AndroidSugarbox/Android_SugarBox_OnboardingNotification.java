package com.zee5.AndroidSugarbox;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_SugarBox_OnboardingNotification {
	
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws Exception {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void LoginToApp(String pUserType) throws Exception { 
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(pUserType);
	}
	
	@Test(priority = 1)		// Need to Pass the valid Phone Number to get OTP
	@Parameters({"SugarBoxPhoneNum"})
	public void OnboardingToSugarBoxNetwork(String PhoneNumber) throws Exception { 
		ZEE5ApplicasterBusinessLogic.onboardingToSugarboxNetwork(PhoneNumber);
	}
	
	@Test(priority = 2)
	@Parameters({"NewUserNotificationTitle","NewUserNotificationSubTitle"})
	public void VerifySugarBoxNotification(String pTitle, String pSubTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.verifySugarBoxNotification(pTitle,pSubTitle);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType" })
	public void WelcomeBackScreenInSugarboxNetwork(String pUserType) throws Exception {
		ZEE5ApplicasterBusinessLogic.verifyWelcomeBackScreenInSugarboxNetwork(pUserType);
	}
	
	@Test(priority = 4)
	@Parameters({"ExistingUserNotificationTitle","ExistingUserNotificationSubTitle"})
	public void VerifySugarBoxExistingUserNotification(String pTitle, String pSubTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.verifySugarBoxNotification(pTitle,pSubTitle);
	}
	
	@Test(priority = 5)
	public void DiconnectionValidationFromSugarBoxNetwork() throws Exception { 
		ZEE5ApplicasterBusinessLogic.sBDisconnectionValidation();
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
