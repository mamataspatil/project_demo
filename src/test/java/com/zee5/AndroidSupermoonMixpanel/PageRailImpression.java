package com.zee5.AndroidSupermoonMixpanel;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class PageRailImpression {

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
	@Parameters({ "userType", "pTabName", "AdID", "trayIndex"})
	public void PageRailImpressionImpressionEvent(String usertype, String pTabName, String AdID, int trayIndex) throws Exception {
		System.out.println("\n Page Rail impression event");
		Zee5ApplicasterMixPanelBusinessLogic.pageRailImpression(usertype, pTabName, AdID, trayIndex);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "pTabName", "AdID", "trayIndex"})
	public void PageRailImpressionImpressionEvent_MoreIcon(String usertype, String pTabName, String AdID, int trayIndex) throws Exception {
		System.out.println("\n Page Rail impression event when user click on 'More' button and come back to previous screen");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.pageRailImpressionByNavigatingFromListingScreen(usertype, pTabName, AdID, trayIndex);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "AdID", "trayIndex"})
	public void PageRailImpressionImpressionEvent_OtherTab(String usertype, String AdID, int trayIndex) throws Exception {
		System.out.println("\n Page Rail impression event when user click on other tabs and come back to home page or any other landing page");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.pageRailImpressionByNavigatingFromOtherTab(usertype, AdID, trayIndex);
	}
	
	//###############-------END OF TEST-------###############
	
		@AfterTest
		public void tearDownApp() {
			System.out.println("\nQuit the App");
			Zee5ApplicasterMixPanelBusinessLogic.tearDown();
		}

}
