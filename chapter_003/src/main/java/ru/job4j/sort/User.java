package ru.job4j.sort;

import java.util.Objects;

/**
 * Пользователь с параметрами возраст и имя
 */
public class User implements Comparable<User> {
    Integer age;
    String name;

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }
}
