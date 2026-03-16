import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.RegisterPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
    WebDriver driver;
    private String accessToken;

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistration() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(RegisterPage.BASE_URL);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Evgeny", "email" + System.currentTimeMillis() + "@mail.ru", "012345");

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
        // Если токен был получен, удаляем юзера через API
        if (accessToken != null) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete("https://education-services.ru")
                    .then()
                    .statusCode(202); // Обычно 202 Accepted или 200 OK
        }

        if (driver != null) {
            driver.quit();
        }
    }
}

