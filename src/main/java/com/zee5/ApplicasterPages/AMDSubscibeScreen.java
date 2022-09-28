package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Vinay

public class AMDSubscibeScreen {
	
	public static By objSelectPackText  = By.xpath("//*[@text='Select Pack']");
	public static By objAdbanner = By.xpath("//*[@id='packSelectionCrousalImageView']");
	public static By objApplyPromoCodeTextbox = By.xpath("//*[@id='txtET_promocode_input']");
	public static By objCancelPromoCode = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_cancel_promo_code']");
	public static By objPriceAfterPromoCode = By.xpath("//*[@resource-id='com.graymatrix.did:id/old_price_tv']");
	public static By objApplybuttonNotHighlighted = By.xpath("//*[@resource-id='com.graymatrix.did:id/apply_promocode' and @selected='false']");
	public static By objApplyPromoCodeText = By.xpath("//*[@id='txtET_promocode_input'] and @text='Have a Promo Code?");

	public static By objDescriptionText = By.xpath("//*[@resource-id='com.graymatrix.did:id/deviceslayout']");
	public static By objClubTab  = By.xpath("//*[@text='Club']");
	public static By objSelectYourPreminumPackText = By.xpath("//*[@id='packsViewTextView']");
	public static By objPremiumTab  = By.xpath("//*[@text='Premium']");
	public static By obj30daysPack = By.xpath("//*[@id='holder_price' and ./*[./*[@text='INR 99 for 30 days']]]");
	public static By objRadioBtn = By.xpath("//*[@id='selectionImageSelector']");
	public static By obj180daysPack = By.xpath("//*[@id='holder_price' and ./*[./*[@text='INR 599 for 180 days']]]");
	public static By obj365daysPack = By.xpath("//*[@id='holder_price' and ./*[./*[@text='INR 699 for 365 days']]]");
	public static By objRSVODPack1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/decription_tv'])[1]");
	public static By objRSVODPack2 = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv']");
	
	//Account info
	public static By objSelectPack = By.xpath("//*[@id='pack_selection_title']");
	public static By objAllAccessPack = By.xpath("//*[@id='tv_selected_pack']");
	public static By objTvPackYear = By.xpath("//*[@id='tv_pack_year']");
	public static By objSelectedPackDesc = By.xpath("//*[@id='tv_selected_pack_description']");
	public static By objProceedButtonNothighlighted = By.xpath("//*[@resource-id='com.graymatrix.did:id/btnContinue_useraccountdetails' and @clickable='false']");
	public static By objORSeperator = By.xpath("//*[@class='android.widget.TextView' and @text='OR']");
	public static By objAccountInfoHeader = By.xpath("//*[@text='Account Info' and ./parent::*[@id='user_inputs_details_layout']]");
	public static By objEmailID = By.xpath("//*[@id='edit_email']");
	public static By objContinueWithText = By.xpath("//*[@text='Continue with:']");
	
	//Password screen 
	public static By objDrabBtn = By.xpath("//*[@id='dialog_divider']");
	public static By objPasswordTextField  = By.xpath("//*[@id='input_password']");
	public static By objProceedPWDScreen = By.xpath("//*[@id='subscription_plan_validate_password_button']");
	public static By objForgotPasswordPageHeader = By.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Forgot Password']");

	//Payment Screen
	public static By objAccountInfoText = By.xpath("//*[@id='account_info_title']");
	public static By objUserEmailID = By.xpath("//*[@id='selectedUserDetailsName']");
	public static By objProfileIcon = By.xpath("//*[@id='iconSmile']");
	public static By objPaymentOption = By.xpath("//*[@text='Payment Options']");
	public static By PaymentOptionsRadioBtn = By.xpath("//*[@class='android.widget.RadioButton']");
	public static By objPaymentReccuringMsg = By.xpath("//*[@id='payment_recurring_msg']");
	public static By objContinueBtnPaymentScreen = By.xpath("//*[@id='btnContinue_paymentdetails']");
	
	public static By objSelectedPackText = By.xpath("//*[@resource-id='com.graymatrix.did:id/pack_selection_title']");
	
	public static By objClub365daysPack = By.xpath("//*[@id='holder_price' and ./*[./*[@text='INR 365 for 365 days']]]");
	
	public static By objPackDescription = By.xpath("//*[@resource-id='com.graymatrix.did:id/decription_tv']");
	public static By objApplyBtn = By.xpath("//*[@id='apply']");
	
	public static By objInvalidPrepaidCodePopUp = By.xpath("//*[@id='prepaid_code_layout']");
	public static By objDoneBtn = By.xpath("//*[@id='btn_dialog_done']");
	
	public static By objDefaultSelectedPack = By.xpath("(//*[@id='selectionImageSelector' and @selected='true']//parent::*//parent::*//child::*//child::*)[1]");

