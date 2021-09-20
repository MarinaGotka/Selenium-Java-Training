package selenium.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeMailPage {

    private static WebDriver driver;
    private final String accountNameLocator = "PSHeader-User";

    @FindBy(xpath = "//a[@aria-label='Log out']")
    WebElement logoutLink;

    @FindBy(className = accountNameLocator)
    WebElement accountName;

    public HomeMailPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {

        accountName.click();
        logoutLink.click();
    }

    public Boolean isLoggedIn() {

        return !driver.findElements(By.className(accountNameLocator)).isEmpty();
    }
}
