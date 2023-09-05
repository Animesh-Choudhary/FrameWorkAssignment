package FraneWorkPageObjectFiles;

import AbstractClass.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends AbstractClass {

    WebDriver driver;
    By loginEmail = By.xpath("//input[contains(@placeholder, 'Enter your email')]");
    By loginPassword = By.xpath("//input[contains(@placeholder, 'Enter your password')]");
    By SIgnIN = By.xpath("//button[@class='ant-btn sign_in_btn__fmPfd']");
    @FindBy(xpath = "//button[@form='auth-standard-login-form' and @type='submit']")
    WebElement loginButton;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        try {
            WebElementToAppear(SIgnIN);
            driver.findElement(SIgnIN).click();
            WebElementToAppear(loginEmail);
            driver.findElement(loginEmail).sendKeys(email);

            WebElementToAppear(loginPassword);
            driver.findElement(loginPassword).sendKeys(password);

            Actions actions = new Actions(driver);
            actions.moveToElement(loginButton).perform();
            loginButton.click();
        } catch (Exception e) {
            // Handle the exception as needed (e.g., log, report, rethrow)
            e.printStackTrace(); // Print the exception stack trace for debugging
        }
    }
//    public boolean isUserLoggedIn() throws InterruptedException {
//        Thread.sleep(1000);
//        String expectedURL = "https://basecopy5.staging.pg-test.com/hub/newhub503949860203/home-v3";
//        String currentURL = driver.getCurrentUrl();
//        boolean isLoggedIn = currentURL.equals(expectedURL);
//
//        if (isLoggedIn) {
//            System.out.println("Successfully logged in.");
//        } else {
//            System.out.println("Login failed.");
//            throw new AssertionError("Login was not successful.");
//        }
//
//        return isLoggedIn;
//    }
    public void GoTO() {
        if (driver != null) {
            driver.get("https://basecopy5.staging.pg-test.com/");
        } else {
            System.out.println("WebDriver is null. Cannot navigate to the URL.");
        }
    }

}
