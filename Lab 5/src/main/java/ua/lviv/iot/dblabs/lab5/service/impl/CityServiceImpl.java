package ua.lviv.iot.dblabs.lab5.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab5.domain.City;
import ua.lviv.iot.dblabs.lab5.domain.Parking;
import ua.lviv.iot.dblabs.lab5.exception.CityNotFoundException;
import ua.lviv.iot.dblabs.lab5.repository.CityRepository;
import ua.lviv.iot.dblabs.lab5.service.CityService;
import ua.lviv.iot.dblabs.lab5.service.ParkingService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ParkingService parkingService;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
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
    public void delete(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        for (Parking parking : parkingService.findByCityId(id)) {
            parkingService.delete(parking.getId());
        }
        cityRepository.delete(city);
    }
}
