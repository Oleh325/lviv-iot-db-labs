package ua.lviv.iot.dblabs.dao;

import ua.lviv.iot.dblabs.domain.Fine;

import java.util.List;

public interface FineDAO extends GeneralDAO<Fine, Integer> {

    List<Fine> findByDriverLicenseNumber(String driverLicenseNumber);

}
