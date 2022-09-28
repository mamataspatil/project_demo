package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class QualityChangeEvent {

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
	@Parameters({ "keyword1" })
	public void verifyQualityChangeEvent(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEvent(keyword1);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "Tab" })
	public void verifyQualityChangeEventForFreeContent(String userType, String tab) throws Exception {
		System.out.println("Verify Quality Change Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForFreeContent(userType, tab);
	}

	@Test(priority = 3)
	@Parameters({ "userType", "Tab" })
	public void verifyQualityChangeEventForPremiumContent(String userType,String tab) throws Exception {
		System.out.println("Verify Quality Change Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForPremiumContent(userType, tab);
	}

	@Test(priority = 4)
	@Parameters({ "Tab" })
	public void verifyQualityChangeEventForTrailer(String keyword1,String tab) throws Exception {
		System.out.println("Verify Quality Change Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForTrailer(tab);
	}

	@Test(priority = 5)
	public void verifyQualityChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Quality Change Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForCarouselContent("Shows");
	}

	@Test(priority = 6)
	@Parameters({ "userType", "Tab" })
	public void verifyQualityChangeEventForContentInTray(String tab) throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentInTray(tab);
	}

	@Test(priority = 7)
	@Parameters({ "keyword4" })
	public void verifyQualityChangeEventForContentFromSearchPage(String keyword4) throws Exception {
		System.out.println("Verify Quality Change Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromSearchPage(keyword4);
	}

	@Test(priority = 8)
	@Parameters({ "userType", "keyword" })
	public void verifyQualityChangeEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		System.out.println("Verify Quality Change Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromMyWatchlistPage(userType, keyword);
	}

//	@Test(priority = 9)
	public void verifyQualityChangeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentInMegamenu();
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void verifyQualityChangeEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 11)
	@Parameters({ "userType"})
	public void verifyQualityChangeEventForContentFromUpnextRail(String userType) throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 12)
	@Parameters({ "freeContentURL" })
	public void verifyQualityChangeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Quality Change Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromSharedLink(freeContentURL);
	}

	@Test(priority = 13)
	public void verifyQualityChangeEventForLinearContent() throws Exception {
		System.out.println("Verify Quality Change Event For Linear Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForLinearContent();
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
