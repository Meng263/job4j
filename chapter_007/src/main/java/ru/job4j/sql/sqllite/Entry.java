package ru.job4j.sql.sqllite;

public class Entry {
    private int field;

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
