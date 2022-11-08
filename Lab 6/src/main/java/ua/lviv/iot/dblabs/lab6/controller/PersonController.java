package ua.lviv.iot.dblabs.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab6.domain.Person;
import ua.lviv.iot.dblabs.lab6.service.PersonService;


@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{personId}/cities/{cityId}")
    public ResponseEntity<Integer> cityPerson(@PathVariable Integer personId, @PathVariable Integer cityId) {
        Integer id = personService.cityPerson(cityId, personId);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/nonames")
    public ResponseEntity<?> addNonames() {
        personService.addNonames();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Integer> insertPerson(@RequestBody Person person) {
        Integer id = personService.insertion(person);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
