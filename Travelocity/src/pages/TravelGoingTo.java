package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TravelGoingTo {
	WebDriver driver;
	By GoingTo = By.id("hotel-destination-hp-hotel");

	By findCheckIn = By.id("hotel-checkin-hp-hotel");
	By findCheckOut = By.id("hotel-checkout-hp-hotel");
	By findRooms = By.id("hotel-rooms-hp-hotel");

	By Adults1 = By.id("hotel-1-adults-hp-hotel");
	By Children1 = By.id("hotel-1-children-hp-hotel");
	By Child1Age1 = By.id("hotel-1-age-select-1-hp-hotel");

	By Adults2 = By.id("hotel-2-adults-hp-hotel");
	By Children2 = By.id("hotel-2-children-hp-hotel");
	By Child2Age1 = By.id("hotel-2-age-select-1-hp-hotel");
	By Child2Age2 = By.id("hotel-2-age-select-2-hp-hotel");

	By AddFlight = By.id("hotel-add-flight-checkbox-hp-hotel");
	By AddCar = By.id("hotel-add-car-checkbox-hp-hotel");

	@SuppressWarnings("static-access")
	By submit = By
		.id("gcw-hotel-form-hp-hotel")
		.xpath("//*[@type='submit']");
	// By submit = By.xpath("//*[@id='gcw-hotel-form-hp-hotel']//*[@type='submit']");

	By titleText = By.id("primary-header-package");

	public TravelGoingTo(WebDriver driver) { this.driver = driver; }

	/** Set location, check in, and check out */
	public void newString(By getBy, String getString) {
		driver.findElement(getBy).sendKeys(getString);
	}

	/** Set number of rooms */
	public void selectString(By getBy, String getString) {
		new Select( driver.findElement(getBy) )
			.selectByVisibleText(getString);
	}

	/** Set number of adults */
	public void setAdults(String[] getAdults) {
		this.selectString(Adults1, getAdults[0]);
		this.selectString(Adults2, getAdults[1]);
	}

	/** Set number of children */
	public void setChildren(String[][] getChildren) {
		this.selectString(Children1, getChildren[0][0]);
		this.selectString(Child1Age1, getChildren[0][1]);

		this.selectString(Children2, getChildren[1][0]);
		this.selectString(Child2Age1, getChildren[1][1]);
		this.selectString(Child2Age2, getChildren[1][2]);
	}

	/** Add flight/car */
	public void checkBox(By getBy, boolean getBool) {
		if (getBool) {
			driver.findElement(getBy).click();
		}
	}

	/** Click on submit button */
	public void clickSubmit() {
		driver
			.findElement(submit)
			.click();
	}

	/** Get the title of submit page */
	public String getSubmitTitle() { return driver.findElement(titleText).getText(); }

	/**
	 * This POM method will be exposed in test case to submit the application
	 * @param location
	 * @param rooms
	 * @param time
	 * @param adults
	 * @param children
	 * @param travel
	 * @return
	 */
	public void travelocitySearch(String location, String rooms, String[] time, String[] adults, String[][] children, boolean[] travel) {
		String checkIn = time[0], checkOut = time[1];
		boolean flight = travel[0], car = travel[1];

		/** Fill location */
		this.newString(GoingTo, location);
		/** Fill check-in time */
		this.newString(findCheckIn, checkIn);
		/** Fill check-out time */
		this.newString(findCheckOut, checkOut);
		/** Fill rooms */
		this.selectString(findRooms, rooms);
		/** Add number of adults */
		this.setAdults(adults);
		/** Add number of children */
		this.setChildren(children);
		/** Add a flight */
		this.checkBox(AddFlight, flight);
		/** Add a car */
		this.checkBox(AddCar, car);
		/** Click submit button */
		this.clickSubmit();
	}
}