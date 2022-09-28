package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterChromeCastBusinessLogic;
import com.utility.Utilities;

public class Android_ChromeCast {

	
	private Zee5ApplicasterChromeCastBusinessLogic Zee5ApplicasterChromeCastBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;
		Zee5ApplicasterChromeCastBusinessLogic = new Zee5ApplicasterChromeCastBusinessLogic("zee");
	}

	@Test(priority = 0)
	public void Chromecast() throws Exception {
		Zee5ApplicasterChromeCastBusinessLogic.ValidateChromeCast();
	}
		
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5ApplicasterChromeCastBusinessLogic.tearDown();
	}
}
