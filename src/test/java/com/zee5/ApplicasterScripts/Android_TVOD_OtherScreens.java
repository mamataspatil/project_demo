package com.zee5.ApplicasterScripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_TVOD_OtherScreens {
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
	@Parameters({ "NonSubsUserWithActiveRentalPlan","NonSubsUserWithActiveRentalPlanPwd"}) 
	public void MyTransactions(String email, String pswd) throws Exception {		
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow","Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(email, pswd);
		ZEE5ApplicasterBusinessLogic.verifyMyTransactionForPLEXPlan();
	}
	
	@Test(priority = 2)
	@Parameters({ "NonSubsUserWithActiveRentalPlan","NonSubsUserWithActiveRentalPlanPwd"}) 
	public void MyRentalsForContentYetToWatch(String email, String pswd) throws Exception {		
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow","Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(email, pswd);
		ZEE5ApplicasterBusinessLogic.myRentalsScreenVerification();
		ZEE5ApplicasterBusinessLogic.pLEXContentYetToWatch();
		
	}
	
		@Test(priority = 3)
		@Parameters({"ExpiredRadheRentalEmail","ExpiredRadheRentalPassword"}) 
		public void MyRentalsForExpiredContent(String Email, String Pswd) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow","Guest");
		ZEE5ApplicasterBusinessLogic.navigateToHomeScreen();
		ZEE5ApplicasterBusinessLogic.loginWithEmailID(Email, Pswd);
		ZEE5ApplicasterBusinessLogic.plexExpiredContent();
	}
	
}
