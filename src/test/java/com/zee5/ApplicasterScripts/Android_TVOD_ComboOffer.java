package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_TVOD_ComboOffer {

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
	
	@Test(priority = 1)
	@Parameters({ "userType","NonsubscribedUserName","NonsubscribedPassword" })
	public void SubscriptionScreenAndPaymentScreenValidation(String userType,String pEmailId,String pPassword) throws Exception {
		ZEE5ApplicasterBusinessLogic.tvodComboOfferScreenValidation(userType,"Buy Premium Now!");
		ZEE5ApplicasterBusinessLogic.verifyNavigationAsNonSusbscribedUser(pEmailId, pPassword);
	}
	
	@Test(priority = 2)
	@Parameters({"NonSubsUserWithActiveRentalPlan","NonSubsUserWithActiveRentalPlanPwd","RentalContentName3"})	//---Need to pass Non-Susbcribeduser credentials which has only Radhe rental Active plan activated
	public void guestUserLogsinAsNonSubsUserWithRentalActivePlan(String pEmailId,String pPassword,String pContent) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.verifyRentalPlanAsNonSubscribedUser(pEmailId, pPassword,pContent);
	}
	
	@Test(priority = 3)
	@Parameters({"SubsUserWithRadheRentalPlan","CommomPassword","RentalContentName3" })	//---Need to pass Susbcribeduser credentials which has 1-year Premium+Radhe Active plan
	public void guestUserLogsinAsSubsUserWithRentalActivePlan(String pEmailId,String pPassword,String pContent) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.verifyRentalPlanAsSubscribedUser(pEmailId, pPassword,pContent);
	}
	
//	@Test(priority = 4)//Sushma - Logged in subscribed user with premium plans lower in value than the Combo offer price of 499/- without Radhe rental
	@Parameters({ "userType","99PremiumPackEmail", "99PremiumPackPwd", "tabName1", "RentalContentName1"})
	public void LoginAs99PremiumUserWithoutRentalPlanToValidateComboOfferScreenUC1(String userType, String PremiumPackEmail99, String PremiumPackPwd99, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(PremiumPackEmail99, PremiumPackPwd99);
		ZEE5ApplicasterBusinessLogic.premiumUser_99(ptabName, pContentTitle);
	}
	
	@Test(priority = 5)//Sushma - Logged in subscribed user with premium plans lower in value than the Combo offer price of 499/- without Radhe rental
	@Parameters({ "userType","299PremiumPackEmail", "CommomPassword", "tabName1", "RentalContentName1"})
	public void LoginAs299PremiumUserWithoutRentalPlanToValidateComboOfferScreenUC1(String userType,String PremiumPackEmail299, String pPassword, String ptabName, String pContentTitle) throws Exception {		
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(PremiumPackEmail299, pPassword);
		ZEE5ApplicasterBusinessLogic.premiumUser_299(ptabName, pContentTitle);
		
	}
	
	@Test(priority = 6)//Sushma - Logged in subscribed user with any non-premium plans (49) lower or equal in value to the Combo offer price of 499/- without Radhe rental 
	@Parameters({ "userType", "RSVOD49PackEmail", "RSVOD49PackPwd", "tabName1", "RentalContentName1"})
	public void LoginAsNonPremiumRSVOD49WithoutRentalPlanToValidateComboOfferScreenUC2(String userType, String RSVOD49PackEmail, String RSVOD49PackPwd, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(RSVOD49PackEmail, RSVOD49PackPwd);
		ZEE5ApplicasterBusinessLogic.rsvodUser_49(ptabName, pContentTitle);
	}
	
//	@Test(priority = 7)//Sushma - Logged in subscribed user with any non-premium plans (499) lower or equal in value to the Combo offer price of 499/- without Radhe rental 
	@Parameters({ "userType", "RSVOD499PackEmail", "CommomPassword", "tabName1", "RentalContentName1"})
	public void LoginAsNonPremiumRSVOD499WithoutRentalPlanToValidateComboOfferScreenUC2(String userType, String RSVOD499PackEmail, String pPassword, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(RSVOD499PackEmail, pPassword);
		ZEE5ApplicasterBusinessLogic.rsvodUser_499(ptabName, pContentTitle);
	}
	
