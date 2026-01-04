package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartpage {

    private WebDriver driver;

    private static final Logger log =
            LogManager.getLogger(cartpage.class);

    @FindBy(xpath = "//span[text()='Shopping cart']")
    private WebElement cartLink;

    @FindBy(xpath = "//td[@class='product']/a")
    private WebElement productName;

    @FindBy(xpath = "//input[contains(@name,'itemquantity')]")
    private WebElement quantityField;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public cartpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Cart page initialized");
    }

    public void openCart() {
        log.info("Opening shopping cart");
        cartLink.click();
    }

    public String getProductName() {
        String name = productName.getText();
        log.info("Product name in cart: {}", name);
        return name;
    }

    public String getQuantity() {
        String quantity = quantityField.getAttribute("value");
        log.info("Product quantity in cart: {}", quantity);
        return quantity;
    }

    public void clickCheckout() {
        log.info("Clicking Checkout button");
        checkoutButton.click();
    }
}
