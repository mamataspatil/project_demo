package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class WebPWAMixpanelScript {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLogin(userType);
	}

//	@Test(priority = 2)
	@Parameters({ "userType"})
	public void verifySkipLoginEvent(String userType) throws Exception {
		System.out.println("Verify Skip Login Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginEvent(userType);
	}
	
//	@Test(priority = 3)
	@Parameters({ "userType", "keyword4"})
	public void verifyResumeEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Resume Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForFreeContent(userType,keyword4);
	}
	
//	@Test(priority = 4)
	@Parameters({ "userType", "keyword" })
	public void verifySkipLoginByClickingOnLoginInRegistrationPopUp(String userType, String keyword) throws Exception {
		System.out.println("Verify Skip Login Event during content playback post clicking on login in registration popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginByClickingOnLoginInRegistrationPopUp(userType,keyword);
	}
	
//	@Test(priority = 5)
	@Parameters({ "userType", "keyword2" })
	public void verifySkipLoginByClickingOnLoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Skip Login Event during content playback post clicking on login button in Get Premium popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginByClickingOnLoginInGetPremiumPopUp(userType,keyword2);
	}
	
//	@Test(priority = 5)
	@Parameters({ "userType" })
	public void verifySkipRegistrationEvent(String userType) throws Exception {
		System.out.println("Verify Skip Registration Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipRegistrationEvent(userType);
	}
	
//	@Test(priority = 6)
	@Parameters({ "userType" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButton(userType);
	}
	
//	@Test(priority = 7)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button On Player");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(userType, keyword2);
	}
	
//	@Test(priority = 8)
	@Parameters({ "userType"})
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(userType);
	}
	
//	@Test(priority = 9)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(userType, keyword2);
	}
	
//	@Test(priority = 10)
	@Parameters({ "userType" })
	public void verifyTVAuthenticationScreenDisplayEvent(String userType) throws Exception {
		System.out.println("Verify TV Authentication Screen Display Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyTVAuthenticationScreenDisplayEvent(userType);
	}
	

//	@Test(priority = 11)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Subscribe button at header");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaSubscribeBtn(userType);
	}

//	@Test(priority = 12)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event for Valid Credentials");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginInitiatedEventForValidCredentials(userType,"emailLogin");
	}

//	@Test(priority = 13)
	@Parameters({ "userType" })
	public void verifyCarouselBannerClickEvent(String userType) throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Home");
	}

//	@Test(priority = 14)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromTray(String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event form Tray");
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromTray("Shows");
	}
	
//	@Test(priority = 15)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromViewMorePage(String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From View More Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromViewMorePage("Shows");
	}
	
//	@Test(priority = 16)
	@Parameters({ "userType" })
	public void verifyThumbnailClickEventFromShowDetailPage(String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From Show Detail Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromShowDetailPage(userType);
	}
	
//	@Test(priority = 17)
	@Parameters({ "userType", "keyword" })
	public void verifyThumbnailClickEventFromPlaybackPage(String keyword,String userType) throws Exception {
		System.out.println("Verify Thumbnail Click Event From Playback Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyThumbnailClickEventFromPlaybackPage(userType,keyword);
	}

//	@Test(priority = 18)
	@Parameters({ "keyword1" })
	public void verifyClearSearchHistoryEvent(String keyword1) throws Exception {
		System.out.println("Verify Clear Search Histroy Event");
		Zee5PWAWEBMixPanelBusinessLogic.clearSearchHistoryEvent( keyword1);
	}
	
//	@Test(priority = 19)
	@Parameters({ "keyword" })
	public void verifyEpisodeListChosenEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Episode List Chosen Event in Show Detail page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyEpisodeListChosenEventFromShowDetailPage(keyword);
	}
	
//	@Test(priority = 20)
	@Parameters({ "keyword" })
	public void verifyContentBucketSwipeEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event in Show Detail page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEventFromShowDetailPage(keyword);
	}
	
//	@Test(priority = 21)
	@Parameters({ "keyword" })
	public void verifyViewMoreSelectedEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify View More Selected Event For content played from Show detail page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyViewMoreSelectedEventFromShowDetailPage(keyword);
	}
	
