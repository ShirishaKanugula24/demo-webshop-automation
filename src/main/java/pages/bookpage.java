package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class bookpage {

    private WebDriver driver;

    private static final Logger log =
            LogManager.getLogger(bookpage.class);

    @FindBy(css = "div.product-item h2 a")
    private WebElement firstBook;

    @FindBy(css = "input[value='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(id = "bar-notification")
    private WebElement successMessage;

    public bookpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Book page initialized");
    }

    public void selectFirstBook() {
        log.info("Selecting the first book from the list");
        firstBook.click();
    }

    public String addBookToCart() {
        log.info("Clicking 'Add to Cart' button");
        addToCartButton.click();

        String message = successMessage.getText();
        log.info("Add to cart success message displayed: {}", message);

        return message;
    }

    public cartpage goToCart() {
        log.info("Navigating to cart page");
        return new cartpage(driver);
    }
}
