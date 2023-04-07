package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage{
    private final WebDriver driver;

    private final By newLoanBy = By.id("request1");

    public WelcomePage(WebDriver driver){
        this.driver = driver;
    }

    public void selectNewLoan(){
        driver.findElement(newLoanBy).click();
    }

    }