package ua.lviv.iot.dblabs.lab6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab6.domain.Car;
import ua.lviv.iot.dblabs.lab6.domain.Rent;
import ua.lviv.iot.dblabs.lab6.domain.enums.FuelType;
import ua.lviv.iot.dblabs.lab6.exception.CarNotFoundException;
import ua.lviv.iot.dblabs.lab6.repository.CarRepository;
import ua.lviv.iot.dblabs.lab6.service.CarService;
import ua.lviv.iot.dblabs.lab6.service.ParkingService;
import ua.lviv.iot.dblabs.lab6.service.RentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    private final RentService rentService;

    private final ParkingService parkingService;

    @Autowired
    public CarServiceImpl(@Lazy RentService rentService,
                          @Lazy ParkingService parkingService) {
        this.rentService = rentService;
        this.parkingService = parkingService;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public List<Rent> findAllRentsForCar(Integer carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        return car.getRents();
    }

    @Override
    public Double getAverageCarPrice() {
        return carRepository.getAverageCarPrice();
    }

    @Override
    public void carCursorProcedure() {
        carRepository.carCursorProcedure();
    }

    @Transactional
    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }

    @Transactional
    public Car create(Car car, Integer parkingId) {
        car.setParking(parkingService.findById(parkingId));
        carRepository.save(car);
        return car;
    }

    @Transactional
    public void update(Integer id, Car uCar) {
        Car car = buildCar(id, uCar);
        car.setParking(uCar.getParking());
        carRepository.save(car);
    }

    @Transactional
    public void update(Integer id, Car uCar, Integer parkingId) {
        Car car = buildCar(id, uCar);
        car.setParking(parkingService.findById(parkingId));
        carRepository.save(car);
    }

    private Car buildCar(Integer id, Car uCar) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        car.setModel(uCar.getModel());
        car.setColor(uCar.getColor());
        car.setTransmissionType(uCar.getTransmissionType());
        car.setSeatsCount(uCar.getSeatsCount());
        car.setHasAc(uCar.getHasAc());
        car.setBaggageCapacityKg(uCar.getBaggageCapacityKg());
        car.setRentCostPerDayUsd(uCar.getRentCostPerDayUsd());
        car.setFuelType(uCar.getFuelType());
        car.setAdditionalInfo(uCar.getAdditionalInfo());
        car.setRents(uCar.getRents());
        return car;
    }

    @Transactional
    public void delete(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        for (Rent rent : rentService.findByCarId(id)) {
            rentService.delete(rent.getId());
        }
        carRepository.delete(car);
    }

    public List<Car> findByModel(String model) {
        return carRepository.findAllByModel(model);
    }

    public List<Car> findByFuelType(FuelType fuelType) {
        return carRepository.findAllByFuelType(fuelType);
    }

    public List<Car> findInPriceRange(Double from, Double to) {
        return carRepository.findAllByRentCostPerDayUsdIsBetween(from, to);
    }

    public List<Car> findByParkingId(Integer parkingId) {
        return carRepository.findAllByParkingId(parkingId);
    }
}
