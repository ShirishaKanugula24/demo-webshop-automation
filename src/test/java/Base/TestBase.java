package Base;


import Base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestBase {

    protected WebDriver driver;

    private static final Logger log =
            LogManager.getLogger(TestBase.class);

    // ---------- SUITE LEVEL ----------
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("Test Suite started");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        log.info("Test Suite finished");
        DriverFactory.quitDriver();
    }

    // ---------- TEST LEVEL ----------
    @BeforeTest(alwaysRun = true)
    @Parameters({"browser"})
    public void beforeTest(@Optional("chrome") String browser) {
        log.info("Test execution started with browser: {}", browser);
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        log.info("Test execution finished");
    }

    // ---------- CLASS LEVEL ----------
    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "baseUrl"})
    public void beforeClass(@Optional("chrome") String browser,
                            @Optional("https://demowebshop.tricentis.com/") String baseUrl) {

        log.info("Starting test class: {}", this.getClass().getSimpleName());
        log.info("Initializing driver and navigating to URL: {}", baseUrl);

        driver = DriverFactory.initDriver(browser);
        driver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        log.info("Finished test class: {}", this.getClass().getSimpleName());
        // DO NOT quit driver here (handled at suite level)
    }

    // ---------- METHOD LEVEL ----------
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        log.info("Starting test method");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        log.info("Finished test method");
    }
}
