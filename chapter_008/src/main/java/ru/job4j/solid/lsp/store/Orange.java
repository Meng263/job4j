package ru.job4j.solid.lsp.store;

import java.util.Date;

public class Orange extends Food {
    public Orange(String name, Date expiryDate, Date createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
