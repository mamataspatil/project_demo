package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Consumption_Screen {

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
	@Parameters({ "userType", "SVODShow" })
	public void SVODConsumptionScreenForShowTab(String userType, String SVODShow) throws Exception {
		ZEE5ApplicasterBusinessLogic.sVODConsumptionScreen(userType, "Shows", SVODShow);
	}

	@Test(priority = 2)
	@Parameters({ "userType", "SVODEpisode" })
	public void SVODConsumptionScreenForEpisodeTab(String userType, String SVODEpisode) throws Exception {
		ZEE5ApplicasterBusinessLogic.sVODConsumptionScreen(userType, "Episode", SVODEpisode);
	}

	@Test(priority = 3)
	@Parameters({ "userType", "SVODMovie" })
	public void SVODConsumptionScreenForMoviesTab(String userType, String SVODMovie) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.sVODConsumptionScreen(userType, "Movies", SVODMovie);
	}

	@Test(priority = 4)
	@Parameters({ "userType", "SVODMusic" })
	public void SVODConsumptionScreenForMusicTab(String userType, String SVODMusic) throws Exception {
		ZEE5ApplicasterBusinessLogic.sVODConsumptionScreen(userType, "Music", SVODMusic);
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void NSVODConsumptionScreen(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.verifyNonSVODConsumptionScreen(userType);
//		ZEE5ApplicasterBusinessLogic.verifySimilarChannels();
	}
	
	@Test(priority = 6)
	@Parameters({ "userType","NonSubsWithoutPhNum","NonSubsWithoutPhNumPwd" })
	public void MandatoryRegistrationOrCompleteProfilePopUp(String userType,String pUserName, String pPassword) throws Exception {
			ZEE5ApplicasterBusinessLogic.relaunch(true);
			ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
			ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
			ZEE5ApplicasterBusinessLogic.zeeAppLoginWithCredentials(userType, pUserName, pPassword);
			ZEE5ApplicasterBusinessLogic.mandatoryPopUpScenarios(userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}
