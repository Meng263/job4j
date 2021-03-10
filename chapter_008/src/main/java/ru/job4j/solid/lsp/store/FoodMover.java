package ru.job4j.solid.lsp.store;

public interface FoodMover {
    void moveProduct(Food product);

    void resort();
}
