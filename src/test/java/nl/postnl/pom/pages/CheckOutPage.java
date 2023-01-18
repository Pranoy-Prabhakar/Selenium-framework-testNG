package nl.postnl.pom.pages;

import nl.postnl.pom.base.BasePage;
import nl.postnl.pom.objects.BillingAddress;
import nl.postnl.pom.objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckOutPage extends BasePage {

     private final By firstNameField = By.id("billing_first_name");
        private final By lastNameField = By.id("billing_last_name");
        private final By billingAddressField = By.id("billing_address_1");
        private final By billingCityField = By.id("billing_city");
        private final By billingPostCodeField = By.id("billing_postcode");
        private final By billingEmailField = By.id("billing_email");
        private final By placeOrderBtn = By.id("place_order");
        private final By countryDropDown = By.id("billing_country");
        private final By stateDropDown = By.id("billing_state");
        private final By spinnerOverlay = By.cssSelector(".blockUI.blockOverlay");
        private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

        private final By checkOutLoginLink = By.cssSelector(".showlogin");

        private final By userNameFld = By.id("username");
        private final By passwordFld = By.id("password");

        private final By loginSubmitBtn = By.cssSelector("button[value='Login']");

        private final By productName = By.cssSelector("td[class='product-name']");



    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    public CheckOutPage enterFirstName(String firstName){
        WebElement e = waitForElementToBeLocated(firstNameField);
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckOutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckOutPage enterLastName(String lastName){
        WebElement e = waitForElementToBeLocated(lastNameField);
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckOutPage enterAddress(String address){
        WebElement e = waitForElementToBeLocated(billingAddressField);
        e.clear();
        e.sendKeys(address);
        return this;
    }
    public CheckOutPage enterCity(String city){
        WebElement e =  waitForElementToBeLocated(billingCityField);
        e.clear();
        e.sendKeys(city);
        return this;
    }
    public CheckOutPage enterPostCode(String postCode){
        WebElement e = waitForElementToBeLocated(billingPostCodeField);
        e.clear();
        e.sendKeys(postCode);
        return this;
    }
    public CheckOutPage enterEmail(String email){
        WebElement e = waitForElementToBeLocated(billingEmailField);
        e.clear();
        e.sendKeys(email);
        return this;
    }

    public CheckOutPage selectCountry(String countryName){
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;

    }

    public CheckOutPage selectState(String stateName){
        Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);
        return this;

    }

    public CheckOutPage selectDirectBankTransferRadioBtn(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }
    public CheckOutPage clickOnCheckOutLoginButton(){
        waitForElementToBeLocated(checkOutLoginLink).click();
        return this;
    }

    public CheckOutPage enterUserName(String userName){
        waitForElementToBeLocated(userNameFld).sendKeys(userName);
        return this;
    }

    public CheckOutPage enterPassword(String password){
        waitForElementToBeLocated(passwordFld).sendKeys(password);
        return this;
    }

    public CheckOutPage clickOnSubmitButton(){
        waitForElementToBeLocated(loginSubmitBtn).click();
        return this;
    }

    public CheckOutPage login(User user){
        return enterUserName(user.getUserName())
                .enterPassword(user.getPassword())
                .clickOnSubmitButton();
    }

    public CheckOutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .enterAddress(billingAddress.getAddress())
                .enterCity(billingAddress.getCity())
                .enterPostCode(billingAddress.getPostCode())
                .enterEmail(billingAddress.getEmail())
                .selectCountry(billingAddress.getCountry())
                .selectState(billingAddress.getState());
    }

    public String getProductName(){
        return waitForElementToBeLocated(productName).getText();
    }

    public FinalPage clickPlaceOrder(){
        waitForElementToDisappear(spinnerOverlay);
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn));
        waitForElementToBeClickable(placeOrderBtn).click();
        return new FinalPage(driver);
    }


}
