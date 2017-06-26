package com.example.mond.googlemaprealm.common;

public interface BaseCrud<T, T1> {

    void insert(T t);
    void getById(String id, T1 listener);
    void updateById(String id, T t);
    void deleteById(String id);
}
