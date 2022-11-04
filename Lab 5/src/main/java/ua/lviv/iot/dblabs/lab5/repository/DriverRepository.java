package ua.lviv.iot.dblabs.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab5.domain.Driver;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {

    List<Driver> findAllBySurname(String surname);

}
