package com.zee5.MPWAMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class SubscriptionCallInitiatedEvent {

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
	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for All access pack");
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionCallInitiatedEvent(userType);
	}

//	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for Club pack");
		Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic.verifySubscriptionCallInitiatedEventClubPack(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5MPWAMixPanelBusinessLogic.tearDown();
	}
}
