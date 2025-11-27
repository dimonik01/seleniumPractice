package seleniumPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstSeleniumTest {
    private WebDriver driver;

    @BeforeAll
    static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void driverSetup(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void driverExit(){
        if (driver != null) driver.quit();
    }

    @Test
    void shouldGoToGoogle(){
        driver.get("https://demoqa.com/automation-practice-form");
        assertTrue(driver.getTitle().toLowerCase().contains("DEMOQA"), "We're at google");
    }
}
