package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class SubtitleLanguageChangeEvent {

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
	@Parameters({ "userType", "subtitleTrackContent" })
	public void verifySubtitleLanguageChangeEventForFreeContent(String userType, String subtitleTrackContent)
			throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForFreeContent(userType, subtitleTrackContent);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySubtitleLanguageChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForPremiumContent(userType, "Home");
	}

	@Test(priority = 3)
	@Parameters({ "keyword5" })
	public void verifySubtitleLanguageChangeEventForTrailer(String keyword5) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForTrailer(keyword5);
	}

	@Test(priority = 4)
	public void verifySubtitleLanguageChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForCarouselContent();
	}

	@Test(priority = 5)
	public void verifySubtitleLanguageChangeEventForContentInTray() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentInTray();
	}

	@Test(priority = 6)
	@Parameters({ "subtitleTrackContent" })
	public void verifySubtitleLanguageChangeEventForContentFromSearchPage(String subtitleTrackContent)
			throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromSearchPage(subtitleTrackContent);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "subtitleTrackContent" })
	public void verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(String userType,
			String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(userType,
				subtitleTrackContent);
	}

	@Test(priority = 8)
	public void verifySubtitleLanguageChangeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentInMegamenu();
	}

	@Test(priority = 9)
	@Parameters({ "userType", "subtitleTrackContent" })
	public void verifySubtitleLanguageChangeEventForContentInPlaylist(String userType, String subtitleTrackContent)
			throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentInPlaylist(userType,
				subtitleTrackContent);
	}

	@Test(priority = 10)
	@Parameters({ "userType", "subtitleTrackContent" })
	public void verifySubtitleLanguageChangeEventForContentFromUpnextRail(String userType, String subtitleTrackContent)
			throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromUpnextRail(userType,
				subtitleTrackContent);
	}

	@Test(priority = 11)
	@Parameters({ "audioTrackURL" })
	public void verifySubtitleLanguageChangeEventForContentFromSharedLink(String subtitleTrackURL) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromSharedLink(subtitleTrackURL);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
