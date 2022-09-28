package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_PlayerEvents {

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
	@Parameters({ "userType"})
	public void PauseEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nPause event of Premium cont	ent");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PauseEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "keyword3"})
	public void PauseEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nPause event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PauseEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 4)
	@Parameters({ "userType"})
	public void PauseEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nPause event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PauseEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 5)
	@Parameters({ "userType","keyword4"})
	public void PauseEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nPause event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PauseEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType"})
	public void PauseEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nPause event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PauseEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 7)
	@Parameters({ "userType", "keyword4"})
	public void PauseEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nPause event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PauseEventOfContentFromUpNextRail(userType, keyword4);
		
	}
	
	@Test(priority = 8)
	@Parameters({ "userType"})
	public void PauseEventForLinearContent(String userType) throws Exception {
		System.out.println("\nPause event for Linear content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PauseEventForLinearContent(userType, "Live TV");
		
	}
	
	@Test(priority = 9)
	@Parameters({ "userType"})
	public void PlayerViewChangedEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nPlayer View Changed event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.playerViewChangedEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 10)
	@Parameters({ "userType", "keyword3"})
	public void PlayerViewChangedEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nPlayer View Changed event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority =11)
	public void  PlayerViewChangedEventofCarouselContent() throws Exception {
		System.out.println("\nPlayer View Changed event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEventForCarouselContent("Home");
	}

	@Test(priority = 12)
	@Parameters({ "userType","keyword4"})
	public void PlayerViewChangedEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nPlayer View Changed event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 13)
	@Parameters({ "userType"})
	public void PlayerViewChangedEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nPlayer View Changed event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 14)
	@Parameters({ "userType", "keyword4"})
	public void PlayerViewChangedEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nPlayer View Changed event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerViewChangedEventOfContentFromUpNextRail(userType, keyword4);	
	}
	
	@Test(priority = 15)
	@Parameters({ "userType"})
	public void QualityChangeEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nQuality Change event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 16)
	@Parameters({ "userType", "keyword3"})
	public void QualityChangeEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("Quality Change event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForTrailerContent(userType,keyword3);
		
	}
	
	@Test(priority = 17)
	@Parameters({ "userType"})
	public void QualityChangeEventofCarouselContent(String userType) throws Exception {
		System.out.println("Quality Change event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForCarouselContent(userType,"Home");
	}
	
	@Test(priority = 18)
	@Parameters({ "userType","keyword4"})
	public void QualityChangeEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("Quality Change event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 19)
	@Parameters({ "userType"})
	public void QualityChangeEventFromMyWatchList(String userType) throws Exception {
		System.out.println("Quality Change event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventOfContentFromMyWatchListPage(userType);
	}
	
	@Test(priority = 20)
	@Parameters({ "userType", "keyword4"})
	public void QualityChangeEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("Quality Change event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventOfContentFromUpNextRail(userType, keyword4);
	}

	@Test(priority = 21)
	@Parameters({ "userType"})
	public void QualityChangeEventForLinearContent(String userType) throws Exception {
		System.out.println("Quality Change event for Linear Content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.QualityChangeEventForLinearContent(userType, "Live TV");
		
	}
	
	@Test(priority = 22)
	@Parameters({ "userType"})
	public void ResumeEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nResume event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 23)
	@Parameters({ "userType", "keyword3"})
	public void ResumeEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nResume event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 24)
	@Parameters({ "userType"})
	public void ResumeEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nResume event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 25)
	@Parameters({ "userType","keyword4"})
	public void ResumeEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nResume event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 26)
	@Parameters({ "userType"})
	public void ResumeEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nResume event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 27)
	@Parameters({ "userType", "keyword4"})
	public void ResumeEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nResume event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventOfContentFromUpNextRail(userType, keyword4);
		
	}
	
	@Test(priority = 28)
	@Parameters({ "userType"})
	public void ResumeEventForLinearContent(String userType) throws Exception {
		System.out.println("\nResume event for Linear content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForLinearContent(userType, "Live TV");
		
	}
	
	@Test(priority = 29)
	@Parameters({ "userType"})
	public void ScrubSeekEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nScrub/Seek event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ScrubSeekEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 30)
	@Parameters({ "userType", "keyword3"})
	public void ScrubSeekEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nScrub/Seek event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ScrubSeekEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 31)
	@Parameters({ "userType"})
	public void ScrubSeekEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nScrub/Seek event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ScrubSeekEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 32)
	@Parameters({ "userType","keyword4"})
	public void ScrubSeekEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nScrub/Seek event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ScrubSeekEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 33)
	@Parameters({ "userType"})
	public void ScrubSeekEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nScrub/Seek event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ScrubSeekEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 34)
	@Parameters({ "userType", "keyword4"})
	public void ScrubSeekEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nScrub/Seek event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ScrubSeekEventOfContentFromUpNextRail(userType, keyword4);
		
	}
	
	@Test(priority = 35)
	@Parameters({ "userType", "keyword3"})
	public void SkipIntroEventofPremiumContent(String userType, String keyword3) throws Exception {
		System.out.println("\nSkip Intro event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventForPremiumContent(userType,keyword3);
		
	}
	
//	@Test(priority = 36)
	@Parameters({ "userType"})
	public void SkipIntroEventOfContentFromContentTrays(String userType) throws Exception {
		System.out.println("\nSkip Intro event of content from Content Trays");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventOfContentFromContentTrays(userType,"Movies");
	}

	@Test(priority = 37)
	@Parameters({ "userType"})
	public void SkipIntroEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nSkip Intro event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventForCarouselContent(userType,"Movies");
	}

	@Test(priority = 38)
	@Parameters({ "userType","keyword8"})
	public void SkipIntroEventFromsearchpage(String userType, String keyword8) throws Exception {
		System.out.println("\nSkip Intro event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SkipIntroEventOfcontentFromSearchPage(userType, keyword8);
	}
	
	@Test(priority = 39)
	@Parameters({ "userType"})
	public void SubtitleLanguageChangeEventofPremiumContent(String userType) throws Exception {
		System.out.println("\n Subtitle Language Change event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 40)
	@Parameters({ "userType", "keyword9"})
	public void SubtitleLanguageChangeEventofTrailerContent(String userType, String keyword9) throws Exception {
		System.out.println("Subtitle Language Change event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventForTrailerContent(userType,keyword9);
		
	}

	@Test(priority = 41)
	@Parameters({ "userType"})
	public void SubtitleLanguageChangeEventofCarouselContent(String userType) throws Exception {
		System.out.println("Subtitle Language Change event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 42)
	@Parameters({ "userType","keyword9"})
	public void SubtitleLanguageChangeEventFromsearchpage(String userType, String keyword9) throws Exception {
		System.out.println("Subtitle Language Change event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventOfcontentFromSearchPage(userType, keyword9);
	}
	
	@Test(priority = 43)
	@Parameters({ "userType"})
	public void SubtitleLanguageChangeEventFromMyWatchList(String userType) throws Exception {
		System.out.println("Subtitle Language Change event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 44)
	@Parameters({ "userType", "keyword9"})
	public void SubtitleLanguageChangeEventFromUpNextRail(String userType, String keyword9) throws Exception {
		System.out.println("Subtitle Language Change event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.SubtitleLanguageChangeEventOfContentFromUpNextRail(userType, keyword9);
		
	}
	
	@Test(priority = 45)
	@Parameters({ "userType"})
	public void VideoViewExitofPremiumContent(String userType) throws Exception {
		System.out.println("\nVideo exit event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.videoExitEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 46)
	@Parameters({ "userType", "keyword3"})
	public void VideoExitEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nVideo exit event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoExitEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 47)
	public void VideoExitEventofCarouselContent() throws Exception {
		System.out.println("\nVideo exit event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.videoExitEventForCarouselContent("Home");
	}

	@Test(priority = 48)
	@Parameters({ "userType","keyword4"})
	public void VideoExitEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo exit event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoExitEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 49)
	@Parameters({ "userType"})
	public void VideoExitEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nVideo exit event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoExitEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 50)
	@Parameters({ "userType", "keyword4"})
	public void VideoExitEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo exit event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoExitEventOfContentFromUpNextRail(userType, keyword4);	
	}
	
	@Test(priority = 51)
	@Parameters({ "userType"})
	public void VideoViewEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nVideo view event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 52)
	@Parameters({ "userType", "keyword3"})
	public void VideoViewEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nVideo view event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 53)
	public void VideoViewEventofCarouselContent() throws Exception {
		System.out.println("\nVideo view event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventForCarouselContent("Home");
	}

	@Test(priority = 54)
	@Parameters({ "userType","keyword4"})
	public void VideoViewEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo view event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 55)
	@Parameters({ "userType"})
	public void VideoViewEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nVideo view event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 56)
	@Parameters({ "userType", "keyword4"})
	public void VideoViewEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo view event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventOfContentFromUpNextRail(userType, keyword4);	
	}
	
	@Test(priority = 57)
	@Parameters({ "userType"})
	public void VideoWatchDurationEventofPremiumContentComplete(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Premium content when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForPremiumContentComplete(userType,"Home");
		
	}
	
	@Test(priority = 58)
	@Parameters({ "userType"})
	public void VideoWatchDurationEventofPremiumContentAbrupt(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Premium content when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForPremiumContentAbrupt(userType,"Home");
		
	}
	
	@Test(priority = 59)
	@Parameters({ "userType", "keyword3"})
	public void VideoWatchDurationEventofTrailerContentComplete(String userType, String keyword3) throws Exception {
		System.out.println("\nVideo Watch Duration event of Trailer content when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForTrailerContentComplete(userType,keyword3);
		
	}
	
	@Test(priority = 60)
	@Parameters({ "userType", "keyword3"})
	public void VideoWatchDurationEventofTrailerContentAbrupt(String userType, String keyword3) throws Exception {
		System.out.println("\nVideo Watch Duration event of Trailer content when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForTrailerContentAbrupt(userType,keyword3);
		
	}
	
	@Test(priority = 61)
	public void VideoWatchDurationEventofCarouselContentComplete() throws Exception {
		System.out.println("\nVideo Watch Duration event of Carousel content when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForCarouselContentComplete("Home");
	}
	
	@Test(priority = 62)
	public void VideoWatchDurationEventofCarouselContentAbrupt() throws Exception {
		System.out.println("Video Watch Duration event of Carousel content when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventForCarouselContentAbrupt("Home");
	}
	
	@Test(priority = 63)
	@Parameters({ "userType","keyword4"})
	public void VideoWatchDurationEventFromsearchpageComplete(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Search page when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfcontentFromSearchPageComplete(userType, keyword4);
	}
	
	@Test(priority = 64)
	@Parameters({ "userType","keyword4"})
	public void VideoWatchDurationEventFromsearchpageAbrupt(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Search page when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfcontentFromSearchPageAbrupt(userType, keyword4);
	}
	
	@Test(priority = 65)
	@Parameters({ "userType"})
	public void VideoWatchDurationEventFromMyWatchListComplete(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from My WatchList page when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromMyWatchListPageComplete(userType);		
	}
	
	@Test(priority = 66)
	@Parameters({ "userType"})
	public void VideoWatchDurationEventFromMyWatchListAbrupt(String userType) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from My WatchList page when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromMyWatchListPageAbrupt(userType);
		
	}
	
	@Test(priority = 67)
	@Parameters({ "userType", "keyword4"})
	public void VideoWatchDurationEventFromUpNextRailComplete(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Upnext Rail when user completely watches the content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromUpNextRailComplete(userType, keyword4);
	}
	
	@Test(priority = 68)
	@Parameters({ "userType", "keyword4"})
	public void VideoWatchDurationEventFromUpNextRailAbrupt(String userType, String keyword4) throws Exception {
		System.out.println("\nVideo Watch Duration event of Content from Upnext Rail when video is closed abruptly");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.VideoWatchDurationEventOfContentFromUpNextRailAbrupt(userType, keyword4);
	}
	
	@Test(priority = 69)
	@Parameters({ "userType"})
	public void DownloadStartEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nDownload Start event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadStartEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 70)
	@Parameters({ "userType", "keyword3"})
	public void DownloadStartEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nDownload Start event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadStartEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 71)
	@Parameters({ "userType"})
	public void DownloadStartEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nDownload Start event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadStartEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 72)
	@Parameters({ "userType","keyword4"})
	public void DownloadStartEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nDownload Start event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadStartEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 73)
	@Parameters({ "userType"})
	public void DownloadStartEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nDownload Start event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadStartEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 74)
	@Parameters({ "userType", "keyword4"})
	public void DownloadStartEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\n Download Start event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.DownloadStartEventOfContentFromUpNextRail(userType, keyword4);
		
	}
	
	@Test(priority = 75)
	@Parameters({ "userType"})
	public void AutoSeekForwardEventForPremiumContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Forward event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 76)
	@Parameters({ "userType"})
	public void AutoSeekRewindEventForPremiumContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 77)
	@Parameters({ "userType", "keyword3"})
	public void AutoSeekForwardEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nAutoSeek Forward event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventForTrailerContent(userType,keyword3);
		
	}
	
	@Test(priority = 78)
	@Parameters({ "userType", "keyword3"})
	public void AutoSeekRewindEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 79)
	@Parameters({ "userType"})
	public void AutoSeekForwardEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Forward event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventForCarouselContent(userType,"Home");
	}
	
	@Test(priority = 80)
	@Parameters({ "userType"})
	public void AutoSeekRewindEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 81)
	@Parameters({ "userType","keyword4"})
	public void AutoSeekForwardEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek forward event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 82)
	@Parameters({ "userType","keyword4"})
	public void AutoSeekRewindEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 83)
	@Parameters({ "userType"})
	public void AutoSeekForwardEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nAutoSeek forward event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 84)
	@Parameters({ "userType"})
	public void AutoSeekRewindEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventOfContentFromMyWatchListPage(userType);
	}
	
	@Test(priority = 85)
	@Parameters({ "userType", "keyword4"})
	public void AutoSeekEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek forward event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekForwardEventOfContentFromUpNextRail(userType, keyword4);
	}
	
	@Test(priority = 86)
	@Parameters({ "userType", "keyword4"})
	public void AutoSeekRewindEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("\nAutoSeek Rewind event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AutoSeekRewindEventOfContentFromUpNextRail(userType, keyword4);
	}
	
	@Test(priority = 87)
	@Parameters({ "userType"})
	public void AudioLanguageChangeEventofPremiumContent(String userType) throws Exception {
		System.out.println("\nAudio Language Change event of Premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AudioLanguageChangeEventForPremiumContent(userType,"Home");
		
	}
	
	@Test(priority = 88)
	@Parameters({ "userType", "keyword7"})
	public void AudioLanguageChangeEventofTrailerContent(String userType, String keyword7) throws Exception {
		System.out.println("Audio Language Change event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AudioLanguageChangeEventForTrailerContent(userType,keyword7);
		
	}

	@Test(priority = 89)
	@Parameters({ "userType"})
	public void AudioLanguageChangeEventofCarouselContent(String userType) throws Exception {
		System.out.println("Audio Language Change event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AudioLanguageChangeEventForCarouselContent(userType,"Home");
	}

	@Test(priority = 90)
	@Parameters({ "userType","keyword4"})
	public void AudioLanguageChangeEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("Audio Language Change event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AuioLanguageChangeEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 91)
	@Parameters({ "userType"})
	public void AudioLanguageChangeEventFromMyWatchList(String userType) throws Exception {
		System.out.println("Audio Language Change event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AudioLanguageChangeEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 92)
	@Parameters({ "userType", "keyword4"})
	public void AudioLanguageChangeEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("Audio Language Change event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AudioLanguageChangeEventOfContentFromUpNextRail(userType, keyword4);
		
	}
	
	@Test(priority = 93)
	@Parameters({ "userType", "keyword5"})
	public void AdWatchDurationEventofTrailerContent(String userType, String keyword5) throws Exception {
		System.out.println("\nAd Watch Duration event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdWatchDurationEventForTrailerContent(userType,keyword5);
		
	}
	
	@Test(priority = 94)
	@Parameters({ "userType"})
	public void AdWatchDurationEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nAd Watch Duration event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdWatchDurationEventForCarouselContent(userType, "Music");
	}
	
	@Test(priority = 95)
	@Parameters({ "userType"})
	public void AdWatchDurationEventofContentFromTray(String userType) throws Exception {
		System.out.println("\nAd Click event of Content from tray");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdWatchDurationEventForContentFromTray(userType, "Music");
	}
	
	@Test(priority = 96)
	@Parameters({ "userType","keyword4"})
	public void AdWatchDurationEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("\nAd Watch Duration event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdWatchDurationEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 97)
	@Parameters({ "userType"})
	public void AdWatchDurationEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nAd Watch Duration event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdWatchDurationEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 98)
	@Parameters({ "userType", "keyword5"})
	public void AdWatchDurationEventFromUpNextRail(String userType, String keyword5) throws Exception {
		System.out.println("\nAd Watch Duration event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdWatchDurationEventOfContentFromUpNextRail(userType, keyword5);
	}
	
	@Test(priority = 99)
	@Parameters({ "userType", "keyword5"})
	public void AdViewEventofTrailerContent(String userType, String keyword5) throws Exception {
		System.out.println("\nAd View event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventForTrailerContent(userType,keyword5);
	}

	@Test(priority = 100)
	@Parameters({ "userType"})
	public void AdViewEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nAd View event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventForCarouselContent(userType, "Home");
	}
	
	@Test(priority = 101)
	@Parameters({ "userType"})
	public void AdViewEventofContentFromTray(String userType) throws Exception {
		System.out.println("\nAd View event of Content from tray");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventForContentFromTray(userType, "Home");
	}

	@Test(priority = 102)
	@Parameters({ "userType","keyword5"})
	public void AdViewEventFromsearchpage(String userType, String keyword5) throws Exception {
		System.out.println("\nAd View event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventOfcontentFromSearchPage(userType, keyword5);
	}
	
	@Test(priority = 103)
	@Parameters({ "userType"})
	public void AdViewEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nAd View event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 104)
	@Parameters({ "userType", "keyword5"})
	public void AdViewEventFromUpNextRail(String userType, String keyword5) throws Exception {
		System.out.println("\nAd View event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventOfContentFromUpNextRail(userType, keyword5);	
	}
	
	@Test(priority = 105)
	@Parameters({ "userType", "keyword3"})
	public void AdInitializedEventofTrailerContent(String userType, String keyword3) throws Exception {
		System.out.println("Ad Initialized event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventForTrailerContent(userType,keyword3);
		
	}

	@Test(priority = 106)
	@Parameters({ "userType"})
	public void AdInitializedEventofCarouselContent(String userType) throws Exception {
		System.out.println("Ad Initialized event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventForCarouselContent(userType, "Home");
	}
	
	@Test(priority = 107)
	@Parameters({ "userType"})
	public void AdInitializedEventofContentFromTray(String userType) throws Exception {
		System.out.println("Ad Initialized event of Content from tray");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventForContentFromTray(userType, "Home");
	}

	@Test(priority = 108)
	@Parameters({ "userType","keyword4"})
	public void AdInitializedEventFromsearchpage(String userType, String keyword4) throws Exception {
		System.out.println("Ad Initialized event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventOfcontentFromSearchPage(userType, keyword4);
	}
	
	@Test(priority = 109)
	@Parameters({ "userType"})
	public void AdInitializedEventFromMyWatchList(String userType) throws Exception {
		System.out.println("Ad Initialized event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 110)
	@Parameters({ "userType", "keyword4"})
	public void AdInitializedEventFromUpNextRail(String userType, String keyword4) throws Exception {
		System.out.println("Ad Initialized event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventOfContentFromUpNextRail(userType, keyword4);
		
	}
	
	@Test(priority = 111)
	@Parameters({ "userType", "keyword5"})
	public void AdClickEventofTrailerContent(String userType, String keyword5) throws Exception {
		System.out.println("\nAd Click event of Trailer content");
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventForTrailerContent(userType,keyword5);
		
	}
	
	@Test(priority = 112)
	@Parameters({ "userType"})
	public void AdClickEventofCarouselContent(String userType) throws Exception {
		System.out.println("\nAd Click event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventForCarouselContent(userType, "Music");
	}
	
	@Test(priority = 113)
	@Parameters({ "userType"})
	public void AdClickEventofContentFromTray(String userType) throws Exception {
		System.out.println("\nAd Click event of Content from tray");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventForContentFromTray(userType, "Music");
	}
	
	@Test(priority = 114)
	@Parameters({ "userType","keyword5"})
	public void AdClickEventFromsearchpage(String userType, String keyword5) throws Exception {
		System.out.println("\nAd Click event of Content from Search page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventOfcontentFromSearchPage(userType, keyword5);
	}
	
	@Test(priority = 115)
	@Parameters({ "userType"})
	public void AdClickEventFromMyWatchList(String userType) throws Exception {
		System.out.println("\nAd Click event of Content from My WatchList page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventOfContentFromMyWatchListPage(userType);
		
	}
	
	@Test(priority = 116)
	@Parameters({ "userType", "keyword5"})
	public void AdClickEventFromUpNextRail(String userType, String keyword5) throws Exception {
		System.out.println("\nAd Click event of Content from Upnext Rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventOfContentFromUpNextRail(userType, keyword5);	
	}
	
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}
