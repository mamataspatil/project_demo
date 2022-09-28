package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_PlayerCTAs {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({"userType", "clipContent"})
	public void AndroidMixPanel_PlayerCTAs_PauseIcon(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_Pause(pUserType, keyword);
	}
	
	@Test(priority = 2)
	@Parameters({"userType", "clipContent"})
	public void AndroidMixPanel_PlayerCTAs_NextIcon(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_NextIcon(pUserType, keyword);
	}
	
	@Test(priority = 3)
	@Parameters({"userType", "keyword2"})
	public void AndroidMixPanel_PlayerCTAs_BuyPlanCTA(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_BuyPlanCTA(pUserType, keyword);
	}
	
	@Test(priority = 4)
	@Parameters({"userType", "keyword2"})
	public void AndroidMixPanel_PlayerCTAs_LoginCTA(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_LoginCTA(pUserType, keyword);
	}
	
	@Test(priority = 5)
	@Parameters({"userType", "zeeplexContent"})
	public void AndroidMixPanel_PlayerCTAs_RentNowCTA(String pUserType, String keyword) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUserType);
		Zee5ApplicasterMixPanelBusinessLogic.PlayerCTAsEvent_RentNowCTA(pUserType, keyword);
	}
}
