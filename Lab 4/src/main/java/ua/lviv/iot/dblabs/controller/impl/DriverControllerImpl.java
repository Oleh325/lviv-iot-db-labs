package ua.lviv.iot.dblabs.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.iot.dblabs.controller.DriverController;
import ua.lviv.iot.dblabs.domain.Driver;
import ua.lviv.iot.dblabs.service.DriverService;

import java.util.List;
import java.util.Optional;

@Component
public class DriverControllerImpl implements DriverController {

    @Autowired
    private DriverService driverService;

    @Override
    public List<Driver> findAll() {
        return driverService.findAll();
    }

    @Override
    public Optional<Driver> findById(String id) {
        return driverService.findById(id);
    }

    @Override
    public int create(Driver driver) {
        return driverService.create(driver);
    }

    @Override
    public int update(String id, Driver driver) {
        return driverService.update(id, driver);
    }

    @Override
    public int delete(String id) {
        return driverService.delete(id);
    }

    @Override
    public List<Driver> findBySurname(String surname) {
        return driverService.findBySurname(surname);
    }
}
