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
    public void testFunction() throws Exception {
    	CreateCustomer body = new CreateCustomer();
    	body.setName("create-customer");
        assertEquals("create-customer", client.apply(body).blockingGet().getName());
    }
}
