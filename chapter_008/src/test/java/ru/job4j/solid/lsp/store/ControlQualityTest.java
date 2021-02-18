package ru.job4j.solid.lsp.store;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ControlQualityTest {
    @Test
    public void moveProduct() {
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();

        Date currentDate = getDate("4/1/2021");
        ControlQuality controlQuality = new ControlQuality(currentDate, List.of(warehouse, shop, trash));
        generateFoods().forEach(controlQuality::moveProduct);
        Assert.assertEquals(trash.countProduct(), 1);
        Assert.assertEquals(shop.countProduct(), 1);
        Assert.assertEquals(warehouse.countProduct(), 1);

    }

    private List<Food> generateFoods() {
        return List.of(
                new Orange(
                        "Orange",
                        getDate("1/01/2021"),
                        getDate("1/06/2021"),
                        100.0,
                        0.0),
                new Bread(
                        "French bread",
                        getDate("2/1/2021"),
                        getDate("4/1/2021"),
                        100.0,
                        0.0),
                new Bread(
                        "Bread white",
                        getDate("2/1/2021"),
                        getDate("5/1/2021"),
                        100.0,
                        0.0)
        );
    }

    private Date getDate(String string) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}