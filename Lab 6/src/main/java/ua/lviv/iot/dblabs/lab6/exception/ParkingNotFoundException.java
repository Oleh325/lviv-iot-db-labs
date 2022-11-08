package ua.lviv.iot.dblabs.lab6.exception;

public class ParkingNotFoundException extends RuntimeException {

    public ParkingNotFoundException(Integer id) {
        super("Could not find 'parking' with id=" + id);
    }

}
