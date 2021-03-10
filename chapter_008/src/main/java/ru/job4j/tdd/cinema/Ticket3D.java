package ru.job4j.tdd.cinema;

import java.util.Objects;

public class Ticket3D implements Ticket {
    private final int id = 1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket3D ticket3D = (Ticket3D) o;
        return id == ticket3D.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
