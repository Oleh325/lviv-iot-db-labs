package ua.lviv.iot.dblabs.service;

import ua.lviv.iot.dblabs.domain.Car;

import java.util.List;

public interface CarService extends GeneralService<Car, Integer> {
    List<Car> findByModel(String model);

    List<Car> findByFuelType(String fuelType);

    List<Car> findInPriceRange(Float from, Float to);

    List<Car> findByParkingId(Integer parkingId);
}
