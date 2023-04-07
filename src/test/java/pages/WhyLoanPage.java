package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WhyLoanPage extends ImprovedSimpleLoanPage {
    private final By amountBy = By.xpath("//input[@placeholder='Amount']");

    public WhyLoanPage(WebDriver driver) {
        super(driver);
    }

    public void selectLoanType(String type){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Mini-loan (minimum €300)')]")));
        driver.findElement(By.xpath("//label[contains(text(),'" +type+"')]")).click();
    }

    public void setLoan(String amount){
        driver.findElement(amountBy).clear();
        driver.findElement(amountBy).sendKeys(amount);
    }

    public void selectNext(){
        this.selectNextButton("Request loan");
    }

    public void setDetailsSelectNext(String type, String amount){
        this.selectLoanType(type);
        this.setLoan(amount);
        this.selectNext();
    }
}
