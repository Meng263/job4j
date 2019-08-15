package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор итераторов
 */
public class Converter {
    /**
     * Метод объединяет вложенные итераторы в один
     *
     * @param it Итератор итераторов
     * @return объединенный итератор
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> innerIterator;

            /**
             * Метод hasNext объединенного итератора
             * @return true, если есть следующий элемент
             */
            @Override
            public boolean hasNext() {
                while (innerIterator == null || (!innerIterator.hasNext() && it.hasNext())) {
                    innerIterator = it.next();
                }
                return innerIterator.hasNext();
            }

            /**
             * Метод next объединенного итератора
             * @return следующий элемент
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return innerIterator.next();
            }
        };
    }
}
