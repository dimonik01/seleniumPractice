package org.seleniumPractice.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.seleniumPractice.pages.PracticeFormPage;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;

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
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-images"); // Ускоряет загрузку, отключая изображения
        options.addArguments("--disable-javascript"); // ВРЕМЕННО: Проверим, не в JS ли проблема (убери потом!)

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3)); // Увеличиваем таймаут загрузки

        practiceFormPage = new PracticeFormPage(driver);

        // Логируем начало загрузки
        System.out.println("=== Начинаем загрузку страницы ===");
        long startTime = System.currentTimeMillis();

        driver.get("https://google.com");

        long loadTime = System.currentTimeMillis() - startTime;
        System.out.println("=== Страница загружена за " + loadTime + " мс ===");

        // Выводим базовую информацию о странице
        System.out.println("Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Вызываем JavaScript для диагностики производительности
        logPerformanceMetrics();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldFillFirstName() {
        practiceFormPage.fillFirstName("John");
        assertThat(practiceFormPage.getFirstNameInputText()).isEqualTo("John");
    }

    // Вспомогательный метод для диагностики
    private void logPerformanceMetrics() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Получаем сетевые метрики
            Object timing = js.executeScript(
                    "return JSON.stringify(window.performance.getEntries(), null, 2);"
            );
            System.out.println("=== NETWORK TIMING ===\n" + timing);

            // Получаем время загрузки страницы
            Long loadTime = (Long) js.executeScript(
                    "return performance.timing.loadEventEnd - performance.timing.navigationStart;"
            );
            System.out.println("Page Load Time (JS): " + loadTime + " ms");

            // Считаем количество сетевых запросов
            Long requestCount = (Long) js.executeScript(
                    "return window.performance.getEntries().length;"
            );
            System.out.println("Total Network Requests: " + requestCount);

            // Считаем AJAX-запросы
            Long ajaxCount = (Long) js.executeScript(
                    "return window.performance.getEntriesByType('resource')"
                            + ".filter(r => r.initiatorType === 'xmlhttprequest' || r.initiatorType === 'fetch').length;"
            );
            System.out.println("AJAX/Fetch Requests: " + ajaxCount);

            // Проверяем, есть ли ещё активные запросы (responseEnd === 0 означает, что запрос ещё не завершён)
            Long pendingRequests = (Long) js.executeScript(
                    "return window.performance.getEntriesByType('resource')"
                            + ".filter(r => r.responseEnd === 0).length;"
            );
            System.out.println("Pending Network Requests: " + pendingRequests);

            // Проверяем, есть ли ошибки в консоли
            Object consoleErrors = js.executeScript(
                    "return window.performance.getEntriesByType('navigation')[0].type;"
            );
            System.out.println("Navigation Type: " + consoleErrors);

        } catch (Exception e) {
            System.out.println("Could not execute performance script: " + e.getMessage());
        }
    }
}