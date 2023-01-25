package com.example.chinook_manipulation.runner;

import com.example.chinook_manipulation.models.Customer;
import com.example.chinook_manipulation.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class PgAppRunner implements CommandLineRunner {

    final CustomerRepository customer;

    public PgAppRunner(CustomerRepository customer) {
        this.customer = customer;
    }

    @Override
    public void run(String... args) throws Exception {
        //Customer requirements: requirement 1
        System.out.println("Customer requirements: requirement 1");
        System.out.println(customer.readAll());
        //Customer requirements: requirement 2
        System.out.println("Customer requirements: requirement 2");
        System.out.println(customer.readByID(3));
        //Customer requirements: requirement 3
        System.out.println("Customer requirements: requirement 3");
        System.out.println(customer.readByName("Daan"));
        //Customer requirements: requirement 4
        System.out.println("Customer requirements: requirement 4");
        System.out.println(customer.page(5,3));
        //Customer requirements: requirement 5
        System.out.println("Customer requirements: requirement 5");
        Customer custom = new Customer(45,"christos","Giannikis","Greece","53321","6970234387","xrist.ginas@hotmail.com");
        System.out.println(customer.insert(custom));
        //Customer requirements: requirement 6
        System.out.println("Customer requirements: requirement 6");
        custom = new Customer(60,"Christos","Giannikis","Greece","32480","6970234387","xrist.ginas@hotmail.com");
        System.out.println(customer.update(custom));
        //Customer requirements: requirement 7
        System.out.println("Customer requirements: requirement 7");
        System.out.println(customer.countryWithMostCustomers());
        //Customer requirements: requirement 8
        System.out.println("Customer requirements: requirement 8");
        System.out.println(customer.highestSpender());
        //Customer requirements: requirement 9
        System.out.println("Customer requirements: requirement 9");
        System.out.println(customer.mostPopGenre(21));
    }
}