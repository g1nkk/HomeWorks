package bank.exceptions;

/**
 * Базовый класс для всех исключений, связанных с банкоматами.
 */
public class ATMException extends Exception {
    public ATMException(String message) {
        super(message);
    }
}
