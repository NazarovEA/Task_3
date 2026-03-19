package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";

    // Кнопка "Войти в аккаунт"
    private final By loginButtonMain = By.xpath(".//button[text()='Войти в аккаунт']");
    // Кнопка "Личный кабинет"
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
// логотип
private final By logo = By.xpath(".//header//a[@href='/']");
    // Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
// булки кнопка
private final By buns = By.xpath(".//span[text()='Булки']/parent::div");
//кнопка оформить заказ
private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    public void waitForOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }
    @Step("Проверить, отображается ли кнопка 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        try {
            // Ждем появления кнопки 10 секунд
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderButton))
                    .isDisplayed();
        } catch (TimeoutException e) {
            // Если кнопка не появилась — возвращаем false вместо падения теста
            return false;
        }
    }
    // соусы кнопка
    private final By sauces = By.xpath(".//span[text()='Соусы']/parent::div");
    // начинки кнопка
    private final By fillings = By.xpath(".//span[text()='Начинки']/parent::div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickСonstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButtonMain))
                .click();
    }

                @Step("Клик на кнопку 'Личный кабинет'")
        public void clickPersonalAccountButton() {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(personalAccountButton))
                    .click();
        }

    public void clickLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logo))
                .click();
    }
    public void clickBuns() {
        WebElement element = driver.findElement(buns);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    public void clickSauces() {
        WebElement element = driver.findElement(sauces);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    public void clickFillings() {
        WebElement element = driver.findElement(fillings);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean fillingsTabActive() {
        // Находим тот же локатор fillings
        // и проверяем его CSS-класс
        String className = driver.findElement(fillings).getAttribute("class");
        return className.contains("tab_tab_type_current");
    }
    public boolean saucesTabActive() {
        String className = driver.findElement(sauces).getAttribute("class");
        return className.contains("tab_tab_type_current");
    }
    public boolean bunsTabActive() {
        String className = driver.findElement(buns).getAttribute("class");
        return className.contains("tab_tab_type_current");
    }
}
