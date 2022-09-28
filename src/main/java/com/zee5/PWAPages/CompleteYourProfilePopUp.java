package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class CompleteYourProfilePopUp {
	
	public static By objCompleteYourProfileTxt = By.xpath("//div[.='Complete your profile']");

	public static By objFirstName = By.xpath("//input[@name='firstName']");
	
	public static By objLastName = By.xpath("//input[@name='lastName']");
	public static By objDay = By.xpath("(//div[@class='inputItem selectWrapper'])[1]");
	
	public static By objMonth = By.xpath("(//div[@class='inputItem selectWrapper'])[2]");
	
	public static By objYear = By.xpath("(//div[@class='inputItem selectWrapper'])[3]");
	
	public static By objGenderMale = By.xpath("(//label[@for='male'])//child::*[3][.='Male']");
	
	public static By objGenderFemale = By.xpath("(//label[@for='female'])//child::*[3][.='Female']");
	
	public static By objMobileNo = By.xpath("(//input[@name='mobile'])");
	
	public static By objOTP = By.xpath("//button[@type='button']//span[.='Send OTP']");
	
	public static By objCloseBtn = By.xpath(" //div[@class='manCloseIcon']");
	
	public static By objDateSelector = By.xpath("(//span[@role='option'])[3]");
	
	public static By objDOBField = By.xpath("//input[@name='dateOfBirth']");
	
	public static By objSendOtp = By.xpath("//*[text()='Send OTP']");
	
	public static By objGenderDropDown = By.xpath("(//div[contains(@class,'react-dropdown-select-dropdown-handle')])[2]");
	
	public static By objGenderfemale = By.xpath("//span[text()='Female']");
	
	public static By objOTPScreen = By.xpath("//input[@name='otp1']");
	public static By verifyOTPscreenHeader = By.xpath("//div[contains(text(),'Verify OTP')]");


	public static By objMobileNumber = By.xpath("//input[@id='textField' and @name='mobile']");
	public static By objMobileError = By.xpath("//*[@class='displayErrormessage' and text()='Invalid Mobile Number']");
	public static By objFullName = By.xpath("//input[@id='textField' and @name='fullName']");
	public static By objAge = By.xpath("//input[@id='textField' and @name='age']");
	public static By objGenderWithRadioButton = By.xpath("//*[contains(@class,'genderRadioButton')]//parent::*//parent::*//*[text()='Gender']");
	public static By objPopUpWindow = By.xpath("//*[@class='pageMainContainerAccountInfo']");
	public static By objAgeError = By.xpath("//*[@class='displayErrormessage' and text()='Age should be between 18 and 103 years.']");
	public static By objDisabledContinue = By.xpath("//button[@disabled]//*[text()='Continue']");
	public static By objGenFemale = By.xpath("//*[contains(@class,'genderRadioButton')]//button[@value='Female']");
	public static By objGenMale = By.xpath("//*[contains(@class,'genderRadioButton')]//button[@value='Male']");
	public static By objGenOther = By.xpath("//*[contains(@class,'genderRadioButton')]//button[@value='Other']");

	public static By objAlreadyRegisteredMobileNumberErrorMsg = By.xpath("//*[@class='displayErrormessage' and text()='An account already exists with provided mobile number.']");

	public static By objTermsOfServicesLink = By.xpath("//*[contains(@class,'link') and text()='Terms of Services']");
	public static By objPrivacyPolicyLink = By.xpath("//*[contains(@class,'link') and text()='Privacy Policy']");
	public static By objLoginLink = By.xpath("//*[contains(@class,'link') and text()='Login']");
	public static By objRegPopupInHindi = By.xpath("//*[contains(@class,'inputLabel') and text()='मोबाइल नंबर']");
	public static By objEmailField = By.xpath("//*[contains(@class,'inputLabel') and contains(text(),'Email')]");

	public static By whatsappConsentCheckBox = By.xpath("//label[text()='I want to receive updates & notifications over WhatsApp']//span");
	public static By continueButton = By.xpath("//button[contains(@class,'purpleBtn')]");
//	public static By objAlreadyRegisteredMobileNumberErrorMsg = By.xpath("//*[@class='displayErrormessage' and text()='An account already exists with provided mobile number.']");
	public static By objEnabledContinue = By.xpath("//div[contains(@class,'buttonContainer')]//button[@type='button']");

}
