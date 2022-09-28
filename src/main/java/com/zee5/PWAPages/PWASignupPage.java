package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWASignupPage {
	// SignUpTxt
	public static By objSignUpTxt = By.xpath("//h1[contains(text(),'Sign up for FREE')]");

	// GenderMaleBtn
	public static By objGenderMaleBtn = By.xpath("//span[contains(text(),'Male')]");

	// GenderFemaleBtn
	public static By objGenderFemaleBtn = By.xpath("//span[contains(text(),'Female')]");

	// GenderOtherBtn
	public static By objGenderOtherBtn = By.xpath("//span[contains(text(),'Other')]");

	// TermsOfService
	public static By objTermsOfService = By.xpath("//span[contains(text(),'Terms of Services')]");

	// PrivacyPolicy
	public static By objPrivacyPolicy = By.xpath("//span[contains(text(),'Privacy Policy')]");

//		====================================================================================================
	public static By objSignUpButtonNotHighlighted = By.xpath("//*[@class='noSelect buttonGradient ']");
	public static By objSignUpButtonHighlighted = By.xpath("//*[@class='noSelect buttonGradient ']");
	public static By objOTPTimer = By.xpath("//*[@class='singleRow-left']");
	public static By objResendOtpOption = By.xpath("//button[contains(text(),'Resend')]");
	public static By objOTP1 = By.xpath("//input[@name='otp1']");
	public static By objOTP2 = By.xpath("//input[@name='otp2']");
	public static By objOTP3 = By.xpath("//input[@name='otp3']");
	public static By objOTP4 = By.xpath("//input[@name='otp4']");
	public static By objPasswordErrorMessage = By.xpath("//div[contains(text(),'Minimum 6 characters')]");
	public static By objPasswordIcon = By.xpath("//span[contains(@class,'noSelect pwdIcon')]");
	public static By objPasswordHiddenField = By.xpath("//input[@type='password']");
	public static By objPasswordFieldShow = By.xpath("//input[@type='text' and @name='password']");

//		ONBOARDING
	public static By objYearPickerTabValueNotActive = By.xpath("//*[contains(text(),'2020')]");

	// DayPickerTab
	public static By objDayPickerTab = By.xpath("(//div[@class='floatingLabelSelect  '])[1]");
	public static By objDayPickerTabValue = By.xpath("//span[contains(text(),'02')]");

	// MonthPickerTab
	public static By objMonthPickerTab = By.xpath("(//div[@class='floatingLabelSelect  '])[2]");
	public static By objMonthPickerTabValue = By.xpath("//*[text()='MAR']");

	// YearPickerTab
	public static By objYearPickerTab = By.xpath("(//div[@class='floatingLabelSelect  '])[3]");
	public static By objYearPickerTabValue = By.xpath("//*[contains(text(),'2001')]");
	public static By objGoogleOtpVerification = By.xpath("//*[@resource-id='headingSubtext']");

	public static By objSignUpButtonNotHighlightedWeb = By.xpath("//div[@class='regBottom']/following-sibling::div[@class='buttonContainer']/child::*/child::*");
	
	public static By objSignUpButtonHighlightedWeb = By.xpath("//button[contains(@class,'noSelect buttonGradient')]");
	
	public static By objVerifyBtnWeb = By.xpath("//div[@class='verifyBtn']/child::*");

	public static By objSendOTPButtonDisabled = By.xpath("//*[text()='Send OTP']//parent::*[@disabled]");
	public static By objSendOTPButtonEnabled = By.xpath("//*[text()='Send OTP']//parent::*");

	public static By objPasswordResetLinkDisabled = By.xpath("//span[text()='Send Password Reset Link']//parent::button[contains(@class,'noSelect buttonGradient') and @disabled]");
	public static By objPasswordResetLinkEnabled = By.xpath("//span[text()='Send Password Reset Link']//parent::button[contains(@class,'noSelect buttonGradient')]");

	public static By objForgotPasswordContinueEnabled = By.xpath("//span[text()='Continue']//parent::button[contains(@class,'noSelect buttonGradient')]");
	public static By objForgotPasswordContinueDisabled = By.xpath("//span[text()='Continue']//parent::button[contains(@class,'noSelect buttonGradient') and @disabled]");

	public static By objCreateNewAccountHeader=By.xpath("//div[@class='regHeader' and text()='Create a new account']");
	public static By objCreateNewAccountText=By.xpath("//div[@class='regSubHead' and text()='Create an account to continue enjoying uninterrupted video and personalised experience']");
	public static By objGoogleSignin=By.xpath("//div[@id='gbtn']//div[text()='Sign in']");
	public static By objOROption=By.xpath("//div[@class='loginhrLine loginhrCircle']//*[text()='OR']");
	public static By objMobileNumberFieldInReg=By.xpath("//*[text()='Mobile Number']//parent::*[@class='floatingLabelInput']//input");
	public static By objLabel=By.xpath("//div[@class='labelContainer']//*[@class='checkboxLabel']");
	public static By objLabelInRegisterPopUp=By.xpath("//div[@class='proceedText']//*[@class='checkboxLabel']");
	public static By objsendotp=By.xpath("//button//*[text()='Send OTP']");
	public static By objAlreadyRegisteredWithLoginLinkInReg=By.xpath("//div[@class='loginLink' and text()='Already registered?']//span[text()='Login']");
	public static By objCountryPin=By.xpath("//*[@class='pinCode']");
	public static By objCountryPinDropDown = By.xpath("//div[contains(@class,'react-dropdown-select-dropdown')]");
	public static By objCountryPins = By.xpath("//div[@class='dropDownWrapper']//*[contains(@class,'dropdown-select-item')]");
	public static By objCountryPin (String countryORpin){
		return By.xpath("//*[text()='"+countryORpin+"']//parent::*[contains(@class,'dropdown-select-item')]");
	}
	public static By objUserAlreadyExists = By.xpath("//*[contains(@class,'formHeader') and text()='An account already exists with provided mobile number. Verify mobile number to login.']");
	public static By objVerifyButtonDisabled = By.xpath("//button[@disabled]//*[text()='Verify']");
	public static By objVerifyButtonEnabled = By.xpath("//button[not(@disabled)]//*[text()='Verify']");
	public static By objOTPfield = By.xpath("//div[contains(@class,'otpInputWrap')]");
	public static By objEnterPasswordButton = By.xpath("//button//*[text()='Enter Password']");
	public static By objChangeNumberLink = By.xpath("//*[contains(@class,'redirectLink')]");
	public static By objLoginToZee5Header = By.xpath("//*[contains(@class,'formHeader') and contains(text(),'Login to ZEE5')]");
	public static By objOTPInvalidToastMessage = By.xpath("//*[@class='toastMessage' and text()='Either OTP is not valid of has expired']");
	public static By objDisabledOTPResendButton = By.xpath("//button[@disabled and text()='Resend']");
	public static By objEnabledOTPResendButton = By.xpath("//button[not(@disabled) and text()='Resend']");
	public static By objDidNotGetOTPText = By.xpath("//*[@class='singleRow-right' and text()='Did not get the OTP?']");
	public static By objOTPTimer(String time) {
		return By.xpath("//*[@class='singleRow-left' and text()='"+time+"']");
	}
	public static By objGoogleSignInWindow = By.xpath("//*[text()='Sign in with Google']");
	public static By objGoogleSigninButton=By.xpath("//div[@id='gbtn']");
	public static By objInlineCompleteYourProfileCTA = By.xpath("//*[contains(@class,'register-button') and text()='Complete your profile']");
	public static By objInlineCompleteYourProfileText = By.xpath("//*[contains(@class,'subscribe-msg-premium')]//*[text()='Please complete your profile to watch this content']");
	public static By objInlineRegisterCTA = By.xpath("//*[contains(@class,'register-button') and text()='Register']");
	public static By objInlineSkipCTA = By.xpath("//*[contains(@class,'skip-button') and text()='Skip']");
	public static By objInlineRegisterText = By.xpath("//*[contains(@class,'subscribe-msg-premium')]//*[text()='Please register to watch this content']");
	public static By objInlineAlreadyRegisteredText = By.xpath("//*[@class='login_que' and contains(text(),'Already Registered?')]");
	public static By objInlineLoginCTA = By.xpath("//*[@class='userLogin-link' and text()='Login']");
	public static By objRegisterPopUp = By.xpath("//*[@class='registerLoginContainer']//*[text()='Please sign up to personalize your watching experience']");
	public static By objPopUpClose = By.xpath("//*[@class='manCloseIcon']");
	public static By objAccountInfoPage = By.xpath("//*[@class='stepTitle' and text()='Account Info']");
	public static By objDisclaimerTextNewRegistration = By.xpath("//*[@class='labelContainer']");
	public static By objWhyDoWeNeedThisText = By.xpath("//div[contains(@class,'tooltip') and text()='Why do we need this?']");
	public static By objWhatsappConsentText = By.xpath("//label[@for='consentCheck' and text()='I want to receive updates & notifications over WhatsApp']");
	public static By objWhatsappConsentCheck = By.xpath("//input[@id='consentCheck']");
	public static By objWhatsappLogo = By.cssSelector("//input[@id='consentCheck']");
	public static By objAlreadyRegisteredWithLogin = By.xpath("//*[@class='loginLink' and text()='Already registered?']//*[text()='Login']");
	public static By objContinueButton = By.xpath("//div[contains(@class,'buttonContainer')]//button[contains(@type,'button')]");

}
