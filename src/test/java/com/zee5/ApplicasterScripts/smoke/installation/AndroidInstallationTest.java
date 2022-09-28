package com.zee5.ApplicasterScripts.smoke.installation;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AndroidInstallationTest {
    private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

    @BeforeTest
    public void launchExistingApp() {
        System.out.println("Launching Android App");
        Utilities.relaunch = true;
        ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
    }

    /**
     * Author: Anusha P R
     * uninstalls existing app and installs fresh playstore app
     */
    @Test(priority = 0)
    public void installPlaystoreBuild() throws Exception {
        ZEE5ApplicasterBusinessLogic.installmarketBuild();
    }

    @AfterTest
    public void tearDown() {
        ZEE5ApplicasterBusinessLogic.tearDown();
    }
}
