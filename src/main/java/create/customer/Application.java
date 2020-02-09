package create.customer;

import com.braintreegateway.BraintreeGateway;
import io.micronaut.runtime.Micronaut;

import java.io.File;

public class Application {
    static BraintreeGateway gateway;

    public static void main(String[] args) {
        File file = new File("gateway.properties");
        if(file.exists())
            gateway = BraintreeGatewayFactory.fromConfigFile(file);
        else
            gateway = BraintreeGatewayFactory.fromConfigMapping(System.getenv());
        Micronaut.run(Application.class);
    }
}