package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDActivateDevicePage {

	public static By objActivateDeviceTitle = By.xpath("//*[@text='Activate ZEE5 on your TV']");
	public static By objActivationDescription = By.xpath("//*[@text='Enter the Activation code displayed on your TV screen']");
	public static By objActivateScreencloseButton = By.xpath("//*[@id='icon_exit']");
	public static By objInputField1 = By.xpath("//*[@class='android.widget.EditText'][1]");
	public static By objInputField2 = By.xpath("//*[@class='android.widget.EditText'][2]");
	public static By objInputField3 = By.xpath("//*[@class='android.widget.EditText'][3]");
	public static By objInputField4 = By.xpath("//*[@class='android.widget.EditText'][4]");
	public static By objInputField5 = By.xpath("//*[@class='android.widget.EditText'][5]");
	public static By objInputField6 = By.xpath("//*[@class='android.widget.EditText'][6]");
	public static By objActivateCTA = By.xpath("//*[@text='Activate']");
	public static By objActivateCTAEnabled = By.xpath("//*[@text='Activate' and @enabled='true']");
	public static By objActivateCTADisabled = By.xpath("//*[@text='Activate' and @enabled='false']");
	public static By objInvalidCode = By.xpath("//*[@text='Device with code A335FT not found']");

}