package repositories;

import models.CustomerGenre;
import models.CustomerSpender;

import java.util.List;

public interface CRUDRepository <T, ID, N>{
    List<T> readAll();

    T readByID(ID id);

    N readByName(N name);

    List<T> page(int limit, int offset); //4)

    int insert(T object);

    int update(T object);

    String countryWithMostCustomers();

    CustomerSpender highestSpender();

    CustomerGenre mostPopGenre();
}