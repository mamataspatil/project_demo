package com.business.zee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
//import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.mixpanelValidation.Mixpanel;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.PWAPages.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Zee5PWAWEBMixPanelBusinessLogic extends Utilities {

	public Zee5PWAWEBMixPanelBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
	}

	String URL = getParameterFromXML("url");

	private int timeout;

	/** Retry Count */
	private int retryCount;

	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	Mixpanel mixpanel = new Mixpanel();

	LocalStorage local;

	String UserType = getParameterFromXML("userType");

	String Username;
	String Password;
	
	SessionStorage session = null;

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void ZeeWEBPWAMixPanelLogin(String LoginMethod) throws Exception {
		String userType = getParameterFromXML("userType");
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			// allowPopUp();
			if (checkElementDisplayed(PWALoginPage.objCleverTapPopUp, "clever tap pop up")) {
				WebElement popup = getWebDriver().findElement(PWALoginPage.objCleverTapPopUp);
				popup.click();
			}
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			if (checkElementDisplayed(PWALoginPage.objCleverTapPopUp, "clever tap pop up")) {
				WebElement popup = getWebDriver().findElement(PWALoginPage.objCleverTapPopUp);
				popup.click();
			}
			verifyElementPresent(PWALoginPage.objWebLoginBtn, "Login button");
			JSClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Email field");
			JSClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objPasswordField, "Password Field");
			JSClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(5000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");
			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");

			if (checkElementDisplayed(PWALoginPage.objCleverTapPopUp, "clever tap pop up")) {
				WebElement popup = getWebDriver().findElement(PWALoginPage.objCleverTapPopUp);
				popup.click();
			}
			verifyElementPresent(PWALoginPage.objWebLoginBtn, "Login button");
			JSClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Email field");
			JSClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objPasswordField, "Password Field");
			JSClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "ClubUser":
			extent.HeaderChildNode("Login as Subscribed User");
			String clubUserName = getParameterFromXML("ClubUserName");
			String clubPassword = getParameterFromXML("ClubPassword");
			// allowPopUp();
			getWebDriver().findElement(By.xpath("//button[@id='wzrk-cancel']")).click();
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, clubUserName, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, clubPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
		waitTime(4000);
		selectLanguages();
		verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		JSClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		extent.version = getText(By.xpath(".//*[@class='versionText']"));
		String ver = getText(By.xpath(".//*[@class='versionText']"));
		extent.extentLogger("", ver);
		verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu");
		JSClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
	}

	public void ZeeWEBPWAMixPanelLoginForParentalControl(String LoginMethod) throws Exception {
		String userType = getParameterFromXML("userType");
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitForElementAndClickIfPresent(PWAHomePage.objNotNow, 30, "Notification popup");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			waitForElementAndClickIfPresent(PWAHomePage.objNotNow, 30, "Notification popup");
			String SUsername = getParameterFromXML("NonSubscribedUserName");
			String SPassword = getParameterFromXML("NonSubscribedUserPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");
			waitForElementAndClickIfPresent(PWAHomePage.objNotNow, 30, "Notification popup");
			String SettingsSubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SettingsSubscribedPassword = getParameterFromXML("SubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SettingsSubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SettingsSubscribedPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Dismiss the Display Language pop up
	 */
	public void dismissDisplayContentLanguagePopUp() throws Exception {
		extent.HeaderChildNode("Dismiss Display and Content Language Pop Ups");
		waitForElementAndClickIfPresent(PWAHomePage.objNotNow, 30, "Notification popup");
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 90,
				"Continue on Display Language Pop Up");
		Thread.sleep(5000);
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 10,
				"Continue on Content Language Pop Up");
	}

	/**
	 * Function to enter url
	 */
	public void enterURLInWEBBrowser(String browser, String url) {
		extent.HeaderChildNode("Enter Browser URL");
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				getWebDriver().get(url);
				extent.extentLogger("enteredURL", "Entered " + url + " in " + browser + " browser");
				logger.info("Entered " + url + " in " + browser + " browser");
			} catch (Exception e) {
				extent.extentLogger("failToEnterURL", "Failed to enter " + url + " in " + browser + " browser");
			}
		}
	}

	/**
	 * Function to select any tab
	 * 
	 */
	public boolean navigateToAnyScreenOnWeb(String screen) throws Exception {
		try {
			waitTime(8000);
			if (checkElementDisplayed(PWAHomePage.objHomeBarText(screen), screen + " Tab")) {
				click(PWAHomePage.objHomeBarText(screen), screen + " Tab");
				return true;
			} else {
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
				waitTime(2000);
				try {
					WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuTabs(screen));
					logger.info(screen + " Tab is displayed");
					extent.extentLogger("tabDisplayed", screen + " Tab is displayed");
					executor.executeScript("arguments[0].click();", tab);
					logger.info("Clicked on " + screen + " Tab");
					extent.extentLogger("tabClicked", "Clicked on " + screen + " Tab");
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return false;
	}

	/**
	 * Function to scroll down
	 */
	public static void scrollDownWEB() {
		js.executeScript("window.scrollBy(0,250)", "");
	}

	/**
	 * Waits for player loader to complete
	 * 
	 * @throws Exception
	 */
	public void waitForPlayerLoaderToComplete() throws Exception {
		// verifyElementNotPresent(PWAPlayerPage.objPlayerLoader, 60);

		new WebDriverWait(getWebDriver(), 10)
				.until(ExpectedConditions.invisibilityOfElementLocated(PWAPlayerPage.objPlayerLoader));
	}

	/**
	 * Video Player or Live Player Ad verify
	 * 
	 * @param playerType
	 * @throws Exception
	 */
	public void waitForPlayerAdToComplete(String playerType) throws Exception {
		boolean adWasDisplayed = false;
		boolean playerDisplayed = false;
		int confirmCount = 0;
		waitTime(5000);
		main: for (int trial = 0; trial < 120; trial++) {
			try {
				findElement(PWAPlayerPage.objAd);
				adWasDisplayed = true;
				if (trial == 5) {
					logger.info("Ad play in progress");
					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
					try {
						getWebDriver().findElement(PWAPlayerPage.objAd);
					} catch (Exception e) {
					}
				}
				if (Math.floorMod(trial, 15) == 0)
					System.out.println("Ad play in progress");
				Thread.sleep(1000);

//				//SkipAD
//				if(checkElementExist(PWAPlayerPage.objSkipAd, "SkipAd")){
//					Thread.sleep(5000);
//					click(PWAPlayerPage.objSkipAd, "SkipButton");					
//				}
//				else
//				{
//					System.out.println("No Skip Button Displayed");
//				}

			} catch (Exception e) {
				try {
					if (playerType.equals("Live Player")) {
						findElement(PWAPlayerPage.objLivePlayerLiveTag);
					} else if (playerType.equals("Video Player")) {
						findElement(PWAPlayerPage.objPlayerSeekBar);
					}
					playerDisplayed = true;
					confirmCount++;
					if (confirmCount == 1) {
						if (adWasDisplayed == false) {
							logger.info("Ad did not play");
							extent.extentLogger("AdDidNotPlay", "Ad did not play");
						} else {
							logger.info("Ad play complete");
							extent.extentLogger("AdPlayComplete", "Ad play complete");
						}
						break main;
					}
				} catch (Exception e1) {
				}
			}
		}
		if (playerDisplayed == false && adWasDisplayed == false) {
			logger.error("Ad play failure");
			extent.extentLogger("failedAd", "Ad play failure");
		}
	}

	/**
	 * Function Scroll to Element
	 *
	 * @param element
	 * @throws Exception
	 */
	public void ScrollToTheElementWEB(By element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	public void tearDown() {
//		local.clear();
		getWebDriver().quit();
	}

	public void navigateHome() {
		getWebDriver().get("https://newpwa.zee5.com/");
		getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void Back_TO_TopArrow_Web(String usertype) throws Exception {

		scrollToBottomOfPageWEB();
		if (usertype.equalsIgnoreCase("Guest")) {
			if (checkElementExist(PWAHomePage.objWhatWonderingPopUp, "Wondering popUp")) {
				waitTime(3000);
				click(PWAHomePage.objWhatWonderingPopUpCloseIcon, "Close icon");
			}
		}

		if (checkElementExist(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top")) {
			click(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
			System.out.println("Scrolled back to top using Back to top btn");
		}

	}

	/**
	 * The method will wait for the element to be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located. The
	 * method throws error if the element could not be located within the given
	 * seconds
	 */
	public boolean waitForElement(By locator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				getWebDriver().findElement(locator);
				logger.info("Located element " + message);
				extent.extentLogger("locatedElement", "Located element " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to locate element " + message);
					extent.extentLoggerFail("failedLocateElement", "Failed to locate element " + message);
				}
			}
		}
		return false;
	}

	/**
	 * Generic function to Logout.
	 */
	public void logout() throws Exception {
		extent.HeaderChildNode("Logout");
		click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objMyProfileOptionsWEB("Logout"), "Logout option");
		waitTime(3000);
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip Login Event");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objSkip, "Skip Login");
			waitTime(5000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginByClickingOnLoginInRegistrationPopUp(String userType, String keyword) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Skip Login Event during content playback post clicking on login in registration popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			click(PWAPremiumPage.objPlayBtn, "Watch First Episode");

			waitForElement(PWALoginPage.objLoginLink, 20, "Login Link");
			click(PWALoginPage.objLoginLink, "Login Link");
			waitTime(5000);
			waitForElement(PWALoginPage.objSkip, 10, "Skip Login");
			click(PWALoginPage.objSkip, "Skip Login");
			waitTime(5000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySkipLoginByClickingOnLoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Skip Login Event during content playback post clicking on login button in Get Premium popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword2), "Search Result");
			waitTime(2000);
			click(PWALoginPage.objLoginCTAInPremiumPopup, "Login CTA");
			waitForElement(PWALoginPage.objSkip, 20, "Skip Login");
			waitTime(5000);
			click(PWALoginPage.objSkip, "Skip Login");
			waitTime(5000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "movie_detail");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
		}
	}

	@SuppressWarnings("static-access")
	public void verifySkipRegistrationEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip Registration Event");
			click(PWALoginPage.objSignUpBtnWEB, "Sign Up For Free");
			waitTime(5000);
//			JSClick(PWALoginPage.objSkip, "Skip Registration");
			verifyElementPresentAndClick(PWALoginPage.objSkip, "Skip Registration");
			waitTime(3000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Page Name", "register");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Registartion");
		}
	}

	public void verifyRegisterScreenDisplayEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Register Screen Display Event");
			click(PWALoginPage.objSignUpBtnWEB, "Sign Up For Free");
			waitTime(3000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "register");
			System.out.println(local.getItem("guestToken"));
			
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Register Screen Display");
		}
	}

	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			waitTime(3000);
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Page Viewed");
			}
		}
	}

	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		if (userType.equalsIgnoreCase("Guest")) {
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			click(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription option");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
		}
	}

	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		if (userType.equalsIgnoreCase("Guest")) {
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			click(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code? option");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
		}
	}

	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Element", "Continue");
			mixpanel.FEProp.setProperty("Source", "home");
			String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", cost);
			mixpanel.FEProp.setProperty("Current Subscription", "false");
			mixpanel.FEProp.setProperty("isUpgrade", "false");
			
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);
			if (userType.equals("Guest")) {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Selected");
			} else {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Selected");
			}
		}
	}


	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event By selecting Club Pack");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objClubPack, "Club Pack");
			click(PWASubscriptionPages.objPackAmount1, "Pack");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Continue");
			String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", cost);
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);
			if (userType.equals("Guest")) {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Selected");
			} else {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Selected");
			}
		}
	}

	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Valid code");
		String promoCode = "prein50";
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			waitTime(2000);
			String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", cost);
			click(PWASubscriptionPages.objHaveACode, "Have A Code");
			click(PWASubscriptionPages.objEnterCode, "Enter Code");
			type(PWASubscriptionPages.objEnterCode, promoCode, "Promo Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");
			waitTime(2000);
//			if (userType.equals("Guest")) {
//				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
//					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
//					type(PWASubscriptionPages.objEmailIDTextField, "zeesub@mailnesia.com", "Email Id");
//					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage,
//							"Proceed Button");
//					// Password Popup
//					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
//					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
//					type(PWASubscriptionPages.objPasswordFieldHidden, "123456", "Password Field");
//					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
//					
//				//	mixpanel.FEProp.setProperty("Cost", "N/A");
//					ResponseInstance.getUserData("zeesub@mailnesia.com","123456");
//				}
//			}
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "APPLY");
			if (findElements(By.xpath(".//*[@class='applyPromo applyFailure']")).size() != 1) {
				mixpanel.FEProp.setProperty("Success", "true");
			} else {
				mixpanel.FEProp.setProperty("Success", "false");
			}
			
			mixpanel.FEProp.setProperty("Promo Code Type", "Product");
			mixpanel.FEProp.setProperty("Promo Code", promoCode);
			mixpanel.FEProp.setProperty("Current Subscription", "false");
			

			if (userType.equals("Guest")) {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Promo Code Result");
			} else {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("ID"), "Promo Code Result");
			}
		}
	}

	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Invalid code");
		String promocode = "sdcrfd";
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			click(PWASubscriptionPages.objHaveACode, "Have A Code");
			click(PWASubscriptionPages.objEnterCode, "Enter Code");
			type(PWASubscriptionPages.objEnterCode, promocode, "Promo Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");

//			if (userType.equals("Guest")) {
//				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
//					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
//					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
//					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage,
//							"Proceed Button");
//					// Password Popup
//					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
//					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
//					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
//					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
//				}
//			}
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "APPLY");
			mixpanel.FEProp.setProperty("Success", "false");
			String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", cost);
			mixpanel.FEProp.setProperty("Promo Code Type", "Product");
			mixpanel.FEProp.setProperty("Promo Code", promocode);
			mixpanel.FEProp.setProperty("Failure Reason", "Subscription plan couldn't be found");
			

			if (userType.equals("Guest")) {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Promo Code Result");
			} else {
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("ID"), "Promo Code Result");
			}
		}
	}


	public void verifyTVAuthenticationScreenDisplayEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify TV Authentication Screen Display Event");
			waitTime(5000);
			JSClick(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authenticate Device");
			waitTime(5000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			System.out.println(local.getItem("ID"));
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "device_authentication");
			mixpanel.FEProp.setProperty("Element", "Authenticate Device");
			mixpanel.ValidateParameter(local.getItem("ID"), "TV Authentication Screen Display");
		}
	}

	public void facebookLogin() throws Exception {
		extent.HeaderChildNode("Login through Facebook");
		getWebDriver().get(URL);
		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");

		waitForElementDisplayed(PWALoginPage.objFacebookIcon, 10);

		click(PWALoginPage.objFacebookIcon, "Facebook Icon");
		switchToWindow(2);

		if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");

		}

		else {
			checkElementDisplayed(PWALoginPage.objFacebookPageVerificationWeb, "Facebook page");
			verifyElementPresent(PWALoginPage.objFacebookLoginEmailWeb, " Email Field");
			type(PWALoginPage.objFacebookLoginEmailWeb, "igstesttt@gmail.com", "Emial Field");
			verifyElementPresent(PWALoginPage.objFacebookLoginpasswordWeb, " Password Field");
			type(PWALoginPage.objFacebookLoginpasswordWeb, "Igs123!@#", "Password Field");
			verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPageWeb, "Login Button");

			if (checkElementDisplayed(PWALoginPage.objOKBtnInFbPage, "Would you like to Continue popup")) {
				click(PWALoginPage.objOKBtnInFbPage, "OK Button");
			}
			switchToParentWindow();
			waitTime(5000);
		}
//		logout();
	}

	public void phoneNumberRegistration() throws Exception {
		extent.HeaderChildNode(
				"verifing that user is able to enter Mobile number, Password, date of birth, gender in Registration page");
		click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
		waitForElementDisplayed(PWALoginPage.objEmailField, 5);
		click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
		type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
		click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
		type(PWASignupPage.objOTP1, "1", "OTP box1");
		type(PWASignupPage.objOTP2, "2", "OTP box2");
		type(PWASignupPage.objOTP3, "3", "OTP box3");
		type(PWASignupPage.objOTP4, "4", "OTP box4");
		waitTime(3000);
		verifyElementPresentAndClick(PWASignupPage.objVerifyBtnWeb, "Verified Button");

	}

	public void twitterLogin() throws Exception {
		extent.HeaderChildNode("Login through Twitter");

		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");
		waitForElementDisplayed(PWALoginPage.objLoginPageheader, 10);

		waitForElementDisplayed(PWALoginPage.objTwitterIcon, 10);
		checkElementDisplayed(PWALoginPage.objTwitterIcon, "Twitter icon");
		waitTime(1000);

		click(PWALoginPage.objTwitterIcon, "twitter Icon");
		switchToWindow(2);

		if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");

		}

		else {
			verifyElementPresent(PWALoginPage.objTwitterEmaildField, " Email Field");
			type(PWALoginPage.objTwitterEmaildField, "zee5latest@gmail.com", "Email Field");

			verifyElementPresent(PWALoginPage.objTwitterPasswordField, " Password Field");
			type(PWALoginPage.objTwitterPasswordField, "User@123", "Password Field");

			verifyElementPresentAndClick(PWALoginPage.objTwitterSignInButton, "Login Button");
			switchToParentWindow();

		}

	}

	public void socialLogin(String LoginMethod) throws Exception {
		switch (LoginMethod) {

		case "twitter":
			twitterLogin();
			waitTime(3000);
			break;

		case "facebook":
			facebookLogin();
			waitTime(3000);
			break;

		case "emailLogin":
			String Username = getParameterFromXML("NonSubscribedUserName");
			String Password = getParameterFromXML("NonSubscribedUserPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			ResponseInstance.getUserData(Username, Password);
			ResponseInstance.getUserSettingsDetails(Username, Password);
			mixpanel.NonSubcribedDetails();
			break;

		}
	}

	public void verifyLoginInitiatedEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event for Valid Credentials");
			socialLogin(loginMethod);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Login Initiated");
		}
	}

	public void verifyLoginResultEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event for Valid Credentials");
			socialLogin(loginMethod);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.FEProp.setProperty("Success", "true");
			mixpanel.FEProp.setProperty("Element", "Login");
			mixpanel.FEProp.setProperty("Method", "Email");
			
			mixpanel.ValidateParameter(local.getItem("ID"), "Login Result");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event By Clicking On Login Button");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(5000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event By Clicking On Login Button On Player");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword2), "Search Result");

			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			verifyElementPresent(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
			JSClick(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");

			waitTime(5000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);

			mixpanel.FEProp.setProperty("Source", "movie_detail");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
			click(PWALoginPage.objSignUpBtnWEB, "Sign Up For Free");
			JSClick(PWALoginPage.objLoginLink, "Login link");

			waitTime(5000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	@SuppressWarnings("static-access")
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword2), "Search Result");
//			mandatoryRegistrationPopUp(userType);
//			waitForPlayerAdToComplete("Video Player");
			waitTime(5000);

			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				// ScrollToTheElementWEB(PWALoginPage.objLoginCTAInPremiumPopup);
				verifyElementPresentAndClick(PWALoginPage.objLoginCTAInPremiumPopup, "Login link");

			}
			waitTime(5000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "movie_detail");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	public void verifyCarouselBannerClickEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Carousel Banner Click Event in Tab : "+tabName);
		int carouselDot=0;
		navigateToHome();
		selectLanguages();
		navigateToAnyScreenOnWeb(tabName);	
		waitTime(5000);
		mandatoryRegistrationPopUp(UserType);		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		String contentLang=fetchContentLanguage(local);		
		String zeeTab = getWebDriver().getWindowHandle();
		Set<String> handlesBeforeClick = getWebDriver().getWindowHandles();
		click(PWAHomePage.objPromotionalBannerCarouselDots(carouselDot + 1), "Carousel Dot");
		JSClick(PWAHomePage.objCarouselCardForClick(carouselDot),"Carousel Card");
		waitTime(4000);						
		String page=pageNameForCarousel(tabName);		
		String source=getElementPropertyToString("innerText",PWAHomePage.objBreadCrumb(1),"");
		if(page.equalsIgnoreCase(source)) source="N/A";		
		mixpanel.FEProp.setProperty("Page Name",page);
		mixpanel.FEProp.setProperty("Source", source);		
		ResponseInstance.getContentDetailsForCarouselClick(tabName,carouselDot,contentLang);		
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameterForCarouselClick(local.getItem("guestToken"), "Carousal Banner Click",contentLang);
		} else {
			mixpanel.ValidateParameterForCarouselClick(local.getItem("ID"), "Carousal Banner Click",contentLang);
		}
		Set<String> handlesAfterClick = getWebDriver().getWindowHandles();
		if (handlesAfterClick.size() > handlesBeforeClick.size()) {
			String externalTab = "";
			for (String winHandle : getWebDriver().getWindowHandles()) {
				if (!winHandle.equals(zeeTab)) getWebDriver().switchTo().window(winHandle).close();
			}
			getWebDriver().switchTo().window(zeeTab);
		}
	}
	
	public String pageNameForCarousel(String tab) throws Exception {
		if(tab.equalsIgnoreCase("Home")) return "home";
		else if(tab.equalsIgnoreCase("TV Shows")) return "tv_shows_view_all";
		else if(tab.equalsIgnoreCase("Movies")) return "movie_landing";
		else if(tab.equalsIgnoreCase("Web Series")) return "zee_originals_view all";
		else if(tab.equalsIgnoreCase("News")) return "news_landing";
		else if(tab.equalsIgnoreCase("Premium")) return "premium";
		else if(tab.equalsIgnoreCase("Play")) return "play_landing";
		else if(tab.equalsIgnoreCase("Kids")) return "kids_landing";
		else if(tab.equalsIgnoreCase("Videos")) return "video_landing";
		else return "N/A";
	}
	
	public String fetchContentLanguage(LocalStorage local) {
		String contentLangFromSetting="";
		try {
			Properties KEYVALUE = new Properties();
			for (String key : local.keySet()) {
				KEYVALUE.setProperty(key, local.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.equals("settings")) {
					JSONObject jsonObj = new JSONObject(KEYVALUE.getProperty(key));
					contentLangFromSetting=jsonObj.get("content_language").toString();
					break;
				}
			}
		} catch (Exception e) {}
		return contentLangFromSetting;
	}

	public void verifyThumbnailClickEventFromTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objThumbnail, "Thumbnail from a tray");

		waitTime(2000);
		
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", tabName);
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", "Play");
		
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}
	}

	public void verifyThumbnailClickEventFromViewMorePage(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objViewAllBtn, "View All Button");
		verifyElementPresentAndClick(PWAPremiumPage.objShowThumbnail, "Thumbnail from View More Page");
		waitTime(4000);
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "tv_shows_view_all");
		mixpanel.FEProp.setProperty("Page Name", "view_all_latest-kannada-episodes");
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}
	}

	public void verifyThumbnailClickEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event From Show Detail Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

		verifyElementPresentAndClick(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail from Show detail page");
		waitTime(4000);
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}
	}

	public void verifyThumbnailClickEventFromPlaybackPage(String keyword, String userType) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event From Playback Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

		click(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail");
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail from playback page");
		waitTime(5000);
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "show_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Thumbnail Click");
		}

	}

	public void verifySearchExecutedEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Executed Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		String searchtxt = "Kam";
		type(PWASearchPage.objSearchEditBox, searchtxt + "\n", "Search Edit box: ");
		waitTime(10000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Search Type", "text");
//		mixpanel.FEProp.setProperty("Results Returned", ResponseInstance.getresponse(searchtxt));
		mixpanel.FEProp.setProperty("Search Query", searchtxt);
		mixpanel.FEProp.setProperty("Search Success", "true");
		mixpanel.FEProp.setProperty("Page Name", "search");
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Executed");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Search Executed");
		}
	}

	public void verifyScreenViewEvent(String tab) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tab);
		waitTime(5000);
		
		if (userType.equals("Guest")) {
			if (tab.equalsIgnoreCase("Home")) {
				mixpanel.FEProp.setProperty("Page Name", "Content Language");
				mixpanel.FEProp.setProperty("Source", "N/A");
			}else {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "home");
			}
		}else {
			if (tab.equalsIgnoreCase("Home")) {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "N/A");
			}else {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "home");
			}
		}
		
//		mixpanel.FEProp.setProperty("Source", "home");
//		mixpanel.FEProp.setProperty("Page Name", "tv_shows_view_all");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Screen View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Screen View");
		}
	}

	public void clearSearchHistoryEvent(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Clear Search History Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitTime(5000);
		navigateToAnyScreenOnWeb("Home");
		waitTime(3000);
		click(PWAHomePage.objSearchBtn, "Search Icon");
		waitTime(5000);
		if (verifyIsElementDisplayed(PWASearchPage.objClearAll, "Clear Search Icon")) {
			click(PWASearchPage.objClearAll, "Clear Search Icon");
			waitTime(8000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "search");
			mixpanel.FEProp.setProperty("Setting Changed", "clear search history");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				System.out.println(local.getItem("guestToken"));
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Clear Search History");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Clear Search History");
			}
		} else {
			logger.info("Clear Search is not available");
			extent.extentLogger("Clear Search", "Clear Search is not available");
		}
	}



