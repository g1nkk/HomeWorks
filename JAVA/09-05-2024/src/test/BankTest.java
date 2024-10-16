package test;

import bank.ATM;
import bank.Bank;
import bank.exceptions.*;
import bank.enums.*;

import java.util.Map;

public class BankTest {
    public static void main(String[] args) {
        Bank bank = new Bank();
        try {
            // Инициализация сети банкоматов: 3 банкомата, по 10 банкнот каждого номинала, мин сумма 10 грн, макс банкнот за транзакцию 20
            bank.initializeATMs(3, 10, 10, 20);
            System.out.println("Сеть банкоматов успешно инициализирована.\n");

            // Отображение статуса сети
            bank.displayNetworkStatus();

            // Пример снятия средств из первого банкомата
            ATM firstATM = bank.getAtmNetwork().get(0);
            System.out.println("\nПопытка снять 280 грн из первого банкомата:");
            Map<Denomination, Integer> dispensed = firstATM.withdrawMoney(280);
            System.out.println("Выдано банкнот:");
            for (Map.Entry<Denomination, Integer> entry : dispensed.entrySet()) {
                System.out.println(entry.getKey().getValue() + " грн: " + entry.getValue());
            }

            // Отображение статуса после снятия
            System.out.println("\nСтатус первого банкомата после снятия:");
            firstATM.displayStatus();

            // Пример попытки снять сумму, превышающую доступные средства
            System.out.println("\nПопытка снять 10000 грн из первого банкомата:");
            firstATM.withdrawMoney(10000);

        } catch (ATMException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        try {
            // Пример загрузки банкнот в первый банкомат
            ATM firstATM = bank.getAtmNetwork().get(0);
            System.out.println("\nЗагрузка 5 банкнот номиналом 500 грн в первый банкомат.");
            firstATM.loadMoney(Denomination.FIVE_HUNDRED, 5);
            firstATM.displayStatus();
        } catch (ATMException e) {
            System.err.println("Ошибка при загрузке банкнот: " + e.getMessage());
        }

        try {
            // Пример попытки снять некорректную сумму
            ATM secondATM = bank.getAtmNetwork().get(1);
            System.out.println("\nПопытка снять 7 грн из второго банкомата (некорректная сумма):");
            secondATM.withdrawMoney(7);
        } catch (ATMException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Вывод общей суммы денег в сети банкоматов
        System.out.println("\nОбщая сумма денег в сети банкоматов: " + bank.getTotalAmountInNetwork() + " грн");
    }
}
