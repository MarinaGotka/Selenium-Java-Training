package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private final By logInBannerButton = By.xpath("//div[@class = 'HeadBanner-ButtonsWrapper']//a[2]");

    private final By logInButton = By.id("passp:sign-in");
    private final By usernameField = By.xpath("//div[@class = 'passp-login-form']//input[@name= 'login']");
    private final By passwordField = By.cssSelector("#passp-field-passwd");

    public LoginPage(WebDriver driver) {
       super(driver);
    }

    public HomeMailPage login(String username, String password) {

        driver.findElement(logInBannerButton).click();

        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        driver.findElement(logInButton).click();

        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        driver.findElement(logInButton).click();

        HomeMailPage homeMailPage = new HomeMailPage(driver);

        //explicit waiter with polling frequency 500 ms
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(Duration.ofMillis(500));
        wait.until(driver -> homeMailPage.isLoggedIn());

        return homeMailPage;
    }
}
