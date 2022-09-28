package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_LoginInitiated {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({"userType"})
	public void AndroidMixPanel_LoginInitiatedEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEvent(userType, "mobileNumberLogin");
	}
	
	@Test(priority = 2)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanHeader(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanHeader(userType);
	}
	
	@Test(priority = 3)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanMoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanMoreSection(userType);
	}
	
	@Test(priority = 4)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanMySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanMySubscription(userType);
	}
	
	@Test(priority = 5)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanMyTransactions(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanMyTransactions(userType);
	}
	
	@Test(priority = 6)
	@Parameters({"userType", "keyword3"})
	public void verifyLoginInitiatedEventViaBuyPlanBelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanBelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 7)
	@Parameters({"userType", "trailerContent"})
	public void verifyLoginInitiatedEventViaBuyPlan_Trailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlan_Trailer(userType,trailerContent);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
	
}
