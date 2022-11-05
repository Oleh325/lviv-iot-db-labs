package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab5.domain.Fine;
import ua.lviv.iot.dblabs.lab5.dto.FineDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.FineDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.FineService;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineService fineService;
    @Autowired
    private FineDTOAssembler fineDTOAssembler;

    @GetMapping("/{fineId}")
    public ResponseEntity<FineDTO> getFine(@PathVariable Integer fineId) {
        Fine fine = fineService.findById(fineId);
        FineDTO fineDTO = fineDTOAssembler.toModel(fine);
        return new ResponseEntity<>(fineDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<FineDTO>> getAllFines() {
        List<Fine> fines = fineService.findAll();
        CollectionModel<FineDTO> fineDTOs = fineDTOAssembler.toCollectionModel(fines);
        return new ResponseEntity<>(fineDTOs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<FineDTO> addFine(@RequestBody Fine fine) {
        Fine newFine = fineService.create(fine);
        FineDTO fineDTO = fineDTOAssembler.toModel(newFine);
        return new ResponseEntity<>(fineDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{fineId}/drivers/{driverId}")
    public ResponseEntity<?> updateFineWithDriver(@RequestBody Fine uFine, @PathVariable Integer fineId, @PathVariable String driverId) {
        fineService.update(fineId, uFine, driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{fineId}")
    public ResponseEntity<?> deleteFine(@PathVariable Integer fineId) {
        fineService.delete(fineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
