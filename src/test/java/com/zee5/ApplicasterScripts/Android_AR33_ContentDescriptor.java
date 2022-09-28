package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_AR33_ContentDescriptor {
	
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
	
	@Test(priority = 1)  //Bhavana
	@Parameters({ "userType" })		
	public void ContentDescriptorForDifferentContent(String userType) throws Exception {	
		ZEE5ApplicasterBusinessLogic.contentDescriptionValidation(userType,"14 Phere","Movie");
		ZEE5ApplicasterBusinessLogic.contentDescriptionValidation(userType,"Bebaakee","Video");		
	}
	
	@Test(priority = 2)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyContentDescriptorForNewsandLiveContent(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.contentDescriptorForNewsAndLiveContent(userType);		
	}
	
	@Test(priority = 3)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyContentDescriptorForAds(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyContentDescriptorForAds(userType,"Shivajinagara");		
	}
	
	@Test(priority = 4)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyContentDescriptorAfterAdFinishes(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyContentDescriptorAfterAdFinishes(userType,"Rekke");		
	}
	
	@Test(priority = 5)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyContentDescriptor_DownloadedContent_WithandWithout_DataEnabled(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.contentDescriptorForDownloadedContent(userType);
		ZEE5ApplicasterBusinessLogic.contentDescriptorForDownloadedContentInOffline(userType);
	}
	
	@Test(priority = 6)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyAgeRATED_If_CD_is_Empty(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyAgeRatedIfCDisEmpty(userType);
	}	
	
	@Test(priority = 7)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyAgeRating_OnPlayerAndBelowPlayer(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyAgeRating_OnPlayer_And_BelowPlayer(userType,"14 Phere");
	}
	
	@Test(priority = 8)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyContentDescriptor_AfterEntering_ParentalPIN(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyContentDescriptor_AfterEntering_ParentalPIN(userType,"Dial 100");
			}
	
	@Test(priority = 9)  //Bhavana
	@Parameters({ "userType" })		
	public void VerifyContentDescriptor_After_Interruption(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verify_CD_Dismissal(userType,"Dial 100");
	}
	
	@Test(priority = 10)  //Kartheek
	@Parameters({ "userType" })		
	public void VerifyContentDescriptorWithAgeRating(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescription_Validation(userType,"Rekke","Movie");		
	}
	
	
	@Test(priority = 11)  //Kartheek
	@Parameters({ "userType" })		
	public void VerifyContentDescriptorInAutoPlay(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyCD_in_AutoPlay(userType,"Socha Na Tha");		
	}
	
	@Test(priority = 12)  //Kartheek
	@Parameters({ "userType" })		
	public void VerifyContentPlayability(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verifyContentPlayability(userType,"Dial 100");		
	}
	
	@Test(priority = 13)  //Kartheek
	@Parameters({ "userType" })		
	public void Verify_PlayerControls(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.verify_PlayerControls(userType,"Rekke");		
	}
	
	@Test(priority = 14)  //Kartheek
	@Parameters({ "userType" })		
	public void VerifyContentDescriptorWhenVideoStarts(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorShouldBeDisplayedOnThePlayerWhenVideoStartingFromTheBeginning(userType);		
	}
	
	@Test(priority = 15)  //Kartheek
	@Parameters({ "userType" })		
	public void VerifyContentDescriptor_For_ContinueWatching(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.resumedFromContinueWatchingTray(userType);		
	}
	
	@Test(priority = 16)  //Kartheek
	@Parameters({ "userType" })		
	public void Verify_CD_onClicking_NextIcon(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.verify_CD_onClicking_NextIcon(userType,"Bebaakee");		
	}
	

	@Test(priority = 17) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorVPlayerElements(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.contentDescriptorDeeplinkValidation(userType);//TC_13
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorVPlayerElements(userType);//TC_14

	}
		
	@Test(priority = 18) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorPlayerinterruption(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorPlayerinterruption(userType);//TC_17

	}
	
	
	@Test(priority = 19) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorPostPlayerkill(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.ContentDescriptorPostPlayerkill(userType);//TC_19

	}
	
	@Test(priority = 20) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorPotraitOrientatonValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorPotraitOrientatonValidation(userType);//TC_20

	}
	
	
	@Test(priority = 21) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorLandscapeOrientatonValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorLandscapeOrientatonValidation(userType);//TC_21 

	}
	
	@Test(priority = 22) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorEpisodeValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorEpisodeValidation(userType);//TC_22

	}
	
	
	//@Test(priority = 23) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorDisplayDurationValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorDisplayDurationValidation(userType);//TC_18

	}
	
	
	//@Test(priority = 24) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptorDisplayValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptorDisplayValidation(userType);//TC_16

	}
	
	
	@Test(priority = 25) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptor_TVODContentFromContinueWatching(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(false);
		ZEE5ApplicasterBusinessLogic.contentDescriptor_TVODContentFromContinueWatching(userType);//TC_34
		}
	
	
	//@Test(priority = 26) //Veena  //No rented content is available
	@Parameters({ "userType" })		
	public void ContentDescriptor_TVODContentFromMyRentals(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.contentDescriptor_TVODContentFromMyRentals(userType);//TC_35
		}
	
	@Test(priority = 27) //Veena
	@Parameters({ "userType" })		
	public void ContentDescriptor_TVODContentForInterruption(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.contentDescriptor_TVODContentForInterruption(userType);//TC_37
		}
	
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
}
