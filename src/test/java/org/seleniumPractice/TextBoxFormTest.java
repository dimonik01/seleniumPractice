package org.seleniumPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TextBoxFormTest {
    private WebDriver driver;
    private String nameParam = "John Doe";
    private String emailParam = "john.doe@example.com";
    private String currentAddressParam = "123 Main St";
    private String permanentAddressParam = "456 Oak Ave";

    private void fillForm(String nameParam, String emailParam, String currentAddressParam, String permanentAddressParam){
        WebElement fullName = driver.findElement(By.id("userName"));
        WebElement email = driver.findElement(By.id("userEmail"));
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        WebElement submitButton = driver.findElement(By.id("submit"));
        fullName.sendKeys(nameParam);
        email.sendKeys(emailParam);
        currentAddress.sendKeys(currentAddressParam);
        permanentAddress.sendKeys(permanentAddressParam);
        submitButton.click();
    }

    @BeforeAll
    static void driverSetup(){
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void initDriver(){
        driver.get("https://demoqa.com/text-box");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    void quitDriver(){
        if (driver != null) driver.quit();
    }

    @Test
    void shouldDisplayCorrectNameInOutput(){
        fillForm(nameParam, emailParam, currentAddressParam, permanentAddressParam);
        assertThat(driver.findElement(By.cssSelector("p#name")).getText()).isEqualTo("Name:" + nameParam);
    }

    @Test
    void shouldDisplayCorrectEmailInOutput(){
        fillForm(nameParam, emailParam, currentAddressParam, permanentAddressParam);
        assertThat(driver.findElement(By.cssSelector("p#email")).getText()).isEqualTo("Email:" + emailParam);
    }

    @Test
    void shouldDisplayCorrectCurrentAddressInOutput(){
        fillForm(nameParam, emailParam, currentAddressParam, permanentAddressParam);
        assertThat(driver.findElement(By.cssSelector("p#currentAddress")).getText()).isEqualTo("Current Address :" + currentAddressParam);
    }

    @Test
    void shouldDisplayCorrectPermanentAddressInOutput(){
        fillForm(nameParam, emailParam, currentAddressParam, permanentAddressParam);
        assertThat(driver.findElement(By.cssSelector("p#permanentAddress")).getText()).isEqualTo("Permananet Address :" + permanentAddressParam);
    }
}
