package create.customer;

import com.braintreegateway.*;
import io.micronaut.function.executor.FunctionInitializer;
import io.micronaut.function.FunctionBean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

@FunctionBean("create-customer")
public class CreateCustomerFunction extends FunctionInitializer implements Function<CreateCustomer, Boolean> {
	private static BraintreeGateway gateway = Application.gateway;

	@Override
	public Boolean apply(CreateCustomer customer) {
		Result<Customer> customerResult = gateway.customer()
				.create(new CustomerRequest()
						.firstName(customer.getFirstName())
						.lastName(customer.getLastName())
						.email(customer.getEmail())
						.phone(customer.getPhone()));
		return customerResult.isSuccess();
	}

	/**
	 * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar
	 * where the argument to echo is the JSON to be parsed.
	 */
	public static void main(String... args) throws IOException {
		File file = new File("gateway.properties");
		if(file.exists())
			gateway = BraintreeGatewayFactory.fromConfigFile(file);
		else
			gateway = BraintreeGatewayFactory.fromConfigMapping(System.getenv());
		CreateCustomerFunction function = new CreateCustomerFunction();
		function.run(args, (context) -> function.apply(context.get(CreateCustomer.class)));
	}
}

