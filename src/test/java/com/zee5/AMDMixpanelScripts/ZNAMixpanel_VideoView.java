package com.zee5.AMDMixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_VideoView {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		System.out.println("\nLogin");
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType","pTabName"})
	public void VideoViewEvent_TopNavigation(String userType,String pTabName) throws Exception {
		System.out.println("\nVideo View event validation from "+pTabName+" tab navigation");
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventFromTopNavigationPage(userType, pTabName);
	}
	
	@Test(priority = 3)		//###########  VIDEO VIEW EVENT FROM SEARCH TAB  ###########
	@Parameters({ "userType","freeContentID","freeContent"})
	public void VideoViewEventForFreeContentFromSearchPage(String userType,String freeContentID,String freeContent) throws Exception {
		System.out.println("\nVideo View event for Free content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoViewEventFromSearchTab(userType,"Free",freeContentID,freeContent);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType","trailerContentID","trailerContent"})
	public void VideoViewEventForTrailerContentFromSearchPage(String userType,String trailerContentID,String trailerContent) throws Exception {
		System.out.println("\nVideo View event for Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoViewEventFromSearchTab(userType,"Trailer",trailerContentID,trailerContent);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType","premiumContentID","premiumContent"})
	public void VideoViewEventForPremiumContentFromSearchPage(String userType,String premiumContentID,String premiumContent) throws Exception {
		System.out.println("\nVideo View event for Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoViewEventFromSearchTab(userType,"Premium",premiumContentID,premiumContent);
	}
	
//	@Test(priority = 6)		//###########  VIDEO VIEW EVENT FROM CAROUSEL  ###########
	@Parameters({ "userType", "pTabName" })
	public void VideoViewEventofCarouselContent(String usertype, String tabName) throws Exception {
		System.out.println("\nVideo view event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventForCarouselContent(usertype, tabName);
	}
	
//###############  CAROUSEl TEST TO ADD  ###############	
	
//	@Test(priority = 6)
	public void VideoViewEventofCarouselContent() throws Exception {
		System.out.println("\nVideo view event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventForCarouselContent("Home");
	}

//	@Test(priority = 7)
	@Parameters({ "userType", "keyword4" })
	public void VideoViewEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo view event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventOfcontentFromSearchPage(userType, keyword4);
	}

//	@Test(priority = 8)
	@Parameters({ "userType" })
	public void VideoViewEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nVideo view event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventOfContentFromMyWatchListPage(userType);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}