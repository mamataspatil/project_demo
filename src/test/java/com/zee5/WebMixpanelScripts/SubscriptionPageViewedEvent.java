package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class SubscriptionPageViewedEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLogin(userType);
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Subscribe button at header");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaSubscribeBtn(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaBuySubscription(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		System.out
				.println("Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaPrepaidCode(userType);
	}

	@Test(priority = 4)
	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel() throws Exception {
		System.out.println("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel();
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
