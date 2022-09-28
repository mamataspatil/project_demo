package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class SkipIntroEvent {

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
	public void verifySkipIntroEventForFreeContent(String userType, String tab) throws Exception {
		System.out.println("Verify Skip Intro Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForFreeContent(userType, tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySkipIntroEventForPremiumContent(String userType, String tab) throws Exception {
		System.out.println("Verify Skip Intro Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForPremiumContent(userType, tab);
	}

	@Test(priority = 3)
	@Parameters({ "keyword5" })
	public void verifySkipIntroEventForTrailer(String tab) throws Exception {
		System.out.println("Verify Skip Intro Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForTrailer(tab);
	}

	@Test(priority = 4)
	@Parameters({ "Tab" })
	public void verifySkipIntroEventForCarouselContent(String tab) throws Exception {
		System.out.println("Verify Skip Intro Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForCarouselContent(tab);
	}

	@Test(priority = 5)
	@Parameters({ "Tab" })
	public void verifySkipIntroEventForContentInTray(String tab) throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentInTray(tab);
	}

	@Test(priority = 6)
	@Parameters({ "freeMovie2" })
	public void verifySkipIntroEventForContentFromSearchPage(String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromSearchPage(freeMovie2);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "freeMovie2" })
	public void verifySkipIntroEventForContentFromMyWatchlistPage(String userType, String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromMyWatchlistPage(userType, freeMovie2);
	}

//	@Test(priority = 8)
	public void verifySkipIntroEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentInMegamenu();
	}

	@Test(priority = 9)
	@Parameters({ "userType", "freeMovie2" })
	public void verifySkipIntroEventForContentInPlaylist(String userType, String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentInPlaylist(userType, freeMovie2);
	}

	@Test(priority = 10)
	@Parameters({ "userType", "freeMovie2" })
	public void verifySkipIntroEventForContentFromUpnextRail(String userType, String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromUpnextRail(userType, freeMovie2);
	}

	@Test(priority = 11)
	@Parameters({ "skipIntroURL" })
	public void verifySkipIntroEventForContentFromSharedLink(String skipIntroURL) throws Exception {
		System.out.println("Verify Skip Intro Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromSharedLink(skipIntroURL);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
