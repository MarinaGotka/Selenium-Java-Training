package selenium.shopTests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.WebDriverFactory;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = WebDriverFactory.driver;
        PageFactory.initElements(driver, this);
    }
}
