package com.example.chinook_manipulation.repositories;

import java.util.List;

/* CRUDRepository interface */
public interface CRUDRepository <T, ID> {

    List<T> readAll();
    List<T> pageOfCustomers(int limit, int offset);
    int insert(T object);
    int update(T object);

}