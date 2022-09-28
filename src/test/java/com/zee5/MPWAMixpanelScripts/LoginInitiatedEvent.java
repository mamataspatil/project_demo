package com.zee5.MPWAMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class LoginInitiatedEvent {

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
	public void verifyLoginInitiatedEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event for Valid Credentials");
		Zee5MPWAMixPanelBusinessLogic.verifyLoginInitiatedEvent(userType, "mobileNumberLogin");
	}

	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void verifyLoginInitiatedEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event post entering invalid credentials");
		Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic.verifyLoginInitiatedEvent(userType,"emailLogin");
	}
	
	@AfterClass
	public void tearDown() {
		Zee5MPWAMixPanelBusinessLogic.tearDown();
	}
}
