package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.lab5.controller.CountryController;
import ua.lviv.iot.dblabs.lab5.domain.City;
import ua.lviv.iot.dblabs.lab5.domain.Country;
import ua.lviv.iot.dblabs.lab5.dto.CountryDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CountryDTOAssembler implements RepresentationModelAssembler<Country, CountryDTO> {

    @Override
    public CountryDTO toModel(Country entity) {
        CountryDTO countryDTO = CountryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cityIds(entity.getCities() == null ? null : entity.getCities().stream().map(City::getId).toList())
                .build();
        Link selfLink = linkTo(methodOn(CountryController.class).getCountry(countryDTO.getId())).withSelfRel();
        countryDTO.add(selfLink);
        return countryDTO;
    }

    @Override
    public CollectionModel<CountryDTO> toCollectionModel(Iterable<? extends Country> entities) {
        CollectionModel<CountryDTO> countryDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();
        countryDTOs.add(selfLink);
        return countryDTOs;
    }

    public CollectionModel<CountryDTO> toCollectionModel(Iterable<? extends Country> entities, Link link) {
        CollectionModel<CountryDTO> countryDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        countryDTOs.add(link);
        return countryDTOs;
    }

}
