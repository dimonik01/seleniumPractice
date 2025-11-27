package pomPractice.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pomPractice.pages.PageObjectModelPracticePage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PageObjectModelPracticeTest {
    private WebDriver driver = new ChromeDriver();

    @Test
    void shouldDisplayCorrectName() {
        PageObjectModelPracticePage page = new PageObjectModelPracticePage(driver);
        page.enterFullName("John Doe");
        page.submit();
        assertThat(page.getDisplayedName()).contains("John Doe");
    }
}
