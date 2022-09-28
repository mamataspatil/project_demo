package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class CleverTapPage {

	public static By objCleverTapLogo = By.xpath(".//*[@class='logo-wrapper']");
	
	public static By objPopUpCloseIcon = By.xpath("(//div[@class='close-icon'])");
	
	public static By objEmailID = By.xpath(".//input[@type='email']");
	
	public static By objPasswordEditBx = By.xpath(".//input[@type='password']");
	
	public static By objLoginBtn = By.xpath(".//span[text()='Log In']");
	
	public static By objSegments = By.xpath(".//*[@icon='ctleftnavicon-Segments']//child::*[contains(text(),'Segments')]");
	
	public static By objSearchField = By.xpath(".//*[@id='searchIput']");
	
	public static By objFindBtn = By.xpath(".//*[@id='searchBtn']");
	
	public static By objActivityBtn = By.xpath(".//*[@id='modeTbDash']//child::*[contains(text(),'Activity')]");
	
	public static By objEventName = By.xpath("((.//*[@class='new_day'])[1])//td/span[1]");
	
	public static By objTime = By.xpath("((.//*[@class='new_day'])[1])//td[1]");
	
	public static By objEventProperties = By.xpath("(((.//*[@class='new_day'])[1])//td/span[1])[1]//following-sibling::*[contains(@class,'label-gray')]//span");

	public static By objSegmentsBelowFindPeople = By.xpath(".//*[@class='has-gutter-cont']//*[@class='segments']");
	
	public static By objCreateSegmentsBtn = By.id("createSegmentBtn");
	
	public static By objPastActionsBtn = By.xpath(".//*[@data-id='pastBehaviorSegment']//*[@class='segCards_title' and text()='Actions']//parent::*//child::*[@class='btn btn-small btn-primary']");

	public static By objEventDD = By.xpath("(.//*[@class='v-align-middle-children grid']//*[@class='chzn-single'])[1]");
	
	public static By objSearchEvent = By.xpath("//*[@id=\"evtSelection1_chzn\"]/*[@class='chzn-drop']/*[@class='chzn-search']/input");
	
	public static By objHighlightedOptionBtn = By.xpath(".//*[@class='group-option active-result result-selected highlighted']");
	
	public static By objFilterByBtn = By.xpath(".//*[@class='filterClass']");
	
	public static By objSystemPropertiesDD = By.xpath("//*[@id=\"selPM8_chzn\"]/a");
	
	public static By objSystemPropertiesSearchBtn = By.xpath("//*[@id=\"selPM8_chzn\"]/*[@class='chzn-drop']/*[@class='chzn-search']/input");
	
	public static By objSystemPropertiesSearchResultBtn = By.xpath("(.//*[@class='active-result group-option']/em[text()='Email'])[1]");
	
	public static By objInputValue = By.xpath("//*[@id=\"FEQuery1\"]/div[2]/div/div/div[3]/div[1]/div/span[2]/div[3]/div/input");
	
//:::::::::::::::::Campaign::::::::::::::::::
	public static By objCampaignIcon = By.xpath(".//*[@class='campaigns']");
	
	public static By objCampaignlist = By.xpath(".//*[@class='campaign-name-link']");
	
	public static By objCloneIcon = By.xpath(".//*[@id='targetReschedule']");
	
	public static By objWhoEditIcon = By.xpath(".//*[@class='overview-section__edit']/a[@href='#who']/img");
	
	public static By objWhoEventDD = By.xpath("//*[@id='sel4JU_chzn']/a/div");
	
	public static By objSearchInput = By.xpath(".//*[@id='sel4JU_chzn']//*[@class='chzn-search']/input");
	
	public static By objSearchResult(String EventName) { 
			return By.xpath(".//*[@id='sel4JU_chzn']//*[@class='chzn-results']//*[@class='active-result' and contains(text()='"+EventName+"')]");
	}
	
	public static By objContinueBtn = By.xpath("//*[@id='btn_top_nav_continue']");
	
}
