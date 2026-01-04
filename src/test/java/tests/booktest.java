package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import pages.bookpage;
import pages.homepage;

public class booktest extends TestBase {

    private static final Logger log =
            LogManager.getLogger(booktest.class);

    @Test(
        groups = {"smoke", "regression"},
        retryAnalyzer = utils.retryAnalyzer.class
    )
    public void verifyUserCanAddBookToCart() {

        log.info("Starting test: verifyUserCanAddBookToCart");

        // Step 1: Home page
        homepage homePage = new homepage(driver);
        log.info("Home page loaded");

        // Step 2: Navigate to Books
        bookpage booksPage = homePage.clickBooks();
        log.info("Navigated to Books page");

        // Step 3: Select a book
        booksPage.selectFirstBook();
        log.info("First book selected");

        // Step 4: Add to cart
        log.info("Adding book to cart");
        String message = booksPage.addBookToCart();

        // Step 5: Validation
        log.info("Validating add-to-cart success message");
        Assert.assertTrue(
                message.contains("The product has been added"),
                "Book was not added to cart"
        );

        log.info("Test passed: verifyUserCanAddBookToCart");
    }
}
