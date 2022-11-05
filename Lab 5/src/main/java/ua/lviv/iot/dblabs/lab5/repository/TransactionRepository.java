package ua.lviv.iot.dblabs.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.dblabs.lab5.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
