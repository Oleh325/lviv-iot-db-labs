package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.Parking;

import java.util.List;

public interface ParkingService extends GeneralService<Parking, Integer> {

    List<Parking> findByCityId(Integer cityId);

}