//	@Test(priority = 22)
	public void verifySearchExecutedEvent() throws Exception {
		System.out.println("Verify Search Executed Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchExecutedEvent();
	}
	
	@Test(priority = 23)
	public void verifyScreenViewEvent() throws Exception {
		System.out.println("Verify Screen View Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScreenViewEvent("Shows");
	}
	
//	@Test(priority = 24)
	@Parameters({ "userType", "keyword1" })
	public void verifyAddtoWatchlistFromPlaybackPage(String keyword1,String userType) throws Exception {
		System.out.println("Verify Add to Watchlist Event From Playback Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAddtoWatchlistFromPlaybackPage(userType,keyword1);
	}
	
//	@Test(priority = 25)
	@Parameters({ "userType", "keyword1" })
	public void verifyRemoveFomWatchlistEventFromPlaybackPage(String keyword1,String userType) throws Exception {
		System.out.println("Verify Remove From Watchlist Event From Playback Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRemoveFomWatchlistEventFromPlaybackPage(userType,keyword1);
	}
	
	
	@Test(priority = 26)
	@Parameters({ "userType" })
	public void verifyAddToWatchlistEventByMouseHover(String userType) throws Exception {
		System.out.println("Verify Add to Watchlist Event by mouse hovering on a Content Card");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAddToWatchlistEventByMouseHover(userType);
	}
	
	@Test(priority = 27)
	@Parameters({ "userType",  "keyword" })
	public void verifyRemoveFomWatchlistEventByMouseHoverInShowDetailPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Remove from Watchlist Event by mouse hovering on a Content Card In show detail page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRemoveFomWatchlistEventByMouseHoverInShowDetailPage(userType,keyword);
	}
	
//	@Test(priority = 28)
//	public void verifyShareEventByMouseHover() throws Exception {
//		System.out.println("Verify Share Event By Mouse Hovering on a Content Card");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventByMouseHover();
//	}
	
	
	@Test(priority = 29)
	@Parameters({ "keyword1" })
	public void verifyShareEventFromPlaybackPage(String keyword1) throws Exception {
		System.out.println("Verify Share Event From Playback Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventFromPlaybackPage(keyword1);
	}
	

	
	@Test(priority = 30)
	@Parameters({ "keyword1" })
	public void verifyQualityChangeEvent(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEvent(keyword1);
		}
	
	@Test(priority = 31)
	@Parameters({ "userType", "keyword1" })
	public void verifyContentBucketSwipeEventInPlaybackPage(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEventInPlaybackPage(keyword1);
		}
	
	@Test(priority = 32)
	public void verifyDisplayLanguageChangeEvent() throws Exception {
		System.out.println("Verify Display Language Change Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyDisplayLanguageChangeEvent();
	}
	
	@Test(priority = 33)
	public void verifyDefaultSettingRestoredEvent() throws Exception {
		System.out.println("Verify Default Setting Restored Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyDefaultSettingRestoredEvent();
	}
	
	@Test(priority = 34)
	public void verifyAdBannerImpressionEvent(String tabName) throws Exception {
		System.out.println("Verify Ad Banner Impression Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdBannerImpressionEvent("Shows");
	}
	
	@Test(priority = 35)
	public void verifyCarouselBannerSwipeEvent() throws Exception {
		System.out.println("Verify Carousel Banner Swipe Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerSwipeEvent("Shows");
	}
	
	@Test(priority = 36)
	public void verifyContentBucketSwipeEvent() throws Exception {
		System.out.println("Verify Content Bucket Swipe Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEvent("Shows");
	}
	
	@Test(priority = 37)
	@Parameters({ "userType" })
	public void verifyCTAsEventHeader(String userType) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventHeader(userType,"Shows");
	}
	
	@Test(priority = 38)
	public void verifyBannerAutoplayEventForNewsContent() throws Exception {
		System.out.println("Verify Banner Autoplay Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyBannerAutoplayEventForNewsContent();
	}
	
	@Test(priority = 39)
	public void verifyMuteChangedEventForNewsContent() throws Exception {
		System.out.println("Verify Mute Changed Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyMuteChangedEventForNewsContent();
	}
	
	
	@Test(priority = 41)
	@Parameters({ "userType"})
	public void verifyResumeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Resume Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 42)
	@Parameters({ "userType", "keyword1"})
	public void verifyResumeEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Resume Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForTrailer(userType,keyword1);
	}
	
//	@Test(priority = 43)
//	public void verifyResumeEventForCarouselContent() throws Exception {
//		System.out.println("Verify Resume Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForCarouselContent();
//	}
//	
//	@Test(priority = 44)
//	public void verifyResumeEventForContentInTray() throws Exception {
//		System.out.println("Verify Resume Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentInTray();
//	}
	
	@Test(priority = 45)
	@Parameters({"keyword1"})
	public void verifyResumeEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Resume Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 46)
	@Parameters({ "userType", "keyword"})
	public void verifyResumeEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Resume Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 47)
	@Parameters({ "userType", "keyword"})
	public void verifyResumeEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Resume Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 48)
	@Parameters({ "userType", "keyword"})
	public void verifyRemoveFromWatchlistEventFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Remove From Watchlist Event for Content from My Watchlist page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRemoveFromWatchlistEventFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 49)
	public void verifyResumeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Resume Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentInMegamenu();
	}
	
	@Test(priority = 50)
	@Parameters({ "userType" })
	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Started Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyChangePasswordStartedEvent(userType);
	}
	
	@Test(priority = 51)
	@Parameters({ "userType" })
	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Result Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyChangePasswordResultEvent(userType);
	}
	
	@Test(priority = 52)
	@Parameters({ "userType" })
	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		System.out.println("Verify Profile Update Result Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyProfileUpdateResultEvent(userType);
	}
	
	@Test(priority = 53)
	public void verifyShareEventForUpcomingProgram() throws Exception {
		System.out.println("Verify Share Event for Upcoming Program");
		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventForUpcomingProgram();
	}
	
	@Test(priority = 54)
	@Parameters({ "userType" })
	public void verifySetReminderEventForUpcomingProgram(String userType) throws Exception {
		System.out.println("Verify Set Reminder Event For Upcoming Program");
		Zee5PWAWEBMixPanelBusinessLogic.verifySetReminderEventForUpcomingProgram(userType);
	}
	
	@Test(priority = 55)
	public void verifySearchCancelledEvent() throws Exception {
		System.out.println("Verify Search Cancelled Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchCancelledEvent();
	}
	
	@Test(priority = 56)
	@Parameters({ "userType" })
	public void verifyParentalRestriction(String userType) throws Exception {
		System.out.println("Verify Parental Restriction Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch(true);
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalRestrictionEvent(userType,"NoRestriction");
		
	}
	
	@Test(priority = 57)
	@Parameters({ "keyword" })
	public void verifyShareEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Share Event From Show Detail Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyShareEventFromShowDetailPage(keyword);
	}
	
	@Test(priority = 58)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForFreeContentAbrupt(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForFreeContentAbrupt(userType,keyword4);
	}
	
	@Test(priority = 59)
	@Parameters({ "userType"})
	public void verifyVideoWatchDurationEventForPremiumContentAbrupt(String userType) throws Exception {
		System.out.println("Verify Video Watch Duration when video is closed abruptly Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForPremiumContentAbrupt(userType,"Home");
	}
	
	@Test(priority = 60)
	@Parameters({ "userType", "keyword1"})
	public void verifyVideoWatchDurationEventForTrailerAbrupt(String userType,String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForTrailerAbrupt(userType,keyword1);
	}
	
//	@Test(priority = 61)
//	public void verifyVideoWatchDurationEventForCarouselContentAbrupt() throws Exception {
//		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForCarouselContentAbrupt();
//	}
//	
//	@Test(priority = 62)
//	public void verifyVideoWatchDurationEventForContentInTrayAbrupt() throws Exception {
//		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInTrayAbrupt();
//	}
//	
	@Test(priority = 63)
	@Parameters({"keyword1"})
	public void verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(keyword1);
	}
	
	@Test(priority = 64)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(userType,keyword);
	}
	
	@Test(priority = 65)
	public void verifyVideoWatchDurationEventForContentInMegamenuAbrupt() throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInMegamenuAbrupt();
	}
	
	@Test(priority = 66)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoWatchDurationEventForContentInPlaylistAbrupt(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInPlaylistAbrupt(userType,keyword);
	}
	
	
	@Test(priority = 67)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoExitEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Exit Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 68)
	@Parameters({"keyword1"})
	public void verifyVideoExitEventAfterRefreshingPage(String keyword1) throws Exception {
		System.out.println("Verify Video Exit Event after refreshing a page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventAfterRefreshingPage(keyword1);
	}
	
	@Test(priority = 69)
	@Parameters({"audioTrackContent",  "userType" })
	public void verifyViewMoreSelectedEventFromPlaybackPage(String audioTrackContent,String userType) throws Exception {
		System.out.println("Verify View More Selected Event For content played from Playback page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyViewMoreSelectedEventFromPlaybackPage(audioTrackContent,userType);
	}
	
	
//	@Test(priority = 70)
//	public void verifyViewMoreSelectedEventFromTray() throws Exception {
//		System.out.println("Verify View More Selected Event For content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyViewMoreSelectedEventFromTray();
//	}
	
	@Test(priority = 71)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaBuySubscription(userType);
	}
	
	@Test(priority = 72)
	@Parameters({ "userType" })
	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		System.out.println("Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventViaPrepaidCode(userType);
	}
	
	@Test(priority = 73)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionSelectedEvent(userType);
	}
	
	@Test(priority = 74)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Valid code");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPromoCodeResultEventForValid(userType);
	}
	
	@Test(priority = 75)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Invalid code");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPromoCodeResultEventForInvalid(userType);
	}
	
	@Test(priority = 76)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event By selecting Club Pack");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionSelectedEventByClubPack(userType);
	}
	
	@Test(priority = 77)
	public void verifySessionEvent() throws Exception {
		System.out.println("Verify Session Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch(true);
	}
	
	@Test(priority = 78)
	@Parameters({ "userType" })
	public void verifyLoginResultEventForValidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event for Valid Credentials");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginResultEventForValidCredentials(userType,"emailLogin");
	}
	
	@Test(priority = 79)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEvent(String userType) throws Exception {
		System.out.println("Verify Register Screen Display Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRegisterScreenDisplayEvent(userType);
	}
	
	@Test(priority = 80)
	@Parameters({ "userType", "keyword2" })
	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForGetPremiumPopUp(userType,keyword2);
	}
	
	@Test(priority = 81)
	@Parameters({ "userType", "keyword4"})
	public void verifyPauseEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Pause Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 82)
	@Parameters({ "userType"})
	public void verifyPauseEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Pause Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 83)
	@Parameters({ "userType", "keyword1"})
	public void verifyPauseEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Pause Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForTrailer(userType,keyword1);
	}
	
