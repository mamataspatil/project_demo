package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class ParentalOverlayResultEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
	}

	@Test(priority = 1)
	@Parameters({ "userType", "Tab" })
	public void verifyParentalOverlayResultEventForFreeContent(String userType, String tab) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForFreeContent(userType, tab);
	}

	@Test(priority = 2)
	@Parameters({ "userType","Tab" })
	public void verifyParentalOverlayResultEventForPremiumContent(String userType,String tabName) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForPremiumContent(userType, tabName);
	}

	@Test(priority = 3)
	@Parameters({ "keyword1", "userType" })
	public void verifyParentalOverlayResultEventForTrailer(String tab, String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForTrailer(tab, userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType","Tab" })
	public void verifyParentalOverlayResultEventForCarouselContent(String userType,String tab) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForCarouselContent(userType,"Shows");
	}

	@Test(priority = 5)
	@Parameters({ "userType","Tab" })
	public void verifyParentalOverlayResultEventForContentInTray(String userType,String tab) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentInTray(userType,tab);
	}

	@Test(priority = 6)
	@Parameters({ "keyword4", "userType" })
	public void verifyParentalOverlayResultEventForContentFromSearchPage(String keyword4, String userType)
			throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromSearchPage(keyword4, userType);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "keyword" })
	public void verifyParentalOverlayResultEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromMyWatchlistPage(userType,
				keyword);
	}

//	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyParentalOverlayResultEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentInMegamenu(userType);
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void verifyParentalOverlayResultEventForContentInPlaylist(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentInPlaylist(userType, "Shows");
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void verifyParentalOverlayResultEventForContentFromUpnextRail(String userType)
			throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromUpnextRail(userType, "Shows");
	}

	@Test(priority = 11)
	@Parameters({ "freeContentURL", "userType" })
	public void verifyParentalOverlayResultEventForContentFromSharedLink(String freeContentURL, String userType)
			throws Exception {
		System.out.println("Verify Parental Overlay Result Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromSharedLink(freeContentURL,
				userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
