package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAHomePage {

	// Zee logo
	public static By objZeeLogo = By.xpath("//*[contains(@class,'zeeLogo')]//img[@title='ZEE5 Logo']");

	// Subscribe Btn
	public static By objSubscribeBtn = By.xpath("//*[@class='subscribeBtn noSelect']");

	// Search Btn
	public static By objSearchBtn = By.xpath("//*[contains(@class,'searchBtn')]");

	// Hamburger menu
	public static By objHamburgerMenu = By.xpath("(//*[text()='Open Menu'])[1]");

	public static By objverifyNumberPopup = By.xpath("//div[@class='formHeader' and text()='Verify Mobile Number']");
	public static By objLiveTVtab = By.xpath("//a[contains(text(),'Live TV')]");

	// h3[@class="cardTitle cardTitleMultiline"]

	// Select tray by text
	public static By objLanguageTextEnglishGetvalue(String trayName) {
		return By.xpath("//*[contains(text(),'" + trayName + "')]");
	}

	public static By objOverlayTrayActive(String text) {
		return By.xpath(" //span[@class='noSelect active' and contains(text(),'" + text + "')]");
	}

	public static By objTabContBar = By.xpath("//div[@class='navMenuWrapper ']");

	public static By objDownloadIcon = By
			.xpath("//div[contains(@class,'noSelect addHomeScreen iconInitialLoad-ic_add_home')]");

	// Device Pin
	public static By objDevicePin1 = By.xpath("//input[@id='parentLockId1']");
	public static By objDevicePin2 = By.xpath("//input[@id='parentLockId2']");
	public static By objDevicePin3 = By.xpath("//input[@id='parentLockId3']");
	public static By objDevicePin4 = By.xpath("//input[@id='parentLockId4']");

//	Manas Nath

	// Subscription page
	public static By objSubscriptionPage = By.xpath("//div[@class =\"subscriptionAndPaymentWrapper\"]");

	public static By objContTitleTextCarousel(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle') and contains(text(),\""
						+ text + "\")]");
	}

	public static By objContTitleWithPlayIconCarousel(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle') and contains(text(),\""
						+ text + "\")]//parent::*//following-sibling::div//div[@class='playIcon']");
	}

	// Carousel Banner
	public static By objCarouselBanner = By.xpath("//div[@class=\"carouselMain\"]");

	// Profile Menu
	public static By objProfileMenu = By.xpath("//div[contains(@class,'bm-icon profileMenuBtn')]");

	public static By objSearchField = By.xpath("//input[@type='text']");

	// Continue in Display/Content language popup
	public static By objContinueDisplayContentLangPopup = By
			.xpath("//div[contains(@class,'popupBtn noSelect') and text()='Continue']");

	public static By objLanguageBtn = By.xpath("//div[@id='languageBtn']");

	
	// Display Language popup Title
	public static By objDisplayLanguagePopupTitle = By.xpath(
			"//div[contains(@class, 'popupContent langFilterPopupWrapper')]//div[contains(text(), 'Display Language')]");

	// Display Language Popup English Option
	public static By objDisplayLanguagePopupOption(String languageOption) {
		return By.xpath("//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(text(), '"
				+ languageOption + "')][2]");
	}

	// Display Language Popup Continue Button
	public static By objDisplayLanguageContinueButton = By
			.xpath("//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(text(), 'Continue')]");
	public static By objWEBWatchTrailerBtn = By.xpath("//p[contains(text(),'Watch Trailer')]");
	public static By objWEBCarouselTitle = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'legendTitle ')]");
	public static By objWEBPlayBtn = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'playIcon')]");

	// Display Language Popup English Option
	public static By objContentLanguagePopupSelectedOption(String languageOption) {
		return By.xpath(
				"//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(@class, 'checkboxWrap checkedHighlight')]//*[contains(text(), '"
						+ languageOption + "') and @class='commonName']");
	}

	// Display Language Popup English Option
	public static By objContentLanguagePopupUnSelectedOption(String languageOption) {
		return By.xpath("//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(text(), '"
				+ languageOption + "') and @class='commonName']");
	}

	public static By objContTitleTextCarouselWeb(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'legendTitle') and contains(text(),'"
						+ text + "')]");
	}

	public static By objMoreMenuTabs(String tabName) {
		return By.xpath(
				"//*[@class='moreMenuBtn iconInitialLoad-ic_Bento']/following-sibling::*/child::*/child::*[text()='"
						+ tabName + "']");
	}

	public static By objMoreMenuIcon = By.xpath("//*[@class='moreMenuBtn iconInitialLoad-ic_Bento']");

	public static By objFirstAssetTrendingOnZEE5 = By.xpath(
			"//h2[text()='Trending on ZEE5']//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']");

//		====================================== SANITY ===================================

//		MandatoryRegistration
	public static By objSearchResult = By.xpath("//*[@title='Ramayana']");
	public static By objPopUpMobileField = By.xpath("//input[@id='textField']");
	public static By objPopUpPasswordField = By.xpath("//input[@name='inputPassword']");

