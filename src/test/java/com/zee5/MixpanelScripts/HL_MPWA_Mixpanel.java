package com.zee5.MixpanelScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5MPWAMixPanelBusinessLogic;

public class HL_MPWA_Mixpanel {
	
private Zee5MPWAMixPanelBusinessLogic zee5PWABusinessLogic;
	
	
	@BeforeTest
	public void init() throws InterruptedException
	{
		zee5PWABusinessLogic = new Zee5MPWAMixPanelBusinessLogic("Chrome");
	}

	
	//==============================LoginInitiated Event=================================
	@Test(priority = 2)
	@Parameters({ "userType" })
	public void verifyLoginInitiatedEvent(String userType) throws Exception {
		System.out.println("Verify Login Initiated Event");
		zee5PWABusinessLogic.relaunch();
		zee5PWABusinessLogic.verifyLoginInitiatedEventForInvalidCredentials(userType,"E-mail");
	}

	
	//==============================LoginResult Event=================================
	@Test(priority = 3)
	@Parameters({ "userType"})
	public void verifyLoginResultEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Login Result Event post entering invalid credentials");
		zee5PWABusinessLogic.relaunch();
		zee5PWABusinessLogic.verifyLoginResultEventForInvalidCredentials(userType,"E-mail");
	}

	
	//============================RegistrationInitiated Event=======================================
	@Test(priority = 4)
	@Parameters({ "userType"})
	public void verifyRegistrationInitiatedEventForInvalidCredentials(String userType) throws Exception {
		System.out.println("Verify Registration Initiated Event post entering invalid credentials");
		zee5PWABusinessLogic.relaunch();
		zee5PWABusinessLogic.verifyRegistrationInitiatedEventForInvalidCredentials(userType);
	}
		

	//=============================RegistrationResultEvent=======================================
	
		@Test(priority = 5)
		@Parameters({ "userType"})
		public void verifyRegistrationResultEventForInvalidCredentials(String userType) throws Exception {
			System.out.println("Verify Registration Result Event post entering invalid credentials");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyRegistrationResultEventForInvalidCredentials(userType);
		}

	
	
		//===============================SubscriptionPageViewedEvent=================================
		@Test(priority = 6)
		@Parameters({ "userType" })
		public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
			System.out.println("Verify Subscription Page Viewed Event by clicking on Subscribe button at header");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySubscriptionPageViewedEventViaSubscribeBtn(userType);
		}

		//===============================SubscriptionSelectedEvent=================================
		@Test(priority = 7)
		@Parameters({ "userType" })
		public void verifySubscriptionSelectedEvent(String userType) throws Exception {
			System.out.println("Verify Subscription Selected Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySubscriptionSelectedEvent(userType);
		}
		
		
		//=============================SubscriptionCallInitiated==================================
		
		@Test(priority = 8)
		@Parameters({ "userType"})
		public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
			System.out.println("Verify Subscription Call Initiated Event for All access pack");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySubscriptionCallInitiatedEvent(userType);
		}

		
		//=============================SubscriptionCallReturned===================================
		@Test(priority = 9)
		@Parameters({ "userType"})
		public void verifySubscriptionCallReturnedEvent(String userType) throws Exception {
			System.out.println("Verify Subscription Call Returned Event when user makes unsuccessful transaction by quitting the payment gateway screen");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySubscriptionCallReturnedEvent(userType);
		}

		
		//=============================Thumbnail Click Event===================================
		@Test(priority = 10)
		@Parameters({ "userType"})
		public void verifyThumbanilClickEvent(String userType) throws Exception {
			System.out.println("Verify Thumbnail Click Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyThumbnailClickEventFromTray(userType,"Home");
		}

		
		
		//=============================ContentBucketSwipe Event===================================
		@Test(priority = 11)
		public void verifyContentBucketSwipeEvent() throws Exception {
			System.out.println("Verify Content Bucket Swipe Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyContentBucketSwipeEvent("Shows");
		}
		
		
		//=============================CarouselBannerSwipe Event===================================
		@Test(priority = 12)
		public void verifyCarouselBannerSwipeEvent() throws Exception {
			System.out.println("Verify Carousel Banner Swipe Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyCarouselBannerSwipeEvent("Shows");
		}
		
		
		//=============================CarouselBannerClick Event===================================
		@Test(priority = 13)
		@Parameters({ "userType"})
		public void verifyCarouselBannerClickEvent(String userType) throws Exception {
			System.out.println("Verify Carousel Banner Click Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyCarouselBannerClickEvent(userType,"Home");
		}

		
		//=============================SearchButtonClick Event===================================
		@Test(priority = 14)
		public void verifySearchButtonClickEvent() throws Exception {
			System.out.println("Verify Search Button Click Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySearchButtonClickEvent();
		}

		
		//=============================SearchResultClick Event===================================
		@Test(priority = 15)
		public void verifySearchResultClickedEvent() throws Exception {
			System.out.println("Verify Search Result Clicked Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySearchResultClickedEvent("Gattimela");
		}

		
		//=============================SearchExecuted Event===================================
		@Test(priority = 16)
		public void verifySearchExecutedEvent() throws Exception {
			System.out.println("Verify Search Executed Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySearchExecutedEvent();
		}

		
		//=============================VideoView Event===================================
		@Test(priority = 17)
		@Parameters({ "userType", "keyword4"})
		public void verifyVideoViewEventForFreeContent(String userType,String keyword4) throws Exception {
			System.out.println("Verify Video View Event For Free Content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyVideoViewEventForFreeContent(userType,keyword4);
		}


		//=============================Pause Event===================================
		@Test(priority = 18)
		@Parameters({ "userType", "keyword4"})
		public void verifyPauseViewEventForFreeContent(String userType,String keyword4) throws Exception {
			System.out.println("Verify Pause Event For Free Content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyPauseEventForFreeContent(userType,keyword4);
		}

		
		//=============================Resume Event===================================
		@Test(priority = 19)
		@Parameters({ "userType", "keyword4"})
		public void verifyResumeEventForFreeContent(String userType,String keyword4) throws Exception {
			System.out.println("Verify Resume Event For Free Content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyResumeEventForFreeContent(userType,keyword4);
		}
		
		
		//=============================AutoSeek Event===================================
		@Test(priority = 20)
		@Parameters({ "userType", "keyword4"})
		public void verifyAutoSeekEventForFreeContent(String userType,String keyword4) throws Exception {
			System.out.println("Verify AutoSeek Event For Free Content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyAutoSeekForwardEventForFreeContent(userType,keyword4);
		}
		
		
		//=============================SeekScrub Event===================================
		@Test(priority = 21)
		@Parameters({ "userType", "keyword4"})
		public void verifySeekScrubEventForFreeContent(String userType,String keyword4) throws Exception {
			System.out.println("Verify SeekScrub Event For Free Content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyScrubSeekEventForFreeContent(userType,keyword4);
		}
		
		
		
		
		
		//=============================ADInitialised Event===================================
		@Test(priority = 22)
		@Parameters({ "userType", "audioTrackContent"})
		public void verifyAdInitializedEventForFreeContent(String userType, String audioTrackContent) throws Exception {
			System.out.println("Verify Ad Initialized Event For Free Content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyAdInitializedEventForFreeContent(userType,audioTrackContent);
		}

		
		//=============================ADView Event===================================
		@Test(priority = 23)
		@Parameters({ "userType", "audioTrackContent"})
		public void verifyAdViewEventForFreeContent(String userType,String audioTrackContent) throws Exception {
			System.out.println("Verify Ad View Event For Free Content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyAdViewEventForFreeContent(userType,audioTrackContent);
		}

		
		//=============================ADWatchDuration Event===================================
		@Test(priority = 24)
		@Parameters({ "userType", "audioTrackContent"})
		public void verifyAdWatchDurationEventForFreeContentForceExit(String userType,String audioTrackContent) throws Exception {
			System.out.println("Verify Ad Watch Duration Event when user force quits the ad playback for free content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyAdWatchDurationEventForFreeContentForceExit(userType,audioTrackContent);
		}

		
		
		//=============================RentalPurchaseCallInitiated Event==================================
		
		@Test(priority = 25)
		@Parameters({ "userType"})
		public void verifyRentalPurchaseCallInitiatedEvent(String userType) throws Exception {
			System.out.println("Verify RentalPurchase Call Initiated Event for All access pack");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyRentalPurchaseCallInitiatedEvent(userType);
		}

		
		
		//=============================RentalPurchaseCallReturned Event==================================
		
		@Test(priority = 26)
		@Parameters({ "userType"})
		public void verifyRentalPurchaseCallReturnedEvent(String userType) throws Exception {
			System.out.println("Verify RentalPurchase Call Initiated Event for All access pack");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyRentalPurchaseCallReturnedEvent(userType);
		}
		
		@Test(priority = 27)
		@Parameters({ "userType" })
		public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
			System.out.println("Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySubscriptionPageViewedEventViaBuySubscription(userType);
		}
		
		@Test(priority = 28)
		@Parameters({ "userType" })
		public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
			System.out.println("Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySubscriptionPageViewedEventViaPrepaidCode(userType);
		}
		
		@Test(priority = 29)
		@Parameters({ "userType" })
		public void verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel(String userType) throws Exception {
			System.out.println("Verify Subscription Page Viewed Event By Clicking on Get Premium CTA On Carousel");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifySubscriptionPageViewedEventByClickingGetPremiumCTAOnCarousel(userType);
		}
		
		@Test(priority = 30)
		public void verifyScreenViewEvent() throws Exception {
			System.out.println("Verify Screen View Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyScreenViewEvent("Shows");
		}
		
		@Test(priority = 31)
		@Parameters({ "userType", "keyword2" })
		public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
			System.out.println("Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyPopUpLaunchEventForGetPremiumPopUp(userType,keyword2);
		}
		
		@Test(priority = 32)
		@Parameters({ "userType", "keyword" })
		public void verifyPopUpLaunchEventForRegisterPopUp(String userType, String keyword) throws Exception {
			System.out.println("Verify Pop Up Launch Event when Native popup is displayed");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyPopUpLaunchEventForRegisterPopUp(userType,keyword);
		}
		
		
		@Test(priority = 33)
		@Parameters({ "userType" })
		public void verifyPopUpLaunchEventForCompleteProfilePopUp(String userType) throws Exception {
			System.out.println("Verify Pop Up Launch Event when Complete Profile popup is displayed");
			zee5PWABusinessLogic.verifyPopUpLaunchEventForCompleteProfilePopUp(userType);
		}
		
		
		// Login through ClubUser Id
		@Test(priority = 34)
		@Parameters({ "userType", "keyword6" })
		public void verifyPopUpLaunchEventForClubUser(String userType, String keyword6) throws Exception {
			System.out.println("Verify Pop Up Launch Event when user gets Upgrade popup for Club User");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyPopUpLaunchEventForClubUser(userType,keyword6);
		}

		@Test(priority = 35)
		@Parameters({ "userType" })
		public void verifyCTAsEventHeader(String userType) throws Exception {
			System.out.println("Verify CTAs Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyCTAsEventHeader(userType,"Shows");
		}
		
		@Test(priority = 36)
		@Parameters({ "userType", "keyword6" })
		public void verifyPopUpCTAsEvent(String userType, String keyword6) throws Exception {
			System.out.println("Verify Pop Up CTA's Event when user clicks on CTA button displayed on the popup");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyPopUpCTAsEvent(userType,keyword6);
		}
		
		@Test(priority = 37)
		@Parameters({ "userType" })
		public void verifyCTAsEventForIcons(String userType) throws Exception {
			System.out.println("Verify CTAs Event");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyCTAsEventForIcons(userType,"Language");
		}
		
		@Test(priority = 38)
		@Parameters({ "userType" })
		public void verifyCTAsEventForSubscribeBtn(String userType) throws Exception {
			System.out.println("Verify CTAs Event when user clicks on subscription option");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyCTAsEventForSubscribeBtn(userType);
		}
		
		
		@Test(priority = 39)
		public void verifyCTAsEventForOptionInHamburger() throws Exception {
			System.out.println("Verify CTAs Event when user clicks on any option in hamburger menu");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyCTAsEventForOptionInHamburger();
		}
		
		@Test(priority = 40)
		@Parameters({ "userType", "keyword4"})
		public void verifyVideoWatchDurationEventForFreeContentComplete(String userType,String keyword4) throws Exception {
			System.out.println("Verify Video Watch Duration Event when user completely watches For Free Content");
			zee5PWABusinessLogic.verifyVideoWatchDurationEventForFreeContentComplete(userType,keyword4);
		}
		
		@Test(priority = 41)
		@Parameters({"keyword1"})
		public void verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(String keyword1) throws Exception {
			System.out.println("Verify Video Watch Duration Event when video is closed abruptly For Content From Search Page");
			zee5PWABusinessLogic.relaunch();
			zee5PWABusinessLogic.verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(keyword1);
		}
		
		
	
	@AfterTest
	public void teardown()
	{
		System.out.println("teardown");
		zee5PWABusinessLogic.tearDown();
	}

	

}
