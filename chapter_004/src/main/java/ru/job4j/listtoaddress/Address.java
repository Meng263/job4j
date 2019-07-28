package ru.job4j.listtoaddress;

public class Address {
    private String city, street;
    private int home, apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }
}
