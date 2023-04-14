package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FancyRequest {

    WebDriver driver;

    @AfterEach
    public void do_stuff_after(){
        driver.close();
    }

    @Test
    public void requestFancy() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);

        driver.get("https://loanapplication.azurewebsites.net/");

        driver.findElement(By.xpath("//a[@id='request2']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Mini')])[1]"))).click();
        driver.findElement(By.xpath("//span[contains(text(),'Furn')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='modal-header']//button"))).click();
        driver.findElement(By.xpath("//input[@data-placeholder='Amount']")).sendKeys("400");
        driver.findElement(By.xpath("(//span[contains(text(), 'Next')])[1]")).click();

        driver.findElement(By.xpath("//span[contains(text(), 'I have')]")).click();
        driver.findElement(By.xpath("(//span[contains(text(), 'Next')])[2]")).click();

        WebElement next_button = driver.findElement(By.xpath("(//span[contains(text(), 'Next')])[3]"));
//        next_button.sendKeys(Keys.RETURN);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next_button);
        next_button.sendKeys(Keys.RETURN);
//        wait.until(ExpectedConditions.visibilityOf(next_button));
//        actions.moveToElement(next_button, 0, 5).perform();
//        next_button.click();

        slaap(4);
    }

    void slaap(int tijd_in_s) throws InterruptedException {
        try {
            Thread.sleep(tijd_in_s*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
