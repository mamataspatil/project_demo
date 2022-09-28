package com.zee5.ApplicasterScripts.smoke.consumption;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class AndroidConsumptionTests {

    private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

    @BeforeTest
    public void appLaunch() throws InterruptedException {
        System.out.println("Launching Android App");
        Utilities.relaunch = true;
        ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
    }

    @DataProvider(name = "DataProvider")
    public Object[][] dataProvider(ITestContext context) {
        String userType = context.getCurrentXmlTest().getParameter("allUserType");
        String searchKeyword = context.getCurrentXmlTest().getParameter("searchKeyword");
        Object[][] data = new Object[userType.split(",").length][2];
        int counter = 0;
        String[] arr = userType.split(",");
        for (String ut : arr) {
            data[counter][0] = ut;
            data[counter][1] = searchKeyword;
            counter++;
        }
        return data;
    }

    @Test(priority = 0)
    @Parameters({"userType"})
    public void applicasterLogin(String userType) throws Exception {
        ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
        ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
    }

    /**
     * Author: Anusha P R
     * validates metadata in consumption screen for all the 3 types of users
     */
    @Test(priority = 1, dataProvider = "DataProvider")
    public void metadataValidationInConsumption(String userType, String searchKeyword) throws Exception {
        ZEE5ApplicasterBusinessLogic.relaunch(true);
        ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        ZEE5ApplicasterBusinessLogic.metaDataValidationInConsumption(searchKeyword);
    }

    /**
     * Author: Anusha P R
     * validates watchlist functionality in consumption screen for all the 3 types of users
     */
    @Test(priority = 2, dataProvider = "DataProvider")
    public void ValidationOfWatchList(String userType,String keyword) throws Exception {
        ZEE5ApplicasterBusinessLogic.relaunch(true);
        ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        ZEE5ApplicasterBusinessLogic.AddToWatchlist(userType,keyword);
        ZEE5ApplicasterBusinessLogic.verifyWatchListScreen(userType);
    }

    /**
     * Author: Anusha P R
     * validates player in consumption screen for all the 3 types of users
     */
    @Test(priority = 3, dataProvider = "DataProvider")
    public void playerValidationAndQualityCheck(String userType, String searchKeyword) throws Exception {
        ZEE5ApplicasterBusinessLogic.relaunch(true);
        ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        ZEE5ApplicasterBusinessLogic.playerValidation(searchKeyword, userType);
    }

    @AfterTest
    public void tearDown() {
        ZEE5ApplicasterBusinessLogic.tearDown();
    }
}