//	@Test(priority = 8)//Sushma - guest user -->>Login as already subscribeduser with premium plans lower in value than the combo offer price of 99/- without radhe rental
	@Parameters({ "userType","99PremiumPackEmail", "99PremiumPackPwd", "tabName1", "RentalContentName1"}) 
	public void guestUserFromComboPageLoginWithPremium99ToValidateUpgradeBottomsheetUC6(String userType, String PremiumPackEmail99, String PremiumPackPwd99, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.guest_premiumUser_99(ptabName, pContentTitle, PremiumPackEmail99, PremiumPackPwd99);
	}
	
	@Test(priority = 9)//Sushma - guest user -->>Login as already subscribeduser with premium plans lower in value than the combo offer price of 499/- without radhe rental
	@Parameters({ "userType","299PremiumPackEmail", "CommomPassword", "tabName1", "RentalContentName1"}) 
	public void guestUserFromComboPageLoginWithPremium299ToValidateUpgradeBottomsheetUC6(String userType, String PremiumPackEmail299, String pPassword, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.guest_premiumUser_299(ptabName, pContentTitle, PremiumPackEmail299, pPassword);
	}
	
	
	@Test(priority = 10)//Sushma - guest user -->>Login as already subscribeduser with active premium plan(1 year or 6 months plan users) without radhe rental
	@Parameters({ "userType","499PremiumPackEmail", "CommomPassword", "tabName1", "RentalContentName1"}) 
	public void guestUserFromComboPageLoginWithPremium499UserUC6b(String userType, String PremiumPackEmail499, String pPassword, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.guest_premiumUser_499(ptabName, pContentTitle, PremiumPackEmail499, pPassword);
	}
	
	@Test(priority = 11)//Sushma -  guest user -->>Login as already subscribeduser with any non-premium plans without radhe rental
	@Parameters({ "userType","RSVOD49PackEmail", "RSVOD49PackPwd", "tabName1", "RentalContentName1"}) 
	public void  guestUserFromComboPageLoginAsNonPremiumRSVOD49ToValidateUpgradeBottomsheetUC7(String userType, String RSVOD49PackEmail, String RSVOD49PackPwd, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.guest_RSVODUser_49(ptabName, pContentTitle, RSVOD49PackEmail, RSVOD49PackPwd);
	}
	
