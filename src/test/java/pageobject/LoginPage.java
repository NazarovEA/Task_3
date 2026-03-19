package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //private final By passwordField = By.xpath(".//input[@type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    @Step("Проверить, отображается ли кнопка 'Войти'")
        public boolean isLoginButtonDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton))
                .isDisplayed();}
    private final By recoverPassword = By.xpath(".//a[text()='Восстановить пароль']");

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/login";

    // Выносим локатор из теста в константу класса
    private final By emailInputField = By.name("name");

    @Step("Ожидание загрузки страницы логина")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }


    public void clickRecoverPassword() {
        driver.findElement(recoverPassword).click();
    }
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}