package com.zee5.MPWAMixpanelScripts;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class LogoutEvent {

	private Zee5MPWAMixPanelBusinessLogic Zee5PWAMixPanelAndroidBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAMixPanelAndroidBusinessLogic = new Zee5MPWAMixPanelBusinessLogic("Chrome");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void PWAMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAMixPanelAndroidBusinessLogic.ZeePWALogin("E-mail", userType);
	}
	
	@Test(priority = 1)
	@Parameters({ "userType" })
	public void verifyLogoutEvent(String userType) throws Exception {
		System.out.println("Verify Logout Event");
		Zee5PWAMixPanelAndroidBusinessLogic.verifyLogoutEvent(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAMixPanelAndroidBusinessLogic.tearDown();
	}
}
