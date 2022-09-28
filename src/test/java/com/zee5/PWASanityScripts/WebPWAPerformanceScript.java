package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityWEBBusinessLogic;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class WebPWAPerformanceScript {

	private Zee5PWASanityWEBBusinessLogic Zee5WEBPWASanityBusinessLogic;

	@BeforeTest(groups = {"required_setup"})
	public void init() throws Exception {
		// zee5WebBusinessLogic.relaunchFlag = false;
		Zee5WEBPWASanityBusinessLogic = new Zee5PWASanityWEBBusinessLogic("Chrome");
	}
	
	@Test(priority=1, groups = {"performance"})
	@Parameters({ "userType" })
	public void PWAWEBPerformance(String userType) throws Exception {		
		//Zee5WEBPWASanityBusinessLogic.pwaPerformance(3);
		Zee5WEBPWASanityBusinessLogic.pwaPerformance_NavigationTimingAPI(1);
	}
	
	//@AfterClass
	public void tearDown() {
		Zee5WEBPWASanityBusinessLogic.tearDown();
	}
}