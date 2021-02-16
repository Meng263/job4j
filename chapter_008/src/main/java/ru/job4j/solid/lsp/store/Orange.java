package ru.job4j.solid.lsp.store;

import java.util.Date;

public class Orange extends Food {
    public Orange(String name, Date createDate, Date expiryDate, double price, double discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
