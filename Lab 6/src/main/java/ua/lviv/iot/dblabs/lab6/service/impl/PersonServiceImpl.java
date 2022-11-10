package ua.lviv.iot.dblabs.lab6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab6.domain.Person;
import ua.lviv.iot.dblabs.lab6.repository.PersonRepository;
import ua.lviv.iot.dblabs.lab6.service.PersonService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person with id " + id + " not found"));
    }

    @Transactional
    public Person create(Person person) {
        personRepository.save(person);
        return person;
    }

    @Transactional
    public void update(Integer id, Person uPerson) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person with id " + id + " not found"));
        person.setCityId(uPerson.getCityId());
        person.setName(uPerson.getName());
        person.setSurname(uPerson.getSurname());
        person.setMiddlename(uPerson.getMiddlename());
        person.setEmail(uPerson.getEmail());
        person.setPhoneNumber(uPerson.getPhoneNumber());
        personRepository.save(person);
    }

    @Transactional
    public void delete(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Person with id " + id + " not found"));
        personRepository.delete(person);
    }

    @Override
    public Integer insertion(Person person) {
        Integer cityId = person.getCityId();
        String name = person.getName();
        String surname = person.getSurname();
        String middlename = person.getMiddlename();
        String email = person.getEmail();
        String phoneNumber = person.getPhoneNumber();
        return personRepository.insertion(cityId, email, middlename, name, phoneNumber, surname);
    }

    @Override
    public Integer cityPerson(Integer cityId, Integer personId) {
        return personRepository.cityPerson(cityId, personId);
    }

    @Override
    public void addNonames() {
        personRepository.addNonames();
    }
}
