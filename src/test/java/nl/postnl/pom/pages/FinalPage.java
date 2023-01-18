package nl.postnl.pom.pages;

import nl.postnl.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FinalPage extends BasePage {

    private final By checkFinalTextTitle = By.cssSelector("#post-1221 > div > div > div > div > div > p");
    public FinalPage(WebDriver driver) {
        super(driver);
    }
    public String getFinalTitle(){
        return waitForElementToBeLocated(checkFinalTextTitle).getText();
    }
}
