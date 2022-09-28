package com.zee5.ApplicasterXrayScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_LoginOrRegister {
	
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
		ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfLoginPage_LoginOrRegisterButton_MoreMenu(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-473";
			ZEE5ApplicasterBusinessLogic.verifyOfLoginScreenThroughLoginOrRegisterButton();
		}
	}
	
	@Test(priority = 2)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfLoginPage_MySubscriptionButton_MoreMenu(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-474";
			ZEE5ApplicasterBusinessLogic.verifyOfLoginScreenThroughMySubscriptionButton();
		}
	}
	
	@Test(priority = 3)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfLoginPage_MyTransactionButton_MoreMenu(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-475";
			ZEE5ApplicasterBusinessLogic.verifyOfLoginScreenThroughMyTransactionButton();
		}
	}
	
	@Test(priority = 4)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfLoginPage_WatchlistButton_MoreMenu(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-476";
			ZEE5ApplicasterBusinessLogic.verifyOfLoginScreenThroughWatchlistButton();
		}
	}
	
	@Test(priority = 5)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfLoginPage_WatchlistButton_ConsumptionPage(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-478";
			ZEE5ApplicasterBusinessLogic.verifyOfLoginScreenThroughWatchlistButtonFromConsumptionPage();
		}
	}
	
	@Test(priority = 6)//Sushma
	@Parameters({ "userType"})
	public void ValidationOfLoginPage_DownloadButton_ConsumptionPage(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-479";
			ZEE5ApplicasterBusinessLogic.verifyOfLoginScreenThroughDownloadButtonFromConsumptionPage();
		}
	}
	
	@Test(priority = 7)//Sushma
	@Parameters({ "userType"})
	public void verifyOfLoginScreenThroughDeepLinks(String userType) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-480";
			ZEE5ApplicasterBusinessLogic.verifyOfLoginScreenThroughDeepLinks("signin");
		}
	}
	
	@Test(priority = 8)//Sushma
	@Parameters({ "userType", "RegisteredEmail"})
	public void verifyEmailID(String userType, String RegisteredEmail) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-481";
			ZEE5ApplicasterBusinessLogic.verifyEmailIDFieldInLoginOrRegisterScreen(RegisteredEmail);
		}
	}
	
	@Test(priority = 9)//Sushma
	@Parameters({ "userType", "RegisteredEmail"})
	public void verifyPassword(String userType, String RegisteredEmail) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-482";
			ZEE5ApplicasterBusinessLogic.verifyPasswordField(RegisteredEmail);
		}
	}
	
	@Test(priority = 10)//Sushma
	@Parameters({ "userType", "RegisteredEmail"})
	public void verifyEmailID_StandardFormat(String userType, String RegisteredEmail) throws Exception {
		if (userType.equals("Guest")) {
			ZEE5ApplicasterBusinessLogic.jiraID = "TC-483";
			ZEE5ApplicasterBusinessLogic.verifyEmailIDInStandardFormat(RegisteredEmail);
		}
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}

