package bank;

import bank.exceptions.ATMException;
import bank.enums.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий банк, который управляет сетью банкоматов.
 */
public class Bank {
    private List<ATM> atmNetwork;

    /**
     * Конструктор банка.
     */
    public Bank() {
        this.atmNetwork = new ArrayList<>();
    }

    /**
     * Метод инициализации сети банкоматов.
     *
     * @param numberOfATMs               Количество банкоматов в сети.
     * @param initialBanknotesPerATM     Количество банкнот каждого номинала для каждого банкомата.
     * @param minWithdrawalAmount        Минимальная сумма для снятия в каждом банкомате.
     * @param maxBanknotesPerTransaction Максимальное количество банкнот за одну транзакцию в каждом банкомате.
     * @throws ATMException Если инициализация банкоматов невозможна.
     */
    public void initializeATMs(int numberOfATMs, int initialBanknotesPerATM, int minWithdrawalAmount, int maxBanknotesPerTransaction) throws ATMException {
        for (int i = 0; i < numberOfATMs; i++) {
            ATM atm = new ATM(minWithdrawalAmount, maxBanknotesPerTransaction);
            // Загрузка начальных банкнот в банкомат
            for (Denomination denom : Denomination.values()) {
                atm.loadMoney(denom, initialBanknotesPerATM);
            }
            atmNetwork.add(atm);
        }
    }

    /**
     * Метод для получения общей суммы денег во всех банкоматах сети.
     *
     * @return Общая сумма денег.
     */
    public int getTotalAmountInNetwork() {
        int total = 0;
        for (ATM atm : atmNetwork) {
            total += atm.getTotalAmount();
        }
        return total;
    }

    /**
     * Метод для отображения статуса всех банкоматов в сети.
     */
    public void displayNetworkStatus() {
        int atmNumber = 1;
        for (ATM atm : atmNetwork) {
            System.out.println("Банкомат #" + atmNumber++);
            atm.displayStatus();
            System.out.println("---------------------------");
        }
    }

    /**
     * Метод получения списка банкоматов.
     *
     * @return Список банкоматов.
     */
    public List<ATM> getAtmNetwork() {
        return atmNetwork;
    }
}
