package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Sushma

public class AMDUpcomingPage {

	public static By objItemImage = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_image']");
	
	public static By objItemReleaseDate = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_releasing_date']");
	
	public static By objItemPrimaryText = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text']");
	
	public static By objItemSecondaryText = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_secondary_text']");
	
	public static By objItemGenre = By.xpath("//*[@resource-id='com.graymatrix.did:id/info_genre']");
	
	public static By objItemAgeRating = By.xpath("//*[@resource-id='com.graymatrix.did:id/info_age_rating']");
	
	public static By objItemMetadata = By.xpath("//*[@resource-id='com.graymatrix.did:id/metadata']");
	
	public static By objContentCardMetadata = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_secondary_text'])[1]");
	
	public static By objCertificate = By.xpath("(//*[@resource-id='com.graymatrix.did:id/info_age_rating'])[1]");
	 
	public static By objTitle(String title) {
		return By.xpath(title);
	}
	
	public static String objContentGenre(String title) {
		return ("("+title+"//parent::*//child::*)[5]");
	}
	
	public static String objContentCertificate(String title) {
		return ("("+title+"//parent::*//child::*)[7]");
	}
	
	public static String objContentReleaseDate(String title) {
	return ("("+title+"//parent::*//child::*)[1]");
	}
	
	public static By objShowTitle(String text) {
		return By.xpath("//*[@id='item_primary_text' and contains(text(),\"" + text + "\")]");
	}
    
    public static By objContentCardTitle1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/showTitle'])[1]");
	
	public static By objContentCardTitle = By.xpath("(//*[@resource-id='com.graymatrix.did:id/showTitle'])[1] | //*[@class='android.widget.ImageView' and ./parent::*[@id='cell_top_container' and (./preceding-sibling::* | ./following-sibling::*)[@id='cell_bottom_container']]]");
	
	public static String objContentCardTitle(String title) {
		return ("//*[@text='"+title+"']");
	}

	public static By objGenre = By.xpath("//*[@text='Drama • U • Kannada']");

	public static By objDownloadIcon = By.xpath("//*[@text='Download']");
	

	public static By objGenre(String title) {
			return By.xpath(""+title+"//following-sibling::*//following-sibling::*");
		}

	public static By objContentCardInfo = By.xpath("//*[@id='cell_top_container'] | (//*[@id='upcoming_toolbar_title']//following::*//*[@id='imageContainer']//following-sibling::*//child::*)");
	
	public static By objContentCard1 = By.xpath(
			"//*[@id='content_title'] | ((//*[@text='Upcoming']//parent::*)//following-sibling::*//following-sibling::*//following-sibling::*)[1]");

	public static By objContentCard = By.xpath("(//*[@id='cell_top_container']//*[@class='android.widget.ImageView'])[1] | (//*[@resource-id='com.graymatrix.did:id/item_image'])[1]");

	public static By objBuyPlanInUpcomingScreen = By.xpath("//*[@id='upcoming_toolbar_buy_plan']");
}
