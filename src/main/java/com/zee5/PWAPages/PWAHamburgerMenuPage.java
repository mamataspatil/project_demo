package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAHamburgerMenuPage {

	/**
	 * Profile Page
	 */
//	Profile Page title
	public static By objProfilePageTitleTxt = By.xpath("//h1[@class='pageTitle']");

//	Profile Image Icon
	public static By objProfilePageIcon = By.xpath("//div[@class='userImg']");

//	Profile Name
	public static By objProfilePageNameTxt = By.xpath("//div[@class='userName ']");

//	Profile Email Id
	public static By objProfilePageUserIdTxt = By.xpath("//div[@class='userId']");

//	Profile Edit Profile Button
	public static By objProfileEditBtn = By.xpath("//div[@class='profileEdit noSelect']");

//	Subscription Button
	public static By objSubscritionBtn = By.xpath("//div[@class='gridWrap']");

//	Change Password Button
	public static By objChangePasswordBtn = By.xpath("//div[@class='noSelect btnContnet' and contains(text(),'Change Password')]");

//	Move Top Arrow Icon
	public static By objMoveTopArrowIcon = By.xpath("//div[@class='iconNavi-ic_arrow_back']");

//	Play Store Button
	public static By objPlayStoreBtn = By.xpath("//img[@alt='Google Play']");

//	App Store
	public static By objAppStoreBtn = By.xpath("//img[@alt='App Store']");

//	Social media Icon
	public static By objFcebookIcon(String SocialMediaIcon) {
		return By.xpath("//a[contains(.,'" + SocialMediaIcon + "')]");
	}

//	About Us, Help Center, Privacy Policy, Terms of use
	public static By objAboutHelpPrivacyTerms(String text) {
		return By.xpath("//div[@class='footerMenu']//div[.='" + text + "']");
	}

//	CopyRight Text
	public static By objCopyRightTxt = By.xpath("//div[@class='copyRightTxt']");

	/**
	 * Hamburger menu
	 */

//	Profile Icon
	public static By objProfileIcon = By.xpath("//div[@class='iconInitialLoad-ic_profile profilePic']");

//	Profile Name 
	public static By objProfileTxt = By.xpath("//div[@class='userEmail ']");

//	Profile Expander Icon
	public static By objProfileExpanderIcon = By.xpath("//div[@class='arrow iconInitialLoad-ic_viewall']");

//  Hamburger menu closed
	public static By objHamburgerClose = By.xpath("//button[contains(text(),'Close Menu')]");

//	Login Button
	public static By objLoginBtn = By.xpath("//span[@class='loginButton borderGradient ']//span[.='Login']");

//	SignUp for free button
	public static By objSignUpForFree = By.xpath("//a[@class='noSelect buttonGradient ']//span[.= 'Sign up for FREE']");

//	Home Button
	public static By objHomeBtn = By.xpath("//div[@class='noSelect menuItem  active']/div[.='Home']");

//	Explore button
	public static By objExploreBtn = By.xpath("//h5[.='Explore']");

//	Explore Down arrow icon
	public static By objDownArrow(String expanderName) {
		return By.xpath("//div[@class='menuTitle noSelect  menuForMyAccount']");
	}

//	Check Explore down arrow icon is Active
	public static By objActiveDownArrow(String expanderName) {
		return By.xpath("//h5[.='" + expanderName + "']//span[contains(@class,'iconNavi-ic_expand_less active')]");
	}

//	Items of Explore button
	public static By objExploreItemBtn(String itemName) {
		return By.xpath("//*[@class='bm-item primaryMenu']//*[.='" + itemName + "']");
	}

//	Plans button
	public static By objInsideItemsBtn(String itemName) {
		return By.xpath("//div[.='" + itemName + "']");
	}

//	Info
	public static By objInsideItemsOfInfoBtn(String itemName) {
		return By.xpath("//div[@class='bm-item primaryMenu']//div[.='" + itemName + "']");
	}

//	Version Text
	public static By objVersionTxt = By.xpath(".//*[@class=\"versionText\"]");

//	Terms of Use / Privacy Policy
	public static By objTermsOfUse = By.xpath("//div[@class='termsPrivacyWrap']");

//	Close Icon
	public static By objCloseIcon = By.xpath("//button[.='Close Menu']");

//	Zee Logo
	public static By objZeeLogo = By.xpath("//div[@class='burgerZeeLogo']/img[@alt='ZEE5 Logo']");

	/**
	 * My Watchlist
	 */
//	Watchlist Icon
	public static By objWatchlistIcon = By.xpath("//div[@class='iconAlerts-ic_no_watchlist_expanded']");

//	Watchlist Text
	public static By objWatchlistTxt = By.xpath("//div[@class='textArea']");

//	Watchlist Tab Navigation
	public static By objTabNavigation(String tabName) {
		return By.xpath("//div[.='" + tabName + "' and //div[contains(@class,'noSelect tabMenuItem') ]]");
	}

//	content Title
	public static By objContentTitle = By.xpath("//h3[@class='cardTitle overflowEllipsis ']");

//	Episode Number
	public static By objEpisodeNumber = By.xpath("//div[@class='dateTime']");

//	Close Icon
	public static By objWatchlistCloseIcon = By.xpath("//span[@class='noSelect iconInitialLoad-ic_close']");

//	Remove All button
	public static By objRemoveAllBtn = By.xpath("//span[.='Remove All']");

//	WATCHLIST
	public static By objMyAccountOptionsText(String option) {
		return By.xpath("//*[contains(@class,'pageTitle') and text()=\"" + option + "\"]");
	}

//	Edit Profile Text
	public static By objEditProfileText = By.xpath("//*[@class='pageTitle editPageTitle']");

	// Edit Profile Changes saved
	public static By objEditProfileChangesSaved = By.xpath(" @class='toastMessage']");

	// Change password Text
	public static By objChangePasswordText = By.xpath("//*[@class='pageTitle changePasswordTitle']");

	// Change Old password
	public static By objChangeOldPassword = By.xpath("//*[@name='oldPwd']");

	// Change passowrd
	public static By objNewPassword = By.xpath("//*[@name='newPwd']");

	// Change passowrd
	public static By objConfirmNewPassword = By.xpath("//*[@name='confirmPwd']");

	// My plan text
	public static By objMyplanText = By.xpath("//h3[@class='planTitle']");

	// My active plan
	public static By objMyActivePlan = By.xpath("//div[@class='myPlanWrapper']");

	public static By objMYSubscriptionActiveStatus = By.xpath("//div[@class='metaItem']//p[2]");

	public static By objNoTransaction = By.xpath("//div[.='No Transaction']");
	public static By objMyTransactionDate = By.xpath("//p[@class='date']");

	public static By objMyTransactionPackStatus = By.xpath("//div[@class='billRow']//p[2]");

	public static By objMyAccountOption = By.xpath("//div[contains(@class,'menuForMyAccount')]");

	public static By objContTitleTextCarousel(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle') and contains(text(),'"
						+ text + "')]");
	}

	public static By objAfterSelectedLanguage = By
			.xpath("//div[@class='checkboxWrap checkedHighlight']//label[@for='select_hi']");
	public static By objSelectLanguage = By.xpath("//label[@for='select_hi']");
	public static By objApplyButtonInContentLangugaePopup = By.xpath("//div[@class='popupBtn noSelect']");
	public static By objCancelButtonInContentLangugaePopup = By.xpath("//div[@class='popupBtn accentBtn noSelect']");
	public static By objAuthenticateDevice = By.xpath("//div[contains(text(),'Authenticate Device')]");
	public static By objParentControlPageTitle = By.xpath("//h2[contains(@class,'pageTitle')]");
	public static By objNoRestrict = By.xpath("//div[contains(text(),'No Restrictions')]");
	public static By objSelectedParentControl = By.xpath(".//*[@class='oval tickIcon iconOther-ic_check']//parent::*");
	public static By objParentalLockPin1 = By.xpath("//input[contains(@name,'pin1')]");
	public static By objParentalLockPin2 = By.xpath("//input[contains(@name,'pin2')]");
	public static By objParentalLockPin3 = By.xpath("//input[contains(@name,'pin3')]");
	public static By objParentalLockPin4 = By.xpath("//input[contains(@name,'pin4')]");
	public static By objParentalLockPopUpInPlayer = By.xpath("//div[@id='childDivId']");
	public static By objParentalLockPin1player = By.xpath("//input[@id='parentLockId1']");
	public static By objParentalLockPin2player = By.xpath("//input[@id='parentLockId2']");
	public static By objParentalLockPin3player = By.xpath("//input[@id='parentLockId3']");
	public static By objParentalLockPin4player = By.xpath("//input[@id='parentLockId4']");
	public static By objAuthenticationText = By.xpath("//h1[contains(@class,'pageHeading') and text()='Activate ZEE5 on your TV']");
	public static By objAuthenticationButtonNotHighlighted = By
			.xpath("//button[contains(@class,'noSelect buttonGradient null')]");
	public static By objCloseHamburgerMenu = By.xpath("//button[contains(text(),'Close Menu')]");
	public static By objPlanInHamburger = By.xpath("//div[contains(@class,'menuTitle noSelect menuForPlans')]");

	public static By objExploreOptions(String expanderName) {
		return By.xpath("//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'"
				+ expanderName + "')]");
	}

	public static By objWEBMyAccount = By.xpath("(//h5[.='My Account'])[2]");

	public static By objMyProfileOptionsWEB(String OptionName) {
		return By.xpath("//*[contains(@class,'noSelect menuItem')][contains(text(),'" + OptionName + "')]");
	}

	public static By objProfileIconInProfilePage = By.xpath(
			"//div[contains(@class,'bm-item profileMenuHeader')]//div[contains(@class,'iconInitialLoad-ic_profile profilePic')]");
	public static By objProfileTextWEB = By.xpath("//div[@class='noSelect pageLink' and text()='My Profile']");
	public static By objProfilePageNameTxtWEB = By
			.xpath("//div[contains(@class,'userDetails')]//div[contains(@class,'userName')]");
	public static By objChangePasswordTextWEB = By.xpath("//h2[contains(@class,'pageSubTitle')]");
	public static By objLanguageBtnWEB = By.xpath("//div[contains(@class,'languageBtn')]");
	public static By objContentLanguageWrapper = By.xpath("//div[@id='contentWrapLanguage']");
	// Display language btn
	public static By objDisplayLang = By.xpath("//div[contains(@class,'noSelect displaylanguageHeader')]");

	// Content language button
	public static By objContentLang = By.xpath("//div[contains(@class,'noSelect contentlangugaeHeader')]");