//	@Test(priority = 84)
//	public void verifyPauseEventForCarouselContent() throws Exception {
//		System.out.println("Verify Pause Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForCarouselContent();
//	}
//	
//	@Test(priority = 85)
//	public void verifyPauseEventForContentInTray() throws Exception {
//		System.out.println("Verify Pause Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentInTray();
//	}
	
	@Test(priority = 86)
	@Parameters({"keyword1"})
	public void verifyPauseEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Pause Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 87)
	@Parameters({ "userType", "keyword"})
	public void verifyPauseEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Pause Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 88)
	@Parameters({ "userType", "keyword"})
	public void verifyPauseEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Pause Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 89)
	public void verifyPauseEventForLinearContent() throws Exception {
		System.out.println("Verify Pause Event For Linear Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForLinearContent();
	}
	
	@Test(priority = 90)
	public void verifyResumeEventForLinearContent() throws Exception {
		System.out.println("Verify Resume Event For Linear Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForLinearContent();
	}
	
	@Test(priority = 91)
	@Parameters({ "userType", "keyword4"})
	public void verifyQualityChangeEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Quality Change Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 92)
	@Parameters({ "userType"})
	public void verifyQualityChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Quality Change Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 93)
	@Parameters({ "keyword1"})
	public void verifyQualityChangeEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForTrailer(keyword1);
	}
	
//	@Test(priority = 94)
//	public void verifyQualityChangeEventForCarouselContent() throws Exception {
//		System.out.println("Verify Quality Change Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForCarouselContent();
//	}
//	
//	@Test(priority = 95)
//	public void verifyQualityChangeEventForContentInTray() throws Exception {
//		System.out.println("Verify Quality Change Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentInTray();
//	}
	
	@Test(priority = 96)
	@Parameters({"keyword1"})
	public void verifyQualityChangeEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Quality Change Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 97)
	@Parameters({ "userType", "keyword"})
	public void verifyQualityChangeEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Quality Change Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 98)
	public void verifyQualityChangeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentInMegamenu();
	}
	
	@Test(priority = 99)
	@Parameters({ "userType", "keyword"})
	public void verifyQualityChangeEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 100)
	@Parameters({ "userType", "keyword4"})
	public void verifyQualityChangeEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Quality Change Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 101)
	@Parameters({"freeContentURL"})
	public void verifyQualityChangeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Quality Change Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 102)
	public void verifyQualityChangeEventForLinearContent() throws Exception {
		System.out.println("Verify Quality Change Event For Linear Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyQualityChangeEventForLinearContent();
	}
	
	@Test(priority = 103)
	@Parameters({ "userType", "keyword4"})
	public void verifyPauseEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Pause Event for content autoplayed from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 104)
	@Parameters({ "userType", "keyword4"})
	public void verifyResumeEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Resume Event for content autoplayed from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 105)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoViewEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video View Event for content autoplayed from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 106)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoExitEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Exit Event for content autoplayed from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForContentFromUpnextRail(userType,keyword4);
	}
	
	
	@Test(priority = 107)
	@Parameters({"freeContentURL"})
	public void verifyVideoViewEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Video View Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 108)
	@Parameters({"freeContentURL"})
	public void verifyPauseEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Pause Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 109)
	@Parameters({"freeContentURL"})
	public void verifyResumeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Resume Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentFromSharedLink(freeContentURL);
	}
	
	
	@Test(priority = 110)
	@Parameters({"freeContentURL"})
	public void verifyVideoExitEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Video Exit Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 111)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForFreeContentComplete(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForFreeContentComplete(userType,keyword4);
	}
	
	@Test(priority = 112)
	@Parameters({ "userType"})
	public void verifyVideoWatchDurationEventForPremiumContentComplete(String userType) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForPremiumContentComplete(userType,"Home");
	}
	
	@Test(priority = 113)
	@Parameters({ "userType", "keyword1"})
	public void verifyVideoWatchDurationEventForTrailerComplete(String userType,String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event  when user completely watches Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForTrailerComplete(userType,keyword1);
	}
	
//	@Test(priority = 114)
//	public void verifyVideoWatchDurationEventForCarouselContentComplete() throws Exception {
//		System.out.println("Verify Video Watch Duration Event when user completely watches Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForCarouselContentComplete();
//	}
//	
//	@Test(priority = 115)
//	public void verifyVideoWatchDurationEventForContentInTrayComplete() throws Exception {
//		System.out.println("Verify Video Watch Duration Event when user completely watches Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInTrayComplete();
//	}
	
	@Test(priority = 116)
	@Parameters({"keyword1"})
	public void verifyVideoWatchDurationEventForContentFromSearchPageComplete(String keyword1) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSearchPageComplete(keyword1);
	}
	
	@Test(priority = 117)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(userType,keyword);
	}
	
	@Test(priority = 118)
	public void verifyVideoWatchDurationEventForContentInMegamenuComplete() throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInMegamenuComplete();
	}
	
	@Test(priority = 119)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoWatchDurationEventForContentInPlaylistComplete(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentInPlaylistComplete(userType,keyword);
	}
	
	@Test(priority = 120)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForContentFromUpnextRailComplete(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event When user completely watches the  auto-played content from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromUpnextRailComplete(userType,keyword4);
	}

	@Test(priority = 121)
	@Parameters({ "freeContentURL"})
	public void verifyVideoWatchDurationEventForContentFromSharedLinkComplete(String freeContentURL) throws Exception {
		System.out.println("Verify Video Watch Duration Event when user completely watches the content playback shared through shared link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSharedLinkComplete(freeContentURL);
	}
	
	@Test(priority = 122)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly on auto-played content from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(userType,keyword4);
	}
	
	@Test(priority = 123)
	@Parameters({"freeContentURL"})
	public void verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(String freeContentURL) throws Exception {
		System.out.println("Verify Video Watch Duration Event when video is closed abruptly For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(freeContentURL);
	}
	
	@Test(priority = 124)
	@Parameters({ "userType", "keyword4"})
	public void verifyScrubSeekEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 125)
	@Parameters({ "userType"})
	public void verifyScrubSeekEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 126)
	@Parameters({ "keyword1"})
	public void verifyScrubSeekEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForTrailer(keyword1);
	}
	
