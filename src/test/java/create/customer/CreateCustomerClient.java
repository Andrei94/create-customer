package create.customer;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;
import javax.inject.Named;

@FunctionClient
public interface CreateCustomerClient {

    @Named("create-customer")
    Single<CreateCustomer> apply(@Body CreateCustomer body);

}
