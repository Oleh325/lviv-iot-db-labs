package ua.lviv.iot.dblabs.lab6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab6.domain.City;
import ua.lviv.iot.dblabs.lab6.domain.Country;
import ua.lviv.iot.dblabs.lab6.exception.CountryNotFoundException;
import ua.lviv.iot.dblabs.lab6.repository.CountryRepository;
import ua.lviv.iot.dblabs.lab6.service.CityService;
import ua.lviv.iot.dblabs.lab6.service.CountryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityService cityService;

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public List<City> findAllCitiesForCountry(Integer countryId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        return country.getCities();
    }

    @Transactional
    public Country create(Country country) {
        countryRepository.save(country);
        return country;
    }

    @Transactional
    public void update(Integer id, Country uCountry) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        country.setName(uCountry.getName());
        country.setCities(uCountry.getCities());
        countryRepository.save(country);
    }

    @Transactional
    public void delete(Integer id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        for (City city : cityService.findAll()) {
            if (city.getCountry().getId() == id) {
                cityService.delete(city.getId());
            }
        }
        countryRepository.delete(country);
    }
}