public void verifyParentalRestrictionEvent(String userType, String restriction) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Parental Restriction Event");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("NonSubscribedUserPassword");
				
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SubscribedPassword");
				
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			click(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restriction option");
			switch (restriction) {

			case "Age13+":
				click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict 13+ content");
				click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
				type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
				waitTime(4000);
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(3000);
				
				
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Parent Control Setting", "U/A");
				mixpanel.FEProp.setProperty("Setting Changed", "U/A");
				mixpanel.FEProp.setProperty("Page Name", "parental_control");

				break;

			case "RestrictAll":
				click(PWAHamburgerMenuPage.objRestrictAll, "Restrict all option");
				click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
				type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
				waitTime(4000);
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(3000);
				
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Parent Control Setting", "U");
				mixpanel.FEProp.setProperty("Setting Changed", "U");
				mixpanel.FEProp.setProperty("Page Name", "parental_control");
				break;

			case "NoRestriction":
				click(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restriction option");
				click(PWAHamburgerMenuPage.objContinueButton, "Continue Button");
				
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Parent Control Setting", "A");
				mixpanel.FEProp.setProperty("Setting Changed", "A");
				mixpanel.FEProp.setProperty("Page Name", "parental_control");
				
				break;
			}

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);

//			if (userType.equals("NonSubscribedUser")) {
//				
//				ResponseInstance.getUserData("zeetest@gmail.com","zee123");
//				ResponseInstance.getUserSettingsValues("zeetest@gmail.com","zee123");
//			
//			} else if (userType.equals("SubscribedUser")) {
//				
//				ResponseInstance.getUserData("zeein7@mailnesia.com","123456");
//				ResponseInstance.getUserSettingsValues("zeein7@mailnesia.com","123456");
//			}
//			mixpanel.parentControl = true;
			mixpanel.ValidateParameter(local.getItem("ID"), "Parental_Restriction");
			HeaderChildNode("Remove Parent control");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("NonSubscribedUserPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SubscribedPassword");
				
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			waitTime(2000);
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			click(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restriction option");
			click(PWAHamburgerMenuPage.objContinueButton, "Continue Button");
		}
	}

	public void verifyShareEventFromPlaybackPage(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Share Event From Playback Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		mandatoryRegistrationPopUp(userType);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		if (UserType.equals("Guest")) {
			if (checkElementDisplayed(PWAPlayerPage.objWhyRegisterPopUp, "Create New Account Popup") == true) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Create New Account Popup close button");
			}
		}
		ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
		click(PWAPlayerPage.shareBtn, "Share Option");
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");
		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			click(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(20000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifyRemoveFomWatchlistEventFromPlaybackPage(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove From Watchlist Event From Playback Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
			ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
			if (checkElementDisplayed(PWAPlayerPage.objPlaybackAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objPlaybackAddToWatchlist, "Watchlist icon");
				waitTime(4000);
				click(PWAPlayerPage.objPlaybackRemoveFromWatchlist, "Remove From Watchlist icon");
			} else {
				click(PWAPlayerPage.objPlaybackRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(4000);
			}
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Remove From Watchlist");
		}
	}

	public void verifyAddtoWatchlistFromPlaybackPage(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event From Playback Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");

			if (checkElementDisplayed(PWAPlayerPage.objPlaybackAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objPlaybackAddToWatchlist, "Watchlist icon");
			} else {
				click(PWAPlayerPage.objPlaybackRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(4000);
				click(PWAPlayerPage.objPlaybackAddToWatchlist, "Add To Watchlist icon");
				waitTime(4000);
			}
			waitTime(4000);
			
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Add To Watchlist");
		}
	}

	public void verifyAddToWatchlistEventByMouseHover(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event by mouse hovering on a Content Card");
			waitTime(5000);
			navigateToAnyScreenOnWeb("Movies");
			waitTime(5000);
	
			scrollDownWEB();
			String link = findElement(PWAPremiumPage.obj1stContentInViewAllPage).getAttribute("data-minutelyurl");
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon")) {
				verifyElementPresentAndClick(PWAPlayerPage.objAddToWatchlist, "Watchlist icon");
			} else {
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				verifyElementPresentAndClick(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon");
				waitTime(4000);
			}
			waitTime(4000);
			ResponseInstance.getContentDetails(fetchContentID(link));
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_landing");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Add To Watchlist");
		}

	}

	public void verifyRemoveFomWatchlistEventByMouseHoverInShowDetailPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Remove from Watchlist Event by mouse hovering on a Content Card In show detail page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			String link = findElement(PWAPremiumPage.obj1stContentInShowDetailPage).getAttribute("data-minutelyurl");
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon")) {
				click(PWAPlayerPage.objAddToWatchlist, "Watchlist icon");
				waitTime(4000);
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
			} else {
				click(PWAPlayerPage.objRemoveFromWatchlist, "Remove From Watchlist icon");
				waitTime(3000);
				
				waitTime(4000);
			}
			waitTime(4000);
			ResponseInstance.getContentDetails(fetchContentID(link));
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "show_detail");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Remove From Watchlist");

		}

	}

	public void verifyShareEventByMouseHover(String tabName) throws Exception {

		extent.HeaderChildNode("Verify Share Event By Mouse Hovering on a Content Card");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		String link = findElement(By.xpath("(//div[@class='movieCard card marginRight minutelyUrl zoomCardHover'])[1]"))
				.getAttribute("data-minutelyurl");
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);

		actions.moveToElement(contentCard).build().perform();
		verifyElementPresentAndClick(PWAPremiumPage.objContentCardShareBtn, "Share icon");

		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");

		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");

			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(20000);
		ResponseInstance.getContentDetails(fetchContentID(link));
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_landing");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifySearchCancelledEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Cancelled Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		click(PWASearchPage.objSearchCancel, "Close Button");
		waitTime(10000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "search");
		mixpanel.FEProp.setProperty("Results Returned", "0");
		mixpanel.FEProp.setProperty("Search Query", "N/A");
		mixpanel.FEProp.setProperty("Search Type", "N/A");
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Cancelled");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Search Cancelled");
		}
	}

	public void verifyShareEventFromShowDetailPage(String keyword) throws Exception {

		extent.HeaderChildNode("Verify Share Event From Show Detail Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

		click(PWAPlayerPage.shareBtn, "Share Option");
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");

		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");

			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		//waitTime(5000);
		getWebDriver().close();
		switchToWindow(1);
		waitTime(20000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifyEpisodeListChosenEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Episode List Chosen Event in Show Detail page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
		waitTime(4000);
		scrollDownWEB();
		click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		click(PWAShowsPage.objShowDetailEpisodeDropdownValues(2), "Episodes 11-20");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Element", "Episodes 11 - 20");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		mixpanel.FEProp.setProperty("Series", "Jodi Hakki");
		
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetailsForNews(fetchContentID(id));
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Episode list Chosen");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Episode list Chosen");
		}
	}

	public void verifyContentBucketSwipeEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event in Show Detail page");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
		
		waitTime(3000);
		scrollDownWEB();
		waitTime(2000);
		scrollDownWEB();
		waitTime(2000);
		click(PWAPremiumPage.objRightArrowBtn, "Right Arrow Button");
//		String TrayTitle = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]")).getText();
//		String link = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]"))
//				.getAttribute("href");
//		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
//		Matcher m = p.matcher(link);
//		String value = null;
//		while (m.find()) {
//			value = m.group(0);
//		}
//		mixpanel.FEProp.setProperty("Carousal ID", value);
//		mixpanel.FEProp.setProperty("Carousal Name", TrayTitle);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
		}

	}

	public void verifyViewMoreSelectedEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Show detail page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
		waitTime(4000);
		scrollDownWEB();
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");
		waitTime(5000);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Element", "View All");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "View More Selected");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "View More Selected");
		}
	}

	public void verifyViewMoreSelectedEventFromPlaybackPage(String audioTrackContent, String userType)
			throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Playback page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		mandatoryRegistrationPopUp(userType);
		waitTime(4000);
		click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
		waitTime(4000);
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");
		waitTime(5000);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Element", "View All");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		
		
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "View More Selected");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "View More Selected");
		}
	}

	public void verifyViewMoreSelectedEventFromTray(String tab) throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Tray");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tab);
		waitTime(5000);
		
		if (userType.equals("Guest")) {
			if (tab.equalsIgnoreCase("Home")) {
				mixpanel.FEProp.setProperty("Page Name", "Content Language");
				mixpanel.FEProp.setProperty("Source", "N/A");
			}else {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "Content Language");
			}
		}else {
			if (tab.equalsIgnoreCase("Home")) {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "N/A");
			}else {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "home");
			}
		}

		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		
		mixpanel.FEProp.setProperty("Element", "View All");
		
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "View More Selected");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "View More Selected");
		}
	}

	public void verifyRemoveFromWatchlistEventFromMyWatchlistPage(String userType, String keyword) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove From Watchlist Event for Content from My Watchlist page");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			waitTime(5000);
			String link = findElement(PWAPremiumPage.obj1stContentInShowDetailPage).getAttribute("data-minutelyurl");
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			waitTime(4000);

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			verifyElementPresentAndClick(PWAAddToWatchListPage.objCancelBtn,
					"Remove From Watchlist option");
			waitTime(3000);
	
			mixpanel.FEProp.setProperty("Source", "show_detail");
			mixpanel.FEProp.setProperty("Page Name", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Remove From Watchlist");
		}
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		waitForElementAndClickIfPresent(PWAHomePage.objNotNow, 30, "Notification popup");
//		getWebDriver().quit();
//		relaunch = clearData;
//		new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.ValidateParameter(fetchMpData(local), "Session");
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch() throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getWebDriver().quit();
		new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
//		getWebDriver().navigate().refresh();
		ZeeWEBPWAMixPanelLogin(userType);
	}

	public void verifyQualityChangeEvent(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Quality Change Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitTime(5000);
		waitForPlayerAdToComplete("Video PLayer");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(1), "Quality option");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Source", "search");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}

	}

	public void verifyContentBucketSwipeEventInPlaybackPage(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Content Bucket Swipe Event in Playback page");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitTime(5000);
		scrollDownWEB();
		waitTime(3000);
		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
//		String TrayTitle = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]")).getText();
//		String link = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]"))
//				.getAttribute("href");
//		Pattern p = Pattern.compile("[0-9]-[0-9]-[0-9]+");
//		Matcher m = p.matcher(link);
//		String value = null;
//		while (m.find()) {
//			value = m.group(0);
//		}
//		mixpanel.FEProp.setProperty("Carousal ID", value);
//		mixpanel.FEProp.setProperty("Carousal Name", TrayTitle);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
		}
	}

	public void verifyDisplayLanguageChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify Display Language Change Event");
		verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
		JSClick(PWALanguageSettingsPage.objFirstLanguage, "Hindi display language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objAllLangByindex(1), "Hindi content language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		waitTime(5000);
//		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		oldLanguage("New Content Language");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("New App Language", "hi");
		mixpanel.FEProp.setProperty("Old App Language", "en");
		 
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.Language = false;
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Display Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Display Language Change");
		}

		click(PWAHomePage.objLanguageBtn, "Language button");
		JSClick(PWALanguageSettingsPage.objSecondLanguage, "English display language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		waitTime(2000);
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
	}

//	public void verifyContentLanguageChangeEvent() throws Exception {
//		extent.HeaderChildNode("Verify Content Language Change Event");
//
//		verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
//		JSClick(PWALanguageSettingsPage.objSecondLanguage, "English display language");
//
//		if (userType.equals("Guest")) {
//			mixpanel.FEProp.setProperty("Old Content Language:",
//					ResponseInstance.getUserOldSettingsDetails("", "").getProperty("content_language"));
//		} else {
//			mixpanel.FEProp.setProperty("Old Content Language:",
//					ResponseInstance.getUserOldSettingsDetails(Username, Password).getProperty("content_language"));
//		}
//		waitTime(2000);
//		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
//		waitTime(2000);
//		JSClick(PWALanguageSettingsPage.objAllLangByindex(1), "Hindi content language");
//		waitTime(2000);
//		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
//		waitTime(3000);
//		local = ((ChromeDriver) getWebDriver()).getLocalStorage();fetchUserType(local);
//		if (userType.equals("Guest")) {
//			System.out.println(local.getItem("guestToken"));
//			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Language Change");
//		} else {
//			mixpanel.ValidateParameter(local.getItem("ID"), "Content Language Change");
//		}
//	}

	public void verifyContentLanguageChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify Content Language Change Event");

		verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
		if (userType.equals("Guest")) {
			oldLanguage("Old Content Language");
		} else {
			mixpanel.FEProp.setProperty("Old Content Language:",
					ResponseInstance.getUserOldSettingsDetails(Username, Password).getProperty("content_language"));
		}
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objAllLangByindex(1), "Hindi content language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		waitTime(8000);
		oldLanguage("New Content Language");
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.Language = false;
		if (userType.equals("Guest")) {
			System.out.println(local.getItem("guestToken"));
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Language Change");
		}
		
	}

	public void verifyContentBucketSwipeEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event Across tabs");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
//		String TrayTitle = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]")).getText();
//		String link = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]"))
//				.getAttribute("href");
//		Pattern p = Pattern.compile("\\/([^\\/]+)\\/?$");
//		Matcher m = p.matcher(link);
//		String value = null;
//		while (m.find()) {
//			value = m.group(0);
//		}
//		mixpanel.FEProp.setProperty("Carousal ID", value.replace("/", ""));
//		mixpanel.FEProp.setProperty("Carousal Name", TrayTitle);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", landingPageName());
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
		}
	}
	
	public void verifyContentBucketSwipeEventLeft(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event Across tabs");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
		
		click(PWAPremiumPage.objPreviousArrowBtn, "Left Arrow Button");
		
//		String TrayTitle = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]")).getText();
//		String link = findElement(By.xpath("(.//*[@class='trayHeader']//*[@class='titleLink'])[1]"))
//				.getAttribute("href");
//		Pattern p = Pattern.compile("\\/([^\\/]+)\\/?$");
//		Matcher m = p.matcher(link);
//		String value = null;
//		while (m.find()) {
//			value = m.group(0);
//		}
//		mixpanel.FEProp.setProperty("Carousal ID", value.replace("/", ""));
//		mixpanel.FEProp.setProperty("Carousal Name", TrayTitle);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name",landingPageName());
		mixpanel.FEProp.setProperty("Element", "left-arrow");
		mixpanel.FEProp.setProperty("Direction", "Left");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
		}
	}


	public void verifyCarouselBannerSwipeEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Carousel Banner Swipe Event Across tabs");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objRightArrowBtn, "Right Arrow Button");
		
		waitTime(5000);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "tv_shows_view_all");
		mixpanel.FEProp.setProperty("Element", "right-arrow");
		mixpanel.FEProp.setProperty("Direction", "Right");
		
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Carousal Banner Swipe");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Carousal Banner Swipe");
		}

	}

	public void verifyAdBannerImpressionEvent(String tab) throws Exception {
		extent.HeaderChildNode("Verify Ad Banner Impression Event Across tabs");
		navigateToAnyScreenOnWeb(tab);
		waitTime(5000);
		
		if (userType.equals("Guest")) {
			if (tab.equalsIgnoreCase("Home")) {
				mixpanel.FEProp.setProperty("Page Name", "Content Language");
				mixpanel.FEProp.setProperty("Source", "N/A");
			}else {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "home");
			}
		}else {
			if (tab.equalsIgnoreCase("Home")) {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "N/A");
			}else {
				mixpanel.FEProp.setProperty("Page Name", landingPageName());
				mixpanel.FEProp.setProperty("Source", "home");
			}
		}
		
		checkElementDisplayed(PWAHomePage.objAdBanner, "Ad Banner");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Banner Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Ad Banner Impression");
		}
	}

	public void verifyDefaultSettingRestoredEvent() throws Exception {
		extent.HeaderChildNode("Verify Default Setting Restored Event");
		waitTime(5000);
		click(PWAHomePage.objHamburgerMenu, "Hamburger menu");
		click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");
		waitTime(5000);
		click(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "settings");
		mixpanel.FEProp.setProperty("Setting Changed", "Default Setting Restored");
		mixpanel.FEProp.setProperty("Element", "Reset Settings to default");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Default Setting Restored");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Default Setting Restored");
		}
	}

	public void FilterLanguage(String lang) throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language");
		}
		click(PWALiveTVPage.objSelectLang(lang), lang + " language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
	}

	public void verifyShareEventForUpcomingProgram() throws Exception {
		extent.HeaderChildNode("Verify Share Event For Upcoming Program");
		navigateToAnyScreenOnWeb("Live TV");

		waitTime(5000);
		wouldYouLikeToPopupClose();
		click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage("Bengali");
		click(PWALiveTVPage.objBanglaShow1, "Show detail");

		click(PWAPremiumPage.objContentCardShareBtn, "Share button");
		waitTime(3000);
		click(PWALiveTVPage.objFacebookShareBtn, "Share to Facebook");
		waitTime(3000);
		verifyAlert();
		switchToWindow(2);
		if (!checkElementDisplayed(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook")) {
			click(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(4000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
//		waitTime(7000);
//		acceptAlert();
//		waitTime(3000);
//		switchToParentWindow();
		getWebDriver().close();
		switchToWindow(1);
		waitTime(20000);
		mixpanel.FEProp.setProperty("Source", "live_tv");
		mixpanel.FEProp.setProperty("Page Name", "tv_guide");
		mixpanel.FEProp.setProperty("Sharing Platform", "Facebook");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Share");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Share");
		}
	}

	public void verifySetReminderEventForUpcomingProgram(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Set Reminder Event");
			navigateToAnyScreenOnWeb("Live TV");
			wouldYouLikeToPopupClose();
			click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
			click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
			FilterLanguage("Bengali");
			click(PWALiveTVPage.objBanglaShow1, "Show detail");

			if (checkElementDisplayed(PWALiveTVPage.objSetReminder, "Reminder")) {
				click(PWALiveTVPage.objSetReminder, "Reminder option");
				waitTime(3000);
			} else if (checkElementDisplayed(PWALiveTVPage.objSetReminderOn, "Reminder")){
				click(PWALiveTVPage.objSetReminderOn, "Reminder option");
				waitTime(3000);
				click(PWALiveTVPage.objSetReminder, "Reminder option");
			}else {
				logger.info("Reminder is not available for the content");
				extent.extentLogger("Reminder", "Reminder is not available for the content");
			}
			
			mixpanel.FEProp.setProperty("Source", "live_tv");
			mixpanel.FEProp.setProperty("Page Name", "tv_guide");
			mixpanel.FEProp.setProperty("Element", "Set Reminder");
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Set Reminder");
		}

	}

	public void wouldYouLikeToPopupClose() throws Exception {
		if (checkElementDisplayed(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
	}

	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Change Password Started Event");waitTime(2000);
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igsindia123", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Update");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Change Password Started");
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igsindia123", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igsindia123", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(8000);
			
		}
	}

	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Change Password Result Event");
			waitTime(2000);
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			waitTime(2000);
			String password ;
			if(userType.equals("NonSubscribedUser")) {
					password = getParameterFromXML("NonSubscribedUserPassword");
			}else {
				password = getParameterFromXML("SubscribedPassword");
			}
			
			JSClick(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, password, "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Update");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			mixpanel.FEProp.setProperty("Success", "true");
			
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Change Password Result");
			JSClick(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, password, "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, password, "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(8000);
			
		}
	}

	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Profile Update Result Event");
			waitTime(5000);
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
			verifyElementPresent(PWAHamburgerMenuPage.objEditProfileTextWEB, "edit profile page");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
			clearField(PWAHamburgerMenuPage.objEditProfileFirstName, "email field");
			type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Editprofile first name");

			verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "save changes");
			waitTime(2000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			mixpanel.FEProp.setProperty("Partner Name", "Zee5");
			mixpanel.ValidateParameter(local.getItem("ID"), "Profile Update Result");

		}
	}

	public void verifyBannerAutoplayEventForNewsContent() throws Exception {
		extent.HeaderChildNode("Verify Banner Autoplay Event For News Content");
		mandatoryRegistrationPopUp(userType);
		navigateToAnyScreenOnWeb("News");
		if (verifyAutoPlay("News")) {
			logger.info("Unmute button is displayed, news is autoplaying");
			extent.extentLogger("", "Unmute button is displayed, news is autoplaying");
			waitTime(15000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "news_landing");
			mixpanel.FEProp.setProperty("Page Name", "news_landing");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Video Autoplay", "true");
			mixpanel.FEProp.setProperty("Tab Name", "news_landing");
			String id = findElement(PWANewsPage.objAutoPlayContent).getAttribute("href");
			ResponseInstance.getContentDetailsForNews(fetchContentID(id));
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Banner Autoplay");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Banner Autoplay");
			}
		} else {
			logger.info("No autoplaying news content");
			extent.extentLogger("", "No autoplaying news content");
		}
		mandatoryRegistrationPopUp(userType);

	}

	public void verifyMuteChangedEventForNewsContent() throws Exception {
		extent.HeaderChildNode("Verify Mute Changed Event");
		navigateToAnyScreenOnWeb("News");
		waitForElementDisplayed(PWANewsPage.objVolume, 30);
		waitTime(6000);
		JSClick(PWANewsPage.objVolume, "Mute Icon");
		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Element", "Mute");
		mixpanel.FEProp.setProperty("Page Name", "news_landing");
		mixpanel.FEProp.setProperty("Tab Name", "news_landing");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Mute Changed");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Mute Changed");
		}

	}

	public void verifyResumeEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Free Content");
		
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");
		waitTime(6000);
		
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Resume Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");

			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		
			mixpanel.FEProp.setProperty("Element", "Resume");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
			}

		}
	}

	public void verifyResumeEventForTrailer(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		
		waitTime(6000);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Page Name", pageName());
		
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Carousel Content");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Tray");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Resume Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Resume");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
			}

		}
	}

	public void verifyResumeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}

	public void verifyResumeEventForContentInPlaylist(String userType, String tab) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Playlist");

		navigateToAnyScreenOnWeb(tab);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);

		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}

	}
	
	
	public void verifyVideoViewEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video View Event for content autoplayed from Upnext rail");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "movie_detail");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);
	}

	
	public void verifyVideoViewEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();
//		String value = null;
//		String[] splits=id.split("/");
//		value=splits[splits.length-1];
//		System.out.println(value);

		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);
	}



public void verifyVideoViewEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Free Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);

	}

	public void verifyVideoViewEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video View Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Video View", "1");
			
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
			}
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoViewEventForTrailer(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoViewEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Carousel Content");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);
	}


public void verifyVideoViewEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Tray");
	
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);

	}

	public void verifyVideoViewEventForContentFromSearchPage(String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content From Search Page");
		
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 10, "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);

	}

	public void verifyVideoViewEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Video View Event For Content From My Watchlist Page");
			mandatoryRegistrationPopUp(userType);
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Video View", "1");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
			}
			mandatoryRegistrationPopUp(userType);
		}
	}

	public void verifyVideoViewEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Megamenu");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);
	}


public void verifyVideoViewEventForContentInPlaylist(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Playlist");
		
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(10000);

		
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoViewEventAfterRefreshingPage(String tab) throws Exception {
		extent.HeaderChildNode("Verify Video View Event after refreshing a page");
		mandatoryRegistrationPopUp(userType);
		navigateToAnyScreenOnWeb(tab);
		clickOnTrayContent(tab,"Free");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		getWebDriver().navigate().refresh();
		logger.info("Refreshed the page");
		extent.extentLogger("", "Refreshed the page");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Video View", "1");

		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video View");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video View");
		}
		mandatoryRegistrationPopUp(userType);
	}

	
	public void verifyVideoExitEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Free Content");

		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoExitEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video Exit Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
			}
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoExitEventForTrailer(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		
		waitTime(6000);
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		
		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoExitEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Carousel Content");
		mandatoryRegistrationPopUp(userType);
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();

		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoExitEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Tray");
		mandatoryRegistrationPopUp(userType);
		navigateToAnyScreenOnWeb(tabName);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoExitEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content From Search Page");
		mandatoryRegistrationPopUp(userType);
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoExitEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Video Exit Event For Content From My Watchlist Page");
			mandatoryRegistrationPopUp(userType);
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
			}
			mandatoryRegistrationPopUp(userType);
		}
	}

	public void verifyVideoExitEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Megamenu");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);

	}

	public void verifyVideoExitEventForContentInPlaylist(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Playlist");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		Back(1);
		waitTime(10000);
		
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
	}

public void verifyVideoExitEventAfterRefreshingPage(String tab) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event after refreshing a page");
		mandatoryRegistrationPopUp(userType);
		navigateToAnyScreenOnWeb(tab);
		clickOnTrayContent(tab,"Free");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		getWebDriver().navigate().refresh();
		logger.info("Refreshed the page");
		extent.extentLogger("", "Refreshed the page");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}

