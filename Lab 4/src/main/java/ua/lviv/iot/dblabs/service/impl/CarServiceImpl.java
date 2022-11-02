package ua.lviv.iot.dblabs.service.impl;

import ua.lviv.iot.dblabs.dao.CarDAO;
import ua.lviv.iot.dblabs.domain.Car;
import ua.lviv.iot.dblabs.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Override
    public List<Car> findAll() {
        return carDAO.findAll();
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carDAO.findById(id);
    }

    @Override
    public int create(Car car) {
        return carDAO.create(car);
    }

    @Override
    public int update(Integer id, Car car) {
        return carDAO.update(id, car);
    }

    @Override
    public int delete(Integer id) {
        return carDAO.delete(id);
    }

    @Override
    public List<Car> findByModel(String model) {
        return carDAO.findByModel(model);
    }

    @Override
    public List<Car> findByFuelType(String fuelType) {
        return carDAO.findByFuelType(fuelType);
    }

    @Override
    public List<Car> findInPriceRange(Float from, Float to) {
        return carDAO.findInPriceRange(from, to);
    }

    @Override
    public List<Car> findByParkingId(Integer parkingId) {
        return carDAO.findByParkingId(parkingId);
    }
}
