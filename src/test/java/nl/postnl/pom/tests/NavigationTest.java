package nl.postnl.pom.tests;

import nl.postnl.pom.base.BasePage;
import nl.postnl.pom.base.BaseTest;
import nl.postnl.pom.pages.HomePage;
import nl.postnl.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(description = "Validate user is successfully able navigate to Store page from Home page ")
    public void NavigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreLink();
        Assert.assertEquals(storePage.getTitle(),"Store");
    }
}
