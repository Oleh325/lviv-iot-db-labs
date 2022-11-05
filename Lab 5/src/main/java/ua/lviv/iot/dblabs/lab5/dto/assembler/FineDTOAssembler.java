package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import ua.lviv.iot.dblabs.lab5.controller.FineController;
import ua.lviv.iot.dblabs.lab5.domain.Fine;
import ua.lviv.iot.dblabs.lab5.dto.FineDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FineDTOAssembler implements RepresentationModelAssembler<Fine, FineDTO> {

    @Override
    public FineDTO toModel(Fine entity) {
        FineDTO fineDTO = FineDTO.builder()
                .id(entity.getId())
                .violationType(entity.getViolationType())
                .driverLicenseNumber(entity.getDriver().getLicenseNumber())
                .build();
        Link selfLink = linkTo(methodOn(FineController.class).getFine(fineDTO.getId())).withSelfRel();
        fineDTO.add(selfLink);
        return fineDTO;
    }

    @Override
    public CollectionModel<FineDTO> toCollectionModel(Iterable<? extends Fine> entities) {
        CollectionModel<FineDTO> fineDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(FineController.class).getAllFines()).withSelfRel();
        fineDTOs.add(selfLink);
        return fineDTOs;
    }

    public CollectionModel<FineDTO> toCollectionModel(Iterable<? extends Fine> entities, Link link) {
        CollectionModel<FineDTO> fineDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        fineDTOs.add(link);
        return fineDTOs;
    }

}
