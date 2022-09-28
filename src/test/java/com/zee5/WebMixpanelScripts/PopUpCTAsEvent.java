package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class PopUpCTAsEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}
	
	@Test(priority = 1)
	@Parameters({ "userType", "keyword" })
	public void verifyPopUpCTAsEvent(String userType, String keyword) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Pop Up CTA's Event when user clicks on CTA button displayed on Registration popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpCTAsEvent(userType, keyword);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void verifyPopUpCTAsEventForCompleteProfilePopUp(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Pop Up CTAs Event for Complete Profile popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpCTAsEventForCompleteProfilePopUp(userType);
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
