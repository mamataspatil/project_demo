package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class PopUpLaunchEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

//	@Test(priority = 1)
	@Parameters({ "userType", "keyword2" })
	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForGetPremiumPopUp(userType, keyword2);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "keyword" })
	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Pop Up Launch Event when Native popup is displayed");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForRegisterPopUp(userType, keyword);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Pop Up Launch Event when Complete Profile popup is displayed");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForCompleteProfilePopUp(userType);
	}

	// Login through ClubUser Id
//	@Test(priority = 4)
	@Parameters({ "userType", "keyword6" })
	public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForClubUser(userType, keyword6);
	}


	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
