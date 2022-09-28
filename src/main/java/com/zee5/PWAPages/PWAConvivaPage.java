package com.zee5.PWAPages;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.By;

public class PWAConvivaPage {
	// User name 
	public static By objUserNameField = By.xpath("//input[@id='idp-discovery-username']"); //murali.appadi@zee.esselgroup.com
	
	//Next
	public static By objNextButton = By.xpath("//input[@id='idp-discovery-submit']");
	
	//Password
	public static By objPasswordField = By.xpath("//input[@id='okta-signin-password']");//Ch@ng3m3!!
	
	//Sign In button
	public static By objSignInButton = By.xpath("//input[@id='okta-signin-submit']");
	
	//Select user type dropdown
	public static By objUserTypeDropdown = By.xpath("//button[@class='sc-fzoLsD mgdkq']");
	
	//Admin user
	public static By objAdminUser = By.xpath("//li[contains(@class,'ant-menu-item') and contains(text(),'Zee_Admin')]");
	
	//Menu button
	public static By objMenuIcon = By.xpath("//*[@id='menu-toggle']");
	
	//Viewers option
	public static By objViewersOption = By.xpath("//*[@title='Viewers']");
	
	//Viewer ID field
	public static By objViewerIDField = By.xpath("//input[@id='viewerId']");
	
	//Find Viewer button
	public static By objFindViewerButton = By.xpath("//*[@class='Btn submit' and @value='Find Viewer']");
	
	//Time duration dropdown
	public static By objTimeDurationDropdown = By.xpath("//*[contains(@class,'Btn Choice')]");
	
	//Live duration
	public static By objLiveDuration = By.xpath("//li[text()='Live']");
	
	//1 day duration
	public static By obj1DayDuration = By.xpath("//li[text()='1 day']");
	
	//7 days duration
	public static By obj7DaysDuration = By.xpath("//li[text()='7 days']");
	
	//30 days duration
	public static By obj30DaysDuration = By.xpath("//li[text()='30 days']");
	
	//Row with ContentID
	public static By objRow(String contentID) {
		return By.xpath("//*[@class='text_wrapper mx-sub-200' and contains(@title,'"+contentID+"')]");
	}
	
	//Show 
	public static By objContentShow = By.xpath("//div[@class='tag_div' and contains(@title,'show:')]");
	
	//ContentID
	public static By objContentID = By.xpath("//div[@class='tag_div' and contains(@title,'contentID:')]");
	
	//Genre
	public static By objGenre = By.xpath("//div[@class='tag_div' and contains(@title,'genre:')]");
	
	//Acess Type
	public static By objAccessType = By.xpath("//div[@class='tag_div' and contains(@title,'accessType:')]");
	
	//iFrame Pulse 4
	public static By objIframePulse4 = By.xpath("//iframe[@title='Pulse 4']");
	
	//Google search field
	public  static By objSearchEditField = By.xpath("//input[@title='Search']");
	
	//Google whats my ip suggestion
	public static By objWhatsMyIPSuggestion = By.xpath("(//span[text()='whatsmyip'])[1]");
	
	//Google public IP
	public static By objPublicIP = By.xpath("(//div[@id='search']//div[@jsname='A813te']//span//span)[1]");
	
	//Manage IP Sort button
	public static By objManageIPSortButton (String ManageIPName) {
		return By.xpath("//div[text()='"+ManageIPName+"']//parent::td//parent::tr//td[@class='actions nosort']//div");
	}
	
	//Manage IP Sort bitton Edit Option
	public static By objEditIP (String ManageIPName) {
		return By.xpath("//div[text()='"+ManageIPName+"']//parent::td//parent::tr//td[@class='actions nosort']//div//ul//li[text()='Edit']");
	}
	
	//IP Address field
	public static By objIPAddressField = By.xpath("//input[@id='id_ip']");
	
	//Update button
	public static By objUpdateButton = By.xpath("//input[@value='Update']");
	
	//Filters button
	public static By objFiltersButton = By.xpath("//span[text()='Filters']");
	