//	@Test(priority = 127)
//	public void verifyScrubSeekEventForCarouselContent() throws Exception {
//		System.out.println("Verify Scrub/Seek Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForCarouselContent();
//	}
//	
//	@Test(priority = 128)
//	public void verifyScrubSeekEventForContentInTray() throws Exception {
//		System.out.println("Verify Scrub/Seek Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForContentInTray();
//	}
	
	@Test(priority = 129)
	@Parameters({"keyword1"})
	public void verifyScrubSeekEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 130)
	@Parameters({ "userType", "keyword"})
	public void verifyScrubSeekEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 131)
	public void verifyScrubSeekEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForContentInMegamenu();
	}
	
	@Test(priority = 132)
	@Parameters({ "userType", "keyword"})
	public void verifyScrubSeekEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 133)
	@Parameters({ "userType", "keyword4"})
	public void verifyScrubSeekEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Scrub/Seek Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 134)
	@Parameters({"freeContentURL"})
	public void verifyScrubSeekEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Scrub/Seek Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyScrubSeekEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 135)
	@Parameters({"keyword1"})
	public void verifyMuteChangedEventDuringContentPlayback(String keyword1) throws Exception {
		System.out.println("Verify Mute Changed Event During Content Playback");
		Zee5PWAWEBMixPanelBusinessLogic.verifyMuteChangedEventDuringContentPlayback(keyword1);
	}
	
	@Test(priority = 136)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInPaymentPage(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event in payment page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInPaymentPage(userType);
	}
	
	@Test(priority = 137)
	@Parameters({ "userType"})
	public void verifyLoginResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event post entering invalid credentials");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginResultEventForInvalidCredentials(userType,"mobileNumberLogin");
	}
	
	@Test(priority = 138)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInSignInScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Sign In Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInSignInScreen(userType);
	}
	
	@Test(priority = 139)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInOTPScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In OTP Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInOTPScreen(userType);
	}
	
	@Test(priority = 140)
	@Parameters({ "userType"})
	public void verifyRegistrationInitiatedEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Registration Initiated Event post entering invalid credentials");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRegistrationInitiatedEventForInvalidCredentials(userType);
	}
	
	@Test(priority = 141)
	@Parameters({ "userType"})
	public void verifyRegistrationResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Registration Result Event post entering invalid credentials");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRegistrationResultEventForInvalidCredentials(userType);
	}
	
	@Test(priority = 142)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInSignUpScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Sign Up Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInSignUpScreen(userType);
	}
	
	@Test(priority = 143)
	@Parameters({ "userType"})
	public void verifySubscriptionCallReturnedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Call Returned Event when user makes unsuccessful transaction by quitting the payment gateway screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallReturnedEvent(userType);
	}
	
	
	@Test(priority = 144)
	@Parameters({ "userType"})
	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for All access pack");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallInitiatedEvent(userType);
	}
	
	
	@Test(priority = 145)
	@Parameters({ "userType"})
	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Call Initiated Event for Club pack");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionCallInitiatedEventClubPack(userType);
	}
	
	@Test(priority = 146)
	@Parameters({ "userType"})
	public void verifyLogoutEvent(String userType) throws Exception {
		System.out.println("Verify Logout Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLogoutEvent(userType);
	}
	
	@Test(priority = 147)
	public void verifySkipLoginThroughBeforeTVContent() throws Exception {
		System.out.println("Verify Skip Login Event gets triggered when user click on close button in login popup "
				+ "on clicking login in Get premium popup on accessing before tv content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipLoginThroughBeforeTVContent();
	}
	
	@Test(priority = 148)
	@Parameters({ "userType"})
	public void verifySettingChangedEventAfterAccountVerification(String userType) throws Exception {
		System.out.println("Verify Setting Changed Event when Parental Control Account Verification is done");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch(true);
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAWEBMixPanelBusinessLogic.verifySettingChangedEventAfterAccountVerification(userType);
	}
	
	@Test(priority = 149)
	@Parameters({ "userType"})
	public void verifySettingChangedEventAfterParentalPinIsSet(String userType) throws Exception {
		System.out.println("Verify Setting Changed Event when Parental Control PIN is Set");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch(true);
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAWEBMixPanelBusinessLogic.verifySettingChangedEventAfterParentalPinIsSet(userType);
	}
	
	@Test(priority = 150)
	@Parameters({ "userType"})
	public void verifySettingChangedEventAfterAgeIsSet(String userType) throws Exception {
		System.out.println("Verify Setting Changed Event when Parental Control Age is Set");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch(true);
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAWEBMixPanelBusinessLogic.verifySettingChangedEventAfterAgeIsSet(userType);
	}
	
	@Test(priority = 151)
	public void verifyToastMessageImpressionEventAfterResetSettingsToDefault() throws Exception {
		System.out.println("Verify Toast Message Impression Event After Reset Settings To Default");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventAfterResetSettingsToDefault();
	}
	
	@Test(priority = 152)
	public void verifySearchButtonClickEvent() throws Exception {
		System.out.println("Verify Search Button Click Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchButtonClickEvent();
	}
	
	@Test(priority = 153)
	@Parameters({"keyword"})
	public void verifySearchResultClickedEvent(String keyword) throws Exception {
		System.out.println("Verify Search Result Clicked Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchResultClickedEvent(keyword);
	}
	
	@Test(priority = 154)
	@Parameters({ "userType", "keyword" })
	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword) throws Exception {
		System.out.println("Verify Pop Up Launch Event when Native popup is displayed");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForRegisterPopUp(userType,keyword);
	}
	
	
	@Test(priority = 155)
	@Parameters({ "userType" })
	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType) throws Exception {
		System.out.println("Verify Pop Up Launch Event when Complete Profile popup is displayed");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForCompleteProfilePopUp(userType);
	}
	
	
	// Login through ClubUser Id
	@Test(priority = 156)
	@Parameters({ "userType", "keyword6" })
	public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
		System.out.println("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpLaunchEventForClubUser(userType,keyword6);
	}
	
	@Test(priority = 157)
	@Parameters({ "userType", "keyword6" })
	public void verifyPopUpCTAsEvent(String userType, String keyword6) throws Exception {
		System.out.println("Verify Pop Up CTA's Event when user clicks on CTA button displayed on the popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPopUpCTAsEvent(userType,keyword6);
	}
	
	@Test(priority = 158)
	@Parameters({ "userType" })
	public void verifyLoginUsernameEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Username Entered Event");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginUsernameEnteredEvent(userType,"UserName");
	}
	
	@Test(priority = 159)
	@Parameters({ "userType" })
	public void verifyLoginPasswordEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Password Entered Event");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginUsernameEnteredEvent(userType,"Password");
	}
	
	@Test(priority = 160)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInParentalControlScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Parental Control Screen");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch(true);
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInParentalControlScreen(userType);
	}
	
	@Test(priority = 161)
	@Parameters({ "userType" })
	public void verifyDeviceAuthenticationEvent(String userType) throws Exception {
		System.out.println("Verify Device Authentication Event when authentication fails in TV Authentication screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyDeviceAuthenticationEvent(userType);
	}
	
	@Test(priority = 162)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInAuthenticateScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Authenticate Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInAuthenticateScreen(userType);
	}
	
	
	@Test(priority = 163)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventForAddToWatchlist(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event after adding card to watchlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventForAddToWatchlist(userType);
	}
	
	
	@Test(priority = 164)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventForRemoveFomWatchlist(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event after removing card from watchlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventForRemoveFomWatchlist(userType);
	}
	
	@Test(priority = 165)
	@Parameters({ "userType", "keyword1" })
	public void verifyToastMessageImpressionEventForEmbedPopUp(String userType,String keyword1) throws Exception {
		System.out.println("Verify Toast Message Impression Event when user gets a toast message in embed popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventForEmbedPopUp(userType,keyword1);
	}
	
	@Test(priority = 166)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekForwardEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 167)
	@Parameters({ "userType"})
	public void verifyAutoSeekForwardEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 168)
	@Parameters({ "keyword1"})
	public void verifyAutoSeekForwardEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForTrailer(keyword1);
	}
	
