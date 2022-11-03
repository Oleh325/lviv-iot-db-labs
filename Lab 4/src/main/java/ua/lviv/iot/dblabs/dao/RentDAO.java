package ua.lviv.iot.dblabs.dao;

import ua.lviv.iot.dblabs.domain.Rent;

import java.sql.Timestamp;
import java.util.List;

public interface RentDAO extends GeneralDAO<Rent, Integer> {

    List<Rent> findInDateRange(Timestamp from, Timestamp to);

    List<Rent> findByCarId(Integer carId);

    List<Rent> findByDriverLicenseNumber(String driverLicenseNumber);
}
