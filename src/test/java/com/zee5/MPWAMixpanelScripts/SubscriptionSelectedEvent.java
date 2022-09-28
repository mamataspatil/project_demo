package com.zee5.MPWAMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class SubscriptionSelectedEvent {

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

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event");
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionSelectedEvent(userType);
	}

//	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event By selecting Club Pack");
		Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionSelectedEventByClubPack(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5MPWAMixPanelBusinessLogic.tearDown();
	}
}
