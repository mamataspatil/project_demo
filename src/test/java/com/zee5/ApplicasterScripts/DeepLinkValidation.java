package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;

public class DeepLinkValidation {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("settings");
	}

	@Test
	@Parameters({ "userType"})
	public void ValidatingDeepLink(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.HeaderChildNode("Validating deeplink");
		if(userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.relaunch(true);
			ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
			ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
			ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
			ZEE5ApplicasterBusinessLogic.deepLinks("signin");
		}
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.deepLinks("Home");	
	}

	@AfterTest
	public void tearDownApp() {
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
