package Mypage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyWelcomePage {

    public void SelectNewLoan(WebDriver driver){
        driver.findElement(By.id("request1")).click();
    }

    public void SelectNewLoan2(WebDriver driver){
        driver.findElement(By.id("request2")).click();
    }

    public void showLoanRequets(WebDriver driver){
        driver.findElement(By.id("request2")).click();
    }
}
