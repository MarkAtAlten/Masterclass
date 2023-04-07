package tests;

import io.netty.util.Timeout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AltenPopup {

    WebDriver driver;

    @Test
    void mytest(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        this.driver = new ChromeDriver();

        Duration duration = Duration.ofSeconds(30);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        driver.manage().window().maximize();
        driver.get("http://www.alten.nl");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("tarteaucitronPersonalize2")));
        driver.findElement(By.id("tarteaucitronPersonalize2")).click();
    }

    @Test
    void mytest2(){
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.alten.nl");

        try {
            driver.findElement(By.id("tarteaucitronPersonalize2")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }
}
