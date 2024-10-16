package bank.exceptions;

/**
 * Исключение, выбрасываемое при вводе некорректной суммы.
 */
public class InvalidAmountException extends ATMException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
