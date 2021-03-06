package adminutils;

import entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Deprecated
class ProductRegistrationTest {

    private Product product;


    @BeforeEach
    void createProductForTests() {
        product = new Product();


        ProductRegistration registration = new ProductRegistration();
        product.setCatalogNumber("yt-0400");

        product.setProductName("productName");

        product.setNetPurchasePrice(new BigDecimal("42"));

        product.setQuantity(5);

        product.setGrossPurchasePrice(registration.grossPurchasePriceCalculate(product.getNetPurchasePrice()));
        product.setNetSellingPrice(registration.netSellPriceCalculate(product.getNetPurchasePrice()));
        product.setGrossSellingPrice(registration.grossSellPriceCalculate(product.getNetSellingPrice()));
    }


    @Test
    void shouldCreateProduct() {

        assertThat(product, notNullValue());
        assertThat(product, instanceOf(Product.class));
    }

    @Test
    void shoudCalculateGrossPurchasePriceCorrectly() {

        assertThat(product.getCatalogNumber(), equalTo("yt-0400"));
        assertThat(product.getGrossPurchasePrice(), equalTo(BigDecimal.valueOf(51.66)));
    }


    @Test
    void shoudCalculateNetSellingPriceCorrectly() {
        assertThat(product.getNetSellingPrice(), equalTo(BigDecimal.valueOf(67.20)));
    }

    @Test
    void shoudCalculaterGrossSellingPriceCorrectly() {
        assertThat(product.getGrossSellingPrice(), equalTo(BigDecimal.valueOf(82.656)));
    }



}