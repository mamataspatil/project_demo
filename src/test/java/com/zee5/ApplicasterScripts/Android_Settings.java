package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Settings {
	
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLoginForSettings(userType);
	}
	
	@Test(priority = 1)
	@Parameters({ "userType", "searchKeyword1"})
	public void SettingsScreen_VideoQualityCheck(String userType, String searchKeyword1) throws Exception {
		ZEE5ApplicasterBusinessLogic.settings_DefaultVideoStreamingQuality(userType, searchKeyword1);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType","searchKeyword11" })
	public void SettingsScreen_VideoQualityCheckInPlayer(String userType, String searchKeyword11) throws Exception {
		ZEE5ApplicasterBusinessLogic.settings_DefaultVideoStreamingQualityInPlayer(userType, searchKeyword11);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType" })
	public void SettingsScreen_Validation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.settingsScreenValidation(userType);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType" })
	public void SettingsScreen_VideoStreamingValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.videoStreamingValidation(userType);
	}
	
	@Test(priority = 5)
	public void Settings_DownloadSettingsValidation() throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.downloadSettingsValidation();
	}
	
	@Test(priority = 6)
	@Parameters({ "userType","searchKeyword1"})
	public void Settings_DownloadOverWifiOnlyValidation(String userType, String searchKeyword1) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.downloadOverWiFiOnlyONValidation(userType, searchKeyword1);
	}
	
	@Test(priority = 7)
	@Parameters({"displayLanguageSelection1", "displayLanguageSelection2" })
	public void Settings_DisplayLanguageValidation(String displayLanguageSelection1, String displayLanguageSelection2) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.settings_Language(displayLanguageSelection1, displayLanguageSelection2);
	}
	
	@Test(priority = 8)
	@Parameters({"userType" })
	public void Settings_SearchHistoryValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.searchHistoryValidation(userType);
	}
	
	@Test(priority = 9)
	@Parameters({"userType" })
	public void Settings_AuthenticateDeviceValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.authenticateDeviceValidation(userType);
	}
	
	@Test(priority = 10)
	@Parameters({"userType" })
	public void Settings_ResetSettingValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.resetSettingsValidation(userType);
	}
	
	@Test(priority = 11)
	@Parameters({"userType","searchKeyword"})
	public void Settings_ParentalPinValidation(String userType, String searchKeyword) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
		ZEE5ApplicasterBusinessLogic.parentalPinValidation(userType, searchKeyword);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}
