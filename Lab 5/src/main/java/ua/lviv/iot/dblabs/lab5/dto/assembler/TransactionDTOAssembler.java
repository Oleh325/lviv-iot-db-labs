package ua.lviv.iot.dblabs.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.lab5.controller.TransactionController;
import ua.lviv.iot.dblabs.lab5.domain.Transaction;
import ua.lviv.iot.dblabs.lab5.dto.TransactionDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionDTOAssembler implements RepresentationModelAssembler<Transaction, TransactionDTO> {

    @Override
    public TransactionDTO toModel(Transaction entity) {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .id(entity.getId())
                .totalUsd(entity.getTotalUsd())
                .rentId(entity.getRent() == null ? null : entity.getRent().getId())
                .build();
        Link selfLink = linkTo(methodOn(TransactionController.class).getTransaction(transactionDTO.getId())).withSelfRel();
        transactionDTO.add(selfLink);
        return transactionDTO;
    }

    @Override
    public CollectionModel<TransactionDTO> toCollectionModel(Iterable<? extends Transaction> entities) {
        CollectionModel<TransactionDTO> transactionDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TransactionController.class).getAllTransactions()).withSelfRel();
        transactionDTOs.add(selfLink);
        return transactionDTOs;
    }

    public CollectionModel<TransactionDTO> toCollectionModel(Iterable<? extends Transaction> entities, Link link) {
        CollectionModel<TransactionDTO> transactionDTOs = RepresentationModelAssembler.super.toCollectionModel(entities);
        transactionDTOs.add(link);
        return transactionDTOs;
    }

}
