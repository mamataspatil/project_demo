package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class TasksAndDefects {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Android App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}

	@Test(priority = 1)
	@Parameters({ "userType", "searchKeyword4" })
	public void tasksAndDefects(String userType, String searchKeyword4) throws Exception {
		ZEE5ApplicasterBusinessLogic.tasksAndDefectsValidation(userType, searchKeyword4);

	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod2(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.downloadUpNextContent(userType);
		ZEE5ApplicasterBusinessLogic.postLogoutValidation(userType);
		ZEE5ApplicasterBusinessLogic.loginFromMoreScreen(userType);
		ZEE5ApplicasterBusinessLogic.recommendRailInMovies(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod3(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.playEduauraaAndValidateExpandCollapseofBenefitsSection(userType);
		ZEE5ApplicasterBusinessLogic.recommendRailListingScreenInNews(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.continueWatchingTrayDefectValidation(userType);
		ZEE5ApplicasterBusinessLogic.trendingNews(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.searchedContent(userType);

	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod4(String userType) throws Exception {
		// re-launch
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();

		ZEE5ApplicasterBusinessLogic.validationOfDownloadedContentInDownlodsScreen(userType);
		ZEE5ApplicasterBusinessLogic.prepaidCodeValidation(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.eduaraaCarousel(userType);

	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod5(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.contentPlayBackValidation(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.downloadBeforeTvContent(userType);
		ZEE5ApplicasterBusinessLogic.allEpisodeTrayListingScreen(userType);

	}

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod6(String userType) throws Exception {
		// re-launch
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.launchAppinOffline(userType);
		ZEE5ApplicasterBusinessLogic.validatingQualityOptionDefect(userType);
		// Login CTA in subscribe screen is not present in latest build
		// ZEE5ApplicasterBusinessLogic.FirstEpisodeContentPlayback(userType);
		ZEE5ApplicasterBusinessLogic.loginThroughAnyentryPointDefect(userType);
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod7(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.playerControlDefect(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.somethingWentWrongDefectValidation(userType);
		// skip CTA on player is not present in latest build
		// ZEE5ApplicasterBusinessLogic.PreviousIconForPremiumContent(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.rentNowCTAforTVODcontent(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod8(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyBackButtonFromPaymentScreen(userType);
		ZEE5ApplicasterBusinessLogic.verifySubscribeIcon(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyExplorePremiiumOffline(userType);

	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void TasksAndDefectsMethod9(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyNegativeRoundOffPrice(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyBuyPlanForEduauraa(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyDiscountAmount(userType);
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void VerifyAccountPopupDefect(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.accountInfoPopupDefect(userType);
	}

	@Test(priority = 11)
	@Parameters({ "userType" })
	public void VerifyLogoutAndAuthenticateOptions(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyLogoutAndAuthenticateDeviceOptions(userType);
	}

	@Test(priority = 12)
	@Parameters({ "userType" })
	public void VeirfyMyTransactions(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyMyTransactionOption(userType);
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void VeirfyGoogleLoginFromSubscriptionJourney(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyGoogleIconInSubscriptionScreen(userType);
	}

	@Test(priority = 14)
	@Parameters({ "userType" })
	public void DownloadIconValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.downloadIconValidation_NetworkInterupption(userType, "Movies");

	}

	@Test(priority = 15)
	@Parameters({ "userType" })
	public void contentPlaybackValidation_afterTappingReplayIcon(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.contentPlayBack_afterTappingReplayIcon(userType);
	}

	@Test(priority = 16)
	@Parameters({ "userType" })
	public void NavigationToEduauraaWebPageValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID("smoke.622@gmail.com", "11223344");
		ZEE5ApplicasterBusinessLogic.navigationToEduauraaWeb(userType, "Eduauraa");
	}

	@Test(priority = 17)
	@Parameters({ "userType" })
	public void PlayBackInBackgroundValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.contentPlayBackInBackGroundValidation(userType, "Music");
	}

	@Test(priority = 18)
	@Parameters({ "userType" })
	public void audioLanguageoptionValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.audioLanguageValidation("Trailer - Khushi");
	}

	@Test(priority = 19)
	@Parameters({ "userType" })
	public void UserAccountInfoValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID("7892215214", "User@123");
		ZEE5ApplicasterBusinessLogic.accountInfoValidationInPaymentPage(userType);
	}

	@Test(priority = 14)
	public void VerifyUpcomingContent() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyUpcomingContent();
	}

	@Test(priority = 15)
	@Parameters({ "userType" })
	public void DownloadWeekInShortContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyWeekInShorts(userType);
	}
	
	@Test(priority = 22)
	@Parameters({ "userType" })
	public void AppCrash_TVODContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.appcrashIssue_ChennaiVSChina(userType,"Chennai vs China | Trailer");
	}
	
	@Test(priority = 23)
	@Parameters({ "userType" })
	public void BottomNavigationBar_ListingScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.bottomNavigationBarValidationInListingScreen(userType);
	}
	
	@Test(priority = 24)
	@Parameters({ "userType" })
	public void ListingScreenHeaderValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.listingScreenHeader("Unlimited Fun | Kannada");
	}
	
	@Test(priority = 25)
	@Parameters({ "userType" })
	public void HaveACodeOptionInPlanSelectionScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.haveAPrepaidCodeDuringUpgradeJourney(userType,"newsubsrevamp4@mailnesia.com", "111222");
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}