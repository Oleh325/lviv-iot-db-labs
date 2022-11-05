package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import ua.lviv.iot.dblabs.lab5.controller.CityController;
import ua.lviv.iot.dblabs.lab5.domain.City;
import ua.lviv.iot.dblabs.lab5.domain.Parking;
import ua.lviv.iot.dblabs.lab5.dto.CityDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CityDTOAssembler implements RepresentationModelAssembler<City, CityDTO> {
    @Override
    public CityDTO toModel(City entity) {
        CityDTO cityDTO = CityDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .countryId(entity.getCountry().getId())
                .parkingIds(entity.getParkings().stream().map(Parking::getId).toList())
                .build();
        Link selfLink = linkTo(methodOn(CityController.class).getCity(cityDTO.getId())).withSelfRel();
        cityDTO.add(selfLink);
        return cityDTO;
    }

    @Override
    public CollectionModel<CityDTO> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityDTO> cityDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CityController.class).getAllCities()).withSelfRel();
        cityDTOs.add(selfLink);
        return cityDTOs;
    }

    public CollectionModel<CityDTO> toCollectionModel(Iterable<? extends City> entities, Link link) {
        CollectionModel<CityDTO> cityDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        cityDTOs.add(link);
        return cityDTOs;
    }
}
