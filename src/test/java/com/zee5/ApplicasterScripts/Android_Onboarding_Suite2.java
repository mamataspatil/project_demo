package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Onboarding_Suite2 {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLoginForOnboarding();
	}
	
//	@Test (priority = 1)  //---- Have a prepaid code is removed in the intro screen
	@Parameters({"userType"})	//  Kushal
	public void HaveAPrepaidCodeUIVerification(String userType) throws Exception {
		System.out.println("\nVerify Have a Prepaid Code PopUp Screen");
		ZEE5ApplicasterBusinessLogic.verifyHaveAPrepaidCodePopUp();
	}
	
//	@Test (priority = 2) //Test suite is not valid,skip link in Intro screen is not present as Intro screen has been removed.
	@Parameters({"userType"})	//  Kushal
	public void BrowseforFreeSkipLoginRegistration(String userType) throws Exception {
		System.out.println("\nBrowse for Free - Skip Login/Register page to Home Screen");
		if(userType.contentEquals("Guest")) {
			ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		}
		ZEE5ApplicasterBusinessLogic.verifySkipLoginRegistrationScreen();
	}
	
	@Test (priority = 3)	//  Kushal
	@Parameters({"userType","RegisteredEmail","RegisteredEmailPassword"})
	public void LoginwithEmailID(String userType,String pEmailId,String pPassword) throws Exception {
		System.out.println("\n Login with EmailID");
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyLoginWithEmailId(pEmailId, pPassword);
	}
	
//	@Test (priority = 4)	//  Kushal   //---- Have a prepaid code is removed in the Intro screen
	@Parameters({"userType","NonsubscribedUserName","NonsubscribedPassword"})
	public void InvalidPrepaidCodeValidationAfterLogin(String userType,String pEmailId,String pPassword) throws Exception {
		System.out.println("\nVerify Invalid Prepaid Code PopUp for registered user");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Deny", userType);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLoginForOnboarding();
		ZEE5ApplicasterBusinessLogic.verifyInvalidPrepaidCodePopUpAfterLogin(pEmailId, pPassword);
	}
	
//	@Test (priority = 5)	//  Kushal //---- Have a prepaid code is removed in the Intro screen
	@Parameters({"userType"})
	public void InvalidPrepaidCodeValidationAfterRegistration(String userType) throws Exception {
		System.out.println("\nVerify Invalid Prepaid Code PopUp for unregistered user");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Deny", userType);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLoginForOnboarding();
		ZEE5ApplicasterBusinessLogic.verifyInvalidPrepaidCodePopUpAfterRegistration();
	}
	

//	@Test (priority = 9)	//  Kushal  - Have a prepaid code is removed in the Intro screen [Need a VALID Prepaid Code to execute this test]
	@Parameters({"userType","PrepaidCode","regUserName","regPassword"})
	public void ApplyValidPrepaidCodeforRegisteredUser(String userType, String pCode, String pUserName, String pPassword) throws Exception {
		System.out.println("\nVerify Successful message for valid Prepaid Code for registered user");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.verifyCongratulationPopupAppearsforValidPrepaidCode(pCode, pUserName, pPassword);
	}
	
	@Test(priority = 10)
	@Parameters({"DeeplinkToLoginScreenFrmEmail","userType"})	//---Author: Kushal
	public void DeeplinkToLoginScreenFromEmailNoticication(String pDeeplinkUrl,String pUserType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", pUserType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.deeplinkToLoginScreenFromEmailNotification(pDeeplinkUrl,pUserType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Quiting the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
