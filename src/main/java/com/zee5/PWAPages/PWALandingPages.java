package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWALandingPages {

	public static By obj_Pwa_Zee5Logo = By.xpath("//*[@title='ZEE5 Logo']");
	public static By obj_Pwa_SearchBtn = By.xpath("//*[@class='noSelect searchBtn iconInitialLoad-ic_search']");

	public static By obj_Pwa_PlayIcon_Carousal = By.xpath(
			"(//*[@nodeName='DIV' and ./parent::*[@nodeName='DIV' and ./parent::*[@nodeName='DIV'] and (./preceding-sibling::* | ./following-sibling::*)[@text=' Previous']]]/*/*/*/*/*/*/*[@nodeName='DIV' and @width>0 and ./parent::*[@nodeName='DIV' and @width>0 and (./preceding-sibling::* | ./following-sibling::*)[@text='Get premium'] and ./parent::*[@nodeName='DIV' and @width>0 and (./preceding-sibling::* | ./following-sibling::*)[./*[@text='Seetharama Kalyana']]]]])[1]");

	public static By obj_Pwa_Content_Rail_View_all = By.xpath(
			"//*[@nodeName='DIV' and @width>0 and ./parent::*[@nodeName='DIV' and @width>0] and (./preceding-sibling::* | ./following-sibling::*)[@nodeName='H2' and ./*[@text='Top ZEE5 Movies in Kannada']]]");
	public static By obj_Pwa_WhyRegister_Popup = By.xpath("//*[@text='Why Register?']");
	public static By obj_Pwa_Popup_Close = By.xpath("//*[@class='manCloseIcon']");

	public static By objFirstTray = By.xpath("(//div[contains(@class,'trayHeader')])[1]");

	public static By objtrayTitle(String trayTitleFromAPI) {
		return By.xpath(
				"//div[@class='trayHeader']/h2/a[@class='titleLink' and contains(text(),'" + trayTitleFromAPI + "')]");
	}

	public static By objtrayFirstContent(String trayTitleFromAPI, String trayFirstContentFromAPI) {
		return By.xpath("//*[@class='titleLink' and contains(text(),'" + trayTitleFromAPI
				+ "')]/following::div[contains(@data-minutelytitle,'" + trayFirstContentFromAPI
				+ "') and contains(@class,'content')]");
	}

	public static By objViewAllOfTray(String trayTitle) {
		return By.xpath("//*[@class='titleLink' and contains(text(),'" + trayTitle
				+ "')]//parent::*//following-sibling::*[@class='arrow iconInitialLoad-ic_viewall noSelect']");
	}

	public static By objViewAllPageTitle = By.xpath("//h1");

	public static By objTrayTitleArrowBtn(String str) {
		return By.xpath("//div[contains(text(),'" + str + "')]/parent::*//following-sibling::div");
	}

//	===============================================================================================================
//	BHAVANA SHOWS MODULE
	public static By objHindiInContentLanguageNotSelected = By.xpath("//label[@for='content_hi']");

//	TANISHA RECO MODULE
	public static By objTrayTitleInUI(String apiTitle) {
		return By.xpath(
				"(//a[@class='titleLink' and text()=\"" + apiTitle + "\"]) | (//h2[text()=\"" + apiTitle + "\"])");
	}

	public static By objHindiInContentLanguageSelected = By
			.xpath("//*[@class='checkboxWrap checkedHighlight']//*[@for='content_hi']");

	public static By objTrayTitle = By.xpath("(//a[@class='titleLink']) | (//div[@class='trayHeader']//h2)");

	public static By objTrayTitleInUIContains(String apiTitle) {
		return By.xpath("(//div[@class='trayHeader']//h2[contains(text(),\"" + apiTitle
				+ "\")]) | (//h2//a[contains(text(),\"" + apiTitle + "\")])");
	}
	public static By objViewAllPageSecondContent = By.xpath(
			"((//div[@class='viewAllGrid']//div[@data-minutelytitle]) | (//div[@class='viewAllGrid']//a[@data-minutelytitle]))[2]");

	public static By objTrayTitleInUIContainsViewAll(String apiTitle) {
		return By.xpath("//h2//a[contains(text(),\"" + apiTitle + "\")]//parent::*//following-sibling::*");
	}

	public static By firstAssetNonRecoTray(String apiTrayTitle, String apiContentTitle) {
		return By.xpath("//a[contains(text(),\"" + apiTrayTitle
				+ "\")]//parent::*//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//*[contains(@data-minutelytitle,\""
				+ apiContentTitle + "\")]");
	}

	public static By secondAssetNonRecoTray(String apiTrayTitle, String apiContentTitle) {
		return By.xpath("//a[contains(text(),\"" + apiTrayTitle
				+ "\")]//parent::*//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='1']//*[contains(@data-minutelytitle,\""
				+ apiContentTitle + "\")]");
	}

	public static By objFirstAssetInTrayPlayIcon(String trayTitleUI) {
		return By.xpath("//h2[contains(text(),\"" + trayTitleUI
				+ "\")]//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//div[@title='Play']");
	}

	public static By obj_Pwa_Trending_On_Zee5 = By.xpath("//*[text()='Trending on ZEE5' or text()='Trending on Zee5']");
	
	public static By objViewAllPageTrayDescription = By.xpath("//*[@class='description']");

	public static By objTrayClubOrPremiumCardInShowDetails = By.xpath("(//div[contains(@class,'clubPackContent') or contains(@class,'cardPremiumContent')])[1]//preceding-sibling::*");

	public static By trayCard = By.xpath("//*[@data-minutelytitle]");
	
	public static By trayCard(String trayTitle) {
		return By.xpath("//*[contains(text(),'"+trayTitle+"')]//ancestor::*[@class='trayHeader']//following-sibling::*//*[@data-minutelytitle]");
	}
	
	public static By objViewAllPageFirstContent = By.xpath(
			"((//div[@class='viewAllGrid']//div[@data-minutelytitle]) | (//div[@class='viewAllGrid']//a[@data-minutelytitle]) | (//div[contains(@class,'channelNewsCard')]//figure))[1]");
	
	public static By objLanguageBtnWEB = By.xpath("//div[contains(@class,'languageBtn')]");
	
	public static By objFirstAssetInTrayIndexType2(String trayTitleUI) {
		return By.xpath("//a[contains(text(),\"" + trayTitleUI
				+ "\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//a");
	}
	
	public static By trayCardImg(String trayTitle) {
		return By.xpath("//*[contains(text(),'"+trayTitle+"')]//ancestor::*[@class='trayHeader']//following-sibling::*//*[@data-minutelytitle]//img");
	}
	
	public static By objFirstAssetInTray(String apiTrayTitle, String apiContentTitle) {
		return By.xpath("//*[contains(text(),\""+apiTrayTitle+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//*[@data-minutelytitle=\""
				+ apiContentTitle + "\"]");
	}

	public static By objFirstAssetInTrayIndex(String trayTitleUI) {
		return By.xpath("//*[contains(text(),\""+trayTitleUI+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//a");
	}

	public static By objAssetInTray(String apiTrayTitle, String apiContentTitle, String index) {
		return By.xpath("//*[contains(text(),\""+apiTrayTitle+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index=\""+ index + "\"]//div[@data-minutelytitle=\"" + apiContentTitle + "\"]");
	}

	public static By objTray(String apiTrayTitle) {
		return By.xpath("//*[contains(text(),\""+apiTrayTitle+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[@class='slick-track']");
	}
	
	public static By obj_Pwa_Back_to_Top_Arrow_btn = By.xpath("//div[contains(@class, 'ic_arrow_back')]");
	
	public static By objAssetInTray(String apiTrayTitle, String dataContentID) {
		return By.xpath("//*[contains(text(),\""+apiTrayTitle+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-contentid=\""+dataContentID+"\"]//img");
	}
	
	public static By objTrayWithTitle(String title) {
		return By.xpath("//*[@class='trayHeader']//h2[contains(text(),\""+title+"\")] | //*[@class='trayHeader']//a[contains(text(),\""+title+"\")]");
	}
	
	//Channels landing page title
	public static By objChannelsLandingPageTitle = By.xpath("//h1[text()='List of TV Channels']");
	//First Channel card in Channels Landing Page
	public static By objFirstChannelCard = By.xpath("(//*[contains(@class,'channelCard')]//img)[1]");
	
	public static By objNavigateToTopStories = By.xpath("//*[contains(@class,'back-top-wrapper')]");
	
	public static By objWebProfileIcon = By.xpath("(//*[text()='Open Menu'])[1]");
	
	//Himadri
	
	public static By objRentPage=By.xpath("//*[@class='plexTrayHeader' and text()='Now Showing']");
	public static By objControlButton=By.xpath("(//div[@class='buttonContainer '])[1]");
	public static By objRentNowBelowZeePlexPage=By.xpath("(//div[@class='rentNowButton'])[1]");
	public static By objConsumptionPage = By.xpath("(//a[@title='Watch Movies Online'])[1]");
	public static By objRentWith299Inr = By.xpath("//div[@class='buttonContainer ']//span[text()='Rent for 299 INR']");
	public static By objNowShowing=By.xpath("//*[@class='plexTrayHeader' and text()='Now Showing']");
			
	public static By objResume = By.xpath("//span[text()='Watch Now']");

	public static By obj_Pwa_Subcription_teaser_btn = By.xpath("//a[@class='subscribeBtn noSelect']//span[contains(text(),'Buy Plan')]");
	
	public static By objChannelsPageTitle(String title) {
		return By.xpath("//h1[contains(text(),'List of "+title+" Serials')]");
	}

	public static By objChannelsSeasonPageTitle(String title,String content) {
		return By.xpath("//h1[contains(text(),'"+content+" "+title+" Episodes')]");
	}
	public static By objCountryCodeDropDownIndia = By.xpath("//*[contains(@class,'react-dropdown-select-type-single')]");

	public static By objFirstAssetInTrayTitle(String trayTitleUI) {
		return By.xpath("//h2[contains(text(),\"" + trayTitleUI
				+ "\")]//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//a[@data-minutelytitle]");
	}
	
	public static By objContinueWatchingRemove=By.xpath("//*[contains(@class,'popupRemoveBtn') and .='Remove']");
	public static By objContinueWatchingRemove (int i) {
		return By.xpath("(//*[contains(@class,'popupRemoveBtn') and .='Remove'])["+i+"]//span");
	}
	public static By objTraysWithAd = By.xpath("//div[@class='page-container']/div[contains(@class,'adContainer') or contains(@class,'tray-container')]");
	public static By objTraysWithAdAndTitle (int i, String title) {
		return By.xpath("(//div[@class='page-container']/div[contains(@class,'tray-container')])["+i+"]//*[@class='trayHeader']//a[contains(text(),\""+title+"\")] | //h2[contains(text(),\""+title+"\")]");
	}
	public static By objOpenProfileMenu = By.xpath("//div[contains(@class,'profileMenuBtn')]//following-sibling::button[.='Open Menu']");
	public static By objPlaylistTrayTitle = By.xpath("//div[@class='similarDiv' or @class='upNextColumn' or @class='recommendCol' or @class='relatedNewsCol']//h2");
	public static By objCarouselElement (int cardNumber) {
		return By.xpath("//div[@data-index='"+cardNumber+"']//div[@class='carouselMain']");
	}
	public static By objWatchListTab (String tab) {
		return By.xpath("//*[@id='"+tab+"']");
	}
	public static By objWatchListItem (String dataContentID) {
		return By.xpath("//div[@data-contentid=\""+dataContentID+"\"]//img");
	}
	public static By objPlaylistCard=By.xpath("//div[@class='playWrap']//div[@data-contentid]");
	public static By objPlaylistCard(int i) {
		return By.xpath("(//div[@class='playWrap']//div[@data-contentid])["+i+"]");
	}
	public static By objPlaylistCardMinutelyTitle(int i) {
		return By.xpath("(//div[@class='playWrap']//div[@data-minutelytitle] | //a[@data-minutelytitle])["+i+"]");
	}
	public static By objPlayerTitle(String title) {
		String xpath="(//div[@class='consumptionMetaDiv']//h1[contains(text(),\""+title+"\")]) | (//div[@class='consumptionMetaDiv']//h2[contains(text(),\""+title+"\")])";
		System.out.println(xpath);
		return By.xpath(xpath);
	}
	public static By objWatchlistedElements= By.xpath("//div[@data-contentid]");
	public static By objMinutely (String trayTitle,String dataContentId) {
		String xpath="//*[text()=\""+trayTitle+"\"]//ancestor::*[@class='trayHeader']//parent::*//div[@data-contentid=\""+dataContentId+"\"]//video";
		System.out.println(xpath);
		return By.xpath(xpath);
	}
	
	public static By objMinutelyPlaylist (String trayTitle,String dataContentId) {
		return By.xpath("//*[text()=\""+trayTitle+"\"]//ancestor::*[@class='playWrap']//parent::*//div[@data-contentid=\""+dataContentId+"\"]//video");
	}
	
	public static By objMinutelyWatchlist (String dataContentId) {
		return By.xpath("//div[@data-contentid=\""+dataContentId+"\"]//video");
	}
	public static By objCardForSharedUrl (String dataContentId,String contentID) {
		return By.xpath("//div[@data-contentid=\""+dataContentId+"\" or @data-contentid=\""+contentID+"\"]");
	}	
	public static By objAssetInPlaylist(String contentID,String dataContentID) {
		return By.xpath("(//*[@class='playWrap']//div[@data-contentid=\""+contentID+"\" or @data-contentid=\""+dataContentID+"\"]//figure//a) | (//*[@class='playWrap']//div[@data-contentid=\""+contentID+"\" or @data-contentid=\""+dataContentID+"\"]/a)");
	}
	public static By objNextButtonInTray(String apiTrayTitle) {
		String xpath="//*[contains(text(),\""+apiTrayTitle+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//button[@class='slick-arrow slick-next']";
		System.out.println(xpath);
		return By.xpath(xpath);
	}
	
	public static By objContinueWatchingCard (int i) {
		return By.xpath("(//*[contains(@class,'removeBtn iconInitialLoad-ic_close')])["+i+"]//parent::*//a");
	}
	
	public static By objNumOfContentsInTray=By.xpath("//div[@class='slick-slider latestEpisodeTray slick-initialized']//div[@class='slick-track']/child::*");

	public static By objContentsInTray(int i) {
			return By.xpath("(//div[@class='slick-slider latestEpisodeTray slick-initialized']//div[@class='slick-track']/child::*)["+i+"]");
		}
	public static By objContentTiltle(int i) {
			return By.xpath("(//div[@class='slick-slider latestEpisodeTray slick-initialized']//div[@class='slick-track']/child::*//h3[@class='cardTitle'])["+i+"]");
		}

	public static By objNextButtoninTray = By.xpath("//*[contains(text(),'Trending Searches')]//ancestor::div[@class='trayHeader']//following-sibling::div//button[@class='slick-arrow slick-next']");

	public static By objFirstAssetInTrayReco(String apiTrayTitle, String apiContentTitle) {
		return By.xpath("//*[contains(text(),\""+apiTrayTitle+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//*[contains(text(), \""+apiContentTitle+"\"]");
	}
	
	public static By objPreviousButtonInTray(String apiTrayTitle) {
		return By.xpath("//*[contains(text(),\""+apiTrayTitle+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//button[@class='slick-arrow slick-prev']");
	}
	
	public static By objPlayerTitle = By.xpath("(//div[@class='episodeDetailContainer']//div[contains(@class,'metaInfo')]//h1)|(//div[@class='movieDetailContainer' or @class='videoDetailContainer' or @class='newsContainer']//h1)");
	
	public static By obj_Pwa_HamburgerMenu = By.xpath("//button[normalize-space()='Open Menu']");
	
	public static By objLoginIcon=By.xpath("//*[contains(@class, 'loginBtn noSelect')]");

	public static By objBuyPlanCTA = By.xpath("//a[contains(@class, 'subscribeBtn noSelect')]");
	
	public static By objUserProfileIcon = By.xpath("//div[@class='profileMenuBtnHeader']//button[contains(.,'Open Menu')]");

	public static By objAccountName = By.xpath("//div[contains(@class, 'userName')]");

	public static By objMyProfilePage = By.xpath("//h1[contains(@class, 'pageTitle')]");
	
	public static By objHeaderMyProfile = By.xpath("//h1[contains(., 'My Profile')]");

	public static By objCarousleTitle = By.xpath("//*[contains(@class, 'slick-slide slick-active slick-center slick-current')]//h2[contains(@class, 'legendTitle')]");
	
	public static By objUserImageIcon = By.xpath("//div[contains(@class, 'userImg')]");

	public static By objNoTVODPack = By.xpath("//p[contains(.,'You have not rented any content yet')]");
	
	public static By objFirstAssetInTray(String trayTitleUI) {
		return By.xpath("//*[contains(text(), \""+trayTitleUI+"\")]//ancestor::div[@class='trayHeader']//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//*[@class='timeMeta']");
	}
	
	public static By objActiveHomeButton = By.xpath("//div[contains(@class,'navMenuHeader navMenuMd ')]//a[contains(@class,'noSelect active ')]");
	
	public static By obj_WEBPwa_HamburgerMenu1 = By.xpath("//button[normalize-space()='Open Menu']");
	
}