//	public static By objPopUpProceedButton = By.xpath("//div[@class='registerLoginContainer']//button[@class='noSelect buttonGradient null']");
	public static By objOtpPopUp = By.xpath("//div[@class='poupWrapper']");

//		BHAVANA SHOWS MODULE

	public static By objAllowNotification = By
			.xpath("//*[@text=concat('Click on the lock in the URL bar, go to') and @nodeName='DIV']");

	public static By objcloseonAllowNotification = By
			.xpath("//*[@nodeName='BUTTON' and [@text=concat('Click on the lock in the URL bar, go to ']]");
	public static By objGooglePlay = By.xpath("//a[@class='gb_ue gb_vc gb_se']");
	public static By objiOS = By.xpath("//a[@id='ac-gn-firstfocus-small']");

	// --------------------------------------------------
	public static By objTumbnailTitle(String str, int i) {
		return By.xpath("((((//div[.='" + str
				+ "']//parent::*//parent::*//parent::*)//child::*[@class='movieTrayWrapper'])//child::*[1][@class='noSelect clickWrapper'])["
				+ i + "])//child::*[@class='content']//child::*");
	}

	public static By objIsPremiumTumbnail(String str, int i) {
		return By.xpath("((((//div[.='" + str
				+ "']//parent::*//parent::*//parent::*)//child::*[@class='movieTrayWrapper'])//child::*[1][@class='noSelect clickWrapper'])["
				+ i + "])//child::*[@class='cardPremiumContent']");
	}

	public static By objPlaybackMovieTitle(String str) {
		return By.xpath("//div[@class='consumptionMetaDiv']//h1[.='" + str + "']");
	}

	public static By objLIVETVIsPremiumTumbnail(String str, int i) {
		return By.xpath("((((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
				+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper'])//figure//a[@class='noSelect content'])//div[@class='cardPremiumContent'])["
				+ i + "]");
	}

	public static By objPlaybackLIVETVTitle(String str) {
		return By.xpath("//div[@class='channelConsumptionMetaDiv']//h2[.='" + str + "']");
	}

	public static By objPlaybackLIVETVTitle1 = By.xpath("//div[@class='channelConsumptionMetaDiv']//h2");

	// -------------------------------------------------
	public static By objShowTumbnailTitle(String str, int i) {
		return By.xpath("(((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='latestEpisodeTrayWrapper']//child::*//div[@class='noSelect clickWrapper'])["
				+ i + "]//figure//div)//img");
	}

	public static By objShowIsPremiumTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='latestEpisodeTrayWrapper']//child::*//div[@class='noSelect clickWrapper'])["
				+ i + "]//div[@class='cardPremiumContent']");
	}

	public static By objPlaybackShowTitle(String str) {
		return By.xpath("//div[@class='consumptionMetaDiv']//h1[.='" + str + "']");
	}

	// -------------------------------------------------
	public static By objVideoTumbnailTitle(String str, int i) {
		return By.xpath("(((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//*[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "])//figure//img");
	}

	public static By objVideoIsPremiumTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//*[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "]//div[@class='cardPremiumContent']");
	}

	public static By toTray(String str) {
		return By.xpath("//div[contains(@class,'trayContentWrap')]//*[contains(text(),'" + str + "')]");
	}

	// ------------------------------------------------------
	public static By objspecificTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "]");
	}

	public static By objspecificTumbnail1(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='continueWatchCard card positionRelative marginRight zoomCardHover minutelyUrl']//a)["
				+ i + "]");
	}

	public static By objspecificTumbnail2(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='"+str+"'])//parent::*//parent::*//div[@class='slick-slider continueWatchTray slick-initialized']//div[@class='slick-track']//div//div//a)["+i+"]");
	}
	
	public static By objtumnails(String str) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])//figure");
	}

	// ------------------------------------------------------
	public static By objKalGetPremium = By.xpath("//a[@class='premiumBtn']");
	public static By objKalGetTitle = By.xpath("//h1[@class='title']");
	public static By objKalGetPremiumPlayicon = By
			.xpath("//div[@class='iconsWrap getPremiumBtn']//child::*[@class='playIcon']");
	public static By objKalKalturaPlayer = By.xpath("//div[@class='kaltura-player-container']");
	public static By objKalconsumptionMetaDiv = By.xpath("//div[@class='consumptionMetaDiv']//h1");
	public static By objKalconsumptionMetainfo = By.xpath("(//div[@class='metaInfo']//p)[1]");
	public static By objKalGetFirstEpisode = By
			.xpath("//div[@class='showDetailIcon']//p[contains(text(),'Watch First Episode')]");
	public static By objKalGetFirstEpisodePlayicon = By
			.xpath("//div[@class='showDetailIcon']//child::*[@class='playIcon']");
	public static By objKalLivetvPlaying = By.xpath("//div[@class='channelConsumptionMetaDiv']//h2");
	public static By objKalLivetvChannel = By.xpath("//div[@class='channelConsumptionMetaDiv']//h1");

