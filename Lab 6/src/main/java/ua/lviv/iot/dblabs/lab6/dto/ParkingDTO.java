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
@Relation(itemRelation = "parking", collectionRelation = "parkings")
public class ParkingDTO extends RepresentationModel<ParkingDTO> {
    private Integer id;
    private String location;
    private String type;
    private Integer cityId;
    private List<Integer> carIds;
}
