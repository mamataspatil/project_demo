package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_P1_Events {

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
	public void AdInitializedEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nAd initialized event of content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
//		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "pTabName" })
	public void AdInitializedEvent_Carousal(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventForCarouselContent(usertype, pTabName);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "clipContent", "clipID", "clipContentID" })
	public void AdInitializedEvent_ConsumptionPageRail(String usertype, String clipContent, String clipID, String clipContentID) throws Exception {
		System.out.println("\nAd initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventOfcontentFromConsumptionPageRail(usertype, clipContent, clipID, clipContentID);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "pTabName" })
	public void AdInitializedEvent_ContinueWatchingRail(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd Initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedForContinueWatchingTray(usertype, pTabName);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType", "pTabName" })
	public void AdInitializedEvent_TrayNavigation(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd Initialized event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdInitializedEventForTrayNavigation(usertype, pTabName);
	}
	
	@Test(priority = 7)
	@Parameters({ "userType", "clipContent" })
	public void AdViewEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nAd view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventOfcontentFromSearchPage(usertype, clipContent);
	}
	
	@Test(priority = 8)
	@Parameters({ "userType", "pTabName" })
	public void AdViewEvent_Carousal(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventForCarouselContent(usertype, pTabName);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType", "clipContent", "clipID", "clipContentID" })
	public void AdViewEvent_ConsumptionPageRail(String usertype, String clipContent, String clipID, String clipContentID) throws Exception {
		System.out.println("\nAd view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventOfContentFromConsumptionPageRail(usertype, clipContent, clipID, clipContentID);
	}

	@Test(priority = 10)
	@Parameters({ "userType", "pTabName" })
	public void AdViewEvent_ContinueWatchingRail(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventForContinueWatchingTray(usertype, pTabName);
	}
	
	@Test(priority = 11)
	@Parameters({ "userType", "pTabName" })
	public void AdViewEvent_TrayNavigation(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdViewEventForTrayNavigation(usertype, pTabName);
	}

	@Test(priority = 12)
	@Parameters({ "userType", "clipContent" })
	public void AdClickEvent_Search(String usertype, String clipContent) throws Exception {
		System.out.println("\nAd click event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventOfcontentFromSearchPage(usertype, clipContent);
	}

	@Test(priority = 13)
	@Parameters({ "userType", "pTabName" })
	public void AdClickEvent_Carousal(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd click event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventForCarouselContent(usertype, pTabName);
	}
	
	@Test(priority = 14)
	@Parameters({ "userType", "clipContent", "clipID", "clipContentID" })
	public void AdClickEvent_ConsumptionPageRail(String usertype, String clipContent, String clipID, String clipContentID) throws Exception {
		System.out.println("\nAd click event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventOfContentFromConsumptionPageRail(usertype, clipContent, clipID, clipContentID);
	}
	
	@Test(priority = 15)
	@Parameters({ "userType", "pTabName" })
	public void AdClickEvent_ContinueWatchingRail(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd click event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickForContinueWatchingTray(usertype, pTabName);
	}
	
	@Test(priority = 16)
	@Parameters({ "userType", "pTabName" })
	public void AdClickEvent_TrayNavigation(String usertype, String pTabName) throws Exception {
		System.out.println("\nAd Click event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.AdClickEventForTrayNavigation(usertype, pTabName);
	}
	
	@Test(priority = 17)
	@Parameters({"userType","pTabName"})
	public void AndroidMixPanel_CarouselBannerClickEventValidation(String userType,String pTabName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCarousalBannerClickEvent(userType,pTabName);
	}
	
	@Test(priority = 18)
	@Parameters({"userType","pTabName"})
	public void VerifyThumbnailClickEvent_LandingPage(String userType,String pTabName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.ThumbnailClickEventValidationFromLandingPage(userType,pTabName);
	}
	
	@Test(priority = 19)
	@Parameters({"userType","clipContent", "clipID", "clipContentID"})
	public void VerifyThumbnailClickEvent_ConsumptionPage(String userType,String clipContent, String clipID, String clipContentID) throws Exception {
		System.out.println("Thumbnail Click event through consumption page rail");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.ThumbnailClickEventValidationFromConsumptionPage(userType,  clipContent, clipID, clipContentID);
	}

	@Test(priority = 20)
	@Parameters({ "userType", "pTabName"})
	public void VideoViewEvent_TrayNavigation(String userType,String pTabName) throws Exception {
		System.out.println("\nVideo View event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventFromTrayNavigationPage(userType, pTabName);
	}
	

	@Test(priority = 21)	
	@Parameters({ "userType","clipContent"})
	public void VideoViewEvent_Search(String userType,String clipContent) throws Exception {
		System.out.println("\nVideo View event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.VideoViewEventOfcontentFromSearchPage(userType, clipContent);
	}
	
	@Test(priority = 22)
	@Parameters({ "userType", "pTabName" })
	public void VideoViewEvent_Carousal1(String usertype, String pTabName) throws Exception {
		System.out.println("\nVideo view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventForCarousalContent(usertype, pTabName);
	}
	
	@Test(priority = 23)
	@Parameters({"userType", "BottomNavigation"})
	public void verifyScreenViewEvent_BottomNavigation(String userType, String pTabName) throws Exception {
		System.out.println("Screen view event for Bottom Navigation");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_BottomNavigation(userType, pTabName);
	}
	
	@Test(priority = 24)
	@Parameters({"userType", "MoreMenuOptions"})
	public void verifyScreenViewEvent_MoreMenuOptions(String userType, String page) throws Exception {
		System.out.println("Screen view event for More menu options");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_MoreMenuOptions(userType, page);
	}
	
	@Test(priority = 25)
	@Parameters({"userType"})
	public void verifyScreenViewEvent_Search(String userType) throws Exception {
		System.out.println("Screen view event for Search");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_SearchPage(userType);
	}
	
	@Test(priority = 26)
	@Parameters({"userType"})
	public void verifyScreenViewEvent_SplashPage(String userType) throws Exception {
		System.out.println("Screen view event for Splash page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent_SplashPage(userType);
	}
	
	@Test(priority = 27)
	@Parameters({"userType","pTabName"})
	public void AndroidMixPanel_CarouselBannerSwipeEventValidation(String userType,String tabName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCarouselBannerSwipeEvent(userType,tabName);
	}

	@Test(priority = 28)
	@Parameters({"userType"})
	public void AndroidMixPanel_CTAsEventValidation(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEventForBuyPlanHeader(pUserType);
	}
	
	@Test(priority = 29)
	@Parameters({"userType"})
	public void verifyCTAsEventForSettingsInMore(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEventForSettingsInMore(pUserType);
	}
	
	@Test(priority = 30)
	@Parameters({"userType"})
	public void verifyCTAsEventForMySubscriptionInMore(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEventForMySubscriptionInMore(pUserType);
	}
	
	@Test(priority = 31)
	@Parameters({"userType"})
	public void verifyCTAsEventForMyTransactionInMore(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEventForMyTransactionInMore(pUserType);
	}
	
	@Test(priority = 32)
	@Parameters({"userType"})
	public void verifyCTAsEventForContinueBtn(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEventForContinueBtn(pUserType);
	}
	
	@Test(priority = 33)
	@Parameters({"userType"})
	public void verifyCTAsEventForMyProfile(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEventForMyProfile(pUserType);
	}
	
	@Test(priority = 34)	
	@Parameters({ "userType","clipContent"})
	public void VideoWatchDurationEvent_Search(String userType,String clipContent) throws Exception {
		System.out.println("\nVideo watch duration event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.videoWatchDurationOfContentFromSearchPage(userType, clipContent);
	}
	
	@Test(priority = 35)
	@Parameters({"userType","keyword9"})
	public void MixPanel_SearchButtonClickEventValidation(String pUserType,String pContentName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.SearchButtonClickEventValidation(pUserType, pContentName);
	}
	
	@Test(priority = 36)
	@Parameters({"userType","keyword9"})
	public void MixPanel_SearchResultClickedEventValidation(String pUserType,String pContentName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.SearchResultClickedEventValidation(pUserType, pContentName);
	}
	
	@Test(priority = 37)
	@Parameters({"userType"})
	public void verifyPopupLaunchEventForParentalRestrictionSetting(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupLaunchEventForParentalRestrictionSetting(pUserType);
	}
	
	@Test(priority = 38)
	@Parameters({"userType", "trailerContent"})
	public void verifyPopupLaunchEventForParentalRestriction(String pUserType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupLaunchEventForParentalRestriction(pUserType,trailerContent);
	}
		
	
	@Test(priority = 39)
	@Parameters({"userType"})
	public void PopupLaunchEventForAccountInfoPopUp(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForAccountInfoPopUp(pUserType);
	}
	
	@Test(priority = 40)
	@Parameters({"userType"})
	public void PopupLaunchEventForHaveAPrepaidCode(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForHaveAPrepaidCode(pUserType);
	}
	
	@Test(priority = 41)
	@Parameters({"userType"})
	public void verifyPopupLaunchEventForDefaultSettingRestoredEvent(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupLaunchEventForDefaultSettingRestoredEvent(pUserType);
	}
	
	@Test(priority = 42)
	@Parameters({"userType","audioTrackContent"})
	public void PopupLaunchEventForSubtitleLanguageSelection(String pUserType,String audioTrackContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForSubtitleLanguageSelection(pUserType,audioTrackContent);
	}
	
	@Test(priority = 43)
	@Parameters({"userType","audioTrackContent"})
	public void PopupLaunchEventForAudioLanguageSelection(String pUserType,String audioTrackContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForAudioLanguageSelection(pUserType,audioTrackContent);
	}
	
	@Test(priority = 44)
	@Parameters({"userType","audioTrackContent"})
	public void PopupCTAsEventForAudioLanguageSelection(String pUserType,String audioTrackContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupCTAsEventForAudioLanguageSelection(pUserType,audioTrackContent);
	}
	
	
	@Test(priority = 45)
	@Parameters({"userType"})
	public void PopUpCTAsEventForAfterApplyingPrepaidCode(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopUpCTAsEventForAfterApplyingPrepaidCode(pUserType);
	}
	
	
	@Test(priority = 46)
	@Parameters({"userType"})
	public void PopUpCTAsEventForInvalidPrepaidCodePopup(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopUpCTAsEventForInvalidPrepaidCodePopup(pUserType);
	}
	
	@Test(priority = 47)
	@Parameters({"userType"})
	public void verifyPopupCTAsEventForDefaultSettingRestoredEvent(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupCTAsEventForDefaultSettingRestoredEvent(pUserType);
	}
	
	@Test(priority = 48)
	@Parameters({"userType"})
	public void verifyPopupCTAsEventForParentalRestrictionSetPin(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupCTAsEventForParentalRestrictionSetPin(pUserType);
	}
	
	@Test(priority = 49)
	@Parameters({"userType"})
	public void verifyPopupCTAsEventForParentalRestrictionDone(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupCTAsEventForParentalRestrictionDone(pUserType);
	}
	
	@Test(priority = 50)
	@Parameters({"userType"})
	public void PopupCTAEventValidationForLogout(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupCTAEventValidationForLogout(pUserType);
	}
	
	@Test(priority = 51)
	@Parameters({"userType"})
	public void AndroidMixPanel_SubscriptionSelectedEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEvent(userType);
	}
	
	@Test(priority = 52)
	@Parameters({"userType"})
	public void AndroidMixPanel_SubscriptionSelectedEvent_MoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEvent_MoreSection(userType);
	}
	
	@Test(priority = 53)
	@Parameters({"userType"})
	public void AndroidMixPanel_SubscriptionSelectedEvent_MySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEvent_MySubscription(userType);
	}
	
	@Test(priority = 54)
	@Parameters({"userType"})
	public void AndroidMixPanel_SubscriptionSelectedEvent_MyTransaction(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEvent_MyTransaction(userType);
	}
	
	@Test(priority = 55)
	@Parameters({"userType","keyword3"})
	public void AndroidMixPanel_SubscriptionSelectedEvent_BelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEvent_BelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 56)
	@Parameters({"userType","trailerContent"})
	public void AndroidMixPanel_SubscriptionSelectedEvent_Trailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEvent_Trailer(userType,trailerContent);
	}
	
	@Test(priority = 57)
	@Parameters({"userType","firstEpisode"})
	public void MixPanel_af_svod_first_episode_free_EventValidation(String userType,String firstEpisode) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verify_af_svod_first_episode_free_Event(userType, firstEpisode);
	}
	
	@Test(priority = 58)
	@Parameters({ "userType"})
	public void af_svod_first_episode_free_Event_Carousal(String usertype) throws Exception {
		System.out.println("\n af_Svod_First_Episode_Free event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.afSvodFirstEpisodeFreeForCarousalContent(usertype);
	}
	
	@Test(priority = 59)
	@Parameters({ "userType"})
	public void af_svod_first_episode_free_Event_TrayNavigation(String usertype) throws Exception {
		System.out.println("\\n af_Svod_First_Episode_Free event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.afSvodFirstEpisodeFreeForTrayNavigation(usertype, "Web Series");
	}
	
	@Test(priority = 60)
	@Parameters({"userType"})
	public void AndroidMixPanel_LoginInitiatedEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEvent(userType, "mobileNumberLogin");
	}
	
	@Test(priority = 61)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanHeader(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanHeader(userType);
	}
	
	@Test(priority = 62)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanMoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanMoreSection(userType);
	}
	
	@Test(priority = 63)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanMySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanMySubscription(userType);
	}
	
	@Test(priority = 64)
	@Parameters({"userType"})
	public void verifyLoginInitiatedEventViaBuyPlanMyTransactions(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanMyTransactions(userType);
	}
	
	@Test(priority = 65)
	@Parameters({"userType", "keyword3"})
	public void verifyLoginInitiatedEventViaBuyPlanBelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlanBelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 66)
	@Parameters({"userType", "trailerContent"})
	public void verifyLoginInitiatedEventViaBuyPlan_Trailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventViaBuyPlan_Trailer(userType,trailerContent);
	}
	
	@Test(priority = 67)
	@Parameters({"userType"})
	public void verifyLoginResultEventViaBuyPlanHeader(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginResultEventViaBuyPlanHeader(userType);
	}
	
	@Test(priority = 68)
	@Parameters({"userType"})
	public void verifyLoginResultEventViaBuyPlanMoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginResultEventViaBuyPlanMoreSection(userType);
	}
	
	@Test(priority = 69)
	@Parameters({"userType"})
	public void verifyLoginResultEventViaBuyPlanMySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginResultEventViaBuyPlanMySubscription(userType);
	}
	
	@Test(priority = 70)
	@Parameters({"userType"})
	public void verifyLoginResultEventViaBuyPlanMyTransactions(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginResultEventViaBuyPlanMyTransactions(userType);
	}
	
	@Test(priority = 71)
	@Parameters({"userType", "keyword3"})
	public void verifyLoginResultEventViaBuyPlanBelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginResultEventViaBuyPlanBelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 72)
	@Parameters({"userType", "trailerContent"})
	public void verifyLoginResultEventViaBuyPlan_Trailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginResultEventViaBuyPlan_Trailer(userType,trailerContent);
	}
	
	@Test(priority = 73)
	@Parameters({"userType"})
	public void MixPanel_RegistrationEventsValidation(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidation(pUserType);
	}

	
	@Test(priority = 74)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlan(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlan(pUserType);
	}
	
	@Test(priority = 75)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlanMoreSection(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanMoreSection(userType);
	}
	
	@Test(priority = 76)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlanMySubscription(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanMySubscription(userType);
	}
	
	@Test(priority = 77)
	@Parameters({"userType"})
	public void RegistrationEventValidationViaBuyPlanMyTransaction(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanMyTransaction(userType);
	}
	
	@Test(priority = 78)
	@Parameters({"userType", "keyword3"})
	public void RegistrationEventValidationViaBuyPlanBelowPlayer(String userType,String keyword3) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanBelowPlayer(userType,keyword3);
	}
	
	@Test(priority = 79)
	@Parameters({"userType", "trailerContent"})
	public void RegistrationEventValidationViaBuyPlanAfterTrailer(String userType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.RegistrationEventValidationViaBuyPlanAfterTrailer(userType,trailerContent);
	}
	
	@Test(priority = 80)
	@Parameters({"userType"})
	public void AndroidMixPanel_RegisterScreenDisplayEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegisterScreenDisplayEvent(userType);
	}
	
	@Test(priority = 81)
	@Parameters({"userType"})
	public void AndroidMixPanel_FirstLaunchEventEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyFirstLaunchEvent(userType);
	}
	
	@Test(priority = 82)
	@Parameters({"userType", "displayLanguage"})
	public void verifyDisplayLanguageChangeEvent(String userType, String displayLanguage) throws Exception {
		System.out.println("Display language Change");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDisplayLanguageChangeEvent(userType, displayLanguage);
	}
	
	@Test(priority = 83)
	@Parameters({"userType"})
	public void verifyContentLanguageChangeEvent(String userType) throws Exception {
		System.out.println("Content language Change");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContentLanguageChangeEvent(userType);
	}
	
	@Test(priority = 84)
	@Parameters({"userType"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_Header(String userType) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through Header");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_Header(userType);
	}
	
	@Test(priority = 85)
	@Parameters({"userType"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_MoreSection(String userType) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through More section");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_MoreSection(userType);
	}
	
	@Test(priority = 86)
	@Parameters({"userType"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_MySubscription(String userType) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through My Subscription");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_MySubscription(userType);
	}
	
	@Test(priority = 87)
	@Parameters({"userType", "keyword2"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_BelowThePlayer(String userType, String keyword2) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through below the player");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_BelowThePlayer(userType, keyword2);
	}
	
	@Test(priority = 88)
	@Parameters({"userType", "trailerContent"})
	public void verifySubscriptionPageViewedEvent_BuyPlan_AfterCompletionOfTrailer_OnPlayer(String userType, String trailerContent) throws Exception {
		System.out.println("Subscription page viewed event for Buy Plan through after completion of trailer on the player");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionPageViewedEvent_BuyPlan_AfterCompletionOfTrailerOnThePlayer(userType, trailerContent);
	}
	
	@Test(priority = 89)
	@Parameters({"userType"})
	public void AndroidMixPanel_LoginScreenDisplayEventValidation(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginScreenDisplayEvent(userType,"emailLogin");
	}
	
	
	@Test(priority = 90)
	@Parameters({"userType","keyword2"})
	public void verifyLoginScreenDisplayEvent_LoginLinkOnPlayer(String userType,String keyword2) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginScreenDisplayEvent_LoginLinkOnPlayer(userType,"emailLogin",keyword2);
	}
	
	@Test(priority = 91)
	@Parameters({"userType"})
	public void MixPanel_SubscriptionJourneyEventValidation(String pUserType) throws Exception {
	    Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.SubscriptionJourneyEventValidation(pUserType);
	}

	@Test(priority = 92)
	@Parameters({"userType", "clipContent"})
	public void MixPanel_AdWatchDurationEventValidation(String pUserType, String keyword4) throws Exception {
	    Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyAdWatchDurationevent(pUserType, keyword4);
	}
	

	@Test(priority = 93)
	@Parameters({"userType", "clipContent"})
	public void MixPanel_ad_view_completeEventValidation(String pUserType, String keyword4) throws Exception {
	    Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyAd_view_completeEvent(pUserType, keyword4);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Reports Generated");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
	
}
