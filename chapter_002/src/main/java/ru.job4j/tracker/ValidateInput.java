package ru.job4j.tracker;

/**
 * Класс реализует корректный ввод данных, обрабатывая исключения
 */
public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }
    @Override
    public int ask(String question, int[] range) {
        int result = -1;
        boolean illegal = true;
        do {
            try {
                result = this.input.ask(question, range);
                illegal = false;
            } catch (MenuOutException moe) {
                System.out.println("Out of range");
                System.out.println("Please, try again");
            } catch (NumberFormatException nfe) {
                System.out.println("Illegal input");
                System.out.println("Please, try again");
            }
        } while (illegal);
        return result;
    }
}
