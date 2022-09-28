package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class AdForcedExitEvent {

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
	public void verifyAdForcedExitEventForFreeContent(String userType, String tabName) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForFreeContent(userType, tabName);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab" })
	public void verifyAdForcedExitEventForTrailer(String userType, String tabName) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForTrailer(userType, tabName);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyAdForcedExitEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForCarouselContent(userType,"Shows");
	}

	@Test(priority = 4)
	@Parameters({ "userType","Tab" })
	public void verifyAdForcedExitEventForContentInTray(String userType,String tabName) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentInTray(userType,tabName);
	}

	@Test(priority = 5)
	@Parameters({ "userType", "keyword4" })
	public void verifyAdForcedExitEventForContentFromSearchPage(String userType, String keyword4)
			throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromSearchPage(userType, keyword4);
	}

	@Test(priority = 6)
	@Parameters({ "userType", "keyword" })
	public void verifyAdForcedExitEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromMyWatchlistPage(userType,
				keyword);
	}

//	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyAdForcedExitEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentInMegamenu(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyAdForcedExitEventForContentInPlaylist(String userType)
			throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 9)
	@Parameters({ "userType"})
	public void verifyAdForcedExitEventForContentFromUpnextRail(String userType)
			throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 10)
	@Parameters({ "userType", "freeContentURL" })
	public void verifyAdForcedExitEventForContentFromSharedLink(String userType, String freeContentURL)
			throws Exception {
		System.out.println("Verify Ad Forced Exit Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromSharedLink(userType, freeContentURL);
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
