package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().clearDriverCache().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                // 1. Отключаем менеджер паролей и всплывающие окна
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.addArguments("--disable-notifications");

                // 2. Полностью выключаем сервис проверки паролей через настройки профиля
                chromeOptions.setExperimentalOption("prefs", java.util.Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false
                ));

                return new ChromeDriver(chromeOptions);

            case "yandex":
                // Для Яндекса (версия 144/145) используем тот же метод
                WebDriverManager.chromedriver().clearDriverCache().setup();

                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:/Program Files/Yandex/YandexBrowser/Application/browser.exe");
                options.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(options);

            default:
                throw new RuntimeException("Браузер " + browserName + " не поддерживается");
        }
    }
}
