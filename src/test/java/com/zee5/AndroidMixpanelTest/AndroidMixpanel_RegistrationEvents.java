package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_RegistrationEvents {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}
	
	@Test(priority = 1)
	@Parameters({"userType"})
	public void MixPanel_RegistrationEventsValidation(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidation(pUserType);
	}

	
	@Test(priority = 2)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlan(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlan(pUserType);
	}
	
	@Test(priority = 3)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlanMoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanMoreSection(userType);
	}
	
	@Test(priority = 4)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlanMySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanMySubscription(userType);
	}
	
	@Test(priority = 5)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlanMyTransaction(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanMyTransaction(userType);
	}
	
	@Test(priority = 6)
	@Parameters({"userType", "keyword3"})
	public void RegistrationEventValidationViaBuyPlanBelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanBelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 7)
	@Parameters({"userType", "trailerContent"})
	public void RegistrationEventValidationViaBuyPlanAfterTrailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanAfterTrailer(userType,trailerContent);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
