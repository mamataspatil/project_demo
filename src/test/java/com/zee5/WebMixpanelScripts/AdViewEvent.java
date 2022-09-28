package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class AdViewEvent {

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
	public void verifyAdViewEventForFreeContent(String userType, String tab) throws Exception {
		System.out.println("Verify Ad View Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForFreeContent(userType, tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab" })
	public void verifyAdViewEventForTrailer(String userType, String tab) throws Exception {
		System.out.println("Verify Ad View Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForTrailer(userType, tab);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyAdViewEventForCarouselContent(String userType) throws Exception {
		System.out.println("Verify Ad View Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForCarouselContent(userType,"Shows");
	}

	@Test(priority = 4)
	@Parameters({ "userType","Tab" })
	public void verifyAdViewEventForContentInTray(String userType,String tabName) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentInTray(userType,tabName);
	}

	@Test(priority = 5)
	@Parameters({ "userType", "keyword4" })
	public void verifyAdViewEventForContentFromSearchPage(String userType, String keyword4)
			throws Exception {
		System.out.println("Verify Ad View Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromSearchPage(userType, keyword4);
	}

	@Test(priority = 6)
	@Parameters({ "userType", "keyword" })
	public void verifyAdViewEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		System.out.println("Verify Ad View Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromMyWatchlistPage(userType, keyword);
	}

//	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyAdViewEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentInMegamenu(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyAdViewEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void verifyAdViewEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 10)
	@Parameters({ "userType", "freeContentURL" })
	public void verifyAdViewEventForContentFromSharedLink(String userType, String freeContentURL) throws Exception {
		System.out.println("Verify Ad View Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromSharedLink(userType, freeContentURL);
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
