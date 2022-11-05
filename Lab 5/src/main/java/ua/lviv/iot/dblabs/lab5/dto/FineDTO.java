package ua.lviv.iot.dblabs.lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "fine", collectionRelation = "fines")
public class FineDTO extends RepresentationModel<FineDTO> {
    private Integer id;
    private String violationType;
    private String driverLicenseNumber;
}
