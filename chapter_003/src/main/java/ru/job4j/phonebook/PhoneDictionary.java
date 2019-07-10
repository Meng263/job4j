package ru.job4j.phonebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Телефоный справочник
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Метод возвращает список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) throws ItemNotFoundException {
        List<Person> result = new ArrayList<>();
        for (Person human : persons) {
            if (human.getName().contains(key)
                    || human.getSurname().contains(key)
                    || human.getAddress().contains(key)
                    || human.getPhone().contains(key)) {
                result.add(human);
            }
            if (result.isEmpty()) {
                throw new ItemNotFoundException("Запись не найдена!");
            }
        }
        return result;
    }
}
