package ru.job4j.generic;

/**
 * @param <T> тип модели
 * @author Oleg Sivakov
 * * @version $Id$
 * * @since 0.1
 */
public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> values;

    /**
     * Конструктор
     *
     * @param values массив объектов
     */
    public AbstractStore(SimpleArray<T> values) {
        this.values = values;
    }

    public SimpleArray<T> getValues() {
        return values;
    }

    /**
     * Добавление позиции
     *
     * @param model модель
     */
    @Override
    public void add(T model) {
        values.add(model);
    }

    /**
     * Замена позиции
     *
     * @param id    идентификатор
     * @param model модель
     * @return true, если замена успешно выполнена
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        if (findIndexById(id) != -1) {
            values.set(findIndexById(id), model);
            result = true;
        }
        return result;
    }

    /**
     * Уадлеине модели по идентификатору
     *
     * @param id идентификатор
     * @return true, если модель удалена
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findIndexById(id) != -1) {
            values.remove(findIndexById(id));
            result = true;
        }
        return result;
    }

    /**
     * Поиск модели по идентификатору
     *
     * @param id идентификатор
     * @return Модель
     */
    @Override
    public T findById(String id) {
        T result = null;
        if (findIndexById(id) != -1) {
            result = values.get(findIndexById(id));
        }
        return result;
    }

    /**
     * Поиск индекса модели по идентификатору
     *
     * @param id идентификатор
     * @return индекс
     */
    private int findIndexById(String id) {
        int result = -1;
        int counter = 0;
        for (T value : values) {
            if (value.getId().equals(id)) {
                result = counter;
            }
            counter++;
        }
        return result;
    }
}
