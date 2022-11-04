package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.Driver;

import java.util.List;

public interface DriverService extends GeneralService<Driver, String> {

    List<Driver> findBySurname(String surname);

}
