package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class LoginScreenDisplayEvent {

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
	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButton(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button On Player");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(userType,
				keyword2);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic
				.verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(userType);
	}

//	@Test(priority = 4)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(userType,
				keyword2);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
