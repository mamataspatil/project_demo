package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class LoginResultEvent {

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
	public void verifyLoginResultEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event for Valid Credentials");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginResultEventForValidCredentials(userType, "emailLogin");
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyLoginResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event post entering invalid credentials");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginResultEventForInvalidCredentials(userType, "mobileNumberLogin");
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
