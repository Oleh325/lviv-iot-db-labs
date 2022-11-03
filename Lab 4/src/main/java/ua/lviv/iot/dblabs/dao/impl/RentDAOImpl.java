package ua.lviv.iot.dblabs.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.lviv.iot.dblabs.dao.RentDAO;
import ua.lviv.iot.dblabs.domain.Rent;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class RentDAOImpl implements RentDAO {

    private static final String FIND_ALL = "SELECT * FROM rent";
    private static final String CREATE = "INSERT rent(date_of_rent, end_date_of_rent, payment_type, " +
            "transaction_id, car_id, driver_license_number) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE rent SET date_of_rent=?, end_date_of_rent=?, payment_type=?, " +
            "transaction_id=?, car_id=?, driver_license_number=? WHERE id=?";
    private static final String DELETE = "DELETE FROM rent WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM rent WHERE id=?";
    private static final String FIND_IN_DATE_RANGE = "SELECT * FROM rent WHERE (date_of_rent BETWEEN ? AND ?) AND " +
            "(end_date_of_rent BETWEEN ? AND ?)";
    private static final String FIND_BY_CAR_ID = "SELECT * FROM rent WHERE car_id=?";
    private static final String FIND_BY_DRIVER_LICENSE_NUMBER = "SELECT * FROM rent WHERE driver_license_number=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Rent> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Rent.class));
    }

    @Override
    public Optional<Rent> findById(Integer id) {
        Optional<Rent> rent;
        try {
            rent = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Rent.class), id));
        } catch (EmptyResultDataAccessException e) {
            rent = Optional.empty();
        }
        return rent;
    }

    @Override
    public int create(Rent rent) {
        return jdbcTemplate.update(CREATE, rent.getDateOfRent(), rent.getEndDateOfRent(),
                rent.getPaymentType(), rent.getTransactionId(), rent.getCarId(),
                rent.getDriverLicenseNumber());
    }

    @Override
    public int update(Integer id, Rent rent) {
        return jdbcTemplate.update(UPDATE, rent.getDateOfRent(), rent.getEndDateOfRent(),
                rent.getPaymentType(), rent.getTransactionId(), rent.getCarId(),
                rent.getDriverLicenseNumber(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Rent> findInDateRange(Timestamp from, Timestamp to) {
        return jdbcTemplate.query(FIND_IN_DATE_RANGE, BeanPropertyRowMapper.newInstance(Rent.class), from, to, from, to);
    }

    @Override
    public List<Rent> findByCarId(Integer carId) {
        return jdbcTemplate.query(FIND_BY_CAR_ID, BeanPropertyRowMapper.newInstance(Rent.class), carId);
    }

    @Override
    public List<Rent> findByDriverLicenseNumber(String driverLicenseNumber) {
        return jdbcTemplate.query(FIND_BY_DRIVER_LICENSE_NUMBER, BeanPropertyRowMapper.newInstance(Rent.class), driverLicenseNumber);
    }
}
