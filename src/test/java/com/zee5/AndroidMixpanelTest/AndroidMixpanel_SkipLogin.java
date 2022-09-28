package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class AndroidMixpanel_SkipLogin {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;
	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void SkipLoginEventValidation(String userType) throws Exception {
		System.out.println("\nLogin");
		Zee5ApplicasterMixPanelBusinessLogic.navigateToHomeScreen();
		Zee5ApplicasterMixPanelBusinessLogic.SkipLoginEvent(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete.");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}	
}
