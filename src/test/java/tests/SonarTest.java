package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import person.Location;
import person.Names;

class SonarTest {

    WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void UI_exercise1() {
        WelcomePage welcomePage = new WelcomePage(driver);
        ImprovedSimpleLoanPage simpleLoanPage = new ImprovedSimpleLoanPage(driver);
        WhyLoanPage whyLoanPage = new WhyLoanPage(driver);
        KnowledgePage knowledgePage = new KnowledgePage(driver);
        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);

        Location location = new Location();
        location.setAddress("myStreet 123");
        location.setZipcode("1233AL");
        location.setCity("myCity");
        Names names = new Names();
        names.setFirstName("myName");
        names.setLastName("myLastName");

        driver.get("https://loanapplication.azurewebsites.net/");

        welcomePage.selectNewLoan();
        whyLoanPage.setDetailsSelectNext("Mini-loan (minimum €300)", "400");
        knowledgePage.setKnowledgeRadio("yes");
        knowledgePage.SelectNext();
        personalDetailsPage.setAllPersonalDetails("Female", names, "01-01-2013", location, "Single", "12000", "Temporary Contract");
        personalDetailsPage.SelectNext();

        //Now validate the loan and select next
        Assertions.assertEquals("Mini-loan", simpleLoanPage.getVerifyValue("Loantype"));
        Assertions.assertEquals("€400", simpleLoanPage.getVerifyValue("Amount"));
        Assertions.assertEquals("yes", simpleLoanPage.getVerifyValue("Acknowledgement"));
        Assertions.assertEquals("Female", simpleLoanPage.getVerifyValue("Gender"));
        Assertions.assertEquals(names.getFirstName(), simpleLoanPage.getVerifyValue("Firstname"));
        Assertions.assertEquals(names.getLastName(), simpleLoanPage.getVerifyValue("Lastname"));
        Assertions.assertEquals("Jan 1, 2013", simpleLoanPage.getVerifyValue("Date of birth"));
        Assertions.assertEquals(location.getAddress(), simpleLoanPage.getVerifyValue("Address"));
        Assertions.assertEquals(location.getZipcode(), simpleLoanPage.getVerifyValue("Zipcode"));
        Assertions.assertEquals(location.getCity(), simpleLoanPage.getVerifyValue("City"));
        Assertions.assertEquals("12000", simpleLoanPage.getVerifyValue("Income"));
        Assertions.assertEquals("Single", simpleLoanPage.getVerifyValue("Marital status"));
        Assertions.assertEquals("Temporary Contract", simpleLoanPage.getVerifyValue("Income type"));
        simpleLoanPage.clickVerifyNext();

        //now get ID for new order
        System.out.print(simpleLoanPage.getOrderId());
    }

    @Test
    void UI_exercise2() {
        WelcomePage welcomePage = new WelcomePage(driver);
        driver.get("https://loanapplication.azurewebsites.net/");

        welcomePage.selectNewLoan();
        WhyLoanPage whyLoanPage = new WhyLoanPage(driver);
        whyLoanPage.setDetailsSelectNext("Mortgage (minimum €50,000)", "4000");

        ImprovedSimpleLoanPage simpleLoanPage = new ImprovedSimpleLoanPage(driver);
        Assertions.assertEquals("The desired amount is lower than the minimum amount (€50,000) of the selected loan type Mortgage. You may want to choose another loan type.", simpleLoanPage.getErrorString());
    }

    @AfterEach //has been changed from @After
    public void tearDown() {
        driver.close();
    }

}