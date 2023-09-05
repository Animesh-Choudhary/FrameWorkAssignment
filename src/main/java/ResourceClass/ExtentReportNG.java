package ResourceClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG {

    static ExtentReports extent;

    public static ExtentReports createInstance() {
        // Define the report path relative to the "test-output" directory
        String reportPath = "ExtentReportFile.html";

        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent.attachReporter(sparkReporter);

        // Customize the report
        sparkReporter.config().setDocumentTitle("Test Result");
        sparkReporter.config().setReportName("Automation Framework");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent.setSystemInfo("Tester", "Animesh Choudhary");

        return extent;
    }
}
