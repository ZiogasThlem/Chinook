package com.example.chinook_manipulation.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;
import com.example.chinook_manipulation.repositories.CustomerRepositoryImpl;
import com.example.chinook_manipulation.repositories.CustomerRepository;

@Component
public class PgAppRunner implements ApplicationRunner {

    private final CustomerRepository customer;

    public PgAppRunner(CustomerRepositoryImpl customer) {
        this.customer = customer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(customer.countryWithMostCustomers());
    }
}