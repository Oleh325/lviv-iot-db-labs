package ua.lviv.iot.dblabs.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab5.domain.Car;
import ua.lviv.iot.dblabs.lab5.domain.enums.FuelType;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByModel(String model);

    List<Car> findAllByFuelType(FuelType fuelType);

    List<Car> findAllByRentCostPerDayUsdIsBetween(Double from, Double to);

    List<Car> findAllByParkingId(Integer parkingId);
}
