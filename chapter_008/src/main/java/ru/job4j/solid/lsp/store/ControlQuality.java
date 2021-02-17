package ru.job4j.solid.lsp.store;

import java.util.Date;
import java.util.List;

public class ControlQuality implements FoodMover {
    private final Date currentDate;
    private final List<Store> stores;

    public ControlQuality(Date currentDate, List<Store> stores) {
        this.currentDate = currentDate;
        this.stores = stores;
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
}
