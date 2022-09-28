package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDPlayerScreen {

	public static By objShareIcon = By.xpath("//*[@id='native_share_button']");
	public static By objWatchlistIcon = By.xpath("//*[@id='watch_list_image']");
	public static By objDownloadIcon = By.xpath("//*[@id='downlowd_image']");

	public static By objfirstContentcardOfParticularTray(String trayTitle) {
		return By.xpath("(//*[@text=" + trayTitle
				+ "]/parent::*/parent::*/following-sibling::*/child::*/child::*/child::*/child::*)[1]");
	}

	public static By objLoginLinkOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/login_button']");
	public static By objPlayerLoader = By.xpath("//*[@id='player_loading_progress']");
	public static By objTweetButton = By.xpath("//*[@id='button_tweet']");
	public static By objFacebook = By.xpath("//*[@text='News Feed' or @text='Facebook']");
	public static By objReplay = By.xpath("//*[@id='icon_replay']");
	public static By objSubtitlePopUp = By.xpath("//*[@id='popup_title']");
	public static By objRegisterPopUp = By.xpath("//*[@id='registrationContainer']");
	public static By objRegisterPopUp1 = By.xpath("//*[@id='tvtitle' and @text='Register']");
	public static By objUpnextContentCard = By.xpath("(//*[@resource-id='com.graymatrix.did:id/card'])[3]");
	public static By objUpnextContentCardTitle = By.xpath("(//*[@id='title_similar'])[3]");
	public static By objUpnextRail = By.xpath("//*[@id='similarcontentlistView']");
	public static By objContentTitle = By.xpath("//*[@id='title_main']");
	public static By objCompleteProfilePopUp = By.xpath("//*[@id='tellUsMoreContainer']");
	public static By objShareNowFb = By.xpath("//*[@contentDescription='SHARE NOW']");
	public static By objWatchCreditsCTA = By.xpath("//*[@text='Watch Credits']");
	public static By objUpNext = By.xpath("//*[@text='Up Next']");
	public static By objUpNextCard = By.xpath("//*[@resource-id='com.graymatrix.did:id/upnext']");
	public static By objFirstContentCardTitleInUpnextTray = By.xpath(
			"((//*[@resource-id='com.graymatrix.did:id/header_primary_text' and @text='Up Next']/parent::*/parent::*/following-sibling::*)[1]/child::*/child::*/child::*[@resource-id='com.graymatrix.did:id/item_primary_text'])[1]");
	public static By objCountDownTimerInUpNextCard = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/counddownwatchcredit']");

	public static By objfbLoginPage = By.xpath(
			"//*[@content-desc='Create New Facebook Account'] | //*[@content-desc='Log In'] | //*[@text='Log In'] | //*[@text='Create New Facebook Account']");

	public static By objAd1 = By.xpath("(//*[contains(text(),'Ad 2 of 2 :')])");
	
	public static By objAd2 = By
			.xpath("(//*[contains(text(),'Ad 1')]) | //*[contains(text(),'Ad 2')] | //*[contains(text(),'Ad :') ]|//*[contains(text(),'Learn More')]");

	public static By objLoginButtonInRegisterPopUp = By.xpath("//*[@resource-id='com.graymatrix.did:id/tvLogin']");

	public static By objLearnMoreTextOnAdPlayBack = By.xpath("//*[contains(text(),'Learn More')]");

	public static By objAudioTrackOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_audioTrack_text']");

	public static By objcontentTitleInconsumptionPage = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/contentCl']/child::*[@resource-id='com.graymatrix.did:id/item_primary_text'] | //*[@id='content_title']");

	public static By objSubscribeButtonBelowThePlayer = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/subscribeButton']");

	public static By objThreeDotsOnPlayer = By.xpath("//*[@id='playerMoreOptionButton']");

	public static By obj3dotMenu = By.xpath("//*[@id='optionsLinearLayout']");
	public static By objplayer = By.xpath("//*[@id='playerTouchToHideShowController']");

	public static By objNextIcon = By.xpath("//*[@id='icon_next'] | //*[@id='playerSkipNextButton']");
	public static By objPreviousIcon = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/playerSkipPreviousButton'] | //*[@resource-id='com.graymatrix.did:id/playerSkipPreviousButton']");

	public static By objSubtitleOptionInPotraitMode = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/subtitlesTv'] | //*[@resource-id='com.graymatrix.did:id/subtitle_language_textView']");
	public static By objSubtitleValueInPotraitMode = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/subtitlesValueTv'] | //*[@resource-id='com.graymatrix.did:id/actual_subtitle_language_textView']");
	public static By objSubscribeNowButtonOnPlayer = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/subscribe_now_action'] | //*[@resource-id='com.graymatrix.did:id/errorViewButton']");
	public static By objSubscribeScreen = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Subscribe']");
	public static By objParentalPinPopUp = By
			.xpath("//*[@id='otpEditText1'] | //*[@id='parentalPinView'] | //*[@id='parentalFragmentTitleTextView']");

	public static By objLandscapePlayerScreen = By.xpath("//*[@id='searchResultPageRecyclerView']");

	public static By objDialogBox = By.xpath("//*[@id='dialog_divider']");

	public static By objSubscribeNowLinkOnPlayer = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/subscribe_now_action'] | //*[@id='errorViewButton' and @text='Subscribe']");

	public static By objGetPremiumPopUp = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/popup_title' and @text='Subscribe'] | //*[@id='screen_title' and @text='Subscribe']");

	public static By objSelectedQualityOption = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/icon_selected']/following-sibling::* | //*[@id='playerOptionsTitleTextView' and @text='Select Video Quality']");

	public static By objRetryBtn = By.xpath("//*[@id='errorViewButton']");
	public static By objQualityOptions = By.xpath("//*[@id='playerOptionTitle']");
	public static By objPlaybackRateSelected = By
			.xpath("((//*[@id='icon_selected']//parent::*//child::*)[2]) | (//*[@text='t']/following-sibling::*)");

	public static By objQualityOptions(int i) {
		return By.xpath("(//*[@id='playerOptionTitle'])[" + i + "]");
	}

	public static By objTitleInLandscape(String title) {
		return By.xpath("//*[@id='playerContentTitle' and contains(text(), '" + title + "')]");
	}

	public static By ObjUpNextFirstContent = By.xpath(
			"(//*[@class='androidx.recyclerview.widget.RecyclerView' and ./parent::*[@id='cell_center_container' and (./preceding-sibling::* | ./following-sibling::*)[./*[@text='Up Next']]]]/*/*/*/*[@class='android.widget.ImageView' and ./parent::*[@id='cell_center_container']])[1]");
	public static By objBenefitsofEduauraa = By.xpath("//*[@id='eduaraaTitleText' and @text='Benefits']");
	public static By objExpandArrowOfBenefitsSection = By
			.xpath("//*[@id='eduaraaTitleText' and @text='Benefits']//following-sibling::*[1]");
	public static By objeduauraaDescriptionText = By.xpath("//*[@id='eduaraaDescriptionText']");

	public static By objTrendingNewsTray = By.xpath("//*[@text='Trending News ']");

	public static By objAdultErrorMessage = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/errorViewText' and contains(text(),'Adult')]");

	public static By objPremiumTextOnPlayer = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/static_premium_text'] | //*[@id='errorViewText' and (contains(text(),'You need subscription'))] | //*[contains(text(), 'To watch this premium content')]");

	public static By objQualityOpt(int i) {
		return By.xpath("(//*[@id='playerOptionTitle'])[" + i + "]/preceding-sibling::*[@text='t']");
	}

	public static By objSharePopUp = By
			.xpath("(//*[@text='Share using']) | ( //*[@text='Share via']) | //*[@text='Copy']");

	public static By objCopyToClipboard = By.xpath("//*[@text='Copy to clipboard'] | //*[@text='Copy']");

	public static By objTwitter = By.xpath("//*[@text='Tweet'] | //*[@text='Twitter Lite']");

	public static By objLoginCTA = By.xpath("//*[@id='secondaryActionButton'] | //*[@id='get_premium_login']");

	public static By objWatchNowCTA = By.xpath("//*[@text='Watch Now']");

	public static By objPaymentStep = By.xpath("//*[@text='Step 3 of 3']");
	public static By objMakePayment = By.xpath("//*[@text='Make Payment']");
	public static By objLoggedInEmail = By.xpath("//*[@text='Logged in']//following-sibling::*");
	public static By objByRentingTerms = By.xpath("By Renting you agree to our Terms");

	public static By objInPlayerError = By.xpath("//*[@id='errorViewText'] | //*[@id='rentNowDescription']");
	public static By objZeePlexLogo = By.xpath("//*[@id='zeeplexLogo']");
	public static By objRentFor = By.xpath("(//*[contains(text(),'Rent for')])[1]");

	public static By objInPlayerCTA = By.xpath("//*[@id='watchNow'] | //*[@id='rentNowCta']");
	public static By objPlayerTrailerText = By.xpath("//*[@id='playerInfo' and @text='You are watching the trailer']");
	public static By objZeePlexIcon = By.xpath("//*[@id='iconZeePlex']");
	public static By objZeePlexValidityDetails = By.xpath("//*[@id='detailsTextValidity']");
	public static By objZeeWatchTimeDetails = By.xpath("//*[@id='detailsText']");

	public static By objZeePlexTitle = By.xpath("//*[@id='contentTitleTextView']");
	public static By objZeePlexValidity = By.xpath("//*[@id='detailsValidityTextView']");
	public static By objZeeWatchTime = By.xpath("//*[@id='detailsWatchTimeTextView']");
	public static By objLoggedIn = By.xpath("//*[@text='Logged in']");
	public static By objParentalPinContinue = By.id("parentalFragmentContinueButton");

	public static By objBuyNowCTABelowThePlayer = By
			.xpath("//*[@id='ctaBannerButton']/child::*[@id='subscribeButton']");

	public static By objUpgradeToPremiumCTAOnPlayer = By.xpath("//*[@id='subscribeButton']");
	public static By objLoginTextOnPlayer = By
			.xpath("//*[@id='secondaryActionButton'] | //*[@resource-id='com.graymatrix.did:id/static_login_text']");

	public static By objCurrentTime = By.xpath("//*[@id='playerDurationCurrent']");

	public static By objContentMeta = By.xpath("//*[@resource-id='com.graymatrix.did:id/content_info']");
	
	public static By objPlayPageUpnextFirstCardImage = By.xpath("(//*[@text='Up Next']//parent::*//following-sibling::*[@id='cell_center_container']//*[@class='android.widget.ImageView'])[1]");
	
	public static By objParentalSuccessScreen = By.xpath("//*[@id='txt_success_title']");
	public static By objParentalPinDialog = By.xpath("//*[@id='parentalFragmentTitleTextView']");
	public static By objShowPin = By.xpath("//*[@id='parentalFragmentShowPin']");
	public static By objForgotPin = By.xpath("//*[@id='parentalFragmentForgotPin'] | //*[contains(text(),'Forgot PIN?')]");
	public static By objtoastErrorMesssage = By.xpath("//*[text()='Incorrect pin entered. Please try again']");
	public static By objcontentDescriptor = By.xpath("");
	public static By objFirstContentCardTitleInUpNextRail = By.xpath("(//*[@text='Up Next']/parent::*/following-sibling::*/child::*/child::*/child::*/child::*[@id='cell_bottom_container']/child::*[@class='android.widget.TextView'])[1]");

	public static By objPlayerStartTime = By.xpath("//*[contains(@text,'00:0')]");
	
	public static By objErrorMessageOnPlayer = By.xpath("//*[@text='Please enter Parental Control PIN to watch video.']");

	public static By objBuyPlanOnThePlayer = By.xpath("//*[@id='subscribeContainer']/child::*[@id='subscribeButton']");
	
	public static By objcontentRating = By.xpath("//*[@id='content_info']");
	
	public static By objAd = By.xpath("(//*[contains(text(),'Ad :')]) | (//*[contains(text(),'Ad 1 of 2 :')]) | (//*[contains(text(),'Ad 2 of 2 :')])  | //*[@content-desc='Learn More']");
	
	public static By objAudioLanguage = By.xpath("//*[@id='audio_language_textView']");
	
	public static By objAudioLanguageOption = By.xpath("(//*[@id='playerOptionTitle'])[1]");

	public static By objcontentCard = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[4] |  (//*[@id='cell_center_container'])[4]");
	
    public static By objPlayerBackbutton =By.xpath("//*[@id='home_toolbar_brand_logo']");
    
    public static By objProgressBar = By.xpath("//*[@class='android.widget.SeekBar'] | //*[@id='playerSeekBar']");
    public static By objQuality = By.xpath("//*[@class='android.view.View' and ./*[@text='q']] | //*[@id='icon_quality_text'] | //*[@id='playerOptionTitle' and contains(text(),'Quality')]");
    public static By objPlayerScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/playerFragmentContainer']");
    public static By objPauseIcon = By.xpath("//*[@class='android.view.View']//*[@text='p'] | //*[@id='playerPlayPauseButton'] | //*[@id='icon_pause']");
    public static By objFullscreenIcon = By.xpath("//*[@class='android.view.View']//*[@text='b'] | //*[@id='playerFullScreenControl'] | //*[@id='icon_fullscreen']");
    public static By objBackButton = By.xpath("//*[@id='icon_down'] |//*[@id='playerBackButton' and @onScreen='true']");
    public static By objPlayIcon = By.xpath("//*[@class='android.view.View']//*[@text='s'] | //*[@id='icon_play'] | //*[@id='playerPlayPauseButton']");
    public static By objTimer = By.xpath("(//*[@class='android.widget.SeekBar']//preceding-sibling::*)[7] | //*[@id='playerDurationCurrent']");
	public static By objStartTime = By.xpath("(//*[@class='android.widget.SeekBar']//preceding-sibling::*)[6] | //*[@id='playerDurationCurrent']");
    public static By objTotalDuration = By.xpath("//*[@class='android.widget.SeekBar']//following-sibling::* | //*[@id='playerDurationTotal']");
    public static By objPlaybackrate = By.xpath("//*[@class='android.view.View' and ./*[@text='l']] | //*[@text='Playback Rate']");
    public static By objOptionInPlaybackrate = By.xpath("//*[@text=' Playback Rate '] | //*[@id='optionsLinearLayout']");
    
	public static By objCloseIcon = By.xpath("//*[@text='H']");
	
	public static By objPlayer = By.xpath("//*[@id='controller'] | //*[@id='playerTouchToHideShowController'] | (//*[@class='android.widget.FrameLayout'])[9]");
	public static By objPause = By.xpath("//*[@id='playerPlayPauseButton']");
	public static By objPlay = By.xpath("//*[@resource-id='com.graymatrix.did:id/playerPlayPauseButton' or @text='s'] | //*[@text='p'] ");
	public static By objSkipIntro = By.xpath("//*[@class='android.view.View']//*[@text=' SKIP INTRO '] | //*[@id='skipintro'] | //*[@resource-id='com.graymatrix.did:id/playerSkipIntroButton']");
	public static By objReplayIconOnPlayer = By.xpath("//*[@class='android.view.View']//*[@text='f'] | //*[@id='playerPlayPauseButton']");
	public static By objSubtitleOption = By.xpath("//*[@class='android.view.View']//*[@text=' Language Settings '] | //*[@id='icon_subtitle_text'] | //*[@id='playerOptionTitle' and contains(text(),'Subtitles')]");
	public static By objSubtitleDefaultSelected = By.xpath("//*[@text=' Subtitle Language ']//following-sibling::*//child::*[2]");
	public static By objPlaybackRate2 = By.xpath("//*[@text='2x'] | //*[@text='2.0X'] | //*[@text='Playback Rate']//following::*[@text='2.0']");
	public static By objEnglishSubtitle = By.xpath("//*[@text=' English '] | //*[@text='English']");
	public static By objPlaybackRate = By.xpath("//*[@text=' Speed (1x)'] | //*[@id='icon_subtitle_text'] | //*[@id='playerOptionTitle' and contains(text(),'Playback Rate')]");
	public static By objChromeCastIcon = By.xpath("//*[@content-desc='Cast. Disconnected'] | //*[@content-desc='‎‏‎‎‎‎‎‏‎‏‏‏‎‎‎‎‎‎‏‎‎‏‎‎‎‎‏‏‏‏‏‏‏‏‏‏‎‎‎‎‎‎‎‎‎‎‏‎‎‏‎‏‎‏‏‏‎‎‏‎‏‏‎‏‎‎‎‏‏‏‎‎‏‏‎‎‏‎‎‎‎‎‏‏‏‏‏‏‎‎‎‎‏‎‏‏‎Cast. Disconnected‎‏‎‎‏‎']");
	public static By objTitleOnPlayer = By.xpath("//*[@id='searchBarText'] | //*[@id='playerContentTitle']");
	public static By objAddToWatchlist = By.xpath("//*[@class='android.view.View']//*[@text='a'] | //*[@id='icon_add_to_watch_list_text']");
	public static By objShareIconOnPlayer = By.xpath("//*[@text='z'] | (//*[@id='icon_share']) | (//*[@id='playerShareContent'])");
	public static By objFacebookPost = By.xpath("//*[@contentDescription='POST'] | //*[@text='POST' or @text='Post' or @text='Share' or @text='SHARE']");

	public static By objVideoQualityOptions = By.xpath("//*[@class='android.widget.ScrollView']//*[@class='android.view.View']//child::*");
	public static By DefaultQualityOption = By.xpath("//*[@class='android.view.View' and ./*[@text='G']]//following-sibling::*");
	public static By objspeed=By.xpath("//*[contains(text(), 'Speed')]");
	public static By objplaybackrateoption=By.xpath("//*[contains(text(),'Playback Rate')]");
	public static By objAgeRatedOnPlayer = By.xpath("//*[@id='ratedText'] | (//*[@class='android.view.View' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.FrameLayout'] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.view.View']]]/*[@text and @class='android.view.View'])[1]");
	public static By objContentDescOnPlayer = By.xpath("//*[@id='contentDescriptorText'] | (//*[@class='android.view.View' and ./parent::*[@class='android.view.ViewGroup' and ./parent::*[@class='android.widget.FrameLayout'] and (./preceding-sibling::*\r\n" + 
				" | ./following-sibling::*)[@class='android.view.View']]]/*[@text and @class='android.view.View'])[2]");
	public static By objForward = By.xpath("//*[@text='i']");
	public static By objRewind = By.xpath("//*[@text='h']");
	public static By objElapsedTime = By.xpath("(//*[@class='android.widget.SeekBar']//preceding-sibling::*)[6]");

	
}

