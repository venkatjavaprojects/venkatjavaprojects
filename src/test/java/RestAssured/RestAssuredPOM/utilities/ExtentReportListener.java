package RestAssured.RestAssuredPOM.utilities;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener implements ITestListener {

    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Initialize Extent Reports
    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("API Automation Test Report");
        sparkReporter.config().setDocumentTitle("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Your Name");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
    }

    // Create Test in Report
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    // Log Pass Event
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed: " + result.getMethod().getMethodName());
    }

    // Log Fail Event
    public void onTestFailure(ITestResult result) {
        test.get().fail("Test Failed: " + result.getMethod().getMethodName());
        test.get().fail(result.getThrowable()); // Log stack trace
    }

    // Log Skipped Event
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped: " + result.getMethod().getMethodName());
    }

    // Log Finished Execution
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush(); // Generate the report
        }
    }
}

