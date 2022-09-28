package com.zee5.AndroidMixpanelTest;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class PaymentPage {

private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;
	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}
	
	@Test(priority = 1)
	@Parameters({ "userType"})
	public void Paymentpage(String usertype) throws Exception {
		System.out.println("\nPayment page");
		Zee5ApplicasterMixPanelBusinessLogic.subscriptionValidation(usertype);
	}
	
	//###############-------END OF TEST-------###############
	
		@AfterTest
		public void tearDownApp() {
			System.out.println("\nQuit the App");
			Zee5ApplicasterMixPanelBusinessLogic.tearDown();
		}

}
