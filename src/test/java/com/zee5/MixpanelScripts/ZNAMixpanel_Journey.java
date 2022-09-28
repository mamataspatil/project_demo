package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_Journey {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		
	}
	
	@Test(priority = 2)
	public void verifyCarouselBannerClickEvent() throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCarouselBannerClickEvent();
	}
	
	@Test(priority = 3)//NonSubscribed and Subscribed user only
	@Parameters({ "userType" })
	public void verifyClearSearchHistoryEvent(String userType) throws Exception {
		System.out.println("Verify Clear Search Histroy Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.clearSearchHistoryEvent(userType);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType" })
	public void verifyScreenViewEvent(String userType) throws Exception {
		System.out.println("Verify Screen View Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyScreenViewEvent("Shows",userType);
	}
	
	@Test(priority = 5)
	@Parameters({"userType"})
	public void verifySearchButtonClickEvent(String userType) throws Exception {
		System.out.println("Verify Search Button Click Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySearchButtonClickEvent(userType);
	}
	
	@Test(priority = 6)
	@Parameters({"keyword2"})
	public void verifySearchExecutedEvent(String keyword2) throws Exception {
		System.out.println("Verify search executed event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.verifySearchExecutedEvent(keyword2);
	}
	
	@Test(priority = 7)
	public void verifyVideoQualityChangeEvent() throws Exception {
		System.out.println("Video Streaming Quality Changed");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoQualityChangeEvent();
	}
	
	@Test(priority = 8)
	public void verifyVideoAutoPlayChangeEventforDisable() throws Exception {
		System.out.println("Video Streaming AutoPlay Changed for disable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoAutoPlayChangeEventforDisable();
	}
	
	@Test(priority = 9)
	@Parameters({"displayLanguage"})
	public void verifyDisplayLanguageChangeEvent(String displayLanguage) throws Exception {
		System.out.println("Display language Change");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDisplayLanguageChangeEvent(displayLanguage);
	}
	
	@Test(priority = 10)
	public void verifyContentLanguageChangeEvent() throws Exception {
		System.out.println("content language Change");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContentLanguageChangeEvent();
	}
	
	@Test(priority = 11)
	public void verifydefaultsettingsRestoreEvent() throws Exception {
		System.out.println("Default settings Restore Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDefaultSettingRestoredEvent();
	}
	
	@Test(priority = 12)
	@Parameters({ "tabName","userType" })
	public void verifyCarouselBannerSwipeEvent(String tabName,String userType) throws Exception {
		System.out.println("Verify Carousel Banner Swipe Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCarouselBannerSwipeEvent(tabName,userType);
	}
	
	@Test(priority = 13)
	@Parameters({ "userType", "keyword2" })//NonSubscribed and Guest user only
	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpLaunchEventForGetPremiumPopUp(userType,keyword2);
	}
	
	@Test(priority = 14)//NonSubscribed user only
	@Parameters({ "userType", "keyword1" })
	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType, String keyword1) throws Exception {
		System.out.println("Verify Pop Up Launch Event when Complete Profile popup is displayed");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpLaunchEventForCompleteProfilePopUp(userType, keyword1);
	}
	
	@Test(priority = 15)//Guest user only
	@Parameters({ "userType", "keyword1" })
	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword1) throws Exception {
		System.out.println("Verify Pop Up Launch Event when get register popup is displayed");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpLaunchEventForRegisterPopUp(userType,keyword1);
	}
	
	@Test(priority = 16)//NonSubscribed and Guest user only
	@Parameters({ "userType", "keyword2" })
	public void verifyPopUpCTAsEvent(String userType, String keyword2) throws Exception {
		System.out.println("Verify Pop Up CTA's Event when user clicks on CTA button displayed on the popup");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopUpCTAsEvent(userType,keyword2);
	}
	
	@Test(priority = 17)
	@Parameters({ "userType", "tabName" })
	public void verifyCTAsEvent(String userType,String tabName) throws Exception {
		System.out.println("Verify CTAs Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyCTAsEvent(userType,tabName);
	}
	
	@Test(priority = 18)//Guest user only
	@Parameters({ "userType" })
	public void verifyLoginUsernameEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Username Entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyUserNameEnteredEvent(userType);
	}
	
	@Test(priority = 19)//Guest user only
	@Parameters({ "userType" })
	public void verifyLoginPasswordEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Login Password Entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginPasswordEnteredEvent();
	}
	
	@Test(priority = 20)//Guest user only
	@Parameters({ "userType" })
	public void verifyRegistrationDOBEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration DOB entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationDOBEnteredEvent(userType);
	}
	
	@Test(priority = 21)//Guest user only
	@Parameters({ "userType" })
	public void verifyRegistrationGenderEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration Gender entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationGenderEnteredEvent(userType);
	}
	
	@Test(priority = 22)//Guest user only
	@Parameters({ "userType" })
	public void verifyRegistrationUserNameEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration Username entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationUserNameEnteredEvent(userType);
	}
	
    @Test(priority = 23)//Guest only
	@Parameters({ "userType" })
	public void verifyRegistrationPasswordEnteredEvent(String userType) throws Exception {
		System.out.println("Verify Registration Password entered Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationPasswordEnteredEvent(userType);
	}
	
	@Test(priority = 24)//NonSubscribed and Subscribed user only
	@Parameters({ "userType" })
	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Started Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyChangePasswordStartedEvent(userType);
	}
	
	@Test(priority = 25)//NonSubscribed and Subscribed user only
	@Parameters({ "userType" })
	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		System.out.println("Verify Change Password Result Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyChangePasswordResultEvent(userType);
	}
	
	@Test(priority = 26)
	public void verifyVideoAutoPlayChangeEventForEnable() throws Exception {
		System.out.println("Video Streaming AutoPlay Changed for enable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoAutoPlayChangeEventForEnable();
	}
	
	@Test(priority = 27)//NonSubscribed and Subscribed user only
	@Parameters({ "userType", "keyword3" })
	public void verifyAddtoWatchlistFromPlaybackPage(String userType, String keyword3) throws Exception {
		System.out.println("Verify Add to Watchlist Event From Playback Page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyAddtoWatchlistFromPlaybackPageInPotrait(userType,keyword3);
	}
	
	@Test(priority = 28)//NonSubscribed and Subscribed user only
	@Parameters({ "userType", "keyword3" })
	public void verifyRemoveFomWatchlistEventFromPlaybackPage(String userType,String keyword3) throws Exception {
		System.out.println("Verify Remove From Watchlist Event From Playback Page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRemoveFromWatchListPlaybackPageInPotrait(userType,keyword3);
	}
	
	@Test(priority = 29)
	public void verifyContentBucketSwipeEvent() throws Exception {
		System.out.println("Verify Content Bucket Swipe Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContentBucketSwipeEvent();
	}
	
	@Test(priority = 30)
	@Parameters({ "keyword2" })
	public void verifyContentBucketSwipeEventInPlayBackPage(String keyword2) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event in playback page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContentBucketSwipeEventInPlayBackPage(keyword2);
	}
	
	@Test(priority = 31)//NonSubscribed and Subscribed user only
	@Parameters({ "userType" })
	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		System.out.println("Verify Profile Update Result Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyProfileUpdateResultEvent(userType);
	}
	
	@Test(priority = 32)
	public void verifyVideoStreamOverWifiChangeEventWhenEnable() throws Exception {
		System.out.println("Verify Video stream  over Wifi Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoStreamOverWifiChangeEventForEnable();
	}
	
	@Test(priority = 33)
	public void verifyVideoStreamOverWifiChangeEventWhenDisable() throws Exception {
		System.out.println("Verify Video stream  over Wifi Change event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyVideoStreamOverWifiChangeEventForDisable();
	}
	
//	@Test(priority = 34)
//	public void verifyDownloadQualityChangeEvent() throws Exception {
//		System.out.println("Verify Download Quality change event");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadQualityChangeEvent();
//	}
	
	@Test(priority = 35)
	public void verifyDownloadOverWfiEventForEnable() throws Exception {
		System.out.println("Verify Download over wifi change for Enable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadOverWifiChangeEventForEnable();
	}
	
	@Test(priority = 36)
	public void verifyDownloadOverWfiEventForDisable() throws Exception {
		System.out.println("Verify Download over wifi change for Disable");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDownloadOverWifiChangeEventForDisable();
	}
	
	@Test(priority = 37)//Guest user only
	@Parameters({ "userType", "displayLanguage" })
	public void verifyDisplayLanguageChangeFromWelcomPage(String userType, String displayLanguage) throws Exception {
		System.out.println("Verify Display Language change from Welcome page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchTillDisplayLanguageScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyDisplayLanguageChangeFromWelcomePage(userType, displayLanguage);
	}
	
	@Test(priority = 38)//Guest user only
	@Parameters({ "userType"})
	public void verifyContentLanguageChangeFromWelcomPage(String userType) throws Exception {
		System.out.println("Verify Content Language change from Welcome page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchTillDisplayLanguageScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyContinueLanguageFromWelcomePage(userType);
	}
	
	@Test(priority = 39)//Guest user only
	@Parameters({ "userType" })
	public void verifyFirstAppLaunchEvent(String userType) throws Exception {
		System.out.println("Verify First App launch Event");
		Zee5ApplicasterMixPanelBusinessLogic.VerifyFirstAppLaunchEvent(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}