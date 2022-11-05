package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import ua.lviv.iot.dblabs.lab5.controller.DriverController;
import ua.lviv.iot.dblabs.lab5.domain.Driver;
import ua.lviv.iot.dblabs.lab5.domain.Fine;
import ua.lviv.iot.dblabs.lab5.domain.Rent;
import ua.lviv.iot.dblabs.lab5.dto.DriverDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class DriverDTOAssembler implements RepresentationModelAssembler<Driver, DriverDTO> {
    @Override
    public DriverDTO toModel(Driver entity) {
        DriverDTO driverDTO = DriverDTO.builder()
                .licenseNumber(entity.getLicenseNumber())
                .name(entity.getName())
                .surname(entity.getSurname())
                .middlename(entity.getMiddlename())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .fineIds(entity.getFines().stream().map(Fine::getId).toList())
                .rentIds(entity.getRents().stream().map(Rent::getId).toList())
                .build();
        Link selfLink = linkTo(methodOn(DriverController.class).getDriver(driverDTO.getLicenseNumber())).withSelfRel();
        driverDTO.add(selfLink);
        return driverDTO;
    }

    @Override
    public CollectionModel<DriverDTO> toCollectionModel(Iterable<? extends Driver> entities) {
        CollectionModel<DriverDTO> driverDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DriverController.class).getAllDrivers()).withSelfRel();
        driverDTOs.add(selfLink);
        return driverDTOs;
    }

    public CollectionModel<DriverDTO> toCollectionModel(Iterable<? extends Driver> entities, Link link) {
        CollectionModel<DriverDTO> driverDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        driverDTOs.add(link);
        return driverDTOs;
    }

}
