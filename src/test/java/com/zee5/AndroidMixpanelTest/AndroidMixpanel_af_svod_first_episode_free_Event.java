package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_af_svod_first_episode_free_Event {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({"userType","firstEpisode"})
	public void MixPanel_af_svod_first_episode_free_EventValidation(String userType,String firstEpisode) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
		Zee5ApplicasterMixPanelBusinessLogic.verify_af_svod_first_episode_free_Event(userType, firstEpisode);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void af_svod_first_episode_free_Event_Carousal(String usertype) throws Exception {
		System.out.println("\n af_Svod_First_Episode_Free event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.afSvodFirstEpisodeFreeForCarousalContent(usertype);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType"})
	public void af_svod_first_episode_free_Event_TrayNavigation(String usertype) throws Exception {
		System.out.println("\\n af_Svod_First_Episode_Free event of content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.afSvodFirstEpisodeFreeForTrayNavigation(usertype, "Web Series");
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}

}
