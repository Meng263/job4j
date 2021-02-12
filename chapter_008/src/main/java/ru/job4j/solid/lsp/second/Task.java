package ru.job4j.solid.lsp.second;

public class Task {
    private Status status = Status.STARTED;

    public void close() {
        status = Status.CLOSED;
    }

    public Status getStatus() {
        return status;
    }
}
