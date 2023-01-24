package com.example.chinook_manipulation.models;

public record Customer(int id,
                       String first_name,
                       String last_name,
                       String country,
                       int postal_code,
                       int phone_number,
                       String email) {
}