public void verifyVideoExitEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event for content autoplayed from Upnext rail");
		
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
	
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		logger.info("Scrubbed till end");
		extent.extentLogger("", "Scrubbed till end");
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		mandatoryRegistrationPopUp(userType);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		navigateToHome();
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}
public void verifyVideoExitEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		navigateToHome();
		logger.info("Video exited by navigating Home");
		extent.extentLogger("", "Video exited by navigating Home");

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Exit");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Exit");
		}
		mandatoryRegistrationPopUp(userType);
	}



	public void verifyVideoWatchDurationEventForFreeContentAbrupt(String userType, String tab) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Free Content");
		
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tab,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
				
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
	
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForPremiumContentAbrupt(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when video is closed abruptly For Premium Content");
			
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForTrailerAbrupt(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Trailer Content");

		clickOnTrayContent(tabName,"trailer");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		mixpanel.FEProp.setProperty("Page Name", pageName());
		
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForCarouselContentAbrupt(String tab) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Carousel Content");
		mandatoryRegistrationPopUp(userType);
		navigateToAnyScreenOnWeb(tab);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentInTrayAbrupt(String tabName) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Tray");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());

		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(String keyword1) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content From Search Page");
		mandatoryRegistrationPopUp(userType);
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);

	}

	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when video is closed abruptly For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			Back(1);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
	}

	public void verifyVideoWatchDurationEventForContentInMegamenuAbrupt() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Megamenu");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();
		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentInPlaylistAbrupt(String userType, String tab)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Playlist");
		
		navigateToAnyScreenOnWeb(tab);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword2), "Search Result");
			checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP");
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Popup launch");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Popup launch");
			}
		}
	}

	public void verifyPauseEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Free Content");
		

		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Pause Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

			
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Pause");
			mixpanel.FEProp.setProperty("Button Type", "Player");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
			}
		}
	}

	public void verifyPauseEventForTrailer(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		
		waitTime(6000);
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Carousel Content");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Carousel", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Pause");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
			}
		}
	}

	public void verifyPauseEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Tray");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		waitTime(5000);
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Carousel", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");
			mixpanel.FEProp.setProperty("Source", "movie_landing");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Pause");
			mixpanel.FEProp.setProperty("Button Type", "Player");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
			}
		}
	}

	public void verifyPauseEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Pause Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			waitTime(5000);

			scrollDownWEB();
			scrollDownWEB();
			waitTime(3000);

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			mandatoryRegistrationPopUp(userType);
			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Pause");
			mixpanel.FEProp.setProperty("Button Type", "Player");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
			}
		}
	}

	public void verifyPauseEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Megamenu");
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitTime(5000);
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Carousel", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Element", "Pause");
			mixpanel.FEProp.setProperty("Button Type", "Player");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
			}
		}
	}

	public void verifyPauseEventForContentInPlaylist(String userType, String tab) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Playlist");

		navigateToAnyScreenOnWeb(tab);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		waitTime(10000);
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyPauseEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Live Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");

		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyResumeEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Live Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}
	}

	public void verifyQualityChangeEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Live Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		click(PWAPlayerPage.playBtn, "Play Icon");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "news_landing");
		mixpanel.FEProp.setProperty("Page Name", "channel_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Free Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

		click(PWAPlayerPage.playBtn, "Play Icon");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Quality Change Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
			click(PWAPlayerPage.playBtn, "Play Icon");
			waitTime(5000);

			
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Old Quality", "Auto");
			mixpanel.FEProp.setProperty("New Quality", "Good");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
			}
		}
	}

	public void verifyQualityChangeEventForTrailer(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		mixpanel.FEProp.setProperty("Page Name", pageName());
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		click(PWAPlayerPage.playBtn, "Play Icon");
		waitTime(5000);
		
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Carousel Content");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

	
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Content", "Content playback is not free for the selected content");
		} else {

			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
			click(PWAPlayerPage.playBtn, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", pageName());

			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Old Quality", "Auto");
			mixpanel.FEProp.setProperty("New Quality", "Good");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
			}
		}
	}

	public void verifyQualityChangeEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Tray");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Content", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
			click(PWAPlayerPage.playBtn, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Old Quality", "Auto");
			mixpanel.FEProp.setProperty("New Quality", "Good");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
			}
		}
	}

	public void verifyQualityChangeEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		click(PWAPlayerPage.playBtn, "Play Icon");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Quality Change Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			waitTime(4000);
			scrollDownWEB();
			scrollDownWEB();
			waitTime(4000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			mandatoryRegistrationPopUp(userType);
			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
			click(PWAPlayerPage.playBtn, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Old Quality", "Auto");
			mixpanel.FEProp.setProperty("New Quality", "Good");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
			}
		}
	}

	public void verifyQualityChangeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Megamenu");
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");

		waitTime(5000);
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Content", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
			click(PWAPlayerPage.playBtn, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Old Quality", "Auto");
			mixpanel.FEProp.setProperty("New Quality", "Good");
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
			}
		}

	}

	public void verifyQualityChangeEventForContentInPlaylist(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Playlist");

		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(10000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		click(PWAPlayerPage.playBtn, "Play Icon");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Upnext rail");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		click(PWAPlayerPage.playBtn, "Play Icon");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void verifyQualityChangeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		click(PWAPlayerPage.playBtn, "Play Icon");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Old Quality", "Auto");
		mixpanel.FEProp.setProperty("New Quality", "Good");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Quality Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Quality Change");
		}
	}

	public void playerScrubTillLastWeb() {
		try {
			WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
			WebElement progressBar = getWebDriver().findElement(PWAPlayerPage.objPlayerProgressBar);
			Actions action = new Actions(getWebDriver());
			action.clickAndHold(scrubber).moveToElement(progressBar, 390, 0).release().perform();
			logger.info("Swiped till end");
			extent.extentLogger("", "Swiped till end");
		} catch (Exception e) {
			logger.info("Swipe till end failed");
			extent.extentLogger("", "Swipe till end failed");
		}
	}
	
	
	public void playerScrub() {
		try {
			WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
			WebElement progressBar = getWebDriver().findElement(PWAPlayerPage.objPlayerProgressBar);
			Actions action = new Actions(getWebDriver());
			action.clickAndHold(scrubber).moveToElement(progressBar, 150, 0).release().perform();
			logger.info("Scrub/Seek");
		} catch (Exception e) {
			logger.info("Swipe failed");
			extent.extentLogger("", "Swipe failed");
		}
	}

	public void verifyResumeEventForContentFromUpnextRail(String userType, String tab) throws Exception {
		extent.HeaderChildNode("Verify Resume Event for content autoplayed from Upnext rail");
		navigateToAnyScreenOnWeb(tab);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		mandatoryRegistrationPopUp(userType);
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}
	}

	public void verifyPauseEventForContentFromUpnextRail(String userType, String tab) throws Exception {
		extent.HeaderChildNode("Verify Pause Event for content autoplayed from Upnext rail");
		navigateToAnyScreenOnWeb(tab);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		mandatoryRegistrationPopUp(userType);
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	
	public void verifyPauseEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Pause Icon");
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Pause");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pause");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Pause");
		}
	}

	public void verifyResumeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("Element", "Resume");
		mixpanel.FEProp.setProperty("Button Type", "Player");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Resume");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Resume");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(String freeContentURL) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For content played from Shared Link");
		
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
	
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromSharedLinkComplete(String freeContentURL) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches the content playback shared through shared link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForFreeContentComplete(String userType, String tab) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration when user completely watches For Free Content");
		
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tab,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
				
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForPremiumContentComplete(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video Watch Duration Event when user completely watches Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(6000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
	}

	public void verifyVideoWatchDurationEventForTrailerComplete(String userType, String tab) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event  when user completely watches Trailer Content");

		clickOnTrayContent(tab,"trailer");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		mixpanel.FEProp.setProperty("Page Name", pageName());

		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForCarouselContentComplete(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when user completely watches Carousel Content");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentInTrayComplete(String tabName) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Tray");
		mandatoryRegistrationPopUp(userType);
		
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name",  pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentFromSearchPageComplete(String keyword1) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content From Search Page");
		mandatoryRegistrationPopUp(userType);
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when user completely watches Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);

			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
			}
		}
	}

	public void verifyVideoWatchDurationEventForContentInMegamenuComplete() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Megamenu");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentInPlaylistComplete(String userType, String tabName)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Playlist");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(10000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		mandatoryRegistrationPopUp(userType);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);
	}

	public void verifyVideoWatchDurationEventForContentFromUpnextRailComplete(String userType, String tab)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event When user completely watches the  auto-played content from Upnext rail");
		navigateToAnyScreenOnWeb(tab);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(5000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
		mandatoryRegistrationPopUp(userType);

	}

	public void verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(String userType, String tab)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly on auto-played content from Upnext rail");
		
		navigateToAnyScreenOnWeb(tab);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(5000);
		
		String id = getWebDriver().getCurrentUrl();
		

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		Back(1);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Video Watch Duration");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Video Watch Duration");
		}
	}

	public void verifyScrubSeekEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Free Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(5000);
			mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

	}

	public void verifyScrubSeekEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Scrub/Seek Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
			}
		}
	}

	public void verifyScrubSeekEventForTrailer(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
	//	waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		mixpanel.FEProp.setProperty("Page Name", pageName());
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrub();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(5000);
		
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Carousel Content");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

	
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Content", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
			}
		}
	}

	public void verifyScrubSeekEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Tray");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		
		waitTime(5000);
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Content", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
			}
		}
	}

	public void verifyScrubSeekEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Scrub/Seek Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			waitTime(4000);
			scrollDownWEB();
			scrollDownWEB();
			waitTime(4000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			mandatoryRegistrationPopUp(userType);
			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
			}
		}
	}

	public void verifyScrubSeekEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Megamenu");
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");

		waitTime(5000);
		if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			logger.info("Content playback is not free for the selected content");
			extent.extentLogger("Content", "Content playback is not free for the selected content");
		} else {
			waitForPlayerAdToComplete("Video Player");

			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
			}
		}
	}

	public void verifyScrubSeekEventForContentInPlaylist(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Playlist");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Upnext rail");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyScrubSeekEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mixpanel.FEProp.setProperty("Source", "N/A");

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(5000);
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		String id = getWebDriver().getCurrentUrl();
		System.out.println("Current URL : " + id);
		ResponseInstance.getContentDetails(fetchContentID(id));
		LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Scrub/Seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Scrub/Seek");
		}
	}

	public void verifyMuteChangedEventDuringContentPlayback(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Mute Changed Event During Content Playback");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");

		click(PWAPlayerPage.audioBtn, "Mute Icon");
		waitTime(2000);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Element", "Mute");
		mixpanel.FEProp.setProperty("Button Type", "Player");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Mute Changed");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Mute Changed");
		}
	}

	public void verifyLoginInitiatedEvent(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event");

			switch (loginMethod) {

			case "mobileNumberLogin":
				click(PWALoginPage.objLoginBtnWEB, "Login button");
				waitForElementDisplayed(PWALoginPage.objEmailField, 5);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, "7892215214", "Phone Number Field");

				click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
//				type(PWASignupPage.objOTP1, "1", "OTP box1");
//				type(PWASignupPage.objOTP2, "2", "OTP box2");
//				type(PWASignupPage.objOTP3, "3", "OTP box3");
//				type(PWASignupPage.objOTP4, "4", "OTP box4");
				waitTime(10000);
				click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "sign_in");
				mixpanel.FEProp.setProperty("Element", "Login");
				mixpanel.FEProp.setProperty("Method", "Mobile");

				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Initiated");
				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonSubscribedUserName");
				String Password = getParameterFromXML("NonsubscribedInvalidPassword");
				click(PWALoginPage.objWebLoginBtn, "Login button");
				waitTime(3000);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				click(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, "hgcsujg", "Password field");
				waitTime(5000);
				click(PWALoginPage.objWebLoginButton, "Login Button");
				waitTime(3000);

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "sign_in");
				mixpanel.FEProp.setProperty("Element", "Login");
				mixpanel.FEProp.setProperty("Method", "Email");
				
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				fetchUserType(local);
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Initiated");

				break;
			}
		}
	}

	public void verifyLoginResultEventForInvalidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event post entering invalid credentials");

			switch (loginMethod) {

			case "mobileNumberLogin":
				click(PWALoginPage.objLoginBtnWEB, "Login button");
				waitForElementDisplayed(PWALoginPage.objEmailField, 5);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, "7892215214", "Phone Number Field");
				click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
				type(PWASignupPage.objOTP1, "1", "OTP box1");
				type(PWASignupPage.objOTP2, "2", "OTP box2");
				type(PWASignupPage.objOTP3, "3", "OTP box3");
				type(PWASignupPage.objOTP4, "4", "OTP box4");
				waitTime(3000);
				click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
				mixpanel.FEProp.setProperty("Source", "sign_in");
				mixpanel.FEProp.setProperty("Page Name", "otp_page");
				mixpanel.FEProp.setProperty("Failure Reason", "Either OTP is not valid or has expired");
				mixpanel.FEProp.setProperty("Success", "false");
				mixpanel.FEProp.setProperty("Method", "Mobile");
				mixpanel.FEProp.setProperty("Element", "Login");
			
				break;

			case "emailLogin":
				String Username = getParameterFromXML("NonSubscribedUserName");
				String Password = getParameterFromXML("NonsubscribedInvalidPassword");
				click(PWALoginPage.objWebLoginBtn, "Login button");
				waitTime(3000);
				click(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				click(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);
				click(PWALoginPage.objWebLoginButton, "Login Button");
				waitTime(3000);
			
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "sign_in");
				mixpanel.FEProp.setProperty("Success", "false");
				mixpanel.FEProp.setProperty("Element", "Login");
				mixpanel.FEProp.setProperty("Method", "Email");
				break;
			}
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Result");
		}
	}

	public void verifyToastMessageImpressionEventInSignInScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In Sign In Screen");
			click(PWALoginPage.objWebLoginBtn, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "sign_in");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Toast Message", "OTP has been sent to your registered Mobile Number!");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for LoggedIn User");
		}
	}

	public void verifyToastMessageImpressionEventInOTPScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In OTP Screen");
			click(PWALoginPage.objWebLoginBtn, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "sign_in");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Toast Message", "Either OTP is not valid or has expired");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for LoggedIn User");
		}
	}

	public void verifyRegistrationInitiatedEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Registration Initiated Event post entering invalid credentials");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "9073258519", "PhoneNumber Field");
			waitTime(3000);
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Send OTP");
			waitTime(5000);
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");

			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Method", "Mobile");
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Registration Initiated");
		}
	}

	public void verifyRegistrationResultEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Registration Result Event post entering invalid credentials");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "9073258519", "PhoneNumber Field");
			waitTime(3000);
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Send OTP");
			waitTime(3000);
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Failure Reason", "Either OTP is not valid or has expired");
			mixpanel.FEProp.setProperty("Success", "false");
			mixpanel.FEProp.setProperty("Method", "Mobile");
			
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Registration Result");
		}
	}

	public void verifyToastMessageImpressionEventInSignUpScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In Sign Up Screen");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			click(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "register");
			mixpanel.FEProp.setProperty("Page Name", "otp_page");
			mixpanel.FEProp.setProperty("Toast Message", "OTP has been sent to your registered Mobile Number!");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for LoggedIn User");
		}
	}

	public void verifySubscriptionCallReturnedEvent(String userType) throws Exception {
		extent.HeaderChildNode(
				"Subscription Call Returned Event when user makes unsuccessful transaction by quitting the payment gateway screen");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			mixpanel.FEProp.setProperty("Page Name", "payment_failure");
			mixpanel.FEProp.setProperty("Source", "N/A");
			String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", cost);
			mixpanel.FEProp.setProperty("Failure Reason", "AUTHORIZATION_FAILED");

			mixpanel.FEProp.setProperty("Payment Method", "visa");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			local = null;
			String localToken = null;

			if (userType.equals("Guest")) {

				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "zeesub@mailnesia.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objEmailContinueButton,
							"Continue Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					click(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "123456", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordContinueButton, "Continue Button");
					Thread.sleep(20000);
					local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					fetchUserType(local);
					localToken = local.getItem("ID");
					System.out.println(local.getItem("ID"));
					
					ResponseInstance.getUserData("zeesub@mailnesia.com","123456");
					ResponseInstance.getUserSettingsValues("zeesub@mailnesia.com","123456");
				}
			} else if (userType.equals("NonSubscribedUser")) {
				Thread.sleep(20000);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				localToken = local.getItem("ID");
				System.out.println(local.getItem("ID"));
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "4012001037141112", "Card Number");
			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0525", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "124", "CVV");
//			String cardDetrails = getWebDriver().findElement(PWASubscriptionPages.objcardDetails).getAttribute("src");
//			mixpanel.FEProp.setProperty("Payment Method", fetchCardDetails(cardDetrails));
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
//			getWebDriver().switchTo().defaultContent();
//			waitTime(10000);
//			click(PWASubscriptionPages.objZeeLink, "Zee link");
			waitTime(20000);

			mixpanel.ValidateParameter(localToken, "Subscription Call Returned");
		}
	}

	public String fetchCardDetails(String cardDetrails) {
		String contentID = null;
		String[] id = cardDetrails.split("/");
		for (int i = 0; i < id.length; i++) {
			if (id[i].contains(".png")) {
				contentID = id[i].replace(".png", "");
			}
		}
		System.out.println(contentID);
		return contentID;
	}

	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Subscription Call Initiated Event for All access pack");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			mixpanel.FEProp.setProperty("Page Name", "payment_page");

			String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", cost);
			mixpanel.FEProp.setProperty("Payment Method", "mastercard");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);
			local = null;
			String localToken = null;

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					mixpanel.FEProp.setProperty("Source", "account_info");
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "zeesub@mailnesia.com", "Email Id");
					Thread.sleep(5000);
					verifyElementPresentAndClick(PWASubscriptionPages.objEmailContinueButton,"Continue Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					click(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "123456", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordContinueButton, "Continue Button");
					Thread.sleep(20000);
					local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					fetchUserType(local);
					localToken = local.getItem("ID");
					System.out.println(local.getItem("ID"));
					ResponseInstance.getUserData("zeesub@mailnesia.com","123456");
					ResponseInstance.getUserSettingsValues("zeesub@mailnesia.com","123456");
				}
			} else if (userType.equals("NonSubscribedUser")) {
				mixpanel.FEProp.setProperty("Source", "pack_selection");
				Thread.sleep(20000);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				localToken = local.getItem("ID");
				System.out.println(local.getItem("ID"));
			}

			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5123456789012346", "Card Number");
			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
