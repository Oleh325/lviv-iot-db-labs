package ua.lviv.iot.dblabs.lab5.service;

import ua.lviv.iot.dblabs.lab5.domain.Transaction;

public interface TransactionService extends GeneralService<Transaction, String> {

    void update(String id, Transaction transaction, Integer rentId);
}
