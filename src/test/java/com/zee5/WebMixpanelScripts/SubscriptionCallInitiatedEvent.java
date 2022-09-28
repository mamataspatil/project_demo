package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class SubscriptionCallInitiatedEvent {

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
	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for All access pack");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallInitiatedEvent(userType);
	}

//	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for Club pack");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallInitiatedEventClubPack(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