	//Delete Filters
	public static By objDeleteFilters = By.xpath("//span[@title='remove item']");
	
	//Filters edit field
	public static By objFiltersEditField = By.xpath("//div[@class='field focus']//input");
	
	//For_automation filter suggestion
	public static By objForAutomationFilterSuggestion= By.xpath("//div[@class='defaultItem hover']//span//b[text()='For_Automation']");
	
	//Apply for Filter selection
	public static By objApplyFilter = By.xpath("//input[@type='submit']");
	
	//whatismyip IP Address
	public static By objIPAddress = By.xpath("//a[contains(@title,'IP address')]"); 
	
	// attempts
	public static By objAttempts = By.xpath("//*[@title='Attempts']//ancestor::div[@class='container']//div[@class='big_num']");
	
	//Exits Before Video Start
	public static By objExitBeforeVideoStart = By.xpath("//*[@title='Exits Before Video Start']//ancestor::div[@class='container']//div[@class='big_num']");
	
	//Plays
	public static By objPlays = By.xpath("//*[@title='Plays']//ancestor::div[@class='container']//div[@class='big_num']");	
	
	// concurrent plays
	public static By objConcurrentPlays = By.xpath("//*[@title='Concurrent Plays']//ancestor::div[@class='container']//div[@class='big_num']");	
	
	// rebuffering ratio
	public static By objRebufferingRatio = By.xpath("//*[@title='Rebuffering Ratio']//ancestor::div[@class='container']//div[@class='big_num']");

	// AFR
	public static By objAverageFrameRate = By.xpath("//*[@title='Average Frame Rate']//ancestor::div[@class='container']//div[@class='big_num']");	

	// VRT
	public static By objVideoRestartTime = By.xpath("//*[@title='Video Restart Time']//ancestor::div[@class='container']//div[@class='big_num']");
		
	
	// VST
	public static By objVideoStartupTime = By.xpath("//*[@title='Video Startup Time']//ancestor::div[@class='container']//div[@class='big_num']");
	
	public static By objAverageBitRate = By.xpath("//*[@title='Average Bitrate']//ancestor::div[@class='container']//div[@class='big_num']");	
	//device validation sort
	public static By objDeviceValidationSortButton = By.xpath("//*[@id='time_selector']");
	
	//device validation sort historical
	public static By objDeviceValidationSortHistorical = By.xpath("//li[text()='Historical']");
	
	//device validation sort live
	public static By objDeviceValidationSortLive = By.xpath("//li[text()='Live']");	
	
	//device validation filter
	public static By objDeviceValidationFilter = By.xpath("//*[@id='ip_selector']");
	
	//Automation filter
	public static By objDeviceValidation (String filterName) {
		return By.xpath("//li[contains(text(),'"+filterName+"')]");
	}
	
