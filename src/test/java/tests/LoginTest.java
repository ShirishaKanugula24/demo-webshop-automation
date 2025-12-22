package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import pages.homepage;
import pages.login;

public class LoginTest extends TestBase {

    @Test(groups = {"smoke", "regression"},
		    
		    retryAnalyzer = utils.retryAnalyzer.class
		)
    public void verifyUserCanLoginWithValidCredentials() {

        // Step 1: Home page (browser already opened in BaseTest)
        homepage homePage = new homepage(driver);

        // Step 2: Click "Log in" link
        login loginPage = homePage.clickLoginLink();

        // Step 3: Perform login
        loginPage.login("testuser@gmail.com", "password123");

        // Step 4: Validate successful login
        Assert.assertTrue(
                driver.getTitle().contains("Demo Web Shop"),
                "Login failed - Home page title not displayed"
        );
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyErrorMessageForInvalidLogin() {

        homepage homePage = new homepage(driver);
        login loginPage = homePage.clickLoginLink();

        loginPage.login("invalid@gmail.com", "wrongpassword");

        String errorMsg = loginPage.getLoginErrorMessage();

        Assert.assertTrue(
                errorMsg.contains("Login was unsuccessful"),
                "Expected error message not displayed"
        );
    }
}
