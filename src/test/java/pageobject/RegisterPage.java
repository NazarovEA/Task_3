package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/register";

    private final By nameField = By.xpath("//input[@name='name']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordNotCorrect = By.xpath(".//p[text()='Некорректный пароль']");

    @Step("Заполнение формы регистрации")
    public void register(String name, String email, String password){
    driver.findElement(nameField).sendKeys(name);
    driver.findElement(emailField).sendKeys(email);
    driver.findElement(passwordField).sendKeys(password);
}

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    public void clickLoginButton() {
        By loginLink = By.xpath("//a[@href='/login']");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginLink))
                .click();
    }
    public boolean passwordErrorMessage() {
        return driver.findElement(passwordNotCorrect).isDisplayed();
    }

}
