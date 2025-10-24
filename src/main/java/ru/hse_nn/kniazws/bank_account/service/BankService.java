package ru.hse_nn.kniazws.bank_account.service;

import ru.hse_nn.kniazws.bank_account.model.Account;
import ru.hse_nn.kniazws.bank_account.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для управления банковскими счетами
 * позволяет создавать счета, искать их и получать информацию
 */
public class BankService {
    private final List<Account> accounts = new ArrayList<>();
    private int nextId = 1;

    /**
     * Открывает новый счёт для указанного владельца
     * @param ownerName имя владельца
     * @return созданный счёт
     * @throws IllegalArgumentException если имя пустое
     */
    public Account openAccount(String ownerName) {
        if (ownerName == null || ownerName.isBlank())
            throw new IllegalArgumentException("Имя владельца не может быть пустым");

        Account acc = new Account(nextId++, ownerName);
        accounts.add(acc);
        return acc;
    }

    /**
     * Ищет счёт по его id
     * @param id номер счёта
     * @return найденный счёт или null, если не найден
     */
    public Account findById(int id) {
        for (Account acc : accounts) {
            if (acc.getId() == id) return acc;
        }
        return null;
    }

    /**
     * Возвращает все счета указанного владельца.
     * @param ownerName имя владельца
     * @return список счетов
     */
    public List<Account> findByOwner(String ownerName) {
        List<Account> result = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getOwnerName().equalsIgnoreCase(ownerName)) {
                result.add(acc);
            }
        }
        return result;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Возвращает все транзакции по счёту.
     * @param id номер счёта
     * @return список транзакций
     * @throws IllegalArgumentException если счёт не найден
     */
    public List<Transaction> getTransactions(int id) {
        Account acc = findById(id);
        if (acc == null) throw new IllegalArgumentException("Счёт не найден");
        return acc.getTransactions();
    }
}
