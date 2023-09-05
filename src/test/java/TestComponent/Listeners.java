package TestComponent;

import ResourceClass.ExtentReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.IOException;

public class Listeners extends BaseClassTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReportNG.createInstance();

    @Override
    public void onTestStart(ITestResult result) {
        // This method is called when a test method starts.
        // You can perform actions or log messages here.
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test step successfully passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        // screenshots
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String filepath = null;
        try {
            filepath = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
        }
        test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // This method is called when a test method is skipped.
        // You can perform actions or log messages here.
    }

    @Override
    public void onStart(ITestContext context) {
        // This method is called when the entire test suite starts execution.
        // You can perform actions or log messages here.
    }

    @Override
    public void onFinish(ITestContext context) {
        // This method is called when the entire test suite finishes execution.
        // You can perform actions or log messages here.
        extent.flush(); // Add this line to ensure the test information is added to the report
    }
}
