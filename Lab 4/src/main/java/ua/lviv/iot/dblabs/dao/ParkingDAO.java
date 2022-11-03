package ua.lviv.iot.dblabs.dao;

import ua.lviv.iot.dblabs.domain.Parking;

import java.util.List;

public interface ParkingDAO extends GeneralDAO<Parking, Integer> {

    List<Parking> findByCityId(Integer cityId);
}
