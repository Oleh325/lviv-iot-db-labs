package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.Rent;

import java.sql.Timestamp;
import java.util.List;

public interface RentService extends GeneralService<Rent, Integer> {

    List<Rent> findInDateRange(Timestamp from, Timestamp to);

    List<Rent> findByCarId(Integer carId);

    List<Rent> findByDriverLicenseNumber(String driverLicenseNumber);

}
