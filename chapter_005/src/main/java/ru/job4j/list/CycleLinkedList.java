package ru.job4j.list;

/**
 * Проверка связного списка на цикличность
 */
public class CycleLinkedList {
    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

   private Node<Integer> first = new Node<>(1);
   private Node<Integer> two = new Node<>(2);
   private Node<Integer> third = new Node<>(3);
   private Node<Integer> four = new Node<>(4);

    /**
     * Инициализация списка с зацикленностю
     */
    public void initCycle() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
    }

    /**
     * Инициализация списка без зацикливания
     */
    public void initNonCycle() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
    }

    /**
     * Метод определяет есть ли зацикленность в списке
     *
     * @param first первый элемент списка
     * @return true, если список зацикленн
     */
    boolean hasCycle(Node first) {
        Node slow = first;
        Node fast = first;
        boolean result = false;
        while (slow != null && fast != null) {
            if (fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            if ((slow != null && fast != null) && slow.next == fast.next) {
                result = true;
                break;
            }
        }
        return result;
    }

    public Node getFirst() {
        return this.first;
    }
}
