package com.example.chinook_manipulation.repositories;

import com.example.chinook_manipulation.models.Customer;
import com.example.chinook_manipulation.models.CustomerCountry;
import com.example.chinook_manipulation.models.CustomerGenre;
import com.example.chinook_manipulation.models.CustomerSpender;

/* CRUDRepository child Interfaces that defines CustomerRepositoryImpl specific behaviour */
public interface CustomerRepository extends CRUDRepository<Customer, Integer> {


    Customer readByID(int id);
    Customer readByName(String name);
    CustomerCountry countryWithMostCustomers();
    CustomerSpender highestSpender();
    CustomerGenre mostPopGenre(int id);
}