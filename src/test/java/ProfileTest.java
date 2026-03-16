import org.junit.jupiter.api.DisplayName;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProfilePage;
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

public class ProfileTest {
    WebDriver driver;
    WebDriverFactory factory = new WebDriverFactory();

    @ParameterizedTest
    @DisplayName("Переход в конструктор из ЛК через кнопку Конструктор")
    @ValueSource(strings = {"chrome"})
    public void inConstructor(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get("https://stellarburgers.education-services.ru");

// После открытия главной страницы, заходим и вносим имя и пароль
        new HomePage(driver).clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        // Переходим на логин

        loginPage.login("evgen881@ya.ru", "NazarovYandex355"); // Логинимся

        new HomePage(driver).clickPersonalAccountButton();
        new ProfilePage(driver).clickConstructorButton();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Оформить заказ']")));
    }

    @ParameterizedTest
    @DisplayName("Переход в конструктор из ЛК через логотип")
    @ValueSource(strings = {"chrome"})
    public void inLogo(String browser) {
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

        new HomePage(driver).clickPersonalAccountButton();
        new HomePage(driver).clickLogo();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Оформить заказ']")));
    }

    @ParameterizedTest
    @DisplayName("Выход из личного кабинета")
    @ValueSource(strings = {"chrome"})
    public void outAccount(String browser) {
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

        new HomePage(driver).clickPersonalAccountButton();
        new ProfilePage(driver).clickOutAccountButton();

        boolean isOpenButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Войти']")))
                .isDisplayed();

        assertTrue(isOpenButton, "не удалось выйти из личного кабинета!");
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}
}