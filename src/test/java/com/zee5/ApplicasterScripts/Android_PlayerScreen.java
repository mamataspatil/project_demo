package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_PlayerScreen {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Android App");
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
	@Parameters({ "userType", "searchKeyword1", "searchKeyword4", "searchKeyword5", "searchKeyword3", "searchKeyword6"})
	public void Player_Potrait(String userType, String searchKeyword1, String searchKeyword4, String searchKeyword5, String searchKeyword3, String searchKeyword6) throws Exception {
		ZEE5ApplicasterBusinessLogic.playerPotrait(searchKeyword1, userType);
		ZEE5ApplicasterBusinessLogic.premiumContentwithTrailer(userType, searchKeyword4);
		ZEE5ApplicasterBusinessLogic.premiumContentWithoutTrailer(userType, searchKeyword5);
		ZEE5ApplicasterBusinessLogic.skipIntroValidationInPotraitMode(searchKeyword3, userType);
		ZEE5ApplicasterBusinessLogic.validationOfReplayIconOnPlayer(searchKeyword6);
		//Upnext & Watch Credit is not appearing on the player due to Defect #AMA2-4385. Hence commenting this block & maintenance pending.
		//ZEE5ApplicasterBusinessLogic.validationOfWatchCreditsAndUpNextCard(searchKeyword6, userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType", "searchKeyword3","searchKeyword8" }) // Manasa,Kushal
	public void verifyPlayer_NextAndSkipIntroInLandscapeMode(String userType,String searchKeyword3,String searchKeyword8) throws Exception {
		System.out.println("\nVerify Player Next&PreviousIcon and SkipIntro Functionality in Landscape Mode");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.nextAndPreviousIconValidation(searchKeyword8);
		ZEE5ApplicasterBusinessLogic.skipIntroValidationInLandscapeMode(searchKeyword3,userType);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "searchKeyword4", "searchKeyword5" }) // Manasa,Kushal
	public void verifyPlayer_PlaybackRateAndPremiumContentInLandscapeMode(String userType,String searchKeyword4,String searchKeyword5) throws Exception {
		System.out.println("\nVerify Player PlaybackRate&PremiumContent Functionality in Landscape Mode");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.subtitleAndPlaybackRateValidation(searchKeyword4,userType);
		ZEE5ApplicasterBusinessLogic.premiumContentWithoutTrailerInLandscapeMode(userType,searchKeyword5);
	}

	
	@Test(priority = 4)
	@Parameters({ "userType", "searchKeyword1" })
	public void ParentalPinPopUpValidationInLandscapeMode(String userType,String searchKeyword1) throws Exception {
		System.out.println("\nParental Pin PopUp Validation in Landscape Mode");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLoginForSettings(userType);
		ZEE5ApplicasterBusinessLogic.parentalPinValidationInLandscapeMode(userType, searchKeyword1);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "searchKeyword11", "searchKeyword6" })
	public void PlayerScreenValidationInLandscapeMode(String userType,String searchKeyword11, String searchKeyword6) throws Exception {
		System.out.println("\nPlayer screen Validation in Landscape Mode");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.playerValidationInFullScreenMode(userType, searchKeyword11, searchKeyword6);
		
	}
	
//	@Test(priority = 6)		//-------- Upnext & Watch Credit is not appearing on the player due to Defect #AMA2-4385. Hence commenting this block & maintenance pending.
	@Parameters({ "userType", "searchKeyword1","searchKeyword8", "searchKeyword9"})
	public void UpnextAndWatchCreditValidationInLandscapeMode(String userType, String searchKeyword1,String searchKeyword8,String searchKeyword9) throws Exception {
		System.out.println("\nParental Pin PopUp Validation in Landscape Mode");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLoginForSettings(userType);
		ZEE5ApplicasterBusinessLogic.upnextRailValidationInLandscapeMode(searchKeyword8);
		ZEE5ApplicasterBusinessLogic.watchCreditsValidationInLandscapeMode(searchKeyword9,userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
