package com.zee5.Zee5TvScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TvZee5TvperformanceScripts {

	private com.business.zee.Zee5TvBusinessLogic Zee5TvBusiness;

	@BeforeTest
	public void Before() throws InterruptedException {
		Zee5TvBusiness = new com.business.zee.Zee5TvBusinessLogic("zeeTV");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void appLaunchPerformance() throws Exception {
		Zee5TvBusiness.appLaunch();
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void loginPerformance() throws Exception {
		Zee5TvBusiness.loginPerformance();
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void navigationPerformance(String userType) throws Exception {
		Zee5TvBusiness.SelectTopNavigationTab_Timer("Shows");
	}

	
	@AfterTest
	public void After() {
		System.out.println("Tear Down");
		Zee5TvBusiness.logout();
		Zee5TvBusiness.TvtearDown();
	}
}
