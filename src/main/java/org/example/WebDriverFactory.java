package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver getWebDriver(String browserName) {
        WebDriverManager.chromedriver().setup();
        switch (browserName.toLowerCase()) {
                        case "chrome":
                return new ChromeDriver();
            case "yandex":
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:/Program Files/Yandex/YandexBrowser/Application/browser.exe");
                return new ChromeDriver(options);
            default:
                throw new RuntimeException("Браузер " + browserName + " не поддерживается");
        }
    }
}
