package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class AutoSeekForwardEvent {

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
	public void verifyAutoSeekForwardEventForFreeContent(String userType, String tab) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForFreeContent(userType, tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab" })
	public void verifyAutoSeekForwardEventForPremiumContent(String userType, String tab) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForPremiumContent(userType, tab);
	}

	@Test(priority = 3)
	@Parameters({ "Tab" })
	public void verifyAutoSeekForwardEventForTrailer(String tab) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForTrailer(tab);
	}

	@Test(priority = 4)
	public void verifyAutoSeekForwardEventForCarouselContent() throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForCarouselContent("Shows");
	}

	@Test(priority = 5)
	@Parameters({ "Tab" })
	public void verifyAutoSeekForwardEventForContentInTray(String tab) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentInTray(tab);
	}

	@Test(priority = 6)
	@Parameters({ "keyword4" })
	public void verifyAutoSeekForwardEventForContentFromSearchPage(String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromSearchPage(keyword4);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "keyword" })
	public void verifyAutoSeekForwardEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromMyWatchlistPage(userType, keyword);
	}

//	@Test(priority = 8)
	public void verifyAutoSeekForwardEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentInMegamenu();
	}

	@Test(priority = 9)
	@Parameters({ "userType"})
	public void verifyAutoSeekForwardEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void verifyAutoSeekForwardEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 11)
	@Parameters({ "freeContentURL" })
	public void verifyAutoSeekForwardEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromSharedLink(freeContentURL);
	}

	@Test(priority = 12)
	@Parameters({ "userType", "Tab" })
	public void verifyAutoSeekRewindEventForFreeContent(String userType, String tab) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForFreeContent(userType, tab);
	}

	@Test(priority = 13)
	@Parameters({ "userType", "Tab"  })
	public void verifyAutoSeekRewindEventForPremiumContent(String userType,String tab) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForPremiumContent(userType, tab);
	}

	@Test(priority = 14)
	@Parameters({ "Tab" })
	public void verifyAutoSeekRewindEventForTrailer(String tab) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForTrailer(tab);
	}

	@Test(priority = 15)
	public void verifyAutoSeekRewindEventForCarouselContent() throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForCarouselContent("Shows");
	}

	@Test(priority = 16)
	@Parameters({ "Tab" })
	public void verifyAutoSeekRewindEventForContentInTray(String tab) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentInTray(tab);
	}

	@Test(priority = 17)
	@Parameters({ "keyword4" })
	public void verifyAutoSeekRewindEventForContentFromSearchPage(String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromSearchPage(keyword4);
	}

	@Test(priority = 18)
	@Parameters({ "userType", "keyword" })
	public void verifyAutoSeekRewindEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromMyWatchlistPage(userType, keyword);
	}

//	@Test(priority = 19)
	public void verifyAutoSeekRewindEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentInMegamenu();
	}

	@Test(priority = 20)
	@Parameters({ "userType" })
	public void verifyAutoSeekRewindEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 21)
	@Parameters({ "userType" })
	public void verifyAutoSeekRewindEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 22)
	@Parameters({ "freeContentURL" })
	public void verifyAutoSeekRewindEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromSharedLink(freeContentURL);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
