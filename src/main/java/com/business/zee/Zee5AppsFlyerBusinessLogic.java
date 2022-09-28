package com.business.zee;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.appsflyerValidation.AppsFlyer;
import com.driverInstance.CommandBase;
import com.driverInstance.DriverInstance;
import com.extent.ExtentReporter;
import com.metadata.ResponseInstance;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Zee5AppsFlyerBusinessLogic extends Utilities{
	
	
	public Zee5AppsFlyerBusinessLogic(String Application) {
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
	
	private String appPackage;
	
	private PropertyFileReader handler;
	
	protected PropertyFileReader getHandler() {
		return handler;
	}

	protected void setHandler(PropertyFileReader handler) {
		this.handler = handler;
	}

	protected void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	protected String getAppPackage() {
		return this.appPackage;
	}
	
	static AppsFlyer appsFlyerObj = new AppsFlyer();
	
	String idNumber;
	
	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
	//	logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
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
		new Zee5AppsFlyerBusinessLogic("zee");
	}
	
	
	
	/**
	 * To Remove the permission of an application
	 * 
	 * @param packagename
	 */
	public void clearAppData(String application) {
		waitTime(15000);
		logger.info("****Clearing the App Data****");
		extent.HeaderChildNode("ClearAppData");
		
		setHandler(new PropertyFileReader("properties/AppPackageActivity.properties"));
		setAppPackage(getHandler().getproperty(application + "Package"));
		
		String packagename = getAppPackage();
		
		String cmd2 = "adb shell pm clear " + packagename;
		try {
			Runtime.getRuntime().exec(cmd2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();
	}
	
	
	
	public void random(){
		generateRandomString(5);
	}

	
	
	
	public void triggerAppsflyerLink(String application) {
		waitTime(15000);
		logger.info("****triggering AppsflyerLink****");
		extent.HeaderChildNode("triggering AppsflyerLink");
		setHandler(new PropertyFileReader("properties/AppPackageActivity.properties"));
		setAppPackage(getHandler().getproperty(application + "Package"));
		
		idNumber = RandomIntegerGenerator(8);
		System.out.println("pid : Basapplicastertest"+idNumber);
		String packagename = getAppPackage();
		
		String cmd = "adb shell am start -W -a android.intent.action.VIEW -d https://app.appsflyer.com/"+packagename+"?pid=Basapplicastertest"+idNumber+"&c=Bastesting"+idNumber+"&advertising_id="+ResponseInstance.AdvertiseId;
		extent.extentLogger("","AppsFlyerLink to capture events : "+cmd);
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void launchapp() throws Exception{
		extent.HeaderChildNode("launch Zee5 through playstore");
		waitTime(15000);
		
		if(verifyIsElementDisplayed(AMDHomePage.objPlaystoreZeeText, "Zee5 app screen in playstore")){
			extent.extentLoggerPass("", "Zee5 app screen in playstore is displayed");
			
			if(verifyIsElementDisplayed(AMDHomePage.objOpenButton, "Open button")){
				verifyElementPresentAndClick(AMDHomePage.objOpenButton, "Open button");
			}else{
				System.out.println("Open Button is not displayed");
			}
			
		}else{
			extent.extentLoggerFail("", "Zee5 app screen in playstore is not displayed");			
		}
		waitTime(15000);
	}
	
	
	@SuppressWarnings("deprecation")
	public void downloadAppsFlyerReport() throws InterruptedException{
		
		appsFlyerObj.deleteOldAppsFlyerFiles();
		appsFlyerObj.deleteOldAppsFlyerEventExcelFiles();
		waitTime(15000);
		
      // Setting chrome driver path
//    System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
// 	WebDriverManager.chromedriver().version(DriverInstance.getDriverVersion()).setup();
		WebDriverManager.chromedriver().cachePath("Drivers").setup();
    // Setting new download directory path
    Map<String, Object> prefs = new HashMap<String, Object>();
    // Use File.separator as it will work on any OS
    prefs.put("download.default_directory",
            System.getProperty("user.dir") + File.separator + "AppsflyerReport");
    // Adding cpabilities to ChromeOptions
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", prefs);
    // Printing set download directory
    System.out.println(options.getExperimentalOption("prefs"));
    // Launching browser with desired capabilities
    ChromeDriver driver= new ChromeDriver(options);
    // URL loading
    Date date = new Date();
	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	System.out.println(modifiedDate);
    driver.get("https://hq.appsflyer.com/export/com.graymatrix.did/in_app_events_report/v5?api_token=8d127012-8a4b-4776-b42b-df2a787af8a0&from="+modifiedDate+"&to="+modifiedDate+"&timezone=Asia%2fKolkata&media_source=basapplicastertest"+idNumber+"&category=basapplicastertest"+idNumber+"&maximum_rows=100");
    // Click on download selenium server jar file
    Thread.sleep(60000);
    driver.quit();	
	}
	
	
	
//	public static void main(String[] args){
//		
//		AppsFlyer.convertCsvToXlsxReport(AppsFlyer.fetchTheDownloadedAppsFlyerReportName());
//		
//	}
	
	

	
	
	@SuppressWarnings("static-access")
	public void extractFilesForValidation(String userType) throws IOException, ParseException{
		waitTime(15000);
		
		appsFlyerObj.convertCsvToXlsxReport(appsFlyerObj.fetchTheDownloadedAppsFlyerReportName());
		System.out.println(idNumber);
		
		//NAVIGATION EVENTS
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Tab_View");

		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"screen_view");

		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Homepage_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"TVshowssection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Moviesection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Originalsection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Newssection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Premiumsection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"LIVETVsection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Musicsection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Eduauraasection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Upcomingsection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Downloadsection_visited");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Moresection_visitied");
		
		//CONSUMPTION EVENTS
//		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"TVShows_Content_Play");
//		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_svod_first_episode_free");
//		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"video_view_50_percent");
//		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"video_view_85_percent");
//		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"videos_viewed_is_20");
//		
//		if(userType.contains("Guest")){
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"consumption_subscribe_cta_click");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"landing_on_subscriber_plans_screen");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_add_to_cart");			
//		}else if(userType.contains("NonSubscribedUser")){
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"consumption_subscribe_cta_click");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"landing_on_subscriber_plans_screen");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_add_to_cart");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"landing_on_payment_screen");			
//		}
//		
//		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Videos_Content_Play");
//		
//		
//		if(userType.contains("Guest")){
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_complete_registration");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"content_lang_done");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"display_lang_done");
//			
//		}else{
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"content_lang_done");
//			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"display_lang_done");
//			
//		}
		
		
		
	}
	
	
	
	@SuppressWarnings("static-access")
	public void extractFilesForValidationConsumption(String userType) throws IOException, ParseException{
		waitTime(15000);
		
		appsFlyerObj.convertCsvToXlsxReport(appsFlyerObj.fetchTheDownloadedAppsFlyerReportName());
		System.out.println(idNumber);
		
	
		//CONSUMPTION EVENTS
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"TVShows_Content_Play");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_svod_first_episode_free");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"video_view_50_percent");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"video_view_85_percent");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"videos_viewed_is_20");
		appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"Videos_Content_Play");
		
		if(userType.contains("Guest")){
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"consumption_subscribe_cta_click");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"landing_on_subscriber_plans_screen");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_add_to_cart");			
		}else if(userType.contains("NonSubscribedUser")){
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"consumption_subscribe_cta_click");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"landing_on_subscriber_plans_screen");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_add_to_cart");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"landing_on_payment_screen");			
		}
		
		
		
		
	}
	
	
	
	
	@SuppressWarnings("static-access")
	public void extractFilesForValidationRegistration(String userType) throws IOException, ParseException{
		waitTime(15000);
		
		appsFlyerObj.convertCsvToXlsxReport(appsFlyerObj.fetchTheDownloadedAppsFlyerReportName());
		System.out.println(idNumber);
		
	
		//CONSUMPTION EVENTS
		if(userType.contains("Guest")){
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"af_complete_registration");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"content_lang_done");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"display_lang_done");
			
		}else{
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"content_lang_done");
			appsFlyerObj.extractFilesForValidationFunction(userType,idNumber,"display_lang_done");
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	public void accessDeviceLocationPopUp(String permission, String userType) throws Exception {
		extent.HeaderChildNode("Access Device Location PopUp");
		extent.extentLogger("User Type", "UserType : " + userType);
		logger.info("UserType : " + userType);
		System.out.println("Access Device Location PopUp");
		Thread.sleep(10000);

				if(verifyIsElementDisplayed(AMDOnboardingScreen.objUpdateZee5PopUpNOTHANKSButton, "NO THANKS Button"))
				{
					click(AMDOnboardingScreen.objUpdateZee5PopUpNOTHANKSButton, "NO THANKS Button");
				}else{
					System.out.println("UpdateZee5 Not displayed");
				}
				Thread.sleep(10000);
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objAllowLocationAccessPopup, "AllowPopup")) {
			Wait(5000);

			String str1 = getAttributValue("text", AMDOnboardingScreen.objFirstPermissionButton);
			String str2 = getAttributValue("text", AMDOnboardingScreen.objSecondPermissionButton);
			System.out.println(str1);
			System.out.println(str2);

			if (str1.contains("ALLOW")) {
				System.out.println("ALLOW is present");
				click(AMDOnboardingScreen.ele1Allow(str1), str1);
			} else if (str1.contains("Allow")) {
				System.out.println("Allow is present");
				click(AMDOnboardingScreen.ele1Allow(str1), str1);
			} else if (str2.contains("ALLOW")) {
				System.out.println("ALLOW is present");
				click(AMDOnboardingScreen.ele1Allow(str2), str2);
			} else if (str2.contains("Allow")) {
				System.out.println("Allow is present");
				click(AMDOnboardingScreen.ele1Allow(str2), str2);
			} else if (str1.contains("WHILE USING THE APP")) {
				System.out.println("WHILE USING THE APP is present");
				click(AMDOnboardingScreen.ele1Allow(str1), str1);
			}
			

			Thread.sleep(10000);
		} else {
			System.out.println("Access Device Location PopUp not displayed");
		}

	}
	
	
	
	
	public void DisplayAndContentLanguage(String userType) throws Exception {
		extent.HeaderChildNode("Content language screen functionlity");
		System.out.println("Content language screen functionlity");
		Thread.sleep(6000);

		// CONTENT LANGUAGE
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objContentLanguagePageTitle, "Content Language Page")) {
			extent.extentLogger("", "Navigated to content language page");
			verifyElementExist(AMDOnboardingScreen.objScreenTitle, "Screen header");
			verifyElementExist(AMDOnboardingScreen.objContentLanguagePageTitle, "Page title");
			verifyElementExist(AMDOnboardingScreen.objContentLanguageContainer, "Content language");
			verifyElementExist(AMDOnboardingScreen.objContent_ContinueBtn,
					"Continue button in content language screen");
			verifyElementExist(AMDOnboardingScreen.objBackBtn, "Back button in content language screen");

			verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
					"Continue button in content language screen");

		} else {
			extent.extentLogger("", "Not navigated to content language page");
		}

	}
	
	
	public void selectCountry() throws Exception {
		extent.HeaderChildNode("Select Country");
		
		waitForElementDisplayed(AMDOnboardingScreen.objContinueBtnInCountryPopUp, 15);
		if (verifyIsElementDisplayed(AMDOnboardingScreen.objContinueBtnInCountryPopUp)) {
			click(AMDOnboardingScreen.objContinueBtnInCountryPopUp, "Continuebutton(Country_Screen)");
		}
		
	}
	
	
	public void ZeeApplicasterLogin(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");

		String UserType = getParameterFromXML("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}

//		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
//		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
//			hideKeyboard();
//			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDOnboardingScreen.objZeeMorebtn1, "More button");
			
			verifyElementPresentAndClick(AMDOnboardingScreen.objZeeLoginRegisterLink, "Login/Register Link");

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

			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");

			verifyElementPresentAndClick(AMDOnboardingScreen.objZeeMorebtn1, "More button");
			
			verifyElementPresentAndClick(AMDOnboardingScreen.objZeeLoginRegisterLink, "Login/Register Link");


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
		waitTime(15000);
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
	
	
	public void tabViewFunctionality1() throws Exception{
		extent.HeaderChildNode("Tab_View");
		verifyElementPresentAndClick(AMDHomePage.objShowsTab, "TV Shows Tab");
		waitTime(15000);
	}
	
	
	public void OriginalContentPlayFunctionality() throws Exception{
		extent.HeaderChildNode("TVShows_Content_Play + af_svod_first_episode_free + videos_viewed_is_20 + video_view_50_percent + video_view_85_percent");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Button");
		waitTime(5000);
		click(AMDSearchScreen.objSearchEditBox, "Search box");
		type(AMDSearchScreen.objSearchBoxBar, "Kailasapura\n", "Search box");
		hideKeyboard();
		waitTime(6000);
		click(AMDSearchScreen.objFirstSearchResult, "Search result");
		waitTime(5000);
		verifyIsElementDisplayed(AMDConsumptionScreen.objContentName);
		waitTime(8000);
		String episode = getText(AMDConsumptionScreen.objContentName);
		System.out.println(episode);
		waitTime(8000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		scrubProgressBarToMidDFP(AMDPlayerScreen.objProgressBar);
		click(AMDPlayerScreen.objPauseIcon, "Pause");
		waitTime(10000);waitTime(10000);
		click(AMDPlayerScreen.objPlayerScreen, "Player screen");
		scrubProgressBarTillEndDFP(AMDPlayerScreen.objProgressBar);
		//click(AMDPlayerScreen.objPauseIcon, "Pause");
		waitTime(10000);
	}

	
	
	
	public void BuyPlanFunctionality(String userType) throws Exception{
		extent.HeaderChildNode("BuyPlan Functionality");
		if(!userType.equals("SubscribedUser")){
			//BackToHome();
			
			verifyElementPresentAndClick(AMDSubscibeScreen.objconsumptionBuyPlanButton1, "Consumption BuyPlan Button");
			waitTime(8000);
			verifyElementPresentAndClick(AMDSubscibeScreen.objContinueButton, "Continue Button");
			waitTime(10000);
			
			BackToHome();
		}else{
			extent.extentLogger("", "Subscribed user doesn't has BUY PLAN");
			Back(1);
			Back(1);
		}
		
	}
	
	
	public void waitForAdToFinishInAmd() {
		waitTime(20000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objAd2)) {
			logger.info("Ad is playing");
			extentLogger("Ad", "Ad is playing");
			verifyElementNotPresent(AMDPlayerScreen.objAd2, 200);
			logger.info("Ad is completed");
			extentLogger("Ad", "Ad is completed");
		} else {
			logger.info("Ad is not played");
			extentLogger("Ad", "Ad is not played");
		}
	}
	
	
	public void MusicPlayFunctionality() throws Exception{
		extent.HeaderChildNode("Music Play Functionality - Videos_Content_Play");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Button");
		waitTime(5000);
		click(AMDSearchScreen.objSearchEditBox, "Search box");
		waitTime(5000);
		type(AMDSearchScreen.objSearchBoxBar, "vollada Maalika\n", "Search box");
		hideKeyboard();
		waitTime(6000);
		click(AMDSearchScreen.objFirstSearchResult, "Search result");
		waitTime(5000);
		waitForAdToFinishInAmd();
		
		Back(1);
		Back(1);
	}
	
	
	public void BackToHome() throws Exception{
		for (int i = 0; i <= 10; i++) {
			if (!verifyIsElementDisplayed(AMDSubscibeScreen.objBuyPlanButton, "BuyPlan Button")) {
				Back(1);
			} else {
				System.out.println("Home Button Displayed");
				click(AMDHomePage.objBottomHomeBtn, "Home Button");
				break;
			}
		}
	}
	
	
	
	public void scrubProgressBarToMidDFP(By byLocator1) throws Exception {
//		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
//		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
//		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));
		click(AMDPlayerScreen.objPauseIcon, "Pause");
		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		Point point = element.getLocation();

		int getX = point.getX();
		int getY = point.getY();
		int height = (int) (size.getHeight());
		int width = (int) (size.getWidth());
		
		System.out.println(getX);
		System.out.println(getY);
		System.out.println(height);
		System.out.println(width);
		
		float midOfWidth = (float) (width * 0.5);
		int pointX = (int) (getX + midOfWidth);
		System.out.println(pointX);
		float midOfHeight = (float) (height/2);
		int pointY = (int) (getY + midOfHeight);
		System.out.println(pointY);

		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).moveTo(PointOption.point(pointX, pointY))
				.release().perform();
		
	}
	
	
	public void scrubProgressBarTillEndDFP(By byLocator1) throws Exception {
		click(AMDPlayerScreen.objPauseIcon, "Pause");
		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		Point point = element.getLocation();

		int getX = point.getX();
		int getY = point.getY();
		int height = (int) (size.getHeight());
		int width = (int) (size.getWidth());
		
		System.out.println(getX);
		System.out.println(getY);
		System.out.println(height);
		System.out.println(width);
		
		float midOfWidth = (float) (width * 0.9);
		int pointX = (int) (getX + midOfWidth);
		System.out.println(pointX);
		float midOfHeight = (float) (height/2);
		int pointY = (int) (getY + midOfHeight);
		System.out.println(pointY);

		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).moveTo(PointOption.point(pointX, pointY))
				.release().perform();
	}
	

 	public void tabViewFunctionality() throws Exception{
		extent.HeaderChildNode("Tab_View");
		verifyElementPresentAndClick(AMDHomePage.objShowsTab, "TV Shows Tab");
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objMoviesTab, "Movies Tab");
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objPremiumTab, "Premium Tab");
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objNewsTab, "News Tab");
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objWebSeriesTab, "Web Series Tab");
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objLiveTvTab, "Live TV Tab");
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objMusicTab, "Music Tab");
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objEduauraaTab, "Eduauraa Tab");
		waitTime(15000);
	}
	
	
	
	
 	/*
	 * Method used for user registration,changing the display language and content language to trigger the events 
	 * 1.Registration Success 2.Display Language Changed 3.Content Language Changed.
	 * 
	 * @parameter used is Usertype. for only 'Guest user' type registration success event is applicable 
	 * */
	public void RegisterAndLanguageFunctionality(String userType) throws Exception{
		extent.HeaderChildNode("Register and Content+Display Language");
		
		
		if(userType.equals("Guest")){
			//REGISTRATION
			String pDOB = "27";
			ResponseInstance.newPassword = "123456";
			String firstName = generateRandomString(6);
			String lastName = generateRandomString(6);

			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegister, "Login/Register Button");
			ResponseInstance.newEmailID = generateRandomString(8) + "@gmail.com";
			extent.extentLogger("", "New emailID : "+ResponseInstance.newEmailID);
			type(AMDRegistrationScreen.objEmailIDTextField, ResponseInstance.newEmailID, "Email field");
			click(AMDRegistrationScreen.objProceedBtn, "Proceed button");
			
			verifyElementExist(AMDRegistrationScreen.objScreenTitle, "Register for free title");
			//verifyElementPresentAndClick(AMDRegistrationScreen.objFirstNameTxtField, "First name field");		
		//	type(AMDRegistrationScreen.objFirstNameTxtField, firstName, "First name");
//			hideKeyboard();
			
		//	verifyElementPresentAndClick(AMDRegistrationScreen.objLastNameTxtField, "Last Name field");
	//		type(AMDRegistrationScreen.objLastNameTxtField, lastName, "Last Name");
//			hideKeyboard();
			
			click(AMDRegistrationScreen.objDOBTxtField, "DOB field");
			type(AMDRegistrationScreen.objDOBTxtField, pDOB, "DOB");
//			hideKeyboard();		
			
			verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");
			
			verifyElementPresentAndClick(AMDRegistrationScreen.objPasswordTxtField, "Passowrd field");
			type(AMDRegistrationScreen.objPasswordTxtField, ResponseInstance.newPassword, "Password");
			hideKeyboard();
			waitTime(5000);
			verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
			waitTime(10000);
			
//			boolean verifyHomePage = verifyElementExist(AMDHomePage.objHomeTab, "Home Screen");
			if (verifyElementExist(AMDHomePage.objHomeTab, "Home Screen")) {
				logger.info("New User Registerd to ZEE5 App successfully");
				extent.extentLoggerPass("Registration", "New User Registerd to ZEE5 App successfully");
				
				
				//LANGUAGE CHECK
				//DISPLAY LANGUAGE
				verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
				verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings Option");
				
				SwipeUntilFindElement(AMDLoginScreen.objDisplayLang, "Up");
				verifyElementPresentAndClick(AMDLoginScreen.objDisplayLang, "Display Language");
				
				verifyElementPresentAndClick(AMDLoginScreen.objLangHindi, "Hindi Language");
				waitTime(5000);
				verifyElementPresentAndClick(AMDLoginScreen.objLanguageContinueBtn, "Continue Button");
				waitTime(3000);
				verifyElementPresentAndClick(AMDLoginScreen.objDisplayLang, "Display Language");
				verifyElementPresentAndClick(AMDLoginScreen.objLangEnglish, "English Language");
				
				verifyElementPresentAndClick(AMDLoginScreen.objLanguageContinueBtn, "Continue Button");
				
				
				//CONTENT LANGUAGE
				waitTime(5000);
				SwipeUntilFindElement(AMDMoreMenu.objContentLang, "UP");
				verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content language");
				
			//	verifyElementPresentAndClick(AMDLoginScreen.objContentLanguageHindi, "Hindi Language");
			//	waitTime(5000);
				verifyElementPresentAndClick(AMDLoginScreen.objContentLanguageHindi, "Hindi Language");
				
				verifyElementPresentAndClick(AMDLoginScreen.objContentLanguageContinueButton, "Continue Button");
				
			} else {
				logger.error("New User failed to Register to ZEE5 App");
				extent.extentLoggerFail("Registration", "New User failed to Register to ZEE5 App");
			}
			
		}else{
			//LANGUAGE CHECK
			//DISPLAY LANGUAGE
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings Option");
			
			SwipeUntilFindElement(AMDLoginScreen.objDisplayLang, "Up");
			verifyElementPresentAndClick(AMDLoginScreen.objDisplayLang, "Display Language");
			
			verifyElementPresentAndClick(AMDLoginScreen.objLangHindi, "Hindi Language");
			waitTime(5000);
			verifyElementPresentAndClick(AMDLoginScreen.objLanguageContinueBtn, "Continue Button");
			waitTime(3000);
			verifyElementPresentAndClick(AMDLoginScreen.objDisplayLang, "Display Language");
			verifyElementPresentAndClick(AMDLoginScreen.objLangEnglish, "English Language");
			
			verifyElementPresentAndClick(AMDLoginScreen.objLanguageContinueBtn, "Continue Button");
			
			//CONTENT LANGUAGE
			waitTime(5000);
			SwipeUntilFindElement(AMDMoreMenu.objContentLang, "UP");
			verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content language");
			
		//	verifyElementPresentAndClick(AMDLoginScreen.objContentLanguageHindi, "Hindi Language");
		//	waitTime(5000);
			verifyElementPresentAndClick(AMDLoginScreen.objContentLanguageHindi, "Hindi Language");
			
			verifyElementPresentAndClick(AMDLoginScreen.objContentLanguageContinueButton, "Continue Button");
			
		}	

	}
	
	
	/**method for navigating to the bottom navigations
	 * Note:More has been moved to top navigation ,however maintenance has been done 
	 * */
	public void ScreenViewFunctionality() throws Exception{
		extent.HeaderChildNode("screen_view");
		verifyElementPresentAndClick(AMDHomePage.objBottomUpcomingBtn, "Upcoming Button");//triggers Upcomingsection_visited event
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objBottomDownloadBtn, "Download Button");//triggers Downloadsection_visited event
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objBottomHomeBtn, "Home Button");//triggers Homepage_visited event
		waitTime(15000);
		verifyElementPresentAndClick(AMDHomePage.objBottomMoreMenuBtn, "MoreMenu Button");//triggers Moresection_visited event
		waitTime(15000);
	}
	

}
