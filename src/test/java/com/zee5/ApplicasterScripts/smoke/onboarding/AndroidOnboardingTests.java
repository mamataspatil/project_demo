package com.zee5.ApplicasterScripts.smoke.onboarding;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AndroidOnboardingTests {

    private Zee5ApplicasterBusinessLogic zee5ApplicasterBusinessLogic;


    @BeforeTest
    public void AppLaunch() throws InterruptedException {
        Utilities.relaunch = true;    // Clear App Data on First Launch
        zee5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
    }

    /**
     * <p> Launch App and Turn off off all network connections using adb command</p>
     *
     * @throws Exception
     */
    @Test
    public void appOfflineValidation() throws Exception {
        zee5ApplicasterBusinessLogic.relaunch(false);
        zee5ApplicasterBusinessLogic.TurnOFFWifi();
        zee5ApplicasterBusinessLogic.offlineScreenValidation();
    }


    @AfterTest
    public void tearDownApp() {
        zee5ApplicasterBusinessLogic.tearDown();
    }
}
