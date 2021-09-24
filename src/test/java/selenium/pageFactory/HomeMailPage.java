package selenium.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.BasePage;

public class HomeMailPage extends BasePage {

    private final String accountNameLocator = "PSHeader-User";

    @FindBy(xpath = "//a[@aria-label='Log out']")
    WebElement logoutLink;

    @FindBy(className = accountNameLocator)
    WebElement accountName;

    public HomeMailPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
        takeScreenshot();
    }

    public void logout() {

        accountName.click();
        logoutLink.click();
    }

    public Boolean isLoggedIn() {

        return !driver.findElements(By.className(accountNameLocator)).isEmpty();
    }
}
