package selenium.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.Utilities;

public class HomeMailPage extends BasePage {

    @FindBy(xpath = "//a[@aria-label='Log out']")
    WebElement logoutLink;

    @FindBy(className = "PSHeader-User")
    WebElement accountName;

    public HomeMailPage(WebDriver driver) {
        super(driver);

        Utilities.takeScreenshot(driver);
    }

    @Override
    protected String GetURL() {
        return URL;
    }

    public void logout() {

        accountName.click();
        logoutLink.click();
    }

    public Boolean isLoggedIn() {

        try {
            return accountName.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
