package ru.job4j.listtomap;

import org.junit.Test;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListToMapTest {
    @Test
    public void whenGradeA() {
        Student jora = new Student("Jora", 80);
        Student gelo = new Student("Gelo", 10);
        Student lena = new Student("Lena", 60);
        Student lina = new Student("Lina", 30);
        Student tanja = new Student("Tanja", 90);
        Student sveta = new Student("Sveta", 65);
        Student ivan = new Student("Ivan", 32);
        Student gena = new Student("Gena", 61);
        Student aram = new Student("Aram", 43);
        Student yura = new Student("Yura", 33);
        List<Student> list = List.of(
                jora,
                gelo,
                lena,
                lina,
                tanja,
                sveta,
                ivan,
                gena,
                aram,
                yura
        );
        ListToMap ltm = new ListToMap();
        Map<String, Student> result = ltm.convert(list);
        Map<String, Student> expected = Map.of(
                jora.getName(), jora,
                gelo.getName(), gelo,
                lena.getName(), lena,
                lina.getName(), lina,
                tanja.getName(), tanja,
                sveta.getName(), sveta,
                ivan.getName(), ivan,
                gena.getName(), gena,
                aram.getName(), aram,
                yura.getName(), yura
                );
        assertThat(expected, is(result));
    }
}
