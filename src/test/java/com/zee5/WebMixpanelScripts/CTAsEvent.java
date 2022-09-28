package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class CTAsEvent {

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
	public void verifyCTAsEventForIcons(String userType) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventForIcons(userType,"Language");
	}
	
	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyCTAsEventForSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify CTAs Event when user clicks on subscription option");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventForSubscribeBtn(userType);
	}
	
	
	@Test(priority = 3)
	public void verifyCTAsEventForOptionInHamburger() throws Exception {
		System.out.println("Verify CTAs Event when user clicks on any option in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventForOptionInHamburger();
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void verifyCTAsEventHeader(String userType) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventHeader(userType,"TV Shows");
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
