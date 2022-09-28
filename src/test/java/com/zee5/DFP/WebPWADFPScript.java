package com.zee5.DFP;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWASanityWEBBusinessLogic;

public class WebPWADFPScript {

	private Zee5PWASanityWEBBusinessLogic Zee5WEBPWASanityBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		CharlesConfigure.openCharles();
		Zee5WEBPWASanityBusinessLogic = new Zee5PWASanityWEBBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5WEBPWASanityBusinessLogic.ZeeWEBPWALogin(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "dfpAdContent" })
	public void DFPValidation(String userType, String dfpAdContent) throws Exception {
		System.out.println("DFP Validation");
		Zee5WEBPWASanityBusinessLogic.DFPValidation(userType, dfpAdContent);
	}

	@AfterClass
	public void tearDown() {
		Zee5WEBPWASanityBusinessLogic.tearDown();
		CharlesConfigure.saveCharles("DFPContentlog_");
		PubAds.main(null);
	}
}