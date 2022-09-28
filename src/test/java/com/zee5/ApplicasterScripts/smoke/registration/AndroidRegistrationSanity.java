package com.zee5.ApplicasterScripts.smoke.registration;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AndroidRegistrationSanity {

    private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

    @BeforeTest
    public void AppLaunch() throws InterruptedException {
        System.out.println("Launching Android App");
        Utilities.relaunch = true;
        ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
    }

    @Test(priority = 0)
    @Parameters({"userType"})
    public void Login(String userType) throws Exception {
        ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
        ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
    }

    @Test(priority = 1)
    @Parameters({"userType", "newEmailPassword"})
    public void emailRegistrationValidation(String userType, String newEmailPassword) throws Exception {
        ZEE5ApplicasterBusinessLogic.emailRegistrationValidation(userType, newEmailPassword);
    }

    @Test(priority = 2)
    @Parameters({"userType", "registeredEmail", "registeredEmailPassword"})
    public void emailLoginVerification(String userType, String registeredEmail, String registeredEmailPassword) throws Exception {
        ZEE5ApplicasterBusinessLogic.relaunch(true);
        ZEE5ApplicasterBusinessLogic.emailLoginFlowValidation(userType, registeredEmail, registeredEmailPassword);
    }

    @Test(priority = 3)
    @Parameters({"userType"})
    public void socialLoginValidation(String userType) throws Exception {
        ZEE5ApplicasterBusinessLogic.relaunch(true);
        ZEE5ApplicasterBusinessLogic.SocialLogin(userType);
    }

    @Test(priority = 4)
    @Parameters({"userType", "registeredMobile", "registeredMobilePassword"})
    public void mobileLoginValidation(String userType, String registeredMobile, String registeredMobilePassword) throws Exception {
        ZEE5ApplicasterBusinessLogic.relaunch(true);
        ZEE5ApplicasterBusinessLogic.mobileLoginValidation(userType, registeredMobile, registeredMobilePassword);
    }

    @AfterTest
    public void tearDown() {
        ZEE5ApplicasterBusinessLogic.tearDown();
    }
}
