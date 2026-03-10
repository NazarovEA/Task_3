package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By recoverPassword = By.xpath(".//a[text()='Восстановить пароль']");


    public void clickRecoverPassword() {
        driver.findElement(recoverPassword).click();
    }
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}