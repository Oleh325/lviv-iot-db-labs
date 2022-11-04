package ua.lviv.iot.dblabs.lab5.exception;

public class RentNotFoundException extends RuntimeException {

    public RentNotFoundException(Integer id) {
        super("Could not find 'rent' with id=" + id);
    }

}
