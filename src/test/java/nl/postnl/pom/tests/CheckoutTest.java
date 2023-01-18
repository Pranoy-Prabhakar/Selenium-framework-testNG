package nl.postnl.pom.tests;

import nl.postnl.pom.api.actions.CartApi;
import nl.postnl.pom.api.actions.SignUpApi;
import nl.postnl.pom.base.BaseTest;
import nl.postnl.pom.objects.BillingAddress;
import nl.postnl.pom.objects.Product;
import nl.postnl.pom.objects.User;
import nl.postnl.pom.pages.CheckOutPage;
import nl.postnl.pom.pages.FinalPage;
import nl.postnl.pom.utils.FakerUtils;
import nl.postnl.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test(description = "Validate guest user is able to order using direct bank transfer payment method")
    public void GuestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress= JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkOutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioBtn();

        FinalPage finalPage = checkOutPage.clickPlaceOrder();
        Assert.assertEquals(finalPage.getFinalTitle(),"Thank you. Your order has been received.");

    }

    @Test(description = "Validate logged in user is able to order using direct bank transfer payment method")
    public void LoginAndCheckOutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress= JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        String userName = "testUser"+new FakerUtils().generateRandomNumber();
        User user =  new User()
                .setUserName(userName)
                .setPassword("testUserPwd")
                .setEmailId(userName+"@test.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getProdId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        Thread.sleep(1000);
        injectCookiesToBrowser(signUpApi.getCookies());
        Thread.sleep(2000);
        checkOutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransferRadioBtn();

        FinalPage finalPage = checkOutPage.clickPlaceOrder();
        Assert.assertEquals(finalPage.getFinalTitle(),"Thank you. Your order has been received.");

    }
}
