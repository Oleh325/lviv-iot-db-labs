package ua.lviv.iot.dblabs.lab5.exception;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(String licenseNumber) {
        super("Could not find 'driver' with license_number=" + licenseNumber);
    }

}
