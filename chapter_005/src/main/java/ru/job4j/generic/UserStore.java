package ru.job4j.generic;

public class UserStore extends AbstractStore<User> {
    public UserStore(SimpleArray<User> values) {
        super(values);
    }
}
