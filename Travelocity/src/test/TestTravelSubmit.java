package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.TravelHomePage;
import pages.TravelGoingTo;

public class TestTravelSubmit {

	WebDriver Driver;
	TravelGoingTo ObjSubmit;
	TravelHomePage ObjHomePage;

	@BeforeTest
	public void Setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\List_of_Jar\\chromedriver.exe");
		Driver = new ChromeDriver();
		Driver
			.manage()
			.timeouts()
			.implicitlyWait(10, TimeUnit.SECONDS);
		Driver.get("https://www.travelocity.com/");
	}

	/**
	 * This test case will go to https://www.travelocity.com/
	 * Enters in travel information
	 * Searches for hotels
	 */
	@Test(priority = 0)
	public void test_Home_Page_Appear_Correct() {
		/** Create submit page object */
		ObjSubmit = new TravelGoingTo(Driver);

		/** Verify submit page title */
		String SubmitPageTitle = ObjSubmit.getSubmitTitle();
		Assert.assertTrue(SubmitPageTitle.toLowerCase().contains("vacation packages"));

		/** Submit to application */
		String location = "Baltimore, Maryland", rooms = "2";
		String[] time = { "07/20/2019", "07/25/2019" };
		String[] adult = { "2", "1" };
		String[][] children = {
			{ "1", "10" },
			{ "2", "4", "5" }
		};
		boolean[] travel = { false, true };
		ObjSubmit.travelocitySearch(location, rooms, time, adult, children, travel);

		/** Go the next page */
		ObjHomePage = new TravelHomePage(Driver);

		/** Verify home page */
		Assert
			.assertTrue(
				ObjHomePage
					.getHomePageDashboardUserName()
					.toLowerCase()
					.contains("start by choosing your hotel")
			);
	}
}