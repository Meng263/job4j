package ru.job4j.solid.isp.menu;

import java.util.List;

public interface Node<T> {
    T getParent();

    List<T> getChildren();

    void addChild(T child);
}