//	@Test(priority = 169)
//	public void verifyAutoSeekForwardEventForCarouselContent() throws Exception {
//		System.out.println("Verify Auto Seek Forward Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForCarouselContent();
//	}
//	
//	@Test(priority = 170)
//	public void verifyAutoSeekForwardEventForContentInTray() throws Exception {
//		System.out.println("Verify Auto Seek Forward Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentInTray();
//	}
	
	@Test(priority = 171)
	@Parameters({"keyword1"})
	public void verifyAutoSeekForwardEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 172)
	@Parameters({ "userType", "keyword"})
	public void verifyAutoSeekForwardEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 173)
	public void verifyAutoSeekForwardEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentInMegamenu();
	}
	
	@Test(priority = 174)
	@Parameters({ "userType", "keyword"})
	public void verifyAutoSeekForwardEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 175)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekForwardEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 176)
	@Parameters({"freeContentURL"})
	public void verifyAutoSeekForwardEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Auto Seek Forward Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekForwardEventForContentFromSharedLink(freeContentURL);
	}
	
	@Test(priority = 177)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekRewindEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 178)
	@Parameters({ "userType"})
	public void verifyAutoSeekRewindEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 179)
	@Parameters({ "keyword1"})
	public void verifyAutoSeekRewindEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForTrailer(keyword1);
	}
	
//	@Test(priority = 180)
//	public void verifyAutoSeekRewindEventForCarouselContent() throws Exception {
//		System.out.println("Verify Auto Seek Rewind Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForCarouselContent();
//	}
//	
//	@Test(priority = 181)
//	public void verifyAutoSeekRewindEventForContentInTray() throws Exception {
//		System.out.println("Verify Auto Seek Rewind Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentInTray();
//	}
	
	@Test(priority = 182)
	@Parameters({"keyword1"})
	public void verifyAutoSeekRewindEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 183)
	@Parameters({ "userType", "keyword"})
	public void verifyAutoSeekRewindEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 184)
	public void verifyAutoSeekRewindEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentInMegamenu();
	}
	
	@Test(priority = 185)
	@Parameters({ "userType", "keyword"})
	public void verifyAutoSeekRewindEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 186)
	@Parameters({ "userType", "keyword4"})
	public void verifyAutoSeekRewindEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 187)
	@Parameters({"freeContentURL"})
	public void verifyAutoSeekRewindEventForContentFromSharedLink(String freeContentURL) throws Exception {
		System.out.println("Verify Auto Seek Rewind Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAutoSeekRewindEventForContentFromSharedLink(freeContentURL);
	}
	
//	@Test(priority = 188)
//	@Parameters({ "userType", "keyword4"})
//	public void verifyParentalOverlayImpressionEventForFreeContent(String userType,String keyword4) throws Exception {
//		System.out.println("Verify Parental Overlay Impression Event For Free Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForFreeContent(userType,keyword4);
//	}
	
	@Test(priority = 189)
	@Parameters({ "userType"})
	public void verifyParentalOverlayImpressionEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 190)
	@Parameters({ "keyword1", "userType"})
	public void verifyParentalOverlayImpressionEventForTrailer(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForTrailer(keyword1,userType);
	}
	
//	@Test(priority = 191)
//	@Parameters({ "userType"})
//	public void verifyParentalOverlayImpressionEventForCarouselContent(String userType) throws Exception {
//		System.out.println("Verify Parental Overlay Impression Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForCarouselContent(userType);
//	}
	
