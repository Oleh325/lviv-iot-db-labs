package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab5.domain.Car;
import ua.lviv.iot.dblabs.lab5.dto.CarDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.CarDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarDTOAssembler carDTOAssembler;

    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Integer carId) {
        Car car = carService.findById(carId);
        CarDTO carDTO = carDTOAssembler.toModel(car);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CarDTO>> getAllCars() {
        List<Car> cars = carService.findAll();
        CollectionModel<CarDTO> carDTOs = carDTOAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/model/{model}")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsByModel(@PathVariable String model) {
        List<Car> cars = carService.findByModel(model);
        CollectionModel<CarDTO> carDTOs = carDTOAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CarDTO> addCar(@RequestBody Car car) {
        Car newCar = carService.create(car);
        CarDTO carDTO = carDTOAssembler.toModel(newCar);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carId}")
    public ResponseEntity<?> updateCar(@RequestBody Car uCar, @PathVariable Integer carId) {
        carService.update(carId, uCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer carId) {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
