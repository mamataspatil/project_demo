package com.business.zee;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import com.deviceDetails.DeviceDetails;
import com.driverInstance.CommandBase;
import com.emailReport.GmailInbox;
import com.extent.ExtentReporter;
//import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.mixpanelValidation.Mixpanel;
import com.mixpanelValidation.MixpanelAndroid;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.*;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Zee5ApplicasterMixPanelBusinessLogic extends Utilities {

	public Zee5ApplicasterMixPanelBusinessLogic(String Application) {
		new CommandBase(Application);
		init();
	}

	private int timeout;

	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	@Override
	public int getTimeout() {
		return timeout;
	}

	@Override
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	GmailInbox gmail = new GmailInbox();

	MixpanelAndroid mixpanel = new MixpanelAndroid();
	String pUserType = getParameterFromXML("userType");

	String FirstName = getParameterFromXML("FirstName");
	String LastName = getParameterFromXML("LastName");
	String generatedEmailId = null;

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
//		logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void relaunchTillDisplayLanguageScreen(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
		accessDeviceLocationPopUp("Allow", userType);
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objContinueBtnInCountryPopUp)) {
			click(AMDOnboardingScreen.objContinueBtnInCountryPopUp, "Continuebutton(Country_Screen)");
		}
	}

	public void relaunchTillIntroScreen(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
		accessDeviceLocationPopUp("Allow", userType);
		navigateToIntroScreen_DisplaylangScreen();
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
		if (userType != "Guest" & clearData == false) {
			System.out.println("Navigates to Landing Sccreen..");
		}
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch2(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
		accessDeviceLocationPopUp("Allow", userType);
		navigateToIntroScreen_DisplaylangScreen();
		ZeeApplicasterMixPanelLoginForParentalControl(userType);
	}

	/**
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();
	}

	// Retrieve the Mobile Device Name
	String getOEMName = DeviceDetails.OEM;

	/**
	 * Function to access the device location
	 */
	public void accessDeviceLocationPopUp(String permission, String userType) throws Exception {
		extent.HeaderChildNode("Access Device Location PopUp");
		extent.extentLogger("User Type", "UserType : " + userType);
		logger.info("UserType : " + userType);
		System.out.println("Access Device Location PopUp");

		waitTime(5000);
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objCancelZEE5Update)) {
			click(AMDOnboardingScreen.objCancelZEE5Update, "No Thanks");
		}

		if (verifyIsElementDisplayed(AMDOnboardingScreen.objContinueBtnInDebugBuild)) {
			click(AMDOnboardingScreen.objContinueBtnInDebugBuild, "Continue button");
		}
		if (checkElementExist(AMDOnboardingScreen.objAllowBtn)) {
			Wait(5000);
			verifyElementPresent(AMDOnboardingScreen.objAllowBtn, "Allow button");
			verifyElementPresent(AMDOnboardingScreen.objDenyBtn, "Deny button");

			if (permission.equalsIgnoreCase("Allow")) {
				click(AMDOnboardingScreen.objAllowBtn, "Allow button");
			} else {
				click(AMDOnboardingScreen.objDenyBtn, "Deny button");
			}
		}
	}

	/**
	 * Functon to navigate to Intro screen
	 */
	public void navigateToIntroScreen_DisplaylangScreen() throws Exception {
		extent.HeaderChildNode("Navigation to Intro Screen");
		waitTime(20000);
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objUpdatePopUpNoThanksOption)) {
			click(AMDOnboardingScreen.objUpdatePopUpNoThanksOption, "No Thanks on Update popUp");
		}
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objContinueBtnInCountryPopUp)) {
			click(AMDOnboardingScreen.objContinueBtnInCountryPopUp, "Continuebutton(Country_Screen)");
		}
//		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
//		verifyElementPresent(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
	}

	String Username;
	String Password;

	/**
	 * Function to Login to Zee5
	 */
	public void ZeeApplicasterLogin(String LoginMethod) throws Exception {

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Login as Guest User");
			extent.extentLoggerPass("Accessing the application as Guest user",
					"Accessing the application as Guest user");

			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			Username = getParameterFromXML("NonsubscribedUserName");
			Password = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");

			Username = getParameterFromXML("SubscribedUserName");
			Password = getParameterFromXML("SubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "ClubUser":
			extent.HeaderChildNode("Login as Club User");

			String ClubUsername = getParameterFromXML("ClubSubscribedUserName");
			String ClubPassword = getParameterFromXML("ClubSubscribedPassword");

			verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, ClubUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, ClubPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Function to verify Skip Login
	 */
	public void verifySkipLoginEvent(String usertype) throws Exception {
		extent.HeaderChildNode("Verify Skip login event");
		if (usertype.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip link");
			mixpanel.FEProp.setProperty("Element", "Skip");
			mixpanel.FEProp.setProperty("Source", "Intro");
			mixpanel.ValidateParameter("", "Skip Login");
		}
	}

	/**
	 * Function to verify Skip login by skipping LoginPopUp while content playback
	 * 
	 * @throws Exception
	 */
	public void verifySkipLogin_LoginInRegistrationPopUp(String usertype, String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip login event in Login or Register popUp during content playback");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword1 + "\n", "Search bar");
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitForAdToFinishInAmd();
			waitTime(5000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objRegisterPopUp, "Register popUp")) {
				verifyElementPresentAndClick(AMDPlayerScreen.objLoginButtonInRegisterPopUp, "Login link");
			}
			verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip link");
		}
	}

	/**
	 * Function to handle Ad
	 */
	public void waitForAdToFinishInAmd() {
		waitTime(20000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objAd)) {
			logger.info("Ad is playing");
			extentLogger("Ad", "Ad is playing");

			verifyElementNotPresent(AMDPlayerScreen.objAd, 200);

			logger.info("Ad is completed");
			extentLogger("Ad", "Ad is completed");
		} else {
			logger.info("Ad is not played");
			extentLogger("Ad", "Ad is not played");
		}
	}

	/**
	 * Function to close the Register popUp
	 */
	public void registerPopUpClose() throws Exception {
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objRegisterPopUp)) {
			logger.info("Register Pop Up is displayed");
			extent.extentLogger("Register Pop Up", "Register Pop Up is displayed");
			click(AMDGenericObjects.objPopUpDivider, "Close PopUp");
		}
	}

	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInMandatoryRegistartionPopUp(String userType,
			String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Mandatory Registartion PopUp");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword1 + "\n", "Search bar");
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitForAdToFinishInAmd();
			waitTime(5000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objRegisterPopUp, "Register popUp")) {
				verifyElementPresentAndClick(AMDPlayerScreen.objLoginButtonInRegisterPopUp, "Login link");
				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				String Username = getParameterFromXML("NonsubscribedUserName");
				type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				waitTime(3000);
				verifyIsElementDisplayed(AMDLoginScreen.objLoginScreenTitle, "Login screen title");
			}
		}
	}

	public void verifyLoginInitiatedEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event for Valid Credentials");
			socialLogin(loginMethod);
		}

	}

	public void socialLogin(String LoginMethod) throws Exception {
		switch (LoginMethod) {

		case "twitterLogin":
			twitterLogin();
			waitTime(3000);
			break;

		case "fbLogin":
			facebookLogin();
			waitTime(3000);
			break;

		}
	}

	public void twitterLogin() throws Exception {
		extent.HeaderChildNode("Verify Login Initiated Event for Valid Credentials through Twitter");

		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
		waitTime(3000);
		waitForElementDisplayed(AMDLoginScreen.objtwitterBtn, 10);
		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter icon");
		waitTime(10000);
		if (verifyIsElementDisplayed(AMDHomePage.objHome, "Home tab")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
			logout();
		} else if (verifyIsElementDisplayed(AMDLoginScreen.objTwitterEmail, "Email Field")) {
			verifyElementPresent(AMDLoginScreen.objTwitterEmail, " Email Field");
			type(AMDLoginScreen.objTwitterEmail, "zee5latest@gmail.com", "Email Field");

			verifyElementPresent(AMDLoginScreen.objTwitterPassword, " Password Field");
			type(AMDLoginScreen.objTwitterPassword, "User@123", "Password Field");

//			verifyElementPresentAndClick(AMDLoginScreen.objLoginButtonInTwitterPage, "Login Button");
			waitTime(2000);
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDLoginScreen.objtwAuthorizeAppBtn, "Authorize App");

			waitForElementDisplayed(AMDHomePage.objHome, 20);
			if (checkElementDisplayed(AMDHomePage.objHome, "Home tab")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
				logout();
			} else {
				logger.info("User is not logged in Successfully");
				extent.extentLoggerFail("Logged in", "User is not logged in Successfully");
				Back(1);
			}
		} else {
			click(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
		}
	}

	public void facebookLogin() throws Exception {
		extent.HeaderChildNode("Verify Login Initiated Event for Valid Credentials through Facebook");
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
		waitTime(3000);
		waitForElementDisplayed(AMDLoginScreen.objfbBtn, 10);
		verifyElementPresentAndClick(AMDLoginScreen.objfbBtn, "Facebook icon");
		waitTime(10000);

		if (verifyIsElementDisplayed(AMDHomePage.objHome, "Home tab")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
		} else {
			verifyElementPresent(AMDLoginScreen.objFBEmail, " Email Field");
			type(AMDLoginScreen.objFBEmail, "igstesttt@gmail.com", "Email Field");

			verifyElementPresent(AMDLoginScreen.objPasswordFieldInFBPage, " Password Field");
			type(AMDLoginScreen.objPasswordFieldInFBPage, "Igs123!@#", "Password Field");

			verifyElementPresentAndClick(AMDLoginScreen.objFBLoginBtn, "Login button");

			waitForElementDisplayed(AMDHomePage.objHome, 40);
			if (verifyIsElementDisplayed(AMDHomePage.objHome, "Home tab")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}
		}
		logout();
	}

	public void verifyLoginResultEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event for Valid Credentials");
			socialLogin(loginMethod);
		}
	}

	public void relaunchToIntroScreen(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
		accessDeviceLocationPopUp("Allow", userType);
		navigateToIntroScreen_DisplaylangScreen();
	}

	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event by clicking on Buy subscription in more menu");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Subscription option");

		}
	}

	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyCarouselBannerClickEvent() throws Exception {
		extent.HeaderChildNode(
				"Verify Carousel Banner Click Event And Video View Event For content played from Carousel");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "Carousel Content");

		setFEProperty(userType);

		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.ValidateParameter("", "Carousal Banner Click");

	}

	public void SelectTopNavigationTab(String pTabname) throws Exception {
		System.out.println("\nSelecting " + pTabname + " from Top navigation tabs");

		verifyElementPresentAndClick(AMDHomePage.objHome, "Home button");
		int noOfTabs = getCount(AMDHomePage.objTitle);
		System.out.println("\nTop Navigation Tabs: " + noOfTabs);
		for (int k = 1; k <= noOfTabs; k++) {
			if (verifyIsElementDisplayed(AMDGenericObjects.objPageTitle(pTabname))) {
				click(AMDGenericObjects.objPageTitle(pTabname), pTabname);
				break;
			} else {
				List<WebElement> element = getDriver().findElements(By.xpath("(//*[@id='homeTabLayout']/*/child::*)"));
				element.get(noOfTabs - 1).click();
				waitTime(1000);
			}
		}
	}

	public void verifyThumbnailClickEventFromTray() throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays");
		waitTime(5000);
		for (int i = 0; i < 5; i++) {
			boolean var = verifyIsElementDisplayed(AMDHomePage.objFirstThumbnailOfTray, "Thumbnail from a tray");
			if (var == false) {
				Swipe("Up", 1);
			} else {
				break;
			}
		}
		verifyElementPresentAndClick(AMDHomePage.objFirstThumbnailOfTray, "Thumbnail from a tray");
	}

	public void verifyThumbnailClickEventFromTrayInPlayBackPage(String keyword2) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays in playback page");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		for (int i = 0; i < 5; i++) {
			boolean var = verifyIsElementDisplayed(AMDConsumptionScreen.objContentCardOfTrayInConsumptionPage,
					"Thumbnail in playback page");
			if (var == false) {
				Swipe("Up", 1);
			} else {
				break;
			}
		}
		verifyElementPresentAndClick(AMDConsumptionScreen.objContentCardOfTrayInConsumptionPage,
				"Thumbnail in playback page");
	}

	@SuppressWarnings("deprecation")
	public void verifyParentalRestrictionEvent(String userType, String restriction) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Parental Restriction Event");
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			if (restriction.equalsIgnoreCase("Age13+")) {
				click(AMDMoreMenu.objRestrict13Above, "Restrict 13+ Content option");
				click(AMDMoreMenu.objContinueBtn, "Continue Button");
				waitTime(2000);

				verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
				type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(4000);
				click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
				waitTime(2000);
				click(AMDMoreMenu.objParentalLockDone, "Done Button");
			} else if (restriction.equalsIgnoreCase("Restrict All")) {
				click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
				click(AMDMoreMenu.objContinueBtn, "Continue Button");
				waitTime(2000);
				verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
				type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(4000);
				click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
				waitTime(2000);
				click(AMDMoreMenu.objParentalLockDone, "Done Button");
			}

			waitTime(3000);
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel(String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Subscription Page Viewed event gets triggered, When user is navigating to Subscirption page by clicking on Get Premium CTA on carousel");
			waitTime(5000);
			verifyElementPresentAndClick(AMDHomePage.objGetPremiumCTAOnCarousel, "Caorsel Content");
			waitTime(4000);
		}
	}

	public void verifySubscriptionPageViewedEventByClickingSubscriptionbelowThePlayer(String usertype, String keyword2)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Subscription page viewed event by clicking Subscription CTA below the player in consumption page");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeButtonBelowThePlayer,
					"Get premium CTA below the Player in consumption page");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyScreenViewEvent(String screen, String userType) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event");
		SelectTopNavigationTab(screen);
		waitTime(3000);
		setFEProperty(userType);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "tvshows");
		mixpanel.ValidateParameter("", "Screen View");
	}

	public void verifyViewMoreSelectedEventFromTray() throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Tray");
		waitTime(5000);
		for (int i = 0; i < 5; i++) {
			boolean var = verifyIsElementDisplayed(AMDHomePage.objFirstViewAllbtn, "View All option a tray");
			if (var == false) {
				Swipe("Up", 1);
			} else {
				break;
			}
		}
		verifyElementPresentAndClick(AMDHomePage.objFirstViewAllbtn, "View All option a tray");
	}

	public void verifyViewMoreSelectedEventFromPlaybackPage(String keyword2) throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Playback page");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		for (int i = 0; i < 5; i++) {
			boolean var = verifyIsElementDisplayed(AMDHomePage.objFirstViewAllbtn, "View All option a tray");
			if (var == false) {
				Swipe("Up", 1);
			} else {
				break;
			}
		}
		verifyElementPresentAndClick(AMDHomePage.objFirstViewAllbtn, "View All option a tray");

	}

	public void clearSearchHistoryEvent(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Clear Search History Event");
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
			waitTime(300);
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSettingsScreen.objClearSearchHistory, "Clear Search Histroy");
			waitTime(4000);

			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Page Name", "user_setting");
			mixpanel.FEProp.setProperty("Source", "More");
			mixpanel.FEProp.setProperty("Element", "Clear");
			mixpanel.FEProp.setProperty("Setting Changed", "User Cleared History");
			mixpanel.ValidateParameter("", "Clear Search History");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySearchButtonClickEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Search Button Click Event");
		waitTime(5000);
		String Username = "";
		String Password = "";
		if (userType.equals("NonSubscribedUser")) {
			Username = getParameterFromXML("NonsubscribedUserName");
			Password = getParameterFromXML("NonsubscribedPassword");
		} else {
			Username = getParameterFromXML("SubscribedUserName");
			Password = getParameterFromXML("SubscribedPassword");

		}

		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(3000);

		if (userType.equals("Guest")) {
			mixpanel.FEProp.setProperty("Old Content Language:",
					ResponseInstance.getUserOldSettingsDetails("", "").getProperty("content_language"));
		} else {
			mixpanel.FEProp.setProperty("Old Content Language:",
					ResponseInstance.getUserOldSettingsDetails(Username, Password).getProperty("content_language"));
		}
		setFEProperty(userType);

		mixpanel.FEProp.setProperty("Page Name", "HomePage");
		mixpanel.FEProp.setProperty("Element", "Search Icon");
		mixpanel.ValidateParameter("", "Search Button Click");
	}

	@SuppressWarnings("static-access")
	public void verifySearchExecutedEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Executed Event");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		String searchtxt = "Kam";
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchtxt + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		waitTime(3000);
		MixpanelAndroid.FEProp.setProperty("page_name", "Search");
		MixpanelAndroid.FEProp.setProperty("tab_name", "All");
		MixpanelAndroid.ValidateParameter("", "Search Executed");

	}

	public void verifySearchResultClickedEvent(String keyword2) throws Exception {
		extent.HeaderChildNode("Verify Search Result click Event");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(3000);
	}

	@SuppressWarnings("static-access")
	public void verifyVideoQualityChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify video quality change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		boolean var = verifyIsElementDisplayed(AMDMoreMenu.objSelectedVideoQualityOption("Auto"));
		if (var == true) {
			click(AMDMoreMenu.objVideo_Quality("Auto"), "Video quality option");
			click(AMDSettingsScreen.objVideoQualityBetter, "option Better");
			click(AMDMoreMenu.objVideo_Quality("Better"), "Video quality option");
			click(AMDMoreMenu.objAutoOption, "option Auto");

			mixpanel.FEProp.setProperty("Source", "More");
			mixpanel.FEProp.setProperty("Page Name", "selector");
			mixpanel.ValidateParameter("", "Video Streaming Quality Changed");

		} else {
			logger.error("the default selection in the Select Video Quality is not 'Auto' option");
			extentLoggerWarning("Default selected Video quality option",
					"the default selection in the Select Video Quality is not 'Auto' option");
		}

	}

	public void verifyVideoAutoPlayChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify video AutoPlay change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		String elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		if (elementAutoPlayToggleStatus.equalsIgnoreCase("ON")) {
			click(AMDMoreMenu.objVideo_Autoply, "Video Auto play toggle");
		} else {
			logger.info("the default state of the 'Auto Play' option is not in ON state");
			extentLoggerWarning("Video Auto Play", "the default state of the 'Auto Play' option is not in ON state");
		}
	}

	public void verifyDisplayLanguageChangeEvent(String usertype) throws Exception {
		extent.HeaderChildNode("Verify display language change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		waitTime(1000);
		SwipeUntilFindElement(AMDMoreMenu.objDisplayLang, "Up");
		click(AMDMoreMenu.objDisplayLang, "Display language");

		boolean flag = false;
		flag = verifyElementPresentAndClick(AMDOnboardingScreen.objSelectDisplayLang("Marathi"), "language");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");

		if (flag) {

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);

			mixpanel.FEProp.setProperty("Source", "user_setting");
			mixpanel.FEProp.setProperty("Page Name", "DisplayLanguage");
			mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);

			if (!(pUserType.equalsIgnoreCase("Guest"))) {
				if (pUserType.equalsIgnoreCase("SubscribedUser")) {
					Username = getParameterFromXML("SubscribedUserName");
					Password = getParameterFromXML("SubscribedPassword");
				} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
					Username = getParameterFromXML("NonsubscribedUserName");
					Password = getParameterFromXML("NonsubscribedPassword");
				}
				mixpanel.FEProp.setProperty("Old App Language",
						ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("display_language"));
			} else {
				mixpanel.FEProp.setProperty("User Type", "guest");
				mixpanel.FEProp.setProperty("Old App Language", "en");
			}

			mixpanel.FEProp.setProperty("New App Language", "mr");

			mixpanel.ValidateParameter("", "Display Language Changed");

			SwipeUntilFindElement(AMDMoreMenu.objDisplayLang, "Up");
			click(AMDMoreMenu.objDisplayLang, "Display language");
			verifyElementPresentAndClick(AMDOnboardingScreen.objSelectDisplayLang("English"), "language");
			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");

		} else {
			logger.info("Failed to change display language");
			extentLoggerWarning("Event", "Failed to change display language");
		}

	}

	public void verifyContentLanguageChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify Content language change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		waitTime(1000);
		SwipeUntilFindElement(AMDMoreMenu.objContentLang, "Up");
		click(AMDMoreMenu.objContentLang, "Content language");
		click(AMDOnboardingScreen.objSelectContentLang("English"), "English language");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in Content language screen");

		mixpanel.FEProp.setProperty("Source", "user_setting");
		mixpanel.FEProp.setProperty("Page Name", "ContentLanguage");
		mixpanel.ValidateParameter("", "Content Language Changed");
		;

	}

	public boolean SwipeUntilFindElement(By locator, String direction) throws Exception {

		boolean checkLocator, eletFound = false;
		if (direction.equalsIgnoreCase("UP")) {
			for (int i = 1; i < 25; i++) {
				PartialSwipe("UP", 1);
				checkLocator = verifyIsElementDisplayed(locator);
				if (checkLocator) {
					eletFound = true;
					break;
				}
			}
		}

		if (direction.equalsIgnoreCase("DOWN")) {
			for (int i = 1; i < 25; i++) {
				PartialSwipe("DOWN", 1);
				checkLocator = verifyIsElementDisplayed(locator);
				if (checkLocator) {
					eletFound = true;
					break;
				}
			}
		}
		return eletFound;
	}

	@SuppressWarnings("static-access")
	public void verifyDefaultSettingRestoredEvent() throws Exception {
		extent.HeaderChildNode("Verify Default Setting Restored Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		Swipe("Up", 2);
		verifyElementPresentAndClick(AMDSettingsScreen.objDefaultSetting, "Default Setting Link");
		verifyElementPresentAndClick(AMDSettingsScreen.objYesCTA, "Yes CTA");

		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
		}
		mixpanel.FEProp.setProperty("Element", "Restore settings to default");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);
		mixpanel.ValidateParameter("", "Default Settings Restored");
	}

	public void ZeeApplicasterMixPanelLoginForParentalControl(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");

		String UserType = getParameterFromXML("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			String Username = getParameterFromXML("ParentalNonsubscribedUserName");
			String Password = getParameterFromXML("ParentalNonsubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");

			String SubscribedUsername = getParameterFromXML("ParentalSubscribedUserName");
			String SubscribedPassword = getParameterFromXML("ParentalSubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		}
	}

	public void verifyCarouselBannerSwipeEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Carousel Banner Swipe Event Across tabs");
		SelectTopNavigationTab(tabName);
		waitForElementDisplayed(AMDHomePage.objCarouselDots, 10);
		waitTime(10000);

		if (verifyElementDisplayed(AMDHomePage.objBannerAd)) {
			verifyElementPresent(AMDHomePage.objCarouselUnitwithmastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		} else {
			verifyElementPresent(AMDHomePage.objCarouselUnitwhenNomastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		}

		// Validating Carousel manual swipe
		String width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);

		String bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];

		waitTime(3000);
		String Carouseltitle1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle1);
		extentLoggerPass("Carousel Title", Carouseltitle1);
		carouselCardsSwipe("LEFT", 1, width, height);

		String Carouseltitle2 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle2);
		extentLoggerPass("Carousel Title", Carouseltitle2);
		carouselCardsSwipe("RIGHT", 1, width, height);
	}

	@SuppressWarnings("rawtypes")
	public void carouselCardsSwipe(String direction, int count, String width, String height) throws Exception {
		touchAction = new TouchAction(getDriver());

		try {

			int yCordinate;
			if (verifyElementIsNotDisplayed(AMDHomePage.objAdBannerAboveCarousel)) {
				yCordinate = (int) ((Integer.valueOf(height)) * 0.4);
			} else {
				yCordinate = (int) ((Integer.valueOf(height)) * 0.5);
			}

			if (direction.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {

					int startx = (Integer.valueOf(width));
					startx = (int) (startx * 0.8);
					int endx = (int) (startx * 0.1);

					int starty = (Integer.valueOf(height)) + yCordinate;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
					extent.extentLoggerPass("SwipeLeft",
							"Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");

					System.out.println("\n<<< Swipe <<<");
					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
				}
			} else if (direction.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					int startx = (int) ((Integer.valueOf(width)) * 0.1);
					int endx = (int) ((Integer.valueOf(width)) * 0.8);
					int starty = (Integer.valueOf(height)) + yCordinate;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();

					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
					extent.extentLoggerPass("SwipeRight",
							"Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");

					System.out.println("\n>>> Swipe >>>");
					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
			hideKeyboard();
			// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
			setFEProperty(userType);
			mixpanel.ValidateParameter("", "Popup launch");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String usertype, String searchKeyword) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when Complete Profile popup is displayed");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
			hideKeyboard();
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
			// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");

			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
			}
			completeProfilePopUpClose(usertype);

			setFEProperty(userType);
			mixpanel.ValidateParameter("", "Popup launch");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when Native popup is displayed");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
			// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");

			if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
			}
			registerPopUpClose();
			setFEProperty(userType);
			mixpanel.ValidateParameter("", "Popup launch");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPopUpCTAsEvent(String userType, String keyword2) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Pop Up CTA's Event when user clicks on CTA button displayed on the popup");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
			hideKeyboard();
			// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDConsumptionScreen.objProceedBtnOnSubscribePopUpInPlayerScreen,
					"Proceed CTA");
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Element", "Proceed");
			mixpanel.ValidateParameter("", "Pop Up CTAs");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEvent(String userType, String cta) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		waitTime(3000);

		if (cta.equalsIgnoreCase("Tab")) {
			SelectTopNavigationTab(cta);
		}
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (cta.equalsIgnoreCase("Subscribe")) {
				verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
				waitTime(3000);
				setFEProperty(userType);
				setUserType_SubscriptionProperties(userType);
				SetAppsflyerProperty();

				MixpanelAndroid.FEProp.setProperty("Element", "Buy Plan");
				MixpanelAndroid.FEProp.setProperty("Page Name", "HomePage");
			}
		}
		MixpanelAndroid.ValidateParameter("", "CTAs");
	}

	public void findingTrayInscreen(int j, By byLocator1, By byLocator2, String str1, String str2, String userType,
			String tabName) throws Exception {
		boolean tray = false;
		for (int i = 0; i < j; i++) {
			if (!((verifyIsElementDisplayed(byLocator1)))) {
				Swipe("UP", 1);
			} else {
				verifyElementExist(byLocator1, str1);
				tray = true;
				if (tabName.equalsIgnoreCase("Home")) {
					if (str1.equalsIgnoreCase("Continue watching tray")) {

						Response respCW = ResponseInstance.getRespofCWTray(userType);

						List<String> ApinoOfContentsInCW = respCW.jsonPath().getList("array");
						logger.info("no.of contents in CW tray in Api " + ApinoOfContentsInCW.size());

						ArrayList<String> listOfContentsInCW = new ArrayList<String>();

						for (int k = 0; k < ApinoOfContentsInCW.size(); k++) {

							String title = respCW.jsonPath().getString("[" + k + "].title");
							listOfContentsInCW.add(title);
						}

						logger.info(listOfContentsInCW);

						for (int p = 0; p < ApinoOfContentsInCW.size(); p++) {

							verifyElementExist(AMDHomePage.objContentTitleOfCWTray(listOfContentsInCW.get(p)),
									"content title");

							if (verifyElementDisplayed(AMDHomePage.objLeftTimeOfFirstContentOfCWTray)) {
								logger.info("Left watch time info on cards is available");
								extent.extentLoggerPass("Left watch time info",
										"Left watch time info on cards is available");
							} else {
								logger.error("Left watch time info on cards is not available");
								extent.extentLoggerFail("Left watch time info",
										"Left watch time info on cards is not available");
							}
							if (verifyElementDisplayed(AMDHomePage.objProgressBarOfFirstContentOfCWTray)) {
								logger.info("Progress bar is displayed to indicate the content watched portion");
								extent.extentLoggerPass("Progress bar",
										"Progress bar is displayed to indicate the content watched portion");
							} else {
								logger.error("Progress bar is not displayed to indicate the content watched portion");
								extent.extentLoggerFail("Progress bar",
										"Progress bar is not displayed to indicate the content watched portion");
							}
							if (p != (ApinoOfContentsInCW.size() - 1)) {
								SwipeRail(AMDHomePage.objContentTitleOfCWTray(listOfContentsInCW.get(p + 1)));
							}
						}
					}
				}
				break;
			}
		}
		if (tray == false) {
			if (userType.equalsIgnoreCase("Guest")) {
				if (str1.equalsIgnoreCase("Continue watching tray")) {
					logger.info(str1 + " is not displayed for Guest user");
					extent.extentLoggerPass("Tray", str1 + " is not displayed for Guest user");
				} else {
					logger.error(str1 + " is not displayed");
					extent.extentLoggerWarning("Tray", str1 + " is not displayed");
				}
			} else {
				if (tabName.equalsIgnoreCase("Home")) {

					if (str1.equalsIgnoreCase("Continue watching tray")) {

						Response respCW = ResponseInstance.getRespofCWTray(userType);

						List<String> ApinoOfContentsInCW = respCW.jsonPath().getList("array");
						logger.info("no.of contents in CW tray in Api " + ApinoOfContentsInCW.size());

						if (ApinoOfContentsInCW.size() == 0) {

							logger.info(str1 + " is not displayed for this user");
							extent.extentLoggerPass("Tray", str1 + " is not displayed for this user");
						} else {
							logger.error(str1 + " is not displayed for this user");
							extent.extentLoggerWarning("Tray", str1 + " is not displayed for this user");
						}
					}
					logger.error(str1 + " is not displayed");
					extent.extentLoggerWarning("Tray", str1 + " is not displayed");
				} else {
					logger.error(str1 + " is not displayed");
					extent.extentLoggerWarning("Tray", str1 + " is not displayed");
				}
			}
		}
		for (int i = 0; i < j; i++) {
			if (!(verifyIsElementDisplayed(byLocator2))) {
				Swipe("DOWN", 1);
			} else {
				verifyElementExist(byLocator2, str2);
				break;
			}
		}
	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationDOBEnteredEvent(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Registration DOB entered event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			waitTime(5000);
			String pEmailID = generateRandomString(5) + "@gmail.com";
			type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");
			verifyElementPresent(AMDRegistrationScreen.objScreenTitle, "Register for Free screen");
			click(AMDRegistrationScreen.objDOBTxtField, "DOB");
			type(AMDRegistrationScreen.objDOBTxtField, "01/01/1990", "DOB");
			hideKeyboard();
			waitTime(3000);

			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Page Name", "Registration");
			mixpanel.FEProp.setProperty("Source", "LoginRegister");
			mixpanel.ValidateParameter("", "Registration DoB Entered");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationGenderEnteredEvent(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Registration Gender entered event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			waitTime(5000);
			String pEmailID = generateRandomString(5) + "@gmail.com";
			type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");
			verifyElementPresent(AMDRegistrationScreen.objScreenTitle, "Register for Free screen");
			verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");
			waitTime(3000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Page Name", "Registration");
			mixpanel.FEProp.setProperty("Source", "Login");
			mixpanel.ValidateParameter("", "Registration Gender Entered");
		}
	}

	public void verifyRegistrationUserNameEnteredEvent(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Registration userName entered event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			waitTime(5000);
			String pEmailID = generateRandomString(5) + "@gmail.com";
			type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");
			verifyElementPresent(AMDRegistrationScreen.objScreenTitle, "Register for Free screen");
			type(AMDRegistrationScreen.objFirstNameTxtField, FirstName, "First name field");
			hideKeyboard();
			type(AMDRegistrationScreen.objLastNameTxtField, LastName, "Last name field");
			hideKeyboard();
			waitTime(3000);

			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Page Name", "Registration");
			mixpanel.FEProp.setProperty("Source", "Login");
			mixpanel.ValidateParameter("", "Registration First Name Entered");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationPasswordEnteredEvent(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Registration Password entered event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			waitTime(5000);
			String pEmailID = generateRandomString(5) + "@gmail.com";
			type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");
			verifyElementPresent(AMDRegistrationScreen.objScreenTitle, "Register for Free screen");
			click(AMDRegistrationScreen.objPasswordTxtField, "Passowrd");
			type(AMDRegistrationScreen.objPasswordTxtField, "123456", "Password field");
			hideKeyboard();
			waitTime(3000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Page Name", "Registration");
			mixpanel.FEProp.setProperty("Source", "Login");
			mixpanel.ValidateParameter("", "Registration Password Entered");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Change Password started event");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			String oldPassword = "123456";
			String NewPassword = "654321";

			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			waitTime(5000);
			verifyElementPresentAndClick(AMDMyProfileScreen.objChangePassword, "Change Password");
			verifyElementPresentAndClick(AMDChangePasswordScreen.objCurrentPwdField, "Current Password field");
			type(AMDChangePasswordScreen.objCurrentPwdField, oldPassword, "Current Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objNewPwdField, "New Password field");
			type(AMDChangePasswordScreen.objNewPwdField, NewPassword, "New Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objConfirmPwdField, "Confirm Password field");
			type(AMDChangePasswordScreen.objConfirmPwdField, NewPassword, "Confirm Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objUpdateBtn, "Update button");
			waitTime(5000);

			verifyElementPresentAndClick(AMDMyProfileScreen.objChangePassword, "Change Password");
			verifyElementPresentAndClick(AMDChangePasswordScreen.objCurrentPwdField, "Current Password field");
			type(AMDChangePasswordScreen.objCurrentPwdField, NewPassword, "Current Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objNewPwdField, "New Password field");
			type(AMDChangePasswordScreen.objNewPwdField, oldPassword, "New Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objConfirmPwdField, "Confirm Password field");
			type(AMDChangePasswordScreen.objConfirmPwdField, oldPassword, "Confirm Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objUpdateBtn, "Update button");
			waitTime(3000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "ChangePassword");
			MixpanelAndroid.FEProp.setProperty("Source", "MyProfile");
			MixpanelAndroid.FEProp.setProperty("Element", "Update");

			MixpanelAndroid.ValidateParameter("", "Change Password Started");

		} else {
			logger.info("Change Password Event is Not applicable for Guest user");
			extentLogger("Change Password", "Change Password Event is Not applicable for Guest user");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			String oldPassword = "123456";
			String NewPassword = "1234567";

			extent.HeaderChildNode("Change Password result event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			waitTime(5000);
			verifyElementPresentAndClick(AMDMyProfileScreen.objChangePassword, "Change Password");
			verifyElementPresentAndClick(AMDChangePasswordScreen.objCurrentPwdField, "Current Password field");
			type(AMDChangePasswordScreen.objCurrentPwdField, oldPassword, "Current Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objNewPwdField, "New Password field");
			type(AMDChangePasswordScreen.objNewPwdField, NewPassword, "New Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objConfirmPwdField, "Confirm Password field");
			type(AMDChangePasswordScreen.objConfirmPwdField, NewPassword, "Confirm Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objUpdateBtn, "Update button");
			waitTime(5000);

			verifyElementPresentAndClick(AMDMyProfileScreen.objChangePassword, "Change Password");
			verifyElementPresentAndClick(AMDChangePasswordScreen.objCurrentPwdField, "Current Password field");
			type(AMDChangePasswordScreen.objCurrentPwdField, NewPassword, "Current Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objNewPwdField, "New Password field");
			type(AMDChangePasswordScreen.objNewPwdField, oldPassword, "New Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objConfirmPwdField, "Confirm Password field");
			type(AMDChangePasswordScreen.objConfirmPwdField, oldPassword, "Confirm Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDChangePasswordScreen.objUpdateBtn, "Update button");
			waitTime(3000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Page Name", "ChangePassword");
			MixpanelAndroid.FEProp.setProperty("Source", "MyProfile");
			MixpanelAndroid.FEProp.setProperty("Element", "ChangePassword");

			MixpanelAndroid.ValidateParameter("", "Change Password Result");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyVideoAutoPlayChangeEventForEnable() throws Exception {
		extent.HeaderChildNode("Verify video AutoPlay change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		String elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		if (elementAutoPlayToggleStatus.equalsIgnoreCase("ON")) {
			logger.info("the default state of the 'Auto Play' option is in ON state");
			extentLoggerWarning("Video Auto Play", "the default state of the 'Auto Play' option is in ON state");
		} else {
			click(AMDMoreMenu.objVideo_Autoply, "Video Auto play toggle");
			waitTime(4000);
			click(AMDMoreMenu.objVideo_Autoply, "Video Auto play toggle");

			mixpanel.FEProp.setProperty("Source", "More");
			mixpanel.FEProp.setProperty("Page Name", "user_setting");

			mixpanel.ValidateParameter("", "Video Streaming Autoplay Changed");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyVideoAutoPlayChangeEventforDisable() throws Exception {
		extent.HeaderChildNode("Verify video AutoPlay change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		String elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		if (elementAutoPlayToggleStatus.equalsIgnoreCase("ON")) {
			click(AMDMoreMenu.objVideo_Autoply, "Video Auto play toggle");
			waitTime(5000);
			click(AMDMoreMenu.objVideo_Autoply, "Video Auto play toggle");

			mixpanel.FEProp.setProperty("Source", "More");
			mixpanel.FEProp.setProperty("Page Name", "user_setting");
			mixpanel.ValidateParameter("", "Video Streaming Autoplay Changed");

		} else {
			logger.info("the default state of the 'Auto Play' option is not in ON state");
			extentLoggerWarning("Video Auto Play", "the default state of the 'Auto Play' option is not in ON state");
		}
	}

	public void verifyAddtoWatchlistFromPlaybackPageInPotrait(String userType, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event From Playback Page");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(3000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");
			waitTime(5000);

			setFEProperty(userType);

			mixpanel.FEProp.setProperty("Source", "Search_Tab");
			mixpanel.FEProp.setProperty("Element", "search_results");
			mixpanel.ValidateParameter("", "add to Watchlist");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyRemoveFromWatchListPlaybackPageInPotrait(String userType, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove from Watchlist Event From Playback Page");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(3000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");
			waitTime(5000);

			setFEProperty(userType);

			mixpanel.FEProp.setProperty("Source", "Search_Tab");
			mixpanel.FEProp.setProperty("Element", "search_results");
			mixpanel.ValidateParameter("", "add to Watchlist");
			mixpanel.ValidateParameter("", "Remove from Watchlist");

		}
	}

	public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
		if (userType.equalsIgnoreCase("ClubUser")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword6 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(3000);
			verifyElementPresentAndClick(AMDClubPack.objUpgradeToPremiumLinkOnPlayer, "Upgrade button on player");
			verifyIsElementDisplayed(AMDClubPack.objUpgradePopUp, "Upgrade popUp");
			waitTime(3000);
		}
	}

	@SuppressWarnings("static-access")
	public void verifyContentBucketSwipeEvent() throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event Across tabs");
		waitForElementDisplayed(AMDHomePage.objHomeTab, 10);
		click(AMDHomePage.objPremiumTab, "Premium tab");
		waitTime(10000);
		SwipeRail(AMDHomePage.objContent);
		waitTime(3000);
		setFEProperty(userType);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Tab Name", "premium");
		mixpanel.FEProp.setProperty("Page Name", "premium");

		mixpanel.ValidateParameter("", "Content Bucket Swipe");
	}

	@SuppressWarnings("static-access")
	public void verifyContentBucketSwipeEventInPlayBackPage(String keyword2) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event in playback page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(3000);
		SwipeRail(AMDHomePage.objContent);
		waitTime(3000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.ValidateParameter("", "Content Bucket Swipe");

	}

	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Profile Update Result Event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			waitTime(5000);
			verifyElementPresentAndClick(AMDProfileScreen.objEditBtn, "Edit");
			verifyElementPresentAndClick(AMDEditProfileScreen.objGenderDropdown, "Gender");
			String gender = getText(AMDEditProfileScreen.objSelectedGender);
			if (gender.equalsIgnoreCase("Male")) {
				click(AMDEditProfileScreen.objFemale, "Female option");
			} else {
				click(AMDEditProfileScreen.objMale, "Male option");
			}
			verifyElementPresentAndClick(AMDEditProfileScreen.objSaveChanges, "Save changes");

			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Source", "MyProfile");
			mixpanel.FEProp.setProperty("Settings Changed", "Profile Edit");
			mixpanel.FEProp.setProperty("Page Name", "EditProfile");
			mixpanel.FEProp.setProperty("Element", "Edit Profile");
			mixpanel.ValidateParameter("", "Setting Changed");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyAddtoWatchlistFromPlaybackPageInFullScreen(String userType, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event From Playback Page in Full screen");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");

			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objAddToWatchlist, "Add to Watchlist option");
			waitTime(6000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objAddToWatchlist, "Add to Watchlist option");
			waitTime(5000);

			setFEProperty(userType);

			mixpanel.FEProp.setProperty("Source", "Search_Tab");
			mixpanel.FEProp.setProperty("Element", "search_results");

			mixpanel.ValidateParameter("", "add to Watchlist");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyRemoveFromWatchlistFromPlaybackPageInFullScreen(String userType, String keyword3)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove from  Watchlist Event From Playback Page in Full screen");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");

//			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			JSClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objAddToWatchlist, "Add to Watchlist option");
			waitTime(6000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			JSClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			JSClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with 3 dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objAddToWatchlist, "Add to Watchlist option");
			waitTime(5000);
			setFEProperty(userType);

			mixpanel.FEProp.setProperty("Source", "Search_Tab");
			mixpanel.FEProp.setProperty("Element", "search_results");
			mixpanel.ValidateParameter("", "Remove from Watchlist");
		}
	}

	public void videoViewEventForPremiumContentInPotrait(String usertype, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Video View Event for premium content in potrait");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
					waitTime(3000);
					Back(1);
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		}
	}

	public void videoViewEventForPremiumContentInFullScreen(String usertype, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Video View Event for premium content in full screen");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
					waitTime(6000);
					click(AMDPlayerScreen.objPlayerScreen, "Player screen");
					verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
					waitTime(4000);
					Back(2);
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		}
	}

	public void videoViewEventForCarouselContentInPotrait(String tabName) throws Exception {
		extent.HeaderChildNode("Video View Event for carousel content in potrait");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 20);
		Boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (var == true) {
			logger.info("Player screen is displayed");
			extentLoggerPass("Player screen", "Player screen is displayed");
		} else if (verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer)) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerPass("Player screen", "Player inline subscription link is displayed");
		}
		Back(1);
	}

	public void videoViewEventForCarouselContentInFullScreen(String tabName) throws Exception {
		extent.HeaderChildNode("Video View Event for carousel content in full screen");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 20);
		Boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (var == true) {
			logger.info("Player screen is displayed");
			extentLoggerPass("Player screen", "Player screen is displayed");
			waitTime(6000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
			waitTime(4000);
			Back(2);
		} else if (verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer)) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerPass("Player screen", "Player inline subscription link is displayed");
		}
	}

	public void videoViewEventOfcontentFromSearchPageInPotrait(String keyword3) throws Exception {
		extent.HeaderChildNode("Video View Event of content from search page in potrait");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		Back(1);
	}

	public void videoViewEventOfcontentFromSearchPageInFullScreen(String keyword3) throws Exception {
		extent.HeaderChildNode("Video View Event of content from search page in Full screen");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		waitTime(6000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(4000);
		Back(2);
	}

	public void videoViewEventOfContentFromMyWatchListPageInPotrait(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Video View Event of content from My WatchList page in potrait");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			boolean contentsInMoviesTab = getDriver()
					.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
					.isDisplayed();
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
				Back(3);
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
				Back(2);
			}
		}

	}

	public void videoViewEventOfContentFromMyWatchListPageInFullScreen(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Video View Event of content from My WatchList page in full screen");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			boolean contentsInMoviesTab = getDriver()
					.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
					.isDisplayed();
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
				if (var == true) {
					logger.info("Player screen is displayed");
					extentLoggerPass("Player screen", "Player screen is displayed");
					waitTime(6000);
					click(AMDPlayerScreen.objPlayerScreen, "Player screen");
					verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
					waitTime(4000);
					Back(4);
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
				Back(2);
			}
		}
	}

	public void verifyRemoveFromWatchlistFromWatchListPage(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Remove an content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			boolean flag = false;
			click(AMDUserSessionManagement.objShowsTabUnderWatchList, "Shows Tab");
			if (verifyIsElementDisplayed(AMDWatchlistPage.objEditBtn)) {
				verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
				flag = true;
			} else {
				click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
				if (verifyIsElementDisplayed(AMDWatchlistPage.objEditBtn)) {
					verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
					flag = true;
				} else {
					click(AMDUserSessionManagement.objVideosTabUnderWatchList, "Videos Tab");
					if (verifyIsElementDisplayed(AMDWatchlistPage.objEditBtn)) {
						verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
						flag = true;
					} else {
						logger.info("No contents in watchlist to remove");
						extentLogger("Remove contents from watchlist page", "No contents in watchlist to remove");
					}
				}

			}
			if (flag == true) {
				verifyElementPresentAndClick(AMDWatchlistPage.objSelectContentByIndex(1), "check box");
				verifyElementPresentAndClick(AMDWatchlistPage.objDeleteAllBtn, "Delete All");

			}

		}

	}

	@SuppressWarnings("static-access")
	public void verifyVideoStreamOverWifiChangeEventForEnable() throws Exception {
		extent.HeaderChildNode("Verify video wifi change Event for Enable");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");
		waitTime(3000);

		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.ValidateParameter("", "Video Stream Over Wifi Changed");

	}

	@SuppressWarnings("static-access")
	public void verifyVideoStreamOverWifiChangeEventForDisable() throws Exception {
		extent.HeaderChildNode("Verify video wifi change Event for Disable");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");
		waitTime(3000);
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.ValidateParameter("", "Video Stream Over Wifi Changed");
	}

	@SuppressWarnings("static-access")
	public void verifyDownloadQualityChangeEvent(String option) throws Exception {
		extent.HeaderChildNode("Verify Download quality change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old Download Quality Setting",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("download_quality"));
		} else {
			mixpanel.FEProp.setProperty("Old Download Quality Setting", "Ask each time");
		}

		String downloadQuality = getText(AMDMoreMenu.objDownloads_Quality);
		getDriver().findElement(By.xpath("//*[@id='downloadLabel']")).click();
		if (downloadQuality.equalsIgnoreCase("Ask each time")) {
			verifyElementPresentAndClick(AMDSettingsScreen.objDownloadVideoQualityOptions(option),
					"download quality option");
		} else {
			verifyElementPresentAndClick(AMDSettingsScreen.objVideoQualityAskEachTime, "Ask each time option");
		}

		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);

		MixpanelAndroid.FEProp.setProperty("Source", "More");
		MixpanelAndroid.FEProp.setProperty("Page Name", "selector");
		MixpanelAndroid.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		MixpanelAndroid.FEProp.setProperty("Brand", DeviceDetails.OEM);

		if (pUserType.equalsIgnoreCase("Guest")) {
			MixpanelAndroid.FEProp.setProperty("User Type", "guest");
			MixpanelAndroid.FEProp.setProperty("New Download Quality Setting", option);
		}

		MixpanelAndroid.ValidateParameter("", "Download Quality Changed");

		click(AMDMoreMenu.objDownloads_Quality, "Download quality option");
		if (downloadQuality.equalsIgnoreCase("Ask each time")) {
			verifyElementPresentAndClick(AMDSettingsScreen.objDownloadVideoQualityOptions(option),
					"download quality option");
		} else {
			verifyElementPresentAndClick(AMDSettingsScreen.objVideoQualityAskEachTime, "Ask each time option");
		}

	}

	@SuppressWarnings("static-access")
	public void verifyDownloadOverWifiChangeEventForEnable() throws Exception {
		extent.HeaderChildNode("Verify Download Over wifi change Event for Enable");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		verifyElementPresentAndClick(AMDMoreMenu.objDownloads_WifiOnly, "Download over wifi only switch");
		waitTime(10000);
		verifyElementPresentAndClick(AMDMoreMenu.objDownloads_WifiOnly, "Download over wifi only switch");
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.ValidateParameter("", "Download Over Wifi Changed");
	}

	@SuppressWarnings("static-access")
	public void verifyDownloadOverWifiChangeEventForDisable() throws Exception {
		extent.HeaderChildNode("Verify Download Over wifi change Event for Disable");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		verifyElementPresentAndClick(AMDMoreMenu.objDownloads_WifiOnly, "Download over wifi only switch");
		waitTime(10000);
		verifyElementPresentAndClick(AMDMoreMenu.objDownloads_WifiOnly, "Download over wifi only switch");
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.ValidateParameter("", "Download Over Wifi Changed");
	}

	@SuppressWarnings("static-access")
	public void verifyDisplayLanguageChangeFromWelcomePage(String usertype, String dsl) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Display Language Change event from Welcome page");
			click(AMDOnboardingScreen.objSelectDisplayLang(dsl), "language");
			verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn,
					"Continue button in Display language Page");
			waitTime(5000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Page Name", "DisplayLanguage");
			mixpanel.ValidateParameter("", "Display Language Changed");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyContinueLanguageFromWelcomePage(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Content Language Change event from Welcome page");

			click(AMDOnboardingScreen.objgetContentLangName(1), "Content Language");
			boolean flag = false;
			flag = verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
					"Continue button in Content language page");
			waitTime(5000);

			if (flag) {
				mixpanel.FEProp.setProperty("User Type", "guest");
				mixpanel.FEProp.setProperty("Source", "N/A");
				mixpanel.FEProp.setProperty("Page Name", "ContentLanguage");
				mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
				mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);
				mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", "Auto");
				mixpanel.FEProp.setProperty("New Autoplay Setting", "true");
				mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", "false");
				mixpanel.FEProp.setProperty("New Download Quality Setting", "Ask Each Time");
				mixpanel.FEProp.setProperty("New Download Over Wifi Setting", "false");
				mixpanel.FEProp.setProperty("New App Language", "en");
				mixpanel.FEProp.setProperty("New Content Language", "en,kn,hi");
				mixpanel.FEProp.setProperty("Old Content Language", "en,kn");

				mixpanel.ValidateParameter("", "Content Language Changed");
			} else {
				logger.info("Failed to change content language");
				extentLoggerWarning("Event", "Failed to content language");
			}

		}
	}

	public void videoViewEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Video View Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 20);
		Boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (var == true) {
			logger.info("Player screen is displayed");
			extentLoggerPass("Player screen", "Player screen is displayed");
		} else if (verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer)) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerPass("Player screen", "Player inline subscription link is displayed");
		}

		waitTime(3000);
		if (var == true) {
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Video View", "1");

			String pContentId = mixpanel.fetchContentId("", "Video View");
			String pDistinctId = mixpanel.DistinctId;
			ResponseInstance.getContentDetails(pContentId);

			mixpanel.ValidateParameter(pDistinctId, "Video View");
		}
	}

	public void videoViewEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Video View Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		Back(2);
		waitTime(4000);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Video View", "1");

		String pContentId = mixpanel.fetchContentId("", "Video View");
		String pDistinctId = mixpanel.DistinctId;
		ResponseInstance.getContentDetails(pContentId);

		mixpanel.ValidateParameter(pDistinctId, "Video View");
	}

	public void videoViewEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Video View Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(5000);
				verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
				flag = true;
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
			if (flag) {
				waitTime(3000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Video View", "1");

				String pContentId = mixpanel.fetchContentId("", "Video View");
				String pDistinctId = mixpanel.DistinctId;
				ResponseInstance.getContentDetails(pContentId);

				mixpanel.ValidateParameter(pDistinctId, "Video View");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}
	}

	public void videoViewEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Video View event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(2000);
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		waitTime(5000);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Video View", "1");

		String pContentId = Mixpanel.fetchContentId("", "Video View");
		String pDistinctId = mixpanel.DistinctId;
		ResponseInstance.getContentDetails(pContentId);

		mixpanel.ValidateParameter(pDistinctId, "Video View");
	}

	public void videoViewEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video View event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		waitTime(5000);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Video View", "1");

		String pContentId = mixpanel.fetchContentId("", "Video View");
		String pDistinctId = mixpanel.DistinctId;
		ResponseInstance.getContentDetails(pContentId);

		mixpanel.ValidateParameter(pDistinctId, "Video View");
	}

	public void videoExitEventForPremiumContent(String usertype, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Video Exit Event for premium content in potrait");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false, flag = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
					waitTime(3000);
					flag = true;
					Back(1);
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (flag) {
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				Mixpanel.ValidateParameter("", "Video Exit");
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		}
	}

	public void videoExitEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Video Exit event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watch Trailer button");
			Back(1);
		}
		waitTime(5000);
		Back(1);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

		Mixpanel.ValidateParameter("", "Video Exit");
	}

	public void videoExitEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Video Exit Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		boolean flagBox = verifyIsElementDisplayed(AMDHomePage.objSboxIcon);
		String pSugarBox = String.valueOf(flagBox);

		String contentName = ResponseInstance.getCarouselContentFromAPI(usertype, tabName);
		System.out.println(contentName);

		for (int i = 0; i < 3; i++) {
			if (verifyElementDisplayed(AMDHomePage.objCarouselContentTitle(contentName))) {
				click(AMDHomePage.objCarouselContentTitle(contentName), "carousal content");
				break;
			}
		}

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else {
			waitTime(5000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			boolean eventFlag = false;
			verifyElementPresent(AMDPlayerScreen.objPlayer, "Player screen");
			waitTime(2000);
			Back(1);
			eventFlag = verifyElementNotPresent(AMDPlayerScreen.objPlayerScreen, 5);
			waitTime(2000);

			if (!eventFlag) {
				if (usertype.equalsIgnoreCase("SubscribedUser")) {
					ResponseInstance.subscriptionDetails();
				}
				String pPage = getPageName(tabName);
				String pSource = getSource(tabName);
				String pManufacturer = DeviceDetails.OEM;
				// String pAdId = getAdId();

				setFEProperty(usertype);

//				mixpanel.FEProp.setProperty("Ad ID", pAdId);
//				mixpanel.FEProp.setProperty("Advertisement ID", pAdId);
				mixpanel.FEProp.setProperty("Source", pSource);
				mixpanel.FEProp.setProperty("Page Name", pPage);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("Brand", pManufacturer);
				mixpanel.FEProp.setProperty("Sugar Box Value", pSugarBox);

				mixpanel.ValidateParameter("", "Video Exit");
			} else {
				logger.info("Failed to Exit the Video");
				extentLoggerWarning("Event", "Failed to Exit the video");
			}
		}
	}

	public void videoExitEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Video Exit Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		waitTime(4000);
		Back(2);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

		mixpanel.ValidateParameter("", "Video Exit");
	}

	public void videoExitEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Video Exit Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(5000);
				verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
				Back(3);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

				Mixpanel.ValidateParameter("", "Video Exit");

			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
				Back(2);
			}
		}
	}

	public void videoExitEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Exit event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		PartialSwipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		Back(2);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

		Mixpanel.ValidateParameter("", "Video Exit");
	}

	public void playerViewChangedEventForPremiumContent(String usertype, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Player View Changed Event for premium content");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
					waitTime(4000);

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.ValidateParameter("", "Player View Changed");

					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		}
	}

	public void PlayerViewChangedEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Player View Changed event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(5000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Player View Changed");
	}

	public void PlayerViewChangedEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Player View Changed Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 20);
		Boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (var == true) {
			logger.info("Player screen is displayed");
			extentLoggerPass("Player screen", "Player screen is displayed");
			waitTime(6000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
			waitTime(5000);

			String pUserType = userType;
			setFEProperty(pUserType);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.ValidateParameter("", "Player View Changed");
		} else if (verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer)) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerPass("Player screen", "Player inline subscription link is displayed");
		}
	}

	public void PlayerViewChangedEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Player View Changed Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(4000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(4000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Player View Changed");
	}

	public void PlayerViewChangedEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Player View Changed Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(5000);
				waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
				verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
				verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
				verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
				waitTime(4000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.ValidateParameter("", "Player View Changed");

			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		}

	}

	public void PlayerViewChangedEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Player View Changed event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		PartialSwipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(4000);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Player View Changed");
	}

	public void seekVideoTillLast(By byLocator1) throws Exception {
		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));

		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		int startX = startx + 180;
		System.out.println(startX);
		SwipeAnElement(element, startX, 0);

		waitTime(2000);
		String afterSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time after seeking in seconds : " + timeToSec(afterSeek));
		extent.extentLogger("Seek", "Current time after seeking in seconds : " + timeToSec(afterSeek));

		String totalDur = findElement(AMDPlayerScreen.objTotalDuration).getText();
		if (timeToSec(afterSeek) > (timeToSec(totalDur) - 120)) {
			logger.info("Seeked the video till last");
			extentLoggerPass("Seeking the video till last", "Seeked the video till last");
			logger.info("Seek bar is functional");
			extent.extentLogger("Seek", "Seek bar is functional");
		} else {
			logger.info("Not seeked the video till last");
			extentLoggerFail("Seeking the video till last", "Not seeked the video till last");
			logger.info("Seek bar is not functional");
			extent.extentLoggerFail("Seek", "Seek bar is not functional");
		}
	}

	public void VideoWatchDurationEventForPremiumContentComplete(String usertype, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode(
					"Video Watch Duration Event for premium content when user completely watches the content");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
					waitTime(4000);
					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
					mixpanel.ValidateParameter("", "Video Watch Duration");
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		} else {
			logger.info("This validation is not applicable for " + usertype);
			extentLoggerPass("Premium Content", "This validation is not applicable for " + usertype);
		}
	}

	public void VideoWatchDurationEventForTrailerContentComplete(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode(
				"Video Watch Duration event for Trailer content when user completely watches the content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}

		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		waitTime(2000);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void VideoWatchDurationEventForCarouselContentComplete(String tabName) throws Exception {
		extent.HeaderChildNode(
				"Video Watch Duration Event for carousel content when user completely watches the content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 20);
		Boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (var == true) {
			logger.info("Player screen is displayed");
			extentLoggerPass("Player screen", "Player screen is displayed");
			waitTime(6000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
			waitTime(2000);

			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.ValidateParameter("", "Video Watch Duration");

		} else if (verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer)) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerPass("Player screen", "Player inline subscription link is displayed");
		}
	}

	public void VideoWatchDurationEventOfcontentFromSearchPageComplete(String usertype, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Video Watch Duration Event of content from search page when user completely watches the content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(4000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		waitTime(4000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void VideoWatchDurationEventOfContentFromMyWatchListPageComplete(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Video Watch Duration Event of content from My WatchList page when user completely watches the content");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(5000);
				waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
				scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
				waitTime(4000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

				mixpanel.ValidateParameter("", "Video Watch Duration");
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Watchlist validation is not applicable for " + usertype);
		}
	}

	public void VideoWatchDurationEventOfContentFromUpNextRailComplete(String usertype, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration event of content from Upnext rail when user completely watches the content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		PartialSwipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		waitTime(4000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void scrubProgressBarTillEnd(By byLocator1) throws Exception {
		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause");
		WebElement element = getDriver().findElement(byLocator1);
		String xDuration = getAttributValue("x", AMDPlayerScreen.objTotalDuration);
		int endX = Integer.parseInt(xDuration) - 30;
		SwipeAnElement(element, endX, 0);
		String afterSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time after seeking : " + timeToSec(afterSeek));
		extent.extentLogger("Seek", "Current time after seeking in seconds: " + timeToSec(afterSeek));
		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			waitForAdToFinishInAmd();
		}
		click(AMDPlayerScreen.objPlayIcon, "Play");
		waitTime(6000);
	}

	public void VideoWatchDurationEventForPremiumContentAbrupt(String usertype, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video Watch Duration event of Premium content when video closed abruptly");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
					waitTime(10000);
					Back(1);
					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.ValidateParameter("", "Video Watch Duration");

					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		} else {
			logger.info("This validation is not applicable for " + usertype);
			extentLoggerPass("Premium Content", "This validation is not applicable for " + usertype);
		}
	}

	public void VideoWatchDurationEventForTrailerContentAbrupt(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration event for Trailer content when video closed abruptly");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 10);
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
			Back(1);
		}
		waitTime(8000);
		Back(1);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void VideoWatchDurationEventForCarouselContentAbrupt(String tabName) throws Exception {
		extent.HeaderChildNode("Video Watch Duration Event for carousel content when video closed abruptly");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 20);
		Boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		if (var == true) {
			logger.info("Player screen is displayed");
			extentLoggerPass("Player screen", "Player screen is displayed");
		} else if (verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer)) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerPass("Player screen", "Player inline subscription link is displayed");
		}
		Back(1);

		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void VideoWatchDurationEventOfcontentFromSearchPageAbrupt(String usertype, String keyword4)
			throws Exception {
		extent.HeaderChildNode("Video Watch Duration Event of content from search page when video closed abruptly");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(3000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(4000);
		Back(2);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void VideoWatchDurationEventOfContentFromMyWatchListPageAbrupt(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Video Watch Duration Event of content from My WatchList page when video closed abruptly");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
//			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(5000);
				verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
				Back(3);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.ValidateParameter("", "Video Watch Duration");

			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
				Back(2);
			}
		} else {
			logger.info("Watchlist validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Watchlist validation is not applicable for " + usertype);
		}
	}

	public void VideoWatchDurationEventOfContentFromUpNextRailAbrupt(String usertype, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration event of content from Upnext rail  when video closed abruptly");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
//		Swipe("UP", 1);
		PartialSwipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyIsElementDisplayed(AMDPlayerScreen.objPlayer);
		Back(2);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void AdInitializedEvent(String userType, String contentType, String contentID, String contentName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			SearchContentFromSearchPage(userType, contentType, contentID, contentName);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitTime(5000);

				setFEProperty(userType);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Initialized");

			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}

		}
	}

	public void AdInitializedEventForTrailerContent(String usertype, String keyword4) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized event for Trailer content");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitTime(5000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Initialized");

			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}

		}
	}

	public void AdInitializedEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad Initialized Event for carousel content");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);

			String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, tabName);
			System.out.println(contentName);

			waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(3000);
				Back(1);
				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "Homepage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad Initialized");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable to this usertype");
			extent.extentLogger("Not Applicable", "Not Applicable to this usertype");
		}
	}

	public void AdInitializedEventForContentFromTray(String usertype, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Ad Initialized Event for content from Tray");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false, ad = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					waitTime(10000);
					ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
					if (ad == true) {
						logger.info("Ad is Displayed");
						extentLogger("Ad", "Ad is Displayed");
					} else {
						logger.info("Ad is not displayed");
						extentLogger("Ad", "Ad is not displayed");
					}
					waitTime(3000);
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (ad == true) {
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Initialized");
			}

			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		}
	}

	public void AdInitializedEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Ad Initialized Event of content from search page");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(3000);
				Back(1);
				String pManufacturer = DeviceDetails.OEM;

				String contentID = getParameterFromXML("clipContentID");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad Initialized");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void AdInitializedEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Ad Initialized Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(10000);
				boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
				if (ad == true) {
					logger.info("Ad is Displayed");
					extentLogger("Ad", "Ad is Displayed");

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
					mixpanel.FEProp.setProperty("Ad Location", "instream");
					mixpanel.ValidateParameter("", "Ad Initialized");

				} else {
					logger.info("Ad is not displayed");
					extentLogger("Ad", "Ad is not displayed");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist ad validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Watchlist ad validation is not applicable for " + usertype);
		}
	}

	public void AdInitializedEventOfContentFromUpNextRail(String usertype, String keyword5) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized event of content from Upnext rail");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword5 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Initialized");

			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
			waitTime(5000);
		}
	}

	public void AdViewEventForTrailerContent(String usertype, String keyword3) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View event for Trailer content");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitTime(5000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

				mixpanel.ValidateParameter("", "Ad view");

			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Ad", "Ad validation is not applicable for " + usertype);
		}
	}

	public void AdViewEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad View Event for carousel content");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);

			String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, tabName);
			System.out.println(contentName);

			waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(3000);
				Back(1);
				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Free");
				}

				mixpanel.FEProp.setProperty("Source", "Homepage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad view");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable to this usertype");
			extent.extentLogger("Not Applicable", "Not Applicable to this usertype");
		}
	}

	public void AdViewEventForContentFromTray(String usertype, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Ad View Event for content from Tray");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					waitTime(10000);
					boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
					if (ad == true) {
						logger.info("Ad is Displayed");
						extentLogger("Ad", "Ad is Displayed");

						waitTime(3000);
						setFEProperty(usertype);
						mixpanel.FEProp.setProperty("Source", "home");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
						mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

						mixpanel.ValidateParameter("", "Ad view");
					} else {
						logger.info("Ad is not displayed");
						extentLogger("Ad", "Ad is not displayed");
					}
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Ad", "Ad validation is not applicable for " + usertype);
		}
	}

	public void AdViewEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Ad view Event of content from search page");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {

			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(3000);
				Back(1);
				String pManufacturer = DeviceDetails.OEM;

				String contentID = getParameterFromXML("clipContentID");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Free");
				}
				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad view");
			} else {
				logger.info("Ad is not displayed");
				extent.extentLogger("Ad", "Ad is not displayed");
			}

		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void AdViewEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Ad View Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(10000);
				boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
				if (ad == true) {
					logger.info("Ad is Displayed");
					extentLogger("Ad", "Ad is Displayed");
					waitTime(2000);
					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
					mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

					mixpanel.ValidateParameter("", "Ad view");
				} else {
					logger.info("Ad is not displayed");
					extentLogger("Ad", "Ad is not displayed");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Ad", "Ad validation is not applicable for " + usertype);
		}
	}

	public void AdViewEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View event of content from Upnext rail");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitTime(2000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

				mixpanel.ValidateParameter("", "Ad view");
			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
			waitTime(5000);
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Ad", "Ad validation is not applicable for " + usertype);
		}
	}

	public void videoViewEventForPremiumContent(String usertype, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Video View Event for premium content in potrait");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			waitForElementDisplayed(AMDHomePage.objCarouselTitle, 60);
			Swipe("UP", 1);
			String pUsername = null, pPwd = null;
			switch (userType) {
			case "NonSubscribedUser":
				pUsername = getParameterFromXML("NonsubscribedUserName");
				pPwd = getParameterFromXML("NonsubscribedPassword");
				break;

			case "SubscribedUser":
				pUsername = getParameterFromXML("SubscribedUserName");
				pPwd = getParameterFromXML("SubscribedPassword");
				break;
			}

			boolean var = false;
			for (int i = 0; i < 3; i++) {
				waitTime(1000);
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					waitTime(5000);
					verifyIsElementDisplayed(AMDPlayerScreen.objPlayer, "Player screen");
					waitTime(3000);
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == true) {

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Video View", "1");

				String pContentId = mixpanel.fetchContentId("", "Video View");
				String pDistinctId = mixpanel.DistinctId;
				ResponseInstance.getContentDetails(pContentId);

				mixpanel.ValidateParameter(pDistinctId, "Video View");
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		} else {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void AdClickEventForTrailerContent(String usertype, String keyword4) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click event for Trailer content");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
				waitTime(4000);

				setFEProperty(usertype);

				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

				mixpanel.ValidateParameter("", "Ad click");

			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
			waitTime(5000);
		}

	}

	public void AdClickEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad click Event for carousel content");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);

			String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, tabName);
			System.out.println(contentName);

			waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "Homepage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad click");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable to this usertype");
			extent.extentLogger("Not Applicable", "Not Applicable to this usertype");
		}
	}

	public void AdClickEventForContentFromTray(String usertype, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Ad Click Event for content from Tray");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					waitTime(10000);
					boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
					if (ad == true) {
						logger.info("Ad is Displayed");
						extentLogger("Ad", "Ad is Displayed");
						verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
						waitTime(4000);

						setFEProperty(usertype);
						mixpanel.FEProp.setProperty("Source", "home");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

						mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
						mixpanel.FEProp.setProperty("Ad Location", "instream");
						mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

						mixpanel.ValidateParameter("", "Ad click");
					} else {
						logger.info("Ad is not displayed");
						extentLogger("Ad", "Ad is not displayed");
					}
					waitTime(3000);
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		}
	}

	public void AdClickEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Ad click Event of content from search page");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
				String pManufacturer = DeviceDetails.OEM;

				String contentID = getParameterFromXML("clipContentID");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad click");
			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable to this usertype");
			extent.extentLogger("Not Applicable", "Not Applicable to this usertype");
		}
	}

	public void AdClickEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Ad Click Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(10000);
				boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
				if (ad == true) {
					logger.info("Ad is Displayed");
					extentLogger("Ad", "Ad is Displayed");
					verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
					waitTime(4000);

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
					mixpanel.FEProp.setProperty("Ad Location", "instream");
					mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

					mixpanel.ValidateParameter("", "Ad click");
				} else {
					logger.info("Ad is not displayed");
					extentLogger("Ad", "Ad is not displayed");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Watchlist is not applicable for " + usertype);
		}
	}

	public void AdClickEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad click event of content from Upnext rail");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
				waitTime(4000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.FEProp.setProperty("Ad Position", "Pre-Roll");

				mixpanel.ValidateParameter("", "Ad click");
			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
			waitTime(5000);
		}
	}

	public void AdWatchDurationEventForTrailerContent(String usertype, String keyword) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Watch Duration event for Trailer content");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitForAdToFinishInAmd();
				waitTime(4000);
				Back(1);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Watch Duration");

			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
			waitTime(5000);
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Ad validation is not applicable for " + usertype);
		}
	}

	public void AdWatchDurationEventForCarouselContent(String usertype, String tabName) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Ad Watch Duration Event for carousel content");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
			waitTime(10000);
			Boolean var = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (var == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitForAdToFinishInAmd();
				waitTime(4000);
				Back(1);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Watch Duration");

			} else {
				logger.info("Ad is not Displayed");
				extentLogger("Ad", "Ad is not Displayed");
			}
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Ad validation is not applicable for " + usertype);
		}
	}

	public void AdWatchDurationEventForContentFromTray(String usertype, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Ad Watch Duration Event for content from Tray");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);
			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					waitTime(10000);
					boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
					if (ad == true) {
						logger.info("Ad is Displayed");
						extentLogger("Ad", "Ad is Displayed");
						waitForAdToFinishInAmd();
						waitTime(4000);
						Back(1);
						setFEProperty(usertype);
						mixpanel.FEProp.setProperty("Source", "home");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

						mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
						mixpanel.FEProp.setProperty("Ad Location", "instream");
						mixpanel.ValidateParameter("", "Ad Watch Duration");
					} else {
						logger.info("Ad is not displayed");
						extentLogger("Ad", "Ad is not displayed");
					}
					waitTime(3000);
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Ad validation is not applicable for " + usertype);
		}
	}

	public void AdWatchDurationEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Ad Watch Duration Event of content from search page");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitForAdToFinishInAmd();
				waitTime(4000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Watch Duration");

			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
			waitTime(4000);
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Ad validation is not applicable for " + usertype);
		}
	}

	public void AdWatchDurationEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Ad Watch Duration Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(10000);
				boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
				if (ad == true) {
					logger.info("Ad is Displayed");
					extentLogger("Ad", "Ad is Displayed");
					waitForAdToFinishInAmd();
					waitTime(4000);

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
					mixpanel.FEProp.setProperty("Ad Location", "instream");
					mixpanel.ValidateParameter("", "Ad Watch Duration");
				} else {
					logger.info("Ad is not displayed");
					extentLogger("Ad", "Ad is not displayed");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist validation is not applicable for " + usertype);
			extentLoggerPass("Watchlist", "Watchlist validation is not applicable for " + usertype);
		}
	}

	public void AdWatchDurationEventOfContentFromUpNextRail(String usertype, String keyword) throws Exception {
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Watch Duration event of content from Upnext rail");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(10000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				waitForAdToFinishInAmd();
				waitTime(4000);
				Back(1);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Ad Title", "In-Stream Video");
				mixpanel.FEProp.setProperty("Ad Location", "instream");
				mixpanel.ValidateParameter("", "Ad Watch Duration");
			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
			waitTime(5000);
		} else {
			logger.info("Ad Validation is not applicable for " + usertype);
			extentLoggerPass("Ad", "Ad validation is not applicable for " + usertype);
		}
	}

	public void PauseEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Pause Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(3000);
				boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
				if (inlineLink == true) {
					logger.info("Player inline subscription link is displayed");
					extentLogger("Player screen", "Player inline subscription link is displayed");
				} else {
					waitTime(6000);
					verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
					verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
					waitTime(2000);

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.ValidateParameter("", "Pause");
				}
				waitTime(3000);
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void PauseEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Pause event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(5000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Pause");
	}

	public void PauseEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Pause Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		boolean flagBox = verifyIsElementDisplayed(AMDHomePage.objSboxIcon);
		String pSugarBox = String.valueOf(flagBox);

		String contentName = ResponseInstance.getCarouselContentFromAPI(usertype, tabName);
		System.out.println(contentName);

		for (int i = 0; i < 3; i++) {
			if (verifyElementDisplayed(AMDHomePage.objCarouselContentTitle(contentName))) {
				click(AMDHomePage.objCarouselContentTitle(contentName), "carousal content");
				break;
			}

		}

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else {
			waitTime(5000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			boolean eventFlag = false;
			eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(2000);

			if (eventFlag) {
				if (usertype.equalsIgnoreCase("SubscribedUser")) {
					ResponseInstance.subscriptionDetails();
				}
				String pPage = getPageName(tabName);
				String pSource = getSource(tabName);
				String pManufacturer = DeviceDetails.OEM;
				// String pAdId = getAdId();

				setFEProperty(usertype);

//				mixpanel.FEProp.setProperty("Ad ID", pAdId);
//				mixpanel.FEProp.setProperty("Advertisement ID", pAdId);
				mixpanel.FEProp.setProperty("Source", pSource);
				mixpanel.FEProp.setProperty("Page Name", pPage);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("Brand", pManufacturer);
				mixpanel.FEProp.setProperty("Sugar Box Value", pSugarBox);

				mixpanel.ValidateParameter("", "Pause");
			} else {
				logger.info("Failed to perform Event Action");
				extentLoggerWarning("Event Action", "Failed to perform Event Action");
			}
		}

	}

	public void PauseEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Pause Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(5000);
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(2000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Pause");
	}

	public void PauseEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Pause Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
				waitTime(2000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.ValidateParameter("", "Pause");
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}
	}

	public void PauseEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Pause event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		// waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		// Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Pause");
	}

	public void PauseEventForLinearContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Pause Event for Linear content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		waitTime(5000);
		boolean checkLoadingicon = verifyElementNotPresent(AMDLiveTVScreen.objLoadingIcon, 30);
		if (!checkLoadingicon) {
			logger.info("Continuous Loading icon is displayed in Live TV section");
			extentLoggerWarning("Loading Icon", "Continuous Loading icon is displayed in Live TV section");
		} else {
			verifyElementPresentAndClick(AMDLiveTVScreen.objFirstContentCardOfFreeChannelsTray, "linear content");
			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
			}
			registerPopUpClose();
			completeProfilePopUpClose(usertype);
			waitTime(6000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(2000);

			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.ValidateParameter("", "Pause");
		}
	}

	public void seekVideo(By byLocator, String usertype) throws Exception {

		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));
		WebElement element = getDriver().findElement(byLocator);
		Dimension size = element.getSize();
		int startx = (int) (size.width);

		SwipeAnElement(element, startx, 0);
		logger.info("Scrolling the seek bar");
		extent.extentLogger("Seek", "Scrolling the seek bar");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		waitTime(5000);
		boolean icon = verifyIsElementDisplayed(AMDPlayerScreen.objPlayIcon, "Play icon");
		if (icon == false) {
			waitTime(6000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		}
		String afterSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time after seeking in seconds : " + timeToSec(afterSeek));
		extent.extentLogger("Seek", "Current time after seeking in seconds : " + timeToSec(afterSeek));
		if (timeToSec(afterSeek) > timeToSec(beforeSeek)) {
			logger.info("Seek bar is functional");
			extent.extentLoggerPass("Seek", "Seek bar is functional");
		} else {
			logger.info("Seek bar is not functional");
			extent.extentLoggerFail("Seek", "Seek bar is not functional");
		}
	}

	public void SwipeAnElement(WebElement element, int posx, int posy) {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(posx, posy))
				.release().perform();
	}

	public static int timeToSec(String s) {
		String[] t = s.split(":");
		int num = 0;
		// System.out.println(t.length);
		if (t.length == 2) {
			num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
		}
		if (t.length == 3) {
			num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
		}
		return num;
	}

	public void QualityChangeEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Quality Change Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(3000);
				boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
				if (inlineLink == true) {
					logger.info("Player inline subscription link is displayed");
					extentLogger("Player screen", "Player inline subscription link is displayed");
				} else {
					waitTime(8000);
					verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
					verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
					verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
					verifyElementPresentAndClick(AMDPlayerScreen.objQuality, "Quality");
					String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
					if (selectedQualityOption.equalsIgnoreCase("Auto")) {
						verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Quality option");
						waitTime(2000);
						setFEProperty(usertype);
						mixpanel.ValidateParameter("", "Qualitiy Change");

					} else {
						verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Quality option");
						waitTime(2000);
						setFEProperty(usertype);
						mixpanel.ValidateParameter("", "Qualitiy Change");
					}
				}
				waitTime(3000);
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void QualityChangeEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Quality Change event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		waitTime(8000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
		verifyElementPresentAndClick(AMDPlayerScreen.objQuality, "Quality");
		String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
		if (selectedQualityOption.equalsIgnoreCase("Auto")) {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		} else {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		}
		waitTime(5000);

	}

	public void QualityChangeEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Quality Change Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		boolean flagBox = verifyIsElementDisplayed(AMDHomePage.objSboxIcon);
		String pSugarBox = String.valueOf(flagBox);

		String contentName = ResponseInstance.getCarouselContentFromAPI(usertype, tabName);
		System.out.println(contentName);

		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 60, "carousal content");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerWarning("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLoggerWarning("Player screen",
					"error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = false;
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");

			waitTime(5000);
//			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Three dots");
			touchAction = new TouchAction(getDriver());
			touchAction.press(PointOption.point(1855, 94)).release().perform();

			String quality = getText(AMDPlayerScreen.objQuality);
			String oldQuality = quality.substring(8, quality.length() - 1);
			System.out.println(oldQuality);
			click(AMDPlayerScreen.objQuality, "Quality");
			if (verifyIsElementDisplayed(AMDPlayerScreen.objQualityOpt(2))) {
				waitTime(3000);
				Swipe("UP", 1);
				eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(3), "Quality option");
			} else {
				eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Quality option");
			}

			click(AMDPlayerScreen.objPauseIcon, "Pause icon");

			waitTime(2000);
			touchAction = new TouchAction(getDriver());
			touchAction.press(PointOption.point(1855, 94)).release().perform();

			String newQuality = getText(AMDPlayerScreen.objQuality);
			newQuality = newQuality.substring(8, newQuality.length() - 1);
			System.out.println(newQuality);

			if (eventFlag) {
				String pPage = "ConsumptionPage";
				String pSource = getSource(tabName);
				String pManufacturer = DeviceDetails.OEM;
				// String pAdId = getAdId();

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);

				// mixpanel.FEProp.setProperty("Ad ID", getParameterFromXML("AdID"));
				// mixpanel.FEProp.setProperty("Advertisement ID", getParameterFromXML("AdID"));
				mixpanel.FEProp.setProperty("Source", pSource);
				mixpanel.FEProp.setProperty("Page Name", pPage);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("Brand", pManufacturer);
				mixpanel.FEProp.setProperty("New Quality", newQuality);
				mixpanel.FEProp.setProperty("Old Quality", oldQuality);
				mixpanel.FEProp.setProperty("Carousal Name", "N/A");
				// mixpanel.FEProp.setProperty("Sugar Box Value",pSugarBox );

				mixpanel.ValidateParameter("", "Video Streaming Quality Changed");
			} else {
				logger.info("Failed to change the quality option");
				extentLoggerWarning("Event", "Failed to change the quality option");
			}
		}
	}

	public void QualityChangeEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Quality Change Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			boolean popUp = verifyIsElementDisplayed(AMDConsumptionScreen.objRegisterPopUp);
			if (popUp) {
				Back(1);
			}
		}

		waitTime(8000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
		verifyElementPresentAndClick(AMDPlayerScreen.objQuality, "Quality");
		String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
		if (selectedQualityOption.equalsIgnoreCase("Auto")) {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		} else {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		}
	}

	public void QualityChangeEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Quality Change Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				if (!usertype.equalsIgnoreCase("SubscribedUser")) {
					waitForAdToFinishInAmd();
					boolean popUp = verifyIsElementDisplayed(AMDConsumptionScreen.objRegisterPopUp);
					if (popUp) {
						Back(1);
					}
				}
				waitTime(8000);
				verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
				verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
				verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
				verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
				verifyElementPresentAndClick(AMDPlayerScreen.objQuality, "Quality");
				String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
				if (selectedQualityOption.equalsIgnoreCase("Auto")) {
					verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Quality option");
					waitTime(2000);
					setFEProperty(usertype);
					mixpanel.ValidateParameter("", "Qualitiy Change");
				} else {
					verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Quality option");
					waitTime(2000);
					setFEProperty(usertype);
					mixpanel.ValidateParameter("", "Qualitiy Change");
				}
				waitTime(4000);
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}

	}

	public void QualityChangeEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Quality Change event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			boolean popUP = verifyIsElementDisplayed(AMDConsumptionScreen.objRegisterPopUp);
			if (popUP) {
				Back(1);
			}
		}

		waitTime(8000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
		verifyElementPresentAndClick(AMDPlayerScreen.objQuality, "Quality");
		String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
		if (selectedQualityOption.equalsIgnoreCase("Auto")) {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		} else {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		}
		waitTime(5000);

	}

	public void QualityChangeEventForLinearContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Quality Change Event for Linear content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		waitTime(3000);
		verifyElementPresentAndClick(AMDLiveTVScreen.objFirstContentCardOfFreeChannelsTray, "linear content");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			boolean popUP = verifyIsElementDisplayed(AMDConsumptionScreen.objRegisterPopUp);
			if (popUP) {
				Back(1);
			}
		}

		waitTime(8000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
		verifyElementPresentAndClick(AMDPlayerScreen.objQuality, "Quality");
		String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
		if (selectedQualityOption.equalsIgnoreCase("Auto")) {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		} else {
			verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Quality option");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.ValidateParameter("", "Qualitiy Change");
		}
		waitTime(3000);
	}

	/**
	 * SKIP_LOGIN
	 */

	/**
	 * Function to verify Skip Login
	 */

	@SuppressWarnings("static-access")
	public void verifySkipLoginEventBrowseForFreeScreen(String userType) throws Exception {

		extent.HeaderChildNode("Verify Skip Login event when user navigated from Browse for Free");
		click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip login button");
		waitTime(5000);

		setFEProperty(userType);

		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Element", "Skip");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Skip Login");
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginEventFromLoginCTA() throws Exception {

		extent.HeaderChildNode("Verify Skip Login event when user navigated from Login CTA");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Element", "Skip");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Skip Login");

	}

	@SuppressWarnings("static-access")
	public void verifySkipLogin_LoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Skip login event on clicking login in Get premium popup during content playback");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
			waitTime(5000);
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDPlayerScreen.objLoginCTA, "Login CTA");
			verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip link");
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Element", "Skip");
			mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
			mixpanel.ValidateParameter("", "Skip Login");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginEventFromHamburgerMenu(String usertype) throws Exception {
		extent.HeaderChildNode("Verify Skip login event");
		if (usertype.equalsIgnoreCase("SubscribedUser") | (usertype.equalsIgnoreCase("NonSubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			Swipe("Up", 2);
			click(AMDHomePage.objLogout, "Logout");
			click(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
			Swipe("Down", 1);
		} else {
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		}
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
		verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip link");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Skip");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Skip Login");

	}

	/**
	 * Logout
	 */

	@SuppressWarnings("static-access")
	public void verifyLogoutEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Logout Event for " + userType);
		if (!(userType.equalsIgnoreCase("Guest"))) {
			logout();
			waitTime(5000);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();
			mixpanel.FEProp.setProperty("Element", "Logout");
			mixpanel.FEProp.setProperty("Page Name", "Logout");
			mixpanel.FEProp.setProperty("Source", "More");
			mixpanel.ValidateParameter("", "Logout");
		}
	}

	public void logout() throws Exception {
		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Logout from the App");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			waitTime(3000);
			Swipe("Up", 1);
			SwipeUntilFindElement(AMDMoreMenu.objLogout, "UP");
			verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
			verifyElementPresentAndClick(AMDMoreMenu.objLogoutPopup, "Logout popUp");
			verifyElementPresentAndClick(AMDMoreMenu.objLogoutButton, "Logout button");
			waitTime(3000);
			Swipe("Down", 1);
			SwipeUntilFindElement(AMDMoreMenu.objGuestUserAccount, "Down");
			verifyElementPresent(AMDMoreMenu.objGuestUserAccount, "Guest user Header");
			verifyElementPresentAndClick(AMDHomePage.objHome, "Home tab");
		} else {
			logger.info("Logout option is Not applicable for Guest user");
			extentLogger("Logout option", "Logout option Event is Not applicable for Guest user");
		}
	}

	/**
	 * LoginScreen Displayed
	 */

	/*
	 * Login Screen Display event
	 */

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventThroughBrowseForScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Browse for free button in welcome screen");

			verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			String Username = getParameterFromXML("NonsubscribedUserName");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");

			waitTime(3000);
			boolean flag = false;
			flag = verifyIsElementDisplayed(AMDLoginScreen.objLoginScreenTitle, "Login screen title");
			hideKeyboard();
			if (flag) {
				setFEProperty(userType);

				mixpanel.FEProp.setProperty("User Type", "guest");
				mixpanel.FEProp.setProperty("Source", "LoginRegister");
				mixpanel.FEProp.setProperty("Page Name", "Login");
				mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
				mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);

				mixpanel.ValidateParameter("", "Login Screen Display");
			} else {
				logger.info("Failed to navigate Login screen");
				extentLoggerWarning("Event", "Failed to navigate Login screen");
			}

		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventThroughLoginLink(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event By Clicking On Login link in welcome screen");

			verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login link");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			String Username = getParameterFromXML("NonsubscribedUserName");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			waitTime(3000);
			verifyIsElementDisplayed(AMDLoginScreen.objLoginScreenTitle, "Login screen title");
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Source", "Login");
			mixpanel.FEProp.setProperty("Page Name", "Login");
			mixpanel.ValidateParameter("", "Login Screen Display");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventThroughMoreMenu(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event through MoreMenu");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			String Username = getParameterFromXML("NonsubscribedUserName");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			waitTime(3000);
			verifyIsElementDisplayed(AMDLoginScreen.objLoginScreenTitle, "Login screen title");
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Source", "Login");
			mixpanel.ValidateParameter("", "Login Screen Display");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
			waitTime(2000);
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDPlayerScreen.objLoginCTA, "Login CTA");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			String Username = getParameterFromXML("NonsubscribedUserName");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			waitTime(3000);
			verifyIsElementDisplayed(AMDLoginScreen.objLoginScreenTitle, "Login screen title");
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Source", "Login");
			mixpanel.ValidateParameter("", "Login Screen Display");
		}
	}

	/**
	 * LoginInitiated
	 */
	/*
	 * Navigation : Get Premium tag Event : Login Initatied
	 */

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventGPlusUserFromGetPremiumCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Google login from Login screen");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objGoogleBtn, "Google Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Social Network", "Google");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventFBUserFromGetPremiumCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Facebook login from Login screen");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objfbBtn, "Facebook Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Social Network", "Facebook");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventTwitterUserFromGetPremiumCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Twitter login from Login screen");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Social Network", "Twitter");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventMobileOTPFromGetPremiumCTA() throws Exception {

		HeaderChildNode(
				"Verify Login Initiated event is triggered when user select Mobile Number login from Login screen");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "mobile");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventNonSubUserGetPremiumCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Email ID login from Login screen");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "1234567", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "email");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventSubUserFromGetPremiumCTA() throws Exception {

		HeaderChildNode(
				"Verify Login Initiated event is triggered when user select Mobile Number login from Login screen");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "rsvod3@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "123456", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "email");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventForInvalidDataEmailGetPremiumCTA() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Initiated event is triggered when user enters invalid email login credentials");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "098765409876", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "email");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventForInvalidDataMobileGetPremiumCTA() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Initiated event is triggered when user enters invalid Mobile login credentials");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "098765409876", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "mobile");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	/*
	 * Navigation : Login CTA Event : Login Initiated
	 */

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventGPlusUserFromLoginCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Google login from Login screen");
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objGoogleBtn, "Google Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Google");
		mixpanel.FEProp.setProperty("Method", "Social");
		Mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventFBUserFromLoginCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Facebook login from Login screen");
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objfbBtn, "Facebook Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Facebook");
		mixpanel.FEProp.setProperty("Method", "Social");
		Mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventTwitterUserFromLoginCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Twitter login from Login screen");
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Twitter");
		mixpanel.FEProp.setProperty("Method", "Social");
		Mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventMobileOTPFromLoginCTA() throws Exception {

		HeaderChildNode(
				"Verify Login Initiated event is triggered when user select Mobile Number login from Login screen");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "mobile");
		Mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventNonSubUserLoginCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Email ID login from Login screen");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "1234567", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "email");
		Mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventSubUserFromLoginCTA() throws Exception {

		HeaderChildNode(
				"Verify Login Initiated event is triggered when user select Mobile Number login from Login screen");
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "rsvod3@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "123456", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "email");
		Mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventForInvalidDataEmailLoginCTA() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Initiated event is triggered when user enters invalid email login credentials");
		click(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "098765409876", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "email");
		Mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventForInvalidDataMobileLoginCTA() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Initiated event is triggered when user enters invalid Mobile login credentials");

		click(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "098765409876", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Method", "mobile");
		Mixpanel.ValidateParameter("", "Login Initatied");
	}

	/*
	 * Event : Login Initiated Navigation : Browse for free CTA
	 */

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventGPlusUserFromBrowseForFreeCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Google login from Login screen");

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objGoogleBtn, "Google Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Google");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventFBUserFromBrowseForFreeCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Facebook login from Login screen");
		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objfbBtn, "Facebook Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Facebook");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventTwitterUserFromBrowseForFreeCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Twitter login from Login screen");

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Twitter");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventMobileOTPFromBrowseForFreeCTA() throws Exception {

		HeaderChildNode(
				"Verify Login Initiated event is triggered when user select Mobile Number login from Login screen");

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Method", "mobile");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventNonSubUserBrowseForFreeCTA() throws Exception {

		HeaderChildNode("Verify Login Initiated event is triggered when user select Email ID login from Login screen");

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "1234567", "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Method", "email");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginIntiatedEventSubUserBrowseForFreeCTA() throws Exception {

		HeaderChildNode(
				"Verify Login Initiated event is triggered when user select Mobile Number login from Login screen");

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "rsvod3@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "123456", "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Method", "email");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventForInvalidDataEmailLogin() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Initiated event is triggered when user enters invalid email login credentials");

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "098765409876", "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Method", "email");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");

	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventForInvalidDataMobileLogin() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Initiated event is triggered when user enters invalid Mobile login credentials");

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "098765409876", "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Method", "mobile");
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.ValidateParameter("", "Login Initatied");
	}

	/**
	 * Login Result
	 */
	/*
	 * Event : Login Result Navigation : Browse for free CTA
	 */

	@SuppressWarnings("static-access")
	public void LoginResultEventFromBrowseforFreeTwitterUser() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Result event when user taps on Login button, Post entering valid credentials");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter button");
		waitTime(4000);
		if (verifyIsElementDisplayed(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App")) {
			click(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
		}
		waitTime(15000);
		if (verifyIsElementDisplayed(AMDHomePage.objHome, "Home tab")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
			logout();
		} else if (verifyIsElementDisplayed(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App")) {
			click(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
		} else {
			verifyElementPresent(AMDLoginScreen.objEmailFieldInTwitterPage, " Email Field");
			type(AMDLoginScreen.objEmailFieldInTwitterPage, "zee5latest@gmail.com", "Email Field");

			verifyElementPresent(AMDLoginScreen.objPasswordFieldInTwitterPage, " Password Field");
			type(AMDLoginScreen.objPasswordFieldInTwitterPage, "User@123", "Password Field");

			verifyElementPresentAndClick(AMDLoginScreen.objLoginButtonInTwitterPage, "Login Button");
			waitTime(3000);
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
			waitForElementDisplayed(AMDHomePage.objHome, 20);
			if (checkElementDisplayed(AMDHomePage.objHome, "Home tab")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
				logout();
			} else {
				logger.info("User is not logged in Successfully");
				extent.extentLoggerFail("Logged in", "User is not logged in Successfully");
				Back(1);
			}
		}
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Twitter");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromBrowseforFreeCTANonSubUser() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Result event when user taps on Login button, Post entering valid credentials for Non subscribed user");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "123456", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromBrowseforFreeCTASubUser() throws Exception {
		HeaderChildNode("Verify Login Result event is triggered when user enters valid subscribed user credentials");
		relaunchToIntroScreen(true);
		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "rsvod3@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "1234566", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.ValidateParameter("", "Login Result");
	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromBrowseforFreeCTAMobileLogin() throws Exception {

		HeaderChildNode("Verify Login Result event is triggered when user enters valid Mobile number user credentials");

		click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "User@1234", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromBrowseforFreeCTAMobileLoginInvalidData() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered when user enters Invalid Mobile number user credentials");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "dfghj@123", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromBrowseforFreeCTAInvalidEmailID() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Result event when user taps on Login button, Post entering Invalid credentials for Email ID");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "asdfghj", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.ValidateParameter("", "Login Result");

	}

	/*
	 * Event : Login Result Navigation : Login CTA
	 */

	@SuppressWarnings("static-access")
	public void LoginResultEventFromLoginCTATwitterUser() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Result event when user taps on Login button, Post entering valid credentials");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter button");
		waitTime(4000);
		if (verifyIsElementDisplayed(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App")) {
			click(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
		}
		waitTime(15000);
		if (verifyIsElementDisplayed(AMDHomePage.objHome, "Home tab")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
			logout();
		} else if (verifyIsElementDisplayed(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App")) {
			click(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
		} else {
			verifyElementPresent(AMDLoginScreen.objEmailFieldInTwitterPage, " Email Field");
			type(AMDLoginScreen.objEmailFieldInTwitterPage, "zee5latest@gmail.com", "Email Field");

			verifyElementPresent(AMDLoginScreen.objPasswordFieldInTwitterPage, " Password Field");
			type(AMDLoginScreen.objPasswordFieldInTwitterPage, "User@123", "Password Field");

			verifyElementPresentAndClick(AMDLoginScreen.objLoginButtonInTwitterPage, "Login Button");
			waitTime(3000);
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
			waitForElementDisplayed(AMDHomePage.objHome, 20);
			if (checkElementDisplayed(AMDHomePage.objHome, "Home tab")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
				logout();
			} else {
				logger.info("User is not logged in Successfully");
				extent.extentLoggerFail("Logged in", "User is not logged in Successfully");
				Back(1);
			}
		}
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Intro");
		mixpanel.FEProp.setProperty("Social Network", "Twitter");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromLoginCTANonSubUser() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Result event when user taps on Login button, Post entering valid credentials for Non subscribed user");

		relaunchToIntroScreen(true);
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "1234567", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromLoginCTASubUser() throws Exception {
		HeaderChildNode("Verify Login Result event is triggered when user enters valid subscribed user credentials");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "rsvod3@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "123456", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromLoginCTAMobileLogin() throws Exception {

		HeaderChildNode("Verify Login Result event is triggered when user enters valid Mobile number user credentials");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromLoginCTAMobileLoginInvalidData() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered when user enters Invalid Mobile number user credentials");
		relaunchToIntroScreen(true);
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "dfghj@123", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromLoginCTAInvalidEmailID() throws Exception {

		extent.HeaderChildNode(
				"Verify Login Result event when user taps on Login button, Post entering Invalid credentials for Email ID and Password");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "asdfghj", "Password field");
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	/*
	 * Event : Login Result Navigation : Get Premium CTA
	 */

	@SuppressWarnings("static-access")
	public void LoginResultEventFromGetPremiumCTATwitterLogin() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered  when user navigates to Login Screen from Get Premium CTA");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter button");
		waitTime(4000);
		if (verifyIsElementDisplayed(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App")) {
			click(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
		}
		waitTime(20000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objPlayerScreen, "Player Screen")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
			Back(1);
			logout();
		} else if (verifyIsElementDisplayed(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App")) {
			click(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
		} else {
			verifyElementPresent(AMDLoginScreen.objEmailFieldInTwitterPage, " Email Field");
			type(AMDLoginScreen.objEmailFieldInTwitterPage, "zee5latest@gmail.com", "Email Field");

			verifyElementPresent(AMDLoginScreen.objPasswordFieldInTwitterPage, " Password Field");
			type(AMDLoginScreen.objPasswordFieldInTwitterPage, "User@123", "Password Field");

			verifyElementPresentAndClick(AMDLoginScreen.objLoginButtonInTwitterPage, "Login Button");
			waitTime(3000);
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDLoginScreen.objAuthorizeAppInTwitterpage, "Authorize App");
			waitForElementDisplayed(AMDHomePage.objHome, 20);
			if (checkElementDisplayed(AMDHomePage.objHome, "Home tab")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
				logout();
			} else {
				logger.info("User is not logged in Successfully");
				extent.extentLoggerFail("Logged in", "User is not logged in Successfully");
				Back(1);
			}
		}
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Social");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Social Network", "Twitter");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromGetPremiumCTANonSubUser() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered  when user navigates to Login Screen from Get Premium CTA");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "1234567", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromGetPremiumCTASubUser() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered  when user navigates to Login Screen from Get Premium CTA");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "rsvod3@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "123456", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");
	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromGetPremiumCTAMobileLogin() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered  when user navigates to Login Screen from Get Premium CTA");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");
	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromGetPremiumCTAMobileLoginInvlaidData() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered  when user navigates to Login Screen from Get Premium CTA for invali data");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "9880710182", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "asdf@123", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	@SuppressWarnings("static-access")
	public void LoginResultEventFromGetPremiumCTAEmailInvlaidData() throws Exception {

		HeaderChildNode(
				"Verify Login Result event is triggered  when user navigates to Login Screen from Get Premium CTA for invalid data");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email ID/Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "zeebuild@gmail.com", "Email ID/Mobile Number");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "asdf@123", "Password field");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Login");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Login Result");

	}

	/*
	 * Event : TV Authentication Screen Display
	 */
	public void verifyTVAuthenticationScreenDisplayEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify TV Authentication Screen Display Event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings");
			waitTime(3000);
			Swipe("Up", 1);
			verifyElementPresentAndClick(AMDSettingsScreen.objAuthenticateDevice, "Authenticate Device");
			waitTime(10000);
		}
	}

	/**
	 * Register screen
	 */
	/*
	 * Register Screen Display Event
	 */

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromBrowseForFreeCTAEmailIDLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen post entering un-registered Email ID");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "unregemailtovalidate@gmail.com", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");

	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromBrowseForFreeCTAMobileLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen post entering un-registered Mobile Number");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "7656545374", "Email ID field");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");

	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromLoginCTAEmailIDLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen post entering un-registered Email ID");

		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "unregemailtovalidate@gmail.com", "Email ID field");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");

	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromLoginCTAMobileLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen post entering un-registered Mobile Number");

		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "7656545374", "Email ID field");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");

	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromProfilePageEmailIDLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen from Profile post entering un-registered Email ID");

		click(AMDHomePage.objMoreMenu, "More menu button");
		if (userType.equalsIgnoreCase("SubscribedUser") | userType.equalsIgnoreCase("NonSubscribedUser")) {
			Swipe("Up", 2);
			click(AMDMoreMenu.objLogoutBtn, "Logout button");
			click(AMDHomePage.objLogout, "Logout");
			click(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
			Swipe("Down", 2);
		}

		click(AMDMoreMenu.objLoginRegisterText, "Profile icon");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "unregemailtovalidate@gmail.com", "Email ID field");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");
	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromProfilePageMobileLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen from Profile post entering un-registered Mobile Number");

		click(AMDHomePage.objMoreMenu, "More menu button");
		if (userType.equalsIgnoreCase("SubscribedUser") | userType.equalsIgnoreCase("NonSubscribedUser")) {
			Swipe("Up", 2);
			click(AMDMoreMenu.objLogoutBtn, "Logout button");
			click(AMDHomePage.objLogout, "Logout");
			click(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
			Swipe("Down", 2);
		}

		click(AMDMoreMenu.objLoginRegisterText, "Profile icon");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "7656545374", "Email ID field");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");
	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromGetPremiumPageEmailIDLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen from Get Premium post entering un-registered Email ID Login");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "unregemailtovalidate@gmail.com", "Email ID field");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");

	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEventFromGetPremiumPageMobileLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Screen Display event is triggered  when user navigates to Registration screen from Get Premium post entering un-registered Mobile Number");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "7656545374", "Email ID field");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
		mixpanel.ValidateParameter("", "Register Screen Display");

	}

	/**
	 * Register Initiated
	 */
	/*
	 * Event : Registration Initiated Navigation : Browse for Free
	 */

	public String createNewEmail() {
		String iD = generateRandomInt(100000);
		String emailID = "AutoUser" + iD + "@gmail.com";
		return emailID;
	}

	public void fillRegisterForm() throws Exception {
		HeaderChildNode("Entering all the data in Register screen");

//		verifyElementPresentAndClick(AMDRegistrationScreen.objFirstNameTxtField, "First Name field");
//		type(AMDRegistrationScreen.objFirstNameTxtField, "Name", "First Name field");
//		verifyElementPresentAndClick(AMDRegistrationScreen.objLastNameTxtField, "Last Name field");
//		type(AMDRegistrationScreen.objLastNameTxtField, "Name", "Last Name field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objDOBTxtField, "Dob field");
		type(AMDRegistrationScreen.objDOBTxtField, "25", "DOBTxtField field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		click(AMDRegistrationScreen.objMale, "Male gender");
		verifyElementPresentAndClick(AMDRegistrationScreen.objPasswordTxtField, "Password field");
		type(AMDRegistrationScreen.objPasswordTxtField, "123456", "Password field");
		hideKeyboard();
	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationInitiatedEventFromBrowseForFreeCTAEmailLogin() throws Exception {

		HeaderChildNode(
				"Verify Registration Initiated event is triggered when user navigates from browse for free CTA and entered all valid data");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.ValidateParameter("", "Registration Initiated");
	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationInitiatedEventFromBrowseForFreeCTAMobileLogin() throws Exception {

		HeaderChildNode(
				"Verify Registration Initiated event when user navigates from Browse for free CTA and entering Invalid data");
		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.ValidateParameter("", "Registration Initiated");
	}

	/*
	 * Event : Registration Initiated Navigation : LoginCTA
	 */

	@SuppressWarnings("static-access")
	public void verifyRegistrationInitiatedEventFromLoginCTAEmailLogin() throws Exception {

		HeaderChildNode(
				"Verify Registration Initiated event is triggered when user navigates from Login CTA and entered all valid data");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Initiated");
	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationInitiatedEventFromLoginCTAMobileLogin() throws Exception {

		HeaderChildNode(
				"Verify Registration Initiated event when user navigates from Login CTA and entering Invalid data");
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Initiated");
	}

	/*
	 * Event : Registration Initiated Navigation : Get Premium CTA
	 */

	@SuppressWarnings("static-access")
	public void verifyRegistrationInitiatedEventFromGetPremiumCTAValidEmailLogin() throws Exception {

		HeaderChildNode(
				"Verify Registration Initiated event triggered when user navigates from Get Premium CTA for Valid Email Login");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Initiated");

	}

	@SuppressWarnings("static-access")
	public void verifyRegistrtionInitiatedEventFroGetPremiumCTAMobileLoginInvalidData() throws Exception {

		HeaderChildNode(
				"Verify Register Initiated event is triggered when user navigates from Get Premium CTA for invalid Mobile Login");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Initiated");

	}

	/*
	 * Event : Registration Initiated Navigation : Hamburger Menu
	 */

	@SuppressWarnings("static-access")
	public void verifyRegistrationInitiatedEventFromHamburgerMenuValidEmailLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Initiated event is triggered when user is navigated from Hamburger menu for valid email data");

		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");

		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Initiated");

	}

	@SuppressWarnings("static-access")
	public void verifyRegistrtionInitiatedEventFromHamburgerMenuMobileLoginInvalidData() throws Exception {

		HeaderChildNode(
				"Verify Register Initiated event is triggered when user navigates from Hamburger menu for invalid Mobile Login");

		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");

		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(1000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Initiated");
	}

	/**
	 * Register Result
	 */
	/*
	 * Event : Registration Result Navigation : Browse for Free
	 */

	@SuppressWarnings("static-access")
	public void verifyRegistrationResultEventFromBrowseForFreeCTAValidEmailRegistration() throws Exception {

		HeaderChildNode(
				"Verify Registration Result event is triggered when user navigates from Browse for Free CTA for valid email data registration");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.ValidateParameter("", "Registration Result");

	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationResultEventFromBrowseForFreeCTAInvalidMobileDataRegistration() throws Exception {

		HeaderChildNode(
				"Verify Registration Result event is triggered when user navigates from Browse for free CTA for invalid mobile registration");

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Verify Mobile");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Source", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.ValidateParameter("", "Registration Result");

	}

	/*
	 * Event : Registration Result Navigation : Login CTA
	 */

	@SuppressWarnings("static-access")
	public void verifyRegistrationResultEventFromLoginCTAValidEmailRegistration() throws Exception {
		HeaderChildNode(
				"Verify Registration Result event is triggered when user navigates from Login CTA for Valid email registration");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Result");

	}

	@SuppressWarnings("static-access")
	public void verifyRegistrationResultEventFromLoginCTACTAInvalidMobileDataRegistration() throws Exception {

		HeaderChildNode(
				"Verify Registration Result event is triggered when user navigates from Login CTA for invalid Mobile Registration");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login Link");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Verify Mobile");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.FEProp.setProperty("Source", "Registration");
		mixpanel.ValidateParameter("", "Registration Result");

	}

	/*
	 * Event : Registration Result Navigation : Get Premium CTA
	 */

	@SuppressWarnings("static-access")
	public void verifyRegistrationResultEventFromGetPremiumCTAValidEmailLogin() throws Exception {

		HeaderChildNode(
				"Verify Registration Result event is triggered when user is navigates from Get Premium CTA for valid Email Registration");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Result");

	}

	@SuppressWarnings("static-access")
	public void verifyRegistrtionResultEventFroGetPremiumCTAMobileLoginInvalidData() throws Exception {

		HeaderChildNode(
				"Verify Register Result event is triggered when user navigates from Get Premium CTA for invalid Mobile Login");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search button");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal", "Search bar");
		hideKeyboard();
		click(AMDSearchScreen.objSearchResult("Shivaji Surathkal"), "Search Result");
		verifyElementPresentAndClick(AMDConsumptionScreen.objGetPremiumCTA, "Get Premium CTA");
		waitTime(3000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(AMDConsumptionScreen.objLoginCTA, "Login CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(5000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Verify Mobile");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.ValidateParameter("", "Registration Result");

	}

	/*
	 * Event : Registration Result Navigation : Hamburger menu
	 */

	@SuppressWarnings("static-access")
	public void verifyRegistrationResultEventFromHamburgerMenuValidEmailLogin() throws Exception {

		HeaderChildNode(
				"Verify Register Result event is triggered when user is navigated from Hamburger menu for valid email data");

		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");

		String email = createNewEmail();
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, email, "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(10000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Registration");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Email");
		mixpanel.FEProp.setProperty("Source", "Login");
		mixpanel.ValidateParameter("", "Registration Result");

	}

	@SuppressWarnings("static-access")
	public void verifyRegistrtionResultEventFromHamburgerMenuMobileLoginInvalidData() throws Exception {

		HeaderChildNode(
				"Verify Register Result event is triggered when user navigates from Hamburger menu for invalid Mobile Login");

		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");

		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
		type(AMDLoginScreen.objEmailIdField, "5647393638", "Email ID field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		waitTime(3000);
		fillRegisterForm();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(5000);
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(1000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Page Name", "Verify Mobile");
		mixpanel.FEProp.setProperty("Element", "Registration");
		mixpanel.FEProp.setProperty("Method", "Mobile");
		mixpanel.ValidateParameter("", "Registration Result");
	}

	/**
	 * Prepaid Code & Promo Code
	 */

	/*
	 * Event : Preapid Code Result
	 */

	@SuppressWarnings("static-access")
	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(100000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Plan Selection");
			MixpanelAndroid.ValidateParameter("", "Subscription Selected");
		}
	}

	/*
	 * Event : Promo Code Result
	 */

	@SuppressWarnings("static-access")
	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Valid code");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			String promoCode = "ZEE5SCB";
			waitTime(6000);
			Swipe("UP", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objHaveAPromocode, "Apply Button");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, promoCode, "Promo Code");
			hideKeyboard();
			waitTime(2000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objApply, "Apply Button");
			waitTime(6000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Promo Code", promoCode);
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.FEProp.setProperty("Button Type", "Button");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Pack Duration", "365");
			mixpanel.ValidateParameter("", "Promo Code Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Invalid code");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, "sdcrfd", "Prepaid Code");
			hideKeyboard();
			verifyElementPresentAndClick(AMDSubscibeScreen.objApply, "Apply Button");
			waitTime(5000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.FEProp.setProperty("Page Name", "subscriptionPlan");
			mixpanel.ValidateParameter("", "Promo Code Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPrepaidCodeResultEvent() throws Exception {
		if (!userType.equals("Guest")) {
			extent.HeaderChildNode("Verify Prepaid Code Result event");
			waitTime(2000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objHaveaPrepaidCode, "Have a Prepaid code");
			verifyElementPresentAndClick(AMDMoreMenu.objPrepaidCodeTxt, "Prepaid code text box");
			type(AMDMoreMenu.objPrepaidCodeTxt, "abcdefg", "Prepaid code text box");
			hideKeyboard();
			click(AMDMoreMenu.objApplyBtn, "Apply button");
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Page Name", "More");
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.ValidateParameter("", "Prepaid Code Result");
		}
	}

	public void AudioLanguageChangeEventForTrailerContent(String usertype, String keyword7) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword7 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(10000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objAvailableAudioLanguages,
				"Available Audio Languages");
		if (lang == true) {
			String noOfAudioLanguages = getText(AMDConsumptionScreen.objAvailableAudioLanguages);
			if (noOfAudioLanguages.equalsIgnoreCase("Available in 1 language")) {
				logger.info("Only one language is displayed");
				extent.extentLogger("Audio Language", "Only one language is displayed");
			} else {
				click(AMDConsumptionScreen.objAudioLanguageValue, "Audio Language Value");
				String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
				String firstAudioLanguageOption = getText(AMDConsumptionScreen.objAudioLanguages(1));
				if (selectedAudioLanguageOption.equalsIgnoreCase(firstAudioLanguageOption)) {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Audio Language option");
				} else {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Audio Language option");
				}

				// Audio language change in Full screen mode
				click(AMDPlayerScreen.objPauseIcon, "Pause icon");
				verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
				verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
				verifyElementPresentAndClick(AMDPlayerScreen.objAudioTrackOption, "Audio track option");
				String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
				if (selectedQualityOption.equalsIgnoreCase("Hindi")) {
					verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Audio Language option");
				} else {
					verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Audio Language option");
				}
				Back(2);
				waitTime(4000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				String pContentId = mixpanel.fetchContentId("", "Language Audio Change");
				String pDistinctId = mixpanel.DistinctId;
				ResponseInstance.getContentDetails(pContentId);

				mixpanel.ValidateParameter(pDistinctId, "Language Audio Change");
			}
		} else {
			logger.info("no Audio Language is displayed");
			extent.extentLogger("Audio Language", "No Audio Language is displayed");
		}
	}

	public void AudioLanguageChangeEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Audio Language Change Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitTime(10000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objAvailableAudioLanguages,
				"Available Audio Languages");
		if (lang == true) {
			String noOfAudioLanguages = getText(AMDConsumptionScreen.objAvailableAudioLanguages);
			if (noOfAudioLanguages.equalsIgnoreCase("Available in 1 language")) {
				logger.info("Only one language is displayed");
				extent.extentLogger("Audio Language", "Only one language is displayed");
			} else {
				click(AMDConsumptionScreen.objAudioLanguageValue, "Audio Language Value");
				String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
				String firstAudioLanguageOption = getText(AMDConsumptionScreen.objAudioLanguages(1));
				if (selectedAudioLanguageOption.equalsIgnoreCase(firstAudioLanguageOption)) {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Audio Language option");
				} else {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Audio Language option");
				}
				waitTime(3000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				String pContentId = mixpanel.fetchContentId("", "Language Audio Change");
				String pDistinctId = mixpanel.DistinctId;
				ResponseInstance.getContentDetails(pContentId);

				mixpanel.ValidateParameter(pDistinctId, "Language Audio Change");
			}
		} else {
			logger.info("no Audio Language is displayed");
			extent.extentLogger("Audio Language", "No Audio Language is displayed");
		}
	}

	public void AuioLanguageChangeEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Audio Language Change Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(15000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objAvailableAudioLanguages,
				"Available Audio Languages");
		if (lang == true) {
			String noOfAudioLanguages = getText(AMDConsumptionScreen.objAvailableAudioLanguages);
			if (noOfAudioLanguages.equalsIgnoreCase("Available in 1 language")) {
				logger.info("Only one language is displayed");
				extent.extentLogger("Audio Language", "Only one language is displayed");
			} else {
				click(AMDConsumptionScreen.objAudioLanguageValue, "Audio Language Value");
				String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
				String firstAudioLanguageOption = getText(AMDConsumptionScreen.objAudioLanguages(1));
				if (selectedAudioLanguageOption.equalsIgnoreCase(firstAudioLanguageOption)) {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Audio Language option");
				} else {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Audio Language option");
				}
				waitTime(3000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				String pContentId = mixpanel.fetchContentId("", "Language Audio Change");
				String pDistinctId = mixpanel.DistinctId;
				ResponseInstance.getContentDetails(pContentId);

				mixpanel.ValidateParameter(pDistinctId, "Language Audio Change");
			}
		} else {
			logger.info("no Audio Language is displayed");
			extent.extentLogger("Audio Language", "No Audio Language is displayed");
		}
	}

	public void AudioLanguageChangeEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Audio Language Change Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(10000);
				boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objAvailableAudioLanguages,
						"Available Audio Languages");
				if (lang == true) {
					String noOfAudioLanguages = getText(AMDConsumptionScreen.objAvailableAudioLanguages);
					if (noOfAudioLanguages.equalsIgnoreCase("Available in 1 language")) {
						logger.info("Only one language is displayed");
						extent.extentLogger("Audio Language", "Only one language is displayed");
					} else {
						click(AMDConsumptionScreen.objAudioLanguageValue, "Audio Language Value");
						String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
						String firstAudioLanguageOption = getText(AMDConsumptionScreen.objAudioLanguages(1));
						if (selectedAudioLanguageOption.equalsIgnoreCase(firstAudioLanguageOption)) {
							verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2),
									"Audio Language option");
						} else {
							verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1),
									"Audio Language option");
						}
						waitTime(4000);
						setFEProperty(usertype);
						mixpanel.FEProp.setProperty("Source", "home");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

						mixpanel.ValidateParameter("", "Language Audio Change");
					}
				} else {
					logger.info("no Audio Language is displayed");
					extent.extentLogger("Audio Language", "No Audio Language is displayed");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}

	}

	public void AudioLanguageChangeEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		waitTime(10000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objAvailableAudioLanguages,
				"Available Audio Languages");
		if (lang == true) {
			String noOfAudioLanguages = getText(AMDConsumptionScreen.objAvailableAudioLanguages);
			if (noOfAudioLanguages.equalsIgnoreCase("Available in 1 language")) {
				logger.info("Only one language is displayed");
				extent.extentLogger("Audio Language", "Only one language is displayed");
			} else {
				click(AMDConsumptionScreen.objAudioLanguageValue, "Audio Language Value");
				String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
				String firstAudioLanguageOption = getText(AMDConsumptionScreen.objAudioLanguages(1));
				if (selectedAudioLanguageOption.equalsIgnoreCase(firstAudioLanguageOption)) {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Audio Language option");
				} else {
					verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Audio Language option");
				}
				waitTime(4000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				String pContentId = mixpanel.fetchContentId("", "Language Audio Change");
				String pDistinctId = mixpanel.DistinctId;
				ResponseInstance.getContentDetails(pContentId);

				mixpanel.ValidateParameter(pDistinctId, "Language Audio Change");
			}
		} else {
			logger.info("no Audio Language is displayed");
			extent.extentLogger("Audio Language", "No Audio Language is displayed");
		}
	}

	public void SubtitleLanguageChangeEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Subtitle Language Change Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(10000);
				boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objSubtitleValue, "Subtitle option value");
				if (lang == true) {
					click(AMDConsumptionScreen.objSubtitleValue, "Subtitle Value");
					String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
					if (selectedAudioLanguageOption.equalsIgnoreCase("Off")) {
						verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2),
								"Subtitle Language option");
					} else {
						verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1),
								"Subtitle Language option");
					}
					waitTime(4000);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					String pContentId = mixpanel.fetchContentId("", "Subtitle Language Change");
					String pDistinctId = mixpanel.DistinctId;
					ResponseInstance.getContentDetails(pContentId);

					mixpanel.ValidateParameter(pDistinctId, "Subtitle Language Change");
				} else {
					logger.info("No Subtitles for this content");
					extent.extentLogger("Subtitle", "No Subtitles for this content");
				}
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void SubtitleLanguageChangeEventForTrailerContent(String usertype, String keyword7) throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword7 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(10000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objSubtitleValue, "Subtitle option value");
		if (lang == true) {
			click(AMDConsumptionScreen.objSubtitleValue, "Subtitle Value");
			String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
			if (selectedAudioLanguageOption.equalsIgnoreCase("Off")) {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Subtitle option");
			} else {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Subtitle option");
			}

			// Audio language change in Full screen mode
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Three Dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objSubtitleOption, "Subtitle option");
			String selectedQualityOption = getText(AMDPlayerScreen.objSelectedQualityOption);
			if (selectedQualityOption.equalsIgnoreCase("Off")) {
				verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(2), "Subtitle option");
			} else {
				verifyElementPresentAndClick(AMDPlayerScreen.objQualityOptions(1), "Subtitle option");
			}
			waitTime(4000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			String pContentId = mixpanel.fetchContentId("", "Subtitle Language Change");
			String pDistinctId = mixpanel.DistinctId;
			ResponseInstance.getContentDetails(pContentId);

			mixpanel.ValidateParameter(pDistinctId, "Subtitle Language Change");
		} else {
			logger.info("No Subtitles for this content");
			extent.extentLogger("Subtitle", "No Subtitles for this content");
		}

	}

	public void SubtitleLanguageChangeEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Subtitle Language Change Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitTime(10000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objSubtitleValue, "Subtitle option value");
		if (lang == true) {
			click(AMDConsumptionScreen.objSubtitleValue, "Subtitle Value");
			String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
			if (selectedAudioLanguageOption.equalsIgnoreCase("Off")) {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Subtitle option");
			} else {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Subtitle option");
			}
			waitTime(3000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			String pContentId = mixpanel.fetchContentId("", "Subtitle Language Change");
			String pDistinctId = mixpanel.DistinctId;
			ResponseInstance.getContentDetails(pContentId);

			mixpanel.ValidateParameter(pDistinctId, "Subtitle Language Change");
		} else {
			logger.info("No Subtitles for this content");
			extent.extentLogger("Subtitle", "No Subtitles for this content");
		}

	}

	public void SubtitleLanguageChangeEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Subtitle Language Change Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(15000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objSubtitleValue, "Subtitle option value");
		if (lang == true) {
			click(AMDConsumptionScreen.objSubtitleValue, "Subtitle Value");
			String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
			if (selectedAudioLanguageOption.equalsIgnoreCase("Off")) {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Subtitle option");
			} else {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Subtitle option");
			}
			waitTime(3000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			String pContentId = mixpanel.fetchContentId("", "Subtitle Language Change");
			String pDistinctId = mixpanel.DistinctId;
			ResponseInstance.getContentDetails(pContentId);

			mixpanel.ValidateParameter(pDistinctId, "Subtitle Language Change");
		} else {
			logger.info("No Subtitles for this content");
			extent.extentLogger("Subtitle", "No Subtitles for this content");
		}
	}

	public void SubtitleLanguageChangeEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Subtitle Language Change Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(10000);
				boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objSubtitleValue, "Subtitle option value");
				if (lang == true) {
					click(AMDConsumptionScreen.objSubtitleValue, "Subtitle Value");
					String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
					if (selectedAudioLanguageOption.equalsIgnoreCase("Off")) {
						verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Subtitle option");
					} else {
						verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Subtitle option");
					}
					waitTime(3000);

					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					String pContentId = mixpanel.fetchContentId("", "Subtitle Language Change");
					String pDistinctId = mixpanel.DistinctId;
					ResponseInstance.getContentDetails(pContentId);

					mixpanel.ValidateParameter(pDistinctId, "Subtitle Language Change");
				} else {
					logger.info("No Subtitles for this content");
					extent.extentLogger("Subtitle", "No Subtitles for this content");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}

	}

	public void SubtitleLanguageChangeEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		waitTime(10000);
		boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objSubtitleValue, "Subtitle option value");
		if (lang == true) {
			click(AMDConsumptionScreen.objSubtitleValue, "Subtitle Value");
			String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
			if (selectedAudioLanguageOption.equalsIgnoreCase("Off")) {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2), "Subtitle option");
			} else {
				verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1), "Subtitle option");
			}
			waitTime(3000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			String pContentId = mixpanel.fetchContentId("", "Subtitle Language Change");
			String pDistinctId = mixpanel.DistinctId;
			ResponseInstance.getContentDetails(pContentId);

			mixpanel.ValidateParameter(pDistinctId, "Subtitle Language Change");
		} else {
			logger.info("No Subtitles for this content");
			extent.extentLogger("Subtitle", "No Subtitles for this content");
		}
	}

	public void AudioLanguageChangeEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Audio Language Change Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(10000);
				boolean lang = verifyIsElementDisplayed(AMDConsumptionScreen.objAvailableAudioLanguages,
						"Available Audio Languages");
				if (lang == true) {
					String noOfAudioLanguages = getText(AMDConsumptionScreen.objAvailableAudioLanguages);
					if (noOfAudioLanguages.equalsIgnoreCase("Available in 1 language")) {
						logger.info("Only one language is displayed");
						extent.extentLogger("Audio Language", "Only one language is displayed");
					} else {
						click(AMDConsumptionScreen.objAudioLanguageValue, "Audio Language Value");
						String selectedAudioLanguageOption = getText(AMDConsumptionScreen.objSelectedAudioLanguage);
						String firstAudioLanguageOption = getText(AMDConsumptionScreen.objAudioLanguages(1));
						if (selectedAudioLanguageOption.equalsIgnoreCase(firstAudioLanguageOption)) {
							verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(2),
									"Audio Language option");
						} else {
							verifyElementPresentAndClick(AMDConsumptionScreen.objAudioLanguages(1),
									"Audio Language option");
						}
						waitTime(4000);
						setFEProperty(usertype);
						mixpanel.FEProp.setProperty("Source", "home");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

						String pContentId = mixpanel.fetchContentId("", "Language Audio Change");
						String pDistinctId = mixpanel.DistinctId;
						ResponseInstance.getContentDetails(pContentId);

						mixpanel.ValidateParameter(pDistinctId, "Language Audio Change");
					}
				} else {
					logger.info("no Audio Language is displayed");
					extent.extentLogger("Audio Language", "No Audio Language is displayed");
				}
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void SkipIntroEventForPremiumContent(String usertype, String keyword4) throws Exception {
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Skip Intro Event of Premium content");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitForElementDisplayed(AMDPlayerScreen.objSkipIntro, 30);
			verifyElementPresentAndClick(AMDPlayerScreen.objSkipIntro, "Skip Intro");
			waitTime(2000);
			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "Search_Tab");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.ValidateParameter("", "Skip Intro");
		} else {
			logger.info("This scenario is not applicable to " + usertype);
			extent.extentLogger("Skip Intro", "This scenario is not applicable to " + usertype);
		}
	}

	public void SkipIntroEventOfContentFromContentTrays(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Skip Intro Event of Content from Content trays");
		waitTime(5000);
		SelectTopNavigationTab(tabName);
		waitTime(10000);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(10000);
				boolean skip = verifyIsElementDisplayed(AMDPlayerScreen.objSkipIntro, "Skip Intro");
				if (skip == true) {
					click(AMDPlayerScreen.objSkipIntro, "Skip Intro");
					waitTime(3000);
					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.ValidateParameter("", "Skip Intro");
				} else {
					logger.info("Skip Intro is not displayed for this content");
					extent.extentLogger("Skip Intro", "Skip Intro is not displayed for this content");
				}
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void SkipIntroEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Skip Intro Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitTime(10000);
		boolean skip = verifyIsElementDisplayed(AMDPlayerScreen.objSkipIntro, "Skip Intro");
		if (skip == true) {
			click(AMDPlayerScreen.objSkipIntro, "Skip Intro");
			waitTime(3000);
			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.ValidateParameter("", "Skip Intro");
		} else {
			logger.info("Skip Intro is not displayed for this content");
			extent.extentLogger("Skip Intro", "Skip Intro is not displayed for this content");
		}
	}

	public void SkipIntroEventOfcontentFromSearchPage(String usertype, String keyword8) throws Exception {
		extent.HeaderChildNode("Skip Intro Event of content from search page");
		if (!usertype.equalsIgnoreCase("guest")) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword8 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
			}
			registerPopUpClose();
			completeProfilePopUpClose(usertype);
			waitTime(3000);
			boolean skip = verifyIsElementDisplayed(AMDPlayerScreen.objSkipIntro, "Skip Intro");
			if (skip == true) {
				click(AMDPlayerScreen.objSkipIntro, "Skip Intro");
				waitTime(3000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.ValidateParameter("", "Skip Intro");
			} else {
				logger.info("Skip Intro is not displayed for this content");
				extent.extentLogger("Skip Intro", "Skip Intro is not displayed for this content");
			}
		} else {
			logger.info("Skip Intro is not applicable for " + usertype);
			extent.extentLogger("Skip Intro", "Skip Intro is not applicable for " + usertype);
		}

	}

	public void forwardAutoSeek(int n) throws Exception {
		Dimension sizee = getDriver().manage().window().getSize();
		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		int FwdXValue;
		if (ScreenOrientation.equalsIgnoreCase("Landscape")) {
			FwdXValue = (int) (sizee.getHeight() * 0.9);
		} else {
			FwdXValue = (int) (sizee.getWidth() * 0.9);
		}
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		int YValue = Integer.valueOf(getAttributValue("y", AMDPlayerScreen.objNextIcon));
		touchAction = new TouchAction(getDriver());
		for (int j = 0; j < n; j++) {
			touchAction.press(PointOption.point(FwdXValue, YValue)).release().perform()
					.press(PointOption.point(FwdXValue, YValue)).release().perform();
		}
	}

	public void rewindAutoSeek(int n) throws Exception {
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		int YValue = Integer.valueOf(getAttributValue("y", AMDPlayerScreen.objNextIcon));
		touchAction = new TouchAction(getDriver());
		for (int i = 0; i < n; i++) {
			touchAction.press(PointOption.point(100, YValue)).release().perform().press(PointOption.point(100, YValue))
					.release().perform();
		}
	}

	public void AutoSeekForwardEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("AutoSeek Forward Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(3000);
				boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
				if (inlineLink == true) {
					logger.info("Player inline subscription link is displayed");
					extentLogger("Player screen", "Player inline subscription link is displayed");
				} else {
					forwardAutoSeek(1);
					waitTime(3000);

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.ValidateParameter("", "Auto-seek");
				}
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void AutoSeekRewindEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("AutoSeek Rewind Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(3000);
				boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
				if (inlineLink == true) {
					logger.info("Player inline subscription link is displayed");
					extentLogger("Player screen", "Player inline subscription link is displayed");
				} else {
					rewindAutoSeek(1);
					waitTime(3000);

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.ValidateParameter("", "Auto-seek");
				}
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void AutoSeekForwardEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify AutoSeek Forward event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		forwardAutoSeek(1);
		waitTime(5000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Auto-seek");
	}

	public void AutoSeekRewindEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify AutoSeek Rewind event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		rewindAutoSeek(1);
		waitTime(3000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Auto-seek");
	}

	public void AutoSeekForwardEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("AutoSeek Forward Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitTime(3000);
		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else {
			forwardAutoSeek(1);

			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.ValidateParameter("", "Auto-seek");
		}
	}

	public void AutoSeekRewindEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("AutoSeek Rewind Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitTime(3000);
		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else {
			rewindAutoSeek(1);
			waitTime(3000);
			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.ValidateParameter("", "Auto-seek");
		}
	}

	public void AutoSeekForwardEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("AutoSeek Forward Event of content from search page");
		waitTime(4000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			waitTime(5000);
		}
		forwardAutoSeek(1);
		waitTime(3000);

		String contentID = getParameterFromXML("clipContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Source", "SearchPage");
		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");

		MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

		MixpanelAndroid.ValidateParameter("", "Auto-seek");
	}

	public void AutoSeekRewindEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("AutoSeek Rewind Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		rewindAutoSeek(1);
		waitTime(3000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Auto-seek");
	}

	public void AutoSeekForwardEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("AutoSeek Forward Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
					waitForAdToFinishInAmd();
				}
				forwardAutoSeek(1);
				waitTime(4000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.ValidateParameter("", "Auto-seek");
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}
	}

	public void AutoSeekRewindEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("AutoSeek Rewind Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();

				if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
					waitForAdToFinishInAmd();
				}
				waitTime(5000);
				rewindAutoSeek(1);
				waitTime(2000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.ValidateParameter("", "Auto-seek");
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}
	}

	public void AutoSeekForwardEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify AutoSeek Forward event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		forwardAutoSeek(1);
		waitTime(4000);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Auto-seek");
	}

	public void AutoSeekRewindEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify AutoSeek Rewind event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		rewindAutoSeek(1);
		waitTime(3000);
		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Auto-seek");
	}

	public void DownloadStartEventForPremiumContent(String usertype, String tabName) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Download Start Event for premium content");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			Swipe("UP", 1);

			String pUserType;
			if (usertype.contains("SubscribedUser")) {
				pUserType = "Premium";
			} else if (usertype.contains("NonSubscribedUser")) {
				pUserType = "expired";
			} else {
				pUserType = "guest";
			}

			boolean var = false;
			for (int i = 0; i < 3; i++) {
				var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
				if (var == true) {
					verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
					waitTime(3000);
					boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
					if (inlineLink == true) {
						logger.info("Player inline subscription link is displayed");
						extentLogger("Player screen", "Player inline subscription link is displayed");
					} else {
						waitTime(3000);
						boolean download = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn,
								"Download icon");
						if (download == true) {
							verifyElementPresentAndClick(AMDConsumptionScreen.objDownloadBtn, "Download icon");
							verifyElementPresentAndClick(AMDConsumptionScreen.objStartDowloadBtn,
									"Start download button");

//						boolean status = verifyIsElementDisplayed(AMDConsumptionScreen.objDowloadStatus, "Download status button");
//						if(status==true) {
//							verifyElementPresentAndClick(AMDConsumptionScreen.objDowloadStatus, "Download status button");
//							verifyElementPresentAndClick(AMDConsumptionScreen.objCancelDownload, "Cancel download button");
//						}
							setFEProperty(usertype);
							waitTime(2000);
							mixpanel.FEProp.setProperty("Source", "home");
							mixpanel.FEProp.setProperty("User Type", pUserType);
							mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

							mixpanel.FEProp.setProperty("Brand", getOEMName);
							mixpanel.FEProp.setProperty("Manufacturer", getOEMName);
							mixpanel.ValidateParameter("", "Download Start");
						} else {
							logger.info("Content is already downloaded");
							extentLogger("Download", "Content is already downloaded");
						}
					}
					break;
				} else {
					Swipe("UP", 1);
				}
			}
			if (var == false) {
				logger.info("Premium content is not displayed in the screen");
				extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
			}
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void DownloadStartEventForTrailerContent(String usertype, String keyword3) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Download start  event for Trailer content");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);

			String pUserType;
			if (usertype.equalsIgnoreCase("SubscribedUser")) {
				pUserType = "Premium";
				verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
			} else if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
				pUserType = "expired";
			} else {
				pUserType = "guest";
			}
			waitTime(3000);
			boolean download = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn, "Download icon");
			if (download == true) {
				verifyElementPresentAndClick(AMDConsumptionScreen.objDownloadBtn, "Download icon");
				verifyElementPresentAndClick(AMDConsumptionScreen.objStartDowloadBtn, "Start download button");

				waitTime(2000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("User Type", pUserType);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Brand", getOEMName);
				mixpanel.FEProp.setProperty("Manufacturer", getOEMName);
				mixpanel.ValidateParameter("", "Download Start");
			} else {
				logger.info("Content is already downloaded");
				extentLogger("Download", "Content is already downloaded");
			}
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void DownloadStartEventForCarouselContent(String usertype, String tabName) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Download start Event for carousel content");
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
			waitTime(3000);

			String pUserType;
			if (usertype.contains("SubscribedUser")) {
				pUserType = "Premium";
			} else if (usertype.contains("NonSubscribedUser")) {
				pUserType = "expired";
			} else {
				pUserType = "guest";
			}

			boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
			if (inlineLink == true) {
				logger.info("Player inline subscription link is displayed");
				extentLogger("Player screen", "Player inline subscription link is displayed");
			} else {
				waitTime(3000);
				boolean download = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn, "Download icon");
				if (download == true) {
					verifyElementPresentAndClick(AMDConsumptionScreen.objDownloadBtn, "Download icon");
					verifyElementPresentAndClick(AMDConsumptionScreen.objStartDowloadBtn, "Start download button");

					waitTime(2000);
					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("User Type", pUserType);
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.FEProp.setProperty("Brand", getOEMName);
					mixpanel.FEProp.setProperty("Manufacturer", getOEMName);
					mixpanel.ValidateParameter("", "Download Start");
				} else {
					logger.info("Content is already downloaded");
					extentLogger("Download", "Content is already downloaded");
				}

			}
			waitTime(3000);
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void DownloadStartEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Download Start Event of content from search page");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);

			verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
			waitTime(3000);
			DownloadVideoQualityPopUp("Good", true);

			String pManufacturer = DeviceDetails.OEM;

			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Download Start");

		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void DownloadStartEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Download start Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);

			String pUserType;
			if (usertype.contains("SubscribedUser")) {
				pUserType = "Premium";
			} else if (usertype.contains("NonSubscribedUser")) {
				pUserType = "expired";
			} else {
				pUserType = "guest";
			}

			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				waitTime(3000);
				boolean download = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn, "Download icon");
				if (download == true) {
					verifyElementPresentAndClick(AMDConsumptionScreen.objDownloadBtn, "Download icon");
					verifyElementPresentAndClick(AMDConsumptionScreen.objStartDowloadBtn, "Start download button");

					waitTime(2000);
					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("User Type", pUserType);
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.FEProp.setProperty("Brand", getOEMName);
					mixpanel.FEProp.setProperty("Manufacturer", getOEMName);
					mixpanel.ValidateParameter("", "Download Start");
				} else {
					logger.info("Content is already downloaded");
					extentLogger("Download", "Content is already downloaded");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}
	}

	public void DownloadStartEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Download start event of content from Upnext rail");
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
			waitTime(8000);
			Swipe("UP", 1);
			if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
				verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray,
						"Upnext rail content");
			}
			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
			}
			registerPopUpClose();
			completeProfilePopUpClose(usertype);
			waitTime(3000);

			String pUserType;
			if (usertype.contains("SubscribedUser")) {
				pUserType = "Premium";
			} else if (usertype.contains("NonSubscribedUser")) {
				pUserType = "expired";
			} else {
				pUserType = "guest";
			}

			boolean download = verifyIsElementDisplayed(AMDConsumptionScreen.objDownloadBtn, "Download icon");
			if (download == true) {
				verifyElementPresentAndClick(AMDConsumptionScreen.objDownloadBtn, "Download icon");
				verifyElementPresentAndClick(AMDConsumptionScreen.objStartDowloadBtn, "Start download button");
				boolean status = verifyIsElementDisplayed(AMDConsumptionScreen.objDowloadStatus,
						"Download status button");
				if (status == true) {
					verifyElementPresentAndClick(AMDConsumptionScreen.objDowloadStatus, "Download status button");
					verifyElementPresentAndClick(AMDConsumptionScreen.objCancelDownload, "Cancel download button");
				}

				waitTime(2000);
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "Search_Tab");
				mixpanel.FEProp.setProperty("User Type", pUserType);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.FEProp.setProperty("Brand", getOEMName);
				mixpanel.FEProp.setProperty("Manufacturer", getOEMName);
				mixpanel.ValidateParameter("", "Download Start");
			} else {
				logger.info("Content is already downloaded");
				extentLogger("Download", "Content is already downloaded");
			}
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void completeProfilePopUpClose(String userType) throws Exception {
		waitTime(5000);
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(AMDPlayerScreen.objCompleteProfilePopUp)) {
				logger.info("Complete Profile Pop Up is displayed");
				extent.extentLogger("Complete Profile Pop Up", "Complete Profile Pop Up is displayed");
				click(AMDGenericObjects.objPopUpDivider, "Click PopUp Divider");
			}
		}
	}

	public void ResumeEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Resume Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(3000);
				boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
				if (inlineLink == true) {
					logger.info("Player inline subscription link is displayed");
					extentLogger("Player screen", "Player inline subscription link is displayed");
				} else {
					waitTime(6000);
					verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
					verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
					waitTime(3000);
					boolean eventFlag = false;
					eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
					waitTime(2000);

					if (eventFlag) {
						setFEProperty(usertype);
						mixpanel.FEProp.setProperty("Source", "home");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
						mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");
						mixpanel.FEProp.setProperty("Appsflyer ID", "VzZG4KdWFLkrRKZheffaHe");

						mixpanel.ValidateParameter("", "Resume");
					} else {
						logger.info("Failed to perform Event Action");
						extentLoggerWarning("Event Action", "Failed to perform Event Action");
					}

				}

				waitTime(3000);
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void ResumeEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Resume event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(3000);

		boolean eventFlag = false;
		eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(2000);

		if (eventFlag) {
			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");
			mixpanel.FEProp.setProperty("Appsflyer ID", "VzZG4KdWFLkrRKZheffaHe");

			mixpanel.ValidateParameter("", "Resume");
		} else {
			logger.info("Failed to perform Event Action");
			extentLoggerWarning("Event Action", "Failed to perform Event Action");
		}

	}

	public void ResumeEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Resume Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		boolean flagBox = verifyIsElementDisplayed(AMDHomePage.objSboxIcon);
		String pSugarBox = String.valueOf(flagBox);

		String contentName = ResponseInstance.getCarouselContentFromAPI(usertype, tabName);
		System.out.println(contentName);

		for (int i = 0; i < 3; i++) {
			if (verifyElementDisplayed(AMDHomePage.objCarouselContentTitle(contentName))) {
				click(AMDHomePage.objCarouselContentTitle(contentName), "carousal content");
				break;
			}

		}

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else {
			waitTime(5000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(3000);
			boolean eventFlag = false;
			eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
			waitTime(2000);

			if (eventFlag) {
				if (usertype.equalsIgnoreCase("SubscribedUser")) {
					ResponseInstance.subscriptionDetails();
				}
				String pPage = getPageName(tabName);
				String pSource = getSource(tabName);
				String pManufacturer = DeviceDetails.OEM;
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", pSource);
				mixpanel.FEProp.setProperty("Page Name", pPage);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("Brand", pManufacturer);
				mixpanel.FEProp.setProperty("Sugar Box Value", pSugarBox);

				mixpanel.ValidateParameter("", "Resume");
			} else {
				logger.info("Failed to perform Event Action");
				extentLoggerWarning("Event Action", "Failed to perform Event Action");
			}
		}
	}

	public void ResumeEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Resume Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(3000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(6000);

		// Resume event on Full Screen mode
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(2000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(3000);
		boolean eventFlag = false;
		eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(2000);

		if (eventFlag) {
			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "Search_Tab");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");
			mixpanel.FEProp.setProperty("Appsflyer ID", "VzZG4KdWFLkrRKZheffaHe");

			mixpanel.ValidateParameter("", "Resume");
		} else {
			logger.info("Failed to perform Event Action");
			extentLoggerWarning("Event Action", "Failed to perform Event Action");
		}
	}

	public void ResumeEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Resume Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
				waitTime(3000);
				boolean eventFlag = false;
				eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
				waitTime(2000);

				if (eventFlag) {
					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
					mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");
					mixpanel.FEProp.setProperty("Appsflyer ID", "VzZG4KdWFLkrRKZheffaHe");

					mixpanel.ValidateParameter("", "Resume");
				} else {
					logger.info("Failed to perform Event Action");
					extentLoggerWarning("Event Action", "Failed to perform Event Action");
				}
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}
	}

	public void ResumeEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Resume event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		completeProfilePopUpClose(usertype);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(5000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(3000);
		boolean eventFlag = false;
		eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(2000);

		if (eventFlag) {
			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "Search_Tab");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");
			mixpanel.FEProp.setProperty("Appsflyer ID", "VzZG4KdWFLkrRKZheffaHe");

			mixpanel.ValidateParameter("", "Resume");
		} else {
			logger.info("Failed to perform Event Action");
			extentLoggerWarning("Event Action", "Failed to perform Event Action");
		}
	}

	public void ResumeEventForLinearContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Resume Event for Linear content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		waitTime(3000);
		verifyElementPresentAndClick(AMDLiveTVScreen.objFirstContentCardUnderFreeChannelsTray, "linear content");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(3000);
		boolean eventFlag = false;
		eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(2000);

		if (eventFlag) {
			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "Live TV");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");
			mixpanel.FEProp.setProperty("Appsflyer ID", "VzZG4KdWFLkrRKZheffaHe");
			mixpanel.ValidateParameter("", "Resume");
		} else {
			logger.info("Failed to perform Event Action");
			extentLoggerWarning("Event Action", "Failed to perform Event Action");
		}
	}

	public void ScrubSeekEventForPremiumContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Scrub/Seek Event for premium content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		Swipe("UP", 1);
		boolean var = false;
		for (int i = 0; i < 3; i++) {
			var = verifyIsElementDisplayed(AMDHomePage.objPremiumTag, "Premium Tag");
			if (var == true) {
				verifyElementPresentAndClick(AMDHomePage.objPremiumTag, "Premium content");
				waitTime(3000);
				boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
				if (inlineLink == true) {
					logger.info("Player inline subscription link is displayed");
					extentLogger("Player screen", "Player inline subscription link is displayed");
				} else {
					waitTime(6000);
					verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
					verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
					seekVideo(AMDPlayerScreen.objProgressBar, usertype);
					waitTime(3000);

					setFEProperty(usertype);
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

					mixpanel.ValidateParameter("", "Scrub/Seek");
				}
				break;
			} else {
				Swipe("UP", 1);
			}
		}
		if (var == false) {
			logger.info("Premium content is not displayed in the screen");
			extentLoggerWarning("Premium Content", "Premium content is not displayed in the screen");
		}
	}

	public void ScrubSeekEventForTrailerContent(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek event for Trailer content");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitForElementDisplayed(AMDPlayerScreen.objPlayer, 30);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer button");
		}
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		seekVideo(AMDPlayerScreen.objProgressBar, usertype);
		waitTime(5000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "Search_Tab");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Scrub/Seek");
	}

	public void ScrubSeekEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Scrub/Seek Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		verifyElementPresentAndClick(AMDHomePage.objCarouselConetentCard, "carousel content");
		waitTime(3000);
		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else {
			waitTime(6000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			seekVideo(AMDPlayerScreen.objProgressBar, usertype);
			waitTime(3000);

			setFEProperty(usertype);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");
			mixpanel.FEProp.setProperty("Appsflyer ID", "VzZG4KdWFLkrRKZheffaHe");
			mixpanel.ValidateParameter("", "Scrub/Seek");
		}
	}

	public void ScrubSeekEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Scrub/Seek Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}

		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		seekVideo(AMDPlayerScreen.objProgressBar, usertype);
		waitTime(4000);

		String contentID = getParameterFromXML("clipContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");

		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Appsflyer Source", "Organic");

		mixpanel.ValidateParameter("", "Scrub/Seek");
	}

	public void ScrubSeekEventOfContentFromMyWatchListPage(String usertype) throws Exception {
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Scrub/Seek Event of content from My WatchList page");
			click(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "Watchlist option");
			click(AMDUserSessionManagement.objMoviesTabUnderWatchList, "Movies Tab");
			waitTime(5000);
			boolean flag = false;
			boolean contentsInMoviesTab = verifyIsElementDisplayed(
					AMDUserSessionManagement.objcontentTitleInWatchListAndReminders);
			if (contentsInMoviesTab == true) {
				getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title'])[1]"))
						.click();
				verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
				seekVideo(AMDPlayerScreen.objProgressBar, usertype);
				waitTime(4000);

				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

				mixpanel.ValidateParameter("", "Scrub/Seek");
			} else {
				logger.info("No contents in Watchlist");
				extentLoggerWarning("Watchlist", "No contents in Watchlist");
			}
		} else {
			logger.info("Watchlist is not applicable for " + usertype);
			extentLogger("Guest User", "Watchlist is not applicable for " + usertype);
		}
	}

	public void ScrubSeekEventOfContentFromUpNextRail(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek event of content from Upnext rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(8000);
		Swipe("UP", 1);
		if (verifyElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpnextTray, "Upnext rail content");
		}
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		seekVideo(AMDPlayerScreen.objProgressBar, usertype);
		waitTime(5000);

		setFEProperty(usertype);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.ValidateParameter("", "Scrub/Seek");
	}

	public void setFEProperty(String pUserType) {
		if (!(pUserType.equalsIgnoreCase("Guest"))) {

			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}

			Properties pro = new Properties();
			pro = ResponseInstance.getUserSettingsDetails(Username, Password);

			String value1 = null;
			boolean var1 = pro.getProperty("eduauraaClaimed") != null;
			if (var1 == false) {
				value1 = "N/A";
			} else {
				value1 = pro.getProperty("eduauraaClaimed");
				if (value1.equalsIgnoreCase("Empty")) {
					value1 = "N/A";
				}
			}

			String value2 = null;
			boolean var = pro.getProperty("parental_control") != null;
			if (var == false) {
				value2 = "N/A";
			} else {
				value2 = pro.getProperty("parental_control");
				if (value2.equalsIgnoreCase("Empty")) {
					value2 = "N/A";
				}
			}

			mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", pro.getProperty("streaming_quality"));
			mixpanel.FEProp.setProperty("New Autoplay Setting", pro.getProperty("auto_play"));
			mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", pro.getProperty("stream_over_wifi"));
			mixpanel.FEProp.setProperty("New Download Quality Setting", pro.getProperty("download_quality"));
			mixpanel.FEProp.setProperty("New Download Over Wifi Setting", pro.getProperty("download_over_wifi"));
			mixpanel.FEProp.setProperty("New App Language", pro.getProperty("display_language"));
			mixpanel.FEProp.setProperty("New Content Language", pro.getProperty("content_language"));
			mixpanel.FEProp.setProperty("hasEduauraa", value1);
			mixpanel.FEProp.setProperty("isEduauraa", value1);
			mixpanel.FEProp.setProperty("Parent Control Setting", value2);
			mixpanel.FEProp.setProperty("Partner Name", "Zee5");
			mixpanel.FEProp.setProperty("Gender",
					ResponseInstance.getUserData_NativeAndroid(Username, Password).getProperty("gender"));
		} else {
			mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", "Auto");
			mixpanel.FEProp.setProperty("New Autoplay Setting", "true");
			mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", "false");
			mixpanel.FEProp.setProperty("New Download Quality Setting", "Ask Each Time");
			mixpanel.FEProp.setProperty("New Download Over Wifi Setting", "false");
			mixpanel.FEProp.setProperty("New App Language", "en");
			mixpanel.FEProp.setProperty("New Content Language", "hi,en,kn");
		}
	}

	public void VerifyFirstAppLaunchEvent(String usertype) throws Exception {
		if (usertype.equalsIgnoreCase("Guest")) {
			HeaderChildNode("Verify First App Launch event");
			logger.info("Relaunching the application");
			extent.extentLogger("Relaunch", "Relaunching the application");
			waitTime(10000);
			getDriver().quit();
			relaunch = true;
			new Zee5ApplicasterBusinessLogic("zee");
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("User Type", "guest");
			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", "N/A");
			mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);
			mixpanel.ValidateParameter("", "First Launch");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginPasswordEnteredEvent() throws Exception {
		HeaderChildNode("Login Password Entered event");

		String userName = getParameterFromXML("NonsubscribedUserName");
		String Password = getParameterFromXML("NonsubscribedPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
		type(AMDLoginScreen.objEmailIdField, userName, "Email Field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
		type(AMDLoginScreen.objPasswordField, Password, "Password field");
		hideKeyboard();
		waitTime(10000);
		setFEProperty(userType);

		mixpanel.FEProp.setProperty("Source", "LoginRegister");
		mixpanel.FEProp.setProperty("Page Name", "Login");
		mixpanel.ValidateParameter("", "Login Password Entered");

	}

	@SuppressWarnings("static-access")
	public void verifyUserNameEnteredEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			HeaderChildNode("User Name Entered event");

			String userName = getParameterFromXML("NonsubscribedUserName");
			verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "Browse for Free CTA");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, userName, "Email Field");
			hideKeyboard();
			waitTime(10000);
			setFEProperty(userType);

			mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
			mixpanel.FEProp.setProperty("Source", "Intro");
			mixpanel.ValidateParameter("", "Username Entered");
		}

	}

	@SuppressWarnings("static-access")
	public void verifyCarouselBannerSwipeEvent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Carousel Banner Swipe Event Across tabs");
		SelectTopNavigationTab(tabName);
		waitForElementDisplayed(AMDHomePage.objCarouselDots, 10);
		waitTime(10000);

		String width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);
		String bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];
		waitTime(3000);
		String Carouseltitle1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle1);
		extentLoggerPass("Carousel Title", Carouseltitle1);
		carouselCardsSwipe("LEFT", 1, width, height);

		String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
		Response pageResp = ResponseInstance.getResponseForAppPages(tabName, pContentLang, userType);
		String carousalName = pageResp.jsonPath().get("buckets[0].title");
		String carousalid = pageResp.jsonPath().get("buckets[0].id");
		MixpanelAndroid.FEProp.setProperty("Carousal Name", carousalName);
		MixpanelAndroid.FEProp.setProperty("Carousal ID", carousalid);

		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Page Name", "Homepage");
		mixpanel.FEProp.setProperty("Direction", "RIGHT");

		MixpanelAndroid.ValidateParameter("", "Carousal Banner Swipe");
	}

	public void verifySessionEvent(String userType) throws Exception {
		HeaderChildNode("App Session event");
		relaunchTillDisplayLanguageScreen(true);
		mixpanel.FEProp.setProperty("User Type", "guest");
		setFEProperty(userType);
		mixpanel.ValidateParameter("", "App session");
	}

	public void VerifyUIUXDownloadScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify the UI/UX of Download landing screen as " + userType);
		System.out.println("\nVerify the UI/UX of Download landing screen as " + userType);
		waitTime(5000);
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		waitTime(3000);
		verifyElementExist(AMDDownloadPage.objDwnloadsHeader, "Downloads header at the top on center of the screen");
		verifyElementExist(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementExist(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen ");
		String getPropertyValue = getAttributValue("enabled", AMDDownloadPage.objshowstab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			extent.extentLogger("Shows tab", "Shows tab is by default highlighted");
			logger.info("Shows tab is by default highlighted");
		} else {
			extent.extentLoggerFail("Shows tab", "Shows tab fails to highlight by default");
			logger.error("Shows tab fails to highlight by default");
		}
		verifyElementPresentAndClick(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
		verifyElementPresentAndClick(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
		verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
	}

	public void VerifyBrowseToDownloadFunctionality(String userType) throws Exception {
		extent.HeaderChildNode("Verify Browse to Download CTA functionality as " + userType);
		System.out.println("\nVerify Browse to Download CTA functionality as " + userType);
		String getSelectedTabName;
		verifyElementPresentAndClick(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Shows")) {
			extent.extentLogger("Shows tab", "User is navigated to Shows landing page");
			logger.info("User is navigated to Shows landing page");
		} else {
			extent.extentLoggerFail("Shows tab", "User fails to navigate to Shows landing page and instead displayed : "
					+ getSelectedTabName + " landing screen");
			logger.error("User fails to navigate to Shows landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		verifyElementPresentAndClick(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Movies tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Movies")) {
			extent.extentLogger("Movies tab", "User is navigated to Movies landing page");
			logger.info("User is navigated to Movies landing page");
		} else {
			extent.extentLoggerFail("Movies tab",
					"User fails to navigate to Movies landing page and instead displayed : " + getSelectedTabName
							+ " landing screen");
			logger.error("User fails to navigate to Movies landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Videos tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Videos")) {
			extent.extentLogger("Videos tab", "User is navigated to videos landing page");
			logger.info("User is navigated to Videos landing page");
		} else {
			extent.extentLoggerFail("Movies tab",
					"User fails to navigate to Videos landing page and instead displayed : " + getSelectedTabName
							+ " landing screen");
			logger.error("User fails to navigate to Videos landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
	}

	public void videoViewEventFromHomePage(String usertype, String tabName) throws Exception {
		setFEProperty(usertype);
		String trayName = ResponseInstance.getTrayNameFromPage(tabName, usertype);
		SwipeUntilFindElement(AMDHomePage.objTrayTitle(trayName), "UP");
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromTrayTitle(trayName), "Content Card");
		waitTime(5000);
		String pManufacturer = DeviceDetails.OEM;
		System.out.println(pManufacturer);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
//		mixpanel.FEProp.setProperty("Manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("Brand", pManufacturer);
		mixpanel.ValidateParameter("", "Video View");
	}

	public static String getSource(String tabName) {

		String pSource = "N/A";
		switch (tabName.toLowerCase()) {
		case "home":
			pSource = "Homepage";
			break;

		case "tv shows":
			pSource = "Homepage";
			break;

		case "movies":
			pSource = "Homepage";
			break;

		case "premium":
			pSource = "Homepage";
			break;

		case "news":
			pSource = "Homepage";
			break;

		case "club":
			pSource = "news";
			break;

		case "eduauraa":
			pSource = "Homepage";
			break;

		case "music":
			pSource = "Homepage";
			break;

		case "live tv":
			pSource = "kids";
			break;

		case "zeeoriginals":
			pSource = "livetv";
			break;

		case "web series":
			pSource = "Homepage";
			break;

		}
		return pSource;
	}

	public static String getPageName(String tabName) {

		String pPageName = "N/A";
		switch (tabName.toLowerCase()) {
		case "home":
			pPageName = "home";
			break;

		case "shows":
			pPageName = "tvshows";
			break;

		case "movies":
			pPageName = "movies";
			break;

		case "premium":
			pPageName = "premium";
			break;

		case "music":
			pPageName = "music";
			break;

		case "eduauraa":
			pPageName = "kids";
			break;

		case "news":
			pPageName = "news";
			break;

		case "club":
			pPageName = "premium";
			break;

		case "zeeoriginals":
			pPageName = "originals";
			break;

		case "zee5 originals":
			pPageName = "originals";
			break;

		case "live tv":
			pPageName = "livetv";
			break;

		default:
			pPageName = "home";
			break;
		}
		return pPageName;
	}

	public void videoViewEventFromTopNavigationPage(String pUsertype, String pTabName) throws Exception {

		waitForElementDisplayed(AMDHomePage.objTitle, 20);
		SelectTopNavigationTab(pTabName);
		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(pUsertype);
		System.out.println(contentLang);

//		String trayName = ResponseInstance.getTrayNameFromPage(pTabName, pUsertype);
		String trayName = ResponseInstance.getRailNameFromPage(pTabName, pUsertype);

		if (pTabName.equalsIgnoreCase("Live TV") || pTabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}

		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");
		if (pUsertype.equalsIgnoreCase("Guest") || pUsertype.equalsIgnoreCase("NonSubscribedUser")) {
			registerPopUpClose();
			waitForAdToFinishInAmd();
			waitTime(2000);
			registerPopUpClose();
			Back(1);
		} else {
			waitTime(3000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player Screen");
			verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		}

//		####### Set All Parameters values ####### 
		setFEProperty(pUsertype);
		setUserType_SubscriptionProperties(pUsertype);

//		String pPage = getPageName(pTabName);
//		String pSource = getSource(pTabName);
		String pPage = "ConsumptionPage";
		String pSource = "Homepage";
		String pManufacturer = DeviceDetails.OEM;

//		String pAdId = getAdId();
//		mixpanel.FEProp.setProperty("Ad ID", pAdId);
//		mixpanel.FEProp.setProperty("Advertisement ID", pAdId);

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Video View");
	}

	public void ResumeEventForTrayContent(String usertype, String tabName, String AdvertisementID) throws Exception {
		extent.HeaderChildNode("Resume Event for Tray content of " + tabName);
		String trayName = ResponseInstance.getTrayNameFromPage(tabName, usertype);
		waitTime(10000);
		SwipeUntilFindElement(AMDHomePage.objTrayTitle(trayName), "UP");
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromTrayTitle(trayName), "Content Card");
		verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(3000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		setFEProperty(usertype);
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			ResponseInstance.subscriptionDetails();
		}
		String pManufacturer = DeviceDetails.OEM;
		System.out.println(pManufacturer);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("Brand", pManufacturer);
		mixpanel.FEProp.setProperty("Advertisement ID", AdvertisementID);
		mixpanel.ValidateParameter("", "Resume");
	}

	public void SearchContentFromSearchPage(String userType, String contentType, String contentId, String contentName)
			throws Exception {

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search edit box");
		type(AMDSearchScreen.objSearchEditBox, contentName, "Search edit box");
//		click(AMDSearchScreen.objSearchItemBySearchTitle(contentName),"Searched content");
		click(AMDSearchScreen.objFirstSearchResult(contentName), "Searched content");

		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			waitForAdToFinishInAmd();
		}
		waitTime(2000);
		if (contentType.equalsIgnoreCase("Trailer")) {
			ResponseInstance.setPropertyForContentDetailsFromSearchPage(contentId);
		}

		if (contentType.equalsIgnoreCase("Free")) {
			ResponseInstance.setPropertyForContentDetailsFromSearchPage(contentId);
		}

		if (contentType.equalsIgnoreCase("Premium")) {
			ResponseInstance.setPropertyForContentDetailsFromSearchPage(contentId);
		}
	}

	public void PauseEvent(String userType, String contentType, String contentID, String contentName) throws Exception {
		HeaderChildNode("Verify Pause Event from search navigation");
		SearchContentFromSearchPage(userType, contentType, contentID, contentName);
		waitTime(3000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(2000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.ValidateParameter("", "Pause");

	}

	public void videoViewEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Video view Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		boolean flagBox = verifyIsElementDisplayed(AMDHomePage.objSboxIcon);
		String pSugarBox = String.valueOf(flagBox);

		String contentName = ResponseInstance.getCarouselContentFromAPI(usertype, tabName);
		System.out.println(contentName);

		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 60, "carousal content");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLoggerWarning("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLoggerWarning("Player screen",
					"error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = false;
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			eventFlag = verifyElementPresent(AMDPlayerScreen.objFullscreenIcon, "Player screen");
			waitTime(2000);

			if (eventFlag) {
//				if (usertype.equalsIgnoreCase("SubscribedUser")) {
//					ResponseInstance.subscriptionDetails();
//				}
				String pPage = "ConsumptionPage";
				String pSource = getSource(tabName);
				String pManufacturer = DeviceDetails.OEM;
				// String pAdId = getAdId();

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);

				// mixpanel.FEProp.setProperty("Ad ID", getParameterFromXML("AdID"));
				// mixpanel.FEProp.setProperty("Advertisement ID", getParameterFromXML("AdID"));
				mixpanel.FEProp.setProperty("Source", pSource);
				mixpanel.FEProp.setProperty("Page Name", pPage);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("Manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("Brand", pManufacturer);
				// mixpanel.FEProp.setProperty("Sugar Box Value",pSugarBox );
				mixpanel.FEProp.setProperty("Carousal Name", "N/A");

				mixpanel.ValidateParameter("", "Video View");
			} else {
				logger.info("Failed to display player screen");
				extentLoggerWarning("Event", "Failed to display player screen");
			}
		}
	}

	public void PlayerViewChangedEvent(String userType, String contentType, String contentID, String contentName)
			throws Exception {
		HeaderChildNode("Verify Resume Event from search navigation");
		SearchContentFromSearchPage(userType, contentType, contentID, contentName);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(4000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.ValidateParameter("", "Player View Changed");

	}

	public void VideoWatchDurationEvent(String userType, String contentType, String contentID, String contentName)
			throws Exception {
		HeaderChildNode("Verify Resume Event from search navigation");
		SearchContentFromSearchPage(userType, contentType, contentID, contentName);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		waitTime(4000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void VideoExitEvent(String userType, String contentType, String contentID, String contentName)
			throws Exception {
		HeaderChildNode("Verify Resume Event from search navigation");
		SearchContentFromSearchPage(userType, contentType, contentID, contentName);
		waitTime(6000);
		verifyElementPresent(AMDPlayerScreen.objPlayerScreen, "Player screen");
		Back(1);
		waitTime(2000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		Mixpanel.ValidateParameter("", "Video Exit");
	}

	public void ResumeEvent(String userType, String contentType, String contentID, String contentName)
			throws Exception {
		HeaderChildNode("Verify Resume Event from search navigation");
		SearchContentFromSearchPage(userType, contentType, contentID, contentName);
		waitTime(6000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player screen");
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		waitTime(3000);
		verifyElementPresentAndClick(AMDPlayerScreen.objPlayIcon, "Play icon");
		waitTime(2000);
		verifyElementPresent(AMDPlayerScreen.objPlayerScreen, "Player screen");
		waitTime(2000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.ValidateParameter("", "Resume");
	}

	public void VideoViewEvent(String userType, String contentType, String contentID, String contentName)
			throws Exception {
		HeaderChildNode("Verify Pause Event from search navigation");
		SearchContentFromSearchPage(userType, contentType, contentID, contentName);
		waitTime(3000);
		verifyElementPresent(AMDPlayerScreen.objPlayerScreen, "Player screen");
		waitTime(2000);
		setFEProperty(userType);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.ValidateParameter("", "Video View");
	}

	public void setUserType_SubscriptionProperties(String pUsertype) {
		String pUsername, pPassword;
		if (!pUsertype.equalsIgnoreCase("Guest")) {
			if (pUsertype.equalsIgnoreCase("SubscribedUser")) {
				pUsername = getParameterFromXML("SubscribedUserName");
				pPassword = getParameterFromXML("SubscribedPassword");

				ResponseInstance.getRegionDetails();
				ResponseInstance.getUniqueAndUserID(pUsername, pPassword);
				ResponseInstance.setSubscriptionDetails_NativeAndroid();
				mixpanel.FEProp.setProperty("User Type", "Premium");
			} else {
				pUsername = getParameterFromXML("NonsubscribedUserName");
				pPassword = getParameterFromXML("NonsubscribedPassword");
				mixpanel.FEProp.setProperty("User Type", "registered");
				MixpanelAndroid.FEProp.setProperty("Pack Duration", "N/A");
				ResponseInstance.getRegionDetails();
			}
			mixpanel.FEProp.setProperty("Email", pUsername);

		} else {
			mixpanel.FEProp.setProperty("User Type", "Free");
			MixpanelAndroid.FEProp.setProperty("Pack Duration", "N/A");
			ResponseInstance.getRegionDetails();
		}
	}

	public void VideoViewEventThroughSearch(String userType, String pTypeOfContent, String pTabName) throws Exception {
		HeaderChildNode("Verify Video View Event through Search navigation");
		System.out.println("\nVerify Video View Event through Search navigation");

		String pPage = "ConsumptionPage";
		String pSource = "SearchPage";
		String pManufacturer = DeviceDetails.OEM;
		String pAdId = getAdId();

		ArrayList<String> arrContentDetails = ResponseInstance.getTrayNameAndContentID("Home", pTypeOfContent);
		String getTrayName, getContentName = null, getContentID = null;
		if (arrContentDetails.size() != 0) {
			getTrayName = arrContentDetails.get(0);
			getContentName = arrContentDetails.get(1);
			getContentID = arrContentDetails.get(2);

			System.out.println("Tray Name: " + getTrayName);
			System.out.println("Content Name: " + getContentName);
			System.out.println("Content ID: " + getContentID);
		}

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search edit box");
		type(AMDSearchScreen.objSearchEditBox, getContentName, "Search edit box");
		click(AMDSearchScreen.objFirstSearchResult(getContentName), "Select Searched content");
		waitTime(2000);
		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			registerPopUpClose();
			waitForAdToFinishInAmd();
			waitTime(2000);
			Back(1);
		} else {
			waitTime(4000);
			Back(1);
		}

		if (getContentID != null) {
			ResponseInstance.setPropertyForContentDetailsFromSearchPage(getContentID);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			mixpanel.FEProp.setProperty("Ad ID", pAdId);
			mixpanel.FEProp.setProperty("Advertisement ID", pAdId);
			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Video View");
		} else {
			logger.error("Failed to fetch the ContentID from API to validate against Mixpanel Dashboard");
			extent.extentLoggerWarning("Validation",
					"Failed to fetch the ContentID from API to validate against Mixpanel Dashboard");
		}

	}

	public void VideoViewEventFromSearchTab(String userType, String contentType, String contentID, String contentName)
			throws Exception {
		HeaderChildNode("Verify Video View Event from Search tab navigation");
		System.out.println("\nVerify Video View Event from Search tab navigation");

		String pSource = "SearchPage";
		String pPage = "ConsumptionPage";
		String pManufacturer = DeviceDetails.OEM;

		SearchContentFromSearchPage(userType, contentType, contentID, contentName);
		waitTime(3000);
		verifyElementPresent(AMDPlayerScreen.objPlayerScreen, "Player screen");
		waitTime(2000);
		Back(1);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Video View");
	}

	public void CarousalBannerImpressionEvent(String pUsertype, String pTabName) throws Exception {
		HeaderChildNode("Verify Carousek banner impression Event from top navigation");

		String pPage = "Homepage";
		String pSource = "SplashPage";
		String pManufacturer = DeviceDetails.OEM;

		waitForElementDisplayed(AMDHomePage.objTitle, 20);
		SelectTopNavigationTab(pTabName);
		waitTime(3000);
		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(pUsertype);
		System.out.println(contentLang);

		setFEProperty(pUsertype);
		setUserType_SubscriptionProperties(pUsertype);

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Carousal Banner Impression");

	}

	public void ScreenViewEventFromTopNavigationPage(String pUsertype, String pTabName) throws Exception {

		waitForElementDisplayed(AMDHomePage.objTitle, 20);
		SelectTopNavigationTab(pTabName);
		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(pUsertype);
		System.out.println(contentLang);

		String trayName = ResponseInstance.getRailNameFromPage(pTabName, pUsertype);

		if (pTabName.equalsIgnoreCase("Live TV") || pTabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}

		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");
		if (pUsertype.equalsIgnoreCase("Guest") || pUsertype.equalsIgnoreCase("NonSubscribedUser")) {
			registerPopUpClose();
			waitForAdToFinishInAmd();
			waitTime(2000);
			registerPopUpClose();
			Back(1);
		} else {
			waitTime(3000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player Screen");
			verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		}

//		####### Set All Parameters values ####### 
		setFEProperty(pUsertype);
		setUserType_SubscriptionProperties(pUsertype);

//		String pPage = getPageName(pTabName);
//		String pSource = getSource(pTabName);
		String pPage = "ConsumptionPage";
		String pSource = "Homepage";
		String pManufacturer = DeviceDetails.OEM;

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Screen View");
	}

	public void PlayerViewChangedEventForCarouselContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Player view changed Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		boolean flagBox = verifyIsElementDisplayed(AMDHomePage.objSboxIcon);
		String pSugarBox = String.valueOf(flagBox);

		String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, tabName);
		System.out.println(contentName);

		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLogger("Player screen", "error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = true;
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");

			ScreenOrientation orientation1 = getDriver().getOrientation();
			String ScreenOrientation1 = orientation1.toString();

			eventFlag = verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
			waitTime(2000);

			ScreenOrientation orientation2 = getDriver().getOrientation();
			String ScreenOrientation2 = orientation2.toString();

			if (eventFlag) {
				String pPage = "ConsumptionPage";
				String pSource = getSource(tabName);
				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);

				mixpanel.FEProp.setProperty("Source", pSource);
				mixpanel.FEProp.setProperty("Page Name", pPage);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);
				mixpanel.FEProp.setProperty("New View Position", ScreenOrientation2);
				mixpanel.FEProp.setProperty("Old View Position", ScreenOrientation1);
				mixpanel.FEProp.setProperty("Carousal Name", "N/A");
				if (tabName.equalsIgnoreCase("TV Shows") || tabName.equalsIgnoreCase("Web Series")) {
					mixpanel.FEProp.setProperty("Series", contentName);
				}

				mixpanel.ValidateParameter("", "Player View Changed");
			} else {
				logger.info("Failed to change the player view");
				extentLoggerWarning("Event", "Failed to change the player view");
			}
		}
	}

	public void AdInitializedEvent(String pUsertype, String pTabName) throws Exception {

//		String pPage = getPageName(pTabName);
//		String pSource = getSource(pTabName);
		String pPage = "ConsumptionPage";
		String pSource = "Homepage";
		String pManufacturer = DeviceDetails.OEM;
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitForElementDisplayed(AMDHomePage.objTitle, 20);
			SelectTopNavigationTab(pTabName);
			String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(pUsertype);
			System.out.println(contentLang);

			String trayName = ResponseInstance.getRailNameFromPage(pTabName, pUsertype);

			if (pTabName.equalsIgnoreCase("Live TV") || pTabName.equalsIgnoreCase("News")) {
				waitTime(5000);
				waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
			}

			SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
			waitTime(3000);
			click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");
			if (pUsertype.equalsIgnoreCase("Guest") || pUsertype.equalsIgnoreCase("NonSubscribedUser")) {
				registerPopUpClose();
				waitForAdToFinishInAmd();
				waitTime(2000);
				registerPopUpClose();
				Back(1);
			} else {
				waitTime(3000);
				verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player Screen");
				verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
			}

//		####### Set All Parameters values ####### 
			setFEProperty(pUsertype);
			setUserType_SubscriptionProperties(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Ad Initialized");
		} else {
			logger.info("Ad Initialized Event is Not applicable for Subscribed user");
			extentLogger("Ad Initialized", "Ad Initialized Event is Not applicable for Subscribed user");
		}
	}

	public void event_LoginInitiated(String pUsertype) throws Exception {

		String pPage = "Login";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";

		waitTime(20000);
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDHomePage.objDownloadBtn, "Download button");
			setFEProperty(pUsertype);
			setUserType_SubscriptionProperties(pUsertype);

			MixpanelAndroid.FEProp.setProperty("Source", pSource);
			MixpanelAndroid.FEProp.setProperty("Page Name", pPage);
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.FEProp.setProperty("manufacturer", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("brand", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("Method", pMethod);
			MixpanelAndroid.FEProp.setProperty("User Type", "guest");
			MixpanelAndroid.FEProp.setProperty("Gender", "N/A");
			MixpanelAndroid.FEProp.setProperty("Age", "N/A");
			MixpanelAndroid.FEProp.setProperty("Partner Name", "N/A");

			MixpanelAndroid.ValidateParameter("", "Login Initatied");
		} else {
			logger.info("Login Initiated Event is Not applicable for Guest user");
			extentLogger("Login Initiated", "Login Initiated Event is Not applicable for Guest user");
		}
	}

	public void event_LoginResult(String pUsertype) throws Exception {

		String pPage = "Login";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";

		waitTime(5000);
		if (!(userType.equalsIgnoreCase("Guest"))) {
			setFEProperty(pUsertype);
			setUserType_SubscriptionProperties(pUsertype);

			MixpanelAndroid.FEProp.setProperty("Source", pSource);
			MixpanelAndroid.FEProp.setProperty("Page Name", pPage);
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.FEProp.setProperty("manufacturer", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("brand", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("Method", pMethod);

			MixpanelAndroid.ValidateParameter("", "Login Result");
		} else {
			logger.info("Login Result Event is Not applicable for Guest user");
			extentLogger("Login Result", "Login Result Event is Not applicable for Guest user");
		}
	}

	public void event_LogOut(String pUsertype) throws Exception {

		String pPage = "Logout";
		String pSource = "More";
		String pManufacturer = DeviceDetails.OEM;

		waitTime(5000);
		if (!(userType.equalsIgnoreCase("Guest"))) {
			setFEProperty(pUsertype);
			setUserType_SubscriptionProperties(pUsertype);

			MixpanelAndroid.FEProp.setProperty("Source", pSource);
			MixpanelAndroid.FEProp.setProperty("Page Name", pPage);
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.FEProp.setProperty("manufacturer", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("brand", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("User Type", "guest");

			MixpanelAndroid.ValidateParameter("", "Logout");
		} else {
			logger.info("Logout Event is Not applicable for Guest user");
			extentLogger("Logout", "Logout Event is Not applicable for Guest user");
		}
	}

	public void verifyVideoQualityChangeEvent(String qualityOption) throws Exception {
		extent.HeaderChildNode("Verify video quality change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		getDriver().findElement(By.xpath("//*[@id='qualityPixels']")).click();
		boolean var = verifyIsElementDisplayed(AMDMoreMenu.objSelectedVideoQualityOption("Auto"));

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old Video Streaming Quality Setting",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("streaming_quality"));
		} else {
			mixpanel.FEProp.setProperty("Old Video Streaming Quality Setting", "Auto");
		}
		if (var == true) {
			click(AMDMoreMenu.objVideoQualityOption(qualityOption), "Video quality option");
		} else {
			click(AMDMoreMenu.objAutoOption, "option Auto");
		}

		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		waitTime(10000);
		MixpanelAndroid.FEProp.setProperty("Source", "More");
		MixpanelAndroid.FEProp.setProperty("Page Name", "selector");
		MixpanelAndroid.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		MixpanelAndroid.FEProp.setProperty("Brand", DeviceDetails.OEM);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
		}
		if (var == true) {
			mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", qualityOption);
		} else {
			mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", "Auto");
		}

		MixpanelAndroid.ValidateParameter("", "Video Streaming Quality Changed");

		if (var == true) {
			click(AMDMoreMenu.objVideo_Quality(qualityOption), "Video quality option");
			click(AMDMoreMenu.objAutoOption, "option Auto");
		}
	}

	public void navigateToRegisterScreen() throws Exception {
		HeaderChildNode("Navigate to Login/Registration screen");
		verifyElementExist(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free button");
		logger.info("Browse for Free button is displayed in language : "
				+ getText(AMDOnboardingScreen.objBrowseForFreeBtn));
		extent.extentLoggerPass("Browse for Free button", "Browse for Free button is displayed in language : "
				+ getText(AMDOnboardingScreen.objBrowseForFreeBtn));

		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");

		if (verifyIsElementDisplayed(AMDLoginScreen.objLoginLnk)) {
			logger.info("Login/Register Screen is displayed on selecting Browse for Free");
			extent.extentLoggerPass("Login/Register Screen",
					"Login/Register Screen is displayed on selecting Browse for Free");
		} else {
			logger.error("Login/Register Screen is not displayed on selecting Browse for Free");
			extent.extentLoggerFail("Login/Register Screen",
					"Login/Register Screen is not displayed on selecting Browse for Free");
		}
	}

	public void newRegistrationThroughEmail() throws Exception {
		extent.HeaderChildNode("Register a New User");
		System.out.println("\nRegister a New User");

		String pDOB = "01/01/1990", pNewPassword = "123456";
		String regEmailID = generateRandomString(5) + "@zee.com";

		if (checkElementExist(AMDOnboardingScreen.objBrowseForFreeBtn)) {
			verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
		}

		type(AMDRegistrationScreen.objEmailIDTextField, regEmailID, "Email field");
		click(AMDRegistrationScreen.objProceedBtn, "Proceed button");

		verifyElementExist(AMDRegistrationScreen.objScreenTitle, "Register new user");
		type(AMDRegistrationScreen.objFirstNameTxtField, FirstName, "First Name");
		click(AMDRegistrationScreen.objEmailIDHeaderTxt, "HideKeyboard");

		click(AMDRegistrationScreen.objLastNameTxtField, "Last Name field");
		type(AMDRegistrationScreen.objLastNameTxtField, LastName, "Last Name");
		click(AMDRegistrationScreen.objEmailIDHeaderTxt, "HideKeyboard");

		click(AMDRegistrationScreen.objDOBTxtField, "DOB field");
		type(AMDRegistrationScreen.objDOBTxtField, pDOB, "DOB");
		click(AMDRegistrationScreen.objEmailIDHeaderTxt, "HideKeyboard");

		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");
		click(AMDRegistrationScreen.objPasswordTxtField, "Passowrd");
		type(AMDRegistrationScreen.objPasswordTxtField, pNewPassword, "Password field");
		click(AMDRegistrationScreen.objEmailIDHeaderTxt, "HideKeyboard");

		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(4000);
		boolean verifyHomePage = verifyElementExist(AMDHomePage.objHomeTab, "Home Screen");
		if (verifyHomePage) {
			logger.info("New User Registerd to ZEE5 App successfully with following Email Id: " + regEmailID);
			extent.extentLoggerPass("Registration",
					"New User Registerd to ZEE5 App successfully with following Email Id: " + regEmailID);
			click(AMDHomePage.objDownloadBtn, "Download button");
		} else {
			logger.error("New User failed to Register to ZEE5 App with EmailId: " + regEmailID);
			extent.extentLoggerFail("Registration",
					"New User failed to Register to ZEE5 App with EmailId: " + regEmailID);
		}

		// Assigning email Id to Global variable
		generatedEmailId = regEmailID;
	}

	public void event_LoginRegistrationScreenDisplayValiation(String pUsertype) throws Exception {
		System.out.println("\nLogin Registration Screen Display Event Validation");

		String pPage = "LoginRegister";
		String pSource = "Intro";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objDownloadBtn, "Download tab");
			waitTime(30000);
			setFEProperty(pUsertype);

			MixpanelAndroid.FEProp.setProperty("Source", pSource);
			MixpanelAndroid.FEProp.setProperty("Page Name", pPage);
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.FEProp.setProperty("manufacturer", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("brand", pManufacturer);

			MixpanelAndroid.ValidateParameter("", "Login Registration Screen Display");
		} else {
			logger.info("Login Registration Screen Display Event is Not applicable for " + pUsertype);
			extentLogger("Login Registration Screen Display",
					"Login Registration Screen Display Event is Not applicable for " + pUsertype);
		}
	}

	public void event_RegisterScreenDisplayValiation(String pUsertype) throws Exception {
		System.out.println("\nRegister Screen Display Event Validation");

		String pPage = "LoginRegister";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			waitTime(5000);
			setFEProperty(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Register Screen Display");

		} else {
			logger.info("Register Screen Display Event is Not applicable for " + pUsertype);
			extentLogger("Register Screen Display", "Register Screen Display Event is Not applicable for " + pUsertype);
		}
	}

	public void event_RegistrationInitiatedValiation(String pUsertype) throws Exception {
		System.out.println("\nRegistration Initiated Event Validation");

		String pPage = "Registration";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			waitTime(5000);
			setFEProperty(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Registration Initiated");
		} else {
			logger.info("Registration Initiated Event is Not applicable for " + pUsertype);
			extentLogger("Registration Initiated Event",
					"Registration Initiated Event is Not applicable for " + pUsertype);
		}
	}

	public void event_RegistrationFirstNameEnteredValiation(String pUsertype) throws Exception {
		System.out.println("\nRegistration First Name Entered Event Validation");

		String pPage = "Registration";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			waitTime(5000);
			setFEProperty(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Registration First Name Entered");
		} else {
			logger.info("Registration First Name Entered is Not applicable for " + pUsertype);
			extentLogger("Registration First Name Entered",
					"Registration First Name Entered is Not applicable for " + pUsertype);
		}
	}

	public void event_RegistrationLastNameEnteredValiation(String pUsertype) throws Exception {
		System.out.println("\nRegistration Last Name Entered Event Validation");

		String pPage = "Registration";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			waitTime(5000);
			setFEProperty(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Registration Last Name Entered");
		} else {
			logger.info("Registration Last Name Entered is Not applicable for " + pUsertype);
			extentLogger("Registration Last Name Entered",
					"Registration Last Name Entered is Not applicable for " + pUsertype);
		}
	}

	public void event_RegistrationDoBEnteredValiation(String pUsertype) throws Exception {
		System.out.println("Registration DoB Entered Event Validation");

		String pPage = "Registration";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			waitTime(5000);
			setFEProperty(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Registration DoB Entered");

		} else {
			logger.info("Registration DoB Entered is Not applicable for " + pUsertype);
			extentLogger("Registration DoB Entered", "Registration DoB Entered is Not applicable for " + pUsertype);
		}
	}

	public void event_RegistrationGenderEnteredValiation(String pUsertype) throws Exception {
		System.out.println("Registration Gender Entered Event Validation");

		String pPage = "Registration";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			waitTime(5000);
			setFEProperty(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Registration Gender Entered");
		} else {
			logger.info("Registration Gender Entered is Not applicable for " + pUsertype);
			extentLogger("Registration Gender Entered",
					"Registration Gender Entered is Not applicable for " + pUsertype);
		}
	}

	public void event_RegistrationPasswordEnteredValiation(String pUsertype) throws Exception {
		System.out.println("Registration Password Entered Event Validation");

		String pPage = "Registration";
		String pSource = "LoginRegister";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			waitTime(5000);
			setFEProperty(pUsertype);

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Registration Password Entered");
		} else {
			logger.info("Registration Password Entered is Not applicable for " + pUsertype);
			extentLogger("Registration Gender Entered",
					"Registration Password Entered is Not applicable for " + pUsertype);
		}
	}

	public void verifyVideoStreamOverWifiChangedEvent() throws Exception {
		extent.HeaderChildNode("Verify video Stream over wifi changed Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old Stream Over Wifi Setting",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("stream_over_wifi"));
		} else {
			mixpanel.FEProp.setProperty("Old Stream Over Wifi Setting", "false");
		}

		boolean var = verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");
		waitTime(3000);
		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
			mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", "true");
		}

		mixpanel.ValidateParameter("", "Video Stream Over Wifi Changed");

		verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");

	}

	public void verifyVideoAutoPlayChangedEvent() throws Exception {
		extent.HeaderChildNode("Verify video AutoPlay change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old Autoplay Setting",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("auto_play"));
		} else {
			mixpanel.FEProp.setProperty("Old Autoplay Setting", "true");
		}

		boolean var = verifyElementPresentAndClick(AMDMoreMenu.objVideo_Autoply, "Video Autoplay toggle");
		waitTime(3000);

		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
			mixpanel.FEProp.setProperty("New Autoplay Setting", "false");
		}

		mixpanel.ValidateParameter("", "Video Streaming Autoplay Changed");

		verifyElementPresentAndClick(AMDMoreMenu.objVideo_Autoply, "Video Autoplay toggle");

	}

	public void verifyDownloadOverWifiChangedEvent() throws Exception {
		extent.HeaderChildNode("Verify Download Over wifi change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		waitTime(3000);
		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old Download Over Wifi Setting",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("download_over_wifi"));
		} else {
			mixpanel.FEProp.setProperty("Old Download Over Wifi Setting", "false");
		}

		boolean var = verifyElementPresentAndClick(AMDMoreMenu.objDownloads_WifiOnly, "Download over wifi only switch");
		waitTime(3000);
		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
			mixpanel.FEProp.setProperty("New Download Over Wifi Setting", "true");
		}
		mixpanel.ValidateParameter("", "Download Over Wifi Changed");
		verifyElementPresentAndClick(AMDMoreMenu.objDownloads_WifiOnly, "Download over wifi only switch");

	}

	public void verifyCarouselBannerClickEvent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Carousel Banner Click");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		boolean flagBox = verifyIsElementDisplayed(AMDHomePage.objSboxIcon);
		String pSugarBox = String.valueOf(flagBox);

		String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, tabName);
		System.out.println(contentName);

		boolean flag = waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 60,
				"carousal content");

		if (flag == true) {

			String pPage = "Homepage";
			String pSource = getSource(tabName);
			String pManufacturer = DeviceDetails.OEM;
			// String pAdId = getAdId();

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);

			// mixpanel.FEProp.setProperty("Ad ID", getParameterFromXML("AdID"));
			// mixpanel.FEProp.setProperty("Advertisement ID", getParameterFromXML("AdID"));
			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("Brand", pManufacturer);
			// mixpanel.FEProp.setProperty("Sugar Box Value",pSugarBox );
			// mixpanel.FEProp.setProperty("Carousal Name", "N/A");

			mixpanel.ValidateParameter("", "Carousal Banner Click");
		} else {
			logger.info("Failed to click on Carousal banner");
			extentLoggerWarning("Event", "Failed to click on Carousal banner");
		}
	}

	public void setFEProperty(String pUserId, String pPassword) {

		Properties pro = new Properties();
		pro = ResponseInstance.getUserSettingsDetails(pUserId, pPassword);

		System.out.println(pro);
		String value = pro.getProperty("eduauraaClaimed");
		if (value.equalsIgnoreCase("Empty")) {
			value = "false";
		}

		String value2 = null;
		boolean var = pro.getProperty("parental_control") != null;
		if (var == false) {
			value2 = "N/A";
		} else {
			value2 = pro.getProperty("parental_control");
			if (value2.equalsIgnoreCase("Empty")) {
				value2 = "N/A";
			}
		}

		MixpanelAndroid.FEProp.setProperty("New Video Streaming Quality Setting", pro.getProperty("streaming_quality"));
		MixpanelAndroid.FEProp.setProperty("New Autoplay Setting", pro.getProperty("auto_play"));
		MixpanelAndroid.FEProp.setProperty("New Stream Over Wifi Setting", pro.getProperty("stream_over_wifi"));
		MixpanelAndroid.FEProp.setProperty("New Download Quality Setting", pro.getProperty("download_quality"));
		MixpanelAndroid.FEProp.setProperty("New Download Over Wifi Setting", pro.getProperty("download_over_wifi"));
		MixpanelAndroid.FEProp.setProperty("New App Language", pro.getProperty("display_language"));
		MixpanelAndroid.FEProp.setProperty("New Content Language", pro.getProperty("content_language"));
		MixpanelAndroid.FEProp.setProperty("hasEduauraa", value);
		MixpanelAndroid.FEProp.setProperty("isEduauraa", value);
		MixpanelAndroid.FEProp.setProperty("Parent Control Setting", value2);
		MixpanelAndroid.FEProp.setProperty("Partner Name", "N/A");
		MixpanelAndroid.FEProp.setProperty("Gender",
				ResponseInstance.getUserData_NativeAndroid(pUserId, pPassword).getProperty("gender"));
	}

	public void event_HomePageVisitedValiation(String pUsertype) throws Exception {
		System.out.println("\nHomepage Visited Event Validation");

		String pPage = "Homepage";
		String pSource = "Intro";
		String pManufacturer = DeviceDetails.OEM;
		String pMethod = "email";
		if (pUsertype.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objDownloadBtn, "Download tab");
			waitTime(30000);
			setFEProperty(pUsertype);

//			mixpanel.FEProp.setProperty("Source", pSource);
//			mixpanel.FEProp.setProperty("Page Name", pPage);
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.FEProp.setProperty("manufacturer", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("brand", pManufacturer);

			MixpanelAndroid.ValidateParameter("", "Homepage_visited");
		} else {
			logger.info("Homepage_visited Event is Not applicable for " + pUsertype);
			extentLogger("Homepage_visited", "Homepage_visited Event is Not applicable for " + pUsertype);
		}
	}

	public void event_ChangePasswordResultEvent(String userType) throws Exception {
		extent.HeaderChildNode("Change Password Result event");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			MixpanelAndroid.FEProp.setProperty("Page Name", "ChangePassword");
			MixpanelAndroid.FEProp.setProperty("Source", "MyProfile");
			MixpanelAndroid.FEProp.setProperty("Element", "ChangePassword");
			MixpanelAndroid.FEProp.setProperty("Partner Name", "N/A");

			MixpanelAndroid.ValidateParameter("", "Change Password Result");
		} else {
			logger.info("Change Password Result Event is Not applicable for Guest user");
			extentLogger("Change Password Result", "Change Password Result Event is Not applicable for Guest user");
		}
	}

	public void SubscriptionPageEvent(String pUsertype) throws Exception {
		HeaderChildNode("Verify Subscription Page Event");

		if (!(pUsertype.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Subscription");
			waitTime(2000);
			verifyElementPresent(AMDSubscibeScreen.objPremiumBadge, "Subscription screen");

			setFEProperty(pUsertype);
			setUserType_SubscriptionProperties(pUsertype);

			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.FEProp.setProperty("Source", "N/A");
			MixpanelAndroid.FEProp.setProperty("Partner Name", "N/A");
			MixpanelAndroid.FEProp.setProperty("Transaction Currency", "N/A");
			MixpanelAndroid.FEProp.setProperty("Current Subscription", "N/A");

			MixpanelAndroid.ValidateParameter("", "Subscription Page Viewed");
		} else {
			logger.info("Subscription Page Viewed Event is Not applicable for " + pUsertype);
			extentLogger("Subscription Page Viewed",
					"Subscription Page Viewed Event is Not applicable for " + pUsertype);
		}
	}

	public void navigateToHomeScreen() throws Exception {
		extent.HeaderChildNode("Navigation to Home Screen");
		waitTime(20000);
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objUpdatePopUpNoThanksOption)) {
			click(AMDOnboardingScreen.objUpdatePopUpNoThanksOption, "No Thanks on Update popUp");
		}
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objContinueBtnInCountryPopUp)) {
			click(AMDOnboardingScreen.objContinueBtnInCountryPopUp, "Continuebutton(Country_Screen)");
		}
	}

	public void SearchEventFunctinality(String userType, String pContentName) throws Exception {
		HeaderChildNode("Search event verification");
		System.out.println("\nSearch event verification");

		String pSource = "More";
		String pPage = "Homepage";
		String pManufacturer = DeviceDetails.OEM;

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search edit box");
		type(AMDSearchScreen.objSearchEditBox, pContentName, "Search edit box");
		click(AMDSearchScreen.objFirstSearchResult(pContentName), "Searched content");

		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			waitForAdToFinishInAmd();
		}

		waitTime(3000);
		verifyElementPresent(AMDPlayerScreen.objPlayerScreen, "Player screen");
		waitTime(2000);
		Back(1);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Search Button Click");
	}

	public void verifyDisplayLanguageChangeEvent(String usertype, String displayLanguage) throws Exception {
		extent.HeaderChildNode("Verify display language change Event");

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old App Language",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("display_language"));
		} else {
			mixpanel.FEProp.setProperty("Old App Language", "en");
		}

		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		Swipe("UP", 1);
		click(AMDMoreMenu.objSettings, "Settings option");
		waitTime(1000);
		SwipeUntilFindElement(AMDMoreMenu.objDisplayLang, "Up");
		click(AMDMoreMenu.objDisplayLang, "Display language");

		boolean flag = false;
		flag = verifyElementPresentAndClick(AMDOnboardingScreen.objSelectDisplayLang(displayLanguage), "language");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");

		if (flag) {

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);

			mixpanel.FEProp.setProperty("Source", "user_setting");
			mixpanel.FEProp.setProperty("Page Name", "DisplayLanguage");
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);
			if (pUserType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Free");
			}

			if (!(pUserType.equalsIgnoreCase("Guest"))) {
				if (pUserType.equalsIgnoreCase("SubscribedUser")) {
					Username = getParameterFromXML("SubscribedUserName");
					Password = getParameterFromXML("SubscribedPassword");
				} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
					Username = getParameterFromXML("NonsubscribedUserName");
					Password = getParameterFromXML("NonsubscribedPassword");
				}
				mixpanel.FEProp.setProperty("New App Language",
						ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("display_language"));
			} else {
				mixpanel.FEProp.setProperty("New App Language", "mr");
			}

			mixpanel.ValidateParameter("", "Display Language Changed");

			SwipeUntilFindElement(AMDMoreMenu.objDisplayLang, "Up");
			click(AMDMoreMenu.objDisplayLang, "Display language");
			verifyElementPresentAndClick(AMDOnboardingScreen.objSelectDisplayLang("English"), "language");
			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");

		} else {
			logger.info("Failed to change display language");
			extentLoggerWarning("Event", "Failed to change display language");
		}

	}

	public void verifyContentLanguageChangeEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Content language change Event");

			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
			waitTime(1000);
			SwipeUntilFindElement(AMDMoreMenu.objContentLang, "Up");
			click(AMDMoreMenu.objContentLang, "Content language");
			SwipeUntilFindElement(AMDOnboardingScreen.objSelectContentLang("Hindi"), "Up");
			boolean flag = verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang("Hindi"),
					"Hindi Content language");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in Content language screen");

			if (flag) {

				setFEProperty(userType);
				setUserType_SubscriptionProperties(userType);

				mixpanel.FEProp.setProperty("Source", "user_setting");
				mixpanel.FEProp.setProperty("Page Name", "ContentLanguage");
				mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
				mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);
				mixpanel.FEProp.setProperty("User Type", "Free");
				mixpanel.FEProp.setProperty("Old Content Language", "en,kn");
				mixpanel.FEProp.setProperty("New Content Language", "en,kn,hi");

				mixpanel.ValidateParameter("", "Content Language Changed");

				SwipeUntilFindElement(AMDMoreMenu.objContentLang, "Up");
				click(AMDMoreMenu.objContentLang, "Content language");
				SwipeUntilFindElement(AMDOnboardingScreen.objSelectContentLang("Hindi"), "Up");
				click(AMDOnboardingScreen.objSelectContentLang("Hindi"), "Hindi Content language");
				click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in Content language screen");

			} else {
				logger.info("Failed to change content language");
				extentLoggerWarning("Event", "Failed to change content language");
			}
		}
	}

	public void verifyScreenViewEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event");
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More menu icon");
		waitTime(3000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		mixpanel.FEProp.setProperty("Ad ID", getParameterFromXML("AdID"));
		mixpanel.FEProp.setProperty("Advertisement ID", getParameterFromXML("AdID"));
		mixpanel.FEProp.setProperty("Source", " Homepage");
		mixpanel.FEProp.setProperty("Page Name", "More");
		mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);

		mixpanel.ValidateParameter("", "Screen View");
	}

	public void verifySubscriptionPageViewedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event by clicking on Buy plan");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeTeaser, "Buy plan");

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			mixpanel.FEProp.setProperty("Ad ID", getParameterFromXML("AdID"));
			mixpanel.FEProp.setProperty("Advertisement ID", getParameterFromXML("AdID"));
			mixpanel.FEProp.setProperty("Source", "Homepage");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);

			mixpanel.ValidateParameter("", "Subscription Page Viewed");
		} else {
			logger.info("Not applicable for this usertype");
			extent.extentLoggerPass("", "Not applicable for this usertype");
		}
	}

	public void SearchButtonClickEventValidation(String userType, String pContentName) throws Exception {
		HeaderChildNode("Search Button Click event validation");
		System.out.println("\nSearch Button Click event validation");

		String pSource = "More";
		String pPage = "Homepage";
		String pManufacturer = DeviceDetails.OEM;

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search edit box");
		Back(1);

		// ### Fetching API Values of the usersettings
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Search Button Click");
	}

	public void SearchResultClickedEventValidation(String userType, String pContentName) throws Exception {
		HeaderChildNode("Search Result Clicked Event");
		System.out.println("\nSearch Result Clicked Event");

		String pSource = "Landing Search";
		String pPage = "Homepage";
		String pManufacturer = DeviceDetails.OEM;

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search edit box");
		type(AMDSearchScreen.objSearchEditBox, pContentName, "Search edit box");
		click(AMDSearchScreen.objFirstSearchResult(pContentName), "Searched content");

		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			waitForAdToFinishInAmd();
		}
		waitTime(3000);
		verifyElementPresent(AMDPlayerScreen.objPlayerScreen, "Player screen");
		Back(1);

		// ### Fetching API Values of the usersettings
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Search Result Clicked");
	}

	public void PopupLaunchEventValidation(String userType) throws Exception {
		HeaderChildNode("Popup Launch Event");
		System.out.println("\nPopup Launch Event");

		String pSource = "More";
		String pPage = "More";
		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old App Language",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("display_language"));
		} else {
			mixpanel.FEProp.setProperty("Old App Language", "en");
		}

		waitTime(3000);
		if (pUserType.equalsIgnoreCase("Guest")) {
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			SwipeUntilFindElement(AMDMoreMenu.objSettings, "UP");
			click(AMDMoreMenu.objHaveaPrepaidCode, "Have Prepaid Code");
			click(AMDGenericObjects.objPopUpDivider, "Close Popup");

			mixpanel.FEProp.setProperty("Parent Control Setting", str);
			mixpanel.FEProp.setProperty("Pop Up Name", "PrepaidCodeScreen");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", str);

		} else {
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			SwipeUntilFindElement(AMDMoreMenu.objLogout, "UP");
			click(AMDMoreMenu.objLogout, "Logout");
			verifyElementPresentAndClick(AMDMoreMenu.objCancelButton, "Cancel CTA");

			mixpanel.FEProp.setProperty("Pop Up Name", "Logout");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", str);
		}

		// ### Fetching API Values of the usersettings
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", str);
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Popup launch");
	}

	public void PopupCTAEventValidation(String userType) throws Exception {
		HeaderChildNode("Popup CTA Event");
		System.out.println("\nPopup CTA Event");

		String pSource = "More";
		String pPage = "More";
		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old App Language",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("display_language"));
		} else {
			mixpanel.FEProp.setProperty("Old App Language", "en");
		}

		if (pUserType.equalsIgnoreCase("Guest")) {
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			SwipeUntilFindElement(AMDMoreMenu.objSettings, "UP");
			click(AMDMoreMenu.objHaveaPrepaidCode, "Have Prepaid Code");
			click(AMDMoreMenu.objPrepaidCodeTxt, "Have Prepaid Field");
			type(AMDMoreMenu.objPrepaidCodeTxt, "junkdata", "Prepaid code");
			hideKeyboard();
			click(AMDMoreMenu.objApplyBtn, "Apply CTA");

			mixpanel.FEProp.setProperty("Parent Control Setting", str);
			mixpanel.FEProp.setProperty("Pop Up Name", "PrepaidCodeScreen");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", str);
			mixpanel.FEProp.setProperty("Element", "Apply");

		} else {
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			SwipeUntilFindElement(AMDMoreMenu.objLogout, "UP");
			click(AMDMoreMenu.objLogout, "Logout");
			verifyElementPresentAndClick(AMDMoreMenu.objCancelButton, "Cancel CTA");

			mixpanel.FEProp.setProperty("Pop Up Name", "Logout");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", str);
			mixpanel.FEProp.setProperty("Element", "Cancel");
		}

		// ### Fetching API Values of the usersettings
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Player Name", str);
		mixpanel.FEProp.setProperty("Button Type", "Button");
		mixpanel.FEProp.setProperty("Tab Name", "N/A");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Pop Up CTAs");
	}

	public void SetAppsflyerProperty() {
		String str = "N/A";
		mixpanel.FEProp.setProperty("Appsflyer Campaign", str);
		mixpanel.FEProp.setProperty("Appsflyer Medium", str);
		mixpanel.FEProp.setProperty("App Source", str);
		mixpanel.FEProp.setProperty("App Campaign", str);
		mixpanel.FEProp.setProperty("App UTM Source", str);
		mixpanel.FEProp.setProperty("App UTM Medium", str);
		mixpanel.FEProp.setProperty("App UTM Campaign", str);
		mixpanel.FEProp.setProperty("App UTM Content", str);
		mixpanel.FEProp.setProperty("App UTM Term", str);
		mixpanel.FEProp.setProperty("App isRetargeting", str);
	}

	public void setFEPropertyNewUser() {
		String str = "N/A";
		String pSource = "Login";
		String pPage = "Registration";

		mixpanel.FEProp.setProperty("User Type", "Free");
		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPage);
		mixpanel.FEProp.setProperty("Element", "Registration");

		mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", "Auto");
		mixpanel.FEProp.setProperty("New Autoplay Setting", "true");
		mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", "false");
		mixpanel.FEProp.setProperty("New Download Quality Setting", "Ask Each Time");
		mixpanel.FEProp.setProperty("New Download Over Wifi Setting", "false");
		mixpanel.FEProp.setProperty("New App Language", "en");
		mixpanel.FEProp.setProperty("New Content Language", "en,kn");
		mixpanel.FEProp.setProperty("New Content Language", "en,kn");

		mixpanel.FEProp.setProperty("Appsflyer Campaign", str);
		mixpanel.FEProp.setProperty("Appsflyer Medium", str);
		mixpanel.FEProp.setProperty("App Source", str);
		mixpanel.FEProp.setProperty("App Campaign", str);
		mixpanel.FEProp.setProperty("App UTM Source", str);
		mixpanel.FEProp.setProperty("App UTM Medium", str);
		mixpanel.FEProp.setProperty("App UTM Campaign", str);
		mixpanel.FEProp.setProperty("App UTM Content", str);
		mixpanel.FEProp.setProperty("App UTM Term", str);
		mixpanel.FEProp.setProperty("App isRetargeting", str);
	}

	public void RegistrationEventValidation(String userType) throws Exception {
		HeaderChildNode("Registration Events Validation");
		System.out.println("\nRegistration Events Validation");

		String pSource = "Login";
		String pPage = "Registration";
		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String newEmail = createNewEmail();

		if (pUserType.equalsIgnoreCase("Guest")) {

			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			click(AMDHomePage.objMyProfileIcon, "Login/Registration");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
			type(AMDLoginScreen.objEmailIdField, newEmail, "Email ID field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(3000);
			fillRegisterForm();
			verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
			verifyElementPresentAndClick(AMDHomePage.objBottomUpcomingBtn, "Upcoming");

			// ### Fetching API Values of the usersettings
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("Element", "Registration");
			mixpanel.FEProp.setProperty("Method", "Email");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("User Type", "Free");

			// ### EVENT VALIDATION
			mixpanel.ValidateParameter("", "Registration Initiated");

			setFEPropertyNewUser();
			mixpanel.ValidateParameterInstantly("", "Registration First Name Entered");

			setFEPropertyNewUser();
			mixpanel.ValidateParameterInstantly("", "Registration Last Name Entered");

			setFEPropertyNewUser();
			mixpanel.ValidateParameterInstantly("", "Registration DoB Entered");

			setFEPropertyNewUser();
			mixpanel.ValidateParameterInstantly("", "Registration Gender Entered");

			setFEPropertyNewUser();
			mixpanel.ValidateParameterInstantly("", "Registration Password Entered");

			setFEPropertyNewUser();
			mixpanel.FEProp.setProperty("Page Name", "LoginRegister");
			mixpanel.ValidateParameterInstantly("", "Register Screen Display");

			setFEProperty(newEmail, "123456");
			ResponseInstance.getUserData(newEmail, "123456");
			ResponseInstance.getUserSettingsValues(newEmail, "123456");

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("User Type", "Registered");
			mixpanel.FEProp.setProperty("Element", "Registration");
			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Result");

			setFEProperty(newEmail, "123456");
			ResponseInstance.getUserData(newEmail, "123456");
			ResponseInstance.getUserSettingsValues(newEmail, "123456");

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("User Type", "Registered");
			mixpanel.FEProp.setProperty("Element", "Registration");
			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Success");

		} else {
			extent.extentLogger("NA", " Registration Initiated event is not applicable for " + pUserType);
		}
	}

	public void verifyLoginInitiatedEvent(String pUsertype, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register text");

			switch (loginMethod) {

			case "mobileNumberLogin":
				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, "7892215214", "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(pUsertype);
				setUserType_SubscriptionProperties(pUsertype);
				SetAppsflyerProperty();

				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "Mobile");

				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonsubscribedUserName");
				String Password = "abcdefg";

				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, Password, "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(pUsertype);
				setUserType_SubscriptionProperties(pUsertype);
				SetAppsflyerProperty();

				waitTime(3000);
				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "email");
				break;
			}

			// waitTime(100000);

			MixpanelAndroid.ValidateParameter("", "Login Initatied");
		}
	}

	public void verifyLoginResultEvent(String pUsertype, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register text");

			switch (loginMethod) {

			case "mobileNumberLogin":
				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, "7892215214", "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(pUsertype);
				setUserType_SubscriptionProperties(pUsertype);
				SetAppsflyerProperty();

				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "Mobile");

				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonsubscribedUserName");
				String Password = "abcdefg";

				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, Password, "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(pUsertype);
				setUserType_SubscriptionProperties(pUsertype);
				SetAppsflyerProperty();

				waitTime(3000);

				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "email");
				break;
			}
			// waitTime(100000);
			MixpanelAndroid.ValidateParameter("", "Login Result");
		}
	}

	public void verifyLoginScreenDisplayEvent(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register text");

			switch (loginMethod) {

			case "mobileNumberLogin":
				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, "7892215214", "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(userType);
				setUserType_SubscriptionProperties(userType);
				SetAppsflyerProperty();

				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "Mobile");

				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonsubscribedUserName");
				String Password = "abcdefg";

				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, Password, "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(userType);
				setUserType_SubscriptionProperties(userType);
				SetAppsflyerProperty();

				waitTime(3000);

				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "email");
				break;
			}
			waitTime(3000);
			MixpanelAndroid.ValidateParameter("", "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyRegisterScreenDisplayEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			HeaderChildNode(
					"Verify Register Screen Display event is triggered  when user navigates to Registration screen post entering un-registered Email ID");
			waitTime(5000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, "unregemailtovalidate@gmail.com", "Email ID field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(5000);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			waitTime(100000);
			MixpanelAndroid.FEProp.setProperty("Source", "Login");
			MixpanelAndroid.FEProp.setProperty("Page Name", "LoginRegister");
			MixpanelAndroid.ValidateParameter("", "Register Screen Display");
		}
	}

	public void verify_af_svod_first_episode_free_Event(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify af_svod_first_episode_free event");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword1 + "\n", "Search bar");
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitForAdToFinishInAmd();
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objRegisterPopUp, "Register popUp")) {
			verifyElementPresentAndClick(AMDPlayerScreen.objLoginButtonInRegisterPopUp, "Login link");
		}
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		MixpanelAndroid.FEProp.setProperty("Content ID", "0-1-247366");
		MixpanelAndroid.FEProp.setProperty("Content Name", "The Hunter's Scent");
		MixpanelAndroid.ValidateParameter("", "af_svod_first_episode_free");
	}

	public void verifyFirstLaunchEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			HeaderChildNode(
					"Verify First Launch event is triggered  when user navigates to Registration screen post entering un-registered Email ID");
			waitTime(5000);
			// verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			// verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText,
			// "Login/Register for best experience text");
			//
			// verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			// type(AMDLoginScreen.objEmailIdField, "unregemailtovalidate@gmail.com", "Email
			// ID field");
			// verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(5000);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			waitTime(100000);
			// MixpanelAndroid.FEProp.setProperty("Page Name", "LoginRegister");
			MixpanelAndroid.ValidateParameter("", "First Launch");
		}
	}

	public void verifyCarousalBannerClickEvent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Carousal Banner Click Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		String contentName = ResponseInstance.getCarouselContentFromAPI3(usertype, tabName);
		System.out.println(contentName);
		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");
		waitTime(15000);
		Back(1);

		String pManufacturer = DeviceDetails.OEM;

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		if (usertype.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		MixpanelAndroid.FEProp.setProperty("Page Name", "Homepage");

		if (tabName.equalsIgnoreCase("TV Shows") || tabName.equalsIgnoreCase("Web Series")) {
			mixpanel.FEProp.setProperty("Series", contentName);
		}
		mixpanel.ValidateParameter("", "Carousal Banner Click");
	}

	public void SubscriptionJourneyEventValidation(String userType) throws Exception {
		HeaderChildNode("Subscription Events Journey");
		System.out.println("\nSubscription Events Journey");

		String pSource = "Subscription";
		String pPage = "SubscriptionJourneyPage";
		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String nCC = "5318 3123 4521 9856";
		String expiryDate = "0325";
		String nCVV = "321";
		String getEmail = getParameterFromXML("NonsubscribedUserName");
		String getPwd = getParameterFromXML("NonsubscribedPassword");

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old App Language",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("display_language"));
		} else {
			mixpanel.FEProp.setProperty("Old App Language", "en");
		}

		waitTime(3000);
		switch (pUserType.toUpperCase()) {
		case "GUEST":

			click(AMDHomePage.objBuyPlanCTA, "Buy Plan CTA");
			click(AMDSubscibeScreen.objContinueBtn, "Select plan");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(5000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(6000);
			click(AMDGenericObjects.objText("Enter Card Number"), "CC/DC");
			type(AMDMySubscriptionPage.objEnterCCTxt, nCC, "CC number");
			type(AMDMySubscriptionPage.objExpiryCCTxt, expiryDate, "Expiry date");
			type(AMDMySubscriptionPage.objCVVTxt, nCVV, "CVV");
			hideKeyboard();
			click(AMDGenericObjects.objText("Pay Now"), "Pay Now CTA");

			mixpanel.FEProp.setProperty("Parent Control Setting", str);
			setFEProperty(getEmail, getPwd);
			ResponseInstance.getUserData(getEmail, getPwd);
//			ResponseInstance.getUserSettingsValues(getPwd, getPwd);

			break;

		case "NONSUBSCRIBEDUSER":

			click(AMDHomePage.objBuyPlanCTA, "Buy Plan CTA");
			click(AMDSubscibeScreen.objContinueBtn, "Select plan");
			waitTime(2000);

			waitTime(3000);
			click(AMDGenericObjects.objText("Enter Card Number"), "CC/DC");
			type(AMDMySubscriptionPage.objEnterCCTxt, nCC, "CC number");
			type(AMDMySubscriptionPage.objExpiryCCTxt, expiryDate, "Expiry date");
			type(AMDMySubscriptionPage.objCVVTxt, nCVV, "CVV");
			hideKeyboard();
			click(AMDGenericObjects.objText("Pay Now"), "Pay Now CTA");

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			break;

		case "SUBSCRIBEDUSER":

			extent.extentLogger("NA", " Subscription event is not applicable for Subscribed User");
			break;
		}

		if (!pUserType.equalsIgnoreCase("SubscribedUser")) {

			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Current Subscription", "false");
			mixpanel.FEProp.setProperty("Billing Country", "INDIA");
			mixpanel.FEProp.setProperty("Billing State", "KARNATAKA");

			SetAppsflyerProperty();
			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", str);
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.ValidateParameter("", "Subscription Call Initiated");
		}

	}

	@SuppressWarnings("static-access")
	public void verifySearchCancelledEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Cancelled Event");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		String searchtxt = "Kam";
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchtxt + "\n", "Search bar");
		hideKeyboard();
		waitTime(2000);
		click(AMDSearchScreen.objClearSearch, "Cancel Search Icon");
		waitTime(8000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		waitTime(3000);
		MixpanelAndroid.FEProp.setProperty("page_name", "Search");
		MixpanelAndroid.FEProp.setProperty("tab_name", "All");
		MixpanelAndroid.ValidateParameter("", "Search Cancelled");
	}

	@SuppressWarnings("static-access")
	public void verifySearchClearedEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Cleared Event");
		waitTime(5000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		String searchtxt = "Kam";
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchtxt + "\n", "Search bar");
		hideKeyboard();
		waitTime(2000);
		click(AMDSearchScreen.objClearSearch, "Cancel Search Icon");
		waitTime(8000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		waitTime(3000);
		MixpanelAndroid.FEProp.setProperty("page_name", "Search");
		MixpanelAndroid.FEProp.setProperty("tab_name", "All");
		MixpanelAndroid.ValidateParameter("", "Search Cleared");
	}

	public void verifyLoginRegistrationScreenDisplayEvent(String pUsertype, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Registration Screen Display Event");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Login/Register text");

			switch (loginMethod) {

			case "mobileNumberLogin":
				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, "7892215214", "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(pUsertype);
				setUserType_SubscriptionProperties(pUsertype);
				SetAppsflyerProperty();

				MixpanelAndroid.FEProp.setProperty("Page Name", "LoginRegister");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "Mobile");

				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonsubscribedUserName");
				String Password = "abcdefg";

				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, Password, "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(pUsertype);
				setUserType_SubscriptionProperties(pUsertype);
				SetAppsflyerProperty();

				waitTime(3000);
				MixpanelAndroid.FEProp.setProperty("Page Name", "LoginRegister");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "email");
				break;
			}
			// waitTime(100000);
			MixpanelAndroid.ValidateParameter("", "Login Registration Screen Display");
		}
	}

	public void Downloadsection_visited(String usertype) throws Exception {

		extent.HeaderChildNode("Download section visited event");
		verifyElementPresentAndClick(AMDHomePage.objDownloadBtn, "Download button");
		boolean value = findElement(AMDHomePage.objDownloadBtn).isSelected();
		if (value == true) {
			logger.info("User navigated to Downloads Screen");
			extentLogger("Downloads", "User navigated to Downloads Screen");
			waitTime(20000);

			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

//			mixpanel.FEProp.setProperty("Source", "SearchPage");
//			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Downloadsection_visited");
		} else {
			logger.info("User is NOT navigated to Downloads Screen");
			extentLogger("Downloads", "User is NOT navigated to Downloads Screen");
		}
	}

	public void Homepage_visited(String usertype) throws Exception {

		extent.HeaderChildNode("Home page section visited event");
		verifyElementPresentAndClick(AMDHomePage.objHomeTab, "Home tab");
		boolean value = findElement(AMDHomePage.objHomeTab).isSelected();
		if (value == true) {
			logger.info("User navigated to Home page");
			extentLogger("HomePage", "User navigated to Home page");
			waitTime(35000);

			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

//			mixpanel.FEProp.setProperty("Source", "SearchPage");
//			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Homepage_visited");
		} else {
			logger.info("User is NOT navigated to Home page");
			extentLogger("Downloads", "User is NOT navigated to Home page");
		}
	}

	public void LIVETVsection_visited(String usertype) throws Exception {

		extent.HeaderChildNode("Live Tv page section visited event");
		SelectTopNavigationTab("Live TV");
		boolean value = findElement(AMDHomePage.objLiveTvTab).isSelected();
		if (value == true) {
			logger.info("User navigated to Live TV page");
			extentLogger("LiveTV", "User navigated to Live TV page");
			waitTime(20000);

			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

//		mixpanel.FEProp.setProperty("Source", "SearchPage");
//		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "LIVETVsection_visited");
		} else {
			logger.info("User is NOT navigated to Live TV page");
			extentLogger("Downloads", "User is NOT navigated to Live TV page");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyVideoStreamOverWifiChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify video wifi change Event for Enable");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");

		waitTime(3000);
		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old Stream Over Wifi Setting",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("stream_over_wifi"));
		} else {
			mixpanel.FEProp.setProperty("Old Stream Over Wifi Setting", "false");
		}

		boolean var = verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");
		waitTime(3000);
		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
			mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", "true");
		}

		MixpanelAndroid.ValidateParameter("", "Video Stream Over Wifi Changed");
		verifyElementPresentAndClick(AMDMoreMenu.objVideo_WifiOnly, "Wifi only Switch");

	}

	@SuppressWarnings("static-access")
	public void verifyVideoStreamingAutoPlayChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify Video Streaming AutoPlay change Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		waitTime(5000);

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("SubscribedUserName");
				Password = getParameterFromXML("SubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("NonsubscribedUserName");
				Password = getParameterFromXML("NonsubscribedPassword");
			}
			mixpanel.FEProp.setProperty("Old Autoplay Setting",
					ResponseInstance.getUserSettingsDetails(Username, Password).getProperty("auto_play"));
		} else {
			mixpanel.FEProp.setProperty("Old Autoplay Setting", "true");
		}

		click(AMDMoreMenu.objVideo_Autoply, "Video Auto play toggle");
		waitTime(4000);

		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		MixpanelAndroid.FEProp.setProperty("Source", "More");
		MixpanelAndroid.FEProp.setProperty("Page Name", "user_setting");

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
			mixpanel.FEProp.setProperty("New Autoplay Setting", "false");
		}

		MixpanelAndroid.ValidateParameter("", "Video Streaming Autoplay Changed");
		click(AMDMoreMenu.objVideo_Autoply, "Video Auto play toggle");

	}

	public void DownloadResultEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Download Result Event of content from search page");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
			waitTime(3000);
			DownloadVideoQualityPopUp("Good", true);
			waitTime(3000);
			Back(1);
			click(AMDHomePage.objDownloadBtn, "Downloads tab");
			click(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
			waitForElementAndClickIfPresent(AMDDownloadPage.objDownloadCompleteIcon, 1000, "downloadCompleteIcon");

			String pManufacturer = DeviceDetails.OEM;

			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Download Result");

		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void DownloadClickEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Download Click Event of content from search page");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
			waitTime(3000);
			DownloadVideoQualityPopUp("Good", true);
			waitTime(3000);
			Back(1);
			click(AMDHomePage.objDownloadBtn, "Downloads tab");
			click(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
			waitForElementAndClickIfPresent(AMDDownloadPage.objDownloadCompleteIcon, 1000, "downloadCompleteIcon");
			click(AMDDownloadPage.objPlayDownloadedContent, "Play option");

			String pManufacturer = DeviceDetails.OEM;

			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "Download");
			mixpanel.FEProp.setProperty("Element", "Download");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Download Click");

		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void DownloadDeleteEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Download Delete Event of content from search page");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
			waitTime(3000);
			DownloadVideoQualityPopUp("Good", true);
			waitTime(3000);
			Back(1);
			click(AMDHomePage.objDownloadBtn, "Downloads tab");
			click(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
			waitForElementAndClickIfPresent(AMDDownloadPage.objDownloadCompleteIcon, 1000, "downloadCompleteIcon");
			click(AMDDownloadPage.objDeleteDownloadedContent, "Delete option");

			String pManufacturer = DeviceDetails.OEM;

			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "Download");
			mixpanel.FEProp.setProperty("Element", "delete");

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Download Delete");

		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void MoreSection_visited(String usertype) throws Exception {

		extent.HeaderChildNode("More section visited event");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Moresection_visitied");
	}

	public void UpcomingSection_visitedEvent(String usertype) throws Exception {

		extent.HeaderChildNode("Upcoming section visited event");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objBottomUpcomingBtn, "Upcoming");
		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Upcomingsection_visited");
	}

	public void TVshowssection_visitedEvent(String usertype) throws Exception {

		extent.HeaderChildNode("TV Shows section visited event");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Home tab");
		boolean value = findElement(AMDHomePage.objShowsTab).isSelected();
		if (value == true) {
			logger.info("User navigated to TV Shows page");
			extentLogger("HomePage", "User navigated to TV Shows page");
			waitTime(35000);

			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "TVshowssection_visited");
		} else {
			logger.info("User is NOT navigated to TV Shows page");
			extentLogger("Downloads", "User is NOT navigated to TV Shows page");
		}
	}

	public void DownloadVideoQualityPopUp(String Quality, boolean checkAlwaysAsk) throws Exception {

		verifyElementExist(AMDDownloadPage.objDownloadVideoQualityPopup, "Download Video Quality PopUp screen");
		click(AMDDownloadPage.objDownloadQualityOptions(Quality), Quality);

		String getValue = getAttributValue("checked", AMDDownloadPage.objAlwaysAskCheckBox);
		if (checkAlwaysAsk) {
			if (!getValue.contains("true")) {
				click(AMDDownloadPage.objAlwaysAskCheckBox, "Always ask quality for every download");
			}
		} else {
			if (getValue.contains("true")) {
				click(AMDDownloadPage.objAlwaysAskCheckBox, "Always ask quality for every download");
			}
		}
		waitTime(3000);
		SwipeUntilFindElement(AMDDownloadPage.objStartDownloadCTA, "UP");
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
	}

	public void JourneyToThumbnailClickEvent(String pUsertype, String pTabName) throws Exception {
		extent.HeaderChildNode("Select first content card from " + pTabName);
		System.out.println("\nSelect the first content card from " + pTabName);

		waitForElementDisplayed(AMDHomePage.objTitle, 20);
		SelectTopNavigationTab(pTabName);
		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(pUsertype);
		System.out.println(contentLang);

		String trayName = ResponseInstance.getRailNameFromPage(pTabName, pUsertype);

		if (pTabName.equalsIgnoreCase("Live TV") || pTabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}

		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		PartialSwipe("UP", 1);
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");
		if (pUsertype.equalsIgnoreCase("Guest") || pUsertype.equalsIgnoreCase("NonSubscribedUser")) {
			registerPopUpClose();
			waitForAdToFinishInAmd();
			waitTime(2000);
			registerPopUpClose();
			Back(1);
		} else {
			waitTime(3000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player Screen");
			verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		}
	}

	public void EventValidation(String pUsertype, String pEventName, String pPageName, String pSource)
			throws Exception {
		System.out.println("\n" + pEventName + " event Validation");

//		####### Set All Parameters values #######
		String pManufacturer = DeviceDetails.OEM;

		setFEProperty(pUsertype);
		setUserType_SubscriptionProperties(pUsertype);
		SetAppsflyerProperty();

		if (pPageName.equalsIgnoreCase("TV Shows")) {
			mixpanel.FEProp.setProperty("Page Name", "tvshows");
		} else if (pPageName.equalsIgnoreCase("Web Series")) {
			mixpanel.FEProp.setProperty("Page Name", "originals");
		} else if (pPageName.equalsIgnoreCase("Eduauraa")) {
			mixpanel.FEProp.setProperty("Page Name", "kids");
		} else {
			mixpanel.FEProp.setProperty("Page Name", pPageName);
		}

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", pEventName);
	}

	public void JourneyForVideoWatchDurationEvent(String pUsertype, String pTabName) throws Exception {
		extent.HeaderChildNode("Select first content card from " + pTabName);
		System.out.println("\nSelect first content card from " + pTabName);

		waitForElementDisplayed(AMDHomePage.objTitle, 30);
		SelectTopNavigationTab(pTabName);
		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(pUsertype);
		System.out.println(contentLang);

		String trayName = ResponseInstance.getRailNameFromPage(pTabName, pUsertype);

		if (pTabName.equalsIgnoreCase("Live TV") || pTabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}

		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		PartialSwipe("UP", 1);
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");
		if (pUsertype.equalsIgnoreCase("Guest") || pUsertype.equalsIgnoreCase("NonSubscribedUser")) {
			registerPopUpClose();
			waitForAdToFinishInAmd();
			waitTime(2000);
			registerPopUpClose();
			Back(1);
		} else {
			waitTime(2000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player Screen");
			waitTime(4000);
			click(AMDPlayerScreen.objPlayerScreen, "Player Screen");
			scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
			waitTime(4000);
			Back(1);
		}
	}

	public void ScreenVisitedEventValidation(String userType) throws Exception {
		extent.HeaderChildNode("Screen visited Events validation");
		System.out.println("\nScreen visited Events validation");

		String NA = "N/A";

		verifyElementDisplayed(AMDHomePage.objZee5Logo);
		SelectTopNavigationTab("Movies");
		// verifyElementPresent(AMDHomePage.objFirstRailDisplay, "Movies Screen");
		Swipe("UP", 1);

		verifyElementPresentAndClick(AMDHomePage.HomeIcon, "Home screen");
		// verifyElementPresent(AMDHomePage.objFirstRailDisplay, "Home Screen");
		Swipe("UP", 1);

		SelectTopNavigationTab("Web Series");
		verifyElementPresent(AMDHomePage.objFirstRailDisplay, "WebSeries Screen");
		Swipe("UP", 1);

		SelectTopNavigationTab("News");
		verifyElementPresent(AMDHomePage.objFirstRailDisplay, "News Screen");
		Swipe("UP", 1);

		SelectTopNavigationTab("Premium");
		verifyElementPresent(AMDHomePage.objFirstRailDisplay, "Premium Screen");
		Swipe("UP", 1);

		SelectTopNavigationTab("Music");
		verifyElementPresent(AMDHomePage.objFirstRailDisplay, "Music Screen");
		Swipe("UP", 1);

		SelectTopNavigationTab("TV Shows");
		// verifyElementPresent(AMDHomePage.objFirstRailDisplay, "TV Shows Screen");
		Swipe("UP", 1);

		SelectTopNavigationTab("Live TV");
		verifyElementPresent(AMDHomePage.objFirstRailDisplay, "Live TV Screen");
		Swipe("UP", 1);

		SelectTopNavigationTab("Eduauraa");
		verifyElementPresent(AMDHomePage.objFirstRailDisplay, "Eduauraa Screen");
		Swipe("UP", 1);

		waitTime(120000);

		EventValidationAfterOneMinute(userType, "Moviesection_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "Homepage_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "Originalsection_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "Newssection_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "Premiumsection_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "Musicsection_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "TVshowssection_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "LIVETVsection_visited", NA, NA);
		EventValidationAfterOneMinute(userType, "Eduauraasection_visited", NA, NA);

	}

	public void EventValidationAfterOneMinute(String pUsertype, String pEventName, String pPageName, String pSource)
			throws Exception {
		System.out.println("\n" + pEventName + " event Validation");

//		####### Set All Parameters values #######
		String pManufacturer = DeviceDetails.OEM;

		setFEProperty(pUsertype);
		setUserType_SubscriptionProperties(pUsertype);
		SetAppsflyerProperty();

		mixpanel.FEProp.setProperty("Source", pSource);
		mixpanel.FEProp.setProperty("Page Name", pPageName);
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameterAfterOneMinute("", pEventName);
	}

	public void SkipLoginEvent(String usertype) throws Exception {
		extent.HeaderChildNode("Skip Login event validation");
		System.out.println("Skip Login event validation");

		String eventName = "Skip Login";

		verifyElementDisplayed(AMDHomePage.objHomeBtn);
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		click(AMDMoreMenu.objLoginRegisterText, "Login/Register");
		click(AMDLoginScreen.objSkipButton, "Skip link");

		EventValidation(usertype, eventName, "LoginRegister", "N/A");
	}

	public void addToWatchlistEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Add to watchlist Event of content from search page");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");

			String pManufacturer = DeviceDetails.OEM;
			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Add to Watchlist");

		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void addToWatchlistSuccessfullEventOfcontentFromSearchPage(String usertype, String keyword4)
			throws Exception {
		extent.HeaderChildNode("Add to watchlist Successfull Event of content from search page");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");

			String pManufacturer = DeviceDetails.OEM;
			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Add to Watchlist Successful");

		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void removeFromWatchlistFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Remove from Watchlist Event of content from search page");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist icon");

			String pManufacturer = DeviceDetails.OEM;
			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Remove from Watchlist");

		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void Tab_ViewEventValidation(String userType, String tabName) throws Exception {
		System.out.println("\nTab_View Event Validation");

		String pManufacturer = DeviceDetails.OEM;

		SelectTopNavigationTab(tabName);

		waitTime(10000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);

		MixpanelAndroid.FEProp.setProperty("Tab Name", tabName);
		MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
		MixpanelAndroid.FEProp.setProperty("manufacturer", pManufacturer);
		MixpanelAndroid.FEProp.setProperty("brand", pManufacturer);
		MixpanelAndroid.ValidateParameter("", "Tab_View");
	}

	public void verifyCarousalBannerCTAsEvent(String usertype, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Carousal Banner CTAs event");
			waitTime(5000);
			SelectTopNavigationTab(tabName);
			String contentName = ResponseInstance.getCarouselContentFromAPI3(usertype, tabName);
			System.out.println(contentName);
			boolean flag = waitForElementAndClickIfPresent(AMDHomePage.objBuyNowCTAForContentOnCarousal(contentName), 7,
					"Buy Plan CTA on carousel");
			if (flag == true) {
				String pManufacturer = DeviceDetails.OEM;
				setFEProperty(usertype);
				mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);
				mixpanel.FEProp.setProperty("Element", "Get Premium");
				mixpanel.FEProp.setProperty("Button Type", "Banner");
				mixpanel.FEProp.setProperty("Content Duration", "N/A");

				mixpanel.ValidateParameter("", "Carousal Banner CTAs");
			} else {
				logger.info("Buy Plan CTA is not displayed on Carousal");
				extentLogger("", "Buy Plan CTA is not displayed on Carousal");
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void verifySettingChangedEvent(String userType, String restriction) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event");
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			if (restriction.equalsIgnoreCase("Age13+")) {
				click(AMDMoreMenu.objRestrict13Above, "Restrict 13+ Content option");
				click(AMDMoreMenu.objContinueBtn, "Continue Button");
				waitTime(2000);

				verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
				type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(4000);
				click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
				waitTime(2000);
				click(AMDMoreMenu.objParentalLockDone, "Done Button");
			} else if (restriction.equalsIgnoreCase("Restrict All")) {
				click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
				click(AMDMoreMenu.objContinueBtn, "Continue Button");
				waitTime(2000);
				verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
				type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(4000);
				click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
				waitTime(2000);
				click(AMDMoreMenu.objParentalLockDone, "Done Button");
			}
			waitTime(3000);
			String pManufacturer = DeviceDetails.OEM;
			setParentalFEProperty(userType);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.parentalSettingsValidateParameter("", "Setting Changed");
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void watchTrailerClickedEventOfContentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Watch Trailer Clicked Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + " Kannada" + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(15000);
		verifyElementPresentAndClick(AMDConsumptionScreen.objWatchTrialer, "Watch Trailer CTA");

		String pManufacturer = DeviceDetails.OEM;
		String contentID = null;
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			contentID = getParameterFromXML("premiumContentID3");
		} else {
			contentID = getParameterFromXML("premiumContentID2");
		}

		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("Element", "Watch Trailer");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Watch Trailer Clicked");
	}

	public void shareEventOfContentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Share Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(10000);
		verifyElementPresentAndClick(AMDConsumptionScreen.objShareBtn, "Share CTA");
		String pManufacturer = DeviceDetails.OEM;
		String contentID = getParameterFromXML("premiumContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();
		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Share");
	}

	public void PlayerControlPlayPauseEvent(String pUsertype, String pTabName) throws Exception {
		extent.HeaderChildNode("Select first content card from " + pTabName);
		System.out.println("\nSelect first content card from " + pTabName);

		waitForElementDisplayed(AMDHomePage.objTitle, 30);
		SelectTopNavigationTab(pTabName);
		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(pUsertype);
		System.out.println(contentLang);

		String trayName = ResponseInstance.getRailNameFromPage(pTabName, pUsertype);

		if (pTabName.equalsIgnoreCase("Live TV") || pTabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}

		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		PartialSwipe("UP", 1);
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");
		if (pUsertype.equalsIgnoreCase("Guest") || pUsertype.equalsIgnoreCase("NonSubscribedUser")) {
			registerPopUpClose();
			waitForAdToFinishInAmd();
			waitTime(2000);
			registerPopUpClose();
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			click(AMDPlayerScreen.objPauseIcon, "Pause");
			waitTime(2000);
		} else {
			waitTime(2000);
			verifyElementPresentAndClick(AMDPlayerScreen.objPlayerScreen, "Player Screen");
			waitTime(4000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			click(AMDPlayerScreen.objPauseIcon, "Pause");
			waitTime(2000);
		}

		mixpanel.FEProp.setProperty("Element", "Play/Pause");
		EventValidation(pUserType, "CTAs", "ConsumptionPage", "Homepage");
	}

	public void videoclick_1EventOfContentFromSearchPage(String usertype) throws Exception {
		extent.HeaderChildNode("videoclick_1 Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, getParameterFromXML("content1") + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLogger("Player screen", "error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon);

			if (eventFlag) {
				String pManufacturer = DeviceDetails.OEM;

				String contentID = getParameterFromXML("contentID1");
				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Guest");
				}
				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "videoclick_1");
			} else {
				logger.info("Failed to play the video");
				extentLoggerWarning("Event", "Failed to play the video");
			}
		}

	}

	public void videoclick_3EventOfContentFromSearchPage(String usertype) throws Exception {
		extent.HeaderChildNode("videoclick_3 Event of content from search page");
		boolean eventFlag = false;

		for (int i = 1; i <= 3; i++) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, getParameterFromXML("content" + i) + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");

			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
			}
			if (usertype.equalsIgnoreCase("Guest")) {
				registerPopUpClose();
			}
			completeProfilePopUpClose(usertype);

			boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
			boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
			if (inlineLink == true) {
				logger.info("Player inline subscription link is displayed");
				extentLogger("Player screen", "Player inline subscription link is displayed");
			} else if (adulterrormsg == true) {
				logger.info("error message saying 'This content is for Adult view only' is displayed");
				extentLogger("Player screen",
						"error message saying 'This content is for Adult view only' is displayed");
			} else {
				waitTime(5000);
				if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
					click(AMDPlayerScreen.objPlayerScreen, "Player screen");
				}
				eventFlag = verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon);

			}
			Back(2);
		}
		if (eventFlag) {
			String pManufacturer = DeviceDetails.OEM;

			String contentID = getParameterFromXML("contentID3");

			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "videoclick_3");
		} else {
			logger.info("Failed to play the video");
			extentLoggerWarning("Event", "Failed to play the video");
		}

	}

	public void videoclick_5EventOfContentFromSearchPage(String usertype) throws Exception {
		extent.HeaderChildNode("videoclick_5 Event of content from search page");
		boolean eventFlag = false;

		for (int i = 1; i <= 5; i++) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, getParameterFromXML("content" + i) + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");

			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
			}
			if (usertype.equalsIgnoreCase("Guest")) {
				registerPopUpClose();
			}
			completeProfilePopUpClose(usertype);

			boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
			boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
			if (inlineLink == true) {
				logger.info("Player inline subscription link is displayed");
				extentLogger("Player screen", "Player inline subscription link is displayed");
			} else if (adulterrormsg == true) {
				logger.info("error message saying 'This content is for Adult view only' is displayed");
				extentLogger("Player screen",
						"error message saying 'This content is for Adult view only' is displayed");
			} else {
				waitTime(5000);
				if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
					click(AMDPlayerScreen.objPlayerScreen, "Player screen");
				}
				eventFlag = verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon);

			}
			Back(2);
		}
		if (eventFlag) {
			String pManufacturer = DeviceDetails.OEM;

			String contentID = getParameterFromXML("contentID5");

			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "videoclick_5");
		} else {
			logger.info("Failed to play the video");
			extentLoggerWarning("Event", "Failed to play the video");
		}

	}

	public void af_preroll_adview(String usertype, String keyword) throws Exception {
		extent.HeaderChildNode("af_preroll_adview Event of content from search page");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(20000);

			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				String pManufacturer = DeviceDetails.OEM;

				String contentID = getParameterFromXML("freeContentID");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Guest");
				}

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "af_preroll_adview");
			} else {
				logger.info("Failed to play the Ad");
				extentLoggerWarning("Event", "Failed to play the Ad");
			}
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void videos_content_PlayEventOfContentFromSearchPage(String usertype, String keyword) throws Exception {
		extent.HeaderChildNode("Videos_content_Play Event of content from search page");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		waitTime(5000);
		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		boolean eventFlag = verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon);
		Back(1);
		if (eventFlag) {
			String pManufacturer = DeviceDetails.OEM;

			String contentID = getParameterFromXML("contentID1");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Videos_Content_Play");
		} else {
			logger.info("Failed to play the video");
			extentLoggerWarning("Event", "Failed to play the video");
		}
	}

	public void Svod_content_viewEventOfContentFromSearchPage(String usertype, String keyword) throws Exception {
		extent.HeaderChildNode("Svod_content_view Event of content from search page");
		if (usertype.equalsIgnoreCase("SubscribedUser")) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(10000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon);

			if (eventFlag) {
				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Svod_content_view");
			} else {
				logger.info("Failed to play the video");
				extentLoggerWarning("Event", "Failed to play the video");
			}
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}
	}

	public void ContentBucketSwipeEventValidation(String pUsertype, String pTabName) throws Exception {
		extent.HeaderChildNode("Content Bucket Swipe Validation");
		System.out.println("\nContent Bucket Swipe Validation");

		SelectTopNavigationTab(pTabName);
		String trayName = ResponseInstance.getRailNameFromPage(pTabName, pUsertype);
		waitForElementDisplayed(AMDHomePage.objCarouselDots, 10);

		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");

		// ### Swiping the content card from rails
		PartialSwipe("UP", 1);
		SwipeRailContentCards(AMDHomePage.objRailName(trayName));
		SwipeRailContentCards(AMDHomePage.objRailName(trayName));

		MixpanelAndroid.FEProp.setProperty("Direction", "RIGHT");
		MixpanelAndroid.FEProp.setProperty("Carousal Name", trayName);
	}

	public void JourneyToCaptureRibbonCTAEvent(String pUsertype, String pContentName) throws Exception {
		extent.HeaderChildNode("Journey to capture Ribbon CTA event");
		System.out.println("\nJourney to capture Ribbon CTA event");

		String pElement = "Buy Plan";
		String pManufacturer = DeviceDetails.OEM;
		if (!pUsertype.equalsIgnoreCase("SubscribedUser")) {

			verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search edit box");
			type(AMDSearchScreen.objSearchEditBox, pContentName, "Search edit box");
			click(AMDSearchScreen.objFirstSearchResult(pContentName), "Searched content");
			waitTime(3000);

			List<WebElement> objBuyPlan = findElements(AMDConsumptionScreen.objBuyPlanRibbonCTA);
			if (objBuyPlan.size() > 1) {
				objBuyPlan.get(1).click();
			} else {
				objBuyPlan.get(0).click();
			}

			MixpanelAndroid.FEProp.setProperty("Element", pElement);
			MixpanelAndroid.FEProp.setProperty("Content Name", pContentName);
			EventValidation(userType, "Ribbon CTAs", "ConsumptionPage", "N/A");
		} else {
			logger.info("Ribbon CTA event is not applicable for Subscribed user");
			extent.extentLogger("Ribbon CTA", "Ribbon CTA event is not applicable for Subscribed user");
		}
	}

	public void setParentalFEProperty(String pUserType) {
		if (!(pUserType.equalsIgnoreCase("Guest"))) {

			if (pUserType.equalsIgnoreCase("SubscribedUser")) {
				Username = getParameterFromXML("ParentalSubscribedUserName");
				Password = getParameterFromXML("ParentalSubscribedPassword");
			} else if (pUserType.equalsIgnoreCase("NonSubscribedUser")) {
				Username = getParameterFromXML("ParentalNonsubscribedUserName");
				Password = getParameterFromXML("ParentalNonsubscribedPassword");
			}

			Properties settingsProp = new Properties();
			settingsProp = ResponseInstance.getUserSettingsDetails(Username, Password);

			mixpanel.FEProp.setProperty("New Video Streaming Quality Setting",
					settingsProp.getProperty("streaming_quality"));
			mixpanel.FEProp.setProperty("New Autoplay Setting", settingsProp.getProperty("auto_play"));
			mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", settingsProp.getProperty("stream_over_wifi"));
			mixpanel.FEProp.setProperty("New Download Quality Setting", settingsProp.getProperty("download_quality"));
			mixpanel.FEProp.setProperty("New Download Over Wifi Setting",
					settingsProp.getProperty("download_over_wifi"));
			mixpanel.FEProp.setProperty("New App Language", settingsProp.getProperty("display_language"));
			mixpanel.FEProp.setProperty("New Content Language", settingsProp.getProperty("content_language"));

			mixpanel.FEProp.setProperty("Partner Name", "Zee5");
			mixpanel.FEProp.setProperty("Gender",
					ResponseInstance.getUserData_NativeAndroid(Username, Password).getProperty("gender"));
		} else {
			mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", "Auto");
			mixpanel.FEProp.setProperty("New Autoplay Setting", "true");
			mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", "false");
			mixpanel.FEProp.setProperty("New Download Quality Setting", "Ask Each Time");
			mixpanel.FEProp.setProperty("New Download Over Wifi Setting", "false");
			mixpanel.FEProp.setProperty("New App Language", "en");
			mixpanel.FEProp.setProperty("New Content Language", "en,kn");
		}
	}

	public void setParentalUserType_SubscriptionProperties(String pUsertype) {
		String pUsername, pPassword;
		if (!pUsertype.equalsIgnoreCase("Guest")) {
			if (pUsertype.equalsIgnoreCase("SubscribedUser")) {
				pUsername = getParameterFromXML("ParentalSubscribedUserName");
				pPassword = getParameterFromXML("ParentalSubscribedPassword");

				ResponseInstance.getRegionDetails();
				ResponseInstance.getUniqueAndUserID(pUsername, pPassword);
				ResponseInstance.setSubscriptionDetails_NativeAndroid();
				mixpanel.FEProp.setProperty("User Type", "Premium");
			} else {
				pUsername = getParameterFromXML("ParentalNonsubscribedUserName");
				pPassword = getParameterFromXML("ParentalNonsubscribedPassword");
				mixpanel.FEProp.setProperty("User Type", "registered");
				MixpanelAndroid.FEProp.setProperty("Pack Duration", "N/A");
				ResponseInstance.getRegionDetails();
			}
			mixpanel.FEProp.setProperty("Email", pUsername);

		} else {
			mixpanel.FEProp.setProperty("User Type", "Free");
			MixpanelAndroid.FEProp.setProperty("Pack Duration", "N/A");
			ResponseInstance.getRegionDetails();
		}
	}

	public void consumption_subscribe_cta_click_Event(String usertype, String keyword3) throws Exception {
		extent.HeaderChildNode("consumption_subscribe_cta_click_Event");
		waitTime(4000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(4000);
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		// if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
		// waitForAdToFinishInAmd();
		// waitTime(5000);
		// }
		// forwardAutoSeek(1);

		click(AMDHomePage.objGetPremiumCTAOnCarousel, "Subscribe CTA");
		waitTime(3000);

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Source", "SearchPage");
		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");

		MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

		MixpanelAndroid.ValidateParameter("", "consumption_subscribe_cta_click");
	}

	public void RibbonCTAs_Event(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Ribbon CTAs Event ");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			click(AMDHomePage.objBuyPlanCTABelowPlayer, "Buy Plan CTA below player");
			waitTime(10000);

			String contentID = getParameterFromXML("premiumContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Source", "SearchPage");
			MixpanelAndroid.FEProp.setProperty("Button Type", "Banner");
			MixpanelAndroid.FEProp.setProperty("Element", "Buy Plan");
			MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");

			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

			MixpanelAndroid.ValidateParameter("", "Ribbon CTAs");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPackToggleEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Pack Toggle Event - Buy Plan Header");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.obj299PlanPrice, "229 Pack Toggle");
			waitTime(10000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Source", "Homepage");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Pack Toggle");
		}
	}

	public void WidgetCTAs_Event(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Widget CTAs Event ");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			PartialSwipeInConsumptionScreen("UP", 1);
			click(AMDTVODComboOffer.objHowItWorksCTA, "Widget CTA");
			waitTime(3000);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");
			MixpanelAndroid.FEProp.setProperty("Button Type", "Banner");
			MixpanelAndroid.FEProp.setProperty("Element", "How it works");
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

			MixpanelAndroid.ValidateParameter("", "Widget CTAs");
		}
	}

	@SuppressWarnings("deprecation")
	public void verifySettingChangedEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event");
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			String oldSetting = getText(AMDMoreMenu.objSeletcedParentalControlOption);
			System.out.println("Old Setting : " + oldSetting);

			if (oldSetting.contains("13")) {
				click(AMDMoreMenu.objRestrict18Plus, "Restrict 18+ content");
			} else {
				click(AMDMoreMenu.objRestrict13Plus, "Restrict 13+ content");
			}

			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);

			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");

			waitTime(10000);
			String pManufacturer = DeviceDetails.OEM;
			setParentalFEProperty(userType);
			setParentalUserType_SubscriptionProperties(userType);

			mixpanel.FEProp.setProperty("Source", "ParentalControl");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("Page Name", "ParentalControl Pin");
			// mixpanel.FEProp.setProperty("Parent Control Setting", "A");
			mixpanel.FEProp.setProperty("Element", "Set Pin");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control PIN Set");

			mixpanel.parentalSettingsValidateParameter("", "Setting Changed");

			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void JourneyToCaptureSubscribeCTAEvent(String pUsertype, String pContentName) throws Exception {
		extent.HeaderChildNode("Journey to capture Consumption screen Subscribe CTA click event");
		System.out.println("\nJourney to capture Consumption screen Subscribe CTA click event");

		String setEventName = "consumption_subscribe_cta_click";
		String pManufacturer = DeviceDetails.OEM;
		if (!pUsertype.equalsIgnoreCase("SubscribedUser")) {

			verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search edit box");
			type(AMDSearchScreen.objSearchEditBox, pContentName, "Search edit box");
			click(AMDSearchScreen.objFirstSearchResult(pContentName), "Searched content");
			waitTime(3000);

			List<WebElement> objBuyPlan = findElements(AMDConsumptionScreen.objBuyPlanRibbonCTA);
			if (objBuyPlan.size() > 0) {
				objBuyPlan.get(0).click();
			}

			setFEProperty(pUsertype);
			setUserType_SubscriptionProperties(pUsertype);
			SetAppsflyerProperty();
			MixpanelAndroid.ValidateParameter("", setEventName);
		} else {
			logger.info("Consumption Subscribe CTA Click event is not applicable for Subscribed user");
			extent.extentLogger("Consumption Subscribe CTA Click",
					"Consumption Subscribe CTA Click event is not applicable for Subscribed user");
		}
	}

	public void JourneyToCaptureManualRefreshEvent(String pUsertype, String pTabname) throws Exception {
		extent.HeaderChildNode("Pull to Refresh " + pTabname + " screen functionality");
		System.out.println("\nPull to Refresh " + pTabname + " screen functionality");

		verifyElementPresent(AMDHomePage.HomeIcon, "Home Screen");
		SelectTopNavigationTab(pTabname);
		waitTime(5000);
		Swipe("DOWN", 1);
		waitTime(5000);
		click(AMDHomePage.objBottomUpcomingBtn, "Upcoming");

		logger.info("Pull to refresh the page is performed in " + pTabname);
		extent.extentLogger("Pull to Refresh", "Pull to refresh the page is performed in " + pTabname);
	}

	public void TVShows_Content_PlayEventOfContentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("TVShows_Content_Play Event of content from search page");

		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);
		waitTime(5000);
		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		String contentID = getParameterFromXML("keywordID4");

		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		if (usertype.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		String pManufacturer = DeviceDetails.OEM;

		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "TVShows_Content_Play");

	}

	public void rentalPurchaseCallReturnedEventOfContentFromSearchPage(String usertype, String keyword)
			throws Exception {
		HeaderChildNode("Rental purchase call returned event of a content through searchpage");
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			String nCC = "5318 3123 4521 9856";
			String expiryDate = "0325";
			String nCVV = "321";

			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			click(AMDTVODComboOffer.objRentNowCTABelowPlayer, "Rent Now CTA");
			waitTime(2000);
			click(AMDSubscibeScreen.objContinueBtn, "Continue button on Subscription page");
			waitTime(20000);
			click(AMDGenericObjects.objText("Enter Card Number"), "CC/DC");
			type(AMDMySubscriptionPage.objEnterCCTxt, nCC, "CC number");
			type(AMDMySubscriptionPage.objExpiryCCTxt, expiryDate, "Expiry date");
			type(AMDMySubscriptionPage.objCVVTxt, nCVV, "CVV");
			hideKeyboard();
			click(AMDGenericObjects.objText("Pay Now"), "Pay Now CTA");
			waitTime(5000);
			verifyIsElementDisplayed(AMDGenericObjects.objText("OTP verification"));
			waitTime(3000);
			click(AMDGenericObjects.objOTPField, "otp field");
			waitTime(4000);
			type(AMDGenericObjects.objOTPField, "111111", "otp field");
			hideKeyboard();
			click(AMDGenericObjects.objSubmitAndPay, "Submit and Pay");

			String pManufacturer = DeviceDetails.OEM;
			String contentID = getParameterFromXML("zeeplexContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Page Name", "SubscriptionJourneyPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("Cost", "299.0");
			mixpanel.FEProp.setProperty("Failure Reason", "Unable to process the request.");
			mixpanel.FEProp.setProperty("Payment Method", "CARD");
			mixpanel.FEProp.setProperty("Success", "false");

			mixpanel.ValidateParameter("", "Rental Purchase Call Returned");
		} else {
			logger.info("This is not applicable for Guest User");
			extent.extentLogger("", "This is not applicable for Guest User");
		}
	}

	public void rentalPurchaseCallInitiatedEventOfContentFromSearchPage(String usertype, String keyword)
			throws Exception {
		HeaderChildNode("Rental purchase call initiated event of a content through searchpage");
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			String nCC = "5318 3123 4521 9856";
			String expiryDate = "0325";
			String nCVV = "321";

			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			click(AMDTVODComboOffer.objRentNowCTABelowPlayer, "Rent Now CTA");
			waitTime(2000);
			click(AMDSubscibeScreen.objContinueBtn, "Continue button on Subscription page");
			waitTime(20000);
			click(AMDGenericObjects.objText("Enter Card Number"), "CC/DC");
			type(AMDMySubscriptionPage.objEnterCCTxt, nCC, "CC number");
			type(AMDMySubscriptionPage.objExpiryCCTxt, expiryDate, "Expiry date");
			type(AMDMySubscriptionPage.objCVVTxt, nCVV, "CVV");
			hideKeyboard();
			click(AMDGenericObjects.objText("Pay Now"), "Pay Now CTA");
			waitTime(5000);
			verifyIsElementDisplayed(AMDGenericObjects.objText("OTP verification"));

			String pManufacturer = DeviceDetails.OEM;
			String contentID = getParameterFromXML("zeeplexContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Cost", "299.0");
			mixpanel.FEProp.setProperty("Page Name", "SubscriptionJourneyPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Rental Purchase Call Initiated");
		} else {
			logger.info("This is not applicable for Guest User");
			extent.extentLogger("", "This is not applicable for Guest User");
		}
	}

	public void SubtitleLanguageChangeEventFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Subtitle Language Change of content from search page");
		waitTime(4000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			waitTime(5000);
		}

		click(AMDPlayerScreen.objSubtitleOptionInPotraitMode, "Subtitle Option");
		waitTime(2000);
		click(AMDPlayerScreen.objEnglishSubtitle, "English Subtitle Option");
		waitTime(10000);

		String contentID = getParameterFromXML("audioTrackContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Source", "SearchPage");
		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");
		MixpanelAndroid.FEProp.setProperty("Subtitle Language", "en");
		MixpanelAndroid.FEProp.setProperty("Subtitles", "en");
		MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

		MixpanelAndroid.ValidateParameter("", "Subtitle Language Change");
	}

	@SuppressWarnings("static-access")
	public void verifySubscriptionSelectedEvent_MoreSection(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event From More section Buy Plan ");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(10000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Plan Selection");
			MixpanelAndroid.ValidateParameter("", "Subscription Selected");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySubscriptionSelectedEvent_MySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event From My Subscription Buy Plan ");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(10000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");

			MixpanelAndroid.FEProp.setProperty("Page Name", "Plan Selection");
			MixpanelAndroid.ValidateParameter("", "Subscription Selected");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySubscriptionSelectedEvent_MyTransaction(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event From My Transaction Buy Plan ");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transaction");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(10000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");

			MixpanelAndroid.FEProp.setProperty("Page Name", "Plan Selection");
			MixpanelAndroid.ValidateParameter("", "Subscription Selected");
		}
	}

	public void verifySubscriptionSelectedEvent_BelowPlayer(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Subscription Selected Event - Buy Plan below player");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			click(AMDHomePage.objBuyPlanCTABelowPlayer, "Buy Plan CTA below player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(10000);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Source", "ConsumptionPage");
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Plan Selection");
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

			MixpanelAndroid.ValidateParameter("", "Subscription Selected");
		}
	}

	public void verifySubscriptionSelectedEvent_Trailer(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Subscription Selected Event - Buy Plan on player post completion of trailer playback");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
				waitTime(5000);
			}
			// waitForElementDisplayed(AMDHomePage.objBuyPlanTextOnPlayer, 120);
			waitTime(200000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAOnPlayer, "Buy Plan CTA on player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(10000);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Source", "ConsumptionPage");
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Plan Selection");
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.ValidateParameter("", "Subscription Selected");
		}
	}

	public void verifyScreenViewEvent_BottomNavigation(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event for bottom navigation - " + tabName);
		waitTime(3000);
		getDriver().findElement(By.xpath("//*[@id='navigationTitleTextView' and @text='Home']")).click();
		waitTime(3000);
		try {
			getDriver().findElement(By.xpath("//*[@id='navigationTitleTextView' and @text='" + tabName + "']")).click();
			logger.info("Clicked on " + tabName);
			extent.extentLogger("", "Clicked on " + tabName);
		} catch (Exception e) {
			System.out.println(e);
		}

		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (tabName.equalsIgnoreCase("Home")) {
			if (pUserType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("Source", "SplashPage");
			} else {
				mixpanel.FEProp.setProperty("Source", "More");
			}

		} else {
			mixpanel.FEProp.setProperty("Source", "Homepage");
		}

		if (tabName.equalsIgnoreCase("More")) {
			mixpanel.FEProp.setProperty("Page Name", "More");
		} else if (tabName.equalsIgnoreCase("Upcoming")) {
			mixpanel.FEProp.setProperty("Page Name", "UpcomingPage");
		} else if (tabName.equalsIgnoreCase("Downloads")) {
			mixpanel.FEProp.setProperty("Page Name", "DownloadPage");
		} else if (tabName.equalsIgnoreCase("Home")) {
			mixpanel.FEProp.setProperty("Page Name", "Homepage");
		}

		mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);
		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.ValidateParameter("", "Screen View");
	}

	public void verifyScreenViewEvent_MoreMenuOptions(String userType, String page) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event for More menu options");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		waitTime(3000);
		boolean flag = false;
		if (!(userType.equalsIgnoreCase("Guest"))) {
			if (page.equalsIgnoreCase("My account")) {
				verifyElementPresentAndClick(AMDMoreMenu.objProfile, "My account");
			} else {
				if (page.equalsIgnoreCase("Settings")) {
					Swipe("UP", 1);
				}
				try {
					getDriver().findElement(By.xpath("//*[@id='list_item' and @text='" + page + "']")).click();
					logger.info("Clicked on " + page);
					extent.extentLogger("", "Clicked on " + page);
					flag = true;
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			if (page.equalsIgnoreCase("Settings")) {
				if (page.equalsIgnoreCase("Settings")) {
					Swipe("UP", 1);
				}
				try {
					getDriver().findElement(By.xpath("//*[@id='list_item' and @text='" + page + "']")).click();
					logger.info("Clicked on " + page);
					extent.extentLogger("", "Clicked on " + page);
					flag = true;
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				logger.info("Not applicable for this userType");
				extent.extentLogger("", "Not applicable for this userType");
			}
		}
		if (flag = true) {
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();
			mixpanel.FEProp.setProperty("Source", "More");
			if (page.equalsIgnoreCase("My Subscription")) {
				mixpanel.FEProp.setProperty("Page Name", "MySubscription");
			} else if (page.equalsIgnoreCase("My Transactions")) {
				mixpanel.FEProp.setProperty("Page Name", "MyTransactions");
			} else if (page.equalsIgnoreCase("Settings")) {
				mixpanel.FEProp.setProperty("Page Name", "Settings");
			} else if (page.equalsIgnoreCase("My account")) {
				mixpanel.FEProp.setProperty("Page Name", "accountdetails");
			}
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);
			mixpanel.ValidateParameter("", "Screen View");
		}
	}

	public void verifyScreenViewEvent_SearchPage(String userType) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event for Search page");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(3000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		mixpanel.FEProp.setProperty("Source", "HomePage");
		mixpanel.FEProp.setProperty("Page Name", "SearchPage");
		mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);
		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.ValidateParameter("", "Screen View");
	}

	public void verifyScreenViewEvent_SplashPage(String userType) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event for Splash page");
		extent.extentLogger("Splash page", "Splash screen is displayed");
		if (userType.equalsIgnoreCase("Guest")) {
			waitTime(3000);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();
			mixpanel.FEProp.setProperty("Source", "HomePage");
			mixpanel.FEProp.setProperty("Page Name", "SplashPage");
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);
			if (pUserType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.ValidateParameter("", "Screen View");
		} else {
			logger.info("Not applicable for this userType");
			extent.extentLogger("", "Not applicable for this userType");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEventForBuyPlanHeader(String userType) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		waitTime(3000);

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Element", "Buy Plan");
			MixpanelAndroid.FEProp.setProperty("Page Name", "HomePage");
			MixpanelAndroid.FEProp.setProperty("Button Type", "Header");
		}
		MixpanelAndroid.ValidateParameter("", "CTAs");
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEventForZeeLogo(String userType) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		waitTime(3000);

		verifyElementPresentAndClick(AMDHomePage.objZee5Logo, "ZEE5 Logo");
		waitTime(3000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		MixpanelAndroid.ValidateParameter("", "CTAs");
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEventForSettingsInMore(String userType) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		waitTime(3000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Element", "Settings");
		MixpanelAndroid.FEProp.setProperty("Page Name", "More");
		MixpanelAndroid.FEProp.setProperty("Button Type", "CTA");
		MixpanelAndroid.ValidateParameter("", "CTAs");
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEventForMySubscriptionInMore(String userType) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription CTA");
		waitTime(3000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Element", "MySubscription");
		MixpanelAndroid.FEProp.setProperty("Page Name", "More");
		MixpanelAndroid.FEProp.setProperty("Button Type", "CTA");

		MixpanelAndroid.ValidateParameter("", "CTAs");
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEventForMyTransactionInMore(String userType) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transactions CTA");
		waitTime(3000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Element", "MyTransactions");
		MixpanelAndroid.FEProp.setProperty("Page Name", "More");
		MixpanelAndroid.FEProp.setProperty("Button Type", "CTA");

		MixpanelAndroid.ValidateParameter("", "CTAs");
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEventForContinueBtn(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify CTAs Event");
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(100000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue with ₹ 499");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.FEProp.setProperty("Button Type", "Button");
			MixpanelAndroid.ValidateParameter("", "CTAs");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyCTAsEventForMyProfile(String userType) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Profile CTA");
		waitTime(3000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Element", "MyProfile");
		MixpanelAndroid.FEProp.setProperty("Page Name", "More");
		MixpanelAndroid.FEProp.setProperty("Button Type", "CTA");
		MixpanelAndroid.ValidateParameter("", "CTAs");
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventViaBuyPlanHeader(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event - Buy Plan Header");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Initiated");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventViaBuyPlanMoreSection(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event - Buy Plan More Section");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Initiated");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventViaBuyPlanMySubscription(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event - Buy Plan My Subscription");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(3000);

			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");

			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");
			MixpanelAndroid.ValidateParameter("", "Login Initiated");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventViaBuyPlanMyTransactions(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event - Buy Plan My Transaction");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transactions");
			waitTime(3000);

			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");

			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");
			MixpanelAndroid.ValidateParameter("", "Login Initiated");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventViaBuyPlanBelowPlayer(String userType, String keyword3) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event - Buy Plan below player");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);

			click(AMDHomePage.objBuyPlanCTABelowPlayer, "Buy Plan CTA below player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(3000);

			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Initiated");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginInitiatedEventViaBuyPlan_Trailer(String userType, String keyword3) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Initiated Event - Buy Plan on player post completion of trailer playback");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);

			// waitForElementDisplayed(AMDHomePage.objBuyPlanTextOnPlayer, 120);
			waitTime(200000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAOnPlayer, "Buy Plan CTA on player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Initiated");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginResultEventViaBuyPlanHeader(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event - Buy Plan Header");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(2000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginResultEventViaBuyPlanMoreSection(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event - Buy Plan More Section");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginResultEventViaBuyPlanMySubscription(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event - Buy Plan My Subscription");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(3000);

			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");

			waitTime(10000);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");
			MixpanelAndroid.ValidateParameter("", "Login Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginResultEventViaBuyPlanMyTransactions(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event - Buy Plan My Transaction");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transactions");
			waitTime(3000);

			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");

			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");
			MixpanelAndroid.ValidateParameter("", "Login Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginResultEventViaBuyPlanBelowPlayer(String userType, String keyword3) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event - Buy Plan below player");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);

			click(AMDHomePage.objBuyPlanCTABelowPlayer, "Buy Plan CTA below player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(3000);

			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginResultEventViaBuyPlan_Trailer(String userType, String keyword3) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Result Event - Buy Plan on player post completion of trailer playback");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			waitTime(3000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);

			// waitForElementDisplayed(AMDHomePage.objBuyPlanTextOnPlayer, 120);
			waitTime(200000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAOnPlayer, "Buy Plan CTA on player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Login Result");
		}
	}

	public void AdViewEventOfContentFromConsumptionPageRail(String usertype, String keyword4, String clipID,
			String clipContentID) throws Exception {
		extent.HeaderChildNode("Ad view Event of content from consumption page rail");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {

			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			if (usertype.equalsIgnoreCase("Guest")) {
				registerPopUpClose();
			}
			completeProfilePopUpClose(usertype);
			PartialSwipeInConsumptionScreen("UP", 1);
			waitTime(3000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail)) {
				verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail,
						"Upnext rail content");
			}
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(3000);
				Back(1);
				String pManufacturer = DeviceDetails.OEM;

				Response upNextResp = ResponseInstance.getUpNextContentResponse(clipID, clipContentID);
				String contentID = upNextResp.jsonPath().getString("items[0].id");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Guest");
				}

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad view");
			} else {
				logger.info("Ad is not displayed");
				extent.extentLogger("Ad", "Ad is not displayed");
			}

		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void AdViewEventForContinueWatchingTray(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad View Event for Continue watching tray content");
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			waitTime(10000);
			PartialSwipe("UP", 1);
			if (verifyIsElementDisplayed(AMDHomePage.objTrayTitle("Continue Watching"), "Continuw watching tray")) {
				verifyElementPresentAndClick(AMDHomePage.objContinueWatchingTrayContentCard, "contentCard of CW tray");
				waitTime(20000);
				boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
				if (ad == true) {
					logger.info("Ad is displayed");
					extent.extentLogger("Ad", "Ad is displayed");
					waitTime(2000);
					Back(1);
					Response respOFCW = ResponseInstance.getRespofCWTray_Mixpanel(usertype);
					String contentID = respOFCW.jsonPath().getString("[0].id");
					System.out.println(contentID);

					Response ContentResp = ResponseInstance.getResponseDetails(contentID);
					ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

					String pManufacturer = DeviceDetails.OEM;

					setFEProperty(usertype);
					setUserType_SubscriptionProperties(usertype);
					SetAppsflyerProperty();

					if (usertype.equalsIgnoreCase("Guest")) {
						mixpanel.FEProp.setProperty("User Type", "Guest");
					}

					mixpanel.FEProp.setProperty("Source", "Homepage");
					mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
					mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
					mixpanel.FEProp.setProperty("brand", pManufacturer);

					mixpanel.ValidateParameter("", "Ad view");
				} else {
					logger.info("Ad is not displayed");
					extentLoggerPass("Ad", "Ad is not displayed");
				}
			} else {
				logger.info("Continue watching tray is not displayed");
				extent.extentLogger("", "Continue watching tray is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void AdClickEventOfContentFromConsumptionPageRail(String usertype, String keyword4, String clipID,
			String clipContentID) throws Exception {
		extent.HeaderChildNode("Ad click Event of content from consumption page rail");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			if (usertype.equalsIgnoreCase("Guest")) {
				registerPopUpClose();
			}
			completeProfilePopUpClose(usertype);
			PartialSwipeInConsumptionScreen("UP", 1);
			waitTime(3000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail)) {
				verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail,
						"Upnext rail content");
			}
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is Displayed");
				extentLogger("Ad", "Ad is Displayed");
				verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
				String pManufacturer = DeviceDetails.OEM;

				Response upNextResp = ResponseInstance.getUpNextContentResponse(clipID, clipContentID);
				String contentID = upNextResp.jsonPath().getString("items[0].id");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad click");
			} else {
				logger.info("Ad is not displayed");
				extentLogger("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable to this usertype");
			extent.extentLogger("Not Applicable", "Not Applicable to this usertype");
		}
	}

	public void AdClickForContinueWatchingTray(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad click Event for Continue watching tray content");
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			waitTime(10000);
			PartialSwipe("UP", 1);
			if (verifyIsElementDisplayed(AMDHomePage.objTrayTitle("Continue Watching"), "Continuw watching tray")) {
				verifyElementPresentAndClick(AMDHomePage.objContinueWatchingTrayContentCard, "contentCard of CW tray");
				waitTime(20000);
				boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
				if (ad == true) {
					logger.info("Ad is displayed");
					extent.extentLogger("Ad", "Ad is displayed");
					verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");
					String pManufacturer = DeviceDetails.OEM;

					Response respOFCW = ResponseInstance.getRespofCWTray_Mixpanel(usertype);
					String contentID = respOFCW.jsonPath().getString("[0].id");
					System.out.println(contentID);

					Response ContentResp = ResponseInstance.getResponseDetails(contentID);
					ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

					setFEProperty(usertype);
					setUserType_SubscriptionProperties(usertype);
					SetAppsflyerProperty();

					mixpanel.FEProp.setProperty("Source", "Homepage");
					mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
					mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
					mixpanel.FEProp.setProperty("brand", pManufacturer);

					mixpanel.ValidateParameter("", "Ad click");
				} else {
					logger.info("Ad is not displayed");
					extentLoggerPass("Ad", "Ad is not displayed");
				}
			} else {
				logger.info("Continue watching tray is not displayed");
				extent.extentLogger("", "Continue watching tray is not displayed");
			}
		}
	}

	public void AdInitializedEventOfcontentFromConsumptionPageRail(String usertype, String keyword4, String clipID,
			String clipContentID) throws Exception {
		extent.HeaderChildNode("Ad Initialized Event of content from consumption page rail");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			if (usertype.equalsIgnoreCase("Guest")) {
				registerPopUpClose();
			}
			completeProfilePopUpClose(usertype);
			PartialSwipeInConsumptionScreen("UP", 1);
			waitTime(3000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail)) {
				verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail,
						"Upnext rail content");
			}
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(3000);
				Back(1);
				String pManufacturer = DeviceDetails.OEM;

				Response upNextResp = ResponseInstance.getUpNextContentResponse(clipID, clipContentID);
				String contentID = upNextResp.jsonPath().getString("items[0].id");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad Initialized");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void AdInitializedForContinueWatchingTray(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad Initialized Event for Continue watching tray content");
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			waitTime(10000);
			PartialSwipe("UP", 1);
			if (verifyIsElementDisplayed(AMDHomePage.objTrayTitle("Continue Watching"), "Continuw watching tray")) {
				verifyElementPresentAndClick(AMDHomePage.objContinueWatchingTrayContentCard, "contentCard of CW tray");
				waitTime(20000);
				boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
				if (ad == true) {
					logger.info("Ad is displayed");
					extent.extentLogger("Ad", "Ad is displayed");
					waitTime(3000);
					Back(1);

					Response respOFCW = ResponseInstance.getRespofCWTray(usertype);
					String contentID = respOFCW.jsonPath().getString("[0].id");
					System.out.println(contentID);

					Response ContentResp = ResponseInstance.getResponseDetails(contentID);
					ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

					String pManufacturer = DeviceDetails.OEM;

					setFEProperty(usertype);
					setUserType_SubscriptionProperties(usertype);
					SetAppsflyerProperty();

					mixpanel.FEProp.setProperty("Source", "Homepage");
					mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
					mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
					mixpanel.FEProp.setProperty("brand", pManufacturer);

					mixpanel.ValidateParameter("", "Ad Initialized");
				} else {
					logger.info("Ad is not displayed");
					extentLoggerPass("Ad", "Ad is not displayed");
				}
			} else {
				logger.info("Continue watching tray is not displayed");
				extent.extentLogger("", "Continue watching tray is not displayed");
			}
		}
	}

	public void verifySubscriptionPageViewedEvent_BuyPlan_Header(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event by clicking on Buy plan Header");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeTeaser, "Buy plan");

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (pUserType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("Source", "Homepage");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);

			mixpanel.ValidateParameter("", "Subscription Page Viewed");
		} else {
			logger.info("Not applicable for this usertype");
			extent.extentLoggerPass("", "Not applicable for this usertype");
		}
	}

	public void verifySubscriptionPageViewedEvent_BuyPlan_MoreSection(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event by clicking on Buy plan in More section");

		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy plan");

		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Page Name", "Subscription");
		mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);

		mixpanel.ValidateParameter("", "Subscription Page Viewed");

	}

	public void verifySubscriptionPageViewedEvent_BuyPlan_MySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event by clicking on Buy plan in My Subscription page");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			click(AMDMoreMenu.objMySubscription, "My Subscription");
			verifyElementPresentAndClick(AMDMoreMenu.objSubNowCTA, "Buy Plan");

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (pUserType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("Source", "More");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);

			mixpanel.ValidateParameter("", "Subscription Page Viewed");
		} else {
			logger.info("Not applicable for this usertype");
			extent.extentLoggerPass("", "Not applicable for this usertype");
		}
	}

	public void verifySubscriptionPageViewedEvent_BuyPlan_BelowThePlayer(String userType, String keyword2)
			throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event by clicking on Buy plan below the player");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword2 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			verifyElementPresentAndClick(AMDPlayerScreen.objBuyNowCTABelowThePlayer, "Buy plan CTA below the player");

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (pUserType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("Source", "ConsumptionPage");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);

			mixpanel.ValidateParameter("", "Subscription Page Viewed");
		} else {
			logger.info("Not applicable for this usertype");
			extent.extentLoggerPass("", "Not applicable for this usertype");
		}
	}

	public void verifySubscriptionPageViewedEvent_BuyPlan_AfterCompletionOfTrailerOnThePlayer(String userType,
			String trailerContent) throws Exception {
		extent.HeaderChildNode(
				"Verify Subscription Page Viewed Event by clicking on Buy plan after completion of Trailer on the player");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, trailerContent + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitForAdToFinishInAmd();
			registerPopUpClose();
			completeProfilePopUpClose(userType);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objBuyPlanOnThePlayer))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
				scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
			}

			verifyElementPresentAndClick(AMDPlayerScreen.objBuyPlanOnThePlayer, "Buy Plan on the player");

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (pUserType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("Source", "ConsumptionPage");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("manufacturer", DeviceDetails.OEM);
			mixpanel.FEProp.setProperty("brand", DeviceDetails.OEM);

			mixpanel.ValidateParameter("", "Subscription Page Viewed");
		} else {
			logger.info("Not applicable for this usertype");
			extent.extentLoggerPass("", "Not applicable for this usertype");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPackToggleEvent_MoreSection(String userType) throws Exception {
		extent.HeaderChildNode("Verify Pack Toggle Event From More section Buy Plan ");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.obj299PlanPrice, "229 Pack Toggle");
			waitTime(10000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Pack Toggle");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPackToggleEvent_MySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Verify Pack Toggle Event From My Subscription Buy Plan ");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.obj299PlanPrice, "229 Pack Toggle");
			waitTime(10000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Pack Toggle");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPackToggleEvent_MyTransaction(String userType) throws Exception {
		extent.HeaderChildNode("Verify Pack Toggle Event From My Transaction Buy Plan ");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transaction");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.obj299PlanPrice, "229 Pack Toggle");
			waitTime(10000);
			hideKeyboard();
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			MixpanelAndroid.FEProp.setProperty("Element", "Continue");

			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.ValidateParameter("", "Pack Toggle");
		}
	}

	public void verifyPackToggleEvent_BelowPlayer(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Pack Toggle Event - Buy Plan below player");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			click(AMDHomePage.objBuyPlanCTABelowPlayer, "Buy Plan CTA below player");
			waitTime(3000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDSubscibeScreen.obj299PlanPrice, "229 Pack Toggle");
			waitTime(10000);
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Source", "ConsumptionPage");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

			MixpanelAndroid.ValidateParameter("", "Pack Toggle");
		}
	}

	public void verifyPackToggleEvent_Trailer(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Pack Toggle Event - Buy Plan on player post completion of trailer playback");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
				waitTime(5000);
			}
			// waitForElementDisplayed(AMDHomePage.objBuyPlanTextOnPlayer, 120);
			waitTime(200000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAOnPlayer, "Buy Plan CTA on player");
			waitTime(3000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDSubscibeScreen.obj299PlanPrice, "229 Pack Toggle");
			waitTime(10000);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Source", "ConsumptionPage");
			MixpanelAndroid.FEProp.setProperty("Page Name", "Subscription");
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");
			MixpanelAndroid.ValidateParameter("", "Pack Toggle");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPromoCodeResultEvent_MoreSection(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event From More section Buy Plan ");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(6000);
			Swipe("Up", 2);
			String promoCode = "ZEE5SCB";
			verifyElementPresentAndClick(AMDSubscibeScreen.objHaveAPromocode, "Apply Button");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, promoCode, "Promo Code");
			hideKeyboard();
			waitTime(2000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objApply, "Apply Button");
			waitTime(6000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Promo Code", promoCode);
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.FEProp.setProperty("Button Type", "Button");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Pack Duration", "365");
			MixpanelAndroid.ValidateParameter("", "Promo Code Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPromoCodeResultEvent_MySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event From My Subscription Buy Plan ");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(6000);
			Swipe("Up", 2);
			String promoCode = "ZEE5SCB";
			verifyElementPresentAndClick(AMDSubscibeScreen.objHaveAPromocode, "Apply Button");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, promoCode, "Promo Code");
			hideKeyboard();
			waitTime(2000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objApply, "Apply Button");
			waitTime(6000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Promo Code", promoCode);
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.FEProp.setProperty("Button Type", "Button");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Pack Duration", "365");
			MixpanelAndroid.ValidateParameter("", "Promo Code Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyPromoCodeResultEvent_MyTransaction(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event From My Transaction Buy Plan ");
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transaction");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(6000);
			Swipe("Up", 2);
			String promoCode = "ZEE5SCB";
			verifyElementPresentAndClick(AMDSubscibeScreen.objHaveAPromocode, "Apply Button");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, promoCode, "Promo Code");
			hideKeyboard();
			waitTime(2000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objApply, "Apply Button");
			waitTime(6000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Promo Code", promoCode);
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.FEProp.setProperty("Button Type", "Button");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Pack Duration", "365");
			MixpanelAndroid.ValidateParameter("", "Promo Code Result");
		}
	}

	public void verifyPromoCodeResultEvent_BelowPlayer(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Promo Code Result Event - Buy Plan below player");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			click(AMDHomePage.objBuyPlanCTABelowPlayer, "Buy Plan CTA below player");
			waitTime(6000);
			String promoCode = "ZEE5SCB";
			Swipe("UP", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objHaveAPromocode, "Apply Button");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, promoCode, "Promo Code");
			hideKeyboard();
			waitTime(2000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objApply, "Apply Button");
			waitTime(6000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Promo Code", promoCode);
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.FEProp.setProperty("Button Type", "Button");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Pack Duration", "365");

			MixpanelAndroid.ValidateParameter("", "Promo Code Result");
		}
	}

	public void verifyPromoCodeResultEvent_Trailer(String usertype, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Promo Code Result Event - Buy Plan on player post completion of trailer playback");
			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(usertype);

			if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
				waitForAdToFinishInAmd();
				waitTime(5000);
			}
			waitForElementDisplayed(AMDHomePage.objBuyPlanCTAOnPlayer, 200);
			// waitTime(120000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAOnPlayer, "Buy Plan CTA on player");
			waitTime(6000);
			Swipe("UP", 2);
			String promoCode = "ZEE5SCB";
			verifyElementPresentAndClick(AMDSubscibeScreen.objHaveAPromocode, "Apply Button");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, promoCode, "Promo Code");
			hideKeyboard();
			waitTime(2000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objApply, "Apply Button");
			waitTime(6000);
			setFEProperty(userType);
			mixpanel.FEProp.setProperty("Promo Code", promoCode);
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("Element", "Apply");
			mixpanel.FEProp.setProperty("Button Type", "Button");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Pack Duration", "365");
			MixpanelAndroid.ValidateParameter("", "Promo Code Result");
		}
	}

	public void verifyLoginScreenDisplayEvent_LoginLinkOnPlayer(String userType, String loginMethod, String keyword3)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event - Via Login Link On Player");

			waitTime(4000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);

			verifyElementPresentAndClick(AMDPlayerScreen.objLoginCTA, "Login CTA");
			waitTime(4000);

			switch (loginMethod) {

			case "mobileNumberLogin":
				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, "7892215214", "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(userType);
				setUserType_SubscriptionProperties(userType);
				SetAppsflyerProperty();

				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "Mobile");

				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonsubscribedUserName");
				String Password = "abcdefg";

				verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
				type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
				type(AMDLoginScreen.objPasswordField, Password, "Password field");
				hideKeyboard();
				verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
				waitTime(3000);
				setFEProperty(userType);
				setUserType_SubscriptionProperties(userType);
				SetAppsflyerProperty();

				waitTime(3000);

				MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
				MixpanelAndroid.FEProp.setProperty("Element", "Login");
				MixpanelAndroid.FEProp.setProperty("Method", "email");
				break;
			}
			waitTime(3000);

			MixpanelAndroid.ValidateParameter("", "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginRegistrationScreenDisplayEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Registration Screen Display Event");
			String getEmail = getParameterFromXML("NonsubscribedUserName");
			String getPwd = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDSubscibeScreen.objEmailID, "EmailId Field");
			type(AMDSubscibeScreen.objEmailID, getEmail, "EmailId");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, getPwd, "Password");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(10000);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);

			MixpanelAndroid.FEProp.setProperty("Page Name", "LoginRegister");

			MixpanelAndroid.ValidateParameter("", "Login Registration Screen Display");
		}
	}

	public void AdViewEventForTrayNavigation(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad View event through Landing screen Tray navigation");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			waitTime(10000);

			String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(usertype);
			System.out.println(contentLang);
			String trayName = ResponseInstance.getRailNameFromPage(tabName, usertype);

			if (tabName.equalsIgnoreCase("Live TV") || tabName.equalsIgnoreCase("News")) {
				waitTime(5000);
				waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
			}
			SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
			waitTime(3000);
			if (!(verifyIsElementDisplayed(AMDGenericObjects.objSelectFirstCardFromRailName(trayName)))) {
				PartialSwipe("Up", 1);
			}

			waitTime(3000);
			click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");

			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(2000);
				Back(1);

				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Guest");
				}

				mixpanel.FEProp.setProperty("Source", "Homepage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad view");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void AdInitializedEventForTrayNavigation(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad Initialized event through Landing screen Tray navigation");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			waitTime(10000);

			String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(usertype);
			System.out.println(contentLang);
			String trayName = ResponseInstance.getRailNameFromPage(tabName, usertype);

			if (tabName.equalsIgnoreCase("Live TV") || tabName.equalsIgnoreCase("News")) {
				waitTime(5000);
				waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
			}
			SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
			waitTime(3000);
			if (!(verifyIsElementDisplayed(AMDGenericObjects.objSelectFirstCardFromRailName(trayName)))) {
				PartialSwipe("Up", 1);
			}

			waitTime(3000);
			click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");

			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitTime(2000);
				Back(1);

				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Guest");
				}

				mixpanel.FEProp.setProperty("Source", "Homepage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad Initialized");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void AdClickEventForTrayNavigation(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Ad Click event through Landing screen Tray navigation");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(10000);
			SelectTopNavigationTab(tabName);
			waitTime(10000);

			String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(usertype);
			System.out.println(contentLang);
			String trayName = ResponseInstance.getRailNameFromPage(tabName, usertype);

			if (tabName.equalsIgnoreCase("Live TV") || tabName.equalsIgnoreCase("News")) {
				waitTime(5000);
				waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
			}
			SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
			waitTime(3000);
			if (!(verifyIsElementDisplayed(AMDGenericObjects.objSelectFirstCardFromRailName(trayName)))) {
				PartialSwipe("Up", 1);
			}

			waitTime(3000);
			click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");

			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				verifyElementPresentAndClick(AMDPlayerScreen.objLearnMoreTextOnAdPlayBack, "Learn More");

				String pManufacturer = DeviceDetails.OEM;

				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				if (usertype.equalsIgnoreCase("Guest")) {
					mixpanel.FEProp.setProperty("User Type", "Guest");
				}

				mixpanel.FEProp.setProperty("Source", "Homepage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad click");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void videoViewEventFromTrayNavigationPage(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Video View event through Landing screen Tray navigation");

		waitTime(10000);
		SelectTopNavigationTab(tabName);

		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(usertype);
		System.out.println(contentLang);
		String trayName = ResponseInstance.getRailNameFromPage(tabName, usertype);

		if (tabName.equalsIgnoreCase("Live TV") || tabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}
		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		waitTime(3000);
		if (!(verifyIsElementDisplayed(AMDGenericObjects.objSelectFirstCardFromRailName(trayName)))) {
			PartialSwipe("Up", 1);
		}

		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLogger("Player screen", "error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = true;
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			String pPage = "ConsumptionPage";
			String pSource = "Homepage";
			String pManufacturer = DeviceDetails.OEM;

			mixpanel.FEProp.setProperty("Source", "Homepage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Video View");
		}
	}

	public void videoWatchDurationOfContentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Video watch duration Event of content from search page");

		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		waitTime(5000);
		String var = getText(AMDMoreMenu.objVideo_Autoply);
		if (!(var.equalsIgnoreCase("ON"))) {
			click(AMDMoreMenu.objVideo_Autoply, "Auto play");
			waitTime(5000);
		}
		Back(2);

		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		waitTime(5000);
		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
		waitTime(5000);
		Back(1);

		String contentID = getParameterFromXML("clipContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		String pManufacturer = DeviceDetails.OEM;

		if (usertype.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Video Watch Duration");
	}

	public void afSvodFirstEpisodeFreeForTrayNavigation(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Verify af_svod_first_episode_free event for Tray navigation entry point");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(usertype);
		System.out.println(contentLang);
		String trayName = ResponseInstance.getRailNameFromPage(tabName, usertype);

		if (tabName.equalsIgnoreCase("Live TV") || tabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}
		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		waitTime(3000);
		if (!(verifyIsElementDisplayed(AMDGenericObjects.objSelectFirstCardFromRailName(trayName)))) {
			PartialSwipe("Up", 1);
		}

		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLogger("Player screen", "error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			String pManufacturer = DeviceDetails.OEM;

			mixpanel.FEProp.setProperty("Source", "Homepage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			MixpanelAndroid.ValidateParameter("", "af_svod_first_episode_free");
		}
	}

	public void afSvodFirstEpisodeFreeForCarousalContent(String usertype) throws Exception {
		extent.HeaderChildNode("Verify af_svod_first_episode_free event for Carousal entry point");
		waitTime(10000);
		SelectTopNavigationTab("Web Series");

		String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, "Web Series");
		System.out.println(contentName);

		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLogger("Player screen", "error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			String pManufacturer = DeviceDetails.OEM;

			mixpanel.FEProp.setProperty("Source", "Homepage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			MixpanelAndroid.ValidateParameter("", "af_svod_first_episode_free");
		}

	}

	public void VideoViewEventOfcontentFromSearchPage(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Video view Event of content from search page");

		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLogger("Player screen", "error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = true;
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");

			String contentID = getParameterFromXML("clipContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			String pManufacturer = DeviceDetails.OEM;

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Video View");
		}

	}

	public void videoViewEventForCarousalContent(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Video View Event for carousel content");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, tabName);
		System.out.println(contentName);

		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		boolean inlineLink = verifyIsElementDisplayed(AMDPlayerScreen.objPremiumTextOnPlayer);
		boolean adulterrormsg = verifyIsElementDisplayed(AMDPlayerScreen.objAdultErrorMessage);
		if (inlineLink == true) {
			logger.info("Player inline subscription link is displayed");
			extentLogger("Player screen", "Player inline subscription link is displayed");
		} else if (adulterrormsg == true) {
			logger.info("error message saying 'This content is for Adult view only' is displayed");
			extentLogger("Player screen", "error message saying 'This content is for Adult view only' is displayed");
		} else {
			waitTime(5000);
			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			boolean eventFlag = true;
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			String pManufacturer = DeviceDetails.OEM;

			mixpanel.FEProp.setProperty("Source", "Homepage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Video View");
		}
	}

	public void RegistrationEventValidationViaBuyPlanMySubscription(String userType) throws Exception {
		HeaderChildNode("Registration Events Validation - Via Buy Plan My Subscription");
		System.out.println("\nRegistration Events Validation");

		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String newEmail = createNewEmail();

		if (pUserType.equalsIgnoreCase("Guest")) {

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(3000);

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
			type(AMDLoginScreen.objEmailIdField, newEmail, "Email ID field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "123456", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");

			waitTime(30000);
			// ### Fetching API Values of the usersettings
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("Method", "Email");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("User Type", "Free");

			// ### EVENT VALIDATION
			mixpanel.ValidateParameter("", "Registration Initiated");

			setFEPropertyNewUserThroughBuyPlan();

			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");

			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Result");

			setFEPropertyNewUserThroughBuyPlan();

			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");
			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Success");

		} else {
			extent.extentLogger("NA", " Registration event is not applicable for " + pUserType);
		}
	}

	public void RegistrationEventValidationViaBuyPlanMyTransaction(String userType) throws Exception {
		HeaderChildNode("Registration Events Validation - Via Buy Plan My Transaction");
		System.out.println("\nRegistration Events Validation");

		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String newEmail = createNewEmail();

		if (pUserType.equalsIgnoreCase("Guest")) {

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transaction");
			waitTime(3000);

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
			type(AMDLoginScreen.objEmailIdField, newEmail, "Email ID field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "rgergwe", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");

			waitTime(30000);
			// ### Fetching API Values of the usersettings
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");
			mixpanel.FEProp.setProperty("Method", "Email");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("User Type", "Free");

			// ### EVENT VALIDATION
			mixpanel.ValidateParameter("", "Registration Initiated");

			setFEPropertyNewUserThroughBuyPlan();
			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");

			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Result");

			setFEPropertyNewUserThroughBuyPlan();
			MixpanelAndroid.FEProp.setProperty("Page Name", "Login");
			MixpanelAndroid.FEProp.setProperty("Element", "Login");

			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Success");

		} else {
			extent.extentLogger("NA", " Registration event is not applicable for " + pUserType);
		}
	}

	public void RegistrationEventValidationViaBuyPlanMoreSection(String userType) throws Exception {
		HeaderChildNode("Registration Events Validation - Via Buy Plan More Section");
		System.out.println("\nRegistration Events Validation");

		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String newEmail = createNewEmail();

		if (pUserType.equalsIgnoreCase("Guest")) {

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			waitTime(3000);

			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(5000);
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
			type(AMDLoginScreen.objEmailIdField, newEmail, "Email ID field");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, "123456", "Password");
			hideKeyboard();
			waitTime(3000);
			click(AMDSubscibeScreen.objContinueBtn, "Continue");

			waitTime(30000);
			// ### Fetching API Values of the usersettings
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("Method", "Email");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("User Type", "Free");

			// ### EVENT VALIDATION
			mixpanel.ValidateParameter("", "Registration Initiated");

			setFEPropertyNewUser();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Result");

			setFEPropertyNewUser();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Success");

		} else {
			extent.extentLogger("NA", " Registration event is not applicable for " + pUserType);
		}
	}

	public void RegistrationEventValidationViaBuyPlan(String userType) throws Exception {
		HeaderChildNode("Registration Events Validation - Via Buy Plan Header");
		System.out.println("\nRegistration Events Validation");

		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String newEmail = createNewEmail();

		if (pUserType.equalsIgnoreCase("Guest")) {

			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);

			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(5000);
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
			type(AMDLoginScreen.objEmailIdField, newEmail, "Email ID field");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, "123456", "Password");
			hideKeyboard();
			waitTime(3000);
			click(AMDSubscibeScreen.objContinueBtn, "Continue");

			waitTime(30000);
			// ### Fetching API Values of the usersettings
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("Method", "Email");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("User Type", "Free");

			// ### EVENT VALIDATION
			mixpanel.ValidateParameter("", "Registration Initiated");

			setFEPropertyNewUserThroughBuyPlan();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Result");

			setFEPropertyNewUserThroughBuyPlan();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Success");

		} else {
			extent.extentLogger("NA", " Registration event is not applicable for " + pUserType);
		}
	}

	public void RegistrationEventValidationViaBuyPlanBelowPlayer(String userType, String keyword3) throws Exception {
		HeaderChildNode("Registration Events Validation - Via Buy Plan Below Player");
		System.out.println("\nRegistration Events Validation");

		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String newEmail = createNewEmail();

		if (pUserType.equalsIgnoreCase("Guest")) {

			waitTime(3000);
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);

			click(AMDHomePage.objBuyPlanCTABelowPlayer, "Buy Plan CTA below player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(5000);
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
			type(AMDLoginScreen.objEmailIdField, newEmail, "Email ID field");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, "123456", "Password");
			hideKeyboard();
			waitTime(3000);
			click(AMDSubscibeScreen.objContinueBtn, "Continue");

			waitTime(30000);
			// ### Fetching API Values of the usersettings
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("Method", "Email");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("User Type", "Free");

			// ### EVENT VALIDATION
			mixpanel.ValidateParameter("", "Registration Initiated");

			setFEPropertyNewUserThroughBuyPlan();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Result");

			setFEPropertyNewUserThroughBuyPlan();
			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Success");

		} else {
			extent.extentLogger("NA", " Registration event is not applicable for " + pUserType);
		}
	}

	public void RegistrationEventValidationViaBuyPlanAfterTrailer(String userType, String keyword3) throws Exception {
		HeaderChildNode("Registration Events Validation - Via Buy Plan After Trailer");
		System.out.println("\nRegistration Events Validation");

		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";
		String newEmail = createNewEmail();

		if (pUserType.equalsIgnoreCase("Guest")) {

			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(4000);
			registerPopUpClose();
			completeProfilePopUpClose(userType);

			// waitForElementDisplayed(AMDHomePage.objBuyPlanTextOnPlayer, 120);
			waitTime(200000);
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAOnPlayer, "Buy Plan CTA on player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(5000);
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email/Phone Number");
			type(AMDLoginScreen.objEmailIdField, newEmail, "Email ID field");
			hideKeyboard();
			click(AMDSubscibeScreen.objContinueBtn, "Continue");
			waitTime(3000);
			click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
			type(AMDSubscibeScreen.objEmailID, "123456", "Password");
			waitTime(3000);
			click(AMDSubscibeScreen.objContinueBtn, "Continue");

			waitTime(30000);
			// ### Fetching API Values of the usersettings
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("Method", "Email");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("User Type", "Free");

			// ### EVENT VALIDATION
			mixpanel.ValidateParameter("", "Registration Initiated");

			setFEPropertyNewUserThroughBuyPlan();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");

			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Result");

			setFEPropertyNewUserThroughBuyPlan();

			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("User Type", "Registered");

			mixpanel.ValidateParameterInstantlyVersion2("", "Registration Success");

		} else {
			extent.extentLogger("NA", " Registration event is not applicable for " + pUserType);
		}
	}

	public void setFEPropertyNewUserThroughBuyPlan() {
		String str = "N/A";

		mixpanel.FEProp.setProperty("New Video Streaming Quality Setting", "Auto");
		mixpanel.FEProp.setProperty("New Autoplay Setting", "true");
		mixpanel.FEProp.setProperty("New Stream Over Wifi Setting", "false");
		mixpanel.FEProp.setProperty("New Download Quality Setting", "Ask Each Time");
		mixpanel.FEProp.setProperty("New Download Over Wifi Setting", "false");
		mixpanel.FEProp.setProperty("New App Language", "en");
		mixpanel.FEProp.setProperty("New Content Language", "en,kn");
		mixpanel.FEProp.setProperty("New Content Language", "en,kn");

		mixpanel.FEProp.setProperty("Appsflyer Campaign", str);
		mixpanel.FEProp.setProperty("Appsflyer Medium", str);
		mixpanel.FEProp.setProperty("App Source", str);
		mixpanel.FEProp.setProperty("App Campaign", str);
		mixpanel.FEProp.setProperty("App UTM Source", str);
		mixpanel.FEProp.setProperty("App UTM Medium", str);
		mixpanel.FEProp.setProperty("App UTM Campaign", str);
		mixpanel.FEProp.setProperty("App UTM Content", str);
		mixpanel.FEProp.setProperty("App UTM Term", str);
		mixpanel.FEProp.setProperty("App isRetargeting", str);
	}

	public void ThumbnailClickEventValidationFromLandingPage(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Select first content card from " + tabName);
		System.out.println("\nSelect the first content card from " + tabName);

		waitTime(10000);
		SelectTopNavigationTab(tabName);
		waitTime(10000);

		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(usertype);
		System.out.println(contentLang);
		String trayName = ResponseInstance.getRailNameFromPage_ForThumbnailClickEvent(tabName, usertype,
				"Thumbnail Click");

		if (tabName.equalsIgnoreCase("Live TV") || tabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}
		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		waitTime(3000);
		if (!(verifyIsElementDisplayed(AMDGenericObjects.objSelectFirstCardFromRailName(trayName)))) {
			PartialSwipe("Up", 1);
		}
		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");
		waitTime(15000);
		Back(1);

		String pManufacturer = DeviceDetails.OEM;

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		if (usertype.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		mixpanel.FEProp.setProperty("Page Name", "Homepage");
		mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Thumbnail Click");
	}

	public void ThumbnailClickEventValidationFromConsumptionPage(String usertype, String keyword4, String clipID,
			String clipContentID) throws Exception {
		extent.HeaderChildNode("ThumbnailClick Event from consumption page rail");

		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);
		PartialSwipeInConsumptionScreen("UP", 1);
		waitTime(3000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail)) {
			verifyElementPresentAndClick(AMDPlayerScreen.objFirstContentCardTitleInUpNextRail, "Upnext rail content");
		}

		waitTime(15000);
		Back(1);

		Response upNextResp = ResponseInstance.getUpNextContentResponse(clipID, clipContentID);
		String contentID = upNextResp.jsonPath().getString("items[0].id");

		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		String pManufacturer = DeviceDetails.OEM;

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		if (usertype.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Thumbnail Click");
	}

	public void PopupLaunchEventForAccountInfoPopUp(String userType) throws Exception {
		HeaderChildNode("Popup Launch Event");
		System.out.println("\nPopup Launch Event");
		String pManufacturer = DeviceDetails.OEM;

		if (pUserType.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objSubscribeIcon, "Subscribe button");
			waitTime(3000);
			// Swipe("Up", 2);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue Button");
			waitTime(10000);
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", "Homepage");
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("Parent Control Setting", "N/A");
			mixpanel.FEProp.setProperty("Pop Up Name", "Account Info");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

			mixpanel.ValidateParameter("", "Popup Launch");

		}

	}

	public void PopupLaunchEventForHaveAPrepaidCode(String userType) throws Exception {
		HeaderChildNode("Popup Launch Event");
		System.out.println("\nPopup Launch Event");
		String pManufacturer = DeviceDetails.OEM;

		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		verifyElementPresentAndClick(AMDMoreMenu.objHaveaPrepaidCode, "Have a Prepaid code");
		waitTime(10000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "More");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.FEProp.setProperty("Parent Control Setting", "N/A");
		mixpanel.FEProp.setProperty("Pop Up Name", "PrepaidCodeScreen");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Popup launch");

	}

	public void PopUpCTAsEventForAfterApplyingPrepaidCode(String userType) throws Exception {
		HeaderChildNode("Popup CTAs Event");
		System.out.println("\nPopup Launch Event");
		String pManufacturer = DeviceDetails.OEM;

		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objHaveaPrepaidCode, "Have a Prepaid code");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objPrepaidCodeTxt, "Prepaid code text box");
		type(AMDMoreMenu.objPrepaidCodeTxt, "abcdefg", "Prepaid code text box");
		hideKeyboard();
		click(AMDMoreMenu.objApplyBtn, "Apply button");
		waitTime(10000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "More");
		mixpanel.FEProp.setProperty("Element", "Apply");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.FEProp.setProperty("Parent Control Setting", "N/A");
		mixpanel.FEProp.setProperty("Pop Up Name", "PrepaidCodeScreen");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Pop Up CTAs");

	}

	public void PopUpCTAsEventForInvalidPrepaidCodePopup(String userType) throws Exception {
		HeaderChildNode("Popup CTAs Event");
		System.out.println("\nPopup Launch Event");
		String pManufacturer = DeviceDetails.OEM;

		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objHaveaPrepaidCode, "Have a Prepaid code");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objPrepaidCodeTxt, "Prepaid code text box");
		type(AMDMoreMenu.objPrepaidCodeTxt, "abcdefg", "Prepaid code text box");
		hideKeyboard();
		click(AMDMoreMenu.objApplyBtn, "Apply button");
		waitTime(10000);
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "More");
		mixpanel.FEProp.setProperty("Element", "Done");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.FEProp.setProperty("Parent Control Setting", "N/A");
		mixpanel.FEProp.setProperty("Pop Up Name", "PrepaidCodeScreen");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Pop Up CTAs");

	}

	@SuppressWarnings("static-access")
	public void verifyPopupCTAsEventForDefaultSettingRestoredEvent(String pUserType) throws Exception {
		extent.HeaderChildNode("Popup CTAs Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		Swipe("Up", 2);
		verifyElementPresentAndClick(AMDSettingsScreen.objDefaultSetting, "Default Setting Link");
		verifyElementPresentAndClick(AMDSettingsScreen.objYesCTA, "Yes CTA");

		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
		}
		mixpanel.FEProp.setProperty("Element", "yes");
		mixpanel.FEProp.setProperty("Page Name", "user_setting");
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Parent Control Setting", "N/A");
		mixpanel.FEProp.setProperty("Pop Up Name", "ExitPopup");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Pop Up CTAs");
	}

	@SuppressWarnings("static-access")
	public void verifyPopupLaunchEventForDefaultSettingRestoredEvent(String pUserType) throws Exception {
		extent.HeaderChildNode("Popup Launch Event");
		click(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		Swipe("Up", 2);
		verifyElementPresentAndClick(AMDSettingsScreen.objDefaultSetting, "Default Setting Link");
		verifyElementPresentAndClick(AMDSettingsScreen.objYesCTA, "Yes CTA");

		setFEProperty(pUserType);
		setUserType_SubscriptionProperties(pUserType);

		if (pUserType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "guest");
		}

		mixpanel.FEProp.setProperty("Page Name", "N/A");
		mixpanel.FEProp.setProperty("Source", "More");
		mixpanel.FEProp.setProperty("Manufacturer", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Brand", DeviceDetails.OEM);
		mixpanel.FEProp.setProperty("Pop Up Name", "Reset Settings");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Popup launch");
	}

	public void PopupLaunchEventForSubtitleLanguageSelection(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Popup Launch Event");
		waitTime(4000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			waitTime(5000);
		}

		click(AMDPlayerScreen.objSubtitleOptionInPotraitMode, "Subtitle Option");
		waitTime(2000);
		click(AMDPlayerScreen.objEnglishSubtitle, "English Subtitle Option");
		waitTime(10000);

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Source", "SearchPage");
		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");
		MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.FEProp.setProperty("Pop Up Name", "PlayerSubtitleChooserDialog");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Popup Launch");

	}

	public void PopupLaunchEventForAudioLanguageSelection(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Popup Launch Event");
		waitTime(4000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			waitTime(5000);
		}

		click(AMDPlayerScreen.objAudioLanguage, "Audio Language");
		waitTime(2000);
		click(AMDPlayerScreen.objAudioLanguageOption, "Audio Language Option");
		waitTime(10000);

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Source", "SearchPage");
		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");
		MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.FEProp.setProperty("Pop Up Name", "PlayerAudioChooserDialog");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Popup Launch");

	}

	public void PopupCTAsEventForAudioLanguageSelection(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Popup Launch Event");
		waitTime(4000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		registerPopUpClose();
		completeProfilePopUpClose(usertype);
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
			waitTime(5000);
		}

		click(AMDPlayerScreen.objAudioLanguage, "Audio Language");
		waitTime(2000);
		click(AMDPlayerScreen.objAudioLanguageOption, "Audio Language Option");
		waitTime(10000);

		setFEProperty(usertype);
		setUserType_SubscriptionProperties(usertype);
		SetAppsflyerProperty();

		MixpanelAndroid.FEProp.setProperty("Source", "SearchPage");
		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");
		MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

		mixpanel.FEProp.setProperty("Pop Up Name", "PlayerAudioChooserDialog");
		mixpanel.FEProp.setProperty("Pop Up Type", "native");
		mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

		mixpanel.ValidateParameter("", "Popup CTAs");

	}

	public void verifyPopupLaunchEventForParentalRestrictionSetting(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Popup Launch Event for Parental Restriction popup in Settings");
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");

			waitTime(3000);
			String pManufacturer = DeviceDetails.OEM;
			setParentalFEProperty(userType);
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("Source", "N/A");
			MixpanelAndroid.FEProp.setProperty("Page Name", "N/A");

			mixpanel.FEProp.setProperty("Pop Up Name", "Set Parental Control Pin");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

			mixpanel.parentalSettingsValidateParameter("", "Popup launch");

			waitTime(10000);

			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void verifyPopupLaunchEventForParentalRestriction(String userType, String keyword3) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Popup Launch Event for Parental Restriction popup on player");

			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");

			waitTime(3000);

			Back(1);
			waitTime(3000);
			Back(1);

			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword3 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);

			setParentalFEProperty(userType);
			String pManufacturer = DeviceDetails.OEM;
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("Source", "Landing Search");
			MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");
			MixpanelAndroid.FEProp.setProperty("Player Name", "Kaltura Android");

			mixpanel.FEProp.setProperty("Pop Up Name", "ParentalControlPinDialog");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", "Parental Control");

			mixpanel.parentalSettingsValidateParameter("", "Popup Launch");
			waitTime(5000);
			Back(3);
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void verifyPopupCTAsEventForParentalRestrictionSetPin(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Popup CTAS Event for Parental Restriction popup in Settings");
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");

			waitTime(3000);
			String pManufacturer = DeviceDetails.OEM;
			setParentalFEProperty(userType);
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("Source", "More");
			MixpanelAndroid.FEProp.setProperty("Page Name", "ParentalControl");
			MixpanelAndroid.FEProp.setProperty("Element", "Set Pin");
			mixpanel.FEProp.setProperty("Pop Up Name", "ParentalControl Pin");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

			mixpanel.parentalSettingsValidateParameter("", "Pop Up CTAs");

			waitTime(10000);

			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void verifyPopupCTAsEventForParentalRestrictionDone(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Popup CTAS Event for Parental Restriction popup in Settings");
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");

			waitTime(3000);
			String pManufacturer = DeviceDetails.OEM;
			setParentalFEProperty(userType);
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			MixpanelAndroid.FEProp.setProperty("Source", "More");
			MixpanelAndroid.FEProp.setProperty("Page Name", "ParentalControl");
			MixpanelAndroid.FEProp.setProperty("Element", "Done");
			mixpanel.FEProp.setProperty("Pop Up Name", "ParentalControlSuccessPopup");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", "N/A");

			mixpanel.parentalSettingsValidateParameter("", "Pop Up CTAs");

			waitTime(10000);

			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void PopupCTAEventValidationForLogout(String userType) throws Exception {
		HeaderChildNode("Popup CTA Event");
		System.out.println("\nPopup CTA Event");

		String pSource = "More";
		String pPage = "More";
		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";

		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			SwipeUntilFindElement(AMDMoreMenu.objLogout, "UP");
			click(AMDMoreMenu.objLogout, "Logout");
			verifyElementPresentAndClick(AMDMoreMenu.objCancelButton, "Cancel CTA");

			mixpanel.FEProp.setProperty("Pop Up Name", "Logout");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", str);
			mixpanel.FEProp.setProperty("Element", "Cancel");

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", str);
			mixpanel.FEProp.setProperty("Button Type", "Button");
			mixpanel.FEProp.setProperty("Tab Name", "N/A");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.ValidateParameter("", "Pop Up CTAs");
		}
	}

	public void PopupLaunchEventValidationForLogout(String userType) throws Exception {
		HeaderChildNode("Popup Launch Event");
		System.out.println("\nPopup Launch Event");

		String pSource = "More";
		String pPage = "More";
		String pManufacturer = DeviceDetails.OEM;
		String str = "N/A";

		waitTime(3000);
		if (!(pUserType.equalsIgnoreCase("Guest"))) {
			click(AMDHomePage.MoreMenuIcon, "More menu icon");
			SwipeUntilFindElement(AMDMoreMenu.objLogout, "UP");
			click(AMDMoreMenu.objLogout, "Logout");
			waitTime(10000);

			mixpanel.FEProp.setProperty("Pop Up Name", "Logout");
			mixpanel.FEProp.setProperty("Pop Up Type", "native");
			mixpanel.FEProp.setProperty("Pop Up Group", str);

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			mixpanel.FEProp.setProperty("Source", pSource);
			mixpanel.FEProp.setProperty("Page Name", pPage);
			mixpanel.FEProp.setProperty("Player Name", str);
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.ValidateParameter("", "Popup launch");
		}
	}

	public void playerCTAsEvent(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Player CTAs Event");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTAOnPlayer, "Rent Now CTA on Player");

		String contentID = getParameterFromXML("supermoonContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		mixpanel.FEProp.setProperty("Element", "SUBSCRIBE");

		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Player CTAs");

	}

	public void RibbonCTAsEvent(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Ribbon CTAs Event");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTABelowPlayer, "Rent Now CTA below the player");

		String contentID = getParameterFromXML("supermoonContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("Button Type", "Banner");
		mixpanel.FEProp.setProperty("Element", "Rent Now");

		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Ribbon CTAs");
	}

	public void ScreenViewEvent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Screen view Event");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		String contentName = ResponseInstance.getCarouselContentFromAPI3(userType, tabName);
		System.out.println(contentName);
		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");
		waitTime(15000);
		Back(1);

		String pManufacturer = DeviceDetails.OEM;

		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}

		MixpanelAndroid.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("Source", "Homepage");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Screen View");
	}

	public void PopUpLaunchEvent(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("PopUp Launch Event");
		if (userType.equalsIgnoreCase("Guest")) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTABelowPlayer, "Rent Now CTA below the player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objCTABelowTheComboOfferPage, "CTA on Combo offer page");

			String pManufacturer = DeviceDetails.OEM;

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("Page Name", "Subscription");
			mixpanel.FEProp.setProperty("Source", "Subscription");
			mixpanel.FEProp.setProperty("Popup Name", "Account Info");
			mixpanel.FEProp.setProperty("Popup Type", "native");

			mixpanel.ValidateParameter("", "Popup Launch");
		} else {
			logger.info("Not applicable for this UserType");
			extentLogger("Not Applicable", "Not applicable for this UserType");
		}

	}

	public void PopUpCTAsEvent(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("PopUp CTAs Event");
		if (userType.equalsIgnoreCase("Guest")) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTABelowPlayer, "Rent Now CTA below the player");
			waitTime(3000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objCTABelowTheComboOfferPage, "CTA on Combo offer page");
			verifyElementPresent(AMDLoginScreen.objAccountInfoScreen, "Account Info screen");
			click(AMDLoginScreen.objEmailIdField, "EmailId");
			type(AMDLoginScreen.objEmailIdField, "ramkt@gmail.com", "Email-Id/Phone");
			hideKeyboard();
			click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
			click(AMDGenericObjects.objContinueCTA, "Continue button");

			String pManufacturer = DeviceDetails.OEM;

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Popup Name", "RENTAL");
			mixpanel.FEProp.setProperty("Popup Type", "Native");
			mixpanel.FEProp.setProperty("Popup Group", "RENTAL");
			mixpanel.FEProp.setProperty("Button Type", "Player");

			mixpanel.ValidateParameter("", "Popup CTAs");
		} else {
			logger.info("Not applicable for this UserType");
			extentLogger("Not Applicable", "Not applicable for this UserType");
		}
	}

	public void carousalBannerClickEvent(String userType, String supermoonContentName) throws Exception {
		extent.HeaderChildNode("Carousal Banner Click Event");
		waitTime(10000);
		SelectTopNavigationTab("Music");

		if (waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(supermoonContentName), 7,
				"Supermoon content in carousal")) {
			waitTime(15000);
			Back(1);

			String pManufacturer = DeviceDetails.OEM;

			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			String contentID = getParameterFromXML("supermoonContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Music");

			String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
			Response pageResp = ResponseInstance.getResponseForAppPages("Music", pContentLang, userType);
			String carousalName = pageResp.jsonPath().get("buckets[0].title");
			String carousalid = pageResp.jsonPath().get("buckets[0].id");
			MixpanelAndroid.FEProp.setProperty("Carousal Name", carousalName);
			MixpanelAndroid.FEProp.setProperty("Carousal ID", carousalid);

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("Page Name", "Homepage");
			mixpanel.FEProp.setProperty("Source", "Homepage");

			mixpanel.ValidateParameter("", "Carousal Banner Click");
		} else {
			logger.info("Supermoon content in carousal is not displayed");
			extentLoggerWarning("", "Supermoon content in carousal is not displayed");
		}
	}

	public void LoginWithEmailID(String pEmailId, String pPassword) throws Exception {
		extent.HeaderChildNode("Log into ZEE5 with registered Email account");

		click(AMDHomePage.MoreMenuIcon, "More Menu");
		click(AMDLoginScreen.objProfileIcon, "Profile icon");
		verifyElementPresent(AMDLoginScreen.objEmailIdLabel, "Login/Register screen");
		type(AMDLoginScreen.objEmailIdField, pEmailId, "Email-Id/Phone");
		hideKeyboard();
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresent(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, pPassword, "Password");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login Button");
	}

	public void RentalPageCTAEvent(String userType, String keyword, String emailID, String password) throws Exception {
		extent.HeaderChildNode("Rental Page CTAs Event");
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			waitTime(5000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objMyRentalsOption, "My Rental option");
			verifyElementPresentAndClick(AMDTVODComboOffer.objCTAInMyRentalsPage(keyword), "CTA");

			String contentID = getParameterFromXML("supermoonContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

			String pManufacturer = DeviceDetails.OEM;

			setFEProperty(emailID, password);
			SetAppsflyerProperty();
			ResponseInstance.getRegionDetails();
			ResponseInstance.getUniqueAndUserID(emailID, password);
			ResponseInstance.setSubscriptionDetails_NativeAndroid(emailID, password);
			mixpanel.FEProp.setProperty("User Type", "Premium");

			mixpanel.FEProp.setProperty("Page Name", "MyRentalsPage");
			mixpanel.FEProp.setProperty("Element", "Resume");
			mixpanel.FEProp.setProperty("Button Type", "CTA");

			mixpanel.ValidateParameterForSpecificUser("", "Rental Page CTAs", emailID, password);

		} else {
			logger.info("Not applicable for this UserType");
			extentLogger("Not Applicable", "Not applicable for this UserType");
		}
	}

	public void CarousalBannerImpression(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Carousal Banner Impression Event");
		SelectTopNavigationTab(tabName);
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");
		HashSet<String> listOfCarousalContents = new HashSet<String>();

		for (int i = 0; i < 20; i++) {
			getDriver()
					.findElement(By.xpath(
							"(//*[@class='androidx.appcompat.widget.LinearLayoutCompat' and @enabled='true'])[2]"))
					.click();
			String contentTitle = getDriver().findElement(By.xpath(
					"(//*[@resource-id='com.graymatrix.did:id/cell_center_container']/child::*//*[@id='cell_center_container']/child::*)[1]"))
					.getText();
			System.out.println(contentTitle);
			listOfCarousalContents.add(contentTitle);
			waitTime(3000);
		}
		System.out.println(listOfCarousalContents);
		ArrayList<String> title = new ArrayList<>();
		title.addAll(listOfCarousalContents);

		mixpanel.fetchEvent2("", "Carousal Banner Impression", title);
	}

	public void CarousalBannerImpression(String userType, String tabName, String AdID) throws Exception {
		extent.HeaderChildNode("Carousal Banner Impression Event");
		SelectTopNavigationTab(tabName);
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");
		HashSet<String> listOfCarousalContents = new HashSet<String>();

		waitTime(5000);
		for (int i = 0; i < 20; i++) {
			getDriver().findElement(By.xpath(
					"((//*[@resource-id='com.graymatrix.did:id/cell_top_container'])[1]/child::*)[1]/parent::*/parent::*/parent::*/parent::*/following-sibling::*"))
					.click();
			String contentTitle = getDriver().findElement(By.xpath(
					"(//*[@resource-id='com.graymatrix.did:id/cell_center_container']/child::*//*[@id='cell_center_container']/child::*)[1]"))
					.getText();
			System.out.println(contentTitle);
			listOfCarousalContents.add(contentTitle);
			waitTime(3000);
		}

		System.out.println(listOfCarousalContents);
		ArrayList<String> title = new ArrayList<>();
		title.addAll(listOfCarousalContents);

		mixpanel.fetchEvent_CarousalContentTitle(AdID, "Carousal Banner Impression", title);
	}

	public void CarousalBannerImpressionForVerticalIndexAndHorizontalIndexproperty(String userType, String tabName,
			String AdID) throws Exception {
		extent.HeaderChildNode("Carousal Banner Impression Event with Vertical index and Horizontal index property");
		SelectTopNavigationTab(tabName);
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");

		waitTime(5000);
		ArrayList<String> title = new ArrayList<>();

		String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
		Response pageResp = ResponseInstance.getResponseForAppPages(tabName, pContentLang, userType);
		pageResp.print();

		for (int i = 0; i < 7; i++) {
			String contentTitle = pageResp.jsonPath().getString("buckets[0].items[" + i + "].title");
			title.add(contentTitle);
			waitTime(3000);
		}

		System.out.println(title);

		mixpanel.fetchEvent_VerticalAndHorizontalIndex(AdID, "Carousal Banner Impression", title);
	}

	public void CarousalBannerImpression_NavigatingBackFromOtherTab(String userType, String tabName, String AdID)
			throws Exception {
		extent.HeaderChildNode("Carousal Banner Impression Event by navigating back from other tab");
		SelectTopNavigationTab("Home");
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");

		waitTime(20000);

		SelectTopNavigationTab(tabName);
		waitTime(5000);

		SelectTopNavigationTab("Home");
		waitTime(5000);
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");
		waitTime(5000);
		ArrayList<String> title = new ArrayList<>();

		String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
		Response pageResp = ResponseInstance.getResponseForAppPages("Home", pContentLang, userType);
		pageResp.print();

		for (int i = 0; i < 7; i++) {
			String contentTitle = pageResp.jsonPath().getString("buckets[0].items[" + i + "].title");
			title.add(contentTitle);
			waitTime(3000);
		}

		System.out.println(title);

		mixpanel.fetchEvent_CarousalContentTitle_NavigatingBackFromOtherTab(AdID, "Carousal Banner Impression", title,
				"Top Navigation");
	}

	public void CarousalBannerImpressionEvent_ClickingContentThumbhnail_BackToPreviousPage(String userType,
			String tabName, String AdID) throws Exception {
		extent.HeaderChildNode(
				"Carousal Banner Impression Event by clicking content thumbnail and navigating back to previous page");
		SelectTopNavigationTab("Home");
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");
		waitTime(20000);

		click(AMDHomePage.objCarouselConetentCard, "Carousal Content card");
		waitTime(15000);
		Back(1);
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");
		ArrayList<String> title = new ArrayList<>();

		String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
		Response pageResp = ResponseInstance.getResponseForAppPages("Home", pContentLang, userType);
		pageResp.print();

		for (int i = 0; i < 7; i++) {
			String contentTitle = pageResp.jsonPath().getString("buckets[0].items[" + i + "].title");
			title.add(contentTitle);
			waitTime(3000);
		}

		System.out.println(title);

		mixpanel.fetchEvent_CarousalContentTitle_NavigatingBackFromOtherTab(AdID, "Carousal Banner Impression", title,
				"ConsumptionPage");

	}

	public void pageRailImpression(String userType, String tabName, String AdID, int trayIndex) throws Exception {
		extent.HeaderChildNode("Page Rail Impression Event");
		SelectTopNavigationTab(tabName);
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");

		String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
		Response pageResp = ResponseInstance.getResponseForAppPages(tabName, pContentLang, userType);
		pageResp.print();
		String trayTitle = pageResp.jsonPath().getString("buckets[" + trayIndex + "].title");
		String trayId = pageResp.jsonPath().getString("buckets[" + trayIndex + "].id");
		List trayContents = pageResp.jsonPath().getList("buckets[" + trayIndex + "].items");
		System.out.println(trayContents.size());
		ArrayList<String> title = new ArrayList<String>();

		for (int i = 0; i < trayContents.size(); i++) {
			String contentTitle = pageResp.jsonPath().getString("buckets[" + trayIndex + "].items[" + i + "].title");
			title.add(contentTitle);
		}
		String content = title.toString().replace(" ", "").replace("[", "").replace("]", "");

		System.out.println(trayTitle);
		System.out.println(trayId);
		System.out.println(content);

		int index = 0;

		if (!(userType.equalsIgnoreCase("Guest"))) {
			if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray, "Continue watching tray")) {
				index = trayIndex + 2;
				mixpanel.FEProp.setProperty("Vertical Index", Integer.toString(index));
			} else {
				index = trayIndex + 1;
				mixpanel.FEProp.setProperty("Vertical Index", Integer.toString(index));
			}
		}

		SwipeUntilFindElement(AMDHomePage.objTrayTitle(trayTitle), "UP");

		mixpanel.FEProp.setProperty("Carousal Name", trayTitle);
		mixpanel.FEProp.setProperty("Carousal ID", trayId);
		mixpanel.FEProp.setProperty("Rail Content Listing", content);

		mixpanel.ValidateParameter("", "Page Rail Impression");

	}

	public void pageRailImpressionByNavigatingFromListingScreen(String userType, String tabName, String AdID,
			int trayIndex) throws Exception {
		extent.HeaderChildNode("Page Rail Impression Event by navigating back from Listing screen");
		SelectTopNavigationTab(tabName);
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");

		String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
		Response pageResp = ResponseInstance.getResponseForAppPages(tabName, pContentLang, userType);
		pageResp.print();
		String trayTitle = pageResp.jsonPath().getString("buckets[" + trayIndex + "].title");
		String trayId = pageResp.jsonPath().getString("buckets[" + trayIndex + "].id");
		List trayContents = pageResp.jsonPath().getList("buckets[" + trayIndex + "].items");
		System.out.println(trayContents.size());
		ArrayList<String> title = new ArrayList<String>();

		for (int i = 0; i < trayContents.size(); i++) {
			String contentTitle = pageResp.jsonPath().getString("buckets[" + trayIndex + "].items[" + i + "].title");
			title.add(contentTitle);
		}
		String content = title.toString().replace(" ", "").replace("[", "").replace("]", "");

		System.out.println(trayTitle);
		System.out.println(trayId);
		System.out.println(content);

		int index = 0;

		if (!(userType.equalsIgnoreCase("Guest"))) {
			if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray, "Continue watching tray")) {
				index = trayIndex + 2;
			} else {
				index = trayIndex + 1;
			}
		} else {
			index = trayIndex + 1;
		}

		SwipeUntilFindElement(AMDHomePage.objTrayTitle(trayTitle), "UP");
		click(AMDHomePage.objMoreIconOfTray(trayTitle), trayTitle + " - More icon");
		waitTime(15000);
		click(AMDHomePage.objBackIcon, "Back icon");

		mixpanel.fetchEvent_TrayTitle_NavigatingBAckToPreviousPage(AdID, "Page Rail Impression", trayTitle, trayId,
				content, Integer.toString(index), "ListingScreen");

	}

	public void pageRailImpressionByNavigatingFromOtherTab(String userType, String AdID, int trayIndex)
			throws Exception {
		extent.HeaderChildNode("Page Rail Impression Event by navigating back from other Tab");
		SelectTopNavigationTab("Home");
		verifyElementExist(AMDHomePage.objCarouselConetentCard, "Carousal Content card");

		String pContentLang = ResponseInstance.getContentLanguageForAppMixpanel(userType);
		Response pageResp = ResponseInstance.getResponseForAppPages("Home", pContentLang, userType);
		pageResp.print();
		String trayTitle = pageResp.jsonPath().getString("buckets[" + trayIndex + "].title");
		String trayId = pageResp.jsonPath().getString("buckets[" + trayIndex + "].id");
		List trayContents = pageResp.jsonPath().getList("buckets[" + trayIndex + "].items");
		System.out.println(trayContents.size());
		ArrayList<String> title = new ArrayList<String>();

		for (int i = 0; i < trayContents.size(); i++) {
			String contentTitle = pageResp.jsonPath().getString("buckets[" + trayIndex + "].items[" + i + "].title");
			title.add(contentTitle);
		}
		String content = title.toString().replace(" ", "").replace("[", "").replace("]", "");

		System.out.println(trayTitle);
		System.out.println(trayId);
		System.out.println(content);

		int index = 0;

		if (!(userType.equalsIgnoreCase("Guest"))) {
			if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray, "Continue watching tray")) {
				index = trayIndex + 2;
			} else {
				index = trayIndex + 1;
			}
		} else {
			index = trayIndex + 1;
		}

		SwipeUntilFindElement(AMDHomePage.objTrayTitle(trayTitle), "UP");
		SelectTopNavigationTab("Movies");
		waitTime(15000);
		SelectTopNavigationTab("Home");
		SwipeUntilFindElement(AMDHomePage.objTrayTitle(trayTitle), "UP");

		mixpanel.fetchEvent_TrayTitle_NavigatingBAckToPreviousPage(AdID, "Page Rail Impression", trayTitle, trayId,
				content, Integer.toString(index), "OtherTab");
	}

	public void RentalPurchaseCallInitiated(String userType, String keyword) throws Exception {
		HeaderChildNode("Rental purchase call initiated event");
		String nCC = "5318 3123 4521 9856";
		String expiryDate = "0325";
		String nCVV = "321";

		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTAOnPlayer, "Rent Now CTA on Player");
		verifyElementPresentAndClick(AMDTVODComboOffer.objCTABelowTheComboOfferPage, "CTA on Combo offer page");

		if (userType.equalsIgnoreCase("Guest")) {
			verifyElementPresent(AMDLoginScreen.objAccountInfoScreen, "Account Info screen");
			click(AMDLoginScreen.objEmailIdField, "EmailId");
			type(AMDLoginScreen.objEmailIdField, "ramkt@gmail.com", "Email-Id/Phone");
			hideKeyboard();
			click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
			click(AMDGenericObjects.objContinueCTA, "Continue button");
			click(AMDLoginScreen.objEmailIdField, "Password field");
			type(AMDLoginScreen.objEmailIdField, "123456", "Password feild");
			hideKeyboard();
			click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
			click(AMDGenericObjects.objContinueCTA, "Continue button");
		}
		click(AMDGenericObjects.objText("Enter Card Number"), "CC/DC");
		type(AMDMySubscriptionPage.objEnterCCTxt, nCC, "CC number");
		type(AMDMySubscriptionPage.objExpiryCCTxt, expiryDate, "Expiry date");
		type(AMDMySubscriptionPage.objCVVTxt, nCVV, "CVV");
		hideKeyboard();
		click(AMDGenericObjects.objText("Pay Now"), "Pay Now CTA");
		waitTime(5000);
		verifyIsElementDisplayed(AMDGenericObjects.objText("OTP verification"));
		String contentID = getParameterFromXML("supermoonContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Rental Purchase Call Initiated");
	}

	public void rentalPurchaseCallReturned(String userType, String keyword) throws Exception {
		HeaderChildNode("Rental purchase call Returned event");
		String nCC = "5318 3123 4521 9856";
		String expiryDate = "0325";
		String nCVV = "321";

		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTAOnPlayer, "Rent Now CTA on Player");
		verifyElementPresentAndClick(AMDTVODComboOffer.objCTABelowTheComboOfferPage, "CTA on Combo offer page");

		if (userType.equalsIgnoreCase("Guest")) {
			verifyElementPresent(AMDLoginScreen.objAccountInfoScreen, "Account Info screen");
			click(AMDLoginScreen.objEmailIdField, "EmailId");
			type(AMDLoginScreen.objEmailIdField, "ramkt@gmail.com", "Email-Id/Phone");
			hideKeyboard();
			click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
			click(AMDGenericObjects.objContinueCTA, "Continue button");
			click(AMDLoginScreen.objEmailIdField, "Password field");
			type(AMDLoginScreen.objEmailIdField, "123456", "Password feild");
			hideKeyboard();
			click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
			click(AMDGenericObjects.objContinueCTA, "Continue button");
		}

		click(AMDGenericObjects.objText("Enter Card Number"), "CC/DC");
		type(AMDMySubscriptionPage.objEnterCCTxt, nCC, "CC number");
		type(AMDMySubscriptionPage.objExpiryCCTxt, expiryDate, "Expiry date");
		type(AMDMySubscriptionPage.objCVVTxt, nCVV, "CVV");
		hideKeyboard();
		click(AMDGenericObjects.objText("Pay Now"), "Pay Now CTA");
		waitTime(5000);
		verifyIsElementDisplayed(AMDGenericObjects.objText("OTP verification"));
		waitTime(30000);
		verifyElementPresentAndClick(AMDMySubscriptionPage.objRetryPaymentCTA, "Retry Payment CTA");

		String contentID = getParameterFromXML("supermoonContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");

		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Rental Purchase Call Returned");
	}

	public void WidgetCTAs(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Widget CTAs Event");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		PartialSwipeInConsumptionScreen("UP", 1);
		verifyElementPresentAndClick(AMDTVODComboOffer.objHowItWorksCTA, "How it works");
		String contentID = getParameterFromXML("supermoonContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");

		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();

		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "SearchPage");
		mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
		mixpanel.FEProp.setProperty("Button Type", "Banner");
		mixpanel.FEProp.setProperty("Element", "How it works");

		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);

		mixpanel.ValidateParameter("", "Widget CTAs");
	}

	public void PackToggle(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Pack toggle Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTABelowPlayer, "Rent Now CTA below the player");
			waitTime(10000);
			Swipe("UP", 2);
			click(AMDTVODComboOffer.objOnlyRentMoviePlanCost, "Only Event Pass cost");

			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();

			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "SearchPage");
			mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
			mixpanel.FEProp.setProperty("Counter", "1");

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Pack Toggle");
		} else {
			logger.info("Not applicable for this userType");
			extentLoggerPass("", "Not applicable for this userType");
		}
	}

	public void SubscriptionSeleted(String userType, String keyword) throws Exception {
		HeaderChildNode("Subscription selected event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTAOnPlayer, "Rent Now CTA on Player");
			verifyElementPresentAndClick(AMDTVODComboOffer.objCTABelowTheComboOfferPage, "CTA on Combo offer page");

			if (userType.equalsIgnoreCase("Guest")) {
				verifyElementPresent(AMDLoginScreen.objAccountInfoScreen, "Account Info screen");
				click(AMDLoginScreen.objEmailIdField, "EmailId");
				type(AMDLoginScreen.objEmailIdField, "ramkt@gmail.com", "Email-Id/Phone");
				hideKeyboard();
				// click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
				click(AMDGenericObjects.objContinueCTA, "Continue button");
				waitTime(5000);
				click(AMDLoginScreen.objEmailIdField, "Password field");
				type(AMDLoginScreen.objEmailIdField, "123456", "Password feild");
				hideKeyboard();
				// click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
				click(AMDGenericObjects.objContinueCTA, "Continue button");
			}

			String contentID = getParameterFromXML("supermoonContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();
			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "account_info");
			mixpanel.FEProp.setProperty("Page Name", "Combo Offer");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", "499.0");
			mixpanel.FEProp.setProperty("Pack Selected", "0-11-1842_Premium");
			mixpanel.FEProp.setProperty("Current Subscription", "false");

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.ValidateParameter("", "Subscription Selected");
		} else {
			logger.info("Not applicable for this userType");
			extentLoggerPass("", "Not applicable for this userType");
		}
	}

	public void subscriptionCallInitiated(String userType, String keyword) throws Exception {
		HeaderChildNode("Subscription call initiated event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			String nCC = "5318 3123 4521 9856";
			String expiryDate = "0325";
			String nCVV = "321";

			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			verifyElementPresentAndClick(AMDTVODComboOffer.objRentNowCTAOnPlayer, "Rent Now CTA on Player");
			verifyElementPresentAndClick(AMDTVODComboOffer.objCTABelowTheComboOfferPage, "CTA on Combo offer page");

			if (userType.equalsIgnoreCase("Guest")) {
				verifyElementPresent(AMDLoginScreen.objAccountInfoScreen, "Account Info screen");
				click(AMDLoginScreen.objEmailIdField, "EmailId");
				type(AMDLoginScreen.objEmailIdField, "ramkt@gmail.com", "Email-Id/Phone");
				hideKeyboard();
				// click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
				click(AMDGenericObjects.objContinueCTA, "Continue button");
				waitTime(5000);
				click(AMDLoginScreen.objEmailIdField, "Password field");
				type(AMDLoginScreen.objEmailIdField, "123456", "Password feild");
				hideKeyboard();
				// click(AMDLoginScreen.objAccountInfoScreen, "HideKeyboard");
				click(AMDGenericObjects.objContinueCTA, "Continue button");
			}
			click(AMDGenericObjects.objText("Enter Card Number"), "CC/DC");
			type(AMDMySubscriptionPage.objEnterCCTxt, nCC, "CC number");
			type(AMDMySubscriptionPage.objExpiryCCTxt, expiryDate, "Expiry date");
			type(AMDMySubscriptionPage.objCVVTxt, nCVV, "CVV");
			hideKeyboard();
			click(AMDGenericObjects.objText("Pay Now"), "Pay Now CTA");
			waitTime(5000);

			String contentID = getParameterFromXML("supermoonContentID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();
			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "account_info");
			mixpanel.FEProp.setProperty("Page Name", "Combo Offer");
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", "499.0");
			mixpanel.FEProp.setProperty("Current Subscription", "false");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.ValidateParameter("", "Subscription Call Initiated");
		} else {
			logger.info("Not applicable for this userType");
			extentLoggerPass("", "Not applicable for this userType");
		}
	}

	public void ContentBucketSwipeEvent_ConsumptionPageRail(String pUsertype, String keyword, String trayName)
			throws Exception {
		HeaderChildNode("Content Bucket Swipe Validation of Consumption page rail");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		registerPopUpClose();
		completeProfilePopUpClose(pUsertype);

		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");

		// ### Swiping the content card from rails
		SwipeRailContentCards(AMDHomePage.objRailName(trayName));
		SwipeRailContentCards(AMDHomePage.objRailName(trayName));

		MixpanelAndroid.FEProp.setProperty("Direction", "RIGHT");
		MixpanelAndroid.FEProp.setProperty("Carousal Name", trayName);

	}

	public void PlayerCTAsEvent_Pause(String pUsertype, String keyword) throws Exception {
		HeaderChildNode("Player CTAs - Pause");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		waitForAdToFinishInAmd();
		registerPopUpClose();
		completeProfilePopUpClose(pUsertype);

		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");

		String contentID = getParameterFromXML("clipContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "Search_results");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		mixpanel.FEProp.setProperty("Element", "PLAY_PAUSE");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Player CTAs");

	}

	public void PlayerCTAsEvent_NextIcon(String pUsertype, String keyword) throws Exception {
		HeaderChildNode("Player CTAs - NextIcon");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitTime(5000);
		waitForAdToFinishInAmd();
		registerPopUpClose();
		completeProfilePopUpClose(pUsertype);

		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		click(AMDPlayerScreen.objPauseIcon, "Pause icon");
		click(AMDPlayerScreen.objNextIcon, "Next icon");

		String contentID = getParameterFromXML("clipContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "Search_results");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		mixpanel.FEProp.setProperty("Element", "PLAY_NEXT");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Player CTAs");

	}

	public void PlayerCTAsEvent_BuyPlanCTA(String pUsertype, String keyword) throws Exception {
		HeaderChildNode("Player CTAs - BuyPlanCTA");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			click(AMDPlayerScreen.objBuyPlanOnThePlayer, "Buy Plan CTA");

			String contentID = getParameterFromXML("keyword2ID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();
			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "Search_results");
			mixpanel.FEProp.setProperty("Page Name", "play_detail");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			mixpanel.FEProp.setProperty("Element", "SUBSCRIBE");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.ValidateParameter("", "Player CTAs");
		} else {
			logger.info("Not applicable for this userType");
			extentLoggerPass("", "Not applicable for this userType");
		}

	}

	public void PlayerCTAsEvent_LoginCTA(String pUsertype, String keyword) throws Exception {
		HeaderChildNode("Player CTAs - LoginCTA");
		if (userType.equalsIgnoreCase("Guest")) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);
			click(AMDPlayerScreen.objLoginCTA, "Login CTA");

			String contentID = getParameterFromXML("keyword2ID");
			Response ContentResp = ResponseInstance.getResponseDetails(contentID);
			ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(userType);
			setUserType_SubscriptionProperties(userType);
			SetAppsflyerProperty();
			if (userType.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}
			mixpanel.FEProp.setProperty("Source", "Search_results");
			mixpanel.FEProp.setProperty("Page Name", "play_detail");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			mixpanel.FEProp.setProperty("Element", "LOGIN");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.ValidateParameter("", "Player CTAs");
		} else {
			logger.info("Not applicable for this userType");
			extentLoggerPass("", "Not applicable for this userType");
		}
	}

	public void PlayerCTAsEvent_RentNowCTA(String pUsertype, String keyword) throws Exception {
		HeaderChildNode("Player CTAs - RentNow CTA");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
		hideKeyboard();
		waitTime(4000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
		waitForElementAndClickIfPresent(AMDTVODComboOffer.objRentNowCTAOnPlayer, 30, "Rent Now CTA");

		String contentID = getParameterFromXML("zeeplexContentID");
		Response ContentResp = ResponseInstance.getResponseDetails(contentID);
		ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
		String pManufacturer = DeviceDetails.OEM;
		setFEProperty(userType);
		setUserType_SubscriptionProperties(userType);
		SetAppsflyerProperty();
		if (userType.equalsIgnoreCase("Guest")) {
			mixpanel.FEProp.setProperty("User Type", "Guest");
		}
		mixpanel.FEProp.setProperty("Source", "Search_results");
		mixpanel.FEProp.setProperty("Page Name", "play_detail");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		mixpanel.FEProp.setProperty("Element", "SUBSCRIBE");
		mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
		mixpanel.FEProp.setProperty("brand", pManufacturer);
		mixpanel.ValidateParameter("", "Player CTAs");
	}

	public void videos_content_PlayEventOfContentFromCarousal(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Videos_content_Play Event of content from Carousal");
		waitTime(10000);
		SelectTopNavigationTab(tabName);

		String contentName = ResponseInstance.getCarouselContentFromAPI2(usertype, tabName);
		System.out.println(contentName);

		waitForElementAndClickIfPresent(AMDHomePage.objContentTitle(contentName), 7, "carousal content");
		waitTime(20000);
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		waitTime(5000);
		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		boolean eventFlag = verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon);
		Back(1);
		if (eventFlag) {
			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Videos_Content_Play");
		} else {
			logger.info("Failed to play the video");
			extentLoggerWarning("Event", "Failed to play the video");
		}
	}

	public void videos_content_PlayEventOfContentFromTrayNavigation(String usertype, String tabName) throws Exception {
		extent.HeaderChildNode("Videos_content_Play Event of content from tray navigation");
		waitTime(10000);
		SelectTopNavigationTab(tabName);
		waitTime(10000);

		String contentLang = ResponseInstance.getContentLanguageForAppMixpanel(usertype);
		System.out.println(contentLang);
		String trayName = ResponseInstance.getRailNameFromPage(tabName, usertype);

		if (tabName.equalsIgnoreCase("Live TV") || tabName.equalsIgnoreCase("News")) {
			waitTime(5000);
			waitForElementDisplayed(AMDGenericObjects.objTrayTitle, 30);
		}
		SwipeUntilFindElement(AMDHomePage.objRailName(trayName), "UP");
		waitTime(3000);
		if (!(verifyIsElementDisplayed(AMDGenericObjects.objSelectFirstCardFromRailName(trayName)))) {
			PartialSwipe("Up", 1);
		}

		waitTime(3000);
		click(AMDGenericObjects.objSelectFirstCardFromRailName(trayName), "Content Card");

		waitTime(20000);

		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			waitForAdToFinishInAmd();
		}
		if (usertype.equalsIgnoreCase("Guest")) {
			registerPopUpClose();
		}
		completeProfilePopUpClose(usertype);

		waitTime(5000);
		if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		}
		boolean eventFlag = verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon);
		Back(1);
		if (eventFlag) {
			String pManufacturer = DeviceDetails.OEM;
			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			mixpanel.ValidateParameter("", "Videos_Content_Play");
		} else {
			logger.info("Failed to play the video");
			extentLoggerWarning("Event", "Failed to play the video");
		}
	}

	public void paymentScreenImpression_BuyPlanHeader(String usertype) throws Exception {
		HeaderChildNode("Payment screen impression through Buy Plan header");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDHomePage.objBuyPlanCTA, "Buy Plan CTA");
			click(AMDSubscibeScreen.objContinueBtn, "Continue button on Subscription page");
			waitTime(2000);
			if (usertype.equalsIgnoreCase("Guest")) {
				click(AMDSubscibeScreen.objEmailID, "EmailId Field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedUserName"), "EmailId");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
				waitTime(5000);
				click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedPassword"), "Password");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
			}
			String pManufacturer = DeviceDetails.OEM;
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.ValidateParameter("", "Payment Screen Impression");
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void paymentScreenImpression_BuyPlanMoreScreen(String usertype) throws Exception {
		HeaderChildNode("Payment screen impression through Buy Plan From More screen");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			click(AMDMoreMenu.objBuySubscription, "Buy Plan");
			click(AMDSubscibeScreen.objContinueBtn, "Continue button on Subscription page");
			waitTime(2000);
			if (usertype.equalsIgnoreCase("Guest")) {
				click(AMDSubscibeScreen.objEmailID, "EmailId Field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedUserName"), "EmailId");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
				waitTime(5000);
				click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedPassword"), "Password");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
			}
			String pManufacturer = DeviceDetails.OEM;
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.ValidateParameter("", "Payment Screen Impression");
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void paymentScreenImpression_MySubscriptionMoreScreen(String usertype) throws Exception {
		HeaderChildNode("Payment screen impression through My Subscription From More screen");
		if (usertype.equalsIgnoreCase("NonSubscribedUser")) {
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			click(AMDMoreMenu.objMySubscription, "My Subscription");
			verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTAMoreSection, "Buy Plan CTA");
			click(AMDSubscibeScreen.objContinueBtn, "Continue button on Subscription page");
			waitTime(2000);
			if (usertype.equalsIgnoreCase("Guest")) {
				click(AMDSubscibeScreen.objEmailID, "EmailId Field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedUserName"), "EmailId");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
				waitTime(5000);
				click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedPassword"), "Password");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
			}
			String pManufacturer = DeviceDetails.OEM;
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.ValidateParameter("", "Payment Screen Impression");
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void paymentScreenImpression_BuyPlanBelowThePlayer(String usertype, String keyword) throws Exception {
		HeaderChildNode("Payment screen impression through My Subscription From More screen");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);

			click(AMDPlayerScreen.objBuyNowCTABelowThePlayer, "Buy Plan CTA Below the player");
			click(AMDSubscibeScreen.objContinueBtn, "Continue button on Subscription page");
			waitTime(2000);
			if (usertype.equalsIgnoreCase("Guest")) {
				click(AMDSubscibeScreen.objEmailID, "EmailId Field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedUserName"), "EmailId");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
				waitTime(5000);
				click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedPassword"), "Password");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
			}
			String pManufacturer = DeviceDetails.OEM;
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.ValidateParameter("", "Payment Screen Impression");
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void paymentScreenImpression_BuyPlanPostTrailer(String usertype, String keyword) throws Exception {
		HeaderChildNode("Payment screen impression through Buy Plan CTA on Player Post Trailer");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(5000);

			if (!(verifyIsElementDisplayed(AMDPlayerScreen.objFullscreenIcon))) {
				click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			}
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			scrubProgressBarTillEnd(AMDPlayerScreen.objProgressBar);
			verifyElementPresentAndClick(AMDPlayerScreen.objBuyPlanOnThePlayer, "Buy Plan CTA on Player");

			click(AMDSubscibeScreen.objContinueBtn, "Continue button on Subscription page");
			waitTime(2000);
			if (usertype.equalsIgnoreCase("Guest")) {
				click(AMDSubscibeScreen.objEmailID, "EmailId Field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedUserName"), "EmailId");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
				waitTime(5000);
				click(AMDSubscibeScreen.objEnterPassword, "Pwd field");
				type(AMDSubscibeScreen.objEmailID, getParameterFromXML("NonsubscribedPassword"), "Password");
				hideKeyboard();
				click(AMDSubscibeScreen.objContinueBtn, "Continue");
			}
			String pManufacturer = DeviceDetails.OEM;
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);

			setFEProperty(usertype);
			setUserType_SubscriptionProperties(usertype);
			SetAppsflyerProperty();

			if (usertype.equalsIgnoreCase("Guest")) {
				mixpanel.FEProp.setProperty("User Type", "Guest");
			}

			mixpanel.ValidateParameter("", "Payment Screen Impression");
		} else {
			logger.info("This is not applicable for " + usertype);
			extentLogger("Guest User", "This is not applicable for " + usertype);
		}

	}

	public void subscriptionValidation(String usertype) throws Exception {
		HeaderChildNode("payment screen validation");
		if (usertype.equalsIgnoreCase("Guest")) {
			for (int i = 1; i <= 2; i++) {
				System.out.println(i);
				extent.extentLogger("", "iteration number: " + i);
				waitTime(20000);
				if (verifyIsElementDisplayed(AMDOnboardingScreen.objUpdatePopUpNoThanksOption)) {
					click(AMDOnboardingScreen.objUpdatePopUpNoThanksOption, "No Thanks on Update popUp");
				}
				if (verifyIsElementDisplayed(AMDOnboardingScreen.objContinueBtnInCountryPopUp)) {
					click(AMDOnboardingScreen.objContinueBtnInCountryPopUp, "Continuebutton(Country_Screen)");
				}
				String pNewPassword = "123456";
				String newEmailId = "Auto" + generateRandomString(4) + "@ZEE.com";
				waitTime(3000);

				verifyElementPresentAndClick(AMDHomePage.objBuyPlanCTA, "Buy Plan CTA");
				waitTime(3000);
				click(AMDSubscibeScreen.objContinueBtn, "Continue button");
				waitTime(5000);
				click(AMDSubscibeScreen.objEmailID, "Email id");
				type(AMDSubscibeScreen.objEmailID, newEmailId, "Email ID");
				hideKeyboard();
				verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue button");
				waitTime(5000);
				click(AMDSubscibeScreen.objEnterPassword, "Password");
				type(AMDSubscibeScreen.objEnterPassword, pNewPassword, "Password");
				hideKeyboard();
				verifyElementPresentAndClick(AMDSubscibeScreen.objContinueBtn, "Continue button");
				waitTime(2000);
				verifyElementPresentAndClick(AMDSubscibeScreen.objEntercardnumber, "Enter Card Number");

				logger.info("Relaunching the application");
				extent.extentLogger("Relaunch", "Relaunching the application");
				waitTime(10000);
				getDriver().quit();
				relaunch = true;
				new Zee5ApplicasterBusinessLogic("zee");
				if (userType != "Guest" & true == false) {
					System.out.println("Navigates to Landing Sccreen..");
				}
			}
		}
	}

	public void verifyParental_RestrictionEvent(String userType, String restriction) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Parental_Restriction Event");
			click(AMDHomePage.MoreMenuIcon, "More Menu tab");
			waitTime(3000);
			click(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			if (restriction.equalsIgnoreCase("Age13+")) {
				click(AMDMoreMenu.objRestrict13Above, "Restrict 13+ Content option");
				click(AMDMoreMenu.objContinueBtn, "Continue Button");
				waitTime(2000);

				verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
				type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(4000);
				click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
				waitTime(2000);
				click(AMDMoreMenu.objParentalLockDone, "Done Button");
			} else if (restriction.equalsIgnoreCase("Restrict All")) {
				Swipe("UP", 1);
				click(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
				click(AMDMoreMenu.objContinueBtn, "Continue Button");
				waitTime(2000);
				verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
				type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
				hideKeyboard();
				type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(4000);
				click(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
				waitTime(2000);
				click(AMDMoreMenu.objParentalLockDone, "Done Button");
			}
			waitTime(3000);
			String pManufacturer = DeviceDetails.OEM;
			setParentalFEProperty(userType);
			mixpanel.FEProp.setProperty("Player Name", "Kaltura Android");
			mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
			mixpanel.FEProp.setProperty("brand", pManufacturer);
			mixpanel.FEProp.setProperty("Source", "ParentalControl");
			mixpanel.FEProp.setProperty("Page Name", "ParentalControl Pin");
			mixpanel.parentalSettingsValidateParameter("", "Parental_Restriction");
			Swipe("Up", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("ParentalNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("ParentalSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);
			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			click(AMDMoreMenu.objNoRestriction, "No Restriction option");
			click(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			click(AMDMoreMenu.objParentalLockDone, "Done Button");
			waitTime(3000);
		}
	}

	public void verifyAdWatchDurationevent(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("Ad Watch Duration Event of content from search page");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitForAdToFinishInAmd();
				String pManufacturer = DeviceDetails.OEM;

				String contentID = getParameterFromXML("clipContentID");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "Ad Watch Duration");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

	public void verifyAd_view_completeEvent(String usertype, String keyword4) throws Exception {
		extent.HeaderChildNode("ad_view_complete Event of content from search page");
		if (!(usertype.equalsIgnoreCase("SubscribedUser"))) {
			click(AMDSearchScreen.objSearchIcon, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, keyword4 + "\n", "Search bar");
			hideKeyboard();
			waitTime(4000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
			click(AMDSearchScreen.objSearchResultFirstContent, "Search result");
			waitTime(20000);
			boolean ad = verifyIsElementDisplayed(AMDPlayerScreen.objAd);
			if (ad == true) {
				logger.info("Ad is displayed");
				extent.extentLogger("Ad", "Ad is displayed");
				waitForAdToFinishInAmd();
				String pManufacturer = DeviceDetails.OEM;

				String contentID = getParameterFromXML("clipContentID");

				Response ContentResp = ResponseInstance.getResponseDetails(contentID);
				ResponseInstance.setFEPropertyOfContentFromAPI2(contentID, ContentResp, "Home");
				setFEProperty(usertype);
				setUserType_SubscriptionProperties(usertype);
				SetAppsflyerProperty();

				mixpanel.FEProp.setProperty("Source", "SearchPage");
				mixpanel.FEProp.setProperty("Page Name", "ConsumptionPage");
				mixpanel.FEProp.setProperty("manufacturer", pManufacturer);
				mixpanel.FEProp.setProperty("brand", pManufacturer);

				mixpanel.ValidateParameter("", "ad_view_complete");
			} else {
				logger.info("Ad is not displayed");
				extentLoggerPass("Ad", "Ad is not displayed");
			}
		} else {
			logger.info("Not Applicable for this usertype");
			extent.extentLoggerPass("", "Not Applicable for this usertype");
		}
	}

}
