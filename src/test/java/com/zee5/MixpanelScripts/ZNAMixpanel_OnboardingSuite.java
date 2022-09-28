package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_OnboardingSuite {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void ZNAMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}

	/*
	 * Skip Login Event
	 */

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifySkipLoginEventBrowseForFreeScreen(String userType) throws Exception {
		System.out.println("Skip Login Event when user navigated from Browse for Free CTA");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySkipLoginEventBrowseForFreeScreen(userType);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifySkipLoginEventFromHamburgerMenu(String userType) throws Exception {
		System.out.println("Verify Skip Login Event from Hamburger menu");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySkipLoginEventFromHamburgerMenu(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType", "keyword2" })
	public void verifySkipLogin_LoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		System.out.println("Verify Skip Login Event from Get Ptremium Popup");
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifySkipLogin_LoginInGetPremiumPopUp(userType, keyword2);

	}

	/*
	 * Logout Event
	 */

  @Test(priority = 5)
	@Parameters({ "userType" })
	public void verifyLogoutEvent(String userType) throws Exception {
		System.out.println("Verify Logout Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLogoutEvent(userType);
	}

	/*
	 * Login Screen Display event
	 */

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void verifyLoginScreenDisplayEventThroughBrowseForFreeInWelcomeScreen(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Browse for free button in welcome screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginScreenDisplayEventThroughBrowseForScreen(userType);
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void verifyLoginScreenDisplayEventThroughMoreMenu(String userType) throws Exception {
		System.out.println("Verify Login Screen Display Event through MoreMenu");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginScreenDisplayEventThroughMoreMenu(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType", "keyword2" })
	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		System.out.println("Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic
				.verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(userType, keyword2);
	}

	/*
	 * Navigation : Get Premium CTA Event : Login Initiated
	 */

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventGPlusUserFromGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventGPlusUserFromGetPremiumCTA();
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventFBUserFromGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventFBUserFromGetPremiumCTA();
		
	}

	@Test(priority = 11)
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventTwitterUserFromGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventTwitterUserFromGetPremiumCTA();
	
	}

	@Test(priority = 12)
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventMobileOTPFromGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventMobileOTPFromGetPremiumCTA();
		
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventNonSubUserGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventNonSubUserGetPremiumCTA();
	}

	@Test(priority = 14)
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventSubUserFromGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventSubUserFromGetPremiumCTA();
		
	}

	@Test(priority = 15)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForInvalidDataEmailGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventForInvalidDataEmailGetPremiumCTA();
		
	}

	@Test(priority = 16)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForInvalidDataMobileGetPremiumCTA(String userType) throws Exception {
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventForInvalidDataMobileGetPremiumCTA();
	}
	
	/*
	 * Navigation : Browse for free CTA Event : Login Initiated
	 */

	@Test(priority = 17)
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventGPlusUserFromBrowseForFreeCTA(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventGPlusUserFromBrowseForFreeCTA();

	}

	@Test(priority = 18)  
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventFBUserFromBrowseForFreeCTA(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventFBUserFromBrowseForFreeCTA();
	}

	@Test(priority = 19)  
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventTwitterUserFromBrowseForFreeCTA(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventTwitterUserFromBrowseForFreeCTA();
	}

	@Test(priority = 20)  
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventMobileOTPFromBrowseForFreeCTA(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventMobileOTPFromBrowseForFreeCTA();
	}

	@Test(priority = 21)  
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventNonSubUserBrowseForFreeCTA(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventNonSubUserBrowseForFreeCTA();
	}

	@Test(priority = 22)  
	@Parameters({ "userType" })
	public void verifyLoginIntiatedEventSubUserBrowseForFreeCTA(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginIntiatedEventSubUserBrowseForFreeCTA();
	}

	@Test(priority = 23)  
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForInvalidDataEmailLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventForInvalidDataEmailLogin();
	}

	@Test(priority = 24)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEventForInvalidDataMobileLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyLoginInitiatedEventForInvalidDataMobileLogin();
	}

	/*
	 * Event : Login Result Navigation : Browse for Free
	 */

	@Test(priority = 25)  
	@Parameters({ "userType" })
	public void LoginResultEventFromBrowseforFreeTwitterUser(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromBrowseforFreeTwitterUser();
	}

	@Test(priority = 26)  
	@Parameters({ "userType" })
	public void LoginResultEventFromBrowseforFreeCTANonSubUser(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromBrowseforFreeCTANonSubUser();
	}

	@Test(priority = 27)  
	@Parameters({ "userType" })
	public void LoginResultEventFromBrowseforFreeCTASubUser(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromBrowseforFreeCTASubUser();
	}

	@Test(priority = 28)
	@Parameters({ "userType" })
	public void LoginResultEventFromBrowseforFreeCTAMobileLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromBrowseforFreeCTAMobileLogin();
	}

	@Test(priority = 29)
	@Parameters({ "userType" })
	public void LoginResultEventFromBrowseforFreeCTAMobileLoginInvalidData(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromBrowseforFreeCTAMobileLoginInvalidData();
	}

	@Test(priority = 30)
	@Parameters({ "userType" })
	public void LoginResultEventFromBrowseforFreeCTAInvalidEmailID(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromBrowseforFreeCTAInvalidEmailID();
	}
	
	/*
	 * Event : Login Result Navigation : Get Premium CTA
	 */

	@Test(priority = 31)
	@Parameters({ "userType" })
	public void LoginResultEventFromGetPremiumCTATwitterLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromGetPremiumCTATwitterLogin();
	}

	@Test(priority = 32)
	@Parameters({ "userType" })
	public void LoginResultEventFromGetPremiumCTAMobileLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromGetPremiumCTAMobileLogin();
	}

	@Test(priority =33)
	@Parameters({ "userType" })
	public void LoginResultEventFromGetPremiumCTAMobileLoginInvlaidData(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromGetPremiumCTAMobileLoginInvlaidData();
	}

	@Test(priority =34)
	@Parameters({ "userType" })
	public void LoginResultEventFromGetPremiumCTAEmailInvlaidData(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.LoginResultEventFromGetPremiumCTAEmailInvlaidData();
	}

	/*
	 * Event : Register Screen Display
	 */

	@Test(priority = 35)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEventFromBrowseForFreeCTAEmailIDLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegisterScreenDisplayEventFromBrowseForFreeCTAEmailIDLogin();
	}

	@Test(priority =36)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEventFromBrowseForFreeCTAMobileLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegisterScreenDisplayEventFromBrowseForFreeCTAMobileLogin();
	}

	@Test(priority = 37)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEventFromProfilePageMobileLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegisterScreenDisplayEventFromProfilePageMobileLogin();
	}

	@Test(priority = 38)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEventFromProfilePageEmailIDLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegisterScreenDisplayEventFromProfilePageEmailIDLogin();
	}

	@Test(priority = 39)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEventFromGetPremiumPageEmailIDLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegisterScreenDisplayEventFromGetPremiumPageEmailIDLogin();
	}

	@Test(priority = 40)
	@Parameters({ "userType" })
	public void verifyRegisterScreenDisplayEventFromGetPremiumPageMobileLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegisterScreenDisplayEventFromGetPremiumPageEmailIDLogin();
	}

	/*
	 * Event : Registration Initiated
	 */

	@Test(priority = 41)
	@Parameters({ "userType" })
	public void verifyRegistrationInitiatedEventFromBrowseForFreeCTAEmailLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationInitiatedEventFromBrowseForFreeCTAEmailLogin();
	}

	@Test(priority = 42)
	@Parameters({ "userType" })
	public void verifyRegistrationInitiatedEventFromBrowseForFreeCTAMobileLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationInitiatedEventFromBrowseForFreeCTAMobileLogin();
	}

	@Test(priority = 43)
	@Parameters({ "userType" })
	public void verifyRegistrationInitiatedEventFromGetPremiumCTAValidEmailLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationInitiatedEventFromGetPremiumCTAValidEmailLogin();
	}

	@Test(priority = 44)
	@Parameters({ "userType" })
	public void verifyRegistrtionInitiatedEventFroGetPremiumCTAMobileLoginInvalidData(String userType)
			throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrtionInitiatedEventFroGetPremiumCTAMobileLoginInvalidData();
	}

	@Test(priority = 45)
	@Parameters({ "userType" })
	public void verifyRegistrationInitiatedEventFromHamburgerMenuValidEmailLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationInitiatedEventFromHamburgerMenuValidEmailLogin();
	}

	@Test(priority = 46)
	@Parameters({ "userType" })
	public void verifyRegistrtionInitiatedEventFromHamburgerMenuMobileLoginInvalidData(String userType)
			throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrtionInitiatedEventFromHamburgerMenuMobileLoginInvalidData();
	}

	/*
	 * Event : Registration Result Navigation : Browse for Free CTA
	 */

	@Test(priority = 47)
	@Parameters({ "userType" })
	public void verifyRegistrationResultEventFromBrowseForFreeCTAValidEmailRegistration(String userType)
			throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationResultEventFromBrowseForFreeCTAValidEmailRegistration();
	}

	@Test(priority = 48)
	@Parameters({ "userType" })
	public void verifyRegistrationResultEventFromBrowseForFreeCTAInvalidMobileDataRegistration(String userType)
			throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunchToIntroScreen(true);
		Zee5ApplicasterMixPanelBusinessLogic
				.verifyRegistrationResultEventFromBrowseForFreeCTAInvalidMobileDataRegistration();
	}
	
	@Test(priority = 49)
	@Parameters({ "userType" })
	public void verifyRegistrationResultEventFromGetPremiumCTAValidEmailLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationResultEventFromGetPremiumCTAValidEmailLogin();
	}

	@Test(priority = 50)
	@Parameters({ "userType" })
	public void verifyRegistrtionResultEventFroGetPremiumCTAMobileLoginInvalidData(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrtionResultEventFroGetPremiumCTAMobileLoginInvalidData();
	}

	@Test(priority = 51)
	@Parameters({ "userType" })
	public void verifyRegistrationResultEventFromHamburgerMenuValidEmailLogin(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrationResultEventFromHamburgerMenuValidEmailLogin();
	}

	@Test(priority = 52)
	@Parameters({ "userType" })
	public void verifyRegistrtionResultEventFromHamburgerMenuMobileLoginInvalidData(String userType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyRegistrtionResultEventFromHamburgerMenuMobileLoginInvalidData();
	}

	/*
	 * Event : Subscription Selected
	 */

	@Test(priority = 53)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEvent(userType);
	}

	@Test(priority = 54)
	@Parameters({ "userType" })
	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		System.out.println("Verify Subscription Selected Event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.verifySubscriptionSelectedEventByClubPack(userType);
	}

	/*
	 * Event : Prepaid Code Result
	 */
	@Test(priority = 55)
	@Parameters({ "userType" })
	public void verifyPrepaidCodeResultEvent(String userType) throws Exception {
			System.out.println("Verify Prepaid Code Result event");
			Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
			Zee5ApplicasterMixPanelBusinessLogic.verifyPrepaidCodeResultEvent();
	}

	/*
	 * Event : Promo Code Result
	 */

	@Test(priority = 56)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Valid code");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEventForValid(userType);
	}

	@Test(priority = 57)
	@Parameters({ "userType" })
	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		System.out.println("Verify Promo Code Result Event For Invalid code");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPromoCodeResultEventForInvalid(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}