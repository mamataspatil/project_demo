package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvPlayerPage {

	public static By objAd = By.xpath("(//*[contains(text(),'Ad ')])[2]");
	public static By objProgressLoader = By.xpath("//*[@id='tv_gif_progress_loader']");
	public static By objPlayPause = By.xpath("//*[@id='new_play_pause_icon']");
	public static By objPlayPauseHighlighted = By.xpath("//*[@id='new_play_pause_icon' and @focused='true']");
	public static By objForward10 = By.xpath("//*[@id='player_10s_fwd']");
	public static By objRewind10 = By.xpath("//*[@id='player_10s_rwd']");
	public static By objPlayerTitle = By.xpath("//*[@id='player_title_text']");
	public static By objElapsedTime = By.xpath("//*[@id='time_elapsed_tv']");
	public static By objTotalTime = By.xpath("//*[@id='total_time_tv']");
	public static By objPlayerSettings = By.xpath("//*[@id='player_settings']");
	public static By objPlayerSettingsHighlighted = By.xpath("//*[@id='player_settings' and @focused='true']");
	public static By objPlayerSkipIntro = By.xpath("//*[@id='skip_button_text']");
	public static By objPlayerSkipIntro1 = By.xpath("//*[@id='skip_button_text']");
	public static By objPlayerScreenMetadata = By.xpath("//*[@id='player_genre_duration']");
	public static By objPlayerScreenTitle = By.xpath("//*[@id='player_title_text']");
	public static By objContentLangPopup = By.xpath("//*[@id='add_content_lang']");

	public static By objSearchedSpecificVideoAudioOption(int str) {
		return By.xpath("(//*[@id='child_player_settings'])[" + str + "]");
	}

	public static By objSpecificplan(int str) {
		return By.xpath("(//*[@id='price_text'])[" + str + "]");
	}
				
	public static By objPlayerAudioLanguageOption = By
			.xpath("//*[@text='AUDIO LANGUAGES']");
	public static By objUpNextContent = By.xpath("(//*[@id='up_next_item_main_image'])[1]");
	public static By objUpnextrail = By.xpath("//*[@id='related_videos_frag_head']");
	public static By objPlayerScreenVideoSettingoptions = By.xpath("//*[@text='SETTINGS']");
	public static By objPlayerScreenVideoSetting = By.xpath("//*[@id='player_expandable']");
	public static By objPlayerScreenUpNext = By.xpath("//*[@id='player_next_show']");
	public static By objPlayerContainer = By.xpath("//*[@id='play_pause']");
	
	
	public static By objPlayerScreenNowPlaying = By.xpath("//*[@id='now_showing_view']");
	public static By objPaymentPage = By.xpath("//*[@id='plan_selected']");
	
	public static By objreloadButton = By.xpath("//*[@id='reload_btn']");
	public static By objplayerzelogo = By.xpath("//*[@id='gif_animation_view']");
	
	public static By objplayeroverlay= By.xpath("//*[@id='player_main_overlay']");

	public static By objpremiumInUpnext= By.xpath("	(//*[@id='related_item_premium_tag']//parent::*//child::*[@id='related_thumbnail'])[1]");
	
}
