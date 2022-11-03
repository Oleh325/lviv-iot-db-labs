package ua.lviv.iot.dblabs.controller;

import ua.lviv.iot.dblabs.domain.Parking;

import java.util.List;

public interface ParkingController extends GeneralController<Parking, Integer> {

    List<Parking> findByCityId(Integer cityId);

}
