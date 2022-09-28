package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class ToastMessageImpressionEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInSignInScreen(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event In Sign In Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInSignInScreen(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInPaymentPage(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event in payment page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInPaymentPage(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInOTPScreen(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event In OTP Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInOTPScreen(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInSignUpScreen(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event In Sign Up Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInSignUpScreen(userType);
	}

	@Test(priority = 5)
	public void verifyToastMessageImpressionEventAfterResetSettingsToDefault() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event After Reset Settings To Default");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventAfterResetSettingsToDefault();
	}

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInParentalControlScreen(String userType) throws Exception {
		System.out.println("Verify Toast Message Impression Event In Parental Control Screen");
		Zee5PWAWEBMixPanelBusinessLogic.relaunchParentalControl();
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInParentalControlScreen(userType);
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInAuthenticateScreen(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event In Authenticate Screen");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInAuthenticateScreen(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventForAddToWatchlist(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event after adding card to watchlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventForAddToWatchlist(userType);
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventForRemoveFomWatchlist(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event after removing card from watchlist");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventForRemoveFomWatchlist(userType);
	}

	@Test(priority = 10)
	@Parameters({ "userType", "keyword1" })
	public void verifyToastMessageImpressionEventForEmbedPopUp(String userType, String keyword1) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression Event when user gets a toast message in embed popup");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventForEmbedPopUp(userType, keyword1);
	}

//	@Test(priority = 11)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventAfterUpdatingProfile(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression event when user updates the profile details");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventAfterUpdatingProfile(userType);
	}

	@Test(priority = 12)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventInPackSelectionPage(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression event in pack selection page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventInPackSelectionPage(userType);
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void verifyToastMessageImpressionEventAfterChangingPassword(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Toast Message Impression event when user changes the password");
		Zee5PWAWEBMixPanelBusinessLogic.verifyToastMessageImpressionEventAfterChangingPassword(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
