package com.zee5.AndroidSupermoonMixpanel;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterMixPanelBusinessLogic;
import com.utility.Utilities;

public class SuperMoon_Mixpanel_Events {
	
private Zee5ApplicasterMixPanelBusinessLogic Zee5ApplicasterMixPanelBusinessLogic;
	
	@BeforeTest
	public void init() throws Exception {
		Utilities.relaunch = true;
		Zee5ApplicasterMixPanelBusinessLogic = new Zee5ApplicasterMixPanelBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType", "supermoonContentName" })
	public void PlayerCTAsEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n Player CTAs event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.playerCTAsEvent(usertype, supermoonContentName);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType", "supermoonContentName", "SuperMoonRentalUserName", "SuperMoonRentalPassword" })
	public void RentalPageCTAsEvent(String usertype, String supermoonContentName, String SuperMoonRentalUserName, String SuperMoonRentalPassword) throws Exception {
		System.out.println("\n Rental page CTAs event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.LoginWithEmailID(SuperMoonRentalUserName, SuperMoonRentalPassword);
		Zee5ApplicasterMixPanelBusinessLogic.RentalPageCTAEvent(usertype, supermoonContentName, SuperMoonRentalUserName, SuperMoonRentalPassword);
	}
	
	@Test(priority = 3)
	@Parameters({ "userType", "supermoonContentName" })
	public void RibbonCTAsEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n Ribbon CTAs event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.RibbonCTAsEvent(usertype, supermoonContentName);
	}
	
	@Test(priority = 4)
	@Parameters({ "userType", "supermoonContentName" })
	public void WidgetCTAsEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n Widget CTAs event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.WidgetCTAs(usertype, supermoonContentName);
	}
	
	@Test(priority = 5)
	@Parameters({ "userType", "pTabName" })
	public void ScreenViewEvent(String usertype, String pTabName) throws Exception {
		System.out.println("\n Screen View event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.ScreenViewEvent(usertype, pTabName);
	}
	
	@Test(priority = 6)
	@Parameters({ "userType", "supermoonContentName" })
	public void PopUpLaunchEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n PopUp Launch event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.PopUpLaunchEvent(usertype, supermoonContentName);
	}

	@Test(priority = 7)
	@Parameters({ "userType", "supermoonContentName" })
	public void PopUpCTAsEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n PopUp CTAs event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.PopUpCTAsEvent(usertype, supermoonContentName);
	}
	
	@Test(priority = 8)
	@Parameters({ "userType", "supermoonContentName" })
	public void CarousalBannerClickEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n Carousal Banner Click event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.carousalBannerClickEvent(usertype, supermoonContentName);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType", "supermoonContentName" })
	public void SubscriptionSelectedEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n Subscription selected event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.SubscriptionSeleted(usertype, supermoonContentName);
	}
	
	@Test(priority = 10)
	@Parameters({ "userType", "supermoonContentName" })
	public void PackToggleEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n Pack Toggle event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.PackToggle(usertype, supermoonContentName);
	}
	
	@Test(priority = 11)
	@Parameters({ "userType", "supermoonContentName" })
	public void SubscriptionCallInitiatedEvent(String usertype, String supermoonContentName) throws Exception {
		System.out.println("\n Subscription call Initiated event");
		Zee5ApplicasterMixPanelBusinessLogic.relaunch(true);
		Zee5ApplicasterMixPanelBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		Zee5ApplicasterMixPanelBusinessLogic.ZeeApplicasterLogin(usertype);
		Zee5ApplicasterMixPanelBusinessLogic.subscriptionCallInitiated(usertype, supermoonContentName);
	}
	
	//###############-------END OF TEST-------###############
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		Zee5ApplicasterMixPanelBusinessLogic.tearDown();
	}


}
