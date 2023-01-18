package nl.postnl.pom.pages;

import nl.postnl.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StorePage extends BasePage {

    private final By searchFld = By.cssSelector("#woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("#woocommerce_product_search-1 > form > button");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");


    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage load(){
        load("/store");
        return this;
    }

    public StorePage waitForPageToBeLoaded(String urlContains){
        wait.until(ExpectedConditions.urlContains(urlContains));
        return this;
    }

    private StorePage searchBlue(String searchText){
        waitForElementToBeLocated(searchFld).sendKeys(searchText);
        return this;
    }
    private StorePage clickSearchButton(){
        waitForElementToBeLocated(searchBtn).click();
        return this;
    }

    public String getTitle(){
        return driver.findElement(title).getText();
    }

    public StorePage search(String text){
        searchBlue(text);
        clickSearchButton();
        return this;
    }

    private By getAddToCartBtnElement(String prdName){
        return By.cssSelector("a[aria-label='Add “"+prdName+"” to your cart']");
    }
    public StorePage clickAddToCartBtn(String prdName){
        By addToCartBtn = getAddToCartBtnElement(prdName);
        waitForElementToBeLocated(addToCartBtn).click();
        return this;
    }
    public CartPage clickOnCartLink(){
        waitForElementToBeLocated(viewCartLink).click();
        return new CartPage(driver);
    }
}
