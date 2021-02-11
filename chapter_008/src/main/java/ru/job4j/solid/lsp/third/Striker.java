package ru.job4j.solid.lsp.third;

import java.lang.reflect.InaccessibleObjectException;

public class Striker extends Worker {
    @Override
    public void work() {
        throw new InaccessibleObjectException("There is a strike, I don't work!");
    }
}
