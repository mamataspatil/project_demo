package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class CarouselBannerClickEvent {
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
	@Parameters({"Tab" })
	public void verifyCarouselBannerClickEvent(String tab) throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Home");//tab and carousel item as parameters
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("TV Shows");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Movies");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Web Series");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("News");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Premium");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Play");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Kids");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Videos");
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}

}
