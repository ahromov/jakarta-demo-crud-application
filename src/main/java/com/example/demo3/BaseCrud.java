package com.example.demo3;

import java.util.List;

public interface BaseCrud<T, V> {

    T find(V id);

    List<T> findAll();

    T create(T t);

    T update(T t);

    void delete(V id);
}