	public static By objGetPremiumBtn = By.xpath("//*[@id='get_premium_button']");
	
	public static By objAccountInfoText1 = By.xpath("(//*[@text='Account Info'])[1]");
	
	public static By objRSVODPack3Desc = By.xpath("(//*[@resource-id='com.graymatrix.did:id/decription_tv'])[3]");
	public static By packdetailsinPaymentPage(String pack) {
			return By.xpath("//*[@text='"+pack+"']");
		}
	
	public static By objPlanPrice = By.xpath("//*[@text='Plan Price']//following-sibling::*//following-sibling::*");
	public static By objTotalPayable = By.id("//*[@text='Payable Amount']//following-sibling::*//following-sibling::*");
	
	public static By objRSVODselectedPackDesc = By.xpath("//*[@resource-id='com.graymatrix.did:id/selectionImageSelector' and @selected='true']/parent::*/parent::*/following-sibling::*/child::*");
	
	public static By objPaymentText = By.xpath("//*[@text='Payment Methods']");
	
	public static By objTermsandPrivacyLink = By.xpath("//*[@text='By proceeding you agree to our Terms of Use and Privacy Policy']");
	
	public static By objZEE5SubscriptionPage = By.xpath("//div[@class='subPackFlowContent']");
	
	public static By objNewSubscribePopup = By.xpath("//*[@id='plansHeader']");
	public static By objContinueOnSubscribePopup = By.xpath("//*[@id='continueButton']");
	public static By objMakePaymentScreen = By.xpath("//*[@class='android.widget.TextView' and @text='Make Payment']");
	
	//Select pack 
	public static By objSubscribeHeader = By.xpath("//*[@text='Subscribe'] | //*[@id='plansHeader']");

	public static By objHaveACodeCTA = By.xpath("//*[@id='codeLabel']");
	public static By objEnterACodeEditFiled = By.xpath("//*[@id='codeInputEditText']");
	public static By objApplyOnHaveACodescreen = By.xpath("//*[@id='action']");
	public static By objPlanPriceValue = By.xpath("//*[@id='planPrice']");
	public static By objAvaliablePack = By.xpath("(//*[@class='android.widget.LinearLayout'])[13]");
	
	public static By objApplyPromoCodeappliedText = By.xpath("//*[@resource-id='com.graymatrix.did:id/textinput_helper_text'] | //*[@id='codeInfo']");
	public static By objInvalidPromoCodeText = By.xpath("//*[@id='validationErrorLabel'] | //*[@resource-id='com.graymatrix.did:id/textinput_error']");
	public static By objContinueBtn = By.xpath("//*[@id='continueButton'] | //*[@id='btnContinue_PackSelection']");
	public static By objSubscribePageBackButton = By.xpath("//*[@id='backIcon'] | //*[@resource-id='com.graymatrix.did:id/icon_back'] | //*[@id='planSelectionBackButton']");
	public static By objGoogleIcon = By.xpath("//*[@id='googleButton'] | //*[@id='google_login']");
	public static By objFacebookIcon = By.xpath("//*[@id='facebookButton'] | //*[@id='fb_login']");
	public static By objTwitterIcon = By.xpath("//*[@id='twitterButton'] | //*[@id='twitter_login']");
	public static By objEnterPassword = By.xpath("//*[@id='edit_email'] | //*[@id='header_verify_pwd']");
	public static By objPasswordErrorMessage = By.xpath("//*[@id='txtMessage'] | //*[@resource-id='com.graymatrix.did:id/textinput_error']");
	public static By objPasswordHidden = By.xpath("//*[@id='edit_email' and @password='true'] | //*[@resource-id='com.graymatrix.did:id/input_password' and @password='true']");
	public static By objPasswordDisplayed = By.xpath("//*[@id='edit_email' and @password='false'] | //*[@resource-id='com.graymatrix.did:id/input_password' and @password='false']");
	public static By objShowIcon = By.xpath("//*[@id='passwordIcon'] | //*[@id='text_input_end_icon']");
	public static By objForgotPassword = By.xpath("//*[@id='forgotPassword'] | //*[@id='option_forgot_password']");
	public static By objProceedBtn = By.xpath("//*[@id='btnFPSendResetLink'] | //*[@id='btnContinue_useraccountdetails']");
	public static By objGetOTP = By.xpath("//*[@id='getOTPButton'] | //*[@resource-id='com.graymatrix.did:id/btn_get_otp']");
	
