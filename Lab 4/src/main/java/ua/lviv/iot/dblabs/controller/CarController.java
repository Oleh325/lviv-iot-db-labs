package ua.lviv.iot.dblabs.controller;

import ua.lviv.iot.dblabs.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarController extends GeneralController<Car, Integer> {
    List<Car> findByModel(String model);

    List<Car> findByFuelType(String fuelType);

    List<Car> findInPriceRange(Float from, Float to);

    List<Car> findByParkingId(Integer parkingId);
}
