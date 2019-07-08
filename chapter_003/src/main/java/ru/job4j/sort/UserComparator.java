package ru.job4j.sort;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {
    @Override
    public int compare(User left, User right) {
        return Integer.compare(left.name.length(), right.name.length());
    }
}
