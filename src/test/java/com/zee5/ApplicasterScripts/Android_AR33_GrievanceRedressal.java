package com.zee5.ApplicasterScripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_AR33_GrievanceRedressal {

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
	public void Grievance_Redressal(String userType) throws Exception {		
		ZEE5ApplicasterBusinessLogic.grievance_Redressal(userType);		
	}
	
	@Test(priority = 2)
	@Parameters({"DeeplinkToGrievanceRedressal","userType"})	//Bhavana
	public void DeeplinkGrievanceRedressal(String pDeeplinkUrl,String pUserType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", pUserType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.deeplinkGrievanceRedressal(pDeeplinkUrl,pUserType);
	}
	
}
