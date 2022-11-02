package ua.lviv.iot.dblabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.dblabs.dao.TransactionDAO;
import ua.lviv.iot.dblabs.domain.Transaction;
import ua.lviv.iot.dblabs.service.TransactionService;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public List<Transaction> findAll() {
        return transactionDAO.findAll();
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return transactionDAO.findById(id);
    }

    @Override
    public int create(Transaction transaction) {
        return transactionDAO.create(transaction);
    }

    @Override
    public int update(String id, Transaction transaction) {
        return transactionDAO.update(id, transaction);
    }

    @Override
    public int delete(String id) {
        return transactionDAO.delete(id);
    }
}