//	@Test(priority = 192)
//	@Parameters({ "userType"})
//	public void verifyParentalOverlayImpressionEventForContentInTray(String userType) throws Exception {
//		System.out.println("Verify Parental Overlay Impression Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForContentInTray(userType);
//	}
	
	@Test(priority = 193)
	@Parameters({"keyword1", "userType"})
	public void verifyParentalOverlayImpressionEventForContentFromSearchPage(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForContentFromSearchPage(keyword1,userType);
	}
	
	@Test(priority = 194)
	@Parameters({ "userType", "keyword"})
	public void verifyParentalOverlayImpressionEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 195)
	@Parameters({ "userType"})
	public void verifyParentalOverlayImpressionEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForContentInMegamenu(userType);
	}
	
	@Test(priority = 196)
	@Parameters({ "userType", "keyword"})
	public void verifyParentalOverlayImpressionEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 197)
	@Parameters({ "userType", "keyword4"})
	public void verifyParentalOverlayImpressionEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForContentFromUpnextRail(userType,keyword4);
	}
	
	@Test(priority = 198)
	@Parameters({"freeContentURL", "userType"})
	public void verifyParentalOverlayImpressionEventForContentFromSharedLink(String freeContentURL,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventForContentFromSharedLink(freeContentURL,userType);
	}
	
	@Test(priority = 199)
	@Parameters({"keyword1", "userType"})
	public void verifyParentalOverlayImpressionEventAfterPageRefresh(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Impression Event after refreshing the page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayImpressionEventAfterPageRefresh(keyword1,userType);
	}
	
	
	@Test(priority = 200)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventAfterUpdatingProfile(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression event when user updates the profile details");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventAfterUpdatingProfile(userType);
	}
	
	@Test(priority = 201)
	@Parameters({ "userType"})
	public void verifyRecommendedRailImpressionEventByScrollingPage() throws Exception {
		System.out.println("Verify Recommended Rail Impression event when user is able to see the recommended tray by scrolling down the page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRecommendedRailImpressionEventByScrollingPage("Home","Trending");
	}
	
	
	@Test(priority = 202)
	@Parameters({ "keyword"})
	public void verifyRecommendedRailImpressionEventInShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Recommended Rail Impression Event In Show Detail Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRecommendedRailImpressionEventInShowDetailPage(keyword,"Trending");
	}
	
	@Test(priority = 203)
	@Parameters({ "keyword1"})
	public void verifyRecommendedRailImpressionEventInConsumptionScreen(String keyword1) throws Exception {
		System.out.println("Verify Recommended Rail Impression Event In Consumption Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyRecommendedRailImpressionEventInConsumptionScreen(keyword1,"Trending");
	}
	
	
	@Test(priority = 204)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventInPackSelectionPage(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression event in pack selection page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInPackSelectionPage(userType);
	}
	
	@Test(priority = 205)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForFreeContent(userType,audioTrackContent);
	}
	
	@Test(priority = 206)
	@Parameters({ "userType"})
	public void verifyAudioLanguageChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Audio Language Change Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 207)
	@Parameters({ "keyword1"})
	public void verifyAudioLanguageChangeEventForTrailer(String audioTrackTrailerContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForTrailer(audioTrackTrailerContent);
	}
	
	@Test(priority = 208)
	public void verifyAudioLanguageChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Audio Language Change Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForCarouselContent();
	}
	
	@Test(priority = 209)
	public void verifyAudioLanguageChangeEventForContentInTray() throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentInTray();
	}
	
	@Test(priority = 210)
	@Parameters({"audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentFromSearchPage(String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromSearchPage(audioTrackContent);
	}
	
	@Test(priority = 211)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	@Test(priority = 212)
	public void verifyAudioLanguageChangeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentInMegamenu();
	}
	
	@Test(priority = 213)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 214)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAudioLanguageChangeEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Audio Language Change Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 215)
	@Parameters({"audioTrackURL"})
	public void verifyAudioLanguageChangeEventForContentFromSharedLink(String audioTrackURL) throws Exception {
		System.out.println("Verify Audio Language Change Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAudioLanguageChangeEventForContentFromSharedLink(audioTrackURL);
	}
	
	@Test(priority = 216)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForFreeContent(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForFreeContent(userType,subtitleTrackContent);
	}
	
	@Test(priority = 217)
	@Parameters({ "userType"})
	public void verifySubtitleLanguageChangeEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 218)
	@Parameters({ "keyword5"})
	public void verifySubtitleLanguageChangeEventForTrailer(String keyword5) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForTrailer(keyword5);
	}
	
	@Test(priority = 219)
	public void verifySubtitleLanguageChangeEventForCarouselContent() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Carousel Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForCarouselContent();
	}
	
	@Test(priority = 220)
	public void verifySubtitleLanguageChangeEventForContentInTray() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Tray");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentInTray();
	}
	
	@Test(priority = 221)
	@Parameters({"subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentFromSearchPage(String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromSearchPage(subtitleTrackContent);
	}
	
	@Test(priority = 222)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 223)
	public void verifySubtitleLanguageChangeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentInMegamenu();
	}
	
	@Test(priority = 224)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentInPlaylist(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentInPlaylist(userType,subtitleTrackContent);
	}
	
	@Test(priority = 225)
	@Parameters({ "userType", "subtitleTrackContent"})
	public void verifySubtitleLanguageChangeEventForContentFromUpnextRail(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromUpnextRail(userType,subtitleTrackContent);
	}
	
	@Test(priority = 226)
	@Parameters({"audioTrackURL"})
	public void verifySubtitleLanguageChangeEventForContentFromSharedLink(String subtitleTrackURL) throws Exception {
		System.out.println("Verify Subtitle Language Change Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubtitleLanguageChangeEventForContentFromSharedLink(subtitleTrackURL);
	}
	
	@Test(priority = 227)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForFreeContent(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForFreeContent(userType,freeMovie2);
	}
	
	@Test(priority = 228)
	@Parameters({ "userType"})
	public void verifySkipIntroEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Skip Intro Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 229)
	@Parameters({ "keyword5"})
	public void verifySkipIntroEventForTrailer(String keyword5) throws Exception {
		System.out.println("Verify Skip Intro Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForTrailer(keyword5);
	}
	
//	@Test(priority = 230)
//	public void verifySkipIntroEventForCarouselContent() throws Exception {
//		System.out.println("Verify Skip Intro Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForCarouselContent();
//	}
//	
//	@Test(priority = 231)
//	public void verifySkipIntroEventForContentInTray() throws Exception {
//		System.out.println("Verify Skip Intro Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentInTray();
//	}
	
	@Test(priority = 232)
	@Parameters({"freeMovie2"})
	public void verifySkipIntroEventForContentFromSearchPage(String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromSearchPage(freeMovie2);
	}
	
	@Test(priority = 233)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForContentFromMyWatchlistPage(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromMyWatchlistPage(userType,freeMovie2);
	}
	
	@Test(priority = 234)
	public void verifySkipIntroEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentInMegamenu();
	}
	
	@Test(priority = 235)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForContentInPlaylist(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentInPlaylist(userType,freeMovie2);
	}
	
	@Test(priority = 236)
	@Parameters({ "userType", "freeMovie2"})
	public void verifySkipIntroEventForContentFromUpnextRail(String userType,String freeMovie2) throws Exception {
		System.out.println("Verify Skip Intro Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromUpnextRail(userType,freeMovie2);
	}
	
	@Test(priority = 237)
	@Parameters({"skipIntroURL"})
	public void verifySkipIntroEventForContentFromSharedLink(String skipIntroURL) throws Exception {
		System.out.println("Verify Skip Intro Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifySkipIntroEventForContentFromSharedLink(skipIntroURL);
	}
	
	@Test(priority = 238)
	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel() throws Exception {
		System.out.println("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");
		Zee5PWAWEBMixPanelBusinessLogic.verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel();
	}
	
	@Test(priority = 239)
	@Parameters({ "userType"})
	public void verifyPrepaidCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Prepaid Code Result Event For Invalid code");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPrepaidCodeResultEventForInvalid(userType);
	}
	
	@Test(priority = 240)
	@Parameters({ "userType"})
	public void verifyLoginInitiatedEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event post entering invalid credentials");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginInitiatedEventForInvalidCredentials(userType,"emailLogin");
	}
	
	@Test(priority = 241)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 242)
	@Parameters({ "userType","keyword1"})
	public void verifyAdViewEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad View Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForTrailer(userType,keyword1);
	}
	
//	@Test(priority = 243)
//	@Parameters({ "userType"})
//	public void verifyAdViewEventForCarouselContent(String userType) throws Exception {
//		System.out.println("Verify Ad View Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForCarouselContent(userType);
//	}
//	
//	@Test(priority = 244)
//	@Parameters({ "userType"})
//	public void verifyAdViewEventForContentInTray(String userType) throws Exception {
//		System.out.println("Verify Ad View Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentInTray(userType);
//	}
	
	@Test(priority = 245)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdViewEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 246)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	@Test(priority = 247)
	@Parameters({ "userType"})
	public void verifyAdViewEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentInMegamenu(userType);
	}
	
	@Test(priority = 248)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 249)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdViewEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad View Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 250)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdViewEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad View Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdViewEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 251)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 252)
	@Parameters({"userType", "keyword1"})
	public void verifyAdForcedExitEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForTrailer(userType,keyword1);
	}
	
//	@Test(priority = 253)
//	@Parameters({ "userType"})
//	public void verifyAdForcedExitEventForCarouselContent(String userType) throws Exception {
//		System.out.println("Verify Ad Forced Exit Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForCarouselContent(userType);
//	}
//	
//	@Test(priority = 254)
//	@Parameters({ "userType"})
//	public void verifyAdForcedExitEventForContentInTray(String userType) throws Exception {
//		System.out.println("Verify Ad Forced Exit Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentInTray(userType);
//	}
	
	@Test(priority = 255)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdForcedExitEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 256)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	@Test(priority = 257)
	@Parameters({ "userType"})
	public void verifyAdForcedExitEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentInMegamenu(userType);
	}
	
	@Test(priority = 258)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 259)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdForcedExitEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 260)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdForcedExitEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Forced Exit Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdForcedExitEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 261)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickEventForFreeContent(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 262)
	@Parameters({"userType", "keyword1"})
	public void verifyAdClickEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Click Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForTrailer(userType,keyword1);
	}
	