//		CONTENT DETAILS MODULE
	public static By objSeletedTab = By.xpath("//div[contains(@class, 'navMenuWrapper')]//a[contains(@class,'noSelect active')]");

//		TANISHA
	// Minutely
	public static By objMinutelyContentNonPremium = By.xpath(
			"//div[contains(@class,'latestEpisodeTray')]//*[contains(@class,'clickWrapper') and not(.//div[@class='cardPremiumContent'])]");

	public static By objContentNonPremium = By
			.xpath("//div[contains(@class,'clickWrapper') and not(.//div[@class='cardPremiumContent'])]");

	public static By objAllTabs = By
			.xpath("//div[contains(@class,'navMenuWrapper')]//div[contains(@class,'noSelect')]");

	public static By objPWANews = By.xpath("//div[contains(text(),'News')]");

//		SUSHMA SANITY LIVETV MODULE
//	public static By objBackToTopArrow = By.xpath("//div[@class='iconNavi-ic_arrow_back']");
	public static By objBackToTopArrow = By.xpath("//div[@class='iconOther-ic_arrow_back'or @class='iconNavi-ic_arrow_back']");

	// Footer section of home page

	// Download Apps text UI
	public static By objDownloadApps = By.xpath("//h3[contains(text(),'Download Apps')]");

	// Up Arrow
	public static By objUpArrow = By.xpath("//div[contains(@class, 'iconOther-ic_arrow_back')]");

	// Facebook page
	public static By objFacebookPage = By.xpath("(//*[@text='Log In'])[2]");

	// Help Center in Footer section
	public static By objHelp = By.xpath("//a[@class='noSelect'][contains(text(),'Help Center')]");
	// Help Center screen
	public static By objHelpScreen = By.xpath("//h2[contains(text(),'Help Center')]");

	// write to us
	public static By objwritetous = By.xpath("//img[contains(@class,'img1')]");

	// About Us in Footer Section
	public static By objAboutUsInFooterSection = By.xpath("//a[@class='noSelect'][contains(text(),'About Us')]");
	// AboutUsScreen
	public static By objAboutUs = By.xpath("//h1[contains(text(),'About Us')]");

	// Privacy Policy in Footer Section
	public static By objPrivacyPolicyInFooterSection = By
			.xpath("//a[@class='noSelect'][contains(text(),'Privacy Policy')]");
	// PrivacyPolicyScreen
	public static By objPrivacyPolicy = By.xpath("//h1[contains(text(),'Privacy Policy')]");
	// TermsOfUse in Footer Section
	public static By objTermsOfUseInfooterSection = By.xpath("//a[@class='noSelect'][contains(text(),'Terms of Use')]");
	// TermsOfUSeScreen
	public static By objTerms = By.xpath("//h1[contains(text(),'Terms of Use')]");

	// Home page continue watching tray
	public static By objContinueWatchingTray = By.xpath("//h2[contains(text(),'Continue Watching')]");

	public static By objActiveTab = By.xpath("//a[contains(@class,'noSelect active')]");

	public static By objAndroidPlayStoreIcon = By.xpath("//*[@class='playstoreIcon']//a[@class='noSelect content']");

	public static By objIoSAppStoreIcon = By.xpath("//*[@class='appStoreIcon']//a[@class='noSelect content']");

	public static By objSearchResultWeb = By.xpath("//div[@class='searchListingNewsWrap']//img[@title='Ramayana']");

	// Subscribe PopUp Home Page
	public static By objSubscripePopupHomePage = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[contains(@id,'adoric_smartbox_')]");
	// Subscribe PopUp close button Home Page
	public static By objSubscripePopupCloseButtonHomePage = By.xpath(
			"//div[(contains(@style,'display: block'))]//div[@class='adoric_element element-text editing closeLightboxButton']//child::*[@class='inner-element']");

	public static By objWhatWonderingPopUp = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[contains(@id,'adoric_smartbox_')]");

	public static By objWhatWonderingPopUpCloseIcon = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[@aria-label='close']");

	public static By objPlayiconAfterMouseHover = By
			.xpath(".//*[@class='noSelect btnIcon playBtnIcon']//child::*[@class='btnText']");

	public static By objShareiconAfterMouseHover = By
			.xpath(".//*[@class='shareCompIcon iconInitialLoad-ic_share']//child::*[@class='shareLabel']");

	public static By objWEBSubscribeBtn = By.xpath("//a[@class='subscribeBtn noSelect']");

	public static By objMoreMenuBtn = By.xpath("//div[@class='moreMenu']");

	public static By objWhatToWatchPopUp = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[contains(@id,'adoric_smartbox_')]");

	public static By objWhatToWatchCloseButton = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[@aria-label='close']");

	public static By objLanguage = By.xpath("//div[@id='languageBtn']");
	public static By objKannadaWEB = By.xpath("//span[contains(@class,'commonName')][contains(text(),'Kannada')]");
	public static By objEnglishWEB = By.xpath("//span[contains(text(),'English')]");

	public static By objGooglePlayLogo = By.xpath("//a[@title='Google Play Logo']");

	public static By objCarousel = By.xpath("//*[contains(@class,'heroBannerCarousel minutelyUrl')]");

	public static By objPageHighlighted(String PageName) {
		return By.xpath("//*[@class='noSelect active' and text()='" + PageName + "']");
	}

	public static By objLanguageChangeContentPopup() {
		return By.xpath("//div[@class='popupContent languageChangeContentPopup']");
	}

	public static By objLanguageChangeContentPopupCloseicon() {
		return By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	}

	public static By objLanguagePop = By.xpath("//div[@class='popupContent langFilterPopupWrapper']");

	// Subscribe Now
	public static By objSubscribeNow = By.xpath(
			"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'noSelect premiumBtn')][contains(text(),'Subscribe Now')]");

	public static By objTwitterFollowWeb = By.xpath("//*[text()='Follow']");

	public static By objAddToWatchlistButtonOnTrayContentCard(String trayName) {
		return By.xpath("(//*[contains(@class,'trayHeader')]//*[contains(text(),'" + trayName
				+ "')]//parent::*//following-sibling::*//*[contains(@class, 'noSelect btnIcon iconInitialLoad-ic_add_Watchlist')])[1]");
	}

	public static By objLoginRequiredPopUpHeader = By.xpath("//*[contains(text(), 'Login Required')]");

	public static By objPopupCloseicon() {
		return By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	}

	public static By objFirstContentCardOfTray(String trayName) {
		return By.xpath("(//*[contains(@class,'trayHeader')]//*[contains(text(),'" + trayName
				+ "')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'content')])[1]");
	}

	public static By objsearchcontent = By.xpath("//*[@title='Krishna Balram: The Warrier Princess']");

	public static By objPlayCarousel = By.xpath(
			"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'noSelect premiumBtn')][contains(text(),'Play')]");

	public static By objHoverMenu(String text) {
		return By.xpath("//a[contains(@class,'noSelect')][contains(text(),'" + text
				+ "')]//following-sibling::*[@class='megaMenu megaMenuCards cardTypetvshows']");
	}

	public static By objOverlayTray = By.xpath("//span[contains(text(),'Top Zee')]");

	public static By objOverlayTray2 = By.xpath("(//span[@class='noSelect ' and contains(text(),'Latest')])[1]");

	public static By objHomeInHambugerMenu = By
			.xpath("//*[contains(@class,'menuGroup')]//a[contains(@class,'active')]//div[contains(text(),'Home')]");

	// Get premium btn
	public static By objGetPremium = By.xpath("//*[text()='Get premium' or text()='Get Club']");

	public static By objWEBShowsPagePlayCarousel = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'carouselMain')]");

	// Android google play store
	public static By objGooglePlayStore = By.xpath("//*[contains(@text,'HiPi, News, Movies, TV Shows, Web Series')]");	
	
	public static By obj3xfasterPopUpNoThanks = By.xpath("//*[@text='NO THANKS\\n']");
	
	public static By objAdBanner = By.xpath("(//*[@class='adContainer'])[1]");
	
	public static By objCreateNewAccountPopUpClose = By.xpath("//div[@class='manCloseIcon']");
	
	//Check for the club tag
	public static By objClubTag = By.xpath("//*[contains(@class,'clubPackContent')]");
	
	public static By objStayTunedPopUpClose = By.xpath("//*[@class='aiq-1xYiw8']");
	
	public static By objRecoTray = By.xpath("//*[@class='trayHeader']//*[contains(text(),'Trending')]");
	
	public static By objAppInstallPopUpClose = By.xpath("(//div[@class='afb-close-accessibility-overlay'])[2]");
	
	// Copy right text
	public static By objCopyRightText = By.xpath("//div[contains(@class,'copyRightTxt')]");
	
	public static By objLearnWithEduauraaTray = By.xpath("//*[@class='titleLink' and text()='Learn with Eduauraa']");
	
	public static By objFirstItemLearnWithEduauraaTray = By.xpath("//*[@class='titleLink' and text()='Learn with Eduauraa']//ancestor::*[@class='trayContentWrap']//*[@class='slick-list']//*[@data-index='0']");
	
	public static By objPlayBtn = By.xpath("//div[contains(@class,'slick-slide slick-active')]//*[contains(@class,'playIcon')]");

	public static By objTitleTextCarousel(String text) {
		return By.xpath(
				"//*[contains(@class,'legendTitle') and contains(text(),'"+text +"')]");
	}
	
	public static By objClubContentCardFromTray = By.xpath("//div[@class='page-container']//div[@class='slick-list']//div[@data-minutelyurl and (.//div[contains(@class,'clubPackContent')])]");
	public static By objStoriesPageCardTitle = By.xpath("//h3[@class='cardTitle']");
	public static By objUpgradeBtnInHeader = By.xpath("//*[contains(@class,'headerContainer')]//span[text()='Upgrade']");
	public static By objJoyStickTag = By.xpath("//*[contains(@class,'cardJoystickContent')]");
	public static By objStoriesPageConsumptionsTitle = By.xpath("//*[@class='storyTitle']");
	
	public static By objtrayname(String text)
	{
		return By.xpath("//*[@class='trayHeader']//*[contains(text(),'"+text+"')]");
	}
	public static By objcontentcard=By.xpath("//div[@data-index='0']");

	public static By objClaimOffer =By.xpath("//button//span[text()='Claim Offer']");
	
	public static By objGetClubCta = By.xpath("//div[contains(@class,'slick-active')]//*[text()='Get Club']//parent::a");
	
	public static By objTrayName=By.xpath("//*[@class='trayHeader']//*[contains(text(),'Learn with Eduauraa')]");
	
	public static By objGetPremiumCta = By.xpath("//div[contains(@class,'slick-active')]//*[text()='Get premium']//parent::a");
	
	//public static By objPlayBtn = By.xpath("//div[contains(@class,'slick-slide slick-active')]//*[contains(@class,'playIcon')]");

	public static By objUpgradeBtn = By.xpath("//div[contains(@class,'slick-slide slick-active')]//*[contains(text(),'Upgrade')]");
	
	public static By objPopUpProceedButton = By.xpath("//button[contains(@class='noSelect buttonGradient')]");
	
	public static By objHomeBarText(String HomeBartitle) {
		return By.xpath("//a[contains(@class,'noSelect') and text()='" + HomeBartitle + "']");
	}
	
	public static By objRentforINR = By.xpath("(//div[@class='rentNowButton']//div//button)[1]");	
	
	public static By objRentforINRPopupRentforINRBtn = By.xpath("(//div[@class='plexBottomWrapper']//div//button)[1]");
	
	public static By objRestrictDescription = By.xpath("//div[@class='RestrictDesc']");
	
	public static By objRestrictContentNotnowBtn = By.xpath("//div[@class='btwrap']//div[@class='closeBtn']//div");
	public static By objPersonalizedNotificationPopup = By.xpath("//p[contains(text(),'Click Allow on the next prompt to subscribe to our push notifications')]");
	public static By objPersonalizedNotificationPopupNotNowBtn = By.xpath("((//p[contains(text(),'Click Allow on the next prompt to subscribe to our push notifications')]//parent::*//parent::*//parent::*)//div[2]//button)[1]");
	
	public static By objFacebookIcon = By.xpath("//a[contains(@class, 'noSelect facebookIcon')]");
	
	public static By objInstagramIcon = By.xpath("//a[contains(@class, 'noSelect instagramIcon')]");
	
	public static By objTwitterIcon = By.xpath("//a[contains(@class, 'noSelect twittercon')]");
	
	public static By objCarouselContentPremiumTag = By.xpath("(.//*[@class='cardJoystickContent cardPremiumContent'])[1]");
	
	public static By objNotNow = By.xpath(".//*[text()='Not Now']");
	
	public static By objcarouselContent(String carouselContentname) {
		return By.xpath("//div[@class='slick-slide slick-active slick-center slick-current']//following-sibling::*[text()=\""+carouselContentname+"\"]");
	}
	
	public static By objTrayContentIcon(String trayName,String contentTitle) {
		return By.xpath("(//div[@class='trayHeader']//h2[.=\""+trayName+"\"])//parent::*//parent::*//following-sibling::*//child::*[@title=\""+contentTitle+"\"]");
	}
	
	public static By objTitle = By.xpath("//span[@class='html-tag' and contains(text(),'title')]//ancestor::td");
	public static By objOgTitle = By.xpath("//span[.='og:title']//following-sibling::span//following-sibling::span");
	public static By objOgDesc = By.xpath("//span[.='og:description']//following-sibling::span//following-sibling::span");
	public static By objTwitterOgTitle = By.xpath("//span[.='twitter:title']//following-sibling::span//following-sibling::span");
	public static By objTwitterOgDesc = By.xpath("//span[.='twitter:description']//following-sibling::span//following-sibling::span");
	public static By objMyProfileBreadCrumb = By.xpath("//ul[contains(@class,'breadcrumbContainer')]//span[.='Home']//parent::li//following-sibling::li//span[.='My Profile']");
	public static By objBreadCrumb (int number) {
		return By.xpath("//ul[contains(@class,'breadcrumbContainer')]//li["+number+"]//span[1]");
	}
	public static By objOpenProfile = By.xpath("//div[contains(@class,'bm-icon profileMenuBtn')]//parent::*//button[.='Open Menu']");
	public static By objOpenProfileIcon = By.xpath("(//div[@class='iconInitialLoad-ic_profile profilePic'])[2]");
	public static By objViewAllBreadCrumb (String tab,String trayTitle) {
		return By.xpath("//ul[contains(@class,'breadcrumbContainer')]//span[.='"+tab+"']//parent::li//following-sibling::li//span[.='"+trayTitle+"' or .='"+trayTitle.toLowerCase()+"']");
	}
	public static By objMovieBreadCrumb (String movieTitle) {
		return By.xpath("//ul[contains(@class,'breadcrumbContainer')]//span[.='Home']//parent::li//following-sibling::li//span[.='Movies']//parent::li//following-sibling::li//span[.='"+movieTitle+"']");
	}
	public static By objShowDetailsBreadCrumb (String showTitle) {
		return By.xpath("//ul[contains(@class,'breadcrumbContainer')]//span[.='Home']//parent::li//following-sibling::li//span[.='Shows']//parent::li//following-sibling::li//span[.='"+showTitle+"']");
	}
	public static By objChannelsBreadCrumb (String channelTitle) {
		return By.xpath("//ul[contains(@class,'breadcrumbContainer')]//span[.='Home']//parent::li//following-sibling::li//span[.='Channels']//parent::li//following-sibling::li//span[.='"+channelTitle+"']");
	}
	public static By objMySubscriptionsBreadCrumb = By.xpath("//ul[contains(@class,'breadcrumbContainer')]//span[.='Home']//parent::li//following-sibling::li//span[.='My Profile']//parent::li//following-sibling::li//span[.='Subscriptions']");
	public static By objLivePlayerBreadCrumb = By.xpath("//ul[contains(@class,'breadcrumbContainer')]//span[.='Home']//parent::li//following-sibling::li//span[.='Live TV']");
	public static By objSubtitleButton = By.xpath("//div[@title='Subtitles']//button");
	public static By objSubtitleEnglish = By.xpath("//div[@class='subMenu' and .='English']");
	public static By objSubtitleEnglishSelected = By.xpath("//div[contains(@class,'tickMark')]//div[@class='subMenu' and .='English']");
	public static By objPromotionalBanner = By.xpath("//div[@class='slick-slide slick-active slick-current']//img");
	public static By objPromotionalBannerCarouselDots = By.xpath("//div[@class='carouselDots']");
	public static By objPromotionalBannerCarouselDots (int dotCount) {
		return By.xpath("(//div[@class='carouselDots'])["+dotCount+"]");
	}
	public static By objZeePlexDisclaimer = By.xpath("//div[@class='plexZee5SubscriptionText']");
	public static By objMarathiWEB = By.xpath("//span[contains(text(),'Marathi')]");
	public static By objTrayBesidePlayer = By.xpath("//div[@class='recommendCol']//h2");
	public static By objMarathi = By.xpath("//input[@value='mr']//parent::*//span");
	public static By objPremiumIcon = By.xpath("//*[contains(@class,'cardPremiumContent')]//div[@class[contains(.,'cardPremiunIconContainer') and not(contains(.,'clubPackContent'))]]");
	public static By objClubIcon = By.xpath("//*[contains(@class,'cardPremiumContent')]//div[@class[contains(.,'cardPremiunIconContainer') and contains(.,'clubPackContent')]]");
	// Continue in Display/Content language popup
	public static By objApplyContentLangPopup = By.xpath("//div[contains(@class,'popupBtn noSelect') and text()='APPLY']");
	
	public static By objInnerMegaMenuFirstCardAnchor(String tab) {
		return By.xpath("(//a[contains(@class,'noSelect') and text()='" + tab + "']//following-sibling::div[contains(@class,'megaMenuCards')]//a)[1]");
	}
	
	public static By objInnerMegaMenuFirstCardImg(String tab) {
		return By.xpath("(//a[contains(@class,'noSelect') and text()='" + tab + "']//following-sibling::div[contains(@class,'megaMenuCards')]//a//img)[1]");
	}
	
	public static By objNextIcon(String trayName) {
		return By.xpath("(//div[@class='trayHeader']//h2[.=\""+trayName+"\"])//parent::*//parent::*//following-sibling::*//child::*//button[text()=\"Next\"]");
	}
	
	public static By objLogoutIcon = By.xpath(".//*[@class='noSelect menuItem ' and text()='Logout']");
	
	public static By objOopsNoInfo = By.xpath("//h2[@class='fallbackTitle' and text()='Oops! No Information available']");
	public static By objZeeLogoInForgotPasswordPage = By.xpath("//img[@alt='Zee5Logo']");
	
	public static By objMastheadCarouselCurrentContentWeb = By.xpath(
			"(//div[contains(@class,'heroBannerCarousel')]//div[@class='slick-slide slick-center slick-cloned']//*[contains(@class,'content')]//img)[2]");

	public static By objHindiWEB=By.xpath("//span[contains(@class,'commonName')][contains(text(),'Hindi')]");

