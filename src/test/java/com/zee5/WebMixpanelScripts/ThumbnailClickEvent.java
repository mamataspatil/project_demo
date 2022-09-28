package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class ThumbnailClickEvent {

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
	public void verifyThumbnailClickEventFromTray(String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event form Tray");
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromTray("Shows");
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromViewMorePage(String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From View More Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromViewMorePage("Shows");
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromShowDetailPage(String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From Show Detail Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromShowDetailPage(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType", "keyword" })
	public void verifyThumbnailClickEventFromPlaybackPage(String keyword, String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From Playback Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromPlaybackPage(userType, keyword);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
