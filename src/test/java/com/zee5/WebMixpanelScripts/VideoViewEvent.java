package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class VideoViewEvent {

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
	
//	@Test(priority = 1)
	@Parameters({ "userType"})
	public void verifyVideoViewEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Video View Event for content autoplayed from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromUpnextRail(userType,"TV Shows");
	}
	
//	@Test(priority = 2)
	@Parameters({"freeContentURL"})
	public void verifyVideoViewEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Video View Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromSharedLink(freeContentURL);
	}
	
//	@Test(priority = 3)
	@Parameters({ "userType", "Tab"})
	public void verifyVideoViewEventForFreeContent(String userType,String Tab) throws Exception {
		System.out.println("Verify Video View Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForFreeContent(userType,Tab);
	}
	
//	@Test(priority = 4)
	@Parameters({ "userType","Tab"})
	public void verifyVideoViewEventForPremiumContent(String userType,String tabName) throws Exception {
		System.out.println("Verify Video View Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForPremiumContent(userType,tabName);
	}
	
//	@Test(priority = 5)
	@Parameters({ "keyword1","Tab"})
	public void verifyVideoViewEventForTrailer(String tabName) throws Exception {
		System.out.println("Verify Video View Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForTrailer(tabName);
	}
	
//	@Test(priority = 6)
	public void verifyVideoViewEventForCarouselContent() throws Exception {
		System.out.println("Verify Video View Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForCarouselContent("TV Shows");
	}
	
//	@Test(priority = 7)
	@Parameters({"Tab"})
	public void verifyVideoViewEventForContentInTray(String tabName) throws Exception {
		System.out.println("Verify Video View Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentInTray(tabName);
	}
	
	@Test(priority = 8)
	@Parameters({"keyword4"})
	public void verifyVideoViewEventForContentFromSearchPage(String keyword4) throws Exception {
		System.out.println("Verify Video View Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromSearchPage(keyword4);
	}

//	@Test(priority = 9)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoViewEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Video View Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
//	@Test(priority = 10)
	public void verifyVideoViewEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Video View Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentInMegamenu();
	}
	
//	@Test(priority = 11)
	@Parameters({ "userType"})
	public void verifyVideoViewEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Video View Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentInPlaylist(userType,"TV Shows");
	}
	
//	@Test(priority = 12)
	@Parameters({"Tab"})
	public void verifyVideoViewEventAfterRefreshingPage(String Tab) throws Exception {
		System.out.println("Verify Video View Event after refreshing a page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventAfterRefreshingPage(Tab);
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
