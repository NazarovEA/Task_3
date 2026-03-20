import io.restassured.response.Response;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.LoginPage;
import pageobject.RegisterPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
    WebDriver driver;
    private User user;
    UserApiSteps userApiSteps = new UserApiSteps();

    @BeforeEach
    public void setUp(){
        user = new User("Evgeny", "evgen" + System.currentTimeMillis() + "@ya.ru", "012345");
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = WebDriverFactory.getWebDriver(browser);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        driver.get(RegisterPage.BASE_URL);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();

       LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.headerLoginPage(), "После регистрации не открылась страница входа!");
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля.")
    public void shortPasswordError() {
        driver.get(RegisterPage.BASE_URL);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), "01234");
        registerPage.clickRegisterButton();

        assertTrue(registerPage.passwordErrorMessage(), "Нет сообщения об ошибке");
    }

    @AfterEach
    public void tearDown() {
        Response response = userApiSteps.userLogin(user);

        if (response.statusCode() == 200) {
            String token = response.path("accessToken");
            userApiSteps.userDelete(token);
        }

        if (driver != null) {
            driver.quit();
        }
        }
}

