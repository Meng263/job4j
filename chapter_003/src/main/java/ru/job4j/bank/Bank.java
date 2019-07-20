package ru.job4j.bank;

import java.util.*;

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
     * @return пользователь, если не найден, кидает исключение UserNotFoundException
     */
    private User findUserByPassport(String passport) throws UserNotFoundException {
        User result = null;
        for (User user : map.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
            }
        }
        if (result == null) {
            throw new UserNotFoundException("User not found!");
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
        try {
            findUserByPassport(passport);
            findAccountByRequisite(account.getRequisites());
        } catch (UserNotFoundException unfe) {
            unfe.printStackTrace();
        } catch (AccountNotFoundException anfe) {
            User user = findUserByPassport(passport);
            map.get(user).add(account);
        }
    }

    /**
     * Метод удаляет аккаунт у пользователя
     *
     * @param passport пасспорт
     * @param account  аккаунт
     */
    public void deleteAccountFromUser(String passport, Account account) {
        try {
            User user = findUserByPassport(passport);
            map.get(user).remove(account);
        } catch (UserNotFoundException unfe) {
            unfe.printStackTrace();
        }
    }

    /**
     * Метод возвращает список аккаунтов пользователя
     *
     * @param passport паспорт
     * @return список аккаунтов
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = null;
        try {
            result = map.get(findUserByPassport(passport));
        } catch (UserNotFoundException unfe) {
            unfe.printStackTrace();
        }
        return result;
    }

    /**
     * Метод ищет аккаунт по реквизитам, если не находит, то кидате исключение AccountNotFoundException
     *
     * @param reqisite реквизиты
     * @return аккаунт
     */
    private Account findAccountByRequisite(String reqisite) throws AccountNotFoundException {
        Account result = null;
        for (User user : map.keySet()) {
            for (Account account : map.get(user)) {
                if (account.getRequisites().equals(reqisite)) {
                    result = account;
                    break;
                }
            }
        }
        if (result == null) {
            throw new AccountNotFoundException("Account not found!");
        }
        return result;
    }

    /**
     * Метод осущетсвляет перевод средств с аккаунта на аккаунт, если не найден пользователь или аккаунт, то кидает исключение
     *
     * @param srcPassport  пасспорт пользователя, с которого списываются средства
     * @param srcRequisite реквизиты, с которого аккаунта происходит перевод
     * @param destPassport пасспорт получателя
     * @param dstRequisite реквизиты получателя
     * @param amount       сумма
     * @return true, если перевод успешен, false если не найден пользователь и/или аккаунт
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite,
                                 double amount) throws AccountNotFoundException, UserNotFoundException {
        boolean result = false;
        User sender = null, resiver = null;
        Account senderAcc = null, resiverAcc = null;
        try {
            sender = findUserByPassport(srcPassport);
            resiver = findUserByPassport(destPassport);
            senderAcc = findAccountByRequisite(srcRequisite);
            resiverAcc = findAccountByRequisite(dstRequisite);
        } catch (AccountNotFoundException | UserNotFoundException nfe) {
            nfe.printStackTrace();
            return false;
        }
        if (senderAcc.getValue() >= amount) {
            senderAcc.setValue(senderAcc.getValue() - amount);
            resiverAcc.setValue(resiverAcc.getValue() + amount);
            result = true;
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
