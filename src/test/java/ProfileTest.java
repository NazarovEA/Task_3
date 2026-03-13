import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.ProfilePage;
import PageObject.RegisterPage;
import org.example.WebDriverFactory;
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
    @ValueSource(strings = {"chrome"})
    public void inLogo(String browser) {
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
        new HomePage(driver).clickLogo();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Оформить заказ']")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome"})
    public void outAccount(String browser) {
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
        new ProfilePage(driver).clickOutAccountButton();

        boolean isOpenButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[text()='Войти']")))
                .isDisplayed();

        assertTrue(isOpenButton, "не удалось выйти из личного кабинета!");
    }
}