package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityWEBBusinessLogic;
import com.business.zee.Zee5PWAWEBMixPanelBusinessLogic;

public class WebPWAInSprintScript {

	private Zee5PWASanityWEBBusinessLogic Zee5WEBPWASanityBusinessLogic;
	private Zee5PWAWEBMixPanelBusinessLogic Zee5PWAWEBMixPanelBusinessLogic;

	@BeforeTest(groups = {"required_setup"})
	public void init() throws Exception {
		// zee5WebBusinessLogic.relaunchFlag = false;
		Zee5WEBPWASanityBusinessLogic = new Zee5PWASanityWEBBusinessLogic("Chrome");
	}

	@Test(priority = 1,groups = {"required_setup"})
	@Parameters({ "userType" })
	public void PWAWEBLogin(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ZeeWEBPWALogin(userType);
	}

	@Test(priority=2, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_9106(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.authenticationFunctionality1(userType);
	}
	
	@Test(priority=3, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_8657(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.xdefaultUrlVerification(userType);
	}
	
	@Test(priority=4, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_9343(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.redirectUserVerification(userType);		
	}
	
	@Test(priority=5, groups = {"sprint72-73"})
	@Parameters({ "userType" })
	public void Sprint72and73_PWA2_9444(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.tearDown();
		Zee5PWAWEBMixPanelBusinessLogic = new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
		Zee5PWAWEBMixPanelBusinessLogic.ZeeWEBPWAMixPanelLogin(userType);	
		String[] requiredEvents= {"Video View"};
		Zee5PWAWEBMixPanelBusinessLogic.ExecuteEvent_Set1(1,userType,"Home",0,"movie",requiredEvents,5,"premium");
		
	}
	
	@Test(priority=6, groups = {"sprint74"})
	@Parameters({ "userType" })
	public void Sprint74and75_PWA2_8755(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.contentDiscriptorVerification(userType);
		String xlsname="cd_contents.xlsx"; //name of excel in project folder
		String[] sheets= {"Movie","Shows","Video"}; //sheets in the excel
		Zee5WEBPWASanityBusinessLogic.contentDiscriptorVerificationFromExcel(userType,xlsname,sheets);
	}
	
	@Test(priority=7, groups = {"sprint74-75"})
	@Parameters({ "userType" })
	public void PWAWEBPerformance(String userType) throws Exception {		
		Zee5WEBPWASanityBusinessLogic.pwaPerformance(3);
	}

	
	@Test(priority=3, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPagePlexMovies(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyComboOfferPagePlexMovies(userType);
	}
	
	@Test(priority=4, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferForMovies(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyComboOfferForMovies(userType, "ZEEPLEX");;
	}
	
	@Test(priority=5, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferThroughSearchEntry(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyComboOfferThroughSearchEntry(userType);
	}
	
	@Test(priority=6, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void EntireTextOnComboOfferPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyEntireTextOnComboOfferPage(userType);
	}
	
	@Test(priority=7, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void KnowMoreCTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyKnowMoreCTA(userType, "ZEEPLEX");;
	}
	
	@Test(priority=8, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SupermoonLogoPremiumIconsPrice(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifySupermoonLogoPremiumIconsPrice(userType, "ZEEPLEX");;
	}
	
	@Test(priority=9, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void userIsAbleToSeeTheComboOfferWidgetBelowPlayerOnPlexConsumptionPageforSupermooon(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.userIsAbleToSeeTheComboOfferWidgetBelowPlayerOnPlexConsumptionPageforSupermooon(userType);
	}
	
	@Test(priority=10, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void textOnComboOffernudgeIsConfigurable(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.textOnComboOffernudgeIsConfigurable(userType , "ZEEPLEX");
	}
	
	@Test(priority=11, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void blocbusterMoviesAndLiveEventsAtHome(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.blocbusterMoviesAndLiveEventsAtHome(userType);
	}
	
	@Test(priority=12, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void comboOfferPageAfterClickinOnRentNow(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.comboOfferPageAfterClickinOnRentNow(userType, "ZEEPLEX");
	}
	
	@Test(priority=13, groups = {"sprint77"})
	@Parameters({ "userType" })
	//Premium is displayed instead of Supermoon combo 
	public void verifyinformationisdisplayingonComboScreen(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.verifyinformationisdisplayingonComboScreen(userType, "ZEEPLEX");
	}
	
	
	@Test(priority=14, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void supermoonInfoInOnlyrentAllInfo(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.supermoonInfoInOnlyrent(userType, "ZEEPLEX");
	}

	//@Test(priority=15, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void OtherPlanOnlyRentContentForINR249(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.OtherPlanOnlyRentContentForINR249(userType, "ZEEPLEX");;
	}
	
	@Test(priority=16, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SupermoonComboPackDefaultSelect(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SupermoonComboPackDefaultSelect(userType, "ZEEPLEX");;
	}
	
	@Test(priority=17, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void supermoonPrivacyPolicyAndTermsAndConditions(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.supermoonPrivacyPolicyAndTermsAndConditions(userType, "ZEEPLEX");
	}
	
	@Test(priority=18, groups = {"sprint77"})
	@Parameters({ "userType" })
	//Buy premium is displayed instead of buy supermoon
	public void BuyorRentorUpgrade(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BuyorRentorUpgrade(userType, "ZEEPLEX");
	}
	
	
	@Test(priority=19, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TextChangeAsDisplayLanguage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TextChangeAsDisplayLanguage(userType, "ZEEPLEX");
	}
	
	@Test(priority=20, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferScreenLessThan299RentNowCTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferScreenLessThan299RentNowCTA(userType);;
	}
	
	@Test(priority=21, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferScreenLessThan299RentNowCTAOnPlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferScreenLessThan299RentNowCTAOnPlayer(userType);;
	}
	
	@Test(priority=22, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferScreenLessThan299RentNowCTABelowPlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferScreenLessThan299RentNowCTABelowPlayer(userType);
	}
	
	@Test(priority=23, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferScreenLessThan299RentAfterClickingOnKnowMore(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferScreenLessThan299RentAfterClickingOnKnowMore(userType);
	}
	
	
	@Test(priority=24, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void comboOfferTitleAndRentContentCardTitle(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.comboOfferTitleAndRentContentCardTitle(userType, "ZEEPLEX");
	}
	
	@Test(priority=25, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void supermoonCardAndStrikedPrice(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.supermoonCardAndStrikedPrice(userType, "ZEEPLEX");;
	}
	
	@Test(priority=26, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void comboOfferpageText(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.comboOfferpageText(userType, "ZEEPLEX");;
	}
	
	@Test(priority=27, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyUpgragebuttonFor299user(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyUpgragebuttonFor299user(userType, "ZEEPLEX");
	}
	
	@Test(priority=28, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyFor299userRentShow(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyFor299userRentShow(userType, "ZEEPLEX");
	}
	
	
	@Test(priority=29, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyFor299PaymentPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyFor299PaymentPage(userType, "ZEEPLEX");
	}
	
	@Test(priority=30, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyFor299PaymentPageforOnlyRentMovie(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyFor299PaymentPageforOnlyRentMovie(userType, "ZEEPLEX");;
	}
	
	@Test(priority=31, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void supermoonCardAndStrikedPriceForRSVODUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.supermoonCardAndStrikedPriceForRSVODUser(userType, "ZEEPLEX");;
	}
	
	@Test(priority=32, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void justPayingDifferenceTxtAndActivePlanTxtRSVODUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.justPayingDifferenceTxtAndActivePlanTxtRSVODUser(userType);
	}
	
	@Test(priority=33, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void comboOfferTitleAndRentContentCardTitleForRSVODUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.comboOfferTitleAndRentContentCardTitleForRSVODUser(userType, "ZEEPLEX");
	}
	
	
	@Test(priority=34, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void comboOfferpageTextForRSVODUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.comboOfferpageTextForRSVODUser(userType, "ZEEPLEX");
	}
	
	@Test(priority=35, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyRentShowRSVODUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyRentShowRSVODUser(userType, "ZEEPLEX");;
	}
	
	@Test(priority=36, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void verifyTheRentNowCTABelowPlayerOnZeePlexConsumptionPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.verifyTheRentNowCTABelowPlayerOnZeePlexConsumptionPage(userType);;
	}
	
	
	@Test(priority=37, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void comboOfferTitleAndRentContentCardTitleForNonsubsUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.comboOfferTitleAndRentContentCardTitleForNonsubsUser(userType, "ZEEPLEX");
	}
	
	@Test(priority=38, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void supermoonCardAndStrikedPriceForNonSubs(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.supermoonCardAndStrikedPriceForNonSubs(userType, "ZEEPLEX");
	}
	
	
	//@Test(priority=39, groups = {"sprint77"})
	//already covered
	@Parameters({ "userType" })
	public void justPayingDifferenceTxtAndActivePlanTxt(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.justPayingDifferenceTxtAndActivePlanTxt(userType, "ZEEPLEX");
	}
	
	@Test(priority=40, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyComboOfferPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyComboOfferPage(userType);;
	}
	
	@Test(priority=41, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BuySubscriptionPageThroughHeader(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BuySubscriptionPageThroughHeader(userType);;
	}
	@Test(priority=42, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BuyPlanInConsumptionPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BuyPlanInConsumptionPage(userType);
	}
	
	@Test(priority=43, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BuyPlanInCarousel(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BuyPlanInCarousel(userType);
	}
	
	
	@Test(priority=44, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyComboOfferPageThroughTab(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyComboOfferPageThroughTab(userType);
	}
	
	@Test(priority=45, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPageThroughTitleClick(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferPageThroughTitleClick(userType);;
	}
	
	@Test(priority=46, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BuySubscriptionPageThroughCarousel(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BuySubscriptionPageThroughCarousel(userType);;
	}
	@Test(priority=47, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void MYSubscriptionPageValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.MYSubscriptionPageValidation(userType);
	}
	
	@Test(priority=48, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UpcomingAndNowShowing(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UpcomingAndNowShowing(userType);
	}
	
	
	@Test(priority=49, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UpcomingTrailer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UpcomingTrailer(userType);
	}
	
	@Test(priority=50, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ZeeplexCTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ZeeplexCTA(userType);;
	}
	
	@Test(priority=51, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TrailerAndRentNow(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TrailerAndRentNow(userType);;
	}
	@Test(priority=52, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TrailerAndRentForInr(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TrailerAndRentForInr(userType);
	}
	
	@Test(priority=53, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ZeeplexCTABasedOnDisplayLanguage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ZeeplexCTABasedOnDisplayLanguage(userType);
	}
	
	
	@Test(priority=54, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferWidget(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferWidget(userType);
	}
	
	@Test(priority=55, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowOnPlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowOnPlayer(userType);;
	}
	
	@Test(priority=56, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowBelowPlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowBelowPlayer(userType);;
	}
	
	@Test(priority=57, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowOnWidget(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowOnWidget(userType);
	}
	
	@Test(priority=58, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPageThroughSearch(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferPageThroughSearch(userType);
	}
	
	
	@Test(priority=59, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowGuestcheckout(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowGuestcheckout(userType);
	}
	
	@Test(priority=60, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TrailerConsumptionPageGuestCheckOut(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TrailerConsumptionPageGuestCheckOut(userType);;
	}
	
	@Test(priority=61, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowBelowPlayerGuestCheckout(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowBelowPlayerGuestCheckout(userType);;
	}
	@Test(priority=62, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowAndLoginOnPlayerGuestCheckOut(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowAndLoginOnPlayerGuestCheckOut(userType);
	}
	
	@Test(priority=63, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferWidgetGuestCheckout(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferWidgetGuestCheckout(userType);
	}
	
	
	@Test(priority=64, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowOnPlayerGuestCheckout(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowOnPlayerGuestCheckout(userType);
	}
	
	@Test(priority=65, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void LiveTVSupermoonContentGuestCheckout(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.LiveTVSupermoonContentGuestCheckout(userType);;
	}
	
	@Test(priority=66, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void KnowMoreWidgetGuestCheckOut(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.KnowMoreWidgetGuestCheckOut(userType);;
	}
	@Test(priority=67, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BuyComboBtnGuestCheckOut(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BuyComboBtnGuestCheckOut(userType);
	}
	
	@Test(priority=68, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void AccountInfoGuestCheckOut(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.AccountInfoGuestCheckOut(userType);
	}
	
	
	@Test(priority=69, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferScreenLessThan299User(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferScreenLessThan299User(userType);
	}
	
	@Test(priority=70, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComsumptionPageLessThan299User(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComsumptionPageLessThan299User(userType);;
	}
	
	@Test(priority=71, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowCTAConsumptionPageLessThan299User(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowCTAConsumptionPageLessThan299User(userType);;
	}
	
	@Test(priority=72, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ValidateSearchContentLessThan299User(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ValidateSearchContentLessThan299User(userType,"Demo Moon Live");
	}
	
	@Test(priority=73, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void LiveTVSupermoonLessThan299User(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.LiveTVSupermoonLessThan299User(userType);
	}
	
	
	@Test(priority=74, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TermsAndPrivacyPolicyRSVOD(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TermsAndPrivacyPolicyRSVOD(userType);
	}
	
	@Test(priority=75, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TermsOfUsePageRSVODUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TermsOfUsePageRSVODUser(userType);;
	}
	
	@Test(priority=76, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PrivacyPolicyPageRSVODUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PrivacyPolicyPageRSVODUser(userType);;
	}
	
	
	@Test(priority=77, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UpgradeButtonRSVOD(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UpgradeButtonRSVOD(userType);;
	}
	
	@Test(priority=78, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentPageUsingUpgradeButtonRSVOD(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentPageUsingUpgradeButtonRSVOD(userType);
	}
	
	
	/*
	 * @Test(priority=79, groups = {"sprint77"})
	 * 
	 * @Parameters({ "userType" }) public void
	 * PaymentPageUsingRentNowButtonRSVOD(String userType) throws Exception {
	 * Zee5WEBPWASanityBusinessLogic.PaymentPageUsingRentNowButtonRSVOD(userType); }
	 */
	
	@Test(priority=80, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TermsAndPrivacyPolicGuest(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TermsAndPrivacyPolicGuest(userType);
	}
	
	@Test(priority=81, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TermsOfUsePageGuest(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TermsOfUsePageGuest(userType);
	}
	
	@Test(priority=82, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PrivacyPolicyPageGuest(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PrivacyPolicyPageGuest(userType);
	}
	
	@Test(priority=83, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowCTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowCTA(userType);
	}
	
	
	@Test(priority=84, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TrailerCTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TrailerCTA(userType);
	}
	
	@Test(priority=85, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowCTAOnPlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowCTAOnPlayer(userType);;
	}
	
	@Test(priority=86, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RentNowCTAOnPlayerThroughSearch(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RentNowCTAOnPlayerThroughSearch(userType);;
	}
	@Test(priority=87, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferWidgetLiveTV(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferWidgetLiveTV(userType);
	}
	
	@Test(priority=88, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPageThroughZEEPLEXTitle(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferPageThroughZEEPLEXTitle(userType);
	}
	
	
	@Test(priority=89, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPageThroughKnowMore(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferPageThroughKnowMore(userType);
	}
	
	@Test(priority=90, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPageOnClickONBelowPlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferPageOnClickONBelowPlayer(userType);;
	}
	
	@Test(priority=91, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferScreenDetails(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferScreenDetails(userType);
	}
	@Test(priority=92, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void authenticationFunctionality1(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.authenticationFunctionality1(userType);
	}
	
	@Test(priority=93, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyComboOfferThroughTabEntryPointsRSVOD(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyComboOfferThroughTabEntryPointsRSVOD(userType , "ZEEPLEX");
	}
	
	
	@Test(priority=94, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void comboOfferpageTextForNonSubsUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.comboOfferpageTextForNonSubsUser(userType , "ZEEPLEX");
	}
	
	
	@Test(priority=95, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void watchCTAonCarousalForSupermoon(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.watchCTAonCarousalForSupermoon(userType , "ZEEPLEX");;
	}
	@Test(priority=96, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ResumeCTAonCarousalForSupermoon(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ResumeCTAonCarousalForSupermoon(userType , "ZEEPLEX");
	}
	
	@Test(priority=97, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void watchCTAonZeePlexForSupermoonevent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.watchCTAonZeePlexForSupermoonevent(userType);
	}
	
	
	@Test(priority=98, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void CreditAndDebitCardAsPerVD(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.CreditAndDebitCardAsPerVD(userType);
	}
	
	@Test(priority=99, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void CardDetailsValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.CardDetailsValidation(userType);;
	}
	
	@Test(priority=100, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BankPageOnSearchedResult(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BankPageOnSearchedResult(userType);;
	}
	@Test(priority=101, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferWidgetBasedOnDisplayLanguage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferWidgetBasedOnDisplayLanguage(userType);
	}
	
	@Test(priority=102, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremium999(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremium999(userType);
	}
	
	
	@Test(priority=103, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremiumPopupClone999(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremiumPopupClone999(userType);
	}
	
	@Test(priority=104, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremium749(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremium749(userType);;
	}
	
	@Test(priority=105, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremiumPopupClone749(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremiumPopupClone749(userType);;
	}
	
	@Test(priority=106, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremium599(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremium599(userType);
	}
	
	@Test(priority=107, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremiumPopupClone599(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremiumPopupClone599(userType);
	}
	
	
	@Test(priority=108, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremium499(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremium499(userType);
	}
	
	@Test(priority=109, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExplorePremiumPopupClone499(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExplorePremiumPopupClone499(userType);;
	}
	
	@Test(priority=110, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TermsAndPrivacyPolicyNonSubscribeduser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TermsAndPrivacyPolicyNonSubscribeduser(userType);;
	}
	
	@Test(priority=111, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TermsOfUsePageNonSubscribeduser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TermsOfUsePageNonSubscribeduser(userType);
	}
	
	@Test(priority=112, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PrivacyPolicyPageNonSubscribeduser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PrivacyPolicyPageNonSubscribeduser(userType);
	}
	
	
	@Test(priority=113, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ResumeCTAonZeePlexForSupermoonevent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ResumeCTAonZeePlexForSupermoonevent(userType);
	}
	
	@Test(priority=114, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void NoComboOfferWidgetBelowThePlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.NoComboOfferWidgetBelowThePlayer(userType);;
	}
	
	@Test(priority=115, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExpiresInHoursBelowThePlayer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExpiresInHoursBelowThePlayer(userType);;
	}
	
	@Test(priority=116, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExpiresInRentalAndWatchTime(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExpiresInRentalAndWatchTime(userType);
	}
	
	@Test(priority=117, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void componentsInRentalAndWatchTime(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.componentsInRentalAndWatchTime(userType);
	}
	
	
	@Test(priority=118, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void activeAndExpiryInRentalAndWatchTime(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.activeAndExpiryInRentalAndWatchTime(userType);
	}
	
	@Test(priority=119, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void NavigateToConsumptionScreenFromMyRental(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.NavigateToConsumptionScreenFromMyRental(userType);;
	}
	
	@Test(priority=120, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void noRentalTimeValidityInZeeplexPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noRentalTimeValidityInZeeplexPage(userType);;
	}
	
	@Test(priority=121, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void HowItWorksInConsumptionPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.HowItWorksInConsumptionPage(userType);
	}
	
	@Test(priority=122, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SixPopularBanks(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SixPopularBanks(userType);
	}
	
	
	@Test(priority=123, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UPISelectionForNonRecuring(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UPISelectionForNonRecuring(userType);
	}
	
	@Test(priority=124, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UPIPaymentScreenValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UPIPaymentScreenValidation(userType);;
	}
	
	@Test(priority=125, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UPICountDownTimer(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UPICountDownTimer(userType);;
	}
	
	@Test(priority=126, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PlanSelectionPageUsingBackButtonInUPI(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PlanSelectionPageUsingBackButtonInUPI(userType);
	}
	
	@Test(priority=127, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void EnterUPIID(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.EnterUPIID(userType);
	}
	
	
	@Test(priority=128, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void GenerateORPageUsingBackArrowInUPI(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.GenerateORPageUsingBackArrowInUPI(userType);
	}
	
	@Test(priority=129, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void vpahandle(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.vpahandle(userType);;
	}
	
	@Test(priority=130, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyAmazonPageValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyAmazonPageValidation(userType);;
	}
	
	@Test(priority=131, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void AmazonPayLogoLoginMobileNumber(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.AmazonPayLogoLoginMobileNumber(userType);
	}
	
	@Test(priority=132, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void MobikvikOTPValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.MobikvikOTPValidation(userType);
	}
	
	
	@Test(priority=133, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ValidateAmazonPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ValidateAmazonPage(userType);
	}
	
	@Test(priority=134, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void MobikvikPayLogoLoginMobileNumber(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.MobikvikPayLogoLoginMobileNumber(userType);;
	}
	
	@Test(priority=135, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SentOTPButtonEnabled(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SentOTPButtonEnabled(userType);;
	}
	
	@Test(priority=136, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void MobikvikOTPPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.MobikvikOTPPage(userType);
	}
	
	@Test(priority=137, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void MobikvikOTPPageValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.MobikvikOTPPageValidation(userType);
	}
	
	
	@Test(priority=138, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ListOfPaymentOption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ListOfPaymentOption(userType);
	}
	
	@Test(priority=139, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ValidateLinkPaytmText(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ValidateLinkPaytmText(userType);;
	}
	
	@Test(priority=140, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ValidateLinksWallets(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ValidateLinksWallets(userType);;
	}
	
	@Test(priority=141, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ZEE5LogoInPaymentPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ZEE5LogoInPaymentPage(userType);
	}
	
	@Test(priority=142, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void CreditAndDebitCardOption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.CreditAndDebitCardOption(userType);;
	}
	
	@Test(priority=143, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentFailurePopupAfterClickingOnBackButton(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentFailurePopupAfterClickingOnBackButton(userType);;
	}
	
	@Test(priority=144, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentFailurePopupAfterClickingOnBrowserBackButton(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentFailurePopupAfterClickingOnBrowserBackButton(userType);
	}
	
	@Test(priority=145, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void HaveAGiftCardOption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.HaveAGiftCardOption(userType);
	}
	
	
	@Test(priority=146, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void CardNumberPINPayValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.CardNumberPINPayValidation(userType);
	}
	
	@Test(priority=147, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void CardNumberAndPinNumberDigitValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.CardNumberAndPinNumberDigitValidation(userType);;
	}
	
	@Test(priority=148, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ErrorMessageValidation(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ErrorMessageValidation(userType);;
	}
	
	
	
	@Test(priority=149, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void supermoonInContinueWatchingTray(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.supermoonInContinueWatchingTray(userType);
	}
	
	@Test(priority=150, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void shareFunctionalitySupermoon(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.shareFunctionalitySupermoon(userType);;
	}
	
	@Test(priority=151, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void watchlistFunctionalitySupermoon(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.watchlistFunctionalitySupermoon(userType);;
	}
	
	@Test(priority=152, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void titleBelowThePlayerSupermoon(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.titleBelowThePlayerSupermoon(userType);
	}
	
	@Test(priority=153, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void descriptionBelowShareBtn(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.descriptionBelowShareBtn(userType);
	}
	
	
	@Test(priority=154, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void zeeplexLogoInLiveTVODConsumption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.zeeplexLogoInLiveTVODConsumption(userType);
	}
	
	@Test(priority=155, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void zeeplexLogoInVideoTVODConsumption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.zeeplexLogoInVideoTVODConsumption(userType , "Binge Holidays!  - December Calendar Launch");;
	}
	
	@Test(priority=156, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void metaInfoForVideoTVODConsumption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.metaInfoForVideoTVODConsumption(userType , "Binge Holidays!  - December Calendar Launch");;
	}
	
	@Test(priority=157, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void clickOnWatchListForVideoTVODConsumption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.clickOnWatchListForVideoTVODConsumption(userType, "Sensational Dialogues | Sensational ZEE5");
	}
	
	@Test(priority=158, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyInWatchListForVideoTVODConsumption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyInWatchListForVideoTVODConsumption(userType ,"Binge Holidays!  - December Calendar Launch");;
	}
	
	@Test(priority=159, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyPreRollAdForLiveTVODContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyPreRollAdForLiveTVODContent(userType, "Demo Moon Live");;
	}
	
	@Test(priority=160, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PreAndMidRollAdsForExclusiveVODContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PreAndMidRollAdsForExclusiveVODContent(userType ,  "Binge Holidays!  - December Calendar Launch");
	}
	
	@Test(priority=161, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PostRollAdsForExclusiveVODContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PostRollAdsForExclusiveVODContent(userType , "ExclusiveTvodcontent");
	}
	
	
	@Test(priority=162, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void playAndPauseForLiveTvTVODContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.playAndPauseForLiveTvTVODContent(userType,  "Demo Moon Live");
	}

	@Test(priority=163, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void noPlayerFunctionalityForLiveTVContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noPlayerFunctionalityForLiveTVContent(userType ,"Demo Moon Live" );;
	}

	@Test(priority=164, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void verifyLiveTvTagForLiveTVContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.verifyLiveTvTagForLiveTVContent(userType , "Demo Moon Live");;
	}
	
	@Test(priority=165, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyPlayerControlsForVideoTVODConsumption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyPlayerControlsForVideoTVODConsumption(userType , "Binge Holidays!  - December Calendar Launch");
	}
	
	@Test(priority=166, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyParentalPinForVideoTVODConsumption(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyParentalPinForVideoTVODConsumption(userType , "videoname");;
	}
	
	@Test(priority=167, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void watchCTAonCarousalForSupermoonCarousal(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.watchCTAonCarousalForSupermoonCarousal(userType , "ZEEPLEX");;
	}
	
	@Test(priority=168, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ResumeCTAonCarousalForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ResumeCTAonCarousalForAnypacklessThan499withSupermoonActiveUser(userType, "ZEEPLEX");
	}
	
	@Test(priority=170, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void watchCTAonZeePlexForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.watchCTAonZeePlexForAnypacklessThan499withSupermoonActiveUser(userType);
	}
	
	
	@Test(priority=171, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ResumeCTAonZeePlexForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ResumeCTAonZeePlexForAnypacklessThan499withSupermoonActiveUser(userType);
	}
	
	@Test(priority=172, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExpiresInHoursBelowThePlayerForAnypacklessThan499withSupermoonActiveUse(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExpiresInHoursBelowThePlayerForAnypacklessThan499withSupermoonActiveUse(userType);;
	}
	
	@Test(priority=173, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ExpiresInRentalAndWatchTimeForAnypacklessThan499withSupermoonActiveUse(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ExpiresInRentalAndWatchTimeForAnypacklessThan499withSupermoonActiveUse(userType);;
	}
	
	
	@Test(priority=174, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void noRentalTimeValidityInZeeplexPageForAnypacklessThan499withSupermoonActiveUse(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noRentalTimeValidityInZeeplexPageForAnypacklessThan499withSupermoonActiveUse(userType);
	}
	
	@Test(priority=175, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void HowItWorksInConsumptionPageForAnypacklessThan499withSupermoonActiveUse(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.HowItWorksInConsumptionPageForAnypacklessThan499withSupermoonActiveUse(userType);;
	}
	
	@Test(priority=176, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void supermoonInContinueWatchingTrayForAnypacklessThan499withSupermoonActiveUse(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.supermoonInContinueWatchingTrayForAnypacklessThan499withSupermoonActiveUse(userType ,"Livetvodcontent");;
	}
	
	@Test(priority=177, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void componentsInRentalAndWatchTimeForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.componentsInRentalAndWatchTimeForAnypacklessThan499withSupermoonActiveUser(userType);
	}
	
//	@Test(priority=178, groups = {"sprint77"})
	//There is no expired tvod content currently (dated 27 aug)
	@Parameters({ "userType" })
	public void activeAndExpiryInRentalAndWatchTimeForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.activeAndExpiryInRentalAndWatchTimeForAnypacklessThan499withSupermoonActiveUser(userType);
	}
	
	
	@Test(priority=179, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VideoTVODContentInContinueWatchingTrayForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VideoTVODContentInContinueWatchingTrayForAnypacklessThan499withSupermoonActiveUser(userType , "Binge Holidays!  - December Calendar Launch");
	}
	
	@Test(priority=180, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void shareFunctionalitySupermoonForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.shareFunctionalitySupermoonForAnypacklessThan499withSupermoonActiveUser(userType, "Demo Moon Live");;
	}
	
	@Test(priority=181, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void watchlistFunctionalityForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.watchlistFunctionalityForAnypacklessThan499withSupermoonActiveUser(userType , "Demo Moon Live");;
	}
	
	
	@Test(priority=182, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VeerifyEPGSectionForAnypacklessThan499withSupermoonActiveUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VeerifyEPGSectionForAnypacklessThan499withSupermoonActiveUser(userType);
	}
	
//@Test(priority=183, groups = {"sprint77"})
	//no  data
	@Parameters({ "userType" })
	public void VeerifyEPGSectionForoneYearPremium6MSupermoonUser(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VeerifyEPGSectionForoneYearPremium6MSupermoonUser(userType);;
	}
	
	@Test(priority=184, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void titleBelowThePlayerSupermoonForAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.titleBelowThePlayerSupermoonForAnypacklessThan499withSupermoonActive(userType);;
	}
	
	@Test(priority=185, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void descriptionBelowShareBtnForAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.descriptionBelowShareBtnForAnypacklessThan499withSupermoonActive(userType);
	}
	
	@Test(priority=186, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void metaInfoForVideoTVODConsumptionForAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.metaInfoForVideoTVODConsumptionForAnypacklessThan499withSupermoonActive(userType , "Binge Holidays!  - December Calendar Launch");
	}
	
	
	@Test(priority=187, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void clickOnWatchListForVideoTVODConsumptionForAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.clickOnWatchListForVideoTVODConsumptionForAnypacklessThan499withSupermoonActive(userType , "Binge Holidays!  - December Calendar Launch");
	}
	
	@Test(priority=189, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyInWatchListForVideoTVODConsumptionAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyInWatchListForVideoTVODConsumptionAnypacklessThan499withSupermoonActive(userType , "Binge Holidays!  - December Calendar Launch");;
	}
	
	@Test(priority=190, groups = {"sprint77"})
	@Parameters({ "userType" })
	
	public void VerifyPreRollAdForLiveTVODContentAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyPreRollAdForLiveTVODContentAnypacklessThan499withSupermoonActive(userType , "Demo Moon Live");
	}
	
	@Test(priority=191, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void NoComboOfferWidgetBelowThePlayerAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.NoComboOfferWidgetBelowThePlayerAnypacklessThan499withSupermoonActive(userType);
	}
	
	@Test(priority=192, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PreAndMidRollAdsForExclusiveVODContentAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PreAndMidRollAdsForExclusiveVODContentAnypacklessThan499withSupermoonActive(userType , "Binge Holidays!  - December Calendar Launch");
	}
	
	
	@Test(priority=193, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PostRollAdsForExclusiveVODContentAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PostRollAdsForExclusiveVODContentAnypacklessThan499withSupermoonActive(userType , "Binge Holidays!  - December Calendar Launch");
	}
	
	@Test(priority=194, groups = {"sprint77"})
	@Parameters({ "userType" })
	
	public void noAdsForSVODContentExceptLiveTVODContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noAdsForSVODContentExceptLiveTVODContent(userType , "Tanu Tries to Manipulate Abhi" , "Demo Moon Live");
	}
	
	@Test(priority=195, groups = {"sprint77"})
	@Parameters({ "userType" })
	
	public void noAdsForSVODContentExceptVideoTVODContent(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noAdsForSVODContentExceptVideoTVODContent(userType , "Tanu Tries to Manipulate Abhi" , "Demo Moon Live");
	}
	
	@Test(priority=196, groups = {"sprint77"})
	@Parameters({ "userType" })
	
	public void playAndPauseForLiveTvTVODContentAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.playAndPauseForLiveTvTVODContentAnypacklessThan499withSupermoonActive(userType);
	}
	
	@Test(priority=197, groups = {"sprint77"})
	@Parameters({ "userType" })
	
	public void noPlayerFunctionalityForLiveTVContentAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noPlayerFunctionalityForLiveTVContentAnypacklessThan499withSupermoonActive(userType , "Demo Moon Live");
	}
	
	
	@Test(priority=198, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void verifyLiveTvTagForLiveTVContentAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.verifyLiveTvTagForLiveTVContentAnypacklessThan499withSupermoonActive(userType , "Demo Moon Live");
	}

	@Test(priority=199, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyPlayerControlsForVideoTVODConsumptionAnypacklessThan499withSupermoonActive(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyPlayerControlsForVideoTVODConsumptionAnypacklessThan499withSupermoonActive(userType , "Binge Holidays!  - December Calendar Launch");;
	}
	
	@Test(priority=200, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void noAdsForSubscribedUsersForPremiumContents(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noAdsForSubscribedUsersForPremiumContents(userType , "Hero");;
	}
	
	@Test(priority=201, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyChromeCastForVideoTVODorLiveTv(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyChromeCastForVideoTVODorLiveTv(userType , "Binge Holidays!  - December Calendar Launch");
	}
	
	@Test(priority=202, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void noAutoRenewalStatusForSupermoonConentTVODLogin(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.noAutoRenewalStatusForSupermoonConentTVODLogin(userType );
	}
	
	
	@Test(priority=203, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyBuySupermoonComboForNonSub(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyBuySupermoonComboForNonSub(userType , "ZEEPLEX");
	}
	
	@Test(priority=204, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyForNonSubuserRentShow(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyForNonSubuserRentShow(userType , "ZEEPLEX");;
	}
	@Test(priority=205, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void paymentModeScreenAfterClickOnBuySupermoonCTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.paymentModeScreenAfterClickOnBuySupermoonCTA(userType , "ZEEPLEX");;
	}
	
	@Test(priority=206, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void paymentModeScreenAfterClickOnRentMovie(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.paymentModeScreenAfterClickOnRentMovie(userType , "ZEEPLEX");
	}
	
	@Test(priority=207, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void noPlexLogoForSearchedLiveTVTvodResult(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.noPlexLogoForSearchedLiveTVTvodResult(userType , "Demo Moon Live");
	}
	
	@Test(priority=208, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void streamedLiveContentOnAug21(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateHome();
		Zee5WEBPWASanityBusinessLogic.streamedLiveContentOnAug21(userType , "Demo Moon Live");
	}
	
	@Test(priority=209, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SupermoonPlanAmount(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SupermoonPlanAmount(userType );
	}
	
	@Test(priority=210, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SuperMoonContentInSearchResult(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SuperMoonContentInSearchResult(userType,"Demo Moon Live");;
	}
	
	@Test(priority=211, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SuperMoonContentInZeeplex(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SuperMoonContentInZeeplex(userType,"Demo Moon Live");
	}
	
	@Test(priority=212, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SuperMoonExclusiveContentInSearchResult(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SuperMoonExclusiveContentInSearchResult(userType,"Binge Holidays! - December Calendar Launch");
	}
	
	
	@Test(priority=213, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboofferPageUI(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboofferPageUI(userType);
	}
	
	@Test(priority=214, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UpgradeToSupermoonPack(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UpgradeToSupermoonPack(userType);;
	}
	
	//@Test(priority=215, groups = {"sprint77"})
		//no locator for loading
	@Parameters({ "userType" })
	public void LoadingInSubscription(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.LoadingInSubscription(userType);;
	}
	
	@Test(priority=216, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPageThroughSearchResult(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferPageThroughSearchResult(userType,"Demo Moon Live");
	}
	
	@Test(priority=217, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void YouHaveItAll(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.YouHaveItAll(userType );
	}
	
	
	@Test(priority=218, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void Upgradetocomboofferbyjustpayingthedifference(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.Upgradetocomboofferbyjustpayingthedifference(userType);
	}
	
	@Test(priority=219, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void upgradePopup(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.upgradePopup(userType);;
	}
	
	@Test(priority=220, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BackButtonInUpgradePopup(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BackButtonInUpgradePopup(userType);;
	}
	
	@Test(priority=221, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UpgradeCTA(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UpgradeCTA(userType);
	}
	
	@Test(priority=222, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentPageThroughOnlyRentmovie(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentPageThroughOnlyRentmovie(userType );
	}
	
	
	@Test(priority=223, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SubscriptionPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SubscriptionPage(userType );
	}
	
	@Test(priority=224, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentPage(userType);;
	}
	
	@Test(priority=225, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void AccountinfoPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.AccountinfoPage(userType);;
	}
	
	@Test(priority=226, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void AccountinfoPageThroughtRentOnlyMovie(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.AccountinfoPageThroughtRentOnlyMovie(userType);
	}
	
	@Test(priority=227, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentMode(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentMode(userType);
	}
	
	
	@Test(priority=228, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void RsvodComboScreenAsPerVD(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.RsvodComboScreenAsPerVD(userType );
	}
	
	@Test(priority=229, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void NonSubscribedUserComboScreenAsPerVD(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.NonSubscribedUserComboScreenAsPerVD(userType);;
	}
	
	@Test(priority=230, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PackDetails(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PackDetails(userType);
	}
	
	@Test(priority=231, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentFailurePopup(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentFailurePopup(userType);
	}
	
	@Test(priority=232, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void ComboOfferPageFor299User(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.ComboOfferPageFor299User(userType );
	}
	
	
	@Test(priority=233, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void SupermoonPoster(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.SupermoonPoster(userType );
	}
	
	@Test(priority=234, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentPageThroughOnlyRentmovie499(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentPageThroughOnlyRentmovie499(userType);
	}
	
	@Test(priority=235, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void upgradePopup499(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.upgradePopup499(userType);
	}
	
	@Test(priority=236, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void BackButtonInUpgradePopup499(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.BackButtonInUpgradePopup499(userType);
	}
	
	@Test(priority=237, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void UpgradeCTA499(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.UpgradeCTA499(userType);
	}
	
	
	@Test(priority=238, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PackDetails499(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PackDetails499(userType);
	}
	
	@Test(priority=239, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void consumptionPageclickingOnWatchCTAFor749users(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.consumptionPageclickingOnWatchCTAFor749users(userType , "Demo Moon Live");
	}
	
	@Test(priority=240, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void paymentModeScreeWithin3seconds(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.paymentModeScreeWithin3seconds(userType);
	}
	
	@Test(priority=241, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void TrailerPlayWithin5seconds(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.TrailerPlayWithin5seconds(userType);
	}
	
	@Test(priority=242, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentFailurePopupThroughBackButton(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentFailurePopupThroughBackButton(userType);
	}
	
	
	@Test(priority=243, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentPageThroughBrowserBackButton(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentPageThroughBrowserBackButton(userType);
	}
	
	
	@Test(priority=244, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void WalletGateway(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.WalletGateway(userType);
	}
	
	@Test(priority=245, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void PaymentPageWithin15seconds(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PaymentPageWithin15seconds(userType);
	}

//	@Test(priority=246, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyExclusiveVodContentInRail(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyExclusiveVodContentInRail(userType);
	}

	@Test(priority=247, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void EventNameInSearchResult(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.EventNameInSearchResult(userType,"Demo moon Live");
	}
	
	@Test(priority=248, groups = {"sprint77"})
	@Parameters({ "userType" })
	public void VerifyTheComboOfferShouldNotDisplay(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.VerifyTheComboOfferShouldNotDisplay(userType);
	}
	
	@Test(priority=249, groups = {"Sprint76_80"})
	@Parameters({ "userType" })
	public void inSprintSprint_76_80(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.PWA2_8779_ParentalControlChanges(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_9609_zeeplexTitle(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10111_liveTVMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10111_channelsMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_showDetailsMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_episodeMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_episodeSpoilerMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_webisodeMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_bestSceneMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_mobisodeMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_weekInShortsMeta(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10112_tvshowTrailerMeta(userType);	
		Zee5WEBPWASanityBusinessLogic.PWA2_10057_ABTestingBannerSlider(userType);
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforTabPageFromSource(userType,"Movies","Movies landing page","Movies");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforTabPageFromSource(userType,"TV Shows","TV Show landing page","TV Shows");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforDetailsPageFromSource(userType,"tvshows/details/kundali-bhagya/0-6-366","TV Show Details page","Kundali Bhagya");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforPlayersPageFromSource(userType,"Episode Consumption page");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforSpoilerPageFromSource(userType,"Episode Spoiler page");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforWebisodePageFromSource(userType,"Webisode Consumption page");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforMobisodePageFromSource(userType,"Mobisode Consumption page");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforWeekInShortPageFromSource(userType,"Week In Short page");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforBestScenePageFromSource(userType,"Best Scene page");
		Zee5WEBPWASanityBusinessLogic.PWA2_10143_headingOptforTabPageFromSource(userType,"Web Series","Web Series landing page","Web Series");

	}	
	
	@AfterClass
	public void tearDown() {
		Zee5WEBPWASanityBusinessLogic.tearDown();
	}
}