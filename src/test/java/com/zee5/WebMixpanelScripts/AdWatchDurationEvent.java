package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class AdWatchDurationEvent {

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
	public void verifyAdWatchDurationEventForFreeContentForceExit(String userType, String tab)
			throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback for free content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForFreeContentForceExit(userType, tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab" })
	public void verifyAdWatchDurationEventForTrailerForceExit(String userType, String tab) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForTrailerForceExit(userType, tab);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForCarouselContentForceExit(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForCarouselContentForceExit(userType,"Shows");
	}

	@Test(priority = 4)
	@Parameters({ "userType", "Tab" })
	public void verifyAdWatchDurationEventForContentInTrayForceExit(String userType, String tab) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInTrayForceExit(userType,tab);
	}

	@Test(priority = 5)
	@Parameters({ "userType", "keyword4" })
	public void verifyAdWatchDurationEventForContentFromSearchPageForceExit(String userType,
			String keyword4) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user force quits the ad playback For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageForceExit(userType,
				keyword4);
	}

	@Test(priority = 6)
	@Parameters({ "userType", "keyword" })
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(String userType,
			String keyword) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user force quits the ad playback For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(userType,
				keyword);
	}

//	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForContentInMegamenuForceExit(String userType) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInMegamenuForceExit(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentInPlaylistForceExit(String userType)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistForceExit(userType,
				"Shows");
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForContentFromUpnextRailForceExit(String userType)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailForceExit(userType,
				"Shows");
	}

	@Test(priority = 10)
	@Parameters({ "userType", "freeContentURL" })
	public void verifyAdWatchDurationEventForContentFromSharedLinkForceExit(String userType, String freeContentURL)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user force quits the ad playback For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkForceExit(userType,
				freeContentURL);
	}

	@Test(priority = 11)
	@Parameters({ "userType", "audioTrackContent" })
	public void verifyAdWatchDurationEventForFreeContentComplete(String userType, String audioTrackContent)
			throws Exception {
		System.out
				.println("Verify Ad Watch Duration Event when user completly watches the ad playback for free content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForFreeContentComplete(userType, audioTrackContent);
	}

	@Test(priority = 12)
	@Parameters({ "userType", "keyword1" })
	public void verifyAdWatchDurationEventForTrailerComplete(String userType, String keyword1) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches the ad playback For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForTrailerComplete(userType, keyword1);
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForCarouselContentComplete(String userType) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches the ad playback For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForCarouselContentComplete(userType,"Shows");
	}

	@Test(priority = 14)
	@Parameters({ "userType" , "Tab"})
	public void verifyAdWatchDurationEventForContentInTrayComplete(String userType,String tabName) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInTrayComplete(userType,tabName);
	}

	@Test(priority = 15)
	@Parameters({ "userType", "keyword4" })
	public void verifyAdWatchDurationEventForContentFromSearchPageComplete(String userType, String keyword4)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches the ad playback For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageComplete(userType,
				keyword4);
	}

	@Test(priority = 16)
	@Parameters({ "userType", "keyword" })
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(String userType,
			String keyword) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches ad playback For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(userType,
				keyword);
	}

//	@Test(priority = 17)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForContentInMegamenuComplete(String userType) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInMegamenuComplete(userType);
	}

	@Test(priority = 18)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForContentInPlaylistComplete(String userType)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistComplete(userType,
				"Shows");
	}

	@Test(priority = 19)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForContentFromUpnextRailComplete(String userType)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailComplete(userType,
				"Shows");
	}

	@Test(priority = 20)
	@Parameters({ "userType", "freeContentURL" })
	public void verifyAdWatchDurationEventForContentFromSharedLinkComplete(String userType, String freeContentURL)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user completly watches ad playback For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkComplete(userType,
				freeContentURL);
	}

	@Test(priority = 21)
	@Parameters({ "userType", "audioTrackContent" })
	public void verifyAdWatchDurationEventForFreeContentSkipAd(String userType, String audioTrackContent)
			throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback for free content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForFreeContentSkipAd(userType, audioTrackContent);
	}

	@Test(priority = 22)
	@Parameters({ "userType", "keyword1" })
	public void verifyAdWatchDurationEventForTrailerSkipAd(String userType, String keyword1) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForTrailerSkipAd(userType, keyword1);
	}

	@Test(priority = 23)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForCarouselContentSkipAd(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForCarouselContentSkipAd(userType,"Shows");
	}

	@Test(priority = 24)
	@Parameters({ "userType","Tab" })
	public void verifyAdWatchDurationEventForContentInTraySkipAd(String userType,String tab) throws Exception {
		System.out
				.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInTraySkipAd(userType,tab);
	}

	@Test(priority = 25)
	@Parameters({ "userType", "keyword4" })
	public void verifyAdWatchDurationEventForContentFromSearchPageSkipAd(String userType, String keyword4)
			throws Exception {
		System.out
				.println("Verify Ad Watch Duration Event when user skips the ad playback For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageSkipAd(userType,
				keyword4);
	}

	@Test(priority = 26)
	@Parameters({ "userType", "keyword" })
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(String userType, String keyword)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user skips ad playback For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(userType,
				keyword);
	}

//	@Test(priority = 27)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForContentInMegamenuSkipAd(String userType) throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInMegamenuSkipAd(userType);
	}

	@Test(priority = 28)
	@Parameters({ "userType" })
	public void verifyAdWatchDurationEventForContentInPlaylistSkipAd(String userType)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistSkipAd(userType,
				"Shows");
	}

	@Test(priority = 29)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(String userType)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(userType,
				"Shows");
	}

	@Test(priority = 30)
	@Parameters({ "userType", "freeContentURL" })
	public void verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(String userType, String freeContentURL)
			throws Exception {
		System.out.println(
				"Verify Ad Watch Duration Event when user skips ad playback For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(userType,
				freeContentURL);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
