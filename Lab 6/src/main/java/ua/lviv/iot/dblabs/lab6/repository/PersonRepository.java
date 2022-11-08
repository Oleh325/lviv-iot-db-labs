package ua.lviv.iot.dblabs.lab6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab6.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Procedure("person_insertion")
    Integer insertion(Integer cityId, String email, String middlename, String name, String phoneNumber, String surname);

    @Procedure("city_person")
    Integer cityPerson(Integer cityId, Integer personId);

    @Procedure("person_add_nonames")
    void addNonames();
}
