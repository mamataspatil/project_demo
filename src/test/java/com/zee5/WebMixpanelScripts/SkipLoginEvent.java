package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class SkipLoginEvent {

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
	public void verifySkipLoginEvent(String userType) throws Exception {
		System.out.println("Verify Skip Login Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginEvent(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "keyword" })
	public void verifySkipLoginByClickingOnLoginInRegistrationPopUp(String userType, String keyword) throws Exception {
		System.out.println(
				"Verify Skip Login Event during content playback post clicking on login in registration popup");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginByClickingOnLoginInRegistrationPopUp(userType, keyword);
	}

//	@Test(priority = 3)
	@Parameters({ "userType", "keyword2" })
	public void verifySkipLoginByClickingOnLoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println(
				"Verify Skip Login Event during content playback post clicking on login button in Get Premium popup");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginByClickingOnLoginInGetPremiumPopUp(userType, keyword2);
	}

//	@Test(priority = 4)
	public void verifySkipLoginThroughBeforeTVContent() throws Exception {
		System.out.println("Verify Skip Login Event gets triggered when user click on close button in login popup "
				+ "on clicking login in Get premium popup on accessing before tv content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginThroughBeforeTVContent();
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
