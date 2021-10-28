package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadingDataPage extends BasePage{
    private final String URL = "https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html";

    @FindBy(css = "#save")
    WebElement getUserButton;

    @FindBy(css = "#loading br")
    WebElement userInfoElement;

    public LoadingDataPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getURL() {
        return URL;
    }

    public LoadingDataPage getRandomUser() {
        getUserButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(driver -> userInfoElement.isEnabled());

        return this;
    }

    public boolean isUserDisplayed() {
        return userInfoElement.isEnabled();
    }
}
