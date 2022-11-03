package ua.lviv.iot.dblabs.service;

import ua.lviv.iot.dblabs.domain.Driver;

import java.util.List;

public interface DriverService extends GeneralService<Driver, String> {

    List<Driver> findBySurname(String surname);

}
