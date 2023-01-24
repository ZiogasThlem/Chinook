package com.example.chinook_manipulation.repositories;

import com.example.chinook_manipulation.models.Customer;

public interface CustomerRepository extends CRUDRepository<Customer, Integer, String>{
    void test();
}
