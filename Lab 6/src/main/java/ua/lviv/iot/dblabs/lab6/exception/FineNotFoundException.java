package ua.lviv.iot.dblabs.lab6.exception;

public class FineNotFoundException extends RuntimeException {

    public FineNotFoundException(Integer id) {
        super("Could not find 'fine' with id=" + id);
    }

}
