package com.zee5.AMDMixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_Onboarding {
	
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
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)
	@Parameters({ "userType"}) //For Registered user only
	public void LoginInitiatedEvent(String userType) throws Exception {
		System.out.println("\nVerify Login Initiated Event on successfull login");
		Zee5ApplicasterMixPanelBusinessLogic.event_LoginInitiated(userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType"}) //For Registered user only
	public void LoginResultEvent(String userType) throws Exception {
		System.out.println("\nVerify Login Result Event on successfull login");
		Zee5ApplicasterMixPanelBusinessLogic.event_LoginResult(userType);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType"}) //For Registered user only
	public void LogOutEvent(String userType) throws Exception {
		System.out.println("\nVerify Logout Event");
		Zee5ApplicasterMixPanelBusinessLogic.logout();
		Zee5ApplicasterMixPanelBusinessLogic.event_LogOut(userType);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType"})
	public void LoginRegistrationScreenDisplayEventValidation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.newRegistrationThroughEmail();
		Zee5ApplicasterMixPanelBusinessLogic.event_LoginRegistrationScreenDisplayValiation(pUsertype);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType"})
	public void HomepageVisitedEventValidation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_HomePageVisitedValiation(pUsertype);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType"})
	public void ChangePasswordEventValidation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.verifyChangePasswordStartedEvent(pUsertype);
	}
	
	@Test(priority = 7)
	@Parameters({ "userType"})
	public void ChangePasswordResultEventValidation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_ChangePasswordResultEvent(pUsertype);
	}
	
	@Test(priority = 8)
	@Parameters({ "userType"})
	public void RegisterScreenDisplayValiation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_RegisterScreenDisplayValiation(pUsertype);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType"})
	public void RegistrationInitiatedValiation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_RegistrationInitiatedValiation(pUsertype);
	}
	
	@Test(priority = 10)
	@Parameters({ "userType"})
	public void RegistrationFirstNameEnteredValiation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_RegistrationFirstNameEnteredValiation(pUsertype);
	}
	
	@Test(priority = 11)
	@Parameters({ "userType"})
	public void RegistrationLastNameEnteredValiation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_RegistrationLastNameEnteredValiation(pUsertype);
	}
	
	@Test(priority = 12)
	@Parameters({ "userType"})
	public void RegistrationDoBEnteredValiation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_RegistrationDoBEnteredValiation(pUsertype);
	}
	
	@Test(priority = 13)
	@Parameters({ "userType"})
	public void RegistrationGenderEnteredValiation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_RegistrationGenderEnteredValiation(pUsertype);
	}
	
	@Test(priority = 14)
	@Parameters({ "userType"})
	public void RegistrationPasswordEnteredValiation(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.event_RegistrationPasswordEnteredValiation(pUsertype);
	}
	
	@Test(priority = 15)
	@Parameters({ "userType" })//Guest user only
	public void verifyLoginScreenDisplayEventThroughBrowseForFreeInWelcomeScreen(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Browse for free button in welcome screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginScreenDisplayEventThroughBrowseForScreen(userType);
	}
	
	@Test(priority = 16)//Guest user only
	@Parameters({ "userType"})
	public void verifyContentLanguageChangeFromWelcomPage(String userType) throws Exception {
		System.out.println("Verify Content Language change from Welcome page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchTillDisplayLanguageScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContinueLanguageFromWelcomePage(userType);
	}
	
	@Test(priority = 17)
	@Parameters({"userType"})
	public void verifyDisplayLanguageChangeEvent(String userType) throws Exception {
		System.out.println("Display language Change");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDisplayLanguageChangeEvent(userType);
	}
	
	@Test(priority = 18)//Guest user only
	@Parameters({ "userType" })
	public void verifyFirstLaunchEvent(String userType) throws Exception {
		System.out.println("Verify First launch Event");
		Zee5ApplicasterMixPanelBusinessLogic.VerifyFirstAppLaunchEvent(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
	
}
