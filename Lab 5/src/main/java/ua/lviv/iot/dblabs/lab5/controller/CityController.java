package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.dblabs.lab5.domain.City;
import ua.lviv.iot.dblabs.lab5.dto.CityDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.CityDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityDTOAssembler cityDTOAssembler;

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDTO> getCity(@PathVariable Integer cityId) {
        City city = cityService.findById(cityId);
        CityDTO cityDTO = cityDTOAssembler.toModel(city);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CityDTO>> getAllCities() {
        List<City> cities = cityService.findAll();
        CollectionModel<CityDTO> cityDTOs = cityDTOAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(cityDTOs, HttpStatus.OK);
    }
}
