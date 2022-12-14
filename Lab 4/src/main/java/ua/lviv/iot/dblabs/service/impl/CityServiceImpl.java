package ua.lviv.iot.dblabs.service.impl;

import ua.lviv.iot.dblabs.dao.CityDAO;
import ua.lviv.iot.dblabs.domain.City;
import ua.lviv.iot.dblabs.domain.Parking;
import ua.lviv.iot.dblabs.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.service.ParkingService;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private ParkingService parkingService;

    @Override
    public List<City> findAll() {
        return cityDAO.findAll();
    }

    @Override
    public Optional<City> findById(Integer id) {
        return cityDAO.findById(id);
    }

    @Override
    public int create(City city) {
        return cityDAO.create(city);
    }

    @Override
    public int update(Integer id, City city) {
        return cityDAO.update(id, city);
    }

    @Override
    public int delete(Integer id) {
        for (Parking parking : parkingService.findByCityId(id)) {
            parkingService.delete(parking.getId());
        }

        return cityDAO.delete(id);
    }
}
