package AbstractClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractClass {

WebDriver driver;
public static String INPUT_DATA = "Animesh xyz 02/09/2023"; // Declaring the Input data that will be consume in Ask Query in Job section

    public AbstractClass(WebDriver driver) {
        this.driver = driver;
    }

    public void WebElementToAppear(By findBy) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(findBy));

    }
}
