package ru.job4j.stream;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    @Test
    public void whenGradeA() {
        School school = new School();
        Student jora = new Student(80);
        Student gelo = new Student(10);
        Student lena = new Student(60);
        Student lina = new Student(30);
        Student tanja = new Student(90);
        Student sveta = new Student(65);
        Student ivan = new Student(32);
        Student gena = new Student(60);
        Student aram = new Student(43);
        Student yura = new Student(33);
        List<Student> list = new ArrayList<>();
        list.add(jora);
        list.add(gelo);
        list.add(lena);
        list.add(lina);
        list.add(tanja);
        list.add(sveta);
        list.add(ivan);
        list.add(gena);
        list.add(aram);
        list.add(yura);
        List<Student> result = school.collect(list, student -> student.getScore() > 70);
        List<Student> expected = new ArrayList<>();
        expected.add(jora);
        expected.add(tanja);
      assertThat(expected, is(result));
    }
    @Test
    public void whenGradeB() {
        School school = new School();
        Student jora = new Student(80);
        Student gelo = new Student(10);
        Student lena = new Student(60);
        Student lina = new Student(30);
        Student tanja = new Student(90);
        Student sveta = new Student(65);
        Student ivan = new Student(32);
        Student gena = new Student(60);
        Student aram = new Student(43);
        Student yura = new Student(33);
        List<Student> list = new ArrayList<>();
        list.add(jora);
        list.add(gelo);
        list.add(lena);
        list.add(lina);
        list.add(tanja);
        list.add(sveta);
        list.add(ivan);
        list.add(gena);
        list.add(aram);
        list.add(yura);
        List<Student> result = school.collect(list, student -> student.getScore() > 50 && student.getScore() < 70);
        List<Student> expected = new ArrayList<>();
        expected.add(lena);
        expected.add(sveta);
        expected.add(gena);
      assertThat(expected, is(result));
    }
    @Test
    public void whenGradeC() {
        School school = new School();
        Student jora = new Student(80);
        Student gelo = new Student(10);
        Student lena = new Student(60);
        Student lina = new Student(30);
        Student tanja = new Student(90);
        Student sveta = new Student(65);
        Student ivan = new Student(32);
        Student gena = new Student(60);
        Student aram = new Student(43);
        Student yura = new Student(33);
        List<Student> list = new ArrayList<>();
        list.add(jora);
        list.add(gelo);
        list.add(lena);
        list.add(lina);
        list.add(tanja);
        list.add(sveta);
        list.add(ivan);
        list.add(gena);
        list.add(aram);
        list.add(yura);
        List<Student> result = school.collect(list, student -> student.getScore() > 0 && student.getScore() < 50);
        List<Student> expected = new ArrayList<>();
        expected.add(gelo);
        expected.add(lina);
        expected.add(ivan);
        expected.add(aram);
        expected.add(yura);
      assertThat(expected, is(result));
    }
}
