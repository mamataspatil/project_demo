package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_P2_Events {
	
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
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	
	@Test(priority = 2)
	@Parameters({ "userType", "clipContent" })
	public void DownloadStartEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nDownload Start event of content");
		Zee5ApplicasterMixPanelBusinessLogic.DownloadStartEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	
	@Test(priority = 3)
	@Parameters({ "userType", "clipContent" })
	public void DownloadResultEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nDownload Result event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadResultEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "clipContent" })
	public void DownloadDeleteEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nDownload Delete event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadDeleteEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "clipContent" })
	public void DownloadClickEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nDownload Click event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadClickEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 6)
	@Parameters({"userType", "pTabName"})
	public void AndroidMixPanel_CarouselBannerCTAsEventValidation(String userType,String tabName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCarousalBannerCTAsEvent(userType, tabName);
	}
	
	@Test(priority = 7)
	@Parameters({"userType","pTabName"})
	public void AndroidMixPanel_ContentBucketSwipeEventValidation(String userType,String pTabName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.ContentBucketSwipeEventValidation(userType,pTabName);
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(userType, "Content Bucket Swipe", "Homepage", "SplashPage");
	}
	
	@Test(priority = 8)
	@Parameters({"userType", "clipContent", "trayName"})
	public void AndroidMixPanel_ContentBucketSwipeEventValidation_ConsumptionRail(String userType, String keyword, String trayName) throws Exception {
        Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.ContentBucketSwipeEvent_ConsumptionPageRail(userType,keyword, trayName);
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(userType, "Content Bucket Swipe", "Homepage", "Search_results");
	}
	
	@Test(priority = 9)
	@Parameters({ "userType", "premiumContent2" })
	public void WatchTrailerClickedEvent_Search(String usertype, String premiumContent2) throws Exception {
		System.out.println("\n Watch Trailer Clicked event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.watchTrailerClickedEventOfContentFromSearchPage(usertype, premiumContent2);
	}
	
	@Test(priority = 10)
	@Parameters({ "userType", "clipContent" })
	public void AddToWatchlistEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\n Add to Watchlist event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.addToWatchlistEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 11)
	@Parameters({ "userType", "clipContent" })
	public void AddToWatchlistSuccessfullEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\n Add to Watchlist successfull event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.addToWatchlistSuccessfullEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 12)
	@Parameters({ "userType", "premiumContent" })
	public void ShareEvent_Search(String usertype, String premiumContent) throws Exception {
		System.out.println("\n Share event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.shareEventOfContentFromSearchPage(usertype, premiumContent);
	}
	
	@Test(priority = 13)
	@Parameters({ "userType", "clipContent" })
	public void RemoveFromWatchlistEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\n Remove from Watchlist event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.removeFromWatchlistFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 14)
	@Parameters({"userType"})
	public void verifySearchExecutedEvent(String userType) throws Exception {
		System.out.println("Verify Search Executed Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySearchExecutedEvent();
	}
	
	
	@Test(priority = 15)
	@Parameters({"userType"})
	public void verifySearchCancelledEvent(String userType) throws Exception {
		System.out.println("Verify Search Cancelled Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySearchCancelledEvent();
	}
	
	@Test(priority = 16)
	@Parameters({"userType"})
	public void verifySearchClearedEvent(String userType) throws Exception {
		System.out.println("Verify Search Cancelled Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySearchClearedEvent();
	}
	
	@Test(priority = 17)
	@Parameters({"userType", "clipContent"})
	public void AndroidMixPanel_PlayerCTAs_PauseIcon(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_Pause(pUserType, keyword);
	}
	
	@Test(priority = 18)
	@Parameters({"userType", "clipContent"})
	public void AndroidMixPanel_PlayerCTAs_NextIcon(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_NextIcon(pUserType, keyword);
	}
	
	@Test(priority = 19)
	@Parameters({"userType", "keyword2"})
	public void AndroidMixPanel_PlayerCTAs_BuyPlanCTA(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_BuyPlanCTA(pUserType, keyword);
	}
	
	@Test(priority = 20)
	@Parameters({"userType", "keyword2"})
	public void AndroidMixPanel_PlayerCTAs_LoginCTA(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_LoginCTA(pUserType, keyword);
	}
	
	@Test(priority = 21)
	@Parameters({"userType", "zeeplexContent"})
	public void AndroidMixPanel_PlayerCTAs_RentNowCTA(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_RentNowCTA(pUserType, keyword);
	}
	
	@Test(priority = 22)
	@Parameters({ "userType", "clipContent" })
	public void AutoSeekEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nAuto Seek event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 23)
	@Parameters({ "userType", "clipContent" })
	public void ScrubSeekEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nScrub Seek event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.ScrubSeekEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 24)
	@Parameters({ "userType", "pTabName" })
	public void PlayerViewChangedEventofCarouselContent(String usertype, String tabName) throws Exception {
		System.out.println("\nPlayer view changed event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEventForCarouselContent(usertype, tabName);
	}
	
	@Test(priority = 25)
	@Parameters({ "userType", "audioTrackContent" })
	public void SubtitleLanguageChange_Search(String usertype, String audioTrackContent) throws Exception {
		System.out.println("\nSubtitle Language Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventFromSearchPage(usertype, audioTrackContent);
	}
	
	@Test(priority = 26)
	@Parameters({ "userType", "keyword3" })
	public void consumption_subscribe_cta_click_Event_Search(String usertype, String keyword3) throws Exception {
		System.out.println("\nconsumption_subscribe_cta_click_Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.consumption_subscribe_cta_click_Event(usertype, keyword3);
	}
	
	@Test(priority = 27)
	@Parameters({ "userType"})
	public void Downloadsection_visited(String usertype) throws Exception {
		System.out.println("\nDownload section visited event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.Downloadsection_visited(usertype);
	}	
	
	@Test(priority = 28)
	@Parameters({ "userType"})
	public void MoreSection_visitedEvent(String usertype) throws Exception {
		System.out.println("\nMore section visited event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.MoreSection_visited(usertype);
	}	
	
	@Test(priority = 29)
	@Parameters({"userType"})
	public void ScreenVisitedEventsValidation(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.ScreenVisitedEventValidation(pUserType);
	}
	
	@Test(priority = 30)
	@Parameters({ "userType", "premiumContent"})
	public void Svod_content_viewEvent_Search(String usertype, String keyword) throws Exception {
		System.out.println("\nSvod_content_view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.Svod_content_viewEventOfContentFromSearchPage(usertype, keyword);
	}
	
	@Test(priority = 31)
	@Parameters({ "userType", "tabName"})
	public void Tab_ViewEventValidation(String usertype,String tabName) throws Exception {
		System.out.println("\nTab View event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.Tab_ViewEventValidation(usertype,tabName);
	}	
	
	@Test(priority = 32)
	@Parameters({ "userType", "keyword4" })
	public void TVShows_Content_PlayEvent_Search(String usertype, String keyword4) throws Exception {
		System.out.println("\nTVShows_Content_Play event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.TVShows_Content_PlayEventOfContentFromSearchPage(usertype, keyword4);
	}
	
	@Test(priority = 33)
	@Parameters({ "userType"})
	public void UpcomingSection_visitedEvent(String usertype) throws Exception {
		System.out.println("\nUpcoming section visited event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.UpcomingSection_visitedEvent(usertype);
	}	
	
	@Test(priority = 34)
	@Parameters({ "userType"})
	public void VideoClick_1_Event_Search(String usertype) throws Exception {
		System.out.println("\nvideoclick_1 event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videoclick_1EventOfContentFromSearchPage(usertype);
	}
	
	@Test(priority = 35)
	@Parameters({ "userType"})
	public void VideoClick_3_Event_Search(String usertype) throws Exception {
		System.out.println("\nvideoclick_3 event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videoclick_3EventOfContentFromSearchPage(usertype);
	}
	
	@Test(priority = 36)
	@Parameters({ "userType"})
	public void VideoClick_5_Event_Search(String usertype) throws Exception {
		System.out.println("\nvideoclick_5 event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videoclick_5EventOfContentFromSearchPage(usertype);
	}
	
	@Test(priority = 37)
	@Parameters({ "userType", "content1"})
	public void Videos_Content_PlayEvent_Search(String usertype, String keyword) throws Exception {
		System.out.println("\nVideos_content_Play event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videos_content_PlayEventOfContentFromSearchPage(usertype, keyword);
	}
	
	@Test(priority = 38)
	@Parameters({ "userType"})
	public void Videos_Content_PlayEvent_Carousal(String usertype) throws Exception {
		System.out.println("\nVideos_content_Play event of contentfor Carousal");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videos_content_PlayEventOfContentFromCarousal(usertype, "Music");
	}
	
	@Test(priority = 39)
	@Parameters({ "userType"})
	public void Videos_Content_PlayEvent_TrayNavigation(String usertype) throws Exception {
		System.out.println("\nVideos_content_Play event of content for Tray navigation");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videos_content_PlayEventOfContentFromTrayNavigation(usertype, "Music");
	}
	
	@Test(priority = 40)
	@Parameters({ "userType", "freeContent"})
	public void af_preroll_adviewEvent_Search(String usertype, String keyword) throws Exception {
		System.out.println("\naf_preroll_adview event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.af_preroll_adview(usertype, keyword);
	}
	
	@Test(priority = 41)
	@Parameters({"userType"})
	public void AndroidMixPanel_LogoutEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLogoutEvent(userType);
	}
	
	@Test(priority = 42)
	@Parameters({"userType"})
	public void AndroidMixPanel_ChangePasswordStartedEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyChangePasswordStartedEvent(userType);
	}
	
	@Test(priority = 43)
	@Parameters({"userType"})
	public void AndroidMixPanel_ChangePasswordResultEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyChangePasswordResultEvent(userType);
	}
	
	@Test(priority = 44)
	@Parameters({"userType"})
	public void AndroidMixPanel_LoginRegistrationScreenDisplayEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginRegistrationScreenDisplayEvent(userType, "mobileNumberLogin");
	}

	
	//@Test(priority = 45)
	@Parameters({"userType"})
	public void verifyLoginRegistrationScreenDisplayEvent(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginRegistrationScreenDisplayEvent(userType);
	}
	
	@Test(priority = 46)
	@Parameters({"userType"})
	public void AndroidMixPanel_verifyPromoCodeResultEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEventForValid(userType);
		}
	
	
	@Test(priority = 47)
	@Parameters({"userType"})
	public void verifyPromoCodeResultEvent_MoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEvent_MoreSection(userType);
	}
	
	@Test(priority = 48)
	@Parameters({"userType"})
	public void verifyPromoCodeResultEvent_MySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEvent_MySubscription(userType);
	}
	
	@Test(priority = 49)
	@Parameters({"userType"})
	public void verifyPromoCodeResultEvent_MyTransaction(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEvent_MyTransaction(userType);
	}
	
	@Test(priority = 50)
	@Parameters({"userType","keyword3"})
	public void verifyPromoCodeResultEvent_BelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEvent_BelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 51)
	@Parameters({"userType","trailerContent"})
	public void verifyPromoCodeResultEvent_Trailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEvent_Trailer(userType,trailerContent);
	}
	
    @Test(priority = 52)
    @Parameters({"userType"})
	public void VideoQualityStreamingChangeEvent(String userType) throws Exception {
		System.out.println("\nVideo Quality Streaming Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoQualityChangeEvent("Best");
	}
    
    @Test(priority = 53)
    @Parameters({"userType"})
	public void VideoStreamingOverWifiChangedEvent(String userType) throws Exception {
		System.out.println("\nVideo Streaming Over Wifi Changed event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoStreamOverWifiChangeEvent();
	}
    
    @Test(priority = 54)
    @Parameters({"userType"})
	public void verifyVideoStreamingAutoPlayChangeEventEvent(String userType) throws Exception {
		System.out.println("\nVideo Streaming Autoplay Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoStreamingAutoPlayChangeEvent();
	}
    
    @Test(priority = 55)
    @Parameters({"userType"})
	public void verifyDownloadQualityChangeEvent(String userType) throws Exception {
		System.out.println("\nVideo Download Quality Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadQualityChangeEvent("Best");
	}

    @Test(priority = 56)
    @Parameters({"userType"})
    public void verifyDownloadOverWifiChangedEvent(String userType) throws Exception {
    	System.out.println("\nDownload Over Wifi Changed event");
    	Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
    	Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
    	Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
    	Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadOverWifiChangedEvent();
    }
    
    @Test(priority = 57)
	@Parameters({"userType"})
	public void AndroidMixPanel_SettingChangedEventValidation(String userType) throws Exception {
    	Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySettingChangedEvent(userType,"Age13+");
    }
    
    @Test(priority = 58)
	@Parameters({"userType"})
	public void AndroidMixPanel_DefaultSettingRestoredEventValidation(String userType) throws Exception {
    	Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDefaultSettingRestoredEvent();
	}
    
    @Test(priority = 59)
	@Parameters({ "userType", "zeeplexContent" })
	public void RentalPurchaseCallInitiatedEvent_Search(String usertype, String zeeplexContent) throws Exception {
		System.out.println("\n Rental purchase call initiated event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.rentalPurchaseCallInitiatedEventOfContentFromSearchPage(usertype, zeeplexContent);
	}
    
    @Test(priority = 60)
	@Parameters({ "userType", "zeeplexContent" })
	public void RentalPurchaseCallReturnedEvent_Search(String usertype, String zeeplexContent) throws Exception {
		System.out.println("\n Rental purchase call returned event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.rentalPurchaseCallReturnedEventOfContentFromSearchPage(usertype, zeeplexContent);
	}
   
    @Test(priority = 61)
	@Parameters({ "userType", "zeeplexContent" })
	public void WidgetCTAs_Event(String usertype, String zeeplexContent) throws Exception {
		System.out.println("\nWidget CTAs Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.WidgetCTAs_Event(usertype, zeeplexContent);
	}
    
    @Test(priority = 62)
	@Parameters({ "userType"})
	public void PaymentScreenImpression_BuyPlan_Header(String usertype) throws Exception {
		System.out.println("\npayment screen impression through Buy plan header");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanHeader(usertype);
	}
	
	@Test(priority = 63)
	@Parameters({ "userType"})
	public void PaymentScreenImpression_BuyPlan_MoreScreen(String usertype) throws Exception {
		System.out.println("\npayment screen impression through Buy plan from More screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanMoreScreen(usertype);
	}
	
	@Test(priority = 64)
	@Parameters({ "userType"})
	public void PaymentScreenImpression_MySubscription_MoreScreen(String usertype) throws Exception {
		System.out.println("\npayment screen impression through MySubscription from More screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_MySubscriptionMoreScreen(usertype);
	}
	
	@Test(priority = 65)
	@Parameters({ "userType", "keyword2"})
	public void PaymentScreenImpression_BuyPlan_BelowThePlayer(String usertype, String keyword) throws Exception {
		System.out.println("\npayment screen impression through Buy Plan Below the player");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanBelowThePlayer(usertype, keyword);
	}
	
	@Test(priority = 66)
	@Parameters({ "userType", "trailerContent"})
	public void PaymentScreenImpression_BuyPlan_OnThePlayerPostTrailer(String usertype, String keyword) throws Exception {
		System.out.println("\npayment screen impression through Buy Plan on the player Post Trailer");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.paymentScreenImpression_BuyPlanPostTrailer(usertype, keyword);
	}
	
	@Test(priority = 67)
	@Parameters({ "userType", "premiumContent" })
	public void RibbonCTAs_Event_Search(String usertype, String premiumContent) throws Exception {
		System.out.println("\nRibbon CTAs Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.RibbonCTAs_Event(usertype, premiumContent);
	}
	
	@Test(priority = 68)
	@Parameters({"userType"})
	public void AndroidMixPanel_PackToggleEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPackToggleEvent(userType);
	}
	
	@Test(priority = 69)
	@Parameters({"userType"})
	public void verifyPackToggleEvent_MoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPackToggleEvent_MoreSection(userType);
	}
	
	@Test(priority = 70)
	@Parameters({"userType"})
	public void verifyPackToggleEvent_MySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPackToggleEvent_MySubscription(userType);
	}
	
	@Test(priority = 71)
	@Parameters({"userType"})
	public void verifyPackToggleEvent_MyTransaction(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPackToggleEvent_MyTransaction(userType);
	}
	
	@Test(priority = 72)
	@Parameters({"userType","keyword3"})
	public void verifyPackToggleEvent_BelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPackToggleEvent_BelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 73)
	@Parameters({"userType","trailerContent"})
	public void verifyPackToggleEvent_Trailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPackToggleEvent_Trailer(userType,trailerContent);
	}
	
	@Test(priority = 74)
	@Parameters({"userType","pTabName"})
	public void AndroidMixPanel_ManualRefreshEventValidation_Home(String pUsertype,String pTabname) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUsertype);
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,pTabname);
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", pTabname, "Homepage");
	}
	
	 @Test(priority = 75)
	 @Parameters({"userType"})
	 public void AndroidMixPanel_Parental_RestrictionEventValidation(String userType) throws Exception {
	    	Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
			Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(userType);
			Zee5ApplicasterMixPanelBusinessLogic.verifyParental_RestrictionEvent(userType,"Age13+");
	    }
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Execution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
	


}
