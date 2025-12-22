package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class login {
	//page object for login page..................................................................
	private WebDriver driver;
	private By emailField    = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton   = By.cssSelector("input[value='Log in']");
    private By errorMessage  = By.cssSelector(".validation-summary-errors");
 // ================== Constructor ==================
    public login(WebDriver driver) {
        this.driver = driver;
      
    }

    // ================== Page Actions ==================

    public void enterEmail(String email) {
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        
        driver.findElement(passwordField).sendKeys(password);
    }

    public homepage clickLogin() {
        driver.findElement(loginButton).click();
        return new homepage(driver);
    }

    public homepage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickLogin();
    }

    public String getLoginErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
	


