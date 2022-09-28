package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityWEBBusinessLogic;

public class WebPWASanityScript {

	private Zee5PWASanityWEBBusinessLogic Zee5WEBPWASanityBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		// zee5WebBusinessLogic.relaunchFlag = false;
		Zee5WEBPWASanityBusinessLogic = new Zee5PWASanityWEBBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ZeeWEBPWALogin(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void DefaultHomePage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.defaultHomePage(userType);
	}


	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void RegistrationPopUp(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.registerPopUpScenarios(userType);
	}


	@Test(priority = 3)
	@Parameters({ "userType" })
	public void Onboarding(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.OnboardingModule(userType);
	}


	@Test(priority = 4)
	@Parameters({ "userType" })
	public void Profile(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.myProfileScenarios(userType);
	}


	@Test(priority = 5)
	@Parameters({ "userType" })
	public void Subscription(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		// SMOKE
		Zee5WEBPWASanityBusinessLogic.WEBPWAValidatingSubscriptionAndTransaction(userType);
		Zee5WEBPWASanityBusinessLogic.WEBPWAValidatingSubscribeLinks(userType);
		// SMOKE SubscriptionModule //SATISH
		Zee5WEBPWASanityBusinessLogic.PaymentGateway(userType);
		Zee5WEBPWASanityBusinessLogic.zeePWASubscriptionSuite(userType);
		// SANITY
		Zee5WEBPWASanityBusinessLogic.contentLanguageVerify(userType);
		Zee5WEBPWASanityBusinessLogic.verifyUIofMySubscriptionPage(userType);
		Zee5WEBPWASanityBusinessLogic.validatingActiveAndExpiredCardsinMyTransactionPage(userType);
		Zee5WEBPWASanityBusinessLogic.verifyUIofZEESubscriptionPage(userType);
	}


	@Test(priority = 6)
	@Parameters({ "userType" })
	public void ExternalLinks(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.LinksValidation(userType);
	}


	@Test(priority = 7)
	@Parameters({ "userType" })
	public void StaticPagesInMenuAndFooterSection(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.staticPagesandFooterSectionValidation(userType);
	}


	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void TimedAnchors(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.TimedAnchors(userType);
		Zee5WEBPWASanityBusinessLogic.ShowsTimeperiodProvided(userType);
		Zee5WEBPWASanityBusinessLogic.musicTimeperiodProvided(userType);
		Zee5WEBPWASanityBusinessLogic.moviesTimeperiodProvided(userType);
		Zee5WEBPWASanityBusinessLogic.continueWatchingtrayData(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void LandingPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		//SMOKE LANDINGPAGE : TEJAS
		Zee5WEBPWASanityBusinessLogic.mandatoryRegistrationPopUp(userType);
		Zee5WEBPWASanityBusinessLogic.landingpagePropertiesValidation(userType,"Home");
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("Home");
		// SANITY
		Zee5WEBPWASanityBusinessLogic.mandatoryRegistrationPopUp(userType);
		if (userType.equals("Guest")) {
			Zee5WEBPWASanityBusinessLogic.trayTitleAndContentValidationWithApiDataMovie("Home", "homepage");
			Zee5WEBPWASanityBusinessLogic.LandingPagegap("The Power Game", "Chemistry of Kariyappa", "Guest");
		} else if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			Zee5WEBPWASanityBusinessLogic.trayTitleAndContentValidationWithApiDataMovie("Home", "homepage");
		}
		Zee5WEBPWASanityBusinessLogic.ContinuewatchingTray(userType);
		Zee5WEBPWASanityBusinessLogic.mandatoryRegistrationPopUp(userType);
		Zee5WEBPWASanityBusinessLogic.FreeContentAndPremiumContent(userType);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType" })
	public void ClubMigration(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.ClubMigiration(userType);		
	}


	@Test(priority = 10)
	@Parameters({ "userType" })
	public void MoviePage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Moviepage(userType, "Movies");
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("Movies");
	}


