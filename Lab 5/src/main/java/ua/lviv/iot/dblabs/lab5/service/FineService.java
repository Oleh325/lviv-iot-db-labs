package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.Fine;

import java.util.List;

public interface FineService extends GeneralService<Fine, Integer> {

    void update(Integer id, Fine fine, String driverLicenseNumber);

    List<Fine> findByDriverLicenseNumber(String driverLicenseNumber);

}
