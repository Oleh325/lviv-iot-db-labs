package ua.lviv.iot.dblabs.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab6.domain.Rent;
import ua.lviv.iot.dblabs.lab6.dto.RentDTO;
import ua.lviv.iot.dblabs.lab6.dto.assembler.RentDTOAssembler;
import ua.lviv.iot.dblabs.lab6.service.RentService;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/rents")
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private RentDTOAssembler rentDTOAssembler;

    @GetMapping("/{rentId}")
    public ResponseEntity<RentDTO> getRent(@PathVariable Integer rentId) {
        Rent rent = rentService.findById(rentId);
        RentDTO rentDTO = rentDTOAssembler.toModel(rent);
        return new ResponseEntity<>(rentDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<RentDTO>> getAllRents() {
        List<Rent> rents = rentService.findAll();
        CollectionModel<RentDTO> rentDTOs = rentDTOAssembler.toCollectionModel(rents);
        return new ResponseEntity<>(rentDTOs, HttpStatus.OK);
    }

    @GetMapping("/cars/{carId}")
    public ResponseEntity<CollectionModel<RentDTO>> getRentsByCarId(@PathVariable Integer carId) {
        List<Rent> rents = rentService.findByCarId(carId);
        CollectionModel<RentDTO> rentDTOs = rentDTOAssembler.toCollectionModel(rents);
        return new ResponseEntity<>(rentDTOs, HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverLicenseNumber}")
    public ResponseEntity<CollectionModel<RentDTO>> getRentsByDriver(@PathVariable String driverLicenseNumber) {
        List<Rent> rents = rentService.findByDriverLicenseNumber(driverLicenseNumber);
        CollectionModel<RentDTO> rentDTOs = rentDTOAssembler.toCollectionModel(rents);
        return new ResponseEntity<>(rentDTOs, HttpStatus.OK);
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<CollectionModel<RentDTO>> getRentsInDateRange(@PathVariable Timestamp from, @PathVariable Timestamp to) {
        List<Rent> rents = rentService.findInDateRange(from, to);
        CollectionModel<RentDTO> rentDTOs = rentDTOAssembler.toCollectionModel(rents);
        return new ResponseEntity<>(rentDTOs, HttpStatus.OK);
    }

    @PostMapping("/{driverId}/{carId}")
    public ResponseEntity<RentDTO> addRentWithDriverAndCar(@RequestBody Rent rent, @PathVariable String driverId,
                                                           @PathVariable Integer carId) {
        Rent newRent = rentService.create(rent, driverId, carId);
        RentDTO rentDTO = rentDTOAssembler.toModel(newRent);
        return new ResponseEntity<>(rentDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{driverId}/{carId}/{transactionId}")
    public ResponseEntity<RentDTO> addRentWithDriverAndCarAndTransaction(@RequestBody Rent rent, @PathVariable String driverId,
                                                                         @PathVariable Integer carId,
                                                                         @PathVariable String transactionId) {
        Rent newRent = rentService.create(rent, driverId, carId, transactionId);
        RentDTO rentDTO = rentDTOAssembler.toModel(newRent);
        return new ResponseEntity<>(rentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{rentId}/{driverId}/{carId}")
    public ResponseEntity<?> updateRent(@RequestBody Rent uRent, @PathVariable Integer rentId, @PathVariable String driverId,
                                        @PathVariable Integer carId) {
        rentService.update(rentId, uRent, driverId, carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{rentId}/{driverId}/{carId}/transactions/{transactionId}")
    public ResponseEntity<?> updateRentWithTransaction(@RequestBody Rent uRent, @PathVariable Integer rentId,
                                                       @PathVariable String driverId,
                                                       @PathVariable Integer carId,
                                                       @PathVariable String transactionId) {
        rentService.update(rentId, uRent, driverId, carId, transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{rentId}")
    public ResponseEntity<?> deleteRent(@PathVariable Integer rentId) {
        rentService.delete(rentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
