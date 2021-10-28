package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.Employee;

import java.util.ArrayList;
import java.util.List;

public class TablePage extends BasePage {
    private final String URL = "https://www.seleniumeasy.com/test/table-sort-search-demo.html";

    @FindBy(css = "select[name = 'example_length']")
    WebElement selectEntriesCountElement;

    @FindBy(xpath = "//table/tbody/tr")
    List<WebElement> tableRowElements;

    @FindBy(xpath = "//a[@class = 'paginate_button next']")
    WebElement nextButton;

    private final String tableRowElementFormat = "//table/tbody/tr[%s]/%s";

    public TablePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getURL() {
        return URL;
    }

    public void selectEntriesCount(int count) {
        Select selectElement = new Select(selectEntriesCountElement);
        selectElement.selectByValue(String.valueOf(count));
    }

    public List<Employee> selectEntries(int ageMin, double salaryMax) {
        List<Employee> employees = new ArrayList<>();

        do {
            employees.addAll(selectEmployees(tableRowElements, ageMin, salaryMax));

            if (!nextButton.isDisplayed()) {
                nextButton.click();
            } else {
                break;
            }
        }
        while (true);

        return employees;
    }

    private List<Employee> selectEmployees(List<WebElement> rows, int ageMin, double salaryMax) {
        List<Employee> employees = new ArrayList<>();

        for (int i = 1; i <= rows.size(); i++) {

            int age = Integer.valueOf(driver.findElement(By.xpath(String.format(tableRowElementFormat, i, "td[4]"))).getText());
            String salaryText = driver.findElement(By.xpath(String.format(tableRowElementFormat, i, "td[6]"))).getText();
            double salary = Double.valueOf(salaryText.substring(1, salaryText.length() - 2).replaceAll(",", ""));

            if (age >= ageMin && salary <= salaryMax) {

                String name = driver.findElement(By.xpath(String.format(tableRowElementFormat, i, "td[1]"))).getText();
                String position = driver.findElement(By.xpath(String.format(tableRowElementFormat, i, "td[2]"))).getText();
                String office = driver.findElement(By.xpath(String.format(tableRowElementFormat, i, "td[3]"))).getText();

                employees.add(new Employee(name, position, office));
            }
        }

        return employees;
    }
}