package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class PWA_Web_Player {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

	@Test
	public void checkPageName() throws Exception {
//		Zee5PWAWEBMixPanelBusinessLogic.clickOnCarousel("Shows","free");
		Zee5PWAWEBMixPanelBusinessLogic.clickOnTrayContent("Home","trailer");
	}

//	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLogin(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
	
	
	public static void main(String[] args) {
	String value = "Vick-Rick Jay Glen";
	String expected = "Vick-RickJayGlen";
		
	System.out.println(value.replaceAll("\\s", "").trim().equals(expected));
	}
}
