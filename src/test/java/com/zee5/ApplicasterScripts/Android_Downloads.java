package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Downloads {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 0)	 
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)
	@Parameters({ "userType"})
	public void ContentPlayBackDownloads(String userType) throws Exception {
			ZEE5ApplicasterBusinessLogic.downloadsContentPlayBackValidation(userType,"Better",true);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void PlayDownloadedContentOffline(String userType) throws Exception {
		     ZEE5ApplicasterBusinessLogic.relaunch(false);
			ZEE5ApplicasterBusinessLogic.playDownloadedContentOffline(userType);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType"})
	public void DonwloadsLandingScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.downloadScreenUIUXValidation(userType);
		ZEE5ApplicasterBusinessLogic.BrowseToDownloadFunctionality(userType);
		
	}
	
	@Test(priority = 4)
	@Parameters({ "userType"})
	public void Downloads_EmptystateScreenValidation(String userType) throws Exception {
			ZEE5ApplicasterBusinessLogic.emptystateScreenValidation(userType);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType"})
	public void Downloads_verifyDownloadsWithSingleTire(String userType) throws Exception {
			ZEE5ApplicasterBusinessLogic.verifyDownloadsWithSingleTire(userType);
	}

	@Test(priority = 6)
	@Parameters({ "userType","MovieName","VideoQuality"})
	public void Downloads_verifyMovieContentInDownloadsScreen(String userType,String pMovie, String pVideoQuality) throws Exception {
			ZEE5ApplicasterBusinessLogic.verifyMovieContentInDownloadsScreen(userType, pMovie, pVideoQuality);
	}	

	//@Test(priority = 7)
	@Parameters({ "userType"})
	public void Downloads_validationofDownloadingContent(String userType) throws Exception {
		    ZEE5ApplicasterBusinessLogic.relaunch(true);
		    ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
			ZEE5ApplicasterBusinessLogic.validationofDownloadingContent(userType);
	}
	
	//@Test(priority = 8)
	@Parameters({ "userType"})
	public void Downloads_DownloadingOffline(String userType) throws Exception {
			ZEE5ApplicasterBusinessLogic.downloadingOffline(userType);
	}
	
	//@Test(priority = 9)
	@Parameters({ "userType"})
	public void Downloads_validationofDeletedContentAndMultipleDownloads(String userType) throws Exception {
			ZEE5ApplicasterBusinessLogic.deletedContentAndMultipleDownloadContent(userType);
	}
	
	//@Test(priority = 10)
	@Parameters({ "userType"})
	public void Downloads_pauseAllAndCancelDownload(String userType) throws Exception {
			ZEE5ApplicasterBusinessLogic.pauseAllAndCancelDownload(userType);
	}
	
	
	//@Test(priority = 11)
	@Parameters({ "userType"})
	public void Downloads_validationofDownloadsSectionAndLatestEpisode(String userType) throws Exception {
		     ZEE5ApplicasterBusinessLogic.relaunch(true);
		     ZEE5ApplicasterBusinessLogic.navigateToHomeLandingScreen();
			 ZEE5ApplicasterBusinessLogic.downloadsSectionAndLatestEpisode(userType);
	}
	
	@Test(priority = 12)
	@Parameters({ "userType"})
	public void Downloads_ValidateSubscriptionExpireBanner(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.validateSubscriptionExpireBanner(userType);
		}
				
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App\n");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}