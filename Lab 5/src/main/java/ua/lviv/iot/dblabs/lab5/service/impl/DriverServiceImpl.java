package ua.lviv.iot.dblabs.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab5.domain.Driver;
import ua.lviv.iot.dblabs.lab5.exception.DriverNotFoundException;
import ua.lviv.iot.dblabs.lab5.repository.DriverRepository;
import ua.lviv.iot.dblabs.lab5.service.DriverService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver findById(String id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
    }

    @Transactional
    public Driver create(Driver driver) {
        driverRepository.save(driver);
        return driver;
    }

    @Transactional
    public void update(String id, Driver uDriver) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
        driver.setName(uDriver.getName());
        driver.setSurname(uDriver.getSurname());
        driver.setMiddlename(uDriver.getMiddlename());
        driver.setEmail(uDriver.getEmail());
        driver.setPhoneNumber(uDriver.getPhoneNumber());
        driver.setFines(uDriver.getFines());
        driver.setRents(uDriver.getRents());
        driverRepository.save(driver);
    }

    @Transactional
    public void delete(String id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
        driverRepository.delete(driver);
    }

    public List<Driver> findBySurname(String surname) {
        return driverRepository.findAllBySurname(surname);
    }
}