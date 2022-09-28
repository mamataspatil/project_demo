package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Kushal

public class AMDOnboardingScreen {
	public static By objTellUsMore = By.xpath("//*[@text='Tell us more']");
	public static By objPermissionMsg = By.xpath("//*[@id='permission_message']");
	public static By objAllowBtn = By.xpath("//*[@id='permission_allow_button']");
	public static By objDenyBtn = By.xpath("//*[@id='permission_deny_button']");
	public static By objFeatureCarousel = By.xpath("//*[@id='zee5RailsTypeCarouselFragmentId']");
	public static By objBenefitsTextSection = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/benefitsTextRecyclerView']");
	public static By objDotsIndicator = By.xpath("(//*[@id='dotsIndicator']/*[@class='android.widget.ImageView'])");
	// Selected Display Language
	public static By objSelectedDisplayLang = By
			.xpath("//*[@id='selectionImage']//following::*[@id='display_language_content'][1]");
 
	// Get Screen Title
	public static By objScreenTitle = By.xpath("//*[@id='screen_title']");

	// Back Icon
	public static By objBackBtn = By.xpath("//*[@id='icon_back']");

	
	public static By objHindiLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Hindi']");
	public static By objMarathiLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Marathi']");
	public static By objTeluguLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Telugu']");
	public static By objTamilLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Tamil']");
	public static By objMalayalamLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Malayalam']");
	public static By objBengaliLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Bengali']");
	public static By objGujaratiLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Gujarati']");
	public static By objPunjabiLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Punjabi']");
	public static By objBhojpuriLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Bhojpuri']");
	public static By objOriyaLanguage = By.xpath("//*[@id='btn_content_lang' and @text='Oriya']");
	
	
	// To Select the Display language
	public static By objSelectDisplayLang(String Language) {
		int index = 0;
		if (Language.equalsIgnoreCase("Hindi")) {
			index = 1;
		} else if (Language.equalsIgnoreCase("English")) {
			index = 2;
		} else if (Language.equalsIgnoreCase("Marathi")) {
			index = 3;
		} else if (Language.equalsIgnoreCase("Telugu")) {
			index = 4;
		} else if (Language.equalsIgnoreCase("Kannada")) {
			index = 5;
		} else if (Language.equalsIgnoreCase("Tamil")) {
			index = 6;
		} else if (Language.equalsIgnoreCase("Malayalam")) {
			index = 3;
		} else if (Language.equalsIgnoreCase("Bengali")) {
			index = 4;
		} else if (Language.equalsIgnoreCase("Gujarati")) {
			index = 5;
		} else if (Language.equalsIgnoreCase("Punjabi")) {
			index = 6;
		} else if (Language.equalsIgnoreCase("Bhojpuri")) {
			index = 7;
		}
		return By.xpath("(//*[@id='display_language_content'])[" + index + "]");
	}

	public static By objContentLanguageContainer = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/content_lang_container']");
	public static By objContentLanguagePageTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/content_lang_screen_title']");
	// Continue button in the Display language screen
	public static By objDiplay_ContinueBtn = By.xpath("//*[@id='dl_language_selection']");

	// Select content language
	public static By objSelectContentLang(String language) {
		return By.xpath("//*[@id='btn_content_lang' and @text='" + language + "']");
	}

	// Continue button in the Content language screen
	public static By objContent_ContinueBtn = By.xpath("//*[@id='btn_content_language_selection']");

	// Login OR Skip Button in Intro Screen
	public static By objLoginLnk = By.xpath("//*[@id='skip_link']");

	public static By objLabels(String text) {
		return By.xpath("//*[@id='becomeATextView' and contains(text(),'" + text + "')]");
	}

	public static By objDotsIndicator(int index) {
		return By.xpath(
				"//*[@id='dotsIndicator']//following-sibling::*[@class='android.widget.ImageView'][" + index + "/]");
	}

	public static By objBrowseForFreeBtn = By.xpath("//*[@id='browseForFreeButton']");

	public static By objSubscribeNowBtn = By.xpath("//*[@id='joinNowButton']");

	public static By objHavePrepaidBtn = By.xpath("//*[@id='haveACouponCodeButton']");

	public static By objPrepaidPopupLabel = By.xpath("//*[@id='txt_prepaid_code']");

	public static By objPrepaidCodeField = By.xpath("//*[@id='edit_prepaid_code']");

	public static By objPopUpDivider = By.xpath("//*[@id='dialog_divider']");

	// What is Prepaid code? button in the Prepaid code Pop-up
	public static By objWhatIsPrepaidCodeBtn = By.xpath("//*[@id='txt_what_is_promo_code']");

	// Get description from About Prepaid Code
	public static By objDescriptionTxt = By.xpath("//*[@id='description']");

	// Apply Button
	public static By objApplyBtn = By.xpath("//*[@id='btn_promo_code_next']");

