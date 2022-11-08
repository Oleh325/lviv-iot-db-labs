package ua.lviv.iot.dblabs.lab6.service;

import ua.lviv.iot.dblabs.lab6.domain.Driver;
import ua.lviv.iot.dblabs.lab6.domain.Fine;

import java.util.List;

public interface DriverService extends GeneralService<Driver, String> {

    List<Driver> findBySurname(String surname);

    List<Fine> findAllFinesForDriver(String driverLicenseNumber);

}
