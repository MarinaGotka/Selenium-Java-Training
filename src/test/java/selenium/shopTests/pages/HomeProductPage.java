package selenium.shopTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium.WebDriverFactory;

public class HomeProductPage extends AccountHeaderPage {

    String productByNameXpath = "//*[@id = 'homefeatured']//li//h5/a[@title='%s']";

    public void viewProduct(String productName) {
        WebElement product = driver.findElement(By.xpath(String.format(productByNameXpath, productName)));

        WebDriverFactory.scrollIntoView(product);
        product.click();
    }
}
