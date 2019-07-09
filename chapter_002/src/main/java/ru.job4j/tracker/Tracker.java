package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Трекер заявок
 *
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>(100);

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод определяет индекс элемента по его id
     *
     * @param id id
     * @return индекс, в случае если элемент не найден, возвращает -1
     */
    int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < position; i++) {
            if ((items.get(i) != null) && (id.equals(items.get(i).getId()))) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(position++ ,item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        final Random random = new Random();
        return "" + random.nextInt();
    }

    /**
     * Метод заменяет ячейку в массиве по ключу
     *
     * @param id   ключ
     * @param item элемент, на который будем менять
     * @return true, если элемент заменен
     */
    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index != -1) {
            items.set(index, item);
            item.setId(id);
            return true;
        }
        return false;
    }

    /**
     * Удаляет элемент из массива и сдвигает его.
     *
     * @param id идентификатор удаляемого элемента
     * @return true, если удаление произошло
     */
    public boolean delete(String id) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
            position--;
            result = true;
        }
        return result;
    }

    /**
     * Метод  возвращает копию массива this.items без null элементов
     *
     * @return массив
     */
    public List<Item> findAll() {
        return  items;
    }

    /**
     * Метод проверяет в цикле все элементы массива items, сравнивая name
     *
     * @param key ключ
     * @return результируюй массив, содержащий элементы с искомым name
     */
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        for(Item item : items) {
            if (item.getName().equals(key)){
                list.add(item);
            }
        }
        return list;
    }

    /**
     * Метод проверяет в цикле все элементы массива items, сравнивая id
     *
     * @param id уникальный идентификатор
     * @return искомый элемент, имеющий уникальный id
     */
    public Item findById(String id) {
        Item result = null;
            for (Item item : items) {
                if (item.getId().equals(id)){
                    result = item;
                }
            }
        return result;
    }
}