//	public static By objContTitleOnCarousel = By
//			.xpath("//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'legendTitle')]");
	public static By objCarouselCardForDataContentID (int carouselDot) {
		return By.xpath("//div[contains(@class,'BannerCarousel')]//div[@data-index='"+carouselDot+"']//div[@class='carouselMain']");
	}
	
	public static By objCarouselCardForClick (int carouselDot) {
		return By.xpath("//div[contains(@class,'BannerCarousel')]//div[@data-index='"+carouselDot+"']//div[@class='carouselMain']//figure//img");
	}
	public static By objSubscribeBtnTopHeader = By
			.xpath("//div[contains(@class,'iconInitialLoad-ic_premium')]");
	
	public static By objPopUpClose = By.xpath("//div[contains(@class, 'drowerCloseIcon iconInitialLoad-ic_close')]"); 

	public static By objTamilWEB = By.xpath("//span[contains(text(),'Tamil')]");
	
	// Masthead carousel current content
	public static By objMastheadCarouselCurrentContent = By.xpath(
				"//div[contains(@class,'heroBannerCarousel')]//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'content')]//img");
	//Home Tab display Language independent
	public static By objRegLangHomeTab =  By.xpath("//*[@class='navMenuWrapper ']//li//a[@href='/']");
	//Play Icon with Get Premium CTA on carousel
	public static By objPlayIconWithGetPremiumCTAOnCarousel = By.xpath("//*[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'playIcon') and following-sibling::*//*[contains(text(),'Get premium')]]");
	
	public static By objMyWatchListIcon = By.xpath(".//*[@class='bm-item mainMenu']//child::*[text()='My Watchlist']");
	
	public static By objHighlightedTab(String tabname) {
		return By.xpath("(//a[contains(@class,'noSelect') and text()=\""+tabname+"\"])[2]");
	}
	
	public static By objGetPremiumGetClubButton = By.xpath("//div[contains(@class,'slick-active')]//*[text()='Buy Plan']//parent::a");
	
	public static By objInstagramPage = By.xpath("(//button[contains(text(),'Follow')])[1]");
	
	public static By objcontactus = By.xpath("//*[text()='Contact Us']");

	//click on By Plan
	public static By objByPlan = By.xpath("//a[@class='subscribeBtn noSelect' and @href='/myaccount/subscription']");
	
	public static By objTabName1(String tabName) {
		return By.xpath("(//a[contains(@class,'noSelect text_news_active') and text()='" + tabName + "'])[3]");
	}
	
	// Home Page
	public static By objHomePage = By.xpath("(//*[text()='Home'])[1]");
	// Click on Rent
	public static By objRent = By.xpath("(//a[@class='noSelect  ' and @href='/zee-plex-movies-on-rent'])[1]");
	// CLICK Trailer
	public static By objTrailer = By.xpath("(//span[contains(text(),'Trailer')])[1]");
	// movies tab
	public static By objMoviesTab = By.xpath("(//*[text()='Home'])[1]");
	
	public static By objMandatoryRegPopUpCloseIcon = By.xpath("//*[contains(@class,'manCloseIcon')]");
	
	public static By objMandatoryRegPopUp = By.xpath("//*[contains(@class,'mandatoryRegisterPopup')]");
	
	public static By objAllow = By.xpath(".//*[text()='Allow']");
	public static By objAllowCloseButton = By.xpath("//*[@class='aiq-1xYiw8']");

 	public static By objGetPremiumWeb = By.xpath("//div[contains(@class,'slick-slide slick-active slick-center slick-current')]//div//span[contains(text(),'Get premium') or contains(text(), 'Buy Plan') or contains(text(), 'Upgrade')]");
 	
 	public static By objWEBGetPremium = By
			.xpath("//div[@class='slick-slide slick-active slick-center slick-current']//a[1]//span[1]");
 	
 	public static By objComingSoonText = By.xpath("//*[@class='comingSoonText' and text()='Coming Soon!']");
 	
 	public static By objBuyPlanCTA = By.xpath(".//*[@class='headerRight']//span[text()='Buy Plan']");
 	
 	public static By objPlayIconWithGetPremiumCTAOnCarousel1 = By.xpath("//*[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'playIcon') and following-sibling::*//*[contains(text(),'Buy Plan')]]");

 	//Crown Symble
 	public static By objCrownSymble = By.xpath("//*[@class='iconInitialLoad-ic_premium']");
 	
 	public static By objTabCountList = By.xpath("//div[@class='navMenuWrapper ']//li");
 	
 	//click on By Plan MPWA
	public static By objByPlanMPWA = By.xpath("(//*[@href='/myaccount/subscription'])[1]");
	
	//Upgrade CTA
	public static By objUpgradeCTA = By.xpath("//*[@text='Upgrade']");
	public static By objTamilmWEB = By.xpath("//span[contains(text(),'Tamil')]");
	public static By objHindimWEB=By.xpath("//span[contains(@class,'commonName')][contains(text(),'இந்தி')]");
 	
	public static By objPageHighlightedanyLanguage(String langtext) {
		return By.xpath("//span[contains(@class,'commonName')][contains(text(),'" + langtext + "')]");
	}

	
	public static By objHomePageLoginbtn= By.xpath("//a[contains(text(), 'Login')]");
 	
	
	public static By objDisplayLanguage (String Lang) {
		return By.xpath("//span[contains(text(),'"+Lang+"')]");
	}

	public static By objPlaybtnCarouselBanner = By.xpath("(//*[contains(@class,'slick-current')]//*[@class='playIcon'])[1]");
	
	public static By objForgotPasswordLinkTxt = By.xpath("//*[@class='forgotPassword']");
	public static By objCreateNewPasswordField = By.xpath("//*[@text='Create New Password']");

	public static By objConfirmNewPasswordtxtField = By.xpath("//*[@text='Confirm New Password']");
	public static By objSetNewPasswordCTA = By.xpath("//*[text()='Set New Password']");

	public static By objCountryCodeDropDownIndia = By.xpath("//*[contains(@class,'react-dropdown-select-type-single')]");
	
	public static By objzeeplexHowitWorks=By.xpath("//*[@class='plexLearnMoreLink']");
	
	public static By objPremiumTag = By.xpath("//*[@class='cardPremiumContent sameEpisodeCardContent']");

	public static By objKalturaGetPremium = By.xpath("//div[@class='cardPremiumContent']");
	public static By objKalturaPremiumContentImg=By.xpath("//div[contains(@class,'clickWrapper') and (.//div[@class='cardPremiumContent'])]//h3[@class='cardTitle']//parent::*//img");
	public static By objKalturaGetPremiumCardTitle=By.xpath("//div[contains(@class,'clickWrapper') and (.//div[@class='cardPremiumContent'])]//h3[@class='cardTitle']");
	public static By objLIVETvTumbnailTitle(String str, int i) {	
		return By.xpath("(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str + "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper'])//h3[@class='cardTitle']//a[@class='noSelect '])['" +i+ "']");
	}
	
	public static By objAddToWatchlist = By.xpath("//div[contains(@class, 'iconInitialLoad-ic_add_Watchlist')]");
	
	public static By objRentNowIncarousel=By.xpath("//div[contains(@class,'slick-slide slick-active slick-center slick-current')]//div//span[contains(text(),'Rent Now')]");

	public static By objTrayListingButton=By.xpath("(//*[@class='arrow iconInitialLoad-ic_viewall noSelect']//div)[1] | //*[@class='viewAllLink']");
	public static By objxDefault=By.xpath("//*[text()='x-default']//following-sibling::*//following-sibling::*");	
 	
 	public static By objEduauraaCardCarousel = By.xpath("//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle')][contains(text(),'Eduauraa')]");
 	
 	public static By objPageH1TitleSEO=By.xpath("//h1[@class='titleH1Seo']");
 	public static By objPageH1TitleSEO2=By.xpath("//h1");
 	public static By objTrayH2TitleSEO (String apiTitle) {
 		return By.xpath("//*[@class='trayHeader']//h2//*[contains(text(),\""+apiTitle+"\")]");
 	}
 	public static By objTrayH2TitleSEO2 (String apiTitle) {
 		return By.xpath("//*[@class='trayHeader']//h2[contains(text(),\""+apiTitle+"\")]");
 	}
 	public static By objTrayH2TitleSEOHref (String href) {
 		return By.xpath("//*[@class='trayHeader']//h2//*[contains(@href,\""+href+"\")]");
 	}
 	public static By objTrayH2TitleSEOElements = By.xpath("//*[contains(@class,'tray-container newsTitle-tray-wrap')]//*[@class='trayHeader']//*");
 	public static By objTrayH2TitleSEOh2Elements = By.xpath("//*[contains(@class,'tray-container newsTitle-tray-wrap')]//*[@class='trayHeader']//h2");
 	public static By objTayH2TitleSEOFromSource= By.xpath("//span[@class='html-tag' and contains(text(),'<h2')]//parent::td");
 	
 	public static By objSourceDescription = By.xpath("//span[text()='description']//following-sibling::*//following-sibling::*");
 	
 	public static By objGetPremiumWebList = By.xpath("//*[@class='carouselMain']//*[contains(text(),'Buy Plan')]");
 	
 	public static By objNextArrowFirstContentCardOfTray(String trayName) {
		return By.xpath("(//*[contains(@class,'trayHeader')]//*[contains(text(),'" + trayName
				+ "')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'content')])[7]");
	}
 	
 	public static By objverifyOtpPopup = By.xpath("//div[@class='formHeader']");
	
	public static By objCheckBoxForWhatsapp = By.xpath("//*[@class='checkBoxStyle']");
	
	public static By objGenderMale = By.xpath("//*[@id='male' and contains(@value, 'Male')]");
	
	public static By objAgeInNumber = By.xpath("//div[@class='itemWrapper']//input[@id='textField']");

	public static By objContineButtonOnMandReg = By.xpath("//button[contains(@class, 'noSelect purpleBtn')]");
	
	public static By objZeelogo1 = By.xpath("//a[@class='zeeLogo noSelect ']//child::*");
	
	public static By objContTitleOnCarousel = By.xpath("//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'descWrap ')]");
	
	public static By objTabName(String tabName) {
		return By.xpath("(//a[contains(@class,'noSelect active regionalLang kn_regionalLang') and text()='\" + tabName + \"'])[1]");
	}

	public static By objTwitterPage = By.xpath("//*[contains(@class ,'css-901oao css-16my406 css-bfa6kz r-poiln3 r-a023e6 r-rjixqe r-bcqeeo r-qvutc0')]//*[text()='Follow']");
}
