package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAPlayPage {

	public static By objJoyStickTag = By.xpath("//*[contains(@class,'cardJoystickContent')]");
	
	public static By objPlayNowButton = By.xpath("//*[contains(@class,'slick-active')]//span[text()='Play Now']");
	
	public static By megaMenuContentCard = By.xpath(
			"(((((//div[@class='navMenuWrapper ']//li//a[contains(text(),'Movies')]//parent::*)//div[@class='megaMenu megaMenuCards cardTypetvshows'])//ul//li[1])[1]//ul//li)[1]//div)//a");

}
