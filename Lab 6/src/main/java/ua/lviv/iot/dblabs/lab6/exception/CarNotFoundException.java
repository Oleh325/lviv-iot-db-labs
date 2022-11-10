package ua.lviv.iot.dblabs.lab6.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Integer id) {
        super("Could not find 'car' with id=" + id);
    }

}
