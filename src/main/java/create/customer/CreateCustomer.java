package create.customer;

import io.micronaut.core.annotation.*;

@Introspected
public class CreateCustomer {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

