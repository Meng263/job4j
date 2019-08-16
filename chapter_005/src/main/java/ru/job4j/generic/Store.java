package ru.job4j.generic;

/**
 * @param <T> тип модели
 * @author Oleg Sivakov
 * * @version $Id$
 * * @since 0.1
 */
public interface Store<T extends Base> {
    /**
     * Добавление позиции
     *
     * @param model модель
     */
    void add(T model);

    /**
     * Замена позиции
     *
     * @param id    идентификатор
     * @param model модель
     * @return true, в случае успеха
     */
    boolean replace(String id, T model);

    /**
     * Уадлеине модели по идентификатору
     *
     * @param id идентификатор
     * @return true, в случае успеха
     */
    boolean delete(String id);

    /**
     * Поиск модели по идентификатору
     *
     * @param id идентификатор
     * @return Модель
     */
    T findById(String id);
}
