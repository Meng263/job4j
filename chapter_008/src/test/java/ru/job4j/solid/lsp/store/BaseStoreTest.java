package ru.job4j.solid.lsp.store;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BaseStoreTest {
    @Test
    public void extractWarehouseTest() {
        Food orange = new Orange("orange", new Date(), new Date(), 20, 0);
        Food bread = new Bread("fresh bread", new Date(), new Date(), 20, 0);

        Warehouse warehouse = new Warehouse();
        warehouse.addProduct(orange);
        warehouse.addProduct(bread);

        Collection<Food> extractedFoods = warehouse.extractFoods();
        Assert.assertEquals(extractedFoods, List.of(orange, bread));
        Assert.assertEquals(warehouse.countProduct(), 0);
    }

    @Test
    public void extractShopTest() {
        Food orange = new Orange("orange", new Date(), new Date(), 20, 0);
        Food bread = new Bread("fresh bread", new Date(), new Date(), 20, 0);

        Shop shop = new Shop();
        shop.addProduct(orange);
        shop.addProduct(bread);

        Collection<Food> extractedFoods = shop.extractFoods();
        Assert.assertEquals(extractedFoods, List.of(orange, bread));
        Assert.assertEquals(shop.countProduct(), 0);
    }
}