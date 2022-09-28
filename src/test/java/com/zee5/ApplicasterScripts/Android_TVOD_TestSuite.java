package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_TVOD_TestSuite {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType", })
	public void ApplicasterLogin(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)//Bhavana
	@Parameters({"SettingsNonsubscribedUserName","SettingsNonsubscribedPassword", "RentalContentName3"})	
	public void ParentalControlValidationForTVOD_WithoutActiveRadhe(String pEmailId,String pPassword,String pContent) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(pEmailId, pPassword);
		ZEE5ApplicasterBusinessLogic.parentalControlValidationOnClickingTrailer(pEmailId, pPassword,pContent);
		
	}
	
	@Test(priority = 2)//Bhavana
	@Parameters({"NonSubsUserWithActiveRentalPlan","NonSubsUserWithActiveRentalPlanPwd","RentalContentName3"})	
	public void ParentalControlValidationForTVOD_WithActiveRadhe(String EmailId, String Password,String pContent) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow","Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(EmailId, Password);
		ZEE5ApplicasterBusinessLogic.playerControlValidationOnclickingWatchNowCTA(EmailId, Password,pContent);
	}
	
	@Test(priority = 3)//Bhavana
	@Parameters({"userType","RentalContentName3"}) //Need to pass TVOD expired credentials
	public void SearchValidationForTVOD(String userType,String ContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.searchForTVODContent(ContentTitle);
	}
	
	@Test(priority = 4)//Bhavana
	@Parameters({"userType","ExpiredRadheRentalEmail","ExpiredRadheRentalPassword","RentalContentName3"}) //Need to pass TVOD expired credentials
	public void SearchValidationForExpiredTVOD(String userType,String email,String pswd,String ContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow","Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(email, pswd);
		ZEE5ApplicasterBusinessLogic.searchForExpiredTVODContent(email, pswd, ContentTitle);
	}
	
	@Test(priority = 5)//Bhavana
	@Parameters({ "userType","RentalContentName3"}) 
	public void HaveACodeValidationForTVOD(String userType,String contentTitle) throws Exception {		
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow","Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.haveACodeValidationTVOD(userType,contentTitle);
	}

	
	@Test(priority = 6)//Only Guest user - Sushma
	@Parameters({ "userType", "RentalContentName1"})
	public void Guest_SearchZeePlexConsumptionPage(String userType, String contentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow","Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.guest_SearchEntryPoint_ZeePlexConsumptionPage(userType, contentTitle);
	}
	
	@Test(priority = 7)//All usertypes - Sushma
	@Parameters({ "userType", "tabName1"})
	public void ThumbhnailEntryPoint_ZeeplexConsumptionPage(String userType, String tabName) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.thumbhnailEntryPoint_ZeeplexConsumptionPage(userType, tabName);
	}
	
	@Test(priority = 8)//Sushma
	@Parameters({ "userType", "NonSubsUserWithActiveRentalPlan", "NonSubsUserWithActiveRentalPlanPwd", "RentalContentName1"})
	public void NonSub_WatchlistEntryPoint_ZeeplexConsumptionPage(String userType, String nonSubEmail, String nonSubPwd, String contentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(nonSubEmail, nonSubPwd);
		ZEE5ApplicasterBusinessLogic.watchlistEntryPoint_ZeeplexConsumptionPage(userType, contentTitle);
	}
	
	@Test(priority = 9)//Sushma
	@Parameters({ "userType", "SubsUserWithRadheRentalPlan","CommomPassword", "RentalContentName1"})
	public void Sub_WatchlistEntryPoint_ZeeplexConsumptionPage(String userType, String subEmail, String pwd, String contentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(subEmail, pwd);
		ZEE5ApplicasterBusinessLogic.watchlistEntryPoint_ZeeplexConsumptionPage(userType, contentTitle);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
}
