package com.zee5.MPWAMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class PrepaidCodeResultEvent {

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
	public void verifyPrepaidCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Prepaid Code Result Event For Invalid code");
		Zee5MPWAMixPanelBusinessLogic.verifyPrepaidCodeResultEventForInvalid(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5MPWAMixPanelBusinessLogic.tearDown();
	}
}
