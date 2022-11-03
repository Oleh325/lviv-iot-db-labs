package ua.lviv.iot.dblabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.dao.CountryDAO;
import ua.lviv.iot.dblabs.domain.City;
import ua.lviv.iot.dblabs.domain.Country;
import ua.lviv.iot.dblabs.service.CityService;
import ua.lviv.iot.dblabs.service.CountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private CityService cityService;

    @Override
    public List<Country> findAll() {
        return countryDAO.findAll();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return countryDAO.findById(id);
    }

    @Override
    public int create(Country country) {
        return countryDAO.create(country);
    }

    @Override
    public int update(Integer id, Country country) {
        return countryDAO.update(id, country);
    }

    @Override
    public int delete(Integer id) {
        for (City city : cityService.findAll()) {
            if (city.getCountryId().equals(id)) {
                cityService.delete(city.getId());
            }
        }

        return countryDAO.delete(id);
    }
}
