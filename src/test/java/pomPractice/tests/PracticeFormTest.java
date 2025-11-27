package pomPractice.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pomPractice.pages.PracticeFormPage;

public class PracticeFormTest {

    private WebDriver driver;
    private PracticeFormPage practiceFormPage;

    @BeforeAll
    static void initBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void visitPage() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--disable-features=IsolateOrigins,site-per-process");
        driver = new ChromeDriver(options);
        practiceFormPage = new PracticeFormPage(driver);
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldFillForm() {
        practiceFormPage.fillFirstName("John");
        practiceFormPage.fillLastName("Lennon");
        practiceFormPage.fillEmail("text@gmail.com");
        practiceFormPage.selectGender("male");
        practiceFormPage.fillMobile("1234567890");
        practiceFormPage.submitForm();
        practiceFormPage.getSubmittedData();
    }
}