package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import pages.bookpage;
import pages.cartpage;
import pages.homepage;
import pages.login;

public class cartpagetest extends TestBase {
	@Test(
		    groups = {"regression"},
		    retryAnalyzer = utils.retryAnalyzer.class
		)
    public void verifyCartDetails() {

        homepage homePage = new homepage(driver);
        bookpage booksPage = homePage.clickBooks();

        booksPage.selectFirstBook();
        booksPage.addBookToCart();

        cartpage cartPage = booksPage.goToCart();
        cartPage.openCart();

        Assert.assertTrue(
                cartPage.getProductName().length() > 0,
                "Product not visible in cart"
        );

        Assert.assertEquals(
                cartPage.getQuantity(),
                "1",
                "Quantity is incorrect"
        );
    }
}
	