	public static By objAccountInfoScreen = By.xpath("//*[@id='heading' and @text='Account Info']");
	public static By objPlanName = By.xpath("//*[@id='planName']");
	public static By objChangeCodebutton = By.xpath("//*[@id='codeChangeLabel']");
	public static By objGetPremiumCTA = By.xpath("//*[@id='subscribeButton']");
	public static By objVerifyOTPScreenProceed = By.xpath("//*[@id='continueButton'] | //*[@resource-id='com.graymatrix.did:id/btn_verify_proceed']");
	
	public static By objRoundOffValue = By.xpath("//*[@text='Round Off' and @class='android.widget.TextView']//following-sibling::*//following-sibling::*");
	public static By objexplorePremiumCTA = By.xpath("//*[@id='continueButton']");
	
	public static By objPremiumBadge = By.xpath("//*[@id='premiumBadge' and @text='P']");
	public static By objStepperLabel = By.xpath("//*[@id='stepperLabel']");
	public static By objComboOfferLabel = By.xpath("//*[@id='unlockLabel']");
	
	public static By objPlanHeader(String label) {
		return By.xpath("//*[@id='plansHeader' and @text='"+label+"']");
	}
	
	public static By objLinkedlabel(String label) {
		return By.xpath("//*[@id='linkedLabel' and @text='"+label+"']");
	}
	
	public static By objHelpIcons = By.id("helpIcon");
	public static By objPlanSelectionBadge = By.id("planSelectionBadge");
	
	public static By objOfferPlanName = By.xpath("(//*[@text='Make Payment']//following::*[@class='android.widget.TextView'])[1]");
	
	public static By objOfferPlanPrice = By.xpath("((//*[@text='Make Payment']//following::*[4])//child::*[3])[1]"); 
	
	public static By objVerifyOTPScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_verify_mobile_otp_account_header'] | //*[@id='heading']");
	
	public static By objPremiumPlansInSubscriptionPage = By.xpath("//*[@id='subscriptionPlanItem']");
	
	public static By objUserEmailIdOrPhnNoInPaymentPage = By.xpath("//*[@text='Logged in']/following-sibling::*");
	
	public static By objPriceValueOnPaymentScreen = By.xpath("(//*[@class='android.widget.LinearLayout']//*[@class='android.widget.TextView'][3])[1] | //*[@class='android.widget.LinearLayout' and ./*[@text='50% Off on 999']]//*[@class='android.widget.TextView'][3]");
	public static By objPlanDescription = By.xpath("//*[@id='planDescription']");
	
	public static By objPlanDescrptionOfPremiumPlans(int i) {
		return By.xpath("(//*[@id='subscriptionPlanItem'])["+i+"]/child::*[@id='planDescription']");
	}	
	public static By objOfferBadgeOnPremiumPlans(int i) {
		return By.xpath("(//*[@id='subscriptionPlanItem'])["+i+"]/child::*[@id='limitedPeriodBadge']");
	}
	 
	public static By objconsumptionBuyPlanButton = By.xpath("(//*[@id='subscribeButton' and @text='Buy Plan'])[1]");
	
	public static By objconsumptionBuyPlanButton1 = By.xpath("((//*[@id='subscribeButton' and @text='Buy Plan'])[1]) | (//*[@text='BUY PLAN'])[1]");
	
	public static By objContinueButton  = By.xpath("//*[@id='continueButton']");
	
	public static By objBuyPlanButton  = By.xpath("//*[@id='home_toolbar_buy_plan']");
	
	public static By obj299Pack = By.xpath("(//*[@id='planBackground'])[2]");
	public static By obj299PlanPrice = By.xpath("(//*[@id='planPrice'])[2]");
	public static By objminusOnVerifyOTP = By.xpath("//*[@id='pillView']");
	
	public static By objSubsciptionScreen = By.id("plansContainer");
	
	public static By objHaveAPromocode = By.xpath("//*[@text='Have a Code?']");
	
	public static By objApply = By.xpath("//*[@text='Apply']");
	
	public static By objPlanNameOfPlan(int i) {
		return By.xpath("(//*[@id='planName'])["+i+"]");
	}
	
	public static By objPlanDescriptionOfPlan(int i) {
		return By.xpath("(//*[@id='planDescription'])["+i+"]");
	}
	
	public static By objEntercardnumber = By.xpath("//*[@text='Enter Card Number']");
	public static By objAddCreditDebitCard = By.xpath("//*[@text='Add a Credit/Debit Card']");
	public static By objUPI = By.xpath("(//*[contains(text(),'UPI')])[2]");
	public static By objpaytm = By.xpath("//*[@text='Link Wallet']");
	public static By objAmazonpaychk = By.xpath("((//*[@text='Amazon Pay'])//parent::*//parent::*//parent::*//parent::*//parent::*)[7]//child::*");
	public static By objPhonepeChk = By.xpath("((//*[@text='PhonePe'])//parent::*//parent::*//parent::*//parent::*//parent::*)[7]//child::*");	
	