//	@Test(priority = 12)//Sushma -  guest user -->>Login as already subscribeduser with any non-premium plans without radhe rental
	@Parameters({ "userType", "RSVOD499PackEmail", "CommomPassword", "tabName1", "RentalContentName1"}) 
	public void  guestUserFromComboPageLoginAsNonPremiumRSVOD499ToValidateUpgradeBottomsheetUC7(String userType, String RSVOD499PackEmail, String pPassword, String ptabName, String pContentTitle) throws Exception {		
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.guest_RSVODUser_499(ptabName, pContentTitle, RSVOD499PackEmail, pPassword);
	}
	
	@Test(priority = 13)//Bhavana
	@Parameters({ "userType","NonsubscribedUserName","CommomPassword","tabName1", "RentalContentName1"})
	public void guestUserFromComboPageLoginAsNonSubsribedWithoutRentalPlanUC5(String userType, String pEmailId, String pPassword, String ptabName, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(pEmailId,pPassword);
        ZEE5ApplicasterBusinessLogic.nonSubscribed_withoutRadheRental(ptabName,pContentTitle); 
    }
	
	@Test(priority = 14)
	@Parameters({ "userType","SubsUserWithRadheRentalPlan","CommomPassword","RentalContentName3"}) //---Need to pass Susbcribeduser credentials which has 1-year Premium+Radhe Active plan
	public void ZEEPLEXContentInPlayerCTAValidationUC3(String userType,String pEmailId, String pPassword, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(pEmailId, pPassword);
		ZEE5ApplicasterBusinessLogic.searchZEEPLEXContentAndPlay(pContentTitle);
		ZEE5ApplicasterBusinessLogic.zeePlexContentInPlayerCTAValidation();
	}
		
	@Test(priority = 15)	
	@Parameters({ "userType","OneYearPremiumPack","CommomPassword","RentalContentName4"}) //---Need to pass Susbcribeduser credentials which has 1-year Premium plan without Rental Active plan
	public void ZEEPLEXContentPaymentScreenValidationUC3b(String userType,String pEmailId, String pPassword, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(pEmailId, pPassword);
		ZEE5ApplicasterBusinessLogic.searchZEEPLEXContentAndPlay(pContentTitle);
		ZEE5ApplicasterBusinessLogic.zeePlexContentRentNowCTAValidation();
	}
	
	@Test(priority = 16)
	@Parameters({ "userType","NonSubsUserWithActiveRentalPlan","NonSubsUserWithActiveRentalPlanPwd","RentalContentName3"}) //---Need to pass Non-Susbcribeduser credentials which has only Radhe Active plan
	public void ZEEPLEXContentInPlayerCTAValidationUC4(String userType,String pEmailId, String pPassword, String pContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(pEmailId, pPassword);
		ZEE5ApplicasterBusinessLogic.searchZEEPLEXContentAndPlay(pContentTitle);
		ZEE5ApplicasterBusinessLogic.zeePlexContentInPlayerCTAValidation();
	}
	
	@Test(priority = 17)
	@Parameters({ "userType", "RadhedeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void ConsumptionDeeplink_guest_LoginAsNonSub_499(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.consumptionDeeplink_TVOD(deepLinkUrl, "499", "Login", email, password);
		
	}
	
	@Test(priority = 18)
	@Parameters({ "userType", "RadhedeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void ConsumptionDeeplink_guest_LoginAsNonSub_249(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.consumptionDeeplink_TVOD(deepLinkUrl, "249", "Login", email, password);
	}
	
	@Test(priority = 19)
	@Parameters({ "userType", "RadhedeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void ConsumptionDeeplink_guest_RegisterAsNonSub_499(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.consumptionDeeplink_TVOD(deepLinkUrl, "499", "Register", email, password);
		
	}
	
	@Test(priority = 20)
	@Parameters({ "userType", "RadhedeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void ConsumptionDeeplink_guest_RegisterAsNonSub_249(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.consumptionDeeplink_TVOD(deepLinkUrl, "249", "Register", email, password);
	}
	
	@Test(priority = 21)
	@Parameters({ "userType", "RadhedeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void ConsumptionDeeplink_NonSub_499(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(email, password);
		ZEE5ApplicasterBusinessLogic.consumptionDeeplink_TVOD(deepLinkUrl, "449", "Login", email, password);
	}
	
	@Test(priority = 22)
	@Parameters({ "userType", "RadhedeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void ConsumptionDeeplink_NonSub_249(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(email, password);
		ZEE5ApplicasterBusinessLogic.consumptionDeeplink_TVOD(deepLinkUrl, "249", "Login", email, password);
	}
	
	@Test(priority = 23)
	@Parameters({ "userType", "subscriptionDeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void subscriptionDeeplink_guest_LoginAsNonSub(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.subscriptionDeeplink_TVOD(deepLinkUrl, "Login", email, password);
	}
	
	@Test(priority = 24)
	@Parameters({ "userType", "subscriptionDeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void subscriptionDeeplink_guest_RegisterAsNonSub(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.subscriptionDeeplink_TVOD(deepLinkUrl, "Register", email, password);
	}
	
	@Test(priority = 25)
	@Parameters({ "userType", "subscriptionDeeplinkURl", "NonsubscribedUserName", "NonsubscribedPassword"}) 
	public void subscriptionDeeplink_NonSub(String userType, String deepLinkUrl, String email, String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(email, password);
		ZEE5ApplicasterBusinessLogic.subscriptionDeeplink_TVOD(deepLinkUrl, "Login", email, password);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
}
