package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TVCarousel {
	public static By objCarouselTitle = By.xpath("//*[@id='txv_title']");

	public static By objCarouselPlayButton = By.xpath("//*[@id='banner_play_button_layout']");

	public static By objCarouselSubscribeButton = By.xpath("//*[@id='subscribe_text']");

	public static By objCarouselPremiumTag = By.xpath("//*[@id='details_page_content_premium_image']");

	public static By objCarouselMetadata = By.xpath("//*[@id='content_tags_layout']");

	public static By objCarouselDescription = By.xpath("//*[@id='txv_description']");

	public static By objCarouselZeelogo = By.xpath("//*[@id='banner_zee_image']");
	public static By objTvodLoginPopup = By.xpath("");

	public static By objCarouselTitleText(String str) {
		return By.xpath("//*[@id='banner_title' and @text='" + str + "']");
	}

	public static By objSubscribePageLoginPopup = By.xpath("//*[@id='tv_pop_up_un_subscribe_text' and @text='LOGIN']");

	public static By objSubscribePagePacks = By.xpath("//*[@id='price_text']");
	public static By objSubscriptionPlanPage = By.xpath("//*[@id='subscrptionPlan']");

	public static By objActivePlan = By.xpath("//*[@id='active_plans_recycle']");
	
	public static By objTvodPopup = By.xpath("//*[@id='tvod_ok']");
	
	public static By objreloadButton = By.xpath("//*[@id='reload_btn']");
	
	public static By objdemomoonresultImage = By.xpath("//*[@id='search_result_title' and @text='Demo Moon Live']//parent::*//parent::*//child::*//child::*//child::*[@id='search_result_image']");
	public static By objSubscribePopUptitle = By.xpath("//*[@id='pop_up_relative' or @id='subscribe_title' or @text='GET PREMIUM']");
	public static By objLoginPopUptitle = By.xpath("//*[@id='pop_up_relative' or text()='LOGIN'or@text='Login or Register with ZEE5']");
	public static By objAgeRatingInContentDeatil = By.xpath(
			"//*[@class='android.widget.TextView' and @index='5' and @text='U/A 16+' or @text='U' or @text='U/A 7+' or @text='U/A 13+' or @text='A']");

	public static By objAgeRatingInPlayerandInfo = By.xpath("//*[@id='age_rating__view']");

	public static By objCastprogressbarInTv = By.xpath("//*[@id='castControlsProgress']");

	public static By objelapsedTimeInTV = By.xpath("//*[@class='android.widget.TextView' and @index='1']");
	public static By objTotalTimeInTV = By.xpath("//*[@class='android.widget.TextView' and @index='2']");

	public static By objPlayerTitleInTV = By.xpath("//*[@id='castMetadataTitle']");

	public static By objPlayerTitleInWEB = By.xpath("(//div[@class='consumptionMetaDiv']//child::*)[1]");
	
	public static By objsliderbg = By.xpath("//*[@id='slider_bg']");
}
