package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class ResumeEvent {

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
	@Parameters({ "userType","Tab" })
	public void verifyResumeEventForPremiumContent(String userType,String Tab) throws Exception {
		System.out.println("Verify Resume Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForPremiumContent(userType, Tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab"})
	public void verifyResumeEventForFreeContent(String userType,String tab) throws Exception {
		System.out.println("Verify Resume Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForFreeContent(userType,tab);
	}
	
	@Test(priority = 3)
	@Parameters({ "Tab" })
	public void verifyResumeEventForCarouselContent(String tabName) throws Exception {
		System.out.println("Verify Resume Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForCarouselContent(tabName);
	}

	@Test(priority = 4)
	@Parameters({ "Tab" })
	public void verifyResumeEventForContentInTray(String tabName) throws Exception {
		System.out.println("Verify Resume Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentInTray(tabName);
	}

	@Test(priority = 5)
	@Parameters({ "keyword4" })
	public void verifyResumeEventForContentFromSearchPage(String keyword4) throws Exception {
		System.out.println("Verify Resume Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromSearchPage(keyword4);
	}

	@Test(priority = 6)
	@Parameters({ "userType", "keyword" })
	public void verifyResumeEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		System.out.println("Verify Resume Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromMyWatchlistPage(userType, keyword);
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyResumeEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Resume Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 8)
	@Parameters({ "freeContentURL" })
	public void verifyResumeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Resume Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromSharedLink(freeContentURL);
	}

	@Test(priority = 9)
	@Parameters({ "userType", "Tab" })
	public void verifyResumeEventForTrailer(String userType, String tabName) throws Exception {
		System.out.println("Verify Resume Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForTrailer(userType, tabName);
	}

	@Test(priority = 10)
	public void verifyResumeEventForLinearContent() throws Exception {
		System.out.println("Verify Resume Event For Linear Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForLinearContent();
	}

	@Test(priority = 11)
	@Parameters({ "userType"})
	public void verifyResumeEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Resume Event for content autoplayed from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromUpnextRail(userType, "Shows");
	}

//	@Test(priority = 12)
	public void verifyResumeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Resume Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentInMegamenu();
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
