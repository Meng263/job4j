package ru.job4j.list2map;

import java.util.Objects;

public class User {
    private int id;
    private String name, sity;

    public User(int id, String name, String sity) {
        this.id = id;
        this.name = name;
        this.sity = sity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSity() {
        return sity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSity(String sity) {
        this.sity = sity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(sity, user.sity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sity);
    }
}
