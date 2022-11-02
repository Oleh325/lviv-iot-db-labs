package ua.lviv.iot.dblabs.service;

import ua.lviv.iot.dblabs.domain.Fine;

import java.util.List;

public interface FineService extends GeneralService<Fine, Integer> {

    List<Fine> findByDriverLicenseNumber(String driverLicenseNumber);

}
