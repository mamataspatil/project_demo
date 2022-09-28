package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvWelcomePage {

	public static By objWelcomeSkipLink = By.xpath("//*[@id='explore']");
	
	
	public static By objzeelogoLaunch = By.xpath("//*[@id='gif_animation_view']");
	public static By objalreadyRegister = By.xpath("//*[@id='already_register' or @id='register_ZEE']");

	public static By objLogiButtonAmazon = By.xpath("//*[@id='login_zee5_button']");

	public static By objLoginPopupAmazon = By.xpath("//*[@id='lwa_title']");

	public static By objauthenticatetext = By.xpath("//*[@id='authenticate_text']");
	public static By objloginCode = By.xpath("//*[@id='code_text']");
	
	public static By objloginCodeNew = By.xpath("//*[@id='device_activation_code']");
	public static By objLoginTimer = By.xpath("//*[@id='device_activation_refresh_timer']");
	public static By objNoThanks = By.xpath(".//*[text()='No thanks']");
	
	public static By objauthenticatePagePhone = By.xpath("//*[@id='phone']");

	public static By objsettingsscreenRow = By.xpath("//*[@id='settingsicon_gridview' and @index='2']");
	public static By objHomepageTrayContent = By.xpath(
			"(//*[@id='row_header' and  contains(text(),'Top') and contains(text(),'10')]//parent::*//parent::*//following-sibling::*//child::*//child::*//child::*)[4]");
	public static By objHomepageFreeTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Movies') and contains(text(),'Free')]//parent::*//parent::*//following-sibling::*//child::*//child::*//child::*)[1]");

	public static By objwatchlistTray = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Watchlist')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*//child::*[@id='main_image'])[1]");
	public static By objContinuewatchingTrayImage = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Continue')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*//child::*[@id='main_image'])[1]");
	public static By objBeforeTVTray = By.xpath(
			"	(//*[@id='row_header' and contains(text(),'Before')]//parent::*//parent::*//following-sibling::*//child::*//child::*//child::*)[1]");
	public static By objshowpageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Trending') or contains(text(),'Top')]//parent::*//parent::*//following-sibling::*//child::*//child::*//child::*)[2]");
	

	
	public static By objshowpageTrayContentClub = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Top')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objLandingshowpageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Top')]//parent::*//parent::*//following-sibling::*//child::*//child::*//child::*)[2]");

	public static By objViewAllPremium = By.xpath("//*[@id='premium_tag']");

	public static By objMoviePageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Trending') or contains(text(),'Top Zee ') or contains(text(),'For You')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objLandingMoviePageTrayContent = By.xpath(
			"(//*[@id='row_header' and  contains(text(),'Popular')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objPremiumPageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text() ,'Premium')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objLandingPremiumPageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text() ,'Top')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objVideoPageTrayContentPlayback = By.xpath(
			"(//*[@id='row_header' and contains(text() ,'Latest on')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objVideoPageTrayContent = By.xpath(
			"(//*[@id='row_header' and contains(text() ,'Top')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objLiveNewsContentinNewsPage = By.xpath(
			"(//*[@id='row_header' and contains(text() ,'Live News')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objLandingNewsContentinNewsPage = By.xpath(
			"	(//*[@id='row_header' and contains(text() ,'Entertainment News')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objLandingNewsContentinVideosPage = By.xpath(
			"	(//*[@id='row_header' and contains(text() ,'Latest')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");
	public static By objcontinueButtonInLoginPage = By.xpath("//*[@id='continue_button_reg']");

	public static By objContentTitleIncontentPage = By.xpath("(//*[@id='txv_title'])[1]");

	public static By objContentDescriptionIncontentPage = By.xpath("//*[@id='txv_description']");
	public static By objContentdurationIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[6]");
	public static By objContentyearIncontentPage = By.xpath("	(//*[@class='android.widget.TextView'])[7]");
	public static By objContentcertificateIncontentPage = By.xpath("//*[@id='content_age_rating']");
	public static By objContentTypeIncontentPage = By.xpath("//*[@id='content_pre_tag']");
	public static By objContentgenreIncontentPage = By.xpath("//*[@id='content_tags']");
	public static By objContenttrailerplaybackIncontentPage = By.xpath("//*[@id='player_gradient_left']");

	public static By objContentShowTypeIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[2]");
	public static By objContentShowgnereIncontentPage = By.xpath("	(//*[@class='android.widget.TextView'])[3]");
	public static By objContentShowcertificateIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[4]");
	public static By objContentShowyearIncontentPage = By.xpath("(//*[@class='android.widget.TextView'])[5]");

	public static By objContinueWatchingTray = By.xpath("//*[@id='row_header' and @text='Continue Watching']");
	public static By objMyWatlistTray = By.xpath("//*[@id='row_header' and @text='Watchlist']");

	public static By objViewallButton = By.xpath("//*[@text='View ALL' or @id='viewAll']");

	public static By objViewallPageHead = By.xpath("//*[@id='details_main_head']");

	public static By objYoumayLike = By.xpath("//*[@id='header_title']");
	public static By objAddtoWatchlist = By
			.xpath("(//*[@id='detail_button_text' and @text='Add to Watchlist']//parent::*//child::*)[1]");

	public static By objRemoveWatchlist = By.xpath("(//*[@id='detail_button_icon'])[3]");

	public static By objPremiumTag(int str) {
		return By.xpath("(//*[@id='premium_tag'])[" + str + "]");
	}

	public static By objViewallTray = By.xpath(
			"(//*[@id='row_header' and contains(text(),'Top ZEE5')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");

	public static By objNameColumn = By.xpath("//*[@id='profile_name']");
	public static By objEmailIdColumn = By.xpath("//*[@id='profile_id_view']");
	public static By objMobileNumberColumn = By.xpath("//*[@id='profile_number_view']");
	public static By objDOBColumn = By.xpath("//*[@id='profile_date_of_birth']");
	public static By objGenderColumn = By.xpath("//*[@id='profile_gender']");

	public static By objEditProfileInMyProfile = By.xpath("//*[@id='profile_tv_edit_button']");

	public static By objSaveButton = By.xpath("//*[@id='profile_tv_save_button']");

	public static By objEditProfilepage = By.xpath("//*[@text='EDIT PROFILE']");

	public static By objNameText = By.xpath("(//*[@id='profile_name']//child::*)[2]");
	public static By objProfileOptionInSettingPage = By
			.xpath("//*[contains(text(),'MY PROFILE') or contains(text(),'My Profile')]//parent::*/child::*[@id='icon_image']");

	public static By objVerison = By.xpath("//*[@id='tv_settings_version']");
	public static By objParentalOption = By
			.xpath("//*[contains(text(),'PARENTAL CONTROL') or contains(text(),'Parental Control')]//parent::*/child::*[@id='icon_image']");

	public static By objLogoutOption = By.xpath("//*[contains(text(),'Logout') or contains(text(),'LOGOUT')]//parent::*/child::*[@id='icon_image']");
	public static By objLoginOption = By.xpath("//*[contains(text(),'Login') or contains(text(),'LOGIN')]//parent::*/child::*[@id='icon_image']");
	public static By objVideoqualityOption = By
			.xpath("//*[contains(text(),'Video Quality') or contains(text(),'VIDEO QUALITY')]//parent::*/child::*[@id='icon_image']");
	public static By objreminderOption = By.xpath("(//*[@text='REMINDERS']//parent::*)[1]//child::*[@id='icon_image']");

	public static By objMyPlanOption = By
			.xpath("//*[contains(text(),'My Plan') or contains(text(),'MY PLAN')]//parent::*/child::*[@id='icon_image']");

	public static By objALLPlanOption = By.xpath("//*[contains(text(),'ALL PLAN') or contains(text(),'All plan')]//parent::*/child::*[@id='icon_image']");
	public static By objnoreminder = By.xpath("//*[@id='no_reminder_msg']");

	public static By objreminderLayout = By.xpath("//*[@id='reminder_vertical']");
	public static By objVideoQualityHeader = By.xpath("//*[@id='videoQualityheading']");

	public static By objZeelogo = By.xpath("//*[@id='zee_image']");

	public static By objwelcomescreenmessage1 = By.xpath("//*[@id='welcome_msg_one']");
	public static By objwelcomescreenmessage2 = By.xpath("//*[@id='welcome_msg_two']");

	public static By objVideoQualityResolutionOptions = By.xpath("//*[@id='radiobtn1']");
	public static By objVideoQualityResolutionOptions2 = By.xpath("//*[@id='radiobtn2']");
	public static By objVideoQualityResolutionOptions3 = By.xpath("//*[@id='radiobtn3']");
	public static By objVideoQualityResolutionOptions4 = By.xpath("//*[@id='radiobtn4']");
	public static By objVideoQualityResolutionOptions5 = By.xpath("//*[@id='radiobtn5']");

	public static By objMyProfilePageheader = By.xpath("//*[@id='my_account_title']");

	public static By objParentpopuptitle = By.xpath("//*[@id='parental_title']");
	public static By objParentalControlMessage = By.xpath("//*[@id='parentalcontrol_site']");

	public static By objChooseLanguagePopup = By
			.xpath("//*[@id='language_title1' and @text='Choose your preferred display language']");

	public static By objChooseLanguagePopupContinue = By.xpath("//*[@id='continue_button_lang']");

	public static By objSelectedOption = By.xpath("//*[@id='language_cb' and @checked='true']");
	

	public static By objLoginIcon = By.xpath("//*[@id='icon_title' and @text='LOGIN']");

	public static By objMenuTopIcon = By.xpath("//*[@id='collection_header_label']");

	public static By objMenuTopParticular(int str) {
		return By.xpath("(//*[@id='collection_header_label'])[" + str + "]");
	}

	public static By objApiRecoTray(String trayname) {
		return By.xpath("//*[@id='row_header' and contains(text(),'" + trayname + "')]");
	}
	
	public static By objtitleInViewall = By.xpath("//*[@id='detail_title']");
	
			public static By objRowHeader = By.xpath("(//*[@id='row_header'])[1]");	
			
			public static By objrownameindetailpage = By.xpath("//*[@id='header_title']");	

	public static By objHomeTabTrending = By.xpath("//*[@id='row_header' and contains(text(),'Trending on')]");
	
			public static By objcontenttitleindetail = By.xpath("(//*[@id='movie_title_detail' or @id='show_title-detail'])[1]");

	public static By objShowsTabTrending = By.xpath("//*[@id='row_header' and contains(text(),'Trending')]");

	public static By objRecommendForYou = By
			.xpath("(//*[@id='row_header' and contains(text(),'For You') or contains(text(),'for You')])[1]");

	public static By objRecommendForYouContentDetail = By.xpath(
			"(//*[@id='header_title' and contains(text(),'For You') or contains(text(),'for You') or contains(text(),'You')])[1]");

	public static By objAuthenticateErrorMessage = By.xpath("//*[@id='authenticate_error_message']");

	public static By objAuthenticateNewCodeButton = By.xpath("//*[@id='new_code_button']");

	public static By objApiContent(String str) {
		return By.xpath("(//*[@id='row_header' and contains(text(),'" + str
				+ "')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*[@id='main_image'])[2]");
	}

	public static By objContentfromApi(String contentName) {
		return By.xpath("//*[@id='home_title' and @text='" + contentName
				+ "']//parent::*//child::*//child::*//following-sibling::*");
	}

	public static By objBannerTitle = By.xpath("//*[@id='banner_title']");


	public static By objViewAllTrayApi = By.xpath("(//*[@id='row_header' and contains(text(),'Top') and contains(text(),'Movies')]//parent::*//parent::*//following-sibling::*//child::*//child::*//child::*)[1]");
	//temp

	public static By objContentTitle = By.xpath("//*[@id='detail_title']");

	public static By objAllplanButton = By.xpath("//*[@id='allaccessBtn']");

	public static By objPriceLayout = By.xpath("//*[@id='price_layout']");
	public static By objCurrencyType = By.xpath("//*[@id='txt_symbol']");
	public static By objAmountText = By.xpath("//*[@id='price_text']");
	public static By objValidityText = By.xpath("//*[@id='validity_text']");
	public static By objGuestPlanTitle = By.xpath("//*[@id='date_text']");

	public static By objSubPlantitle = By.xpath("//*[@id='device_title']");
	public static By objSubPlanDescription = By.xpath("//*[@id='device_offer' or @id='device_desc']");
	public static By objLoggedInPlantitle = By.xpath("//*[@id='date_text']");

	public static By objzeelogo = By.xpath("//*[@id='zee_logo_image' or @id='zee5_logo']");
	public static By objGuestPlanDescription = By.xpath("//*[@id='device_offer' or @id='device_desc']");
	public static By objNumberOfDevice = By.xpath("//*[@id='device_text']");

	public static By objExpiredPlanCard = By.xpath("//*[@id='llCardFocus5']");

	public static By objSettingsIcon = By.xpath("//*[@id='icon_image']");

	public static By objdeviceInfoValues(int str) {
		return By.xpath("((//*[@id='rl']//*[contains(@resource-id,'r')])//child::*[3])[" + str + "]");
	}

	public static By objdeviceInfoOptions(int str) {
		return By.xpath("((//*[@id='rl']//*[contains(@resource-id,'r')])//child::*[2])[" + str + "]");
	}

	public static By objDeviceInfoTab = By
			.xpath("(//*[@id='icon_title' and @text='DEVICE DETAILS']//parent::*//child::*)[1]");
	public static By objSettingsIconfocused = By
			.xpath("//*[@class='android.widget.RelativeLayout' and @focused='true']//child::*[@id='icon_title']");

	public static By objSettingsButtonfocused = By
			.xpath("//*[@id='menu_items_title' and @text='Settings' and @focused='true']");

	public static By objhighlightedTopMenu = By.xpath("//*[@id='menu_items_title' and @focused = 'true']");

	public static By objCarouselzeelogo = By.xpath("(//*[@id='row_header'])[1]");

	public static By objDescriptionIncontentPage = By.xpath("//*[@id='details_description_text']");

	public static By objNewsPageSvodContent = By.xpath(
			"(//*[@id='row_header' and contains(text(),'News Shows')]//parent::*//following-sibling::*//child::*//child::*//child::*//child::*//child::*[@id='main_image'])[1]");

	public static By objhighlightedcontent = By
			.xpath("//*[@id='main_image' and @selected='true']//parent::*//parent::*//child::*[@id='home_title']");
	
	public static By objLoginPopUp = By.xpath("//*[@id='pop_up_realtive']");
	
	public static By objAFSLoginPopUpSlider = By.xpath("//*[@id='mainlayout_for_plex']");
	
	public static By objautneticatePage = By.xpath("//*[@id='device_activation_header']");
	
	
}
