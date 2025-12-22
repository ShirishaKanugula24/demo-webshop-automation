package pages;
 
import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
 
public class homepage {
 
    private WebDriver driver;
 
    @FindBy(xpath = "//a[text()='Books']")
    private WebElement booksMenu;

   
    @FindBy(css = "a[href*='contact']")  // example

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

    }
 
    public String getTitle() {

        return driver.getTitle();

    }
 
    public boolean isBooksMenuVisible() {
        return booksMenu.isDisplayed();
    }
 
    public void clickContactUs() {

        contactUsLink.click();

    }
 
    public void scrollToFooter() {

        ((JavascriptExecutor) driver)

                .executeScript("arguments[0].scrollIntoView(true);", footer);

    }
 
    public String getFooterText() {

        return footer.getText();

    }
    public login clickLoginLink() {
        loginLink.click();
        return new login(driver);
    }
    public bookpage clickBooks() {
        booksLink.click();
        return new bookpage(driver);
    }

}
 