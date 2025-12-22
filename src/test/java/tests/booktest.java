package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import pages.bookpage;
import pages.homepage;
import pages.login;

public class booktest extends TestBase {

	 @Test(groups = {"smoke", "regression"},
			    
			    retryAnalyzer = utils.retryAnalyzer.class
			)
    public void verifyUserCanAddBookToCart() {

        // Step 1: Home page
        homepage homePage = new homepage(driver);

        // Step 2: Navigate to Books
        bookpage booksPage = homePage.clickBooks();

        // Step 3: Select a book
        booksPage.selectFirstBook();

        // Step 4: Add to cart
        booksPage.addBookToCart();

        // Step 5: Basic validation (URL / title)
        String message = booksPage.addBookToCart();

        Assert.assertTrue(
                message.contains("The product has been added"),
                "Book was not added to cart"
        );
    }
}

