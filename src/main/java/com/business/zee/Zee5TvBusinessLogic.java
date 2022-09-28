package com.business.zee;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import org.apache.poi.util.SystemOutLogger;
import org.apache.xmlbeans.UserType;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.driverInstance.CommandBase;
import com.driverInstance.DriverInstance;
import com.extent.ExtentReporter;
//import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.mixpanelValidation.Mixpanel;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.AMDGenericObjects;
import com.zee5.ApplicasterPages.AMDHomePage;
import com.zee5.ApplicasterPages.AMDPlayerScreen;
import com.zee5.PWAPages.PWAHomePage;
import com.zee5.PWAPages.PWASearchPage;
import com.zee5.TVPages.PWAHamburgerMenuPage;
import com.zee5.TVPages.PWALandingPages;
import com.zee5.TVPages.PWALoginPage;
import com.zee5.TVPages.Zee5TVCarousel;
import com.zee5.TVPages.Zee5TvHomePage;
import com.zee5.TVPages.Zee5TvPlayerPage;
import com.zee5.TVPages.Zee5TvSearchPage;
import com.zee5.TVPages.Zee5TvWelcomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.response.Response;

@SuppressWarnings("unused")
public class Zee5TvBusinessLogic extends Utilities {

	public Zee5TvBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	}

	private int timeout;
	SoftAssert softAssertion = new SoftAssert();
	boolean launch = "" != null;
	/** Retry Count */
	private int retryCount;
	Mixpanel mixpanel = new Mixpanel();
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	String language = "abcdefghijklmnopqrstuvwxyz";

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	static String code = "";
	String asset_SubType = "";
	String assetType = "";
	Set<String> hash_Set = new HashSet<String>();
	String viewAllTrayname = "";
	@SuppressWarnings("unused")
	private String LacationBasedLanguge;

	public String titleLanguage = "";

	List<String> LocationLanguage = new ArrayList<String>();

	List<String> DefaultLanguage = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInWelcomscreen = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInHamburgerMenu = new ArrayList<String>();

	Response resp;

	ArrayList<String> MastheadTitleApi = new ArrayList<String>();
	public String onboardingTraytitle = "";
	public String onboardingPremiumContenttitle = "";

	public static boolean relaunchFlag = false;
	public static boolean appliTools = false;

	public static boolean PopUp = false;

	private static String IP = "-s 192.168.0.89:5555";

	public String title = "";

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

	public String carouselTitle = "";
	public String carouselDescription = "";
	public String trayTitle = "";

	public String contentName = "";

	public String titleDescription = "";

	String userType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
	String parentpasswordNonSub = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("NonsubscribedPassword");

	String parentpasswordSub = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("SubscribedPassword");

	/**
	 * Initiate Property File.
	 *
	 * @param byLocator the by locator
	 */

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void TvtearDown() {
		getDriver().quit();
	}

	public void LoadingInProgress() throws Exception {
		verifyElementNotPresent(Zee5TvPlayerPage.objProgressLoader, 10);
		extent.extentLoggerPass("Progress Loader Matched", "Progress Loader Verfied");
	}

	public void AdVerify() throws Exception {
		verifyElementNotPresent(Zee5TvPlayerPage.objAd, 40);
		extent.extentLoggerPass("Ad Verified", "Ad Verified");
	}

	/**
	 *  To Verifying the functionality of playing the different contents from search
	 *  */
	public void searchScenarios(String userType) throws Exception {
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			searchMovie();
			searchPremium();
			searchEPG();
//			partlySpletSearch();
//			actorSearch();
//			genreSearch();
//			languageSearch();
		}
		if (userType.equals("SubscribedUser")) {
			searchMovie();
			searchEPG();
//			partlySpletSearch();
//			actorSearch();
//			genreSearch();
//			languageSearch();
		}
	}
	

	/**
	 *   Function to perform the Search button navigation functionality
	  */
	@SuppressWarnings("unused")
	public void searchMovie() throws Exception {
		extent.HeaderChildNode("Search Button navigation Functionality");
		waitTime(13000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		waitTime(5000);

		TVTabSelect("Home");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
//		if (verifyIsElementDisplayed(Zee5TvSearchPage.objSearchSpaceBar, "Search page")) {
//			logger.info("User is navigated to search page after clicking on search button");
//			extent.extentLoggerPass("Search", "User is navigated to search page after clicking on search button");
//		} else {
//			logger.info("User is not navigated to serach page");
//			extent.extentLoggerFail("Navigation", "User is not navigated to serach page");
//		}
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		HeaderChildNode("Search Movie content and its playback functionality");
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceButton, "Space bar");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceButton, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceButton, "Space");
		type(searchdata4);
		HeaderChildNode("Search result Movie playback functionality");
//		String content = TVgetText(Zee5TvSearchPage.objEditbox);
//
//		logger.info("Entered Search Data : " + content);
//		extent.extentLogger("Search", "Entered Searched Data : " + content);

//		int k;
//		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele.size(); i++) {
//
//			title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			logger.info(title);
//			extent.extentLogger("Title", "Serach result content title : " + title);
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
//					"Searched Movie"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
//				break;
//			} else {
//				System.out.println("No match");
//			}
//
//		}

		waitTime(8000);
		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		if (title.equals(title)) {
			logger.info("user is navigated to respective content detail page");
			extent.extentLoggerPass("user", "user is navigated to respective content detail page");
		}
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(10000);
		waitTime(25000);
		waitTime(10000);

		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			logger.info("clicked on skip intro");
			extent.extentLoggerPass("Intro", "clicked on skip intro");
		} else {
			logger.info("Skip intro is not displayed");
			extent.extentLogger("Intro", "Skip intro is not displayed");
		}
		waitTime(12000);
		TVRemoteEvent(23);
		waitTime(4000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play free content");
			extent.extentLoggerPass("play", "User is able to play free content");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
		waitTime(10000);

	}
	

	/**
	 *   Function to perform the Search result premium content popup functionality
	  */

	public void searchPremium() throws Exception {
		HeaderChildNode("Search result Premium content popup functionality");
		getDriver().navigate().back();
		waitTime(5000);
		getDriver().navigate().back();
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
		String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
		type(searchdata1);
		waitTime(5000);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
//		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele.size(); i++) {
//
//			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			logger.info(title);
//			extent.extentLogger("Title", "Serach result content title : " + title);
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
//					"Searched Premium movie"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
//				break;
//			} else {
//				logger.info("No match");
//			}
//
//		}
		waitTime(8000);
		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		if (title.equals(title)) {
			logger.info("user is navigated to respective content detail page");
			extent.extentLoggerPass("user", "user is navigated to respective content detail page");
		}
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(5000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium content as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium content as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}
		}
	}


	/**
	 *   Function to perform the Search result EPG content playback functionality
	  */
	@SuppressWarnings("unused")
	public void searchEPG() throws Exception {
		HeaderChildNode("Search result EPG content playback functionality");
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		waitTime(3000);
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
			waitTime(2000);
		}
		String searchdata1[] = { "n", "e", "w", "s" };
		type(searchdata1);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		verifyIsElementDisplayed(Zee5TvSearchPage.objSearchPageNowPlayingButton, "Now playing option");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchPageNowPlayingButton, "Now playing option");
		TVclick(Zee5TvSearchPage.objSearchPageNowPlayingButton, "Now playing option");
		waitTime(3000);
		boolean epg = verifyIsElementDisplayed(Zee5TvSearchPage.objElapsedTime, "Search result content Elapsed time");
		if (epg) {
			logger.info("EPG contents are displayed");
			extent.extentLoggerPass("Search", "EPG contents are displayed");
		} else {
			logger.info("EPG contents are not displayed");
			extent.extentLogger("Search", "EPG contents are not displayed");
		}
		verifyIsElementDisplayed(Zee5TvSearchPage.objElapsedTime, "Elapsed time");
		verifyIsElementDisplayed(Zee5TvSearchPage.objChalnnelName, "Channel name");
		verifyIsElementDisplayed(Zee5TvSearchPage.objProgressBar, "Progress bar");
		verifyIsElementDisplayed(Zee5TvSearchPage.objEPGtitle, "EPG Title");
		String channelName = TVgetText(Zee5TvSearchPage.objChalnnelName);
		String channelTitle = TVgetText(Zee5TvSearchPage.objEPGtitle);
		TVclick(Zee5TvSearchPage.objSearchedTumbnailImageEPG(channelName), "EPG news content");
		waitTime(15000);
		AdVerify();
		waitTime(2000);
		for (int i = 0; i <= 5; i++) {
			TVRemoteEvent(23);
			waitTime(2000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objGotolivebutton, "Live button in player")) {
				logger.info("User is able play Free EPG content");
				extent.extentLoggerPass("EPG", "User is able play Free EPG content");
				break;
			}
		}
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(8000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		int lenText2 = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText2; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}


	/**
	 *   Function to perform the Partly spelt content functionality
	  */
	@SuppressWarnings("unused")
	public void partlySpletSearch() throws Exception {
		HeaderChildNode("Partly spelt content functionality");
		String searchdata1[] = { "g", "a", "t", "t", "u" };
		type(searchdata1);
		waitTime(3000);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 3; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Gattu")) {
				logger.info("Partly spelt asset is displayed in search page");
				extent.extentLoggerPass("Spelt", "Partly spelt asset is displayed in search page");
			} else {
				logger.info("Partly spelt asset is not displayed in search page");
				extent.extentLogger("Spelt", "Partly spelt asset is not displayed in search page");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}
	

	/**
	 *   Function to perform the Search actor content functionality
	  */

	@SuppressWarnings("unused")
	public void actorSearch() throws Exception {
		HeaderChildNode("Searched Actor content functionality");
		String searchdata1[] = { "r", "a", "m", "e", "s", "h" };
		type(searchdata1);
		waitTime(3000);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 3; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Ramesh")) {
				logger.info("Actor name is displayed in search page when user search for actor");
				extent.extentLoggerPass("Spelt", "Actor name is displayed in search page when user search for actor");
			} else {
				logger.info("Actor name is not displayed in search page when user search for actor");
				extent.extentLogger("Spelt", "Actor name is not displayed in search page when user search for actor");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}

	@SuppressWarnings("unused")
	public void genreSearch() throws Exception {
		HeaderChildNode("Searched Genre content functionality");
		String searchdata1[] = { "c", "o", "m", "e", "d", "y" };
		type(searchdata1);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		waitTime(3000);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 3; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Comedy")) {
				logger.info("Genre search results are displayed when user search for particular genre");
				extent.extentLoggerPass("Spelt",
						"Genre search results are displayed when user search for particular genre");
			} else {
				logger.info("Genre search results are not displayed when user search for particular genre");
				extent.extentLogger("Spelt",
						"Genre search results are not displayed when user search for particular genre");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
	}


	/**
	 *   Function to perform the Searched language content functionality
	  */
	@SuppressWarnings("unused")
	public void languageSearch() throws Exception {
		HeaderChildNode("Searched language content functionality");
		String searchdata1[] = { "k", "a", "n", "n", "a", "d", "a" };
		type(searchdata1);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
		waitTime(3000);
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= 2; i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLoggerPass("Title", "Serach result content title : " + title);
			if (title.contains("Kannada")) {
				logger.info("language search results are displayed when user search for particular language");
				extent.extentLoggerPass("Spelt",
						"language search results are displayed when user search for particular language");
			} else {
				logger.info("language search results are not displayed when user search for particular language");
				extent.extentLogger("Spelt",
						"language search results are not displayed when user search for particular language");
			}
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();

		}
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(4000);
		TVTabSelect("Home");
		getDriver().closeApp();
		waitTime(5000);
		getDriver().launchApp();
		waitTime(7000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);

		}
	}
	
	/**
	 *  To Verifying the functionality of Authentication screen
	 *  */

	public void login(String userType) throws Exception {
		HeaderChildNode("Authentication process - Login as : " + userType);
		if (userType.equals("Guest")) {
			logger.info("Guest user");
			extent.extentLoggerPass("Login", "Guest user scenarios");
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(15000);
			verifyIsElementDisplayed(Zee5TvHomePage.objonboardingrevamploginbutton, "Already Register button");
			TVclick(Zee5TvHomePage.objonboardingrevamploginbutton, "Already Register button");
			waitTime(3000);
			code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			HeaderChildNode("Switching to WEB platform to Authenticate device");
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(10000);
			click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
			waitTime(2000);
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(3000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);
			//TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
		//	waitTime(5000);
		}
	}

	/**
	 *  To Verifying the 'Playback' and content details functionality on Home page
	 *  */
	public void playbackHomepage() throws Exception {
		HeaderChildNode("Content detail page functionality through Home page");
		waitTime(20000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
		if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
			waitTime(2000);
			TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
			extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
		}
		}
		if (userType.equals("Guest")) {
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			logger.info("logged in as Guest User");
			extent.extentLoggerPass("Button", "logged in as Guest User");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		}
		waitTime(10000);
		TVRemoteEvent(20);
		waitTime(2000);
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content");
				waitTime(5000);
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Movie page Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentgenreIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium content as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium content as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLogger("Popup", "Popup Functioanlity failed");
			}

		}
		waitTime(4000);
		if (userType.equals("NonSubscribedUser")) {
			for(int i=0;i<=30;i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}
			if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
				logger.info("Subscribe popup is displayed when user play premium contents");
				extent.extentLoggerPass("Page",
						"Subscribe popup is displayed when user play premium contents");
		
			}
			
		 else {
			
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption Screen")) {
				logger.info("Trailer Playback happened for Premium contents");
				//waitForElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, 80000);
				waitTime(65000);
			if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
				if (userType.equals("NonSubscribedUser")) {
					logger.info("Subscribe popup is displayed when user play Premium contents");
					extent.extentLoggerPass("Page",
							"Subscribe popup is displayed when user  play Premium contents");
				}
			}
			} else {
				logger.info("User is navigated to consumption page and premium content is played");
				extent.extentLoggerPass("Player",
						"User is navigated to consumption page and Premium content is played");
			}
		}
//			if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
//				logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
//				extent.extentLoggerPass("Popup",
//						"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
//			} else {
//				logger.info("User is navigated to consumption screen");
//				extent.extentLogger("Popup", "User is navigated to consumption screen");
//				getDriver().navigate().back();
//			}
	}
		if (userType.equals("SubscribedUser")) {
			// LoadingInProgress();
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVRemoteEvent(23);
				logger.info("clicked on skip intro");
				extent.extentLoggerPass("Intro", "clicked on skip intro");
			} else {
				logger.info("Skip intro is not displayed");
				extent.extentLoggerPass("Intro", "Skip intro is not displayed");
			}
			waitTime(8000);
			TVRemoteEvent(23);
			waitTime(2000);
			TVRemoteEvent(23);
			waitTime(2000);
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption page")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}

		}
		getDriver().navigate().back();
		waitTime(5000);
		getDriver().navigate().back();
		waitTime(5000);
		getDriver().navigate().back();
		waitTime(5000);
//		for (int k = 0; k <= 15; k++) {
//			TVRemoteEvent(19);
//			waitTime(2000);
//		}
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
	}


	/**
	 *   Verifying the shows content Play back functionality on shows landing page
	  */

	public void playbackShowspage() throws Exception {
		HeaderChildNode("Content detail page functionality through Shows page");
		TVTabSelect("Shows");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content")) {
				TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentShowTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentShowgnereIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentShowcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objShowsLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium content as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium content as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objShowsLoginPopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Functioanlity failed");
			}
		}
		if (userType.equals("SubscribedUser")) {
			// LoadingInProgress();
			TVRemoteEvent(23);
			waitTime(3000);
			TVRemoteEvent(23);
			TVRemoteEvent(23);
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}

		}
		getDriver().navigate().back();
		waitTime(5000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				// LoadingInProgress();
				waitTime(10000);
				TVRemoteEvent(23);
				waitTime(3000);
				TVRemoteEvent(23);
				if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
					logger.info("User is able to play free content");
					extent.extentLoggerPass("play", "User is able to play free content");
				} else {
					logger.info("playback did not initiate");
					extent.extentLoggerFail("play", "playback did not initiate");
				}
				getDriver().navigate().back();
				waitTime(5000);
			} else {
				logger.info("Watch trailer button is not present");
				extent.extentLoggerPass("Trailer", "Watch trailer button is not present");
			}
		}
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 7; k++) {
			TVRemoteEvent(19);
			waitTime(3000);
		}
	}
	

	/**
	 *   Verifying the Movies content Play back functionality on Movies landing page
	  */


	public void playbackMoviespage() throws Exception {
		HeaderChildNode("Content detail page functionality through Movies page");
		waitTime(10000);
		TVTabSelect("Movies");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objMoviePageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objMoviePageTrayContent, "Tray content");
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objMoviePageTrayContent, "Movie page Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentgenreIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentgenreIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentdurationIncontentPage, "Content duration")) {
			logger.info("Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
			extent.extentLoggerPass("title",
					"Content duration : " + TVgetText(Zee5TvWelcomePage.objContentdurationIncontentPage));
		} else {
			logger.info("content duration is not displayed");
			extent.extentLoggerFail("title", "content duration is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium content as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium content as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}
		}
		if (userType.equals("SubscribedUser")) {
			// LoadingInProgress();
			TVRemoteEvent(23);
			waitTime(3000);
			TVRemoteEvent(23);
			TVRemoteEvent(23);
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}

		}
		getDriver().navigate().back();
		waitTime(5000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			// TVRemoteEvent(22");
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				// LoadingInProgress();
				waitTime(10000);
				TVRemoteEvent(23);
				waitTime(3000);
				TVRemoteEvent(23);
				if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
					logger.info("User is able to play free content");
					extent.extentLoggerPass("play", "User is able to play free content");
				} else {
					logger.info("playback did not initiate");
					extent.extentLoggerFail("play", "playback did not initiate");
				}
				getDriver().navigate().back();
				waitTime(5000);
			} else {
				logger.info("Watch trailer button is not present");
				extent.extentLoggerPass("Trailer", "Watch trailer button is not present");
			}
		}
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 8; k++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}

	}
	
	/**
	 *   Verifying the video content Play back functionality on video landing page
	  */

	public void playbackvideospage() throws Exception {
		HeaderChildNode("Content detail page functionality through Videos page");
		TVTabSelect("Videos");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objVideoPageTrayContentPlayback,
					"Videos page Tray content")) {
				TVclick(Zee5TvWelcomePage.objVideoPageTrayContentPlayback, "Videos page Tray content");
				waitTime(4000);
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVRemoteEvent(20);
					TVclick(Zee5TvWelcomePage.objVideoPageTrayContentPlayback, "Videos page Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentTitleIncontentPage, "Content Title")) {
			logger.info("Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
			extent.extentLoggerPass("title",
					"Content title : " + TVgetText(Zee5TvWelcomePage.objContentTitleIncontentPage));
		} else {
			logger.info("content title is not displayed");
			extent.extentLoggerFail("title", "content title is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentDescriptionIncontentPage, "Content Description")) {
			logger.info("Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
			extent.extentLoggerPass("title",
					"Content Description : " + TVgetText(Zee5TvWelcomePage.objContentDescriptionIncontentPage));
		} else {
			logger.info("content Description is not displayed");
			extent.extentLoggerFail("title", "content Description is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentShowTypeIncontentPage, "Content type")) {
			logger.info("Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
			extent.extentLoggerPass("title",
					"Content type : " + TVgetText(Zee5TvWelcomePage.objContentShowTypeIncontentPage));
		} else {
			logger.info("content type is not displayed");
			extent.extentLoggerFail("title", "content type is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentShowgnereIncontentPage, "Content genre")) {
			logger.info("Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
			extent.extentLoggerPass("title",
					"Content genre : " + TVgetText(Zee5TvWelcomePage.objContentShowgnereIncontentPage));
		} else {
			logger.info("content genre is not displayed");
			extent.extentLoggerFail("title", "content genre is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentShowcertificateIncontentPage, "Content certificate")) {
			logger.info("Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
			extent.extentLoggerPass("title",
					"Content certificate : " + TVgetText(Zee5TvWelcomePage.objContentShowcertificateIncontentPage));
		} else {
			logger.info("content certificate is not displayed");
			extent.extentLoggerFail("title", "content certificate is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContenttrailerplaybackIncontentPage, "Trailer playback")) {
			logger.info("Auto trailer playback is initiated");
			extent.extentLoggerPass("trailer", "Auto trailer playback is initiated");
		} else {
			logger.info("Content does not have trailer");
			extent.extentLoggerPass("trailer", "Content does not have trailer");
		}

		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);

		// LoadingInProgress();
		waitTime(2000);
		AdVerify();
		waitTime(10000);
		TVRemoteEvent(23);
		waitTime(3000);
		TVRemoteEvent(23);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is navigated to consumption screen");
			extent.extentLoggerPass("play", "User is navigated to consumption screen");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
		getDriver().navigate().back();
		waitTime(5000);
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 8; k++) {
			TVRemoteEvent(19);
			waitTime(3000);
		}
		waitTime(3000);
		for (int k = 0; k <= 8; k++) {
			TVRemoteEvent(21);
			waitTime(3000);
		}
	}
	
	/**
	 *   Verifying the news content Play back functionality on News landing page
	  */

	public void playbackNewspage() throws Exception {
		HeaderChildNode("Content detail page functionality through News page");
		TVTabSelect("News");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		TVRemoteEvent(20);
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content")) {
				waitTime(5000);
				TVclick(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content");
				TVclick(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content");
				waitTime(4000);
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(5000);
		// LoadingInProgress();
		AdVerify();
		waitTime(15000);
		for (int i = 0; i <= 5; i++) {
			TVRemoteEvent(23);
			waitTime(2000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objGotolivebutton, "Live button in player")) {
				logger.info("User is able play Free EPG content");
				extent.extentLoggerPass("EPG", "User is able play Free news content");
				break;
			}
		}
		getDriver().navigate().back();
		for (int k = 0; k <= 5; k++) {
			TVRemoteEvent(19);
			waitTime(3000);
		}

	}
	
	/**
	 *  To Verifying the functionality of Carousel
	 *  */

	public void carouselValidation(String tab) throws Exception {
		HeaderChildNode("Carousel Validation  " + tab + " Tab");
		waitTime(10000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(5000);
		HeaderChildNode("Carousel content details validation");
		waitTime(7000);
		TVclick(Zee5TVCarousel.objCarouselTitle, "Carousel title");
		if (verifyIsElementDisplayed(Zee5TVCarousel.objCarouselTitle, "Carousel title")) {
			carouselTitle = TVgetText(Zee5TVCarousel.objCarouselTitle);
			logger.info("Carousel title : " + carouselTitle);
			extent.extentLoggerPass("Title", "Carousel title : " + carouselTitle);
		} else {
			logger.info("Carousel title is not displayed");
			extent.extentLoggerFail("Title", "Carousel title is not displayed");
		}

		if (verifyIsElementDisplayed(Zee5TVCarousel.objCarouselPremiumTag, "Carousel Premium tag")) {
			logger.info("Premium tag is displayed for premium content");
			extent.extentLoggerPass("Tag", "Premium tag is displayed for premium content");
		} else {
			logger.info("Premium tag is not displayed in carousel for " + carouselTitle);
			extent.extentLoggerPass("Title", "Premium tag is not displayed in carousel for " + carouselTitle);
		}
		if (verifyIsElementDisplayed(Zee5TVCarousel.objCarouselMetadata, "Carousel Metadata")) {
			String carouselMetadata = TVgetText(Zee5TVCarousel.objCarouselMetadata);
			logger.info("Carousel Metadata : " + carouselMetadata);
			extent.extentLoggerPass("Metadata", "Carousel Metadata : " + carouselMetadata);
		} else {
			logger.info("Carousel Metadata is not displayed for" + carouselTitle);
			extent.extentLoggerPass("Carousel", "Carousel Metadata is not displayed for" + carouselTitle);
		}
		if (verifyIsElementDisplayed(Zee5TVCarousel.objCarouselDescription, "Carousel Description")) {
			carouselDescription = TVgetText(Zee5TVCarousel.objCarouselDescription);
			logger.info("Carousel Description : " + carouselDescription);
			extent.extentLoggerPass("Description", "Carousel Description : " + carouselDescription);
		} else {
			logger.info("Carousel Description is not displayed for " + carouselTitle);
			extent.extentLoggerFail("Carousel", "Carousel Description is not displayed for " + carouselTitle);
		}

		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

	}
	
	/**
	 *   Verifying the Consumption page Functionality via search landing page
	  */

	@SuppressWarnings({ "unused", "rawtypes" })
	public void playerScenarios() throws Exception {
		extent.HeaderChildNode("Consumption page Functionality");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		waitTime(5000);
		TVTabSelect("Home");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		HeaderChildNode("Search Movie content and its playback functionality");
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);

		HeaderChildNode("Navigating to consumption through searched content");
//		String content = TVgetText(Zee5TvSearchPage.objEditbox);
//
//		logger.info("Entered Search Data : " + content);
//		extent.extentLogger("Search", "Entered Searched Data : " + content);
//
//		int k;
//		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele.size(); i++) {
//			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			logger.info("Serach result content title : " + title);
//			extent.extentLogger("Title", "Serach result content title : " + title);
//
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
//					"Searched Movie"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
//				break;
//			} else {
//				System.out.println("No match");
//			}
//
//		}

		waitTime(8000);
		// LoadingInProgress();

		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(10000);

		// Consumption Page

//		LoadingInProgress();
		waitTime(10000);
		AdVerify();

		waitTime(5000);
		HeaderChildNode("Consumption screen player options functionality");
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			logger.info("clicked on skip intro");
			extent.extentLoggerPass("Intro", "clicked on skip intro");
		} else {
			logger.info("Skip intro is not displayed");
			extent.extentLoggerPass("Intro", "Skip intro is not displayed");
		}
		waitTime(15000);
		TVRemoteEvent(23);
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objinfo, "Info")) {
			logger.info("Player is paused");
		} else {
			TVRemoteEvent(23);
		}
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(23);

		waitTime(3000);
		TVRemoteEvent(19);
		waitTime(2000);
		verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSettings, "Setting tab");

		waitTime(2000);
		List Options = getDriver().findElements(By.xpath("//*[@id='child_player_settings']"));
		Options.size();
		for (int i = 1; i <= Options.size(); i++) {

			String videooption = getDriver().findElement(Zee5TvPlayerPage.objSearchedSpecificVideoAudioOption(i))
					.getText();
			logger.info("Video setting options present : " + videooption);
		}
		getDriver().navigate().back();
		waitTime(5000);
		TVRemoteEvent(19);
		waitTime(3000);
		TVRemoteEvent(19);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objElapsedTime, "Elapsed Time")) {
			logger.info("Elapsed time in player is displayed");
			extent.extentLoggerPass("Time", "Elapsed time in player is displayed");
		} else {
			logger.info("Elapsed time in player is not displayed");
			extent.extentLogger("Time", "Elapsed time in player is not displayed");
		}
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objTotalTime, "Total Time")) {
			logger.info("Total time in player is displayed");
			extent.extentLoggerPass("Time", "Total time in player is displayed");
		} else {
			logger.info("Total time in player is not displayed");
			extent.extentLogger("Time", "Total time in player is not displayed");
		}
		String beforeTime = TVgetText(Zee5TvPlayerPage.objElapsedTime);
		logger.info("Time before forward functionality : " + beforeTime);
		extent.extentLoggerPass("Time", "Time before forward functionality : " + beforeTime);
		TVRemoteEvent(22);
		waitTime(2000);
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(22);
		TVclick(Zee5TvPlayerPage.objElapsedTime, "Elapsed time");
		String afterTime = TVgetText(Zee5TvPlayerPage.objElapsedTime);
		logger.info("Time after forward functionality : " + afterTime);
		extent.extentLoggerPass("Time", "Time after forward functionality : " + afterTime);
		if (!beforeTime.equals(afterTime)) {
			logger.info("Forward functionality successfull");
			extent.extentLoggerPass("Time", "Forward functionality successfull");
		} else {
			logger.info("Forward functionality not successfull");
			extent.extentLoggerFail("Time", "Forward functionality not successfull");
		}
		String beforeTime1 = TVgetText(Zee5TvPlayerPage.objElapsedTime);
		logger.info("Time before Reverse functionality : " + beforeTime1);
		extent.extentLoggerPass("Time", "Time before Reverse functionality : " + beforeTime1);
		TVRemoteEvent(21);
		waitTime(3000);
		TVRemoteEvent(21);
		waitTime(5000);
		TVclick(Zee5TvPlayerPage.objElapsedTime, "Elapsed time");
		String afterTime1 = TVgetText(Zee5TvPlayerPage.objElapsedTime);
		logger.info("Time after Reverse functionality : " + afterTime1);
		extent.extentLoggerPass("Time", "Time after Reverse functionality : " + afterTime1);
		if (!beforeTime1.equals(afterTime1)) {
			logger.info("Reverse functionality successfull");
			extent.extentLoggerPass("Time", "Reverse functionality successfull");
		} else {
			logger.info("Reverse functionality not successfull");
			extent.extentLoggerFail("Time", "Reverse functionality not successfull");
		}
		TVRemoteEvent(20);
		waitTime(3000);

		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(23);

		waitTime(4000);
		verifyIsElementDisplayed(Zee5TvPlayerPage.objUpnextrail, "Up Next rail");
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(12000);
		waitTime(5000);
		// LoadingInProgress();
		AdVerify();
		waitTime(10000);
		TVRemoteEvent(23);
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play content from upnext rail");
			extent.extentLoggerPass("play", "User is able to play content from upnext rail");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
		TVRemoteEvent(23);
		waitTime(10000);
		if (!verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSettings, "Player options")) {
			logger.info("Player options is hidden after 5 seconds");
			extent.extentLoggerPass("Seconds", "Player options is hidden after 5 seconds");
		} else {
			logger.info("Player options is not hidden after 5 seconds");
			extent.extentLogger("Seconds", "Player options is not hidden after 5 seconds");
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLoggerPass("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			getDriver().navigate().back();
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}

		getDriver().navigate().back();
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
		String searchdata[] = { "a", "g", "e", "n", "t" };
		String searchdata5[] = { "r", "a", "g", "h", "a", "v" };
		type(searchdata);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata5);
		List<WebElement> ele2 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele2.size(); i++) {
			String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Shows"),
					"Searched Show"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Shows"), "serached Show");
				break;
			} else {
				System.out.println("No match");
			}

		}
		waitTime(8000);
		// LoadingInProgress();

		String title5 = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(10000);

		// Consumption Page

		// LoadingInProgress();
		waitTime(5000);
		AdVerify();

		waitTime(5000);
		TVRemoteEvent(23);
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objinfo, "Info")) {
			logger.info("Player is paused");
		} else {
			TVRemoteEvent(23);
		}
		TVRemoteEvent(20);
		waitTime(3000);
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(3000);
		TVRemoteEvent(19);

		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerAudioLanguageOption, "Audio setting")) {
			logger.info("Audio setting is dsiplayed when user taps on setting button in player page");
			extent.extentLoggerPass("Player",
					"Audio setting is dsiplayed when user taps on setting button in player page");
		} else {
			logger.info("Audio setting is not displayed");
			extent.extentLogger("Audio", "Audio setting is not displayed");
		}

		waitTime(2000);
		List Options2 = getDriver().findElements(By.xpath("//*[@id='child_player_settings']"));
		Options2.size();
		for (int i = 1; i <= Options2.size(); i++) {
			String videooption = getDriver().findElement(Zee5TvPlayerPage.objSearchedSpecificVideoAudioOption(i))
					.getText();
			logger.info("Audio setting options present : " + videooption);
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objContentLangPopup, "POPUP")) {
			getDriver().navigate().back();
		}
		getDriver().navigate().back();
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objEditbox, "Edit box")) {
			logger.info("User is navigated to search page");
		} else {
			getDriver().navigate().back();
		}
		int lenText2 = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText2; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
