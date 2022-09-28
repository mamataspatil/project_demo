package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_AR31_SubTasks {
	
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Android App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)//Sushma
	@Parameters({ "userType"})
	public void WhatsappOptIn_UnRegisteredUser_RegistrationScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.whatsApp_UnRegisteredUser(userType);
	}
	
	@Test(priority = 2)//Sushma
	@Parameters({ "userType"})
	public void WhatsappOptIn_RegisteredUser_EditProfileScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.whatsApp_RegisteredUser(userType);
	}
	
	@Test(priority = 3)//Sushma
	@Parameters({ "userType"})
	public void Validation_FloatingBannerAndSmallIcon_HomeScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		//ZEE5ApplicasterBusinessLogic.floatingBannerAndSmallIconOnHomeScreen(userType);
		
	}
	
	@Test(priority = 4)//Sushma
	@Parameters({ "userType"})
	public void Validation_CollapsingFloatingBanner_BrowsingFromTopToBottom_HomeScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		//ZEE5ApplicasterBusinessLogic.whatsAppBannerCollapseWhileBrowsingPageFromTopToBottom(userType);
	}
	
	@Test(priority = 5)//Sushma
	@Parameters({ "userType", "WhatsappOptInDeeplinkUrl"})
	public void Deeplinking(String userType, String url) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.navigationToHomePageOrLoginOrRegisterPageThrough_Deeplinking(userType, url);
	}
	
	@Test(priority = 5)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfHipiLogoAndDurationOnSubscriptionPlan(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.hipiLogoAndDurationOnSubscriptionPlan(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}
