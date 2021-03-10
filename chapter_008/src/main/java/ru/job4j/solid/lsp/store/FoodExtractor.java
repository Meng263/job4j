package ru.job4j.solid.lsp.store;

import java.util.Collection;

public interface FoodExtractor {
    Collection<Food> extractFoods();
}
