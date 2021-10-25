package composers;

import entities.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ProductComposerTest {

    @Test
    void shouldCreateProduct() {
        //given
        ProductComposer composer = new ProductComposer();
        Product product = new Product();
        //when
        product.setProductID(1);
        product = composer.createProduct(
                "yt-0400",
                "Zestaw kluczy",
                5,
                BigDecimal.valueOf(42.00));
        //then

        assertThat(product, notNullValue());
        assertThat(product, instanceOf(Product.class));
    }

    @Test
    void shoudCalculateGrossPurchasePriceCorrectly(){
        //given
        ProductComposer composer = new ProductComposer();
        Product product = new Product();

        //when

        product = composer.createProduct(
                "yt-0400",
                "Zestaw kluczy",
                5,
                BigDecimal.valueOf(42));
        //then

        assertThat(product.getCatalogNumber(), equalTo("yt-0400"));
        assertThat(product.getGrossPurchasePrice(), equalTo(BigDecimal.valueOf(51.66)));
    }


    @Test
    void shoudCalculateNetSellingPriceCorrectly(){
        //given
        ProductComposer composer = new ProductComposer();
        Product product = new Product();

        //when

        product = composer.createProduct(
                "yt-0400",
                "Zestaw kluczy",
                5,
                BigDecimal.valueOf(42));
        //then


        assertThat(product.getNetSellingPrice(), equalTo(BigDecimal.valueOf(67.20)));
    }

    //todo dopisać @BeforeEach z produktem bo jest w kazdym tescie

}