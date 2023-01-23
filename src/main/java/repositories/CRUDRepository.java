package repositories;

import java.util.List;

public interface CRUDRepository <T, ID, N>{
    List<T> readAll();

    T readByID(ID id);

    N readByName(N name);

    T page(int limit, int offset); //4)

    int insert(T object);

    int update(T object);

    String countryWithMostCustomers();

    T highestSpender();

    String mostPopGenre();
}