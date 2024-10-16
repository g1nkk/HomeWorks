package bank.exceptions;

public class WithdrawalException extends ATMException {
    public WithdrawalException(String message) {
        super(message);
    }
}
