package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_VideoView {

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
	@Parameters({ "userType", "pTabName"})
	public void VideoViewEvent_TrayNavigation(String userType,String pTabName) throws Exception {
		System.out.println("\nVideo View event of content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
//		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventFromTrayNavigationPage(userType, pTabName);
	}
	

	@Test(priority = 3)	
	@Parameters({ "userType","clipContent"})
	public void VideoViewEvent_Search(String userType,String clipContent) throws Exception {
		System.out.println("\nVideo View event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.VideoViewEventOfcontentFromSearchPage(userType, clipContent);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "pTabName" })
	public void VideoViewEvent_Carousal1(String usertype, String pTabName) throws Exception {
		System.out.println("\nVideo view event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.videoViewEventForCarousalContent(usertype, pTabName);
	}
	
	

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}