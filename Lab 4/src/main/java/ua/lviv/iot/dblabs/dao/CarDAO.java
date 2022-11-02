package ua.lviv.iot.dblabs.dao;

import ua.lviv.iot.dblabs.domain.Car;

import java.util.List;

public interface CarDAO extends GeneralDAO<Car, Integer> {
    List<Car> findByModel(String model);

    List<Car> findByFuelType(String fuelType);

    List<Car> findInPriceRange(Float from, Float to);

    List<Car> findByParkingId(Integer parkingId);
}
