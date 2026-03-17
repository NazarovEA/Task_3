import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegisterPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {
    WebDriver driver;
    private String accessToken;
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User("Evgeny", "evgen" + System.currentTimeMillis() + "@ya.ru", "012345");
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginLogYourAccount() {
        driver.get(RegisterPage.BASE_URL);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Evgeny", user.getEmail(), user.getPassword());

        Response response = given()
                .log().all()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(user)
                .post("https://stellarburgers.education-services.ru/api/auth/login");
        accessToken = response.path("accessToken");

        LoginPage loginPage = new LoginPage(driver);
        // Используем объект homePage вместо создания нового через new HomePage(driver)
        HomePage homePage = new HomePage(driver);
        //homePage.clickLoginButton();

        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        homePage.waitForOrderButton();
        assertTrue(homePage.isOrderButtonDisplayed());
}

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginLogYourAccount2() {
        driver.get(HomePage.BASE_URL);

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        new HomePage(driver).clickLoginButton();
        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword()); // Логинимся
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


    }}
