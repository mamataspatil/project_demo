package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class AdClickEvent {

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
	public void verifyAdClickEventForFreeContent(String userType, String tabName) throws Exception {
		System.out.println("Verify Ad Click Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForFreeContent(userType, tabName);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab" })
	public void verifyAdClickEventForTrailer(String userType, String tabName) throws Exception {
		System.out.println("Verify Ad Click Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForTrailer(userType, tabName);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyAdClickEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Ad Click Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForCarouselContent(userType,"Shows");
	}

	@Test(priority = 4)
	@Parameters({ "userType","Tab" })
	public void verifyAdClickEventForContentInTray(String userType,String tabName) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentInTray(userType,tabName);
	}

	@Test(priority = 5)
	@Parameters({ "userType", "keyword4" })
	public void verifyAdClickEventForContentFromSearchPage(String userType, String keyword4)
			throws Exception {
		System.out.println("Verify Ad Click Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentFromSearchPage(userType, keyword4);
	}

	@Test(priority = 6)
	@Parameters({ "userType", "keyword" })
	public void verifyAdClickForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		System.out.println("Verify Ad Click Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickForContentFromMyWatchlistPage(userType, keyword);
	}

//	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyAdClickEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentInMegamenu(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyAdClickEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 9)
	@Parameters({ "userType"})
	public void verifyAdClickEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 10)
	@Parameters({ "userType", "freeContentURL" })
	public void verifyAdClickEventForContentFromSharedLink(String userType, String freeContentURL) throws Exception {
		System.out.println("Verify Ad Click Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentFromSharedLink(userType, freeContentURL);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
