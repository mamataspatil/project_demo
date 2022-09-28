package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_MoreScreen {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
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

	@Test(priority = 1) // Vinay
	@Parameters({ "userType" })
	public void AccountDetails(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accountDetails(userType);
	}

	@Test(priority = 2) // Vinay
	@Parameters({ "userType" })
	public void BuySubscription(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToLandingScreen();
		ZEE5ApplicasterBusinessLogic.buySubscription(userType);
	}

	@Test(priority = 3) // Vinay
	@Parameters({ "userType" })
	public void MySubscription(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.mySubscription(userType);
	}

	@Test(priority = 4) // Vinay
	@Parameters({ "userType" })
	public void MyTransactions(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.myTransactions(userType);
	}

	@Test(priority = 5) //Hitesh
	@Parameters({ "userType" })
	public void MyWatchlist(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.myWatchList(userType);
	}

	//@Test(priority = 6) // Hitesh // My Reminders feature has been removed [Mallikarjun]
	@Parameters({ "userType" })
	public void MyReminders(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.myReminders(userType);
	}

	@Test(priority = 7) // Hitesh
	@Parameters({ "userType" })
	public void Haveaprepaidcode(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.haveaprepaidcode(userType);
	}

	@Test(priority = 8) // Hitesh
	@Parameters({ "userType" })
	public void Settings(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.settings(userType);
	}

	@Test(priority = 9)
	@Parameters({ "userType" }) // Yashaswini
	public void InviteAFriend(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.invite_a_Friend(userType);
	}

	@Test(priority = 10)
	@Parameters({ "userType" }) //Bhavana
	public void AboutUsValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.aboutUsScreenValidation(userType);
	}

	@Test(priority = 11)
	@Parameters({ "userType" }) //Bhavana
	public void HelpCenterValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToLandingScreen();
		ZEE5ApplicasterBusinessLogic.helpCenterScreen(userType);
	}

	@Test(priority = 12)
	@Parameters({ "userType" }) //Bhavana
	public void LogoutValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.logoutValidation(userType);
		ZEE5ApplicasterBusinessLogic.loginAfterLogout(userType);
	}

	@Test(priority = 13)
	@Parameters({ "userType" }) //Bhavana
	public void PrivacyPolicyValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.privacyPolicyScreen(userType);
	}

	@Test(priority = 14)
	@Parameters({ "userType" })  //Bhavana
	public void StaticPagesInDisplayLanguage(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.staticPagesInDisplayLanguage();
	}

	@Test(priority = 15)
	@Parameters({ "userType" }) //Bhavana
	public void TermsOfUseValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.termsOfUseScreen(userType);
	}
	
	@Test(priority = 16) //Mallikarjun
	@Parameters({ "userType" })
	public void ActivateDevice(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.navigateToLandingScreen();
		ZEE5ApplicasterBusinessLogic.activateDevice(userType);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App\n");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}