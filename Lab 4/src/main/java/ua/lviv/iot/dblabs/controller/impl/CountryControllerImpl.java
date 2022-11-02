package ua.lviv.iot.dblabs.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.iot.dblabs.controller.CountryController;
import ua.lviv.iot.dblabs.domain.Country;
import ua.lviv.iot.dblabs.service.CountryService;

import java.util.List;
import java.util.Optional;

@Component
public class CountryControllerImpl implements CountryController {

    @Autowired
    private CountryService countryService;

    @Override
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @Override
    public Optional<Country> findById(Integer id) {
        return countryService.findById(id);
    }

    @Override
    public int create(Country country) {
        return countryService.create(country);
    }

    @Override
    public int update(Integer id, Country country) {
        return countryService.update(id, country);
    }

    @Override
    public int delete(Integer id) {
        return countryService.delete(id);
    }
}
