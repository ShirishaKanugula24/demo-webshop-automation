package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homepage {

    private WebDriver driver;

    private static final Logger log =
            LogManager.getLogger(homepage.class);

    @FindBy(xpath = "//a[text()='Books']")
    private WebElement booksMenu;

    @FindBy(css = "a[href*='contact']")
    private WebElement contactUsLink;

    @FindBy(css = "footer")
    private WebElement footer;

    @FindBy(css = "a.ico-login")
    private WebElement loginLink;

    @FindBy(linkText = "Books")
    private WebElement booksLink;

    public homepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Home page initialized");
    }

    public String getTitle() {
        String title = driver.getTitle();
        log.info("Home page title retrieved: {}", title);
        return title;
    }

    public boolean isBooksMenuVisible() {
        boolean visible = booksMenu.isDisplayed();
        log.info("Books menu visibility: {}", visible);
        return visible;
    }

    public void clickContactUs() {
        log.info("Clicking Contact Us link");
        contactUsLink.click();
    }

    public void scrollToFooter() {
        log.info("Scrolling to footer section");
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", footer);
    }

    public String getFooterText() {
        String footerText = footer.getText();
        log.info("Footer text retrieved");
        return footerText;
    }

    public login clickLoginLink() {
        log.info("Clicking Login link");
        loginLink.click();
        return new login(driver);
    }

    public bookpage clickBooks() {
        log.info("Clicking Books link");
        booksLink.click();
        return new bookpage(driver);
    }
}
