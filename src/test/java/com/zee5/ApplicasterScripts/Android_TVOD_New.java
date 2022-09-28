package com.zee5.ApplicasterScripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_TVOD_New {

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
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)//Bhavana
	@Parameters({"userType","RentalContentName3"}) 
	public void SearchValidationForTVOD(String userType,String ContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.guest_SearchEntryPoint_ZeePlexConsumptionPage(userType, ContentTitle);
				
	}
	
	@Test(priority = 2)//Bhavana
	@Parameters({"RentalContentName3"}) 
	public void ValidateConsumptionScreen(String ContentTitle) throws Exception {
		ZEE5ApplicasterBusinessLogic.validationOfConsumptionPageForTVODContent(ContentTitle);
		
	}
	
	@Test(priority = 3)//Bhavana
	@Parameters({"userType","RentalContentName3","NonsubscribedUserName","NonsubscribedPassword"}) 
	public void ValidateComboOffer_PaymentScreen(String userType,String ContentTitle, String email , String password) throws Exception {
		ZEE5ApplicasterBusinessLogic.validationOfComboOfferPage_PaymentPage("Pay less, Watch more!", "Buy Premium","Premium offer",email, password);
		
	}
	
	
	
}
