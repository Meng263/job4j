package ru.job4j.listtomap;

public class Student {
    private int score;
    private String name;

    public Student(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
