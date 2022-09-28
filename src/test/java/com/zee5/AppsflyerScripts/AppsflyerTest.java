package com.zee5.AppsflyerScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5AppsFlyerBusinessLogic;
import com.utility.Utilities;

public class AppsflyerTest {

	
	private Zee5AppsFlyerBusinessLogic Zee5AppsFlyerBusinessLogic;
	
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		Zee5AppsFlyerBusinessLogic = new Zee5AppsFlyerBusinessLogic("zee");
	}
	
	
	
//	@Test(priority = 1)
	@Parameters({"userType"})  
	public void AppsFlyerNavigationEventCheck(String userType) throws Exception {
		//Clear App Data
		Zee5AppsFlyerBusinessLogic.clearAppData("zee");
		//Trigger AppsFlyerLink to capture events
		Zee5AppsFlyerBusinessLogic.triggerAppsflyerLink("zee");
		//launch zee5 app through playstore and perform functionality
		Zee5AppsFlyerBusinessLogic.launchapp();

		//Functionality
		Zee5AppsFlyerBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5AppsFlyerBusinessLogic.DisplayAndContentLanguage(userType);
		Zee5AppsFlyerBusinessLogic.selectCountry();
		Zee5AppsFlyerBusinessLogic.ZeeApplicasterLogin(userType);

		
		//Navigation Functionality
		Zee5AppsFlyerBusinessLogic.tabViewFunctionality();
		Zee5AppsFlyerBusinessLogic.ScreenViewFunctionality();

		//Download the funcitonality event report
		Zee5AppsFlyerBusinessLogic.downloadAppsFlyerReport();

		//Extract the Events report
		Zee5AppsFlyerBusinessLogic.extractFilesForValidation(userType);
	}

	
	
//	@Test(priority = 2)
	@Parameters({"userType"})  
	public void AppsFlyerConsumptionandBuyPlanEventCheck(String userType) throws Exception {
		//Clear App Data
		Zee5AppsFlyerBusinessLogic.clearAppData("zee");
		//Trigger AppsFlyerLink to capture events
		Zee5AppsFlyerBusinessLogic.triggerAppsflyerLink("zee");
		//launch zee5 app through playstore and perform functionality
		Zee5AppsFlyerBusinessLogic.launchapp();

		//Functionality
		Zee5AppsFlyerBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5AppsFlyerBusinessLogic.DisplayAndContentLanguage(userType);
		Zee5AppsFlyerBusinessLogic.selectCountry();
		Zee5AppsFlyerBusinessLogic.ZeeApplicasterLogin(userType);

		//Consumption Functionality
		Zee5AppsFlyerBusinessLogic.OriginalContentPlayFunctionality();
		Zee5AppsFlyerBusinessLogic.BuyPlanFunctionality(userType);
		Zee5AppsFlyerBusinessLogic.MusicPlayFunctionality();

		//Download the funcitonality event report
		Zee5AppsFlyerBusinessLogic.downloadAppsFlyerReport();

		//Extract the Events report
		Zee5AppsFlyerBusinessLogic.extractFilesForValidationConsumption(userType);
	}

	
	
	
	
	@Test(priority = 3)
	@Parameters({"userType"})  
	public void AppsFlyerRegisterAndLanguage(String userType) throws Exception {
		//Clear App Data
		Zee5AppsFlyerBusinessLogic.clearAppData("zee");
		//Trigger AppsFlyerLink to capture events
		Zee5AppsFlyerBusinessLogic.triggerAppsflyerLink("zee");
		//launch zee5 app through playstore and perform functionality
		Zee5AppsFlyerBusinessLogic.launchapp();

		//Functionality
		Zee5AppsFlyerBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5AppsFlyerBusinessLogic.DisplayAndContentLanguage(userType);
		Zee5AppsFlyerBusinessLogic.selectCountry();
		Zee5AppsFlyerBusinessLogic.ZeeApplicasterLogin(userType);
		
		Zee5AppsFlyerBusinessLogic.RegisterAndLanguageFunctionality(userType);
		

		//Download the funcitonality event report
		Zee5AppsFlyerBusinessLogic.downloadAppsFlyerReport();

		//Extract the Events report
		Zee5AppsFlyerBusinessLogic.extractFilesForValidationRegistration(userType);
	}
	
	
	
	

	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5AppsFlyerBusinessLogic.tearDown();
	}
	
	
	
}
