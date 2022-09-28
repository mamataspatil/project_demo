package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.business.zee.Zee5PWASanityAndroidBusinessLogic;

public class AndroidPWAVIIntegrationClassicScripts {
	
	private Zee5PWASanityAndroidBusinessLogic Zee5PWASanityBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Vodafone");
	}
	
//	P1 - zee 5 contents on Vi app
	@Test(priority = 0)
	public void NavigateTOZeeAndVerifyZee5Content() throws Exception {
		
	}
	
//	PR2 : Assigning ZEE5 pack to Vi MTV users
	@Test(priority = 0)
	public void VerifyComplimentaryPackIsAttachedForClassicUserViaVIJourney() throws Exception {
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Vodafone");
		Zee5PWASanityBusinessLogic.VerifyComplimentaryPackIsAttachedForClassicUserViaVIJourney();
	}
	
	public void VerifyAutoRenewableAndRenewedDetailsOnMySubscriptionPage() throws Exception {
		Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Vodafone");
		Zee5PWASanityBusinessLogic.VerifyAutoRenewableAndRenewedDetailsOnMySubscriptionPage();
	}
	
	
	@AfterClass
	public void tearDown() {
		Zee5PWASanityBusinessLogic.tearDown();
	}	
	
}



//public void VerifyComplimentaryPackIsAttachedForVipUserViaVIJourney() throws Exception {
//	Zee5PWASanityBusinessLogic = new Zee5PWASanityAndroidBusinessLogic("Vodafone");
//	Zee5PWASanityBusinessLogic.VerifyComplimentaryPackIsAttachedForClassicUserViaVIJourney();
//}