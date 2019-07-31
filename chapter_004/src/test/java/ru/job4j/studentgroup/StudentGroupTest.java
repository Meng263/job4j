package ru.job4j.studentgroup;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

public class StudentGroupTest {
    @Test
    public void whenDeleteNullAndScopeGrateThanBound() {
        Student jora = new Student("Jora", 80);
        Student gelo = new Student("Gelo", 10);
        Student lena = new Student("Lena", 60);
        Student lina = new Student("Lina", 30);
        Student tanja = new Student("Tanja", 85);
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
        List<Student> expected = List.of(
                tanja,
                jora
        );
        StudentGroup sg = new StudentGroup();
        assertThat(expected, is(sg.levelOf(list, 70)));
    }
}
