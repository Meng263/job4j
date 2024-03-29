package ru.job4j.tracker;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    /**
     * Констуктор Item
     *
     * @param name имя
     * @param description описание
     * @param time время
     */
    public Item(String name, String description, Date time) {
        this.name = name;
        this.description = description;
        this.time = time;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decs) {
        this.description = decs;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Метод выводит в консоль все поля заявки
     */
    public void show() {
        System.out.println("------------ ticket --------------");
        System.out.println("Item id " + getId());
        System.out.println("Item name " + getName());
        System.out.println("Item description " + getDescription());
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(description, item.description)
                && Objects.equals(time, item.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, time);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                '}';
    }
}