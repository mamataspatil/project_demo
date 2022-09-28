package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvSearchPage {

	public static By objSearchSpaceBar = By.xpath("//*[@id='searchQuery']");

	public static By objSearchSpaceButton = By.xpath("(//*[@id='keyboardLetterImageView'])[1]");
	public static By objSearchBackButton = By.xpath("(//*[@id='keyboardLetterImageView'])[2]");
	public static By objSearchClearButton = By.xpath("(//*[@id='keyboardLetterImageView'])[3]");
	public static By objshowpageContentTitle = By.xpath("//*[@id='txv_title']");
			public static By objTopsearch = By.xpath("(//*[@id='poster'])[1]");	
			
			public static By objSuggestionsInSearchPage = By.xpath("(//*[@id='suggestion'])[1]");	

	public static By objSearchKeyboardBtn(String str) {
		return By.xpath("//*[@id='letter' and @text='" + str + "']");
	}
	
	public static By objsuggestionsdisplayed(int str) {
		return By.xpath("(//*[@id='suggestion'])[" + str + "]");
	}
	
	public static By objselectedsuggestions = By.xpath("//*[@id='suggestion' and @selected='true']");	
	
	public static By objSearchedSpecificTumbnailTitle(String str) {
		return By.xpath("//*[@id='search_result_title' and @text='" + str + "']");
	}

	public static By objSearchedSpecificTumbnailcard(String str, String type) {
		return By.xpath("(//*[@class='android.widget.RelativeLayout']//child::*[contains(text(),'" + str
				+ "')]//parent::*//parent::*[@text='" + type + "']//parent::*//parent::*//parent::*)[3]");
	}
	// *[@text="Videos"]

	public static By objSearchPageNowPlayingButton = By
			.xpath("//*[@id='popular_his_sugg' and contains(text(),'Now Playing')]");

	public static By objSearchedSpecificTumbnailmetadata(String str) {
		return By.xpath("//*[@id='search_result_title_assert']//*[@text='" + str + "']");
	}

	public static By objSearchedTumbnailTitle(int i) {
		return By.xpath("(//*[@id='search_result_title'])[" + i + "]");
	}

	public static By objSearchedTumbnailmetadata(int i) {
		return By.xpath("(//*[@id='search_result_title_assert'])[" + i + "]");
	}

	public static By objSearchedTumbnailImage(int i) {
		return By.xpath("(//*[@id='search_result_image'])[" + i + "]");
	}

	public static By objSearchedDataMainHead = By.xpath("//*[@id='details_main_head']");
	public static By objSearchedDataTitle = By.xpath("//*[@id='detail_title']");
	public static By objSearchedDataDescription = By.xpath("//*[@id='details_description_text']");
	public static By objPlayIcon = By.xpath("(//*[@id='txv_title' and @text='Watch Now' or @text='Subscribe to watch now' or @text='Watch Now S1 E1'or @text='Resume Watching']//parent::*)[1]");
	public static By objResumePlayIcon = By.xpath("//*[@id='seekbar_resume_layout']");
	public static By objwatchTrailerIcon = By.xpath("(//*[@text='Watch\nTrailer']//parent::*//child::*)[1]");
	public static By objEditbox = By.xpath("//*[@id='searchQuery']");

	public static By objNamefieldbox = By.xpath("//*[@id='profile_edit_tv_name_enter']");
	public static By objElapsedTime = By.xpath("(//*[@id='search_title_elapsed_time'])[1]");

	public static By objChalnnelName = By.xpath("(//*[@id='channel_name_text'])[1]");

	public static By objLiveChalnnelName = By.xpath("(//*[@id='search_result_title'])[1]");
	public static By objProgressBar = By.xpath("(//*[@id='episode_elapsed_progress_bar_search'])[1]");

	public static By objSearchedTumbnailImageEPG(String str) {
		return By.xpath("//*[@id='channel_name_text' and @text='" + str
				+ "']//parent::*//parent::*//parent::*//child::*//child::*[@id='imageOverlay']");
	}
	
	public static By objSearchedLiveTumbnailImageEPG(String str) {
		return By.xpath("//*[@id='search_result_title' and @text='" + str
				+ "']//parent::*//parent::*//parent::*//child::*//child::*[@id='imageOverlay']");
	}

	public static By objEPGtitle = By.xpath("(//*[@id='search_result_title'])[1]");

	public static By objGotolivebutton = By.xpath("//*[@id='switch_to_live_text']");

	// ================================Content detail
	// page=================================

	public static By objLoginPopup = By.xpath("//*[@id='pop_up_realtive' or @id='pop_up_relative' or @id='slider_bg' or @text='Login or Register with ZEE5']");

	public static By objShowsLoginPopup = By.xpath("//*[@id='pop_up_relative']");
	public static By objSubscribePopup = By.xpath("//*[@id='tv_pop_up_un_subscribe_text' or @id='subscribe_title' or @text='Become a Premium Member']");

	public static By objContinueWatchingProgressbar = By.xpath("(//*[@id='progressbar'])[1]");

	public static By objClosePopupButton = By.xpath("//*[@id='tv_pop_up_cancel_text']");

	public static By objSearchedText = By.xpath("//*[@id='popular_his_sugg' and @text='Panchatantra']");

	public static By objnewslider = By.xpath("//*[@id='popup_heading']");
	public static By objZeeLogo = By.xpath("//*[@id='qr_code_image']");
	public static By objinfotext1 = By.xpath("//*[@id='info_text1']");
	public static By objinfotext2 = By.xpath("//*[@id='info_text2']");
	public static By objinfotext3 = By.xpath("//*[@id='info_text3']");
	public static By objtvodokbutton = By.xpath("//*[@id='tvod_ok']");

	public static By objzeetvhdimage = By
			.xpath("(//*[@id='search_result_title' and @text='Zee TV HD']//parent::*//parent::*//child::*)[1]");
	
	public static By objMovieSuggestionInSearchPage = By.xpath("//*[@id='suggestion' and @text='Movies']");
	
			public static By objMovieContent= By.xpath("(//*[@id='poster'])[1]");

}
