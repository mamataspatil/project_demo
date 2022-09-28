package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_AppUpgradeScenarios {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Android App");
		 Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void InstallMarketBuild(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.installmarketBuild();		
	}
	
	@Test(priority = 1)
	@Parameters({ "userType" })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
//		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp1("Allow", userType);
//		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen1();
		ZEE5ApplicasterBusinessLogic.loginForUpgradeModule(userType);
	} 
	
	@Test(priority = 2)
	@Parameters({ "userType" })
	public void CaptureUserSettingDetailsForMarketBuild(String userType) throws Exception {		
		ZEE5ApplicasterBusinessLogic.GetSettingsDetails(userType);		
	} 
	
	@Test(priority = 4)
	@Parameters({ "userType" })
	public void RecentSearchedForMarketBuild(String userType) throws Exception {		
		ZEE5ApplicasterBusinessLogic.recentSearchedForMarketBuild(userType);
			} 
	
	
	@Test(priority = 5)
	@Parameters({ "userType" })
	public void GetContinueWatchingTrayDetailsForMarketBuild(String userType) throws Exception {		
		ZEE5ApplicasterBusinessLogic.getContinueWatchingTrayDetails(userType);
			} 
	
	@Test(priority = 6)
	@Parameters({ "userType" })
	public void GetDownloadsDetailsForMarketBuild(String userType) throws Exception {		
		ZEE5ApplicasterBusinessLogic.downloadsDetails(userType);
	} 
	
	@Test(priority = 7)
	@Parameters({ "userType" })
	public void GetWatchListDetailsForMarketBuild(String userType) throws Exception {		
		ZEE5ApplicasterBusinessLogic.watchListDetails(userType);
	} 
	
	@Test(priority = 8)
	@Parameters({ "userType" })
	public void LaunchPlaystoreApp(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.clearPlayStoreAppData();
			}
	
	@Test(priority = 9)
	@Parameters({ "userType" })
	public void InstallUpgradeBuild(String userType) throws Exception {
				ZEE5ApplicasterBusinessLogic.InstallZee5();			
	}
	
	@Test(priority = 10)
	@Parameters({ "userType" })
	public void CaptureUserSettingsDetailsForUpgradeBuild(String userType) throws Exception {
				ZEE5ApplicasterBusinessLogic.captureSettingsDetailsForupgradeBuild();			
	}
	
	@Test(priority = 11)
	@Parameters({ "userType" })
	public void RecentSearchedForUpgradeBuild(String userType) throws Exception {
				ZEE5ApplicasterBusinessLogic.recentSearchedForUpgradeBuild(userType);			
	}
	
	@Test(priority = 12)
	@Parameters({ "userType" })
	public void CaptureContinueWatchingTrayForUpgradeBuild(String userType) throws Exception {
				ZEE5ApplicasterBusinessLogic.captureContinueWatchingTrayForUpgradeBuild(userType);			
	}
	
	@Test(priority = 13)
	@Parameters({ "userType" })
	public void CaptureDownloadsForUpgradeBuild(String userType) throws Exception {
				ZEE5ApplicasterBusinessLogic.captureDownloadsForUpgradeBuild(userType);			
	}
	
	@Test(priority = 14)
	@Parameters({ "userType" })
	public void captureWatchlistDetailsForUpgradeBuild(String userType) throws Exception {
				ZEE5ApplicasterBusinessLogic.captureWatchlistDetailsForUpgradeBuild(userType);			
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
	
}
