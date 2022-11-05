package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab5.domain.Driver;
import ua.lviv.iot.dblabs.lab5.domain.Fine;
import ua.lviv.iot.dblabs.lab5.domain.Parking;
import ua.lviv.iot.dblabs.lab5.dto.DriverDTO;
import ua.lviv.iot.dblabs.lab5.dto.FineDTO;
import ua.lviv.iot.dblabs.lab5.dto.ParkingDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.DriverDTOAssembler;
import ua.lviv.iot.dblabs.lab5.dto.assembler.FineDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.DriverService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverDTOAssembler driverDTOAssembler;
    @Autowired
    private FineDTOAssembler fineDTOAssembler;

    @GetMapping("/{licenseNumber}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable String licenseNumber) {
        Driver driver = driverService.findById(licenseNumber);
        DriverDTO driverDTO = driverDTOAssembler.toModel(driver);
        return new ResponseEntity<>(driverDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<DriverDTO>> getAllDrivers() {
        List<Driver> drivers = driverService.findAll();
        CollectionModel<DriverDTO> driverDTOs = driverDTOAssembler.toCollectionModel(drivers);
        return new ResponseEntity<>(driverDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{driverId}/fines")
    public ResponseEntity<CollectionModel<FineDTO>> getAllFinesForDriver(@PathVariable String driverId) {
        List<Fine> fines = driverService.findAllFinesForDriver(driverId);
        Link selfLink = linkTo(methodOn(DriverController.class).getAllFinesForDriver(driverId)).withSelfRel();
        CollectionModel<FineDTO> fineDTOs = fineDTOAssembler.toCollectionModel(fines, selfLink);
        return new ResponseEntity<>(fineDTOs, HttpStatus.OK);
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<CollectionModel<DriverDTO>> getDriversBySurname(@PathVariable String surname) {
        List<Driver> drivers = driverService.findBySurname(surname);
        CollectionModel<DriverDTO> driverDTOs = driverDTOAssembler.toCollectionModel(drivers);
        return new ResponseEntity<>(driverDTOs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DriverDTO> addDriver(@RequestBody Driver driver) {
        Driver newDriver = driverService.create(driver);
        DriverDTO driverDTO = driverDTOAssembler.toModel(newDriver);
        return new ResponseEntity<>(driverDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{licenseNumber}")
    public ResponseEntity<?> updateDriver(@RequestBody Driver uDriver, @PathVariable String licenseNumber) {
        driverService.update(licenseNumber, uDriver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{licenseNumber}")
    public ResponseEntity<?> deleteDriver(@PathVariable String licenseNumber) {
        driverService.delete(licenseNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
