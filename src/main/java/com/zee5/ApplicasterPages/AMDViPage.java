package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDViPage {
	public static By objBlockerLoginLink =By.xpath("//*[@id='existingZee5User']");
	
	public static By objHamburgerMenu = By.xpath("//*[@contentDescription='Vi Movies and TV']"); //*[@id='btnOk'] 
	
	public static By objZee5Logo =By.xpath("(//*[@id='cp_logo'])[6]");
	
	public static By objCarouselBanner = By.xpath("(//*[@id='overlay_ripple'])[4]");
	
	public static By objZEE5NewMoviesReleased = By.xpath("//*[@resource-id='com.vodafone.vodafoneplay:id/textview_genre_title']");

	public static By objSearchIcon= By.xpath("//*[@id='search_button']");
	
	public static By objSearchBox = By.xpath("//*[@id='search_src_text']");
	
	public static By objFirstSearchResult1 = By.xpath("//*[@resource-id='com.vodafone.vodafoneplay:id/thumbnail_layout']");

	
	public static By objFirstEpisode = By.xpath("(//*[@id='imageview_play_alarm_download'])[1]");

	 public static By objFirstSearchResult1(String text)
	 {
		 return By.xpath("//*[@resource-id='com.vodafone.vodafoneplay:id/title' and @text='"+text+"']");
	 }
	
		public static By objEnterYourMobileNumberTextbox = By.xpath("//*[@resource-id='com.vodafone.vodafoneplay:id/otp_mobile_no']");

		public static By objContinue = By.xpath("//*[@resource-id='com.vodafone.vodafoneplay:id/otp_btn_1']");
		
		public static By objBackToViMovie = By.xpath("//*[@resource-id='com.graymatrix.did:id/backToVIApp']");
		
		public static By objViLoGO = By.xpath("//*[@resource-id='com.vodafone.vodafoneplay:id/app_logo']");
		
		
		public static By objLaunchViMoviesTV = By.xpath("//*[@resource-id='com.graymatrix.did:id/btnOk']");
		public static By obJLoginCTAVi = By.xpath("//*[@resource-id='com.graymatrix.did:id/existingZee5User']");


}
