package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.City;

public interface CityService extends GeneralService<City, Integer> {

    void update(Integer id, City city, Integer countryId);

}
