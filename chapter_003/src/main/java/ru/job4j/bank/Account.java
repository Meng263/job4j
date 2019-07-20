package ru.job4j.bank;

import java.util.Objects;

/**
 * Аккаунт пользователя, имеет поля реквизиты и остаток на счете
 */
public class Account {
    private String requisites;
    private Double value;

    public Account(String requisites, Double value) {
        this.requisites = requisites;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(value, account.value)
                && Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, requisites);
    }
}
