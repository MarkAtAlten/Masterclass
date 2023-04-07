package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SimpleLoanPage;
import pages.WelcomePage;


class POMTest {

    WebDriver driver;

    @Test
    void UI_exercise1() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");
        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.selectNewLoan();
        SimpleLoanPage simpleLoanPage = new SimpleLoanPage(driver);
        simpleLoanPage.fillWhyDetails("Mini-loan (minimum €300)", "400");

        simpleLoanPage.setKnowledgeRadio("yes");
        simpleLoanPage.acknowledgeSelectNext();

        simpleLoanPage.setPersonalDetails("Female", "myName", "myLastName", "01-01-2013", "myStreet 123", "1233AL", "myCity", "Single", "12000", "Temporary Contract");

        //Now validate the loan and select next
        Assertions.assertEquals("Mini-loan", simpleLoanPage.getVerifyValue("Loantype"));
        Assertions.assertEquals("€400", simpleLoanPage.getVerifyValue("Amount"));
        Assertions.assertEquals("yes", simpleLoanPage.getVerifyValue("Acknowledgement"));
        Assertions.assertEquals("Female", simpleLoanPage.getVerifyValue("Gender"));
        Assertions.assertEquals("myName", simpleLoanPage.getVerifyValue("Firstname"));
        Assertions.assertEquals("myLastName", simpleLoanPage.getVerifyValue("Lastname"));
        Assertions.assertEquals("Jan 1, 2013", simpleLoanPage.getVerifyValue("Date of birth"));
        Assertions.assertEquals("myStreet 123", simpleLoanPage.getVerifyValue("Address"));
        Assertions.assertEquals("1233AL", simpleLoanPage.getVerifyValue("Zipcode"));
        Assertions.assertEquals("myCity", simpleLoanPage.getVerifyValue("City"));
        Assertions.assertEquals("12000", simpleLoanPage.getVerifyValue("Income"));
        Assertions.assertEquals("Single", simpleLoanPage.getVerifyValue("Marital status"));
        Assertions.assertEquals("Temporary Contract", simpleLoanPage.getVerifyValue("Income type"));
        simpleLoanPage.clickVerifyNext();

        //now get ID for new order
        System.out.print(simpleLoanPage.getOrderId());
        driver.close();
    }

    @Test
    void UI_exercise2() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");
        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.selectNewLoan();
        SimpleLoanPage simpleLoanPage = new SimpleLoanPage(driver);
        simpleLoanPage.fillWhyDetails("Mortgage (minimum €50,000)", "4000");
        Assertions.assertEquals("The desired amount is lower than the minimum amount (€50,000) of the selected loan type Mortgage. You may want to choose another loan type.", simpleLoanPage.getErrorString());

        driver.close();
    }
}