package ua.lviv.iot.dblabs.controller.impl;

import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.controller.CarController;
import ua.lviv.iot.dblabs.domain.Car;
import ua.lviv.iot.dblabs.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Component
public class CarControllerImpl implements CarController {
    @Autowired
    private CarService carService;

    @Override
    public List<Car> findAll() {
        return carService.findAll();
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carService.findById(id);
    }

    @Override
    public int create(Car car) {
        return carService.create(car);
    }

    @Override
    public int update(Integer id, Car car) {
        return carService.update(id, car);
    }

    @Override
    public int delete(Integer id) {
        return carService.delete(id);
    }

    @Override
    public List<Car> findByModel(String model) {
        return carService.findByModel(model);
    }

    @Override
    public List<Car> findByFuelType(String fuelType) {
        return carService.findByFuelType(fuelType);
    }

    @Override
    public List<Car> findInPriceRange(Float from, Float to) {
        return carService.findInPriceRange(from, to);
    }

    @Override
    public List<Car> findByParkingId(Integer parkingId) {
        return carService.findByParkingId(parkingId);
    }
}
