package Mypage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WhyLoan {

    public void selectLoanType(WebDriver driver, String loanType){
        driver.findElement(By.xpath("//label[contains(text(), '" + loanType + "')]")).click();
    }

    public void setAmount(WebDriver driver, Integer amount){
        driver.findElement(By.xpath("//input[@placeholder='Amount']")).sendKeys(amount.toString());
    }

    public void selectNextButton(WebDriver driver){
        driver.findElement(By.xpath("//h3[contains(text(),'Request loan')]/following-sibling::form//button[@type='submit']")).click();
    }
}