	// Pop-up screen elements
	public static By objFaceIcon = By.xpath("//*[@id='iconSmile']");
	public static By objSuccessTitle = By.xpath("//*[@id='txt_success_title']");
	public static By objSuccessDesc = By.xpath("//*[@id='txt_success_desc']");
	public static By objLoginBtn = By.xpath("//*[@id='btn_dialog_login']");
	public static By objRegisterBtn = By.xpath("//*[@id='btn_dialog_register']");
	public static By objDoneBtn = By.xpath("//*[@id='btn_dialog_done']");
	public static By objWatchNowBtn = By.xpath("//*[@id='btn_dialog_watch_now']");

	// Pop-up screen title
	public static By objSuccessTitle(String popupTitle) {
		return By.xpath("//*[@id='txt_success_title' and @text='" + popupTitle + "']");
	}

	public static By objNextBtnPopUpInCalender = By.xpath("//*[@id='next']");

	public static By objNextDate(String date) {
		return By.xpath("//*[@text='" + date + "']");
	}

	public static By objWaitForSplashScreenDisapear = By.xpath(
			"//*[@id='permission_allow_button'] | //*[@id='browseForFreeButton'] | //*[@id='title' and @text='Home']");
	/**
	 * Kushal
	 */

	public static By objContentLangBtns = By.xpath("//*[@id='btn_content_lang']");

	public static By objgetContentLangName(int index) {
		return By.xpath("(//*[@id='btn_content_lang'])[" + index + "]");
	}

	public static By objContinueBtnInDebugBuild = By.xpath("//*[@resource-id='com.graymatrix.did:id/continue_button']");
	
	public static By objContinueBtnInCountryPopUp = By.xpath("//*[@resource-id='com.graymatrix.did:id/country_selection_button']");
	
	public static By objCarouselInIntroScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/zee5RailsTypeCarouselFragmentId']");
	
	public static By objContinueBtnInLanguagePopUp = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_content_language_selection']");
	
	public static By objFirstPermissionButton = By.xpath("(//*[@class='android.widget.Button'])[1]");
	public static By objSecondPermissionButton = By.xpath("(//*[@class='android.widget.Button'])[2]");	
	public static By objAllow(String str){
		return By.xpath("//*[@text='"+str+"']");
	}
	
	public static By objAllowLocationAccessPopup = By.xpath("//*[@id='permission_message']");
	
	public static By objExitPopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/verify_account_desc'] | //*[@resource-id='com.graymatrix.did:id/title']");

	public static By objExitYes = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_exit_yes'] | //*[@resource-id='com.graymatrix.did:id/accept']");

	public static By objExitNo = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_exit_no'] | //*[@resource-id='com.graymatrix.did:id/dismiss']");
	
	public static By objExitPopupDivider = By.xpath("//*[@resource-id='com.graymatrix.did:id/dialog_divider'] | //*[@id='pillView']");

	public static By objExitPopupHorizontalLinebar = By.xpath("(//*[@class='android.view.View'])[2] | //*[@id='pillView']");

	public static By objSelectDisplayLanguage(int index) {
		return By.xpath("(//*[@id='display_language_content'])[" + index + "]");
	}
	
	public static By objExitPopUpTitle = By.xpath("//*[@id='tv_title']");
	public static By objExitPopUpDesc = By.xpath("//*[@id='tv_desc']");
	public static By objExitPopUp_CancelCTA = By.xpath("//*[@id='btn_cancel']");
	public static By objCancelZEE5Update = By.xpath("//*[@id='0_resource_name_obfuscated' and @text='NO THANKS']");
	public static By objUpdateZEE5UpdateApp= By.xpath("//*[@id='0_resource_name_obfuscated' and @text='UPDATE']");
	public static By objExitPopUp_ConfirmCTA = By.xpath("//*[@id='btn_continue']");
	
	public static By objUpdateZee5PopUpNOTHANKSButton = By.xpath("//*[@text='NO THANKS']");
	
	public static By ele1Allow(String str){
		return By.xpath("//*[@text='"+str+"']");
	}

	public static By objZeeMoreButton = By.xpath("//*[contains(@resource-id,'navigationTitleTextView') and @text='More']");	
	
	public static By objZeeLoginRegisterLink = By.xpath("//*[@text='Login/Register for best experience']");
	
	public static By AcceptVmaxAppAlert = By.xpath("//*[@id='continue_button']");
	
	public static By zeeSkip = By.xpath("//*[@id='skip_link']");
	
	public static By objUpdatePopUpNoThanksOption = By.xpath("//*[contains(text(), 'NO THANKS')]");
	public static By objZeeMorebtn1 = By.xpath("(//*[@id='navigationTitleTextView' and @text='More']) | //*[@id='home_toolbar_more_icon']");
}

