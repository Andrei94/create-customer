package create.customer;

import com.braintreegateway.*;
import io.micronaut.function.executor.FunctionInitializer;
import io.micronaut.function.FunctionBean;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

@FunctionBean("create-customer")
public class CreateCustomerFunction extends FunctionInitializer implements Function<CreateCustomer, String> {
	private final BraintreeGateway gateway = BraintreeGatewayFactory.fromConfigFile(new File("gateway.properties"));

	@Override
	public String apply(CreateCustomer customer) {
		ResourceCollection<Customer> search = gateway.customer().search(new CustomerSearchRequest().email().is(customer.getEmail()).firstName().is(customer.getFirstName()).lastName().is(customer.getLastName()).phone().is(customer.getPhone()));
		if(search.iterator().hasNext())
			return search.getFirst().getId();
		Result<Customer> customerResult = gateway.customer()
				.create(new CustomerRequest()
						.firstName(customer.getFirstName())
						.lastName(customer.getLastName())
						.email(customer.getEmail())
						.phone(customer.getPhone()));
		return customerResult.getTarget().getId();
	}

	/**
	 * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar
	 * where the argument to echo is the JSON to be parsed.
	 */
	public static void main(String... args) throws IOException {
		CreateCustomerFunction function = new CreateCustomerFunction();
		function.run(args, (context) -> function.apply(context.get(CreateCustomer.class)));
	}
}

