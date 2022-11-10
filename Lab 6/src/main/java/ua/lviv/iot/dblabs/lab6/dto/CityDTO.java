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
@Relation(itemRelation = "city", collectionRelation = "cities")
public class CityDTO extends RepresentationModel<CityDTO> {
    private final Integer id;
    private final String name;
    private final Integer countryId;
    private final List<Integer> parkingIds;
}
