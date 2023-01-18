package nl.postnl.pom.objects;

import nl.postnl.pom.utils.JacksonUtils;

import java.io.IOException;

public class Product {

    private int prodId;
    private String prodName;

    public Product() {
    }

    public Product(int prodId) throws IOException {
        Product[] products=JacksonUtils.deserializeJson("products.json", Product[].class);
        for(Product product: products){
            if(product.getProdId()==prodId){
                this.prodId=prodId;
                this.prodName=product.getProdName();
            }
        }
    }


    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }


}
