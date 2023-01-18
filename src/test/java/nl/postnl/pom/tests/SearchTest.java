package nl.postnl.pom.tests;

import nl.postnl.pom.base.BaseTest;
import nl.postnl.pom.pages.StorePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "Validate user is able to search the product using search box at the store page")
    public void searchWithPartialMatch(){
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search("Blue")
                .waitForPageToBeLoaded("s=Blue&post_type=product");
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
    }
}
