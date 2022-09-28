package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_SubscriptionPageViewed {
	
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
	@Parameters({"userType"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_Header(String userType) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through Header");
		//Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_Header(userType);
	}
	
	@Test(priority = 2)
	@Parameters({"userType"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_MoreSection(String userType) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through More section");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_MoreSection(userType);
	}
	
	@Test(priority = 3)
	@Parameters({"userType"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_MySubscription(String userType) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through My Subscription");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_MySubscription(userType);
	}
	
	@Test(priority = 4)
	@Parameters({"userType", "keyword2"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_BelowThePlayer(String userType, String keyword2) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through below the player");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_BelowThePlayer(userType, keyword2);
	}
	
	@Test(priority = 5)
	@Parameters({"userType", "trailerContent"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_AfterCompletionOfTrailer_OnPlayer(String userType, String trailerContent) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through after completion of trailer on the player");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_AfterCompletionOfTrailerOnThePlayer(userType, trailerContent);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
