package ua.lviv.iot.dblabs.lab6.service;

import ua.lviv.iot.dblabs.lab6.domain.City;
import ua.lviv.iot.dblabs.lab6.domain.Country;

import java.util.List;

public interface CountryService extends GeneralService<Country, Integer> {

    List<City> findAllCitiesForCountry(Integer countryId);

}
