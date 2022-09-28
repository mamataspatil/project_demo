package com.business.zee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.metadata.ResponseInstance;
import com.mixpanelValidation.Mixpanel;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.TVPages.PWAHamburgerMenuPage;
import com.zee5.TVPages.PWALoginPage;
import com.zee5.TVPages.Zee5TVCarousel;
import com.zee5.TVPages.Zee5TvHomePage;
import com.zee5.TVPages.Zee5TvPlayerPage;
import com.zee5.TVPages.Zee5TvSearchPage;
import com.zee5.TVPages.Zee5TvWelcomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Zee5TvMixPanelBusinessLogic extends Utilities {
	public Zee5TvMixPanelBusinessLogic(String Application) throws InterruptedException {
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

	static String code = "";

	Set<String> hash_Set = new HashSet<String>();

	@SuppressWarnings("unused")
	private String LacationBasedLanguge;

	List<String> LocationLanguage = new ArrayList<String>();

	List<String> DefaultLanguage = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInWelcomscreen = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInHamburgerMenu = new ArrayList<String>();

	Response resp;

	ArrayList<String> MastheadTitleApi = new ArrayList<String>();

	public static boolean relaunchFlag = false;
	public static boolean appliTools = false;

	public static boolean PopUp = false;

	public String title = "";

	public String onboardingTraytitle = "";
	public String onboardingPremiumContenttitle = "";

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void TvtearDown() {
		getDriver().quit();
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	Mixpanel mixpanel = new Mixpanel();

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

	public void AdVerify() throws Exception {
		verifyElementNotPresent(Zee5TvPlayerPage.objAd, 40);
		extent.extentLoggerPass("Ad Verified", "Ad Verified");
	}

	public void login(String userType) throws Exception {
		HeaderChildNode("Authentication process - Login as : " + userType);
		if (userType.equals("Guest")) {
			logger.info("Guest user");
			extent.extentLoggerPass("Login", "Guest user scenarios");
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			waitTime(15000);
			verifyIsElementDisplayed(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
			TVclick(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
			waitTime(3000);
			code = TVgetText(Zee5TvWelcomePage.objloginCode);
			logger.info("Authenticate code in TV : " + code);
			extentLoggerPass("Code", "Authenticate code in TV : " + code);
			HeaderChildNode("Switching to WEB platform to Authenticate device");
			setPlatform("Web");
			new Zee5TvBusinessLogic("zee");
			waitTime(15000);
			verifyElementPresentAndClick(PWALoginPage.objLanguageButton, "Language button");
			waitTime(3000);
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
			TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
			waitTime(10000);
		}
	}

	@SuppressWarnings("static-access")
	public void onboarding() throws Exception {
		HeaderChildNode("Skip login event through welcome screen");
		waitTime(10000);
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}

		mixpanel.FEProp.setProperty("Source", "Welcome Screen");
		mixpanel.FEProp.setProperty("Page Name", "Welcome Screen");
		mixpanel.ValidateParameter("", "Skip Login");

		waitTime(5000);
		getDriver().closeApp();
		waitTime(3000);
		getDriver().launchApp();
		waitTime(5000);
		HeaderChildNode("TV Authentication Screen Display event through welcome screen");
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(2000);
		TVclick(Zee5TvWelcomePage.objalreadyRegister, "Already Register button");
		waitTime(3000);

		mixpanel.FEProp.setProperty("Source", "Login and Authentication screen");
		mixpanel.FEProp.setProperty("Page Name", "Login and Authentication screen");
		mixpanel.ValidateParameter("", "TV Authentication Screen Display");

		getDriver().navigate().back();
		waitTime(3000);
		HeaderChildNode("Skip login event post returing from authentication screen through welcome page");
		TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");

		mixpanel.FEProp.setProperty("Source", "Welcome Screen");
		mixpanel.FEProp.setProperty("Page Name", "Welcome Screen");
		mixpanel.ValidateParameter("", "Skip Login");

		HeaderChildNode("TV Authentication Screen Display event through setting screen");
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(3000);

		mixpanel.FEProp.setProperty("Source", "Login and Authentication screen");
		mixpanel.FEProp.setProperty("Page Name", "Login and Authentication screen");
		mixpanel.ValidateParameter("", "TV Authentication Screen Display");

		code = TVgetText(Zee5TvWelcomePage.objloginCode);
		logger.info("Authenticate code in TV : " + code);
		extentLoggerPass("Code", "Authenticate code in TV : " + code);
		setPlatform("Web");
		new Zee5TvBusinessLogic("zee");
		waitTime(15000);
		verifyElementPresentAndClick(PWALoginPage.objLanguageButton, "Language button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");

		String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("NonsubscribedUserName");
		String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("NonsubscribedPassword");
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
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		HeaderChildNode("Login Result event post login");
		TVclick(Zee5TvWelcomePage.objcontinueButtonInLoginPage, "Continue button in TV authentication page");
		waitTime(15000);

		mixpanel.FEProp.setProperty("Source", "Login and Authentication screen");
//		mixpanel.FEProp.setProperty("Page Name", "Login and Authentication screen");
		mixpanel.ValidateParameter("", "Login Result");

		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		HeaderChildNode("Logout event");
		TVTabSelect("Settings");
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 15; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		TVclick(Zee5TvWelcomePage.objLogoutOption, "Logout option");

		waitTime(3000);

		mixpanel.FEProp.setProperty("Source", "settings");
		mixpanel.FEProp.setProperty("Page Name", "settings");
		mixpanel.ValidateParameter("", "Logout");
		TVTabSelect("Home");
		waitTime(3000);
		for (int i = 0; i <= 10; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		TVclick(Zee5TvWelcomePage.objLoginOption, "Login option");
		waitTime(3000);
		login(userType);
		if (userType.equals("NonSubscribedUser") || userType.equals("Guest")) {
			HeaderChildNode("Subscription page view event from carousel");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");

			if (verifyIsElementDisplayed(Zee5TVCarousel.objCarouselSubscribeButton, "Subscribe button")) {
				TVclick(Zee5TVCarousel.objCarouselSubscribeButton, "Subscribe button");
				waitTime(10000);

				mixpanel.FEProp.setProperty("Source", "Subscription Page");
				mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
				mixpanel.ValidateParameter("", "Subscription Page Viewed");

			}
			getDriver().navigate().back();
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			HeaderChildNode("Subscription page view event from search page");
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);
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
			List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele.size(); i++) {

				String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				logger.info(title);
				extent.extentLogger("Title", "Serach result content title : " + title);
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
						"Searched Premium movie"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
					break;
				} else {
					logger.info("No match");
				}

			}
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
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							logger.info(
									"Guest user is navigated to login screen post tapping on login butoon in login popup for premium content");
						}
						if (userType.equals("NonSubscribedUser")) {
							logger.info("Subscribe popup is displayed when user clicks on before tv contents");
							extent.extentLoggerPass("Page",
									"Subscribe popup is displayed when user clicks on before tv contents");
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

						}
					} else {
						TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
						waitTime(4000);
						if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
							if (userType.equals("Guest")) {
								logger.info("Login popup is displayed when Guest user clicks on before tv contents");
								extent.extentLoggerPass("Page",
										"Login popup is displayed when Guest user clicks on before tv contents");
								Runtime.getRuntime().exec("adb shell input keyevent 22");
								waitTime(2000);
								Runtime.getRuntime().exec("adb shell input keyevent 22");
								waitTime(2000);
								Runtime.getRuntime().exec("adb shell input keyevent 23");

								mixpanel.FEProp.setProperty("Source", "Subscription Page");
								mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
								mixpanel.ValidateParameter("", "Subscription Page Viewed");

							}
							if (userType.equals("NonSubscribedUser")) {
								logger.info("Subscribe popup is displayed when user clicks on before tv contents");
								extent.extentLoggerPass("Page",
										"Subscribe popup is displayed when user clicks on before tv contents");
								Runtime.getRuntime().exec("adb shell input keyevent 22");
								waitTime(2000);
								Runtime.getRuntime().exec("adb shell input keyevent 22");
								waitTime(2000);
								Runtime.getRuntime().exec("adb shell input keyevent 23");

								mixpanel.FEProp.setProperty("Source", "Subscription Page");
								mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
								mixpanel.ValidateParameter("", "Subscription Page Viewed");

								waitTime(5000);
								getDriver().navigate().back();
							}
						} else {
							logger.info("User is navigated to consumption page and before TV content is played");
							extent.extentLoggerPass("Player",
									"User is navigated to consumption page and before TV content is played");
						}
					}
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
					waitTime(3000);
				}
			}
			getDriver().navigate().back();
			waitTime(3000);
			for (int i = 0; i <= 15; i++) {
				Runtime.getRuntime().exec("adb shell input keyevent 19");
			}
			TVTabSelect("Home");

			for (int i = 0; i <= 10; i++) {
				Runtime.getRuntime().exec("adb shell input keyevent 22");
				waitTime(2000);
			}
			HeaderChildNode("Subscription Page Viewed from setting screen");
			TVTabSelect("Settings");
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 3; i++) {
				Runtime.getRuntime().exec("adb shell input keyevent 22");
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

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(3000);
			if (userType.equals("NonSubscribedUser")) {
				Runtime.getRuntime().exec("adb shell input keyevent 23");
			}
			waitTime(2000);

			mixpanel.FEProp.setProperty("Source", "Subscription Page");
			mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
			mixpanel.ValidateParameter("", "Subscription Page Viewed");
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
			HeaderChildNode("Subscription Page Viewed event from playback of premium content");
			String language = getLanguage(userType);
			Response resp = ResponseInstance.getResponseForPagesTv("Home", language, 1, userType);
			for (int i = 0; i <= 10; i++) {
				onboardingTraytitle = resp.jsonPath().getString("buckets[" + i + "].title");
				if (onboardingTraytitle.contains("Movies")) {
					logger.info("Tray title : " + onboardingTraytitle);
					for (int j = 0; j <= 20; j++) {
						String businessType = resp.jsonPath()
								.getString("buckets[" + i + "].items[" + j + "].business_type");
						if (businessType.equals("premium_downloadable")) {
							onboardingPremiumContenttitle = resp.jsonPath()
									.getString("buckets[" + i + "].items[" + j + "].title");
							logger.info("Premium Content name : " + onboardingPremiumContenttitle);
							break;
						}
					}
					break;
				}
			}
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(onboardingTraytitle), "Movies tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(onboardingTraytitle), "Movies tray");
					logger.info("Movies tray is displayed");
					extent.extentLoggerPass("Trending", "Movies tray is displayed");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(onboardingPremiumContenttitle),
						"Premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(onboardingPremiumContenttitle), "Premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(onboardingPremiumContenttitle), "Premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			if (userType.equals("Guest")) {
				logger.info("Login popup is displayed when Guest user clicks on before tv contents");
				extent.extentLoggerPass("Page",
						"Login popup is displayed when Guest user clicks on before tv contents");
				Runtime.getRuntime().exec("adb shell input keyevent 22");
				waitTime(2000);
				Runtime.getRuntime().exec("adb shell input keyevent 22");
				waitTime(2000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");

				mixpanel.FEProp.setProperty("Source", "Login and Authentication screen");
				mixpanel.FEProp.setProperty("Page Name", "Login and Authentication screen");
				mixpanel.ValidateParameter("", "TV Authentication Screen Display");

				logger.info(
						"Guest user is navigated to login screen post tapping on login butoon in login popup for premium content");
				getDriver().navigate().back();
			}
			if (userType.equals("NonSubscribedUser")) {
				logger.info("Subscribe popup is displayed when user clicks on before tv contents");
				extent.extentLoggerPass("Page", "Subscribe popup is displayed when user clicks on before tv contents");
				Runtime.getRuntime().exec("adb shell input keyevent 22");
				waitTime(2000);
				Runtime.getRuntime().exec("adb shell input keyevent 22");
				waitTime(2000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");

				mixpanel.FEProp.setProperty("Source", "Subscription Page");
				mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
				mixpanel.ValidateParameter("", "Subscription Page Viewed");

				waitTime(5000);
				getDriver().navigate().back();
			}
			waitTime(3000);
			HeaderChildNode(
					"Subscription Page Viewed event from post completion of trailer playback of premium content");
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				for (int i = 0; i <= 13; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
				}
				waitTime(5000);
				Runtime.getRuntime().exec("adb shell input keyevent 22");
				waitTime(2000);
				Runtime.getRuntime().exec("adb shell input keyevent 22");
				waitTime(2000);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(8000);

				mixpanel.FEProp.setProperty("Source", "Subscription Page");
				mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
				mixpanel.ValidateParameter("", "Subscription Page Viewed");

				for (int i = 0; i <= 3; i++) {
					getDriver().navigate().back();
					waitTime(2000);

				}
			}
		}
	}

	public static String getLanguage(String userType) {

		String language = null;
		if (userType.contains("Guest")) {
			language = "en,kn";
		} else {
			Response resplanguage = ResponseInstance.getUserinfoforNonSubORSub(userType);

			for (int i = 0; i < resplanguage.jsonPath().getList("array").size(); i++) {

				String key = resplanguage.jsonPath().getString("[" + i + "].key");

				if (key.contains("content_language")) {
					language = resplanguage.jsonPath().getString("[" + i + "].value");

					break;
				}
			}
		}

		return language;

	}

	@SuppressWarnings({ "static-access", "rawtypes" })
	public void journey() throws Exception {
		HeaderChildNode("Journey Splash screen Event");
		waitTime(10000);
		mixpanel.FEProp.setProperty("Source", "Splash Screen");
		mixpanel.FEProp.setProperty("Page Name", "Splash Screen");
		mixpanel.ValidateParameter("", "App Session");
		getDriver().closeApp();
		waitTime(3000);
		getDriver().launchApp();
		waitTime(8000);
		logger.info("Splash screen event post minimize and relaunch application");
		extent.extentLogger("Event", "Splash screen event post minimize and relaunch application");

		mixpanel.FEProp.setProperty("Source", "Splash Screen");
		mixpanel.FEProp.setProperty("Page Name", "Splash Screen");
		mixpanel.ValidateParameter("", "App Session");

		if (userType.equals("Guest")) {
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
		}
		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			login(userType);
		}
		waitTime(3000);
		TVTabSelect("Home");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		for (int i = 0; i <= 4; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		HeaderChildNode("Carousal Banner Swipe event");
		logger.info("Swiping carousel");
		extent.extentLogger("Swipe", "Swiping carousel");

		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "Home");
		mixpanel.ValidateParameter("", "Carousal Banner Swipe");

		TVclick(Zee5TvWelcomePage.objBannerTitle, "Carousel banner");
		for (int i = 0; i <= 4; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 21");
			waitTime(2000);
		}
		// carousel banner swipe check
		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "Home");
		mixpanel.ValidateParameter("", "Carousal Banner Swipe");

		HeaderChildNode("Carousal banner click event");

		verifyIsElementDisplayed(Zee5TVCarousel.objCarouselPlayButton, "Play button");
		TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
		waitTime(3000);
		// carousel banner click
		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "Home");
		mixpanel.ValidateParameter("", "Carousal Banner Click");

		getDriver().navigate().back();
		waitTime(3000);
		HeaderChildNode("View More Selected and thumbnail click in view all page event");
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objViewallTray, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objViewallTray, "Tray content");
				waitTime(3000);
				TVclick(Zee5TvWelcomePage.objViewallTray, "Tray content");
				waitTime(7000);
				getDriver().navigate().back();
				waitTime(3000);
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
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
				Runtime.getRuntime().exec("adb shell input keyevent 22");
			}

		}
		// View More Selected event
		waitTime(3000);
		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "Home");
		mixpanel.ValidateParameter("", "View More Selected");

		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(4000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(4000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(8000);
		// Thumbnail Click view all

		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "Home");
		mixpanel.ValidateParameter("", "Thumbnail Click");

		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		for (int i = 0; i <= 18; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
		}
		HeaderChildNode("Search Button click event");
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);

		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "Home");
		mixpanel.ValidateParameter("", "Search Button Click");
		HeaderChildNode("Search Executed event");
		String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
		type(searchdata1);
		waitTime(5000);
		String content = TVgetText(Zee5TvSearchPage.objEditbox);
		waitTime(3000);

		mixpanel.FEProp.setProperty("Source", "Search");
		mixpanel.FEProp.setProperty("Page Name", "Search");
		mixpanel.ValidateParameter("", "Search Executed");
		HeaderChildNode("Search Result clicked event");

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
		List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele.size(); i++) {

			String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info(title);
			extent.extentLogger("Title", "Serach result content title : " + title);
			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
					"Searched Premium movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
				break;
			} else {
				logger.info("No match");
			}

		}
		waitTime(8000);

		mixpanel.FEProp.setProperty("Source", "Search");
		mixpanel.FEProp.setProperty("Page Name", "Search");
		mixpanel.ValidateParameter("", "Search Result Clicked");

		HeaderChildNode("Player Recommendation Invocation and Thumbnail Click from player event");
		String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
		if (title.equals(title)) {
			logger.info("user is navigated to respective content detail page");
			extent.extentLoggerPass("user", "user is navigated to respective content detail page");
		}
		getDriver().navigate().back();
		waitTime(3000);
		int lenText = getDriver().findElement(Zee5TvSearchPage.objEditbox).getAttribute("text").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(Zee5TvSearchPage.objSearchBackButton).click();
		}
		String searchdata2[] = { "b", "a", "b", "l", "u", };
		String searchdata3[] = { "d", "a", "b", "l", "u" };
		String searchdata4[] = { "r", "o", "b", "o" };
		String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
		type(searchdata2);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata3);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata4);
		TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
		type(searchdata5);

		String content2 = TVgetText(Zee5TvSearchPage.objEditbox);

		logger.info("Entered Search Data : " + content2);
		extent.extentLogger("Search", "Entered Searched Data : " + content2);

		List<WebElement> ele2 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
		for (int i = 1; i <= ele2.size(); i++) {
			String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
			logger.info("Serach result content title : " + title2);
			extent.extentLogger("Title", "Serach result content title : " + title2);

			if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Movies"),
					"Searched Movie"))) {
				TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Movies"), "serached Movie");
				break;
			} else {
				System.out.println("No match");
			}

		}

		waitTime(8000);
		waitTime(2000);
		TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

		waitTime(10000);

		waitTime(5000);
		waitTime(5000);
		waitTime(5000);
		waitTime(5000);

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
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(4000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 22");
		if (getDriver().findElement(Zee5TvPlayerPage.objPlayerScreenUpNext).getAttribute("focused") != null) {
			Runtime.getRuntime().exec("adb shell input keyevent 23");
		} else {
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
		}
		waitTime(8000);

		if (verifyIsElementDisplayed(Zee5TvPlayerPage.objUpnextrail, "Up Next rail")) {
			logger.info("User can click on upnext button and up next rail is displayed");
			extent.extentLoggerPass("UpNext", "User can click on upnext button and up next rail is displayed");
		} else {
			logger.info("Upnext tab functionality failed");
			extent.extentLoggerFail("UpNext", "Upnext tab functionality failed");
		}

		mixpanel.FEProp.setProperty("Source", "Player");
		mixpanel.FEProp.setProperty("Page Name", "Player");
		mixpanel.ValidateParameter("", "Player Recommendation Invocation");

		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(3000);

		mixpanel.FEProp.setProperty("Source", "Player");
		mixpanel.FEProp.setProperty("Page Name", "Player");
		mixpanel.ValidateParameter("", "Thumbnail Click");

		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		HeaderChildNode("Thumbnail Click event");
		TVTabSelect("Home");

		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content")) {
				TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Tray content");
				waitTime(7000);
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Content Title")) {
					logger.info("User is navigated to content detail page");
					extent.extentLoggerPass("Page", "User is navigated to content detail page");
				} else {
					TVclick(Zee5TvWelcomePage.objHomepageTrayContent, "Movie page Tray content");
				}
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		waitTime(10000);

		mixpanel.FEProp.setProperty("Source", "Home");
		mixpanel.FEProp.setProperty("Page Name", "Home");
		mixpanel.ValidateParameter("", "Thumbnail Click");

		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);

		for (int i = 0; i <= 10; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		HeaderChildNode("Video Streaming Autoplay Changed and Video Streaming Quality Changed event");
		TVTabSelect("Settings");
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(2000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 3; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
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
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(4000);

		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");

		mixpanel.FEProp.setProperty("Source", "Video Quality Screen");
		mixpanel.FEProp.setProperty("Page Name", "Video Quality Screen");
		mixpanel.ValidateParameter("", "Video Streaming Autoplay Changed");

		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");

		mixpanel.FEProp.setProperty("Source", "Video Quality Screen");
		mixpanel.FEProp.setProperty("Page Name", "Video Quality Screen");
		mixpanel.ValidateParameter("", "Video Streaming Autoplay Changed");

		TVclick(Zee5TvWelcomePage.objVideoQualityResolutionOptions3, "Video option");
		waitTime(2000);
		TVclick(Zee5TvWelcomePage.objVideoQualityResolutionOptions2, "Video option");
		waitTime(2000);
		TVclick(Zee5TvWelcomePage.objVideoQualityResolutionOptions4, "Video option");
		waitTime(2000);
		TVclick(Zee5TvWelcomePage.objVideoQualityResolutionOptions5, "Video option");
		waitTime(2000);

		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");

		mixpanel.FEProp.setProperty("Source", "Video Quality Screen");
//		mixpanel.FEProp.setProperty("Page Name", "Video Quality Screen");
		mixpanel.ValidateParameter("", "Video Streaming Quality Changed");

		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		TVTabSelect("Home");
		for (int i = 0; i <= 6; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		HeaderChildNode("Filter Applied event");
		TVTabSelect("Live TV");
		waitTime(2000);
		TVclick(Zee5TvHomePage.objChannelFilterButton, "Channel filter");
		waitTime(2000);
		for (int i = 0; i <= 4; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
		}
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(2000);

		mixpanel.FEProp.setProperty("Source", "Live TV");
		mixpanel.FEProp.setProperty("Page Name", "Live TV");
		mixpanel.ValidateParameter("", "Filter Applied");

		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(2000);

		mixpanel.FEProp.setProperty("Source", "Live TV");
		mixpanel.FEProp.setProperty("Page Name", "Live TV");
		mixpanel.ValidateParameter("", "Filter Applied");

		Runtime.getRuntime().exec("adb shell input keyevent 22");
		waitTime(2000);
		TVclick(Zee5TvHomePage.objGenreInFilterPopup, "Genre option in filter popup");
		waitTime(3000);
		for (int i = 0; i <= 4; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
		}
		Runtime.getRuntime().exec("adb shell input keyevent 23");
		waitTime(6000);

		mixpanel.FEProp.setProperty("Source", "Live TV");
		mixpanel.FEProp.setProperty("Page Name", "Live TV");
		mixpanel.ValidateParameter("", "Filter Applied");

		Runtime.getRuntime().exec("adb shell input keyevent 23");

		mixpanel.FEProp.setProperty("Source", "Live TV");
		mixpanel.FEProp.setProperty("Page Name", "Live TV");
		mixpanel.ValidateParameter("", "Filter Applied");

		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		getDriver().navigate().back();
		waitTime(2000);
		HeaderChildNode("Popup CTA and Popup Launch event");
		TVTabSelect("Home");
		for (int i = 0; i <= 18; i++) {
			waitTime(3000);
			if (verifyIsElementDisplayed(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content")) {
				TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
				waitTime(7000);
				if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
					if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
						logger.info("popup is displayed when Guest/Nonsubscribe user clicks on before tv contents");
						extent.extentLoggerPass("Page",
								" popup is displayed when Guest/Nonsubscribe user clicks on before tv contents");

						mixpanel.FEProp.setProperty("Source", "Home");
						mixpanel.FEProp.setProperty("Page Name", "Home");
						mixpanel.ValidateParameter("", "Popup Launch");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Home");
						mixpanel.FEProp.setProperty("Page Name", "Home");
						mixpanel.ValidateParameter("", "Popup CTA");

					}

				} else {
					TVclick(Zee5TvWelcomePage.objBeforeTVTray, "BeforeTv Tray content");
					waitTime(4000);
					if (verifyIsElementDisplayed(Zee5TVCarousel.objSubscribePopUptitle, "Subscribe popup")) {
						if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
							logger.info("popup is displayed when Guest/Nonsubscribe user clicks on before tv contents");
							extent.extentLoggerPass("Page",
									" popup is displayed when Guest/Nonsubscribe user clicks on before tv contents");

							mixpanel.FEProp.setProperty("Source", "Home");
							mixpanel.FEProp.setProperty("Page Name", "Home");
							mixpanel.ValidateParameter("", "Popup Launch");

							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Home");
							mixpanel.FEProp.setProperty("Page Name", "Home");
							mixpanel.ValidateParameter("", "Popup CTA");

						}
					} else {
						logger.info("User is navigated to consumption page and before TV content is played");
						extent.extentLoggerPass("Player",
								"User is navigated to consumption page and before TV content is played");
					}
				}
				break;
			} else {
				Runtime.getRuntime().exec("adb shell input keyevent 20");
				waitTime(3000);
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);

		TVTabSelect("Home");

		waitTime(3000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			String language = getLanguage(userType);
			Response resp = ResponseInstance.getResponseForPagesTv("Home", language, 1, userType);
			for (int i = 0; i <= 10; i++) {
				onboardingTraytitle = resp.jsonPath().getString("buckets[" + i + "].title");
				if (onboardingTraytitle.contains("Movies")) {
					logger.info("Tray title : " + onboardingTraytitle);
					for (int j = 0; j <= 20; j++) {
						String businessType = resp.jsonPath()
								.getString("buckets[" + i + "].items[" + j + "].business_type");
						if (businessType.equals("premium_downloadable")) {
							onboardingPremiumContenttitle = resp.jsonPath()
									.getString("buckets[" + i + "].items[" + j + "].title");
							logger.info("Premium Content name : " + onboardingPremiumContenttitle);
							break;
						}
					}
					break;
				}
			}
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(onboardingTraytitle), "Movies tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(onboardingTraytitle), "Movies tray");
					logger.info("Movies tray is displayed");
					extent.extentLoggerPass("Trending", "Movies tray is displayed");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(onboardingPremiumContenttitle),
						"Premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(onboardingPremiumContenttitle), "Premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(onboardingPremiumContenttitle), "Premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			if (userType.equals("Guest")) {
				logger.info("Login popup is displayed when Guest user clicks on before tv contents");
				extent.extentLoggerPass("Page",
						"Login popup is displayed when Guest user clicks on before tv contents");

				mixpanel.FEProp.setProperty("Source", "Details Screen");
				mixpanel.FEProp.setProperty("Page Name", "Details Screen");
				mixpanel.ValidateParameter("", "Popup Launch");

			}
			if (userType.equals("NonSubscribedUser")) {
				logger.info("Subscribe popup is displayed when user clicks on before tv contents");
				extent.extentLoggerPass("Page", "Subscribe popup is displayed when user clicks on before tv contents");

				mixpanel.FEProp.setProperty("Source", "Details Screen");
				mixpanel.FEProp.setProperty("Page Name", "Details Screen");
				mixpanel.ValidateParameter("", "Popup Launch");
			}
		}
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);
		HeaderChildNode("Screen View Event");
		TVTabSelect("Home");
		waitTime(2000);
		TVTabSelect("Shows");

		mixpanel.FEProp.setProperty("Source", "Shows");
		mixpanel.FEProp.setProperty("Page Name", "Shows");
		mixpanel.ValidateParameter("", "Screen View");

		waitTime(2000);
		HeaderChildNode("Display Language Change and Content Language Change Event");
		TVTabSelect("Home");
		for (int i = 0; i <= 10; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		TVTabSelect("Settings");
		Runtime.getRuntime().exec("adb shell input keyevent 20");
		for (int i = 0; i <= 9; i++) {
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
		}
		TVclick(Zee5TvHomePage.objlanguageButton, "Language option");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");
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

		mixpanel.FEProp.setProperty("Source", "Display Language Page");
		mixpanel.FEProp.setProperty("Page Name", "Display Language Page");
		mixpanel.ValidateParameter("", "Display Language Change");

		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(3000);

		Runtime.getRuntime().exec("adb shell input keyevent 23");
		TVclick(Zee5TvHomePage.objContentlanguageButton, "Content language button");
		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 19");
		waitTime(3000);

		Runtime.getRuntime().exec("adb shell input keyevent 23");

		waitTime(3000);
		Runtime.getRuntime().exec("adb shell input keyevent 23");

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

		mixpanel.FEProp.setProperty("Source", "Content Language Page");
		mixpanel.FEProp.setProperty("Page Name", "Content Language Page");
		mixpanel.ValidateParameter("", "Content Language Change");

		getDriver().navigate().back();
		waitTime(3000);
		getDriver().navigate().back();
		waitTime(3000);

		getDriver().quit();
		relaunch = true;
		new Zee5TvMixPanelBusinessLogic("zeeTV");
		waitTime(150000);
		HeaderChildNode("Display Language Change and Content Language Change Event from Onboarding screen");
		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objChooseLanguagePopup, "Choose display language popup")) {
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 19");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);

			mixpanel.FEProp.setProperty("Source", "Onboarding Display Language Screen");
			mixpanel.FEProp.setProperty("Page Name", "Onboarding Display Language Screen");
			mixpanel.ValidateParameter("", "Display Language Change");

			waitTime(4000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 22");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(3000);

			mixpanel.FEProp.setProperty("Source", "Onboarding Content Language Screen");
			mixpanel.FEProp.setProperty("Page Name", "Onboarding Content Language Screen");
			mixpanel.ValidateParameter("", "Content Language Change");

		} else {
			logger.info("Choose language popup not displayed");
			extent.extentLoggerPass("Popup", "Choose language popup not displayed");
		}

		getDriver().quit();
		relaunch = true;
		new Zee5TvMixPanelBusinessLogic("zeeTV");
		waitTime(150000);
		HeaderChildNode("First Launch Event");
		mixpanel.FEProp.setProperty("Source", "Splash Screen");
		mixpanel.FEProp.setProperty("Page Name", "Splash Screen");
		mixpanel.ValidateParameter("", "First Launch");

		if (verifyIsElementDisplayed(Zee5TvWelcomePage.objChooseLanguagePopup, "Choose display language popup")) {
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objChooseLanguagePopupContinue, "Continue button");
			waitTime(4000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			TVclick(Zee5TvWelcomePage.objChooseLanguagePopupContinue, "Continue button");
			waitTime(3000);

		} else {
			logger.info("Choose language popup not displayed");
			extent.extentLoggerPass("Popup", "Choose language popup not displayed");
		}

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			login(userType);
		}

	}

	@SuppressWarnings("static-access")
	public void videoViewEvent(String entryPoint, String tabName, String searchContent) throws Exception {

		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			AdVerify();

			waitTime(10000);
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

			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(2000);
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video View");

			getDriver().navigate().back();
			waitTime(3000);
			getDriver().navigate().back();
			waitTime(3000);
			getDriver().navigate().back();
			waitTime(3000);
			for (int i = 0; i <= 10; i++) {
				Runtime.getRuntime().exec("adb shell input keyevent 19");
				waitTime(2000);
			}
			for (int i = 0; i <= 10; i++) {
				Runtime.getRuntime().exec("adb shell input keyevent 21");
				waitTime(2000);
			}
			TVTabSelect("Home");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(10000);
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

				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(2000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
				
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 19");
					waitTime(2000);
				}
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 21");
					waitTime(2000);
				}
				TVTabSelect("Home");
				
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);
			Runtime.getRuntime().exec("adb shell input keyevent 23");
			waitTime(2000);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video View");
			getDriver().navigate().back();
			waitTime(3000);
			getDriver().navigate().back();
			waitTime(3000);
			getDriver().navigate().back();
			waitTime(3000);
			for (int i = 0; i <= 10; i++) {
				Runtime.getRuntime().exec("adb shell input keyevent 19");
				waitTime(2000);
			}
			for (int i = 0; i <= 10; i++) {
				Runtime.getRuntime().exec("adb shell input keyevent 21");
				waitTime(2000);
			}
			TVTabSelect("Home");
			

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(2000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video View");
						getDriver().navigate().back();
						waitTime(3000);
						getDriver().navigate().back();
						waitTime(3000);
						getDriver().navigate().back();
						waitTime(3000);
						for (int l = 0; l <= 10; l++) {
							Runtime.getRuntime().exec("adb shell input keyevent 19");
							waitTime(2000);
						}
						for (int m = 0; m <= 10; m++) {
							Runtime.getRuntime().exec("adb shell input keyevent 21");
							waitTime(2000);
						}
						TVTabSelect("Home");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(10000);
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

				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(2000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
				
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 19");
					waitTime(2000);
				}
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 21");
					waitTime(2000);
				}
				TVTabSelect("Home");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(10000);
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

					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(2000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
					
					getDriver().navigate().back();
					waitTime(3000);
					getDriver().navigate().back();
					waitTime(3000);
					getDriver().navigate().back();
					waitTime(3000);
					getDriver().navigate().back();
					waitTime(3000);
					for (int i = 0; i <= 10; i++) {
						Runtime.getRuntime().exec("adb shell input keyevent 19");
						waitTime(2000);
					}
					for (int i = 0; i <= 10; i++) {
						Runtime.getRuntime().exec("adb shell input keyevent 21");
						waitTime(2000);
					}
					TVTabSelect("Home");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(2000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
				
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 19");
					waitTime(2000);
				}
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 21");
					waitTime(2000);
				}
				TVTabSelect("Home");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(2000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
				
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 19");
					waitTime(2000);
				}
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 21");
					waitTime(2000);
				}
				TVTabSelect("Home");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(10000);
				AdVerify();
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				waitTime(2000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				getDriver().navigate().back();
				waitTime(3000);
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 19");
					waitTime(2000);
				}
				for (int i = 0; i <= 10; i++) {
					Runtime.getRuntime().exec("adb shell input keyevent 21");
					waitTime(2000);
				}
				TVTabSelect("Home");
				
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void videoExitEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			AdVerify();
			waitTime(5000);

			getDriver().navigate().back();
			waitTime(3000);
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video Exit");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);

				getDriver().navigate().back();
				waitTime(3000);

				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Exit");
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);

			getDriver().navigate().back();
			waitTime(3000);

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video Exit");

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						getDriver().navigate().back();
						waitTime(3000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video Exit");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				getDriver().navigate().back();
				waitTime(3000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Exit");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);

					getDriver().navigate().back();
					waitTime(3000);

					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video Exit");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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
				getDriver().navigate().back();
				waitTime(3000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Exit");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);
				getDriver().navigate().back();
				waitTime(3000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Exit");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				getDriver().navigate().back();
				waitTime(3000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Exit");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void videoWatchedDuration(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video Watch Duration");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Watch Duration");
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video Watch Duration");

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						waitTime(5000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video Watch Duration");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Watch Duration");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video Watch Duration");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Watch Duration");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Watch Duration");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video Watch Duration");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void adInitializedEvent(String entryPoint, String tabName, String searchContent) throws Exception {

		if (userType.equals("Guest")) {
			verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		TVTabSelect("Home");
		waitTime(2000);
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);

		if (entryPoint.equals("Movie")) {
			String searchdata2[] = { "b", "a", "b", "l", "u", };
			String searchdata3[] = { "d", "a", "b", "l", "u" };
			String searchdata4[] = { "r", "o", "b", "o" };
			String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata5);

			String content = "BabluDabluRoboRumble";

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

			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad Initialized");
		}

		if (entryPoint.equals("episode")) {
			String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
			String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
			String searchpromo2[] = { "t", "o" };
			String searchpromo3[] = { "p", "a", "a", "r", "u" };

			type(searchpromo);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo3);
			waitTime(10000);
			String content = "adityaapologisestopaaru";

			List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele3.size(); i++) {
				String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
						"Searched Episodes"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"), "serached Episodes");
					break;
				} else {
					System.out.println("No match");
				}

			}

			waitTime(20000);
			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad Initialized");
		}

	}

	@SuppressWarnings("static-access")
	public void adIViewEvent(String entryPoint, String tabName, String searchContent) throws Exception {

		if (userType.equals("Guest")) {
			verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		TVTabSelect("Home");
		waitTime(2000);
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);

		if (entryPoint.equals("Movie")) {
			String searchdata2[] = { "b", "a", "b", "l", "u", };
			String searchdata3[] = { "d", "a", "b", "l", "u" };
			String searchdata4[] = { "r", "o", "b", "o" };
			String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata5);

			String content = "BabluDabluRoboRumble";

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

			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad View");
		}

		if (entryPoint.equals("episode")) {
			String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
			String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
			String searchpromo2[] = { "t", "o" };
			String searchpromo3[] = { "p", "a", "a", "r", "u" };

			type(searchpromo);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo3);
			waitTime(10000);
			String content = "adityaapologisestopaaru";

			List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele3.size(); i++) {
				String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
						"Searched Episodes"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"), "serached Episodes");
					break;
				} else {
					System.out.println("No match");
				}

			}

			waitTime(20000);
			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad View");
		}

	}

	@SuppressWarnings("static-access")
	public void adWatchDurationEvent(String entryPoint, String tabName, String searchContent) throws Exception {

		if (userType.equals("Guest")) {
			verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		TVTabSelect("Home");
		waitTime(2000);
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);

		if (entryPoint.equals("Movie")) {
			String searchdata2[] = { "b", "a", "b", "l", "u", };
			String searchdata3[] = { "d", "a", "b", "l", "u" };
			String searchdata4[] = { "r", "o", "b", "o" };
			String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata5);

			String content = "BabluDabluRoboRumble";

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

			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad Watch Duration");
		}

		if (entryPoint.equals("episode")) {
			String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
			String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
			String searchpromo2[] = { "t", "o" };
			String searchpromo3[] = { "p", "a", "a", "r", "u" };

			type(searchpromo);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo3);
			waitTime(10000);
			String content = "adityaapologisestopaaru";

			List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele3.size(); i++) {
				String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
						"Searched Episodes"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"), "serached Episodes");
					break;
				} else {
					System.out.println("No match");
				}

			}

			waitTime(20000);
			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad Watch Duration");
		}

	}

	@SuppressWarnings("static-access")
	public void adForcedExitEvent(String entryPoint, String tabName, String searchContent) throws Exception {

		if (userType.equals("Guest")) {
			verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
			TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
			extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
		} else {
			logger.info("User is logged in");
			extent.extentLoggerPass("Button", "User is logged in");
		}
		TVTabSelect("Home");
		waitTime(2000);
		if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		} else {

			TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
		}
		waitTime(5000);

		if (entryPoint.equals("Movie")) {
			String searchdata2[] = { "b", "a", "b", "l", "u", };
			String searchdata3[] = { "d", "a", "b", "l", "u" };
			String searchdata4[] = { "r", "o", "b", "o" };
			String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
			type(searchdata2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata3);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata4);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
			type(searchdata5);

			String content = "BabluDabluRoboRumble";

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

			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad Forced Exit");
		}

		if (entryPoint.equals("episode")) {
			String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
			String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
			String searchpromo2[] = { "t", "o" };
			String searchpromo3[] = { "p", "a", "a", "r", "u" };

			type(searchpromo);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo1);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo2);
			TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
			type(searchpromo3);
			waitTime(10000);
			String content = "adityaapologisestopaaru";

			List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
			for (int i = 1; i <= ele3.size(); i++) {
				String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
				if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
						"Searched Episodes"))) {
					TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"), "serached Episodes");
					break;
				} else {
					System.out.println("No match");
				}

			}

			waitTime(20000);
			ResponseInstance.getSearchresponseTv(content);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Ad Forced Exit");
		}

	}

	@SuppressWarnings("static-access")
	public void resumeEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						waitTime(5000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video View");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void pauseEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						waitTime(5000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video View");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void qualityChnageEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						waitTime(5000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video View");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void audioLanguageChnageEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void subtitleLanguageChnageEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void skipIntroEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void autoseekEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						waitTime(5000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video View");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	@SuppressWarnings("static-access")
	public void playerCTAEvent(String entryPoint, String tabName, String searchContent) throws Exception {
		if (entryPoint.equals("Free")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "Free");
			TVTabSelect("Home");
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"Free content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "Free content tray");
					logger.info("Free content tray");
					extent.extentLoggerPass("Trending", "Free content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"Free content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "Free content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");

			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("premium")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("trailer")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "trailer");
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"trailer content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "trailer content tray");
					logger.info("trailer content tray");
					extent.extentLoggerPass("trailer", "trailer content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"trailer content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "trailer content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			waitTime(5000);
			if (verifyIsElementDisplayed(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button")) {
				waitTime(5000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objwatchTrailerIcon, "watch trailer button");
				waitTime(2000);
			}
			waitTime(10000);
			mixpanel.FEProp.setProperty("Source", "Player");
			mixpanel.FEProp.setProperty("Page Name", "Player");
			mixpanel.FEProp.setProperty("Player Name", "Kaltura");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			mixpanel.ValidateParameter("", "Video View");

		} else if (entryPoint.equals("carousel")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "carousel");
			ResponseInstance.getContentDetails(contentTitle.get(2));

			Runtime.getRuntime().exec("adb shell input keyevent 20");
			for (int i = 0; i <= 10; i++) {
				if (verifyIsElementDisplayed(Zee5TvHomePage.objCarouselBannerTitle(contentTitle.get(0)),
						"Carousel content")) {

					TVclick(Zee5TVCarousel.objCarouselPlayButton, "Play button");
					waitTime(5000);
					ResponseInstance.getContentDetails(contentTitle.get(2));
					waitTime(4000);
					TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
					waitTime(5000);
					if (userType.equals("Guest")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
							logger.info("Login popup is displayed when user play premium content as guest user");
							extent.extentLoggerPass("Popup",
									"Login popup is displayed when user play premium content as guest user");
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
					if (userType.equals("NonSubscribedUser")) {
						if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
							logger.info(
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							extent.extentLoggerPass("Popup",
									"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
							waitTime(5000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 22");
							waitTime(2000);
							Runtime.getRuntime().exec("adb shell input keyevent 23");
							waitTime(5000);

							mixpanel.FEProp.setProperty("Source", "Subscription Page");
							mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
							mixpanel.ValidateParameter("", "Subscription Page Viewed");

							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
							getDriver().navigate().back();
							waitTime(2000);
						}
					}
					if (userType.equals("SubscribedUser")) {

						waitTime(10000);
						waitTime(5000);
						mixpanel.FEProp.setProperty("Source", "Player");
						mixpanel.FEProp.setProperty("Page Name", "Player");
						mixpanel.FEProp.setProperty("Player Name", "Kaltura");

						mixpanel.ValidateParameter("", "Video View");
					}

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}
			}

		} else if (entryPoint.equals("ContentTray"))

		{
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv(tabName, "premium");
			ResponseInstance.getContentDetails(contentTitle.get(2));
			TVTabSelect(tabName);
			waitTime(3000);
			Runtime.getRuntime().exec("adb shell input keyevent 20");
			waitTime(2000);
			for (int i = 0; i <= 15; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)),
						"premium content tray")) {
					TVclick(Zee5TvWelcomePage.objApiContent(contentTitle.get(0)), "premium content tray");
					logger.info("premium content tray");
					extent.extentLoggerPass("Trending", "premium content tray");
					waitTime(2000);
					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 20");
				}
			}
			for (int i = 0; i <= 22; i++) {
				waitTime(3000);
				if (verifyIsElementDisplayed(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)),
						"premium content")) {
					TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					waitTime(5000);
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objPlayIcon, "Play Icon")) {
						logger.info("User is navigated to content detail page");
						extent.extentLoggerPass("Page", "User is navigated to content detail page");
					} else {
						TVclick(Zee5TvWelcomePage.objContentfromApi(contentTitle.get(1)), "premium content");
					}
					waitTime(7000);

					break;
				} else {
					Runtime.getRuntime().exec("adb shell input keyevent 22");
				}

			}
			TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");

			if (userType.equals("Guest")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
					logger.info("Login popup is displayed when user play premium content as guest user");
					extent.extentLoggerPass("Popup",
							"Login popup is displayed when user play premium content as guest user");
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
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
			if (userType.equals("NonSubscribedUser")) {
				if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
					logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					extent.extentLoggerPass("Popup",
							"Subscribe popup is displayed when user play premium content as Nonsubscribe user");
					waitTime(5000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 22");
					waitTime(2000);
					Runtime.getRuntime().exec("adb shell input keyevent 23");
					waitTime(5000);

					mixpanel.FEProp.setProperty("Source", "Subscription Page");
					mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
					mixpanel.ValidateParameter("", "Subscription Page Viewed");

					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
					getDriver().navigate().back();
					waitTime(2000);
				}
			}
			if (userType.equals("SubscribedUser")) {
				waitTime(5000);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else if (entryPoint.equals("Search")) {
			if (userType.equals("Guest")) {
				verifyIsElementDisplayed(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip Link");
				TVclick(Zee5TvWelcomePage.objWelcomeSkipLink, "Skip link");
				extent.extentLoggerPass("Clicked on Skip Link", "Clicked on Skip Link");
			} else {
				logger.info("User is logged in");
				extent.extentLoggerPass("Button", "User is logged in");
			}
			TVTabSelect(tabName);
			waitTime(2000);
			if (TVgetAttributValue("focused", Zee5TvHomePage.objSearchIcon).equals("false")) {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			} else {

				TVclick(Zee5TvHomePage.objSearchIcon, "Search Icon");
			}
			waitTime(5000);

			if (searchContent.equals("premium")) {
				String searchdata1[] = { "p", "a", "n", "c", "h", "a", "t", "a", "n", "t", "r", "a" };
				type(searchdata1);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				TVclick(Zee5TvSearchPage.objSearchedText, "Searched suggestion");
				waitTime(2000);
				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					String title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"),
							"Searched Premium movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Movies"), "Premium movie");
						break;
					} else {
						logger.info("No match");
					}

				}
				waitTime(8000);
				String title = TVgetText(Zee5TvSearchPage.objSearchedDataTitle);
				if (title.equals(title)) {
					logger.info("user is navigated to respective content detail page");
					extent.extentLoggerPass("user", "user is navigated to respective content detail page");
				}
				String content = "Panchatantra";
				ResponseInstance.getSearchresponseTv(content);
				TVclick(Zee5TvSearchPage.objPlayIcon, "Play Icon");
				waitTime(5000);
				if (userType.equals("Guest")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objLoginPopup, "Login popup")) {
						logger.info("Login popup is displayed when user play premium content as guest user");
						extent.extentLoggerPass("Popup",
								"Login popup is displayed when user play premium content as guest user");
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
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
				if (userType.equals("NonSubscribedUser")) {
					if (verifyIsElementDisplayed(Zee5TvSearchPage.objSubscribePopup, "Subscribe now popup")) {
						logger.info("Subscribe popup is displayed when user play premium content as Nonsubscribe user");
						extent.extentLoggerPass("Popup",
								"Subscribe popup is displayed when user play premium content as Nonsubscribe user");

						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 22");
						waitTime(2000);
						Runtime.getRuntime().exec("adb shell input keyevent 23");
						waitTime(5000);

						mixpanel.FEProp.setProperty("Source", "Subscription Page");
						mixpanel.FEProp.setProperty("Page Name", "Subscription Page");
						mixpanel.ValidateParameter("", "Subscription Page Viewed");

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
				if (userType.equals("SubscribedUser")) {
					waitTime(5000);
					mixpanel.FEProp.setProperty("Source", "Player");
					mixpanel.FEProp.setProperty("Page Name", "Player");
					mixpanel.FEProp.setProperty("Player Name", "Kaltura");

					mixpanel.ValidateParameter("", "Video View");
				}

			}

			if (searchContent.equals("Free")) {
				String searchdata2[] = { "b", "a", "b", "l", "u", };
				String searchdata3[] = { "d", "a", "b", "l", "u" };
				String searchdata4[] = { "r", "o", "b", "o" };
				String searchdata5[] = { "r", "u", "m", "b", "l", "e" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);

				String content = "BabluDabluRoboRumble";

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

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("trailer")) {

				String searchdata2[] = { "n", "a", "a", "n", "u", };
				String searchdata3[] = { "m", "a", "t", "t", "h", "u" };
				String searchdata4[] = { "g", "u", "n", "d", "a" };
				String searchdata5[] = { "t", "r", "a", "i", "l", "e", "r" };
				type(searchdata2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata3);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata4);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space");
				type(searchdata5);
				String content = "NaanuMatthuGundaTrailer";

				List<WebElement> ele = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele.size(); i++) {

					title = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					logger.info(title);
					extent.extentLogger("Title", "Serach result content title : " + title);
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"),
							"Searched Movie"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title, "Trailer"), "serached Trailer");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(8000);

				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}
			if (searchContent.equals("episode")) {
				String searchpromo[] = { "a", "d", "i", "t", "y", "a" };
				String searchpromo1[] = { "a", "p", "o", "l", "o", "g", "i", "s", "e", "s" };
				String searchpromo2[] = { "t", "o" };
				String searchpromo3[] = { "p", "a", "a", "r", "u" };

				type(searchpromo);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo1);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo2);
				TVclick(Zee5TvSearchPage.objSearchSpaceBar, "Space bar");
				type(searchpromo3);
				waitTime(10000);
				String content = "adityaapologisestopaaru";

				List<WebElement> ele3 = getDriver().findElements(By.xpath("//*[@id='search_result_title']"));
				for (int i = 1; i <= ele3.size(); i++) {
					String title2 = TVgetText(Zee5TvSearchPage.objSearchedTumbnailTitle(i));
					if ((verifyIsElementDisplayed(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
							"Searched Episodes"))) {
						TVclick(Zee5TvSearchPage.objSearchedSpecificTumbnailcard(title2, "Episodes"),
								"serached Episodes");
						break;
					} else {
						System.out.println("No match");
					}

				}

				waitTime(20000);
				ResponseInstance.getSearchresponseTv(content);
				mixpanel.FEProp.setProperty("Source", "Player");
				mixpanel.FEProp.setProperty("Page Name", "Player");
				mixpanel.FEProp.setProperty("Player Name", "Kaltura");

				mixpanel.ValidateParameter("", "Video View");
			}

		} else {
			logger.info("Invalid Entry Point");
		}
	}

	public static void main(String[] args) {
		String title = "Naanu Matthu Gunda";
		String str = title.replaceAll("\\s", "");
		System.out.println(str);
		ResponseInstance.getSearchresponseTv(str);

//		ArrayList<String> contentTitle = ResponseInstance.getTrayResponseTv("Shows", "carousel");
//		System.out.println(contentTitle.get(0));
//		System.out.println(contentTitle.get(1));
//		System.out.println(contentTitle.get(2));
//		String id = contentTitle.get(2);
//		ResponseInstance.getContentDetails(id);
//		ResponseInstance.getWatchList("autotv@mailnesia.com", "123456");

	}
}
