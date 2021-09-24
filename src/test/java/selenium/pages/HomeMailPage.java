package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeMailPage extends BasePage {

    private final By logoutLink = By.xpath("//a[@aria-label='Log out']");
    private final By accountName = By.className("PSHeader-User");

    public HomeMailPage(WebDriver driver) {
        super(driver);

        this.driver = driver;
    }

    public void logout() {

        driver.findElement(accountName).click();
        driver.findElement(logoutLink).click();
    }

    public Boolean isLoggedIn() {

        return !driver.findElements(accountName).isEmpty();
    }
}
