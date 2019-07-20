package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    @Test
    public void whenAddUser() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        bank.addUser(jora);
        assertThat(bank.toString(), is(new StringBuilder()
                .append("Jora")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()));
    }

    @Test
    public void whenDeleteUser() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        User tanya = new User("Tanya", "462121");
        User goro = new User("Goro", "462133");
        bank.addUser(jora);
        bank.addUser(tanya);
        bank.addUser(goro);
        bank.deleteUser(goro);
        assertThat(bank.toString(), is(new StringBuilder()
                .append("Jora")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Tanya")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()));
    }

    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        bank.addUser(jora);
        bank.addAccountToUser("462154", new Account("122345", 500D));
        assertThat(bank.toString(), is(new StringBuilder()
                .append("Jora")
                .append(System.lineSeparator())
                .append("122345")
                .append(" ")
                .append(System.lineSeparator())
                .toString()));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        bank.addUser(jora);
        Account first = new Account("122345", 500D);
        bank.addAccountToUser("462154", first);
        bank.deleteAccountFromUser("462154", first);
        assertThat(bank.toString(), is(new StringBuilder()
                .append("Jora")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()));
    }

    @Test
    public void whenGetUserAccount() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        bank.addUser(jora);
        Account first = new Account("122345", 500D);
        Account second = new Account("122346", 200D);
        bank.addAccountToUser("462154", first);
        bank.addAccountToUser("462154", second);
        List<Account> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        assertThat(list, is(bank.getUserAccounts("462154")));
    }

    @Test
    public void whenTransferMoneyFromTheUserToTheUser() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        bank.addUser(jora);
        Account first = new Account("122345", 500D);
        Account second = new Account("122346", 200D);
        bank.addAccountToUser("462154", first);
        bank.addAccountToUser("462154", second);
        Boolean resultTransfer = bank.transferMoney("462154", "122345", "462154", "122346", 100D);
        assertThat(resultTransfer, is(true));
        assertThat(bank.getUserAccounts("462154").get(0).getValue(), is(400D));
        assertThat(bank.getUserAccounts("462154").get(1).getValue(), is(300D));
    }

    @Test
    public void whenTransferMoneyFromTheUserToTheUserNotEnoughMoney() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        bank.addUser(jora);
        Account first = new Account("122345", 500D);
        Account second = new Account("122346", 200D);
        bank.addAccountToUser("462154", first);
        bank.addAccountToUser("462154", second);
        Boolean resultTransfer = bank.transferMoney("462154", "122345", "462154", "122346", 1000D);
        assertThat(resultTransfer, is(false));
        assertThat(bank.getUserAccounts("462154").get(0).getValue(), is(500D));
        assertThat(bank.getUserAccounts("462154").get(1).getValue(), is(200D));
    }

    @Test
    public void whenTransferMoneyFromUserToAnotherUser() {
        Bank bank = new Bank();
        User jora = new User("Jora", "462154");
        User tanya = new User("Tanya", "462121");
        bank.addUser(jora);
        bank.addUser(tanya);
        Account first = new Account("122345", 500D);
        Account second = new Account("122346", 200D);
        Account third = new Account("122347", 300D);
        bank.addAccountToUser("462154", first);
        bank.addAccountToUser("462154", second);
        bank.addAccountToUser("462121", third);
        Boolean resultTransfer = bank.transferMoney("462154", "122345", "462121", "122347", 100D);
        assertThat(resultTransfer, is(true));
        assertThat(bank.getUserAccounts("462154").get(0).getValue(), is(400D));
        assertThat(bank.getUserAccounts("462121").get(0).getValue(), is(400D));
    }

}

