package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final String URL = "https://mail.yandex.com/";

    @FindBy(xpath = "//div[@class = 'HeadBanner-ButtonsWrapper']//a[2]")
    WebElement logInBannerButton;

    @FindBy(id = "passp:sign-in")
    WebElement logInButton;

    @FindBy(xpath = "//div[@class = 'passp-login-form']//input[@name= 'login']")
    WebElement usernameField;

    @FindBy(css = "#passp-field-passwd")
    WebElement passwordField;

    @FindBy(className = "PSHeader-User")
    WebElement accountName;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String GetURL() {
        return URL;
    }

    public void login(String username, String password) {

        logInBannerButton.click();

        usernameField.clear();
        usernameField.sendKeys(username);
        logInButton.click();

        passwordField.clear();
        passwordField.sendKeys(password);
        logInButton.click();

        HomeMailPage homeMailPage = new HomeMailPage(driver);

        //explicit waiter with polling frequency 500 ms
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(Duration.ofMillis(500));
        wait.until(driver -> homeMailPage.isLoggedIn());
    }
}
