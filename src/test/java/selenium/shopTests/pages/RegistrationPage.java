package selenium.shopTests.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.WebDriverFactory;

import java.time.Duration;

public class RegistrationPage extends BasePage {

    //Personal information

    @FindBy(id = "customer_firstname")
    WebElement firstNameField;

    @FindBy(id = "customer_lastname")
    WebElement lastNameField;

    @FindBy(id = "passwd")
    WebElement passwordField;

    //Address

    @FindBy(id = "address1")
    WebElement addressField;

    @FindBy(id = "city")
    WebElement cityField;

    @FindBy(id = "id_state")
    WebElement stateSelect;

    @FindBy(id = "postcode")
    WebElement postalCodeField;

    @FindBy(id = "phone_mobile")
    WebElement mobileField;

    @FindBy(id = "submitAccount")
    WebElement registerButton;

    public void createAccount(String firstName, String lastName, String password, String address, String city, String state, String postalCode, String phone) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        passwordField.sendKeys(password);

        WebDriverFactory.scrollIntoView(registerButton);

        addressField.sendKeys(address);
        cityField.sendKeys(city);
        selectState(state);
        postalCodeField.sendKeys(postalCode);
        mobileField.sendKeys(phone);

        registerButton.click();

        MyAccountPage myAccountPage = new MyAccountPage();

        //explicit waiter with polling frequency 500 ms
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(Duration.ofMillis(500));
        wait.until(driver -> myAccountPage.isLoggedIn());
    }

    private void selectState(String state) {

        Select selectElement = new Select(stateSelect);
        selectElement.selectByVisibleText(state);
    }

    public boolean isOpened() {
        try {
            return firstNameField.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
