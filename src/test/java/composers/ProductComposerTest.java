package composers;

import entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ProductComposerTest {

    private Product product;

    @BeforeEach
    void createProductForTests() {
        ProductComposer composer = new ProductComposer();
        product = composer.createProduct(
                "yt-0400",
                "Zestaw kluczy",
                5,
                BigDecimal.valueOf(42));
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