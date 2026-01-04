package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Base.DriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestListener implements ITestListener {

    private static final Logger log =
            LogManager.getLogger(ExtentTestListener.class);

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        log.info("Extent Listener started for suite: {}", context.getName());
        extent = ExtentManager.getExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test started: {}", result.getMethod().getMethodName());
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: {}", result.getMethod().getMethodName());
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error(
            "Test failed: {}",
            result.getMethod().getMethodName(),
            result.getThrowable()
        );

        WebDriver driver = DriverFactory.getDriver();
        test.get().fail(result.getThrowable());

        String screenshotPath =
                ScreenshotUtils.takeScreenshot(
                        driver,
                        result.getMethod().getMethodName()
                );

        if (screenshotPath != null) {
            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
                log.info("Screenshot attached to Extent report");
            } catch (Exception e) {
                log.error("Failed to attach screenshot to Extent", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: {}", result.getMethod().getMethodName());
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Extent Listener finished for suite: {}", context.getName());
        if (extent != null) {
            extent.flush();
            log.info("Extent report flushed successfully");
        }
    }
}
