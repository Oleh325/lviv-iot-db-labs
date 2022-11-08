package ua.lviv.iot.dblabs.lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab6.domain.Rent;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    List<Rent> findAllByDateOfRentIsBetweenAndEndDateOfRentIsBetween(Timestamp from, Timestamp to, Timestamp endFrom, Timestamp endTo);

    List<Rent> findAllByCarId(Integer carId);

    List<Rent> findAllByDriverLicenseNumber(String driverLicenseNumber);

}
