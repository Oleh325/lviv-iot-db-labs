package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.lab5.controller.ParkingController;
import ua.lviv.iot.dblabs.lab5.domain.Car;
import ua.lviv.iot.dblabs.lab5.domain.Parking;
import ua.lviv.iot.dblabs.lab5.dto.ParkingDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ParkingDTOAssembler implements RepresentationModelAssembler<Parking, ParkingDTO> {

    @Override
    public ParkingDTO toModel(Parking entity) {
        ParkingDTO parkingDTO = ParkingDTO.builder()
                .id(entity.getId())
                .location(entity.getLocation())
                .type(entity.getType())
                .cityId(entity.getCity().getId())
                .carIds(entity.getCars().stream().map(Car::getId).toList())
                .build();
        Link selfLink = linkTo(methodOn(ParkingController.class).getParking(parkingDTO.getId())).withSelfRel();
        parkingDTO.add(selfLink);
        return parkingDTO;
    }

    @Override
    public CollectionModel<ParkingDTO> toCollectionModel(Iterable<? extends Parking> entities) {
        CollectionModel<ParkingDTO> parkingDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ParkingController.class).getAllParkings()).withSelfRel();
        parkingDTOs.add(selfLink);
        return parkingDTOs;
    }

    public CollectionModel<ParkingDTO> toCollectionModel(Iterable<? extends Parking> entities, Link link) {
        CollectionModel<ParkingDTO> parkingDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        parkingDTOs.add(link);
        return parkingDTOs;
    }

}
