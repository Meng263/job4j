package ru.job4j.map;

import java.util.Calendar;

/**
 * @since 23.08.19
 * Класс описывает пользователя
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
}
