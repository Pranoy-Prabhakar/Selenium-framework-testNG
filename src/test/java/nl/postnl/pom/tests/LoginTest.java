package nl.postnl.pom.tests;

import nl.postnl.pom.api.actions.CartApi;
import nl.postnl.pom.api.actions.SignUpApi;
import nl.postnl.pom.base.BaseTest;
import nl.postnl.pom.objects.BillingAddress;
import nl.postnl.pom.objects.Product;
import nl.postnl.pom.objects.User;
import nl.postnl.pom.pages.CheckOutPage;
import nl.postnl.pom.utils.FakerUtils;
import nl.postnl.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest{

    @Test(description = "Validate the user can able to successfully able to login during checkout")
    public void loginDuringCheckOut() throws IOException, InterruptedException {
        String userName = "testUser"+new FakerUtils().generateRandomNumber();
        User user =  new User()
                .setUserName(userName)
                .setPassword("testUserPwd")
                .setEmailId(userName+"@test.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getProdId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        Thread.sleep(1000);
        injectCookiesToBrowser(cartApi.getCookies());
        Thread.sleep(2000);
        checkOutPage
                .load();
        Thread.sleep(2000);
        checkOutPage
                .clickOnCheckOutLoginButton()
                .login(user);
        Thread.sleep(2000);
        Assert.assertTrue(checkOutPage.getProductName().contains(product.getProdName()));
    }
}
