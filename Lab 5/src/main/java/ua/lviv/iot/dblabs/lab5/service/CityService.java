package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.City;
import ua.lviv.iot.dblabs.lab5.domain.Parking;

import java.util.List;

public interface CityService extends GeneralService<City, Integer> {

    void update(Integer id, City city, Integer countryId);

    List<Parking> findAllParkingsForCity(Integer cityId);

}
