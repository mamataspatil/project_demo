package com.business.zee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.driverInstance.CommandBase;
import com.driverInstance.DriverInstance;
import com.extent.ExtentReporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.AMDSearchScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class Zee5ApplicasterChromeCastBusinessLogic extends Utilities {

	public Zee5ApplicasterChromeCastBusinessLogic(String Application) {
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

	static WebDriverWait wait;
	public static JavascriptExecutor js;
	private SoftAssert softAssert = new SoftAssert();

	public void init() {
		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
//		logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void initDriver() {
		if (platform.equals("Android")) {
			wait = new WebDriverWait(getDriver(), 30);
			js = (JavascriptExecutor) getDriver();
		} else if (platform.equals("TV")) {
			wait = new WebDriverWait(getTVDriver(), 30);
			js = (JavascriptExecutor) getTVDriver();
		}
	}

	public WebElement findElement(By byLocator) throws Exception {
		initDriver();
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return element;
	}

	public boolean verifyElementPresent(By byLocator, String validationtext) throws Exception {

		try {
			WebElement element = findElement(byLocator);
			softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
			logger.info(validationtext + " is displayed");
			extent.extentLogger("", validationtext + " is displayed");
			return true;
		} catch (Exception e) {
			softAssert.assertEquals(false, true, validationtext + " " + " is not displayed");
//			softAssert.assertAll();
			logger.error(validationtext + " is not displayed");
			extent.extentLogger("", validationtext + " is not displayed");
			return false;
		}
	}

	public void click(By byLocator, String validationtext) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			element.click();
			logger.info("Clicked on " + validationtext);
			extent.extentLogger("", "Clicked on " + validationtext);
		} catch (Exception e) {
//			screencapture();
		}
	}

	public void waitTime(int x) {

		try {
			Thread.sleep(x);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void hideKeyboard() {
		try {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("", "Hiding keyboard was Successfull");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void type(By byLocator, String input, String FieldName) {
		try {
			waitTime(1000);
			if (!platform.equals("Android")) {
				Actions a = new Actions(getDriver());
				a.sendKeys(input);
				a.perform();
			} else {
				WebElement element = findElement(byLocator);
				element.sendKeys(input);
			}
			input = input.split("\n")[0];
			logger.info("Typed the value " + input + " into " + FieldName);
			extent.extentLogger("", "Typed the value " + input + " into " + FieldName);

		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static String getTheOSVersion() {
		String version = null;
		try {
			String cmd1 = "adb -s CUZTSOEMLB85EUR8 shell getprop ro.build.version.release";
			Process p1 = Runtime.getRuntime().exec(cmd1);
			BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			// outputText1 ="";
			while ((version = br.readLine()) != null) {
				logger.info("Version :: " + version.toString());
				Thread.sleep(3000);
				break;
			}

		} catch (Exception e) {
			// logger.error(e);
		}
		return version;
	}

	public void TurnOFFWifi() throws IOException {
		String Deviceversion = getTheOSVersion();
		System.out.println("Turn off wifi");
		if (Deviceversion.contains("6")) {
			Runtime.getRuntime().exec(
					"adb -s CUZTSOEMLB85EUR8 shell am broadcast -a io.appium.settings.wifi --es setstatus disable");
			logger.info("Turning off wifi");
			extent.extentLogger("", "Turning off wifi");
		} else {
			Runtime.getRuntime().exec("adb -s CUZTSOEMLB85EUR8 shell svc wifi disable");
			logger.info("Turning off wifi");
			extent.extentLogger("", "Turning off wifi");
		}
	}

	public void TurnONWifi() throws IOException {
		String Deviceversion = getTheOSVersion();
		System.out.println("Turn on wifi");
		if (Deviceversion.contains("6")) {
			Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable");
			logger.info("Turning ON wifi");
			extent.extentLogger("", "Turning ON wifi");
		} else {
			Runtime.getRuntime().exec("adb -s CUZTSOEMLB85EUR8 shell svc wifi enable");
			logger.info("Turning ON wifi");
			extent.extentLogger("", "Turning ON wifi");
		}
	}

	public void ValidateChromeCast() throws Exception {
		HeaderChildNode("Chrome Cast validation From Native Android App to Android TV");
		platform = "Android";
		System.out.println(platform);
		waitTime(3000);
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(4000);
		click(AMDSearchScreen.objSearchEditBox, "Search Box");
		waitTime(2000);
		type(AMDSearchScreen.objSearchBoxBar, "bablu dablu robo rumble" + "\n", "Searchbar");
		waitTime(2000);
		click(AMDSearchScreen.objcastsearchresult, "Searched movie");
		waitTime(5000);
		verifyElementPresent(AMDSearchScreen.objchromecasticon, "Chrome cast");
		waitTime(3000);
		String playerTitleInNative = getDriver().findElement(AMDSearchScreen.objplayercontentTitle).getText();
		waitTime(3000);
		click(AMDSearchScreen.objchromecasticon, "Chrome cast");
		waitTime(2000);
		verifyElementPresent(AMDSearchScreen.objchooseDeviceChromeCastPopup, "Cast to popup");
		waitTime(2000);
		click(AMDSearchScreen.objDeviceList, "TV device");
		waitTime(5000);
		click(AMDSearchScreen.objPostChromecastIcon, "Chromecast icon");
		verifyElementPresent(AMDSearchScreen.objCastedDeviceName, "Chromecast popup");
		verifyElementPresent(AMDSearchScreen.objExpandControllerPauseIcon, "Chromecast pause icon");
		verifyElementPresent(AMDSearchScreen.objExpandControllerStopCastButton, "Chromecast stop casting button");
		verifyElementPresent(AMDSearchScreen.objExpandControllerCloseIcon, "Chromecast close icon");
		verifyElementPresent(AMDSearchScreen.objExpandControllerVolumeController, "Chromecast volume controller");
		waitTime(3000);
		platform = "TV";
		waitTime(5000);
		System.out.println(platform);
		String cmd = "adb -s "+DriverInstance.getTVDeviceList()+" shell input keyevent 23";
		Runtime.getRuntime().exec(cmd);
		waitTime(5000);
		String playerTitleInTV = getTVDriver().findElement(AMDSearchScreen.objContentTitleInTV).getText();
		if (playerTitleInNative.contains(playerTitleInTV)) {
			logger.info("Chrome cast playback in TV functionality successfull and title verified");
			extent.extentLoggerPass("", "Chrome cast playback in TV functionality successfull and title verified");
		} else {
			logger.info("Chromecast functionality failed");
			extent.extentLoggerFail("", "Chromecast functionality failed");
		}
		verifyElementPresent(AMDSearchScreen.objQueueInTV, "Queue text");
		logger.info("Queue text is displayed");
		extent.extentLogger("", "Queue text is displayed");
		platform = "Android";
		waitTime(3000);
		click(AMDSearchScreen.objExpandControllerCloseIcon, "Chromecast close icon");
		waitTime(3000);
		click(AMDSearchScreen.objPostChromecastIcon, "Chromecast icon");
		waitTime(3000);
		waitTime(3000);
		TurnOFFWifi();
		waitTime(5000);
		platform = "TV";
		String cmd2 = "adb -s "+DriverInstance.getTVDeviceList()+" shell input keyevent 23";
		Runtime.getRuntime().exec(cmd2);
		waitTime(2000);
		if (verifyElementPresent(AMDSearchScreen.objPlayIconInTV, "play icon")) {
			logger.info("Chrome cast functionality is verifed when user disconnects network from sender device");
			extent.extentLoggerPass("",
					"Chrome cast functionality is verifed when user disconnects network from sender device");
			waitTime(3000);
		} else {
			logger.info("Chrome cast functionality failed");
			extent.extentLoggerFail("", "Chrome cast functionality failed");
		}
		platform = "Android";
		TurnONWifi();
		waitTime(5000);
		String lock = "adb -s "+DriverInstance.getDeviceList()+" shell input keyevent 26";
		Runtime.getRuntime().exec(lock);
		logger.info("Sender device has been locked");
		extent.extentLogger("", "Sender device has been locked");
		waitTime(3000);
		Runtime.getRuntime().exec(lock);
		logger.info("Sender device has been un-locked");
		extent.extentLogger("", "Sender device has been un-locked");
		waitTime(3000);
		if (verifyElementPresent(AMDSearchScreen.objExpandControllerCloseIcon, "ChromecastIcon")) {
			logger.info("Chromecast functionality is verifed post lock and unlock");
			extent.extentLoggerPass("", "Chromecast functionality is verifed post lock and unlock");
			waitTime(3000);
		} else {
			logger.info("Chromecast functionality failed post lock and unlock");
			extent.extentLoggerFail("", "Chromecast functionality failed post lock and unlock");
		}
		getDriver().runAppInBackground(Duration.ofSeconds(6));
		logger.info("Device has been minimised");
		extent.extentLogger("", "Device has been minimised");
		waitTime(3000);
		if (verifyElementPresent(AMDSearchScreen.objExpandControllerCloseIcon, "ChromecastIcon")) {
			logger.info("Chromecast functionality is verifed post minimize and resume");
			extent.extentLoggerPass("", "Chromecast functionality is verifed post minimize and resume");
			waitTime(3000);
		} else {
			logger.info("Chromecast functionality is verifed post minimize and resume");
			extent.extentLoggerFail("", "Chromecast functionality is verifed post minimize and resume");
		}
		waitTime(3000);
		click(AMDSearchScreen.objExpandControllerStopCastButton, "Stop casting button");

		waitTime(3000);
		if (!verifyElementPresent(AMDSearchScreen.objPostChromecastIcon, "Chrome cast")) {
			logger.info("stop cast button functionality verified");
			extent.extentLoggerPass("Stop Cast", "Stop cast button functionality verified");
		} else {
			logger.info("Stop cast button functionality failed");
			extent.extentLoggerFail("Stop Cast", "Stop cast button functionality failed");
		}
	}

	public void tearDown() {
		getDriver().quit();
		getTVDriver().quit();
	}
	
	
}
