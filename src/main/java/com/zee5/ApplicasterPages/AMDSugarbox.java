package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDSugarbox {
	
	public static By objSugrBxZoneNotification = By.id("sugar_box_location_title");
	
	public static By objSubTitleNotification = By.id("sugar_box_location_sub_title");
	
	public static By objLocateBtn =  By.id("btn_sugar_box_locate");
	
	public static By objDenyLocationPopup =  By.id("permission_deny_button");
	
	public static By objSugarboxLogo =  By.id("home_sugarBox_toggle_icon");
	
	public static By objLearnMore =  By.xpath("//*[@id='connectToSugarBox']/following::*[@text='Learn More']");
	
	public static By objEnjoyZEE5Txt = By.xpath("//*[@text='Enjoy ZEE5 at ZERO data cost with SugarBox']");
	
	public static By objEnterMobNumTV =  By.id("enterMobileNumberTV");
	
	public static By objMobNumField =  By.id("mobileEditText");
	
	public static By objContinueBtn =  By.id("submitBtn");
	
	public static By objHeadeTitle =  By.id("text_header_title");
	
	public static By objHeadeDesc =  By.id("text_header_info_description");
	
	public static By objDontShowCheckBx =  By.id("checkBox_use_mobile_data");
	
	public static By objContinueCTA =  By.id("checkBox_use_mobile_data");
	
	public static By objSubmitOTP =  By.id("submitOtpBtn");
	
	public static By objCancelCTA =  By.id("text_cancel");
	
	public static By objContentTitle =  By.id("content_title");
	
	public static By objOTPScreen = By.id("otpText");
	
	public static By objOTPField(String index) {
		return By.xpath("//*[@id='otpET"+index+"']");
	}
	
	public static By objContinueNext = By.id("btn_next");
	
	public static By objSkip = By.id("btn_skip");
	
	public static By objOnboardingText = By.id("onboardingText3");
	
	public static By objOnboardingScreen = By.xpath("//*[@text='Get the Best of ZEE5 experience']");
	
	public static By objContentCard(String railName, int index) {
		return By.xpath("(//*[@text='"+railName+"']//following::*[@class='android.widget.ImageView'])["+index+"]");
	}
	
	public static By objSugrBoxWifi = By.xpath("//*[@text='SugarBox']");
	
	public static By objVideoNotAvailableContinue = By.id("button_continue");
	
	public static By objExitSBNetwrkPopup = By.xpath("//*[@text='Are you sure you want to disconnect from SugarBox ?']");
	
	public static By objCloseImg = By.id("image_close");
	
	public static By objDisconnetced = By.id("text_disconnect_header");
	
	public static By objLocateNearBySB = By.id("button_locate_sugarBox_zone");
	
	public static By objListofZones = By.id("sbDpName");
	
	public static By SBLogoWelcomeScreen = By.id("img_sb_logo");
	
	public static By SBLogoPopup = By.id("img_sugarBox_logo");
	
	public static By objContinueToSugarBoxCTA = By.xpath("//*[@id='button_continue' and @text='Continue to SugarBox']");

	public static By objAllowLocationPopup =  By.xpath("//*[@id='permission_allow_button']| //*[@id='permission_allow_foreground_only_button']");
	public static By objConnectToSugarBox =  By.xpath("//*[@id='connectToSugarBox']");
}