//		====================================================================================================

	public static By objKannadaSelectedLanguage = By
			.xpath("((//div[@class='checkboxWrap checkedHighlight'])[2])//child::*[@class='commonName']");

	public static By objSelectedContentLanguages = By
			.xpath("(//div[@class='checkboxWrap checkedHighlight'])//child::*[@class='commonName']");

	public static By objPackTitle = By.xpath("(//p[@class='packTitle'])");

	public static By objPackPrice = By.xpath("(//p[@class='packPrice'])");

	public static By objPackDuration = By.xpath("(//div[@class='billRow']//p[2])[3]");

	public static By objPackTitle1 = By.xpath("(//p[@class='packTitle'])[2]");

	public static By objPackPrice1 = By.xpath("(//p[@class='packPrice'])[2]");

	public static By objPackDuration1 = By.xpath("(//div[@class='billRow']//p[2])[8]");

	public static By objMyTransactionPackStatus1 = By.xpath("(//div[@class='billRow']//p[2])[6]");

	public static By objMyTransactionAutoRenewalStatus1 = By.xpath("(//div[@class='billRow']//p[2])[10]");

	public static By objZeeLogo1 = By.xpath("(//img[@alt='ZEE5 Logo'])[1]");

//	Shreenidhi -- PROFILE MODULE

	public static By objNoTranscationText = By.xpath("//div[@class='textArea']");

	public static By objSubscribitionPageActivePlan = By.xpath("//div[@class='subscriptionItem']");

	public static By objPasswordErrorText = By.xpath("//div[contains(text(),'Minimum 6 characters')]");

	public static By objEditProfileGender = By.xpath("//div[contains(text(),'Gender')]");

	public static By objEditProfileMobileNumber = By.xpath("//input[@name='userMobile']");

	public static By objTransactionPageGrid = By.xpath("//div[@class='transactionContents']");

	// About Us information
	public static By objAboutUsInfo = By.xpath("//div[@class='staticPageContainer']");

	// Hyperlink on About us screen
	public static By objHyperLink = By.xpath("//a[contains(text(),'www.zee5.com')]");

	// Help Center header in Help Center page
	public static By objHelpUsHeader = By.xpath("//div[contains(@class,'headerContent_h1')]");

	// categories of Help Center screen

	// Getting started category
	public static By objGettingStarted = By.xpath("//div[contains(@class,'zpicon-container')]//div[1]//div[1]//h3[1]");

	// My account category
	public static By objMyAccountInHelpCenter = By.xpath("//span[contains(text(),'My Account')]");

	// Quick links category
	public static By objQuickLinks = By.xpath("//div[contains(text(),'Quick Links')]");

	// FAQ under Getting started category
	public static By objFAQInGettingStarted = By
			.xpath("//*[@text='How do I watch ZEE5 on my television?  ' and @nodeName='A']");

	// Offer Terms and conditions
	public static By objOfferTermsAndConditions = By.xpath("//b[contains(text(),'Offer Terms & Conditions')]");

	// Terms and conditions in Terms of Use Screen
	public static By objTermsAndConditions = By.xpath("//body//ul[3]");

	// Write to us button in Help Center screen
	public static By objWritetous = By.xpath("//div[contains(@class,'feedback_cls')]//div[2]");

	// Contact Us page
	public static By objContactUs = By.xpath("//div[@class='headingTxt shiftLeft chgLeft']");

	// Select your country field
	public static By objSelectYourCountry = By.xpath("//span[@id='countrySpan']");

	// Auto filled country
	public static By objAutofilledcountry = By.xpath("//div[@class='dropdown selectedCountry']");

	// Drop down arrow
	public static By objDropDown = By.xpath("//button[@id='countryOptions']//span[@class='imgClass']");

	// Registered mobile number field
	public static By objRegisteredMobileNumber = By.xpath("//input[@id='userMobile']");

	// Auto filled country code
	public static By objCountryCode = By.xpath("//input[@id='countryCode']");

	// Email ID field
	public static By objEmailField = By.xpath("//input[@id='userEmail']");

	// Email Id Asterisk
	public static By objEmailIDAsterisk = By
			.xpath("//span[@class='email floating-label mobileMargin']//span[@class='asterisk'][contains(text(),'*')]");

	// Tell us more about your issue text
	public static By objText = By.xpath("//div[@class='cotentTxt alignElmDiv radioNextLine chgLeft']");

	// Content option
	public static By objContentOption = By.xpath("//span[@id='optImgRadio0']");
	// Product option
	public static By objProductOption = By.xpath("//span[@id='optImgRadio1']");

	// Enquiry option
	public static By objEnquiryOption = By.xpath("//label[@id='radioOpt2']");

	// Feedback option
	public static By objFeedbackOption = By.xpath("//label[@id='radioOpt3']");

	// Content as default option
	public static By objContentAsDefault = By.xpath("//input[@id='catOpt0']");

	// Select category field
	public static By objSelectCategory = By
			.xpath("//div[@class='dropdown selectedCategory removeBothPadding addpadding']");

	// Video not playing option as default
	public static By objVideoNotPlaying = By.xpath("//button[@id='options']");

	// Error message
	public static By objErrorMessage = By.xpath("//span[@class='error errorMsg']");

	// Error message field
	public static By objErrorMessageField = By.xpath("//input[@id='errMsg']");

	// Error message asterisk

	public static By objErrorMessageAsterisk = By
			.xpath("//span[@class='floating-label errorMargin']//span[@class='asterisk'][contains(text(),'*')]");

	// SUBMIT button
	public static By objSubmitButton = By.xpath("//button[@id='submitData']");

	// RESET button
	public static By objResetButton = By.xpath("//button[@id='resetData']");

	// Platform asterisk
	public static By objPlatformAsterisk = By
			.xpath("//span[@id='spanDyan0']//span[@class='asterisk'][contains(text(),'*')]");

	// SUBMIT button enabled
	public static By objSUBMITEnabled = By.xpath("//div[@class='btnSection']//div[3]");

	// FAQ's under My account category
	public static By FAQunderMyAcoount = By.xpath("//div[@id='layoutContainer']//div[2]//div[1]//div[1]");

	// FAQ's under Getting started
	public static By FAQunder = By.xpath("//div[contains(@class,'zpicon-container')]//div[1]//div[1]//div[1]//ul[1]");

	public static By objHelpSectioOptionsHeading(String text) {
		return By.xpath("//*[contains(text(),'" + text + "')]");
	}

	// *[@data-id='article_Title']
	public static By objArticleTitle = By.xpath("//*[@data-id='article_Title']");

	// About Us option in Kannada
	public static By objAboutUsinKannada = By.xpath("//a[text()='ನಮ್ಮ ಬಗ್ಗೆ']");
	// Privacy Policy in Kannada
	public static By objPrivacyPolicyInKannadA = By.xpath("//a[text()='ಗೌಪ್ಯತೆ ನೀತಿ']");
	// English option
	public static By objEnglishOption = By.xpath("//input[@value='en']//parent::*//span");
	public static By objKannadaLanguage = By.xpath("//input[@value='kn']//parent::*//span");

	public static By objchooseAppToOpen = By.xpath("//*[@resource-id='android:id/resolver_page']");
	// *[@text='Chrome']

	public static By objSelectAppToOpen = By.xpath("//*[@text='Chrome']");

	// Cashback for payment by Amazon pay
	public static By objCashbackByAmazonPay = By
			.xpath("//p[contains(text(),'ZEE5 - Cashback for Payment by Amazon Pay upto INR')]");

	// 30 % Cashback on any bank card
	public static By objCashbackByAnyBankCard = By
			.xpath("//p[contains(text(),'ZEE5 - 30% Cashback on any Bank Credit')]");

	// Paytm 50% Cashback
	public static By objCashbackOnPaytm = By.xpath("//p[contains(text(),'ZEE5 Paytm 50% Cashback Offer T & C')]");
	// Offer duration in Terms of Use
	public static By objOfferDUration = By.xpath("//b[contains(text(),'Offer Duration')]");
	// Hyper link in Privacy Policy Screen
	public static By objLinkOnPrivacyPolicy = By.xpath("//a[contains(text(),'www.zee5.com')]");

