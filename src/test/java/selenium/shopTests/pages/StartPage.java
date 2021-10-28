package selenium.shopTests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage extends BasePage{

    @FindBy(className = "login")
    WebElement signInButton;

    public void invokeSignIn(){
        signInButton.click();

        AuthenticationPage authenticationPage = new AuthenticationPage();

        //explicit waiter with polling frequency 500 ms
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(Duration.ofMillis(500));
        wait.until(driver -> authenticationPage.isOpened());
    }
}
