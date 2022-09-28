package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_LearningTab {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
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
	@Parameters({ "userType" }) 	
	public void LearningTabValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.learningLandingPage(userType);
		ZEE5ApplicasterBusinessLogic.verifyBuyPlan_on_ConsumptionPage_SubscriptionScreen(userType);
		ZEE5ApplicasterBusinessLogic.validationOfSubscriptionScreen_EduauraaContent(userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType" }) 	
	public void DemoVideo_Validation(String userType) throws Exception {
		if(!userType.contentEquals("Guest")) {
	    ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);	
		ZEE5ApplicasterBusinessLogic.demoVideo_Validation();
		}	
	}
	
	@Test(priority = 3)
	@Parameters({ "userType" }) 	
	public void FunctionalityOfKeepLearning(String userType) throws Exception {
		if(userType.contentEquals("SubscribedUser")) {
	    ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);	
		ZEE5ApplicasterBusinessLogic.keepLearningCTAsValidation();
		}	
	}
	
	@Test(priority = 4)
	@Parameters({ "userType" }) 	
	public void ExitZee5PopupValidation(String userType) throws Exception {
		if(userType.contentEquals("SubscribedUser")) {
	    ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);	
		ZEE5ApplicasterBusinessLogic.validateExitZee5Popup();	
		}	
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
	
}
