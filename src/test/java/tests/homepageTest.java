package tests;
 
import Base.TestBase;

import pages.homepage;

import utils.JsonDataProvider;

import org.testng.Assert;

import org.testng.annotations.Test;
 
import java.util.Map;
 
public class homepageTest extends TestBase {
 
    @Test(groups = {"smoke", "regression"}, priority = 1,
		   
		    retryAnalyzer = utils.retryAnalyzer.class,
		

          dataProvider = "jsonData", dataProviderClass = JsonDataProvider.class,

          description = "Verify home page title")

    public void TC01_homeTitle(Map<String,String> data) {

    	 homepage home = new homepage(driver);

        Assert.assertEquals(home.getTitle(), data.get("expectedTitle"));

    }
 
    @Test(groups = {"smoke"}, priority = 2,
    	      description = "Verify Books menu is visible")
    	public void TC02_homeMenuVisible() {

    	    homepage home = new homepage(driver);
    	    Assert.assertTrue(home.isBooksMenuVisible(), "Books menu is not visible");

    	}
 
    @Test(groups = {"regression"}, priority = 3,

          dataProvider = "jsonData", dataProviderClass = JsonDataProvider.class,

          description = "Scroll to footer and verify footer text")

    public void TC03_scrollToFooter(Map<String,String> data) {

    	homepage home = new homepage(driver);

        home.scrollToFooter();

        Assert.assertTrue(home.getFooterText().contains(data.get("footerText")));

    }
 
    @Test(groups = {"regression"}, priority = 4,

          dataProvider = "jsonData", dataProviderClass = JsonDataProvider.class,

          description = "Click Contact Us link")

    public void TC04_clickContactUs(Map<String,String> data) {

    	homepage home = new homepage(driver);

        home.clickContactUs();

        Assert.assertTrue(driver.getTitle().contains(data.get("contactPageTitle")));

    }

}
 