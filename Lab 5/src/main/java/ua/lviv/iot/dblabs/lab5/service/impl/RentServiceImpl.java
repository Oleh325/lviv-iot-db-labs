package ua.lviv.iot.dblabs.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab5.domain.Rent;
import ua.lviv.iot.dblabs.lab5.exception.RentNotFoundException;
import ua.lviv.iot.dblabs.lab5.repository.RentRepository;
import ua.lviv.iot.dblabs.lab5.service.CarService;
import ua.lviv.iot.dblabs.lab5.service.DriverService;
import ua.lviv.iot.dblabs.lab5.service.RentService;
import ua.lviv.iot.dblabs.lab5.service.TransactionService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;

    @Autowired
    private TransactionService transactionService;

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    public Rent findById(Integer id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    @Transactional
    public Rent create(Rent rent) {
        rentRepository.save(rent);
        return rent;
    }

    @Transactional
    public Rent create(Rent rent, String driverLicenseNumber, Integer carId) {
        rent.setDriver(driverService.findById(driverLicenseNumber));
        rent.setCar(carService.findById(carId));
        rentRepository.save(rent);
        return rent;
    }

    @Transactional
    public Rent create(Rent rent, String driverLicenseNumber, Integer carId, String transactionId) {
        rent.setDriver(driverService.findById(driverLicenseNumber));
        rent.setCar(carService.findById(carId));
        rent.setTransaction(transactionService.findById(transactionId));
        rentRepository.save(rent);
        return rent;
    }

    @Transactional
    public void update(Integer id, Rent uRent) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        rent.setDateOfRent(uRent.getDateOfRent());
        rent.setEndDateOfRent(uRent.getEndDateOfRent());
        rent.setPaymentType(uRent.getPaymentType());
        rent.setTransaction(uRent.getTransaction());
        rent.setCar(uRent.getCar());
        rent.setDriver(uRent.getDriver());
        rentRepository.save(rent);
    }

    @Transactional
    public void update(Integer id, Rent uRent, String driverLicenseNumber, Integer carId) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        rent.setDateOfRent(uRent.getDateOfRent());
        rent.setEndDateOfRent(uRent.getEndDateOfRent());
        rent.setPaymentType(uRent.getPaymentType());
        rent.setTransaction(uRent.getTransaction());
        rent.setCar(carService.findById(carId));
        rent.setDriver(driverService.findById(driverLicenseNumber));
        rentRepository.save(rent);
    }

    @Transactional
    public void update(Integer id, Rent uRent, String driverLicenseNumber, Integer carId, String transactionId) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        rent.setDateOfRent(uRent.getDateOfRent());
        rent.setEndDateOfRent(uRent.getEndDateOfRent());
        rent.setPaymentType(uRent.getPaymentType());
        rent.setTransaction(transactionService.findById(transactionId));
        rent.setCar(carService.findById(carId));
        rent.setDriver(driverService.findById(driverLicenseNumber));
        rentRepository.save(rent);
    }

    @Transactional
    public void delete(Integer id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
        rentRepository.delete(rent);
    }

    public List<Rent> findInDateRange(Timestamp from, Timestamp to) {
        return rentRepository.findAllByDateOfRentIsBetweenAndEndDateOfRentIsBetween(from, to, from, to);
    }

    public List<Rent> findByCarId(Integer carId) {
        return rentRepository.findAllByCarId(carId);
    }

    public List<Rent> findByDriverLicenseNumber(String driverLicenseNumber) {
        return rentRepository.findAllByDriverLicenseNumber(driverLicenseNumber);
    }
}
