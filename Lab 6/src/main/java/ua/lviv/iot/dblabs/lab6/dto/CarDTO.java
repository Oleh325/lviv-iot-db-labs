package ua.lviv.iot.dblabs.lab6.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "car", collectionRelation = "cars")
public class CarDTO extends RepresentationModel<CarDTO> {
    private final Integer id;
    private final String model;
    private final String color;
    private final String transmissionType;
    private final Integer seatsCount;
    private final Byte hasAc;
    private final Double baggageCapacityKg;
    private final Double rentCostPerDayUsd;
    private final String fuelType;
    private final String additionalInfo;
    private final List<Integer> rentIds;
    private final Integer parkingId;
}
