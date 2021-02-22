package ru.job4j.solid.isp.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MenuElement implements Node<MenuElement>, MenuItem {
    private final int level;
    private final MenuElement parent;
    private final List<MenuElement> children = new ArrayList<>();
    private final String name;
    private final String title;

    public MenuElement(int level, MenuElement parent, String name) {
        this.level = level;
        this.parent = parent;
        this.name = name;
        this.title = createTitle();
    }

    public String getName() {
        return name;
    }

    @Override
    public void exec() {
        System.out.printf("elem with level %d selected!%n", level);
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public String getTitle() {
        return title;
    }

    private String createTitle() {
        MenuElement parentElem = this;
        StringBuilder sb = new StringBuilder(name);
        while (parentElem.getParent() != null) {
            sb.append(String.format(".%s", parentElem.getParent().getName()));
            parentElem = parentElem.getParent();
        }
        List<String> split = Arrays.asList(sb.toString().split("\\."));
        Collections.reverse(split);
        return split.stream()
                .reduce((first, second) -> String.format("%s.%s", first, second))
                .orElse("");
    }

    @Override
    public MenuElement getParent() {
        return parent;
    }

    @Override
    public List<MenuElement> getChildren() {
        return children;
    }

    @Override
    public void addChild(MenuElement child) {
        children.add(child);
    }
}
