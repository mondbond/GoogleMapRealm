package com.example.mond.googlemaprealm.common;

// TODO: 28/06/17 don't like this, read https://habrahabr.ru/post/263033/ and rewrite
public interface BaseCrud<T, T1> {

    void insert(T t);
    void getById(String id, T1 listener);
    void updateById(String id, T t);
    void deleteById(String id);
}
