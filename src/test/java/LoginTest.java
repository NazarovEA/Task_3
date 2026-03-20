import io.restassured.response.Response;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.ForgotPasswordPage;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegisterPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    WebDriver driver;
    private String accessToken;
    private User user;
    UserApiSteps userApiSteps = new UserApiSteps();
    LoginPage loginPage;
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeEach
    public void setUp() {
        //Создаем пользователя
        user = new User("Evgeny", "evgen" + System.currentTimeMillis() + "@ya.ru", "Naz35514");
        //Запускаем браузер
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = WebDriverFactory.getWebDriver(browser);
        //Это инициализация объектов страниц (паттерн Page Object)
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        //Регистрируем пользователя
        Response response = userApiSteps.userCreate(user);
        accessToken = response.path("accessToken");
        //Запуск страниц
        driver.get(RegisterPage.BASE_URL);
        driver.get(HomePage.BASE_URL);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginLogYourAccount() {
        //Нажимаем кнопку Войти в аккаунт
        homePage.clickLoginButton();
        //Ожидаем прогрузки страницы авторизации
        loginPage.waitForLoad();
        //Ввводим данные авторизации и кликаем войти
        loginPage.login(user.getEmail(), user.getPassword());
        //Пооверяем, что отображается Оформить заказ
        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");
    }

    @Test
    @DisplayName("вход через личный кабинет")
    public void loginPersonalAccountButton() {
        // После открытия главной страницы, заходим и вносим имя и пароль
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword());
        //добавляем ожидание, так как не успевает страница открыться
        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");
    }

    @Test
    @DisplayName("вход через кнопку войти на странице регистрации")
    public void loginRegisterButton() {
        driver.get(LoginPage.BASE_URL);
        // нажимаем кнопку Кнопка зарегестрироваться на странице логин,Вы — новый пользователь?
        loginPage.clickRegisterButton();
        registerPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void loginRecoverPassword() {
        homePage.clickPersonalAccountButton();
        loginPage.waitForLoad();
        loginPage.clickForgotPasswordButton();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginButton();


        loginPage.waitForLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(homePage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не появилась!");
    }


    @AfterEach
    public void tearDown() {
        // Удаляем пользователя только если есть токен
        userApiSteps.userDelete(accessToken);
        if (driver != null) {
           driver.quit();
        }
    }
}

