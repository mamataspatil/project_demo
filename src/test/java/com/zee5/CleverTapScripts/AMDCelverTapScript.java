package com.zee5.CleverTapScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterCleverTapBusinessLogic;

public class AMDCelverTapScript {
	
	private Zee5ApplicasterCleverTapBusinessLogic Zee5ApplicasterCleverTapBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic = new Zee5ApplicasterCleverTapBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	public void CleverTapLogin() throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.loginCleverTap();
	}
	
	@Test(priority = 2)
	public void CleverTapNavigation() throws Exception {
//		Zee5ApplicasterCleverTapBusinessLogic.navigateToCleverTap();
//		Zee5ApplicasterCleverTapBusinessLogic.getEventName("");
		Zee5ApplicasterCleverTapBusinessLogic.EditCampaign("Subscription Page Viewed","AutoTest");
	}
	
	@AfterClass
	public void tearDown() {
//		Zee5ApplicasterCleverTapBusinessLogic.tearDown();
	}
}
