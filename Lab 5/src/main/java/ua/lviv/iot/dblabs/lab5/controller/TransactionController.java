package ua.lviv.iot.dblabs.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.dblabs.lab5.dto.assembler.TransactionDTOAssembler;
import ua.lviv.iot.dblabs.lab5.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionDTOAssembler transactionDTOAssembler;

}
