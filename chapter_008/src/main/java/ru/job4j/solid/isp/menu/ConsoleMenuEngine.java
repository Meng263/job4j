package ru.job4j.solid.isp.menu;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class ConsoleMenuEngine implements MenuEngine {
    private final MenuElement root;

    public ConsoleMenuEngine(MenuElement root) {
        this.root = root;
    }

    @Override
    public void showMenu() {
        System.out.println(root.getTitle());
        actionOnEachElem(
                root,
                element -> System.out.printf("%s%s%n", "---".repeat(element.getLevel()), element.getTitle())
        );
    }

    private void actionOnEachElem(MenuElement element, Consumer<MenuElement> consumer) {
        element.getChildren().forEach(child -> {
            consumer.accept(child);
            actionOnEachElem(child, consumer);
        });
    }

    public MenuElement searchMenuElem(String title) {
        if (root.getTitle().equals(title)) return root;

        Queue<MenuElement> queue = new LinkedList<>(root.getChildren());
        MenuElement result = null;
        while (!queue.isEmpty()) {
            MenuElement element = queue.poll();
            if (element.getTitle().equals(title)) {
                result = element;
                break;
            } else {
                queue.addAll(element.getChildren());
            }

        }
        return result;
    }

    @Override
    public void execMenuElementAction() {

    }
}
