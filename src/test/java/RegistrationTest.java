import io.restassured.http.ContentType;
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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
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
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
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
        assertTrue(loginPage.isLoginButtonDisplayed(), "После регистрации не открылась страница входа!");
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля.")
    public void shortPasswordError() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(RegisterPage.BASE_URL);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Evgeny2", "Evgen2@ya.ru", "01234");
        assertTrue(registerPage.passwordErrorMessage());
    }
    @AfterEach
    public void tearDown() {
        // Удаляем пользователя только если есть токен
        if (accessToken != null) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete("https://stellarburgers.education-services.ru/api/auth/user")
                    .then()
                    .statusCode(202);
        }

        if (driver != null) {
            driver.quit();
        }
        }
}

