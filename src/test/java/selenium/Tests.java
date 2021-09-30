package selenium;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.pages.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests {

    private WebDriver driver;

    @BeforeEach
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @DisplayName("Multiselect test")
    @Test
    void multiselectTest() {
        int numberSelections = 3;

        MultiselectPage multiselectPage = new MultiselectPage(driver);
        multiselectPage.selectRandomOptions(numberSelections);

        Assert.assertTrue(multiselectPage.getAllSelectedOptions().size() == numberSelections);
    }

    @DisplayName("Alert box test")
    @Test
    void alertBoxTest() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.clickForAlertBox();

        Assert.assertTrue(alertPage.isAlertBoxMessageCorrect());
    }

    @DisplayName("Confirm box test - Click Cancel button")
    @Test
    void confirmBoxCancelTest() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.clickCancelForConfirmBox();

        Assert.assertTrue(alertPage.isConfirmCancelClicked());
    }

    @DisplayName("Confirm box test - Click Ok button")
    @Test
    void confirmBoxOkTest() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.clickOkForConfirmBox();

        Assert.assertTrue(alertPage.isConfirmOkClicked());
    }

    @DisplayName("Loading the data dynamically test.")
    @Test
    void loadUserTest() {
        LoadingDataPage loadingDataPage = new LoadingDataPage(driver);
        loadingDataPage.getRandomUser();

        Assert.assertTrue(loadingDataPage.isUserDisplayed());
    }

    @DisplayName("Progress Bar test.")
    @Test
    void progressBarTest() {
        ProgressBarPage progressBarPage = new ProgressBarPage(driver);
        progressBarPage.startDownload();
        progressBarPage.waitUntilPercent(50);

        Assert.assertTrue(progressBarPage.getPercentValue() >= 50);

        progressBarPage.refresh();

        Assert.assertTrue(progressBarPage.getPercentValue() == 0);
    }

    @DisplayName("Sort and Search test.")
    @Test
    void sortAndSearchTest() {
        TablePage tablePage = new TablePage(driver);
        tablePage.selectEntriesCount(10);
        List<Employee> list = tablePage.selectEntries(20, 200000);

        Assert.assertNotNull(list);

        for (Employee e : list) {
            System.out.println(e.toString());
        }
    }

    @AfterEach
    public void tearDown() {

        driver.close();
        driver.quit();
    }
}
