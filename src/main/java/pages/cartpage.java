package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class cartpage {


	    private WebDriver driver;

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
	    }

	    public void openCart() {
	        cartLink.click();
	    }

	    public String getProductName() {
	        return productName.getText();
	    }

	    public String getQuantity() {
	        return quantityField.getAttribute("value");
	    }

	    public void clickCheckout() {
	        checkoutButton.click();
	    }
	}
	



