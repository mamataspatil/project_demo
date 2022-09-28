package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityAndroidBusinessLogic;

public class AndroidPWASanityScript {

	private Zee5PWASanityAndroidBusinessLogic Zee5PWASanityBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Chrome");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.ZeePWALogin("E-mail", userType);
		Zee5PWASanityBusinessLogic.selectLanguages();
	}

	@Test(priority = 1)
	@Parameters({ "userType" }) // SHREENIDHI
	public void PWAMandatoryRegistration(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.registerPopUpScenarios(userType);
	}

	@Test(priority = 2) // SHREENIDHI
	@Parameters({ "userType" })
	public void PWAOnboardingScenarios(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.OnboardingScenario(userType); // from smoke
	}

	@Test(priority = 3)
	@Parameters({ "userType" }) // SHREENIDHI
	public void PWAMenuOrSettingScenarios(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.MenuOrSettingScenarios(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" }) // MANAS
	public void PWACarousel() throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.verifyCarouselAutoRotation();
		Zee5PWASanityBusinessLogic.verifyCarouselPlayIconFunctionality();
		Zee5PWASanityBusinessLogic.verifyCarouselPremiumIconFunctionality();
		Zee5PWASanityBusinessLogic.verifyCarouselMetaData();
		Zee5PWASanityBusinessLogic.verifyCarouselLeftRightFunctionality();
		System.out.println(">>>> 4th method completed");
	}

	@Test(priority = 5) // VINAY
	@Parameters({ "userType" })
	public void PWALanguageModule(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.LanguageModule(UserType);
	}

	@Test(priority = 6)
	@Parameters({ "userType" }) // SHREENIDHI
	public void PWANetworkInterruptions(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.networkInterruptionScenarios(userType);
	}

	@Test(priority = 7) // SHREENIDHI
	@Parameters({ "userType" })
	public void PWAProfile(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.myProfileScenarios(userType);
	}

	@Test(priority = 8) // BASAVARAJ
	@Parameters({ "userType" })
	public void PWAKalturaPlayability(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.Kaltura(userType);
		// smoke Vinay
		Zee5PWASanityBusinessLogic.playerValidations(userType);
		// Zee5PWASanityBusinessLogic.UpnextRail();
	}

	@Test(priority = 9) // BASAVARAJ
	@Parameters({ "userType", "consumptionsEpisode", "consumptionsShow", "consumptionsFreeContent",
			"consumptionsPremiumContent" })
	public void PWAContentDetails(String userType, String consumptionsEpisode,
			String consumptionsShow, String consumptionsFreeContent, String consumptionsPremiumContent)
			throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.ContentDetails(userType);
		Zee5PWASanityBusinessLogic.checkDurationInLivetv();
		Zee5PWASanityBusinessLogic.checkDurationandProgressVideo(userType);
		// smoke Tanisha
		Zee5PWASanityBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Episode", consumptionsEpisode); 
		Zee5PWASanityBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Live TV", "");
		Zee5PWASanityBusinessLogic.verifyNoSubscriptionPopupForFreeContent(userType, consumptionsFreeContent);
		Zee5PWASanityBusinessLogic.verifySubscriptionPopupForPremiumContent(userType, consumptionsPremiumContent);
		Zee5PWASanityBusinessLogic.verifyCTAandMetaDataInDetailsAndConsumption(consumptionsShow);
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void PWAMoviesPageValidation(String userType) throws Exception // SUSHMA MOVIES
	{
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.Moviepage(userType, "Movies");
	}

	@Test(priority = 11)
	@Parameters({ "userType" }) // HITESH
	public void PWAMusicPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.MusicPageValidation(userType, "Music");
	}

	@Test(priority = 12)
	@Parameters({ "userType" }) // MANASA
	public void PWAPremiumPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.premiumPageValidation(userType, "Premium");
	}

	@Test(priority = 13)
	@Parameters({ "userType" }) // BHAVANA
	public void PWATVShowsPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.TVShowsValidation(userType);
	}

	@Test(priority = 14)
	@Parameters({ "userType" }) // MANASA
	public void PWALandingPagesHomeValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.ValidatingLandingPages(userType);// Tejas
		Zee5PWASanityBusinessLogic.verifyUIofHomePage(); // manasa default home
	}

	@Test(priority = 15)
	@Parameters({ "userType" }) // YASHASHWINI
	public void PWANewsPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.newsPageValidation(userType, "News");
	}

	@Test(priority = 16) // BINDU
	@Parameters({ "userType" })
	public void PWAWebSeriesPageValidation(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.WebSeriesScreen(UserType, "Web Series");
	}

	@Test(priority = 17) // BINDU
	@Parameters({ "userType" })
	public void PWAClubPageValidation(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.Clubpage(UserType, "Club");
	}
	
	@Test(priority = 17) 
	@Parameters({ "userType" })
	public void PWAEduauraaVerification(String UserType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.PWAEduauraaVerification(UserType);
	}


	@Test(priority = 18) // TANISHA
	@Parameters({ "userType" })
	public void PWARecoTalamoosModule(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.verificationOfRecoTalamoos(userType);
	}

	@Test(priority = 19) // BHAVANA
	@Parameters({ "userType" })
	public void PWAStaticPagesInMenuAndFooterSection(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.staticPagesandFooterSectionValidation(userType);
	}

	@Test(priority = 20)
	@Parameters({ "userType", "searchModuleSearchKey", "url" }) // SUSHMA SEARCH
	public void PWASearch(String userType, String searchKey, String url) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.searchScreen(userType);
		Zee5PWASanityBusinessLogic.searchResultScreen(searchKey);
		Zee5PWASanityBusinessLogic.navigationToConsumptionScreenThroughTrendingSearches();
	}

	@Test(priority = 21)
	@Parameters({ "userType" })
	public void PWALiveTV(String userType) throws Exception // SUSHMA LIVE TV
	{
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.LiveTvScreen(userType);
		// smoke
		Zee5PWASanityBusinessLogic.verifyLiveTvAndChannelGuideScreen();

	}

	@Test(priority = 22)
	@Parameters({ "userType" }) // SATHISH
	public void PWASubscriptionPopUp(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.zeePWAVerifySubscriptionPopupAfterTrailerPlaybackIsComplete(userType);
		Zee5PWASanityBusinessLogic
				.zeePWAVerifyNavigationToSubscriptionFlowFromSubscriptionPopupFullscreenPlayer(userType);
		// smoke
		Zee5PWASanityBusinessLogic.zeePWASubscriptionSuite(userType);
	}

	@Test(priority = 23)
	@Parameters({ "userType" }) // MANASA
	public void PWASubscriptionPageValidation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.contentLanguageVerify(userType);
		Zee5PWASanityBusinessLogic.verifyUIofZEESubscriptionPage(userType);
		Zee5PWASanityBusinessLogic.verifyUIofMySubscriptionPage(userType);
		Zee5PWASanityBusinessLogic.validatingActiveAndExpiredCardsinMyTransactionPage(userType);
	}

	@Test(priority = 24)
	@Parameters({ "userType" }) // BHAVANA
	public void PWAExternalLinks(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.LinksValidation(userType);
	}

	@Test(priority = 25)
	@Parameters({ "userType" }) // VINAY
	public void PWAUserActions(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.UserActions(userType);
	}

	@Test(priority = 26) // BASAVARAJ
	@Parameters({ "userType" })
	public void PWATimedAnchors(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.ShowsTimeperiodProvided(userType);
		Zee5PWASanityBusinessLogic.musicTimeperiodProvided(userType);
		Zee5PWASanityBusinessLogic.TimedAnchors(userType);
		Zee5PWASanityBusinessLogic.moviesTimeperiodProvided(userType);
		Zee5PWASanityBusinessLogic.continueWatchingtrayData(userType);

	}

	// @Test(priority = 27) // BASAVARAJ
	public void PWAVIL() throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.verifyingVodafoneNativeApp();
	}
	
	@Test(priority = 28)
	@Parameters({ "userType" })
	public void TasksImplementation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.PWAVerifyTitleInAnchorTags(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyImageWebP(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyMetaTags(userType); 
		Zee5PWASanityBusinessLogic.PWASubtitleSelection(userType);//defect associated PWA2-6919, PWA2-6832
		Zee5PWASanityBusinessLogic.PWAVerifyPageFreezeForChannel(userType);
		Zee5PWASanityBusinessLogic.PWAClickOnPromotionalBanners(userType); 
		Zee5PWASanityBusinessLogic.PWAZeeplexDisclaimer(userType);
		Zee5PWASanityBusinessLogic.verifyMandatoryRegistrationPopUpCount(userType);
		Zee5PWASanityBusinessLogic.verifyClubTagForLiveTVContents(userType);				
		Zee5PWASanityBusinessLogic.PWAVerifyMetaTagsForTwitter(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyZeePlexContents(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyWatchTrailerCTA(userType);
		Zee5PWASanityBusinessLogic.PWAWatchPromoAndVerifyTitle(userType);
		Zee5PWASanityBusinessLogic.PWAWatchMovieAndThenClickTrailer(userType);
		Zee5PWASanityBusinessLogic.PWAWatchNewsVODAndThenClickAnotherContent(userType);
		Zee5PWASanityBusinessLogic.PWAClickSubscribeDuringAdPlay(userType);
		Zee5PWASanityBusinessLogic.PWAVmaxAdForMusicAndNews(userType);
		Zee5PWASanityBusinessLogic.PWALatestEpisodeInURLAndCheckSubscribe(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyingRefreshForShowDetailsAndConsumptions(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyNextContentPlayAfterBeforeTVContent(userType);
		Zee5PWASanityBusinessLogic.PWAClickSubscribeDuringTrailerPlay(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyNewsVODPlay(userType);
		Zee5PWASanityBusinessLogic.PWAVerifyImageAssetForZee5Logo(userType);
	}
	
	@Test(priority = 29) //Kartheek
	@Parameters({ "userType" })
	public void mPWATasksImplementation(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.reloadHome();
		Zee5PWASanityBusinessLogic.contentsOnTheWatchlist(userType);
		Zee5PWASanityBusinessLogic.invalidCodeErrorMessage(userType);
		Zee5PWASanityBusinessLogic.relatedContentOrDescriptionShouldBeDisplayed(userType);
		Zee5PWASanityBusinessLogic.headerTextAndRailNameShouldBeUpdatedAndDisplayed(userType);
		Zee5PWASanityBusinessLogic.crownSymbolShouldBeCisplayedOnGetPremiumCTA(userType);
		Zee5PWASanityBusinessLogic.planPriceINR749ShouldBeDisplayed(userType);
		Zee5PWASanityBusinessLogic.verifyOTPCTAShouldBeDisplayed(userType);
		Zee5PWASanityBusinessLogic.payNowCTAShouldBeDisplayed(userType);
//		Zee5PWASanityBusinessLogic.userShouldBeNavigatedBacKToTheSubscriptionPage(userType);
		Zee5PWASanityBusinessLogic.toWatchThisPremiumContentAndSkipAndByPlan(userType);
		Zee5PWASanityBusinessLogic.explorePremiumCTAShouldBeDisplayed(userType);
		Zee5PWASanityBusinessLogic.explorePremiumCTAScreenWithToastMessage(userType);
		Zee5PWASanityBusinessLogic.reloadingTrailerPlaybackPage(userType);
		Zee5PWASanityBusinessLogic.geTPremiumCTAShouldBeDisplayedOnBelowThePlayer(userType);
		Zee5PWASanityBusinessLogic.verifyNewTabRevised(userType);
		Zee5PWASanityBusinessLogic.step2Of3ShouldNotBeDisplayed(userType);
		Zee5PWASanityBusinessLogic.skipCTAInlineMessageOnPlayerShouldBeTranslateAsPerTheSelectedDisplaylanguage(userType);
		Zee5PWASanityBusinessLogic.verifyUpgradeToAnnualPlanInSubscriptionPlanPage(userType);
		Zee5PWASanityBusinessLogic.theTranslationShouldHappenBasedOnTheSelectedDisplayLanguage(userType);
		Zee5PWASanityBusinessLogic.subscriptionPageShouldBeDisplayedWithTheToastMessage(userType);
		Zee5PWASanityBusinessLogic.autoflipCarouselTitleShouldBeDisplayed(userType);
		Zee5PWASanityBusinessLogic.userShouldBeNavigatedBackToTheConsumptionPage(userType);
		Zee5PWASanityBusinessLogic.upgradeCTAAvailabilityOnTheHeaderMenu(userType);
		Zee5PWASanityBusinessLogic.contentOrDisplayLanguageShouldChange(userType);
		Zee5PWASanityBusinessLogic.textSlidersOnTopOfTheScreen(userType);
		Zee5PWASanityBusinessLogic.subscriptionWidgetBelowThePlayerShouldBeDisplayedForBeforeTvContent(userType);
		Zee5PWASanityBusinessLogic.mpwa28154(userType);
		Zee5PWASanityBusinessLogic.changeOfHeaderForAllBreakpoints(userType);
		Zee5PWASanityBusinessLogic.paymentOptionsShouldBeTranslatedBasedOnTheDisplayLanguage(userType);
		Zee5PWASanityBusinessLogic.newSVODContentShouldBePlayedProperly(userType);
		Zee5PWASanityBusinessLogic.paymentPageShouldBeDisplayAfterApplyingPromoCode(userType);
		Zee5PWASanityBusinessLogic.haveACodeShouldBeAutoPopulate(userType);
		Zee5PWASanityBusinessLogic.verifyplanpriceinsubscriptionpage(userType);
		Zee5PWASanityBusinessLogic.verifyshowscontentinbeforetv(userType);
		Zee5PWASanityBusinessLogic.haveACodeCTATextShouldBeDisplayedForThEExpiredUsers(userType);
		Zee5PWASanityBusinessLogic.carouselBannerShouldLoadAndThumbnailShouldBeDisplayed(userType);
		Zee5PWASanityBusinessLogic.EpsiodeAndSeasonValidationInConsumptionPage(userType);
		Zee5PWASanityBusinessLogic.pageShouldNavigateToPlanListingPageWithoutShowingAnyError(userType);
		Zee5PWASanityBusinessLogic.seasonAndEpisodeNumberMetadata(userType);
		Zee5PWASanityBusinessLogic.FooterSectionValidationBasedOnContentLanguage(userType);
		Zee5PWASanityBusinessLogic.passwordResetLinkSentToYourEmailIDToastMessageShouldBeDisplayed(userType);
//		Zee5PWASanityBusinessLogic.somethingWentWrongMessageWithRetryOptionShouldBeDisplayed(userType);
	}
	

	@AfterClass
	public void tearDown() {
		Zee5PWASanityBusinessLogic.tearDown();
	}
}