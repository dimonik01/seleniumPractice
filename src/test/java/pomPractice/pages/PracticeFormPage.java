package pomPractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticeFormPage {
    private WebDriver driver;
    //локаторы
    private static final By firstNameInput = By.id("firstName");
    private static final By lastNameInput = By.id("lastName");
    private static final By emailInput = By.id("userEmail");
    private static final By radiobuttonMale = By.id("gender-radio-1");
    private static final By radiobuttonFemale = By.id("gender-radio-2");
    private static final By radiobuttonOther = By.id("gender-radio-3");
    private static final By mobileInput = By.id("userNumber");
    private static final By submitButton = By.id("submit");
    private static final By table = By.cssSelector(".table > tbody");

    public String getFirstNameInputText(){
        return driver.findElement(firstNameInput).getAttribute("value");
    }

    public String getLastNameInputText(){
        return driver.findElement(lastNameInput).getText();
    }

    public String getEmailInputText(){
        return driver.findElement(emailInput).getText();
    }

    public boolean getRadiobuttonMaleStatus(){
        return driver.findElement(radiobuttonMale).isEnabled();
    }

    public boolean getRadiobuttonFemaleStatus(){
        return driver.findElement(radiobuttonFemale).isEnabled();
    }

    public boolean getRadiobuttonOtherStatus(){
        return driver.findElement(radiobuttonOther).isEnabled();
    }

    public String getMobileInputText(){
        return driver.findElement(mobileInput).getText();
    }

    public String getSubmitButton(){
        return driver.findElement(submitButton).getText();
    }

    public By getTable(){
        return table;
    }

    public PracticeFormPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillFirstName(String value){
        driver.findElement(firstNameInput).sendKeys(value);
    }

    public void fillLastName(String value){
        driver.findElement(lastNameInput).sendKeys(value);
    }

    public void fillEmail(String value){
        driver.findElement(emailInput).sendKeys(value);
    }

    public void selectGender(String gender) throws NullPointerException{
        if (gender == "male") driver.findElement(radiobuttonMale).click();
        if (gender == "female") driver.findElement(radiobuttonFemale).click();
        if (gender == "other") driver.findElement(radiobuttonOther).click();
    }

    public void fillMobile(String value){
        driver.findElement(mobileInput).sendKeys(value);
    }

    public void submitForm(){
        driver.findElement(submitButton).click();
    }

    public void getSubmittedData(){
        System.out.println(table);
    }
}
