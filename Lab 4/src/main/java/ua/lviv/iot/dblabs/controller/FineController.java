package ua.lviv.iot.dblabs.controller;

import ua.lviv.iot.dblabs.domain.Fine;

import java.util.List;

public interface FineController extends GeneralController<Fine, Integer> {

    List<Fine> findByDriverLicenseNumber(String driverLicenseNumber);

}
