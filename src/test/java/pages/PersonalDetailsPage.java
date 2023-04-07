package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import person.Location;
import person.Names;

public class PersonalDetailsPage extends ImprovedSimpleLoanPage{
    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void setPersonalGender(String gender){ this.selectAndSetDropdown(getSelectBy("gender"), gender);}
    public void setPersonalFirstName(String firstName){ driver.findElement(getinputBy("firstname")).sendKeys(firstName); }
    public void setPersonalLastName(String lastName){ driver.findElement(getinputBy("lastname")).sendKeys(lastName); }
    public void setPersonalDateOfBirth(String dob){ driver.findElement(getinputBy("dob")).sendKeys(dob); }
    public void setPersonalAddress(String address){ driver.findElement(getinputBy("address")).sendKeys(address); }
    public void setPersonalZipcode(String zipcode){ driver.findElement(getinputBy("zipcode")).sendKeys(zipcode); }
    public void setPersonalCity(String firstname){ driver.findElement(getinputBy("city")).sendKeys(firstname); }
    public void setPersonalMaritalStatus(String maritalStatus){ this.selectAndSetDropdown(getSelectBy("maritalStatus"), maritalStatus); }
    public void setPersonalIncome(String firstname){ driver.findElement(getinputBy("income")).sendKeys(firstname); }
    public void setPersonalIncomeType(String incomeType){ this.selectAndSetDropdown(getSelectBy("incometype"), incomeType); }

    public void SelectNext(){
        selectNextButton("Personal details");
    }

    private By getSelectBy(String formValue){
        return By.xpath("//select[@formcontrolname='"+formValue+"']");
    }

    private By getinputBy(String formValue){
        return By.xpath("//input[@formcontrolname='"+formValue+"']");
    }

    //now methods for filling personal details
    public void setAllPersonalDetails(String gender, Names names, String dateOfBirth, Location location, String maritalStatus, String income, String incomeType){
        this.setPersonalGender(gender);
        this.setPersonalFirstName(names.getFirstName());
        this.setPersonalLastName(names.getLastName());
        this.setPersonalDateOfBirth(dateOfBirth);
        this.setPersonalAddress(location.getAddress());
        this.setPersonalZipcode(location.getZipcode());
        this.setPersonalCity(location.getCity());
        this.setPersonalMaritalStatus(maritalStatus);
        this.setPersonalIncome(income);
        this.setPersonalIncomeType(incomeType);
    }

}
