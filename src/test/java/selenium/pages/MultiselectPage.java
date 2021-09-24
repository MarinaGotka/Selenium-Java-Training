package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class MultiselectPage extends BasePage{
    private final String MULTISELECT_URL = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

    private final By select = By.id("multi-select");
    private final By options = By.xpath("//select[@id = 'multi-select']/option");

    Select selectElement;

    public MultiselectPage(WebDriver driver) {
        super(driver);

        driver.get(MULTISELECT_URL);
    }

    public void selectRandomOptions(int numberOptions) {
        List<WebElement> optionsElements = driver.findElements(options);
        selectElement = new Select(driver.findElement(select));

        for (int i = numberOptions; i > 0; i--) {
            int index = new Random().nextInt(optionsElements.size());
            selectElement.selectByIndex(index);
        }
    }

    public List<WebElement> getAllSelectedOptions() {
        return selectElement.getAllSelectedOptions();
    }
}
