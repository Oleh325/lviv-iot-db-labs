package ua.lviv.iot.dblabs.lab6.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String id) {
        super("Could not find 'transaction' with id=" + id);
    }

}
