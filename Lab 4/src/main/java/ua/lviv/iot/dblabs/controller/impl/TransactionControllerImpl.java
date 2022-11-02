package ua.lviv.iot.dblabs.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.iot.dblabs.controller.TransactionController;
import ua.lviv.iot.dblabs.domain.Transaction;
import ua.lviv.iot.dblabs.service.TransactionService;

import java.util.List;
import java.util.Optional;

@Component
public class TransactionControllerImpl implements TransactionController {

        @Autowired
        private TransactionService transactionService;


        @Override
        public List<Transaction> findAll() {
                return transactionService.findAll();
        }

        @Override
        public Optional<Transaction> findById(String id) {
                return transactionService.findById(id);
        }

        @Override
        public int create(Transaction transaction) {
                return transactionService.create(transaction);
        }

        @Override
        public int update(String id, Transaction transaction) {
                return transactionService.update(id, transaction);
        }

        @Override
        public int delete(String id) {
                return transactionService.delete(id);
        }
}
