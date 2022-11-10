package ua.lviv.iot.dblabs.lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab6.domain.Fine;

import java.util.List;

@Repository
public interface FineRepository extends JpaRepository<Fine, Integer> {

    List<Fine> findAllByDriverLicenseNumber(String driverLicenseNumber);

}