//		String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
//		String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
//		String searchpromo2[] = { "t", "o" };
//		String searchpromo3[] = { "p", "a", "a", "r", "u" };
//
//		type(searchpromo);
//		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
//		type(searchpromo1);
//		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
//		type(searchpromo2);
//		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
//		type(searchpromo3);
//		waitTime(10000);
//		List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele3.size(); i++) {
//			String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Promo"),
//					"Searched Promo"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Promo"), "serached Promo");
//				break;
//			} else {
//				System.out.println("No match");
//			}
//
//		}
//		waitTime(45000);
//		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
//			logger.info(
//					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
//			extent.extentLoggerPass("Popup",
//					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
//			getDriver().navigate().back();
//		} else {
//			logger.info("Popup is not displayed");
//			extent.extentLogger("Popup", "Popup is not displayed");
//		}
//
//		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objreloadButton, "Replay icon")) {
//			logger.info("Replay button is displayed when user plays for the content which does not have upnext rail");
//			extent.extentLoggerPass("Replay",
//					"Replay button is displayed when user plays for the content which does not have upnext rail");
//		} else {
//			logger.info("Functionality went wrong");
//			extent.extentLogger("Replay", "Functionality went wrong");
//		}
//		getDriver().navigate().back();
//		waitTime(3000);
//		if (verifyIsElementDisplayed(Zee5TvSearchPage.objEditbox, "Edit box")) {
//			logger.info("User is navigated to search page");
//		} else {
//			getDriver().navigate().back();
//		}
//		int lenText3 = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
//		for (int i = 0; i < lenText3; i++) {
//			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
//
//		}
//		getDriver().navigate().back();
//		waitTime(5000);
		TVTabSelect("Home");
		waitTime(5000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
		}
	}

	/**
	 *   Verifying the Different rails and view all functionality on Home landing page
	  */
	@SuppressWarnings({ "unused", "rawtypes" })
	public void landingPageHome(String userType) throws Exception {
		extent.HeaderChildNode("Home Landing page Functionality");
		waitTime(10000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(10000);
		if (userType.equals("Guest")) {
			if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objContinueWatchingTray, "Continue watching tray")) {
				logger.info("For guest user continue watching tray is not dsiplayed");
				extent.extentLoggerPass("Guest", "For guest user continue watching tray is not dsiplayed");
			} else {
				logger.info("Continue watching is displayed");
			}
			if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objMyWatlistTray, "My watchlist tray")) {
				logger.info("For guest user MyWatchlist tray is not dsiplayed");
				extent.extentLoggerPass("Guest", "For guest user MyWatchlist tray is not dsiplayed");
			} else {
				logger.info("MyWatchlist tray is displayed");
			}
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			TVRemoteEvent(20);
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContinueWatchingTray, "Continue watching tray")) {
				logger.info("For logged in user continue watching tray is dsiplayed");
				extent.extentLoggerPass("Guest", "For logged in user continue watching tray is dsiplayed");
			} else {
				logger.info("Continue watching is not displayed");
			}
			verifyIsElementDisplayed(Zee5TvWelcomePage.objMyWatlistTray, "My watchlist tray");
			logger.info("For logged in user My watchlist tray is dsiplayed");
			extent.extentLoggerPass("Guest", "For logged in user My watchlist tray is dsiplayed");

			HeaderChildNode("View All button functionality");
			TVRemoteEvent(20);
			waitTime(2000);
			String language = getLanguage(userType);
			Response resp = ResponseInstance.getResponseForPagesTv("Home", language, 1, userType);
			for (int i = 1; i <= 30; i++) {
				int total = resp.jsonPath().getInt("buckets[" + i + "].total");
				if (total > 20) {
					viewAllTrayname = resp.jsonPath().getString("buckets[" + i + "].title");
					logger.info(viewAllTrayname);
					break;
				}
			}
			for (int i = 0; i <= 30; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewAllTrayApi, "Tray content")) {
					TVclick(Zee5TvWelcomePage.objViewAllTrayApi, "Tray content");
					//TVRemoteEvent(20);
					waitTime(7000);
					break;
				} else {
					TVRemoteEvent(20);
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallButton, "ViewAll button")) {
					TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
						logger.info("User is navigated to view all page");
						extent.extentLoggerPass("Page", "User is navigated to view all page");
					} else {
						TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
					}
					waitTime(7000);

					break;
				} else {
					TVRemoteEvent(22);
				}

			}
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
				String pagename = TVgetText(Zee5TvWelcomePage.objViewallPageHead);
				logger.info("User is navigated to" + pagename + "view all page");
				extent.extentLoggerPass("Page", "User is navigated to " + pagename + " view all page");
			}
			List premiumTag = getDriver().findElements(By.xpath("//*[@id='premium_tag']"));
			premiumTag.size();
			for (int i = 2; i <= 6; i++) {
				WebElement videooption = getDriver().findElement(Zee5TvWelcomePage.objPremiumTag(i));
				logger.info("Premium tag is displayed for premium content");
				extent.extentLoggerPass("Tag", "Premium tag is displayed for premium content");
			}
			getDriver().navigate().back();
			HeaderChildNode("Verifications of different trays present");
			waitTime(3000);
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(20);
			List rowHeader = getDriver().findElements(By.xpath("// *[@id='row_header']"));
			for (int i = 1; i <= rowHeader.size(); i++) {
				String rowHeaderTrays = getDriver().findElement(By.xpath("(// *[@id='row_header'])[" + i + "]"))
						.getText();
				logger.info("Different content Trays are present : " + rowHeaderTrays);
				extent.extentLoggerPass("Tag", "Different content Trays are present : " + rowHeaderTrays);
			}
			waitTime(3000);