//	@Test(priority = 263)
//	@Parameters({ "userType"})
//	public void verifyAdClickEventForCarouselContent(String userType) throws Exception {
//		System.out.println("Verify Ad Click Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForCarouselContent(userType);
//	}
//	
//	@Test(priority = 264)
//	@Parameters({ "userType"})
//	public void verifyAdClickEventForContentInTray(String userType) throws Exception {
//		System.out.println("Verify Ad Click Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentInTray(userType);
//	}
	
	@Test(priority = 265)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdClickEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 266)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	@Test(priority = 267)
	@Parameters({ "userType"})
	public void verifyAdClickEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentInMegamenu(userType);
	}
	
	@Test(priority = 268)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 269)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdClickEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 270)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdClickEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Click Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 271)
	public void verifyContentLanguageChangeEvent() throws Exception {
		System.out.println("Verify Content Language Change Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentLanguageChangeEvent();
	}
	
	
	@Test(priority = 272)
	@Parameters({ "userType",  "keyword" })
	public void verifyAddToWatchlistEventFromShowDetailPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Add To Watchlist Event from show detail page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAddToWatchlistEventFromShowDetailPage(userType,keyword);
	}
	
	@Test(priority = 273)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForFreeContentForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback for free content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForFreeContentForceExit(userType,audioTrackContent);
	}

	@Test(priority = 274)
	@Parameters({"userType", "keyword1"})
	public void verifyAdWatchDurationEventForTrailerForceExit(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForTrailerForceExit(userType,keyword1);
	}
	
//	@Test(priority = 275)
//	@Parameters({ "userType"})
//	public void verifyAdWatchDurationEventForCarouselContentForceExit(String userType) throws Exception {
//		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForCarouselContentForceExit(userType);
//	}
//	
//	@Test(priority = 276)
//	@Parameters({ "userType"})
//	public void verifyAdWatchDurationEventForContentInTrayForceExit(String userType) throws Exception {
//		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInTrayForceExit(userType);
//	}
	
	@Test(priority = 277)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdWatchDurationEventForContentFromSearchPageForceExit(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageForceExit(userType,subtitleTrackContent);
	}
	
	@Test(priority = 278)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(userType,audioTrackContent);
	}
	
	@Test(priority = 279)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentInMegamenuForceExit(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInMegamenuForceExit(userType);
	}
	
	@Test(priority = 280)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentInPlaylistForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistForceExit(userType,audioTrackContent);
	}
	
	@Test(priority = 281)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromUpnextRailForceExit(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailForceExit(userType,audioTrackContent);
	}
	
	@Test(priority = 282)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdWatchDurationEventForContentFromSharedLinkForceExit(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkForceExit(userType,audioTrackURL);
	}
	
	@Test(priority = 283)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForFreeContentComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback for free content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForFreeContentComplete(userType,audioTrackContent);
	}

	@Test(priority = 284)
	@Parameters({"userType", "keyword1"})
	public void verifyAdWatchDurationEventForTrailerComplete(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForTrailerComplete(userType,keyword1);
	}
	
//	@Test(priority = 285)
//	@Parameters({ "userType"})
//	public void verifyAdWatchDurationEventForCarouselContentComplete(String userType) throws Exception {
//		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForCarouselContentComplete(userType);
//	}
//	
//	@Test(priority = 286)
//	@Parameters({ "userType"})
//	public void verifyAdWatchDurationEventForContentInTrayComplete(String userType) throws Exception {
//		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInTrayComplete(userType);
//	}
	
	@Test(priority = 287)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdWatchDurationEventForContentFromSearchPageComplete(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageComplete(userType,subtitleTrackContent);
	}
	
	@Test(priority = 288)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches ad playback For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(userType,audioTrackContent);
	}
	
	@Test(priority = 289)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentInMegamenuComplete(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInMegamenuComplete(userType);
	}
	
	@Test(priority = 290)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentInPlaylistComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistComplete(userType,audioTrackContent);
	}
	
	@Test(priority = 291)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromUpnextRailComplete(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailComplete(userType,audioTrackContent);
	}
	
	@Test(priority = 292)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdWatchDurationEventForContentFromSharedLinkComplete(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user completly watches ad playback For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkComplete(userType,audioTrackURL);
	}

	@Test(priority = 293)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForFreeContentSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback for free content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForFreeContentSkipAd(userType,audioTrackContent);
	}

	@Test(priority = 294)
	@Parameters({"userType", "keyword1"})
	public void verifyAdWatchDurationEventForTrailerSkipAd(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForTrailerSkipAd(userType,keyword1);
	}
	
