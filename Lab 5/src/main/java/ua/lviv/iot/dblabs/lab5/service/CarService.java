package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.Car;
import ua.lviv.iot.dblabs.lab5.domain.Rent;
import ua.lviv.iot.dblabs.lab5.domain.enums.FuelType;

import java.util.List;

public interface CarService extends GeneralService<Car, Integer> {

    Car create(Car car, Integer parkingId);

    void update(Integer id, Car car, Integer parkingId);

    List<Car> findByModel(String model);

    List<Car> findByFuelType(FuelType fuelType);

    List<Car> findInPriceRange(Double from, Double to);

    List<Car> findByParkingId(Integer parkingId);

    List<Rent> findAllRentsForCar(Integer carId);
}
