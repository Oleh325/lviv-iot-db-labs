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
@Relation(itemRelation = "driver", collectionRelation = "drivers")
public class DriverDTO extends RepresentationModel<DriverDTO> {
    private String licenseNumber;
    private String name;
    private String surname;
    private String middlename;
    private String email;
    private String phoneNumber;
    private List<Integer> fineIds;
    private List<Integer> rentIds;
}
