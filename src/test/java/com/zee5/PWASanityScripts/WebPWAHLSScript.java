package com.zee5.PWASanityScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASanityWEBBusinessLogic;

public class WebPWAHLSScript {

	private Zee5PWASanityWEBBusinessLogic Zee5WEBPWASanityBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		Zee5WEBPWASanityBusinessLogic = new Zee5PWASanityWEBBusinessLogic("Chrome");
	}

	@Test(priority = 1)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		System.out.println("PWAWEBLogin");
		Zee5WEBPWASanityBusinessLogic.ZeeWEBPWALogin(userType);
	}

	@Test(priority = 2)
	@Parameters({ "userType" })
	public void HomePage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Homepagevalidation(userType, "Home");
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void MoviePage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.movies("Movies", userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void TVShowsPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.tvShowsValidation("TV Shows", userType);
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void OnBoarding(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.silentRegistrationViaEmail(userType);
		Zee5WEBPWASanityBusinessLogic.SocialLogin(userType);
		Zee5WEBPWASanityBusinessLogic.Carouselcontent(userType);
	}

	@Test(priority = 6)
	@Parameters({ "userType" })
	public void NewsPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.newsValidation(userType, "News");
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void ClubPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Clubvalidation("Club", userType);
	}

	@Test(priority = 7)
	@Parameters({ "userType" })
	public void PlayPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.PlayValidation("Play", userType);
	}
	
	@Test(priority = 8)
	@Parameters({ "userType" })
	public void PremiumPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Premiumvalidation("Premium", userType);
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void MusicPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Musicvalidation("Music", userType);
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void LiveTVPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.LiveTVValidation(userType);
	}

	@Test(priority = 11)
	@Parameters({ "userType" })
	public void StoriesPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.storiesvalidation(userType, "Stories");
	}

	@Test(priority = 12)
	@Parameters({ "userType" })
	public void VideoPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.videoValidation(userType, "Videos");
	}

	@Test(priority = 13)
	@Parameters({ "userType" })
	public void WebSeriesPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.webSeriesValidation(userType, "Web Series");
	}

	@Test(priority = 14)
	@Parameters({ "userType" })
	public void SearchPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Search("Paaru ");
	}

	@Test(priority = 15)
	@Parameters({ "userType" })
	public void Player(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.kaltura(userType, "Home");
	}

	@Test(priority = 16)
	@Parameters({ "userType" })
	public void SubscriptionPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.Subscriptionjourney(userType, "Home");
	}

	@Test(priority = 17)
	@Parameters({ "userType" })
	public void MySubscriptionPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.MySubscription(userType);
		Zee5WEBPWASanityBusinessLogic.MyTransactions(userType);
	}

	//@Test(priority = 0)
	@Parameters({ "userType" })
	public void UpgradePage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.upgrade(userType);
	}

	@Test(priority = 18)
	@Parameters({ "userType" })
	public void ZeePlexPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.zeeplexvalidation("ZEEPLEX", userType);
	}

	@Test(priority = 19)
	@Parameters({ "userType" })
	public void KidsPage(String userType) throws Exception {
		Zee5WEBPWASanityBusinessLogic.navigateToHome();
		Zee5WEBPWASanityBusinessLogic.kidsvalidation(userType, "Kids");
	}	

	@AfterClass
	public void tearDown() {
		Zee5WEBPWASanityBusinessLogic.tearDown();
	}
}