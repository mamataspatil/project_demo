package com.zee5.MPWAMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class SubscriptionPageViewedEvent {

	private Zee5MPWAMixPanelBusinessLogic Zee5MPWAMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5MPWAMixPanelBusinessLogic = new Zee5MPWAMixPanelBusinessLogic("Chrome");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void PWAMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5MPWAMixPanelBusinessLogic.ZeePWALogin("E-mail", userType);
	}

//	@Test(priority = 1)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Subscribe button at header");
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaSubscribeBtn(userType);
	}

//	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaBuySubscription(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		System.out
				.println("Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaPrepaidCode(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");
		Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5MPWAMixPanelBusinessLogic.tearDown();
	}
}
