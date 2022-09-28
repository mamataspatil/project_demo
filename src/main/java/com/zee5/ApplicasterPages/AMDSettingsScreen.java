package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDSettingsScreen {

	public static By objSelectVideoQualityLabel = By.xpath("//*[@id='selector_screen_title']");
	public static By objVideoQualityBest = By.xpath("//*[@text='Best']");
	public static By objVideoQualityBetter = By.xpath("//*[@text='Better']");
	public static By objVideoQualityGood = By.xpath("//*[@text='Good']");
	public static By objVideoQualityDatasaver = By.xpath("//*[@text='Datasaver']");
	public static By objVideoQualityAskEachTime = By.xpath("//*[@text='Ask each time']");
	public static By objXButton = By.xpath("//*[@text='D']");
	public static By objTickMark = By.xpath("//*[@id='selectionImageSelector']");
	public static By objDownloadOverWifiToggle = By.xpath("//*[@id='downloadOverWifiSwitch']");

	public static By objSettingsScreenTitle = By.xpath("//*[@id='screen_title' and //*[@text='Settings']]");

	public static By objClearSearchHistory = By.xpath("//*[@id='searchHistoryAction']");
	public static By objSearchHistory = By.xpath("//*[@id='searchHistoryLabel']");
	public static By objResetSettings = By.xpath("//*[@id='resetSettingLabel']");
	public static By objDefaultSetting = By.xpath("//*[@id='resetSettingAction']");
	public static By objResetSettingPopUp = By.xpath("//*[@text='Are you sure you want to reset your settings?']");
	public static By objNoCTA = By.xpath("//*[@id='btn_exit_no']");
	public static By objYesCTA = By.xpath("//*[@id='btn_exit_yes']");
	public static By objAuthenticateDevice = By.xpath("//*[@id='authenticateDevice']");
	public static By objAuthenticateScreen = By.xpath("//*[@text='Authenticate Device']");
	public static By objAuthenticateCloseBtn = By.xpath("//*[@id='icon_exit']");
	public static By objLoadingAnimator = By.xpath("//*[@class='android.widget.ProgressBar']");
	public static By objUpdateSettingsMessage = By.xpath("//*[@id='txt_progress']");
	public static By objVideoQualityDefaultvalue = By
			.xpath("//*[@id='videoStreamingContainer']//*[@id='qualityPixels']");
	public static By objAutoPlayToggleON = By.xpath("//*[@id='autoPlaySwitch'][@text='ON' or 'On']");
	public static By objAutoPlayToggleOFF = By.xpath("//*[@id='autoPlaySwitch'][@text='OFF' or 'Off']");
	public static By objAutoPlayToggleSwitch = By.xpath("//*[@id='autoPlaySwitch']");
	public static By objVideoQualitySelectedOptionOnPlayer = By
			.xpath("//*[@id='popuplistitem' and ./*[@id='icon_selected']]");

	public static By objSelectVideoQualityInLandscapeMode = By.xpath(
			"(//*[@id='playerOptionsTitleTextView' and @text='Select Video Quality']//following::*[@id='playerOptionTitle'])[2]");


	public static By objDownloadVideoQualityOptions(String option) {
		return By.xpath("//*[@id='selector_content' and @text='" + option + "']");
	}

	public static By objStreamOverWifiValue = By.xpath("//*[@id='videoOverWifiSwitch']");
	public static By objDownloadQualityValue = By.xpath("//*[@id='downloadLabel']");
	public static By objDisplayLangValue = By.xpath("//*[@id='displayLanguageValue']");
	public static By objContentLangValue = By.xpath("//*[@id='contentLanguageValue']");

	public static By objDownloadOverWifiOFF = By.xpath("//*[@id='downloadOverWifiSwitch' and @text='OFF']");

	public static By objDownloadOverWifiON = By.xpath("//*[@id='downloadOverWifiSwitch' and @text='ON']");

	public static By objParentalControloption = By.xpath("//*[@resource-id='com.graymatrix.did:id/parentalControl']");

	// No Restrictions
	public static By objNoRestrictions = By.xpath("//*[@id='agelimit_text' and @text='No Restriction']");
	
	// Restrict U/A 16+ Content
	public static By objRestrictUA16Content = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/agelimit_text' and @text='Restrict U/A 16+ Content']");
	// Restrict U/A 13+ Content
	public static By objRestrictUA13Content = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/agelimit_text' and @text='Restrict U/A 13+ Content']");
	// Restrict U/A 7+ Content
	public static By objRestrictUA7Content = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/agelimit_text' and @text='Restrict U/A 7+ Content']");
	// Restrict All Content
	public static By objRestrictAllContent = By.xpath("//*[@id='agelimit_text' and @text='Restrict All Content']");
	// Parental Control title in the centre of the Header
	public static By objParentalControlTitleCentreOfTheHeader = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title']");
	// Back button should be displayed at the top left corner of the screen.
	public static By objBackButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_back']");
	// Set a parental lock to block your child from accessing certain content"
	// should be displayed at the centre of the screen, below the Header.
	public static By objSetAParentalLockToBlockYourChildFromAccessingCertainContent = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/selector_screen_title']");
	// Continue CTA
	public static By objContinueCTA = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/btn_parental_control_continue']");
	// Select Option
	public static By objSelectOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/selectionImageSelector']");

	// Done Button
	public static By objDoneButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/dialog_done']");

	// Success!
	public static By objSuccessPage = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_success_title']");

	// Parental control settings applied successfully
	public static By objParentalControlSettingsAppliedSuccessfully = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/txt_success_desc']");

	// Set PIN
	public static By objSetPIN = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_verify_email_account_header']");

	// Enter new 4-digit PIN
	public static By objEnterNew4DigitPIN = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/verify_email_account_desc']");

	// Show PIN
	public static By objShowPIN = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_showpin']");

	public static By objContinueCTAVerifyAccount = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/btn_verify_email_continue']");

	// Continue Button Set PIN page
	public static By objContinueButtonSetPINpage = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/btn_set_pin_continue']");

	// Enter PIN Text Feiled
	public static By objEnterPINTextFeiled = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/editTextsLinearLayout']");

	public static By objPasswordfield = By.xpath("//*[@resource-id='com.graymatrix.did:id/txtET_password_input']");
	
	public static By objRestrictA18Content = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/agelimit_text' and @text='Restrict A 18+ Content']");
	

public static By objQualityOptionOnPlayer = By.xpath("//*[contains(text(),'Quality')] | //*[@text='q']//following-sibling::*[1]");
	public static By objoptionsInVideoQuality = By.xpath("//*[contains (text(),'DataSaver')]");
	
	public static By objCloseIconOnQualityOptionInPlayer = By.xpath("//*[@class='android.view.View']//*[@text='H']"); 
}