package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс, реализует Map пользователей, имеющих аккаунты и имеет метод перевод денег с аккаунта на аккаунт
 */
public class Bank {
    private Map<User, List<Account>> map = new TreeMap<>();

    /**
     * Метод добавляет пользователя в Map, если такого пользователя еще нет
     *
     * @param user пользователь
     */
    public void addUser(User user) {
        map.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод удаляет пользователя из Map
     *
     * @param user пользователь
     */
    public void deleteUser(User user) {
        map.remove(user);
    }

    /**
     * Метод ищет пользователя по полю паспорт
     *
     * @param passport паспорт
     * @return пользователь, если не найден, возвращает Null
     */
    private User findUserByPassport(String passport) {
        Optional<User> optionalUser = map.keySet().stream().filter(elem -> elem.getPassport().equals(passport)).findFirst();
        User result = null;
        if (optionalUser.isPresent()) {
            result = optionalUser.get();
        }
        return result;
    }

    /**
     * Метод добавляет аккаунт пользователю, если у него еще нет аккаунта с такими же реквизитами
     *
     * @param passport паспорт
     * @param account  аккаунт
     */
    public void addAccountToUser(String passport, Account account) {
        User user = findUserByPassport(passport);
        Account acc = findAccountByRequisite(account.getRequisites());
        if ((user != null) && (acc == null)) {
            map.get(findUserByPassport(passport)).add(account);
        }
    }


    /**
     * Метод удаляет аккаунт у пользователя
     *
     * @param passport пасспорт
     * @param account  аккаунт
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = findUserByPassport(passport);
        if (user != null) {
            map.get(user).remove(account);
        }
    }

    /**
     * Метод возвращает список аккаунтов пользователя, пустой список, если пользователь не найден
     *
     * @param passport паспорт
     * @return список аккаунтов
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        User user = findUserByPassport(passport);
        if (user != null) {
            result = map.get(findUserByPassport(passport));
        }
        return result;
    }

    /**
     * Метод ищет аккаунт по реквизитам, если не находит, то возвращает Null
     *
     * @param reqisite реквизиты
     * @return аккаунт
     */
    private Account findAccountByRequisite(String reqisite) {
        Account result = null;
        Optional<Account> optionalAccount = map.values().stream().flatMap(Collection::stream)
                .filter(account -> account.getRequisites().equals(reqisite)).findFirst();
        if (optionalAccount.isPresent()) {
            result = optionalAccount.get();
        }
        return result;
    }

    /**
     * Метод осущетсвляет перевод средств с аккаунта на аккаунт
     *
     * @param srcPassport  пасспорт пользователя, с которого списываются средства
     * @param srcRequisite реквизиты, с которого аккаунта происходит перевод
     * @param destPassport пасспорт получателя
     * @param dstRequisite реквизиты получателя
     * @param amount       сумма
     * @return true, если перевод успешен, false если не найден пользователь и/или аккаунт
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite,
                                 double amount) {
        boolean result = false;
        Account senderAcc = findAccountByRequisiteAndPassport(srcPassport, srcRequisite);
        Account receiverAcc = findAccountByRequisiteAndPassport(destPassport, dstRequisite);
        if ((senderAcc != null) && (receiverAcc != null) && (senderAcc.getValue() >= amount)) {
            senderAcc.setValue(senderAcc.getValue() - amount);
            receiverAcc.setValue(receiverAcc.getValue() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Метод ищет аккаунт по паспорту и реквизитам.
     * Если аккаунт не найден, не найден пользователь,
     * либо аккаунт не пренадлежит пользователю возвращает null
     *
     * @param passport  пасспорт
     * @param requisite реквизиты
     * @return аккаунт
     */
    public Account findAccountByRequisiteAndPassport(String passport, String requisite) {
        final Account account = findAccountByRequisite(requisite);
        Account result = account;
        List<Account> list = getUserAccounts(passport);
        if ((account != null) && list.stream().noneMatch(acc -> acc.equals(account))) {
            result = null;
        }
        return result;
}

    /**
     * Переопределенный метод toString, выводит имя пользователя, затем с новой строки на одной строке его аккаунты
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (User user : map.keySet()) {
            str
                    .append(user.getName())
                    .append(System.lineSeparator());
            for (Account acc : map.get(user)) {
                str
                        .append(acc.getRequisites())
                        .append(" ");
            }
            str.append(System.lineSeparator());
        }
        return str.toString();
    }
}
