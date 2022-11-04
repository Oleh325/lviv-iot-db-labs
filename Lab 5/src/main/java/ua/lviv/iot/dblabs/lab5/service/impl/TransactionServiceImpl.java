package ua.lviv.iot.dblabs.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.lab5.domain.Transaction;
import ua.lviv.iot.dblabs.lab5.exception.TransactionNotFoundException;
import ua.lviv.iot.dblabs.lab5.repository.TransactionRepository;
import ua.lviv.iot.dblabs.lab5.service.TransactionService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(String id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @Transactional
    public void update(String id, Transaction uTransaction) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        transaction.setTotalUsd(uTransaction.getTotalUsd());
        transaction.setRent(uTransaction.getRent());
        transactionRepository.save(transaction);
    }

    @Transactional
    public void delete(String id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        transactionRepository.delete(transaction);
    }
}
