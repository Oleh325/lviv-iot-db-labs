package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab5.domain.Country;
import ua.lviv.iot.dblabs.lab5.dto.CountryDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.CountryDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryDTOAssembler countryDTOAssembler;

    @GetMapping("/{countryId}")
    public ResponseEntity<CountryDTO> getCountry(@PathVariable Integer countryId) {
        Country country = countryService.findById(countryId);
        CountryDTO countryDTO = countryDTOAssembler.toModel(country);
        return new ResponseEntity<>(countryDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<CountryDTO>> getAllCountries() {
        List<Country> countries = countryService.findAll();
        CollectionModel<CountryDTO> countryDTOs = countryDTOAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(countryDTOs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CountryDTO> addCountry(@RequestBody Country country) {
        Country newCountry = countryService.create(country);
        CountryDTO countryDTO = countryDTOAssembler.toModel(newCountry);
        return new ResponseEntity<>(countryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody Country uCountry, @PathVariable Integer countryId) {
        countryService.update(countryId, uCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryId) {
        countryService.delete(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
