package ua.lviv.iot.dblabs.lab6.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab6.domain.City;
import ua.lviv.iot.dblabs.lab6.domain.Parking;
import ua.lviv.iot.dblabs.lab6.exception.CityNotFoundException;
import ua.lviv.iot.dblabs.lab6.repository.CityRepository;
import ua.lviv.iot.dblabs.lab6.service.CityService;
import ua.lviv.iot.dblabs.lab6.service.CountryService;
import ua.lviv.iot.dblabs.lab6.service.ParkingService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    private final ParkingService parkingService;

    private final CountryService countryService;

    @Autowired
    public CityServiceImpl(@Lazy ParkingService parkingService, @Lazy CountryService countryService) {
        this.parkingService = parkingService;
        this.countryService = countryService;
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Override
    public List<Parking> findAllParkingsForCity(Integer cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException(cityId));
        return city.getParkings();
    }

    @Transactional
    public City create(City city) {
        cityRepository.save(city);
        return city;
    }

    @Transactional
    public void update(Integer id, City uCity) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        city.setName(uCity.getName());
        city.setCountry(uCity.getCountry());
        city.setParkings(uCity.getParkings());
        cityRepository.save(city);
    }

    @Transactional
    public void update(Integer id, City uCity, Integer countryId) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        city.setName(uCity.getName());
        city.setCountry(countryService.findById(countryId));
        city.setParkings(uCity.getParkings());
        cityRepository.save(city);
    }

    @Transactional
    public void delete(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        for (Parking parking : parkingService.findByCityId(id)) {
            parkingService.delete(parking.getId());
        }
        cityRepository.delete(city);
    }
}
