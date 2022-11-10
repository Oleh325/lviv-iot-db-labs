package ua.lviv.iot.dblabs.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dblabs.lab6.domain.Transaction;
import ua.lviv.iot.dblabs.lab6.dto.TransactionDTO;
import ua.lviv.iot.dblabs.lab6.dto.assembler.TransactionDTOAssembler;
import ua.lviv.iot.dblabs.lab6.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionDTOAssembler transactionDTOAssembler;

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable String transactionId) {
        Transaction transaction = transactionService.findById(transactionId);
        TransactionDTO transactionDTO = transactionDTOAssembler.toModel(transaction);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CollectionModel<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        CollectionModel<TransactionDTO> transactionDTOs = transactionDTOAssembler.toCollectionModel(transactions);
        return new ResponseEntity<>(transactionDTOs, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.create(transaction);
        TransactionDTO transactionDTO = transactionDTOAssembler.toModel(newTransaction);
        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{transactionId}/rents/{rentId}")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction uTransaction, @PathVariable String transactionId,
                                               @PathVariable Integer rentId) {
        transactionService.update(transactionId, uTransaction, rentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String transactionId) {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
