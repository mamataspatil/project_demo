package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Defects {
	
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
	
	@Test(priority = 1)//Sushma
	@Parameters({ "userType"})
	public void PremiumTag_SearchResultScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.premiumTagOnSearchResultScreen(userType);

	}
	
	@Test(priority = 2)//Sushma
	public void SwipeOnContentCards() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.swipeFunctionalityOnContentCardsOfTray();

	}
	
	@Test(priority = 3)//Sushma
	public void BackbuttonAndHeaderName_ListingScreen() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.listingScreenBackArrowAndHeaderName("Home");
	}
	
	@Test(priority = 4)//Sushma
	public void PlayBack_IndiaTodayLiveChannelContent() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.indiaTodayLiveChannel();
	}
	
	@Test(priority = 5)//Sushma
	@Parameters({ "userType" })
	public void audioLanguageoptionValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.audioLanguageValidation("Trailer - Khushi");
	}
	
	@Test(priority = 6)////Sushma
	@Parameters({ "userType" })
	public void BottomNavigationBar_ListingScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.bottomNavigationBarValidationInListingScreen(userType);
	}
	
	@Test(priority = 7)//Sushma
	public void ListingScreenHeaderValidation() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.listingScreenHeader("Unlimited Fun | Kannada");
	}
	
	@Test(priority = 8)//Sushma
	@Parameters({ "userType" })
	public void UserAccountInfoValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID("7892215214", "User@123");
		ZEE5ApplicasterBusinessLogic.accountInfoValidationInPaymentPage(userType);
	}
	
	@Test(priority = 9)//Sushma
	@Parameters({ "userType" })
	public void HaveACodeOptionInPlanSelectionScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.haveAPrepaidCodeDuringUpgradeJourney(userType,"newsubsrevamp4@mailnesia.com", "111222");
	}
	
	@Test(priority = 10)//Sushma
	public void AppCrash_LearnWithEduauraaTray() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.appcrashIssue_OnTappingBackButton_LearnWithEduauraaTray();
	}
	
	@Test(priority = 11)//Sushma
	public void AppCrashIssue_tapingOptionsBelowThePlayer() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.crashIssue_OptionBelowPlayer("Expiry Date");
	}

	@Test(priority = 12)//Sushma
	@Parameters({ "userType" })
	public void AppCrash_TVODContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.appcrashIssue_ChennaiVSChina(userType,"Chennai vs China | Trailer");
	}
	
	@Test(priority = 13)//Sushma
	@Parameters({ "userType" })
	public void AppCrashIssue_MaximizeAndMinimize_LiveContents(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.crashIssue_LiveTvContents();
	}	
	
	@Test(priority = 14)//Sushma
	@Parameters({ "userType", "searchKeyword4" })
	public void DownloadingMovieContent_DownloadsTab(String userType, String searchKeyword4) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.movieDownloadFunctonality(userType, searchKeyword4);

	}

