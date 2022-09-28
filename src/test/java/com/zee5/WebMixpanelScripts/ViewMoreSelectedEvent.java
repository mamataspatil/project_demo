package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class ViewMoreSelectedEvent {

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
	@Parameters({ "keyword" })
	public void verifyViewMoreSelectedEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify View More Selected Event For content played from Show detail page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyViewMoreSelectedEventFromShowDetailPage(keyword);
	}

	@Test(priority = 2)
	@Parameters({ "audioTrackContent", "userType" })
	public void verifyViewMoreSelectedEventFromPlaybackPage(String audioTrackContent, String userType)
			throws Exception {
		System.out.println("Verify View More Selected Event For content played from Playback page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyViewMoreSelectedEventFromPlaybackPage(audioTrackContent, userType);
	}

	@Test(priority = 3)
	@Parameters({ "Tab"})
	public void verifyViewMoreSelectedEventFromTray(String tab) throws Exception {
		System.out.println("Verify View More Selected Event For content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyViewMoreSelectedEventFromTray(tab);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}

}
