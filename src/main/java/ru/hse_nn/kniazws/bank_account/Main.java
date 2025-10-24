package ru.hse_nn.kniazws.bank_account;

import ru.hse_nn.kniazws.bank_account.model.Account;
import ru.hse_nn.kniazws.bank_account.model.Transaction;
import ru.hse_nn.kniazws.bank_account.service.BankService;
import ru.hse_nn.kniazws.bank_account.util.InputUtils;
import java.util.List;

public class Main {
    private static final BankService bankService = new BankService();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        while (true) {
            printMenu();
            String choice = InputUtils.readString("Выберите пункт: ");

            switch (choice) {
                case "1" -> openAccount();
                case "2" -> depositMoney();
                case "3" -> withdrawMoney();
                case "4" -> showBalance();
                case "5" -> showTransactions();
                case "6" -> searchByOwner();
                case "7" -> showAllAccounts();
                case "0" -> {
                    System.out.println("Выход");
                    return;
                }
                default -> System.out.println("Неверный пункт меню");
            }
            System.out.println();
        }
    }

    /**
     * Выводит пункты меню в консоль
     */
    private void printMenu() {
        System.out.println("БАНК");
        System.out.println("1. Открыть счёт");
        System.out.println("2. Положить деньги");
        System.out.println("3. Снять деньги");
        System.out.println("4. Показать баланс");
        System.out.println("5. Список транзакций");
        System.out.println("6. Найти счета по владельцу");
        System.out.println("7. Показать все счета");
        System.out.println("0. Выход");
    }

    /**
     * Создаёт новый счёт
     */
    private void openAccount() {
        String name = InputUtils.readString("Имя владельца: ");
        try {
            Account acc = bankService.openAccount(name);
            System.out.println("Счёт создан: " + acc);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Вносит деньги на счёт по ID
     */
    private void depositMoney() {
        int id = (int) InputUtils.readDouble("ID счёта: ");
        double amount = InputUtils.readDouble("Сумма пополнения: ");
        try {
            Account acc = bankService.findById(id);
            if (acc == null) throw new IllegalArgumentException("Счёт не найден");
            acc.deposit(amount);
            System.out.println("Баланс обновлён: " + acc.getBalance());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Снимает деньги со счёта по ID
     */
    private void withdrawMoney() {
        int id = (int) InputUtils.readDouble("ID счёта: ");
        double amount = InputUtils.readDouble("Сумма снятия: ");
        try {
            Account acc = bankService.findById(id);
            if (acc == null) throw new IllegalArgumentException("Счёт не найден");
            acc.withdraw(amount);
            System.out.println("Снятие успешно. Баланс: " + acc.getBalance());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Показывает баланс выбранного счёта
     */
    private void showBalance() {
        int id = (int) InputUtils.readDouble("ID счёта: ");
        Account acc = bankService.findById(id);
        if (acc != null)
            System.out.println("Баланс: " + acc.getBalance());
        else
            System.out.println("Счёт не найден");
    }

    /**
     * Выводит список транзакций по счёту
     */
    private void showTransactions() {
        int id = (int) InputUtils.readDouble("ID счёта: ");
        try {
            List<Transaction> txs = bankService.getTransactions(id);
            if (txs.isEmpty()) System.out.println("Нет транзакций");
            else txs.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Находит и показывает все счета владельца
     */
    private void searchByOwner() {
        String name = InputUtils.readString("Имя владельца: ");
        List<Account> list = bankService.findByOwner(name);
        if (list.isEmpty()) System.out.println("Счета не найдены");
        else list.forEach(System.out::println);
    }

    /**
     * Показывает все счета в банке
     */
    private void showAllAccounts() {
        List<Account> list = bankService.getAccounts();
        if (list.isEmpty()) System.out.println("Счетов пока нет");
        else list.forEach(System.out::println);
    }
}
