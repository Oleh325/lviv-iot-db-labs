package ua.lviv.iot.dblabs.lab6.service;

import ua.lviv.iot.dblabs.lab6.domain.City;
import ua.lviv.iot.dblabs.lab6.domain.Parking;

import java.util.List;

public interface CityService extends GeneralService<City, Integer> {

    void update(Integer id, City city, Integer countryId);

    List<Parking> findAllParkingsForCity(Integer cityId);

}
