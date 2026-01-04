package Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    private static final Logger log =
            LogManager.getLogger(DriverFactory.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriver initDriver(String browserName) {

        if (browserName == null || browserName.isEmpty()) {
            log.warn("Browser name is null/empty. Defaulting to Chrome.");
            browserName = "chrome";
        }

        WebDriver localDriver;

        log.info("Initializing WebDriver for browser: {}", browserName);

        switch (browserName.toLowerCase()) {

            case "firefox":
                log.info("Launching Firefox browser");
                FirefoxOptions ffOptions = new FirefoxOptions();
                localDriver = new FirefoxDriver(ffOptions);
                break;

            case "edge":
                log.info("Launching Edge browser");
                EdgeOptions edgeOptions = new EdgeOptions();
                localDriver = new EdgeDriver(edgeOptions);
                break;

            case "chrome":
            default:
                log.info("Launching Chrome browser");
                localDriver = new ChromeDriver();
                break;
        }

        log.info("Maximizing browser window");
        localDriver.manage().window().maximize();

        driver.set(localDriver);
        log.info("WebDriver instance stored in ThreadLocal");

        return getDriver();
    }

    public static void quitDriver() {
        WebDriver localDriver = driver.get();

        if (localDriver != null) {
            log.info("Closing browser and quitting WebDriver");
            localDriver.quit();
            driver.remove();
        } else {
            log.warn("quitDriver called but WebDriver was null");
        }
    }
}
