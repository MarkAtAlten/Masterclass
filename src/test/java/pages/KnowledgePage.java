package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KnowledgePage extends ImprovedSimpleLoanPage {

    public KnowledgePage(WebDriver driver) {
        super(driver);
    }

    //now comes the Acknowledgement section
    public void setKnowledgeRadio(String value){
        driver.findElement(By.xpath("//input[@formcontrolname='knowledge'][@value='"+value+"']")).click();
    }
    public void SelectNext(){
        selectNextButton("Acknowledgement");
    }
}
