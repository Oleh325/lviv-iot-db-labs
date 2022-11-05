package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.lab5.controller.CarController;
import ua.lviv.iot.dblabs.lab5.domain.Car;
import ua.lviv.iot.dblabs.lab5.domain.Rent;
import ua.lviv.iot.dblabs.lab5.dto.CarDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarDTOAssembler implements RepresentationModelAssembler<Car, CarDTO> {

    @Override
    public CarDTO toModel(Car entity) {
        CarDTO carDTO = CarDTO.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .color(entity.getColor())
                .transmissionType(entity.getTransmissionType().toLowerCase())
                .seatsCount(entity.getSeatsCount())
                .hasAc(entity.getHasAc())
                .baggageCapacityKg(entity.getBaggageCapacityKg())
                .rentCostPerDayUsd(entity.getRentCostPerDayUsd())
                .fuelType(entity.getFuelType().toLowerCase())
                .additionalInfo(entity.getAdditionalInfo())
                .rentIds(entity.getRents().stream().map(Rent::getId).toList())
                .parkingId(entity.getParking() == null ? null : entity.getParking().getId())
                .build();
        Link selfLink = linkTo(methodOn(CarController.class).getCar(carDTO.getId())).withSelfRel();
        carDTO.add(selfLink);
        return carDTO;
    }

    @Override
    public CollectionModel<CarDTO> toCollectionModel(Iterable<? extends Car> entities) {
        CollectionModel<CarDTO> carDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();
        carDTOs.add(selfLink);
        return carDTOs;
    }

    public CollectionModel<CarDTO> toCollectionModel(Iterable<? extends Car> entities, Link link) {
        CollectionModel<CarDTO> carDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        carDTOs.add(link);
        return carDTOs;
    }
}
