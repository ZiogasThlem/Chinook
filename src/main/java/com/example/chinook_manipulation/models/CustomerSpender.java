package com.example.chinook_manipulation.models;

/* Implementing a record for customer with the required fields.
Used for highestSpender()  method */
public record CustomerSpender(int id,
                              String name,
                              double total) {
}
