package ua.lviv.iot.dblabs.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.iot.dblabs.controller.ParkingController;
import ua.lviv.iot.dblabs.domain.Parking;
import ua.lviv.iot.dblabs.service.ParkingService;

import java.util.List;
import java.util.Optional;

@Component
public class ParkingControllerImpl implements ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Override
    public List<Parking> findAll() {
        return parkingService.findAll();
    }

    @Override
    public Optional<Parking> findById(Integer id) {
        return parkingService.findById(id);
    }

    @Override
    public int create(Parking parking) {
        return parkingService.create(parking);
    }

    @Override
    public int update(Integer id, Parking parking) {
        return parkingService.update(id, parking);
    }

    @Override
    public int delete(Integer id) {
        return parkingService.delete(id);
    }

    @Override
    public List<Parking> findByCityId(Integer cityId) {
        return parkingService.findByCityId(cityId);
    }
}
