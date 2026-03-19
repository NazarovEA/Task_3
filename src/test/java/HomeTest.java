import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.HomePage;
import pageobject.LoginPage;
import org.example.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeTest {
    WebDriver driver;
    HomePage homePage;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        WebDriverFactory factory = new WebDriverFactory();
        driver = factory.getWebDriver(browser);
        driver.get(HomePage.BASE_URL);
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Переход к начинкам")
    public void fillingsOpen() {
        homePage.clickFillings();
        assertTrue(homePage.fillingsTabActive(), "Вкладка 'Начинки' не стала активной после клика!");
    }

    @Test
    @DisplayName("Переход к соусам")
    public void sausesOpen() {
        homePage.clickSauces();
        assertTrue(homePage.saucesTabActive(), "Вкладка 'соусы' не стала активной после клика!");

    }

    @Test
    @DisplayName("Переход к булкам")
    public void bunsOpen() {
        homePage.clickSauces();
        homePage.clickBuns();
        assertTrue(homePage.bunsTabActive(), "Вкладка 'булки' не стала активной после клика!");
    }

     @AfterEach
     public void tearDown() {
         if (driver != null) {
            driver.quit();
        }}
}