//			for (int i = 1; i <= 18; i++) {
//				TVRemoteEvent(19);
//				waitTime(2000);
//			}
			getDriver().closeApp();

			waitTime(3000);

			getDriver().launchApp();

			waitTime(10000);
		}
	}


	/**
	 *   Function to perform the Shows landing page functionality
	  */
	public void landingPageShows(String userType) throws Exception {
		extent.HeaderChildNode("Shows Landing page Functionality");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		waitTime(5000);
		TVTabSelect("Shows");
		logger.info("User is navigated to Shows tab");
		extent.extentLoggerPass("Tab", "User is navigated to Shows tab");
		TVRemoteEvent(20);
		waitTime(2000);
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLandingshowpageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objLandingshowpageTrayContent, "Tray content");
				waitTime(7000);
				break;
			} else {
				TVRemoteEvent(20);
			}
		}
		HeaderChildNode("View All button functionality");
		for (int i = 0; i <= 22; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallButton, "ViewAll button")) {
				TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				waitTime(5000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
					logger.info("User is navigated to view all page");
					extent.extentLoggerPass("Page", "User is navigated to view all page");
				} else {
					TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				}
				waitTime(7000);

				break;
			} else {
				TVRemoteEvent(22);
			}

		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
			String pagename = TVgetText(Zee5TvWelcomePage.objViewallPageHead);
			logger.info("User is navigated to" + pagename + "view all page");
			extent.extentLoggerPass("Page", "User is navigated to " + pagename + " view all page");
		}
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objViewAllPremium, "Premium tag")) {
			logger.info("Shows view all page does not have premium icon");
			extent.extentLoggerPass("Shows", "Shows view all page does not have premium icon");
		} else {
			logger.info("premium icon is displayed");
			extent.extentLogger("Shows", "premium icon is displayed");
		}
		getDriver().navigate().back();
		HeaderChildNode("Verifications of different trays present");
		waitTime(3000);
		TVRemoteEvent(20);
		waitTime(3000);
		TVRemoteEvent(20);
		List rowHeader = getDriver().findElements(By.xpath("// *[@id='row_header']"));
		for (int i = 1; i <= rowHeader.size(); i++) {
			String rowHeaderTrays = getDriver().findElement(By.xpath("(// *[@id='row_header'])[" + i + "]")).getText();
			logger.info("Different content Trays are present : " + rowHeaderTrays);
			extent.extentLoggerPass("Tag", "Different content Trays are present : " + rowHeaderTrays);
		}
		for (int i = 1; i <= 12; i++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
	}
	

	/**
	 *   Function to perform the Movies landing functionality
	  */

	@SuppressWarnings({ "unused", "rawtypes" })
	public void landingPageMovies(String userType) throws Exception {
		extent.HeaderChildNode("Movies Landing page Functionality");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		waitTime(5000);
		TVTabSelect("Movies");
		logger.info("User is navigated to Movies tab");
		extent.extentLoggerPass("Tab", "User is navigated to Shows tab");
		TVRemoteEvent(20);
		waitTime(2000);
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLandingMoviePageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objLandingMoviePageTrayContent, "Tray content");
				waitTime(7000);
				break;
			} else {
				TVRemoteEvent(20);
			}
		}
		HeaderChildNode("View All button functionality");
		for (int i = 0; i <= 22; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallButton, "ViewAll button")) {
				TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				waitTime(5000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
					logger.info("User is navigated to view all page");
					extent.extentLoggerPass("Page", "User is navigated to view all page");
				} else {
					TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				}
				waitTime(7000);

				break;
			} else {
				TVRemoteEvent(22);
			}

		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
			String pagename = TVgetText(Zee5TvWelcomePage.objViewallPageHead);
			logger.info("User is navigated to" + pagename + "view all page");
			extent.extentLoggerPass("Page", "User is navigated to " + pagename + " view all page");
		}
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objViewAllPremium, "Premium tag")) {
			logger.info("Movies view all page does not have premium icon");
			extent.extentLoggerPass("Movies", "Movies view all page does not have premium icon");
		} else {
			logger.info("premium icon is displayed");
			extent.extentLogger("Movies", "premium icon is displayed");
		}
		getDriver().navigate().back();
		HeaderChildNode("Verifications of different trays present");
		waitTime(3000);
		TVRemoteEvent(20);
		waitTime(3000);
		TVRemoteEvent(20);
		List rowHeader = getDriver().findElements(By.xpath("// *[@id='row_header']"));
		for (int i = 1; i <= rowHeader.size(); i++) {
			String rowHeaderTrays = getDriver().findElement(By.xpath("(// *[@id='row_header'])[" + i + "]")).getText();
			logger.info("Different content Trays are present : " + rowHeaderTrays);
			extent.extentLoggerPass("Tag", "Different content Trays are present : " + rowHeaderTrays);
		}
		for (int i = 1; i <= 12; i++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
	}
	

	/**
	 *   Function to perform the News landing page functionality
	  */

	@SuppressWarnings({ "unused", "rawtypes" })
	public void landingPageNews(String userType) throws Exception {
		extent.HeaderChildNode("News Landing page Functionality");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		waitTime(5000);
		TVTabSelect("News");
		logger.info("User is navigated to News tab");
		extent.extentLoggerPass("Tab", "User is navigated to Shows tab");
		TVRemoteEvent(20);
		waitTime(2000);
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLandingNewsContentinNewsPage, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objLandingNewsContentinNewsPage, "Tray content");
				waitTime(7000);
				break;
			} else {
				TVRemoteEvent(20);
			}
		}
		HeaderChildNode("View All button functionality");
		for (int i = 0; i <= 22; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallButton, "ViewAll button")) {
				TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				waitTime(5000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
					logger.info("User is navigated to view all page");
					extent.extentLoggerPass("Page", "User is navigated to view all page");
				} else {
					TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				}
				waitTime(7000);

				break;
			} else {
				TVRemoteEvent(22);
			}

		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
			String pagename = TVgetText(Zee5TvWelcomePage.objViewallPageHead);
			logger.info("User is navigated to" + pagename + "view all page");
			extent.extentLoggerPass("Page", "User is navigated to " + pagename + " view all page");
		}
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objViewAllPremium, "Premium tag")) {
			logger.info("News view all page does not have premium icon");
			extent.extentLoggerPass("Shows", "News view all page does not have premium icon");
		} else {
			logger.info("premium icon is displayed");
			extent.extentLogger("News", "premium icon is displayed");
		}
		getDriver().navigate().back();
		HeaderChildNode("Verifications of different trays present");
		waitTime(3000);
		TVRemoteEvent(20);
		waitTime(3000);
		TVRemoteEvent(20);
		List rowHeader = getDriver().findElements(By.xpath("// *[@id='row_header']"));
		for (int i = 1; i <= rowHeader.size(); i++) {
			String rowHeaderTrays = getDriver().findElement(By.xpath("(// *[@id='row_header'])[" + i + "]")).getText();
			logger.info("Different content Trays are present : " + rowHeaderTrays);
			extent.extentLoggerPass("Tag", "Different content Trays are present : " + rowHeaderTrays);
		}
		for (int i = 1; i <= 12; i++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
	}
	

	/**
	 *   Function to perform the Videos landing page functionality
	  */

	@SuppressWarnings({ "unused", "rawtypes" })
	public void landingPageVideos(String userType) throws Exception {
		extent.HeaderChildNode("Videos Landing page Functionality");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		waitTime(5000);
		TVTabSelect("Videos");
		logger.info("User is navigated to Videos tab");
		extent.extentLoggerPass("Tab", "User is navigated to Shows tab");
		TVRemoteEvent(20);
		waitTime(2000);
		for (int i = 0; i <= 10; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLandingNewsContentinVideosPage, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objLandingNewsContentinVideosPage, "Tray content");
				waitTime(7000);
				break;
			} else {
				TVRemoteEvent(20);
			}
		}
		HeaderChildNode("View All button functionality");
		for (int i = 0; i <= 22; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallButton, "ViewAll button")) {
				TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				waitTime(5000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
					logger.info("User is navigated to view all page");
					extent.extentLoggerPass("Page", "User is navigated to view all page");
				} else {
					TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				}
				waitTime(7000);

				break;
			} else {
				TVRemoteEvent(22);
			}

		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
			String pagename = TVgetText(Zee5TvWelcomePage.objViewallPageHead);
			logger.info("User is navigated to" + pagename + "view all page");
			extent.extentLoggerPass("Page", "User is navigated to " + pagename + " view all page");
		}
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objViewAllPremium, "Premium tag")) {
			logger.info("Videos view all page does not have premium icon");
			extent.extentLoggerPass("Shows", "Videos view all page does not have premium icon");
		} else {
			logger.info("premium icon is displayed");
			extent.extentLogger("Shows", "premium icon is displayed");
		}
		getDriver().navigate().back();
		HeaderChildNode("Verifications of different trays present");
		waitTime(3000);
		TVRemoteEvent(20);
		waitTime(3000);
		TVRemoteEvent(20);
		List rowHeader = getDriver().findElements(By.xpath("// *[@id='row_header']"));
		for (int i = 1; i <= rowHeader.size(); i++) {
			String rowHeaderTrays = getDriver().findElement(By.xpath("(// *[@id='row_header'])[" + i + "]")).getText();
			logger.info("Different content Trays are present : " + rowHeaderTrays);
			extent.extentLoggerPass("Tag", "Different content Trays are present : " + rowHeaderTrays);
		}
		for (int i = 1; i <= 12; i++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
	}


	/**
	 *   Function to perform the Setting page functionality
	  */
	public void settings() throws Exception {
		HeaderChildNode("Setting Page functionality");
		waitTime(20000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(10000);
		for (int i = 0; i <= 4; i++) {
			TVRemoteEvent(21);
			waitTime(2000);
		}

		for (int i = 0; i <= 7; i++) {
			TVRemoteEvent(20);
			waitTime(2000);
		}

		TVTabSelect("Settings");
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objVideoqualityOption, "Video quality option");
		waitTime(3000);
		waitTime(3000);
		TVRemoteEvent(21);
		waitTime(3000);
		TVRemoteEvent(21);
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objMyProfilePageheader, "Profile page header")) {
			logger.info("User is navigated to Profile page");
			extent.extentLoggerPass("Profile", "User is navigated to Profile page");
		} else {
			TVclick(Zee5TvWelcomePage.objProfileOptionInSettingPage, "Profile icon");
			waitTime(5000);
			
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objMyProfilePageheader, "Profile page header")) {
				logger.info("User is navigated to Profile page");
				extent.extentLoggerPass("Profile", "User is navigated to Profile page");
			} else {
				logger.info("Navigation failed");
				extent.extentLoggerFail("Profile", "Navigation failed");
			}
		}
		waitTime(5000);
		verifyIsElementDisplayed(Zee5TvWelcomePage.objNameColumn, "Name field in profile page");
		verifyIsElementDisplayed(Zee5TvWelcomePage.objEmailIdColumn, "Email field in profile page");
		verifyIsElementDisplayed(Zee5TvWelcomePage.objMobileNumberColumn, "mobile number field in profile page");
		verifyIsElementDisplayed(Zee5TvWelcomePage.objDOBColumn, "DOB field in profile page");
		verifyIsElementDisplayed(Zee5TvWelcomePage.objGenderColumn, "Gender field in profile page");
		getDriver().navigate().back();
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objVideoqualityOption, "Video quality option")) {
		} else {
			getDriver().navigate().back();
			logger.info("Returned to Settings Page");
		}
		waitTime(5000);

	}

	public void parentalContorlFunctionality() throws Exception {
		HeaderChildNode("Parental control functionality");
		getDriver().navigate().back();
		waitTime(2000);
		for (int i = 0; i <= 3; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objParentalOption, "Parental Option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objParentalOption, "Parental Option");
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objParentalControlMessage, "Parental control message")) {
			logger.info("Parental control message - Go to zee5.com is displayed");
			extent.extentLoggerPass("Parental", "Parental control message - Go to zee5.com is displayed");
		} else {
			logger.info("Parental control message is not displayed");
			extent.extentLoggerFail("Parental", "Parental control message is not displayed");
		}
		getDriver().navigate().back();
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
				waitTime(7000);
			} else {
				logger.info("Popup not displayed");
			}
			code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			HeaderChildNode("Switching to WEB platform to Authenticate device");
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(5000);

			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordNonSub, "Password field");
			} else if (userType.equals("SubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordSub, "Password field");
			}
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			HeaderChildNode("Selecting Restrict 13+ option in parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrict13, "Restrict 13+ option");
			verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(5000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objcontinueButtonInLoginPage,
					"Continue button in TV authentication page")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVRemoteEvent(20);
				waitTime(3000);
				TVRemoteEvent(22);
				waitTime(3000);
				TVRemoteEvent(22);
				waitTime(3000);
				TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
				waitTime(15000);
			}
			TVTabSelect("Home");
			logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
			extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);
			String searchdata1[] = { "t", "h", "e" };
			String searchdata2[] = { "a", "w", "a", "k", "e", "n", "i", "n", "g" };
			type(searchdata1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchdata2);
			String content = TVgetText(Zee5TvSearchPage.objEditbox);
			logger.info("Entered Search Data : " + content);
			extent.extentLoggerPass("Search", "Entered Searched Data : " + content);
			List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele.size(); i++) {

				String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				logger.info(title);
				extent.extentLogger("Title", "Serach result content title : " + title);
				waitTime(7000);
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Episodes"),
						"Searched 13+ content"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Episodes"),
							"Searched 13+ content");
					break;
				} else {
					logger.info("No match");
				}

			}
		}
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objParentpopuptitle, "Parental popup")) {
			logger.info("Parental popup is displayed when user keeps parental control for 13+ content");
			extent.extentLoggerPass("13+",
					"Parental popup is displayed when user keeps parental control for 13+ content");
		} else {
			logger.info("Popup functionality failed");
			extent.extentLoggerFail("Popup", "Popup functionality failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		TVTabSelect("Home");

		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}

		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
				waitTime(7000);
			} else {
				logger.info("Popup not displayed");
			}
			code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(5000);

			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordNonSub, "Password field");
			} else if (userType.equals("SubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordSub, "Password field");
			}
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			HeaderChildNode("Selecting Restrict ALL option in parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrictAll, "Restrict ALL option");
			verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(5000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objcontinueButtonInLoginPage,
					"Continue button in TV authentication page")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVRemoteEvent(20);
				waitTime(3000);
				TVRemoteEvent(22);
				waitTime(3000);
				TVRemoteEvent(22);
				waitTime(3000);
				TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
				waitTime(15000);
			}
			TVTabSelect("Home");
			logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
			extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);
			String searchdata1[] = { "b", "a", "b", "l", "u", };
			String searchdata2[] = { "d", "a", "b", "l", "u" };
			String searchdata3[] = { "r", "o", "b", "o" };
			String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			String content = TVgetText(Zee5TvSearchPage.objEditbox);

			logger.info("Entered Search Data : " + content);
			extent.extentLogger("Search", "Entered Searched Data : " + content);

			List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele.size(); i++) {

				title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				logger.info(title);
				extent.extentLogger("Title", "Serach result content title : " + title);
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
						"Searched Movie"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
					break;
				} else {
					System.out.println("No match");
				}

			}
		}
		waitTime(10000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objParentpopuptitle, "Parental popup")) {
			logger.info("Parental popup is displayed when user keeps parental control for all content");
			extent.extentLoggerPass("All",
					"Parental popup is displayed when user keeps parental control for all content");
		} else {
			logger.info("Popup functionality failed");
			extent.extentLoggerFail("Popup", "Popup functionality failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		TVTabSelect("Home");

		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}

		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginOption, "Login optipn")) {
			logger.info("Logout functionality successfull");
			extent.extentLoggerPass("Logger", "Logout functionality successfull");
		}
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
				waitTime(7000);
			} else {
				logger.info("Popup not displayed");
			}
			code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(5000);

			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordNonSub, "Password field");
			} else if (userType.equals("SubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordSub, "Password field");
			}
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			HeaderChildNode("Selecting No Restrict option in parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restriction option");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(5000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objcontinueButtonInLoginPage,
					"Continue button in TV authentication page")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVRemoteEvent(20);
				waitTime(3000);
				TVRemoteEvent(22);
				waitTime(3000);
				TVRemoteEvent(22);
				waitTime(3000);
				TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
				waitTime(15000);
			}

			TVTabSelect("Home");
			logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
			extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);
			String searchdata1[] = { "b", "a", "b", "l", "u", };
			String searchdata2[] = { "d", "a", "b", "l", "u" };
			String searchdata3[] = { "r", "o", "b", "o" };
			String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			String content = TVgetText(Zee5TvSearchPage.objEditbox);

			logger.info("Entered Search Data : " + content);
			extent.extentLogger("Search", "Entered Searched Data : " + content);

			List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele.size(); i++) {

				title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				logger.info(title);
				extent.extentLogger("Title", "Serach result content title : " + title);
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
						"Searched Movie"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
					break;
				} else {
					System.out.println("No match");
				}

			}
		}
		waitTime(7000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(7000);
		// LoadingInProgress();
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objParentpopuptitle, "Parental popup")) {
			logger.info("Parental popup is not displayed when user keeps parental control for No restrict option");
			extent.extentLoggerPass("All",
					"Parental popup is not displayed when user keeps parental control for No restrict option");
		} else {
			logger.info("Popup functionality failed");
			extent.extentLoggerFail("Popup", "Popup functionality failed");
		}
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);

	}
	

	/**
	 *   Function to perform the Video quality option functionality in setting page
	  */

	public void qualityOptions() throws Exception {
		HeaderChildNode("Video quality option functionality in setting page");
		waitTime(5000);
		TVclick(Zee5TvWelcomePage.objVideoqualityOption, "Video quality option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objVideoqualityOption, "Video quality option");
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objVideoQualityHeader, "Video quality page header")) {
			logger.info("User is navigated to video quality page");
			extent.extentLoggerPass("Video", "User is navigated to video quality page");
		} else {
			logger.info("Navigation failed");
			extent.extentLoggerFail("Video", "Navigation failed");
		}
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions2));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions3));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions4));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions5));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions2));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions3));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions4));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions5));

		getDriver().navigate().back();
		waitTime(3000);

	}
	
	/**
	 *   Function to perform the Reminder option functionality in setting page
	  */


	public void reminders() throws Exception {
		HeaderChildNode("Reminder option functionality in setting page");
		TVRemoteEvent(21);
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objreminderOption, "Reminder option");
		waitTime(8000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objnoreminder, "No reminder")) {
			logger.info("No reminder text is displayed");
			extent.extentLoggerPass("No", "No reminder text is displayed");
		} else {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objreminderLayout, "reminder layout")) {
				logger.info("Reminders are dislayed");
				extent.extentLoggerPass("Reminder", "Reminders are dislayed");
			}
		}
		getDriver().navigate().back();
	}

	public void navigations() throws Exception {
		HeaderChildNode("Navigations from different page");
		getDriver().navigate().back();
		waitTime(3000);
		for (int i = 0; i <= 4; i++) {
			TVRemoteEvent(21);
			waitTime(2000);
		}
		TVTabSelect("Shows");
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(5000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content")) {
				TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
				TVRemoteEvent(23);
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objshowpageContentTitle, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
					waitTime(5000);
					verifyIsElementDisplayed(Zee5TvSearchPage.objshowpageContentTitle, "Content Title");
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();
		waitTime(5000);
		getDriver().launchApp();
		waitTime(5000);
	}
	/**
	 *   Function to perform the Settings page validation functionality
	  */

	public void setting(String userType) throws Exception {
		if (userType.equals("Guest")) {
			HeaderChildNode("Settings page validation");
			logger.info("settins module not applicable for guest user");
			extent.extentLoggerPass("Guest", "settins module not applicable for guest user");
			waitTime(3000);
			getDriver().closeApp();
			waitTime(5000);
			getDriver().launchApp();
			waitTime(10000);
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			settings();
			qualityOptions();
			navigations();
		}
	}

	/**
	 *   Function to perform the collection page navigation validation functionality
	  */		

	public void collectionpage() throws Exception {
		HeaderChildNode("Collection page navigation");
		waitTime(10000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(5000);
		TVRemoteEvent(20);
		waitTime(2000);
		String language = getLanguage(userType);
		Response resp = ResponseInstance.getResponseForPagesTv("Home", language, 1, userType);
		for (int i = 1; i <= 20; i++) {
			int total = resp.jsonPath().getInt("buckets[" + i + "].total");
			if (total > 20) {
				viewAllTrayname = resp.jsonPath().getString("buckets[" + i + "].title");
				logger.info(viewAllTrayname);
				break;
			}
		}
		TVRemoteEvent(20);
		waitTime(2000);
		for (int i = 0; i <= 30; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewAllTrayApi, "Tray content")) {
				logger.info("Collection tray is displayed");
				extent.extentLoggerPass("Collection", "Collection tray is displayed");
				TVRemoteEvent(20);
				waitTime(7000);
				break;
			} else {
				TVRemoteEvent(20);
			}
		}
		for (int i = 0; i <= 22; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallButton, "ViewAll button")) {
				TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
				waitTime(5000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
					logger.info("User is navigated to view all/collection page");
					extent.extentLoggerPass("Page", "User is navigated to view all/collection page");
				} else {
					TVclick(Zee5TvWelcomePage.objViewallButton, "ViewAll button");
					if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
						logger.info("User is navigated to view all/collection page");
						extent.extentLoggerPass("Page", "User is navigated to view all/collection page");
					}
				}
				waitTime(7000);

				break;
			} else {
				TVRemoteEvent(22);
			}

		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
			String pagename = TVgetText(Zee5TvWelcomePage.objViewallPageHead);
			logger.info("User is navigated to " + pagename + " collection page");
			extent.extentLoggerPass("Page", "User is navigated to " + pagename + " collection page");
		}
		TVRemoteEvent(20);
		waitTime(4000);
		TVRemoteEvent(20);
		waitTime(4000);
		TVRemoteEvent(23);
		waitTime(8000);

		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallPageHead, "View all page")) {
			logger.info("User is navigated to page collection page");
			extent.extentLoggerPass("Page", "User is navigated to page collection page");
		} else {
			logger.info("User is not navigated to page collection page");
			extent.extentLogger("User", "User is not navigated to page collection page");
		}

		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
		}
	}

	/**
	 *   Function to perform the Subscription pop up validation functionality
	  */
	@SuppressWarnings("rawtypes")
	public void subscription() throws Exception {

		HeaderChildNode("Subscription popup validation");
		waitTime(10000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(10000);

if(userType.equals("Guest")|| userType.equals("NonSubscribedUser")) {
		HeaderChildNode("Before Tv functionality");
		for (int i = 0; i <= 18; i++) 
		{
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content")) {
				TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
				waitTime(7000);
				break;
				}
			 
       else {
				TVRemoteEvent(20);
			}
			
		}
		if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
			logger.info("Subscribe popup is displayed when user clicks on before tv contents");
			extent.extentLoggerPass("Page",
					"Subscribe popup is displayed when user clicks on before tv contents");
		} else {
			TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
			waitTime(3000);
		}
		waitTime(5000);	
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption Screen")) {
				logger.info("Trailer is availabe for before tv contents");
				waitForElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, 60000);
				waitTime(3000);
				verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup");
					logger.info("Subscribe popup is displayed when user clicks on before tv contents");
					extent.extentLoggerPass("Page",
							"Subscribe popup is displayed when user clicks on before tv contents");
			}
			else {
				waitTime(5000);
				if(	verifyIsElementDisplayed(Zee5TVCarousel.objLoginPopUptitle, "Login popup")) {
						logger.info("Login popup is displayed when user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								"Login popup is displayed when user clicks on before tv contents");
			}
			}
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
}

		if (userType.equals("SubscribedUser")) {
			HeaderChildNode("Subscription plan page validation");
			waitTime(10000);
			if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
					waitTime(2000);
					TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
					extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
				}
				else {
					logger.info("profile selection not displayed");
				}
				}
				if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
					TVRemoteEvent(20);
					waitTime(2000);
					TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
					extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
					logger.info("logged in as Guest User");
					extent.extentLoggerPass("Button", "logged in as Guest User");
				} else {
					logger.info("User is logged in");
					extent.extentLoggerPass("Button", "User is logged in");
				}
				}
				waitTime(5000);
				TVRemoteEvent(21);
				TVRemoteEvent(21);
			for (int i = 0; i <=7; i++) {
				TVRemoteEvent(20);
				waitTime(2000);
			}
			TVTabSelect("Settings");
			TVRemoteEvent(23);
			waitTime(2000);
			TVRemoteEvent(22);
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objMyPlanOption, "My Plan option");
//			waitTime(2000);
//			TVclick(Zee5TvWelcomePage.objMyPlanOption, "My Plan option");
			waitTime(4000);
			if (verifyIsElementDisplayed(Zee5TVCarousel.objActivePlan, "Active plan card")) {
				logger.info("Active plan is displayed for subscribed user in My plan page");
				extent.extentLoggerPass("Plan", "Active plan is displayed for subscribed user in My plan page");
			} else {
				logger.info("Active plan is not displayed for subscribed user");
				extent.extentLoggerFail("Plan", "Active plan is not displayed for subscribed user");
			}
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			waitTime(8000);
			if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscriptionPlanPage, "My Plans page")) {
				logger.info("User is navigated to Subscription page post tapping on View all plan in my plan page");
				extent.extentLoggerPass("plan",
						"User is navigated to Subscription page post tapping on View all plan in my plan page");
			} else {
				logger.info("Navigation failed");
				extent.extentLoggerFail("Plan", "Navigation failed");
			}
			List Options2 = getDriver().findElements(Zee5TVCarousel.objSubscribePagePacks);
			Options2.size();
			for (int i = 1; i <= Options2.size(); i++) {
				String plans = getDriver().findElement(Zee5TvPlayerPage.objSpecificplan(i)).getText();
				logger.info("Plan available in subscription page : " + plans);
				extent.extentLoggerPass("Plan", "Plan available in subscription page : " + plans);
			}
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().closeApp();

			waitTime(3000);

			getDriver().launchApp();

			waitTime(10000);
		}
	}

	/**
	 *  Function to perform LiveTv tab functionality
	  */
	public void liveTv() throws Exception {
		HeaderChildNode("All channel tab functionality");
			waitTime(20000);
			if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
					waitTime(2000);
					TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
					extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
				}
				}
				if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
					TVRemoteEvent(20);
					waitTime(2000);
					TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
					extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
					logger.info("logged in as Guest User");
					extent.extentLoggerPass("Button", "logged in as Guest User");
				} else {
					logger.info("User is logged in");
					extent.extentLoggerPass("Button", "User is logged in");
				}
				}
				waitTime(10000);
				for (int i = 0; i <= 4; i++) {
					TVRemoteEvent(21);
					waitTime(2000);
				}

				for (int i = 0; i <= 7; i++) {
					TVRemoteEvent(20);
					waitTime(2000);
				}

				TVTabSelect("Live TV");
				waitTime(3000);
				TVRemoteEvent(23);
				waitTime(5000);
				TVclick(Zee5TvHomePage.objChannelFilterButton, "Channel filter");
				waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objChannelFilterButton, "Channel filter")) {
			logger.info("User is navigated to all channel page and filter button is displayed");
			extent.extentLoggerPass("Channel", "User is navigated to all channel page and filter button is displayed");
		} else {
			logger.info("Navigation for to all channel tab");
			extent.extentLoggerFail("Channel", "Navigation for to all channel tab");
		}
		waitTime(5000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(23);
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objFilterPopup, "Filter popup")) {
			logger.info("Filter popup is displayed when user clicks on filter button is all channel page");
		}
		else {
			TVRemoteEvent(23);
			waitTime(5000);
		verifyIsElementDisplayed(Zee5TvHomePage.objFilterPopup, "Filter popup");
			logger.info("Filter popup is displayed when user clicks on filter button is all channel page");
		}
		waitTime(5000);
		HeaderChildNode("All channel tab Filter button functionality");
		if (verifyIsElementDisplayed(Zee5TvHomePage.objFilterPopup, "Filter popup")) {
			logger.info("Filter popup is displayed when user clicks on filter button is all channel page");
			extent.extentLoggerPass("Filter",
					"Filter popup is displayed when user clicks on filter button is all channel page");
		}
		//verifyIsElementDisplayed(Zee5TvHomePage.objLanguageFilterPopup, "Language option in filter popup");
		//verifyIsElementDisplayed(Zee5TvHomePage.objGenreInFilterPopup, "Genre option in filter popup");
		waitTime(3000);
		int languageSelected = getDriver().findElements(Zee5TvWelcomePage.objSelectedOption).size();
		for (int i = 0; i <= 4; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVRemoteEvent(23);
		waitTime(2000);
		//TVclick(Zee5TvHomePage.objLanguageFilterPopup, "Filter");
		int languageSelectedAfter = getDriver().findElements(Zee5TvWelcomePage.objSelectedOption).size();
		logger.info(languageSelected);
		logger.info(languageSelectedAfter);
		if (languageSelected != languageSelectedAfter) {
			logger.info("Language functionality verified");
			extent.extentLoggerPass("Language", "Language functionality verified");
		} else {
			logger.info("Language functionality failed");
			extent.extentLoggerFail("Language", "Language functionality failed");
		}
		waitTime(3000);
		//TVclick(Zee5TvHomePage.objGenreInFilterPopup, "Genre option in filter popup");
		//waitTime(3000);
//		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objSelectedOption, "Genre filter counter")) {
//			logger.info("User has not set any genre is filter section");
//			extent.extentLoggerPass("Genre", "User has not set any genre is filter section");
//		} else {
//			logger.info("User has set  genre is filter section");
//			extent.extentLoggerPass("Genre", "User has set  genre is filter section");
//		}
//		for (int i = 0; i <= 4; i++) {
//			TVRemoteEvent(20);
//			waitTime(2000);
//		}
//		TVRemoteEvent(23);
//		waitTime(6000);
//		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objSelectedOption, "Genre filter counter")) {
//			logger.info("Clicked on genre and genre functionality successfull");
//			extent.extentLoggerPass("Genre", "Clicked on genre and genre functionality successfull");
//		} else {
//			logger.info("Genre functionality Failed");
//			extent.extentLoggerFail("Genre", "Genre functionality Failed");
//		}
		getDriver().navigate().back();
		waitTime(3000);
//		getDriver().navigate().back();
//		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
	}

	/**
	 *  Function to perform BeforeTv tray validation
	  */
	public void beforeTV() throws Exception {
		HeaderChildNode("BeforeTV tray validation");
		waitTime(10000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(10000);

if(userType.equals("Guest")|| userType.equals("NonSubscribedUser")) {
		HeaderChildNode("Before Tv functionality");
		for (int i = 0; i <= 18; i++) 
		{
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content")) {
				TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
				waitTime(7000);
				break;
				}
			 
       else {
				TVRemoteEvent(20);
			}
			
		}
		if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
			logger.info("Subscribe popup is displayed when user clicks on before tv contents");
			extent.extentLoggerPass("Page",
					"Subscribe popup is displayed when user clicks on before tv contents");
		} else {
			TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
			waitTime(3000);
		}
		waitTime(5000);	
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption Screen")) {
				logger.info("Trailer is availabe for before tv contents");
				waitForElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, 60000);
				waitTime(3000);
				verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup");
					logger.info("Subscribe popup is displayed when user clicks on before tv contents");
					extent.extentLoggerPass("Page",
							"Subscribe popup is displayed when user clicks on before tv contents");
			}
			else {
				waitTime(5000);
					if(verifyIsElementDisplayed(Zee5TVCarousel.objLoginPopUptitle, "Login popup")) {
						logger.info("Login popup is displayed when user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								"Login popup is displayed when user clicks on before tv contents");
			}
			}
		waitTime(3000);
		
//		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {
//
//			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
//			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
//		} else {
//
//			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
//		}
//		waitTime(7000);
//		String searchdata[] = { "g", "a", "t", "t", "i", "m", "e", "l", "a" };
//		type(searchdata);
//		List<WebElement> ele2 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele2.size(); i++) {
//			String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Shows"),
//					"Searched Show"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Shows"), "serached Show");
//				break;
//			} else {
//				System.out.println("No match");
//			}
//
//		}
//		waitTime(8000);
//		// LoadingInProgress();
//		waitTime(2000);
//		TVclick(Zee5TvHomePage.obj3rdEpisode, "Previous episode");
//		waitTime(5000);
////		LoadingInProgress();
//		waitTime(10000);
//		AdVerify();
//		waitTime(3000);
//		TVRemoteEvent(23");
//		waitTime(2000);
//		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
//			logger.info("User is able to play non premium content and it is playable");
//			extent.extentLoggerPass("play", "User is able to play non premium content and it is playable");
//		} else {
//			logger.info("playback did not initiate");
//			extent.extentLoggerFail("play", "playback did not initiate");
//		}
//		getDriver().navigate().back();
//		waitTime(3000);
//		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
//			logger.info(
//					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
//			extent.extentLoggerPass("Popup",
//					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
//			getDriver().navigate().back();
//		} else {
//			logger.info("Popup is not displayed");
//			extent.extentLogger("Popup", "Popup is not displayed");
//		}
//
//		getDriver().navigate().back();
//		waitTime(3000);
//		getDriver().navigate().back();
//		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
	}
    if(userType.equals("SubscribedUser")){
		HeaderChildNode("Before Tv functionality");
		for (int i = 0; i <= 18; i++) 
		{
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content")) {
				TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
				waitTime(7000);
				break;
				}
			 
       else {
				TVRemoteEvent(20);
			}
			
		}
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption Screen")) {
			logger.info("User is able to Play Befor Tv Content");
			extent.extentLoggerPass("Player",
					"User is able to Play Befor Tv Content");
		} else {
			TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
			waitTime(10000);
			
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption Screen")) {
				logger.info("User is able to Play Befor Tv Content");
				extent.extentLoggerPass("Player",
						"User is able to Play Befor Tv Content");
			} else {
				logger.info("User is not able to Play Befor Tv Content");
				extent.extentLoggerPass("Player",
						"User is not able to Play Befor Tv Content");
			}
		}
			
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
    }


	}
	
	/**
	 *  Function to perform upnext functionality
	  */
	
	
	public void upnext() throws Exception {
		HeaderChildNode("UpNext Tray Validation");
		if (userType.equals("Guest")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
		}
		TVTabSelect("Home");
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);

//		String content = TVgetText(Zee5TvSearchPage.objEditbox);
//
//		logger.info("Entered Search Data : " + content);
//		extent.extentLogger("Search", "Entered Searched Data : " + content);

