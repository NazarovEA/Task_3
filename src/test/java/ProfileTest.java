import io.restassured.response.Response;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.ProfilePage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import pageobject.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest {
    WebDriver driver;
    private String accessToken;
    private User user;
    UserApiSteps userApiSteps = new UserApiSteps();
    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registerPage;
ProfilePage profilePage;

    @BeforeEach
    public void setUp() {
        //Создаем пользователя
        user = new User("Evgeny", "evgen" + System.currentTimeMillis() + "@ya.ru", "Naz35514");
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = WebDriverFactory.getWebDriver(browser);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profilePage = new ProfilePage(driver);
        Response response = userApiSteps.userCreate(user);
        accessToken = response.path("accessToken");
        driver.get(HomePage.BASE_URL);
    }

    @Test
    @DisplayName("Переход в конструктор из ЛК через кнопку Конструктор")
    public void inConstructor() {
// После открытия главной страницы, заходим и вносим имя и пароль
        homePage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        homePage.clickPersonalAccountButton();
        profilePage.clickConstructorButton();

      assertTrue(homePage.isOrderButtonDisplayed(), "Не удалось перейти в конструктор!");
    }

    @Test
    @DisplayName("Переход в конструктор из ЛК через логотип")
    public void inLogo() {
        homePage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        homePage.clickPersonalAccountButton();
        homePage.clickLogo();
        assertTrue(homePage.isOrderButtonDisplayed(), "Не удалось перейти в конструктор!");
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void outAccount() {
        homePage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        homePage.clickPersonalAccountButton();
        profilePage.clickOutAccountButton();
        boolean isLoggedOut = loginPage.isLoginButtonDisplayed();
        assertTrue(isLoggedOut, "После выхода не открылась страница входа!");
    }
    @AfterEach
    public void tearDown() {
        userApiSteps.userDelete(accessToken);
        if (driver != null) {
            driver.quit();
        }
    }
}