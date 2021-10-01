package selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertPage extends BasePage{

    private final String URL = "https://www.seleniumeasy.com/test/javascript-alert-box-demo.html";

    @FindBy(xpath = "//button[@onclick= 'myAlertFunction()']")
    WebElement alertBoxButton;

    @FindBy(xpath = "//button[@onclick= 'myConfirmFunction()']")
    WebElement confirmBoxButton;

    private final String alertBoxMessage = "I am an alert box!";
    private final String confirmResultOkMessage = "You pressed OK!";
    private final String confirmResultCancelMessage = "You pressed Cancel!";

    private final By confirmResult = By.cssSelector("#confirm-demo");

    private Alert alert;
    private String actualAlertText;

    public AlertPage(WebDriver driver) {
        super(driver);
    }

    public void acceptAlert() {
        alert = driver.switchTo().alert();
        actualAlertText = alert.getText();
        alert.accept();
    }

    public void dissmissAlert() {
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void clickForAlertBox() {
        alertBoxButton.click();
        acceptAlert();
    }

    public void clickOkForConfirmBox() {
        confirmBoxButton.click();
        acceptAlert();
    }

    public void clickCancelForConfirmBox() {
        confirmBoxButton.click();
        dissmissAlert();
    }

    public boolean isAlertBoxMessageCorrect() {
        return alertBoxMessage.equals(actualAlertText);
    }

    public boolean isConfirmOkClicked() {
        return driver.findElement(confirmResult).getText().equals(confirmResultOkMessage);
    }

    public boolean isConfirmCancelClicked() {
        return driver.findElement(confirmResult).getText().equals(confirmResultCancelMessage);
    }

    @Override
    protected String GetURL() {

        return URL;
    }
}