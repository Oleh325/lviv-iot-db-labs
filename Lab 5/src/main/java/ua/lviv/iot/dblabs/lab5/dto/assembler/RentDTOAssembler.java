package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.lab5.controller.RentController;
import ua.lviv.iot.dblabs.lab5.domain.Rent;
import ua.lviv.iot.dblabs.lab5.dto.RentDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RentDTOAssembler implements RepresentationModelAssembler<Rent, RentDTO> {

    @Override
    public RentDTO toModel(Rent entity) {
        RentDTO rentDTO = RentDTO.builder()
                .id(entity.getId())
                .dateOfRent(entity.getDateOfRent())
                .endDateOfRent(entity.getEndDateOfRent())
                .paymentType(entity.getPaymentType())
                .transactionId(entity.getTransaction() == null ? null : entity.getTransaction().getId())
                .carId(entity.getCar().getId())
                .driverLicenseNumber(entity.getDriver().getLicenseNumber())
                .build();
        Link selfLink = linkTo(methodOn(RentController.class).getRent(rentDTO.getId())).withSelfRel();
        rentDTO.add(selfLink);
        return rentDTO;
    }

    @Override
    public CollectionModel<RentDTO> toCollectionModel(Iterable<? extends Rent> entities) {
        CollectionModel<RentDTO> rentDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RentController.class).getAllRents()).withSelfRel();
        rentDTOs.add(selfLink);
        return rentDTOs;
    }

    public CollectionModel<RentDTO> toCollectionModel(Iterable<? extends Rent> entities, Link link) {
        CollectionModel<RentDTO> rentDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        rentDTOs.add(link);
        return rentDTOs;
    }

}
