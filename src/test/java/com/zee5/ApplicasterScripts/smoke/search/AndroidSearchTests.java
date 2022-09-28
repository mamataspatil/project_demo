package com.zee5.ApplicasterScripts.smoke.search;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AndroidSearchTests {


    private Zee5ApplicasterBusinessLogic zee5ApplicasterBusinessLogic;


    @BeforeTest
    public void AppLaunch() throws InterruptedException {

        Utilities.relaunch = true;    // Clear App Data on First Launch
        zee5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
    }


    /**
     * <p> Verify Trending and Top Searches</p>
     *
     * @param UserType
     * @throws Exception
     */

    @Test(priority = 0)

    @Parameters({"userType"})
    public void topAndTrendingSearches(String UserType) throws Exception {
        zee5ApplicasterBusinessLogic.topSearches(UserType);
        zee5ApplicasterBusinessLogic.trendingSearches();
    }


    /**
     * <p>Search Screen Validations with Inputs</p>
     *
     * @param userType
     * @throws Exception
     */
    @Test(priority = 1)
    @Parameters({"userType"})
    public void searchResultScreenValidation(String userType) throws Exception {
        zee5ApplicasterBusinessLogic.relaunch(true);
        zee5ApplicasterBusinessLogic.noSearchResults("gsggdhshs%$$%&");
        zee5ApplicasterBusinessLogic.searchPageValidation("Kam");
        zee5ApplicasterBusinessLogic.searchResultsAllTabs("Kamali");
    }


    /**
     * <p>Search for given content and play</p>
     *
     * @param userType
     * @param searchKeyword1
     * @throws Exception
     */

    @Test(priority = 2)
    @Parameters({"userType", "searchKeyword1"})
    public void searchAndPlayContent(String userType, String searchKeyword1) throws Exception {
        zee5ApplicasterBusinessLogic.relaunch(true);
        zee5ApplicasterBusinessLogic.zeeApplicasterLogin(userType);
        zee5ApplicasterBusinessLogic.playerOnSearch(searchKeyword1,userType);
    }


    @AfterTest
    public void tearDownApp() {
        zee5ApplicasterBusinessLogic.tearDown();
    }
}
