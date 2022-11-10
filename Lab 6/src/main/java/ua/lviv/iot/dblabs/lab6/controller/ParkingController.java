package ua.lviv.iot.dblabs.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab6.domain.Parking;
import ua.lviv.iot.dblabs.lab6.dto.ParkingDTO;
import ua.lviv.iot.dblabs.lab6.dto.assembler.ParkingDTOAssembler;
import ua.lviv.iot.dblabs.lab6.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingDTOAssembler parkingDTOAssembler;

    @GetMapping("/{parkingId}")
    public ResponseEntity<ParkingDTO> getParking(@PathVariable Integer parkingId) {
        Parking parking = parkingService.findById(parkingId);
        ParkingDTO parkingDTO = parkingDTOAssembler.toModel(parking);
        return new ResponseEntity<>(parkingDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<ParkingDTO>> getAllParkings() {
        List<Parking> parkings = parkingService.findAll();
        CollectionModel<ParkingDTO> parkingDTOs = parkingDTOAssembler.toCollectionModel(parkings);
        return new ResponseEntity<>(parkingDTOs, HttpStatus.OK);
    }

    @GetMapping("/cities/{cityId}")
    public ResponseEntity<CollectionModel<ParkingDTO>> getParkingsByCity(@PathVariable Integer cityId) {
        List<Parking> parkings = parkingService.findByCityId(cityId);
        CollectionModel<ParkingDTO> parkingDTOs = parkingDTOAssembler.toCollectionModel(parkings);
        return new ResponseEntity<>(parkingDTOs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ParkingDTO> addParking(@RequestBody Parking parking) {
        Parking newParking = parkingService.create(parking);
        ParkingDTO parkingDTO = parkingDTOAssembler.toModel(newParking);
        return new ResponseEntity<>(parkingDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{parkingId}/cities/{cityId}")
    public ResponseEntity<?> updateParkingWithCity(@RequestBody Parking uParking, @PathVariable Integer parkingId, @PathVariable Integer cityId) {
        parkingService.update(parkingId, uParking, cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{parkingId}")
    public ResponseEntity<?> deleteParking(@PathVariable Integer parkingId) {
        parkingService.delete(parkingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
