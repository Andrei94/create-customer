package create.customer;

import com.braintreegateway.BraintreeGateway;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class BraintreeGatewayFactory {
	public static BraintreeGateway fromConfigMapping(Map<String, String> mapping) {
		return new BraintreeGateway(
				mapping.get("environment"),
				mapping.get("merchantId"),
				mapping.get("publicKey"),
				mapping.get("privateKey")
		);
	}

	public static BraintreeGateway fromConfigFile(File configFile) {
		InputStream inputStream = null;
		Properties properties = new Properties();

		try {
			inputStream = new FileInputStream(configFile);
			properties.load(inputStream);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		return new BraintreeGateway(
				properties.getProperty("environment"),
				properties.getProperty("merchantId"),
				properties.getProperty("publicKey"),
				properties.getProperty("privateKey")
		);
	}
}
