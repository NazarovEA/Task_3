package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;

    // Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
}