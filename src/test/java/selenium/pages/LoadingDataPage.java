package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadingDataPage {
    private final String LOADING_DATA_URL = "https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html";

    private final By getUserButton = By.cssSelector("#save");
    private final By userInfoElement = By.cssSelector("#loading br");

    private static WebDriver driver;

    public LoadingDataPage(WebDriver driver) {
        this.driver = driver;
        driver.get(LOADING_DATA_URL);
    }

    public LoadingDataPage getRandomUser() {
        driver.findElement(getUserButton).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(userInfoElement));

        return this;
    }

    public boolean isUserDisplayed() {
        return driver.findElement(userInfoElement).isEnabled();
    }
}