//			String cardDetrails = getWebDriver().findElement(PWASubscriptionPages.objcardDetails).getAttribute("src");
//			mixpanel.FEProp.setProperty("Payment Method", fetchCardDetails(cardDetrails));
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			getWebDriver().switchTo().defaultContent();
			waitTime(10000);
			waitTime(5000);
			mixpanel.ValidateParameter(localToken, "Subscription Call Initiated");
		}
	}


	/**
	 * Login through ClubUser Id
	 * 
	 * @param userType
	 * @param keyword6
	 * @throws Exception
	 */
	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		extent.HeaderChildNode("Subscription Call Initiated Event for Club pack");

		if (userType.equals("ClubUser")) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objClubPack, "Club Pack");
			click(PWASubscriptionPages.objPackAmount1, "Pack");
			mixpanel.FEProp.setProperty("Page Name", "payment_page");
			String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			mixpanel.FEProp.setProperty("Transaction Currency", "INR");
			mixpanel.FEProp.setProperty("Cost", cost);
			mixpanel.FEProp.setProperty("Payment Method", "mastercard");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			local = null;
			String localToken = null;

			if (userType.equals("Guest")) {
				mixpanel.FEProp.setProperty("Source", "account_info");
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage,
							"Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
					local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					fetchUserType(local);
					localToken = local.getItem("ID");
					System.out.println(local.getItem("ID"));
				}
			} else if (userType.equals("NonSubscribedUser")) {
				mixpanel.FEProp.setProperty("Source", "pack_selection");
		
		Thread.sleep(20000);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				localToken = local.getItem("ID");
				System.out.println(local.getItem("ID"));
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5123456789012346", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
//			String cardDetrails = getWebDriver().findElement(PWASubscriptionPages.objcardDetails).getAttribute("src");
//			mixpanel.FEProp.setProperty("Payment Method", fetchCardDetails(cardDetrails));
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			getWebDriver().switchTo().defaultContent();
			waitTime(10000);

			mixpanel.ValidateParameter(localToken, "Subscription Call Initiated");

		}
	}

	@SuppressWarnings("static-access")
	public void verifyLogoutEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			waitTime(5000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Element", "LOGOUT");
			mixpanel.FEProp.setProperty("Page Name", "home");
			mixpanel.FEProp.setProperty("Source", "N/A");
			String ID = local.getItem("ID");
			logout();
			waitTime(5000);
			mixpanel.ValidateParameter(ID, "Logout");
		}
	}

	public void verifySkipLoginThroughBeforeTVContent() throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip Login Event gets triggered when user click on close button in login popup "
							+ "on clicking login in Get premium popup on accessing before tv content");
			navigateToAnyScreenOnWeb("Shows");
			if (checkElementDisplayed(PWAHomePage.objFirstContentCardOfTray("Before"),
					"First Content Card Of Before TV Tray")) {
				click(PWAHomePage.objFirstContentCardOfTray("Before"), "First Content Card Of Before TV Tray");
				waitForPlayerAdToComplete("Video Player");
				waitTime(6000);
				click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
				playerScrubTillLastWeb();
				click(PWAPlayerPage.objPlayerPlay, "Play Icon");
				waitForPlayerAdToComplete("Video Player");
				waitForElement(PWASubscriptionPages.objGetPremiumPopupTitle, 10, "Get Premium Popup Title");
				click(PWALoginPage.objLoginCTAInPremiumPopup, "Login CTA");
				waitForElement(PWALoginPage.objSkip, 10, "Skip Login");
				waitTime(5000);
				click(PWALoginPage.objSkip, "Skip Login");
				waitTime(2000);
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "sign_in");
				mixpanel.FEProp.setProperty("Element", "Cross");				
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login");
			}
		}
	}

	public void verifySettingChangedEventAfterAccountVerification(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event when Parental Control Account Verification is done");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonSubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", "home");
			mixpanel.FEProp.setProperty("Element", "Continue");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control Account Verification");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Setting Changed");
		}
	}

	public void verifySettingChangedEventAfterParentalPinIsSet(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event when Parental Control PIN is Set");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonSubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objRestrictAll, "Restrict all option");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(2000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control Age Set");
			mixpanel.FEProp.setProperty("Element", "Set parental lock");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Setting Changed");
		}
	}

	public void verifySettingChangedEventAfterAgeIsSet(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Setting Changed Event when Parental Control Age is Set");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonSubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict 13+ Content");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(2000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control PIN Set");
			mixpanel.FEProp.setProperty("Element", "Set parental lock");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U/A");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			
			mixpanel.parentalValidateParameter(local.getItem("ID"), "Setting Changed");
			
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonSubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			click(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restriction option");
			click(PWAHamburgerMenuPage.objContinueButton, "Continue Button");

		}
	}

	public void verifyToastMessageImpressionEventAfterResetSettingsToDefault() throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression Event After Reset Settings To Default");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");
		click(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(10000);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "settings");
		mixpanel.FEProp.setProperty("Toast Message", "Changes Saved Successfully");

		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}

	}

	public void verifySearchButtonClickEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Button Click Event");
		waitTime(10000);
		click(PWAHomePage.objSearchBtn, "Search Icon");
		waitTime(5000);
		
		if (userType.equals("Guest")) {
			mixpanel.FEProp.setProperty("Page Name", "Content Language");
		} else {
			mixpanel.FEProp.setProperty("Page Name", "home");
		}
		
		mixpanel.FEProp.setProperty("Element", "Search");
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Button Click");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Search Button Click");
		}
	}

	public void verifySearchResultClickedEvent(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Search Result Clicked Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
		waitTime(4000);
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		
		waitTime(10000);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Search Query", keyword);
		mixpanel.FEProp.setProperty("Search Type", "text");
		mixpanel.FEProp.setProperty("Page Name", "search");
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Search Result Clicked");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Search Result Clicked");
		}
	}

	public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when Native popup is displayed");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			waitTime(4000);
			click(PWAPremiumPage.objPlayBtn, "Content From a tray");
			waitForElement(PWALoginPage.objCloseRegisterPopup, 10, "Register Pop Up");
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "show_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Pop Up Group", "Skippable");
			mixpanel.FEProp.setProperty("Pop Up Name", "Registration");
			mixpanel.FEProp.setProperty("Pop Up Type", "Mandatory Registration");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Popup launch");
		}else {
			extentLogger("", "Scenario is valid for only guest userType");
		}
	}

	public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when Complete Profile popup is displayed");
		//	relaunch();
			logout();
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			extent.HeaderChildNode("Login through incomplete profile account");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, "indaus24@gmail.com", "Email Field");
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, "123456", "Password field");
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
			checkElementDisplayed(PWAHomePage.objSearchField, "Search field");
			String keyword = getParameterFromXML("keyword");
			type(PWAHomePage.objSearchField, keyword, "Search");
			waitTime(5000);
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
			waitTime(3000);
			click(PWAPremiumPage.objPlayBtn, "Play Button");
			waitTime(4000);
			checkElementDisplayed(CompleteYourProfilePopUp.objCompleteYourProfileTxt, "Complete Your Profile");
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "show_detail");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Pop Up Group", "Skippable");
			mixpanel.FEProp.setProperty("Pop Up Name", "Profile Update");
			mixpanel.FEProp.setProperty("Pop Up Type", "Mandatory Registration");
			
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			ResponseInstance.getUserData("indaus24@gmail.com","123456");
			mixpanel.ValidateParameter(local.getItem("ID"), "Popup launch");
		}
	}

	/**
	 * Login through ClubUser Id
	 * 
	 * @param userType
	 * @param keyword6
	 * @throws Exception
	 */
	public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
		if (userType.equalsIgnoreCase("ClubUser")) {
			extent.HeaderChildNode("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword6 + "\n", "Search Edit box: " + keyword6);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword6), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword6), "Search Result");
			waitForElement(PWAHamburgerMenuPage.objPopupClose, 10, "Upgrade Pop Up");
			waitTime(6000);
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Popup launch");
		}
	}

	public void verifyPopUpCTAsEvent(String userType, String keyword) throws Exception {
		if ((userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Pop Up CTA's Event when user clicks on CTA button displayed on Registration popup");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			waitTime(3000);
			click(PWAPremiumPage.objPlayBtn, "Play Button");
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			waitForElement(PWALoginPage.objLoginLink, 20, "Login Button");
			click(PWALoginPage.objLoginLink, "Login Button");
			waitTime(5000);
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "show_detail");
			mixpanel.FEProp.setProperty("Element", "Login");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			
			mixpanel.FEProp.setProperty("Pop Up Group", "Skippable");
			mixpanel.FEProp.setProperty("Pop Up Name", "Registration");
			mixpanel.FEProp.setProperty("Pop Up Type", "Mandatory Registration");
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Pop Up CTAs");
		}else {
			extentLogger("", "Scenario is valid only for Guest userType");
		}
	}

	public void verifyToastMessageImpressionEventInParentalControlScreen(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Toast Message Impression Event In Parental Control Screen");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonSubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict 13+ Content");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(2000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");
			mixpanel.FEProp.setProperty("Toast Message", "Changes Saved Successfully");
			Mixpanel.parentalValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for Guest User");
		}
	}

	public void verifyDeviceAuthenticationEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Device Authentication Event when authentication fails in TV Authentication screen");
			String AuthPin = "abcdef";
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authenticate Device");
			type(PWAHamburgerMenuPage.objAuthenticationField, AuthPin, "AuthenticationField");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			
			mixpanel.FEProp.setProperty("Page Name", "device_authentication");
			mixpanel.FEProp.setProperty("Failure Reason", "Device code " + AuthPin + " has expired");
			mixpanel.FEProp.setProperty("Element", "Authenticate");
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Success", "false");
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			System.out.println(local.getItem("ID"));
			mixpanel.ValidateParameter(local.getItem("ID"), "Device Authentication");
		}
	}

	public void verifyToastMessageImpressionEventInAuthenticateScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression Event In Authenticate Screen");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authenticate Device");
			type(PWAHamburgerMenuPage.objAuthenticationField, "abcdef", "AuthenticationField");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "device_authentication");
			mixpanel.FEProp.setProperty("Toast Message", "Device code ABCDEF has expired");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for Guest User");
		}
	}

	public void verifyToastMessageImpressionEventForAddToWatchlist(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Toast Message Impression Event after adding card to watchlist");
			navigateToAnyScreenOnWeb("Movies");
			waitTime(3000);
			scrollByWEB();
			waitTime(3000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			} else {
				click(PWAPremiumPage.objContentCardRemoveFromWatchlistBtn, "Remove From Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_landing");
			mixpanel.FEProp.setProperty("Toast Message", "Added to Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for Guest User");
		}

	}

	public void verifyToastMessageImpressionEventForRemoveFomWatchlist(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Toast Message Impression Event after removing card from watchlist");

			waitTime(5000);
			navigateToAnyScreenOnWeb("Movies");
			scrollByWEB();
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInViewAllPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPremiumPage.objContentCardRemoveFromWatchlistBtn, "Remove From Watchlist icon");

			} else {
				click(PWAPremiumPage.objContentCardRemoveFromWatchlistBtn, "Remove From Watchlist icon");
				waitTime(3000);
			}
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_landing");
			mixpanel.FEProp.setProperty("Toast Message", "Removed from Watchlist");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for Guest User");
		}

	}

	public void verifyToastMessageImpressionEventForEmbedPopUp(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression Event when user gets a toast message in embed popup");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		click(PWAPremiumPage.objEmbedPopUp, "Embed option");
		click(PWAPremiumPage.objEmbedCopy, "Copy");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Toast Message", "Copied to Clipboard");

		if (userType.equals("Guest")) {
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
		} else {
			Mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}

	}

	public void verifyAutoSeekForwardEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Free Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);
		String id = getWebDriver().getCurrentUrl();
		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Auto Seek Forward Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
			waitTime(5000);
			String id = getWebDriver().getCurrentUrl();
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekForwardEventForTrailer(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Trailer Content");

		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Carousel Content");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Tray");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name",  pageName());
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Auto Seek Forward Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

		
			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Direction", "forward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekForwardEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentInPlaylist(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Playlist");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For Content played from Upnext rail");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekForwardEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Forward Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Forward 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "forward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Free Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForPremiumContent(String userType, String tabName) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Auto Seek Rewind Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tabName,"Free");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			
			mixpanel.FEProp.setProperty("Direction", "backward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekRewindEventForTrailer(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Carousel Content");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Tray");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
	//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
		clickOnTrayContent(tabName,"Free");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "Content Language");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Direction", "backward");
			mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
			}
		}
	}

	public void verifyAutoSeekRewindEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.forward10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentInPlaylist(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Playlist");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For Content played from Upnext rail");
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitTime(5000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAutoSeekRewindEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Auto Seek Rewind Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		freeContentURL = site + freeContentURL;
		System.out.println(freeContentURL);
		getWebDriver().get(freeContentURL);
		logger.info("Opened link : " + freeContentURL);
		extent.extentLogger("", "Opened link : " + freeContentURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 sec button");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Direction", "backward");
		mixpanel.FEProp.setProperty("Seek-Scrub Duration", "10");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Auto-seek");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Auto-seek");
		}
	}

	public void verifyAudioLanguageChangeEventForFreeContent(String userType, String audioTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		mandatoryRegistrationPopUp(userType);
		waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 20, "Search Result");
		click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		String oldLang = mixpanel.languageShortform(contentLang);
		
		System.out.println("contentLang : "+contentLang);
		System.out.println("old Lang : "+oldLang);
		click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
		waitTime(5000);
		ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		System.out.println("contentLang : "+NewcontentLang);
		String newLang = mixpanel.languageShortform(NewcontentLang);
		System.out.println("new Lang : "+newLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Audio Language Change Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			waitTime(6000);
			waitForPlayerAdToComplete("Video Player");
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
				click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
				String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
				String oldLang = mixpanel.languageShortform(contentLang);
				
				System.out.println("contentLang : "+contentLang);
				System.out.println("old Lang : "+oldLang);
				click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
				waitTime(5000);
				ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
				click(PWAPlayerPage.objPlayerSettings, "Settings icon");
				click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
				String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
				System.out.println("contentLang : "+NewcontentLang);
				String newLang = mixpanel.languageShortform(NewcontentLang);
				System.out.println("new Lang : "+newLang);
				Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
				mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
				Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
				mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);;

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
				}

			} else {
				logger.info("Audio Track is not available for the content");
				extent.extentLogger("Audio Track", "Audio Track is not available for the content");
			}
			waitTime(5000);

		}
	}

	public void verifyAudioLanguageChangeEventForTrailer(String audioTrackTrailerContent) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackTrailerContent + "\n",
				"Search Edit box: " + audioTrackTrailerContent);
		waitTime(4000);
		mandatoryRegistrationPopUp(userType);
		waitForElement(PWASearchPage.objSearchResultTxt(audioTrackTrailerContent), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(audioTrackTrailerContent), "Search Result");
		
	//	waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		String oldLang = mixpanel.languageShortform(contentLang);
		
		System.out.println("contentLang : "+contentLang);
		System.out.println("old Lang : "+oldLang);
		click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
		waitTime(5000);
		ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		System.out.println("contentLang : "+NewcontentLang);
		String newLang = mixpanel.languageShortform(NewcontentLang);
		System.out.println("new Lang : "+newLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
		
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "video_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
	
		
		
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Carousel Content");
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
			String oldLang = mixpanel.languageShortform(contentLang);
			
			System.out.println("contentLang : "+contentLang);
			System.out.println("old Lang : "+oldLang);
			click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
			waitTime(5000);
			ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
			System.out.println("contentLang : "+NewcontentLang);
			String newLang = mixpanel.languageShortform(NewcontentLang);
			System.out.println("new Lang : "+newLang);
			Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
			mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
			Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
			mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
			}

		} else {
			logger.info("Audio Track is not available for the content");
			extent.extentLogger("Audio Track", "Audio Track is not available for the content");
		}

	}

	public void verifyAudioLanguageChangeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Tray");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
			
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
			String oldLang = mixpanel.languageShortform(contentLang);
			
			System.out.println("contentLang : "+contentLang);
			System.out.println("old Lang : "+oldLang);
			click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
			waitTime(5000);
			ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
			System.out.println("contentLang : "+NewcontentLang);
			String newLang = mixpanel.languageShortform(NewcontentLang);
			System.out.println("new Lang : "+newLang);
			Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
			mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			
			
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
			}

		} else {
			logger.info("Audio Track is not available for the content");
			extent.extentLogger("Audio Track", "Audio Track is not available for the content");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromSearchPage(String audioTrackContent) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		String oldLang = mixpanel.languageShortform(contentLang);
		
		System.out.println("contentLang : "+contentLang);
		System.out.println("old Lang : "+oldLang);
		click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
		waitTime(5000);
		ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		System.out.println("contentLang : "+NewcontentLang);
		String newLang = mixpanel.languageShortform(NewcontentLang);
		System.out.println("new Lang : "+newLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", pageName());
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Audio Language Change Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
				click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
				click(PWAPlayerPage.objHindiAudioTrack, "Hindi Audio Track");
				String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
				String oldLang = mixpanel.languageShortform(contentLang);
				
				System.out.println("contentLang : "+contentLang);
				System.out.println("old Lang : "+oldLang);
				click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
				waitTime(5000);
				ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
				click(PWAPlayerPage.objPlayerSettings, "Settings icon");
				click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
				String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
				System.out.println("contentLang : "+NewcontentLang);
				String newLang = mixpanel.languageShortform(NewcontentLang);
				System.out.println("new Lang : "+newLang);
				Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
				mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
				}

			} else {
				logger.info("Audio Track is not available for the content");
				extent.extentLogger("Audio Track", "Audio Track is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAudioLanguageChangeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();
		mandatoryRegistrationPopUp(userType);
		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		if (checkElementDisplayed(PWAPlayerPage.objPlayerAudioTrack, "Audio Track") == true) {
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
			String oldLang = mixpanel.languageShortform(contentLang);
			
			System.out.println("contentLang : "+contentLang);
			System.out.println("old Lang : "+oldLang);
			click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
			waitTime(5000);
			ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
			click(PWAPlayerPage.objPlayerSettings, "Settings icon");
			click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
			String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
			System.out.println("contentLang : "+NewcontentLang);
			String newLang = mixpanel.languageShortform(NewcontentLang);
			System.out.println("new Lang : "+newLang);
			Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
			mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
			}

		} else {
			logger.info("Audio Track is not available for the content");
			extent.extentLogger("Audio Track", "Audio Track is not available for the content");
		}
	}

	public void verifyAudioLanguageChangeEventForContentInPlaylist(String userType, String audioTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");

		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		String oldLang = mixpanel.languageShortform(contentLang);
		
		System.out.println("contentLang : "+contentLang);
		System.out.println("old Lang : "+oldLang);
		click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
		waitTime(5000);
		ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		System.out.println("contentLang : "+NewcontentLang);
		String newLang = mixpanel.languageShortform(NewcontentLang);
		System.out.println("new Lang : "+newLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
	
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromUpnextRail(String userType, String audioTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		String oldLang = mixpanel.languageShortform(contentLang);
		
		System.out.println("contentLang : "+contentLang);
		System.out.println("old Lang : "+oldLang);
		click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
		waitTime(5000);
		ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		System.out.println("contentLang : "+NewcontentLang);
		String newLang = mixpanel.languageShortform(NewcontentLang);
		System.out.println("new Lang : "+newLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
	
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyAudioLanguageChangeEventForContentFromSharedLink(String audioTrackURL) throws Exception {
		extent.HeaderChildNode("Verify Audio Language Change Event For content played from Shared Link");
		getWebDriver().get(audioTrackURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String contentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		String oldLang = mixpanel.languageShortform(contentLang);
		
		System.out.println("contentLang : "+contentLang);
		System.out.println("old Lang : "+oldLang);
		click(PWAPlayerPage.objSelectAudioLanguage, "Audio Track");
		waitTime(5000);
		ResponseInstance.getContentDetails(fetchContentID(getWebDriver().getCurrentUrl()));
		click(PWAPlayerPage.objPlayerSettings, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrack, "Audio Track");
		String NewcontentLang = getText(By.xpath(".//*[@class='subMenuWrapper tickMark']/div"));
		System.out.println("contentLang : "+NewcontentLang);
		String newLang = mixpanel.languageShortform(NewcontentLang);
		System.out.println("new Lang : "+newLang);
		Mixpanel.FEProp.setProperty("Content Original Language",oldLang);
		mixpanel.FEProp.setProperty("New Audio Language", NewcontentLang);
		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifyToastMessageImpressionEventInPaymentPage(String userType) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression Event in payment page");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				mixpanel.FEProp.setProperty("Source", "account_info");
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objEmailContinueButton,
							"Continue Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordContinueButton, "Continue Button");
				}
			} else {
				mixpanel.FEProp.setProperty("Source", "pack_selection");
		}
			waitTime(5000);

			click(PWASubscriptionPages.objExpandLess, "Gift card option");
			click(PWASubscriptionPages.objGiftCardNumber, "Gift Card Number");
			type(PWASubscriptionPages.objGiftCardNumber, "5123456789012346", "Gift Card Number");
			click(PWASubscriptionPages.objPin, "Pin");
			type(PWASubscriptionPages.objPin, "12345678", "Pin");
			click(PWASubscriptionPages.objPay, "Pay Button");
			waitTime(5000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);

//			if (userType.equals("Guest")) {
//
//				mixpanel.FEProp.setProperty("Source", "account_info");
//				mixpanel.FEProp.setProperty("Page Name", "payment_page");
//				mixpanel.FEProp.setProperty("Toast Message", "Could not find card. Please enter valid card number");
//				mixpanel.ValidateParameter(local.getItem("guestToken"), "Toast Message Impression");
//			} else {

				mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
				
				mixpanel.FEProp.setProperty("Page Name", "payment_page");
				mixpanel.FEProp.setProperty("Toast Message", "Could not find card. Please enter valid card number");
		//	}
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for Subscribed User");
		}
	}

	public void verifySubtitleLanguageChangeEventForFreeContent(String userType, String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 20, "Search Result");
		click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");
		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}
	}

	public void verifySubtitleLanguageChangeEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Subtitle Language Change Event For Premium Content");
			navigateToAnyScreenOnWeb(tab);
			click(PWAPremiumPage.objPremiumTag, "Premium Content");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);

			if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
				click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
				click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
				waitTime(5000);

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				mixpanel.FEProp.setProperty("New Subtitle Language", "English");

				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
				}

			} else {
				logger.info("Subtitle is not available for the content");
				extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
			}

		}
	}

	public void verifySubtitleLanguageChangeEventForTrailer(String keyword5) throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword5 + "\n", "Search Edit box: " + keyword5);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword5), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword5), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}
	}

	public void verifySubtitleLanguageChangeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");

		if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
			click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
			click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Subtitle Language", "English");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
			}

		} else {
			logger.info("Subtitle is not available for the content");
			extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
			click(PWAPlayerPage.subtitlesBtn, "Subtitle option");
			click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Subtitle Language", "English");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
			}

		} else {
			logger.info("Subtitle is not available for the content");
			extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentFromSearchPage(String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Audio Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Audio Language Change");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentFromMyWatchlistPage(String userType,
			String subtitleTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Subtitle Language Change Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
				click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
				click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
				waitTime(5000);

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				mixpanel.FEProp.setProperty("New Subtitle Language", "English");

				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
				}

			} else {
				logger.info("Subtitle is not available for the content");
				extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifySubtitleLanguageChangeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.objSubtitleIcon, "Subtitle option") == true) {
			click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
			click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("New Subtitle Language", "English");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
			}

		} else {
			logger.info("Subtitle is not available for the content");
			extent.extentLogger("Subtitle Track", "Subtitle is not available for the content");
		}
	}

	public void verifySubtitleLanguageChangeEventForContentInPlaylist(String userType, String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");

		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}

	}

	public void verifySubtitleLanguageChangeEventForContentFromUpnextRail(String userType, String subtitleTrackContent)
			throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n", "Search Edit box: " + subtitleTrackContent);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "episode_detail");
		mixpanel.FEProp.setProperty("Page Name", "episode_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}

	}

	public void verifySubtitleLanguageChangeEventForContentFromSharedLink(String subtitleTrackURL) throws Exception {
		extent.HeaderChildNode("Verify Subtitle Language Change Event For content played from Shared Link");
		getWebDriver().get(subtitleTrackURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objSubtitleIcon, "Subtitle option");
		click(PWAPlayerPage.objEnglishSubtitle, "English Subtitles");
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
		mixpanel.FEProp.setProperty("New Subtitle Language", "English");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Subtitle Language Change");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Subtitle Language Change");
		}

	}

	public void verifySkipIntroEventForFreeContent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Free Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"Free");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button")) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Skip Intro Event For Premium Content");

			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			
			waitForPlayerAdToComplete("Video Player");
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button")) {
				click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
				waitTime(5000);
			
				mixpanel.FEProp.setProperty("Element", "Skip Intro");
				mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));

				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (UserType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
				}
			} else {
				logger.info("Skip Intro is not available for the content");
				extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifySkipIntroEventForTrailer(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Trailer Content");
		mandatoryRegistrationPopUp(userType);
		clickOnTrayContent(tabName,"trailer");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
			JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
			mixpanel.FEProp.setProperty("Source", "show_detail");
		}else {
			mixpanel.FEProp.setProperty("Source", "home");
		}
		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForCarouselContent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Carousel Content");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tabName);
		mandatoryRegistrationPopUp(userType);
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

		
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForContentInTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Tray");
		navigateToAnyScreenOnWeb("Movies");
		JSClick(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForContentFromSearchPage(String freeMovie2) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(freeMovie2), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(freeMovie2), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button")) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForContentFromMyWatchlistPage(String userType, String freeMovie2) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Skip Intro Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(freeMovie2), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(freeMovie2), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			partialScrollDown();
			// if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn,
			// "Add To Watchlist icon")) {
			JSClick(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			// }

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
				click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Element", "Skip Intro");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (UserType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
				}
			} else {
				logger.info("Skip Intro is not available for the content");
				extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifySkipIntroEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		mandatoryRegistrationPopUp(UserType);
		waitForPlayerAdToComplete("Video Player");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}

		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySkipIntroEventForContentInPlaylist(String userType, String freeMovie2) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(freeMovie2), "Search Result");
		mandatoryRegistrationPopUp(UserType);
		waitForPlayerAdToComplete("Video Player");

		click(PWAPremiumPage.objThumbnail, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
		waitTime(5000);
	}

	public void verifySkipIntroEventForContentFromUpnextRail(String userType, String freeMovie2) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, freeMovie2 + "\n", "Search Edit box: " + freeMovie2);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(freeMovie2), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button") == true) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
		waitTime(5000);
	}

	public void verifySkipIntroEventForContentFromSharedLink(String skipIntroURL) throws Exception {
		extent.HeaderChildNode("Verify Skip Intro Event For content played from Shared Link");
		mandatoryRegistrationPopUp(userType);
		String site = getParameterFromXML("url");
		skipIntroURL = site + skipIntroURL;
		System.out.println(skipIntroURL);
		getWebDriver().get(skipIntroURL);
		logger.info("Opened link : " + skipIntroURL);
		extent.extentLogger("", "Opened link : " + skipIntroURL);
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if (checkElementDisplayed(PWAPlayerPage.skipIntroBtn, "Skip Intro Button")) {
			click(PWAPlayerPage.skipIntroBtn, "Skip Intro Button");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Element", "Skip Intro");
			mixpanel.FEProp.setProperty("Page Name", "kids_movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Intro");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Skip Intro");
			}
		} else {
			logger.info("Skip Intro is not available for the content");
			extent.extentLogger("Skip Intro", "Skip Intro is not available for the content");
		}
	}

	public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel() throws Exception {
		if (!(UserType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");

			navigateToAnyScreenOnWeb("Movies");
			JSClick(PWAPremiumPage.objGetPremiumCTAOnCarousel, "Get Premium CTA on carousel");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Source", "movie_landing");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Subscription Page Viewed");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Subscription Page Viewed");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForFreeContent(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Free Content");
			waitTime(4000);
//			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
//			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
//			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
//			String password = "";
//			if (userType.equals("NonSubscribedUser")) {
//				password = getParameterFromXML("SettingsNonSubscribedPassword");
//			} else if (userType.equals("SubscribedUser")) {
//				password = getParameterFromXML("SettingsSubscribedPassword");
//			}
//			type(PWALoginPage.objPasswordField, password, "Password field");
//			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
//			waitTime(2000);
//			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
//
//			click(PWAHamburgerMenuPage.objRestrictAll, "Restrict All");
//			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
//			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
//			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
//			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
//			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
//			waitTime(4000);
//			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
//			waitTime(3000);
			navigateToAnyScreenOnWeb(tabName);
			clickOnTrayContent(tabName,"Free");
			
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			if(userType.equals("NonSubscribedUser")) {
				String SUsername = getParameterFromXML("SettingsNonSubscribedUserName");
				String SPassword = getParameterFromXML("SettingsNonSubscribedPassword");
				ResponseInstance.getUserData(SUsername,SPassword);
			}else if(userType.equals("SubscribedUser")) {
				String SUsername = getParameterFromXML("SettingsSubscribedUserName");
				String SPassword = getParameterFromXML("SettingsSubscribedPassword");
				ResponseInstance.getUserData(SUsername,SPassword);
			}

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			if(userType.equals("SubscribedUser")) {
				String SUsername = getParameterFromXML("SettingsSubscribedUserName");
				String SPassword = getParameterFromXML("SettingsSubscribedPassword");
				ResponseInstance.getUserData(SUsername,SPassword);
			}
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForTrailer(String tab, String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Trailer Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Page Name",  pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForCarouselContent(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Carousel Content");
			waitTime(5000);
			navigateToAnyScreenOnWeb(tabName);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentInTray(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Tray");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromSearchPage(String keyword1, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentInPlaylist(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Playlist");

			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromUpnextRail(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For Content played from Upnext rail");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventForContentFromSharedLink(String freeContentURL, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayImpressionEventAfterPageRefresh(String tabName, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Impression Event after refreshing the page");
			
			
			navigateToAnyScreenOnWeb(tabName);
			clickOnTrayContent(tabName,"Free");
			
			
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(5000);
			getWebDriver().navigate().refresh();

			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");

		 	mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			
			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Impression");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Impression");
			}
		}
	}

	public void verifyParentalOverlayResultEventForFreeContent(String userType, String tabName) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Free Content");

			navigateToAnyScreenOnWeb(tabName);
			clickOnTrayContent(tabName,"Free");
			
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForPremiumContent(String userType, String tab) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Premium Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForTrailer(String tab, String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Trailer Content");
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			scrollDownByY(300);
			click(PWAMoviesPage.objPremiumContentCardFromTray, "Premium Content from Tray");
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mixpanel.FEProp.setProperty("Page Name", pageName());
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Page Name",  pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForCarouselContent(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Carousel Content");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentInTray(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Tray");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "Content Language");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentFromSearchPage(String keyword1, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
			mixpanel.FEProp.setProperty("Page Name", pageName());
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}

		}
	}

	public void verifyParentalOverlayResultEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "movie_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForContentInPlaylist(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Playlist");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForContentFromUpnextRail(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For Content played from Upnext rail");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyParentalOverlayResultEventForContentFromSharedLink(String freeContentURL, String userType)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Parental Overlay Result Event For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			verifyElementPresent(PWAPlayerPage.objParentalLockOnPlayer, "Parental Lock Overlay");
			click(PWAHamburgerMenuPage.objParentalLockPin1player, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", "episode_detail");
			mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U");
			mixpanel.FEProp.setProperty("Failure Reason", "N/A");

			String id = getWebDriver().getCurrentUrl();
			ResponseInstance.getContentDetails(fetchContentID(id));

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Parental Overlay Result");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Parental Overlay Result");
			}
		}
	}

	public void verifyToastMessageImpressionEventAfterUpdatingProfile(String userType) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression event when user updates the profile details");
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(PWAHamburgerMenuPage.objProfileIconWEB, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "Profile");
			click(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
			click(PWAHamburgerMenuPage.objEditProfileTextWEB, "Edit profile page");
			click(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
			clearField(PWAHamburgerMenuPage.objEditProfileFirstName, "Email field");
			type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Editprofile first name");

			click(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save Changes Button");
			waitTime(2000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			mixpanel.FEProp.setProperty("Toast Message", "Changes Saved Successfully");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}
	}

	public void verifyToastMessageImpressionEventAfterChangingPassword(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Toast Message Impression event when user changes the password");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igsindia123", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "my_profile");
			mixpanel.FEProp.setProperty("Toast Message", "Password was changed successfully");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");

			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igsindia123", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igsindia123", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for Guest User");
		}
	}

