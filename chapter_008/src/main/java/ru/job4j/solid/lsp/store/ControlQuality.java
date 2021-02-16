package ru.job4j.solid.lsp.store;

import java.util.Date;

public class ControlQuality implements IControlQuality {
    private final Date currentDate;
    private final Warehouse wareHouse;
    private final Shop shop;
    private final Trash trash;

    public ControlQuality(Date currentDate, Warehouse wareHouse, Shop shop, Trash trash) {
        this.currentDate = currentDate;
        this.wareHouse = wareHouse;
        this.shop = shop;
        this.trash = trash;
    }

    public ControlQuality(Warehouse wareHouse, Shop shop, Trash trash) {
        this.wareHouse = wareHouse;
        this.shop = shop;
        this.trash = trash;
        this.currentDate = new Date();
    }

    @Override
    public void moveProduct(Food food) {
        int freshnessCoefficient = evaluateFreshnessCoefficient(
                currentDate,
                food.getCreateDate(),
                food.getExpiryDate()
        );

        if (freshnessCoefficient < 25) {
            wareHouse.addProduct(food);
        } else if (freshnessCoefficient > 25 && freshnessCoefficient < 75) {
            food.setDiscount(25);
            shop.addProduct(food);
        } else if (freshnessCoefficient >= 100) {
            trash.addProduct(food);
        }
    }

    private int evaluateFreshnessCoefficient(Date currentDate, Date createDate, Date expiryDate) {
        long allTerm = expiryDate.getTime() - createDate.getTime();
        long spendTerm = currentDate.getTime() - createDate.getTime();

        return (int) Math.ceil(((double) spendTerm / allTerm) * 100);
    }
}
