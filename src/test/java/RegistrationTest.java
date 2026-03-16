import org.junit.jupiter.api.DisplayName;
import pageObject.RegisterPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
    WebDriver driver;

    @ParameterizedTest
    @DisplayName("Успешная регистрация")
    @ValueSource(strings = {"chrome"})
    public void successfulRegistration(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get("https://stellarburgers.education-services.ru/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Evgeny", "email" + System.currentTimeMillis() + "@mail.ru", "012345");

    }

    @ParameterizedTest
    @DisplayName("Ошибка для некорректного пароля.")
    @ValueSource(strings = {"chrome"})
    public void shortPasswordError(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(RegisterPage.BASE_URL);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Evgeny2", "Evgen2@ya.ru", "01234");
        assertTrue(registerPage.passwordErrorMessage());
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }}
}
