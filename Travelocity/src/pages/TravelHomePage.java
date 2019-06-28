package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelHomePage {
    WebDriver driver;
    By homePageUserName = By.xpath("//*header//*[@class='section-header-main']");

    public TravelHomePage(WebDriver driver) { this.driver = driver; }

    /** Get the User name from Home Page */
    public String getHomePageDashboardUserName() {
        return driver
			.findElement(homePageUserName)
			.getText();
    }
}