package com.zee5.DFP;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityAndroidBusinessLogic;

public class AndroidPWADFPScript {

	private Zee5PWASanityAndroidBusinessLogic Zee5PWASanityBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		CharlesConfigure.openCharles();
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Chrome");
	}
	
	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.ZeePWALogin("E-mail", userType);
		Zee5PWASanityBusinessLogic.selectLanguages();		
	}
	
	@Test(priority = 1) 
	@Parameters({ "userType" })
	public void PWADFPHighLevel(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.PWADFPHighLevel(userType);
	}
 
	@AfterClass
	public void tearDown() {
		Zee5PWASanityBusinessLogic.tearDown(); 
		CharlesConfigure.saveCharles("DFPContentlog_");
		PubAds.main(null);
	}
	
}