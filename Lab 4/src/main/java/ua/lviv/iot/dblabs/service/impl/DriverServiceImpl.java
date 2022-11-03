package ua.lviv.iot.dblabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.dao.DriverDAO;
import ua.lviv.iot.dblabs.domain.Driver;
import ua.lviv.iot.dblabs.service.DriverService;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverDAO driverDAO;

    @Override
    public List<Driver> findAll() {
        return driverDAO.findAll();
    }

    @Override
    public Optional<Driver> findById(String id) {
        return driverDAO.findById(id);
    }

    @Override
    public int create(Driver driver) {
        return driverDAO.create(driver);
    }

    @Override
    public int update(String id, Driver driver) {
        return driverDAO.update(id, driver);
    }

    @Override
    public int delete(String id) {
        return driverDAO.delete(id);
    }

    @Override
    public List<Driver> findBySurname(String surname) {
        return driverDAO.findBySurname(surname);
    }
}
