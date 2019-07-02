package ru.job4j.tracker;

import java.util.Arrays;
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
    private final Item[] items = new Item[100];

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
            if ((items[i] != null) && (id.equals(items[i].getId()))) {
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
        this.items[this.position++] = item;
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
            items[index] = item;
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
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, items.length - index - 1);
            position--;
            return true;
        }
        return false;
    }

    /**
     * Метод  возвращает копию массива this.items без null элементов
     *
     * @return массив
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Метод проверяет в цикле все элементы массива items, сравнивая name
     *
     * @param key ключ
     * @return результируюй массив, содержащий элементы с искомым name
     */
    public Item[] findByName(String key) {
        Item[] array = new Item[position];
        int k = 0;
        for (int i = 0; i < position; i++) {
            if ((items[i] != null) && (key.equals(items[i].getName()))) {
                array[k++] = items[i];
            }
        }
        return Arrays.copyOf(array, k);
    }

    /**
     * Метод проверяет в цикле все элементы массива items, сравнивая id
     *
     * @param id уникальный идентификатор
     * @return искомый элемент, имеющий уникальный id
     */
    public Item findById(String id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) {
                return items[i];
            }
        }
        return null;
    }
}