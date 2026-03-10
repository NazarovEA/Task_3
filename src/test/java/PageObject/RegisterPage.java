package PageObject;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By nameField = By.xpath("//input[@name='name']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordNotCorrect = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");

public void register(String name, String email, String password){
    driver.findElement(nameField).sendKeys(name);
    driver.findElement(emailField).sendKeys(email);
    driver.findElement(passwordField).sendKeys(password);
    driver.findElement(registerButton).click();
}

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public boolean passwordErrorMessage() {
        return driver.findElement(passwordNotCorrect).isDisplayed();
    }
}
