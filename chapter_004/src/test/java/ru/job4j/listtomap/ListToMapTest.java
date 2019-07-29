package ru.job4j.listtomap;

import org.junit.Test;
import ru.job4j.stream.Student;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListToMapTest {
    @Test
    public void whenGradeA() {
        Student jora = new Student(80);
        Student gelo = new Student(10);
        Student lena = new Student(60);
        Student lina = new Student(30);
        Student tanja = new Student(90);
        Student sveta = new Student(65);
        Student ivan = new Student(32);
        Student gena = new Student(61);
        Student aram = new Student(43);
        Student yura = new Student(33);
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
        Map<Integer, Student> result = ltm.convert(list);
        Map<Integer, Student> expected = Map.of(
                jora.getScore(), jora,
                gelo.getScore(), gelo,
                lena.getScore(), lena,
                lina.getScore(), lina,
                tanja.getScore(), tanja,
                sveta.getScore(), sveta,
                ivan.getScore(), ivan,
                gena.getScore(), gena,
                aram.getScore(), aram,
                yura.getScore(), yura
                );
        assertThat(expected, is(result));
    }
}
