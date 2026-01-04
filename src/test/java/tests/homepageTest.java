package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Base.TestBase;
import pages.homepage;
import utils.JsonDataProvider;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class homepageTest extends TestBase {

    private static final Logger log =
            LogManager.getLogger(homepageTest.class);

    @Test(
        groups = {"smoke", "regression"},
        priority = 1,
        retryAnalyzer = utils.retryAnalyzer.class,
        dataProvider = "jsonData",
        dataProviderClass = JsonDataProvider.class,
        description = "Verify home page title"
    )
    public void TC01_homeTitle(Map<String, String> data) {

        log.info("Starting test: TC01_homeTitle");
        log.info("Validating home page title using test data");

        homepage home = new homepage(driver);

        Assert.assertEquals(
                home.getTitle(),
                data.get("expectedTitle"),
                "Home page title does not match"
        );

        log.info("Test passed: TC01_homeTitle");
    }

    @Test(
        groups = {"smoke"},
        priority = 2,
        description = "Verify Books menu is visible"
    )
    public void TC02_homeMenuVisible() {

        log.info("Starting test: TC02_homeMenuVisible");

        homepage home = new homepage(driver);

        Assert.assertTrue(
                home.isBooksMenuVisible(),
                "Books menu is not visible"
        );

        log.info("Test passed: TC02_homeMenuVisible");
    }

    @Test(
        groups = {"regression"},
        priority = 3,
        dataProvider = "jsonData",
        dataProviderClass = JsonDataProvider.class,
        description = "Scroll to footer and verify footer text"
    )
    public void TC03_scrollToFooter(Map<String, String> data) {

        log.info("Starting test: TC03_scrollToFooter");

        homepage home = new homepage(driver);
        log.info("Scrolling to footer");

        home.scrollToFooter();

        Assert.assertTrue(
                home.getFooterText().contains(data.get("footerText")),
                "Footer text validation failed"
        );

        log.info("Test passed: TC03_scrollToFooter");
    }

    @Test(
        groups = {"regression"},
        priority = 4,
        dataProvider = "jsonData",
        dataProviderClass = JsonDataProvider.class,
        description = "Click Contact Us link"
    )
    public void TC04_clickContactUs(Map<String, String> data) {

        log.info("Starting test: TC04_clickContactUs");

        homepage home = new homepage(driver);
        log.info("Clicking Contact Us link");

        home.clickContactUs();

        Assert.assertTrue(
                driver.getTitle().contains(data.get("contactPageTitle")),
                "Contact Us page title validation failed"
        );

        log.info("Test passed: TC04_clickContactUs");
    }
}
