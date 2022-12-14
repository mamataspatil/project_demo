package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_LandingScreen_TopNavigation {

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

	@Test(priority = 1) // Sushma
	@Parameters({ "userType", "tabName1" })
	public void Home_LandingScreen(String userType, String tabName1) throws Exception {
		System.out.println("\n---Verify Home landing screen---\n");
		ZEE5ApplicasterBusinessLogic.homeLandingScreen(userType, tabName1);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "tabName5"}) // Bindu
	public void Shows_LandinScreen(String userType, String TabName) throws Exception {
		System.out.println("\n---Verify Shows landing screen---\n");
		ZEE5ApplicasterBusinessLogic.verifyShowsScreen(userType, TabName);
		ZEE5ApplicasterBusinessLogic.verifyConsumptionScreenOfBeforeTVContent(userType);
	}

	@Test(priority = 3) // Sushma
	@Parameters({ "userType", "tabName2" })
	public void Movies_LandingScreen(String userType, String tabName2) throws Exception {
		System.out.println("\n---Verify Movies landing screen---\n");
		ZEE5ApplicasterBusinessLogic.moviesLandingScreen(userType, tabName2);
	}

	@Test(priority = 4)
	@Parameters({ "userType" }) // Vinay
	public void Premium_LandingScreen(String userType) throws Exception {
		System.out.println("\n---Verify Premium landing screen---\n");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomecreenFromIntroScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.premiumTabScreen(userType);
	}

	@Test(priority = 5)
	@Parameters({ "userType" }) // Bindu
	public void News_LandingScreen(String userType) throws Exception {
		System.out.println("\n---Verify News landing screen---\n");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomecreenFromIntroScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.verifyNewsLandingScreen(userType);
		ZEE5ApplicasterBusinessLogic.verifyTraysInNewsScreen(userType);
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyListingCollectionScreen(userType);
	}

	@Test(priority = 6)
	@Parameters({ "userType" }) // Bhavana
	public void Learning_LandingScreen(String userType) throws Exception {
		System.out.println("\n---Verify Learning landing screen---\n");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomecreenFromIntroScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.eduauraaTabScreen(userType);
	}
 
	@Test(priority = 7)
	@Parameters({ "userType" }) // Vinay
	public void Music_LandingScreen(String userType) throws Exception {
		System.out.println("\n---Verify Music landing screen---\n");
		ZEE5ApplicasterBusinessLogic.musicLandingScreen(userType);
	}

	@Test(priority = 8)
	@Parameters({ "userType", "tabName4" }) // Hitesh
	public void LiveTV_landingScreen(String userType, String tabName) throws Exception {
		System.out.println("\n---Verify LiveTv landing screen---\n");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToHomecreenFromIntroScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.LiveTV(userType, tabName);
	}

	@Test(priority = 9)
	@Parameters({ "userType" }) // Hitesh
	public void ChannelGuide_LandingScreen(String userType) throws Exception {
		System.out.println("\n---Verify Channel guide screen---\n");
		ZEE5ApplicasterBusinessLogic.channelGuideScreenValidation(userType);
	}

	@Test(priority = 10)
	@Parameters({ "userType", "tabName3" }) // Manasa
	public void WebSeries_LandingScreen(String userType, String tabName) throws Exception {
		System.out.println("\nVerify Web Series Landing Screen");
		ZEE5ApplicasterBusinessLogic.zee5OriginalsLandingScreen(userType, tabName);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Quiting the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}
