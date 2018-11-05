package by.bsuir.gurinov.dao;

import java.util.ArrayList;

public interface EntityDao<T> {
    ArrayList<T> getAll();
    void add(T obj);
    boolean delete(T obj);
}
