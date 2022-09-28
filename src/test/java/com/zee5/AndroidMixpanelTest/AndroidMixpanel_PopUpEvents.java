package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_PopUpEvents {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({"userType"})
	public void PopupLaunchEventValidationForLogout(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventValidationForLogout(pUserType);
	}
	
	@Test(priority = 2)
	@Parameters({"userType"})
	public void verifyPopupLaunchEventForParentalRestrictionSetting(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupLaunchEventForParentalRestrictionSetting(pUserType);
	}
	
	@Test(priority = 3)
	@Parameters({"userType", "trailerContent"})
	public void verifyPopupLaunchEventForParentalRestriction(String pUserType,String trailerContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupLaunchEventForParentalRestriction(pUserType,trailerContent);
	}
		
	
	@Test(priority = 4)
	@Parameters({"userType"})
	public void PopupLaunchEventForAccountInfoPopUp(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForAccountInfoPopUp(pUserType);
	}
	
	@Test(priority = 5)
	@Parameters({"userType"})
	public void PopupLaunchEventForHaveAPrepaidCode(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForHaveAPrepaidCode(pUserType);
	}
	
	@Test(priority = 6)
	@Parameters({"userType"})
	public void verifyPopupLaunchEventForDefaultSettingRestoredEvent(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupLaunchEventForDefaultSettingRestoredEvent(pUserType);
	}
	
	@Test(priority = 7)
	@Parameters({"userType","audioTrackContent"})
	public void PopupLaunchEventForSubtitleLanguageSelection(String pUserType,String audioTrackContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForSubtitleLanguageSelection(pUserType,audioTrackContent);
	}
	
	@Test(priority = 8)
	@Parameters({"userType","audioTrackContent"})
	public void PopupLaunchEventForAudioLanguageSelection(String pUserType,String audioTrackContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupLaunchEventForAudioLanguageSelection(pUserType,audioTrackContent);
	}
	
	@Test(priority = 9)
	@Parameters({"userType","audioTrackContent"})
	public void PopupCTAsEventForAudioLanguageSelection(String pUserType,String audioTrackContent) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupCTAsEventForAudioLanguageSelection(pUserType,audioTrackContent);
	}
	
	
	@Test(priority = 10)
	@Parameters({"userType"})
	public void PopUpCTAsEventForAfterApplyingPrepaidCode(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopUpCTAsEventForAfterApplyingPrepaidCode(pUserType);
	}
	
	
	@Test(priority = 11)
	@Parameters({"userType"})
	public void PopUpCTAsEventForInvalidPrepaidCodePopup(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopUpCTAsEventForInvalidPrepaidCodePopup(pUserType);
	}
	
	@Test(priority = 12)
	@Parameters({"userType"})
	public void verifyPopupCTAsEventForDefaultSettingRestoredEvent(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupCTAsEventForDefaultSettingRestoredEvent(pUserType);
	}
	
	@Test(priority = 13)
	@Parameters({"userType"})
	public void verifyPopupCTAsEventForParentalRestrictionSetPin(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupCTAsEventForParentalRestrictionSetPin(pUserType);
	}
	
	@Test(priority = 14)
	@Parameters({"userType"})
	public void verifyPopupCTAsEventForParentalRestrictionDone(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterMixPanelLoginForParentalControl(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.verifyPopupCTAsEventForParentalRestrictionDone(pUserType);
	}
	
	@Test(priority = 15)
	@Parameters({"userType"})
	public void PopupCTAEventValidationForLogout(String pUserType) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PopupCTAEventValidationForLogout(pUserType);
	}
	
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
