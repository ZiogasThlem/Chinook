package models;

public record Customer(int id,
                       String firstName,
                       String lastName,
                       String country,
                       int postalCode,
                       int phoneNumber,
                       String email) {
}
