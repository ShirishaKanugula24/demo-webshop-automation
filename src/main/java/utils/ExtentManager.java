package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    // Prevent object creation
    private ExtentManager() {}

    public static synchronized ExtentReports getExtentReport() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir")
                    + "/test-output/ExtentReport.html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Demo Web Shop Execution");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}
