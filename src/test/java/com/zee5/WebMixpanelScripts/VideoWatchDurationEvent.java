package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class VideoWatchDurationEvent {

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
	public void verifyVideoWatchDurationEventForFreeContentAbrupt(String userType, String tab) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForFreeContentAbrupt(userType, tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab" })
	public void verifyVideoWatchDurationEventForPremiumContentAbrupt(String userType,String tab) throws Exception {
		System.out.println("Verify Video Watch Duration when video is closed abruptly Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForPremiumContentAbrupt(userType, tab);
	}

	@Test(priority = 3)
	@Parameters({ "userType", "Tab" })
	public void verifyVideoWatchDurationEventForTrailerAbrupt(String userType, String Tab) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForTrailerAbrupt(userType, Tab);
	}

	@Test(priority = 4)
	public void verifyVideoWatchDurationEventForCarouselContentAbrupt() throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForCarouselContentAbrupt("Shows");
	}

	@Test(priority = 5)
	@Parameters({"Tab" })
	public void verifyVideoWatchDurationEventForContentInTrayAbrupt(String tabName) throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInTrayAbrupt(tabName);
	}

	@Test(priority = 6)
	@Parameters({ "keyword4" })
	public void verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(String keyword4) throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when video is closed abruptly For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(keyword4);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "keyword" })
	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(String userType, String keyword)
			throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when video is closed abruptly For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(userType,
				keyword);
	}

	@Test(priority = 8)
	public void verifyVideoWatchDurationEventForContentInMegamenuAbrupt() throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInMegamenuAbrupt();
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void verifyVideoWatchDurationEventForContentInPlaylistAbrupt(String userType)
			throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Playlist");
	//	Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInPlaylistAbrupt(userType, "Shows");
	}

	@Test(priority = 10)
	@Parameters({ "userType", "Tab" })
	public void verifyVideoWatchDurationEventForFreeContentComplete(String userType, String tab) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForFreeContentComplete(userType, tab);
	}

	@Test(priority = 11)
	@Parameters({ "userType","Tab" })
	public void verifyVideoWatchDurationEventForPremiumContentComplete(String userType, String tab) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForPremiumContentComplete(userType, tab);
	}

	@Test(priority = 12)
	@Parameters({ "userType", "Tab" })
	public void verifyVideoWatchDurationEventForTrailerComplete(String userType, String tab) throws Exception {
		System.out.println("Verify Video Watch Duration Event  when user completely watches Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForTrailerComplete(userType, tab);
	}

	@Test(priority = 13)
	public void verifyVideoWatchDurationEventForCarouselContentComplete() throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForCarouselContentComplete("Shows");
	}

	@Test(priority = 14)
	@Parameters({ "Tab" })
	public void verifyVideoWatchDurationEventForContentInTrayComplete(String tabName) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInTrayComplete(tabName);
	}

	@Test(priority = 15)
	@Parameters({ "keyword4" })
	public void verifyVideoWatchDurationEventForContentFromSearchPageComplete(String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSearchPageComplete(keyword4);
	}

	@Test(priority = 16)
	@Parameters({ "userType", "keyword" })
	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(String userType, String keyword)
			throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when user completely watches Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(userType,
				keyword);
	}

//	@Test(priority = 17)
	public void verifyVideoWatchDurationEventForContentInMegamenuComplete() throws Exception {
		System.out
				.println("Verify Video Watch Duration Event when user completely watches Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInMegamenuComplete();
	}

	@Test(priority = 18)
	@Parameters({ "userType", "keyword" })
	public void verifyVideoWatchDurationEventForContentInPlaylistComplete(String userType, String keyword)
			throws Exception {
		System.out
				.println("Verify Video Watch Duration Event when user completely watches Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInPlaylistComplete(userType, "Shows");
	}

	@Test(priority = 19)
	@Parameters({ "userType" })
	public void verifyVideoWatchDurationEventForContentFromUpnextRailComplete(String userType)
			throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event When user completely watches the  auto-played content from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromUpnextRailComplete(userType,
				"Shows");
	}

	@Test(priority = 20)
	@Parameters({ "freeContentURL" })
	public void verifyVideoWatchDurationEventForContentFromSharedLinkComplete(String freeContentURL) throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when user completely watches the content playback shared through shared link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSharedLinkComplete(freeContentURL);
	}

	@Test(priority = 21)
	@Parameters({ "userType" })
	public void verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(String userType)
			throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when video is closed abruptly on auto-played content from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(userType, "Shows");
	}

	@Test(priority = 22)
	@Parameters({ "freeContentURL" })
	public void verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(String freeContentURL) throws Exception {
		System.out.println(
				"Verify Video Watch Duration Event when video is closed abruptly For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(freeContentURL);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
