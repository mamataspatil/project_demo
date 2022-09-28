package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class PWA_Web_Journey {

	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAWEBMixPanelLogin(String userType) throws Exception {
		System.out.println("Login");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLogin(userType);
	}

	@Test(priority = 2)
	public void verifySessionEvent() throws Exception {
		System.out.println("Verify Session Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch(true);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void verifyCarouselBannerClickEvent(String userType) throws Exception {
		System.out.println("Verify Carousel Banner Click Event");
//		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerClickEvent("Home");
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void verifyParentalRestriction(String userType) throws Exception {
		System.out.println("Verify Parental Restriction Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLoginForParentalControl(userType);
		Zee5PWAWEBMixPanelBusinessLogic.verifyParentalRestrictionEvent(userType, "NoRestriction");
	}

	@Test(priority = 9)
	@Parameters({ "keyword1" })
	public void verifyClearSearchHistoryEvent(String keyword1) throws Exception {
		System.out.println("Verify Clear Search Histroy Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.clearSearchHistoryEvent(keyword1);
	}

	@Test(priority = 10)
	public void verifyScreenViewEvent() throws Exception {
		System.out.println("Verify Screen View Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyScreenViewEvent("Shows");
	}

	@Test(priority = 14)
	@Parameters({ "userType" })
	public void verifySetReminderEventForUpcomingProgram(String userType) throws Exception {
		System.out.println("Verify Set Reminder Event For Upcoming Program");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifySetReminderEventForUpcomingProgram(userType);
	}

	@Test(priority = 32)
	@Parameters({ "userType", "audioTrackContent" })
	public void verifyAdClickEventForFreeContent(String userType, String audioTrackContent) throws Exception {
		System.out.println("Verify Ad Click Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdClickEventForFreeContent(userType, audioTrackContent);
	}

	@Test(priority = 33)
	@Parameters({ "userType", "keyword4" })
	public void verifyPauseEventForFreeContent(String userType, String keyword4) throws Exception {
		System.out.println("Verify Pause Event For Free Content");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyPauseEventForFreeContent(userType, keyword4);
	}

	@Test(priority = 34)
	public void verifyResumeEventForContentInMegamenu() throws Exception {
		System.out.println("Verify Resume Event For Content played from Megamenu");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyResumeEventForContentInMegamenu();
	}

	@Test(priority = 37)
	public void verifyCarouselBannerSwipeEvent() throws Exception {
		System.out.println("Verify Carousel Banner Swipe Event");
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		Zee5PWAWEBMixPanelBusinessLogic.verifyCarouselBannerSwipeEvent("Shows");
	}

	@Test(priority = 41)
	public void verifyCTAsEventForOptionInHamburger() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify CTAs Event when user clicks on any option in hamburger menu");
		Zee5PWAWEBMixPanelBusinessLogic.verifyCTAsEventForOptionInHamburger();
	}

	@Test(priority = 42)
	@Parameters({ "userType" })
	public void verifyLoginPasswordEnteredEvent(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Login Password Entered Event");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginUsernameEnteredEvent(userType, "Password");
	}

	@Test(priority = 43)
	@Parameters({ "userType" })
	public void verifyLoginUsernameEnteredEvent(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Login Username Entered Event");
//		Zee5PWAWEBMixPanelBusinessLogic.verifyLoginUsernameEnteredEvent(userType, "UserName");
	}

	@Test(priority = 44)
	public void verifySearchCancelledEvent() throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Search Cancelled Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifySearchCancelledEvent();
	}

	@Test(priority = 45)
	@Parameters({ "userType" })
	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Profile Update Result Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyProfileUpdateResultEvent(userType);
	}

	@Test(priority = 46)
	public void verifyAdBannerImpressionEvent(String tabName) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Ad Banner Impression Event");
		Zee5PWAWEBMixPanelBusinessLogic.verifyAdBannerImpressionEvent("Shows");
	}

	@Test(priority = 47)
	@Parameters({ "keyword" })
	public void verifyEpisodeListChosenEventFromShowDetailPage(String keyword) throws Exception {
		Zee5PWAWEBMixPanelBusinessLogic.relaunch();
		System.out.println("Verify Episode List Chosen Event in Show Detail page");
		Zee5PWAWEBMixPanelBusinessLogic.verifyEpisodeListChosenEventFromShowDetailPage(keyword);
	}

	

	@AfterClass
	public void tearDown() {
		Zee5PWAWEBMixPanelBusinessLogic.tearDown();
	}

}
