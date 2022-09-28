package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDMyReminderPage {
	
	public static By objReminberHeaderTitle = By.xpath("//*[@id='screen_title' and //*[@text='Reminders']] | //*[@id='title']");
	public static By objEditBtn = By.xpath("//*[@id='skip_link' and //*[@text='Edit']] | //*[@id='customAction']");
	public static By objTitleOfContentTxt = By.xpath("//*[@id='txt_reminder_item_title'] | //*[@text='Sathya']");
	public static By objEpisodeDatetxt = By.xpath("//*[@id='txt_episode_duration'] | //*[@text='07 Dec 2020']");
	public static By objBackIcon = By.xpath("//*[@id='icon_back'] | //*[@id='back']");
	public static By objCloseIcon = By.xpath("//*[@id='icon_close'] | //*[@id='closeIcon']");
	public static By objSelectAllIcon = By.xpath("//*[@id='txt_select_all'] | //*[@id='action' and //*[@text='Select All'] ]");
	public static By objCheckBox = By.xpath("//*[@id='check_box_item_selector'] | //*[@class='android.widget.CheckBox']");
	public static By objSelectItemsToDeleteHeaderTxt = By.xpath("//*[@text='Select Items to Delete']");
	public static By objDeleteAllIcon = By.xpath("//*[@id='txt_select_all' and //*[@text='Delete All']] | //*[@id='action' and //*[@text='Delete All'] ]");
	public static By objNoReminderIcon = By.xpath("//*[@id='icon_ic_no_reminder'] | //*[@id='emptyIcon']");
	public static By objNoReminderTxt = By.xpath("//*[@id='txt_no_reminder'] | //*[@id='emptyText']");
	
}
