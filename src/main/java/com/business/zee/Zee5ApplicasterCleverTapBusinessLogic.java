package com.business.zee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.CleverTap.CleverTapDashboardData;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.AMDCleverTapPage;
import com.zee5.ApplicasterPages.AMDConsumptionScreen;
import com.zee5.ApplicasterPages.AMDHomePage;
import com.zee5.ApplicasterPages.AMDLoginScreen;
import com.zee5.ApplicasterPages.AMDMoreMenu;
import com.zee5.ApplicasterPages.AMDMySubscriptionPage;
import com.zee5.ApplicasterPages.AMDOnboardingScreen;
import com.zee5.ApplicasterPages.AMDRegistrationScreen;
import com.zee5.ApplicasterPages.AMDSearchScreen;
import com.zee5.ApplicasterPages.AMDSubscibeScreen;
import com.zee5.ApplicasterPages.AMDWatchlistPage;
import com.zee5.PWAPages.CleverTapPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class Zee5ApplicasterCleverTapBusinessLogic extends Utilities{

	
	public Zee5ApplicasterCleverTapBusinessLogic(String Application) {
		new CommandBase(Application);
		init();
	}

	private int timeout;

	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
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

	private static String AdID = "__g7e128be08f024eb493e4e7382eb01d82";
	private static String newUsername = null;
	private static Boolean registerUser = false;
	
	public void init() {
		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		decode();
	}	
	
	public void tearDown() {
		getWebDriver().quit();
		getDriver().quit();
	}
	
	public void loginCleverTap() throws Exception {
		HeaderChildNode("Login Clever tap");
		enter(CleverTapPage.objEmailID, CTUserName, "Email field");
		enter(CleverTapPage.objPasswordEditBx, CTPWD, "Password field");
		verifyElementPresentAndClick(CleverTapPage.objLoginBtn, "Login button");
		
		waitTime(3000);
		
		try{
			getWebDriver().switchTo().frame("wiz-iframe-intent");
			verifyElementPresentAndClick(CleverTapPage.objPopUpCloseIcon, "Popup close icon");
			waitTime(4000);
			getWebDriver().switchTo().defaultContent();
			waitTime(4000);
		}catch(Exception e){
			
		}
	}
	
	public void getEventName(String EventName) {
		try {
			HeaderChildNode("Event Name : "+EventName);
//			CleverTapDashboardData.creatExcelCleverTap();
			waitTime(10000);
			List<WebElement> event = findElements(CleverTapPage.objEventName);
			List<WebElement> time = findElements(CleverTapPage.objTime);
//			System.out.println(event.size());
//			System.out.println(time.size());
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//			System.out.println("D1 : "+CTCurrentTime);
			boolean eventReflected = false;
			for (int i = 0; i < event.size(); i++) {
//				System.out.println(i);
				String t = time.get(i).getText();
//				System.out.println(event.get(i).getText());
//				System.out.println(t);
				String CTTime = (t.replaceAll("am", "").replace("pm", ""));
//				System.out.println("D2 : "+CTTime);
				if ((sdf.parse(CTCurrentTime).equals(sdf.parse(CTTime))) || (sdf.parse(CTCurrentTime).before(sdf.parse(CTTime)))) {
					if (event.get(i).getText().contains(EventName)) {
						logger.info(EventName + " Event Reflected in dashboard ");
						extent.extentLoggerPass("Event", EventName + " Event Reflected in dashboard ");
						eventReflected = true;
						break;
					}
				} else {
					eventReflected = true;
					break;
				}
			}
			if (!eventReflected) {
				logger.info( EventName +" Event not reflected in dashboard ");
				extent.extentLoggerFail("Event", EventName + " Event not reflected in dashboard ");
			}
		} catch (Exception e) {
		}
	}

	
	public void SubscriptionSelected(String userType) throws Exception {
		HeaderChildNode("Subscription Selected");
		if(!userType.equals("SubscribedUser")) {
		waitTime(6000);
		PartialSwipe("UP", 2);
		waitTime(6000);
		verifyElementPresentAndClick(AMDMySubscriptionPage.objSelectPlanCheckBx, "Premium Plan Check Box");
		}
	}
	
	public void ContentLanguageChange() throws Exception {
		HeaderChildNode("Content Language Change");
//		verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More menu");
//		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings");		
		verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content Language");
		waitTime(5000);
		click(AMDMoreMenu.objContinueLangBtn,"Continue");
		waitTime(5000);
	}

	public void SearchCancelled() throws Exception {
		waitTime(8000);
		Back(1);
		waitTime(8000);
		Back(1);
		waitTime(8000);
		HeaderChildNode("Search Cancelled");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search");
		waitTime(8000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchBackBtn, "Back button");
		waitTime(6000);
	}

	public void AddToWatchlist(String userType) throws Exception {
		
		HeaderChildNode("Add To Watchlist");
		waitTime(6000);
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search");
		waitTime(6000);
		verifyElementPresentAndClick(AMDSearchScreen.objsearchBox, "SerchBox");
		waitTime(6000);
		type(AMDSearchScreen.objsearchBox,"love u ganesha","search field");
		waitTime(6000);
		hideKeyboard();
		verifyElementPresentAndClick(AMDSearchScreen.objFisrtSearchContent,"First Content In Search");
		waitTime(6000);
		verifyElementPresentAndClick(AMDConsumptionScreen.objWatchlistBtn, "Watchlist button");
		waitTime(6000);
		if(userType.equalsIgnoreCase("Guest")){
			waitTime(6000);
			hideKeyboard();
			waitTime(6000);
			Back(1);
		}
		
	}

	public void Share() throws Exception {
		HeaderChildNode("Share");
		extent.HeaderChildNode("Verify Share CTA functionality");
		System.out.println("\nVerify Share CTA functionality");

		click(AMDConsumptionScreen.objShareBtn, "Share button");
		boolean isShareOption = verifyIsElementDisplayed(AMDMoreMenu.objshareOptions);
		if (isShareOption) {

			verifyElementPresentAndClick(AMDMoreMenu.objFacebook1, "Facebook option");
			waitTime(15000);
			verifyElementPresentAndClick(AMDMoreMenu.objFacebookPost, "Post/Share button");
		
		} else {
			logger.info("Share Options are not displayed after clicking on Share CTA");
			extent.extentLoggerFail("Share through options screen",
					"Share Options are not displayed after clicking on Share CTA");
		}
		Back(1);
	}

	public void PromoCodeResult() {
		HeaderChildNode("Promo Code Result");
		
	}
	
	public void NoThanksPopUp(String permission, String userType) throws Exception {
		extent.HeaderChildNode("Access Device Location PopUp");
		extent.extentLogger("User Type", "UserType : " + userType);
		logger.info("UserType : " + userType);
		System.out.println("Access Device Location PopUp");
		Thread.sleep(10000);
		Thread.sleep(10000);
		Thread.sleep(10000);
				if(verifyIsElementDisplayed(AMDOnboardingScreen.objUpdateZee5PopUpNOTHANKSButton, "NO THANKS Button"))
				{
					click(AMDOnboardingScreen.objUpdateZee5PopUpNOTHANKSButton, "NO THANKS Button");
				}else{
					System.out.println("UpdateZee5 Not displayed");
				}
				Thread.sleep(10000);
				
				
//		if (verifyIsElementDisplayed(AMDOnboardingScreen.objAllowLocationAccessPopup, "AllowPopup")) {
//			Wait(5000);
//
//			String str1 = getAttributValue("text", AMDOnboardingScreen.objFirstPermissionButton);
//			String str2 = getAttributValue("text", AMDOnboardingScreen.objSecondPermissionButton);
//			System.out.println(str1);
//			System.out.println(str2);
//
//			if (str1.contains("ALLOW")) {
//				System.out.println("ALLOW is present");
//				click(AMDOnboardingScreen.ele1Allow(str1), str1);
//			} else if (str1.contains("Allow")) {
//				System.out.println("Allow is present");
//				click(AMDOnboardingScreen.ele1Allow(str1), str1);
//			} else if (str2.contains("ALLOW")) {
//				System.out.println("ALLOW is present");
//				click(AMDOnboardingScreen.ele1Allow(str2), str2);
//			} else if (str2.contains("Allow")) {
//				System.out.println("Allow is present");
//				click(AMDOnboardingScreen.ele1Allow(str2), str2);
//			} else if (str1.contains("WHILE USING THE APP")) {
//				System.out.println("WHILE USING THE APP is present");
//				click(AMDOnboardingScreen.ele1Allow(str1), str1);
//			}
//			Thread.sleep(10000);
//		} else {
//			System.out.println("Access Device Location PopUp not displayed");
//		}

				
				SelectYourCountry();
				
	}
	
	
	
	public void SelectYourCountry() throws Exception
	{
		extent.HeaderChildNode("Select Your country and Language");
		waitTime(5000);
		verifyElementPresentAndClick(AMDOnboardingScreen.objContinueBtnInCountryPopUp, "SelectYourCountry Continue Button");
		waitTime(5000);
	}
	
	
	
	public void cleverTapLoginLogoutFunctionality(String userType) throws Exception{
		extent.HeaderChildNode("CleverTap Login");
		
		if(userType.equalsIgnoreCase("Guest")) {
			
		String Username = getParameterFromXML("NonsubscribedUserName");
		String Password = getParameterFromXML("NonsubscribedPassword");

		verifyElementPresentAndClick(AMDOnboardingScreen.objZeeMoreButton, "More button");
		
		verifyElementPresentAndClick(AMDOnboardingScreen.objZeeLoginRegisterLink, "Login/Register Link");

		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
		type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
		type(AMDLoginScreen.objPasswordField, Password, "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
		waitTime(5000);

		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More menu");
		waitTime(5000);
		PartialSwipe("UP", 2);
		waitTime(5000);
		verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
		waitTime(5000);
		verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout");
		
		
		waitTime(5000);
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
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
		if (userType != "Guest" & clearData == false) {
			System.out.println("Navigates to Landing Sccreen..");
		}
	}
		
	public void EditCampaign(String EventName,String CampaignName) throws Exception {
		HeaderChildNode("Edit Campaign");
		verifyElementPresentAndClick(CleverTapPage.objCampaignIcon, "Campaign Icon");
		waitTime(3000);
		List<WebElement> listOfEvents = findElements(CleverTapPage.objCampaignlist);
		for (int i = 1; i < listOfEvents.size(); i++) {
			WebElement Eventname = listOfEvents.get(i);
			if(i == 7) {
				scrollDownWEB();
			}
			System.out.println(Eventname.getText());
			if(Eventname.getText().contains(CampaignName)) {
				Eventname.click();
				break;
			}
		}
		verifyElementPresentAndClick(CleverTapPage.objCloneIcon, "Clone Icon");
		waitTime(8000);
		verifyElementPresentAndClick(CleverTapPage.objWhoEditIcon, "Edit Icon");
		verifyElementPresentAndClick(CleverTapPage.objWhoEventDD, "Event Drop Down");
		type(CleverTapPage.objSearchInput, EventName,"Search Event");
		verifyElementPresentAndClick(CleverTapPage.objSearchResult(EventName), "Event Name");
		waitTime(8000);
		verifyElementPresentAndClick(CleverTapPage.objContinueBtn, "Continue Button");
	}
	
	/**Method used to navigate till subscription plan screen for Guest
	 * ,Non subscribed and 299 pack user(less than max price)*/
	public void SubscriptionPageViewed(String userType) throws Exception {
		HeaderChildNode("Subscription Page Viewed");
		if(!userType.equals("SubscribedUser")) {
			waitTime(2000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Plan");//which is present in More menu
		}
	}
	
	/** method to register the user
	 * modified by @Madhav
	 * Note:The first name and Last name text field has been commented as a Short registration form feature for india region
	 * for global its applicable*/
	
	public void RegisterFunctionality(String userType) throws Exception{
		extent.HeaderChildNode("CleverTap Register");
		if(userType.equals("Guest")){
			//REGISTRATION
			String pDOB = "27";
			String newPassword = "123456";
			//ResponseInstance.newPassword = "123456";
			String firstName = generateRandomString(6);
			String lastName = generateRandomString(6);

			Swipe("DOWN", 1);
			
			//verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			
			verifyElementPresentAndClick(AMDMoreMenu.objLoginRegister, "Login/Register Button");
			//ResponseInstance.newEmailID = generateRandomString(8) + "@gmail.com";
			newUsername = generateRandomString(8) + "@gmail.com";
			extent.extentLogger("", "New emailID : "+newUsername);
			type(AMDRegistrationScreen.objEmailIDTextField, newUsername, "Email field");
			click(AMDRegistrationScreen.objProceedBtn, "Proceed button");
			
			verifyElementExist(AMDRegistrationScreen.objScreenTitle, "Register for free title");
	//		verifyElementPresentAndClick(AMDRegistrationScreen.objFirstNameTxtField, "First name field");		
	//		type(AMDRegistrationScreen.objFirstNameTxtField, firstName, "First name");
//			hideKeyboard();
			
	//		verifyElementPresentAndClick(AMDRegistrationScreen.objLastNameTxtField, "Last Name field");
	//		type(AMDRegistrationScreen.objLastNameTxtField, lastName, "Last Name");
//			hideKeyboard();
			
			click(AMDRegistrationScreen.objDOBTxtField, "DOB field");
			type(AMDRegistrationScreen.objDOBTxtField, pDOB, "DOB");
//			hideKeyboard();		
			
			verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");
			
			verifyElementPresentAndClick(AMDRegistrationScreen.objPasswordTxtField, "Passowrd field");
			type(AMDRegistrationScreen.objPasswordTxtField, newPassword, "Password");
			hideKeyboard();
			waitTime(5000);
			verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
			waitTime(10000);	
		}
	}
	
	/**
	 * This method is used to remove the content from watchlist 
	 * Modified by :@Madhav
	 * Event covered:Remove From Watchlist
	 * 	precondition:Some contents should be present in the Watchlist*/

	public void RemoveFromWatchlist(String userType) throws Exception {
		if(!userType.equals("Guest")) {
		Back(1);
		HeaderChildNode("Remove From Watchlist");
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home");//taps on home button in bottom navigation bar
		verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objWatchlist, "Watchlist");
		verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
		verifyElementPresentAndClick(AMDWatchlistPage.objSelectCheckBox, "Check box");
		verifyElementPresentAndClick(AMDWatchlistPage.objDeleteAllBtn, "Delete button");
		}
	}
	
	
	/**
	 * Method used for logging out of registered user
	 * Modified by :Madhav */
	public void logout(String userType) throws Exception {
		if(!userType.equals("Guest")) {
		HeaderChildNode("LogOut");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objHomeBtn, "Home tab");
		verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More menu");
		PartialSwipe("UP", 2);
		verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
		waitTime(5000);
		verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout");
		waitTime(5000);
		}
	}
	
	/**
	 * Method used for changing display language 
	 * event triggered =Display language changed
	 * applicable for all user type @parameters*/
	public void DisplayLanguageChange() throws Exception {
		HeaderChildNode("Display Language Change");
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home");
		verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings");
		verifyElementPresentAndClick(AMDMoreMenu.objDisplayLang, "Display Language");
		waitTime(2000);
		click(AMDMoreMenu.objContinueLangBtn,"Continue");
	}
	
	
	/**
	 * This method is used for Logging in of registered user
	 * Modified By:Madhav
	 */
	
	public void ZeeApplicasterLogin(String LoginMethod) throws Exception {
		System.out.println("\nLogin to the App");
		
		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Logged in as <b>Guest</b> User");

			extent.extentLogger("Accessing the application as Guest user",
					"Accessing the application as <b>Guest</b> user");
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			
			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objHomeBtn, "Home tab");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresent(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			click(AMDMoreMenu.objLoginRegisterText, "Login/Registet link");
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

			verifyElementPresentAndClick(AMDHomePage.objHomeBtn, "Home tab");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresent(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			click(AMDMoreMenu.objLoginRegisterText, "Login/Registet link");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;	
		case "299SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");

			String SubscribedUsername299 = getParameterFromXML("299SubscribedUserName");
			String SubscribedPassword299 = getParameterFromXML("299SubscribedPassword");

			verifyElementPresentAndClick(AMDHomePage.objHomeBtn, "Home tab");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			verifyElementPresent(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");

			click(AMDMoreMenu.objLoginRegisterText, "Login/Registet link");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername299, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword299, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;	
		}
	}
	
	
	/**
	 * Method is used for checking promocode */
	public void SubscriptionCallInitiated(String userType) throws Exception {
		HeaderChildNode("Promo Code Result & Subscription Call Initiated");
		
		if(!userType.equals("SubscribedUser")){
			
			if (userType.equals("299SubscribedUser")) {
				verifyElementPresentAndClick(AMDSubscibeScreen.objUpgrade, "Upgrade CTA");
				
			} else {
				
				Swipe("UP", 1);
				
				verifyElementExist(AMDSubscibeScreen.objHaveACodeCTA, "Have a code in subscribe page");
				click(AMDSubscibeScreen.objHaveACodeCTA, "Have a code in subscribe page");
				
				click(AMDSubscibeScreen.objEnterACodeEditFiled, "Promo");
				type(AMDSubscibeScreen.objEnterACodeEditFiled, "zee5scb", "Promo code");
				hideKeyboard();
				click(AMDSubscibeScreen.objApplyOnHaveACodescreen, "Apply button");
				
				waitTime(3000);
				verifyElementPresentAndClick(AMDMySubscriptionPage.objContinueBtnInBuyPremiumNow, "Continue button");
			}
			
		}	
	}
	
	
	/** method is used to trigger Subscription call returned for NonSubscribed user and subscribed user (less than premium pack)*/
	public void SubscriptionCallReturned(String userType) throws Exception {
		if((userType.equals("NonSubscribedUser"))||(userType.equals("299SubscribedUser"))) {
		HeaderChildNode("Subscription Call Returned");
		verifyElementPresentAndClick(AMDMySubscriptionPage.objEnterCardNumberBtn, "Enter Card Number");
		waitTime(8000);
		type(AMDMySubscriptionPage.objEnterCCTxt,"4012001037141112", "Card Number");
		waitTime(5000);
		type(AMDMySubscriptionPage.objExpiryCCTxt, "0525", "Expiry");
		waitTime(5000);
		type(AMDMySubscriptionPage.objCVVTxt, "124", "CVV");
		verifyElementPresentAndClick(AMDMySubscriptionPage.objPayNow, "Pay Now button");
	//	waitTime(180000);
		}
		waitTime(5000);
			Back(1);
		verifyElementPresentAndClick(AMDMySubscriptionPage.objOk, "Ok button on cancel Transaction pop up");
			Back(2);
	}
	
	/**Method used for validating the events from CleverTap Dashboard
	 * @Author :Madhav
	 * @pamameters: 1.Guest 2.Non Subscribed 3.Subscribed 4.299Subscribed user*/
	public void validateResult(String userType) throws Exception {
		HeaderChildNode("Verify Events are reflected in dashboard");
		new Zee5ApplicasterCleverTapBusinessLogic("Chrome");
			loginCleverTap();
			navigateToCleverTap(userType);
			
			if(userType.equalsIgnoreCase("Guest")){
				getEventName("Subscription Page Viewed");
				getEventName("Subscription Selected");
				getEventName("Display Language Changed");
				getEventName("Content Language Changed");
				getEventName("Add To Watchlist");
				getEventName("Share");
				getEventName("Search Cancelled");
				getEventName("Promo Code Result");
				getEventName("Login Screen Display");
				getEventName("Login Initatied");
				getEventName("Logout");
				getEventName("Register Screen Display");
				getEventName("Registration Initiated");
				registerUser = true;
				navigateToCleverTap(userType);
				getEventName("Registration Result");
			}else if(userType.equalsIgnoreCase("NonSubscribedUser")){
				getEventName("Subscription Page Viewed");
				getEventName("Subscription Selected");
				getEventName("Display Language Changed");
				getEventName("Content Language Changed");
				getEventName("Add To Watchlist");
				getEventName("Share");
				getEventName("Search Cancelled");
				getEventName("Promo Code Result");
				getEventName("Subscription Call Initiated");
				getEventName("Subscription Call Returned");
				getEventName("Remove From Watchlist");
				getEventName("Logout");
				getEventName("Login Result");
			}else if(userType.equals("SubscribedUser")){
				getEventName("Login Result");
				getEventName("Display Language Changed");
				getEventName("Content Language Changed");
				getEventName("Add To Watchlist");
				getEventName("Share");
				getEventName("Remove From Watchlist");
				getEventName("Search Cancelled");
				getEventName("Logout");
				
			}else if(userType.equals("299SubscribedUser")){
					getEventName("Subscription Page Viewed");
					getEventName("Subscription Selected");
					getEventName("Subscription Call Initiated");
					getEventName("Subscription Call Returned");
					getEventName("Login Result");
					getEventName("Display Language Changed");
					getEventName("Content Language Changed");
					getEventName("Add To Watchlist");
					getEventName("Share");
					getEventName("Remove From Watchlist");
					getEventName("Search Cancelled");
					getEventName("Logout");
			} else {
				System.out.println("Invalid user type ");
			}
	}

	/**
	 * Method used navigate to cleverTap dashboard and logging in 
	 * modified by @Madhav*/
	public void navigateToCleverTap(String userType) throws Exception{
		HeaderChildNode("Navigating to Segment section");
		verifyElementPresentAndClick(CleverTapPage.objCleverTapLogo, "CleverTap Logo");
		waitTime(2000);
		verifyElementPresentAndClick(CleverTapPage.objSegments, "Segment");
		waitTime(2000);
		
		if(userType.equalsIgnoreCase("Guest")){
			if(registerUser){
				type(CleverTapPage.objSearchField, newUsername, "Search field");
			}else{
				type(CleverTapPage.objSearchField, AdID, "Search field");
			}
		}else if(userType.equalsIgnoreCase("NonSubscribedUser")){
			
			type(CleverTapPage.objSearchField, getParameterFromXML("NonsubscribedUserName"), "Search field");
		}else if(userType.equalsIgnoreCase("SubscribedUser")){
			type(CleverTapPage.objSearchField, getParameterFromXML("SubscribedUserName"), "Search field");
		}
		else if (userType.equalsIgnoreCase("299SubscribedUser")) {
			type(CleverTapPage.objSearchField, getParameterFromXML("299SubscribedUserName"), "Search field");
		}
		
		waitTime(2000);
		verifyElementPresentAndClick(CleverTapPage.objFindBtn, "Find button");
		waitTime(20000);
		verifyElementPresentAndClick(CleverTapPage.objActivityBtn, "Activity button");
	}
	
}
