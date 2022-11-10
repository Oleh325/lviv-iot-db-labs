package ua.lviv.iot.dblabs.lab6.service;

import ua.lviv.iot.dblabs.lab6.domain.Parking;

import java.util.List;

public interface ParkingService extends GeneralService<Parking, Integer> {

    void update(Integer id, Parking parking, Integer cityId);

    List<Parking> findByCityId(Integer cityId);

}
