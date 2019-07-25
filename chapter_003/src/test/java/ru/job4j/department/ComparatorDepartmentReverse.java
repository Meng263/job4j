package ru.job4j.department;

import java.util.Comparator;

public class ComparatorDepartmentReverse implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = o1.compareTo(o2);
        if (result != 0) {
            result = -result;
        }
        if (o1.indexOf(o2) == 0) {
            result = 1;
        }
        if (o2.indexOf(o1) == 0) {
            result = -1;
        }
        return result;
    }
}
