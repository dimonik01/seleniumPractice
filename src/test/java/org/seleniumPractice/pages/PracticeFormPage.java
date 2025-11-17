package org.seleniumPractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticeFormPage {
    private WebDriver driver;
    //локаторы
    private By firstNameInput = By.id("firstName");
    private By lastNameInput = By.id("lastName");
    private By emailInput = By.id("userEmail");
    private By radiobuttonMale = By.id("gender-radio-1");
    private By radiobuttonFemale = By.id("gender-radio-2");
    private By radiobuttonOther = By.id("gender-radio-3");
    private By mobileInput = By.id("userNumber");
    private By submitButton = By.id("submit");
    private By table = By.cssSelector(".table > tbody");

    public String getFirstNameInputText(){
        return driver.findElement(firstNameInput).getText();
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
        return driver.findElement(radiobuttonMale).isEnabled();
    }

    public boolean getRadiobuttonOtherStatus(){
        return driver.findElement(radiobuttonMale).isEnabled();
    }

    public String getMobileInputText(){
        return driver.findElement(radiobuttonMale).getText();
    }

    public String getSubmitButton(){
        return driver.findElement(radiobuttonMale).getText();
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
