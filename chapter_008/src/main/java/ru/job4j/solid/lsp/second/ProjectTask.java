package ru.job4j.solid.lsp.second;

public class ProjectTask extends Task {
    @Override
    public void close() {
        if (getStatus() == Status.STARTED) throw new IllegalStateException("Cannot close a started Project Task!");
    }
}
