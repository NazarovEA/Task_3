import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeTest {
    WebDriver driver;

    @Test
    @DisplayName("Переход к начинкам")
    public void fillingsOpen() {
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
        //добавляем ожидание, так как не успевает страница открыться
        homePage.waitForOrderButton();

       //new HomePage(driver).clickBuns();
      // new HomePage(driver).clickSauces();
       new HomePage(driver).clickFillings();
        assertTrue(homePage.fillingsTabActive(), "Вкладка 'Начинки' не стала активной после клика!");

    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}

    @Test
    @DisplayName("Переход к соусам")
    public void sausesOpen() {
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
        //добавляем ожидание, так как не успевает страница открыться
        homePage.waitForOrderButton();

        //new HomePage(driver).clickBuns();
        new HomePage(driver).clickSauces();
        assertTrue(homePage.saucesTabActive(), "Вкладка 'соусы' не стала активной после клика!");

    }

    @Test
    @DisplayName("Переход к булкам")
    public void bunsOpen() {
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
        //добавляем ожидание, так как не успевает страница открыться
        homePage.waitForOrderButton();


        new HomePage(driver).clickBuns();
        assertTrue(homePage.bunsTabActive(), "Вкладка 'булки' не стала активной после клика!");
    }


}
