package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_PaymentScreenImpression {
	
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
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)
	@Parameters({ "userType"})
	public void PaymentScreenImpression_BuyPlan_Header(String usertype) throws Exception {
		System.out.println("\npayment screen impression through Buy plan header");
		//Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanHeader(usertype);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void PaymentScreenImpression_BuyPlan_MoreScreen(String usertype) throws Exception {
		System.out.println("\npayment screen impression through Buy plan from More screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanMoreScreen(usertype);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType"})
	public void PaymentScreenImpression_MySubscription_MoreScreen(String usertype) throws Exception {
		System.out.println("\npayment screen impression through MySubscription from More screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_MySubscriptionMoreScreen(usertype);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "keyword2"})
	public void PaymentScreenImpression_BuyPlan_BelowThePlayer(String usertype, String keyword) throws Exception {
		System.out.println("\npayment screen impression through Buy Plan Below the player");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanBelowThePlayer(usertype, keyword);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "trailerContent"})
	public void PaymentScreenImpression_BuyPlan_OnThePlayerPostTrailer(String usertype, String keyword) throws Exception {
		System.out.println("\npayment screen impression through Buy Plan on the player Post Trailer");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanPostTrailer(usertype, keyword);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
