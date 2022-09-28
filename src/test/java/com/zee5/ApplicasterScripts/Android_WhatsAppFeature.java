package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_WhatsAppFeature {
	
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
		//ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfEmailIDField_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25107";
		}
		ZEE5ApplicasterBusinessLogic.validationOfEmailIDField(userType);
	}
	
	@Test(priority = 2)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappOptIn_EmailIdOnEmailIDField_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
		    ZEE5ApplicasterBusinessLogic.jiraID = "TC-25108";
		}
		ZEE5ApplicasterBusinessLogic.whatsAppTickBox_EmailIDOnEmailIDField(userType);
	}
	
	@Test(priority = 3)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappOptIn_SpecialCharacterOnEmailIDField_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25109";
		}
		ZEE5ApplicasterBusinessLogic.whatsAppTickbox_SpecialCharacterOnEmailID(userType);
	}
	
	@Test(priority = 4)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappOptIn_MobileNoOnEmailIDField_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25110";
		}
		ZEE5ApplicasterBusinessLogic.whatsAppTickBox_MobileNoInEmailIDField(userType);
	}
	
	@Test(priority = 5)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappTextOnWhatsappOptIn_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25111";
		}	
		ZEE5ApplicasterBusinessLogic.whatsappTextOnWhatsappOptIn(userType);
	}
	
	@Test(priority = 6)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappOptInChechBoxByDefault_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25112";
		}	
		ZEE5ApplicasterBusinessLogic.whatsAppOptinCheckBoxByDefault(userType);
	}
	
	@Test(priority = 7)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappOptInChechBoxFunctionality_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25113";
		}
		ZEE5ApplicasterBusinessLogic.whatsAppOptInCheckBoxFunctionality(userType);
	}
	
	@Test(priority = 8)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfRegisterCTAOnSelectingWhatsappOptIn_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25114";
		}
		ZEE5ApplicasterBusinessLogic.registerCTAFunctionalityOnSelectingWhatsappOptIn(userType);
	}
	
	@Test(priority = 9)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfOTPScreen_SelectingWhatsappOptIn_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25115";
		}
		ZEE5ApplicasterBusinessLogic.otpScreen_OnselectingWhatsappOptIn(userType);
	}
	
	@Test(priority = 10)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfOTPScreen_DeselectingWhatsappOptIn_UnRegisteredUser(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25117";
		}
		ZEE5ApplicasterBusinessLogic.otpScreen_OnDeselectingWhatsappOptIn(userType);
	}
	
	@Test(priority = 11)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappOptInTickBox_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25118";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25121";
		}
		ZEE5ApplicasterBusinessLogic.whatsAppOptInTickBoxInEditProfileScreen(userType);
	}
	
	@Test(priority = 12)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfWhatsappTextOnWhatsappOptIn_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25119";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25122";
		}
		ZEE5ApplicasterBusinessLogic.whatsAppTextInwhatsAppOptIn_EditProfileScreen(userType);
	}
	
	@Test(priority = 13)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfFields_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25120";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25123";
		}
		ZEE5ApplicasterBusinessLogic.fieldValidation_EditProfileSreen(userType);
	}
	
	@Test(priority = 14)//Sushma
	@Parameters({ "userType"})
	public void whatsappOptInFunctionality_MobileNo_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25124";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25125";
		}
		ZEE5ApplicasterBusinessLogic.whatsappOptInFunctionalityPostEnteringMobileNoInEditProfileScreen(userType);
	}
	
	@Test(priority = 15)//Sushma
	@Parameters({ "userType"})
	public void whatsappOptInEnable_MobileNo_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25126";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25127";
		}
		ZEE5ApplicasterBusinessLogic.whatsappOptInEnable_EditProfileScreen(userType);
	}
	
	@Test(priority = 16)//Sushma
	@Parameters({ "userType"})
	public void whatsappOptInDisable_EmaiId_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25128";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25129";
		}
		ZEE5ApplicasterBusinessLogic.whatsappOptInDisable_EditProfileScreen(userType);
	}
	
	@Test(priority = 17)//Sushma
	@Parameters({ "userType"})
	public void whatsappOptInFunctionality_EmailID_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25130";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25131";
		}
		ZEE5ApplicasterBusinessLogic.whatsappOptInFunctionalityPostEnteringOnlyEmailIdInEditProfileScreen(userType);
	}
	
	@Test(priority = 18)//Sushma
	@Parameters({ "userType"})
	public void whatsappOptInEnable_EmaiIdAndMobileNumber_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25132";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25133";
		}
		ZEE5ApplicasterBusinessLogic.whatsappOptInFunctionalityPostEnteringBothEmailIdAndMobileNumberInEditProfileScreen(userType);
	}
	
	@Test(priority = 19)//Sushma
	@Parameters({ "userType"})
	public void SaveChangesCTA_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25228";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25229";
		}
		ZEE5ApplicasterBusinessLogic.saveChangesCTA_Highlight_InEditProfileScreen(userType);
	}

	@Test(priority = 20)//Sushma
	@Parameters({ "userType"})
	public void saveChangesFunctionality_havingMobileNumber_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25232";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25233";
		}
		ZEE5ApplicasterBusinessLogic.saveChangesFunctionality_PreviouslyRegisteredUser_havingMobileNumber(userType);
	}
	
	@Test(priority = 21)//Sushma
	@Parameters({ "userType"})
	public void saveChangesFunctionality_havingOnlyEmailID_RegisteredUser(String userType) throws Exception {
		if (userType.equals("NonSubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25234";
		}else if(userType.equals("SubscribedUser")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-25235";
		}
		ZEE5ApplicasterBusinessLogic.saveChangesFunctionality_PreviouslyRegisteredUser_havingOnlyEmailID(userType);
	}
	
//	@Test(priority = 3)//Sushma
//	@Parameters({ "userType"})
//	public void Validation_FloatingBannerAndSmallIcon_HomeScreen(String userType) throws Exception {
//		ZEE5ApplicasterBusinessLogic.relaunch(false);
//		ZEE5ApplicasterBusinessLogic.floatingBannerAndSmallIconOnHomeScreen(userType);
//		
//	}
//	
//	@Test(priority = 4)//Sushma
//	@Parameters({ "userType"})
//	public void Validation_CollapsingFloatingBanner_BrowsingFromTopToBottom_HomeScreen(String userType) throws Exception {
//		ZEE5ApplicasterBusinessLogic.relaunch(true);
//		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
//		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
//		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLogin(userType);
//		ZEE5ApplicasterBusinessLogic.WhatsAppBannerCollapseWhileBrowsingPageFromTopToBottom(userType);
//	}
//	
//	@Test(priority = 5)//Sushma
//	@Parameters({ "userType", "WhatsappOptInDeeplinkUrl"})
//	public void Deeplinking(String userType, String url) throws Exception {
//		ZEE5ApplicasterBusinessLogic.relaunch(true);
//		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
//		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
//		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLogin(userType);
//		ZEE5ApplicasterBusinessLogic.navigationToHomePageOrLoginOrRegisterPageThrough_Deeplinking(userType, url);
//	}
//	

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}
