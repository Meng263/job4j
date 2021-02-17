package ru.job4j.solid.lsp.store;

import java.util.Date;

public interface Store {
    int capacity();

    int countProduct();

    boolean accept(Food food, Date currentDate);

    boolean addProduct(Food food);
}
