package ua.lviv.iot.dblabs.lab5.exception;

public class FineNotFoundException extends RuntimeException {

    public FineNotFoundException(Integer id) {
        super("Could not find 'fine' with id=" + id);
    }

}