//		NETWORK
	public static By objPlaybackErrorMessage = By.xpath("//div[@class='networkErrorContainer']");

	public static By objApplyBtn = By.xpath("//div[contains(text(),'APPLY')]");

	// My remainder option
	public static By objMyRemainder = By.xpath("//a[contains(text(),'My Reminders')]");

	public static By objkannadalanguage = By.xpath("//span[@class='commonName'][contains(text(),'Kannada')]");

	public static By objPlans = By.xpath("//*[text()='Plans']");

	public static By objHaveAPrepaidCode = By.xpath("//a[.='Have a prepaid code ?']");

	public static By objMyAccount = By.xpath("//*[text()='My Account'] ");

	public static By objMySubscription = By.xpath("//*[.='My Subscription']");

	public static By objMyTransactions = By.xpath("//*[.='My Transactions']");

	public static By objMySubscriptionPage = By.xpath("//*[.='My Subscription']");

	public static By objNoActiveSubscription = By.xpath("//*[.='No Active Subscription']");

	public static By objMySubscriptionItem = By.xpath("//*[@class='subscriptionItem']");

	public static By objMySubscriptionPackName = By.xpath("//*[@class='packNameWrap']");

	public static By objMyTransactionPackName = By.xpath("//*[@class='packInline']");

	public static By objGetPremiumCTAbelowPlayer = By
			.xpath("//*[@class='subscribe-teaser-button' and //*[.='GET PREMIUM']]");

	public static By objMyTransactionPage = By.xpath("//*[@class='pageTitle' and //*[text()='My Transactions']]");

	public static By objGetPremiumPopup = By
			.xpath("//h2[contains(@class,'popupTitle bigTitle')]//p[text()='Subscribe']");

	public static By objPopupClose = By.xpath("//*[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objSubscribeNowLink = By
			.xpath("(//span[contains(@class,'subscribe-link') and contains(text(),'Subscribe to')])[1]");

	public static By objSelectedLanguages = By
			.xpath("//*[@class='checkboxWrap checkedHighlight']//*[@class='innnerContentWrap']");

	public static By objCancelBtnOnLangPp = By.xpath("//*[@class='popupBtn accentBtn noSelect']");

//	public static By objContentLanguage = By.xpath("//div[contains(@class,'contentlangugaeHeader')]");
	// manas
	public static By objContentLanguage = By.xpath("//div[contains(text(),'Content Language')]");
	public static By objAboutUsOption = By.xpath("(//a[.='About Us'])[1]");

	public static By objHelpCenterOption = By
			.xpath("//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'Help Center')]");

	public static By objTermsOfUseOption = By
			.xpath("//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'Terms of Use')]");

	public static By objPrivacyPolicy = By.xpath(
			"//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'Privacy Policy')]");

	public static By objPrivacyPolicyInfo = By.xpath("//*[@class='staticPageContainer']");

	public static By objSecurityInfo = By.xpath("//*[contains(text(),'Security and Compliance with Laws')]");

	public static By objBuildVersion = By.xpath("//*[@class='versionText']");

	public static By objCloseHamburger = By.xpath("//*[text()='Close Menu']");

	public static By objApply = By.xpath("//*[@class='popupBtn noSelect']");

	public static By objApplybutton = By.xpath("//*[@class='filterButtonContainer']");

	public static By objAboutUsTextInPage = By.xpath("//*[contains(text(),'About Us')]");

	public static By objTermsOfUseScreen = By.xpath("//h1[contains(text(),'Terms of Use')]");

	public static By objPrivacyPolicyScreen = By.xpath("//h1[contains(text(),'Privacy Policy')]");

	public static By objLanguage = By.xpath("//*[contains(@class,'languageMenu')]");

	public static By objMoreSettingInHamburger = By.xpath("//*[contains(text(),'More Settings')]");

	public static By objParentalControl = By.xpath("//*[contains(text(),'Parental Control') and @class]");

	public static By objAuthenticationButtonHighlighted = By
			.xpath("//button[contains(@class,'noSelect buttonGradient')]");

	public static By objPlanInsideItemsBtn(String itemName) {
		return By.xpath("//*[contains(@class,'menuForPlans') and contains(text(),'" + itemName + "')]");
	}

	public static By objPopUpProceedButton = By
			.xpath("//*[@class='registerLoginContainer']//*[@class='noSelect buttonGradient ']");

	public static By objMoreSettingInKannada = By.xpath("//*[contains(text(),'ಹೆಚ್ಚಿನ ಸೆಟ್ಟಿಂಗ್ಸ್ ') and @class]");

	public static By objUpdatePasswordButton = By.xpath("//*[contains(@class,'noSelect buttonGradient')]");

//	public static By objNotNow =  By.xpath("//*[contains(@class,'addToHomeScreen')]//*[contains(text(),'Not now')]");
	// manas
	public static By objNotNow = By.xpath("//*[@class='btnWrap']//*[contains(text(),'Not now')]");

	public static By objSetParentalLockButton = By.xpath("//button[contains(@class,'noSelect buttonGradient')]");

	public static By objLanguageButtonWeb = By.xpath("//div[@id='languageBtn']");

	public static By objMyAccountOptionsText = By.xpath("//h1[contains(@class,'pageTitle')]");

	public static By objUserNameInMyProfileWeb = By
			.xpath("//div[@class='userDetails']//div[contains(@class,'userName')]");

	public static By objHomeInOpenMenuTab = By.xpath("//a[@class='noSelect menuItem  active']//div[.='Home']");

	public static By objProfileText = By.xpath("//div[@class='noSelect pageLink' and contains(text(),'My Profile')]");

	public static By objSettings = By.xpath("//*[text()='Settings']");

	public static By objLanguagePop = By.xpath("//div[@class='popupContent langFilterPopupWrapper']");

	public static By objInfo = By.xpath("//*[text()='Info']");

	// terms of use in Sign up screen
	public static By objTermsOfServicesInSignupScreen = By.xpath("//span[text()='Terms of Services']");

	// Privacy policy in Sign up screen
	public static By objPrivacyPolicyInSignupScreen = By.xpath("//*[text()='Privacy Policy']");

	public static By objDisplayLanguage = By
			.xpath("//*[contains(@class,'settingleft') and contains(text(),'Display Language')]");

	public static By carouselFirstDot = By.xpath("(//*[contains(@class,'carouselDots')])[1]");

	public static By objArticleTitle(String title) {
		return By.xpath("//*[contains(@text,'" + title + "')]");
	}

	public static By myAccount = By.xpath("//div[contains(@class,'menuTitle')]//*[.='My Account']");

	public static By objContinueButtonInVerifyAccount = By.xpath("//div[@class='noSelect popupBtn' and .='Continue']");

	public static By objTermsInKannada2 = By.xpath("//div[@class='menuGroup active'][2]//a[3]");

	public static By objPrivacyPolicyInKannadA2 = By.xpath("//div[@class='menuGroup active'][2]//a[4]");

	public static By objAboutUsinKannada2 = By.xpath("//div[@class='menuGroup active'][2]//a[1]");

	public static By objMyProfile = By.xpath("//div[contains(@class,'userEmail')]");

	public static By carouselDot(int i) {
		return By.xpath("(//*[contains(@class,'carouselDots')])[" + i + "]");
	}

	public static By objRestrict13PlusContent = By.xpath("//div[contains(text(),'Restrict 13+ Content')]");

	public static By objContinueButton = By.xpath("//span[contains(text(),'Continue')]");

	public static By objHighlightedTab(String tabname) {
		return By.xpath("//a[@class='noSelect active' and text()=\"" + tabname + "\"]");
	}

	public static By objzeeAppCloseHelpCenter = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_exit']");

	public static By objzeeAppCloseContactUs = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_exit']");

	public static By objzeeAppExit = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_exit_yes']");

	public static By objzeeAppInterstitialAddClose = By.xpath("//*[@content-desc='Interstitial close button']");

	// UpdatePasswordBtnHighlighted
	public static By objUpdatePasswordBtnHighlighted = By
			.xpath("//span[text()='Update']//parent::button[contains(@class,'noSelect buttonGradient')]");
	
	public static By objUnselectedKannadaContentLanguage = By.xpath("//div[@class='checkboxWrap ']//span[@class='commonName' and .='Kannada']");
	
	public static By objUnselectedHindiContentLanguage = By.xpath("//div[@class='checkboxWrap ']//span[@class='commonName' and .='Hindi']");
	
	public static By objplay = By.xpath("//span[text()='Play']");
	
	public static By objGetpremium = By.xpath("//*[text()='Get premium']");
	public static By objClub = By.xpath("//*[text()='Get Club']");
	//div[@class='page-container']
	public static By objrail = By.xpath("//div[@class='page-container']");
	
	public static By objpremiumcard = By.xpath("//div[@class='cardPremiumContent']");
	//div[@class='viewAllGrid']
	
	public static By objSubscribeWithClub = By.xpath("//button[@class='subscribe-teaser-button player-club-icon']");
	
	public static By objSubscribebtn=By.xpath("//button[@class='subscribe-teaser-button ']");
	
	public static By objFirstcontentCard=By.xpath("(//*[contains(@class,'trayHeader')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'content')])[1]");
	
	public static By obj2ndcontentCard=By.xpath("(//*[contains(@class,'trayHeader')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'content')])[2]");
	
	public static By objStoriescard=By.xpath("//div[contains(@class,'clickWrapper')]");
	
	public static By objClubAccess=By.xpath("//span[text()='Club']");
	
	public static By objPackactive=By.xpath("//div[contains(@class,' active')]");
	
	public static By objFreeContentCardFromTray =By.xpath("//div[@class='page-container']//div[@class='slick-list']//div[@data-minutelyurl and not(.//div[@class='cardPremiumContent' or contains(@class,'clubPackContent')])]");

	public static By objClubcontentcard=By.xpath("//div[@class='page-container']//div[@class='slick-list']//div[@data-minutelyurl and (.//div [contains(@class,'clubPackContent')])]");

	public static By objwatchfirstepisode=By.xpath("//*[text()='Watch First Episode']");
	
	public static By objwatchlatestepisode=By.xpath("//*[text()='Watch Latest Episode']");
	
	public static By objPremiumcontentcard=By.xpath("//div[@class='page-container']//div[@class='slick-list']//div[@data-minutelyurl and (.//div[@class='cardPremiumContent' or contains(@class,'clubPackContent')])]");
	
	public static By objFirstsearchcard=By.xpath("(//*[@class='metaData'])[1]");
	
	public static By objPlanPrice=By.xpath("//div[@class='amountBreakup']//p[text()='Plan Price']");
	
	public static By objDiscount=By.xpath("//div[@class='amountBreakup']//p[text()='Discount']");
	
	public static By objRoundoff=By.xpath("//div[@class='amountBreakup']//p[text()='Round Off']");
	
	public static By objTotalPayable=By.xpath("//div[@class='totalAmount']//p[text()='Total Payable Amount']");
	
	public static By objzeeplexcontent=By.xpath("//*[@class='plexTrayContainer']");
	public static By objTrailer=By.xpath("//*[text()='Trailer']");
	
	public static By objrentalpopupclose=By.xpath("//div[@class='plexExpiryCloseIcon']");
	public static By objEduauraaSignupPage = By.xpath("//div[@class='signup-block']");
	public static By objStorisarrowup=By.xpath("//*[@class='fa fa-arrow-up']");
	public static By objsendotp=By.xpath("//*[text()='Send OTP']");
	public static By objzeeplextab=By.xpath("(//a[contains(@class,'noSelect')][contains(text(),'ZEEPLEX')])[2]");
	public static By objrentforINR=By.xpath("//*[contains(text(),'Rent for INR ')]");
	public static By objrentforINRpopup=By.xpath("//div[@class='popupContent']//span[contains(text(),'Rent for INR ')]");
	public static By objPlayBtn = By.xpath("//a[@class='playIcon']");
	public static By objLanguageBtnWeb = By.xpath("//*[@id='languageBtn']");
	public static By objWouldYouLikeWatchMorePopup = By.xpath("//div[contains(text(),'Would you like to watch more')]");
	public static By objWouldYouLikeWatchMoreCloseButton  = By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");	
	public static By objzeeplexbanner=By.xpath("//*[@class='plexLandingPageBanner']");
	public static By objzeeplexHowitWorks=By.xpath("//span[.='How it Works ?']");
	public static By objzeeplexHowitWorkspopup=By.xpath("//div[@class='popupContent']");
	public static By objplexTrayTitle(int i) {
		return By.xpath("(//div[contains(@class,'plexTrayContainer')]//h2[@class='plexTrayHeader'])[" + i + "]");
	}
	
	public static By objzeeplexHowitworkscloseicon=By.xpath("//div[@class='PlexLearnNowCloseIcon']");

	public static By objzeeplexbannerHome=By.xpath("//div[@class='plexContainer']");
	
	public static By objzeeplexcontentcard=By.xpath("(//*[contains(@class,'plexTrayHeader')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'content')])[1]");
	public static By objrentforinrbelowtheplayer=By.xpath("//div[@class='rentButton']");
	
	public static By objrentforINRpopupClose=By.xpath("//*[@class='plexExpiryCloseIcon']");
	
	public static By objzeeplexHowitWorksMobile=By.xpath("//div[@class='plexLearnMoreLink' and .='How it Works ?']");
	public static By objZeeplexComesToYouHomePage=By.xpath("//*[@class='plexContainer']//*[.='Theatre comes to you']");
	public static By objFirstAssetImageFirstRail = By.xpath("(//div[@class='slick-list'])[1]//div[@data-index='0']");
	public static By Objnextarrowcarousel=By.xpath("(//button[@class='slick-arrow slick-next'])[1]");
	
	public static By objeposidedate=By.xpath("(//div[@class='metaInfo']//p)[2]");
	public static By objproceedbtn=By.xpath("//*[text()='PROCEED']");
	public static By objproceed2btn=By.xpath("(//*[text()='PROCEED'])[2]");
	public static By objgetclubinKannada=By.xpath("//div[@class='slick-slide slick-active slick-center slick-current']//a[1]//span[contains(text(), 'ಕ್ಲಬ್ಗೆ ಹೋಗಿ')]");
	
	public static By objUnselectedEnglishContentLanguage = By.xpath("//div[@class='checkboxWrap ']//span[@class='commonName' and .='English']");
	
	public static By objPaymentoption=By.xpath("//*[@class='linearLayout sidebarItem_object']//following-sibling::*//article[contains(text(),'Airtel Payments Bank')]");

	public static By objQwikcilver=By.xpath("//p[@class='iconNavi-ic_expand_less']");
	
	public static By objPay=By.xpath("//*[text()='PAY']");
	public static By objcontinueinkannada=By.xpath("//span[contains(text(),'ಮುಂದುವರಿಸಿ')]");
	public static By objhometab=By.xpath("//a[contains(@class,'noSelect')][contains(text(),'Home')]");
	public static By objtotalamount=By.xpath("//div[@class='totalAmount']");
	
	public static By objtotalamount2=By.xpath("(//h2[@class='ng-binding'])[2]");
	
	public static By objbrwserallpack=By.xpath("//div[@class='btnContnet']");
	public static By objsubscriptioninHindi=By.xpath("//*[.='मेरी सदस्यता']");
	
	public static By objPackAmount4 = By.xpath("(//p[@class='currency'])[4]");
	
	public static By objupi=By.xpath("//*[@class='linearLayout sidebarItem_object ']//following-sibling::*//article[contains(text(),'UPI')]");
	public static By objupieditbox=By.xpath("//input[@class='editText']");
	public static By objupiidtext=By.xpath("//*[@class='textView ']//following-sibling::*//article[contains(text(),'UPI ID / VPA')]");
	public static By objplaybtncarousel=By.xpath("//div[@class='slick-slide slick-active slick-center slick-current']//div[@class='playIcon']");
	public static By objtermsofuse=By.xpath("//a[@class='terms' and text()='Terms of use']");
	public static By objprivacypolicy=By.xpath("//a[@class='terms' and text()='Terms of use']");
	public static By objhowitworks=By.xpath("//*[@class='buttonContainer ']//span[text()='How it Works ?']");
	public static By objhowitworksexpand=By.xpath("//h3[@class='Collapsible__trigger is-closed']");
	public static By objclosepopup=By.xpath("//div[@class='PlexLearnNowCloseIcon']");
	
	public static By objrentalsubscribelink=By.xpath("//span[@class='subscribe-link subscribemsg_en']");
	public static By objtvodtitle=By.xpath("//h5[@class='tvodTitle']");
	
	public static By objcardmouseovercontent=By.xpath("(//div[@class='showCard   zoomCardHover minutelyUrl card marginRight positionRelative'])[1]");
	public static By objcontentname=By.xpath("//div[@class='plexExpiryTitle']");
	
	public static By objreleasedby=By.xpath("//div[@class='productionhouse']");
	public static By objRentForINRbutton=By.xpath("//div[@class='buttonContainer ']");
	public static By objtransationcard=By.xpath("//div[@class='transactionCard']");
	public static By objallacesshighestpack=By.xpath("//div[@class='noSelect subscriptionPlanCard active discountCard']//p[text()='1 Year']");
	public static By objhaveacode=By.xpath("//input[@placeholder='Have a Code?']");
	public static By objapply=By.xpath("//div[text()='APPLY']");
	
	public static By objtermsandconditionlink=By.xpath("//a[text()='Terms & Conditions']");
	public static By objpriyacypolicy=By.xpath("(//a[text()='Privacy Policy'])[1]");
	
	public static By objzeeplexplayerbanner=By.xpath("//div[@class='tvodBanner']");
	public static By objsubscriptionpageimg=By.xpath("//*[@title='Subscription Page - Image 2']");
	
	public static By objrecocard=By.xpath("//div[@class='movieCard card marginRight minutelyUrl zoomCardHover']");
	public static By objcontentdate=By.xpath("//div[@class='showDuration']//span[3]");
	public static By objlogintext=By.xpath("//div[@class='adult-content-error-text']//span");
	
	public static By objprofileusername=By.xpath("(//div[@class='userName '])[1]");
	
	public static By objeduauraadropdown=By.xpath("//a[@class='dropdown-toggle']");
	public static By objeduauraalogout=By.xpath("(//span[text()='Logout'])[1]");
	public static By objconfirmpopupYes=By.xpath("//span[@class='mat-button-wrapper' and  text()='Yes']");
	
	public static By objtv9marathi=By.xpath("//div[@class='slick-slide']//*[text()='TV9 Marathi']");
	public static By objeduauraaprofileicon=By.xpath("//div[@class='inset']");
	
	public static By objeduauraaprofiledetails=By.xpath("(//div[@class='user-detail'])[1]");
	public static By objreleasingdate=By.xpath("//div[@class='expire']//h5[@class='yellowText']");
	
	public static By objupcomingmovietray=By.xpath("//h2[text()='Upcoming Movies']");
	public static By objerrormessage=By.xpath("//p[@class='errorMessage']");
	
	public static By objtop5watchednewa=By.xpath("(//*[contains(@class,'trayHeader')]//*[contains(text(),'Top 5 Most Watched News Videos')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'view-all')])[1]");
	
	public static By objvisaimg=By.xpath("(//img[contains(@class,'imageView')])[6]");
	
	public static By objreleatednews=By.xpath("//div[@class='relatedNewsCol']//h2");
	public static By objthirdcardsearchresult=By.xpath("(//h3[contains(@class,'cardTitle')]//parent::div//preceding-sibling::figure)[3]");
	public static By objshowtitle=By.xpath("//div[@class='bannerTitle hidden']");
	
	public static By objhighestpackcard=By.xpath("//div[@class='noSelect subscriptionPlanCard  ']//p[text()='1 Year']");
	
	
	public static By objchannelname(String text) {
		return By.xpath("//img[@title='"+text+"']");
	}
	public static By objchanneltitle(String text) {
		return By.xpath("//h1[text()='"+text+"']");
	}
			
	public static By objpayupage=By.xpath("//div[@id='payu_logo']");
			
	public static By objcluborpremiumcontentcard=By.xpath("(//div[contains(@class,'clubPackContent') or contains(@class,'cardPremiumContent')])");
	public static By objclubcard=By.xpath("//div[contains(@class,'clubPackContent')]");
			
	public static By objthreebreadcrumb=By.xpath("(//ul[contains(@class,'breadcrumbContainer ')]//li[@class='item '])[3]");
			
	public static By objmetadatainconsumption=By.xpath("(//div[@class='metaInfo']//a)[3]");
	public static By objcontentcardthroughtext(String text) {
		return By.xpath("(//div[@class='showCard   zoomCardHover minutelyUrl card marginRight positionRelative'])//img[@title='"+text+"']");
	}
	
	//Edit profile LastName
	public static By objEditProfilLastName = By.xpath("//*[@name='lastName']");
		
	public static By objFirstCard = By.xpath("//div[@class='viewAllGrid']//div//a//img");
		
	public static By objHamburgerBtn = By.xpath("//*[contains(@class,'hamburgerMenuBtn')]//following-sibling::*[contains(text(),'Open Menu')]");
	public static By objUnslectedNoRestrictions = By.xpath("//div[contains(text(),'No Restrictions')]//child::*[contains(@class,'_unchecked')]");
	
	public static By objfooter = By.xpath("//*[@class='megaMenuFooter']");
	
	public static By objEmptyStateScreen = By.xpath("//div[@class='textArea']");
	
	public static By objperklsidestext(String text)
	{
		return By.xpath("//div[@class='item']//span[text()='"+text+"']");
	}

	public static By objperksidetitle(String text)
	{
		return By.xpath("//div[@class='title' and text()='"+text+"']");
	}
	public static By objepisodenumberbelowthecard=By.xpath("//div[@class='showDuration']//span");
	public static By objmoviecontentinsubscriptionpage=By.xpath("//a[@data-minutelytitle='New Subs Page - Movies 1']");
	public static By objclosebtn=By.xpath("//div[@class='drowerCloseIcon iconInitialLoad-ic_close']");
	public static By objmetadataoncard=By.xpath("//div[@class='metaInfo hidden']//a");	
	public static By objchannelcard=By.xpath("//div[@class='viewAllWrap']//img");
	public static By objproceedtopay=By.xpath("//*[@class='linearLayout sidebarItem_object']//following-sibling::*//article[text()='Proceed to pay']");
	public static By objnetbanking = By.xpath(
			"//*[@class='linearLayout sidebarItem_object']//following-sibling::*//article[contains(text(),'Netbanking')]");
	public static By objdrmmessageonplayer=By.xpath("//p[@class='launch-text' and text()='There was a problem while playing this content. Please try again later.']");
	public static By objenterupiid=By.xpath("//*[contains(@class,'editText') and contains(@placeholder,'e.g rakesh@upi ')]");
	
	public static By objsomethingmess=By.xpath("//article[text()='Something went wrong. Please retry the payment.']");
	public static By objcontentcardinmywatchlist=By.xpath("(//h3[contains(@class,'cardTitle overflowEllipsis')]//a)");

	public static By objsubscriptionlinkmessage=By.xpath("//div[@class ='subscribe-msg-premium subscribemsg_en']//span");
	public static By objpackdiscountamtinmysubscriptionpage=By.xpath("//div[contains(@class,'packPrice')]//*[@class='price']");

	public static By objverifybtninaccountinfopage=By.xpath("//*[contains(@class, 'noSelect pinkBtn')]/span");

	public static By objloginbtninsubscriptionpage=By .xpath("//*[@class='loginBtn noSelect subscriptionLoginType']");

	public static By objExplorePremium=By.xpath("//h2[text()='Explore Premium']");

	public static By objstep2and3=By.xpath("//div[@class='parentalControlPopup']//span");

	public static By objamtinpaymentpage=By.xpath("//div[@class='totalAmount']//span");

	public static By objchangebtn = By
	.xpath("//*[contains(@class, 'promoCodePopupApply') and contains(text(), 'Change')]");

	public static By objhaveagiftcard=By.xpath("//span[@class='labelText' and text()='Have a Gift Card?']");

	public static By objentercarddetailstohaveagiftcard=By.xpath("//span[@class='inputLabel ' and text()='Enter Card Number Here']");

	public static By objgiftcardnumber=By.xpath("//input[@type='text' and @name='giftCardNumber']");
	
	public static By objcrownicon=By.xpath("//div[contains(@class,'slick-active')]//*[@class='premiumContainer']//div");
	
	public static By objProfileIconWEB = By.xpath("//*[contains(@class, 'bm-icon profileMenuBtn iconInitialLoad-ic_profile')]");
	
	public static By objBuySubscription = By.xpath("//a[contains(text(),'Buy Plan') or contains(text(), 'Buy Subscription')]");
	
	public static By objContentLanguageBtn = By.xpath("//*[@class='noSelect contentlangugaeHeader ']");
	public static By objMySubscriptionOption = By.xpath("//*[contains(@href,'/myprofile/plans')]");
	//My Subscription Page Title
	public static By objMySubscriptionPageTitle = By.xpath("//*[@class='subscriptionPageWrap']//h1[@class='pageTitle']");
	//My Transactions Page Title
	public static By objMyTransactionsPageTitle = By.xpath("//*[@class='transactionPage']//h1[@class='pageTitle']");
	//Browse All Packs Page Description Text
	public static By objBrowseAllPacksPageDescriptionText = By.xpath("//*[@class='subscriptionAndPaymentWrapper']//*[@class='stepDesc']");
	                         
	public static By objUnselectedContentLanguage(String Language) {
		return By.xpath("//div[@class='checkboxWrap ']//span[contains(@class,'Name') and .='"+Language+"']");
	}
	//My Transactions Option
	public static By objMyTransactionsOption = By.xpath("//*[contains(@href,'/myprofile/payments')]");
	
	//Browse All Packs Page Description Text
	public static By objBrowseAllPacksPageTitleText = By
			.xpath("//*[@class='subscriptionAndPaymentWrapper']//*[@class='stepTitle']");

	public static By objonlymovierent = By.xpath("//div[@class='bottomSection']//h3[text()='Only Rent Movie']");

	public static By objrentmovie = By.xpath("//div[@class='buttonContainer']//span");

	public static By objBuySubscriptionOption = By
			.xpath("//*[contains(@class,'menuItem') and contains(text(),'Buy Plan')]");

	// Lakshmi Combo Offer
	public static By obj499pack = By.xpath("//span[@class='price' and text()='499']");
	public static By objplanpriceinpaymentpage = By.xpath("//p[@class='packPrice ']");

	public static By objscanorcodetext(String text) {
		return By.xpath("//*[@class='textView']//following-sibling::*//article[contains(text(),'" + text + "')]");
	}

	public static By objtoarstmessage = By.xpath("//div[text()='Could not find card. Please enter valid card number']");
	public static By objamazonpaylogo = By.xpath("//img[@alt='Amazon Pay']");
	public static By objamazonlogintext = By.xpath("//div[@class='a-box-inner a-padding-extra-large']//h1");
	public static By objamazonemailandphnofield = By.xpath("//div[@class='a-row a-spacing-base']//label");

	public static By objamazonsignincta = By.xpath("//input[@id='signInSubmit']");
	public static By objplandetailsinamazon = By.xpath("//div[@class='a-box a-spacing-none']");

	public static By objmobikvikloginpage = By.xpath("//div[@id='view_login']");

	public static By objmobikviklogo = By.xpath("//div[@class='logos']");
	public static By objmobikviklogintext = By.xpath("//h2[text()='Please Login/Register to your wallet']");
	public static By objmobikvikemailandphnofield = By.xpath("//div[@class='form-group']//label");
	public static By objmobikviksendotpcta = By.xpath("//*[text()='Send OTP']");
	public static By objmobikvikentermobilenumber = By.xpath("//input[@placeholder='Enter Mobile Number']");
	public static By objenterotppage = By.xpath("//input[@placeholder='Enter OTP']");
	public static By objotpsentsuccessfully = By.xpath("//h2[text()='OTP Sent Successfully on 9000000000']");

	public static By objchangecta = By.xpath("//button[text()='Change ']");
	public static By objmobikvikresendotp = By.xpath("//button[text()='Resend OTP ']");
	public static By objsubmitmobikvik = By.xpath("//button[text()='Submit']");

	public static By objinputnumbercard = By.xpath("//input[@name='giftCardNumber']");
	public static By objentermobilenumber = By.xpath("//input[@placeholder='Enter Mobile Number']");

	public static By objtimerinupi = By.xpath("//div[@id='timer2']//article");
	public static By objscanandpay = By
			.xpath("//*[@class='linearLayout']//following-sibling::*//article[contains(text(),'Scan QR and Pay')]");
	public static By objotpscreen = By.xpath("//div[@class='tabs-Otp']");
	public static By objsearchbarinnetbanking = By.xpath("//input[@placeholder='Search']");
	public static By objlistofbanks = By.xpath(
			"//div[@class='horizontalScrollView']//div[@class='linearLayout']//div[@class='linearLayout']//div[@class='linearLayout']");

	public static By objpinnumber = By.xpath("//input[@type='password' and @name='pinNumber']");
	public static By objinputpincard = By.xpath("//input[@name='pinNumber']");
	public static By objenterpintohaveagiftcard = By.xpath("//span[@class='inputLabel ' and text()='PIN']");

	public static By objcardsinpaytm = By
			.xpath("//*[@class='_15Mz d-block po-n overflow-h fl ']//span[text()='Debit Card']");
	public static By objgobackinpaytm = By.xpath("//span[text()='go back']");
	public static By objcancelpaymentyes = By.xpath("//div[@class='_WCVy btn btn-primary pos-r']");

	public static By objpaymentfailurepopup = By.xpath("//h1[@class='retryPaymentHeader']");
	public static By objpaymentpopupclosebtn = By.xpath("//div[@class='drowerCloseIcon iconInitialLoad-ic_close']");

	public static By objrentnowinlandingpage=By.xpath("//span[contains(text(),'Rent Now')]");
	public static By objHamburgerMenu = By.xpath("(//*[text()='Open Menu'])[2]");

	public static By objPaymentoption(String text) {
		return By.xpath("//*[@class='linearLayout sidebarItem_object']//following-sibling::*//article[contains(text(),'"+text+"')]");
	}	
	
	public static By objPaytmProceedToPay = By
			.xpath("//article[contains(text(),'Paytm')]//ancestor::*[@class='linearLayout PaymentOptionViewNotListNestedChild']//child::*[text()='Proceed to pay']");

	public static By objTermsInKannada = By.xpath("(//a[text()='ಬಳಕೆಯ ನಿಯಮಗಳು'])[1]");
	
	public static By objLoadingPaymentOption=By.xpath("//div[@class='loadingMsg']");
	public static By objBuyPlanInSubscription=By.xpath("//h3[@class='bannerTitle']");
	public static By objBuyPlanonheader=By.xpath("//*[contains(@class,'headerContainer')]//span[text()='Buy Plan']");
	public static By objLoginLink=By.xpath("//span[@class='login-link']");
	public static By objEpisodeNumberInconsumptionPage=By.xpath("(//div[@class='metaInfo lineHeightClass']//p)[1]");
	public static By objEpisodeNumber1=By.xpath("(//div[@class='metaInfo lineHeightClass']//span)[1]");
	public static By objSeasonNumber=By.xpath("(//div[@class='metaInfo lineHeightClass']//a)[1]");
	public static By objShowMetaData=By.xpath("//a[@class='showMetaAnchor']");
	public static By objFailureMessage=By.xpath("//div[contains(@class,'applyPromo applyPromoFailure')]");
	public static By objOTP1=By.xpath("(//input[@name='otp1'])");
	public static By objOTP2=By.xpath("(//input[@name='otp2'])");
	public static By objOTP3=By.xpath("(//input[@name='otp3'])");
	public static By objOTP4=By.xpath("(//input[@name='otp4'])");
	public static By objChannelsHeader=By.xpath("//div[@class='tvchannelLanding allItemsPage viewAllShow']//h1");
	public static By objErrorMessageInAccountInfoPage=By.xpath("//*[contains(@class, 'errorContainer')]");
	public static By objchannelnameHeader= By.xpath("//div[@class='channelDetailContainer']//h1");
	public static By objChromeCast=By.xpath("//google-cast-launcher[@class='chromeCastIcon']");
	public static By objToastMessage=By.xpath("//*[@class='toastMessage']");
	public static By objAutoFlipcontent(String text)
	{
			return By.xpath("(//h2[text()='"+text+"'])[2]");
	}
	public static By objPerkSideText=By.xpath("//div[@class='title']");
	
	public static By objZeeplexComesToYou=By.xpath("//*[@class='plexBannerDescription' and text()='Watch films before the theatre']");
	
	public static By objzeeplexHowitworkspopupClose=By.xpath("//*[@class='drowerCloseIcon iconInitialLoad-ic_close']");
	
	//Humbergermenu
	public static By objWebBuySubscriptionOption = By.xpath("(//*[text()='Buy Plan'])[2]");

	public static By objmetadataofcontenttype=By.xpath("//div[@class='metaInfo ']//a");
	
	public static By objContinuebuttominsubscriptionpage=By.xpath("//*[@class='buttonContainer']//span");
	
	public static By objproceedtopayindex(int index)
	{
		return By.xpath("(//*[@class='linearLayout sidebarItem_object']//following-sibling::*//article[text()='Proceed to pay'])[" + index + "]");
	}

	public static By objmetadataforshows=By.xpath("//*[@class='showMetaAnchor']");
	
	public static By objsubscriptioncard=By.xpath("//div[@class='subscriptionItem']");
	
	public static By objcontinueinkannada1=By.xpath("//span[contains(text(),' ಮುಂದುವರಿಯಿರಿ')]");
	
	public static By objExploreOption = By.xpath("(//div[@class='menuTitle noSelect  '])[1]");
	
	public static By objBlankSpace = By.xpath("//*[@class='mainlink']");
	
	public static By objGrievanceRedressal = By.xpath("//a[contains(text(),'Grievance Redressal')]");
	
	public static By objGrievanceRedressalPage = By.xpath("//*[@id='navBarContainer']//span[contains(text(),'GRIEVANCE REDRESSAL')]");
	
	public static By objHamburgerMenuOpened =By.xpath("//div[@class='bm-item primaryMenu' and @tabindex='0']");
	
	public static By objSubscriptionTeaserBanner = By.xpath("//div[@class='subscriptionBanner ']");
	
	public static By objupcomingTrailer=By.xpath("");
	
	public static By objNowShowingTray=By.xpath("//h2[text()='Now Showing']");
	
	public static By objActivateNowButtonEnabled=By.xpath("//button[contains(@class,'noSelect buttonGradient')]//span");
	public static By objActivateNowButtonDisabled=By.xpath("//button[@disabled]//span");
	public static By objAuthenticateCode(String place) {
		String name="otp"+place;
		return By.xpath("//input[@name='"+name+"']");
	}
	public static By objActivateDeviceError=By.xpath("//span[@class='statusMessage errorMessage']");
	
	public static By objAuthenticationTDesc = By.xpath("//p[contains(@class,'pageDesc')]");
	
	public static By objzeeplex=By.xpath("//*[.='My Rentals']");
	
	public static By objSearchThroughTitle=By.xpath("(//*[contains(@class,'highLight')])[1]");
	
	public static By objupiarrowSupermoon = By.xpath("//*[@id='1000437']");
	
	public static By objupiproceedtopay=By.xpath("//*[@class='linearLayout sidebarItem_content sidebarItem_content_addupi']//following-sibling::*//article[text()='Verify']");

	public static By objupiarrow = By.xpath("//img[contains(@src,'round')]");
	
	public static By objSubscriptionCardTitle=By.xpath("//div[@class='subscriptionItem']//h2[@class='packName']");
	public static By objZeeplexlogo=By.xpath("//img[@class='plexLandingPageLogo']");
	public static By objRentAndWatch=By.xpath("//*[@class='planLandingPageHeader']");
	public static By objHowItWorksInKannada=By.xpath("//*[@class='buttonContainer ']//span[text()='ಇದು ಹೇಗೆ ಕೆಲಸ ಮಾಡುತ್ತದೆ?']");
	public static By objWatchFilmsBeforeTheatre=By.xpath("//*[@class='plexBannerDescription']");
	public static By objZeeplexSubscriptionText=By.xpath("//*[@class='plexZee5SubscriptionText']");
	public static By objMyTransactionAutoRenewalStatus = By.xpath("(//div[@class='billRow']//p[2])[5]");
	
	public static By objTextBelowThePlayer = By.xpath("//*[contains(text(), '50% OFF: 1 Year pack ₹499 + Paytm Cashback')]");
	
	public static By objAuthenticationField = By.xpath("//input[contains(@name,'otp')]");
	
	public static By objShowTitle=By.xpath("//*[@class='consumptionMetaDiv']//h1");
	public static By objShowName=By.xpath("(//*[@class='consumptionMetaDiv']//h2)[1]");
	public static By objEpisodeNumberInShows=By.xpath("//*[@class='consumptionMetaDiv']//p");
	
	public static By objTextBelowThePlayerAtZee = By.xpath("//*[contains(text(), '50% OFF: 1 Year pack ₹499 + Paytm Cashback')]");
	
	public static By objTextBelowThePlayerAtNewpwa = By.xpath("//*[contains(text(), 'Ad-Free with Premium now at 50%OFF : ₹499/year')]");

	public static By objLiveNewsInKannada=By.xpath("//*[contains(@class,'trayHeader')]//*[contains(text(),'ಲೈವ್ ನ್ಯೂಸ್')]");
	
	public static By objLiveChannelTray=By.xpath("//*[contains(@class,'trayHeader')]//*[contains(text(),'Live News Channels - Free')]");
	
	public static By objTooltipForWeb(String tooltipText) {
		return By.xpath("//*[@class='playkit-tooltip-label playkit-tooltip-right playkit-hide' and text()='" + tooltipText + "']");
	}
	
	public static By objSubscriptionButton=By.xpath("//div[contains(@class ,'subscribe-button subscribemsg')]");
	
	public static By objAdultmessage=By.xpath("//div[@class='adult-content-error-msg']");
	
	public static By objYourWatchingATrailer=By.xpath("//*[@class='trailerInfoContainer']");
	
	public static By objstoriesCardTitle=By.xpath("(//*[@class='cardTitle'])[1]");
	
	public static By objWebSeriesStories=By.xpath("//*[contains(@class,'trayHeader')]//*[contains(text(),'Stories')]");
	
	public static By objStoriesRail=By.xpath("//*[contains(@class,'trayHeader')]//*[contains(text(),'Kundali Bhagya Stories')]");
	
	public static By objTrayHeader=By.xpath("//div[@class='trayHeader']");
	
	public static By objstoriesTitle=By.xpath("//*[@class='storyTitle']");
	
	public static By objEditProfileTextWEB = By.xpath("//h1[@class= 'pageTitle' and .= 'Edit Profile']");
	
	public static By objEditProfileEmailField = By.xpath("//input[@name='email']");
	
	public static By objEditProfileDOB = By.xpath("//*[@name='age']");
	
	public static By objResetToDefaultHamburgerMenu = By.xpath("//*[contains(@href,'resetdefault') and text()='Reset settings to default']");
	public static By objParentalControlOption(String text) {
		return By.xpath("//*[@class='ageGroupRowText' and text()=\""+text+"\"]");
	}
	
	public static By objLiveCard=By.xpath("Verify if the user can see the similar channels and upnext rail on the Live TV consumtion page");
	public static By objUpnextRail=By.xpath("//*[contains(@class,'relatedNewsCol')]");
	public static By objStripBelowPlayer=By.xpath("//div[@class='teaser-container']//span");
	public static By objWatchButton=By.xpath("//div[contains(@class,'slick-slide slick-active slick-center slick-current')]//div//span[contains(text(),'Watch')]");
	public static By objPublishDateAndMin=By.xpath("(//div[@class='cardMeta']//span)[1]");
	public static By objTitle=By.xpath("//span[text()='<title>']//parent::td");

	public static By objAfterClickNextArrowStoriesTitle=By.xpath("(//*[@class='cardTitle'])[6]");
	
	public static By objFooterLinks(String text) {
		return By.xpath("//*[@class='Collapsible']//*[contains(text(),'" + text + "')]");
	}
	public static By objChannelsList=By.xpath("(//*[@class='Collapsible__contentInner']//ul)[3]");
	
	public static By objListOfChannel=By.xpath("//*[@class='channelDetailContainer']//h1[contains(text(),'List of')]");
	
	public static By objFirstChannelCard=By.xpath("(//div[@class='viewAllWrap']//img)[1]");
	
	public static By objGrievanceFooter=By.xpath("(//a[contains(@class,'noSelect') and text()='Grievance Redressal'])[2]");
	
	public static By objGrievance=By.xpath("//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'Grievance Redressal')]");
	
	public static By objGrievanceHeader=By.xpath("//div[contains(@class,'KbDetailLtContainer__articleTitle')]");
	
	public static By objOTPScreenToLogin=By.xpath("//*[@class='floatingLabelInput otpInputWrap']");
	public static By objEnterPasswordInOTPScreen=By.xpath("//*[contains(@class,'verifyBtn enterPwdVmn')]");
	public static By objPopUpProceedBtn=By.xpath("//*[@class='popupBtn']");
	public static By objParentalLockNoRestrictionOption = By.xpath("//span[contains(text(),'No Restrictions')]");
	public static By objAuthenticationOption = By.xpath("//*[@href='/device' and contains(text(),'Activate TV')]");
	public static By objTvLogo=By.xpath("//span[@class='tvImage iconOther-ic_authenticate_device']");
	public static By objPageHeader=By.xpath("//*[@class='pageHeading']");
	public static By objPagedesc=By.xpath("//*[@class='pageDesc']");
	public static By objOTPInActivedevice=By.xpath("//*[contains(@class,'floatingLabelInput fpotpInputWrap')]");
	public static By objActiveButtonDevice=By.xpath("//*[contains(@class,'buttonContainer')]");
	
	public static By objOTP5 = By.xpath("//input[@name='otp5']");
	public static By objOTP6 = By.xpath("//input[@name='otp6']");

	public static By objHeaderMenuTabs=By.xpath("(//*[contains(@class,'navMenuWrapper')]/ul)[1]/li/a");
	public static By objTabOverLay=By.xpath("//div[contains(@class,'megaMenu megaMenuCards cardTypetvshows')]");
	
	public static By objJoyStickIcon=By.xpath("//div[@class='joystickIcon premiumIcon iconInitialLoad-ic_game']");

	public static By objKannadaDisplayRadio=By.xpath("//label[@for='select_kn']//span[@class='radioInner']");

	public static By objEnglishActivevalidation=By.xpath("//div[contains(@class, 'checkboxWrap checkedHighlight')]//*[contains(text(), 'English') and @class='commonName']");

	public static By objAgeRating=By.xpath("//*[@class='info-container']");
	public static By objcontentRating = By.xpath("(//*[@class='showMetaClass']//p)[4]");

	
	public static By objTitleOnCard=By.xpath("//*[contains(@class,'popupTitle')]//span");
	public static By objDuration=By.xpath("//*[contains(@class,'popupMetaDetails ')]//span[@class='duration']");
	public static By objGenre=By.xpath("//*[contains(@class,'popupMetaDetails ')]//span[@class='genre2']");

	public static By objChangePasswordCTA = By.xpath("//div[contains(@class, 'noSelect bannerContainer')]");
	
	public static By objBuyPlanUnit = By.xpath("//div[contains(@class, 'noSelect bannerContainer')]");
	
	public static By objSettingsOption=By.xpath("//h5[.='Settings']");

	public static By objBrowseAllPacks = By.xpath("//button[contains(@class,'noSelect purpleBtn purpleColorBgColor')]");

	public static By objMoreButtonWithTrayTitle(String trayTitle) {
		return By.xpath("//*[contains(text(),'"+trayTitle+"')]//ancestor::*[@class='trayHeader']//following-sibling::*//div[contains(@class,'view-all')]");
	}
	
	public static By objSelectCategoryDropDownMenu = By.xpath("//button[@id='options']//img");

	public static By objTermsOfUseInfo = By.xpath("//div[@class='staticPageContainer']");
	
	public static By objPremiumicon=By.xpath("//*[contains(@class,'trayHeader')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'cardPremiumContent')]");

	public static By objAllChannelsFilterOption=By.xpath("//*[contains(@class,'filterComponent filterComponentAllItem filterComponentFixedBottom')]");
	
	public static By objFirstcardTitleofBrowseByGenre(String trayTitle) {
		return By.xpath("(//*[contains(text(),'"+trayTitle+"')]//ancestor::*[@class='trayHeader']//following-sibling::*//h3[contains(@class,'cardTitle')])[1]");
	}
	
	public static By objSecondcardTitleofBrowseByGenre(String trayTitle) {
		return By.xpath("(//*[contains(text(),'"+trayTitle+"')]//ancestor::*[@class='trayHeader']//following-sibling::*//h3[contains(@class,'cardTitle')])[2]");
	}

	public static By objThirdcardTitleofBrowseByGenre(String trayTitle) {
		return By.xpath("(//*[contains(text(),'"+trayTitle+"')]//ancestor::*[@class='trayHeader']//following-sibling::*//h3[contains(@class,'cardTitle')])[3]");
	}
	
	public static By objLanguageOption=By.xpath("//div[@class='checkboxWrap ' or @class='checkboxWrap checkedHighlight']");
	
	public static By objContentTitleInLiveTV=By.xpath("//h3[@class='cardTitle']");
	
	public static By objWatchCTAOnCarousel=By.xpath("//*[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'playIcon') and following-sibling::*]");
	public static By objEditProfileFirstName = By.xpath("//*[@name='fullName']");
	public static By objEditProfileSavechangesBtn = By.xpath("//span[text()='Save Changes']//parent::button[contains(@class,'noSelect purpleBtn')]");
	public static By objSettingInHamburger = By.xpath("(//*[contains(text(),'Settings')])[2]");
	public static By objResetSettingsToDefault = By.xpath("(//*[@class='menuGroup active']//a)[3]");
	public static By objNoRestrictionSelected = By.xpath("//span[contains(text(),'No Restrictions')]");
	public static By objRestrictAll = By.xpath("//span[contains(text(),'Restrict All Content')]");
	public static By objActivatedevice = By.xpath("//*[@href='/device' and contains(text(),'Activate TV')]");
	public static By objTvodCardDetails = By.xpath("//div[@class='tvodCardDetails']");
	public static By objZeeplexContent = By.xpath("//*[contains(@class,'tvodCardDetails')]");
	public static By objWatchNow = By.xpath("//*[text()='Watch Now']");
	public static By objPageCombo=By.xpath("//div[@class='titleSubTitleComp']//h1[contains(text(),'Movie + Premium Offer')]");
	public static By objFirstCardFromContentDetailsPage = By.xpath("(//div[@class='slick-track']//img)[1]");
	public static By objKidsMovies=By.xpath("(//*[contains(@class,'trayHeader')]//*[contains(text(),'Movies')])[1]");
	public static By objFirstCardFromCollectionPage =By.xpath("(//div[@class='viewAllGrid']//div[@class='content']//img)[1]");
	public static By objEditProfileGoBackBtn = By.xpath("//div[@class='goBackBtn']");
	public static By objChangePasswordGoBackBtn = By.xpath("//*[@class='noSelect btnContnet'] | //*[@class='noSelect backBtn iconOther-ic_back']");
	public static By objSearchCloceicon = By.xpath("//*[@class='noSelect backBtnDesktop iconInitialLoad-ic_close']");
	public static By objLanguageBtn = By.xpath("//div[contains(@class,'noSelect languageBtn  iconInitialLoad-ic_language')]");
	public static By objHeaderTabs(int index){
		return By.xpath("(//a[contains(@class,'noSelect active regionalLang kn_regionalLang')])[\"+ index + \"]");
	}

	
}