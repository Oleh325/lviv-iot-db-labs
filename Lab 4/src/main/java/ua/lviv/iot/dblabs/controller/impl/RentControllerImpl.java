package ua.lviv.iot.dblabs.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.iot.dblabs.controller.RentController;
import ua.lviv.iot.dblabs.domain.Rent;
import ua.lviv.iot.dblabs.service.RentService;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Component
public class RentControllerImpl implements RentController {

    @Autowired
    private RentService rentService;

    @Override
    public List<Rent> findAll() {
        return rentService.findAll();
    }

    @Override
    public Optional<Rent> findById(Integer id) {
        return rentService.findById(id);
    }

    @Override
    public int create(Rent rent) {
        return rentService.create(rent);
    }

    @Override
    public int update(Integer id, Rent rent) {
        return rentService.update(id, rent);
    }

    @Override
    public int delete(Integer id) {
        return rentService.delete(id);
    }

    @Override
    public List<Rent> findInDateRange(Time from, Time to) {
        return rentService.findInDateRange(from, to);
    }

    @Override
    public List<Rent> findByCarId(Integer carId) {
        return rentService.findByCarId(carId);
    }

    @Override
    public List<Rent> findByDriverLicenseNumber(String driverLicenseNumber) {
        return rentService.findByDriverLicenseNumber(driverLicenseNumber);
    }
}
