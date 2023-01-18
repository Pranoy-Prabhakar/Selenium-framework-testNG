package nl.postnl.pom.tests;

import nl.postnl.pom.base.BaseTest;
import nl.postnl.pom.objects.Product;
import nl.postnl.pom.pages.CartPage;
import nl.postnl.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
    @Test(description = "Validate the product has been added in to cart from store page")
    public void addToCartFromStorePage() throws IOException {
        Product product= new Product(1215);
        CartPage cartPage = new StorePage(getDriver())
                .load()
                .clickAddToCartBtn(product.getProdName())
                .clickOnCartLink();

        Assert.assertEquals(cartPage.getAddedProductName(),product.getProdName()+ "asdfas");
    }

}