//		int k;
//		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele.size(); i++) {
//			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			logger.info("Serach result content title : " + title);
//			extent.extentLogger("Title", "Serach result content title : " + title);
//
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
//					"Searched Movie"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
//				break;
//			} else {
//				System.out.println("No match");
//			}
//
//		}

		waitTime(8000);
		// LoadingInProgress();

		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(10000);

		// Consumption Page

		// LoadingInProgress();
		waitTime(5000);
		AdVerify();

		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			logger.info("clicked on skip intro");
			extent.extentLoggerPass("Intro", "clicked on skip intro");
		} else {
			logger.info("Skip intro is not displayed");
			extent.extentLoggerPass("Intro", "Skip intro is not displayed");
		}
		waitTime(20000);
		TVRemoteEvent(23);
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objinfo, "Info")) {
			logger.info("Player is paused");
		} else {
			TVRemoteEvent(23);
		}
		waitTime(3000);
		TVRemoteEvent(20);
		waitTime(3000);
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(6000);

		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objUpnextrail, "Up Next rail")) {
			logger.info("User can click on upnext button and up next rail is displayed");
			extent.extentLoggerPass("UpNext", "User can click on upnext button and up next rail is displayed");
		} else {
			logger.info("Upnext tab functionality failed");
			extent.extentLoggerFail("UpNext", "Upnext tab functionality failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLoggerPass("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			getDriver().navigate().back();
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLoggerPass("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			getDriver().navigate().back();
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
		}
	}

	
	/**
	 *  Function to perform settings page language button functionality
	  */

	@SuppressWarnings("rawtypes")
	public void languagePage() throws Exception {
		HeaderChildNode("Setting Page Language button functionality");
		waitTime(20000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(10000);
		for (int i = 0; i <= 4; i++) {
			TVRemoteEvent(21);
			waitTime(2000);
		}

		for (int i = 0; i <= 7; i++) {
			TVRemoteEvent(20);
			waitTime(2000);
		}

		TVTabSelect("Settings");
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(3000);
		TVclick(Zee5TvHomePage.objlanguageButton, "Language option");
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objDisplaylanguageButton, "Display language button")) {
			logger.info("User is navigated to language screen post tapping on language button in setting page");
			extent.extentLoggerPass("Navigation",
					"User is navigated to language screen post tapping on language button in setting page");
		} else {
			logger.info("User is not navigated to language screen post tapping on language button in setting page");
			extent.extentLoggerFail("Navigation",
					"User is not navigated to language screen post tapping on language button in setting page");
		}
		HeaderChildNode("Display language options validation");
		List Options2 = getDriver().findElements(Zee5TvHomePage.objDisplaylanguageOptions);
		Options2.size();
		for (int i = 1; i <= Options2.size(); i++) {
			TVclick(Zee5TvHomePage.objDisplaylanguageOptionsSelect(i), "Display language options");
			String language = TVgetText(Zee5TvHomePage.objDisplaylanguageOptionsSelectText(i));
			logger.info("clicked on " + language + " language option in display language menu");
		}
		TVclick(Zee5TvHomePage.objSaveButton, "Save button");
		waitTime(3000);
		TVclick(Zee5TvHomePage.objSaveButton, "Save button");
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLoggerPass("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}
		getDriver().navigate().back();

		TVRemoteEvent(19);
		waitTime(3000);

		TVRemoteEvent(23);
		TVclick(Zee5TvHomePage.objContentlanguageButton, "Content language button");
		waitTime(3000);
		TVRemoteEvent(19);
		waitTime(3000);

		TVRemoteEvent(23);

		waitTime(3000);
		TVRemoteEvent(23);

		TVclick(Zee5TvHomePage.objEnglistDisplaylanguageButton, "English display language");
		waitTime(2000);
		TVclick(Zee5TvHomePage.objEnglistDisplaylanguageButton, "English display language");
		TVclick(Zee5TvHomePage.objSaveButton, "Save button");
		waitTime(3000);
		TVclick(Zee5TvHomePage.objSaveButton, "Save button");
		waitTime(3000);
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLoggerPass("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			getDriver().navigate().back();
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}

		HeaderChildNode("Content language options validation");
		List contentOptions = getDriver().findElements(Zee5TvHomePage.objContentlanguageButtonChecked);
		contentOptions.size();
		for (int i = 1; i <= contentOptions.size(); i++) {
			TVclick(Zee5TvHomePage.objContentlanguageOptionsSelectText(1), "Selected content language");
			waitTime(3000);
		}
		TVclick(Zee5TvHomePage.objOdiaContentlanguageButton, "Odia content language option");
		TVclick(Zee5TvHomePage.objSaveButton, "Save button");
		waitTime(3000);
		TVclick(Zee5TvHomePage.objSaveButton, "Save button");
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objAddcontentLanguagePopup, "Add language popup")) {
			logger.info(
					"For better ZEE5 experience, Please select at least one more language of your choice while selecting primary 2 languages popup is displayed when user select only 1 language in content language menu");
			extent.extentLoggerPass("Popup",
					"For better ZEE5 experience, Please select at least one more language of your choice while selecting primary 2 languages popup is displayed when user select only 1 language in content language menu");
		}
		getDriver().navigate().back();
		waitTime(3000);
		List contentOptions2 = getDriver().findElements(Zee5TvHomePage.objContentlanguageOptions);
		contentOptions2.size();
		for (int i = 1; i <= Options2.size(); i++) {
			TVclick(Zee5TvHomePage.objContentlanguageOptionsSelect(i), "Content language options");
			String language = TVgetText(Zee5TvHomePage.objDisplaylanguageOptionsSelectText(i));
			logger.info("clicked on " + language + " language option in content language menu");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
		}
	}
	/**
	    Function to perform the display and content language popup validation functionality
	  */
	public void chooseLanguagePopup(String userType) throws Exception {
		HeaderChildNode("Choose display/content language popup");
		waitTime(20000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objChooseLanguagePopup, "Choose display language popup")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objChooseLanguagePopupContinue, "Continue button");
			waitTime(4000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objChooseLanguagePopupContinue, "Continue button");
			waitTime(3000);

		} else {
			logger.info("Choose language popup not displayed");
			extent.extentLoggerPass("Popup", "Choose language popup not displayed");
		}
	}
	
	/**
	 *  Function to perform the Ads validation
	  */

	public void ads() throws Exception {
		HeaderChildNode("Ad validation");
		if (userType.equals("Guest")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
		}
		TVTabSelect("Home");
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);

//		String content = TVgetText(Zee5TvSearchPage.objEditbox);
//
//		logger.info("Entered Search Data : " + content);
//		extent.extentLogger("Search", "Entered Searched Data : " + content);

//		int k;
//		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele.size(); i++) {
//			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			logger.info("Serach result content title : " + title);
//			extent.extentLogger("Title", "Serach result content title : " + title);
//
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
//					"Searched Movie"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
//				break;
//			} else {
//				System.out.println("No match");
//			}
//
//		}

		waitTime(8000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(20000);

		// Consumption Page

		// LoadingInProgress();
		// waitTime(10000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objAd, "Ad")) {
				logger.info("Ad is present when user click on any content and verified");
				extent.extentLoggerPass("Ad", "Ad is present when user click on any content and verified");
				AdVerify();
			} else {
				logger.info("Ad is not present");
				extent.extentLoggerPass("Ad", "Ad is not present");
			}

		}
		if (userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objAd, "Ad")) {
				logger.info("Ad is present when user click on any content for subscribe content");
				extent.extentLoggerFail("Ad", "Ad is present when user click on any content for subscribe content");
			} else {
				logger.info("Ad is not present for subscribe user");
				extent.extentLoggerPass("Ad", "Ad is not present for subscribe user");
			}
		}
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			logger.info("clicked on skip intro");
			extent.extentLoggerPass("Intro", "clicked on skip intro");
		} else {
			logger.info("Skip intro is not displayed");
			extent.extentLoggerPass("Intro", "Skip intro is not displayed");
		}
		waitTime(20000);
		TVRemoteEvent(23);
		waitTime(2000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Player control")) {
			logger.info("Content playback is started after ad playback");
			extent.extentLoggerPass("Player", "Content playback is started after ad playback");
		} else {
			logger.info("Content playback is not started after ad playback");
			extent.extentLogger("Player", "Content playback is not started after ad playback");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		TVTabSelect("Home");
		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
		}
	}

	public void profile() throws Exception {
		HeaderChildNode("Profile options validations");
		if (userType.equals("Guest")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
		}

		TVTabSelect("Home");
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objSearchSpaceBar, "Search page")) {
			logger.info("User is navigated to search page after clicking on search button");
			extent.extentLoggerPass("Search", "User is navigated to search page after clicking on search button");
		} else {
			logger.info("User is not navigated to serach page");
			extent.extentLoggerFail("Navigation", "User is not navigated to serach page");
		}
		String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
		type(searchdata1);
		waitTime(5000);
//		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
		waitTime(2000);
//		logger.info("Entered Search Data : " + content);
//		extent.extentLoggerPass("Search", "Entered Searched Data : " + content);