//	@Test(priority = 295)
//	@Parameters({ "userType"})
//	public void verifyAdWatchDurationEventForCarouselContentSkipAd(String userType) throws Exception {
//		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForCarouselContentSkipAd(userType);
//	}
//	
//	@Test(priority = 296)
//	@Parameters({ "userType"})
//	public void verifyAdWatchDurationEventForContentInTraySkipAd(String userType) throws Exception {
//		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInTraySkipAd(userType);
//	}
	
	@Test(priority = 297)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdWatchDurationEventForContentFromSearchPageSkipAd(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSearchPageSkipAd(userType,subtitleTrackContent);
	}
	
	@Test(priority = 298)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips ad playback For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(userType,audioTrackContent);
	}
	
	@Test(priority = 299)
	@Parameters({ "userType"})
	public void verifyAdWatchDurationEventForContentInMegamenuSkipAd(String userType) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInMegamenuSkipAd(userType);
	}
	
	@Test(priority = 300)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentInPlaylistSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentInPlaylistSkipAd(userType,audioTrackContent);
	}
	
	@Test(priority = 301)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips the ad playback For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(userType,audioTrackContent);
	}
	
	@Test(priority = 302)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Watch Duration Event when user skips ad playback For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(userType,audioTrackURL);
	}
	
	@Test(priority = 303)
	@Parameters({ "userType" })
	public void verifyCTAsEventForIcons(String userType) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventForIcons(userType,"Language");
	}
	
	@Test(priority = 304)
	@Parameters({ "userType" })
	public void verifyCTAsEventForSubscribeBtn(String userType) throws Exception {
		System.out.println("Verify CTAs Event when user clicks on subscription option");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventForSubscribeBtn(userType);
	}
	
	
	@Test(priority = 305)
	public void verifyCTAsEventForOptionInHamburger() throws Exception {
		System.out.println("Verify CTAs Event when user clicks on any option in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventForOptionInHamburger();
	}
	
	@Test(priority = 306)
	@Parameters({ "userType", "keyword" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginInRegistrationPopUp(String userType, String keyword) throws Exception {
		System.out.println("Verify Login Screen Display Event during content playback post clicking on login in registration popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginScreenDisplayEventByClickingOnLoginInRegistrationPopUp(userType,keyword);
	}
	
	@Test(priority = 307)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForFreeContent(String userType, String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForFreeContent(userType,audioTrackContent);
	}

	@Test(priority = 308)
	@Parameters({"userType", "keyword1"})
	public void verifyAdInitializedEventForTrailer(String userType,String keyword1) throws Exception {
		System.out.println("Verify Ad Initialized Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForTrailer(userType,keyword1);
	}
	
//	@Test(priority = 309)
//	@Parameters({ "userType"})
//	public void verifyAdInitializedEventForCarouselContent(String userType) throws Exception {
//		System.out.println("Verify Ad Initialized Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForCarouselContent(userType);
//	}
//	
//	@Test(priority = 310)
//	@Parameters({ "userType"})
//	public void verifyAdInitializedEventForContentInTray(String userType) throws Exception {
//		System.out.println("Verify Ad Initialized Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForContentInTray(userType);
//	}
	
	@Test(priority = 311)
	@Parameters({"userType","subtitleTrackContent"})
	public void verifyAdInitializedEventForContentFromSearchPage(String userType,String subtitleTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForContentFromSearchPage(userType,subtitleTrackContent);
	}
	
	@Test(priority = 312)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForContentFromMyWatchlistPage(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForContentFromMyWatchlistPage(userType,audioTrackContent);
	}
	
	@Test(priority = 313)
	@Parameters({ "userType"})
	public void verifyAdInitializedEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForContentInMegamenu(userType);
	}
	
	@Test(priority = 314)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForContentInPlaylist(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForContentInPlaylist(userType,audioTrackContent);
	}
	
	@Test(priority = 315)
	@Parameters({ "userType", "audioTrackContent"})
	public void verifyAdInitializedEventForContentFromUpnextRail(String userType,String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Initialized Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForContentFromUpnextRail(userType,audioTrackContent);
	}
	
	@Test(priority = 316)
	@Parameters({"userType","audioTrackURL"})
	public void verifyAdInitializedEventForContentFromSharedLink(String userType,String audioTrackURL) throws Exception {
		System.out.println("Verify Ad Initialized Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdInitializedEventForContentFromSharedLink(userType,audioTrackURL);
	}
	
	@Test(priority = 317)
	public void verifyPauseEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Pause Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForContentInMegamenu();
	}

	@Test(priority = 318)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoViewEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video View Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 319)
	@Parameters({ "userType"})
	public void verifyVideoViewEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Video View Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForPremiumContent(userType,"Home");
	}
	
//	@Test(priority = 320)
//	@Parameters({ "keyword1"})
//	public void verifyVideoViewEventForTrailer(String keyword1) throws Exception {
//		System.out.println("Verify Video View Event For Trailer Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForTrailer(keyword1);
//	}
	
//	@Test(priority = 321)
//	public void verifyVideoViewEventForCarouselContent() throws Exception {
//		System.out.println("Verify Video View Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForCarouselContent();
//	}
	
//	@Test(priority = 322)
//	public void verifyVideoViewEventForContentInTray() throws Exception {
//		System.out.println("Verify Video View Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentInTray();
//	}
	
	@Test(priority = 323)
	@Parameters({"keyword1"})
	public void verifyVideoViewEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Video View Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 324)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoViewEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Video View Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 325)
	public void verifyVideoViewEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Video View Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentInMegamenu();
	}
	
	@Test(priority = 326)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoViewEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Video View Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventForContentInPlaylist(userType,keyword);
	}
	
	@Test(priority = 327)
	@Parameters({"keyword1"})
	public void verifyVideoViewEventAfterRefreshingPage(String keyword1) throws Exception {
		System.out.println("Verify Video View Event after refreshing a page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoViewEventAfterRefreshingPage(keyword1);
	}
	
	@Test(priority = 328)
	@Parameters({ "userType", "keyword4"})
	public void verifyVideoExitEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Video Exit Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 329)
	@Parameters({ "userType"})
	public void verifyVideoExitEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Video Exit Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 330)
	@Parameters({ "keyword1"})
	public void verifyVideoExitEventForTrailer(String keyword1) throws Exception {
		System.out.println("Verify Video Exit Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForTrailer(keyword1);
	}
	
//	@Test(priority = 331)
//	public void verifyVideoExitEventForCarouselContent() throws Exception {
//		System.out.println("Verify Video Exit Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForCarouselContent();
//	}
//	
//	@Test(priority = 332)
//	public void verifyVideoExitEventForContentInTray() throws Exception {
//		System.out.println("Verify Video Exit Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForContentInTray();
//	}
	
	@Test(priority = 333)
	@Parameters({"keyword1"})
	public void verifyVideoExitEventForContentFromSearchPage(String keyword1) throws Exception {
		System.out.println("Verify Video Exit Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForContentFromSearchPage(keyword1);
	}
	
	@Test(priority = 334)
	@Parameters({ "userType", "keyword"})
	public void verifyVideoExitEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Video Exit Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
	@Test(priority = 335)
	public void verifyVideoExitEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Video Exit Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyVideoExitEventForContentInMegamenu();
	}
	
	@Test(priority = 336)
	@Parameters({ "userType", "keyword4"})
	public void verifyParentalOverlayResultEventForFreeContent(String userType,String keyword4) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForFreeContent(userType,keyword4);
	}
	
	@Test(priority = 337)
	@Parameters({ "userType"})
	public void verifyParentalOverlayResultEventForPremiumContent(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Premium Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForPremiumContent(userType,"Home");
	}
	
	@Test(priority = 338)
	@Parameters({ "keyword1",  "userType"})
	public void verifyParentalOverlayResultEventForTrailer(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Trailer Content");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForTrailer(keyword1,userType);
	}
	
////	@Test(priority = 339)
//	@Parameters({ "userType"})
//	public void verifyParentalOverlayResultEventForCarouselContent(String userType) throws Exception {
//		System.out.println("Verify Parental Overlay Result Event For Carousel Content");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForCarouselContent(userType);
//	}
//	
////	@Test(priority = 340)
//	@Parameters({ "userType"})
//	public void verifyParentalOverlayResultEventForContentInTray(String userType) throws Exception {
//		System.out.println("Verify Parental Overlay Result Event For Content played from Tray");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentInTray(userType);
//	}
	
//	@Test(priority = 341)
	@Parameters({"keyword1", "userType"})
	public void verifyParentalOverlayResultEventForContentFromSearchPage(String keyword1,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content From Search Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromSearchPage(keyword1,userType);
	}
	
//	@Test(priority = 342)
	@Parameters({ "userType", "keyword"})
	public void verifyParentalOverlayResultEventForContentFromMyWatchlistPage(String userType,String keyword) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content From My Watchlist Page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromMyWatchlistPage(userType,keyword);
	}
	
//	@Test(priority = 343)
	@Parameters({ "userType"})
	public void verifyParentalOverlayResultEventForContentInMegamenu(String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentInMegamenu(userType);
	}
	
//	@Test(priority = 344)
	@Parameters({ "userType", "keyword"})
	public void verifyParentalOverlayResultEventForContentInPlaylist(String userType,String keyword) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Playlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentInPlaylist(userType,keyword);
	}
	
//	@Test(priority = 345)
	@Parameters({ "userType", "keyword4"})
	public void verifyParentalOverlayResultEventForContentFromUpnextRail(String userType,String keyword4) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For Content played from Upnext rail");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromUpnextRail(userType,keyword4);
	}
	
//	@Test(priority = 346)
	@Parameters({"freeContentURL" ,"userType"})
	public void verifyParentalOverlayResultEventForContentFromSharedLink(String freeContentURL,String userType) throws Exception {
		System.out.println("Verify Parental Overlay Result Event For content played from Shared Link");
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalOverlayResultEventForContentFromSharedLink(freeContentURL,userType);
	}
	
//	@Test(priority = 347)
	@Parameters({ "userType"})
	public void verifyToastMessageImpressionEventAfterChangingPassword(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression event when user changes the password");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventAfterChangingPassword(userType);
	}
	
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
