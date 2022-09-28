package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvHomePage {

	public static By objHighlightedTab = By.xpath("//*[@id='collection_header_label' and @selected='true']");

	public static By objSelectTab(String str) {
		return By.xpath("//*[@id='collection_header_label' and @text='"+ str +"']");
		
	}
	
	
	public static By objcarouselBanner = By.xpath("//*[@id='details_fragment_container']");
	public static By objUpdateButtonInBlockerScreen = By.xpath("//*[@id='update']");
	
	public static By objPlaystore = By.xpath("//*[@contentDescription='Image of app or game icon for ZEE5: Movies, TV Shows, Web Series, News']");
	
	public static By objContinueButtonInBlockerScreen = By.xpath("//*[@id='continueBrowsing']");
	
	public static By objExitButtonInBlockerScreen = By.xpath("");
	
	public static By objzee5app = By.xpath("//*[@id='language_title']");
	public static By objdescription = By.xpath("//*[@id='details_description_text']");
	public static By objchannelInAllchannelTab(int i) {
		return By.xpath("(//*[@id='channel_horizontal_view_channel_image'])[" + i + "]");
	}
	public static By objwelcomepopup = By.xpath("//*[@id='slider_bg']");
	
	public static By objonboardingrevamploginbutton = By.xpath("//*[@id='login_button']");
	
	public static By objexplorebutton = By.xpath("//*[@id='explore']");
	
	public static By objSeekbar = By.xpath("//*[@id='seekbar_layout']");
	
	public static By objSeasonPage = By.xpath("//*[@id='select_seasons_textview']");
	public static By objpinkloaderinPlayer= By.xpath("//*[@id='tv_gif_progress_loader']");
	public static By objplayerTitle = By.xpath("//*[@id='player_title_text']");

	public static By objplayerskipad = By.xpath("(//*[@id='kexo_subtitles'])[1]");
	public static By objSearchIcon = By.xpath("//*[@id='search_icon_menu']");

	public static By objChannelFilterButton = By.xpath("//*[@text='Filter' or @id='channel_listing_filter']");

	public static By objFilterPopup = By.xpath("//*[@id='language_label' and @text='हिन्दी']");

	public static By objSortinFilterPopup = By.xpath("//*[@id='sort_layout']");
	public static By objPopularFilterPopup = By.xpath("//*[@id='popularity_layout']");
	public static By objAtoZFilterPopup = By.xpath("//*[@id='atoz_layout']");
	public static By objItemCountLanguageFilterPopup = By.xpath("//*[@id='item_count_language']");
	public static By objLanguageFilterPopup = By.xpath("//*[@id='btn_language']");
	public static By objGenreInFilterPopup = By.xpath("//*[@id='btn_genre']");

	public static By objGenreConterInFilterPopup = By.xpath("//*[@id='item_count_genres_channel']");

	public static By objChannelSort = By.xpath("(//*[@id='channel_sort_textview'])[1]");

	public static By obj3rdEpisode = By.xpath("(//*[@id='tv_shows_result_images'])[3]");

	public static By objupnextTrayContent(int i) {
		return By.xpath("(//*[@id='up_next_container'])[" + i + "]");
	}

	public static By objDisplaylanguageOptions = By.xpath("//*[@id='relative_individual_display']");

	public static By objContentlanguageOptions = By.xpath("//*[@id='relative_individual']");

	public static By objContentlanguageOptionsSelect(int i) {
		return By.xpath("//*[@id='relative_individual'][" + i + "]");
	}

	public static By objDisplaylanguageOptionsSelect(int i) {
		return By.xpath("(//*[@id='relative_individual_display'])[" + i + "]");
	}

	public static By objDisplaylanguageOptionsSelectText(int i) {
		return By.xpath("(//*[@id='english_text'])[" + i + "]");
	}

	public static By objContentlanguageOptionsSelectText(int i) {
		return By.xpath("(//*[@id='checkbox_content' and @checked='true'])[" + i + " ]//parent::*");
	}

	public static By objContentlanguageButtonChecked = By.xpath("//*[@id='checkbox_content' and @checked='true']");
	public static By objEnglistDisplaylanguageButton = By
			.xpath("//*[@text='English']//parent::*//parent::*[@id='relative_individual_display']");

	public static By objOdiaContentlanguageButton = By
			.xpath("//*[@text='Odia']//parent::*//parent::*[@id='relative_individual']");

	public static By objAddcontentLanguagePopup = By.xpath("//*[@id='add_content_lang']");
	public static By objDisplaylanguageButton = By.xpath("//*[@id='display_button']");
	public static By objContentlanguageButton = By.xpath("//*[@id='content_button']");

	public static By objDisplayLanguageHeader = By.xpath("//*[@id='language_title' and @text='DISPLAY LANGUAGE']");

	public static By objlanguageButton = By.xpath("(//*[@text='Language Settings']//parent::*)[1]");

	public static By objSaveButton = By.xpath("//*[@id='save_button_lang']");

	public static By objMorecontentPopup = By.xpath("//*[@id='more_content']");

	public static By objOptionsInSettingClick(String option) {
		return By.xpath("(//*[contains(text(),'" + option + "')]//parent::*)[1]");
	}

	public static By objPlatformName = By.xpath("//*[@id='txtPlatform']");
	public static By objPlatformChipset = By.xpath("//*[@id='txtDeviceChipset']");
	public static By objPlatformAppVersion = By.xpath("//*[@id='txtDeviceAppVersion']");
	public static By objPlatformInternetType = By.xpath("//*[@id='txtDeviceNetworkStatus']");

	public static By objFAQPage = By.xpath("//*[@id='faq_activity_title']");

	public static By objContactUsPage = By.xpath("//*[@id='contact_us_text']");
	public static By objAboutUsPage = By.xpath("//*[@id='faq_activity_title' and @text='ABOUT US']");
	public static By objTermsOfUsePage = By.xpath("//*[@text='Terms Of Use']");
	public static By objPrivacyPolicyPage = By.xpath("(//*[@text='Privacy Policy'])[1]");

	public static By objSubmitButton = By.xpath("//*[@id='submit_btn']");

	public static By objSuccessPopup = By.xpath("//*[@id='textview_success']");

	public static By objSplCharechter = By.xpath("//*[@id='spl_char']");
	public static By objatbutton = By.xpath("//*[@text='@']");
	public static By objdotbutton = By.xpath("//*[@text='.']");

	public static By objmessagebox = By.xpath("//*[@id='edittext_msg']");

	public static By objAlphaKeyboard = By.xpath("//*[@id='caps_turn_On']");

	public static By objAlphaKeyboardGbutton = By.xpath("//*[@id='button_g']");
	public static By objAlphaKeyboardMbutton = By.xpath("//*[@id='button_m']");
	public static By objAlphaKeyboardAbutton = By.xpath("//*[@id='button_a']");
	public static By objAlphaKeyboardIbutton = By.xpath("//*[@id='button_i']");
	public static By objAlphaKeyboardLbutton = By.xpath("//*[@id='button_l']");
	public static By objAlphaKeyboardCbutton = By.xpath("//*[@id='button_c']");
	public static By objAlphaKeyboardObutton = By.xpath("//*[@id='button_o']");
	public static By objAlphaKeyboardXbutton = By.xpath("//*[@id='button_x']");
	public static By objAlphaKeyboardYbutton = By.xpath("//*[@id='button_y']");
	public static By objAlphaKeyboardZbutton = By.xpath("//*[@id='button_z']");
	public static By objAlphaKeyboard7button = By.xpath("//*[@id='button_7']");
	public static By objAlphaKeyboard8button = By.xpath("//*[@id='button_8']");
	public static By objAlphaKeyboard9button = By.xpath("//*[@id='button_9']");
	public static By objAlphaKeyboard2button = By.xpath("//*[@id='button_2']");
	public static By objAlphaKeyboard1button = By.xpath("//*[@id='button_1']");
	public static By objAlphaKeyboard5button = By.xpath("//*[@id='button_5']");
	public static By objAlphaKeyboard4button = By.xpath("//*[@id='button_4']");

	public static By objCountryCodePopup = By.xpath("//*[@id='country_code_text']");

	public static By objClubPremiumPopup = By.xpath("//*[@id='tv_pop_up_un_subscribe_text']");

	public static By objAllaccessButton = By.xpath("//*[@id='allaccessBtn']");

	public static By objWatchNextTray = By.xpath("//*[@id='row_header' and @text='Watch Next']");

	public static By objEditBox = By.xpath("//*[@id='edittext_msg']");
	public static By objSelectCountryBox = By.xpath("//*[@id='select_your_country_hint']");
	public static By objSelectCountryBoxArrow = By.xpath("//*[@id='country_arrow']");
	
	public static By objResetButton = By.xpath("//*[@id='reset_button']");

	public static By objrowHeaderTray(String trayname) {
		return By.xpath("//*[@id='row_header' and @text='" + trayname + "']");
	}

	public static By objClubContent(String contentName) {
		return By.xpath("//*[@id='home_title' and @text='" + contentName + "']");
	}

	public static By objCarouselBannerTitle(String title) {
		return By.xpath("//*[@id='banner_title' and @text='" + title + "']");
	}

	public static By objPlayerScreenUpNext = By.xpath("//*[@id='info_text']");

	public static By objinfo = By.xpath("//*[@id='player_info']");
	public static By obDeviceAutneticationPage = By.xpath("//*[@id='device_activation_header']");
	public static By objOptionsInSetting(String option) {
		return By.xpath("//*[@id='icon_title' and contains(text(),'" + option + "')]");
	}
	public static By objContactUsKeyboardBtn(String str) {
		return By.xpath("//*[@id='button_" + str + "']");
    }



}
