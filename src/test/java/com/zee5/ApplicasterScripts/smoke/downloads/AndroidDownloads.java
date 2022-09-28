package com.zee5.ApplicasterScripts.smoke.downloads;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.DataUtility;
import com.utility.Utilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AndroidDownloads {

    private Zee5ApplicasterBusinessLogic zee5ApplicasterBusinessLogic;

    @BeforeTest
    public void appLaunch() throws InterruptedException {
        Utilities.relaunch = true; // Clear App Data on First Launch
        zee5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
    }

    /**
     * <p>Validate if user is able to download and play contents offline</p>
     *
     * @param userType
     * @throws Exception
     */
    @Test(priority = 0, dataProvider = "getRegisteredUsers", dataProviderClass = DataUtility.class)
    @Parameters({"userType"})
    public void downloadContentAndPlayOfflineTest(String userType) throws Exception {
        zee5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
        zee5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        zee5ApplicasterBusinessLogic.downloadsContentPlayBackValidation(userType, "Better", true);
        zee5ApplicasterBusinessLogic.playDownloadedContentOffline(userType);
        zee5ApplicasterBusinessLogic.relaunch(true);
    }

    @AfterTest
    public void tearDown() {
        zee5ApplicasterBusinessLogic.tearDown();
    }

}
