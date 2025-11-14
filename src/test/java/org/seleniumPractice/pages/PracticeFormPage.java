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

    public PracticeFormPage(WebDriver driver){
        this.driver = driver;
    }

    void fillFirstName(String value){
        driver.findElement(firstNameInput).sendKeys(value);
    }

    void fillLastName(String value){
        driver.findElement(lastNameInput).sendKeys(value);
    }

    void fillEmail(String value){
        driver.findElement(emailInput).sendKeys(value);
    }

    void selectGender(String gender) throws NullPointerException{
        if (gender == "male") driver.findElement(radiobuttonMale).click();
        if (gender == "female") driver.findElement(radiobuttonFemale).click();
        if (gender == "other") driver.findElement(radiobuttonOther).click();
    }

    void fillMobile(String value){
        driver.findElement(mobileInput).sendKeys(value);
    }

    void submitForm(){
        driver.findElement(submitButton).click();
    }

    public String getSubmittedData(){
        String data =
       return data;
    }
}
