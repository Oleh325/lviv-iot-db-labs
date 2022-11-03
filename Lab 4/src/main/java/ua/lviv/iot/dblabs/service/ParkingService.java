package ua.lviv.iot.dblabs.service;

import ua.lviv.iot.dblabs.domain.Parking;

import java.util.List;

public interface ParkingService extends GeneralService<Parking, Integer> {

    List<Parking> findByCityId(Integer cityId);

}
