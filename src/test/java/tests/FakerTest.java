package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import com.github.javafaker.Faker;
import person.Location;
import person.Names;


class FakerTest {

    WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach //has been changed from @After
    public void tearDown() {
        driver.close();
    }

    @Test
    void UI_exercise1() {
        WelcomePage welcomePage = new WelcomePage(driver);
        ImprovedSimpleLoanPage simpleLoanPage = new ImprovedSimpleLoanPage(driver);
        WhyLoanPage whyLoanPage = new WhyLoanPage(driver);
        KnowledgePage knowledgePage = new KnowledgePage(driver);
        PersonalDetailsPage personalDetailsPage = new PersonalDetailsPage(driver);

        Faker faker = new Faker();
        String fakerAmount = String.valueOf(faker.number().numberBetween(300, 1000));

        Location location = new Location();
        location.setAddress(faker.address().fullAddress());
        location.setZipcode("1233AL");
        location.setCity("myCity");
        Names names = new Names();
        names.setFirstName(faker.name().firstName());
        names.setLastName("myLastName");

        driver.get("https://loanapplication.azurewebsites.net/");

        welcomePage.selectNewLoan();
        whyLoanPage.setDetailsSelectNext("Mini-loan (minimum €300)", fakerAmount);
        knowledgePage.setKnowledgeRadio("yes");
        knowledgePage.SelectNext();
        personalDetailsPage.setAllPersonalDetails("Female", names, "01-01-2013", location, "Single", "12000", "Temporary Contract");
        personalDetailsPage.SelectNext();

        //Now validate the loan and select next
        Assertions.assertEquals("Mini-loan", simpleLoanPage.getVerifyValue("Loantype"));
        Assertions.assertEquals("€"+fakerAmount, simpleLoanPage.getVerifyValue("Amount"));
    }
}