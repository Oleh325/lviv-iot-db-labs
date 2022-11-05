package ua.lviv.iot.dblabs.lab5.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String id) {
        super("Could not find 'transaction' with id=" + id);
    }

}
