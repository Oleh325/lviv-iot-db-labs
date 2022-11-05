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
@Relation(itemRelation = "transaction", collectionRelation = "transactions")
public class TransactionDTO extends RepresentationModel<TransactionDTO> {
    private String id;
    private double totalUsd;
    private Integer rentId;
}
