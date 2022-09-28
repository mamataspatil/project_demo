package com.zee5.MPWAMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class LoginScreenDisplayEvent {

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

//	@Test(priority = 1)
//	@Parameters({ "userType" })
//	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
//		System.out.println("Verify Login Screen Display Event By Clicking On Login Button");
//		Zee5MPWAMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButton(userType);
//	}
//
//	@Test(priority = 2)
//	@Parameters({ "userType", "keyword2" })
//	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2)
//			throws Exception {
//		System.out.println("Verify Login Screen Display Event By Clicking On Login Button On Player");
//		Zee5MPWAMixPanelBusinessLogic.relaunch();
//		Zee5MPWAMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(userType,
//				keyword2);
//	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
	//	Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic
				.verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(userType);
	}

//	@Test(priority = 4)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");
		Zee5MPWAMixPanelBusinessLogic.relaunch();
		Zee5MPWAMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(userType,
				keyword2);
	}

	@AfterClass
	public void tearDown() {
		Zee5MPWAMixPanelBusinessLogic.tearDown();
	}
}
