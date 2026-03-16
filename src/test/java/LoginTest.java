import org.junit.jupiter.api.DisplayName;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegisterPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginTest {
    WebDriver driver;
    WebDriverFactory factory = new WebDriverFactory();

    @ParameterizedTest
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    @ValueSource(strings = {"chrome"})
    public void loginLogYourAccount(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);

// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        // Переходим на логин

        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Оформить заказ']")));
    }


    @ParameterizedTest
    @DisplayName("вход через личный кабинет")
    @ValueSource(strings = {"chrome"})
    public void loginPersonalAccountButton(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);

// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        // Переходим на логин

        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Оформить заказ']")));
    }

    @ParameterizedTest
    @DisplayName("вход через кнопку зарегестрироваться")
    @ValueSource(strings = {"chrome"})
    public void loginRegisterButton(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(RegisterPage.BASE_URL);

// После открытия главной страницы, заходим и вносим имя и пароль
        new RegisterPage(driver).clickRegisterButton();
        new RegisterPage(driver).clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        // Переходим на логин

        loginPage.login("evgen88@ya.ru", "123456"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Оформить заказ']")));
    }

    @ParameterizedTest
    @DisplayName("вход через кнопку в форме восстановления пароля")
    @ValueSource(strings = {"chrome"})

    public void loginRecoverPassword(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(LoginPage.BASE_URL);

// После открытия главной страницы, заходим и вносим имя и пароль
        new LoginPage(driver).clickRecoverPassword();
        new RegisterPage(driver).clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        // Переходим на логин

        loginPage.login("evgen88@ya.ru", "123456"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Оформить заказ']")));
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}
}
