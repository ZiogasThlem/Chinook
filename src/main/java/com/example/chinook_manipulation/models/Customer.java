package com.example.chinook_manipulation.models;

/* Implementing a record for customer with the required fields */
public record Customer(int id,
                       String first_name,
                       String last_name,
                       String country,
                       String postal_code,
                       String phone,
                       String email) {
}
