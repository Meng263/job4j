package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация HashMap через массив
 *
 * @param <K> ключ
 * @param <V> значение
 */
public class SimpleHashMap<K, V> implements Iterable<V> {
    /**
     * Массив, который хранит пары ключ-значение, индекс вычисляется хеш-функцией
     */
    private Object[] table;
    /**
     * Начальное кол-во элеметов таблицы
     */
    private static final int INIT_TABLE_SIZE = 16;
    /**
     * Лоад фактор, т.к. не рализована обработка коллизий, значение 0,5
     * чтобы уменьшить вероятность их возниконовения
     */
    private static final float LOAD_FACTOR = 0.5f;
    /**
     * Кол-во элементов в карте
     */
    private int size;
    /**
     * Переменная для ослеживания изменений коллекции, для корректной работы итератора
     */
    private int modCount;

    /**
     * Итератор значений
     *
     * @return итератор
     */
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int position = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = position; i < table.length; i++) {
                    if (table[i] != null) {
                        result = true;
                        position = i;
                        break;
                    }
                }
                return result;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Entry<K, V> elem = (Entry<K, V>) table[position];
                V result = elem.getValue();
                position++;
                return result;
            }
        };
    }

    /**
     * Конструктор
     */
    public SimpleHashMap() {
        this.table = new Entry[INIT_TABLE_SIZE];
        size = 0;
        modCount = 0;
    }

    /**
     * Класс, хранит пары ключ-значение
     *
     * @param <K> ключ
     * @param <V> значение
     */
    private class Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * Вставка элемента в карту
     * Если кол-во элементов в карте превышает предельное количество элементов,
     * размер хэш-таблицы увеличивается вдвое
     *
     * @param key   ключ
     * @param value значение
     * @return true, если вставка успешна
     */
    public boolean insert(K key, V value) {
        if (size >= table.length * LOAD_FACTOR) {
            grow();
        }
        int keyHashcode = key != null ? key.hashCode() : 0;
        boolean result = false;
        int index = 0;
        index = indexFor(key, table.length);
        Entry<K, V> elem;
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            result = true;
            size++;
            modCount++;
        } else {
            elem = (Entry<K, V>) table[index];
            int elemKeyHashCode = elem.getKey() != null ? elem.getKey().hashCode() : 0;
            if (elemKeyHashCode == keyHashcode || elem.equals(key)) {
                elem.setValue(value);
                table[index] = elem;
                result = true;
                size++;
                modCount++;
            }
        }

        return result;
    }

    /**
     * Метод удваивает кол-во элементов таблицы
     * и пересчитывает позиции элементов в новой таблице
     */
    private void grow() {
        int newLength = table.length * 2;
        Object[] newTable = new Object[newLength];
        for (Object elem : table) {
            if (elem != null) {
                Entry<K, V> entry = (Entry<K, V>) elem;
                newTable[indexFor(entry.getKey(), newLength)] = elem;
            }
        }
        table = newTable;
    }

    /**
     * Метод Возвращает значение по ключу
     *
     * @param key ключ
     * @return значение
     */
    public V get(K key) {
        V result = null;
        int index = indexFor(key, table.length);
        Entry<K, V> elem;
        elem = (Entry<K, V>) table[index];
        if (elem != null) {
            result = elem.getValue();
        }
        return result;
    }

    /**
     * Метод удаляет пару из карты по ключу
     *
     * @param key ключ
     * @return true, если удаление успешно
     */
    public boolean delete(K key) {
        boolean result = false;
        int index = indexFor(key, table.length);
        Entry<K, V> elem = (Entry<K, V>) table[index];
        if (elem != null) {
            table[index] = null;
            result = true;
        }
        modCount++;
        return result;
    }

    /**
     * Хеш функция для равномерного распределения элементов по таблице
     *
     * @param hashCode хешкод
     * @return хеш
     */
    private static int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    /**
     * Метод вычисляет индекс по ключу
     *
     * @param key         ключ
     * @param tableLength размер таблицы
     * @return индекс
     */
    private static int indexFor(Object key, int tableLength) {
        int hashCode = (key != null) ? key.hashCode() : 0;
        return hash(hashCode) & (tableLength - 1);
    }
}
