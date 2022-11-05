package ua.lviv.iot.dblabs.lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Timestamp;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "rent", collectionRelation = "rents")
public class RentDTO extends RepresentationModel<RentDTO> {
    private Integer id;
    private Timestamp dateOfRent;
    private Timestamp endDateOfRent;
    private String paymentType;
    private String transactionId;
    private Integer carId;
    private String driverLicenseNumber;
}
