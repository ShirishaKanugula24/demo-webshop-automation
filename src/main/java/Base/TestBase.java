package Base;


import Base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
 
// IMPORTANT: this import must be org.testng.ITestContext if you use it
// import org.testng.ITestContext;
 
public class TestBase {
 
    protected WebDriver driver;
 
    // ---------- SUITE LEVEL ----------
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("==> @BeforeSuite : Test Suite started");
    }
 
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("==> @AfterSuite : Test Suite finished");
        DriverFactory.quitDriver();
    }
 
    // ---------- TEST LEVEL ----------
    @BeforeTest(alwaysRun = true)
    @Parameters({"browser"})
    public void beforeTest(@Optional("chrome") String browser) {
        System.out.println("==> @BeforeTest : Browser = " + browser);
        // if you really want, you can store browser in a field
    }
 
    @AfterTest(alwaysRun = true)
    public void afterTest() {
        System.out.println("==> @AfterTest");
    }
 
    // ---------- CLASS LEVEL ----------
    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "baseUrl"})
    public void beforeClass(@Optional("chrome") String browser,
                            @Optional("https://demowebshop.tricentis.com/") String baseUrl) {
        System.out.println("==> @BeforeClass : " + this.getClass().getSimpleName());
        driver = DriverFactory.initDriver(browser);
        driver.get(baseUrl);
    }
 
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("==> @AfterClass : " + this.getClass().getSimpleName());
        // DO NOT quit driver here
    
    }
 
    // ---------- METHOD LEVEL ----------
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        System.out.println("==> @BeforeMethod : Starting test method");
    }
 
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        System.out.println("==> @AfterMethod : Finished test method");
    }
}