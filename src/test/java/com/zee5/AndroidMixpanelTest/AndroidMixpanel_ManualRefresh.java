package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_ManualRefresh {
	
	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;
	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}
	
	@Test(priority = 0)
	@Parameters({"userType"})
	public void AndroidMixPanel_OnboardingJourney(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(pUsertype);
	}
	
	@Test(priority = 1)
	@Parameters({"userType","pTabName"})
	public void AndroidMixPanel_ManualRefreshEventValidation_Home(String pUsertype,String pTabname) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,pTabname);
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "home", "Homepage");
	}
	
	@Test(priority = 2)
	@Parameters({"userType"})
	public void AndroidMixPanel_ManualRefreshEventValidation_TVShows(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,"TV Shows");
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "tvshows", "Homepage");
	}
	
	@Test(priority = 3)
	@Parameters({"userType"})
	public void AndroidMixPanel_ManualRefreshEventValidation_Movies(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,"Movies");
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "movies", "Homepage");
	}
	
	@Test(priority = 4)
	@Parameters({"userType"})
	public void AndroidMixPanel_ManualRefreshEventValidation_WebSeries(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,"Web Series");
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "originals", "Homepage");
	}
	
	@Test(priority = 5)
	@Parameters({"userType"})
	public void AndroidMixPanel_ManualRefreshEventValidation_News(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,"News");
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "news", "Homepage");
	}
	
	@Test(priority = 6)
	@Parameters({"userType"})
	public void AndroidMixPanel_ManualRefreshEventValidation_Premium(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,"Premium");
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "premium", "Homepage");
	}
	
	@Test(priority = 7)
	@Parameters({"userType"})
	public void AndroidMixPanel_ManualRefreshEventValidation_Music(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,"Music");
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "music", "Homepage");
	}
	
	@Test(priority = 8)
	@Parameters({"userType"})
	public void AndroidMixPanel_ManualRefreshEventValidation_Eduauraa(String pUsertype) throws Exception {
		Zee5ApplicasterMixPanelBusinessLogic.JourneyToCaptureManualRefreshEvent(pUsertype,"Eduauraa");
		Zee5ApplicasterMixPanelBusinessLogic.EventValidation(pUsertype, "Manual Refresh", "kids", "Homepage");
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Execution Complete");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}
