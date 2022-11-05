package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.City;
import ua.lviv.iot.dblabs.lab5.domain.Country;

import java.util.List;

public interface CountryService extends GeneralService<Country, Integer> {

    List<City> findAllCitiesForCountry(Integer countryId);

}
