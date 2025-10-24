package ru.hse_nn.kniazws.bank_account.util;

import java.util.Scanner;

/**
 * Вспомогательный класс для чтения данных из консоли
 */
public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Считывает строку из консоли
     * @param message подсказка для пользователя
     * @return введённая строка
     */
    public static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    /**
     * Считывает число типа double из консоли
     * @param message подсказка для пользователя
     * @return введённое число
     */
    public static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число");
            }
        }
    }
}