//	public void webScrollToElement(By Locator, String validationText) throws Exception {
//		for (int i = 1; i <= 10; i++) {
//			if (checkElementDisplayed(Locator, validationText)) {
//				break;
//			}
//			waitTime(2000);
//			scrollDownWEB();
//		}
//	}

	public void verifyRecommendedRailImpressionEventByScrollingPage(String tabname, String trayTitle) throws Exception {
		extent.HeaderChildNode("Verify Recommended Rail Impression event when user is able to see the recommended tray by scrolling down the page");
		navigateToAnyScreenOnWeb(tabname);
		WebElement element = getWebDriver().findElement(PWAShowsPage.objTrayTitle1(trayTitle));
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		checkElementDisplayed(PWAHomePage.objRecoTray, "Recommended Rail");
		try{
			String title=getElementPropertyToString("innerText",PWAShowsPage.objTrayTitle1(trayTitle),"Title");
			logger.info("Tray title displayed : "+title);
			extent.extentLogger("", "Tray title displayed : "+title);
		}catch(Exception e) {}	
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Recommended Rail Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Recommended Rail Impression");
		}
	}

	public void verifyRecommendedRailImpressionEventInShowDetailPage(String keyword, String trayTitle)
			throws Exception {
		extent.HeaderChildNode("Verify Recommended Rail Impression Event In Show Detail Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword), 20, "Search Result");
		click(PWASearchPage.objSearchNavigationTab("TV Shows"), "TV Shows Tab");
		waitTime(4000);
		click(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(4000);
		try{
			String title=getElementPropertyToString("innerText",PWAShowsPage.objShowsTitle,"Title");
			logger.info("Show Details page displayed with title: " + title);
			extent.extentLogger("showDetails", "Show Details page displayed with title: " + title);
		}catch(Exception e) {}	
		WebElement element = getWebDriver().findElement(PWAShowsPage.objRecoTrayTitle(trayTitle));
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		checkElementDisplayed(PWAShowsPage.objRecoTrayTitle(trayTitle), "Recommended Rail");
		try{
			String title=getElementPropertyToString("innerText",PWAShowsPage.objTrayTitle1(trayTitle),"Title");
			logger.info("Tray title displayed : "+title);
			extent.extentLogger("", "Tray title displayed : "+title);
		}catch(Exception e) {}	
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "show_detail");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Recommended Rail Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Recommended Rail Impression");
		}
	}

	public void verifyRecommendedRailImpressionEventInConsumptionScreen(String keyword1, String trayTitle)throws Exception {
		extent.HeaderChildNode("Verify Recommended Rail Impression Event In Consumption Screen");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResultTxt(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResultTxt(keyword1), "Search Result");
		waitTime(4000);
		try{
			String title=getElementPropertyToString("innerText",PWAPlayerPage.objContentTitleInConsumptionPage,"Title");
			logger.info("Consumptions page displayed with title: " + title);
			extent.extentLogger("showDetails", "Consumptions page displayed with title: " + title);
		}catch(Exception e) {}	
		WebElement element = getWebDriver().findElement(PWAShowsPage.objRecoTrayTitle(trayTitle));
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		checkElementDisplayed(PWAShowsPage.objRecoTrayTitle(trayTitle), "Recommended Rail");
		try{
			String title=getElementPropertyToString("innerText",PWAShowsPage.objTrayTitle1(trayTitle),"Title");
			logger.info("Tray title displayed : "+title);
			extent.extentLogger("", "Tray title displayed : "+title);
		}catch(Exception e) {}	
		waitTime(5000);
		mixpanel.FEProp.setProperty("Source", "search");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Recommended Rail Impression");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Recommended Rail Impression");
		}
	}

	public void verifyToastMessageImpressionEventInPackSelectionPage(String userType) throws Exception {
		extent.HeaderChildNode("Verify Toast Message Impression event in pack selection page");
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			click(PWAHamburgerMenuPage.objProfileIconWEB, "Profile Icon");
			click(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Subscription"), "My Subscription");
			click(PWAHamburgerMenuPage.objBrowseAllPacks, "Browse All Packs");
			waitTime(2000);
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "pack_selection");
			mixpanel.FEProp.setProperty("Page Name", "home");
			mixpanel.FEProp.setProperty("Toast Message", "You are already subscribed to ZEE5 Club Pack");
			mixpanel.ValidateParameter(local.getItem("ID"), "Toast Message Impression");
		}else {
			extent.extentLoggerPass("Info", "Scenario is not applicable for Guest/NonSubscribed User");
		}
	}

	public void verifyPrepaidCodeResultEventForInvalid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Prepaid Code Result Event For Invalid code");
		String promocode = "Z56MSK93rJGDyi";
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			click(PWASubscriptionPages.objHaveACode, "Have A Code");
			click(PWASubscriptionPages.objEnterCode, "Enter Code");
			type(PWASubscriptionPages.objEnterCode, promocode, "Prepaid Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");

			if (userType.equals("Guest")) {
				mixpanel.FEProp.setProperty("Page Name", "account_info");
				mixpanel.FEProp.setProperty("Source", "pack_selection");
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "zeesub@mailnesia.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objEmailContinueButton,
							"Continue Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					click(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "123456", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordContinueButton, "Continue Button");
					mixpanel.FEProp.setProperty("Cost", "N/A");
					ResponseInstance.getUserData("zeesub@mailnesia.com","123456");
					ResponseInstance.getUserSettingsValues("zeesub@mailnesia.com","123456");
					mixpanel.NonSubcribedDetails();
				}
			} else {
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "pack_selection");
				String cost = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
				mixpanel.FEProp.setProperty("Transaction Currency", "INR");
				mixpanel.FEProp.setProperty("Cost", cost);
			}
			

			mixpanel.FEProp.setProperty("Element", "APPLY");
			mixpanel.FEProp.setProperty("Success", "false");
			
			mixpanel.FEProp.setProperty("Promo Code Type", "Prepaid");
			mixpanel.FEProp.setProperty("Promo Code", promocode);
			mixpanel.FEProp.setProperty("Current Subscription", "false");
			mixpanel.FEProp.setProperty("Failure Reason", "The coupon code entered has expired");
			
			
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Prepaid Code Result");

		}
	}


	public void verifyAdViewEventForFreeContent(String userType, String tabName) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Free Content");

			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tabName,"Free");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());

				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForTrailer(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Trailer Content");

			clickOnTrayContent(tabName,"trailer");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForCarouselContent(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Carousel Content");
			waitTime(5000);
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentInTray(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Tray");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad View Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitTime(4000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentInPlaylist(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Playlist");

			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdViewEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For Content played from Upnext rail");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);

			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdViewEventForContentFromSharedLink(String userType, String freeContentURL) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad View Event For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "N/A");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad View");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad View");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdForcedExitEventForFreeContent(String userType, String tabName) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Free Content");
			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tabName,"Free");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());

				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}
			}

		} else {
			logger.info("Ad is not available for the content");
			extent.extentLogger("Ad", "Ad is not available for the content");
		}

		waitTime(5000);

	}

	public void verifyAdForcedExitEventForTrailer(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Trailer Content");

			clickOnTrayContent(tabName,"trailer");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				
				mixpanel.FEProp.setProperty("Page Name",  pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForCarouselContent(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Carousel Content");
			waitTime(5000);
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentInTray(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Tray");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			waitTime(2000);
			mandatoryRegistrationPopUp(userType);
			waitTime(4000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentInPlaylist(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Playlist");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
	
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdForcedExitEventForContentFromUpnextRail(String userType, String freeContentURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For Content played from Upnext rail");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "N/A");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdForcedExitEventForContentFromSharedLink(String userType, String audioTrackURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Forced Exit Event For content played from Shared Link");
			getWebDriver().get(audioTrackURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Forced Exit");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Forced Exit");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdClickEventForFreeContent(String userType, String tabName) throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Free Content");

			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tabName,"Free");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				waitTime(3000);
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());

				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				waitTime(3000);
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdClickEventForTrailer(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Trailer Content");

			clickOnTrayContent(tabName,"trailer");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			}
		} else {
			logger.info("Ad is not available for the content");
			extent.extentLogger("Ad", "Ad is not available for the content");
		}

	}

	public void verifyAdClickEventForCarouselContent(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Carousel Content");
			waitTime(5000);
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentInTray(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Tray");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdViewClickForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Click Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentInPlaylist(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Playlist");

			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);

		}
	}

	public void verifyAdClickEventForContentFromUpnextRail(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For Content played from Upnext rail");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);

			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdClickEventForContentFromSharedLink(String userType, String freeContentURL) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Click Event For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "N/A");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				JSClick(PWAPlayerPage.objAdPlayerOverlay, "Ad");
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);

		}
	}

	public void verifyAdWatchDurationEventForFreeContentForceExit(String userType, String tabName)
			throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback for free content");

			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tabName,"Free");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				waitTime(10000);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}
	}

	public void verifyAdWatchDurationEventForTrailerForceExit(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Trailer Content");
			clickOnTrayContent(tabName,"trailer");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForCarouselContentForceExit(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Carousel Content");
			waitTime(5000);
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInTrayForceExit(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Tray");

			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSearchPageForceExit(String userType,
			String subtitleTrackContent) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);

			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageForceExit(String userType,
			String audioTrackContent) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			scrollDownWEB();
			scrollDownWEB();
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			mandatoryRegistrationPopUp(userType);

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInMegamenuForceExit(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Megamenu");
			waitTime(5000);
			mandatoryRegistrationPopUp(userType);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInPlaylistForceExit(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Playlist");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForContentFromUpnextRailForceExit(String userType, String audioTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For Content played from Upnext rail");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			verifyElementPresentAndClick(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");

			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");

			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSharedLinkForceExit(String userType, String freeContentURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user force quits the ad playback For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				mixpanel.FEProp.setProperty("Source", "N/A");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForFreeContentComplete(String userType, String tabName)
			throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback for free content");


			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tabName,"Free");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				Back(1);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForTrailerComplete(String userType,  String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Trailer Content");
			clickOnTrayContent(tabName,"trailer");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForCarouselContentComplete(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Carousel Content");
			waitTime(5000);
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInTrayComplete(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Tray");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSearchPageComplete(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageComplete(String userType,
			String audioTrackContent) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches ad playback For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);

			scrollDownWEB();
			scrollDownWEB();
			waitTime(2000);

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInMegamenuComplete(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Megamenu");
			waitTime(5000);
			mandatoryRegistrationPopUp(userType);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInPlaylistComplete(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Playlist");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));

				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForContentFromUpnextRailComplete(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches the ad playback For Content played from Upnext rail");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);

			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);


			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");
				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));

				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSharedLinkComplete(String userType, String freeContentURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user completly watches ad playback For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				waitForPlayerAdToComplete("Video Player");
				mixpanel.FEProp.setProperty("Source", "N/A");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				System.out.println("Current URL : " + id);
				ResponseInstance.getContentDetails(fetchContentID(id));

				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAddToWatchlistEventFromShowDetailPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add To Watchlist Event From Show Detail Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			String link = findElement(PWAPremiumPage.obj1stContentInShowDetailPage).getAttribute("data-minutelyurl");
			waitTime(5000);
			scrollDownWEB();
			scrollDownWEB();
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}else {
				click(PWAPremiumPage.objContentCardRemoveFromWatchlistBtn, "Remove From Watchlist icon");
				waitTime(3000);
				actions.moveToElement(contentCard).build().perform();
				click(PWAPlayerPage.objAddToWatchlist, "Add To Watchlist icon");
				waitTime(4000);
			}
			
			ResponseInstance.getContentDetails(fetchContentID(link));
			
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			mixpanel.FEProp.setProperty("Source", "search");
			mixpanel.FEProp.setProperty("Page Name", "show_detail");
			mixpanel.FEProp.setProperty("Element", "Watchlist");
			mixpanel.ValidateParameter(local.getItem("ID"), "Add To Watchlist");
		}
	}


	public void verifyAdWatchDurationEventForFreeContentSkipAd(String userType, String tabName)
			throws Exception {

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Watch Duration Event when user skips the ad playback for free content");


			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tabName,"Free");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
				
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "Content Language");
					mixpanel.FEProp.setProperty("Page Name", pageName());
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}

			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForTrailerSkipAd(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Trailer Content");
			clickOnTrayContent(tabName,"trailer");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

				
					mixpanel.FEProp.setProperty("Page Name", pageName());
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForCarouselContentSkipAd(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Carousel Content");
			waitTime(5000);
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Page Name", pageName());
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInTraySkipAd(String userType,String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Tray");
			extent.HeaderChildNode("Verify Ad View Event For Content played from Tray");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
		//	click(PWAPremiumPage.objThumbnail, "Content From a tray");
			clickOnTrayContent(tabName,"Free");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
				
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "Content Language");
					mixpanel.FEProp.setProperty("Page Name", pageName());
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSearchPageSkipAd(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "search");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromMyWatchlistPageSkipAd(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips ad playback For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			scrollDownWEB();
			scrollDownWEB();
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			mandatoryRegistrationPopUp(userType);
			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInMegamenuSkipAd(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Megamenu");
			waitTime(5000);
			mandatoryRegistrationPopUp(userType);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");
					mixpanel.FEProp.setProperty("Source", "home");
					mixpanel.FEProp.setProperty("Page Name", "movie_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentInPlaylistSkipAd(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Playlist");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");
					mixpanel.FEProp.setProperty("Source", "episode_detail");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdWatchDurationEventForContentFromUpnextRailSkipAd(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips the ad playback For Content played from Upnext rail");
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);

			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			mandatoryRegistrationPopUp(userType);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "episode_detail");
					mixpanel.FEProp.setProperty("Page Name", "episode_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}

				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}
		}
	}

	public void verifyAdWatchDurationEventForContentFromSharedLinkSkipAd(String userType, String freeContentURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Ad Watch Duration Event when user skips ad playback For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				if (checkElementDisplayed(PWAPlayerPage.objSkipAd, "SkipAd")) {
					Thread.sleep(5000);
					click(PWAPlayerPage.objSkipAd, "Skip Ad Button");

					mixpanel.FEProp.setProperty("Source", "N/A");
					mixpanel.FEProp.setProperty("Page Name", "movie_detail");
					mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
					String id = getWebDriver().getCurrentUrl();
					System.out.println("Current URL : " + id);
					ResponseInstance.getContentDetails(fetchContentID(id));
					LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
					if (userType.equals("Guest")) {
						mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Watch Duration");
					} else {
						mixpanel.ValidateParameter(local.getItem("ID"), "Ad Watch Duration");
					}
				} else {
					System.out.println("Skip Ad Button is not displayed");
				}
			} else {
				logger.info("Skip Ad is not available for the content");
				extent.extentLogger("Skip Ad", "Skip Ad is not available for the content");
			}

			waitTime(5000);
		}
	}

	public void verifyCTAsEventForIcons(String userType, String icon) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event when user clicks on Search, Language, Profile icon");
		waitTime(5000);
		if (icon.equalsIgnoreCase("Search")) {
			click(PWAHomePage.objSearchBtn, "Search Icon");
			waitTime(5000);
		}

		if (icon.equalsIgnoreCase("Language")) {
			click(PWAHomePage.objLanguageBtn, "Language button");
			waitTime(5000);
		}

		if (!(userType.equalsIgnoreCase("Guest"))) {
			if (icon.equalsIgnoreCase("Profile")) {
				click(PWALandingPages.objWebProfileIcon, "Profile Icon");
				waitTime(5000);
			}
		}

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", "Language");

		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
		}
	}

	public void verifyCTAsEventForSubscribeBtn(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify CTAs Event when user clicks on subscription option");
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			waitTime(5000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "N/A");
			mixpanel.FEProp.setProperty("Page Name", "home");
			mixpanel.FEProp.setProperty("Element", "Get premium");

			if (UserType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
			}
		}
	}

	public void verifyCTAsEventForOptionInHamburger() throws Exception {
		extent.HeaderChildNode("Verify CTAs Event when user clicks on any option in hamburger menu");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");
		waitTime(2000);
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", "More Settings");

		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
		}
	}

	public void verifyCTAsEventHeader(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		navigateToAnyScreenOnWeb(tabName);

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		mixpanel.FEProp.setProperty("Source", "N/A");
		mixpanel.FEProp.setProperty("Page Name", "home");
		mixpanel.FEProp.setProperty("Element", tabName);

		waitTime(8000);
		if (UserType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTAs");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTAs");
		}
	}

	public void verifyAdInitializedEventForFreeContent(String userType, String tab) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Free Content");
			mandatoryRegistrationPopUp(userType);
			clickOnTrayContent(tab,"Free");
			mandatoryRegistrationPopUp(userType);
			waitTime(6000);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				
				String id = getWebDriver().getCurrentUrl();
				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (UserType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

			waitTime(5000);

		}
	}

	public void verifyAdInitializedEventForTrailer(String userType, String tabName) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Trailer Content");

			clickOnTrayContent(tabName,"trailer");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 0) {
				JSClick(By.xpath(".//*[@class='iconsWrap getPremiumBtn']//child::*[@class='playBtn']"),"Play Icon");
				mixpanel.FEProp.setProperty("Source", "show_detail");
			}else {
				mixpanel.FEProp.setProperty("Source", "home");
			}
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}

		}

	}

	public void verifyAdInitializedEventForCarouselContent(String userType,String tab) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Carousel Content");
			navigateToAnyScreenOnWeb(tab);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentInTray(String userType,String tab) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Tray");
			clickOnTrayContent(tab,"Free");
