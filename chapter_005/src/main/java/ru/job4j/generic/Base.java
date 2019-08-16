package ru.job4j.generic;

/**
 * @author Oleg Sivakov
 * * @version $Id$
 * * @since 0.1
 */
public abstract class Base {
    private final String id;

    /**
     * Конструктор
     * @param id идентификатор
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Геттер идентификатора
     * @return идентификатор
     */
    public String getId() {
        return id;
    }
}