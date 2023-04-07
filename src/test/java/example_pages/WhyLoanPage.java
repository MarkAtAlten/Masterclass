package example_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ImprovedSimpleLoanPage;

public class WhyLoanPage {
    WebDriver driver;

    //set correct xpath here
    private final By amountBy = By.xpath("some xpath to identifier");

    public WhyLoanPage(WebDriver driver) {
        this.driver = driver;
    }

    //in this method select a particular loan type
    public void selectLoanType(String type){

    }

    //in this method select a particular loan amount
    public void setLoanAmount(String amount){

    }

    //in this method select the next page button
    public void selectNext(){

    }

    //call this method from your test file
    public void setDetailsSelectNext(String type, String amount){
        this.selectLoanType(type);
        this.setLoanAmount(amount);
        this.selectNext();
    }
}
