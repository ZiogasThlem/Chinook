package repositories;

import models.Customer;
import models.CustomerCountry;
import models.CustomerGenre;
import models.CustomerSpender;

import java.util.List;

public interface CRUDRepository <T, ID, N>{
    List<T> readAll();

    T readByID(ID id);

    Customer readByName(N name);

    List<Customer> page(int limit, int offset); //4)

    int insert(T object);

    int update(T object);

    CustomerCountry countryWithMostCustomers();

    CustomerSpender highestSpender();

    CustomerGenre mostPopGenre();
}