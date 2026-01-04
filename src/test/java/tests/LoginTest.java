package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import pages.homepage;
import pages.login;

public class LoginTest extends TestBase {

    private static final Logger log =
            LogManager.getLogger(LoginTest.class);

    @Test(
        groups = {"smoke", "regression"},
        retryAnalyzer = utils.retryAnalyzer.class
    )
    public void verifyUserCanLoginWithValidCredentials() {

        log.info("Starting test: verifyUserCanLoginWithValidCredentials");

        // Step 1: Home page
        homepage homePage = new homepage(driver);
        log.info("Home page loaded");

        // Step 2: Click Login link
        login loginPage = homePage.clickLoginLink();
        log.info("Navigated to Login page");

        // Step 3: Perform login
        log.info("Attempting login with valid credentials");
        loginPage.login("testuser@gmail.com", "password123");

        // Step 4: Validation
        log.info("Validating successful login");
        Assert.assertTrue(
                driver.getTitle().contains("Demo Web Shop"),
                "Login failed - Home page title not displayed"
        );

        log.info("Test passed: verifyUserCanLoginWithValidCredentials");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyErrorMessageForInvalidLogin() {

        log.info("Starting test: verifyErrorMessageForInvalidLogin");

        homepage homePage = new homepage(driver);
        log.info("Home page loaded");

        login loginPage = homePage.clickLoginLink();
        log.info("Navigated to Login page");

        log.info("Attempting login with invalid credentials");
        loginPage.login("invalid@gmail.com", "wrongpassword");

        String errorMsg = loginPage.getLoginErrorMessage();

        log.info("Validating error message for invalid login");
        Assert.assertTrue(
                errorMsg.contains("Login was unsuccessful"),
                "Expected error message not displayed"
        );

        log.info("Test passed: verifyErrorMessageForInvalidLogin");
    }
}