	//monitor session ID
	public static By objMonitorSessionContent (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]");
	}	
	
	//monitor session ID
	public static By objMonitorSessionID (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//a");
	}
	
	//monitor session ID with Playing content
	public static By objMonitorSessionIDPlayingContent (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td//div[@title='Playing']//ancestor::tr//a");
	}	
	
	//monitor session ID with Played content
	public static By objMonitorSessionIDPlayedContent (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td//div[@title='Played']//ancestor::tr//a");
	}

	//monitor session ID with Started - Not Joined content 
	public static By objMonitorSessionIDStartedNotJoinedContent (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td//div[@title='Started - Not Joined']//ancestor::tr//a");
	}
	
	//monitor session ID with Exit Before Video Start
	public static By objMonitorSessionIDExitBeforeVideoStart (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td//div[@title='Exit Before Video Start']//ancestor::tr//a");
	}		
	
	//Video Startup Time
	public static By objVST = By.xpath("//td[@data-value='Video Startup Time']//following-sibling::td//div");
		
	//Total Playing Time
	public static By objTotalPlayingTime = By.xpath("//td[@data-value='Total Playing Time']//following-sibling::td//div");
	
	
	//search filter field
	public static By objSearchFilterField = By.xpath("//input[@type='search']");
	
	//filters sort button
	public static By objFiltersSortButton = By.xpath("//div[@title='For_Automation']//ancestor::tr//td[@class='actions nosort']");
	
	//filters Edit option
	public static By objFiltersEditButton = By.xpath("//li[text()='Edit']");
	
	//Client ID value field
	public static By objClientIDVlaueField = By.xpath("//input[@class='val_field']");
	
	//Rules Select Field
	public static By objRulesSelectField(int i) {
		return By.xpath("(//div[@class='form__row or_field'])["+i+"]//select[@class='type']");
	}
	
	public static By objName = By.xpath("//input[@id='id_name']");
	
	public static By objRulesSelectField = By.xpath("(//select[@class='type'])['Asset Name']");
	
	public static By objDeleteField = By.xpath("//*[@class='Btn delete']");	
	
	//Rules Value Field
	public static By objRulesValueField(int i) {
		return By.xpath("(//input[@type='text' and contains(@class,'val_field')])["+i+"]");
	}	
	
	public static By objFilterRulesFieldsCount = By.xpath("//div[@class='and_field']");
	
	public static By objAndButton = By.xpath("//span[@class='Btn and_button']");
	
	public static By objRulesSelectField(int i,String s) {
		return By.xpath("(//div[@class='form__row or_field'])["+i+"]//option[.='"+s+"']");
	}	
	
	//Save Filter button
	public static By objSaveFilterButton = By.xpath("//input[@value='Save']");
	
	//Ad UI
	public static By objAdUi= By.xpath("//div[@class='timeDuration-Container']");
	
	//Ad Frame
	public static By objAdFrame= By.xpath("//iframe[@allow]");
	
	//Bowser version
	public static By objChromeVersionFromChrome = By.xpath("//td[@class='version' and @id='version']//span");
	
	//System OS 
	public static By objSystemOSFromChrome = By.xpath("//td[@class='version' and @id='os_type']//span");

	//System OS version
	public static By objSystemOSVersionFromChrome = By.xpath("//td[@class='version' and @id='os_type']//span[@id='os_version']");
	
	//User Agent
	public static By objUserAgentFromChrome = By.xpath("//td[@id='useragent']");
	
	//Browser name
	public static By objBrowserName = By.xpath("//td[@data-value='Browser Name']//following-sibling::td//div");
	
	//Browser Version
	public static By objBrowserVersion = By.xpath("//td[@data-value='Browser Version']//following-sibling::td//div");

	//Device Hardware Type
	public static By objDeviceHardwareType = By.xpath("//td[@data-value='Device Hardware Type']//following-sibling::td//div");
	
	//Device Manufacturer
	public static By objDeviceManufacturer = By.xpath("//td[@data-value='Device Manufacturer']//following-sibling::td//div");
	
	//Device Marketing Name
	public static By objDeviceMarketingName = By.xpath("//td[@data-value='Device Marketing Name']//following-sibling::td//div");

	//Device Model
	public static By objDeviceModel = By.xpath("//td[@data-value='Device Model']//following-sibling::td//div");

	//Device Name
	public static By objDeviceName = By.xpath("//td[@data-value='Device Name']//following-sibling::td//div");
	
	//Device Operating System
	public static By objDeviceOS = By.xpath("//td[@data-value='Device Operating System']//following-sibling::td//div");
		
	//Device Operating System Family
	public static By objDeviceOSFamily = By.xpath("//td[@data-value='Device Operating System Family']//following-sibling::td//div");
	
	//Device Operating System Version
	public static By objDeviceOSVersion = By.xpath("//td[@data-value='Device Operating System Version']//following-sibling::td//div");
	
	//Player Framework Name
	public static By objPlayerFrameworkName = By.xpath("//td[@data-value='Player Framework Name']//following-sibling::td//div");
	
	//Player Framework Version
	public static By objPlayerFrameworkVersion = By.xpath("//td[@data-value='Player Framework Version']//following-sibling::td//div");

	//Conviva Library Version
	public static By objConvivaLibraryVersion = By.xpath("//td[@data-value='Conviva Library Version']//following-sibling::td//div");
	
	//Player Name
	public static By objPlayerName = By.xpath("//td[@data-value='Player Name']//following-sibling::td//div");

	//Session ID
	public static By objSessionID = By.xpath("//td[@data-value='Session ID']//following-sibling::td//div");
	
	//Viewer ID
	public static By objViewerID = By.xpath("//td[@data-value='Viewer ID']//following-sibling::td//div");
	
	//Client ID
	public static By objClientID = By.xpath("//td[@data-value='Client ID']//following-sibling::td//div");	
	
	//Session Status
	public static By objSessionStatus = By.xpath("//td[@data-value='Session Status']//following-sibling::td//div");
	
	//Asset Name
	public static By objAssetName = By.xpath("//td[@data-value='Asset Name']//following-sibling::td//div");

	//Stream URL
	public static By objStreamURL = By.xpath("//td[@data-value='Stream URL']//following-sibling::td//div");

	//Video (Live/VoD)
	public static By objVideoLiveVOD = By.xpath("//td[@data-value='Video (Live/VoD)']//following-sibling::td//div");

	//Content Length
	public static By objAssetDuration = By.xpath("//td[@data-value='Content Length']//following-sibling::td//div");
	
	//Avg % Complete
	public static By objAvgPercentageComplete = By.xpath("//td[@data-value='Average % Complete']//following-sibling::td//div");
	
	//City
	public static By objCity = By.xpath("//td[@data-value='City']//following-sibling::td//div");
	
	//State
	public static By objState = By.xpath("//td[@data-value='State']//following-sibling::td//div");
	
	//Country
	public static By objCountry = By.xpath("//td[@data-value='Country']//following-sibling::td//div");
	
	//ASNumber
	public static By objASNumber = By.xpath("//td[@data-value='ASNumber']//following-sibling::td//div");

	//ASName
	public static By objASName = By.xpath("//td[@data-value='ASName']//following-sibling::td//div");
	
	//CDN
	public static By objCDN = By.xpath("//td[@data-value='CDN']//following-sibling::td//div");
	
	//ISP
	public static By objISP = By.xpath("//td[@data-value='ISP']//following-sibling::td//div");

	//Device Connection Type
	public static By objDeviceConnectionType = By.xpath("//td[@data-value='Device Connection Type']//following-sibling::td//div");
	
	//accessType
	public static By objaccessType = By.xpath("//td[@data-value='accessType']//following-sibling::td//div");
	
	//adID
	public static By objadID = By.xpath("//td[@data-value='adID']//following-sibling::td//div");

	//affiliate
	public static By objaffiliate = By.xpath("//td[@data-value='affiliate']//following-sibling::td//div");

	//appVersion
	public static By objappVersion = By.xpath("//td[@data-value='appVersion']//following-sibling::td//div");

	//audioLanguage
	public static By objaudioLanguage = By.xpath("//td[@data-value='audioLanguage']//following-sibling::td//div");

	//autoPlay
	public static By objautoPlay = By.xpath("//td[@data-value='autoPlay']//following-sibling::td//div");

	//catchUp
	public static By objcatchUp = By.xpath("//td[@data-value='catchUp']//following-sibling::td//div");

	//category
	public static By objcategory = By.xpath("//td[@data-value='category']//following-sibling::td//div");

	//connectionType
	public static By objconnectionType = By.xpath("//td[@data-value='connectionType']//following-sibling::td//div");

	//contentAccessType
	public static By objcontentAccessType = By.xpath("//td[@data-value='contentAccessType']//following-sibling::td//div");

	//contentID
	public static By objcontentID = By.xpath("//td[@data-value='contentID']//following-sibling::td//div");

	//contentType
	public static By objcontentType = By.xpath("//td[@data-value='contentType']//following-sibling::td//div");

	//episodeName
	public static By objEpisodeName = By.xpath("//td[@data-value='episodeName']//following-sibling::td//div");

	//episodeNumber
	public static By objEpisodeNumber = By.xpath("//td[@data-value='episodeNumber']//following-sibling::td//div");

	//genre
	public static By objGenreC = By.xpath("//td[@data-value='genre']//following-sibling::td//div");
	
	//originalLanguage
	public static By objOriginalLanguage = By.xpath("//td[@data-value='originalLanguage']//following-sibling::td//div");

	//platformName
	public static By objPlatformName = By.xpath("//td[@data-value='platformName']//following-sibling::td//div");

	//playbackQuality
	public static By objPlaybackQuality = By.xpath("//td[@data-value='playbackQuality']//following-sibling::td//div");

	//playerVersion
	public static By objPlayerVersion = By.xpath("//td[@data-value='playerVersion']//following-sibling::td//div");

	//pubDate
	public static By objPubDate = By.xpath("//td[@data-value='pubDate']//following-sibling::td//div");

	//rootID
	public static By objRootID = By.xpath("//td[@data-value='rootID']//following-sibling::td//div");

	//show
	public static By objShow = By.xpath("//td[@data-value='show']//following-sibling::td//div");

	//streamingProtocol
	public static By objStreamingProtocol = By.xpath("//td[@data-value='streamingProtocol']//following-sibling::td//div");

	//subtitle
	public static By objSubtitle = By.xpath("//td[@data-value='subtitle']//following-sibling::td//div");

	//videoStartPoint
	public static By objVideoStartPoint = By.xpath("//td[@data-value='videoStartPoint']//following-sibling::td//div");
	
	//Content Type
	public static By objContentType = By.xpath("//td[@data-value='ContentType']//following-sibling::td//div");
	
	//device
	public static By objDevice = By.xpath("//td[@data-value='DEVICE']//following-sibling::td//div");
	
	//Zee5 App Version
	public static By objAppVersion = By.xpath("//td[@data-value='appVersion']//following-sibling::td//div");
	
	//audio language
	public static By objAudioLanguage = By.xpath("//td[@data-value='audioLanguage']//following-sibling::td//div");
	
	//category
	public static By objCategory = By.xpath("//td[@data-value='category']//following-sibling::td//div");
	
	//contentID
	public static By objContentIDC = By.xpath("//td[@data-value='contentID']//following-sibling::td//div");
	
	//originalLanguage
	public static By objOriginalLang = By.xpath("//td[@data-value='originalLanguage']//following-sibling::td//div");
	
	//site
	public static By objSite = By.xpath("//td[@data-value='site']//following-sibling::td//div");
	
	//userAgent
	public static By objUserAgent = By.xpath("//td[@data-value='userAgent']//following-sibling::td//div");
	
	//episode number zee5
	public static By objEpisodeNumberZee = By.xpath("//div[@class='metaInfo']//p");
	
	//monitor session ID with Playing content
	public static By objMonitorSessionPlayingContent (String content) {
		String xpath="//div[contains(text(),\""+content+"\")]//ancestor::tr//td//div[@title='Playing']";
		System.out.println(xpath);
		return By.xpath(xpath);
	}	
	
	//device validation assetName
	public static By objDeviceValidationAssetName (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]");
	}
	
	//device validation sessionStatus
	public static By objDeviceValidationSessionStatus (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td[2]//div");
	}
	
	//device validation sessionStartTime
	public static By objDeviceValidationSessionStartTime (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td[3]//div");
	}
	
	//device validation deviceType
	public static By objDeviceValidationDeviceType (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td[4]//div");
	}
	
	//device validation browser
	public static By objDeviceValidationBrowser (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td[5]//div");
	}
	
	//device validation clientID
	public static By objDeviceValidationClientID (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td[6]//div");
	}
	
	//device validation monitorSessionID
	public static By objDeviceValidationMonitorSessionID (String content) {
		return By.xpath("//div[contains(text(),'"+content+"')]//ancestor::tr//td[7]//div");
	}
	
	//Google search field mobile
	public  static By objSearchEditFieldMobile = By.xpath("//input[@type='search']");

	//Google public IP mobile
	public static By objPublicIPMobile = By.xpath("(//div[@jsname='A813te']//span//span)[1]");
	

}