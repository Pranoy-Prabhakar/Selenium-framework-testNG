package nl.postnl.pom.pages;

import nl.postnl.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By cartAddedProductName = By.cssSelector("td[class='product-name'] a");
    private final By checkOutButton = By.cssSelector(".checkout-button.button.alt.wc-forward");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getAddedProductName() {
        return waitForElementToBeLocated(cartAddedProductName).getText();
    }

    public CheckOutPage clickOnCheckOutBtn(){
        waitForElementToBeLocated(checkOutButton).click();
        return new CheckOutPage(driver);
    }
}
