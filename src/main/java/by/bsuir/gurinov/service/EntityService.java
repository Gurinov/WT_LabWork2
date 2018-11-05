package by.bsuir.gurinov.service;

import java.util.ArrayList;

public interface EntityService<T> {
    ArrayList<T> getAll();
    boolean add(T obj);
}
