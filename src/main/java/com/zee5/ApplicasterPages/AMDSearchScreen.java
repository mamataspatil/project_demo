package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDSearchScreen {
	public static By objSearchkeypad = By.xpath("//*[@contentDescription='Enter'] | (//*[@class='android.view.ViewGroup' and ./*[./*[@text='1']]]//child::*)[90]");
	public static By objUpcomingOption = By.xpath(
			"//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Upcoming']]");

	public static By objDownloadsOption = By.xpath(
			"//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Downloads']]");

	public static By objMoreOption = By
			.xpath("//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='More']]");

	public static By objSearchBoxEdit = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchToolbarTitle']");

	public static By objVoiceSearchBackButton = By.xpath("//*[@id='backIv']");
 
	public static By objRecentsearchOverlay = By
			.xpath("//*[contains(text(),'Recent Searches')]");

	public static By objContentRecentSearch = By
			.xpath("//*[@id='item_primary_text' and contains(text(), ' \"+title+\" ')]");

	public static By objRecentSearchConent(String title) {
		return By.xpath("//*[@id='item_primary_text' and contains(text(), ' \"+title+\" ')]");
	}

	public static By objShowsTab = By.xpath("//*[@text='Shows' and @id='title']");

	public static By objNewsTab = By.xpath("//*[@text='News' and @id='title']");

	public static By objFreeMoviesTab = By.xpath("//*[@text='Free Movies' and @id='title']");

	public static By objPremiumTab = By.xpath("//*[@text='Premium' and @id='title']");

	public static By objKidsTab = By.xpath("//*[@text='Kids' and @id='title']");

	public static By objMusicTab = By.xpath("//*[@text='Music' and @id='title']");

	public static By objLiveTVTab = By.xpath("//*[@text='Live TV' and @id='title']");

	public static By objZeeoriginalsTab = By.xpath("//*[@text='ZEE5 Originals' and @id='title']");

	public static By objRecentSearchContent = By.xpath("//*[@id='item_primary_text']");

	public static By objVirtualKeyboard = By.xpath("//*[@id='keyboard_area']");

	public static By objFreeMovies = By
			.xpath("//*[@id='title']//ancestor::*[@class='android.support.v7.app.ActionBar$b']");
	
	public static By objEnterKey = By.xpath("(//*[@id='icon'])[11]");

	// ***** Objects SearchPage by Manasa
	public static By objSearchInKeyboard = By.xpath("//*[@contentDescription='Search']");

	public static By objSearchResultPremiunm(String ResultName) {
		return By.xpath("//*[@resource-id='com.graymatrix.did:id/special_image_1']//following-sibling::*[@text='"
				+ ResultName + "']");
	}

	public static By objUpgradePopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/dialog_layout']");

	public static By objUpgradePopupTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title']");

	public static By objUpgradePopupDescription = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/upgrade_subtitle']");
	public static By objUpgradePopupProceedButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/proceed']");

	public static By objUpgradePopUpPacks(String ResultName) {
		return By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_packDescription' and @text='" + ResultName + "']");
	}

	public static By objTermsOfUse = By.xpath("//*[@resource-id='com.graymatrix.did:id/terms_of_use']");
	public static By objPrivacyPolicy = By.xpath("//*[@resource-id='com.graymatrix.did:id/privacy_policy']");
	public static By objYouWillBeChargedInfo = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/payment_recurring_msg']");

	public static By objAllTab = By.xpath("(//*[@text='All'])");

	public static By objMoviesTabIndx = By.xpath("(//*[@id='title'and @text='Movies'])[2]");
	public static By objNewsTabIndx = By.xpath("(//*[@id='title'and @text='News'])[2]");
	
	public static By objBackBtn = By.xpath("//*[@id='backIv']");

	public static By objDeny = By.xpath("//*[@id='permission_deny_button']");

	public static By objAudioPermissionPopUp = By.xpath("//*[@id='permission_message']");

	public static By objCloseBtn = By.xpath("//*[@id='closeButton']");

	public static By objSeeUrTextMsg = By.xpath("//*[@id='resultsOfRec']");

	public static By objRecentSearch = By.xpath("//*[@text='Recent Searches']");

	public static By objVoiceSearchScreen = By.xpath("//*[@id='speechListeningScreen']");

	public static By objVirtualKeypadLetter = By.xpath("//*[@id='key_pos_1_4']");

	public static By objGetPremiumBelowThePlayer = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/get_premium_button']");

	public static By objConsumptionScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text']");

	public static By objTopSearches = By.xpath(
			"//*[@text='Trending Searches']/parent::*/parent::*/following-sibling::*/child::*/child::*[@text='Top Searches']");

	public static By objContentCardTitleOfTopSearchesTray = By.xpath(
			"//*[@text='Top Searches']/parent::*/parent::*/following-sibling::*/child::*/child::*[@resource-id='com.graymatrix.did:id/item_primary_text']");

	public static By objSelectFirstEpisodeResult = By.xpath("//*[@id='item_secondary_text'][1]");
	
	public static By objEpisodeSearch = By.xpath("//*[@id='item_secondary_text']");
	
	public static By objSearchMoviesTab = By.xpath("(//*[@text='Movies' and @id='title'])[2]");
	
	public static By objSearchShowsTab = By.xpath("(//*[@id='title' and @text='Shows'])[2]");
	
	public static By objSearchedClubContent = By.xpath("(//*[@id='special_image_1' and (./preceding-sibling::* | ./following-sibling::*)[@id='itemImageParent']]//following-sibling::*)[1]"); 
	
//	public static By objTabs = By
//			.xpath("((//*[@resource-id='com.graymatrix.did:id/tabLayout'])[1]/child::*/child::*/child::*/child::*)");

	public static By objTrendingSearches = By.xpath("//*[@id='header_primary_text' and @text='Trending Searches']");
	
	public static By objVoiceicon = By.xpath("//*[@id='searchBarVoiceRecord']");
	public static By objMicroPhone = By.xpath("//*[@id='searchRecordingPermissionText']");
	public static By objsearchBox = By.xpath("//*[@id='searchBarText']");
	public static By objSearchBar = By.xpath("//*[@id='searchBarText']");
	public static By objClearSearch = By.xpath("(//*[contains(text(),'H')])[1]");
	public static By objMicrophoneIcon = By.xpath("//*[@id='searchBarVoiceRecord']");
	public static By objVoiceSearchPermission = By.xpath("//*[@id='searchRecordingPermissionText']");
	public static By objTermsAndConditions = By.xpath("//*[@id='termsAndConditionsText']");
	public static By objProceedBtn = By.xpath("//*[@id='proceedButton']");
	public static By objMicrophoneLogoInVoiceSearch = By.xpath("//*[@id='voiceRecordingGroup']");
	public static By objTabs = By.xpath("//*[@id='searchTypeTabLayout']//*[@class='android.widget.TextView']");
	
	public static By objMicrophoneIconLogo = By.xpath("//*[@id='searchRecordingIcon']");
	public static By objMusicTabIndx = By.xpath("//*[@text='Music']");
	public static By objShowsTabIndx = By.xpath("//*[@text='Shows']");
	public static By objVideosTab = By.xpath("(//*[@text='Videos'])");
	
	public static By objSearchItemBySearchTitle(String title) {
		return By.xpath("//*[@id='searchItemTitleText' and text()='"+title+"']");
	}
	
	public static By objSearchResultText(String title) {
		return By.xpath("//*[contains(text(), '"+title+"')]");
	}
	
	public static By objHomeOption = By
			.xpath("//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Home']] | //*[@id='navigationTitleTextView']");
	
	public static By objSearchEditBox = By.xpath("(//*[contains(text(),'M')]/parent::*)[1]");
	public static By objSearchBoxBar = By.xpath("(//*[contains(text(),'M')]/parent::*)[1]");
	public static By objFirstContentAfterSearch(String title) {
		return By.xpath("(//*[contains(text(),'"+title+"')])[2]");
	}
	
	public static By objContentNameInPlayer(String ResultName) {
		return By.xpath("(//*[@class='android.view.View' and ./*[contains(text(),'"+ResultName+"')] and ./*[@class='android.view.View']])[2]");
	}
	

	 public static By objSearchIcon2 = By.xpath("//*[@id='home_toolbar_search_icon']");
	 public static By objTopSearchesbelowTheTrendingSearches = By.xpath("//*[@text='Trending Searches']/parent::*/parent::*/parent::*/following-sibling::*/child::*/child::*/child::*[@text='Top Searches']");
	 public static By objRelatedSearchResult(String partlySpeltSearchKeyword) {
	 		return By.xpath("//*[contains(text(),'"+partlySpeltSearchKeyword+"')]");
	 	}
	 public static By objFirstSearchResult = By.xpath("((//*[@resource-id='com.graymatrix.did:id/cell_center_container'])[2]/child::*)[1]");
	 
	 public static By objTrendingSearchOverlay = By.xpath("//*[@resource-id='com.graymatrix.did:id/cell_top_container']/child::*[@text='Trending Searches']");
	 public static By objTopSearchOverlay = By.xpath("//*[@resource-id='com.graymatrix.did:id/cell_top_container']/child::*[@text='Top Searches']");
	 public static By objNoOftraysInSearchpage = By.xpath("//*[@resource-id='com.graymatrix.did:id/cell_top_container']");
	 public static By objContentCardTitleOfTrendingSearchesTray = By.xpath("(//*[@text='Trending Searches']/parent::*/following-sibling::*/child::*/child::*/child::*/child::*/child::*[@class='android.widget.TextView'])[1]");
	 public static By objNoSearchResults = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchResultEmptyText']");
	 public static By objSearchResultPage = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchResultViewPager']");
	 public static By objRelatedSearchResult = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchResultViewPager']");
	 
	 public static By objSearchResult(String title) {
			return By.xpath("//*[@id='searchItemTitleText' and contains(text(), \""+title+"\")] | (//*[contains(@text,'"+title+"')])[2]");
		}
	 
	 public static By objFirstSearchResult(String title) {
			return By.xpath("(//*[@id='cell_center_container']//*[@class='android.widget.TextView' and @text='"+ title +"'])[1]");
	}

	public static By objSecondSearchResult(String title) {
			return By.xpath("(//*[@id='cell_center_container']//*[@class='android.widget.TextView' and @text='"+ title +"'])[2]");
		}
	
	 public static By objTextofSearchresult(String ResultName) {

		 return By.xpath("(//*[@class='android.view.View' and ./*[contains(text(),'"+ResultName+"') and @class='android.view.View'] and ./*[@class='android.view.View']]//child::*)[2]");
		 }
	public static By objFirstResult = By.xpath("(//*[@id='cell_start_container'])[1]");
	
	public static By objFirstContentInSearchResult(String searchcontent) {
		return By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@id='cell_start_container'] and ./*[./*[@text='" + searchcontent + "']]]");
			}
	
	public static By objSearchResultContainsText (String title) {
		return By.xpath("//*[@class='android.widget.TextView' and contains(@text,\""+title+"\")]");
	}
	
	public static By objFirstContentInSearchResult = By.xpath("(//*[@id='Zee5SearchResultItem'])[1] | //*[@class='android.widget.LinearLayout' and ./*[@id='cell_start_container'] and ./*[./*[@text='Ammana Mane']]]");
	
	public static By objSearchResultFirstContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_primary_text'])[4] | (//*[@id='searchResultViewPager']//following-sibling::*//child::*)[1]");

	public static By objPremiumSearchResult(String title) {
		return By.xpath("//*[@text='"+title+"']/parent::*/preceding-sibling::*/child::*[@text='P']");
	}
	
	public static By objEpsiodesTab = By.xpath("(//*[@id='title'or @text='Episodes'])");
	
	public static By objContentDescription = By.xpath("//*[@id='contentDescriptionTextView']");
	
	public static By objMoviesTabInSearchResult = By.xpath("//*[@text='Movies']");
	
    public static By objAllow = By.xpath("//*[@id='permission_allow_button'] | //*[@id='permission_allow_foreground_only_button']");
    
    public static By objClearAll = By.xpath("//*[contains(text(),'Clear All')]");
    
    public static By objMoviesTab = By.xpath("//*[@text='Movies' or @id='title']"); 
    
    public static By objRecentSearchHistory = By.xpath("(//*[@id='recentSearchRecyclerView']//*[@class='android.widget.LinearLayout']//*[@id='cell_top_container']//child::*[1])");
    
	public static By objRecentSearches(int index) {
	    	return By.xpath("(//*[@id='recentSearchRecyclerView']//*[@id='cell_top_container']//child::*[1])["+index+"]");
	 }
	public static By objFisrtSearchContent = By.xpath("(//*[@id='cell_center_container']//*[@class='android.widget.TextView'])[1]");
	
	public static By objSearchBackBtn = By.xpath("//*[@id='searchBarBackButton']");
	
	public static By objSearchResult(String titleContains,String metaDataContains) {
		return By.xpath("//*[contains(@text,\""+titleContains+"\")]//following-sibling::*[contains(@text,\""+metaDataContains+"\")]");
	}
	
	 public static By objFirstSearchResult1(String text)
	 {
		 return By.xpath("//*[@class='android.widget.TextView' and @text='"+text+"']");
	 }
	
	// chromecast

	public static By objchromecasticon = By.xpath("//*[@id='contentMetaInfo']//*[@text='c']");

	public static By objchooseDeviceChromeCastPopup = By.xpath("//*[@id='mr_chooser_title']");

	public static By objplayercontentTitle = By.xpath("//*[@id='content_title']");

	public static By objDeviceList = By.xpath(" (//*[@id='mr_chooser_route_name'])[1]");

	public static By objPostChromecastIcon = By.xpath("//*[@id='contentMetaInfo']//*[@text='b']");

	public static By objCastedDeviceName = By.xpath("//*[@id='mr_name']");

	public static By objExpandControllerPlayIcon = By.xpath("//*[@content-desc='Play']");

	public static By objExpandControllerPauseIcon = By.xpath("//*[@content-desc='Pause']");

	public static By objExpandControllerStopCastButton = By.xpath("//*[@id='button1' and @text='STOP CASTING']");

	public static By objExpandControllerCloseIcon = By.xpath("//*[@id='mr_close']");

	public static By objExpandControllerVolumeController = By.xpath("//*[@id='mr_volume_control']");

	public static By objContentTitleInTV = By.xpath("//*[contains(text(),'Queue')]//preceding-sibling::*");

	public static By objPlayIconInTV = By.xpath("//*[@text='Loading...']");
	public static By objQueueInTV = By.xpath("//*[contains(text(),'Queue')]//preceding-sibling::*");
	
	public static By objcastsearchresult = By.xpath(
			"	//*[@text='Bablu Dablu - Robo Rumble']//parent::*//parent::*//child::*[@class='android.widget.ImageView']");

	public static By objPlanPrice = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_plan_price'] | //*[@text='Plan Price']");

	public static By objDiscount = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_discount'] | //*[@text='Discount']");

	public static By objRoundoff = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_round_off'] | //*[@text='Round Off']");

	public static By objTotalPayableAmount = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_total_amount'] | //*[@text='Payable Amount']");

	public static By objRevisedBillingInfo = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/revised_billing_cycle_decription'] | //*[@text='Revised billing cycle will be effective from date of payment']");

	public static By objAccountInfo = By.xpath("//*[@resource-id='com.graymatrix.did:id/account_info_title'] | //*[@text='Logged in']");
	
	public static By objSearchIcon = By.xpath("//*[@id='home_toolbar_search_icon']");
	
}
