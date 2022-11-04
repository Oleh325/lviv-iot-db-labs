package ua.lviv.iot.dblabs.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab5.domain.Parking;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

    List<Parking> findAllByCityId(Integer cityId);

}