//		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
//		for (int i = 1; i <= ele.size(); i++) {
//
//			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
//			logger.info(title);
//			extent.extentLogger("Title", "Serach result content title : " + title);
//			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
//					"Searched Premium movie"))) {
//				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
//				break;
//			} else {
//				logger.info("No match");
//			}
//
//		}
		waitTime(8000);
		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		if (title.equals(title)) {
			logger.info("user is navigated to respective content detail page");
			extent.extentLoggerPass("user", "user is navigated to respective content detail page");
		}
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(4000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium content as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium content as guest user");
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}

		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content")) {
				TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
				waitTime(7000);
				if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
					if (userType.equals("Guest")) {
						logger.info("Login popup is displayed when Guest user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								"Login popup is displayed when Guest user clicks on before tv contents");
					}

				} else {
					TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
					waitTime(4000);
					if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
						if (userType.equals("Guest")) {
							logger.info("Login popup is displayed when Guest user clicks on before tv contents");
							extent.extentLoggerPass("Page",
									"Login popup is displayed when Guest user clicks on before tv contents");
						}
					} else {
						logger.info("User is navigated to consumption page and before TV content is played");
						extent.extentLoggerPass("Player",
								"User is navigated to consumption page and before TV content is played");
					}
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(3000);
		if (userType.equals("Guest")) {
			if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objLogoutOption, "Logout option")) {
				logger.info("Logout option is not displayed in setting page");
				extent.extentLoggerPass("Logout", "Logout option is not displayed in setting pages");
			} else {
				logger.info("Logout option is displayed in setting page");
				extent.extentLoggerFail("Logout", "Logout option is displayed in setting page");
			}
		}
		waitTime(3000);
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objEditProfileInMyProfile, "Edit profile option")) {
			logger.info("Edit profile is not displayed in setting page");
			extent.extentLoggerPass("Logout", "Edit profile is not displayed in setting pages");
		} else {
			logger.info("Edit profile is displayed in setting page");
			extent.extentLoggerFail("Edit", "Edit profile is displayed in setting page");
		}
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objVideoqualityOption, "Video quality option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objVideoqualityOption, "Video quality option");
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objVideoQualityHeader, "Video quality page header")) {
			logger.info("User is navigated to video quality page");
			extent.extentLoggerPass("Video", "User is navigated to video quality page");
		} else {
			logger.info("Navigation failed");
			extent.extentLoggerFail("Video", "Navigation failed");
		}
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions2));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions3));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions4));
		logger.info("Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions5));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions2));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions3));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions4));
		extent.extentLoggerPass("Video",
				"Video quality options : " + TVgetText(Zee5TvWelcomePage.objVideoQualityResolutionOptions5));

		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		TVTabSelect("Home");
		TVTabSelect("Shows");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content")) {
				TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objshowpageTrayContent, "Show page Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
			waitTime(5000);
			TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
			waitTime(2000);
			TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
			waitTime(2000);
			waitTime(5000);
			TVRemoteEvent(23);
			waitTime(3000);

			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
				logger.info("User is navigated to consumption page");
				extent.extentLoggerPass("play", "User is navigated to consumption page");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}
			getDriver().navigate().back();
			waitTime(5000);
			getDriver().navigate().back();
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "Watch trailer")) {
				logger.info("User is navigated to content detail page when back button is tapped in consumption page");
				extent.extentLoggerPass("Content",
						"User is navigated to content detail page when back button is tapped in consumption page");
			} else {
				logger.info("Navigation failed");
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		for (int i = 0; i <= 18; i++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
		if (verifyIsElementDisplayed(Zee5TvHomePage.objSelectTab("Shows"), "Shows landing")) {
			logger.info("User is navigated to Shows landing page when back button is tapped in content detail page");
			extent.extentLoggerPass("Content",
					"User is navigated to Shows landing page when back button is tapped in content detail page");
		} else {
			logger.info("Navigation failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSelectTab("Home")).equals("true")) {
			logger.info("User is navigated to home page post tapping back button in shows landing page");
			extent.extentLoggerPass("Navigation",
					"User is navigated to home page post tapping back button in shows landing page");
		} else {
			logger.info("Navigation failed");
		}
		getDriver().navigate().back();
		waitTime(4000);
		if (!verifyIsElementDisplayed(Zee5TvHomePage.objSelectTab("Home"), "Home page")) {
			logger.info("User is navigated to TV home page after tapping back button in Zee Home landing page");
			extent.extentLoggerPass("Navigation",
					"User is navigated to TV home page after tapping back button in Zee Home landing page");
		} else {
			logger.info("Navigation failed");
		}

		getDriver().launchApp();

		waitTime(10000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
		}

	}
	
	/**
	 *  Function to Validate the  header section
	  */

	@SuppressWarnings("rawtypes")
	public void headerSection() throws Exception {
		HeaderChildNode("Verification of Header section options");
		waitTime(20000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(10000);
			for (int i = 0; i <= 4; i++) {
				TVRemoteEvent(21);
				waitTime(2000);
			}

			for (int i = 0; i <= 7; i++) {
				TVRemoteEvent(20);
				waitTime(2000);
			}

		List Options2 = getDriver().findElements(Zee5TvWelcomePage.objMenuTopIcon);
		Options2.size();
		logger.info(Options2.size());
		for (int i = 1; i <= Options2.size(); i++) {
			String plans = getDriver().findElement(Zee5TvWelcomePage.objMenuTopParticular(i)).getText();
			logger.info(plans + " Tab is displayed ");
			extent.extentLoggerPass("Plan", plans + " Tab is displayed ");
		}
		TVTabSelect("Home");
		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(20000);
		if (userType.equals("Guest")) {
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
			TVRemoteEvent(19);
			waitTime(3000);
		}
	}
	
	/**
	 *  Function to perform the device Authentication functionality
	  */

	public void deviceAuthentication() throws Exception {
		HeaderChildNode("Device Authentication functionality");
		waitTime(15000);
		verifyIsElementDisplayed(Zee5TvHomePage.objonboardingrevamploginbutton, "Already Register button");
		TVclick(Zee5TvHomePage.objonboardingrevamploginbutton, "Already Register button");
		waitTime(8000);
		code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(10000);
		click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
		waitTime(2000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

		if (userType.equals("NonSubscribedUser") || userType.equals("Guest")) {
			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);

		}
		if (userType.equals("SubscribedUser")) {
			String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
			waitTime(5000);

		}
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");

		type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		waitTime(3000);
		BrowsertearDown();
		setPlatform("TV");
		waitTime(10000);
		TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objcarouselBanner, "Home landing")) {
			logger.info("User is navigated to Home landing page post authentication");
			logger.info("User logged in with existing id");
			extent.extentLoggerPass("Id", "User logged in with existing id");
			extent.extentLoggerPass("Home", "User is navigated to Home landing page post authentication");
		} else {
			logger.info("Post Authentication Navigation failed");
		}

		for (int i = 0; i <= 4; i++) {
			TVRemoteEvent(21);
			waitTime(2000);
		}

		for (int i = 0; i <= 7; i++) {
			TVRemoteEvent(20);
			waitTime(2000);
		}

		TVTabSelect("Settings");
		TVRemoteEvent(23);
		waitTime(5000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		waitTime(2000);
		for (int i = 0; i <= 7; i++) {
			TVRemoteEvent(22);
			waitTime(3000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
//		waitTime(3000);
//		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(10000);
		getDriver().closeApp();
		waitTime(5000);
		getDriver().launchApp();
		waitTime(3000);
	}

	public void authenticationLogin(String userid) throws Exception {
		HeaderChildNode("Authenticating device through Email " + userid + " id");
		waitTime(5000);
		code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		HeaderChildNode("Switching to WEB platform to Authenticate device");
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(5000);

		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		if (userid.equals("NonSubscribedUser")) {
			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);

		}
		if (userid.equals("SubscribedUser")) {
			String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
			waitTime(5000);

		}
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		waitTime(3000);
		BrowsertearDown();
		setPlatform("TV");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objcontinueButtonInLoginPage,
				"Continue button in TV authentication page")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(22);
			waitTime(3000);
			TVRemoteEvent(22);
			waitTime(3000);
			TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
			waitTime(15000);
		}
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objProfileOptionInSettingPage, "Profile option")) {
			logger.info("User is logged in succesfully with " + userid + " id");
			extent.extentLoggerPass("Login", "User is logged in succesfully with " + userid + " id");
		} else {
			logger.info("Login functionality failed");
			extent.extentLoggerFail("Login", "Login functionality failed");
		}
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);
	}
	
	/**
	 *  Function to perform Authenticating device through mobile number
	  */

	public void mobilenumberLogin() throws Exception {
		HeaderChildNode("Authenticating device through mobile number");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
			TVRemoteEvent(19);
			waitTime(2000);
			TVRemoteEvent(19);
			waitTime(2000);
			TVRemoteEvent(19);
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
			waitTime(7000);
		} else {
			logger.info("Popup not displayed");
		}
		code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(10000);
		click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
		waitTime(2000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		String mobileLogin = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("MobileLogin");
		String MobilePassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("MobilePassword");
		type(PWALoginPage.objEmailField, mobileLogin, "Email Field");
		waitTime(3000);
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWALoginPage.objWebMobileEnterPasswordButton, "Enter Password button");
		waitTime(5000);
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, MobilePassword, "Password field");
		waitTime(5000);
		verifyElementPresentAndClick(PWALoginPage.objProceedbuttoninpopup, "Proceed button in popup");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		waitTime(3000);
		BrowsertearDown();
		setPlatform("TV");
		waitTime(10000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objProfileOptionInSettingPage, "Profile option")) {
			logger.info("User is logged in succesfully with mobile number credentials");
			extent.extentLoggerPass("Login", "User is logged in succesfully with mobile number credentials");
		} else {
			logger.info("Login functionality failed");
			extent.extentLoggerFail("Login", "Login functionality failed");
		}
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);
	}

	public void device() throws Exception {
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			deviceAuthentication();

		}
		if (userType.equals("SubscribedUser")) {
			deviceAuthentication();

		}
	}

	/**
	 *  Function to perform Authenticating device through facebook login
	  */
	public void facebooklogin() throws Exception {
		HeaderChildNode("Authenticating device through facebook login");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
			TVRemoteEvent(19);
			waitTime(2000);
			TVRemoteEvent(19);
			waitTime(2000);
			TVRemoteEvent(19);
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
			waitTime(7000);
		} else {
			logger.info("Popup not displayed");
		}
		code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(10000);
		click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
		waitTime(2000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		waitTime(10000);
		click(PWALoginPage.objFacebookIcon, "Facebook Icon");
		switchToWindow(2);

		if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");

		}

		else {
			checkElementDisplayed(PWALoginPage.objFacebookPageVerificationWeb, "Facebook page");
			verifyElementPresent(PWALoginPage.objFacebookLoginEmailWeb, " Email Field");
			type(PWALoginPage.objFacebookLoginEmailWeb, "8277333678", "Emial Field");
			verifyElementPresent(PWALoginPage.objFacebookLoginpasswordWeb, " Password Field");
			type(PWALoginPage.objFacebookLoginpasswordWeb, "Igs123!@#", "Password Field");
			verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPageWeb, "Login Button");
			switchToWindow(1);
			waitForElementDisplayed(PWALandingPages.objWebProfileIcon, 20);
		}
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		waitTime(3000);
		BrowsertearDown();
		setPlatform("TV");
		waitTime(10000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objProfileOptionInSettingPage, "Profile option")) {
			logger.info("User is logged in succesfully with facebook credentials");
			extent.extentLoggerPass("Login", "User is logged in succesfully with facebook credentials");
		} else {
			logger.info("Login functionality failed");
			extent.extentLoggerFail("Login", "Login functionality failed");
		}
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
//		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
//		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		getDriver().closeApp();
		waitTime(3000);
		getDriver().launchApp();
		waitTime(7000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
	}

	/**
	 *  Function to perform Static page options validation
	  */
	public void staticPages() throws Exception {
		HeaderChildNode("Static page options validation");
			waitTime(20000);
			if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
					waitTime(2000);
					TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
					extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
				}
				}
				if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
					TVRemoteEvent(20);
					waitTime(2000);
					TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
					extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
					logger.info("logged in as Guest User");
					extent.extentLoggerPass("Button", "logged in as Guest User");
				} else {
					logger.info("User is logged in");
					extent.extentLoggerPass("Button", "User is logged in");
				}
				}
				waitTime(10000);
				for (int i = 0; i <= 4; i++) {
					TVRemoteEvent(21);
					waitTime(2000);
				}

				for (int i = 0; i <= 7; i++) {
					TVRemoteEvent(20);
					waitTime(2000);
				}

				TVTabSelect("Settings");
				waitTime(3000);
				TVRemoteEvent(23);
				waitTime(5000);
				TVRemoteEvent(22);
				waitTime(2000);
				TVRemoteEvent(22);
				waitTime(3000);
			
			if (verifyIsElementDisplayed(Zee5TvHomePage.objOptionsInSetting("FAQ"), "FAQ option in setting page")) {
				TVclick(Zee5TvHomePage.objOptionsInSettingClick("FAQ"), "FAQ option");
				waitTime(3000);
				TVRemoteEvent(23);
				waitTime(7000);
				verifyIsElementDisplayed(Zee5TvHomePage.objFAQPage, "FAQ page");
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(2000);
				getDriver().navigate().back();
				waitTime(2000);
				
			} else {
				logger.info("Navigation Failed");
			}
			waitTime(4000);
			for (int i = 0; i <= 4; i++) {
				TVRemoteEvent(21);
				waitTime(2000);
			}

			for (int i = 0; i <= 7; i++) {
				TVRemoteEvent(20);
				waitTime(2000);
			}

			TVTabSelect("Settings");
			waitTime(3000);
			TVRemoteEvent(23);
			waitTime(5000);
			TVRemoteEvent(22);
		
			waitTime(2000);
			verifyIsElementDisplayed(Zee5TvHomePage.objOptionsInSetting("Contact"), "Contact us option in setting page");
			//TVclick(Zee5TvHomePage.objOptionsInSettingClick("Contact"), "Contact us option");
			if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(21);
		    waitTime(3000);
			TVRemoteEvent(23);
			waitTime(7000);
			if(verifyIsElementDisplayed(Zee5TvHomePage.objContactUsPage, "Contact us page")) {
			waitTime(3000);			
				getDriver().navigate().back();
				waitTime(2000);
				getDriver().navigate().back();
				waitTime(2000);
			}
			 else {
				logger.info("Navigation Failed");
			}
			}
			
			if (userType.equals("Guest")) {
				TVRemoteEvent(22);
				waitTime(2000);
				TVRemoteEvent(22);
				waitTime(2000);
				TVRemoteEvent(23);
				waitTime(7000);
				if(verifyIsElementDisplayed(Zee5TvHomePage.objContactUsPage, "Contact us page")) {
				waitTime(3000);			
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
				else {
					logger.info("Navigation Failed");
				}
			}
		
		
			for (int i = 0; i <= 4; i++) {
				TVRemoteEvent(21);
				waitTime(2000);
			}

			for (int i = 0; i <= 7; i++) {
				TVRemoteEvent(20);
				waitTime(2000);
			}

			TVTabSelect("Settings");
			waitTime(3000);
			TVRemoteEvent(23);
			waitTime(5000);
			TVRemoteEvent(22);
		
			waitTime(2000);
			
			if (verifyIsElementDisplayed(Zee5TvHomePage.objOptionsInSetting("About"),
					"About us option in setting page")) {
				TVclick(Zee5TvHomePage.objOptionsInSettingClick("About"), "About us option");
				waitTime(3000);
				TVRemoteEvent(23);
				waitTime(10000);
				verifyIsElementDisplayed(Zee5TvHomePage.objAboutUsPage, "About us page");
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(2000);
				getDriver().navigate().back();
				waitTime(2000);
				
				
				
			} else {
				logger.info("Navigation Failed");
			}
			
		
			for (int i = 0; i <= 4; i++) {
				TVRemoteEvent(21);
				waitTime(2000);
			}

			for (int i = 0; i <= 7; i++) {
				TVRemoteEvent(20);
				waitTime(2000);
			}

			TVTabSelect("Settings");
			waitTime(3000);
			TVRemoteEvent(23);
			waitTime(5000);
			TVRemoteEvent(22);
		
			waitTime(2000);
			
			if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvHomePage.objOptionsInSetting("Terms"),
					"Terms of use option in setting page")) {
				TVclick(Zee5TvHomePage.objOptionsInSettingClick("Terms"), "Terms of use option");
				waitTime(3000);
				TVRemoteEvent(23);
				waitTime(10000);
				verifyIsElementDisplayed(Zee5TvHomePage.objTermsOfUsePage, "Terms of use page");
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(2000);
				getDriver().navigate().back();
				waitTime(2000);
				
			} else {
				logger.info("Navigation Failed");
			}
			}
			
			if (userType.equals("Guest")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVRemoteEvent(20);
				waitTime(2000);
				TVRemoteEvent(21);
			    waitTime(3000);
				TVRemoteEvent(23);
				waitTime(7000);
				if(verifyIsElementDisplayed(Zee5TvHomePage.objTermsOfUsePage, "Terms of use page")) {
				waitTime(3000);			
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
				 else {
					logger.info("Navigation Failed");
				}
			}
		
			for (int i = 0; i <= 4; i++) {
				TVRemoteEvent(21);
				waitTime(2000);
			}

			for (int i = 0; i <= 7; i++) {
				TVRemoteEvent(20);
				waitTime(2000);
			}

			TVTabSelect("Settings");
			waitTime(3000);
			TVRemoteEvent(23);
			waitTime(5000);
			TVRemoteEvent(22);
		
			waitTime(2000);
			if (verifyIsElementDisplayed(Zee5TvHomePage.objOptionsInSetting("Privacy"),
					"Privacy policy option in setting page")) {
				TVclick(Zee5TvHomePage.objOptionsInSettingClick("Privacy"), "Privacy policy option");
				waitTime(3000);
				TVRemoteEvent(23);
				waitTime(10000);
				verifyIsElementDisplayed(Zee5TvHomePage.objPrivacyPolicyPage, "Privacy policy page");
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(2000);
				getDriver().navigate().back();
				waitTime(2000);
				
				
			} else {
				logger.info("Navigation Failed");
			}
		
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
		
	}

	public static String getLanguage(String userType) {

		String language = null;
		if (userType.contains("Guest")) {
			language = "en,kn";
		} else {
			Response resplanguage = ResponseInstance.getUserinfoforNonSubORSub(userType);
			// System.out.println(resplanguage.print());

			// System.out.println(resplanguage.jsonPath().getList("array").size());

			for (int i = 0; i < resplanguage.jsonPath().getList("array").size(); i++) {

				String key = resplanguage.jsonPath().getString("[" + i + "].key");
				// System.out.println(language);
				if (key.contains("content_language")) {
					language = resplanguage.jsonPath().getString("[" + i + "].value");
					// System.out.println("UserType Language: " + language);
					break;
				}
			}
		}

		return language;

	}

	public void clubScenarios() throws Exception {
		HeaderChildNode("Club scenarios - Login through club user id");
		waitTime(15000);
		TVRemoteEvent(20);
		waitTime(3000);
		TVRemoteEvent(19);
		waitTime(3000);
		TVRemoteEvent(19);
		waitTime(3000);
		TVRemoteEvent(19);
		waitTime(3000);

		verifyIsElementDisplayed(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
		TVclick(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
		waitTime(8000);
		code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		HeaderChildNode("Switching to WEB platform to Authenticate device");
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(10000);
		verifyElementPresentAndClick(PWALoginPage.objLanguageButton, "Language button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("ClubUserName");
		String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("ClubPassword");
		type(PWALoginPage.objEmailField, Username, "Email Field");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, Password, "Password field");
		waitTime(5000);
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		waitTime(3000);
		BrowsertearDown();
		setPlatform("TV");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objcontinueButtonInLoginPage,
				"Continue button in TV authentication page")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(22);
			waitTime(3000);
			TVRemoteEvent(22);
			waitTime(3000);
			TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
			waitTime(15000);
		}
		HeaderChildNode("Before Tv content playback validation in home page");
		TVTabSelect("Home");
		for (int i = 0; i <= 20; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content")) {
				TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
				waitTime(7000);
				if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
					if (userType.equals("Guest")) {
						logger.info("Login popup is displayed when Guest user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								"Login popup is displayed when Guest user clicks on before tv contents");
					}
					if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
						logger.info("Subscribe popup is displayed when user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								"Subscribe popup is displayed when user clicks on before tv contents");
					}
				} else {
					TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
					waitTime(4000);
					if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
						if (userType.equals("Guest")) {
							logger.info("Login popup is displayed when Guest user clicks on before tv contents");
							extent.extentLoggerPass("Page",
									"Login popup is displayed when Guest user clicks on before tv contents");
						}
						if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
							logger.info("Subscribe popup is displayed when user clicks on before tv contents");
							extent.extentLoggerPass("Page",
									"Subscribe popup is displayed when user clicks on before tv contents");
						}
					} else {
						waitTime(12000);
						TVRemoteEvent(23);
						waitTime(2000);
						TVRemoteEvent(23);
						waitTime(4000);
						if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
							logger.info("User is able to play before tv content in home page for club user");
							extent.extentLoggerPass("play",
									"User is able to play before tv content in home page for club user");
						}
					}
					break;
				}
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLoggerPass("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			getDriver().navigate().back();
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		for (int i = 0; i <= 18; i++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
		HeaderChildNode("Before Tv content playback validation in shows page");
		TVTabSelect("Home");
		waitTime(2000);
		TVTabSelect("Shows");
		waitTime(3000);
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content")) {
				TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
				waitTime(7000);
				if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
					if (userType.equals("Guest")) {
						logger.info("Login popup is displayed when Guest user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								"Login popup is displayed when Guest user clicks on before tv contents");
					}
					if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
						logger.info("Subscribe popup is displayed when user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								"Subscribe popup is displayed when user clicks on before tv contents");
					}
				} else {
					TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
					waitTime(4000);
					if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
						if (userType.equals("Guest")) {
							logger.info("Login popup is displayed when Guest user clicks on before tv contents");
							extent.extentLoggerPass("Page",
									"Login popup is displayed when Guest user clicks on before tv contents");
						}
						if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
							logger.info("Subscribe popup is displayed when user clicks on before tv contents");
							extent.extentLoggerPass("Page",
									"Subscribe popup is displayed when user clicks on before tv contents");
						}
					} else {
						waitTime(12000);
						TVRemoteEvent(23);
						waitTime(2000);
						TVRemoteEvent(23);
						waitTime(4000);
						if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
							logger.info("User is able to play before tv content in shows page for club user");
							extent.extentLoggerPass("play",
									"User is able to play before tv content in shows page for club user");
						}
					}
					break;
				}
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLogger("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			getDriver().navigate().back();
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		for (int i = 0; i <= 18; i++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
		HeaderChildNode("Content playback validation in News page");
		TVTabSelect("News");
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content")) {
				waitTime(5000);
				TVclick(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content");
				TVclick(Zee5TvWelcomePage.objLiveNewsContentinNewsPage, "News page Tray content");
				waitTime(4000);
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		for (int i = 0; i <= 5; i++) {
			TVRemoteEvent(23);
			waitTime(2000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objGotolivebutton, "Live button in player")) {
				logger.info("User is able play Free news content");
				extent.extentLoggerPass("EPG", "User is able play Free news content");
				break;
			}
		}
		getDriver().navigate().back();
		for (int k = 0; k <= 15; k++) {
			TVRemoteEvent(19);
			waitTime(3000);
		}
		HeaderChildNode("Content playback validation in videos page");
		TVTabSelect("Videos");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objVideoPageTrayContent, "Videos page Tray content")) {
				TVclick(Zee5TvWelcomePage.objVideoPageTrayContent, "Videos page Tray content");
				waitTime(8000);
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objVideoPageTrayContent, "Videos page Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(8000);
		TVRemoteEvent(23);
		waitTime(2000);
		TVRemoteEvent(23);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User can play free content in videos page for club user");
			extent.extentLoggerPass("play", "User can play free content in videos page for club user");
		} else {
			logger.info("playback did not initiate");
			extent.extentLogger("play", "playback did not initiate");
		}
		getDriver().navigate().back();
		waitTime(5000);
		for (int k = 0; k <= 15; k++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
		getDriver().navigate().back();
		waitTime(3000);
		HeaderChildNode("Content playback validation through show content detail page");
		TVTabSelect("Shows");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 20; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objshowpageTrayContentClub, "Show page Tray content")) {
				TVclick(Zee5TvWelcomePage.objshowpageTrayContentClub, "Show page Tray content");
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objshowpageTrayContentClub, "Show page Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(7000);
		TVRemoteEvent(23);
		waitTime(2000);
		TVRemoteEvent(23);
		waitTime(4000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play shows from show content detail page");
			extent.extentLoggerPass("play", "User is able to play shows from show content detail page");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		for (int k = 0; k <= 15; k++) {
			TVRemoteEvent(19);
			waitTime(2000);
		}
		getDriver().navigate().back();
		waitTime(3000);
		HeaderChildNode("Content playback validation through movie content detail page");
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objSearchSpaceBar, "Search page")) {
			logger.info("User is navigated to search page after clicking on search button");
			extent.extentLoggerPass("Search", "User is navigated to search page after clicking on search button");
		} else {
			logger.info("User is not navigated to serach page");
			extent.extentLoggerFail("Navigation", "User is not navigated to serach page");
		}
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		HeaderChildNode("Search Movie content and its playback functionality");
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content);
		extent.extentLogger("Search", "Entered Searched Data : " + content);

		int k;
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele.size(); i++) {

			title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}

		waitTime(8000);
		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		if (title.equals(title)) {
			logger.info("user is navigated to respective content detail page");
			extent.extentLoggerPass("user", "user is navigated to respective content detail page");
		}
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(15000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			logger.info("clicked on skip intro");
			extent.extentLoggerPass("Intro", "clicked on skip intro");
		} else {
			logger.info("Skip intro is not displayed");
			extent.extentLogger("Intro", "Skip intro is not displayed");
		}
		waitTime(12000);
		TVRemoteEvent(23);
		waitTime(2000);
		TVRemoteEvent(23);
		waitTime(4000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play free movie content from movie content detail page for club user");
			extent.extentLoggerPass("play",
					"User is able to play free movie content from movie content detail page for club user");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
//		HeaderChildNode("Content playback validation through upnext rail");
//		TVRemoteEvent(23);
//		waitTime(3000);
//		TVRemoteEvent(20);
//		waitTime(2000);
//		TVRemoteEvent(23);
//		waitTime(3000);
//		TVRemoteEvent(20);
//		waitTime(3000);
//		TVRemoteEvent(22);
//		waitTime(3000);
//		TVRemoteEvent(23);
//
//		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objUpnextrail, "Up Next rail")) {
//			logger.info("User can click on upnext button and up next rail is displayed");
//			extent.extentLoggerPass("UpNext", "User can click on upnext button and up next rail is displayed");
//		} else {
//			logger.info("Upnext tab functionality failed");
//			extent.extentLoggerFail("UpNext", "Upnext tab functionality failed");
//		}
//		TVRemoteEvent(23);
//		waitTime(10000);
//		TVRemoteEvent(23);
//		waitTime(3000);
//		TVRemoteEvent(23);
//		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
//			logger.info("User is able to play content from upnext rail for club user");
//			extent.extentLoggerPass("play", "User is able to play content from upnext rail for club user");
//		} else {
//			logger.info("playback did not initiate");
//			extent.extentLoggerFail("play", "playback did not initiate");
//		}
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		waitTime(3000);
		HeaderChildNode("Get premium popup validation and subscription page navigation for premium content");
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(10000);
		String searchdataContent[] = { "y", "o", "u", "n", "g", };
		String searchdataContent2[] = { "d", "r", "e", "a", "m", "s" };
		type(searchdataContent);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdataContent2);
		String content2 = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content2);
		extent.extentLogger("Search", "Entered Searched Data : " + content2);
		waitTime(5000);
		int l;
		List<WebElement> ele2 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele2.size(); i++) {

			title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Shows"),
					"Searched Shows"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Shows"), "serached Shows");
				break;
			} else {
				System.out.println("No match");
			}

		}
		waitTime(8000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(15000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objClubPremiumPopup, "Subscription popup")) {
			logger.info(
					"Subscription popup is displayed when club user plays any premium content not included in club pack");
			extent.extentLoggerPass("Popup",
					"Subscription popup is displayed when club user plays any premium content not included in club pack");
			TVRemoteEvent(22);
			waitTime(2000);
			TVclick(Zee5TvHomePage.objClubPremiumPopup, "Subscription button");
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscriptionPlanPage, "My Plans page")) {
				logger.info("User is navigated to Subscription page post tapping on get premim button");
				extent.extentLoggerPass("plan",
						"User is navigated to Subscription page post tapping on get premim button");
			} else {
				logger.info("Navigation failed");
				extent.extentLogger("Plan", "Navigation failed");
			}
			if (verifyIsElementDisplayed(Zee5TvHomePage.objAllaccessButton, "All access button")) {
				logger.info("All access premium button is displayed for club user");
				extent.extentLoggerPass("Button", "All access premium button is displayed for club user");
			} else {
				logger.info("Navigation failed");
				extent.extentLogger("Plan", "Navigation failed");
			}
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
		} else {
			logger.info("Premium popup functionality failed");
			extent.extentLogger("Popup", "Premium popup functionality failed");
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
		}
		waitTime(5000);
		HeaderChildNode("Content playback validation through continue watching tray");
		TVTabSelect("Home");
		waitTime(2000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVclick(Zee5TvWelcomePage.objContinuewatchingTrayImage, "Continue watching content");
		waitTime(5000);
		TVRemoteEvent(23);
		waitTime(10000);
		TVRemoteEvent(23);
		waitTime(3000);
		TVRemoteEvent(23);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play content from continue watching rail for club user");
			extent.extentLoggerPass("play", "User is able to play content from continue watching rail for club user");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);

		if (verifyIsElementDisplayed(Zee5TvHomePage.objMorecontentPopup, "More content popup")) {
			logger.info(
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			extent.extentLogger("Popup",
					"Would you like to add selected display language as content language popup is diaplyed when user clicks on different display language");
			getDriver().navigate().back();
		} else {
			logger.info("Popup is not displayed");
			extent.extentLogger("Popup", "Popup is not displayed");
		}
		waitTime(3000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVTabSelect("Home");
		waitTime(2000);
		TVTabSelect("Shows");
		for (int i = 0; i <= 15; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvHomePage.objWatchNextTray, "Watch next Tray")) {
				logger.info("Watch next tray is displayed in show page for club user");
				extent.extentLoggerPass("Tray", "Watch next tray is displayed in show page for club user");
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		getDriver().navigate().back();
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(10000);
		getDriver().closeApp();
		waitTime(3000);
		getDriver().launchApp();
		waitTime(7000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);
		TVRemoteEvent(19);
		waitTime(2000);

	}

	@SuppressWarnings("static-access")
	public void suscriptionCallInitiatedInsprint() throws Exception {
		HeaderChildNode("Subscription Call Initiated Event id- 6138 & 6039");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		TVTabSelect("Home");

		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		HeaderChildNode("Subscription Page Viewed from setting screen");
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 3; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		if (userType.equals("Guest")) {
			TVclick(Zee5TvWelcomePage.objALLPlanOption, "All plan option");
			waitTime(3000);
			TVclick(Zee5TvWelcomePage.objALLPlanOption, "All plan option");
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			TVclick(Zee5TvWelcomePage.objMyPlanOption, "My Plan option");
			waitTime(3000);
			TVclick(Zee5TvWelcomePage.objMyPlanOption, "My Plan option");
		}
		waitTime(7000);

		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		waitTime(3000);
		if (userType.equals("NonSubscribedUser")) {
			TVRemoteEvent(23);
			waitTime(3000);
			TVRemoteEvent(20);
		}
		TVRemoteEvent(23);
		waitTime(3000);

		waitTime(5000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium content as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium content as guest user");
				TVRemoteEvent(22);
				waitTime(2000);
				TVRemoteEvent(22);
				waitTime(2000);
				TVRemoteEvent(23);
				waitTime(5000);

				mixpanel.FEProp.setProperty("Source", "Login and Authentication screen");
				mixpanel.FEProp.setProperty("Page Name", "Login and Authentication screen");
				mixpanel.ValidateParameter("", "TV Authentication Screen Display");
				logger.info(
						"Guest user is navigated to login screen post tapping on login butoon in login popup for premium content");

				getDriver().navigate().back();
				waitTime(2000);
				getDriver().navigate().back();
				waitTime(2000);
				getDriver().navigate().back();
				waitTime(2000);
			} else {
				logger.info("Functioanlity failed");
				extent.extentLoggerFail("Popup", "Popup Functioanlity failed");
			}
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			mixpanel.FEProp.setProperty("Source", "Subscription Page");
			mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
			mixpanel.FEProp.setProperty("Payment method", "");
			mixpanel.FEProp.setProperty("Payment gateway", "");
			mixpanel.ValidateParameter("", "Subscription call initiated");
			if (userType.equals("NonSubscribedUser")) {
				getDriver().navigate().back();
				waitTime(2000);
			}

			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			getDriver().navigate().back();
			waitTime(2000);
			TVTabSelect("Home");
		}
	}

	/**
	 *  Function to perform Top shows validation
	  */
	@SuppressWarnings("static-access")
	public void topcatagory() throws Exception {
		HeaderChildNode("Top catagory id - 6712");
		String language = getLanguage(userType);
		Response resp = ResponseInstance.getResponseForPagesTv("Shows", language, 1, userType);
		for (int i = 1; i <= 20; i++) {
			asset_SubType = resp.jsonPath().getString("buckets[" + i + "].items.[" + i + "].asset_subtype");
			if (asset_SubType.equals("episode")) {
				assetType = resp.jsonPath()
						.getString("buckets[" + i + "].items.[" + i + "].tvshow_details.asset_subtype");
				viewAllTrayname = resp.jsonPath().getString("buckets[" + i + "].title");
				String title = resp.jsonPath().getString("buckets[" + i + "].items.[" + i + "].title");
				break;
			}
		}
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewAllTrayApi, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objViewAllTrayApi, "Tray content");
				waitTime(7000);
				break;
			} else {
				TVRemoteEvent(20);
			}
		}
		mixpanel.FEProp.setProperty("Source", "Player");
		mixpanel.FEProp.setProperty("Page Name", "Player");
		mixpanel.FEProp.setProperty("Content Specification", asset_SubType);
		mixpanel.FEProp.setProperty("Top Category", assetType);
		mixpanel.ValidateParameter("", "Video View");
	}
	
	/**
	 *  Function to perform the popup and play back validation
	  */
	public void popupAndPlayback() throws Exception {
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
				logger.info("Login popup is displayed when user play premium content as guest user");
				extent.extentLoggerPass("Popup",
						"Login popup is displayed when user play premium content as guest user");
			} else {
				logger.info("User is navigated to consumption page");
				extent.extentLoggerPass("Popup", "User is navigated to consumption page");
			}

		}
		if (userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Subscribe now popup")) {
				logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
				extent.extentLoggerPass("Popup",
						"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
			} else {
				logger.info("User is navigated to consumption page");
				extent.extentLoggerPass("Popup", "User is navigated to consumption page");
			}
		}
		if (userType.equals("SubscribedUser")) {
			// LoadingInProgress();
			waitTime(7000);
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVRemoteEvent(23);
				logger.info("clicked on skip intro");
				extent.extentLoggerPass("Intro", "clicked on skip intro");
			} else {
				logger.info("Skip intro is not displayed");
				extent.extentLoggerPass("Intro", "Skip intro is not displayed");
			}
			waitTime(5000);
			TVRemoteEvent(23);
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Player container")) {
				logger.info("User is able to play premium content for premium user");
				extent.extentLoggerPass("play", "User is able to play premium content for premium user");
			} else {
				logger.info("playback did not initiate");
				extent.extentLoggerFail("play", "playback did not initiate");
			}
		}
	}
	/**
	 *  Function to perform Plancard validation
	  */

	public void plancard() throws Exception {
		HeaderChildNode("New Plan card validation ID - CON-6972");
		if (userType.equals("Guest")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect("Home");
			waitTime(2000);
			for (int i = 0; i <= 10; i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}
			TVTabSelect("Settings");
			TVRemoteEvent(20);
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objALLPlanOption, "My Plan option");
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objALLPlanOption, "My Plan option");
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objAllplanButton, "All plan button")) {
				logger.info("All plan button is displayed in subscription page");
				extent.extentLoggerPass("Plan", "All plan button is displayed in subscription page");
			} else {
				logger.info("All plan button is not displayed in subscription page");
				extent.extentLoggerPass("Plan", "All plan button is not displayed in subscription page");
			}
		}
	}



	
	// Function to check String for only Alphabets
	public static boolean isStringOnlyAlphabet(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z]*$")));
	}
	/**
	 *  Function to perform Setting screen options validation
	  */
	@SuppressWarnings("rawtypes")
	public void settingScreen() throws Exception {
		HeaderChildNode("Settings screen options validation CON-7299/CON-7661/CON-7662");
		if (userType.equals("Guest")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
		}
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objsettingsscreenRow, "Seeting option rows")) {
			logger.info("Settings icon is displayed in 2 rows");
			extent.extentLoggerPass("Rows", "Settings icon is displayed in 2 rows");
		} else {
			logger.info("Settings icon is not displayed in 2 rows");
			extent.extentLoggerFail("Rows", "Settings icon is not displayed in 2 rows");
		}
		List Options = getDriver()
				.findElements(By.xpath("//*[@class='android.widget.RelativeLayout']//child::*[@id='icon_image']"));
		logger.info("For " + userType + "user " + Options.size() + " Tabs are displayed in Settings page");
		extent.extentLoggerPass("Tab",
				"For " + userType + "user " + Options.size() + " Tabs are displayed in Settings page");
		waitTime(3000);
	}

	/**
	 *  Function to perform Skip Ad options validation
	  */

	public void skipAD() throws Exception {
		HeaderChildNode("Skip Ad validation - CON-7772");
		if (userType.equals("Guest")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
		}
		TVTabSelect("Home");
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);

		String content = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content);
		extent.extentLogger("Search", "Entered Searched Data : " + content);

		int k;
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele.size(); i++) {
			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info("Serach result content title : " + title);
			extent.extentLogger("Title", "Serach result content title : " + title);

			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}

		waitTime(8000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(12000);

		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objAd, "Ad")) {
				logger.info("Ad is present when user click on any content and verified");
				extent.extentLoggerPass("Ad", "Ad is present when user click on any content and verified");
				if (verifyIsElementDisplayed(Zee5TvHomePage.objplayerskipad, "Skip Ad")) {
					logger.info("Skip Ad is displayed");
					extent.extentLoggerPass("Ad", "Skip Ad is displayed");
				} else {
					logger.info("Skip Ad is not displayed");
					extent.extentLoggerFail("Ad", "Skip Ad is not displayed");
					AdVerify();
				}

			} else {
				logger.info("Ad is not present");
				extent.extentLoggerFail("Ad", "Ad is not present");
			}

		}
		if (userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TvPlayerPage.objAd, "Ad")) {
				logger.info("Ad is present when user click on any content for subscribe content");
				extent.extentLoggerFail("Ad", "Ad is present when user click on any content for subscribe content");
			} else {
				logger.info("Ad is not present for subscribe user");
				extent.extentLoggerPass("Ad", "Ad is not present for subscribe user");
			}
		}

		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();
		waitTime(5000);
		getDriver().launchApp();
		waitTime(3000);
	}

	
	
	public ArrayList<String> Memory_UsagePerformance() throws IOException {
		System.out.println("Memory Usage of Native App");

		String getNativeMemory = "";
		String getTotalMemory = "";
		String adbCommand1 = "adb " + IP + " shell \"dumpsys meminfo com.graymatrix.did | grep Native\"";
		String adbCommand2 = "adb " + IP + " shell \"dumpsys meminfo com.graymatrix.did | grep TOTAL\"";

		Process process1 = Runtime.getRuntime().exec(adbCommand1);
		BufferedReader nativeResult = new BufferedReader(new InputStreamReader(process1.getInputStream()));
		Process process2 = Runtime.getRuntime().exec(adbCommand2);
		BufferedReader totalResult = new BufferedReader(new InputStreamReader(process2.getInputStream()));

		getNativeMemory = nativeResult.readLine().trim();
		getTotalMemory = totalResult.readLine().trim();

		ArrayList<String> getNativeValue = new ArrayList<String>();
		String[] splitData = getNativeMemory.split(" ");
		for (int i = 0; i < splitData.length; i++) {
			if (!splitData[i].isEmpty()) {
				getNativeValue.add(splitData[i]);
			}
		}

		ArrayList<String> getTotalValue = new ArrayList<String>();
		String[] splitData2 = getTotalMemory.split(" ");
		for (int i = 0; i < splitData2.length; i++) {
			if (!splitData2[i].isEmpty()) {
				getTotalValue.add(splitData2[i]);
			}
		}

		int mbNativeHeap = (Integer.parseInt(getNativeValue.get(2)) / 1024);
		logger.info("App Memory Info - Native Heap : " + mbNativeHeap + " MB");

		int mbTotal = (Integer.parseInt(getTotalValue.get(1)) / 1024);
		logger.info("App Memory Info - TOTAL : " + mbTotal + " MB");

		ArrayList<String> Memory_UsagePerformanceV2 = new ArrayList<String>();
		Memory_UsagePerformanceV2.add(convertToString(mbNativeHeap));
		Memory_UsagePerformanceV2.add(convertToString(mbTotal));

		return Memory_UsagePerformanceV2;
	}

	public String BatteryStats_Performance() throws Exception {
		System.out.println("\nBattery Stats Information");

		String getBatteryInfo = "";
//		String adbCommand="adb shell dumpsys batterystats --charged com.graymatrix.did | grep Computed";
		String adbCommand = "adb " + IP + " shell pm dump com.graymatrix.did | grep Computed";
		String strDrain = "Not found";
		try {
			Process process = Runtime.getRuntime().exec(adbCommand);
			BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));

			getBatteryInfo = result.readLine().trim();
			System.out.println(getBatteryInfo);

			if (getBatteryInfo.contains("drain")) {
				String[] listOfData = getBatteryInfo.split(",");
				for (int i = 0; i < listOfData.length; i++) {
					if (listOfData[i].contains("Computed drain")) {
						strDrain = listOfData[i];
					}
				}
				logger.info("\nApp Battery Info - " + strDrain);
			}

		} catch (Exception e) {
			System.out.println(strDrain);
		}
		return strDrain;
	}

	public String CPU_UsagePerformance() throws IOException {
		System.out.println("\nCPU Usage of App");

		String getCpuStats = "";
		String adbCommand = "adb " + IP + " shell \"dumpsys cpuinfo | grep com.graymatrix.did\"";
		Process process = Runtime.getRuntime().exec(adbCommand);
		BufferedReader adbResult = new BufferedReader(new InputStreamReader(process.getInputStream()));

		getCpuStats = adbResult.readLine().trim();

		String[] getCPUStatus = getCpuStats.split(" ");
		logger.info("App CPU Usage status : " + getCPUStatus[0]);

		String CPUInfo = getCPUStatus[0].replace("%", "").trim();
		return CPUInfo;
	}

	public ArrayList<String> GPU_UsagePerformance() throws Exception {
		System.out.println("\nGPU Usage of App");

		String getGPUInfo = "";
		String nGPUFramesRendered = "";
		String GPUConsumed = null;
		String adbCommand = "adb " + IP + " shell \"dumpsys gfxinfo com.graymatrix.did | grep MB\"";
		String adbCommand2 = "adb " + IP + " shell \"dumpsys gfxinfo com.graymatrix.did | grep rendered\"";

		Process process = Runtime.getRuntime().exec(adbCommand);
		BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream()));

		Process process2 = Runtime.getRuntime().exec(adbCommand2);
		BufferedReader result2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));

		getGPUInfo = result.readLine().trim();
		// System.out.println(getGPUInfo);
