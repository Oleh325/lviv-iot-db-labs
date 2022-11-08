package ua.lviv.iot.dblabs.lab6.service;

import ua.lviv.iot.dblabs.lab6.domain.Transaction;

public interface TransactionService extends GeneralService<Transaction, String> {

    void update(String id, Transaction transaction, Integer rentId);
}
