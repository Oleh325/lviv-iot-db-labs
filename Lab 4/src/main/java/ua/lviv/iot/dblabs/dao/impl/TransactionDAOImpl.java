package ua.lviv.iot.dblabs.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.lviv.iot.dblabs.dao.TransactionDAO;
import ua.lviv.iot.dblabs.domain.Transaction;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class TransactionDAOImpl implements TransactionDAO {

    private static final String FIND_ALL = "SELECT * FROM transaction";
    private static final String CREATE = "INSERT transaction(total_usd) VALUES (?)";
    private static final String UPDATE = "UPDATE transaction SET total_usd=? WHERE id=?";
    private static final String DELETE = "DELETE FROM transaction WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM transaction WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Transaction.class));
    }

    @Override
    public Optional<Transaction> findById(String id) {
        Optional<Transaction> transaction;
        try {
            transaction = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Transaction.class), id));
        } catch (EmptyResultDataAccessException e) {
            transaction = Optional.empty();
        }
        return transaction;
    }

    @Override
    public int create(Transaction transaction) {
        return jdbcTemplate.update(CREATE, transaction.getTotalUSD());
    }

    @Override
    public int update(String id, Transaction transaction) {
        return jdbcTemplate.update(UPDATE, transaction.getTotalUSD(), id);
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
