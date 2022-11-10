package ua.lviv.iot.dblabs.lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab6.domain.Parking;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

    List<Parking> findAllByCityId(Integer cityId);

}
