package Base;
 
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
 
public class DriverFactory {
 
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
 
    public static WebDriver getDriver() {
        return driver.get();
    }
 
    public static WebDriver initDriver(String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome";
        }
 
        WebDriver localDriver;
 
        switch (browserName.toLowerCase()) {
 
        case "firefox":
            FirefoxOptions ffOptions = new FirefoxOptions();
            localDriver = new FirefoxDriver(ffOptions);
            break;
 
        case "edge":
            EdgeOptions edgeOptions = new EdgeOptions();
            localDriver = new EdgeDriver(edgeOptions);
            break;
 
        case "chrome":
        default:
           // ChromeOptions chOptions = new ChromeOptions();
            localDriver = new ChromeDriver();
            break;
    }
 
        localDriver.manage().window().maximize();
        driver.set(localDriver);
        return getDriver();
    }
 
    public static void quitDriver() {
        WebDriver localDriver = driver.get();
        if (localDriver != null) {
            localDriver.quit();
            driver.remove();
        }
    }
}