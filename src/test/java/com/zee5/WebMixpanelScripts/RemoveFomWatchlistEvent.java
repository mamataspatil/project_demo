package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class RemoveFomWatchlistEvent {

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
	@Parameters({ "userType", "keyword1" })
	public void verifyRemoveFomWatchlistEventFromPlaybackPage(String userType, String keyword1) throws Exception {
		System.out.println("Verify Remove From Watchlist Event From Playback Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRemoveFomWatchlistEventFromPlaybackPage(userType, keyword1);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "keyword" })
	public void verifyRemoveFomWatchlistEventByMouseHoverInShowDetailPage(String userType, String keyword)
			throws Exception {
		System.out.println("Verify Remove from Watchlist Event by mouse hovering on a Content Card In show detail page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyRemoveFomWatchlistEventByMouseHoverInShowDetailPage(userType, keyword);
	}

	@Test(priority = 3)
	@Parameters({ "userType", "keyword" })
	public void verifyRemoveFromWatchlistEventFromMyWatchlistPage(String userType, String keyword) throws Exception {
		System.out.println("Verify Remove From Watchlist Event for Content from My Watchlist page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyRemoveFromWatchlistEventFromMyWatchlistPage(userType, keyword);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
