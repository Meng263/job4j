package ru.job4j.tracker;

/**
 * Класс реализует корректный ввод данных, обрабатывая исключения
 */
public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        int result = -1;
        boolean illegal = true;
        do {
            try {
                result = super.ask(question, range);
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
