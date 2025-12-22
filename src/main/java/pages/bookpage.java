package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class bookpage {

	private WebDriver driver;
	@FindBy(css = "div.product-item h2 a")
    private WebElement firstBook;

    @FindBy(css = "input[value='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(id = "bar-notification")
    private WebElement successMessage;

    public bookpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFirstBook() {
        firstBook.click();
    }

    public String addBookToCart() {
        addToCartButton.click();
        return successMessage.getText();
    }
    public cartpage goToCart() {
        return new cartpage(driver);
    }
}
