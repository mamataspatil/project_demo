package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_af_preroll_adview {
	
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
	@Parameters({ "userType", "freeContent"})
	public void af_preroll_adviewEvent_Search(String usertype, String keyword) throws Exception {
		System.out.println("\naf_preroll_adview event of content");
		//Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.af_preroll_adview(usertype, keyword);
	}

	//###############-------END OF TEST-------###############
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}