	@Test(priority = 11)
	@Parameters({ "userType" })
	public void PremiumPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		// SMOKE DEFAULT HOME PAGE : MANASA
		Zee5WEBPWASanityBusinessLogic.verifyUIofHomePage();
		// SANITY
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("Premium");
		Zee5WEBPWASanityBusinessLogic.landingPagesValidation("Premium");
		Zee5WEBPWASanityBusinessLogic.landingPagesTrailerAndPopUpValidation(userType, "Premium");
		Zee5WEBPWASanityBusinessLogic.trayTitleAndContentValidationWithApiDataMovie("Premium", "premiumcontents");
	}


	@Test(priority = 12)
	@Parameters({ "userType" })
	public void MusicPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.landingPagesValidation("Music");
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("Music");
		Zee5WEBPWASanityBusinessLogic.musicPageTrayTitleAndContentValidationWithApiData("Music", "music", userType);
		Zee5WEBPWASanityBusinessLogic.musicPageValidation("Music", userType,
				"Kalede Hode Naanu - Chambal | Sathish Ninasam | Sonu Gowda");
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void TVShowsPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		//update
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("TV Shows");
		Zee5WEBPWASanityBusinessLogic.ShowsValidationWeb(userType);
	}


	@Test(priority = 14)
	@Parameters({ "userType" })
	public void NewsPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.mandatoryRegistrationPopUp(userType);
		Zee5WEBPWASanityBusinessLogic.newsPageValidation("News");
		Zee5WEBPWASanityBusinessLogic.mandatoryRegistrationPopUp(userType);
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("News");
		Zee5WEBPWASanityBusinessLogic.trayTitleAndContentValidationWithApiDataNews("News", "news");
	}


	@Test(priority = 15)
	@Parameters({ "userType" })
	public void WebSeriesPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Zee5OriginalsScreen(userType, "Web Series");
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("Web Series");
	}


	@Test(priority = 16)
	@Parameters({ "userType" })
	public void SearchPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		// Sanity
		Zee5WEBPWASanityBusinessLogic.SearchResultsScreen(userType);
		// Smoke : sushma
		String liveContentName = Zee5WEBPWASanityBusinessLogic.fetchLiveContent();
		Zee5WEBPWASanityBusinessLogic.landingOnSearchScreen();
		Zee5WEBPWASanityBusinessLogic.searchResultScreen("Paaru");
		Zee5WEBPWASanityBusinessLogic.liveTv(liveContentName);
		Zee5WEBPWASanityBusinessLogic.navigationToConsumptionScreenThroughTrendingSearches(userType);
	}


	@Test(priority = 17)
	@Parameters({ "userType" })
	public void NewSubscriptionJourney(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		// Sanity
		Zee5WEBPWASanityBusinessLogic.SubscriptionPopupScenarios(userType);
	}

	
	@Test(priority = 18)
	@Parameters({ "userType" })
	public void UserActions(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		if (userType.equals("Guest")) {
			// Zee5PWASanityBusinessLogic.validateDisplayLanguagePopup();
			Zee5WEBPWASanityBusinessLogic.mandatoryRegistrationPopUp(userType);
			Zee5WEBPWASanityBusinessLogic.UserActionGuestUser(userType);
		} else if (userType.equals("NonSubscribedUser")) {
			Zee5WEBPWASanityBusinessLogic.ContinueWatching();
			Zee5WEBPWASanityBusinessLogic.UserActionLoggedInUser(userType);
		} else {
			Zee5WEBPWASanityBusinessLogic.ContinueWatching();
			//Zee5WEBPWASanityBusinessLogic.MyReminder();
			Zee5WEBPWASanityBusinessLogic.MyWatchlistSubscribedUser();
		}
	}


	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void MenuOrSetting(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.MenuOrSettingScenarios(userType);
	}


	@Test(priority = 19)
	@Parameters({ "userType" })
	public void LanguageSettings(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.LanguageModule(userType);
		Zee5WEBPWASanityBusinessLogic.ContentAndDisplayLanguage(userType);		
	}


	@Test(priority = 20)
	@Parameters({ "userType" })
	public void RecommendationTalamoos(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.verificationOfRecoTalamoosWeb(userType);
	}


	@Test(priority = 21)	  
	@Parameters({ "userType" }) 
	public void LiveTVPage(String userType) throws Exception {
		// SANITY 
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.liveLandingPage(userType);
		Zee5WEBPWASanityBusinessLogic.live();
		//Zee5WEBPWASanityBusinessLogic.premiumPopUp();
		Zee5WEBPWASanityBusinessLogic.ChannelGuide(userType); 
		// SMOKE
		Zee5WEBPWASanityBusinessLogic.verifyLiveTvAndChannelGuideScreen();
		Zee5WEBPWASanityBusinessLogic.LiveTVLinkValidation(userType, "Live TV");
		Zee5WEBPWASanityBusinessLogic.InAlphabeticalOrder();
	}


	@Test(priority = 22)
	@Parameters({ "userType" })
	public void Player(String userType) throws Exception {
		// SANITY
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Kaltura(userType);
		// content
		Zee5WEBPWASanityBusinessLogic.checkDurationInLivetv(userType);
		Zee5WEBPWASanityBusinessLogic.checkDurationandProgressVideo(userType);
		// SMOKE
		Zee5WEBPWASanityBusinessLogic.ValidatingPlayer(userType);
	}


	@Test(priority = 23)
	@Parameters({ "browserType", "url", "userType", "consumptionsEpisode", "consumptionsShow",
		"consumptionsFreeContent", "consumptionsPremiumContent" })
	public void ContentDetailsPage(String browser, String url, String userType,
			String consumptionsEpisode, String consumptionsShow, String consumptionsFreeContent,
			String consumptionsPremiumContent) throws Exception {
		// SANITY
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.contentDetailsValidation(userType);
		// SMOKE : Tanisha
		Zee5WEBPWASanityBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Episode", consumptionsEpisode);
		Zee5WEBPWASanityBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Live TV", "");
		Zee5WEBPWASanityBusinessLogic.mandatoryRegistrationPopUp(userType);
		Zee5WEBPWASanityBusinessLogic.verifyNoSubscriptionPopupForFreeContent(userType, "Ramayana "); // changed_6th
		Zee5WEBPWASanityBusinessLogic.verifySubscriptionPopupForPremiumContent(userType, consumptionsPremiumContent);
		Zee5WEBPWASanityBusinessLogic.verifyCTAandMetaDataInDetailsAndConsumption(consumptionsShow);
	}


	@Test(priority = 24)
	@Parameters({ "userType" })
	public void Carousel(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		// SANITY
		Zee5WEBPWASanityBusinessLogic.ValidatingWebPwaCarousalinalltabs(userType);
		// SMOKE
		// auto rotating
		Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("Home");
		Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("Movies");
		// Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("Free Movies");
		Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("TV Shows");
		Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("Premium");
		Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("Play");
		Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("Kids");
		// Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("Stories");
		// Zee5WEBPWASanityBusinessLogic.verifyAutoroatingOnCarousel("ZEE5 Originals");
		// play icon functionality
		Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("Web Series");
		Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("Kids");
		Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("Premium");
		Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("Home");
		Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("TV Shows");
		// Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("Free Movies");
		Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("Movies");
		Zee5WEBPWASanityBusinessLogic.verifyPlayIconFunctionality("Home");
		// premium icon functionality
		Zee5WEBPWASanityBusinessLogic.verifyPremiumIconFunctionality("Home", userType);
		Zee5WEBPWASanityBusinessLogic.verifyPremiumIconFunctionality("Premium", userType);
		Zee5WEBPWASanityBusinessLogic.verifyPremiumIconFunctionality("Movies", userType);
		Zee5WEBPWASanityBusinessLogic.verifyPremiumIconFunctionality("Web Series", userType);
		// left right functionality
		Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Home");
		Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Movies");
		// Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Free Movies");
		Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("TV Shows");
		Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Premium");
		Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Play");
		Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Kids");
		// Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Stories");
		Zee5WEBPWASanityBusinessLogic.verifyLeftRightFunctionality("Web Series");
		// zee5WebBusinessLogic.verifyLeftRightFunctionality("News");
		// metadata
		String languageSmallText = Zee5WEBPWASanityBusinessLogic.allSelectedLanguagesWEB();
		Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Web Series", "zeeoriginals", languageSmallText);
		// Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Stories", "stories", "");
		Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Kids", "kids", languageSmallText);
		Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Play", "play", languageSmallText);
		Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Premium", "premiumcontents", languageSmallText);
		Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("TV Shows", "tvshows", languageSmallText);
		// Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Free Movies", "freemovies", languageSmallText);
		Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Movies", "movies", languageSmallText);
		Zee5WEBPWASanityBusinessLogic.verifyMetadataOnCarousel("Home", "home", languageSmallText);
		// zee5WebBusinessLogic.verifyMetadataOnNews("News", "news", languageSmallText);

		Zee5WEBPWASanityBusinessLogic.WatchCTAvalidation(userType, "Movies");
		Zee5WEBPWASanityBusinessLogic.WatchCTAvalidation(userType, "Home");
		Zee5WEBPWASanityBusinessLogic.WatchCTAvalidation(userType, "TV Shows");
		Zee5WEBPWASanityBusinessLogic.WatchCTAvalidation(userType, "Web Series");
		Zee5WEBPWASanityBusinessLogic.WatchCTAvalidation(userType, "News");
		Zee5WEBPWASanityBusinessLogic.WatchCTAvalidation(userType, "Premium");
		Zee5WEBPWASanityBusinessLogic.CarouselSubscriptionPage(userType);
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void Club(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.landingPagesValidationclub("Club");
		Zee5WEBPWASanityBusinessLogic.trayTitleAndContentValidationWithApiDataClub("Club", "club");
		Zee5WEBPWASanityBusinessLogic.landingPagesTrailerAndPopUpValidationClub(userType, "Club");
	}

	@Test(priority = 25)
	@Parameters({ "userType" })
	public void CTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.VerifyCTA(userType);
	}

	@Test(priority = 26)
	@Parameters({ "userType" })
	public void EduauraaLandingPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.validatingEduaraa(userType ,"Kids");
		Zee5WEBPWASanityBusinessLogic.validatingclaimCTA(userType ,"Kids");
	}

	@Test(priority = 27)
	@Parameters({ "userType" })
	public void ZeePlexPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.zeeplex(userType ,"ZEEPLEX");
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void ConvivaVerification(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.ConvivaVerification(userType);
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void TasksImplementation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.defects(userType, "ZEEPLEX");
		Zee5WEBPWASanityBusinessLogic.TasksAndDefectssprint57(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2Sprint58(userType);
		Zee5WEBPWASanityBusinessLogic.TasksAndDefects(userType);
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void InSprintAutomation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Sprint58(userType);
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void TestSprint71Automation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.removeMinutelyContent();
		Zee5WEBPWASanityBusinessLogic.GrievanceRedressalOption();
		Zee5WEBPWASanityBusinessLogic.LiveTvRailContenttypeCheck(userType);
	}

	@Test(priority = 28)
	@Parameters({ "userType" })
	public void PlayPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.collectionDescriptionShowArrowbutton("Play");
		Zee5WEBPWASanityBusinessLogic.PlayPageValidation(userType, "Play");
		Zee5WEBPWASanityBusinessLogic.landingpagePropertiesValidation(userType, "Play");
	}

	@Test(priority = 29)
	@Parameters({ "userType" })
	public void KidsPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.KidsPageValidation(userType, "Kids");
	}

	@Test(priority = 30)
	@Parameters({ "userType" })
	public void VideoPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.VideosPageValidation(userType, "Videos");
	}

	@Test(priority = 31)
	@Parameters({ "userType" })
	public void ChannelsLandingPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.ChannelPageValidationAndChromeCast(userType, "Channels");
	}
	
	@Test(priority = 32)
	@Parameters({ "userType" })
	public void ArticlesPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.ArticlesPageValidation(userType, "Articles");
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.ToolTipValidation(userType);	
	}
	
	@Test(priority = 33)
	@Parameters({ "userType" })
	public void Trailer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.Trailer(userType);
	}

	@Test(priority = 34)
	@Parameters({ "userType" })
	public void HeaderMenu(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.HeaderMenu(userType);		
	}
	
	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void ContentAndDisplayLanguage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.ContentAndDisplayLanguage(userType);
	}

	@Test(priority = 35)
	@Parameters({ "userType" })
	public void AgeRatingAndContentDescription(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.AgeRatingAndContentDescriptionForDifferentcontents(userType);
		Zee5WEBPWASanityBusinessLogic.ContentDescriptorDisplayValidation(userType);
		Zee5WEBPWASanityBusinessLogic.ContentDescriptionNotDisplayed(userType);
		Zee5WEBPWASanityBusinessLogic.ContentDescriptorPostPlayerkill(userType);
		Zee5WEBPWASanityBusinessLogic.ContentDescriptorLandscapeOrientatonValidation(userType);
		Zee5WEBPWASanityBusinessLogic.ContentDescriptorForNewsAndLiveContent(userType);
		Zee5WEBPWASanityBusinessLogic.VerifyContentDescriptionNotDisplayedWhenMouseOver(userType);
		Zee5WEBPWASanityBusinessLogic.VerifyContentDescriptorForAds(userType);
		Zee5WEBPWASanityBusinessLogic.ContentDescriptorPlayerinterruption(userType);
		Zee5WEBPWASanityBusinessLogic.verifyAgeRating_OnPlayer_And_BelowPlayer(userType);
		Zee5WEBPWASanityBusinessLogic.VerifyAgeRatedIfCDisEmpty(userType);
		Zee5WEBPWASanityBusinessLogic.VerifyContentDescriptorAfterAdFinishes(userType);
	}	

	@Test(priority = 36)
	@Parameters({ "userType" })
	public void MouseHoverAction(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.MouseOverModule(userType);
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void CatchUp(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.catchUp(userType);
	}
	
	@Test(priority = 37)
	@Parameters({ "userType" })
	public void HamburgerMenu(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.Hamburgermenu(userType);
	}

	@Test(priority = 38)
	@Parameters({ "userType" })
	public void ZeelogoImg(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.PWAVerifyImageAssetForZee5Logo(userType);
	}
	
	@AfterClass
	public void tearDown() {
		Zee5WEBPWASanityBusinessLogic.tearDown();
	}
}