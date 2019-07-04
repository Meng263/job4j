package ru.job4j.phoneBook;

public class ItemNotFoundException extends RuntimeException {
    ItemNotFoundException(String msg) {
        super(msg);
    }
}
