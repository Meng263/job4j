package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    public final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

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
        return "" + (int) Math.random() * 100;
    }

    /**
     * Метод заменяет ячейку в массиве по ключу
     *
     * @param id   ключ
     * @param item элемент, на который будем менять
     * @return true, если элемент заменен
     */
    public boolean replace(String id, Item item) {
        int k = 0;
        for (Item i : items) {
            if (i.getId().equals(id)) {
                items[k] = item;
                return true;
            }
            k++;
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
        int k = 0;
        boolean bool = false;
        for (Item i : items) {
            if (id.equals(i.getId())) {
                bool = true;
                break;
            }
            k++;
        }
        if (bool) {

            System.arraycopy(items, k + 1, items, k, items.length - k - 1);
        }
        return bool;
    }

    /**
     * Метод  возвращает копию массива this.items без null элементов
     * сначала вычисляет размер возвращаемого массива, затем заполняет его
     *
     * @return массив
     */
    public Item[] findAll() {
        int k = 0;
        for (Item i : items)
            if (i != null) k++;
        Item[] array = new Item[k + 1];
        k = 0;
        for (Item i : items) {
            array[k++] = i;
        }
        return array;
    }

    /**
     * Метод проверяет в цикле все элементы массива items, сравнивая name
     *
     * @param key ключ
     * @return результируюй массив, содержащий элементы с искомым name
     */
    public Item[] findByName(String key) {
        int k = 0;
        for (int i = 0; i < items.length; i++) {
            if ((items[i] != null) && (key.equals(items[i].getName()))) {
                k++;
            }
        }
        Item[] array = new Item[k+1];
        k = 0;
        for (int i = 0; i < items.length; i++) {
            if ((items[i] != null) && (key.equals(items[i].getName()))) {
                array[k++] = items[i];
            }
        }
        return array;
    }

    public Item findById(String id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId().equals(id)) return items[i];
        }
        return null;
    }


}