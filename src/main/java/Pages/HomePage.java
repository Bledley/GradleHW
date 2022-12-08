package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement openRegPageBtn = driver.findElement(By.xpath("//*[@data-testid='open-registration-form-button']"));
}