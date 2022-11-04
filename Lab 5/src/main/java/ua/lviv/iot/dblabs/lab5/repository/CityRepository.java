package ua.lviv.iot.dblabs.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab5.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
