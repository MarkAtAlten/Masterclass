package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SimpleLoanPage;
import pages.WelcomePage;


class POMTest {

    WebDriver driver;

    @Test
    void UI_exercise1() {
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
        Assertions.assertEquals("Mini-loan", driver.findElement(By.xpath(getVerifyString("Loantype:"))).getText());
        Assertions.assertEquals("€400", driver.findElement(By.xpath(getVerifyString("Amount:"))).getText());
        driver.close();
    }

    @Test
    void GetPersonalLoan() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");
        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.selectNewLoan();
        SimpleLoanPage simpleLoanPage = new SimpleLoanPage(driver);
        simpleLoanPage.fillWhyDetails("Personal", "4000");

        simpleLoanPage.setKnowledgeRadio("yes");
        simpleLoanPage.acknowledgeSelectNext();
        simpleLoanPage.setPersonalDetails("Female", "myName", "myLastName", "01-01-2013", "myStreet 123", "1233AL", "myCity", "Single", "12000", "Temporary Contract");

        //Now validate the loan and select next
        Assertions.assertEquals("Personal loan", simpleLoanPage.getVerifyValue("Loantype"));
        Assertions.assertEquals("€4,000", simpleLoanPage.getVerifyValue("Amount"));
        driver.close();
    }

    @Test
    void GetResolvingCredit() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");
        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.selectNewLoan();
        SimpleLoanPage simpleLoanPage = new SimpleLoanPage(driver);
        simpleLoanPage.fillWhyDetails("Revolving credit", "5000");

        simpleLoanPage.setKnowledgeRadio("yes");
        simpleLoanPage.acknowledgeSelectNext();
        simpleLoanPage.setPersonalDetails("Female", "myName", "myLastName", "01-01-2013", "myStreet 123", "1233AL", "myCity", "Single", "12000", "Temporary Contract");

        //Now validate the loan and select next
        Assertions.assertEquals("Revolving credit", simpleLoanPage.getVerifyValue("Loantype"));
        Assertions.assertEquals("€5,000", simpleLoanPage.getVerifyValue("Amount"));
        driver.close();
    }

    @Test
    void GetCarLoan() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");
        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.selectNewLoan();
        SimpleLoanPage simpleLoanPage = new SimpleLoanPage(driver);
        simpleLoanPage.fillWhyDetails("Car-loan", "2456");

        simpleLoanPage.setKnowledgeRadio("yes");
        simpleLoanPage.acknowledgeSelectNext();
        simpleLoanPage.setPersonalDetails("Female", "myName", "myLastName", "01-01-2013", "myStreet 123", "1233AL", "myCity", "Single", "12000", "Temporary Contract");

        //Now validate the loan and select next
        Assertions.assertEquals("Car-loan", simpleLoanPage.getVerifyValue("Loantype"));
        Assertions.assertEquals("€2,456", simpleLoanPage.getVerifyValue("Amount"));
        driver.close();
    }

    @Test
    void Mortgage() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");
        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.selectNewLoan();
        SimpleLoanPage simpleLoanPage = new SimpleLoanPage(driver);
        simpleLoanPage.fillWhyDetails("Mortgage", "100000");

        simpleLoanPage.setKnowledgeRadio("yes");
        simpleLoanPage.acknowledgeSelectNext();
        simpleLoanPage.setPersonalDetails("Female", "myName", "myLastName", "01-01-2013", "myStreet 123", "1233AL", "myCity", "Single", "12000", "Temporary Contract");

        //Now validate the loan and select next
        Assertions.assertEquals("Mortgage", simpleLoanPage.getVerifyValue("Loantype"));
        Assertions.assertEquals("€100,000", simpleLoanPage.getVerifyValue("Amount"));
        driver.close();
    }

    private String getVerifyString(String valueName) {
        return "//table[@id='verifyTable']//strong[contains(text(),'" + valueName + "')]/parent::td/following-sibling::td";
    }
}