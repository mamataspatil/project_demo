package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAKidsPage {
	
	public static By objPlayButton = By.xpath("//*[contains(@class,'slick-active')]//span[text()='Play']");
	
	public static By objExitZee5Continue = By.xpath("//button//*[text()='Continue']");
	
	public static By objFirstContentOfEduauraa = By.xpath("(.//*[@class='viewAllWrap']//child::*//*[@class='clickWrapper'])[1]");
	
	public static By objExpanderIconInEduauraaPlayback = By.xpath("(.//*[contains(@class,'Collapsible__trigger is-')])[1]"); 
	
	public static By objTermAndCondition = By.xpath(".//*[@href='https://www.zee5.com/termsofuse']");
	
	public static By objPrivacyPolicy = By.xpath(".//*[@href='https://www.zee5.com/privacypolicy']");

	public static By objWatchButton = By.xpath("//*[contains(@class,'slick-active')]//span[text()='WATCH']");
	
	public static By objHerocarouselPlayBtn(String titleName) {
		return By.xpath("//div[@aria-hidden='false']/following::h2[contains(text(), '" +titleName+ "')]/parent::div/following-sibling::div/child::div[@class='playIcon']");
	}
	
	public static By objBuyPlanCTAOnCarousel = By.xpath("//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'noSelect subscribeBtn')]");
	public static By objGoToEduauraa=By.xpath("//*[text()='Keep Learning']");
	public static By objSecondItemLearnWithEduauraaTray = By.xpath("//*[@class='titleLink' and text()='Learn with Eduauraa']//ancestor::*[@class='trayContentWrap']//*[@class='slick-list']//*[@data-index='1']");
	
	public static By objKeepLearning = By.xpath("(//div[@class='buttonContainer '] //span[text()='Keep Learning'])");
}
