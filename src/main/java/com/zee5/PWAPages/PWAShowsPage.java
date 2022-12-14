package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAShowsPage {

	// Search Btn
	public static By objSearchBtn = By.xpath("//div[contains(@class,'searchBtn')]");

	// Play Btn On carousel
	public static By objPlayBtn = By.xpath("//div[@class=\"playBtn\"]");

	// Play Btn Txt On carousel
	public static By objPlayTxt = By.xpath("//div[@class=\"noSelect premiumBtn\"]");

	// First asset image from first content rail
	public static By objFirstAssetImageFirstRail = By
			.xpath("(//div[@class='slick-list'])[1]//div[@data-index='0']//img");

	// First asset image from Live TV card rail
	public static By objFirstAssetImageLiveTvCard = By.xpath("//h2[text()='FREE Channels']//parent::*//following-sibling::*//div[@data-index='0']//img");
	// First asset title from Live TV card rail
	// Share button in Show Details page
	public static By objShareIcon = By.xpath("//div[contains(@class,'shareCompIcon')]");

	
	  // Facebook app for Share in Show Details Page public static By
	  public static By objFacebookApp = By.xpath("//*[@text='Facebook' or @text='News Feed' or @text='Post']");
	 
	// Native Share window
	public static By nativeShareWindow = By.xpath("//*[@class='android.widget.LinearLayout' and @index='3']//*[@class='android.widget.ImageView']");

//	===========================================================================================
//	SANITY 

//	BHAVANA SHOWS MODULE
	// (>) at end of the tray
	public static By objEndOfTray = By.xpath(
			"//body/div[@id='root']/div[contains(@class,'appContainer')]/div[contains(@class,'routerContainer')]/div[contains(@class,'pageContentWrapper')]/div[contains(@class,'pageLanding')]/div[contains(@class,'page-container')]/div[10]/div[1]/div[1]/div[1]");

	// Content card in Play and Win
	public static By objContentCardInPlayandWin = By
			.xpath("//div[@data-minutelytitle='ZEE5 Super Family League - Play & Win']");

	public static By objViewAll = By.xpath(
			"//body/div[@id='root']/div[contains(@class,'appContainer')]/div[contains(@class,'routerContainer')]/div[contains(@class,'pageContentWrapper')]/div[contains(@class,'pageLanding')]/div[contains(@class,'page-container')]/div[1]/div[1]/div[1]/div[1]");

	public static By objViewAllWrap = By.xpath("//div[@class='viewAllWrap']");

	public static By objPlayWin = By.xpath("//div[contains(text(),'Play & Win')]");

//		CONTENT MODULE
	public static By objTwitterPostBtn1 = By.xpath("//*[@text='Tweet' or @text='TWEET']");
	
	public static By objEpisodeTrayinShowdetailPage = By.xpath("//div[@class='AllEpisodesListDiv']");
	public static By objShowDetailEpisodeDropdown = By
			.xpath("(//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[4]");

	public static By objShowDetailEpisodeDropdownValues(int i) {
		return By.xpath(
				"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span["
						+ i + "]");
	}

	public static By objSelectedEpisodeinDropdown = By.xpath(
			"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span[@aria-selected='true']");

	public static By objShowDetailNonSelectedEpisodeDropdownValues(int i) {
		return By.xpath(
				"((((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span[@aria-selected='false'])["
						+ i + "]");
	}
	public static By objShowsTitle = By.xpath("//*[contains(@class,'metaDataDiv')]//h1");

	public static By objWatchLatestCTA = By.xpath("//*[@class='playWrap']//following-sibling::p");

	public static By objLatestepisode = By.xpath("//*[@class='showWrap']//*[@class='iconsWrap latest'] ");

	public static By objPlayforPremium = By.xpath("//*[@class='showDetailIcon']//*[@class='playIcon']");

	public static By objPlayAndWin = By.xpath("//a[contains(text(),'Play & Win')]");

	public static By objGuessScore = By.xpath("//*[@data-minutelytitle='Guess the score & win']");

	public static By objWatchLatestCTAPlayicon= By.xpath("//div[@class='playWrap']//div[@class='playBtn']");
	
	public static By objTwitterCloseBtn = By.xpath("//*[@class='android.widget.ImageButton' and @content-desc='Navigate up']");
	
	public static By objTwitterDeletePost = By.xpath("//*[@text='DELETE']");
	// POST button in Facebook app
//	public static By objFacebookPostBtn = By.xpath("//*[@text='POST' or @text='Post' or @text='SHARE']");
	public static By objFacebookPostBtn = By.xpath("//*[@contentDescription='POST']"); 

	public static By metainfolist=By.xpath("//div[contains(@class,'metaInfo')]//span");
	
	public static By objThirdContentInTray=By.xpath("//div[@data-index='2']//img");
	
	public static By objFirstContentInTray=By.xpath("//div[@data-index='0']//img");
	public static By objSecondSetEpisodeTray = By.xpath("//div[@class='dropDownWrapper']//span[2]");
	
	public static By objallowCaps = By.xpath("//*[@text='ALLOW']");
	
	public static By objEpisodesSetTray = By.xpath("//div[@class='allEpisodeSelect']");
	
	public static By objTwitterApp = By.xpath("//*[@text='Tweet' or @text='Twitter']");
	
	public static By objEduraacard=By.xpath("//div[@class='viewAllGrid']");

	public static By objEduraalabel=By.xpath("//div[@class='clickWrapper']");
	
	public static By objUpgradeCTAInShowDetails = By.xpath("//*[@class='premiumBtn' and text()='Upgrade']");
	
	public static By objGetClubCTAInShowDetails = By.xpath("//*[@class='premiumBtn' and text()='Get Club']");

	public static By objWatchPromo = By.xpath("//p[text()='Watch Promo']//parent::*//div[@class='playBtn']");
	
	// First asset title from first content rail
	public static By objFirstAssetTitleFirstRail = By.xpath("(//div[@class='slick-list'])[1]//div[@data-index='0']//h3[@class='showCardTitle']");
	
	public static By objEpisodeCard = By.xpath("//div[@data-index='0']//a");
	
	public static By objEpisodeCardTwo = By.xpath("//div[@data-index='1']//a");
	
	public static By objPlayAndWinFirstItem = By.xpath("//h2//a[contains(text(),'Play ') and contains(text(),' Win')]//ancestor::div[@class='trayHeader']//following-sibling::div[@class='latestEpisodeTrayWrapper']//*[@data-index='0']//figure//img");
	
	public static By objPlayAndWinViewAllFirstItem=By.xpath("//*[@class='viewAllGrid']//figure//img");
	
	public static By objThirdAssetImageFirstRail = By.xpath("(//div[@class='slick-list'])[1]//div[@data-index='2']");
	
	public static By objBackToTopArrow = By.xpath("//*[contains(@class, 'iconOther-ic_arrow_back')]");
	
	public static By objRecoTrayTitle(String str) {
		return By.xpath("//h2[contains(text(),'"+str+"')]");
	}

	public static By objEpisodeDD = By.xpath(".//*[contains(@class,'react-dropdown-select-content react-dropdown-select-type-single css-jznujr-ContentComponent')]/span");
	
	public static By objTrayTitle1(String str) {
		return By.xpath("//div[@class='trayHeader']//h2[contains(text(),'"+str+"')]");
	}
	
	public static By objShowDetailsEpisodeDropdownText = By.xpath("(//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[4]//span");
	
	public static By objGetPremiumCTAInShowDetails = By.xpath("//*[@class='premiumBtn' and text()='Buy Plan']");
	
	public static By objShows = By.xpath("(//a[contains(text(),'TV Shows')])[2]");
	
	public static By objEpisodeCard2 = By.xpath("(//div[@class='clickWrapper'])[2]");

	public static By objSetEpisodeTray = By.xpath("//div[@class='dropDownWrapper']//span[2]");

	public static By objLastAssetEpisodeFirstRail = By.xpath("((//div[@data-index='9']//div[@class='showDuration']//span)[1])[last()]");
	
	public static By objFirstAssetEpisodeFirstRail = By.xpath("(//div[@data-index='0']//div[@class='showDuration']//span)[1]");
	
//	public static By objShowsBannerTitle = By.xpath("//*[contains(@class,'bannerTitle hidden')]//h1");
	public static By objShowsBannerTitle = By.xpath("//*[contains(@class,'h1class')]//h1");
	
	public static By objSecondAssetImageFirstRail = By.xpath("(//a[@class='noSelect content'])[2]//img[1]");

	public static By objFirstAssetDurationFirstRail = By.xpath("((//div[@class='slick-list'])[1]//div[@data-index='0']//div[@class='showDuration']//span)[5]");
	
	public static By objShowdeatilPlayIcon = By.xpath("//a[@class='iconsWrap latest']");
	
	public static By objFirstAssetTitleLiveTvCard = By.xpath("//*[text()='News TV Channels']/parent::*//following::*//div[@class='slick-track']//div[@data-index='0']//h3[@class='cardTitle']//a");
	
	}
