package com.zee5.PWAPages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;

public class PWANewsPage {

//	 Right on News
	public static By objRight = By
			.xpath("//div[@class='slick-slider slick-initialized']//*[contains(@class,'slick-arrow slick-next')]");
//	 Left on News
	public static By objLeft = By
			.xpath("//div[@class='slick-slider slick-initialized']//*[contains(@class,'slick-arrow slick-prev')]");
//	 Content Title on carousel
	public static By objContTitleOnCarousel = By.xpath("//div[@class=\"descWrap\"]/child::h2[@class=\"legendTitle \"]");
//	SANITY YASHASHWINI NEWS MODULE
	public static By objContent = By.xpath("(//div[@class='content'])[2]");
	public static By objLive = By.xpath("//div[@class='playkit-live-tag']");
	public static By objLiveNews = By.xpath("(//div[@class='titleLink'])[1]");
	public static By objVideoOnDemand = By.xpath("(//div[@class='titleLink'])[2]");
	public static By objViewAll = By.xpath("(//div[@class='arrow iconInitialLoad-ic_viewall noSelect'])[1]");
	public static By objViewAllWrap = By.xpath("//div[@class='viewAllWrap']");
	
//	 Verifying different Feature on player screen
	public static By objFullScreen = By.xpath("//i[@class='playkit-icon playkit-icon-maximize']");
	public static By objNonLiveNews = By.xpath("(//div[@class='content'])[6]");
	public static By objSeekbar = By.xpath("//div[@class='playkit-progress-bar']");
	public static By ContentPlayer = By.xpath("//div[@id='player-placeholder']");
	public static By objBannerMute = By.xpath("//*[@class='playkit-icon playkit-icon-volume-mute']");
	public static By objBannerUnMute = By.xpath("//*[@class='playkit-icon playkit-icon-volume-waves']");
	public static By objLiveNewsConsumptionsTitle = By.xpath("//div[contains(@class,'onsumptionMetaDiv')]//h1");
	public static By objAutoPlayContent =By.xpath("//*[@class='playkit-icon playkit-icon-volume-waves']//ancestor::*[@id='anchorTag']");
	public static By objVolume = By.xpath("//*[@class='playkit-control-button-container playkit-volume-control' or @class='playkit-control-button-container playkit-control-volume playkit-volume-control']");
	
	public static By objAutoplayCarousel = By.xpath("//*[contains(@class,'slick-current')]//*[contains(@class,'carouselMain')]//video");
	
	public static By objCatchUp = By.xpath(".//*[@class='relatedNewsCol']//*[@class='upnextDate']");
	
	public static By objCatchUpContents = By.xpath(".//*[@class='relativeNewsWrap catchUp']//*[@class='relNewsCard ']");
	
	public static By objLiveIndicator = By.xpath("(.//*[@class='relNewsCard liveCard']//*[@class='liveIndicator'])[last()]");
	
	public static By objShareBtn = By.xpath(".//*[@class='channelConsumptionMetaDiv']//*[@class='shareCompIcon iconInitialLoad-ic_share' and @title='Share']");
	
	public static By objDatePicker(String Date) {
		return By.xpath("(.//*[text()='"+Date+"' and contains(@aria-label,'Choose')])[2]");
	}
	
	public static By objPlayIcon = By.xpath(".//*[@class='playkit-icon playkit-icon-play']");
	
	public static By objPause = By.xpath(".//*[@class='playkit-icon playkit-icon-pause']");
	
	public static By objtimer = By.xpath("(.//*[@class='playkit-time-display'])[1]");
	
	public static By objGoLiveBtn = By.xpath(".//*[@class='goliveBtn']");
	
	public static By objTodayBtn = By.xpath("(.//*[@class='textHolder'])[1]");
	
	public static By objDatePicker = By.xpath("(.//*[@class='react-datepicker__month-container'])[1]");
	
	public static By objLiveNewsCard = By.xpath("(//div[@data-minutelytitle])[3]");
	
}
