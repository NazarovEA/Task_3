import PageObject.RegisterPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest {
    WebDriver driver;

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    public void successfulRegistration(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get("https://stellarburgers.education-services.ru/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Evgeny", "email" + System.currentTimeMillis() + "@mail.ru", "012345");

    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "yandex"})
    public void shortPasswordError(String browser) {
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get("https://stellarburgers.education-services.ru/register");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Evgeny2", "Evgen2@ya.ru", "01234");
        assertTrue(registerPage.passwordErrorMessage());
    }
}
