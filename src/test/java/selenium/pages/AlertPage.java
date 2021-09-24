package selenium.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertPage extends BasePage{

    private final String ALERT_URL = "https://www.seleniumeasy.com/test/javascript-alert-box-demo.html";

    private final By alertBoxButton = By.xpath("//button[@onclick= 'myAlertFunction()']");
    private final By confirmBoxButton = By.xpath("//button[@onclick= 'myConfirmFunction()']");

    private final String alertBoxMessage = "I am an alert box!";
    private final String confirmResultOkMessage = "You pressed OK!";
    private final String confirmResultCancelMessage = "You pressed Cancel!";

    private final By confirmResult = By.cssSelector("#confirm-demo");

    private Alert alert;
    private String actualAlertText;

    public AlertPage(WebDriver driver) {
        super(driver);

        driver.get(ALERT_URL);
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
        driver.findElement(alertBoxButton).click();
        acceptAlert();
    }

    public void clickOkForConfirmBox() {
        driver.findElement(confirmBoxButton).click();
        acceptAlert();
    }

    public void clickCancelForConfirmBox() {
        driver.findElement(confirmBoxButton).click();
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
}