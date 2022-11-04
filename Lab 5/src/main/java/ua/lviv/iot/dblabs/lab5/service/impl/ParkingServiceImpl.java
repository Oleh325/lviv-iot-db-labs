package ua.lviv.iot.dblabs.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab5.domain.Car;
import ua.lviv.iot.dblabs.lab5.domain.Parking;
import ua.lviv.iot.dblabs.lab5.exception.ParkingNotFoundException;
import ua.lviv.iot.dblabs.lab5.repository.ParkingRepository;
import ua.lviv.iot.dblabs.lab5.service.CarService;
import ua.lviv.iot.dblabs.lab5.service.ParkingService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ParkingServiceImpl implements ParkingService {

        @Autowired
        private ParkingRepository parkingRepository;

        @Autowired
        private CarService carService;

        public List<Parking> findAll() {
                return parkingRepository.findAll();
        }

        public Parking findById(Integer id) {
                return parkingRepository.findById(id)
                        .orElseThrow(() -> new ParkingNotFoundException(id));
        }

        @Transactional
        public Parking create(Parking parking) {
                parkingRepository.save(parking);
                return parking;
        }

        @Transactional
        public void update(Integer id, Parking uParking) {
                Parking parking = parkingRepository.findById(id)
                        .orElseThrow(() -> new ParkingNotFoundException(id));
                parking.setLocation(uParking.getLocation());
                parking.setType(uParking.getType());
                parking.setCity(uParking.getCity());
                parking.setCars(uParking.getCars());
                parkingRepository.save(parking);
        }

        @Transactional
        public void delete(Integer id) {
                Parking parking = parkingRepository.findById(id)
                        .orElseThrow(() -> new ParkingNotFoundException(id));
                for (Car car : carService.findByParkingId(id)) {
                        car.setParking(null);
                        carService.update(car.getId(), car);
                }

                parkingRepository.delete(parking);
        }

        public List<Parking> findByCityId(Integer cityId) {
                return parkingRepository.findAllByCityId(cityId);
        }
}
