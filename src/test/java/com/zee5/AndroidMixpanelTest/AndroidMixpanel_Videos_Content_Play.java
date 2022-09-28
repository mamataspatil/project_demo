package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_Videos_Content_Play {
	
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
	@Parameters({ "userType", "content1"})
	public void Videos_Content_PlayEvent_Search(String usertype, String keyword) throws Exception {
		System.out.println("\nVideos_content_Play event of content");
		//Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.videos_content_PlayEventOfContentFromSearchPage(usertype, keyword);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "pTabName"})
	public void Videos_Content_PlayEvent_Carousal(String usertype, String pTabName) throws Exception {
		System.out.println("\nVideos_content_Play event of contentfor Carousal");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videos_content_PlayEventOfContentFromCarousal(usertype, pTabName);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "pTabName"})
	public void Videos_Content_PlayEvent_TrayNavigation(String usertype, String pTabName) throws Exception {
		System.out.println("\nVideos_content_Play event of content for Tray navigation");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videos_content_PlayEventOfContentFromTrayNavigation(usertype, pTabName);
	}

	//###############-------END OF TEST-------###############
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}


}
