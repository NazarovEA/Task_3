import org.junit.jupiter.api.DisplayName;
import pageObject.HomePage;
import pageObject.LoginPage;
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

public class HomeTest {
    WebDriver driver;

    @ParameterizedTest
    @DisplayName("Переход к начинкам")
    @ValueSource(strings = {"chrome"})
    public void fillingsOpen(String browser) {
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

       //new HomePage(driver).clickBuns();
      // new HomePage(driver).clickSauces();
       new HomePage(driver).clickFillings();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.fillingsTabActive(), "Вкладка 'Начинки' не стала активной после клика!");

    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}

    @ParameterizedTest
    @DisplayName("Переход к соусам")
    @ValueSource(strings = {"chrome"})
    public void sausesOpen(String browser) {
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

        //new HomePage(driver).clickBuns();
        new HomePage(driver).clickSauces();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.saucesTabActive(), "Вкладка 'соусы' не стала активной после клика!");

    }

    @ParameterizedTest
    @DisplayName("Переход к булкам")
    @ValueSource(strings = {"chrome"})
    public void bunsOpen(String browser) {
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


        new HomePage(driver).clickBuns();
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.bunsTabActive(), "Вкладка 'булки' не стала активной после клика!");
    }


}
