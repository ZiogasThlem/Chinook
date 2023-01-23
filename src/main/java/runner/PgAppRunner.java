package runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;
import repositories.CustomerImpl;

@Component
public class PgAppRunner implements ApplicationRunner {

    private final CustomerImpl customer;

    public PgAppRunner(CustomerImpl customer) {
        this.customer = customer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        customer.test();
    }
}