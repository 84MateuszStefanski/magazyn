package composers;

import entities.Customer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerComposerTest {

    @Test
    void shouldCreateCustomer() {
        //given
        CustomerComposer composer = new CustomerComposer();
        Customer customer = composer.createCustomer(
                "Maxtool",
                "5555555555",
                "Maxtool spółka jawna",
                "05-090",
                "Wschodnia 3",
                "Raszyn",
                "Poland",
                "517771863",
                "maxtool@pl.pl"
        );
        customer.setCustomerID(1);
        //then

        assertThat(customer).isInstanceOf(Customer.class);
        assertThat(customer.getCustomerName()).isEqualTo("Maxtool");
        assertThat(customer.getCustomerLevel().getDiscount()).isEqualTo(1);
    }
}