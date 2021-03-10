package ru.job4j.solid.lsp.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ControlQuality implements FoodMover, FoodExtractor {
    private Date currentDate;
    private final List<Store> stores;

    public ControlQuality(Date currentDate, List<Store> stores) {
        this.currentDate = currentDate;
        this.stores = stores;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
        this.currentDate = new Date();
    }

    @Override
    public void moveProduct(Food food) {
        stores.stream()
                .filter(store -> store.accept(food, currentDate))
                .findFirst()
                .ifPresent(store -> store.addProduct(food));
    }

    public void resort() {
        extractFoods().forEach(this::moveProduct);
    }

    @Override
    public Collection<Food> extractFoods() {
        List<Food> foodList = new ArrayList<>();

        stores.stream()
                .filter(store -> store instanceof FoodExtractor)
                .map(store -> (FoodExtractor) store)
                .forEach(foodExtractor -> foodList.addAll(foodExtractor.extractFoods()));

        return foodList;
    }
}
