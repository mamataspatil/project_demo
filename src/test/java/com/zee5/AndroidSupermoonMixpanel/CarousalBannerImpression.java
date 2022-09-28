package com.zee5.AndroidSupermoonMixpanel;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class CarousalBannerImpression {
	
private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;
	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void AndroidAppMixPanelLogin(String userType) throws Exception {
		System.out.println("\nLogin");
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType", "pTabName", "AdID"})
	public void CarousalBannerImpressionEvent(String usertype, String pTabName, String AdID) throws Exception {
		System.out.println("\n Carousal Banner impression event");
		Zee5ApplicasterMixPanelBusinessLogic.CarousalBannerImpression(usertype, pTabName, AdID);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "pTabName", "AdID"})
	public void CarousalBannerImpressionEvent_VerticalIndex_HorizontalIndex(String usertype, String pTabName, String AdID) throws Exception {
		System.out.println("\n Carousal Banner impression event with Vertical Index and Horizontal Index property");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.CarousalBannerImpressionForVerticalIndexAndHorizontalIndexproperty(usertype, pTabName, AdID);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "AdID"})
	public void CarousalBannerImpressionEvent_NavigatingBackFromOtherTab(String usertype, String AdID) throws Exception {
		System.out.println("\n Carousal Banner impression event navigating back from other tab");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.CarousalBannerImpression_NavigatingBackFromOtherTab(usertype, "Movies", AdID);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "pTabName", "AdID"})
	public void CarousalBannerImpressionEvent_ClickingContentThumbhnail_BackToPreviousPage(String usertype, String pTabName, String AdID) throws Exception {
		System.out.println("\n Carousal Banner impression event by clicking Content thumbhnail and navigating back to previous page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.CarousalBannerImpressionEvent_ClickingContentThumbhnail_BackToPreviousPage(usertype, pTabName, AdID);
	}
	


	//###############-------END OF TEST-------###############
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}


}
