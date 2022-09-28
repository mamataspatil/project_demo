package com.zee5.DFP;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class AMDDFPTestScript {

	private Zee5ApplicasterBusinessLogic zee5ApplicasterBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
	//	CharlesConfigure.openCharles();
		Utilities.relaunch = true;
		zee5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 1)
	@Parameters({ "userType", "DFPData" })
	public void ValidationOfDifferentTypeAds(String userType, String SearchData) throws Exception {
		System.out.println("\n Validation Of Different Type Ads");
		zee5ApplicasterBusinessLogic.accessDeviceLocationPopUpDFP("Allow", userType);
		zee5ApplicasterBusinessLogic.selectYourCountryAndLanguage();
		zee5ApplicasterBusinessLogic.zee5AppLoginDFP(userType);	
		zee5ApplicasterBusinessLogic.validateNativeAds(userType);
		zee5ApplicasterBusinessLogic.validateMastheadAds(userType);
		zee5ApplicasterBusinessLogic.validateCompanionAds(userType,SearchData);
	}
	
	
//	@Test(priority = 2)
	@Parameters({ "userType", "DFPData" })
	public void ValidationOfPlaybackAds(String userType, String SearchData) throws Exception {
		System.out.println("\nValidation Of Playback Ads");
		zee5ApplicasterBusinessLogic.relaunch(true);
		zee5ApplicasterBusinessLogic.accessDeviceLocationPopUpDFP("Allow", userType);
		zee5ApplicasterBusinessLogic.selectYourCountryAndLanguage();
		zee5ApplicasterBusinessLogic.zee5AppLoginDFP(userType);	
		zee5ApplicasterBusinessLogic.validatePlaybackAds(userType, SearchData);
	}
	
	
//	@Test(priority = 3)
	@Parameters({ "userType", "DFPData" })
	public void CharlesScenarioValidation(String userType, String SearchData) throws Exception {
		System.out.println("\n Charles Scenario Validation");
		zee5ApplicasterBusinessLogic.relaunch(true);
		//CharlesConfigure.clearCharlesLog();
		zee5ApplicasterBusinessLogic.accessDeviceLocationPopUpDFP("Allow", userType);
		zee5ApplicasterBusinessLogic.selectYourCountryAndLanguage();
		zee5ApplicasterBusinessLogic.zee5AppLoginDFP(userType);	
		zee5ApplicasterBusinessLogic.validateCharlesResponseAds(userType, SearchData);
		if(!userType.equalsIgnoreCase("SubscribedUser")){
			CharlesConfigure.saveCharles("DFPContentlog3_");
			ParseCharlesLogs.readDocumnet();
		}
	}
	
	@AfterTest
	public void tearDownApp() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		System.out.println("\nExecution Complete - Quiting the App");
		zee5ApplicasterBusinessLogic.tearDown();
	}
}
