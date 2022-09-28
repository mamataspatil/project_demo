package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class AndroidApp_HLS {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	@BeforeTest
	@Parameters({ "userType","TruecallerFlag" })
	public void AppLaunch(String userType,boolean TruecallerFlag) throws InterruptedException {
		System.out.println("Launching Android App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");		
	} 
	

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);

	}
	
	@Test(priority = 1) // Kushal
	@Parameters({ "userType", "RegisteredEmail","RegisteredMobile","TruecallerFlag" })
	public void LoginScreenVerification(String userType, String RegisteredEmail,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		System.out.println("\n--- Login screen verification ---\n");
	//	ZEE5ApplicasterBusinessLogic.introScreenAndLoginScreenValidation(userType, RegisteredEmail);
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		
	}
	
	@Test(priority = 2) 
	@Parameters({ "userType"})
	public void VerifyDisplayLanguage(String userType) throws Exception {
		System.out.println("\n--- verify Display language ---\n");
		ZEE5ApplicasterBusinessLogic.verifyDisplayLanguage(userType);		
	}
	
	@Test(priority = 3) // Manasa
	@Parameters({ "userType", "contentWithoutTrailer" })
	public void SubscriptionScreenValidPrepaidCodeVerificationHLS(String userType, String contentWithoutTrailer) throws Exception {
		System.out.println("\n---Verify valid Promo/prepaid code from Subscription Screen---\n");
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.subscriptionValidationHLSForValidPrepaidCode(userType);
	}

	@Test(priority = 4) 
	@Parameters({ "userType", "contentWithoutTrailer" })
	public void SubscriptionScreenInvalidPrepaidCodeVerificationHLS(String userType, String contentWithoutTrailer) throws Exception {
		System.out.println("\n----Verify invalid Promo/prepaid code from Subscription Screen ---\n");
		ZEE5ApplicasterBusinessLogic.subscriptionValidationHLSForInvalidPrepaidCode(userType, contentWithoutTrailer);
	}
	
	@Test(priority = 5) 
	@Parameters({ "userType"})
	public void SubscriptionValidationHLSForSubUser(String userType) throws Exception {
		System.out.println("\n---Subscription screen Validation for SubscribedUser---\n");
		ZEE5ApplicasterBusinessLogic.subscriptionValidationForSubscribedUser(userType);
	}
	
	@Test(priority = 6) // Sushma
	@Parameters({ "userType", "searchModuleKeyword", "searchKeyword10", "searchKeyword4" })
	public void HomeScreen_Search_Playback_HLS(String userType, String searchModuleKeyword, String searchKeyword10, String searchKeyword4)
			throws Exception {

		System.out.println("\n---Verify Home landing screen---\n");
		ZEE5ApplicasterBusinessLogic.home_LandingScreen(userType);

		System.out.println("\n---Verify Search ---\n");
		ZEE5ApplicasterBusinessLogic.TextSearchAndVoiceSearch(userType, searchModuleKeyword);

		System.out.println("\n---Verify Playback ---\n");
		ZEE5ApplicasterBusinessLogic.playBack(userType, searchKeyword10, searchKeyword4);

	}
	
	@Test(priority = 7) //--- Kushal
	@Parameters({"userType"})
	public void SkipButtonVerification(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.skipToHomeLandingScreenVerification(userType);
	}
	
	@Test(priority = 8) //--- Kushal
	@Parameters({"userType","RegisteredMobile","TruecallerFlag"})
	public void TrendingAndRecentSearchScreen(String userType,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.recentSearchHistoryValidation();
	}
	
	@Test(priority = 9) //--- Kushal
	@Parameters({"userType","RegisteredMobile","TruecallerFlag"})
	public void HaveaCodeJourneyForInvalidPrepaidCodeValidation(String userType,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.haveaCodeForInvalidPrepaidCodeValidation(userType);
	}

	@Test(priority = 10) //--- Sushma
	@Parameters({"userType","RegisteredMobile","TruecallerFlag"})
	public void ValidationOfSubscriptionPlanTitleAndDescription(String userType,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.subscriptionPackValidation(userType);
	}

	@Test(priority = 11) //--- Sushma
	@Parameters({"userType","RegisteredMobile","TruecallerFlag"})
	public void ValationOfPremiumContents(String userType,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.premiumContentsValidationInPlayerScreen(userType);
	}
	
	@Test(priority = 12) 
	@Parameters({"userType","RegisteredMobile","TruecallerFlag"})
	public void ValidationOfDownloadedContent(String userType,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.downloadsContentPlayBackValidation(userType,"Data saver",true);
	}

	@Test(priority = 13) 
	@Parameters({"userType"})
	public void ValidationOfDownloadedContentAndPlayBack_Offline(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.PlayDownloadedContentOffline_And_PlayBackValidation(userType);
	}
	
	@Test(priority = 14) 
	@Parameters({"userType","searchKeyword"})
	public void ValidationOfWatchList(String userType,String keyword) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.AddToWatchlist(userType,keyword);
		ZEE5ApplicasterBusinessLogic.verifyWatchListScreen(userType);
	}
	
	@Test(priority = 15) 
	@Parameters({ "userType"})
	public void SocialLoginValidation(String userType) throws Exception {
		System.out.println("\n--- Social Login Validation ---\n");
		ZEE5ApplicasterBusinessLogic.SocialLogin(userType);
	}
	
	@Test(priority = 16) 
	@Parameters({ "userType", "FirstName", "LastName", "loginThrough","RegisteredMobile","TruecallerFlag" })
	public void NewRegistrationValidation(String userType, String FirstName, String lastName, String loginThrough,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
		System.out.println("\n--- New Registration Validation ---\n");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.navigateToRegisterScreen(loginThrough);
		ZEE5ApplicasterBusinessLogic.validateRegister(FirstName, lastName);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.navigateToRegisterScreen(loginThrough);
		ZEE5ApplicasterBusinessLogic.registerForFreeScreen("Email");
		}else {
			ZEE5ApplicasterBusinessLogic.loggerForNonGuestUserTypes("Validate ZEE5 Registration");
		}
	}
	
	
    @Test(priority = 17) 
	@Parameters({"userType","contentLanguage","RegisteredMobile","TruecallerFlag"})
	public void VerifyRails_AccordingToContentLanguage(String userType, String contentLanguage,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);	
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.railsVerificationAccordingToContentLanguage(userType, contentLanguage);
	}
	
	@Test(priority = 18) 
	@Parameters({"userType", "tabName1","RegisteredMobile","TruecallerFlag"})
	public void VerifyRails_LandingScreens(String userType, String tabName,String RegisteredMobile,boolean TruecallerFlag) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.newOnboarding(TruecallerFlag,RegisteredMobile,userType);
		ZEE5ApplicasterBusinessLogic.verifyRailsInLangingScreen(userType, tabName);
	}
	
	@Test(priority = 19)
	@Parameters({ "userType", "searchKeyword1"})
	public void VideoQualityCheck(String userType, String searchKeyword1) throws Exception {
		ZEE5ApplicasterBusinessLogic.settings_DefaultVideoStreamingQuality(userType, searchKeyword1);
	}
	
	@Test(priority = 20)
	@Parameters({ "userType","searchKeyword11" })
	public void VideoQualityCheckInPlayer(String userType, String searchKeyword11) throws Exception {
		ZEE5ApplicasterBusinessLogic.settings_DefaultVideoStreamingQualityInPlayer(userType, searchKeyword11);
	}
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
