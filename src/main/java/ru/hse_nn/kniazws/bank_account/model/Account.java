package ru.hse_nn.kniazws.bank_account.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий банковский счёт
 * хранит информацию о владельце, балансе и операциях
 */
public class Account {
    private final int id;
    private final String ownerName;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Создаёт новый счёт с указанным владельцем и id
     * @param id уникальный номер счёта
     * @param ownerName имя владельца
     */
    public Account(int id, String ownerName) {
        this.id = id;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Возвращает список всех транзакций по счёту.
     * @return список транзакций
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Пополняет счёт на указанную сумму.
     * @param amount сумма для внесения
     * @throws IllegalArgumentException если сумма меньше или равна нулю
     */
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Сумма должна быть положительной");
        balance += amount;
        transactions.add(new Transaction("Пополнение", amount));
    }

    /**
     * Снимает указанную сумму со счёта.
     * @param amount сумма для снятия
     * @throws IllegalArgumentException если сумма некорректна или превышает баланс
     */
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Сумма должна быть положительной");
        if (amount > balance) throw new IllegalArgumentException("Недостаточно средств");
        balance -= amount;
        transactions.add(new Transaction("Снятие", -amount));
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Владелец: %s | Баланс: %.2f", id, ownerName, balance);
    }
}
