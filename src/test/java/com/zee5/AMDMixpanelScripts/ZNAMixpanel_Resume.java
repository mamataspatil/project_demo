package com.zee5.AMDMixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class ZNAMixpanel_Resume {

	private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

//	@Test(priority = 1)
//	@Parameters({ "userType" })
//	public void AndroidAppMixPanelLogin(String userType) throws Exception {
//		System.out.println("\nLogin");
//		Zee5ApplicasterMixPanelBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
//		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
//		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(userType);
//		
//	}
//	
//	@Test(priority = 2)
//	@Parameters({ "userType","keyword4"})
//	public void ResumeEventForFreeContent(String userType, String keyword4) throws Exception {
//		System.out.println("\nResume event of Free content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventOfcontentFromSearchPage(userType, keyword4);
//	}
//	
//	@Test(priority = 3)
//	@Parameters({ "userType", "keyword3"})
//	public void ResumeEventofTrailerContent(String userType, String keyword3) throws Exception {
//		System.out.println("\nResume event of Trailer content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrailerContent(userType,keyword3);
//		
//	}
//
//	//---------------------------------Carousal-------------------------------------------------//
//	
//	@Test(priority = 4)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Home(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
//	
//	@Test(priority = 5)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Shows(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Shows");
//	}
//	
//	@Test(priority = 6)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Movies(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
//	
//	@Test(priority = 7)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Premium(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
//	
//	@Test(priority = 8)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Club(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
//	
//	@Test(priority = 9)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Eduauraa(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
//	
//	@Test(priority = 10)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_News(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
//	
//	@Test(priority = 11)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Music(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
//	
//	@Test(priority = 12)
//	@Parameters({ "userType"})
//	public void ResumeEvent_CarouselContent_Zee5Oiginals(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForCarouselContent(userType,"Home");
//	}
	
	//---------------------------------Tray Content-------------------------------------------------//

	@Test(priority = 13)
	@Parameters({ "userType", "AdvertisementID"})
	public void ResumeEvent_TrayContent_Home(String userType, String AdvertisementID) throws Exception {
		System.out.println("\nResume event of Carousel content");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Home", AdvertisementID);
	}
	
//	@Test(priority = 14)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_Shows(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Shows");
//	}
//	
//	@Test(priority = 15)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_Movies(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Movies");
//	}
//	
//	@Test(priority = 16)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_Premium(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Premium");
//	}
//	
//	@Test(priority = 17)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_News(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"News");
//	}
//	
//	@Test(priority = 18)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_Club(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Club");
//	}
//	
//	@Test(priority = 19)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_Eduauraa(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Eduauraa");
//	}
//	
//	@Test(priority = 20)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_Music(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Music");
//	}
//	
//	@Test(priority = 21)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_LiveTv(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"Live TV");
//	}
//	
//	@Test(priority = 22)
//	@Parameters({ "userType"})
//	public void ResumeEvent_TrayContent_Zee5Oiginals(String userType) throws Exception {
//		System.out.println("\nResume event of Carousel content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForTrayContent(userType,"ZEE5 Originals");
//	}
	
//	@Test(priority = 23)
//	@Parameters({ "userType"})
//	public void ResumeEventFromMyWatchList(String userType) throws Exception {
//		System.out.println("\nResume event of Content from My WatchList page");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventOfContentFromMyWatchListPage(userType);
//		
//	}
//	
//	@Test(priority = 24)
//	@Parameters({ "userType", "keyword4"})
//	public void ResumeEventFromUpNextRail(String userType, String keyword4) throws Exception {
//		System.out.println("\nResume event of Content from Upnext Rail");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventOfContentFromUpNextRail(userType, keyword4);
//		
//	}
//	
//	@Test(priority = 25)
//	@Parameters({ "userType"})
//	public void ResumeEventForLinearContent(String userType) throws Exception {
//		System.out.println("\nResume event for Linear content");
//		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
//		Zee5ApplicasterMixPanelBusinessLogic.ResumeEventForLinearContent(userType, "Live TV");
//		
//	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}
}
