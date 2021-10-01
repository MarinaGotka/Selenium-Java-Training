package selenium.tests;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import selenium.Employee;
import selenium.pages.*;

import java.util.List;

public class Tests extends TestBase{

    @DisplayName("Multiselect test")
    @Test
    void multiselectTest() {
        int numberSelections = 3;

        MultiselectPage multiselectPage = new MultiselectPage(driver);
        multiselectPage.GoToURL();
        multiselectPage.selectRandomOptions(numberSelections);

        Assert.assertTrue(multiselectPage.getAllSelectedOptions().size() == numberSelections);
    }

    @DisplayName("Alert box test")
    @Test
    void alertBoxTest() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.GoToURL();
        alertPage.clickForAlertBox();

        Assert.assertTrue(alertPage.isAlertBoxMessageCorrect());
    }

    @DisplayName("Confirm box test - Click Cancel button")
    @Test
    void confirmBoxCancelTest() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.GoToURL();
        alertPage.clickCancelForConfirmBox();

        Assert.assertTrue(alertPage.isConfirmCancelClicked());
    }

    @DisplayName("Confirm box test - Click Ok button")
    @Test
    void confirmBoxOkTest() {
        AlertPage alertPage = new AlertPage(driver);
        alertPage.GoToURL();
        alertPage.clickOkForConfirmBox();

        Assert.assertTrue(alertPage.isConfirmOkClicked());
    }

    @DisplayName("Loading the data dynamically test.")
    @Test
    void loadUserTest() {
        LoadingDataPage loadingDataPage = new LoadingDataPage(driver);
        loadingDataPage.GoToURL();
        loadingDataPage.getRandomUser();

        Assert.assertTrue(loadingDataPage.isUserDisplayed());
    }

    @DisplayName("Progress Bar test.")
    @Test
    void progressBarTest() {
        ProgressBarPage progressBarPage = new ProgressBarPage(driver);
        progressBarPage.GoToURL();
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
        tablePage.GoToURL();
        tablePage.selectEntriesCount(10);
        List<Employee> list = tablePage.selectEntries(20, 200000);

        Assert.assertNotNull(list);

        for (Employee e : list) {
            System.out.println(e.toString());
        }
    }
}
