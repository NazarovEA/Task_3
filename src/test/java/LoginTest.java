import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {
    WebDriver driver;
    WebDriverFactory factory = new WebDriverFactory();

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginLogYourAccount() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);
        HomePage homePage = new HomePage(driver);

// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        // Переходим на логин

        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        homePage.waitForOrderButton();
        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");
    }


    @Test
    @DisplayName("вход через личный кабинет")
    public void loginPersonalAccountButton() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);
        HomePage homePage = new HomePage(driver);
// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        // Переходим на логин

        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        homePage.waitForOrderButton();
        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");
    }

    @Test
    @DisplayName("вход через кнопку зарегестрироваться")
    public void loginRegisterButton() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(RegisterPage.BASE_URL);
        HomePage homePage = new HomePage(driver);
// После открытия главной страницы, заходим и вносим имя и пароль
        new RegisterPage(driver).clickRegisterButton();
        new RegisterPage(driver).clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        // Переходим на логин

        loginPage.login("evgen88@ya.ru", "123456"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        homePage.waitForOrderButton();
        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")

    public void loginRecoverPassword() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(LoginPage.BASE_URL);
        HomePage homePage = new HomePage(driver);
// После открытия главной страницы, заходим и вносим имя и пароль
        new LoginPage(driver).clickRecoverPassword();
        new RegisterPage(driver).clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        // Переходим на логин

        loginPage.login("evgen88@ya.ru", "123456"); // Логинимся
        //добавляем ожидание, так как не успевает страница открыться
        homePage.waitForOrderButton();
        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");


    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}
}