//		String[] splitData = getGPUInfo.split(",");
//		String GPUConsumed = splitData[1].trim();

		if (getGPUInfo.contains(",")) {
			String[] splitData = getGPUInfo.split(",");
			if (splitData[1].contains("(")) {
				String[] splitData2 = splitData[1].split("MB");
				GPUConsumed = splitData2[0].trim() + " MB";
			} else {
				GPUConsumed = splitData[1].trim();
			}
		} else if (getGPUInfo.contains(":")) {
			String[] splitData = getGPUInfo.replace("Texture:", "").split("MB");
			GPUConsumed = splitData[0].trim() + " MB";
		}
		nGPUFramesRendered = result2.readLine().trim();

		logger.info("\nTotal GPU Memory Usage of Current session : " + GPUConsumed);
		logger.info("\nGPU Rendering Info of Current session - " + nGPUFramesRendered);

		ArrayList<String> GPU_UsagePerformanceV2 = new ArrayList<String>();
		GPU_UsagePerformanceV2.add(GPUConsumed);
		GPU_UsagePerformanceV2.add(nGPUFramesRendered);

		return GPU_UsagePerformanceV2;
	}

	public double getApp_NetworkTrafficUsage(String pPackageName) throws Exception {

		double flowAction = 0;
		try {
			String pidCommand = "adb " + IP + " shell pidof " + pPackageName;
			Process process = Runtime.getRuntime().exec(pidCommand);
			BufferedReader pidResult = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String PID = pidResult.readLine().trim();
			// System.out.println("PID : "+PID);

			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("adb " + IP + " shell cat /proc/" + PID + "/net/dev");
			try {
				if (proc.waitFor() != 0) {
					System.err.println("exit value = " + proc.exitValue());
				}
				BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				StringBuffer stringBuffer = new StringBuffer();
				String line = null;
				while ((line = in.readLine()) != null) {
					stringBuffer.append(line + " ");

				}
				String str1 = stringBuffer.toString();
				String str2 = str1.substring(str1.indexOf("wlan0:"), str1.indexOf("wlan0:") + 100);

				// System.out.println("sent first sentence" + str2);
				// The space is divided into a string array to take the second and tenth
				// numbers, which are the sending traffic and the receiving traffic.
				String[] toks = str2.split(" +");
				String str4 = toks[1];
				String str6 = toks[9];
				int b = Integer.parseInt(str4);
				int a = Integer.parseInt(str6);

				double sendFlow = a / 1024;
				double revFlow = b / 1024;
				flowAction = sendFlow + revFlow;
				logger.info("\nThe current App traffic usage is : " + (int) flowAction / 1024 + "Mbps");
				// extent.extentLogger("Traffic Usage","<b>The Current App traffic usage is :
				// </b> " + (int)flowAction/1024 + "Mbps");

			} catch (InterruptedException e) {
				System.err.println(e);
			} finally {
				try {
					proc.destroy();
				} catch (Exception e2) {
				}
			}
		} catch (Exception StringIndexOutOfBoundsException) {
			System.out.println("Please check if the device is connected | App is closed");
			extent.extentLoggerWarning("Traffic Usage",
					"<b>Please check if the device is connected | App is closed </b>");
		}

		if (flowAction != 0) {
			return (flowAction / 1024);
		} else {
			return flowAction;
		}
	}

	public void Performance_LoginFunctionality(String userType) throws Exception {
		extent.HeaderChildNode("Login Functionality Performance");
		System.out.println("\nLogin Functionality Performance");
		waitTime(3000);
		String appPackageName = "com.graymatrix.did";

//		Instant startTime = Instant.now();
		logger.info("Instant Start time : " + DriverInstance.startTime);
		extent.extentLoggerPass("Time", "Instant Start time : " + DriverInstance.startTime);

		Instant endTime = Instant.now();
		logger.info("Instant End time : " + endTime);

		Duration timeElapsed = Duration.between(DriverInstance.startTime, endTime);

		long time = timeElapsed.getSeconds();
		long time2 = 5;
		System.out.println(time);
		if (time > time2) {
			launch = false;
			logger.info("Application did not launch in expected time");
			extent.extentLoggerFail("Launch", "Application did not launch in expected time");
		} else {
			launch = true;
			logger.info("Application launched in expected time");
			extent.extentLoggerPass("Launch", "Application launched in expected time");
		}

		if (userType.equals("Guest")) {
			verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			verifyIsElementDisplayed(Zee5TvHomePage.objSelectTab("Home"), "Home Tab");
		}
		logger.info("Time taken to login with registered user (sec): " + timeElapsed.toMillis() / 1000);
		extent.extentLogger("Timer",
				"<b>Time taken to login with registered user (sec)</b>: " + timeElapsed.toMillis() / 1000);

		softAssertion.assertEquals(launch, true);
		softAssertion.assertAll();

	}
	/**
	 *  Function to perform App launch functionality
	  */

	public void appLaunch() throws Exception {
		extent.HeaderChildNode("App Launch Functionality Performance");
		waitTime(3000);
		String appPackageName = "com.graymatrix.did";

		// Threshold Values declaration
		int threshold_TimeTaken = 16;
		int threshold_NativeMemory = 30;
		int threshold_TotalMemory = 200;
		int threshold_CPU = 200;
		int threshold_GPUMem = 7;
		int threshold_GPURendered = 1500;
		int threshold_Network = 8;

		logger.info("Instant Start time : " + DriverInstance.startTime);
		extent.extentLoggerPass("Time", "Instant Start time : " + DriverInstance.startTime);

		Instant endTime = Instant.now();
		logger.info("Instant End time : " + endTime);

		Duration timeElapsed = Duration.between(DriverInstance.startTime, endTime);

		long time = timeElapsed.getSeconds();
		long time2 = 5;
		System.out.println(time);
		if (time > time2) {
			launch = false;
			logger.info("Application did not launch in expected time");
			extent.extentLoggerFail("Launch", "Application did not launch in expected time");
		} else {
			launch = true;
			logger.info("Application launched in expected time");
			extent.extentLoggerPass("Launch", "Application launched in expected time");
		}

		verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");

		ArrayList<String> getMmoryInfo = Memory_UsagePerformance();
		int nativeMemory = Integer.parseInt(getMmoryInfo.get(0).trim());
		int totalMemory = Integer.parseInt(getMmoryInfo.get(1).trim());

		// #### App Performance CPU Usage Info
		String getCPUInfo = CPU_UsagePerformance();
		int nCpuUSage = Integer.parseInt(getCPUInfo);

		// #### App Performance GPU Usage Info
		ArrayList<String> getGPUInfo = GPU_UsagePerformance();
		float nGPUMemory = Float.parseFloat(getGPUInfo.get(0).replace(" MB", "").trim());
		int nGPURendered = Integer.parseInt(getGPUInfo.get(1).replace("Total frames rendered: ", "").trim());

		// #### App Performance Network Traffic Usage Info
		double nNetTraffic = getApp_NetworkTrafficUsage("com.graymatrix.did");

		if (timeElapsed.getSeconds() < threshold_TimeTaken) {
			logger.info("Time taken to launch the App (Sec): " + timeElapsed.getSeconds());
			extent.extentLoggerPass("Timer", "<b>Time taken to launch the App (Sec)</b>: " + timeElapsed.getSeconds());
		} else {
			logger.info("Time taken to launch the App (Sec): " + timeElapsed.getSeconds());
			extent.extentLoggerFail("Timer", "<b>Time taken to launch the App (Sec)</b>: " + timeElapsed.getSeconds());
		}

		if (nativeMemory < threshold_NativeMemory) {
			logger.info("App Memory Info - Native Heap : " + nativeMemory + " MB");
			extent.extentLoggerPass("Memory Info", "<b>App Memory Info - Native Heap :</b> " + nativeMemory + " MB");
		} else {
			logger.error("App Memory Info - Native Heap : " + nativeMemory + " MB");
			extent.extentLoggerFail("Memory Info", "<b>App Memory Info - Native Heap :</b> " + nativeMemory + " MB");
		}

		if (totalMemory < threshold_TotalMemory) {
			logger.info("App Memory Info - Total : " + totalMemory + " MB");
			extent.extentLoggerPass("Memory Info", "<b>App Memory Info - Total :</b> " + totalMemory + " MB");
		} else {
			logger.error("App Memory Info - Total : " + totalMemory + " MB");
			extent.extentLoggerFail("Memory Info", "<b>App Memory Info - Total :</b> " + totalMemory + " MB");
		}

		if (nCpuUSage < threshold_CPU) {
			logger.info("App CPU  Usage status : " + nCpuUSage + "%");
			extent.extentLoggerPass("CPU Info", "<b>App CPU Usage status : </b> " + nCpuUSage + "%");
		} else {
			logger.error("App Memory Info - Total : " + nCpuUSage + "%");
			extent.extentLoggerFail("CPU Info", "<b>App CPU Usage status : </b> " + nCpuUSage + "%");
		}

		if (nGPUMemory < threshold_GPUMem) {
			logger.info("\nTotal GPU Memory Usage of Current session : " + nGPUMemory + " MB");
			extent.extentLoggerPass("GPU Info",
					"<b>Total GPU Memory Usage of Current session :</b> " + nGPUMemory + " MB");
		} else {
			logger.error("\nTotal GPU Memory Usage of Current session exceeded : " + nGPUMemory + " MB");
			extent.extentLoggerFail("GPU Info",
					"<b>Total GPU Memory Usage of Current session exceeded:</b> " + nGPUMemory + " MB");
		}

		if (nGPURendered < threshold_GPURendered) {
			logger.info("\nGPU Current session - Total frames rendered: " + nGPURendered);
			extent.extentLoggerPass("GPU Info", "<b>GPU Current session - Total frames rendered: </b> " + nGPURendered);
		} else {
			logger.error("\nGPU Current session - Total frames rendered: " + nGPURendered);
			extent.extentLoggerFail("GPU Info", "<b>GPU Current session - Total frames rendered: </b> " + nGPURendered);
		}

		if (nNetTraffic < threshold_Network) {
			logger.info("\nThe current App traffic usage is : " + (int) nNetTraffic + " Mbps");
			extent.extentLoggerPass("Traffic Usage",
					"<b>The Current App traffic usage is : </b> " + (int) nNetTraffic + " Mbps");
		} else {
			logger.error("\nThe current App traffic usage is : " + (int) nNetTraffic + " Mbps");
			extent.extentLoggerFail("Traffic Usage",
					"<b>The Current App traffic usage is : </b> " + (int) nNetTraffic + " Mbps");
		}
		performaceDetails.add("App Launch " + "," + timeElapsed.getSeconds() + "," + nativeMemory + "MB," + totalMemory
				+ "MB," + nCpuUSage + "%," + nGPUMemory + "MB," + nGPURendered + "," + nNetTraffic + "MB");
		softAssertion.assertEquals(launch, true);
		softAssertion.assertAll();
	}

	/**
	 *  Function to perform login functionality
	  */
	public void loginPerformance() throws Exception {
		extent.HeaderChildNode("Login Functionality Performance");
		System.out.println("\n>>> Login Functionality Performance");

		String appPackageName = "com.graymatrix.did";

		// Threshold Values declaration
		int threshold_TimeTaken = 100;
		int threshold_NativeMemory = 70;
		int threshold_TotalMemory = 140;
		int threshold_CPU = 75;
		int threshold_GPUMem = 12;
		int threshold_GPURendered = 2300;
		int threshold_Network = 27;

		Instant startTime = Instant.now();
		logger.info("Instant Start time : " + startTime);

		login("SubscribedUser");
		// AppPerformanceTestInfo(appPackageName);

		// #### App Performance MEMORY Usage Info
		ArrayList<String> getMmoryInfo = Memory_UsagePerformance();
		int nativeMemory = Integer.parseInt(getMmoryInfo.get(0).trim());
		int totalMemory = Integer.parseInt(getMmoryInfo.get(1).trim());

		// #### App Performance CPU Usage Info
		String getCPUInfo = CPU_UsagePerformance();
		int nCpuUSage = Integer.parseInt(getCPUInfo);

		// #### App Performance GPU Usage Info
		ArrayList<String> getGPUInfo = GPU_UsagePerformance();
//	float nGPUMemory = Float.parseFloat(getGPUInfo.get(0).replace(" MB", "").trim());
		String[] parseString = getGPUInfo.get(0).split("MB");
		float nGPUMemory = Float.parseFloat(parseString[0].trim());
		int nGPURendered = Integer.parseInt(getGPUInfo.get(1).replace("Total frames rendered: ", "").trim());

		// #### App Performance Network Traffic Usage Info
		double nNetTraffic = getApp_NetworkTrafficUsage(appPackageName);

		// #### App Performance Battery Info
//		String batteryInfo = BatteryStats_Performance();

		verifyIsElementDisplayed(Zee5TvHomePage.objSelectTab("Home"), "Home Tab");

		Instant endTime = Instant.now();
		logger.info("Instant End time : " + endTime);

		Duration timeElapsed = Duration.between(startTime, endTime);
		logger.info("Time taken to login with registered user (sec): " + timeElapsed.getSeconds());

		if (timeElapsed.getSeconds() < threshold_TimeTaken) {
			logger.info("Time taken to login with registered user (Sec): " + timeElapsed.getSeconds());
			extent.extentLoggerPass("Timer",
					"<b>Time taken to login with registered user (Sec)</b>: " + timeElapsed.getSeconds());
			launch = true;
		} else {
			logger.info("Taken too long to login with registered user (Sec): " + timeElapsed.getSeconds());
			extent.extentLoggerFail("Timer",
					"<b>Taken too long to login with registered user (Sec)</b>: " + timeElapsed.getSeconds());
			launch = false;
		}

		if (nativeMemory < threshold_NativeMemory) {
			logger.info("App Memory Info - Native Heap : " + nativeMemory + " MB");
			extent.extentLoggerPass("Memory Info", "<b>App Memory Info - Native Heap :</b> " + nativeMemory + " MB");
		} else {
			logger.error("App Memory Info - Native Heap : " + nativeMemory + " MB");
			extent.extentLoggerFail("Memory Info", "<b>App Memory Info - Native Heap :</b> " + nativeMemory + " MB");
		}

		if (totalMemory < threshold_TotalMemory) {
			logger.info("App Memory Info - Total : " + totalMemory + " MB");
			extent.extentLoggerPass("Memory Info", "<b>App Memory Info - Total :</b> " + totalMemory + " MB");
		} else {
			logger.error("App Memory Info - Total : " + totalMemory + " MB");
			extent.extentLoggerFail("Memory Info", "<b>App Memory Info - Total :</b> " + totalMemory + " MB");
		}

		if (nCpuUSage < threshold_CPU) {
			logger.info("App CPU  Usage status : " + nCpuUSage + "%");
			extent.extentLoggerPass("CPU Info", "<b>App CPU Usage status : </b> " + nCpuUSage + "%");
		} else {
			logger.error("App Memory Info - Total : " + nCpuUSage + "%");
			extent.extentLoggerFail("CPU Info", "<b>App CPU Usage status : </b> " + nCpuUSage + "%");
		}

		if (nGPUMemory < threshold_GPUMem) {
			logger.info("\nTotal GPU Memory Usage of Current session : " + nGPUMemory + " MB");
			extent.extentLoggerPass("GPU Info",
					"<b>Total GPU Memory Usage of Current session :</b> " + nGPUMemory + " MB");
		} else {
			logger.error("\nTotal GPU Memory Usage of Current session exceeded : " + nGPUMemory + " MB");
			extent.extentLoggerFail("GPU Info",
					"<b>Total GPU Memory Usage of Current session exceeded:</b> " + nGPUMemory + " MB");
		}

		if (nGPURendered < threshold_GPURendered) {
			logger.info("\nGPU Current session - Total frames rendered: " + nGPURendered);
			extent.extentLoggerPass("GPU Info", "<b>GPU Current session - Total frames rendered: </b> " + nGPURendered);
		} else {
			logger.error("\nGPU Current session - Total frames rendered: " + nGPURendered);
			extent.extentLoggerFail("GPU Info", "<b>GPU Current session - Total frames rendered: </b> " + nGPURendered);
		}

		if (nNetTraffic < threshold_Network) {
			logger.info("\nThe current App traffic usage is : " + (int) nNetTraffic + " Mbps");
			extent.extentLoggerPass("Traffic Usage",
					"<b>The Current App traffic usage is : </b> " + (int) nNetTraffic + " Mbps");
		} else {
			logger.error("\nThe current App traffic usage is : " + (int) nNetTraffic + " Mbps");
			extent.extentLoggerFail("Traffic Usage",
					"<b>The Current App traffic usage is : </b> " + (int) nNetTraffic + " Mbps");
		}

//		if (batteryInfo.contains("drain")) {
//			logger.info("\nApp Battery Info - " + batteryInfo);
//			extent.extentLoggerPass("Timer", "<b>App Battery Info - </b>" + batteryInfo);
//		} else {
//			logger.error("\nApp Battery Info - " + batteryInfo);
//			extent.extentLoggerFail("Timer", "<b>App Battery Info - </b>" + batteryInfo);
//		}
		performaceDetails
				.add("Login Functionality " + "," + timeElapsed.getSeconds() + "," + nativeMemory + "MB," + totalMemory
						+ "MB," + nCpuUSage + "%," + nGPUMemory + "MB," + nGPURendered + "," + nNetTraffic + "MB");
		softAssertion.assertEquals(launch, true);
		softAssertion.assertAll();

	}
	/**
	 *  Function to perform Screen navigation validation
	  */

	public void SelectTopNavigationTab_Timer(String pTabname) throws Exception {
		extent.HeaderChildNode("Screen Navigation Performance");
		System.out.println("\n>>> Selecting " + pTabname + " from Top navigation tabs");

		String appPackageName = "com.graymatrix.did";

		// Threshold Values declaration
		int threshold_TimeTaken = 16;
		int threshold_NativeMemory = 80;
		int threshold_TotalMemory = 250;
		int threshold_CPU = 75;
		int threshold_GPUMem = 18;
		int threshold_GPURendered = 2300;
		int threshold_Network = 50;

		// Initiated Variable declaration
		int nativeMemory = 0, totalMemory = 0, nCpuUSage = 0, nGPURendered = 0;
		float nGPUMemory = 0;
		double nNetTraffic = 0;
		String batteryInfo = null;

		TVclick(Zee5TvHomePage.objSelectTab("Home"), "Home tab");

		Instant startTime = Instant.now();
		logger.info("Start time: " + startTime);

		TVclick(Zee5TvHomePage.objSelectTab("Shows"), "Shows tab");

		Instant endTime = Instant.now();
		logger.info("End time: " + endTime);
		// AppPerformanceTestInfo(appPackageName);

		// #### App Performance Memory Usage Info
		ArrayList<String> getMmoryInfo = Memory_UsagePerformance();
		nativeMemory = Integer.parseInt(getMmoryInfo.get(0).trim());
		totalMemory = Integer.parseInt(getMmoryInfo.get(1).trim());

		// #### App Performance CPU Usage Info
		String getCPUInfo = CPU_UsagePerformance();
		nCpuUSage = Integer.parseInt(getCPUInfo);

		// #### App Performance GPU Usage Info
		ArrayList<String> getGPUInfo = GPU_UsagePerformance();
		// nGPUMemory = Float.parseFloat(getGPUInfo.get(0).replace(" MB", "").trim());
		String[] parseString = getGPUInfo.get(0).split("MB");
		nGPUMemory = Float.parseFloat(parseString[0].trim());
		nGPURendered = Integer.parseInt(getGPUInfo.get(1).replace("Total frames rendered: ", "").trim());

		// #### App Performance Network Traffic Usage Info
		nNetTraffic = getApp_NetworkTrafficUsage(appPackageName);

		// #### App Performance Battery Info
		batteryInfo = BatteryStats_Performance();

		Duration timeElapsed = Duration.between(startTime, endTime);
		logger.info("Time taken to navigate from Home to " + pTabname + " screen (sec): " + timeElapsed.getSeconds());
		extent.extentLogger("Timer",
				"<b>Time taken to navigate from Home to " + pTabname + " (sec):</b> " + timeElapsed.getSeconds());

		if (timeElapsed.getSeconds() < threshold_TimeTaken) {
			logger.info(
					"Time taken to navigate from Home to " + pTabname + " screen (sec): " + timeElapsed.getSeconds());
			extent.extentLoggerPass("Timer", "<b>Time taken to navigate from Home to " + pTabname
					+ " screen (sec): </b>: " + timeElapsed.getSeconds());
			launch = false;
		} else {
			logger.info(
					"Time taken to navigate from Home to " + pTabname + " screen (sec): " + timeElapsed.getSeconds());
			extent.extentLoggerFail("Timer", "<b>Time taken to navigate from Home to " + pTabname
					+ " screen (sec): </b>: " + timeElapsed.getSeconds());
			launch = true;
		}

		if (nativeMemory < threshold_NativeMemory) {
			logger.info("App Memory Info - Native Heap : " + nativeMemory + " MB");
			extent.extentLoggerPass("Memory Info", "<b>App Memory Info - Native Heap :</b> " + nativeMemory + " MB");
		} else {
			logger.error("App Memory Info - Native Heap : " + nativeMemory + " MB");
			extent.extentLoggerFail("Memory Info", "<b>App Memory Info - Native Heap :</b> " + nativeMemory + " MB");
		}

		if (totalMemory < threshold_TotalMemory) {
			logger.info("App Memory Info - Total : " + totalMemory + " MB");
			extent.extentLoggerPass("Memory Info", "<b>App Memory Info - Total :</b> " + totalMemory + " MB");
		} else {
			logger.error("App Memory Info - Total : " + totalMemory + " MB");
			extent.extentLoggerFail("Memory Info", "<b>App Memory Info - Total :</b> " + totalMemory + " MB");
		}

		if (nCpuUSage < threshold_CPU) {
			logger.info("App CPU  Usage status : " + nCpuUSage + "%");
			extent.extentLoggerPass("CPU Info", "<b>App CPU Usage status : </b> " + nCpuUSage + "%");
		} else {
			logger.error("App Memory Info - Total : " + nCpuUSage + "%");
			extent.extentLoggerFail("CPU Info", "<b>App CPU Usage status : </b> " + nCpuUSage + "%");
		}

		if (nGPUMemory < threshold_GPUMem) {
			logger.info("\nTotal GPU Memory Usage of Current session : " + nGPUMemory + " MB");
			extent.extentLoggerPass("GPU Info",
					"<b>Total GPU Memory Usage of Current session :</b> " + nGPUMemory + " MB");
		} else {
			logger.error("\nTotal GPU Memory Usage of Current session exceeded : " + nGPUMemory + " MB");
			extent.extentLoggerFail("GPU Info",
					"<b>Total GPU Memory Usage of Current session exceeded:</b> " + nGPUMemory + " MB");
		}

		if (nGPURendered < threshold_GPURendered) {
			logger.info("\nGPU Current session - Total frames rendered: " + nGPURendered);
			extent.extentLoggerPass("GPU Info", "<b>GPU Current session - Total frames rendered: </b> " + nGPURendered);
		} else {
			logger.error("\nGPU Current session - Total frames rendered: " + nGPURendered);
			extent.extentLoggerFail("GPU Info", "<b>GPU Current session - Total frames rendered: </b> " + nGPURendered);
		}

		if (nNetTraffic < threshold_Network) {
			logger.info("\nThe current App traffic usage is : " + (int) nNetTraffic + " Mbps");
			extent.extentLoggerPass("Traffic Usage",
					"<b>The Current App traffic usage is : </b> " + (int) nNetTraffic + " Mbps");
		} else {
			logger.error("\nThe current App traffic usage is : " + (int) nNetTraffic + " Mbps");
			extent.extentLoggerFail("Traffic Usage",
					"<b>The Current App traffic usage is : </b> " + (int) nNetTraffic + " Mbps");
		}
		performaceDetails
				.add("Screen Navigation " + "," + timeElapsed.getSeconds() + "," + nativeMemory + "MB," + totalMemory
						+ "MB," + nCpuUSage + "%," + nGPUMemory + "MB," + nGPURendered + "," + nNetTraffic + "MB");
		softAssertion.assertEquals(launch, true);
		softAssertion.assertAll();

	}
	/**
	 *  Function to perform Authenticate device changes
	  */
	
	public void authenticateChanges() throws Exception {
		HeaderChildNode("Authenticate Device changes CON-7747/CON-8249/CON-8250");
		waitTime(15000);
		verifyIsElementDisplayed(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
		TVclick(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
		waitTime(5000);
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.obDeviceAutneticationPage, "Device Authentication screen")) {
			logger.info(
					"User is navigated to device autentication page post tapping on autenticate button in welcome screen");
			extent.extentLoggerPass("Page",
					"User is navigated to device autentication page post tapping on autenticate button in welcome screen");
		} else {
			logger.info(
					"User is not navigated to device autentication page post tapping on autenticate button in welcome screen");
			extent.extentLoggerFail("Page",
					"User is not navigated to device autentication page post tapping on autenticate button in welcome screen");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objloginCodeNew, "Login code")) {
			logger.info("Login code is displayed in autenticate device page");
			extent.extentLoggerPass("Page", "Login code is displayed in autenticate device page");
		} else {
			logger.info("Login code is displayed in autenticate device page");
			extent.extentLoggerFail("Page", "Login code is displayed in autenticate device page");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objauthenticatePagePhone, "Phone background")) {
			logger.info("Phone background image is displayed in autenticate device page");
			extent.extentLoggerPass("Page", "Phone background image is displayed in autenticate device page");
		} else {
			logger.info("Phone background image is not displayed in autenticate device page");
			extent.extentLoggerFail("Page", "Phone background image is not displayed in autenticate device page");
		}
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginTimer, "Timer")) {
			logger.info("Timer is displayed in autenticate device page");
			extent.extentLoggerPass("Page", "Timer is displayed in autenticate device page");
		} else {
			logger.info("Timer is not displayed in autenticate device page");
			extent.extentLoggerFail("Page", "Timer is not displayed in autenticate device page");
		}

		String beforetime = TVgetText(Zee5TvWelcomePage.objLoginTimer);
		logger.info("Intial timer : " + beforetime);
		extent.extentLoggerPass("Code", "Intial timer : " + beforetime);
		waitTime(4000);
		TVclick(Zee5TvWelcomePage.objloginCodeNew, "code");
		String aftertime = TVgetText(Zee5TvWelcomePage.objLoginTimer);
		logger.info("Authentication code after clicking on get new code button : " + aftertime);
		extent.extentLoggerPass("Code", "Authentication code after clicking on get new code button : " + aftertime);
		if (!aftertime.equals(beforetime)) {
			logger.info("Timer reverse functionality successfull");
			extent.extentLoggerPass("Code", "Timer reverse functionality successfull");
		} else {
			logger.info("Timer reverse functionality not successfull");
			extent.extentLoggerFail("Code", "Timer reverse not functionality successfull");
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objauthenticatePagePhone, "Authenticate page")) {
			logger.info("Navigation functionality is successfull when user taps back button in authenticate page");
			extent.extentLoggerPass("Pass",
					"Navigation functionality is successfull when user taps back button in authenticate page");
		} else {
			logger.info("Navigation functionality is not successfull when user taps back button in authenticate page");
			extent.extentLoggerFail("Pass",
					"Navigation functionality is not successfull when user taps back button in authenticate page");
		}
		verifyIsElementDisplayed(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
		TVclick(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
		waitTime(5000);
		code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(10000);
		click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
		waitTime(2000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

		if (userType.equals("NonSubscribedUser") || userType.equals("Guest")) {
			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);

		}
		if (userType.equals("SubscribedUser")) {
			String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
			waitTime(5000);

		}
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		waitTime(3000);
		BrowsertearDown();
		setPlatform("TV");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objSelectTab("Home"), "Home landing")) {
			logger.info("User is navigated to Home landing page post authentication automatically");
			extent.extentLoggerPass("Id", "User is navigated to Home landing page post authentication automatically");

		} else {
			logger.info("Automatic authentication Navigation failed");
			extent.extentLoggerFail("Authentication", "Automatic authentication Navigation failed");
		}

	}

	public void blockerScreenSoftBlocker() throws Exception {
		HeaderChildNode("Blocker screen Soft Blocker CON-8251/CON-8252");
		waitTime(15000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Update Button")) {
			logger.info("Update button is displayed when user launch zee5 app");
			extent.extentLoggerPass("Id", "Update button is displayed when user launch zee5 app");

		} else {
			logger.info("Update button is displayed when user launch zee5 app");
			extent.extentLoggerFail("Authentication", "Update button is displayed when user launch zee5 app");
		}
		if (verifyIsElementDisplayed(Zee5TvHomePage.objContinueButtonInBlockerScreen, "Continue button")) {
			logger.info("Continue to zee5 button is displayed in soft blocker screen");
			extent.extentLoggerPass("Id", "Continue to zee5 button is displayed in soft blocker screen");

		} else {
			logger.info("Continue to zee5 button is not displayed in soft blocker screen");
			extent.extentLoggerFail("Authentication",
					"Continue to zee5 button is not displayed in soft blocker screen");
		}
		TVclick(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Update now button");
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objPlaystore, "Play store zee5 app")) {
			logger.info("User is navigated to play store to update the app");
			extent.extentLoggerPass("Playstore", "User is navigated to play store to update the app");
		} else {
			logger.info("User is not navigated to play store and functionality failed");
			extent.extentLoggerPass("Playstore", "User is not navigated to play store and functionality failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Update Button")) {
			logger.info("User is navigated back to zee5 app post tapping back button from play store");
			extent.extentLoggerPass("Id",
					"User is navigated back to zee5 app post tapping back button from play store");

		} else {
			logger.info("User is not navigated back to zee5 app post tapping back button from play store");
			extent.extentLoggerFail("Authentication",
					"User is not navigated back to zee5 app post tapping back button from play store");
		}
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVclick(Zee5TvHomePage.objContinueButtonInBlockerScreen, "Continue button in blocker screen");
		waitTime(8000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objzee5app, "Zee5 app logo")) {
			logger.info("User is navigated to zee5 app post clicking on continue to zee5 app button in blocker screen");
			extent.extentLoggerPass("Page",
					"User is navigated to zee5 app post clicking on continue to zee5 app button in blocker screen");
		} else {
			logger.info(
					"User is not navigated to zee5 app post clicking on continue to zee5 app button in blocker screen");
			extent.extentLoggerFail("Page",
					"User is not navigated to zee5 app post clicking on continue to zee5 app button in blocker screen");
		}
		getDriver().navigate().back();
		waitTime(3000);
		if (!verifyIsElementDisplayed(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Zee5 app logo")) {
			logger.info("User is navigated to TV home page post clicking on back rcu from zee5 app");
			extent.extentLoggerPass("Page",
					"User is navigated to TV home page post clicking on back rcu from zee5 app");
		} else {
			logger.info("User is not navigated to TV home page post clicking on back rcu from zee5 app");
			extent.extentLoggerFail("Page",
					"User is not navigated to TV home page post clicking on back rcu from zee5 app");
		}
	}

	public void blockerScreenHardBlocker() throws Exception {
		HeaderChildNode("Blocker screen Hard Blocker CON-8251/CON-8252");
		waitTime(15000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Update Button")) {
			logger.info("Update button is displayed when user launch zee5 app");
			extent.extentLoggerPass("Id", "Update button is displayed when user launch zee5 app");

		} else {
			logger.info("Update button is displayed when user launch zee5 app");
			extent.extentLoggerFail("Authentication", "Update button is displayed when user launch zee5 app");
		}
		if (verifyIsElementDisplayed(Zee5TvHomePage.objExitButtonInBlockerScreen, "Exit button")) {
			logger.info("Exit button is displayed in hard blocker screen");
			extent.extentLoggerPass("Id", "Exit button is displayed in hard blocker screen");

		} else {
			logger.info("Exit button is not displayed in hard blocker screen");
			extent.extentLoggerFail("Authentication", "Exit button is not displayed in hard blocker screen");
		}
		TVclick(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Update now button");
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objPlaystore, "Play store zee5 app")) {
			logger.info("User is navigated to play store to update the app");
			extent.extentLoggerPass("Playstore", "User is navigated to play store to update the app");
		} else {
			logger.info("User is not navigated to play store and functionality failed");
			extent.extentLoggerPass("Playstore", "User is not navigated to play store and functionality failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Update Button")) {
			logger.info("User is navigated back to zee5 app post tapping back button from play store");
			extent.extentLoggerPass("Id",
					"User is navigated back to zee5 app post tapping back button from play store");

		} else {
			logger.info("User is not navigated back to zee5 app post tapping back button from play store");
			extent.extentLoggerFail("Authentication",
					"User is not navigated back to zee5 app post tapping back button from play store");
		}
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVclick(Zee5TvHomePage.objExitButtonInBlockerScreen, "Exit button in blocker screen");
		waitTime(8000);
		if (!verifyIsElementDisplayed(Zee5TvHomePage.objUpdateButtonInBlockerScreen, "Zee5 app logo")) {
			logger.info("User is navigated to TV home page post clicking on back rcu from zee5 app");
			extent.extentLoggerPass("Page",
					"User is navigated to TV home page post clicking on back rcu from zee5 app");
		} else {
			logger.info("User is not navigated to TV home page post clicking on back rcu from zee5 app");
			extent.extentLoggerFail("Page",
					"User is not navigated to TV home page post clicking on back rcu from zee5 app");
		}
	}

	public void blockerScreenCompleteBlocker() {
		HeaderChildNode("Authenticate Device changes CON-7747/CON-8249/CON-8250");
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objSelectTab("Home"), "Home landing")) {
			logger.info("User is navigated to Home landing page post authentication automatically");
			extent.extentLoggerPass("Id", "User is navigated to Home landing page post authentication automatically");

		} else {
			logger.info("Automatic authentication Navigation failed");
			extent.extentLoggerFail("Authentication", "Automatic authentication Navigation failed");
		}
		if (verifyIsElementDisplayed(Zee5TvHomePage.objSelectTab("Home"), "Home landing")) {
			logger.info("User is navigated to Home landing page post authentication automatically");
			extent.extentLoggerPass("Id", "User is navigated to Home landing page post authentication automatically");

		} else {
			logger.info("Automatic authentication Navigation failed");
			extent.extentLoggerFail("Authentication", "Automatic authentication Navigation failed");
		}

		// Back click and navigation

		// Playstore click and navigation
	}

	public void afsdoubleSlider() throws Exception {
		HeaderChildNode("Removing double silder CON-8003");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			TVTabSelect("Home");
			for (int i = 0; i <= 10; i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}
			TVTabSelect("Settings");
			TVRemoteEvent(20);
			waitTime(3000);
			if (userType.equals("Guest")) {
				TVclick(Zee5TvWelcomePage.objALLPlanOption, "All plan option");
				waitTime(3000);
				TVclick(Zee5TvWelcomePage.objALLPlanOption, "All plan option");
				TVRemoteEvent(20);
				waitTime(3000);
				TVRemoteEvent(20);
				waitTime(3000);
				TVRemoteEvent(23);
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objAFSLoginPopUpSlider, "AFS login popup")) {
					logger.info("AFS Login popup is displayed when user clicks on plan card for guest user");
					extent.extentLoggerPass("Login",
							"AFS Login popup is displayed when user clicks on plan card for guest user");
				} else {
					logger.info("AFS login popup is not displayed, Functionality failed");
					extent.extentLoggerFail("Login", "AFS login popup is not displayed, Functionality failed");
				}
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(23);
				waitTime(3000);

				if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopUp, "LoginPopup")) {
					logger.info("Login popup is not displayed as expected for guest user");
					extent.extentLoggerPass("Popup", "Login popup is not displayed as expected for guest user");
				} else {
					logger.info("Login popup is displayed when user clicks on plan card for guest user");
					extent.extentLoggerFail("Popup",
							"Login popup is displayed when user clicks on plan card for guest user");
				}

				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objautneticatePage, "Authenticate page")) {
					logger.info("User is navigated to authenticate page post clicking on login button");
					extent.extentLoggerPass("Page",
							"User is navigated to authenticate page post clicking on login button");
				} else {
					logger.info("User is not navigated to authenticate page post clicking on login button");
					extent.extentLoggerFail("Page",
							"User is not navigated to authenticate page post clicking on login button");
				}
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);

			}

		} else {
			logger.info("Scenario not applicable for Nonsubscribe/subscribe user");
			extent.extentLoggerPass("Pass", "Scenario not applicable for Nonsubscribe/subscribe user");
		}
	}

	

	public void logout() {
		try {
			HeaderChildNode("Logout");
			TVTabSelect("Home");

			for (int i = 0; i <= 10; i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}

			TVTabSelect("Settings");
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(20);
			for (int i = 0; i <= 14; i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}
			TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
			waitTime(3000);
			TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
			waitTime(3000);
			TVTabSelect("Home");
		} catch (Exception e) {

		}
	}

	public void parentalControl() throws Exception {
		HeaderChildNode("Parental control verification CON-8570/CON-8679/CON-8681");
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			TVTabSelect("Home");
			for (int i = 0; i <= 10; i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}
			TVTabSelect("Settings");
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(20);
			waitTime(3000);

			for (int i = 0; i <= 3; i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}
			for (int i = 0; i <= 14; i++) {
				TVRemoteEvent(22);
				waitTime(2000);
			}
			if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
				TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
				waitTime(3000);
				TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
			}
			waitTime(3000);
		}
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
				waitTime(7000);
			} else {
				logger.info("Popup not displayed");
			}
			code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			HeaderChildNode("Switching to WEB platform to Authenticate device");
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(10000);
			click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
			waitTime(2000);
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordNonSub, "Password field");
			} else if (userType.equals("SubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordSub, "Password field");
			}
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			HeaderChildNode("Selecting Restrict 13+ option in parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrict13, "Restrict 13+ option");
			verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(5000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);

			TVTabSelect("Home");
			logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
			extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);
			String searchdata6[] = { "t", "h", "e" };
			String searchdata7[] = { "a", "w", "a", "k", "e", "n", "i", "n", "g" };
			type(searchdata6);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchdata7);
			String content3 = TVgetText(Zee5TvSearchPage.objEditbox);
			logger.info("Entered Search Data : " + content3);
			extent.extentLoggerPass("Search", "Entered Searched Data : " + content3);
			List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele3.size(); i++) {

				String title3 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				logger.info(title3);
				extent.extentLogger("Title", "Serach result content title : " + title3);
				waitTime(7000);
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title3, "Episodes"),
						"Searched 13+ content"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title3, "Episodes"),
							"Searched 13+ content");
					break;
				} else {
					logger.info("No match");
				}

			}
		}
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objParentpopuptitle, "Parental popup")) {
			logger.info("Parental popup is displayed when user keeps parental control for 13+ content");
			extent.extentLoggerPass("13+",
					"Parental popup is displayed when user keeps parental control for 13+ content");
		} else {
			logger.info("Popup functionality failed");
			extent.extentLoggerFail("Popup", "Popup functionality failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		TVTabSelect("Home");

		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}

		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
				waitTime(7000);
			} else {
				logger.info("Popup not displayed");
			}
			code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(5000);

			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordNonSub, "Password field");
			} else if (userType.equals("SubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordSub, "Password field");
			}
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			HeaderChildNode("Selecting Restrict ALL option in parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrictAll, "Restrict ALL option");
			verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
			type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
			waitTime(4000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(5000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);
			TVTabSelect("Home");
			logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
			extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);
			String searchdata1[] = { "b", "a", "b", "l", "u", };
			String searchdata2[] = { "d", "a", "b", "l", "u" };
			String searchdata3[] = { "r", "o", "b", "o" };
			String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			String content = TVgetText(Zee5TvSearchPage.objEditbox);

			logger.info("Entered Search Data : " + content);
			extent.extentLogger("Search", "Entered Searched Data : " + content);

			List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele.size(); i++) {

				title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				logger.info(title);
				extent.extentLogger("Title", "Serach result content title : " + title);
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
						"Searched Movie"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
					break;
				} else {
					System.out.println("No match");
				}

			}
		}
		waitTime(10000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objParentpopuptitle, "Parental popup")) {
			logger.info("Parental popup is displayed when user keeps parental control for all content");
			extent.extentLoggerPass("All",
					"Parental popup is displayed when user keeps parental control for all content");
		} else {
			logger.info("Popup functionality failed");
			extent.extentLoggerFail("Popup", "Popup functionality failed");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		TVTabSelect("Home");

		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}

		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		for (int i = 0; i <= 14; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginOption, "Login optipn")) {
			logger.info("Logout functionality successfull");
			extent.extentLoggerPass("Logger", "Logout functionality successfull");
		}
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(10000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objLoginPopupAmazon, "Afs login popup")) {
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objLogiButtonAmazon, "Login button amazon");
				waitTime(7000);
			} else {
				logger.info("Popup not displayed");
			}
			code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(10000);
			click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

			if (userType.equals("NonSubscribedUser")) {
				String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedUserName");
				String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
				type(PWALoginPage.objEmailField, Username, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, Password, "Password field");
				waitTime(5000);

			}
			if (userType.equals("SubscribedUser")) {
				String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedUserName");
				String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
				type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
				waitTime(5000);

			}
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(8000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			if (userType.equals("NonSubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordNonSub, "Password field");
			} else if (userType.equals("SubscribedUser")) {
				type(PWALoginPage.objPasswordField, parentpasswordSub, "Password field");
			}
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			HeaderChildNode("Selecting No Restrict option in parent control page");
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objNoRestrictionSelected, "No Restriction option");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
			waitTime(5000);
			checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
			type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			waitTime(3000);
			BrowsertearDown();
			setPlatform("TV");
			waitTime(10000);
			TVTabSelect("Home");
			logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
			extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);
			String searchdata1[] = { "b", "a", "b", "l", "u", };
			String searchdata2[] = { "d", "a", "b", "l", "u" };
			String searchdata3[] = { "r", "o", "b", "o" };
			String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			String content = TVgetText(Zee5TvSearchPage.objEditbox);

			logger.info("Entered Search Data : " + content);
			extent.extentLogger("Search", "Entered Searched Data : " + content);

			List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele.size(); i++) {

				title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				logger.info(title);
				extent.extentLogger("Title", "Serach result content title : " + title);
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
						"Searched Movie"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
					break;
				} else {
					System.out.println("No match");
				}

			}
		}
		waitTime(7000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
		waitTime(7000);
		// LoadingInProgress();
		if (!verifyIsElementDisplayed(Zee5TvWelcomePage.objParentpopuptitle, "Parental popup")) {
			logger.info("Parental popup is not displayed when user keeps parental control for No restrict option");
			extent.extentLoggerPass("All",
					"Parental popup is not displayed when user keeps parental control for No restrict option");
		} else {
			logger.info("Popup functionality failed");
			extent.extentLoggerFail("Popup", "Popup functionality failed");
		}
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
	}
	/**
	 *  Function to perform verifying new age ratings
	  */
	public void newAgeRating() throws Exception {
		HeaderChildNode("Verifying New age rating implementation CON-7739/CON-8685/CON-8686");
		if (userType.equals("Guest")) {
			waitTime(10000);
			getDriver().navigate().back();
		}
		waitTime(3000);
		if (!verifyIsElementDisplayed(Zee5TVCarousel.objAgeRatingInContentDeatil, "Age rating in carousel")) {
			logger.info("Age rating in carousel is not displayed as expected");
			extent.extentLoggerPass("Age", "Age rating in carousel is not displayed as expected");
		} else {
			logger.info("Age rating is displayed in carousel");
			extent.extentLoggerFail("Age", "Age rating is displayed in carousel");
		}
		String language = getLanguage(userType);
		Response resp = ResponseInstance.getResponseForPagesTv("Home", language, 1, userType);
		for (int i = 1; i <= 20; i++) {
			int total = resp.jsonPath().getInt("buckets[" + i + "].total");
			if (total > 20) {
				viewAllTrayname = resp.jsonPath().getString("buckets[" + i + "].title");
				logger.info(viewAllTrayname);
				break;
			}
		}
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content");
				waitTime(7000);
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content");
				}
				break;
			} else {
				TVRemoteEvent(20);
				waitTime(3000);
			}
		}
		waitTime(10000);
		String ageRatingExpected = TVgetText(Zee5TVCarousel.objAgeRatingInContentDeatil);

		if (verifyIsElementDisplayed(Zee5TVCarousel.objAgeRatingInContentDeatil, "Age rating in carousel")) {
			logger.info("Age rating in details page is displayed as expected");
			extent.extentLoggerPass("Age", "Age rating in details page is displayed as expected");
		} else {
			logger.info("Age rating is not displayed in details page");
			extent.extentLoggerFail("Age", "Age rating is not displayed in details page");
		}
		logger.info("Exepected age rating : " + ageRatingExpected);
		extent.extentLoggerPass("Age", "Exepected age rating : " + ageRatingExpected);
		logger.info("Actual age rating : " + ageRatingExpected);
		extent.extentLoggerPass("Age", "Actual age rating : " + ageRatingExpected);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		TVTabSelect("Movies");
		waitTime(3000);
		if (!verifyIsElementDisplayed(Zee5TVCarousel.objAgeRatingInContentDeatil, "Age rating in carousel")) {
			logger.info("Age rating in  movies carousel is not displayed as expected");
			extent.extentLoggerPass("Age", "Age rating in movies carousel is not displayed as expected");
		} else {
			logger.info("Age rating is displayed in movies carousel");
			extent.extentLoggerFail("Age", "Age rating is movies displayed in carousel");
		}
		verifyIsElementDisplayed(Zee5TVCarousel.objCarouselPlayButton, "Play button");
		TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
		waitTime(9000);
		String ageRatingExpectedplayer = TVgetText(Zee5TVCarousel.objAgeRatingInContentDeatil);
		if (verifyIsElementDisplayed(Zee5TVCarousel.objAgeRatingInContentDeatil, "Age rating in carousel")) {
			logger.info("Age rating in details page is displayed as expected");
			extent.extentLoggerPass("Age", "Age rating in details page is displayed as expected");
		} else {
			logger.info("Age rating is not displayed in details page");
			extent.extentLoggerFail("Age", "Age rating is not displayed in details page");
		}
		logger.info("Exepected age rating : " + ageRatingExpectedplayer);
		extent.extentLoggerPass("Age", "Exepected age rating : " + ageRatingExpectedplayer);
		logger.info("Actual age rating : " + ageRatingExpectedplayer);
		extent.extentLoggerPass("Age", "Actual age rating : " + ageRatingExpectedplayer);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		TVTabSelect("News");
		waitTime(2000);
		if (!verifyIsElementDisplayed(Zee5TVCarousel.objAgeRatingInContentDeatil, "Age rating in carousel")) {
			logger.info("Age rating in news content is not displayed as expected");
			extent.extentLoggerPass("Age", "Age rating in news content is not displayed as expected");
		} else {
			logger.info("Age rating in news content is displayed");
			extent.extentLoggerFail("Age", "Age rating in news content is displayed");
		}
	}
	
	/**
	 *  Function to perform age rating and content descriptor in player
	  */

	public void ageRatingInPlayer() throws Exception {
		HeaderChildNode("Age Rating and Content descriptor in player screen CON-7734/CON-8687/CON-8688");
		waitTime(5000);
		if (userType.equals("Guest")) {
			waitTime(10000);
			getDriver().navigate().back();
		}
		waitTime(3000);
		waitTime(5000);

		TVTabSelect("Home");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objSearchSpaceBar, "Search page")) {
			logger.info("User is navigated to search page after clicking on search button");
			extent.extentLoggerPass("Search", "User is navigated to search page after clicking on search button");
		} else {
			logger.info("User is not navigated to serach page");
			extent.extentLoggerFail("Navigation", "User is not navigated to serach page");
		}
		String searchdata1[] = { "b", "a", "b", "l", "u", };
		String searchdata2[] = { "d", "a", "b", "l", "u" };
		String searchdata3[] = { "r", "o", "b", "o" };
		String searchdata4[] = { "r", "u", "m", "b", "l", "e" };
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content);
		extent.extentLogger("Search", "Entered Searched Data : " + content);

		int k;
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele.size(); i++) {

			title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}

		waitTime(8000);
		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		if (title.equals(title)) {
			logger.info("user is navigated to respective content detail page");
			extent.extentLoggerPass("user", "user is navigated to respective content detail page");
		}
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(10000);
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerSkipIntro, "SkipIntro")) {
			TVRemoteEvent(20);
			waitTime(2000);
			TVRemoteEvent(23);
			logger.info("clicked on skip intro");
			extent.extentLoggerPass("Intro", "clicked on skip intro");
		} else {
			logger.info("Skip intro is not displayed");
			extent.extentLogger("Intro", "Skip intro is not displayed");
		}
		waitTime(12000);
		TVRemoteEvent(23);
		waitTime(4000);
		TVRemoteEvent(23);
		waitTime(2000);
		String ageanddesc = TVgetText(Zee5TVCarousel.objAgeRatingInPlayerandInfo);
		logger.info("Exepected age rating : " + ageanddesc);
		extent.extentLoggerPass("Age", "Exepected age rating : " + ageanddesc);
		logger.info("Actual age rating : " + ageanddesc);
		extent.extentLoggerPass("Age", "Actual age rating : " + ageanddesc);
		if (verifyIsElementDisplayed(Zee5TVCarousel.objAgeRatingInPlayerandInfo, "Age Rating and Descriptor")) {
			logger.info("Age Rating and content descriptor is displayed in consumption page");
			extent.extentLoggerPass("play", "Age Rating and content descriptor is displayed in consumption page");
		} else {
			logger.info("Age Rating and content descriptor is not displayed in consumption page");
			extent.extentLoggerFail("play", "Age Rating and content descriptor is not displayed in consumption page");
		}
		TVRemoteEvent(23);
		waitTime(7000);
		if (verifyIsElementDisplayed(Zee5TvHomePage.objinfo, "Info")) {
			logger.info("Player is paused");
		} else {
			TVRemoteEvent(23);
		}
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(23);
		waitTime(3000);
		waitTime(2000);
		String ageanddescinPlayer = TVgetText(Zee5TVCarousel.objAgeRatingInPlayerandInfo);
		logger.info("Exepected age rating in info page : " + ageanddesc);
		extent.extentLoggerPass("Age", "Exepected age rating in info page : " + ageanddesc);
		logger.info("Actual age rating in info page : " + ageanddesc);
		extent.extentLoggerPass("Age", "Actual age rating in info page : " + ageanddesc);
		if (verifyIsElementDisplayed(Zee5TVCarousel.objAgeRatingInPlayerandInfo, "Age Rating and Descriptor")) {
			logger.info("Age Rating and content descriptor is displayed in player info page");
			extent.extentLoggerPass("play", "Age Rating and content descriptor is displayed in player info page");
		} else {
			logger.info("Age Rating and content descriptor is not displayed in player info page");
			extent.extentLoggerFail("play", "Age Rating and content descriptor is not displayed in player info page");
		}

		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();
		waitTime(5000);
		getDriver().launchApp();
		waitTime(3000);
	}
	/**
	 *  Function to perform cast functionality
	  */
	public void castfunctionality() throws Exception {
		HeaderChildNode("Chromecast functioanlity CON-7585");
		waitTime(3000);
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(10000);
		click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
		waitTime(2000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

		if (userType.equals("NonSubscribedUser") || userType.equals("Guest")) {
			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);

		}
		if (userType.equals("SubscribedUser")) {
			String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
			waitTime(5000);

		}
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		if (checkElementDisplayed(PWASearchPage.objSearchEditBox, "Search EditBox")) {
			logger.info("User landed on Search landing screen post tapping on search icon");
			extent.extentLogger("Search landingscreen",
					"User landed on Search landing screen post tapping on search icon");
		}
		type(PWASearchPage.objSearchEditBox, "bablu dablu robo rumble", "Search bar");
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Movies"), "All Tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchedMovie, " Search Result");
		waitTime(5000);
		String titleInWeb = getText(Zee5TVCarousel.objPlayerTitleInWEB);
		verifyElementPresentAndClick(PWASearchPage.objchromecastIcon, "Chromecast icon");
		waitTime(3000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		waitTime(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		waitTime(15000);
		setPlatform("TV");
		TVRemoteEvent(23);
		waitTime(2000);
		TVRemoteEvent(23);
		waitTime(2000);
		String titleInTV = TVgetText(Zee5TVCarousel.objPlayerTitleInTV);
		if (titleInWeb.equals(titleInTV)) {
			logger.info("Chromecast playback verified");
			extent.extentLoggerPass("Playback", "Chromecast playback verified");
		}
		String beforeTime = TVgetText(Zee5TVCarousel.objelapsedTimeInTV);
		logger.info("Time before forward functionality : " + beforeTime);
		extent.extentLoggerPass("Time", "Time before forward functionality : " + beforeTime);
		TVRemoteEvent(22);
		waitTime(2000);
		TVRemoteEvent(22);
		waitTime(3000);
		TVRemoteEvent(22);
		TVclick(Zee5TVCarousel.objelapsedTimeInTV, "Elapsed time");
		String afterTime = TVgetText(Zee5TVCarousel.objelapsedTimeInTV);
		logger.info("Time after forward functionality : " + afterTime);
		extent.extentLoggerPass("Time", "Time after forward functionality : " + afterTime);
		if (!beforeTime.equals(afterTime)) {
			logger.info("Forward functionality successfull");
			extent.extentLoggerPass("Time", "Forward functionality successfull");
		} else {
			logger.info("Forward functionality not successfull");
			extent.extentLoggerFail("Time", "Forward functionality not successfull");
		}
		String beforeTime1 = TVgetText(Zee5TVCarousel.objelapsedTimeInTV);
		logger.info("Time before Reverse functionality : " + beforeTime1);
		extent.extentLoggerPass("Time", "Time before Reverse functionality : " + beforeTime1);
		TVRemoteEvent(21);
		waitTime(3000);
		TVRemoteEvent(21);
		waitTime(5000);
		TVclick(Zee5TVCarousel.objelapsedTimeInTV, "Elapsed time");
		String afterTime1 = TVgetText(Zee5TVCarousel.objelapsedTimeInTV);
		logger.info("Time after Reverse functionality : " + afterTime1);
		extent.extentLoggerPass("Time", "Time after Reverse functionality : " + afterTime1);
		if (!beforeTime1.equals(afterTime1)) {
			logger.info("Reverse functionality successfull");
			extent.extentLoggerPass("Time", "Reverse functionality successfull");
		} else {
			logger.info("Reverse functionality not successfull");
			extent.extentLoggerFail("Time", "Reverse functionality not successfull");
		}
		waitTime(3000);
		BrowsertearDown();
	}

	public void tvodContentPlayback() throws Exception {
		HeaderChildNode("TVOD content Playback");
		waitTime(10000);

		waitTime(5000);

		TVTabSelect("Home");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(8000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objSearchSpaceBar, "Search page")) {
			logger.info("User is navigated to search page after clicking on search button");
			extent.extentLoggerPass("Search", "User is navigated to search page after clicking on search button");
		} else {
			logger.info("User is not navigated to serach page");
			extent.extentLoggerFail("Navigation", "User is not navigated to serach page");
		}
		String searchdata1[] = { "d", "e", "m", "o" };
		String searchdata2[] = { "m", "o", "o", "n" };
		type(searchdata1);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata2);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content);
		extent.extentLogger("Search", "Entered Searched Data : " + content);

		TVclick(Zee5TVCarousel.objdemomoonresultImage, "Plex content");
		logger.info("Plex tag is displayed for plex content");
		extent.extentLoggerPass("tag", "Plex tag is displayed for plex content");
		waitTime(5000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TVCarousel.objsliderbg, "Login popup")) {
				logger.info("Login popup is displayed when guest user clicks on zee plex content");
				extent.extentLoggerPass("Pass", "Login popup is displayed when guest user clicks on zee plex content");
			} else {
				logger.info("Popup functionality failed");
				extent.extentLoggerFail("popup", "Popup functionality failed");
			}
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TVCarousel.objTvodPopup, "How do i watch this popup")) {
				logger.info("How do i watch this popup is displayed when non Zeeplex user taps on zeeplex content");
				extent.extentLoggerPass("Popup",
						"How do i watch this popup is displayed when non Zeeplex user taps on zeeplex content");
			} else {
				logger.info("Zee plex popup functionality failed");
				extent.extentLoggerFail("Popup", "Zee plex popup functionality failed");
			}
		}
		getDriver().navigate().back();
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
		String searchdata3[] = { "b", "i", "n", "g", "e" };
		String searchdata4[] = { "h", "o", "l", "i", "d", "a", "y", "s" };
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata4);
		String content2 = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content);
		extent.extentLogger("Search", "Entered Searched Data : " + content);

		List<WebElement> ele2 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele2.size(); i++) {

			title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Videos"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Videos"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}
		waitTime(5000);
		if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TVCarousel.objsliderbg, "Login popup")) {
				logger.info("Login popup is displayed when guest user clicks on zee plex content");
				extent.extentLoggerPass("Pass", "Login popup is displayed when guest user clicks on zee plex content");
			} else {
				logger.info("Popup functionality failed");
				extent.extentLoggerFail("popup", "Popup functionality failed");
			}
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			if (verifyIsElementDisplayed(Zee5TVCarousel.objTvodPopup, "How do i watch this popup")) {
				logger.info("How do i watch this popup is displayed when non Zeeplex user taps on zeeplex content");
				extent.extentLoggerPass("Popup",
						"How do i watch this popup is displayed when non Zeeplex user taps on zeeplex content");
			} else {
				logger.info("Zee plex popup functionality failed");
				extent.extentLoggerFail("Popup", "Zee plex popup functionality failed");
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			TVRemoteEvent(22);
			waitTime(2000);
		}
		TVTabSelect("Settings");
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(8000);
		code = TVgetText(Zee5TvWelcomePage.objloginCodeNew);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(10000);
		click(Zee5TvWelcomePage.objNoThanks, "No thanks option");
		waitTime(2000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

		type(PWALoginPage.objEmailField, "manju@cash.com", "Email Field");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, "123456", "Password field");
		waitTime(5000);

		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(8000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		checkElementDisplayed(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		type(PWAHamburgerMenuPage.objAuthenticationField, code, "Authentication Field");
		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		waitTime(3000);
		BrowsertearDown();
		setPlatform("TV");
		waitTime(10000);
		TVTabSelect("Home");
		logger.info(TVgetText(Zee5TvHomePage.objHighlightedTab) + "Tab is highlighted");
		extent.extentLoggerPass("Tab", "HighLighted Tab :" + TVgetText(Zee5TvHomePage.objHighlightedTab));

		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objSearchSpaceBar, "Search page")) {
			logger.info("User is navigated to search page after clicking on search button");
			extent.extentLoggerPass("Search", "User is navigated to search page after clicking on search button");
		} else {
			logger.info("User is not navigated to serach page");
			extent.extentLoggerFail("Navigation", "User is not navigated to serach page");
		}
		String searchdata7[] = { "d", "e", "m", "o" };
		String searchdata8[] = { "m", "o", "o", "n" };
		type(searchdata7);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata8);
		String content5 = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content5);
		extent.extentLogger("Search", "Entered Searched Data : " + content5);

		TVclick(Zee5TVCarousel.objdemomoonresultImage, "Plex content");
		logger.info("Plex tag is displayed for plex content");
		waitTime(35000);
		extent.extentLoggerPass("tag", "Plex tag is displayed for plex content");
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objAd, "Ad")) {
			logger.info("Ad is present when user click on any content and verified");
			extent.extentLoggerPass("Ad", "Ad is present when user click on any content and verified");
			AdVerify();
		} else {
			logger.info("Ad is not present");
			extent.extentLoggerPass("Ad", "Ad is not present");
		}
		waitTime(3000);
		if (verifyIsElementDisplayed(Zee5TvSearchPage.objGotolivebutton, "Live button in player")) {
			logger.info("Demo moon live playback verified");
			extent.extentLoggerPass("Popup", "Demo moon live playback verified");
		} else {
			logger.info("Demo moon live playback failed");
			extent.extentLoggerFail("Popup", "Demo moon live playback failed");
		}
		getDriver().navigate().back();
		int lenText2 = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText2; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
		String searchdata6[] = { "b", "i", "n", "g", "e" };
		String searchdata9[] = { "h", "o", "l", "i", "d", "a", "y", "s" };
		type(searchdata6);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(searchdata9);
		String content3 = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content3);
		extent.extentLogger("Search", "Entered Searched Data : " + content3);

		List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele3.size(); i++) {

			title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Videos"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Videos"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}
		waitTime(35000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objAd, "Ad")) {
			logger.info("Ad is present when user click on any content and verified");
			extent.extentLoggerPass("Ad", "Ad is present when user click on any content and verified");
			AdVerify();
		} else {
			logger.info("Ad is not present");
			extent.extentLoggerPass("Ad", "Ad is not present");
		}
		waitTime(5000);
		waitTime(4000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play free content");
			extent.extentLoggerPass("play", "User is able to play free content");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
		logger.info("Watch now slider is not displayed as expected");
		extent.extentLoggerPass("Popup", "Watch now slider is not displayed as expected");
		getDriver().navigate().back();
		waitTime(3000);
		int lenText7 = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText7; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
		String search6[] = { "s", "e", "n", "s", "a", "t", "i", "o", "n", "a", "l" };
		String search9[] = { "d", "i", "a", "l", "o", "g", "u", "e", "s" };
		type(search6);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
		type(search9);
		String content4 = TVgetText(Zee5TvSearchPage.objEditbox);
		logger.info("Entered Search Data : " + content4);
		extent.extentLogger("Search", "Entered Searched Data : " + content4);

		List<WebElement> ele4 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele4.size(); i++) {

			title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Videos"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Videos"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}
		waitTime(35000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objAd, "Ad")) {
			logger.info("Ad is present when user click on any content and verified");
			extent.extentLoggerPass("Ad", "Ad is present when user click on any content and verified");
			AdVerify();
		} else {
			logger.info("Ad is not present");
			extent.extentLoggerPass("Ad", "Ad is not present");
		}
		waitTime(5000);
		waitTime(4000);
		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objPlayerContainer, "Consumption screen")) {
			logger.info("User is able to play free content");
			extent.extentLoggerPass("play", "User is able to play free content");
		} else {
			logger.info("playback did not initiate");
			extent.extentLoggerFail("play", "playback did not initiate");
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);

	}
	
	/**
	 *  Function to perform Contact us page validation
	  */
	public void contactUs() throws Exception {
		HeaderChildNode("Contact us page validations");
		waitTime(20000);
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			if (verifyIsElementDisplayed(PWAHamburgerMenuPage.objprofilepic, "Profile icon")) {
				waitTime(2000);
				TVclick(PWAHamburgerMenuPage.objprofilepic, "Profile icon");
				extent.extentLoggerPass("Clicked on Profile icon", "Clicked on Profile icon");
			}
			}
			if (userType.equals("Guest")) {
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
				TVRemoteEvent(20);
				waitTime(2000);
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
				logger.info("logged in as Guest User");
				extent.extentLoggerPass("Button", "logged in as Guest User");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			}
			waitTime(10000);
			for (int i = 0; i <= 4; i++) {
				TVRemoteEvent(21);
				waitTime(2000);
			}

			for (int i = 0; i <= 7; i++) {
				TVRemoteEvent(20);
				waitTime(2000);
			}

			TVTabSelect("Settings");
			waitTime(3000);
			TVRemoteEvent(23);
			waitTime(5000);
			TVRemoteEvent(22);
			waitTime(2000);
			TVRemoteEvent(22);
			waitTime(3000);		
			verifyIsElementDisplayed(Zee5TvHomePage.objOptionsInSetting("Contact"), "Contact us option in setting page");
		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(20);
		waitTime(2000);
		TVRemoteEvent(21);
		waitTime(2000);
		TVRemoteEvent(21);
	    waitTime(3000);
		TVRemoteEvent(23);
		waitTime(7000);
		verifyIsElementDisplayed(Zee5TvHomePage.objContactUsPage, "Contact us page");
		waitTime(3000);
			}
		if (userType.equals("Guest")) {
			TVRemoteEvent(22);
		    waitTime(3000);
			TVRemoteEvent(23);
			waitTime(7000);
			verifyIsElementDisplayed(Zee5TvHomePage.objContactUsPage, "Contact us page");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objSelectCountryBoxArrow, "Country Code Arrow");
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvHomePage.objCountryCodePopup, "Country code popup")) {
				waitTime(10000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(19);
				waitTime(2000);
				TVRemoteEvent(23);
				waitTime(3000);
				String countryBefore = TVgetText(Zee5TvHomePage.objSelectCountryBox);
				logger.info("Current country : " + countryBefore);
				TVclick(Zee5TvHomePage.objResetButton, "reset button");
				waitTime(3000);
				TVclick(Zee5TvHomePage.objResetButton, "reset button");
				waitTime(5000);
				String countryAfter = TVgetText(Zee5TvHomePage.objSelectCountryBox);
				logger.info("Current country : " + countryAfter);
				if (!countryBefore.equals(countryAfter)) {
					logger.info("Reset functionality successfull");
					extent.extentLoggerPass("Reset", "Reset functionality successfull");
				} else {
					logger.info("Reset functionality failed");
					extent.extentLoggerFail("reset", "Reset functionality failed");
				}
			}
			for (int i = 0; i <= 5; i++) {
				TVRemoteEvent(19);
				waitTime(2000);
			}
			TVRemoteEvent(22);
			waitTime(2000);
			TVRemoteEvent(22);
			waitTime(3000);
			TVRemoteEvent(23);
			waitTime(3000);
			String phoneNumber[] = { "7", "8", "9", "2", "2", "1", "5", "2", "1", "4" };
			contactUsType(phoneNumber);
			for (int i = 0; i <= 8; i++) {
				TVRemoteEvent(22);
			}
			waitTime(3000);
			TVRemoteEvent(20);
			waitTime(3000);
			TVRemoteEvent(23);
			waitTime(3000);
			String emailID1[] = { "t", "v", "a", "u", "t", "o", "m", "a", "t", "i", "o", "n" };
			contactUsType(emailID1);
			TVclick(Zee5TvHomePage.objSplCharechter, " Special Characters button");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objatbutton, "@ button");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objAlphaKeyboard, " Alpha keyboard button");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objAlphaKeyboard, " Alpha keyboard button");
			waitTime(3000);
			TVRemoteEvent(23);
			String emailID2[] = { "g", "m", "a", "i", "l" };
			contactUsType(emailID2);
			TVclick(Zee5TvHomePage.objSplCharechter, " Special Characters button");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objdotbutton, " . button");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objAlphaKeyboard, " Alpha keyboard button");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objAlphaKeyboard, " Alpha keyboard button");
			waitTime(3000);
			String emailID3[] = { "c", "o", "m" };
			contactUsType(emailID3);
			for (int i = 0; i <= 8; i++) {
				TVRemoteEvent(22);
			}
			waitTime(3000);
			TVRemoteEvent(20);
			waitTime(5000);
			TVRemoteEvent(20);
			waitTime(5000);
			TVRemoteEvent(23);
			waitTime(3000);
			String description[] = { "x", "y", "z" };
			contactUsType(description);
			for (int i = 0; i <= 8; i++) {
				TVRemoteEvent(22);
			}
			waitTime(3000);

			TVclick(Zee5TvHomePage.objSubmitButton, "Submit button");
			waitTime(7000);
			if (verifyIsElementDisplayed(Zee5TvHomePage.objSuccessPopup, "Success popup")) {
				logger.info("Successfully submitted popup is dsiplayed and contact us page submission succesfull");
				extent.extentLoggerPass("Success",
						"Successfully submitted popup is dsiplayed and contact us page submission succesfull");
			} else {
				logger.info("Contact us page validation failed");
				extent.extentLoggerFail("Contact", "Contact us page validation failed");
			}
			getDriver().navigate().back();
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			TVRemoteEvent(23);
			String description[] = { "x", "y", "z" };
			contactUsType(description);
			String before = TVgetText(Zee5TvHomePage.objEditBox);
			TVclick(Zee5TvHomePage.objResetButton, "reset button");
			waitTime(3000);
			TVclick(Zee5TvHomePage.objResetButton, "reset button");
			waitTime(2000);
			String after = TVgetText(Zee5TvHomePage.objEditBox);
			if (!before.equals(after)) {
				logger.info("Reset functionality successfull");
				extent.extentLoggerPass("Reset", "Reset functionality successfull");
			} else {
				logger.info("Reset functionality failed");
				extent.extentLoggerFail("reset", "Reset functionality failed");

			}
			TVRemoteEvent(23);
			String description1[] = { "x", "y", "z" };
			contactUsType(description1);
			for (int i = 0; i <= 8; i++) {
				TVRemoteEvent(22);
			}
			waitTime(3000);

			TVclick(Zee5TvHomePage.objSubmitButton, "Submit button");
			waitTime(7000);
			if (verifyIsElementDisplayed(Zee5TvHomePage.objSuccessPopup, "Success popup")) {
				logger.info("Successfully submitted popup is dsiplayed and contact us page submission succesfull");
				extent.extentLoggerPass("Success",
						"Successfully submitted popup is dsiplayed and contact us page submission succesfull");
			} else {
				logger.info("Contact us page validation failed");
				extent.extentLoggerFail("Contact", "Contact us page validation failed");
			}
			
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().closeApp();

		waitTime(3000);

		getDriver().launchApp();

		waitTime(10000);
	}
	
	public void contactUsType(String array[]) throws Exception {
		String typedata[] = array;
		int typedatalength = typedata.length;
		StringBuilder typeData = new StringBuilder();
		for (int j = 0; j < typedatalength; j++) {
			getDriver().findElement(Zee5TvHomePage.objContactUsKeyboardBtn(typedata[j])).click();
			waitTime(2000);	
			typeData.append(typedata[j]);
		}
		waitTime(2000);
		logger.info("Typing : " + typeData);
		extentLogger("ContactUS", "Typing the content : "  +  typeData);
	}


}
