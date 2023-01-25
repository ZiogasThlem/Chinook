package com.example.chinook_manipulation.models;

/* Implementing a record for customer with the required fields.
Used for mostPopGenre() method */
public record CustomerGenre(int id,
                            String name,
                            String genre) {
}
