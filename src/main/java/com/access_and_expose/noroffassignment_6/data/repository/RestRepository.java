package com.access_and_expose.noroffassignment_6.data.repository;

import java.util.ArrayList;
import java.util.Collection;

public interface RestRepository<T> {
    Collection<T> getAll();
    Collection<T> getAll(int offset, int limit);
    T getById(String id);
    Collection<T> getByName(String name);
    T add(T item);
    boolean update(T item);
    boolean deleteById(String id);
    ArrayList<T> getFromSQLDatabase(String SQLQuery, String... params);
}
