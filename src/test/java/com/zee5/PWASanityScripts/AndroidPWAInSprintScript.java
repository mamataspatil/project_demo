package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityAndroidBusinessLogic;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class AndroidPWAInSprintScript {

	private Zee5PWASanityAndroidBusinessLogic Zee5PWASanityBusinessLogic;

	@BeforeTest(groups = {"required_setup"})
	public void init() throws Exception {
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Chrome");
	}

	@Test(priority = 0,groups = {"required_setup"})
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.ZeePWALogin("E-mail", userType);
		Zee5PWASanityBusinessLogic.selectLanguages();
	}

	@Test(priority=1, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_9106(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.authenticationFunctionality1(userType);
	}
	
	@Test(priority=3, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_8657(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.xdefaultUrlVerification(userType);
	}
	
	@Test(priority=4, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_9343(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.redirectUserVerification(userType);		
	}
	
	//Aug 25
	@Test(priority=5, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_8755(String userType) throws Exception {
		Zee5PWASanityBusinessLogic.contentDiscriptorVerification(userType);
		String xlsname="cd_contents.xlsx"; //name of excel in project folder
		String[] sheets= {"Movie","Shows","Video"}; //sheets in the excel
		Zee5PWASanityBusinessLogic.contentDiscriptorVerificationFromExcel(userType,xlsname,sheets);
	}
	
	@Test(priority=5, groups = {"sprint74-75"})
	@Parameters({ "userType" })
	public void Sprint74and75_Supermoon(String userType) throws Exception {
		String user="notTvodActive";
		//notTvodActive -> Guest and Non Subscribed user
		//notTvodActive299 -> Subscribed 299 user with tvod not active
		//notTvodActive499 -> Subscribed 299 user with tvod not active
		//TvodActive -> tvod active user
		Zee5PWASanityBusinessLogic.tvodVerification(userType,user);
	}

	@AfterClass(groups = {"required_setup"})
	public void tearDown() {
		Zee5PWASanityBusinessLogic.tearDown();
	}
}