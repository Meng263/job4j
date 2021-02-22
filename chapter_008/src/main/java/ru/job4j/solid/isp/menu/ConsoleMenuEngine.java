package ru.job4j.solid.isp.menu;

import java.util.function.Consumer;

public class ConsoleMenuEngine implements MenuEngine {
    private final MenuElement root;

    public ConsoleMenuEngine(MenuElement root) {
        this.root = root;
    }

//    @Override
//    public void showMenu() {
//        Queue<MenuElement> queue = new LinkedList<>(root.getChildren());
//        System.out.println(root.getTitle());
//        while (!queue.isEmpty()) {
//            MenuElement currentElement = queue.poll();
//            System.out.println(currentElement.getTitle());
//            List<MenuElement> children = currentElement.getChildren();
//            queue.addAll(children);
//        }
//
//    }

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

    @Override
    public void execMenuElementAction() {

    }

    public static void main(String[] args) {
        MenuElement root = new MenuElement(0, null, "Task 1");
        MenuElement childOne = new MenuElement(1, root, "A");
        MenuElement childTwo = new MenuElement(1, root, "B");
        MenuElement grandsonOne = new MenuElement(2, childOne, "a");
        MenuElement grandsonTwo = new MenuElement(2, childOne, "b");
        MenuElement grandsonThree = new MenuElement(2, childTwo, "a");
        MenuElement grandsonFour = new MenuElement(2, childTwo, "b");
        root.addChild(childOne);
        root.addChild(childTwo);
        childOne.addChild(grandsonOne);
        childOne.addChild(grandsonTwo);
        childTwo.addChild(grandsonThree);
        childTwo.addChild(grandsonFour);

        ConsoleMenuEngine engine = new ConsoleMenuEngine(root);
        engine.showMenu();


    }
}
