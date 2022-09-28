package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class RecommendedRailImpressionEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}
	
	@Test(priority = 1)
	@Parameters({ "userType" })
	public void verifyRecommendedRailImpressionEventByScrollingPage() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println(
				"Verify Recommended Rail Impression event when user is able to see the recommended tray by scrolling down the page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRecommendedRailImpressionEventByScrollingPage("Home", "Trending");
	}

	@Test(priority = 2)
	@Parameters({ "keyword" })
	public void verifyRecommendedRailImpressionEventInShowDetailPage(String keyword) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Recommended Rail Impression Event In Show Detail Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRecommendedRailImpressionEventInShowDetailPage(keyword, "You May Like");
	}

	@Test(priority = 3)
	@Parameters({ "keyword1" })
	public void verifyRecommendedRailImpressionEventInConsumptionScreen(String keyword1) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Recommended Rail Impression Event In Consumption Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRecommendedRailImpressionEventInConsumptionScreen(keyword1, "You May Like");
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
