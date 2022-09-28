package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAVIAppPage {

//	VI APP
	public static By objHamburgerMenu = By.xpath("//*[@content-desc='Vi Movies and TV']");
	
	public static By objContentPartner = By.xpath("//*[@id='cp_logo']");
	
	public static By objMobileNoField = By.xpath("//*[@id='otp_mobile_no']");
	
	public static By objContinueBtn = By.xpath("//*[@id='otp_btn_1']");
	
	public static By objOtpField = By.xpath("//*[@id='otp_text_enter_otp']");
	
	public static By objGOIcon = By.xpath("//*[@id='otp_btn_2']");
	
	public static By objFirstZee5Content = By.xpath("(//*[@resource-id='com.myplex.vodafonestaging:id/thumbnail_movie'])[1]");
	
	
//	My Subscription Page
	public static By objViMTVVIPPackTitle = By.xpath("//*[@text='ViMTV VIP Pack']");
	
	public static By objFor30DaysTxt = By.xpath("(//*[@class='packDuration'])[1]");
	
	public static By objDateOfPurchaseTxt = By.xpath("(//*[@class='packDuration'])[2]");
	
	public static By objAutoRenewsOnTxt = By.xpath("//*[@class='packExpiery']");
	
	public static By objStatusValue = By.xpath("//*[@text='Status']//following-sibling::*[@class='value']");
	
	public static By objPaymentModeValue = By.xpath("//*[@text='Payment Mode']//following-sibling::*[@class='value']");
	
	public static By objAutoRenewalValue = By.xpath("//*[@text='Auto Renewal']//following-sibling::*[@class='value']");
	
	public static By objCancelSubscriptionBtn = By.xpath("//*[@text='Cancel Subscription']");
	
	public static By objBrowseAllPacksBtn = By.xpath("//*[@class='btnContnet' and text()='Browse All Packs']");
	
//	Consumption Page

	public static By objProgressBar = By.xpath("//*[@class='playkit-progress-bar']");
	
	public static By objContentTitle = By.xpath("//*[@class='consumptionMetaDiv']//h1");
	
	public static By objOtherContentOnConsumption = By.xpath("(//*[@class='trayContentWrap']//*[@class='content'])[1]");
	
	
//	Zee5 Hamburger Menu
	public static By objMyAccount = By.xpath("//*[@class='menuTitle noSelect  menuForMyAccount']//*[@text='My Account']");
	
	public static By objMySubscription = By.xpath("//*[@class='menuGroup active']//*[@text='My Subscription']");
	
	public static By objAthenticationDevice = By.xpath("//*[@text='Authenticate Device']");
	
//	Zee5 Profile Icon
	
	public static By objUserPhoneNumber = By.xpath("");

}
