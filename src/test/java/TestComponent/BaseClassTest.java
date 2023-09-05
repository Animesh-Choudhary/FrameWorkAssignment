package TestComponent;

import FraneWorkPageObjectFiles.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClassTest {

    protected WebDriver driver = null;
    public LandingPage landingPage;

    @BeforeMethod
    @Parameters("browser")
    public void initializeDriver() throws IOException {
        driver = initializeWebDriver();
        landingPage = new LandingPage(driver);
        landingPage.GoTO();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Add logic to capture screenshots or perform other actions on test failure
        }

        // Close the WebDriver or perform other cleanup steps
        if (driver != null) {
            driver.quit();
        }
    }

    public String getScreenShot(String testcasename, WebDriver driver) throws IOException {
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        // Capture the screenshot as a file
        File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//report//" + testcasename + ".png");
        FileUtils.copyFile(screenshotFile, file);
        System.out.println("Screenshot saved to: " + file);

        return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
    }

    private WebDriver initializeWebDriver() throws IOException {
//        Properties prop = new Properties();
//        FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src/main//java/PropertyClass//Global/GlobalData.properties");
//        prop.load(fs);
//        String browserName = prop.getProperty("browser");

        WebDriver driver = null;

        driver = new ChromeDriver();

        if (driver != null) {
            driver.manage().window().maximize();
        }

        return driver;
    }
}