//	@Test(priority = 15)//Sushma
	@Parameters({ "userType" })
	public void contentPlaybackValidation_afterTappingReplayIcon(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.contentPlayBack_afterTappingReplayIcon(userType);
	}
	
	@Test(priority = 16)//Sushma
	@Parameters({ "userType" })
	public void NavigationToEduauraaWebPageValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID("smoke.622@gmail.com", "11223344");
		ZEE5ApplicasterBusinessLogic.navigationToEduauraaWeb(userType, "Eduauraa");
	}
	
	@Test(priority = 17)//Sushma
	@Parameters({ "userType" })
	public void PlayBackInBackgroundValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.contentPlayBackInBackGroundValidation(userType, "Music");
	}
	
	@Test(priority = 18)//Sushma
	@Parameters({ "userType" })
	public void DownloadIconValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.downloadIconValidation_NetworkInterupption(userType, "Movies");

	}
	
	@Test(priority = 19)//Sushma
	public void OffineMode_UpComingScreenValdation() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.upcomingScreen_OfflineMode();
	}
	
	@Test(priority = 20)//Sushma
	public void OffineMode_AppCrash_OnTappingShareOption() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.CrashIssue_Share_OfflineMode();
	}
	
	@Test(priority = 21)//Sushma
	@Parameters({ "userType" })
	public void OfflineMode_AppLaunch(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	    ZEE5ApplicasterBusinessLogic.crashIssue_AppLaunch_OfflineMode(userType);
	}
	
	
	@Test(priority = 22) //Bhavana
	@Parameters({ "userType" })
	public void VerifyDownloadUpNextContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.downloadUpNextContent(userType);
			}
	
	@Test(priority = 23) //Bhavana
	@Parameters({ "userType" })
	public void LogoutValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.postLogoutValidation(userType);
		
			}
	
	@Test(priority = 24) //Bhavana
	@Parameters({ "userType" })
	public void loginFromMoreScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.loginFromMoreScreen(userType);
		
			}
	
	@Test(priority = 25) //Bhavana
	@Parameters({ "userType" })
	public void RecommendRailInMoviesAndNews(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.recommendRailInMovies(userType);
		ZEE5ApplicasterBusinessLogic.recommendRailListingScreenInNews(userType);
			}
	
	@Test(priority = 26) //Bhavana
	@Parameters({ "userType" })
	public void EduauraaAndValidateBenefitsSection(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.playEduauraaAndValidateExpandCollapseofBenefitsSection(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.eduaraaCarousel(userType);
			}
	
	@Test(priority = 27) //Bhavana
	@Parameters({ "userType" })
	public void AllEpisodesListingScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);		
		ZEE5ApplicasterBusinessLogic.allEpisodeTrayListingScreen(userType);
		
			}
	
	@Test(priority = 28) //Bhavana
	@Parameters({ "userType" })
	public void ContinueWatchingTray(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.continueWatchingTrayDefectValidation(userType);
			}
	
	@Test(priority = 29) //Bhavana
	@Parameters({ "userType" })
	public void TrendingNews(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);	
		ZEE5ApplicasterBusinessLogic.trendingNews(userType);
			}
	
	@Test(priority = 30) //Bhavana
	@Parameters({ "userType" })
	public void SearchedContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);	
		ZEE5ApplicasterBusinessLogic.searchedContent(userType);
			}
	

	@Test(priority = 31) //Bhavana
	@Parameters({ "userType" })
	public void VerifyDownloadContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.validationOfDownloadedContentInDownlodsScreen(userType);		
		
	}
	
	@Test(priority = 32) //Bhavana
	@Parameters({ "userType" })
	public void DownloadBeforeTvContent(String userType) throws Exception {		
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.downloadBeforeTvContent(userType);
	}

	@Test(priority = 33) //Bhavana
	@Parameters({ "userType" })
	public void ContentPlayBackValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.contentPlayBackValidation(userType);
			}

	@Test(priority = 34) //Bhavana
	@Parameters({ "userType" })
	public void LaunchAppInOffline(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);		
		ZEE5ApplicasterBusinessLogic.launchAppinOffline(userType);
		}
	
	@Test(priority = 35) //Bhavana
	@Parameters({ "userType" })
	public void LoginThroughAnyentryPointDefect(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.loginThroughAnyentryPointDefect(userType);
		}
	
	// Login CTA in subscribe screen is not present in latest build
	//@Test(priority = 36) //Bhavana
	@Parameters({ "userType" })
	public void FirstEpisodeContentPlayback(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.firstEpisodeContentPlayback(userType);
		}
	
	@Test(priority = 37) //Bhavana
	@Parameters({ "userType" })
	public void PlayerControlDefect(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.playerControlDefect(userType);
		}
	
	@Test(priority = 38) //Bhavana
	@Parameters({ "userType" })
	public void ValidateQualityOptions(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.validatingQualityOptionDefect(userType);
		}
	

	@Test(priority = 39) //Bhavana
	@Parameters({ "userType" })
	public void SomethingWentWrongDefectValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.somethingWentWrongDefectValidation(userType);
		}
	
	@Test(priority = 40) //Bhavana
	@Parameters({ "userType" })
	public void RentNowCTAforTVODcontent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.rentNowCTAforTVODcontent(userType);
		}
	
	// skip CTA on player is not present in latest build
	//@Test(priority = 41)  //Bhavana
	@Parameters({ "userType" })
	public void PreviousIconForPremiumContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.previousIconForPremiumContent(userType);
		}
	
	@Test(priority = 42) //Bhavana
	@Parameters({ "userType" })
	public void VerifyBackButtonFromPaymentScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyBackButtonFromPaymentScreen(userType);
		
	}
	
	@Test(priority = 43) //Bhavana
	@Parameters({ "userType" })
	public void VerifySubscribeIcon(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifySubscribeIcon(userType);
		
	}

	@Test(priority = 44) //Bhavana
	@Parameters({ "userType" })
	public void verifyExplorePremiiumOffline(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyExplorePremiiumOffline(userType);
		
	}
	@Test(priority = 45) //Bhavana
	@Parameters({ "userType" })
	public void verifyNegativeRoundOffPrice(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyNegativeRoundOffPrice(userType);
		
	}
	
	@Test(priority = 46) //Bhavana
	@Parameters({ "userType" })
	public void verifyBuyPlanForEduauraa(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyBuyPlanForEduauraa(userType);
	}
	
	@Test(priority = 47) //Bhavana
	@Parameters({ "userType" })
	public void VerifyDiscountAmount(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyDiscountAmount(userType);
	}
	
	@Test(priority = 48) //Bhavana
	@Parameters({ "userType" })
	public void VerifyAccountPopupDefect(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.accountInfoPopupDefect(userType);
	}
	
	@Test(priority = 49) //Bhavana
	@Parameters({ "userType" })
	public void VerifyLogoutAndAuthenticateOptions(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyLogoutAndAuthenticateDeviceOptions(userType);
	}
	
	@Test(priority = 50) //Bhavana
	@Parameters({ "userType" })
	public void VeirfyMyTransactions(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyMyTransactionOption(userType);		
	}
	
	@Test(priority = 51) //Bhavana
	@Parameters({ "userType" })
	public void VeirfyGoogleLoginFromSubscriptionJourney(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyGoogleIconInSubscriptionScreen(userType);		
	}
	
	@Test(priority = 52) //Bhavana
	public void VerifyUpcomingContent() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyUpcomingContent();		
	}
	
	@Test(priority = 53) //Bhavana
	@Parameters({ "userType" })
	public void DownloadWeekInShortContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyWeekInShorts(userType);		
	}
	
	@Test(priority = 54) //Bhavana
	@Parameters({ "userType" })
	public void VerifyContinueCTAInSubscribeScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyContinueCTAInSubscribeScreen(userType);		
	}
	
	@Test(priority = 56) //Bhavana
	@Parameters({ "userType" })
	public void VerifyErrorMessageOnPlayerOnTappingNextButton(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyErrorMessageOnPlayerOnTappingNextButton(userType);		
	}
	
	@Test(priority = 57) //Bhavana
	@Parameters({ "userType" })
	public void FunctionalityOfExplorePremium(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.functionalityOfExplorePremium(userType);		
	}
	
	@Test(priority = 58) //Bhavana
	@Parameters({ "userType" })
	public void VerifyBrowseAllPacksInMySubscription(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyBrowseAllPacksInMySubscription(userType);		
	}
	
	@Test(priority = 59) //Bhavana
	@Parameters({ "userType" })
	public void verifyErrorMeasageOfExpiredCode(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyErrorMeasageOfExpiredCode(userType);		
	}
	@Test(priority = 60) //Bhavana
	@Parameters({ "userType" })
	public void VerifySubscribeIconPostLoggingFromLoginctaOnPlayer(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifySubscribeIconPostLoggingFromLoginctaOnPlayer(userType);		
	}
	
	@Test(priority = 61) //Bhavana
	@Parameters({ "userType" })
	public void VerifyOTPPopup(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyOTPPopup(userType);		
	}

	@Test(priority = 62) //Bhavana
	@Parameters({ "userType","RSVODUserEmail","RSVODUserPassword" })
	public void UpgradeToPremiumCTA(String userType,String pEmailId, String pPassword) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(pEmailId, pPassword);
		ZEE5ApplicasterBusinessLogic.upgradeToPremiumCTA(userType);		
	}
	
	@Test(priority = 63) //Bhavana
	@Parameters({ "userType","zeetestcluball@test.com","123456"  })
	public void VerifyPurchaseofPremiumPack(String userType,String pEmailId, String pPassword) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(pEmailId, pPassword);
		ZEE5ApplicasterBusinessLogic.verifyPurchaseofPremiumPack(userType);		
	}
	
	@Test(priority = 64) //Bhavana
	@Parameters({ "userType" })
	public void IncorrectMessageForInvalidMobileNumber(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.incorrectMessageForInvalidMobileNumber(userType);		
	}

	@Test(priority = 65) //Bhavana
	@Parameters({ "userType" })
	public void AppCrash_OnTappingBuyPlanInOffline(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.appCrash_OnTappingBuyPlanInOffline(userType);		
	}
	
	@Test(priority = 66) //Bhavana
	@Parameters({ "userType" })
	public void VerifyPrepaidCode(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyPrepaidCode(userType);		
	}
	
	@Test(priority = 67) //Bhavana
	@Parameters({ "userType" })
	public void VerifyPlaybackOfPremiumContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifyPlaybackOfPremiumContent(userType);		
	}
	
	@Test(priority = 68) //Bhavana
	@Parameters({ "userType" })
	public void VerifySkipCTAOnPlayer(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.verifySkipCTAOnPlayer(userType);		
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
	
		
}
