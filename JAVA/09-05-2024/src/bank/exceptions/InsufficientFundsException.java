package bank.exceptions;

/**
 * Исключение, выбрасываемое при недостаточном количестве средств в банкомате.
 */
public class InsufficientFundsException extends ATMException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

