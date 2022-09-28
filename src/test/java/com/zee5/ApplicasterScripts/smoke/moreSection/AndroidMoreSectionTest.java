package com.zee5.ApplicasterScripts.smoke.moreSection;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class AndroidMoreSectionTest {
    private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

    @BeforeTest
    public void appLaunch() throws InterruptedException {
        System.out.println("Launching Android App");
        Utilities.relaunch = true;
        ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
    }

    /**
     * Author: Anusha P R
     * Data provider to provide all the 3 user types to the tests
     */
    @DataProvider(name = "DataProvider")
    public Object[] dataProvider(ITestContext context) {
        String userType = context.getCurrentXmlTest().getParameter("allUserType");
        Object[] data = new Object[userType.split(",").length];
        int counter = 0;
        String[] arr = userType.split(",");
        for (String ut : arr) {
            data[counter] = ut;
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
     * logs in to the app and validates myTransaction screen for all the 3 types of users
     */
    @Test(priority = 1, dataProvider = "DataProvider")
    public void myTransactionScreenValidation(String userType) throws Exception {
        ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        ZEE5ApplicasterBusinessLogic.myTransactionPageValidation(userType);
    }

    /**
     * Author: Anusha P R
     * logs in to the app and validates mySubscription screen for all the 3 types of users
     */
    @Test(priority = 2, dataProvider = "DataProvider")
    public void mySubscriptionScreenValidation(String userType) throws Exception {
        ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        ZEE5ApplicasterBusinessLogic.mySubscriptionPageValidation(userType);
    }

    /**
     * Author: Anusha P R
     * logs in to the app and validates myProfile screen for all the 3 types of users
     */
    @Test(priority = 3, dataProvider = "DataProvider")
    public void myProfileScreenValidation(String userType) throws Exception {
        ZEE5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        ZEE5ApplicasterBusinessLogic.myProfileValidation(userType);
    }

    @AfterTest
    public void tearDown() {
        ZEE5ApplicasterBusinessLogic.tearDown();
    }
}
