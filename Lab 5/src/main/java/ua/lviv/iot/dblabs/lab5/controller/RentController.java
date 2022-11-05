package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab5.domain.Rent;
import ua.lviv.iot.dblabs.lab5.dto.RentDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.RentDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.RentService;

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
