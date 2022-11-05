package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab5.domain.Car;
import ua.lviv.iot.dblabs.lab5.domain.Rent;
import ua.lviv.iot.dblabs.lab5.domain.enums.FuelType;
import ua.lviv.iot.dblabs.lab5.dto.CarDTO;
import ua.lviv.iot.dblabs.lab5.dto.RentDTO;
import ua.lviv.iot.dblabs.lab5.dto.assembler.CarDTOAssembler;
import ua.lviv.iot.dblabs.lab5.dto.assembler.RentDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.CarService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarDTOAssembler carDTOAssembler;
    @Autowired
    private RentDTOAssembler rentDTOAssembler;

    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Integer carId) {
        Car car = carService.findById(carId);
        CarDTO carDTO = carDTOAssembler.toModel(car);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{carId}/rents")
    public ResponseEntity<CollectionModel<RentDTO>> getAllRentsForCar(@PathVariable Integer carId) {
        List<Rent> rents = carService.findAllRentsForCar(carId);
        Link selfLink = linkTo(methodOn(CarController.class).getAllRentsForCar(carId)).withSelfRel();
        CollectionModel<RentDTO> rentDTOs = rentDTOAssembler.toCollectionModel(rents, selfLink);
        return new ResponseEntity<>(rentDTOs, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<CarDTO>> getAllCars() {
        List<Car> cars = carService.findAll();
        CollectionModel<CarDTO> carDTOs = carDTOAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsByModel(@PathVariable String model) {
        List<Car> cars = carService.findByModel(model);
        CollectionModel<CarDTO> carDTOs = carDTOAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @GetMapping("/parkings/{parkingId}")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsByParkingId(@PathVariable Integer parkingId) {
        List<Car> cars = carService.findByParkingId(parkingId);
        CollectionModel<CarDTO> carDTOs = carDTOAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @GetMapping("/fuel_type/{fuelType}")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsByFuelType(@PathVariable FuelType fuelType) {
        List<Car> cars = carService.findByFuelType(fuelType);
        CollectionModel<CarDTO> carDTOs = carDTOAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsInPriceRange(@PathVariable Double from, @PathVariable Double to) {
        List<Car> cars = carService.findInPriceRange(from, to);
        CollectionModel<CarDTO> carDTOs = carDTOAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDTOs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CarDTO> addCar(@RequestBody Car car) {
        Car newCar = carService.create(car);
        CarDTO carDTO = carDTOAssembler.toModel(newCar);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{parkingId}")
    public ResponseEntity<CarDTO> addCarWithParking(@RequestBody Car car, @PathVariable Integer parkingId) {
        Car newCar = carService.create(car, parkingId);
        CarDTO carDTO = carDTOAssembler.toModel(newCar);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<?> updateCar(@RequestBody Car uCar, @PathVariable Integer carId) {
        carService.update(carId, uCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{carId}/parkings/{parkingId}")
    public ResponseEntity<?> updateCarWithParking(@RequestBody Car uCar, @PathVariable Integer carId, @PathVariable Integer parkingId) {
        carService.update(carId, uCar, parkingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer carId) {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
