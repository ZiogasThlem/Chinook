package com.example.chinook_manipulation.runner;

import com.example.chinook_manipulation.models.Customer;
import com.example.chinook_manipulation.repositories.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/* Class that makes use of ApplicationRunner Interface to "run"
all CustomerRepositoryImpl methods that are specified in the
overridden run() method. */
@Component
public class PgAppRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    public PgAppRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Customer requirements: requirement 1
        System.out.println("Customer requirements: requirement 1");
        System.out.println(customerRepository.readAll());
        //Customer requirements: requirement 2
        System.out.println("Customer requirements: requirement 2");
        System.out.println(customerRepository.readByID(3));
        //Customer requirements: requirement 3
        System.out.println("Customer requirements: requirement 3");
        System.out.println(customerRepository.readByName("Daan"));
        //Customer requirements: requirement 4
        System.out.println("Customer requirements: requirement 4");
        System.out.println(customerRepository.pageOfCustomers(5,3));
        //Customer requirements: requirement 5
        System.out.println("Customer requirements: requirement 5");
        Customer custom = new Customer(45,"christos","Giannikis","Greece","53321","6970234387","xrist.ginas@hotmail.com");
        System.out.println(customerRepository.insert(custom));
        //Customer requirements: requirement 6
        System.out.println("Customer requirements: requirement 6");
        custom = new Customer(60,"Christos","Giannikis","Greece","32480","6970234387","xrist.ginas@hotmail.com");
        System.out.println(customerRepository.update(custom));
        //Customer requirements: requirement 7
        System.out.println("Customer requirements: requirement 7");
        System.out.println(customerRepository.countryWithMostCustomers());
        //Customer requirements: requirement 8
        System.out.println("Customer requirements: requirement 8");
        System.out.println(customerRepository.highestSpender());
        //Customer requirements: requirement 9
        System.out.println("Customer requirements: requirement 9");
        System.out.println(customerRepository.mostPopGenre(21));
    }
}