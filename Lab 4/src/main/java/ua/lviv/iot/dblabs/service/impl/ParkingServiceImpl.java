package ua.lviv.iot.dblabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.dao.ParkingDAO;
import ua.lviv.iot.dblabs.domain.Parking;
import ua.lviv.iot.dblabs.service.ParkingService;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

        @Autowired
        private ParkingDAO parkingDAO;

        @Override
        public List<Parking> findAll() {
                return parkingDAO.findAll();
        }

        @Override
        public Optional<Parking> findById(Integer id) {
                return parkingDAO.findById(id);
        }

        @Override
        public int create(Parking parking) {
                return parkingDAO.create(parking);
        }

        @Override
        public int update(Integer id, Parking parking) {
                return parkingDAO.update(id, parking);
        }

        @Override
        public int delete(Integer id) {
                return parkingDAO.delete(id);
        }

        @Override
        public List<Parking> findByCityId(Integer cityId) {
                return parkingDAO.findByCityId(cityId);
        }
}
