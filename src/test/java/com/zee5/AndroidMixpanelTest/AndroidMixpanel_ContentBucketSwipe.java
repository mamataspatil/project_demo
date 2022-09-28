package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_ContentBucketSwipe {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;
	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({"userType","pTabName"})
	public void AndroidMixPanel_ContentBucketSwipeEventValidation(String userType,String pTabName) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.ContentBucketSwipeEventValidation(userType,pTabName);
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(userType, "Content Bucket Swipe", "Homepage", "SplashPage");
	}
	
	@Test(priority = 2)
	@Parameters({"userType", "clipContent", "trayName"})
	public void AndroidMixPanel_ContentBucketSwipeEventValidation_ConsumptionRail(String userType, String keyword, String trayName) throws Exception {
                             Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.ContentBucketSwipeEvent_ConsumptionPageRail(userType,keyword, trayName);
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(userType, "Content Bucket Swipe", "Homepage", "Search_results");
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Execution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}
