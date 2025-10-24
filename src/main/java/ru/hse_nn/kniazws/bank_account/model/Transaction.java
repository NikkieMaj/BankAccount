package ru.hse_nn.kniazws.bank_account.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий одну банковскую операцию
 */
public class Transaction {
    private final String type;
    private final double amount;
    private final LocalDateTime timestamp;

    /**
     * Создаёт запись о транзакции
     * @param type тип операции (например, пополнение, снятие)
     * @param amount сумма операции
     */
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: %.2f", timestamp.format(fmt), type, amount);
    }
}
