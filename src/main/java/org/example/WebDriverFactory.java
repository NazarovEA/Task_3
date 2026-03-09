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
                            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                            return new org.openqa.selenium.chrome.ChromeDriver();
            case "yandex":
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().browserVersion("142").setup();
                org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
                options.setBinary("C:/Program Files/Yandex/YandexBrowser/Application/browser.exe");
                options.addArguments("--remote-allow-origins=*");

                return new org.openqa.selenium.chrome.ChromeDriver(options);
            default:
                throw new RuntimeException("Браузер " + browserName + " не поддерживается");
        }
    }
}
