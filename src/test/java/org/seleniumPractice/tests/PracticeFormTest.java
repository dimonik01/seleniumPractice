package org.seleniumPractice.tests;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumPractice.pages.PracticeFormPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PracticeFormTest {
    private WebDriver driver;
    PracticeFormPage practiceFormPage;

    @BeforeAll
    static void initBrowser(){

    }

    @BeforeEach
    void visitPage(){
        driver = new ChromeDriver();
        practiceFormPage = new PracticeFormPage(driver);
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @AfterEach
    void teardown(){
        if (driver != null) driver.quit();
    }

    @Test
    void launch(){
        practiceFormPage.fillFirstName("text");
        assertThat(practiceFormPage.getFirstNameInputText()).contains("text");
    }


}
