package ua.lviv.iot.dblabs.lab6.service;

import ua.lviv.iot.dblabs.lab6.domain.Rent;

import java.sql.Timestamp;
import java.util.List;

public interface RentService extends GeneralService<Rent, Integer> {

    Rent create(Rent rent, String driverLicenseNumber, Integer carId);

    Rent create(Rent rent, String driverLicenseNumber, Integer carId, String transactionId);

    void update(Integer id, Rent rent, String driverLicenseNumber, Integer carId);

    void update(Integer id, Rent rent, String driverLicenseNumber, Integer carId, String transactionId);

    List<Rent> findInDateRange(Timestamp from, Timestamp to);

    List<Rent> findByCarId(Integer carId);

    List<Rent> findByDriverLicenseNumber(String driverLicenseNumber);

}
