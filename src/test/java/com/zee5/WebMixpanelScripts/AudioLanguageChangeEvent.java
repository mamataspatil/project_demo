package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class AudioLanguageChangeEvent {

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
	@Parameters({ "userType", "audioTrackContent" })
	public void verifyAudioLanguageChangeEventForFreeContent(String userType, String audioTrackContent)
			throws Exception {
		System.out.println("Verify Audio Language Change Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForFreeContent(userType, audioTrackContent);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyAudioLanguageChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Audio Language Change Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForPremiumContent(userType, "Home");
	}

	@Test(priority = 3)
	@Parameters({ "keyword1" })
	public void verifyAudioLanguageChangeEventForTrailer(String audioTrackTrailerContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForTrailer(audioTrackTrailerContent);
	}

	@Test(priority = 4)
	public void verifyAudioLanguageChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Audio Language Change Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForCarouselContent();
	}

	@Test(priority = 5)
	public void verifyAudioLanguageChangeEventForContentInTray() throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentInTray();
	}

	@Test(priority = 6)
	@Parameters({ "audioTrackContent" })
	public void verifyAudioLanguageChangeEventForContentFromSearchPage(String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromSearchPage(audioTrackContent);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "audioTrackContent" })
	public void verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		System.out.println("Verify Audio Language Change Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(userType,
				audioTrackContent);
	}

	@Test(priority = 8)
	public void verifyAudioLanguageChangeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentInMegamenu();
	}

	@Test(priority = 9)
	@Parameters({ "userType", "audioTrackContent" })
	public void verifyAudioLanguageChangeEventForContentInPlaylist(String userType, String audioTrackContent)
			throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentInPlaylist(userType, audioTrackContent);
	}

	@Test(priority = 10)
	@Parameters({ "userType", "audioTrackContent" })
	public void verifyAudioLanguageChangeEventForContentFromUpnextRail(String userType, String audioTrackContent)
			throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromUpnextRail(userType,
				audioTrackContent);
	}

	@Test(priority = 11)
	@Parameters({ "audioTrackURL" })
	public void verifyAudioLanguageChangeEventForContentFromSharedLink(String audioTrackURL) throws Exception {
		System.out.println("Verify Audio Language Change Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromSharedLink(audioTrackURL);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
