package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab5.domain.Parking;
import ua.lviv.iot.dblabs.lab5.dto.ParkingDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.ParkingDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.ParkingService;

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
