package create.customer;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class CreateCustomerFunctionTest {

    @Inject
    CreateCustomerClient client;

    @Test
    public void testFunction() {
    	CreateCustomer body = new CreateCustomer();
    	body.setFirstName("create-customer");
        assertEquals("create-customer", client.apply(body).blockingGet().getFirstName());
    }
}