	public static By objBank(String BankName) {
		return By.xpath("//*[@text='"+BankName+"']");
	}
	
	public static By objAirtelPaymentsbank = By.xpath("//*[@text='Airtel Payments Bank']");
	public static By objCityUnion = By.xpath("//*[@text='CityUnion']");
	public static By objDhanalaxmiBank = By.xpath("//*[@text='Dhanalaxmi Bank']");
	
	public static By objFederalBank = By.xpath("//*[@text='Federal Bank']");
	public static By objIDFCFIRSTBank = By.xpath("//*[@text='IDFC FIRST Bank']");
	public static By objIndianBank = By.xpath("//*[@text='Indian Bank ']");
	public static By objIndianOverseasBank = By.xpath("//*[@text='Indian Overseas Bank']");
	public static By objIndusIndBank = By.xpath("//*[@text='IndusInd Bank']");
	public static By objIndustrialDevelopmentBankofIndia = By.xpath("//*[@text='Industrial Development Bank of India']");
	public static By objJammuandKashmirBank = By.xpath("//*[@text='Jammu and Kashmir Bank']");
	public static By objKarurVysya = By.xpath("//*[@text='Karur Vysya ']");
	public static By objKotakBank = By.xpath("//*[@text='Kotak Bank']");
	public static By objPaytmPaymentsBankLimited = By.xpath("//*[@text='Paytm Payments Bank Limited']");
	public static By objPunjabNationalBank = By.xpath("//*[@text='Punjab National Bank']");
	public static By objPunjabandSindBank = By.xpath("//*[@text='Punjab and Sind Bank']");
	public static By objShamraoVithalCoopBank = By.xpath("//*[@text='Shamrao Vithal Coop Bank']");
	public static By objSouthIndianBank = By.xpath("//*[@text='South Indian Bank']");
	public static By objYESBank = By.xpath("//*[@text='Yes Bank']");
	
	public static By objPayNow = By.xpath("//*[@text='Pay Now']");
	
	public static By objPaymentfailed = By.xpath("//*[@text='Payment failed']");
	
	public static By objAirtelPaymentScreen = By.xpath("//*[contains(@text,'airtel-bank-logo')]");
	public static By objCancelTransaction = By.xpath("//*[@text='Cancel Transaction?']");
	public static By objOk = By.xpath("//*[@text='OK']");
	
	public static By objCityUnionBank = By.xpath("//*[@text='onlinecub1']");
	
	public static By objPhonePeScreen = By.xpath("//*[@id='tv_action']");

    public static By objAirtelBankScreen = By.xpath("//*[@text='airtel-bank-logo.23710c66']");
    public static By objCityUnionBankScreen = By.xpath("//*[@text='Login']");
    public static By objDhanalaxmiBankScreen = By.xpath("//*[@text='Payment failed']");
    public static By objFederalBankScreen = By.xpath("//*[@text='federal bank']");
    public static By objIDFCFirstBankScreen = By.xpath("//*[@text='idfc-web-logo']");
    public static By objIndianBankScreen = By.xpath("//*[@text='Bank_logo']");
    public static By objIndianOverseasBankScreen = By.xpath("//*[@text='Individual Login']");
    public static By objIndusIndBankScreen = By.xpath("//*[@text='Welcome To The Online Payment Page Of IndusInd Bank']");

	public static By objNetbanking = By.xpath("(//*[@text='Net Banking'])");
	
	public static By objYes = By.xpath("//*[@id='button1']");

    public static By objIndustrialDevelopmentBankScreen = By.xpath("//*[@id='LoginHDisplay.Header3']");
    public static By objJammuAndKashmirBankScreen = By.xpath("//*[@id='STU_VALIDATE_CREDENTIALS']");
    public static By objKarurVysyaBankScreen = By.xpath("//*[@text='Welcome to KVB NetBanking']");
    public static By objKotakBankScreen = By.xpath("//*[@text='Net / Mobile Banking']");
    public static By objPaytmPaymentsBankScreen = By.xpath("//*[@text='BankLogo']");
    
    public static By objPunjabNationalBankScreen = By.xpath("//*[@id='UserId_Text']");
    public static By objPunjabAndSindBankScreen = By.xpath("//*[@text='Pay Now']");
    public static By objShamraoVithalBankScreen = By.xpath("//*[@id='ctl00_cphPG_uctLogin_btnContinue']");
    public static By objSouthIndianBankScreen = By.xpath("//*[@id='VALIDATE_CREDENTIALS']");
    public static By objYesBankScreen = By.xpath("//*[@text='YES BANK NetBanking']");
    public static By objUpgrade=By.xpath("//*[contains(text(),'Upgrade') and @id='continueButton']");
	
}
