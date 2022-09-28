package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWASubscriptionPages {

	public static By objApply = By.xpath("//div[text()='APPLY']");

	// Selected Subscription Plan Type
	public static By objSelectedSubscriptionPlanType = By.xpath(
			"//*[contains(@class, 'planDescription') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class, 'noSelect subscriptionPlanCard active')]]");

	// Selected Pack Text
	public static By objSelectedPackText = By
			.xpath("//div[contains(@class, 'selectPackDetails')]/p[contains(text(), 'Selected Pack')]");

	// Proceed Btn Disabled
	public static By objProceedBtnNotHighlighted = By.xpath(
			"//button[contains(@class, 'noSelect buttonGradient') and @disabled]/span[contains(text(),'PROCEED')]");

	// Enter Email ID OR Mobile Number Error message
	public static By objEnterEmailIDErrorMsg = By.xpath("//div[contains(text(), 'Enter Email ID OR Mobile Number')]");

	// Password Field Visible Text
	public static By objPasswordFieldVisible = By
			.xpath("//input[contains(@name, 'inputPassword') and contains(@type, 'text')]");

	// Password Eye Icon disabled
	public static By objPasswordEyeIconDisabled = By
			.xpath("/span[contains(@class, 'noSelect eyeIcon iconNavi-ic_visibility')])");

	// Password Eye Icon Enabled
	public static By objPasswordEyeIconEnabled = By
			.xpath("/span[contains(@class, 'noSelect eyeIcon iconNavi-ic_visibility openEye')]");

	// Password Minimum characters error message
	public static By objPasswordMinimumCharErrorMsg = By
			.xpath("//span[contains(@class, 'error') and contains(text(), 'Minimum 6 characters')]");

	// Password incorrect error message
	public static By objPasswordWrongErrorMsg = By.xpath(
			"//span[contains(@class, 'error') and contains(text(), 'The email address and password combination was wrong during login')]");

	// Forgot Password Link
	public static By objForgotPasswordLink = By
			.xpath("//span[contains(@class, 'forgotPassword') and contains(text(), 'Forgot Password')]");

	public static By objProceedBtn = By.xpath("//div[@class='popupBtn']");

	// Payment Page

	// Account Info Text
	public static By objAccountInfoText = By
			.xpath("//div[contains(@class,'accountInfoPlanCard')]//p[@class='autoPopulatedDetail']");

	// Credit Card radio Btn
	public static By objCreditCardRadioBtn = By.xpath(
			"//label[contains(@for, 'Credit')]/span[contains(text(),'Credit Card') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class,'radioStyle')]]");

	// Credit Card radio Btn
	public static By objDebitCardRadioBtn = By.xpath(
			"//label[contains(@for, 'Debit')]/span[contains(text(),'Debit Card') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class,'radioStyle')]]");

	// PayTM radio Btn
	public static By objPayTMRadioBtn = By.xpath(
			"//label[contains(@for, 'PayTM')]/span[contains(@class,'paytmImg') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class,'radioStyle')]]/img[contains(@alt,'PayTM')]");

	// Recurrence Message
	public static By objRecurrenceMessage = By.xpath(
			"//div[contains(@class,'recurrenceNote')]/p[contains(text(), 'You will be charged INR 99 every billing cycle until you cancel.')]");

	// Continue Btn Disabled
	public static By objContinueBtnDisabled = By.xpath(
			"//div[contains(@class,'continueButton')]//button[contains(@class, 'noSelect buttonGradient null') and contains(@disabled,'')]");

	// Continue Btn Enabled
	public static By objContinueBtnEnabled = By.xpath(
			"//div[contains(@class,'continueButton')]//button[contains(@class, 'noSelect buttonGradient null') and contains(@enabled, '')]");

	// PayTM Page

	// PayTm Wallet Radio Btn
	public static By objPaytmWalletOption = By.xpath("//img[contains(@alt, 'Paytm')]");

	// Enter Password Popup
	public static By objGetPremiumCTAInPlater = By.xpath("//div[contains(@class,'teaser-grid2')]//button");

	public static By objSubscribepopup = By
			.xpath("//div[contains(@class,'ReactModal__Content ReactModal__Content--after-open popupModal')]");

	public static By objGetPremiumPopupPlan = By
			.xpath("//div[contains(@class,'upgradeCard active')]//p[contains(@class,'planDescription')]");

	public static By objGetPremiumPopipProceed = By.xpath("//div[contains(text(),'PROCEED')]");

	public static By objadhocPopup = By.xpath("//*[@class='__ADORIC__1 __ADORIC__  ']");

	public static By objadhocPopupRegestrationScreen = By.xpath("//h1[contains(text(),'Sign up for FREE')]");

	public static By objadhocPopupbn = By.xpath("//*[@nodeName='H1']");

	public static By objadhocPopupCloseBtn = By.xpath(".//div[@title='close lightbox']");
	public static By objadhocPopupArea = By
			.xpath(".//div[@class='element-shape closeLightboxButton editing adoric_element']");
	public static By objadhocPopupSignUpBtn = By.xpath("//button[contains(text(),'Button')]");
	public static By adoricCloseBtn = By.xpath("//div[contains(@class,'closeLightboxButton')]");

// Popup Close Button
	public static By objPopupCloseButton = By.xpath("//div[contains(@class,'noSelect closePupup')]");

	public static By objSearchResultPremiumContent = By.xpath("//div[@class='cardPremiumContent']/parent::*");

	public static By objPopup99Plan = By
			.xpath("//p[contains(@class, 'planDescription')]//span[contains(@class, 'currency')]/span[text()='99']");

	// Get Premium popup close button
	public static By objGetPremiumPopupCloseButton = By.xpath(
			"//div[@class='noSelect closePupup iconInitialLoad-ic_close' and (./preceding-sibling: | ./following-sibling::*)[contains(@class,'popupContent upgradePopupContent')]/h2[contains(text(),'Get premium')]]");

//	=====================================================================================================

	public static By objSybscribeNowOnPlayerBtn = By.xpath("//SPAN[@text='Subscribe Now']");

	public static By objPackTypes = By.xpath("(//div[@class='categoryContainer']//child::*[@class='noSelect'])");

	public static By objPaymentFailure = By.xpath("h2[.='Payment Failure']");

	public static By objPaymentFailureCloseBtn = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objPackTitle = By.xpath("(//div[@class='planDescription'])");

//	MANASA
	public static By objPackType(int i) {
		return By.xpath("(//div[@class='categoryContainer']//child::*[@class='noSelect'])[" + i + "]");
	}

	public static By objSubscribeBtnTopHeader = By
			.xpath("//*[contains(@class,'subscribeBtn noSelect')]//span[contains(text(), 'Subscribe')]");

	public static By objPackCategoryTabSelected = By
			.xpath("//*[contains(@class,'active')]/*[contains(@class, 'noSelect')]");

	public static By objProceedBtnDisabled = By
			.xpath("//*[contains(@class, 'popupBtn disableButton') and contains(text(), 'PROCEED')]");

	public static By objPasswordFieldHidden = By
			.xpath("//*[contains(@name, 'inputPassword') and contains(@type, 'password')]");

	public static By objCreditAndDebitCardBtn = By.xpath(
			"//*[contains(@class,'linearLayout sidebarItem_object')]//following-sibling::*//article[contains(text(),'Credit / Debit Card')]");

//	public static By objPaymentPageProceedBtn = By
//			.xpath("//button[contains(@class, 'noSelect buttonGradient ')]/span[contains(text(),'PROCEED')]");
	public static By objEnterCreditAndDebitCardDetails = By
			.xpath("//*[contains(@class,'editText') and contains(@placeholder,'Enter card number here')]");
	public static By objJusPayIframe = By.xpath("//iframe[@id='juspay_iframe']");
	public static By objLinkPaytm = By.xpath("//article[contains(text(), 'Link PAYTM Wallet')]");
	public static By objNetbanking = By.xpath("//article[contains(text(),'Netbanking')]");
	public static By objAmazonPay = By.xpath("//article[contains(text(),'Amazon Pay')]");
	public static By objMobikwik = By.xpath("//article[contains(text(),'Mobikwik')]");
	public static By objBanks = By.xpath("(//div[@class='horizontalScrollView ']//div)//div//child::*[2]//article");
	public static By objCardNumber = By.xpath("//article[contains(text(),'Card Number')]");
	public static By objCVV = By.xpath("//article[contains(text(),'CVV')]");
	public static By objExpiry = By.xpath("//article[contains(text(),'Expiry')]");
	public static By objProceedToPay = By.xpath("(//article[contains(text(), 'Proceed to pay')])[6]");

	public static By objLoginLinkInPlayer = By.xpath("//*[@class='login-link']");
	public static By objPasswordField = By.xpath("//*[@type='password']");

	public static By objCreditDebitProceedToPay = By.xpath("//article[contains(text(),'Proceed to pay')]");

	public static By objarrowbtn = By.xpath("//div[@class='linearLayout sidebarItem_object ']");

	public static By objLoginSectionInPopup = By.xpath("//div[contains(@class,'loginContainer')]");

	public static By objLoginButtonInPopup = By.xpath("//div[contains(@class,'popupBtn accentBtn')]");

	public static By objMobileCreditDebitCardRecurrenceMessage = By
			.xpath("//*[@text='You will be charged every billing cycle until you cancel']");
	public static By objMobileAddCardText = By.xpath("//*[@text='Add Card']");
	public static By objMobileCardNumberText = By.xpath("//*[@text='Card Number']");
	public static By objMobileCardNumberEditBox = By.xpath("//*[@resource-id='987654321']");
	public static By objMobileExpiryText = By.xpath("//*[@text='Expiry']");
	public static By objMobileCVVText = By.xpath("//*[@text='CVV']");
	public static By objMobilePaytmOption = By.xpath("//*[@text='Paytm']");
	public static By objMobilePaytmNumberField = By.xpath("//*[@class='android.widget.EditText']");
	public static By objMobilePaytmSendOTP = By.xpath("//*[@text='SEND OTP']");
	public static By objPackType = By.xpath("//div[@class='categoryContainer']//child::*[@class='noSelect']");

	public static By objMobilePayTMRecurrenceMessage = By
			.xpath("//*[@text='You will be charged every billing cycle until you cancel']");

	// Get Premium Popup Title
	public static By objGetPremiumPopupTitle = By
			.xpath("//h2[contains(@class,'popupTitle bigTitle')]//p[text()='Subscribe']");

	//Login Required Popup
	public static By objLoginRequiredPopupTitle = By
			.xpath("//h2[contains(@class,'popupTitle bigTitle') and contains(text(),'Login Required')]");

	// Get Subscribe Pop Up Title
	public static By objSubscribePopupTitle = By.xpath("//div[contains(@class,'popup')]//*[text()='Subscribe']");

	public static By objHaveACodeCloseBtn = By
			.xpath("//div[contains(@class,'applyPromo')]//following-sibling::span[contains(@class,'close')]");

	public static By objClubPack = By.xpath("//span[contains(text(),'Club')]");

	public static By objEnterCardNumber = By.xpath("//input[@placeholder='Enter card number here']");

	public static By objEnterExpiry = By.xpath("//input[@placeholder='MM / YY']");

	public static By objEnterCVV = By.xpath("(//input[@placeholder='CVV'])");

	public static By objCancelTransaction = By.xpath("//*[@class='Btn cancel']");

	public static By objExpandLess = By.xpath("//*[@class='iconOther-ic_expand_less']");

	public static By objGiftCardNumber = By.xpath("//*[@name='giftCardNumber']");

	public static By objPin = By.xpath("//*[@name='pinNumber']");

	public static By objPay = By.xpath("//span[contains(text(),'PAY')]");

	public static By objZeeLink = By.xpath("(//a[contains(text(),'www.zee5.com')])[1]");

	public static By objCreditDebitClose = By.xpath("(//*[contains(@src,'ic_search_cancel')])[2]");

	public static By objPremiumPack = By.xpath("//span[contains(text(),'Premium')]");
	public static By objActiveStatusTransaction = By
			.xpath("//*[text()='Status']//following-sibling::*[text()='Active']");

	public static By objUpgradePopupTitle = By.xpath("//div[contains(@class,'popupContent upgradePopupContent')]");
	public static By objMobileCreditDebitCardOption = By.xpath("//*[text()='Credit / Debit Card']");
	public static By objMobileWalletsOption = By.xpath("//*[text()='Wallets']");
	public static By objPaymentPageProceedBtn = By.xpath("//button[contains(@class,'noSelect buttonGradient')]");

	public static By objcardDetails = By
			.xpath(".//*[@placeholder='Enter card number here']//following-sibling::*[@class='imageView']");

	public static By objMobileProceedToPayButton = By.xpath("(//article[contains(text(),'Proceed to pay')])[2]");

	public static By objEnterCode = By.xpath("//*[@placeholder='Enter code']");

	public static By objPasswordContinueButton = By
			.xpath("//div[@class='buttonContainer']//span[contains(text(), 'Continue')]");

	public static By objZEE5SubscriptionPage = By.xpath("//div[@class='subPackFlowContent']");

	public static By objSelectPackHighlighted = By
			.xpath("//*[@class='subscriPack ']//div[contains(@class, 'packDetails active')]");

	public static By objAccountInfoHighlighted = By.xpath("//div[contains(text(),'Account Info')]");

	public static By objDefaultSelectedPack = By
			.xpath("//div[contains(@class, 'packDetails active')]//*[contains(@class, 'planPrice')]");

	public static By objPopup299Plan = By
			.xpath("//div[contains(@class, 'packDetails')]//div[contains(@class, 'planPrice')]//span[text()='299']");

	public static By objHaveACodetoenter = By.xpath("//*[contains(@placeholder,'Enter code')]");

	public static By objAppliedSuccessfullyMessage = By
			.xpath("//div[contains(@class,'applyPromo applyPromoSuccess successIcon')]");

	public static By objPopupProceedBtn = By.xpath("//div[@class='buttonContainer']");

	public static By objEmailIDTextField = By.xpath("//*[@id='textField' and @name='userName']");

	public static By objWallets = By
			.xpath("//*[contains(@class, 'linearLayout PaymentOptionViewNotList')]//*[contains(text(), 'Wallets')]");

	public static By objPackDescription = By.xpath("//*[@class='subscriTopContWrapper']");

	public static By objSelectedSubscriptionPlanDuration = By.xpath(
			"//*[contains(@class, 'packDetails active')]/*[contains(@class, 'planDuration')]/*[contains(@class,'heading')]");

	public static By objApplyBtn = By
			.xpath("//*[contains(@class, 'promoCodePopupApply') and contains(text(), 'Apply')]");

	public static By objAppliedCodeFailureMessage = By.xpath("//*[@class='applyPromo applyPromoFailure']");

	public static By objremovebtn = By
			.xpath("//*[contains(@class, 'promoCodePopupApply') and contains(text(), 'Remove')]");

	public static By objProceedButtonInPassword = By.xpath("//*[text()='Continue']");
	public static By objProceedBtnInSubscriptionPage = By.xpath("//*[text()='Continue']");

	public static By objContinueBtnn = By.xpath("//*[@type='button']//span[text()='Continue']");

	public static By objDiscountAppliedMessage = By.xpath("//div[contains(text(),'DISCOUNT APPLIED')]");

	public static By objHaveACodePlaceHolder = By.xpath("//*[contains(@placeholder,'Enter code')]");

	public static By objHaveACodeChangeBtn = By.xpath(
			"//div[contains(@class,'applyPromoMobile')]//following-sibling::div[contains(@class,'changeMobileButton')]");

	public static By objSelectedSubscriptionPlanAmount = By
			.xpath("//*[contains(@class, 'packDetails active')]//*[contains(@class, 'planDuration')]");

	public static By objPasswordPopupInSubscriptionPage = By.xpath("//div[contains(@class, 'drowerPopupContent')]");

	public static By objPremiumText = By.xpath("//span[contains(text(), 'To Watch this Premium Content')]");

	public static By objGetPremiumButton = By.xpath("//*[contains(@class, 'subscribe-button subscribemsg_en')]");

	public static By objExistUserText = By.xpath("//*[contains(text(), 'Already have an account?')]");

	public static By objLoginLink = By.xpath("//*[contains(@class, 'login-link')]");

	public static By objTrailerTextAtConsumptionPage = By.xpath("//*[contains(@class, 'trailerInfoContainer')]");

	public static By objLoginPage = By.xpath("//*[contains(@class, 'formHeader loginHeaderIn')]");

	public static By objMovieLink = By.xpath("//*[text()='Blockbuster Movies']");

	public static By objMovieTrayName = By.xpath("//*[@class='trayHeader']//h2");

	public static By objMovieTray1 = By
			.xpath("(//*[@class='slick-slider movieTray slick-initialized']//*[@class='slick-slide slick-active'])[1]");

	public static By objMovieTray2 = By
			.xpath("(//*[@class='slick-slider movieTray slick-initialized']//*[@class='slick-slide slick-active'])[4]");

	public static By objPaymentHighlighted = By.xpath("//h2[@class='stepTitle' and contains(text(),'Make Payment')]");

	public static By objTeaserGetPremiumButton = By.xpath("//button[contains(@class, 'subscribe-teaser-button ')]");

	public static By objPackAmount1 = By.xpath("(//span[@class='price'])[1]");

	public static By objPackAmount = By.xpath("(//span[@class='price'])");

	public static By objPackAmount2 = By.xpath("(//span[@class='price'])[2]");

	public static By objHaveACode = By
			.xpath("//*[contains(@class,'haveCodeText') and contains(text(),'Have a Code?')]");

	public static By objAccountInfoDetails = By.xpath("//div[contains(@class, 'userDetails')]");

	public static By objSelectedPackDescription = By.xpath("//div[contains(@class, 'packDetails pack')]");

	public static By objSelectedPackDuration = By.xpath("//div[contains(@class, 'yearDetails')]");

	public static By objSelectedPackName = By.xpath("//div[contains(@class, 'packName')]");

	public static By objCancelBtn = By.xpath("//span[contains(@class, 'promoCodePopupApply')]");

	public static By objPaytmWallet = By
			.xpath("//div[contains(@class,'linearLayout sidebarItem_object')]//*[contains(text(), 'Paytm')]");

	public static By objLoginButtonFromSubscriptionPage = By
			.xpath("//*[contains(@class, 'loginBtn noSelect subscriptionLoginType')]");

	// Account Info tab Title
	public static By objAccountInfoTitle = By.xpath("//h2[contains(text(),'Account Info')]");
	// Verify OTP screen title
	public static By objVerifyOTPTitle = By
			.xpath("//*[@class='verifyOTPBox mobileLoginPopup']//*[contains(text(), 'Verify OTP')]");
	// OTP text field 1
	public static By objOTPTextField1 = By.xpath("//input[@name='otp1']");
	// OTP text field 2
	public static By objOTPTextField2 = By.xpath("//input[@name='otp2']");
	// OTP text field 3
	public static By objOTPTextField3 = By.xpath("//input[@name='otp3']");
	// OTP text field 4
	public static By objOTPTextField4 = By.xpath("//input[@name='otp4']");
	// Verify Button
	public static By objVerifyButton = By.xpath("(//span[contains(text(), 'Verify')])[1]");
	// Error Message
	public static By objInvalidOTPErrorMessage = By.xpath(
			"//*[contains(@class, 'errorContainer') and contains(text(),'Either OTP is not valid or has expired')]");

	// Plan Duration in My transactions page
	public static By objPlanPurchaseDate = By.xpath("//*[@class='transactionCard']//*[@class='date']");

	// Plan Duration in My transactions page
	public static By objPlanPurchaseDate(int i) {
		return By.xpath("(//*[@class='transactionCard']//*[@class='date'])[" + i + "]");
	}

	// Plan Duration in My Transactions page
	public static By objPlanDuration = By.xpath(
			"//*[@class='transactionCard']//*[@class='value' and (./preceding-sibling::* | ./following-sibling::*)[contains(text(),'Duration')]]");

	// Plan Duration in My Transactions page
	public static By objPlanDuration(int i) {
		return By.xpath(
				"(//*[@class='transactionCard']//*[@class='value' and (./preceding-sibling::* | ./following-sibling::*)[contains(text(),'Duration')]])["
						+ i + "]");
	}

	// Zee Logo
	public static By objZeeLogo = By.xpath("//*[@title='ZEE5 Logo']");

	// One Time Radhe ZEEPLEX Rental Text

	public static By objOneTimeRadheZEEPLEXRentalText = By.xpath("//div[@class='title']");

	// Description Combo Radhe Year Zee5Premium
	public static By objDescriptionComboRadheYearZee5Premium = By.xpath("//div[@class='description']");

	// Cancle Button
	public static By objCancleButton = By.xpath("//div[@class='drowerCloseIcon iconInitialLoad-ic_close']");

	// Make Payment
	public static By objMakePayment = By.xpath("//h2[@class='stepTitle']");

	// Continue with 499
	public static By objContinueWith499 = By.xpath("//span[contains(text(),'Continue with ₹499')]");

	// Login Button
	public static By objLoginButton = By.xpath("//a[@class='loginBtn noSelect subscriptionLoginType']");

	// Watch Add-Free
	public static By objWatchAddFree = By.xpath("(//div[@class='item'])[5]");

	// Watch Before Tv
	public static By objWatchBeforeTv = By
			.xpath("(//div[@class='subscriTopContWrapper']//div[@class='item']//span)[4]");

	// 200+Web Series
	public static By obj200PlushWebSeries = By
			.xpath("(//div[@class='subscriTopContWrapper']//div[@class='item']//span)[3]");

	// Blockbuster Movie
	public static By objBlockbusterMovie = By
			.xpath("(//div[@class='subscriTopContWrapper']//div[@class='item']//span)[2]");

	// Combo:Radhe + Year Zee5 Premium
	public static By objComboRadheYearZee5Premium = By
			.xpath("(//div[@class='subscriTopContWrapper']//div[@class='item']//span)[1]");

	public static By objTextForComboOffer = By.xpath("//h1[contains(text(),'Radhe Combo Offer')]");

	public static By objsavethiscard = By.xpath(
			"//*[contains(@class,'linearLayout')]//following-sibling::*//article[contains(text(),'Save this card for faster payments')]");
	public static By objamazonpaypage = By.xpath("//div[@class='a-box-inner a-padding-extra-large']");
	public static By objpaytmentermobileno = By.xpath("//input[@placeholder='Please Enter a Number']");
	public static By objpaytmlink = By.xpath("//article[contains(text(),'Paytm')]");

	public static By objAmazonPayProceedToPay = By.xpath(
			"//article[contains(text(),'Amazon Pay')]//ancestor::*[@class='linearLayout PaymentOptionViewNotListNestedChild']//child::*[text()='Proceed to pay']");

	public static By objMobikwikProceedToPay = By.xpath(
			"//article[contains(text(),'Mobikwik')]//ancestor::*[@class='linearLayout PaymentOptionViewNotListNestedChild']//child::*[text()='Proceed to pay']");

	// Watch Now CTA
	public static By objWatchNowCTA = By.xpath("//span[text()='Watch Now']");

	// explore Premium
	public static By objexplorePremium = By.xpath("//span[text()='Explore Premium']");
	// You are currently on ₹499 Premium 1 Year plan
	public static By objYouAreCurrentlyOnPremiumYearPlan = By
			.xpath("//span[text()='You are currently on ₹499 Premium 1 Year plan.']");
	// Upgrade Now Message
	public static By objUpgradeNowMessage = By.xpath("(//span[.='Upgrade'])[2]");
	// Your are currently on plan<Plan Name >. CTA's Message
	public static By objYourAreCurrentlyOnPlanPlanNameCTAsMessage = By.xpath("//li[@class='subtitleItem']");
	// Right Tick As Per VD
	public static By objRightTickAsPerVD = By.xpath("//img[@alt='combo offer popup icon']");
	// Upgrade To Radhe Combo Pack By Just Paying
	public static By objUpgradeToRadheComboPackByJustPaying = By.xpath("//span[@class='title']");
	// differential amount calculated.
	public static By objDifferentialAmountCalculated = By.xpath("(//span[text()='309'])[1]");

	public static By objComboOfferPageHeader = By.xpath("//*[@class='titleSubTitleComp']");// added 5/5/2021
	public static By objExplorePremiumButton = By.xpath("//*[text()='Explore Premium']");// added 6/5/2021
	public static By objBuyCombobutton = By.xpath("//*[@class='buttonContainer']");// added 4/5/2021
	public static By objRentMoviebutton = By.xpath("//*[text()='Rent Movie']");// added 4/5/2021
	public static By objHaveitAllPopDialog = By.xpath("//*[text()='You have it all']");// added 5/5/2021
	public static By objYouHaveAlreadyRentedThisZEEPLEXMovieDialog = By
			.xpath("//*[@class='subtitleItem']//span[text()='You have already rented this ZEEPLEX movie']");// added
																											// 5/5/2021

	public static By objZeeplexPopupCloseButton = By.xpath("//*[@class='drowerCloseIcon iconInitialLoad-ic_close']");// added
																														// 5/5/2021
	public static By obj299PackUpgradePopup = By.xpath(
			"(//div[@class='titleAndSubtitle'])//span[text()='Upgrade to Combo Offer by just paying the difference']");// added
																														// 7/5/2021
	public static By obj299PackUpgradeCTA = By.xpath("(//div[@class='buttonContainer '])//span[text()='Upgrade']");// added
																													// 7/5/2021
	public static By objZeeplexAndTickIcon = By.xpath("//*[@class='icon']//img[@alt='combo offer popup icon']");// added
																												// 7/5/2021
	public static By objComboOfferPageUpgrade299PackHeader = By
			.xpath("//li[@class='subtitleItem']//span[text()='You are currently on ₹299 Premium 3 Months plan.']");// added
																													// 7/5/2021
	public static By objUpgradeTextJustPayingDifference = By.xpath("//div[@class='titleSubTitleComp']//h1");// added
																											// 7/5/2021
	public static By objPayLessWatchMoreHeader = By
			.xpath("//div[@class='titleSubTitleComp']//p[text()='Pay less, Watch more!']");// added 7/5/2021

	public static By objRentOnlyMovieCheckboxPrice = By.xpath("(//div[@class='priceAmout'])[2]");// added 8/5/2021

	public static By objZeePlexLogo = By.xpath("(//a[@href='/zee-plex-movies-on-rent'])[1]");// added 4/5/2021

	public static By objBuyPlanLogo = By.xpath("//*[@class='iconInitialLoad-ic_premium']");// added 3/5/2021
	public static By objSubcriptionPage = By.xpath("//*[@class='stepWrapper']//div[@class='stepValue']");// added
																											// 3/5/2021
	public static By objRadheComboOfferTitle = By.xpath("//*[@class='pageTitle']");// added 3/5/2021

	public static By objRentOnlyMoviePriceMakePaymentPage = By.xpath("//p[@class='period']");// added 8/5/2021
	public static By objGenerateQRCode = By.xpath("//article[(text()='Generate QR Code')]");// added 8/5/2021
	public static By objWalletsProceedToPay = By.xpath("(//article[contains(text(), 'Proceed to pay')])[3]");// added
																												// 8/5/2021
	public static By objBankName = By.xpath("//article[contains(text(),'COSMOS Bank')]");// added 11/5/2021
	public static By objUpgradeButton = By.xpath("//div[@class='buttonContainer ']//span[text()='Upgrade']");// added
																												// 11/5/2021
	public static By objDebitCredit = By.xpath("(//article[(text()='Credit / Debit Card')])[1]");// added 11/5/2021
	public static By objUpi = By.xpath("//article[(text()='UPI')]");// added 14/5/2021 updated

	public static By objNetBanking = By.xpath("//article[contains(text(),'Netbanking')]");// added 13/5/2021 updated
	public static By objHaveACard = By.xpath("//div[@class='qwickCiver']");// added 13/5/2021 added
	public static By objWallet = By.xpath("//article[contains(text(), 'Wallets')]");// added 13/5/2021
	public static By objYouHaveActivePremiumPlanDialog = By
			.xpath("//*[@class='subtitleItem']//span[text()='You are currently on ₹999 Premium 1 Year plan.']");// updated
																												// 13/5/2021

	public static By objBuyPremiumCTA = By.xpath("//*[@class='buttonContainer ']//span[text()='Buy Premium']");// added
																												// 13/5/2021
	public static By objMobileLinkPaytmOption = By.xpath("//*[@text='Link Wallet']");

	public static By objContinueBtn = By.xpath("//div[@class='buttonContainer']");

	public static By objEmailContinueButton = By.xpath("//*[contains(@class, 'noSelect buttonGradient')]");

	public static By objPackSelected = By.xpath(
			".//*[contains(@class,'iconOther-ic_tick radioBtn active')]//following-sibling::*[@class='heading']//child::*[@class='price']");

	public static By objContinueButtonTxt = By.xpath(".//*[@class='buttonContainer']//span");

	public static By objPackCheckBox = By.xpath(".//*[@class='iconOther-ic_tick radioBtn ']");

	public static By objSkipLink = By.xpath("//*[contains(@class, 'skip-button subscribe-link')]");

	// new accountTitle xpath
	public static By objAccountInfoTitle1 = By.xpath("(//h2[contains(text(),'Account Info')])[2]");

	// new continue xpath
	public static By objContinueBtnWithINR = By.xpath("//div[@class='buttonContainer']//button[@type='button']");

	// new Play Icon with Buy Plan CTA on carousel

	public static By objPlayIconWithGetPremiumCTAOnCarousel = By.xpath(
			"//*[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'playIcon') and following-sibling::*//*[contains(text(),'Buy Plan')]]");

	// Have a code ?
	public static By objHaveACodeMPWA = By.xpath("//*[@text='Have a Code?']");
//Have a Code Text field
	public static By objTextForHaveACode = By.xpath("//*[@nodeName='INPUT']");

	// Code is Invalid
	public static By objCodeIsInvalid = By.xpath("//*[@text='Code is Invalid']");
//Blockbuster Movies  Popup        //*[@text='Blockbuster Movies']
	public static By objBlockbusterMovies = By.xpath("//*[@text='Blockbuster Movies']");
//close Button 
	public static By objCloseButton = By.xpath("//*[@class='drowerCloseIcon iconInitialLoad-ic_close']");
//200+ Web series             //*[@text='200+ Web series']
	public static By obj200WebSeriesPopUp = By.xpath(" //*[@text='200+ Web series']");
//Payment Based Offers
	public static By objPaymentBasedOffers = By
			.xpath("(//div[@class='subscriTopContWrapper']//div[@class='item']//span)[4]");
//See Payment Based Offers
	public static By objSeePaymentBasedOffersPopUP = By.xpath("//*[@text='See Payment Based Offers']");
//25% DISCOUNTON 999
	public static By OBJDiscount999 = By.xpath("//*[@css='H2.packName']");

	// INR 749
	public static By objINR749 = By.xpath("//*[@class='packPrice']");

	// Continue Btn in Account info page MPWA
	public static By objContinueBtnAccountInfoPage = By.xpath("//*[@text='Continue']");

// Verify Button
	public static By objVerifyOTPButton = By.xpath("(//span[contains(text(), 'Verify')])[1]");
//Continue Btn MPWA
	public static By objContinueBtnMPWA = By.xpath("//*[contains(text(), 'Continue')]");
//UPI MPWA
	public static By OBJUPIMPWA = By.xpath("//*[@text='UPI']");
	
	//tamil 200+Web Series Title
	public static By objTamil200WebSeriesTitle = By.xpath("//*[@text='200+ வெப் சீரீஸ்']");
	
	//Step 2 to 3 
	public static By OBJStep2To3 = By.xpath("//*[@text='Step 2 of 3']");
	
//tamil Blockbuster Movies  Titel
	public static By objTamilBlockbusterMoviesTitle = By.xpath("//*[@text='4500+ பிளாக்பஸ்டர் திரைப்படங்கள்']");
	
	public static By objAccountInfoHighlightedKannadaLang = By.xpath("//div[contains(text(),'ಖಾತೆಯ ಮಾಹಿತಿ')]");
	
	//Buy Premium Now!
	public static By objByePremiumNow = By.xpath("//*[@nodeName='H1']");
	//your Primium 749 for 1 year plan
	public static By OBJYourPrimium749For1YearPlan= By.xpath("//*[@text='Your Premium ₹749  for 1 Year  plan is active']");
	
//Continue Button Kannada Lang
	public static By objContinueButtonKannadaLang = By.xpath("//*[@text='₹499 ನೊಂದಿಗೆ ಮುಂದುವರಿಯಿರಿ']");
	public static By objProceedBtnEnabledKannadaLang = By
	.xpath("//*[contains(@class, 'noSelect pinkBtn')]/span[contains(text(),'ಮುಂದುವರಿಸಿ')]");
	public static By objProceedBtnHighlightedKannadaLang = By
			.xpath("//*[contains(@class, 'noSelect buttonGradient')]/span[contains(text(),'ಮುಂದುವರಿಸಿ')]");

//Buy Plan CTA My Subscription
	public static By objBuyPlanCTAMySubscription = By.xpath("//*[@nodeName='DIV']//*[@css='DIV.gridWrap']");
	
	// sent to your Email ID" toast message
	public static By objSentToYourEmailIDTtoastMessage = By.xpath("(//*[@class='noSelect pinkBtn  \\n            '])[1]");
	
	public static By objAccountInfoTitleName = By.xpath("(//*[contains(text(),'Account Info')])[2]");

	public static By objBuyPlanMySubscription = By.xpath("//*[contains(@class,'bannerContainer')]");

	public static By objToastMessageNetworkInterruption = By.xpath("//*[contains(@id,'portalContainer')]//*[@class='toastMessage']");
	
	public static By objAmazonLoginPage = By.xpath("//*[@class='a-spacing-micro a-spacing-top-small a-text-left']");
	public static By objLoadingPaymentOptions = By.xpath("//*[@text='Loading Payment Options...']");
	
	public static By objChangeButtonHaveAcode = By.xpath("//*[@class='changeMobileButton']");

	public static By objUISymbol = By.xpath("(//*[@class='circle'])[4]");public static By objProceedBtnGuestCheckOut = By.xpath("//*[contains(@class,'buttonContainer')]//button//span");
	
	public static By objZeeplexTitle(String contentTitle) {
			return By.xpath("//div[@class='tvodCardContainer']//*[text()='"+contentTitle+"']");
	}
	
	public static By objProceedBtnHighlighted = By.xpath("//*[contains(@class, 'noSelect purpleBtn')]");

	public static By objsubscribebtnonplayer = By.xpath("//div[contains(@class, 'subscribe-button subscribemsg_en')]");
	
	public static By objContinueBtnForEmailOrMobile = By.xpath("//button[contains(@class, 'noSelect purpleBtn')]");

	public static By objPaytmProceedToPay = By.xpath("(//*[text()='Proceed to pay' or text()= 'Send OTP'])[2]");

	public static By objProceedBtnEnabled = By.xpath("//*[contains(@class, 'noSelect purpleBtn')]");
	
	public static By objZEE5Logo = By.xpath("//*[contains(@class,'zeeLogo')]//img[@title='ZEE5 Logo']");
	
	public static By objBuyPlanCTAAtConsumptionPage = By.xpath("//div[contains(@class, 'subscribe-button subscribemsg_en')]");
	
	public static By objZEE5Subscription = By.xpath("//*[contains(text(),'Subscribe to Premium') and contains(@class, 'stepTitle')]");
	
	public static By objAccountDetailInSubscription = By.xpath("//*[@class='autoPopulatedDetail']");
	
	public static By objEnterPasswordPopupTitle = By.xpath("//*[contains(@class, 'popupDesc bigTitle')]");
	

}
