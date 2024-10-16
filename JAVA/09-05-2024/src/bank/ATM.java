package bank;

import bank.exceptions.*;
import bank.enums.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * Класс, представляющий банкомат.
 */
public class ATM {
    private Map<Denomination, Integer> banknotes;
    private final int minWithdrawalAmount;
    private final int maxBanknotesPerTransaction;

    /**
     * Конструктор банкомата.
     *
     * @param minWithdrawalAmount     Минимальная сумма для снятия.
     * @param maxBanknotesPerTransaction Максимальное количество банкнот за одну транзакцию.
     * @throws ATMInitializationException Если инициализация банкнот невозможна.
     */
    public ATM(int minWithdrawalAmount, int maxBanknotesPerTransaction) throws ATMInitializationException {
        this.minWithdrawalAmount = minWithdrawalAmount;
        this.maxBanknotesPerTransaction = maxBanknotesPerTransaction;
        this.banknotes = new EnumMap<>(Denomination.class);
        // Инициализация с нулем банкнот каждого номинала
        for (Denomination denom : Denomination.values()) {
            banknotes.put(denom, 0);
        }
    }

    /**
     * Метод загрузки банкнот в банкомат.
     *
     * @param denomination Номинал банкноты.
     * @param count        Количество банкнот.
     * @throws ATMInitializationException Если количество банкнот отрицательно.
     */
    public void loadMoney(Denomination denomination, int count) throws ATMInitializationException {
        if (count < 0) {
            throw new ATMInitializationException("Количество банкнот не может быть отрицательным.");
        }
        banknotes.put(denomination, banknotes.get(denomination) + count);
    }

    /**
     * Метод ручного ввода суммы через купюроприемник.
     *
     * @param denomination Номинал банкноты.
     * @param count        Количество банкнот.
     * @throws ATMInitializationException Если ввод некорректен.
     */
    public void depositMoney(Denomination denomination, int count) throws ATMInitializationException {
        if (count <= 0) {
            throw new ATMInitializationException("Количество банкнот должно быть положительным.");
        }
        banknotes.put(denomination, banknotes.get(denomination) + count);
    }

    /**
     * Метод снятия денег из банкомата.
     *
     * @param amount Сумма для снятия.
     * @return Map, содержащий количество банкнот каждого номинала, которые будут выданы.
     * @throws ATMException Если сумма некорректна или недостаточно средств.
     */
    public Map<Denomination, Integer> withdrawMoney(int amount) throws ATMException {
        if (amount < minWithdrawalAmount) {
            throw new InvalidAmountException("Сумма меньше минимальной для снятия: " + minWithdrawalAmount);
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Сумма должна быть положительной.");
        }

        // Проверка доступных средств
        if (getTotalAmount() < amount) {
            throw new InsufficientFundsException("Недостаточно средств в банкомате.");
        }

        // Попытка составить сумму минимальным количеством банкнот
        Map<Denomination, Integer> toDispense = new EnumMap<>(Denomination.class);
        int remainingAmount = amount;
        int totalBanknotesToDispense = 0;

        // Сортировка номиналов по убыванию
        Denomination[] sortedDenominations = Denomination.values();
        java.util.Arrays.sort(sortedDenominations, (d1, d2) -> d2.getValue() - d1.getValue());

        for (Denomination denom : sortedDenominations) {
            int denomValue = denom.getValue();
            int availableBanknotes = banknotes.get(denom);
            int requiredBanknotes = remainingAmount / denomValue;

            if (requiredBanknotes > 0) {
                int banknotesToUse = Math.min(requiredBanknotes, availableBanknotes);
                if (banknotesToUse > 0) {
                    toDispense.put(denom, banknotesToUse);
                    remainingAmount -= denomValue * banknotesToUse;
                    totalBanknotesToDispense += banknotesToUse;
                }
            }
        }

        if (remainingAmount != 0) {
            throw new WithdrawalException("Невозможно выдать запрашиваемую сумму с текущим набором банкнот.");
        }

        if (totalBanknotesToDispense > maxBanknotesPerTransaction) {
            throw new WithdrawalException("Превышено максимальное количество банкнот за одну транзакцию: " + maxBanknotesPerTransaction);
        }

        // Вычитание выданных банкнот из банка банкомата
        for (Map.Entry<Denomination, Integer> entry : toDispense.entrySet()) {
            Denomination denom = entry.getKey();
            int count = entry.getValue();
            banknotes.put(denom, banknotes.get(denom) - count);
        }

        return toDispense;
    }

    /**
     * Метод для получения общей суммы денег в банкомате.
     *
     * @return Общая сумма денег.
     */
    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Denomination, Integer> entry : banknotes.entrySet()) {
            total += entry.getKey().getValue() * entry.getValue();
        }
        return total;
    }

    /**
     * Метод отображения текущего состояния банкомата.
     */
    public void displayStatus() {
        System.out.println("Текущие банкноты в банкомате:");
        for (Denomination denom : Denomination.values()) {
            System.out.println(denom.getValue() + " грн: " + banknotes.get(denom));
        }
        System.out.println("Общая сумма: " + getTotalAmount() + " грн");
    }
}
