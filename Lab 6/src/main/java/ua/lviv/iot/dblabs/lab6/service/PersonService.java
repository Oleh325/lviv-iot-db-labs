package ua.lviv.iot.dblabs.lab6.service;

import ua.lviv.iot.dblabs.lab6.domain.Person;

public interface PersonService extends GeneralService<Person, Integer> {

    Integer insertion(Person person);

    Integer cityPerson(Integer cityId, Integer personId);

    void addNonames();

}
