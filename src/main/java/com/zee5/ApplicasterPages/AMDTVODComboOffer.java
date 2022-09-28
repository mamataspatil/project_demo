package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDTVODComboOffer {

	public static final By ObjOnlyRentMoviePlanSelect = null;
	public static By objTrailerCTAonCarousel = By.xpath("");
	public static By objRentNowTextOnPlayer = By.xpath("//*[@id='rentNowDescription']");
	public static By objRentNowCTAOnPlayer = By.xpath("//*[@id='rentNowCta']");
	public static By objRentNowCTABelowPlayer = By.xpath("//*[@id='ctaBannerButton']/child::*[@id='subscribeButton' and contains(text(), 'Rent')]");
	public static By objComboOfferWidgetBelowThePlayer = By.xpath("//*[@id='comboOfferImageView']");
	
	public static By objHowItWorksCTA = By.xpath("//*[@id='howItWorksTextView']");
	public static By objQandAModelWindow = By.xpath("//*[@id='faqList']");
	
	public static By objPayLessWatchMoreText = By.xpath("//*[@id='mainSubtitle' and @text='Pay less, Watch more!']");
	public static By objKnowMoreCTAOnWidget = By.xpath("");
	public static By objPosterOfMovieContent = By.xpath("//*[@id='posterImageView']");
	public static By objDefaultComboOfferPlan = By.xpath("");
	public static By objBuyComboOfferCTAOnComboOfferPage = By.xpath("");
	public static By objRentMovieCTAonComboOfferPage = By.xpath("");

	public static By objCTABelowTheComboOfferPage = By.xpath("//*[@id='continueButton']");
	public static By objUpgradeTextOnComboOfferPage = By.xpath("//*[@id='mainTitle']");
	public static By objUpgradeSubTextOnComboOfferPage = By.xpath("//*[@id='mainSubtitle']");
	public static By objPaymentStep2 = By.xpath("//*[@id='steptwoofthree']");
	public static By objAccountInfoLabel = By.xpath("//*[@id='heading' and @text='Account Info']");
	public static By objBackIcon = By.xpath("//*[@id='backIcon']");
	public static By objEmailIdfield = By.xpath("//*[@id='edit_email']");
	public static By objContinueBtn = By.xpath("//*[@id='continueButton']");

	public static By objAlreadyActive = By.xpath("//*[@text='You have it all']");

	public static By objAdFreeTxt = By.xpath("//*[@id='label' and @text='Watch Ad-free']");
	public static By objPlanName = By.xpath("//*[@id='planName']");
	public static By objPlanDesc = By.xpath("//*[@id='planDescription']");
	public static By objPlanPrice = By.xpath("//*[@id='planPrice']");
	public static By objPlanReccuring = By.xpath("//*[@id='planRecurring']");
	public static By objPlanFooterlbl = By.xpath("///*[@id='planFooterLabel']");

	// Radhe combo Plan Card
	public static By objPlanTitle = By.xpath("//*[@id='comboPlanTitleLabel']");
	public static By objPlanCost = By.xpath("//*[@id='comboRadioButton']");
	public static By objStrikeOutCost = By.xpath("//*[@id='comboPerceivedPriceLabel']");
	public static By objSaveAmount = By.xpath("//*[@id='comboPlanSavingLabel']");
	public static By objRadheRentalPrice = By.xpath("//*[@id='rentalPriceLabel']");
	public static By obj1YearPremiumPlanPrice = By.xpath("//*[@id='yearlyPlanPriceLabel']");
	public static By obj1YearPremiumPlanPack = By.xpath("//*[@id='yearlyPlanBenefitsSectionLabel']");
	public static By objPriceOnBottomOfTheComboCard = By.xpath("//*[@id='comboActualTotalPriceValue']");
	public static By objRadheCard = By.xpath("//*[@id='itemThumbnailImageView']");
	public static By obj1YearPremiumCard = By.xpath("//*[@id='planThumbnailImageView']");
	public static By objRentalValidity = By.xpath("//*[@id='comboRentalValidityLabel']");
	public static By objWatchTimeValidity = By.xpath("//*[@id='comboWatchTimeValidityLabel']");

	// Only Rent Movie card
	public static By objOnlyRentMoviePlanCost = By.xpath("//*[@id='onlyRentRadioButton']");
	public static By objOnlyRentMovieRentalValidity = By.xpath("//*[@id='onlyRentRentalValidityLabel']");
	public static By objOnlyRentMovieWatchTimeValidity = By.xpath("//*[@id='onlyRentWatchTimeValidityLabel']");

	public static By objUpgradeBottomSheet = By.xpath("//*[@id='design_bottom_sheet']");
	public static By objUpgradeBottomSheetText = By.xpath("//*[@id='titleText']");
	public static By objUpgradeBottomSheetSubText = By.xpath("//*[@id='subtitleText']");
	
	public static By objTrailerCTAonCarouselForContent(String contentTitle) {
		return By.xpath("//*[@text='"+contentTitle+"']/parent::*/following-sibling::*/child::*/child::*[@id='outlinedButton' and @text='Trailer']");
	}
	
	public static By objRentNowCTAonCarouselForContent(String contentTitle) {
		return By.xpath("//*[@text='"+contentTitle+"']/parent::*/following-sibling::*/child::*/child::*[@id='subscribeButton' and @text='Rent Now']");
	}
	
	public static By objRentNowCTAonCarousel = By.xpath("//*[@id='subscribeButton' and @text='Rent Now']");
	public static By objRentNowCTAonCarouselAagKaGola = By.xpath("//*[@text='Aag Ka Gola']//following::*//*[@id='subscribeButton' and @text='Rent Now']");
	
	public static By obComboOfferScreen = By.xpath("//*[@id='posterImageView']");
	public static By objPasswordfield = By.xpath("//*[@id='edit_email']");

	public static By objBottomSheetTitle = By.xpath("//*[@id='titleText']");
	public static By objBottomSheetSubTitle = By.xpath("//*[@id='subtitleText']");

	public static By objActivePlanMsg1 = By.xpath("//*[@id='titleText']");

	public static By objWatchNowCalloutCTA = By.xpath("//*[@id='buttonFirstAction' and @text='Watch Now']");
	public static By objBuyPremiumCalloutCTA = By.xpath("//*[@id='buttonSecondAction' and @text='Buy Premium']");
	public static By objExplorePremiumCTA = By.xpath("//*[@id='buttonSecondAction' and @text='Explore Premium']");
	
	public static By objPlayCTAonCarouselforContent(String pContentName) {
		return By.xpath("//*[@text='"+pContentName+"']//following::*//*[@id='outlinedButton' and @text='Play']");
	}
	
	public static By objBottomImg = By.id("bottomImage");
	public static By objBottomSheetCTA = By.xpath("//*[@id='buttonFirstAction']");
	public static By objHeadingBottomSheet = By.xpath("//*[@id='heading']");
	
	public static By objPlayerInfo = By.xpath("//*[@id='playerInfo']");
	public static By objAgreeAndWatchCTA = By.xpath("//*[@id='tvodAgreeAndStartButton']");
	
	public static By objZeePlexLogoBelowThePlayer = By.xpath("//*[@id='iconZeePlex']");
	
	public static By objMyRentalsOption = By.xpath("//*[@id='list_item'][@text='My Rentals']");
    
    public static By objMyRentalsTitle = By.xpath("//*[@id='title' and @text='My Rentals']");
    public static By objContentThumbnailInMyRentals = By.xpath("//*[@class='android.widget.ImageView' and ./parent::*[@id='cell_start_container']]");
    public static By objContentTitleInMyRentals = By.xpath("(//*[@id='cell_center_container']//*[@class='android.widget.TextView'])[1]");
    public static By objReleasedByInMyRentals = By.xpath("(//*[@id='cell_center_container']//*[@class='android.widget.TextView'])[2]");
    public static By objPackValidityInMyRentals = By.xpath("(//*[@id='cell_center_container']//*[@class='android.widget.TextView'])[4]");
    public static By objWatchNowCTAinMyRentals = By.xpath("//*[@id='button'][@text='Watch Now']");
    public static By objZEEPLEXLogo = By.xpath("//*[@id='zee_plex_logo']");
    
    public static By objTermsOfUse_PrivacyPolicy = By.xpath("//*[@id='privacyPolicyAndTAC']");
    
    public static By objAlreadyRented = By.xpath("//*[@id='titleText'] | //*[@text='Movie already rented']");
	public static By objWatchNowCTA = By.xpath("//*[@id='continueButton' and @text='Watch Now']");
	public static By objBuyPremiumCTA = By.xpath("//*[@id='continueButton' and @text='Buy Premium']");
	public static By objActivePlanMsg2 = By.xpath("//*[@id='subtitleText'] | //*[@text='You have already rented this ZEEPLEX movie']");
	public static By objComboOfferPlan = By.xpath("//*[@id='comboPlanTitleLabel' or @text='Radhe Combo']");
	public static By objOnlyRentMoviePlan = By.xpath("//*[@id='onlyRentSectionTitleLabel' or @text='Only Rent Movie']");
	public static By objRecommendedTag = By.xpath("//*[@id='comboTagLabel' and @text='Recommended']");
	public static By objBuyRadheComboCTA = By.xpath("//*[@text='Buy Radhe Combo'] | //*[@text='Buy Premium']");
	
	public static By objResumeCTAonPlayer = By.xpath("//*[@id='watchNow']");
    public static By objCombopackbadge = By.xpath("//*[@id='comboPackBenefitsBadge']");
    public static By objyearlyplanbadge = By.xpath("//*[@id='yearlyPlanBenefitsBadge']");
    public static By objComboPlantext = By.xpath("//*[@id='comboPackAllSectionsLabel']");
    public static By objAvailabilityTextOnPlayer = By.xpath("//*[@id='tvod_content_availability_info']");
    public static By objCTAInMyRentalsPage(String title) {
    	return  By.xpath("//*[@text='"+title+"']/following-sibling::*/child::*[@id='button']");
    }
    public static By objUpgradeBottomSheetCTA = By.xpath("//*[@id='buttonFirstAction'] | //*[@text='Buy TVOD TEST MOVIE 2']");

}
