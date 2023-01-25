package com.example.chinook_manipulation.repositories;

import com.example.chinook_manipulation.models.Customer;
import com.example.chinook_manipulation.models.CustomerCountry;
import com.example.chinook_manipulation.models.CustomerGenre;
import com.example.chinook_manipulation.models.CustomerSpender;

import java.util.List;

public interface CRUDRepository <T, ID>{

    List<T> readAll();
    List<T> page(int limit, int offset);
    int insert(T object);
    int update(T object);

}