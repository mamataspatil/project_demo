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

public class AMDVmaxTestScripts {

	private Zee5ApplicasterBusinessLogic zee5ApplicasterBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		CharlesConfigure.openCharles();
		Utilities.relaunch = true;
		zee5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zeeVmax");
	}

	@Test(priority = 1)
	@Parameters({ "userType", "VmaxData1" })
	public void AdPodVideoAdsValidation(String userType, String SearchData) throws Exception {
		System.out.println("Vmax : Ad Pod Video Ads");
		zee5ApplicasterBusinessLogic.relaunchVmax(true);
		CharlesConfigure.clearCharlesLog();
		zee5ApplicasterBusinessLogic.acceptAlertVmax();
		zee5ApplicasterBusinessLogic.accessDeviceLocationPopUpDFP("Allow", userType);
		zee5ApplicasterBusinessLogic.selectYourCountryAndLanguage();
		zee5ApplicasterBusinessLogic.zeeSkip();
		zee5ApplicasterBusinessLogic.zee5AppLoginVMAX(userType);
		zee5ApplicasterBusinessLogic.validatePlaybackVMAXAds(userType, SearchData);

		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			CharlesConfigure.saveCharles("VMAXContentLog_");
			ParseCharlesLogs.readVMAXDocumnet();

		}
	}

	@Test(priority = 2)
	@Parameters({ "userType", "VmaxData2" })
	public void BackToBackVideoAdsValidation(String userType, String SearchData) throws Exception {
		System.out.println("Vmax : Back to Back Video Ads");
		zee5ApplicasterBusinessLogic.relaunchVmax(true);
		CharlesConfigure.clearCharlesLog();
		zee5ApplicasterBusinessLogic.acceptAlertVmax();
		zee5ApplicasterBusinessLogic.accessDeviceLocationPopUpDFP("Allow", userType);
		zee5ApplicasterBusinessLogic.selectYourCountryAndLanguage();
		zee5ApplicasterBusinessLogic.zeeSkip();
		zee5ApplicasterBusinessLogic.zee5AppLoginVMAX(userType);
		zee5ApplicasterBusinessLogic.ValidatePlaybackVMAXAds2(userType, SearchData);

		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			CharlesConfigure.saveCharles("VMAXContentLog_");
			ParseCharlesLogs.readVMAXDocumnet();
			// Back to Back Video Ads
			ParseCharlesLogs.VMAXValidation("Omreta", "15", "Stage Video Preroll", "77325");
			ParseCharlesLogs.VMAXValidation("Rejectx", "15", "Stage Video MidA", "77327");
			ParseCharlesLogs.VMAXValidation("Ghoomketu", "30", "Stage Video MidB", "77328");

		}
	}

	@Test(priority = 3)
	@Parameters({ "userType", "VmaxData3" })
	public void BackToBackCompanionANDVideoCompanionValidation(String userType, String SearchData) throws Exception {
		System.out.println("Vmax : Back to Back Companion + Video - Companion");
		zee5ApplicasterBusinessLogic.relaunchVmax(true);
		CharlesConfigure.clearCharlesLog();
		zee5ApplicasterBusinessLogic.acceptAlertVmax();
		zee5ApplicasterBusinessLogic.accessDeviceLocationPopUpDFP("Allow", userType);
		zee5ApplicasterBusinessLogic.selectYourCountryAndLanguage();
		zee5ApplicasterBusinessLogic.zeeSkip();
		zee5ApplicasterBusinessLogic.zee5AppLoginVMAX(userType);
		zee5ApplicasterBusinessLogic.validatePlaybackVMAXAds3(userType, SearchData);

		if (!userType.equalsIgnoreCase("SubscribedUser")) {
			CharlesConfigure.saveCharles("VMAXContentLog_");
			ParseCharlesLogs.readVMAXDocumnet();
			// Back to Back Companion
			ParseCharlesLogs.VMAXValidation("Baarish", "32", "Stage Companion Preroll", "77237");
			ParseCharlesLogs.VMAXValidation("SOS", "30", "Stage Companion Bumper", "77238");
			// Video + Companion
			ParseCharlesLogs.VMAXValidation("Maafia", "15", "Stage Video MidrollA", "77240");
			ParseCharlesLogs.VMAXValidation("Yaara", "15", "Stage Companion MidrollB", "77243");

		}
	}

	@AfterTest
	public void tearDownApp() throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		System.out.println("\nExecution Complete - Quiting the App");
		zee5ApplicasterBusinessLogic.tearDown();
	}

}
