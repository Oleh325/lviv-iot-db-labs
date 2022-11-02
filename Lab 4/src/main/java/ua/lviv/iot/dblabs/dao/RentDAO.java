package ua.lviv.iot.dblabs.dao;

import ua.lviv.iot.dblabs.domain.Rent;

import java.sql.Time;
import java.util.List;

public interface RentDAO extends GeneralDAO<Rent, Integer> {

    List<Rent> findInDateRange(Time from, Time to);

    List<Rent> findByCarId(Integer carId);

    List<Rent> findByDriverLicenseNumber(String driverLicenseNumber);
}
