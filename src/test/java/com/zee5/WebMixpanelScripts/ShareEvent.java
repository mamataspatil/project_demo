package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class ShareEvent {

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
	@Parameters({ "Tab" })
	public void verifyShareEventByMouseHover(String tab) throws Exception {
		System.out.println("Verify Share Event By Mouse Hovering on a Content Card");
		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventByMouseHover(tab);
	}

//	@Test(priority = 2)
	@Parameters({ "keyword1" })
	public void verifyShareEventFromPlaybackPage(String keyword1) throws Exception {
		System.out.println("Verify Share Event From Playback Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventFromPlaybackPage(keyword1);
	}

//	@Test(priority = 3)
	public void verifyShareEventForUpcomingProgram() throws Exception {
		System.out.println("Verify Share Event for Upcoming Program");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventForUpcomingProgram();
	}

//	@Test(priority = 4)
	@Parameters({ "keyword" })
	public void verifyShareEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Share Event From Show Detail Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventFromShowDetailPage(keyword);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
