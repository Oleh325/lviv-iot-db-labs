package ua.lviv.iot.dblabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.dao.RentDAO;
import ua.lviv.iot.dblabs.domain.Rent;
import ua.lviv.iot.dblabs.service.RentService;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentDAO rentDAO;

    @Override
    public List<Rent> findAll() {
        return rentDAO.findAll();
    }

    @Override
    public Optional<Rent> findById(Integer id) {
        return rentDAO.findById(id);
    }

    @Override
    public int create(Rent rent) {
        return rentDAO.create(rent);
    }

    @Override
    public int update(Integer id, Rent rent) {
        return rentDAO.update(id, rent);
    }

    @Override
    public int delete(Integer id) {
        return rentDAO.delete(id);
    }

    @Override
    public List<Rent> findInDateRange(Time from, Time to) {
        return rentDAO.findInDateRange(from, to);
    }

    @Override
    public List<Rent> findByCarId(Integer carId) {
        return rentDAO.findByCarId(carId);
    }

    @Override
    public List<Rent> findByDriverLicenseNumber(String driverLicenseNumber) {
        return rentDAO.findByDriverLicenseNumber(driverLicenseNumber);
    }
}
