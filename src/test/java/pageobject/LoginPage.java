package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerButton = By.xpath("//a[@href='/register']");
    private final By headerLoginPage = By.xpath(".//h2[text()='Вход']");

    @Step("Проверка отображения заголовка 'Вход'")
    public boolean headerLoginPage() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerLoginPage))
                .isDisplayed();
    }

    @Step("Кнопка зарегестрироваться на странице логин,Вы — новый пользователь?")
    public void clickRegisterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(registerButton))
                .click();
    }
    @Step("Проверить, отображается ли кнопка 'Войти'")
        public boolean isLoginButtonDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton))
                .isDisplayed();}

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/login";

    // Выносим локатор из теста в константу класса
    private final By emailInputField = By.name("name");

    @Step("Ожидание загрузки страницы логина")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Вводим данные для аторизации и нажимаем кнопку войти")
    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    private final By forgotPasswordLink = By.xpath("//a[@href='/forgot-password']");

    public void clickForgotPasswordButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(forgotPasswordLink))
                .click();
    }
}