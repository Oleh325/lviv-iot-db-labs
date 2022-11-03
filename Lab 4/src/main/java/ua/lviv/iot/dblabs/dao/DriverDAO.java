package ua.lviv.iot.dblabs.dao;

import ua.lviv.iot.dblabs.domain.Driver;

import java.util.List;

public interface DriverDAO extends GeneralDAO<Driver, String> {

    List<Driver> findBySurname(String surname);

}
