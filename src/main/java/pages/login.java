package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class login {

    // ================== Logger ==================
    private static final Logger log =
            LogManager.getLogger(login.class);

    // ================== Page Objects ==================
    private WebDriver driver;

    private By emailField    = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton   = By.cssSelector("input[value='Log in']");
    private By errorMessage  = By.cssSelector(".validation-summary-errors");

    // ================== Constructor ==================
    public login(WebDriver driver) {
        this.driver = driver;
        log.info("Login page initialized");
    }

    // ================== Page Actions ==================

    public void enterEmail(String email) {
        log.info("Entering email address");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        log.info("Entering password");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public homepage clickLogin() {
        log.info("Clicking Login button");
        driver.findElement(loginButton).click();
        return new homepage(driver);
    }

    public homepage login(String email, String password) {
        log.info("Performing login action");
        enterEmail(email);
        enterPassword(password);
        return clickLogin();
    }

    public String getLoginErrorMessage() {
        String error = driver.findElement(errorMessage).getText();
        log.warn("Login failed with error message: {}", error);
        return error;
    }
}
