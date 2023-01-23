package repositories;

import models.CustomerCountry;

import java.util.List;

public interface CRUDRepository <T, ID, N>{
    List<T> readAll();

    T readByID(ID id);  // ***********

    T   readByName(N name);  // ***********

    T page(int limit, int offset);

    int insert(T object);

    int update(T object);

    CustomerCountry countryWithMostCustomers(); // ***********

    T highestSpender();

    String mostPopGenre(); // ***********
}