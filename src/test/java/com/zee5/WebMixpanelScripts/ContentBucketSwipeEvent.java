package com.zee5.WebMixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class ContentBucketSwipeEvent {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "keyword" })
	public void verifyContentBucketSwipeEventFromShowDetailPage(String keyword) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event in Show Detail page");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEventFromShowDetailPage(keyword);
	}

	@Test(priority = 2)
	public void verifyContentBucketSwipeEvent() throws Exception {
		System.out.println("Verify Content Bucket Swipe Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEvent("TV Shows");
	}
	

	@Test(priority = 2)
	public void verifyContentBucketSwipeEventLeft() throws Exception {
		System.out.println("Verify Content Bucket Swipe Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEventLeft("TV Shows");
	}

	@Test(priority = 3)
	@Parameters({ "userType", "keyword1" })
	public void verifyContentBucketSwipeEventInPlaybackPage(String userType, String keyword1) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEventInPlaybackPage(keyword1);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType" })
	public void verifyContentBucketSwipeEventForPremiumBenefitDrawer(String userType) throws Exception {
		System.out.println("Verify Content Bucket Swipe Event For Premium Benefit Drawer");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyContentBucketSwipeEventForPremiumBenefitDrawer(userType);
	}

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}
}
