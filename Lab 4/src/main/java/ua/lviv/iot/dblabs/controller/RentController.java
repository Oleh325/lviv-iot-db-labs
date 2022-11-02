package ua.lviv.iot.dblabs.controller;

import ua.lviv.iot.dblabs.domain.Rent;

import java.sql.Time;
import java.util.List;

public interface RentController extends GeneralController<Rent, Integer> {

    List<Rent> findInDateRange(Time from, Time to);

    List<Rent> findByCarId(Integer carId);

    List<Rent> findByDriverLicenseNumber(String driverLicenseNumber);

}
