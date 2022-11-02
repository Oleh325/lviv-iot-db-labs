package ua.lviv.iot.dblabs.controller;

import ua.lviv.iot.dblabs.domain.Driver;
import ua.lviv.iot.dblabs.service.GeneralService;

import java.util.List;

public interface DriverController extends GeneralService<Driver, String> {

    List<Driver> findBySurname(String surname);

}
