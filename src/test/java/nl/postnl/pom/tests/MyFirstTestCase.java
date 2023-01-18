package nl.postnl.pom.tests;

import nl.postnl.pom.base.BaseTest;
import nl.postnl.pom.objects.BillingAddress;
import nl.postnl.pom.objects.Product;
import nl.postnl.pom.objects.User;
import nl.postnl.pom.pages.*;
import nl.postnl.pom.utils.ConfigLoader;
import nl.postnl.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {


    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

        BillingAddress billingAddress=JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product= new Product(1215);
        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreLink()
                .search("Blue");

        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        storePage.clickAddToCartBtn(product.getProdName());

        CartPage cartPage = storePage.clickOnCartLink();
        Assert.assertEquals(cartPage.getAddedProductName(),product.getProdName());

        CheckOutPage checkOutPage = cartPage.clickOnCheckOutBtn();
        checkOutPage
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioBtn();

        FinalPage finalPage = checkOutPage.clickPlaceOrder();

        Assert.assertEquals(finalPage.getFinalTitle(),"Thank you. Your order has been received.");

    }

    public void loginAndCheckOutUsingDirectBankTransfer() throws InterruptedException, IOException {

        BillingAddress billingAddress=JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product= new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUserName(), ConfigLoader.getInstance().getPassword());
        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreLink()
                .search("Blue");

        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        storePage.clickAddToCartBtn(product.getProdName());

        CartPage cartPage = storePage.clickOnCartLink();
        Assert.assertEquals(cartPage.getAddedProductName(),product.getProdName());

        CheckOutPage checkOutPage = cartPage.clickOnCheckOutBtn();
        checkOutPage
                .clickOnCheckOutLoginButton()
                .login(user)
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioBtn();

        FinalPage finalPage = checkOutPage.clickPlaceOrder();

        Assert.assertEquals(finalPage.getFinalTitle(),"Thank you. Your order has been received.");

    }

}
