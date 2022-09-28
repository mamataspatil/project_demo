package com.zee5.PWASanityScripts;

import com.business.zee.Zee5PWASanityWEBBusinessLogic;
import org.testng.annotations.*;

public class WebPWABuildStabilityScript {

    private Zee5PWASanityWEBBusinessLogic buildStabilityBusinessLogic;

    @BeforeTest
    public void init() throws Exception {
        buildStabilityBusinessLogic = new Zee5PWASanityWEBBusinessLogic("test");
    }

    @Test
    @Parameters({ "userType" })
    public void continueWatching(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.ZeeWEBPWALogin(userType);
        buildStabilityBusinessLogic.ContinueWatching();
    }

    @Test
    @Parameters({ "userType" })
    public void login(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.defaultHomePage(userType);
        buildStabilityBusinessLogic.ZeeWEBPWALogin(userType);
        buildStabilityBusinessLogic.logout();
        buildStabilityBusinessLogic.MobileLogin();
    }

    @Test
    @Parameters({ "userType" })
    public void socialLogin(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.SocialLogin(userType);
    }

    @Test
    @Parameters({ "userType" })
    public void completeProfile(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.CompleteProfile(userType);
    }

    @Test
    @Parameters({ "userType" })
    public void onboarding(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.OnboardingScenario(userType);
    }

    @Test
    @Parameters({ "userType" })
    public void mandatoryRegistration(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.MandatoryRegistration(userType);
    }

    @Test
    @Parameters({ "userType" })
    public void railContentValidation(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.movies("Movies",userType);
    }

    @Test
    @Parameters({ "userType" })
    public void mouseHoverActions(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.MouseOverModule(userType);
    }

    @Test
    @Parameters({ "userType" })
    public void moviePageValidation(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.Moviepage(userType, "Movies");
    }

    @Test
    @Parameters({ "userType" })
    public void searchContent() throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.Search("Antim");
        buildStabilityBusinessLogic.searchShow("The toy box");
        buildStabilityBusinessLogic.searchEpisode("Kundali Bhagya");
    }

    @Test
    @Parameters({ "userType" })
    public void hamburgerMenuValidations(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.Hamburgermenu(userType);
    }

    @Test
    @Parameters({ "userType" })
    public void subscriptionScenarios(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.Subscriptionjourney(userType, "Home");
        buildStabilityBusinessLogic.ZeeWEBPWALogin(userType);
        buildStabilityBusinessLogic.MySubscription(userType);
    }

    @Test
    @Parameters({ "userType" })
    public void playerValidation(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.ValidatingPlayer(userType);
    }

    //This is for nonsubscribed user journey
    @Test
    @Parameters({ "userType" })
    public void loggedInUserScenarios(String userType) throws Exception {
        buildStabilityBusinessLogic.navigateToHome();
        buildStabilityBusinessLogic.ZeeWEBPWALogin(userType);
        buildStabilityBusinessLogic.UserActionLoggedInUser(userType);
        buildStabilityBusinessLogic.CarouselSubscriptionPage(userType);
    }

    @AfterClass
    public void tearDown() {
        buildStabilityBusinessLogic.tearDown();
    }

}
