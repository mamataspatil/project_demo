package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDVMAXAPP {

	
	public static By objZeeMoreButton = By.xpath("//*[@id='bb_bottom_bar_title' and @text='More']");
	public static By objZeeLoginRegisterLink = By.xpath("//*[@text='Login/Register for best experience']");
	public static By objEmailIdField = By.xpath("//*[@id='edit_email']");
	public static By objProceedBtn = By.xpath("//*[@id='btnProceed']");
	public static By objPasswordField = By.xpath("//*[@id='txtET_password_input']");
	public static By objLoginBtn = By.xpath("//*[@id='btnLogin']");
	
	public static By objHomeBottomBtn = By.xpath("//*[@resource-id='com.graymatrix.did:id/bb_bottom_bar_title' and @text='Home']");
	public static By objSearchIcon = By.xpath("(//*[@id='toolbar'])//child::*[3]");
	public static By objSearchEditBox = By.xpath("//*[@id='searchBarText'] | //*[@id='searchToolbarTitle']");
	public static By objSearchBoxBar = By.xpath("//*[@id='searchBarText'] | //*[@id='searchToolbarTitle']");
	public static By objAllTab = By.xpath("(//*[@text='All'])");
	public static By objShowsTab = By.xpath("(//*[@text='Shows'])");
	public static By objSearchResultContainsText (String title) {
		return By.xpath("//*[@class='android.widget.TextView' and contains(@text,\""+title+"\")]");
	}
	public static By objAd = By.xpath("(//*[contains(text(),'Ad ·')]) | (//*[contains(text(),'Ad 1 of 2 ·')]) | (//*[contains(text(),'Ad 2 of 2 ·')])");
	public static By objAd1 = By.xpath("(//*[contains(text(),'Ad 2 of 2 ·')])");
	
	public static By objAdProgressTime = By.xpath("//*[@id='progressCount']");
	
	public static By objSkipCTA = By.xpath("//*[@id='txtSkip']");
	public static By objKnowMoreLink = By.xpath("//*[@text='Know More']");
	public static By objVisitAdvertiserLink = By.xpath("//*[@text='Visit advertiser']");
	public static By objSubscribeLink = By.xpath("//*[@text='Subscribe']");
	
	public static By objCompanionAdTitle = By.xpath("((((//*[@id='companion'])//child::*)//child::*//child::*)//child::*//*[@class='android.widget.TextView'])[2]");
	
	public static By objPause = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_pause']");
	public static By objPlay = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_play']");
	public static By objProgressBar = By.xpath("//*[@id='progress']");

}
