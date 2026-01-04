package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import pages.bookpage;
import pages.cartpage;
import pages.homepage;

public class cartpagetest extends TestBase {

    private static final Logger log =
            LogManager.getLogger(cartpagetest.class);

    @Test(
        groups = {"regression"},
        retryAnalyzer = utils.retryAnalyzer.class
    )
    public void verifyCartDetails() {

        log.info("Starting test: verifyCartDetails");

        homepage homePage = new homepage(driver);
        log.info("Home page loaded");

        bookpage booksPage = homePage.clickBooks();
        log.info("Navigated to Books page");

        booksPage.selectFirstBook();
        log.info("First book selected");

        booksPage.addBookToCart();
        log.info("Book added to cart");

        cartpage cartPage = booksPage.goToCart();
        cartPage.openCart();
        log.info("Navigated to Cart page");

        log.info("Validating product presence in cart");
        Assert.assertTrue(
                cartPage.getProductName().length() > 0,
                "Product not visible in cart"
        );

        log.info("Validating product quantity");
        Assert.assertEquals(
                cartPage.getQuantity(),
                "1",
                "Quantity is incorrect"
        );

        log.info("Test passed: verifyCartDetails");
    }
}
