package com.zee5.CleverTapScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterCleverTapBusinessLogic;
import com.utility.Utilities;

public class SubscriptionCartAbandonment {

	private Zee5ApplicasterCleverTapBusinessLogic Zee5ApplicasterCleverTapBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;
		Zee5ApplicasterCleverTapBusinessLogic = new Zee5ApplicasterCleverTapBusinessLogic("zee");
	}
	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void loginresult(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.NoThanksPopUp("Allow", userType);
		Zee5ApplicasterCleverTapBusinessLogic.ZeeApplicasterLogin(userType);
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void SubscriptionPageViewed(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.SubscriptionPageViewed(userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType" })
	public void SubscriptionCallInitiated(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.SubscriptionSelected(userType);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType" })
	public void SubscriptionSelected(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.SubscriptionCallInitiated(userType);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType" })
	public void SubscriptionCallReturned(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.SubscriptionCallReturned(userType);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType" })
	public void DisplayLanguageChange(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.DisplayLanguageChange();
	}
	
	@Test(priority = 6)
	@Parameters({ "userType" })
	public void ContentLanguageChange(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.ContentLanguageChange();
	}
	
	@Test(priority = 7)
	@Parameters({ "userType" })
	public void SearchCancelled(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.SearchCancelled();
	}
	
	@Test(priority = 8)
	@Parameters({ "userType" })
	public void AddToWatchlist(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.AddToWatchlist(userType);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType" })
	public void Share(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.Share();
	}
	
	@Test(priority = 10)
	@Parameters({ "userType" })
	public void RemoveFromWatchlist(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.RemoveFromWatchlist(userType);
	}
	
	@Test(priority = 11)
	@Parameters({ "userType" })
	public void logout(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.logout(userType);
	}
		
	@Test(priority = 12)
	@Parameters({ "userType" })
	public void CleverTapLoginEventsCheck(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.cleverTapLoginLogoutFunctionality(userType);
		Zee5ApplicasterCleverTapBusinessLogic.RegisterFunctionality(userType);
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void cleverTapValidation(String userType) throws Exception {
		Zee5ApplicasterCleverTapBusinessLogic.validateResult(userType);
	}
	
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		Zee5ApplicasterCleverTapBusinessLogic.tearDown();
	}
}
