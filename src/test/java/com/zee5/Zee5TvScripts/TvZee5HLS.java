package com.zee5.Zee5TvScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TvZee5HLS {
	
	private com.business.zee.Zee5TvBusinessLogic Zee5TvBusiness;

	@BeforeTest
	public void Before() throws InterruptedException {
		// Utilities.relaunch = true;
		Zee5TvBusiness = new com.business.zee.Zee5TvBusinessLogic("zeeTV");
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void TvLogin(String userType) throws Exception {
		Zee5TvBusiness.login(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void searchScenarios(String userType) throws Exception {
		Zee5TvBusiness.searchScenarios(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void playback(String userType) throws Exception {
		Zee5TvBusiness.playbackHomepage();
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void carousel(String tab) throws Exception {
		Zee5TvBusiness.carouselValidation("Home");
	}

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void playerScenarios(String userType) throws Exception {
		Zee5TvBusiness.playerScenarios();
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void setting(String userType) throws Exception {
		Zee5TvBusiness.setting(userType);
	}

	@Test(priority = 8)
	public void subscription() throws Exception {
		Zee5TvBusiness.subscription();
	}


	@Test(priority = 9)
	public void liveTV() throws Exception {
		Zee5TvBusiness.liveTv();
	}


	@Test(priority = 10)
	public void language() throws Exception {
		Zee5TvBusiness.languagePage();
	}


	@Test(priority = 11)
	public void headerSection() throws Exception {
		Zee5TvBusiness.headerSection();
	}
	
	@Test(priority = 12)
	public void staticPage() throws Exception {
		Zee5TvBusiness.staticPages();
	}

	@Test(priority = 13)
	public void contactUS() throws Exception {
		Zee5TvBusiness.contactUs();
	}

	@AfterTest
	public void After() {
		System.out.println("Tear Down");
		Zee5TvBusiness.TvtearDown();
	}
}
