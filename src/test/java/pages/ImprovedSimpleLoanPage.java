package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ImprovedSimpleLoanPage {
    final WebDriver driver;

    private final By errorMessage = By.xpath("//div[contains(@class,'alert-danger')]");
    private final By orderId = By.cssSelector("#requestId");

    public ImprovedSimpleLoanPage(WebDriver driver){
        this.driver = driver;
    }

    public void selectNextButton(String headerValue){
        driver.findElement(By.xpath("//h3[contains(text(),'"+headerValue+"')]/following-sibling::form//button[@type='submit']")).click();
    }

    public String getVerifyValue(String valueName){
        By verifyBy = By.xpath("//h3[contains(text(),'Verify your information')]/following-sibling::form//strong[contains(text(),'" + valueName +":')]/parent::td/following-sibling::td");
        return driver.findElement(verifyBy).getText();
    }

    public void clickVerifyNext(){
        this.selectNextButton("Verify your information");
    }

    public String getOrderId() { return driver.findElement(orderId).getText(); }

    public String getErrorString(){
        return driver.findElement(errorMessage).getText();
    }

    public void selectAndSetDropdown(By dropDownBy, String value){
        new Select(driver.findElement(dropDownBy)).selectByVisibleText(value);
    }
}
