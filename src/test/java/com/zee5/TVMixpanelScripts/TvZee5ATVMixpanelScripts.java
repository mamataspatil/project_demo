package com.zee5.TVMixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TvZee5ATVMixpanelScripts {
	private com.business.zee.Zee5TvMixPanelBusinessLogic Zee5TvMixPanelBusiness;

	@BeforeTest
	public void Before() throws InterruptedException {
		// Utilities.relaunch = true;
		Zee5TvMixPanelBusiness = new com.business.zee.Zee5TvMixPanelBusinessLogic("zeeTV");
	}
//	@Test(priority = 1)
	@Parameters({ "userType" })
	public void TvLogin(String userType) throws Exception {
		Zee5TvMixPanelBusiness.login(userType);
	}
//	@Test(priority = 2)
	@Parameters({ "userType" })
	public void onboarding_MP(String userType) throws Exception {
		Zee5TvMixPanelBusiness.onboarding();
	}
	
	@Test(priority = 3)
	@Parameters({ "userType" })
	public void journey_MP(String userType) throws Exception {
		Zee5TvMixPanelBusiness.journey();
	}
	
	@AfterTest
	public void After() {
		System.out.println("Tear Down");
		Zee5TvMixPanelBusiness.TvtearDown();
	}
}
