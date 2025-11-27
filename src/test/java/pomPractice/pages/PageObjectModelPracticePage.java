package pomPractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageObjectModelPracticePage {
    private WebDriver driver;
    // в полях класса будут локаторы
    private By fullNameInput = By.id("userName");
    private By submitButton = By.id("submit");
    private By nameOutput = By.id("name");


    // конструктор, который позволяет выбирать разные браузеры в качестве драйвера
    public PageObjectModelPracticePage(WebDriver driver){
        this.driver = driver;
    }

    //методы действий
    public void enterFullName(String name) {
        driver.findElement(fullNameInput).sendKeys(name);
    }

    public void submit() {
        driver.findElement(submitButton).click();
    }
    //методы получения данных
    public String getDisplayedName() {
        return driver.findElement(nameOutput).getText();
    }

}
