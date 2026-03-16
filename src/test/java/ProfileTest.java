import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProfilePage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest {
    WebDriver driver;
    WebDriverFactory factory = new WebDriverFactory();

    @Test
    @DisplayName("Переход в конструктор из ЛК через кнопку Конструктор")
    public void inConstructor() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);

// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickLoginButton();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        // Переходим на логин

        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся

        new HomePage(driver).clickPersonalAccountButton();
        new ProfilePage(driver).clickConstructorButton();

        assertTrue(homePage.isOrderButtonDisplayed(), "Не удалось перейти в конструктор!");
    }

    @Test
    @DisplayName("Переход в конструктор из ЛК через логотип")
    public void inLogo() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);

// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickLoginButton();

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        // Переходим на логин
        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся

        new HomePage(driver).clickPersonalAccountButton();
        new HomePage(driver).clickLogo();
        assertTrue(homePage.isOrderButtonDisplayed(), "Не удалось перейти в конструктор!");
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void outAccount() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);

// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickLoginButton();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        // Переходим на логин

        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся

        new HomePage(driver).clickPersonalAccountButton();
        new ProfilePage(driver).clickOutAccountButton();

        boolean isLoggedOut = loginPage.isLoginButtonDisplayed();

        assertTrue(isLoggedOut, "После выхода не открылась страница входа!");
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}
}