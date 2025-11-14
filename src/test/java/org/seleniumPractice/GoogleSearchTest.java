package org.seleniumPractice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeAll
    static void driverSetup(){

    }

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown(){
        if (driver != null) driver.quit();
    }

    @Test
    void triggersSuccessfulSearch(){
        driver.get("https://google.com");
        WebElement input = driver.findElement(By.className("gLFyf"));
        input.sendKeys("Selenium WebDriver", Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
        assertFalse(driver.getTitle().contains("Selenium WebDriver"), "Selenium WebDriver");
    }
}
