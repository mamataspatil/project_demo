package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class SearchEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLogin(userType);
	}

	@Test(priority = 1)
	public void verifySearchButtonClickEvent() throws Exception {
		System.out.println("Verify Search Button Click Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchButtonClickEvent();
	}

	@Test(priority = 2)
	@Parameters({ "keyword4" })
	public void verifySearchResultClickedEvent(String keyword4) throws Exception {
		System.out.println("Verify Search Result Clicked Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchResultClickedEvent(keyword4);
	}

	@Test(priority = 3)
	public void verifySearchExecutedEvent() throws Exception {
		System.out.println("Verify Search Executed Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchExecutedEvent();
	}

	@Test(priority = 4)
	public void verifySearchCancelledEvent() throws Exception {
		System.out.println("Verify Search Cancelled Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchCancelledEvent();
	}
	
	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
