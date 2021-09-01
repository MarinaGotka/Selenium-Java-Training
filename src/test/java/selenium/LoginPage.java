package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private final By logInBannerButton = By.xpath("//div[@class = 'HeadBanner-ButtonsWrapper']//a[2]");

    private final By logInButton = By.id("passp:sign-in");
    private final By usernameField = By.xpath("//div[@class = 'passp-login-form']//input[@name= 'login']");
    private final By passwordField = By.cssSelector("#passp-field-passwd");
    private final By accountName = By.className("PSHeader-User");

    private static WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void login(String username, String password){

        driver.findElement(logInBannerButton).click();

        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        driver.findElement(logInButton).click();

        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        driver.findElement(logInButton).click();
    }

    public Boolean isLoggedIn(){

        return  driver.findElement(accountName).isDisplayed();
    }
}
