package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {
    @Test
  public void whenNotOverrideEqualAndHashCode() {
        Calendar birthday = new GregorianCalendar(1989, 9, 22);
        User joraOne = new User("Jora", 2, birthday);
        User joraTwo = new User("Jora", 2, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(joraOne, new Object());
        map.put(joraTwo, new Object());
       for (User elem : map.keySet()) {
           System.out.println(elem);
       }
        System.out.println(joraOne.hashCode());
        System.out.println(joraTwo.hashCode());
    }

}