//			click(PWAPremiumPage.objThumbnail, "Content From a tray");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "Content Language");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentFromSearchPage(String userType, String subtitleTrackContent)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content From Search Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, subtitleTrackContent + "\n",
					"Search Edit box: " + subtitleTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(subtitleTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(subtitleTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "search");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentFromMyWatchlistPage(String userType, String audioTrackContent)
			throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			waitTime(4000);
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentInMegamenu(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Megamenu");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
			actions.moveToElement(contentCard).build().perform();

			click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "home");
				mixpanel.FEProp.setProperty("Page Name", "movie_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentInPlaylist(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Playlist");

			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objContentInPlaylistbtn, "Content in playlist");
			waitTime(5000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}
			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyAdInitializedEventForContentFromUpnextRail(String userType, String tabName)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For Content played from Upnext rail");
		
			navigateToAnyScreenOnWeb(tabName);
			mandatoryRegistrationPopUp(userType);
			waitTime(5000);
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
			mandatoryRegistrationPopUp(userType);
			waitForPlayerAdToComplete("Video Player");
			
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			mandatoryRegistrationPopUp(userType);

			waitForPlayerAdToComplete("Video Player");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			
			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "episode_detail");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyAdInitializedEventForContentFromSharedLink(String userType, String freeContentURL)
			throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Ad Initialized Event For content played from Shared Link");
			mandatoryRegistrationPopUp(userType);
			String site = getParameterFromXML("url");
			freeContentURL = site + freeContentURL;
			System.out.println(freeContentURL);
			getWebDriver().get(freeContentURL);
			logger.info("Opened link : " + freeContentURL);
			extent.extentLogger("", "Opened link : " + freeContentURL);
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");

				mixpanel.FEProp.setProperty("Source", "N/A");
				mixpanel.FEProp.setProperty("Page Name", "episode_detail");
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Initialized");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Initialized");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
			waitTime(5000);
		}
	}

	public void verifyLoginScreenDisplayEventByClickingOnLoginInRegistrationPopUp(String userType, String keyword)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Login Screen Display Event during content playback post clicking on login in registration popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResultTxt(keyword), "Search Result");
			click(PWAPremiumPage.obj1stContentInShowDetailPage, "Content Card");
			waitForElement(PWALoginPage.objLoginLink, 20, "Login Link");
			click(PWALoginPage.objLoginLink, "Login Link");
			waitTime(5000);

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.FEProp.setProperty("Source", "episode_detail");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Screen Display");
		}
	}

	public void verifyAdClickForContentFromMyWatchlistPage(String userType, String audioTrackContent) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Ad Click Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, audioTrackContent + "\n", "Search Edit box: " + audioTrackContent);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResultTxt(audioTrackContent), 10, "Search Result");

			click(PWASearchPage.objSearchResultTxt(audioTrackContent), "Search Result");
			mandatoryRegistrationPopUp(userType);
			waitTime(2000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();
			if (checkElementDisplayed(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon")) {
				click(PWAPremiumPage.objContentCardAddToWatchlistBtn, "Add To Watchlist icon");
			}
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItem, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

			if (checkElementDisplayed(PWAPlayerPage.objAd, "Ad")) {
				logger.info("Ad play in progress");
				extent.extentLogger("Ad", "Ad play in progress");
				click(PWAPlayerPage.objAd, "Ad");

				mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");
				mixpanel.FEProp.setProperty("Page Name", pageName());
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				String id = getWebDriver().getCurrentUrl();
				ResponseInstance.getContentDetails(fetchContentID(id));
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameter(local.getItem("guestToken"), "Ad Clicks");
				} else {
					mixpanel.ValidateParameter(local.getItem("ID"), "Ad Clicks");
				}

			} else {
				logger.info("Ad is not available for the content");
				extent.extentLogger("Ad", "Ad is not available for the content");
			}
		}
	}

	public void verifyLoginUsernameEnteredEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Username Entered Event");

			String Username = getParameterFromXML("NonSubscribedUserName");
			click(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			click(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			click(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, "hgcsujg", "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Username Entered");
		}
	}

	public void verifyLoginPasswordEnteredEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Password Entered Event");

			String Username = getParameterFromXML("NonSubscribedUserName");
			click(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			click(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			click(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, "hgcsujg", "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(5000);

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "sign_in");

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Login Password Entered");
		}
	}

	public void verifyRentalPurchaseCallInitiatedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Rental Purchase Call Initiated Event");

		if (!(userType.equals("SubscribedUser"))) {
			Thread.sleep(5000);
			navigateToAnyScreenOnWeb("ZEEPLEX");
			waitTime(4000);
			scrollByWEB();

			verifyElementExist(PWAHomePage.objRentforINR, "RentforINR");
			JSClick(PWAHomePage.objRentforINR, "RentforINR");
			waitTime(4000);
			JSClick(PWAHomePage.objRentforINRPopupRentforINRBtn, "RentforINR Btn");

			mixpanel.FEProp.setProperty("Page Name", "payment_page");
			mixpanel.FEProp.setProperty("Payment Method", "mastercard");
			Thread.sleep(5000);

			String TokenORID = null;
			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					mixpanel.FEProp.setProperty("Source", "account_info");
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage,
							"Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
					waitTime(10000);

					TokenORID = (String) js.executeScript("return window.localStorage.getItem('ID')");
					System.out.println(TokenORID);
					waitTime(10000);
				}
			} else if (userType.equals("NonSubscribedUser")) {
				mixpanel.FEProp.setProperty("Source", "pack_selection");
				Thread.sleep(20000);
				TokenORID = (String) js.executeScript("return window.localStorage.getItem('ID')");
				System.out.println(TokenORID);
				waitTime(10000);
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5123456789012346", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(20000);
			getWebDriver().switchTo().defaultContent();
			waitTime(5000);

			Mixpanel.ValidateParameter(TokenORID, "Rental Purchase Call Initiated");

		}
	}

	public void verifyRentalPurchaseCallReturnedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Rental Purchase Call Returned Event");

		if (!(userType.equals("SubscribedUser"))) {
			Thread.sleep(5000);
			navigateToAnyScreenOnWeb("ZEEPLEX");
			waitTime(4000);
			scrollByWEB();

			verifyElementExist(PWAHomePage.objRentforINR, "RentforINR");
			JSClick(PWAHomePage.objRentforINR, "RentforINR");
			waitTime(4000);
			JSClick(PWAHomePage.objRentforINRPopupRentforINRBtn, "RentforINR Btn");

			mixpanel.FEProp.setProperty("Page Name", "payment_page");
			mixpanel.FEProp.setProperty("Source", "payment_failure");
			mixpanel.FEProp.setProperty("Payment Method", "mastercard");

			Thread.sleep(5000);

			String TokenORID = null;
			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {

					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnInSubscriptionPage,
							"Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
					waitTime(10000);

					TokenORID = (String) js.executeScript("return window.localStorage.getItem('ID')");
					System.out.println(TokenORID);
					waitTime(10000);
				}
			} else if (userType.equals("NonSubscribedUser")) {
				Thread.sleep(20000);
				TokenORID = (String) js.executeScript("return window.localStorage.getItem('ID')");
				System.out.println(TokenORID);
				waitTime(10000);
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5123456789012346", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(20000);
			getWebDriver().switchTo().defaultContent();
			waitTime(5000);
			click(PWASubscriptionPages.objZeeLink, "Zee link");
			waitTime(5000);

			Mixpanel.ValidateParameter(TokenORID, "Rental Purchase Call Returned");

		}
	}

	public void verifyMegamenuThumbnailClickEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Megamenu Thumbnail Click Event");
		waitTime(15000);

		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(10000);

		mixpanel.FEProp.setProperty("Source", "home");
		mixpanel.FEProp.setProperty("Page Name", "movie_detail");
		mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");

		String id = getWebDriver().getCurrentUrl();
		ResponseInstance.getContentDetails(fetchContentID(id));
		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);

		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Megamenu Thumbnail Click");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Megamenu Thumbnail Click");
		}

	}

	public void verifyPopupLaunchEventTriggerforParentControl(String userType) throws Exception {
		extent.HeaderChildNode("Verify Popup Launch Event Trigger for Parent Control");
		waitTime(3000);
		if (!userType.equals("Guest")) {
			verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "Parent control");
			waitTime(10000);
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			mixpanel.ValidateParameter(local.getItem("ID"), "Popup Launch");
		}
	}

	public void verifPopupLaunchEventTriggerforSubscribePage(String userType) throws Exception {
		extent.HeaderChildNode("verify Popup Launch Event Trigger for Subscribe");
		waitTime(3000);
		if (!userType.equals("SubscribedUser")) {
			verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscribe button");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			type(PWASubscriptionPages.objEmailIDTextField, Username, "Email ID");
			verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtn, "Proceed button");

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);

			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Popup Launch");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Popup Launch");
			}
		}
	}

	public void verifyCTAEventTriggerInEduauraaExpandIcon(String userType) throws Exception {
		extent.HeaderChildNode("verify CTA Event Trigger In Eduauraa Expand Icon");
		waitTime(3000);
		navigateToAnyScreenOnWeb("Kids");
		waitTime(2000);
		partialScrollDown();
		verifyElementPresentAndClick(PWAHomePage.objFirstContentCardOfTray("Learn with Eduauraa"),
				"First content of Eduauraa tray");
		verifyElementPresentAndClick(PWAKidsPage.objFirstContentOfEduauraa, "First content of Eduaraa");
		partialScrollDown();
		verifyElementPresentAndClick(PWAKidsPage.objExpanderIconInEduauraaPlayback, "Expander Icon");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTA");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTA");
		}
	}

	public void verifyPromoCodeResultEventTriggered(String userType, String tab) throws Exception {
		extent.HeaderChildNode("verify CTA Event Trigger In Eduauraa Expand Icon");
		navigateToAnyScreenOnWeb(tab);
		verifyElementPresentAndClick(PWAHomePage.objCarouselContentPremiumTag, "Premium Content");
		verifyElementPresentAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen, "Subscribed");
		verifyElementPresent(PWAPlayerPage.objHavePromoCodeEditTxt, "Have a Promo Code ?");
		type(PWAPlayerPage.objHavePromoCodeEditTxt, "PNB20", "Have a Promo Code ?");
		verifyElementPresentAndClick(PWAPlayerPage.objProceedBtn, "Proceed Button");

		mixpanel.FEProp.setProperty("Pop Up Group", "Skippable");
		mixpanel.FEProp.setProperty("Pop Up Name", "Registration");
		mixpanel.FEProp.setProperty("Pop Up Type", "Mandatory Registration");
		mixpanel.FEProp.setProperty("Promo Code", "PNB20");
		mixpanel.FEProp.setProperty("Success", "true");
		

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "Promo Code Result");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "Promo Code Result");
		}
	}

	public void verifyCTAEventTriggerInEduauraaClickOneTermAndCondition(String userType) throws Exception {
		extent.HeaderChildNode(
				"verify CTA Event Trigger In Eduauraa playback page when user click on Tearm and conditions");
		waitTime(3000);
		navigateToAnyScreenOnWeb("Kids");
		waitTime(2000);
		partialScrollDown();
		verifyElementPresentAndClick(PWAHomePage.objFirstContentCardOfTray("Learn with Eduauraa"),
				"First content of Eduauraa tray");
		verifyElementPresentAndClick(PWAKidsPage.objFirstContentOfEduauraa, "First content of Eduaraa");
		waitTime(2000);
		verifyElementPresentAndClick(PWAKidsPage.objTermAndCondition, "Tearm and Conditions");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTA");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTA");
		}
	}

	public void verifyCTAEventTriggerInEduauraaClickOnePrivacyPolicy(String userType) throws Exception {
		extent.HeaderChildNode("verify CTA Event Trigger In Eduauraa playback page when user click on privacy policy");
		waitTime(3000);
		navigateToAnyScreenOnWeb("Kids");
		waitTime(2000);
		partialScrollDown();
		verifyElementPresentAndClick(PWAHomePage.objFirstContentCardOfTray("Learn with Eduauraa"),
				"First content of Eduauraa tray");
		verifyElementPresentAndClick(PWAKidsPage.objFirstContentOfEduauraa, "First content of Eduaraa");
		waitTime(2000);
		verifyElementPresentAndClick(PWAKidsPage.objPrivacyPolicy, "privacy policy");

		local = ((ChromeDriver) getWebDriver()).getLocalStorage();
		fetchUserType(local);
		if (userType.equals("Guest")) {
			mixpanel.ValidateParameter(local.getItem("guestToken"), "CTA");
		} else {
			mixpanel.ValidateParameter(local.getItem("ID"), "CTA");
		}
	}

	public String fetchContentID(String url) {
		String contentID = null;
		String[] id = url.split("/");
		for (int i = 0; i < id.length; i++) {
			if (id[i].contains("0-")) {
				contentID = id[i];
			}
		}
		if(findElements(By.xpath(".//*[@class='trailerInfoContainer']")).size() > 0) {
			ResponseInstance.trailer = true;
		}
		return contentID;
	}

	public void fetchUserType(LocalStorage local) {
		try {
			Properties KEYVALUE = new Properties();
			for (String key : local.keySet()) {
				KEYVALUE.setProperty(key, local.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.startsWith("mp_")) {
					JSONObject jsonObj = new JSONObject(KEYVALUE.getProperty(key));
					mixpanel.FEProp.setProperty("isPWA", jsonObj.get("isPWA").toString());
					mixpanel.FEProp.setProperty("User Type", jsonObj.get("User Type").toString());
					break;
				}
			}
		} catch (Exception e) {

		}
	}
	
	public String fetchMpData(LocalStorage local) {
		try {
			Properties KEYVALUE = new Properties();
			for (String key : local.keySet()) {
				KEYVALUE.setProperty(key, local.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.startsWith("mp_")) {
					JSONObject jsonObj = new JSONObject(KEYVALUE.getProperty(key));
					return jsonObj.get("distinct_id").toString();
				}
			}
		} catch (Exception e) {

		}
		return "";
	}

	public void relaunchParentalControl() throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getWebDriver().quit();
		new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
		ZeeWEBPWAMixPanelLoginForParentalControl(userType);
	}

	public void selectLanguages() throws Exception {
		extent.extentLogger("", "Language selection");
		waitTime(5000);
		click(PWAHamburgerMenuPage.objLanguageBtnWeb, "Language Button");
		waitTime(2000);
		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, 2, "Content Languages");
		waitTime(2000);
		unselectAllContentLanguages();
		clickElementWithWebLocator(PWAHamburgerMenuPage.objUnselectedKannadaContentLanguage);
		logger.info("Selected content language Kannada");
		extent.extentLogger("", "Selected content language Kannada");
		clickElementWithWebLocator(PWAHamburgerMenuPage.objUnselectedHindiContentLanguage);
		logger.info("Selected content language Hindi");
		extent.extentLogger("", "Selected content language Hindi");
		clickElementWithWebLocator(PWAHamburgerMenuPage.objUnselectedEnglishContentLanguage);
		logger.info("Selected content language English");
		extent.extentLogger("", "Selected content language English");
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
		waitTime(3000);
	}

	public void unselectAllContentLanguages() throws Exception {
		List<WebElement> selectedLanguages = getWebDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);
		for (int i = 0; i < selectedLanguages.size(); i++) {
			clickElementWithWebElement(selectedLanguages.get(i));
		}
	}

	public void waitForElementAndClick(By locator, int seconds, String message) throws InterruptedException {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				getWebDriver().findElement(locator).click();
				logger.info("Clicked element " + message);
				extent.extentLogger("clickedElement", "Clicked element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to click element " + message);
					extent.extentLoggerFail("failedClickElement", "Failed to click element " + message);
				}
			}
		}
	}

	public void navigateToHome() {
		String url = getParameterFromXML("url");
		getWebDriver().get(url);
	}

	public static void scrollDownByY(int y) {
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("window.scrollBy(0," + y + ")", "");
	}

	public boolean verifyAutoPlay(String Tabname) throws Exception {
		boolean autoplayingItemsPresent = false;
		boolean autoPlayed = false;
		int autoplayItem = 0;
		// String languageSmallText = allSelectedLanguages();
		Response tabResponse = ResponseInstance.getResponseForPages(Tabname.toLowerCase(), "en,hi,kn");
		int carouselItemsCount = tabResponse.jsonPath().get("buckets[0].items.size()");
		System.out.println(carouselItemsCount);
		if (carouselItemsCount > 7)
			carouselItemsCount = 7;
		for (int i = 0; i < carouselItemsCount; i++) {
			try {
				if (tabResponse.jsonPath().get("buckets[0].items[" + i + "].tags[0]").toString().equals("Autoplay")) {
					autoplayItem = i;
					autoplayingItemsPresent = true;
					break;
				}
			} catch (Exception e) {
			}
		}
		if (autoplayingItemsPresent == false) {
			logger.info("Autoplay could not be verified because no Autoplaying Carousel Items");
			extent.extentLoggerWarning("Autoplay",
					"Autoplay could not be verified because no Autoplaying Carousel Items");
		} else {
			navigateToAnyScreenOnWeb(Tabname);
			click(PWAHamburgerMenuPage.carouselDot(autoplayItem + 1), "Carousel Dot " + (autoplayItem + 1) + "");
			waitTime(3000);
			if (verifyElementPresent(PWANewsPage.objRight, "Right facing arrow on Carousel")) {
				logger.info("Autoplay is begun and verified by presence of Right facing arrow");
				extent.extentLoggerPass("", "Autoplay is begun and verified by presence of Right facing arrow");
				autoPlayed = true;
			} else {
				logger.error("Autoplay has failed");
				extent.extentLoggerFail("", "Autoplay has failed");
				autoPlayed = false;
			}
			if (verifyElementPresent(PWANewsPage.objLeft, "Left facing arrow on Carousel")) {
				logger.info("Autoplay is begun and verified by presence of Left facing arrow");
				extent.extentLoggerPass("", "Autoplay is begun and verified by presence of Left facing arrow");
			} else {
				logger.error("Autoplay has failed");
				extent.extentLoggerFail("", "Autoplay has failed");
			}
		}
		if (autoPlayed)
			return true;
		else
			return false;
	}

	public String pageName() throws Exception {
		waitTime(2000);
		PropertyFileReader handler = new PropertyFileReader("properties/MixpanelKeys.properties");
		String pageNameTxt = js.executeScript("return arguments[0].text", findElement(By.xpath(".//*[@class='noSelect active ']"))).toString();
		if (pageNameTxt.equals("TV Shows")) {
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 1) {
				return handler.getproperty("episode_details".toLowerCase());
			}
		}
		return handler.getproperty((pageNameTxt.replaceAll(" ","") + "_details").toLowerCase());
	}
	
	public String landingPageName() throws Exception {
		waitTime(2000);
		PropertyFileReader handler = new PropertyFileReader("properties/MixpanelKeys.properties");
		String pageNameTxt = js.executeScript("return arguments[0].text", findElement(By.xpath(".//*[@class='noSelect active ']"))).toString();
		if (pageNameTxt.equals("TV Shows")) {
			if (findElements(By.xpath(".//*[@class='episodeDetailContainer']")).size() == 1) {
				return handler.getproperty("episode_details".toLowerCase());
			}
		}
		return handler.getproperty((pageNameTxt.replaceAll(" ","") + "_View").toLowerCase());
	}

	public void clickOnCarousel(String tabName, String typeOfContent) throws Exception {
		extent.HeaderChildNode("Carousel");
		navigateToAnyScreenOnWeb(tabName);
		String contentTitle = ResponseInstance.getPageResponse(tabName, typeOfContent);
		System.out.println(contentTitle);
		if (!contentTitle.equals("NoContent")) {
			JSClick(PWAHomePage.objcarouselContent(contentTitle), contentTitle);
		}
		waitTime(10000);
	}

	public void oldLanguage(String language) {
		try {
			Properties KEYVALUE = new Properties();
			for (String key : local.keySet()) {
				KEYVALUE.setProperty(key, local.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.startsWith("settings")) {
					JSONObject jsonObj = new JSONObject(KEYVALUE.getProperty(key));
					mixpanel.FEProp.setProperty(language, jsonObj.get("content_language").toString());
					break;
				}
			}
		} catch (Exception e) {
			
		}
	}

	public void clickOnTrayContent(String tabName, String typeOfContent) throws Exception {
		navigateToAnyScreenOnWeb(tabName);
		ArrayList<String> contentTitle = ResponseInstance.getTrayResponse(tabName, typeOfContent);
//		scrollToElement(PWAHomePage.objtrayname(contentTitle.get(0)));
		swipeTillTray(100,contentTitle.get(1)," ");
		ScrollToTheElementWEB(PWAHomePage.objtrayname(contentTitle.get(0)));
		partialScroll();
		waitTime(8000);
		for (int i = 0; i <= 20; i++) {
			if(verifyElementDisplayed(PWAHomePage.objTrayContentIcon(contentTitle.get(0), contentTitle.get(1)))) {
				JSClick(PWAHomePage.objTrayContentIcon(contentTitle.get(0), contentTitle.get(1)),
						"clicked on Content " + contentTitle.get(1));
				mixpanel.FEProp.setProperty("Carousal Name",contentTitle.get(0));
				break;
			}else {
				click(PWAHomePage.objNextIcon(contentTitle.get(0)), "Next Icon");
			}
		}
	}

	public boolean scrollToElement(By element) throws Exception {
		for (int i = 1; i <= 10; i++) {
			scrollByWEB();
			if (findElements(element).size() > 0) {
				return true;
			}
		}
		return false;
	}

	public static void partialScroll() {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(0,500)", "");
	}
	
	
	public void playerScrubToStart() {
		try {
			WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
			WebElement progressBar = getWebDriver().findElement(PWAPlayerPage.objPlayerProgressBar);
			Actions action = new Actions(getWebDriver());
			action.clickAndHold(scrubber).moveToElement(progressBar,-390 ,0).release().perform();
			logger.info("Swiped to the beginning");
			extent.extentLogger("", "Swiped to the beginning");
		} catch (Exception e) {
			logger.info("Swiped to the beginning");
			extent.extentLogger("", "Swiped to the beginning");
		}
	}
	
	public void verifySettingChangedEventAfterPinIsSet(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {		
			extent.HeaderChildNode("Verify Setting Changed Event when Parental Control Age is Set");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			waitTime(2000);
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";			
			if (userType.equals("NonSubscribedUser")) {
				password=getParameterFromXML("NonSubscribedUserPassword");
			} else if (userType.equals("SubscribedUser")) {
				password=getParameterFromXML("SubscribedPassword");
			}			
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			try {
				findElement(PWAHamburgerMenuPage.objUnslectedNoRestrictions);
				click(PWAHamburgerMenuPage.objUnslectedNoRestrictions,"No Restrictions");
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			}catch(Exception e) {}
			click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict 13+ Content");
			click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");

			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "parental_control");
			mixpanel.FEProp.setProperty("Setting Changed", "Parental Control PIN Set");
			mixpanel.FEProp.setProperty("Element", "Set parental lock");
			mixpanel.FEProp.setProperty("Parent Control Setting", "U/A");
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			
			mixpanel.ValidateParameter(local.getItem("ID"), "Setting Changed");
			
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				password=getParameterFromXML("NonSubscribedUserPassword");
			} else if (userType.equals("SubscribedUser")) {
				password=getParameterFromXML("SubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			try {
				findElement(PWAHamburgerMenuPage.objUnslectedNoRestrictions);
				click(PWAHamburgerMenuPage.objUnslectedNoRestrictions,"No Restrictions");
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(2000);
			}catch(Exception e) {}
		}
	}
	
	public void verifyPopUpCTAsEventForCompleteProfilePopUp(String userType) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("Verify Pop Up CTAs Event for Complete Profile popup");
		//	relaunch();
			logout();
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			extent.HeaderChildNode("Login through incomplete profile account");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, "indaus24@gmail.com", "Email Field");
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, "123456", "Password field");
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
			checkElementDisplayed(PWAHomePage.objSearchField, "Search field");
			String keyword = getParameterFromXML("keyword");
			type(PWAHomePage.objSearchField, keyword, "Search");
			waitTime(5000);
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
			waitTime(3000);
			click(PWAPremiumPage.objPlayBtn, "Play Button");
			waitTime(4000);
			checkElementDisplayed(CompleteYourProfilePopUp.objCompleteYourProfileTxt, "Complete Your Profile");
			waitTime(6000);
			
			mixpanel.FEProp.setProperty("Source", "show_detail");
			mixpanel.FEProp.setProperty("Page Name","episode_detail");
			mixpanel.FEProp.setProperty("Element", "Cross");
			mixpanel.FEProp.setProperty("Pop Up Group", "Skippable");
			mixpanel.FEProp.setProperty("Pop Up Name", "Profile Update");
			mixpanel.FEProp.setProperty("Pop Up Type", "Mandatory Registration");
			
			String id = getWebDriver().getCurrentUrl();
			System.out.println("Current URL : " + id);
			ResponseInstance.getContentDetails(fetchContentID(id));
			
			click(PWAHomePage.objCreateNewAccountPopUpClose, "Close icon");
			waitTime(6000);
			
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			ResponseInstance.getUserData("indaus24@gmail.com","123456");
			mixpanel.ValidateParameter(local.getItem("ID"), "Pop Up CTAs");
		}
	}
	
	public void videoViewExit(String userType,String tab,int assetType,String assetSubtype,String event,int scenario,String requiredBusiness) throws Exception {
		String source="",page="",verticalIndex="1",sessionID="",convivaID="";
		try {
			navigateToHome();
			selectLanguages();
			navigateToAnyScreenOnWeb(tab);	
			waitTime(5000);
			mandatoryRegistrationPopUp(UserType);		
			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			String contentLang=fetchContentLanguage(local);	
			ArrayList<String> trayValues=ResponseInstance.getTrayValues(userType,tab,assetType,assetSubtype,requiredBusiness,contentLang,scenario);
			String contentID=trayValues.get(3);
			extent.extentLogger("", "Content ID fetched from API : "+contentID);	
			logger.info("Content ID fetched from API : "+contentID);			
			if(contentID.equals("")) {
				extent.extentLoggerWarning("", "Content not available in API");	
				logger.info("Content not available in API");
			}
			else {
				String trayTitle=trayValues.get(0);
				extent.extentLogger("", "Tray title fetched from API : "+trayTitle);
				logger.info("Tray title fetched from API : "+trayTitle);
				String contentTitle=trayValues.get(1);
				extent.extentLogger("", "Content title fetched from API : "+contentTitle);
				logger.info("Content title fetched from API : "+contentTitle);
				String dataContentID=trayValues.get(2);
				extent.extentLogger("", "Data Content ID fetched from API : "+dataContentID);
				logger.info("Data Content ID fetched from API : "+dataContentID);		
				if(scenario==1 || scenario==2 || scenario==3 || scenario==5 || scenario==12) {
					String title=swipeTillTray(100, trayTitle, "\"" + trayTitle + "\" tray");
					HashMap<String,Integer> alltrays=ResponseInstance.findVerticalIndex(tab,trayTitle,contentLang);
					verticalIndex=String.valueOf(alltrays.get(trayTitle)+1);
					System.out.println("verticalIndex: "+verticalIndex);
					WebElement element = getWebDriver().findElement(PWALandingPages.objTrayWithTitle(title));
					((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
					JSClick(PWALandingPages.objAssetInTray(title,dataContentID),"Content card under tray "+title);
					try{ 
						String uiContentTitle=getElementPropertyToString("innerText",PWAPlayerPage.objContentTitleInPlayerPage,"");
						extent.extentLogger("", "Content title from UI: " + uiContentTitle);
						logger.info("Content title from UI: " + uiContentTitle);
					}
					catch(Exception e) {}
					source=getElementPropertyToString("innerText",PWAHomePage.objBreadCrumb(1),"");
					mixpanel.FEProp.setProperty("Tab Name", tab.toLowerCase());
				}
				if(scenario==6) {
					click(PWAHomePage.objSearchBtn, "Search icon");
					type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
					if(assetType==6) {
						click(PWASearchPage.objSearchNavigationTab("TV Shows"), "TV Shows tab");
						mixpanel.FEProp.setProperty("Tab Name", "tv shows");
					}
					if(assetSubtype.equals("episode")) {
						click(PWASearchPage.objSearchNavigationTab("Episodes"), "Episodes tab");
						mixpanel.FEProp.setProperty("Tab Name", "episodes");
					}
					if(assetSubtype.equals("movie")) {
						click(PWASearchPage.objSearchNavigationTab("Movies"), "Movies tab");	
						mixpanel.FEProp.setProperty("Tab Name", "movies");
					}
					waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
					verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
					source="search";
				}
				if(!userType.equals("SubscribedUser")) waitForPlayerAdToComplete("Video");	
				page=pageNameForPlayer(assetSubtype);	
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				sessionID=fetchSessionID(local);
				BigDecimal sessionId = new BigDecimal(sessionID);
				convivaID=fetchConvivaID(local);
				waitTime(10000);
				navigateToHome();	
				mixpanel.FEProp.setProperty("Page Name",page);
				mixpanel.FEProp.setProperty("Source", source);
				mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
				mixpanel.FEProp.setProperty("Player Version", "1.3.0");
				mixpanel.FEProp.setProperty("Video View", "1");
				mixpanel.FEProp.setProperty("Vertical Index", verticalIndex);
				mixpanel.FEProp.setProperty("New Quality", "Auto");
				mixpanel.FEProp.setProperty("Talamoos clickID", "N/A");
				mixpanel.FEProp.setProperty("Talamoos modelName", "N/A");
				mixpanel.FEProp.setProperty("Talamoos origin", "N/A");
				mixpanel.FEProp.setProperty("Preview status", "N/A");
				mixpanel.FEProp.setProperty("Video Autoplay", "true");
				mixpanel.FEProp.setProperty("Session ID", String.valueOf(sessionId));
				
				ResponseInstance.getContentDetailsForPlayer(contentID,assetSubtype);
				LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				if (userType.equals("Guest")) {
					mixpanel.ValidateParameterForPlayer(local.getItem("guestToken"), event,contentLang);
				} else {
					mixpanel.ValidateParameterForPlayer(local.getItem("ID"), event,contentLang);
				}
				mandatoryRegistrationPopUp(userType);
			}		
		}
		catch(Exception e) {
			extent.extentLoggerFail("", "Failed : "+userType+"-"+tab+"-"+assetType+"-"+assetSubtype+"-"+event);
			logger.error("Failed : "+userType+" - "+tab+" - "+assetType+" - "+assetSubtype+" - "+event);
		}
	}
	
	public String fetchConvivaID(LocalStorage local) {
		String convivaIDFromSetting="";
		try {
			Properties KEYVALUE = new Properties();
			for (String key : local.keySet()) {
				KEYVALUE.setProperty(key, local.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.contains("Conviva")) {
					JSONObject jsonObj = new JSONObject(KEYVALUE.getProperty(key));
					convivaIDFromSetting=jsonObj.get("clId").toString();
					System.out.println("convivaIDFromSetting:"+convivaIDFromSetting);
					break;
				}
			}
		} catch (Exception e) {}
		return convivaIDFromSetting;
	}
	
	public String fetchSessionID(LocalStorage local) {
		String sesionIDFromSetting="";
		try {
			Properties KEYVALUE = new Properties();
			for (String key : local.keySet()) {
				KEYVALUE.setProperty(key, local.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.contains("_mixpanel")) {
					JSONObject jsonObj = new JSONObject(KEYVALUE.getProperty(key));
					sesionIDFromSetting=jsonObj.get("Session ID").toString();
					break;
				}
			}
		} catch (Exception e) {}
		return sesionIDFromSetting;
	}
	
	public static ArrayList<String> getTrayValues (String userType,String tab,int assetType,String assetSubtype, String requiredType,String contentLanguages) {
		ArrayList<String> trayDetails=new ArrayList<String>();
		String trayTitle="",contentTitle="",contentDataID="",contentID="";
		boolean foundContent=false;
		main : for(int i=1;i<20;i++) {//Considering each tab may have upto 20 pages
			Response respContent = ResponseInstance.getResponseForPages(tab.toLowerCase(), i, contentLanguages);
			int bucketsSize=0;
			try{ bucketsSize=respContent.jsonPath().get("buckets.size()"); }
			catch(Exception e) {break;}
			if(bucketsSize==0) break;
			else {
				int iter=0,j=0;
				if(i==1) j=1;//In first page skip carousel
				else j=0; 
				for(iter=j;iter<bucketsSize;iter++) {//iterate through trays
					for(int k=0;k<4;k++) {//iterate through 4 cards of the tray
						try {
							int assetypeAPI=Integer.valueOf(respContent.jsonPath().get("buckets["+iter+"].items["+k+"].asset_type").toString());													
							if(assetypeAPI==assetType) {
								String assetSubtypeAPI=respContent.jsonPath().get("buckets["+iter+"].items["+k+"].asset_subtype").toString();
								String businesssType=respContent.jsonPath().get("buckets["+iter+"].items["+k+"].business_type").toString();
								String ageRating=respContent.jsonPath().get("buckets["+iter+"].items["+k+"].age_rating").toString();
								if(assetSubtypeAPI.equals(assetSubtype)) {
									trayTitle=respContent.jsonPath().get("buckets["+iter+"].title").toString();
									System.out.println("trayTitle : "+trayTitle);
									contentTitle=respContent.jsonPath().get("buckets["+iter+"].items["+k+"].title").toString();			
									contentDataID=respContent.jsonPath().get("buckets["+iter+"].items["+k+"].id").toString();
									if(userType.equals("Guest")) {
										//Guest user - free content - not A content
										if(requiredType.equalsIgnoreCase("free") && !businesssType.equals("premium_downloadable") && !ageRating.equals("A")) {
											contentID=contentDataID;
											foundContent=true;
										}
									}
									else {
										//Non Sub user and sub user - free content 
										if(requiredType.equalsIgnoreCase("free") && !businesssType.equals("premium_downloadable")) {
											contentID=contentDataID;
											foundContent=true;
										}
									}					
									if(requiredType.equalsIgnoreCase("premium")) {
										if(userType.equals("SubscribedUser") && businesssType.equals("premium_downloadable")) {
											contentID=contentDataID;
											foundContent=true;
										}
										else if(businesssType.equals("premium_downloadable")){		
											int relatedsize=0,trailerSize= 0,seasonSize=0,orderID=0,previewSize=0;
											Response contentDetails=ResponseInstance.getContentDetails(contentDataID, "content");
											orderID = contentDetails.jsonPath().get("orderid");
											relatedsize = contentDetails.jsonPath().get("related.size()");
											for(int z=0;z<relatedsize;z++) {
												try {
													if(contentDetails.jsonPath().get("related["+z+"].asset_subtype").equals("trailer")) {
														contentID=contentDetails.jsonPath().get("related["+z+"].id").toString();													
														foundContent=true;
													}
												}catch(Exception e) {}
											}
											if(relatedsize==0) {
												try {
													trailerSize=contentDetails.jsonPath().get("tvshow_details.trailers.size()");
													contentID=contentDetails.jsonPath().get("tvshow_details.trailers["+(trailerSize-1)+"]id").toString();	
													foundContent=true;
												}catch(Exception e) {}
											}
											if(trailerSize==0) {
												try {
													String tvshowid=contentDetails.jsonPath().get("tvshow.id");
													Response showDetails=ResponseInstance.getContentDetails(tvshowid, "original");
													seasonSize=showDetails.jsonPath().get("seasons.size()");
													previewSize=showDetails.jsonPath().get("seasons["+(seasonSize-1)+"].previews.size()");
													for(int l=0;l<previewSize;l++) {
														int temporderid=Integer.valueOf(showDetails.jsonPath().get("seasons["+(seasonSize-1)+"].previews["+l+"].orderid").toString());
														if(temporderid==orderID) {
															contentID=showDetails.jsonPath().get("seasons["+(seasonSize-1)+"].previews["+l+"].id").toString();
															foundContent=true;
															break;
														}
													}
												}
												catch(Exception e) {}
											}
										}	
									}
								}
							}
						}catch(Exception e) {
							System.out.println("Tab "+tab+", page number : "+i+", bucket : "+iter+", card "+k+" caused exception");
						}
						if(foundContent==true) break;
					}
					if(foundContent==true) break;
				}
				if(foundContent==true) break;
			}
		}
		trayDetails.add(trayTitle);//tray title					
		trayDetails.add(contentTitle);//content title
		trayDetails.add(contentDataID);//data content id
		trayDetails.add(contentID);//content id
		return trayDetails;
	}
	
	public String pageNameForPlayer(String assetType) throws Exception {
		if(assetType.equalsIgnoreCase("episode")) return "episode_detail";
		else if(assetType.equalsIgnoreCase("movie")) return "movie_detail";
		else if(assetType.equalsIgnoreCase("trailer")) return "video_detail";
		else return "N/A";
	}
	
	public String swipeTillTray(int noOfSwipes, String trayTitle, String message) throws Exception {
		boolean foundTray = false;
		int i = 0, j = 0;
		String trayTitleInUI = "";
		main: for (i = 0; i <= noOfSwipes; i++) {
			ArrayList<WebElement> trays = new ArrayList<WebElement>();
			trays = (ArrayList<WebElement>) getWebDriver().findElements(PWALandingPages.objTrayTitle);
			for (int traycount = 0; traycount < trays.size(); traycount++) {
				if (trays.get(traycount).getAttribute("innerText").equalsIgnoreCase(trayTitle)) {
					trayTitleInUI = trays.get(traycount).getText();
					foundTray = true;
					break main;
				}
			}
			scrollDownByY(200);
			waitTime(2000);
			if(Math.floorMod(i, 10)==0) {
				logger.info("Scrolled down");
				extent.extentLogger("scrolled", "Scrolled down");
			}
			if (i == noOfSwipes) {
				logger.error(message + " is not displayed");
				extent.extentLoggerFail("failedToLocate", message + " is not displayed");
			}
		}
		if (foundTray == true) {
			for (j = i; j <= noOfSwipes; j++) {
				if (waitForElementPresence(PWALandingPages.objTrayTitleInUI(trayTitleInUI), 1,
						trayTitleInUI + " tray")) {
					break;
				} else {
					scrollDownByY(150);
					waitTime(2000);
					logger.info("Scrolled down");
					extent.extentLogger("scrolled", "Scrolled down");
					if (j == noOfSwipes) {
						logger.error(message + " is not displayed");
						extent.extentLoggerFail("failedToLocate", message + " is not displayed");
					}
				}
			}
		}
		if (!trayTitleInUI.equals("")) {// Scroll till first card of the tray
			for (int k = j; k <= noOfSwipes; k++) {
				try {
					getWebDriver().findElement(PWALandingPages.objFirstAssetInTrayIndex(trayTitleInUI));
					logger.info("Located first asset under " + trayTitleInUI);
					extent.extentLogger("firstAsset", "Located first asset under " + trayTitleInUI);
					scrollDownByY(150);
					return trayTitleInUI;
				} catch (Exception e) {
					scrollDownByY(150);
					waitTime(2000);
					logger.info("Scrolled down");
					extent.extentLogger("scrolled", "Scrolled down");
				}
			}
		}
		return "";
	}
	
	public boolean waitForElementPresence(By locator, int seconds, String message) throws Exception {
		try {
			WebDriverWait w = new WebDriverWait(getWebDriver(), seconds);
			w.until(ExpectedConditions.visibilityOfElementLocated(locator));
			logger.info(message + " is displayed");
			extent.extentLogger("element is displayed", message + " is displayed");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void verifyContentBucketSwipeEventForPremiumBenefitDrawer(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode("Verify Content Bucket Swipe Event For Premium Benefit Drawer");
			waitTime(3000);
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			waitTime(5000);

			click(PWASubscriptionPages.objMovieLink, "Movies Link");
			waitTime(8000);

			Actions act = new Actions(getWebDriver());
			act.dragAndDrop(findElement(PWASubscriptionPages.objMovieTray2),
					findElement(PWASubscriptionPages.objMovieTray1)).build().perform();

			waitTime(10000);
			mixpanel.FEProp.setProperty("Source", "home");
			mixpanel.FEProp.setProperty("Page Name", "pack_selection");
			mixpanel.FEProp.setProperty("Direction", "Right");

			local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			fetchUserType(local);
			if (userType.equals("Guest")) {
				mixpanel.ValidateParameter(local.getItem("guestToken"), "Content Bucket Swipe");
			} else {
				mixpanel.ValidateParameter(local.getItem("ID"), "Content Bucket Swipe");
			}
		}
	}
	
	

	public void ExecuteEvent_Set1(int slno,String userType,String tab,int assetType,String assetSubtype,String[] events,int scenario,String requiredBusiness) throws Exception {
		extent.HeaderChildNode(slno+" : "+tab+" Tab :"+requiredBusiness+" content, assetType:"+assetType+", assetSubtype:"+assetSubtype+" -> "+Scenario(scenario));
		System.out.println(slno+" : "+tab+" Tab :"+requiredBusiness+" content, assetType:"+assetType+", assetSubtype:"+assetSubtype+" -> "+Scenario(scenario));	
		video_ViewExit(slno,userType,tab,assetType,assetSubtype,events,scenario,requiredBusiness);
	}
	
	public String Scenario (int scenario) {
		if(scenario==1) {
			return ": Click free content";
		}
		else if(scenario==2) {
			return ": Click premium content";
		}
		else if(scenario==3) {
			return ": Click trailer content";
		}
		else if(scenario==4) {
			return ": Click carousel content";
		}
		else if(scenario==5) {
			return ": Click tray content";
		}
		else if(scenario==6) {
			return ": Click searched content";
		}
		else if(scenario==7) {
			return ": Click from Watchlist";
		}
		else if(scenario==8) {
			return ": Upnext play";
		}
		else if(scenario==9) {
			return ": Shared url";
		}
		else if(scenario==10) {
			return ": Click from Playlist";
		}
		else if(scenario==11) {
			return ": Click from Megamenu";
		}
		else if(scenario==12) {
			return ": Refresh url";
		}
		else return "";
	}
	
	
	public void video_ViewExit(int slno,String userType,String tab,int assetType,String assetSubtype,String[] events,int scenario,String requiredBusiness) throws Exception {
		
		String contentID="",dataContentID="",source="",page="",verticalIndex="1",sessionID="",preview="N/A",topCategory="",contentDuration="",tvShowID="",channelID="";
		String parentContentID="",horizontalIndex="",playListTrayTitle="",shareUrl="",weburl="";
		boolean evaluateMP=false,fromCollections=false;
		String mainUrl = getParameterFromXML("url");
		String playerHeadPositionVideoView="0",playerHeadPositionVideoExit="0";
		
		ArrayList<HashMap<String,String>> valuesPendingValidation = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> valuesPendingValidationMap = new HashMap<String,String>();
		
		if(scenario==7 && userType.equalsIgnoreCase("Guest")) {
			extent.extentLoggerWarning("", "Play from Watchlist is not applicable to Guest user");	
			logger.info("Play from Watchlist is not applicable to Guest user");
		}
		else if(!userType.equals("SubscribedUser") && scenario==8 && requiredBusiness.equals("premium")) {
			extent.extentLoggerWarning("", "Upnext cannot be verified for "+userType+" user, premium content");
			logger.info("Upnext cannot be verified for "+userType+" user, premium content");
		}
		else if(!userType.equals("SubscribedUser") && scenario==8 && assetSubtype.equals("trailer")) {
			extent.extentLoggerWarning("", "Upnext cannot be verified for "+userType+" user, trailer content");
			logger.info("Upnext cannot be verified for "+userType+" user, trailer content");
		}
		else {
			try {
				navigateToHome();
				selectLanguages();
				if(!userType.equalsIgnoreCase("Guest")) {
					clearContinueWatchingTray();
					waitTime(7000);
				}	
				navigateToAnyScreenOnWeb(tab);
				mandatoryRegistrationPopUp(UserType);
				waitTime(5000);					
				String zeeTab = getWebDriver().getWindowHandle();
				Set<String> handlesBeforeClick = getWebDriver().getWindowHandles();
				local = ((ChromeDriver) getWebDriver()).getLocalStorage();
				fetchUserType(local);
				String contentLang=fetchContentLanguage(local);	
				String guestToken=fetchGuestTokenLanguage(local);			
				ArrayList trayValues=new ArrayList();
				if(scenario==4) {
					ArrayList<HashMap<Integer,String>> carouselValuesUI=returnCarouselValuesFromUI();
					trayValues=ResponseInstance.getTrayValuesForCarousel(userType,tab,assetType,assetSubtype,requiredBusiness,contentLang,scenario,local.getItem("guestToken"),carouselValuesUI);
				}
				else {
					trayValues=ResponseInstance.getTrayValuesNew(userType,tab,assetType,assetSubtype,requiredBusiness,contentLang,scenario,local.getItem("guestToken"));
				}
				contentID=trayValues.get(3).toString();			
				if(contentID.equals("")) { 	    
					extent.extentLoggerWarning("", "Content not available in API");	
					logger.info("Content not available in API");
				}
				else {
					String trayTitle=trayValues.get(0).toString();
					extent.extentLogger("", "Tray title fetched from API : "+trayTitle);
					logger.info("Tray title fetched from API : "+trayTitle);
					String sourceContentTitle=trayValues.get(8).toString();
					extent.extentLogger("", "Tray content fetched from API : "+sourceContentTitle);
					logger.info("Tray content fetched from API : "+sourceContentTitle);
					String contentTitle=trayValues.get(1).toString();
					extent.extentLogger("", "Playing Content-> Title: "+contentTitle);
					logger.info("Playing Content->Title: "+contentTitle);
					dataContentID=trayValues.get(2).toString();				
					extent.extentLogger("", "Playing Content-> Data Content ID: "+dataContentID);
					logger.info("Playing Content-> Data Content ID: "+dataContentID);
					extent.extentLogger("", "Playing Content-> Content ID: "+contentID);	
					logger.info("Playing Content-> Content ID: "+contentID);
					tvShowID=trayValues.get(6).toString();
					if(!tvShowID.equals("")) {
						extent.extentLogger("", "Playing Content-> TV Show ID: "+tvShowID);
						logger.info("Playing Content-> TV Show ID: "+tvShowID);
					}
					channelID=trayValues.get(7).toString();
					if(!channelID.equals("")) {
						extent.extentLogger("", "Playing Content-> Channel ID: "+channelID);
						logger.info("Playing Content-> Channel ID: "+channelID);
					}
					int cardNo=Integer.valueOf(trayValues.get(4).toString());
					System.out.println("card no :"+cardNo);			
					if(scenario==1||scenario==2||scenario==3||scenario==5||scenario==7||scenario==8||scenario==9||scenario==10||scenario==12) {	
						if(tab.equalsIgnoreCase("music")) {
							//String tabnameurl=getWebDriver().getCurrentUrl();
							///getWebDriver().get(tabnameurl);
							navigateToAnyScreenOnWeb("Home");
							scrollDownWEB();
							scrollDownWEB();
							scrollDownWEB();
							navigateToAnyScreenOnWeb(tab);
							//		
							//click(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
						}
						clickWithNoWait(PWAHomePage.objNotNow);
						String title=swipeTillTray(300, trayTitle, "\"" + trayTitle + "\" tray");				
						verticalIndex=findVerticalIndex(trayTitle);
						System.out.println("verticalIndex: "+verticalIndex);
						WebElement element = getWebDriver().findElement(PWALandingPages.objTrayWithTitle(title));
						((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
						scrollDownByY(-100);
						waitTime(2000);
						moveToElementAboveCenter(title,dataContentID);
						waitTime(7000);	//for minutely video to load
						if(scenario==7) {	
							String link=trayValues.get(5).toString();
							link=mainUrl+link;
							getWebDriver().get(link);
							extent.extentLogger("", "URL opened: " + link);
							logger.info("URL opened: " + link);
							JSClick(PWAPlayerPage.watchListBtnNotAdded,"Add to Watchlist button");
							navigateToHome();
							//JSClick(PWALandingPages.objAssetInTrayWatchlist(title,dataContentID),"Watchlist button of Content card under tray "+title);
							click(PWALandingPages.objOpenProfileMenu, "Profile icon");
							click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
							waitTime(4000);
							if(assetSubtype.equals("episode")) tab="episodes";
							else if(assetSubtype.equals("movie")) tab="movies";
							else tab="videos";
							click(PWALandingPages.objWatchListTab(tab),tab+" tab");
							waitTime(5000);
							preview=findPreviewWatchlist(dataContentID);
							horizontalIndex=findWatchlistHorizontalIndex(dataContentID);
							Mixpanel.FEProp.setProperty("Horizontal Index", horizontalIndex);
							JSClick(PWALandingPages.objWatchListItem(dataContentID),"Watchlisted Content card");
							verticalIndex="N/A";
							click(PWAPlayerPage.objPlaybackRemoveFromWatchlist,"Remove from Watchlist button");
							scrollDownByY(-300);
							evaluateMP=true;
						}
						else if(scenario==9) {	
							try {
								shareUrl=getElementPropertyToString("data-minutelyurl",PWALandingPages.objCardForSharedUrl(dataContentID,contentID),"");
								logger.info("Shared URL fetched : "+shareUrl);
								extent.extentLogger("", "Shared URL fetched : "+shareUrl);
							}catch(Exception e) {}
							if(!shareUrl.equals("")) {
								if(shareUrl.contains("www.zee5.com")) {
									System.out.println("inside shared");
									shareUrl=shareUrl.replace("www.zee5.com", "newpwa.zee5.com");
								}
								extent.extentLogger("", "Edited Shared URL : " + shareUrl);
								logger.info("Edited Shared URL : "+shareUrl);
								getWebDriver().get(shareUrl);
								extent.extentLogger("", "Shared URL opened: " + shareUrl);
								logger.info("Shared URL opened : "+shareUrl);
								tab="N/A";
								verticalIndex="N/A";
								horizontalIndex="N/A";
								evaluateMP=true;
							}
						}
						else if(scenario==8 ||scenario==10 || scenario==12) {					
							String link=trayValues.get(5).toString();
							link=mainUrl+link;
							getWebDriver().get(link);
							extent.extentLogger("", "URL opened: " + link);
							logger.info("URL opened: " + link);
							tab="N/A";
							waitTime(2000);
							if(scenario==10) {
								source=pageNameForPlayer(assetSubtype);
								parentContentID=contentID;
								waitTime(8000);
								for(int i=0;i<=3;i++) {
									playListTrayTitle=getElementPropertyToString("innerText",PWALandingPages.objPlaylistTrayTitle,"");
									if(!playListTrayTitle.equals("null")) {
										extent.extentLogger("", "Playlist tray title: "+playListTrayTitle);
										logger.info("Playlist tray title: "+playListTrayTitle);
										break;
									}
									else waitTime(5000);
								}						
								String contentIDSourceContent=dataContentID;
								List<WebElement> playlistCards=findElements(PWALandingPages.objPlaylistCard);
								int playlistCardsSize=playlistCards.size();
								if(playlistCards.size()>6) playlistCardsSize=6;
								String playListDataContIdUI="";
								int playlistCardHorIndex=0;
								System.out.println("playlistCardsSize"+playlistCardsSize);
								ArrayList playlistCardDetails=new ArrayList<String>();
								for(int i=0;i<playlistCardsSize;i++) {					
									playListDataContIdUI=getElementPropertyToString("data-contentid",PWALandingPages.objPlaylistCard(i+1),"");
									playlistCardDetails=ResponseInstance.setPlayListCard(userType,contentIDSourceContent,playListDataContIdUI,assetType,contentLang,playListTrayTitle,guestToken,tvShowID,channelID);
									System.out.println("playlistCardDetails"+playlistCardDetails);
									if(!playlistCardDetails.get(0).equals("")) {
										playlistCardHorIndex=i+1;
										dataContentID=playListDataContIdUI;
										break;								
									}
								}							
								mixpanel.FEProp.setProperty("Horizontal Index", String.valueOf(playlistCardHorIndex));
								contentID=playlistCardDetails.get(0).toString();
								logger.info("Playlist card content ID: "+contentID);
								extent.extentLogger("","Playlist card content ID: "+contentID);
								contentTitle=playlistCardDetails.get(1).toString();
								logger.info("Playlist card content Title: "+contentTitle);
								extent.extentLogger("","Playlist card content Title: "+contentTitle);
								assetSubtype=playlistCardDetails.get(3).toString();
								logger.info("Playlist card asset subtype: "+assetSubtype);
								extent.extentLogger("","Playlist card asset subtype: "+assetSubtype);
								preview=findPreviewPlaylist(playListTrayTitle,dataContentID);
								waitTime(3000);
								if(!parentContentID.equals("")) ResponseInstance.updateWatchHistory(parentContentID,1,local.getItem("guestToken"));	
								JSClick(PWALandingPages.objAssetInPlaylist(contentID,dataContentID),"Content card in Playlist: "+contentTitle);
								verticalIndex="N/A";
								mixpanel.FEProp.setProperty("Tab Name", "N/A");
								System.out.println("Assigned");
								evaluateMP=true;
							}
							if(scenario==8) {
								parentContentID=contentID;
								waitTime(10000);
								String playlistTrayTitleUI="",playlistDataContIdUI="",playlistMinutelyUrl="";
								int upnextCardHorIndex=0;
								ArrayList upnextCardDetails=new ArrayList<String>();
								for(int i=0;i<=3;i++) {
									playlistTrayTitleUI=getElementPropertyToString("innerText",PWALandingPages.objPlaylistTrayTitle,"");
									if(!playListTrayTitle.equals(null)) {
										extent.extentLogger("", "Playlist tray title: "+playlistTrayTitleUI);
										logger.info("Playlist tray title: "+playlistTrayTitleUI);
										break;
									}
									else waitTime(5000);
								}	
								String contentIDSourceContent=dataContentID;		
								playlistDataContIdUI=getElementPropertyToString("data-contentid",PWALandingPages.objPlaylistCard(1),"");
								System.out.println("playlistDataContIdUI"+playlistDataContIdUI);
								playlistMinutelyUrl=getElementPropertyToString("data-minutelyurl",PWALandingPages.objPlaylistCard(1),"");
								System.out.println("playlistMinutelyUrl"+playlistMinutelyUrl);
								String playlistImageTitle=getElementPropertyToString("data-minutelytitle",PWALandingPages.objPlaylistCardMinutelyTitle(1),"");
								System.out.println("playlistImageTitle"+playlistImageTitle);
								String seasonID=trayValues.get(10).toString();
								System.out.println("seasonID"+seasonID);
								upnextCardDetails=ResponseInstance.setUpnextCard(userType,contentIDSourceContent,playlistDataContIdUI,assetType,contentLang,playlistMinutelyUrl,playlistTrayTitleUI,seasonID,guestToken);					
								contentID=upnextCardDetails.get(0).toString();
								String temp=upnextCardDetails.get(1).toString();
								if(!userType.equals("SubscribedUser")) waitForPlayerAdToComplete("Video PLayer");
								preview="N/A";
								ScrubToPlayerEnd();
								playerHeadPositionVideoView=waitUntilUpNextCardPlays(dataContentID);
								source=pageNameForPlayer(assetSubtype);//from parent
								assetSubtype=upnextCardDetails.get(3).toString();
								System.out.println("upNextassetSubtype"+assetSubtype);
								String playerUrl=getWebDriver().getCurrentUrl();
								if(playerUrl.contains(playlistDataContIdUI)) {
									if(!userType.equals("SubscribedUser")) waitForPlayerAdToComplete("Video PLayer");
								}
								else {
									System.out.println("Content not played from playlist");
									extent.extentLogger("", "Content not played from playlist");
									String webURL = getWebDriver().getCurrentUrl();
									String[] abc = webURL.split("/");
									dataContentID = abc[abc.length - 1];
									extent.extentLogger("", "Data Content ID fetched from URL: " + dataContentID);
									logger.info("Data Content ID fetched from URL: " + dataContentID);
									ArrayList<String> upnextDetails=ResponseInstance.getUpnextWhenNotPlayedFromPlaylist(userType,dataContentID,webURL,guestToken);	
									System.out.println("into main after upnextDetails");
									contentID=upnextDetails.get(0).toString();
									System.out.println("into main after upnextDetails added contentid");
									assetSubtype=upnextDetails.get(3).toString();
									System.out.println("upNextassetSubtype"+assetSubtype);								
								}
								if(!parentContentID.equals("")) ResponseInstance.updateWatchHistory(parentContentID,1,local.getItem("guestToken"));									
								verticalIndex="N/A";
								evaluateMP=true;	
							}
							if(scenario==12) {
								getWebDriver().navigate().refresh();
								logger.info("Refreshed the page");
								extent.extentLogger("", "Refreshed the page");
								mixpanel.FEProp.setProperty("Tab Name", findTabName(tab.toLowerCase()));
								evaluateMP=true;
							}							
						}
						else {
							preview=findPreview(trayTitle,dataContentID);
							JSClick(PWALandingPages.objAssetInTray(title,dataContentID),"Content card under tray "+title);
							source=findSource(tab);
							mixpanel.FEProp.setProperty("Tab Name", findTabName(tab.toLowerCase()));
							evaluateMP=true;
						}					
					}
					if(scenario==4) {
						String zee_Tab=getWebDriver().getWindowHandle();
						click(PWAHomePage.objPromotionalBannerCarouselDots(cardNo + 1), "Carousel Dot");
						JSClick(PWAHomePage.objCarouselCardForClick(cardNo),"Carousel Card");
						Mixpanel.FEProp.setProperty("Horizontal Index", String.valueOf(cardNo + 1));
						source=findSource(tab);
						mixpanel.FEProp.setProperty("Tab Name", findTabName(tab.toLowerCase()));						
						evaluateMP=true;
					}
					if(scenario==6) {
						click(PWAHomePage.objSearchBtn, "Search icon");
						type(PWASearchPage.objSearchEditBox, sourceContentTitle + "\n", "Search Edit box: " + sourceContentTitle);
						if(assetSubtype.equals("episode")) {
							click(PWASearchPage.objSearchNavigationTab("Episodes"), "Episodes tab");
							mixpanel.FEProp.setProperty("Tab Name", "episodes");
						}
						else if(assetSubtype.equals("movie")) {
							click(PWASearchPage.objSearchNavigationTab("Movies"), "Movies tab");	
							mixpanel.FEProp.setProperty("Tab Name", "movies");
						}
						else if(assetSubtype.equals("video")) {
							click(PWASearchPage.objSearchNavigationTab("Videos"), "Videos tab");	
							mixpanel.FEProp.setProperty("Tab Name", "videos");
						}
						else mixpanel.FEProp.setProperty("Tab Name", "all");
						waitForElement(PWASearchPage.objSearchResultDataContentID(dataContentID), 30, "Search Result");
						horizontalIndex=findHorizontalIndexForSearch(dataContentID);
						preview=findPreviewWatchlist(dataContentID);//preview finding same as search
						JSClick(PWASearchPage.objSearchResultDataContentID(dataContentID), "Search Result");
						source="search";
						Mixpanel.FEProp.setProperty("Horizontal Index", horizontalIndex);
						evaluateMP=true;
					}
					
					if(evaluateMP==true) {
						waitTime(2000);
						Set<String> handlesAfterClick = getWebDriver().getWindowHandles();
						String externalTab = "";
						boolean externalOpened=false;
						if(handlesAfterClick.size()>handlesBeforeClick.size()) {
							logger.info("New tab has been opened");
							extent.extentLogger("", "New tab has been opened");							
							for (String winHandle : getWebDriver().getWindowHandles()) {
								System.out.println(winHandle);
								if (!winHandle.equals(zeeTab)) {
									externalTab = winHandle;
									getWebDriver().switchTo().window(externalTab);
									logger.info("Switched to External Tab");
									extent.extentLogger("", "Switched to External Tab");
									externalOpened=true;
									break;
								}
							}
						}
						if(userType.equals("Guest") && scenario!=8) clickWithNoWait(PWAPlayerPage.objWEBCloseBtnLoginPopup);
						//if(scenario==8) waitTime(2000);
						if(scenario!=8)playerHeadPositionVideoView=getPlayerHeadPosition();
						page=pageNameForPlayer(assetSubtype);
						local = ((ChromeDriver) getWebDriver()).getLocalStorage();
						session = ((ChromeDriver) getWebDriver()).getSessionStorage();
						sessionID=String.valueOf(fetchSessionIDFromSessionStorage(session));
						System.out.println(sessionID);
						waitTime(2000);
						if(clickWithNoWait(PWAPlayerPage.objLiveFromCollectionPage)) { 
							waitForPlayerAdToComplete("Video PLayer");
							fromCollections=true;
							String collectionUrl=getWebDriver().getCurrentUrl();
							String[] splits=collectionUrl.split("/");
							String carouselId=splits[splits.length-1];
							String carouselName=splits[splits.length-2];
							mixpanel.FEProp.setProperty("Carousal ID", carouselId);
							mixpanel.FEProp.setProperty("Carousal Name", carouselName);
							source="tv_channel_detail";
							verticalIndex="N/A";
							mixpanel.FEProp.setProperty("Tab Name", "N/A");
							waitTime(2000);
						}
						else {
							if(scenario==9 || scenario==12) source="N/A";
						}
						JSClick(PWAPlayerPage.objPlayerPause,"Player Pause");
						waitTime(2000);
						JSClick(PWAPlayerPage.objPlayerPlay,"Player Play");
						if(assetType!=9) playerHeadPositionVideoExit=getPlayerHeadPosition();
						String url=getWebDriver().getCurrentUrl();
						if(url.contains("kids-movies")) mixpanel.FEProp.setProperty("Top Category", "Kids Movies");
						if(url.contains("zee5originals")) mixpanel.FEProp.setProperty("Top Category", "Original");
						if(url.contains("kids-music")) mixpanel.FEProp.setProperty("Top Category", "Kids Music");
						if(url.contains("kids-shows")) mixpanel.FEProp.setProperty("Top Category", "Kids Tv Show");
						if(url.contains("kids-videos")) mixpanel.FEProp.setProperty("Top Category", "Kids Video");
						if(externalOpened==true) {
							getWebDriver().switchTo().window(externalTab).close();
							getWebDriver().switchTo().window(zeeTab);
						}
						navigateToHome();
						extent.extentLogger("", "Navigated to Home tab");
						String watchedDuration=getWatchHistoryDuration(playerHeadPositionVideoView,playerHeadPositionVideoExit);
						System.out.println("watchedDuration"+watchedDuration);
						mixpanel.FEProp.setProperty("Page Name",page);
						mixpanel.FEProp.setProperty("Source", source);
						if(scenario==7) mixpanel.FEProp.setProperty("Source", "my_profile_watchlist");						
						mixpanel.FEProp.setProperty("Player Name", "kaltura-player-js");
						mixpanel.FEProp.setProperty("dekey", "N/A");					
						mixpanel.FEProp.setProperty("Partner Name", "N/A");
						mixpanel.FEProp.setProperty("Player Version", "1.3.0");
						mixpanel.FEProp.setProperty("Video View", "1");
						mixpanel.FEProp.setProperty("Vertical Index", verticalIndex);
						mixpanel.FEProp.setProperty("New Quality", "Auto");
						if(!(scenario==8 || scenario==10)) {						
							mixpanel.FEProp.setProperty("Talamoos modelName", "N/A");
							mixpanel.FEProp.setProperty("Talamoos origin", "N/A");
							mixpanel.FEProp.setProperty("isTalamoos", "false");
						}
						if(scenario==8) mixpanel.FEProp.setProperty("Video Autoplay", "true");
						else mixpanel.FEProp.setProperty("Video Autoplay", "false");
						mixpanel.FEProp.setProperty("Session ID", sessionID);
						mixpanel.FEProp.setProperty("Preview status", preview);
						String link=trayValues.get(5).toString();
						System.out.println("link"+link);
						String[] splits=link.split("/");
						String lastTerm=splits[splits.length-1];
						if(lastTerm.contains("latest")) {
							contentID=dataContentID;
							tvShowID="";
							channelID="";
						}
						ArrayList<String> contentDetailsReturnValues=ResponseInstance.getContentDetailsForPlayer(userType,contentID,assetSubtype,tvShowID,channelID,guestToken);															
						LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();		
						
						try { Thread.sleep(60000);} catch (Exception e) {}
						/*
						try {//Wait before fetching MP
							Thread.sleep(180000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}*/
						if (userType.equals("Guest")) {
							String[] mpResponse=mixpanel.fetchMPResponse(local.getItem("guestToken"),events);
							if(Arrays.asList(events).contains("Video View")){
								//Video View								
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoView);
								valuesPendingValidation.add(valuesPendingValidationMap);
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("guestToken"), "Video View",contentLang,valuesPendingValidation,contentID);
							}
							if(Arrays.asList(events).contains("Video Exit")){
								//Video Exit
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);								
								valuesPendingValidation.add(valuesPendingValidationMap);
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("guestToken"), "Video Exit",contentLang,valuesPendingValidation,contentID);
							}
							if(Arrays.asList(events).contains("Video Watch Duration")) {
								//Video Watch Duration
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);	
								valuesPendingValidationMap.put("Watch Duration", watchedDuration);
								valuesPendingValidation.add(valuesPendingValidationMap);
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("guestToken"), "Video Watch Duration",contentLang,valuesPendingValidation,contentID);
								
							}
							if(Arrays.asList(events).contains("Pause")) {
								//Pause
								mixpanel.FEProp.setProperty("Button Type", "Player");
								mixpanel.FEProp.setProperty("Element", "Pause");
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);	
								valuesPendingValidation.add(valuesPendingValidationMap);
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("guestToken"), "Pause",contentLang,valuesPendingValidation,contentID);
								
							}
							if(Arrays.asList(events).contains("Resume")) {
								//Resume
								mixpanel.FEProp.setProperty("Button Type", "Player");
								mixpanel.FEProp.setProperty("Element", "Resume");
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);	
								valuesPendingValidation.add(valuesPendingValidationMap);	
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("guestToken"), "Resume",contentLang,valuesPendingValidation,contentID);							
							}
						} else {
							String[] mpResponse=mixpanel.fetchMPResponse(local.getItem("ID"),events);
							if(Arrays.asList(events).contains("Video View")){
								//Video View
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoView);
								valuesPendingValidation.add(valuesPendingValidationMap);
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("ID"), "Video View",contentLang,valuesPendingValidation,contentID);
								
							}
							if(Arrays.asList(events).contains("Video Exit")){
								//Video Exit
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);								
								valuesPendingValidation.add(valuesPendingValidationMap);						
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("ID"), "Video Exit",contentLang,valuesPendingValidation,contentID);
								
							}
							if(Arrays.asList(events).contains("Video Watch Duration")) {
								//Video Watch Duration
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);	
								valuesPendingValidationMap.put("Watch Duration", watchedDuration);
								valuesPendingValidation.add(valuesPendingValidationMap);
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("ID"), "Video Watch Duration",contentLang,valuesPendingValidation,contentID);
								
							}
							if(Arrays.asList(events).contains("Pause")) {
								//Pause
								mixpanel.FEProp.setProperty("Button Type", "Player");
								mixpanel.FEProp.setProperty("Element", "Pause");
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);	
								valuesPendingValidation.add(valuesPendingValidationMap);	
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("ID"), "Pause",contentLang,valuesPendingValidation,contentID);
								
							}
							if(Arrays.asList(events).contains("Resume")) {
								//Resume
								mixpanel.FEProp.setProperty("Button Type", "Player");
								mixpanel.FEProp.setProperty("Element", "Resume");
								valuesPendingValidation=new ArrayList<HashMap<String,String>>();
								valuesPendingValidationMap = new HashMap<String,String>();
								valuesPendingValidationMap.put("Player Head Position", playerHeadPositionVideoExit);	
								valuesPendingValidation.add(valuesPendingValidationMap);	
								mixpanel.ValidateParameterForPlayer(mpResponse,slno,local.getItem("ID"), "Resume",contentLang,valuesPendingValidation,contentID);
								
							}
						}
						mandatoryRegistrationPopUp(userType);
					}			
				}		
			}
			catch(Exception e) {
				extent.extentLoggerFail("", "Failed : "+userType+"-"+tab+"-"+assetType+"-"+assetSubtype);
				logger.error("Failed : "+userType+" - "+tab+" - "+assetType+" - "+assetSubtype);
			}
			if(!contentID.equals(""))				
			ResponseInstance.updateWatchHistory(contentID,1,local.getItem("guestToken"));
			if(!userType.equals("Guest")) ResponseInstance.deleteAddDevicesForTheUser();
		}
		mixpanel.FEProp.clear();
	}
	
	public void clearContinueWatchingTray() {
		System.out.println("inside clearContinueWatchingTray");
		Actions actions = new Actions(getWebDriver());
		int items=0;
		List<WebElement> removeBtns=findElements(PWALandingPages.objContinueWatchingRemove);
		if (removeBtns.size()>0) scrollDownByY(300);
		while(removeBtns.size()!=0) {			
			for(int i=0;i<removeBtns.size();i++) {
				WebElement contentCard = getWebDriver().findElement(PWALandingPages.objContinueWatchingCard(1));
				actions.moveToElement(contentCard).build().perform();
				JSClick(PWALandingPages.objContinueWatchingRemove(1),"Remove button in Continue watching card");
				waitTime(2000);
			}
			removeBtns=findElements(PWALandingPages.objContinueWatchingRemove);
		}
		
	}
	
	public String fetchGuestTokenLanguage(LocalStorage local) {
		String guestToken="";
		try {
			Properties KEYVALUE = new Properties();
			for (String key : local.keySet()) {
				KEYVALUE.setProperty(key, local.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.contains("guestToken")) {
					guestToken = KEYVALUE.getProperty(key);
					System.out.println("guestToken:"+guestToken);
					break;
				}
			}
		} catch (Exception e) {}
		return guestToken;
	}
	
	
	public boolean clickWithNoWait(By xpath) {
		getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try {
			getWebDriver().findElement(xpath).click();
			return true;
		}
		catch(Exception e) { return false;}
	}
	
	public String findVerticalIndex(String title) {
		List<WebElement> trays=findElements(PWALandingPages.objTraysWithAd);
		getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		System.out.println("trays"+trays.size());
		for(int i=1;i<=trays.size();i++) {
			try {
				//System.out.println(i);
				getWebDriver().findElement(PWALandingPages.objTraysWithAdAndTitle(i,title));
				return String.valueOf(i+1);
			}
			catch(Exception e) {}
		}		
		return "";	
	}
	
	public ArrayList<HashMap<Integer,String>> returnCarouselValuesFromUI() throws Exception {
		ArrayList<HashMap<Integer,String>> carouselValues=new ArrayList<HashMap<Integer,String>>();
		HashMap<Integer,String> carouselValuesMap=new HashMap<Integer,String>();
		for(int i=0;i<7;i++) {
			try {
				WebElement carouselElement=getWebDriver().findElement(PWALandingPages.objCarouselElement(i));
				String url=carouselElement.getAttribute("data-minutelyurl");
				int cardNumber=i;
				carouselValuesMap.put(cardNumber, url);
				carouselValues.add(carouselValuesMap);
			}
			catch(Exception e) {}
		}
		return carouselValues;
	}
	
	public String findPreviewWatchlist(String dataContentID) {
		try {
			getWebDriver().findElement(PWALandingPages.objMinutelyWatchlist(dataContentID));
			return "Minutely";
		}
		catch(Exception e) {
			return "N/A";
		}
	}
	
	public String findWatchlistHorizontalIndex(String dataContentID) {
		String horizontalIndex="";
		List<WebElement> watchlistedElements=getWebDriver().findElements(PWALandingPages.objWatchlistedElements);
		for(int i=0;i<watchlistedElements.size();i++) {
			try {
				String uiDataContentID=watchlistedElements.get(i).getAttribute("data-contentid");
				if(uiDataContentID.equals(dataContentID)) {
					horizontalIndex=String.valueOf(i+1);
					System.out.println("findWatchlistHorizontalIndex horizontal index : "+horizontalIndex);
					break;
				}
			}catch(Exception e) {}
		}
		return horizontalIndex;
	}
	
	public void moveToElementAboveCenter(String trayTitle,String cardDataContentID) throws Exception {
		boolean foundCard=false;
		Actions actions=new Actions(getWebDriver());
		WebElement ele=null;
		try {
			ele=getWebDriver().findElement(PWALandingPages.objAssetInTray(trayTitle,cardDataContentID));
			foundCard=true;
		}
		catch(Exception e) {
			click(PWALandingPages.objNextButtonInTray(trayTitle),"Next arrow");
			try {
				ele=getWebDriver().findElement(PWALandingPages.objAssetInTray(trayTitle,cardDataContentID));
				foundCard=true;
			}
			catch(Exception e1) {}
		}
		try {
			if(foundCard) {
				System.out.println("found the element");
				int x=ele.getLocation().getX();
				int y=ele.getLocation().getY();
				int width=ele.getSize().getWidth();
				int height=ele.getSize().getHeight();
				int requiredX=x+width/2;
				int requiredY=y+height/5;
				System.out.println("requiredx"+requiredX);
				System.out.println("requiredy"+requiredY);
				actions.moveToElement(ele, requiredX, requiredY).build().perform();
				System.out.println("moved moved");
			}
		}
		catch(Exception e) {}
	}
	
	public String getPlayerHeadPosition() throws Exception {
		String playerHeadPosition="0";
		waitTime(2000);
		for(int i=0;i<200;i++) {
			try {
				playerHeadPosition=getWebDriver().findElement(PWAPlayerPage.objPlaykitSeekBar).getAttribute("aria-valuenow");
				System.out.println("playerHeadPosition:"+playerHeadPosition);
				extent.extentLogger("", "Player Head Position :"+playerHeadPosition);
				break;
			}catch(Exception e) {
				try {
					getWebDriver().findElement(PWAPlayerPage.objLivePlayerLiveTag);
					System.out.println("Live player is displayed");
					extent.extentLogger("", "Live player is displayed");
					break;
				}
				catch(Exception e2) {
					try {
						getWebDriver().findElement(PWAPlayerPage.objAdLayer);
						if (i == 0) {
							logger.info("Ad play in progress");
							extent.extentLogger("AdPlayInProgress", "Ad play in progress");
						}
					}
					catch(Exception e1) {}
				}
			}	
		}
		System.out.println("out of getplayerhead");
		return playerHeadPosition;		
	}
	
	
	public String findHorizontalIndexForSearch(String dataContentID) {
		String horizontalIndex="0";
		try {
			int searchItems=findElements(PWASearchPage.objSearchItemsCount).size();
			for(int i=1;i<=searchItems;i++) {
				try {
					getWebDriver().findElement(PWASearchPage.objSearchItemsCount(i,dataContentID));
					horizontalIndex=String.valueOf(i);
					break;
				}
				catch(Exception e) {}			
			}
		}catch(Exception e1) {}
		return horizontalIndex;
	}
	
	
	public String getWatchHistoryDuration(String getPlayerHeaderPositionStart,String getPlayerHeaderPositionEnd) {
		int start=Integer.valueOf(getPlayerHeaderPositionStart);
		int end=Integer.valueOf(getPlayerHeaderPositionEnd);
		int duration=end-start;
		return String.valueOf(duration);
	}
	
	
	public String findSource(String tabName) throws Exception{
		if(tabName.equalsIgnoreCase("Home")) return "home";
		else if(tabName.equalsIgnoreCase("TV Shows")) return "tv_shows_view_all";	
		else if(tabName.equalsIgnoreCase("Movies")) return "movie_landing";
		else if(tabName.equalsIgnoreCase("Premium")) return "premium";
		else if(tabName.equalsIgnoreCase("Music")) return "music_videos_landing";
		else if(tabName.equalsIgnoreCase("Kids")) return "kids_landing";
		return "";
	}
	
	public String findTabName(String tabName) throws Exception{
		if(tabName.equalsIgnoreCase("Home")) return "home";
		else if(tabName.equalsIgnoreCase("TV Shows")) return "N/A";	
		else if(tabName.equalsIgnoreCase("Movies")) return "movie_landing";
		else if(tabName.equalsIgnoreCase("Premium")) return "premium";
		else if(tabName.equalsIgnoreCase("Music")) return "music_videos_landing";
		else if(tabName.equalsIgnoreCase("Kids")) return "kids_landing";
		return "N/A";
	}
	
	
	public void ScrubToPlayerEnd() throws Exception {
		Actions act = new Actions(getWebDriver());
		WebElement overlay=findElement(PWAPlayerPage.objSubTitleOverlay);
		int overlayX=overlay.getLocation().getX();
		int overlayY=overlay.getLocation().getY();
		act.moveToElement(overlay, (overlayX+10), (overlayY+10)).build().perform();
		/////////////////////////////////////////////////////
		WebElement progressBar = findElement(PWAPlayerPage.progressBar);
		int progressBarWidth = progressBar.getSize().getWidth();
		System.out.println(progressBarWidth);
		int progressBarX = progressBar.getLocation().getX();
		System.out.println(progressBarX);
		int progressBarEndX = progressBarX + progressBarWidth;
		System.out.println(progressBarEndX);
		WebElement scrubber = findElement(PWAPlayerPage.objPlayerScrubber);
		int scrubberX = scrubber.getLocation().getX();
		System.out.println(scrubberX);
		int offsetForEnd = progressBarEndX - scrubberX - 10;
		System.out.println(offsetForEnd);
		/////////////////////////////////////		
		act.moveToElement(scrubber, offsetForEnd, 0).click().build().perform();
		waitTime(2000);
		mandatoryRegistrationPopUp(userType);
		extent.extentLogger("", "Scrubbed to end of the player");
		logger.info("Scrubbed to end of the player");
		mandatoryRegistrationPopUp(userType);
	}
	
	public void ScrubPlayer(int offsetX) throws Exception {
		Actions act = new Actions(getWebDriver());
		WebElement overlay=findElement(PWAPlayerPage.objSubTitleOverlay);
		int overlayX=overlay.getLocation().getX();
		int overlayY=overlay.getLocation().getY();
		act.moveToElement(overlay, (overlayX+10), (overlayY+10)).build().perform();
		/////////////////////////////////////////////////////
		WebElement scrubber = findElement(PWAPlayerPage.objPlayerScrubber);
		int scrubberX = scrubber.getLocation().getX();
		System.out.println(scrubberX);
		int scrubberY = scrubber.getLocation().getY();
		System.out.println(scrubberY);
		int offsetForX = scrubberX + offsetX;
		System.out.println(offsetForX);
		/////////////////////////////////////	
		act.dragAndDropBy(scrubber, -10, 0).build().perform();
		waitTime(2000);
		extent.extentLogger("", "Scrubbed the player by "+offsetX);
		logger.info("Scrubbed the player by "+offsetX);
		mandatoryRegistrationPopUp(userType);
	}
	
	
	public String findPreview(String trayTitle,String dataContentID) {
		try {
			getWebDriver().findElement(PWALandingPages.objMinutely(trayTitle, dataContentID));
			return "Minutely";
		}
		catch(Exception e) {
			return "N/A";
		}
	}
	
	public String findPreviewPlaylist(String trayTitle,String dataContentID) {
		try {
			getWebDriver().findElement(PWALandingPages.objMinutelyPlaylist(trayTitle, dataContentID));
			return "Minutely";
		}
		catch(Exception e) {
			return "N/A";
		}
	}
	
	
	public String fetchSessionIDFromSessionStorage(SessionStorage session) {
		String sessionID="";
		try {
			Properties KEYVALUE = new Properties();
			for (String key : session.keySet()) {
				KEYVALUE.setProperty(key, session.getItem(key));
			}
			Set<String> keys = KEYVALUE.stringPropertyNames();
			for (String key : keys) {
				if (key.contains("sessionID")) {
					sessionID = KEYVALUE.getProperty(key);
					break;
				}
			}
		} catch (Exception e) {}
		return sessionID;
	}
	
	
	public String waitUntilUpNextCardPlays(String playingCardDataContIdUI) throws Exception {
		getWebDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		main:for(int trial=0;trial<480;trial++) {
			for(int sec=0;sec<60;sec++) {
				//check for Ad Play After Scrub
				try {
					getWebDriver().findElement(PWAPlayerPage.objAd);
					if (trial== 0 && sec==5) {
						logger.info("Ad play in progress");
						extent.extentLogger("AdPlayInProgress", "Ad play in progress");
					}
				}
				catch(Exception e) {
					//No Ad
					try {
						String playerWebUrl=getWebDriver().getCurrentUrl();	
						if(!playerWebUrl.contains(playingCardDataContIdUI)) {
							logger.info("Upnext Card is playing :"+playerWebUrl);
							extent.extentLogger("", "Upnext Card is playing :"+playerWebUrl);
							return getPlayerHeadPosition();
						}					
					}
					catch(Exception e1) {}
				}				
			}
			if(Math.floorMod(trial, 60)==0) {
				logger.info("Waiting for Upnext content to play");
				extent.extentLogger("", "Waiting for Upnext content to play");
			}
		}
		return "";
	}
	

}
