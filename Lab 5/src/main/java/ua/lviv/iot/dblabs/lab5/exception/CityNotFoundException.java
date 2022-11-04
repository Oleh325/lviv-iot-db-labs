package ua.lviv.iot.dblabs.lab5.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(Integer id) {
        super("Could not find 'city' with id=" + id);
    }

}
