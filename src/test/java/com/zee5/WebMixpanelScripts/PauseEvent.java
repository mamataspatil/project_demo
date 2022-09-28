package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class PauseEvent {

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
	@Parameters({ "userType", "Tab" })
	public void verifyPauseEventForFreeContent(String userType, String Tab) throws Exception {
		System.out.println("Verify Pause Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForFreeContent(userType, Tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyPauseEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Pause Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForPremiumContent(userType, "Home");
	}

	@Test(priority = 3)
	@Parameters({ "userType", "Tab" })
	public void verifyPauseEventForTrailer(String userType, String Tab) throws Exception {
		System.out.println("Verify Pause Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForTrailer(userType, Tab);
	}

	@Test(priority = 4)
	@Parameters({"Tab"})
	public void verifyPauseEventForCarouselContent(String tabName) throws Exception {
		System.out.println("Verify Pause Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForCarouselContent(tabName);
	}

	@Test(priority = 5)
	@Parameters({"Tab"})
	public void verifyPauseEventForContentInTray(String tabName) throws Exception {
		System.out.println("Verify Pause Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentInTray(tabName);
	}

	@Test(priority = 6)
	@Parameters({ "keyword4" })
	public void verifyPauseEventForContentFromSearchPage(String keyword4) throws Exception {
		System.out.println("Verify Pause Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromSearchPage(keyword4);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "keyword" })
	public void verifyPauseEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		System.out.println("Verify Pause Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromMyWatchlistPage(userType, keyword);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyPauseEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Pause Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 9)
	public void verifyPauseEventForLinearContent() throws Exception {
		System.out.println("Verify Pause Event For Linear Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForLinearContent();
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void verifyPauseEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Pause Event for content autoplayed from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 11)
	@Parameters({ "freeContentURL" })
	public void verifyPauseEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Pause Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromSharedLink(freeContentURL);
	}

//	@Test(priority = 12)
	public void verifyPauseEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Pause Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentInMegamenu();
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
