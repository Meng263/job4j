package ru.job4j.phonebook;

public class ItemNotFoundException extends RuntimeException {
    ItemNotFoundException(String msg) {
        super(msg);
    }
}
