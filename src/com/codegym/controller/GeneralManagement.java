package com.codegym.controller;

public interface GeneralManagement<T> {
    void displayAll();

    void addNew(T t);

    void updateByName(String name, T t);

    boolean deleteByName(String name);

    T getByName(String name);
}
