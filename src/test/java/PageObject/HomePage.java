package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    // Кнопка "Войти в аккаунт"
    private final By loginButtonMain = By.xpath(".//button[text()='Войти в аккаунт']");
    // Кнопка "Личный кабинет"
    private final By accountButton = By.xpath(".//p[text()='Личный кабинет']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginButton() {
        driver.findElement(loginButtonMain).click();
    }
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }
}
