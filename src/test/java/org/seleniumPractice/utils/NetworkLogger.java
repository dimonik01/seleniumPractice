package org.seleniumPractice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.devtools.v125.network.model.Request;
import org.openqa.selenium.devtools.v125.network.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Optional;

public class NetworkLogger {
    private static final Logger log = LoggerFactory.getLogger(NetworkLogger.class);
    private DevTools devTools;
    private boolean isLoggingEnabled = false;

    public void enableLogging(WebDriver driver) {
        if (!(driver instanceof HasDevTools)) {
            log.warn("Driver does not support DevTools");
            return;
        }

        try {
            devTools = ((HasDevTools) driver).getDevTools();

            // Проверяем, есть ли активная сессия через рефлексию
            // Это альтернатива отсутствующему hasActiveSession()
            boolean hasActiveSession = false;
            try {
                Field cdpSessionField = DevTools.class.getDeclaredField("cdpSession");
                cdpSessionField.setAccessible(true);
                Object session = cdpSessionField.get(devTools);
                hasActiveSession = session != null;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.debug("Could not access cdpSession field, assuming no active session", e);
            }

            // Если сессии нет, создаем новую
            if (!hasActiveSession) {
                log.debug("Creating new DevTools session via reflection");
                // В Selenium 4.21.0 createSession() должен быть доступен
                devTools.createSession(); // Создаём сессию, если её нет
            }

            // Включаем мониторинг сети
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

            // Логируем все запросы
            devTools.addListener(Network.requestWillBeSent(), request -> {
                Request req = request.getRequest();
                log.info("→ [{}] {}", req.getMethod(), req.getUrl());
            });

            // Логируем все ответы
            devTools.addListener(Network.responseReceived(), response -> {
                Response res = response.getResponse();
                log.info("← [{}] {} - Status: {}",
                        response.getType(),
                        res.getUrl(),
                        res.getStatus());
            });

            // Логируем ошибки сетевых запросов
            devTools.addListener(Network.loadingFailed(), event -> {
                log.error("❌ FAILED REQUEST: {} - Error: {}",
                        event.getRequestId(),
                        event.getErrorText());
            });

            isLoggingEnabled = true;
            log.info("Network logging enabled successfully");
        } catch (Exception e) {
            log.error("Failed to enable network logging", e);
        }
    }

    public void disableLogging() {
        if (devTools != null && isLoggingEnabled) {
            try {
                devTools.clearListeners();
                devTools.send(Network.disable());

                // Закрываем сессию DevTools
                // В Selenium 4.21.0 close() должен быть доступен
                devTools.close();

                isLoggingEnabled = false;
                log.info("Network logging disabled successfully");
            } catch (Exception e) {
                log.error("Failed to disable network logging", e);
            }
        }
    }
}