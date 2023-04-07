package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SimpleLoanPage {
    private final WebDriver driver;

    private final By amountBy = By.xpath("//input[@placeholder='Amount']");
    private final By whyNextBy = By.xpath("//h3[contains(text(),'Request loan')]/following-sibling::form//button[@type='submit']");

    private final By acknowledgeNextBy = By.xpath("//h3[contains(text(),'Acknowledgement')]/following-sibling::form//button[@type='submit']");

    private final By personalGenderBy = By.xpath("//select[@formcontrolname='gender']");
    private final By personalFirstNameBy = By.xpath("//input[@formcontrolname='firstname']");
    private final By personalLastNameBy = By.xpath("//input[@formcontrolname='lastname']");
    private final By personalDateOfBirthBy = By.xpath("//input[@formcontrolname='dob']");
    private final By personalAddressBy = By.xpath("//input[@formcontrolname='address']");
    private final By personalZipCodeBy = By.xpath("//input[@formcontrolname='zipcode']");
    private final By personalCityBy = By.xpath("//input[@formcontrolname='city']");
    private final By personalMaritalStatusBy = By.xpath("//select[@formcontrolname='maritalStatus']");
    private final By personalIncomeBy = By.xpath("//input[@formcontrolname='income']");
    private final By personalIncomeTypeBy = By.xpath("//select[@formcontrolname='incometype']");
    private final By personalNextBy = By.xpath("//h3[contains(text(),'Personal details')]/following-sibling::form//button[@type='submit']");

    private final By verifyRequestTheLoan = By.xpath("//h3[contains(text(),'Verify your information')]/following-sibling::form//button[@type='submit']");
    private final By errorMessage = By.xpath("//div[@class='col-lg-12 col-xl-12 alert alert-danger']");

    private final By orderId = By.cssSelector("#requestId");

    public SimpleLoanPage(WebDriver driver){
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Mini-loan (minimum €300)')]")));
    }

    public void selectLoanType(String type){
        driver.findElement(By.xpath("//label[contains(text(),'" +type+"')]")).click();
    }

    public void setLoan(String amount){
        driver.findElement(amountBy).clear();
        driver.findElement(amountBy).sendKeys(amount);
    }

    public void clickWhyLoanNext(){
        driver.findElement(whyNextBy).click();
    }

    public void fillWhyDetails(String type, String amount){
        this.selectLoanType(type);
        this.setLoan(amount);
        this.clickWhyLoanNext();
    }

    //now comes the Acknowledgement section
    public void setKnowledgeRadio(String value){
        driver.findElement(By.xpath("//input[@formcontrolname='knowledge'][@value='"+value+"']")).click();
    }

    public void acknowledgeSelectNext(){
        driver.findElement(acknowledgeNextBy).click();
    }

    //now methods for filling personal details
    public void setPersonalDetails(String gender, String firstName, String lastName, String dateOfBirth, String address, String zipCode, String city, String maritalStatus, String income, String incomeType){
        WebElement genderDropDownEle = driver.findElement(personalGenderBy);
        Select genderSelect = new Select(genderDropDownEle);
        genderDropDownEle.click();
        genderSelect.selectByVisibleText(gender);

        driver.findElement(personalFirstNameBy).sendKeys(firstName);
        driver.findElement(personalLastNameBy).sendKeys(lastName);
        driver.findElement(personalDateOfBirthBy).sendKeys(dateOfBirth);
        driver.findElement(personalAddressBy).sendKeys(address);
        driver.findElement(personalZipCodeBy).sendKeys(zipCode);
        driver.findElement(personalCityBy).sendKeys(city);

        WebElement maritalStatusEle = driver.findElement(personalMaritalStatusBy);
        Select maritalSelect = new Select(maritalStatusEle);
        maritalStatusEle.click();
        maritalSelect.selectByVisibleText(maritalStatus);

        driver.findElement(personalIncomeBy).sendKeys(income);
        WebElement incomeTypeEle = driver.findElement(personalIncomeTypeBy);
        Select incomeTypeSelect = new Select(incomeTypeEle);
        incomeTypeEle.click();
        incomeTypeSelect.selectByVisibleText(incomeType);
        driver.findElement(personalNextBy).click();
    }

    public String getVerifyValue(String valueName){
        By verifyBy = By.xpath("//h3[contains(text(),'Verify your information')]/following-sibling::form//strong[contains(text(),'" + valueName +":')]/parent::td/following-sibling::td");
        return driver.findElement(verifyBy).getText();
    }

    public void clickVerifyNext(){
        driver.findElement(verifyRequestTheLoan).click();
    }

    public String getOrderId() { return driver.findElement(orderId).getText(); }

    public String getErrorString(){
        return driver.findElement(errorMessage).getText();
    }





}
