package ru.job4j.solid.isp.menu;

public interface MenuItem {
    void exec();

    int getLevel();

    String getTitle();
}
