package tests;

import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//This is an example of how a test masterclass participant may have written casus assignment
//'UI Exercise 1' and 'UI Exercise 2'
class DemoTest {

    WebDriver driver;

    @Test
    void UI_Excecise1() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");

        driver.findElement(By.id("request1")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//label[contains(text(), 'Mini-loan')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Amount']")).sendKeys("400");
        driver.findElement(By.xpath("//h3[contains(text(),'Request loan')]/following-sibling::form//button[@type='submit']")).click();

        driver.findElement(By.xpath("//input[@formcontrolname='knowledge'][@value='yes']")).click();
        driver.findElement(By.xpath("//h3[contains(text(),'Acknowledgement')]/following-sibling::form//button[@type='submit']")).click();

        //Now fill personal details
        new Select(driver.findElement(By.xpath("//select[@formcontrolname='gender']"))).selectByVisibleText("Female");
        driver.findElement(By.xpath("//input[@formcontrolname='firstname']")).sendKeys("myName");
        driver.findElement(By.xpath("//input[@formcontrolname='lastname']")).sendKeys("myLastName");
        driver.findElement(By.xpath("//input[@formcontrolname='dob']")).sendKeys("01-01-2013");
        driver.findElement(By.xpath("//input[@formcontrolname='address']")).sendKeys("myStreet 123");
        driver.findElement(By.xpath("//input[@formcontrolname='zipcode']")).sendKeys("1233AL");
        driver.findElement(By.xpath("//input[@formcontrolname='city']")).sendKeys("myCity");
        new Select(driver.findElement(By.xpath("//select[@formcontrolname='maritalStatus']"))).selectByVisibleText("Single");
        driver.findElement(By.xpath("//input[@formcontrolname='income']")).clear();
        driver.findElement(By.xpath("//input[@formcontrolname='income']")).sendKeys("12000");
        new Select(driver.findElement(By.xpath("//select[@formcontrolname='incometype']"))).selectByVisibleText("Temporary Contract");

        driver.findElement(By.xpath("//h3[contains(text(),'Personal details')]/following-sibling::form//button[@type='submit']")).click();

        //Now validate the loan and select next
        Assertions.assertEquals("Mini-loan", driver.findElement(By.xpath(getVerifyString("Loantype:"))).getText());
        Assertions.assertEquals("€400", driver.findElement(By.xpath(getVerifyString("Amount:"))).getText());
        Assertions.assertEquals("yes", driver.findElement(By.xpath(getVerifyString("Acknowledgement:"))).getText());
        Assertions.assertEquals("Female", driver.findElement(By.xpath(getVerifyString("Gender:"))).getText());
        Assertions.assertEquals("myName", driver.findElement(By.xpath(getVerifyString("Firstname:"))).getText());
        Assertions.assertEquals("myLastName", driver.findElement(By.xpath(getVerifyString("Lastname:"))).getText());
        Assertions.assertEquals("Jan 1, 2013", driver.findElement(By.xpath(getVerifyString("Date of birth:"))).getText());
        Assertions.assertEquals("myStreet 123", driver.findElement(By.xpath(getVerifyString("Address:"))).getText());
        Assertions.assertEquals("1233AL", driver.findElement(By.xpath(getVerifyString("Zipcode:"))).getText());
        Assertions.assertEquals("myCity", driver.findElement(By.xpath(getVerifyString("City:"))).getText());
        Assertions.assertEquals("12000", driver.findElement(By.xpath(getVerifyString("Income:"))).getText());
        Assertions.assertEquals("Single", driver.findElement(By.xpath(getVerifyString("Marital status:"))).getText());
        Assertions.assertEquals("Temporary Contract", driver.findElement(By.xpath(getVerifyString("Income type:"))).getText());
        driver.findElement(By.xpath("//h3[contains(text(),'Verify your information')]/following-sibling::form//button[@type='submit']")).click();

        //now get ID for new order
        String orderId = driver.findElement(By.cssSelector("#requestId")).getText();
        System.out.print(orderId);
        driver.close();
    }

    @Test
    void UI_Excecise2() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://loanapplication.azurewebsites.net/");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("request1")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//label[contains(text(), 'Mortgage')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Amount']")).sendKeys("400");
        driver.findElement(By.xpath("//h3[contains(text(),'Request loan')]/following-sibling::form//button[@type='submit']")).click();

        WebElement errorEle = driver.findElement(By.xpath(("//div[@class='col-lg-12 col-xl-12 alert alert-danger']")));
        Assertions.assertEquals("The desired amount is lower than the minimum amount (€50,000) of the selected loan type Mortgage. You may want to choose another loan type.", errorEle.getText());
        driver.close();
    }

    private String getVerifyString(String valueName) {
        return "//table[@id='verifyTable']//strong[contains(text(),'" + valueName + "')]/parent::td/following-sibling::td";
    }
}