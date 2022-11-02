package ua.lviv.iot.dblabs.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.lviv.iot.dblabs.dao.DriverDAO;
import ua.lviv.iot.dblabs.domain.Driver;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class DriverDAOImpl implements DriverDAO {
    private static final String FIND_ALL = "SELECT * FROM driver";
    private static final String CREATE = "INSERT driver(license_number, name, surname, middlename, " +
            "email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE driver SET license_number=?, name=?, surname=?, middlename=?, " +
            "email=?, phone_number=? WHERE id=?";
    private static final String DELETE = "DELETE FROM driver WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM driver WHERE id=?";
    private static final String FIND_BY_SURNAME = "SELECT * FROM driver WHERE surname=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Driver> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Driver.class));
    }

    @Override
    public Optional<Driver> findById(String id) {
        Optional<Driver> driver;
        try {
            driver = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Driver.class), id));
        } catch (EmptyResultDataAccessException e) {
            driver = Optional.empty();
        }
        return driver;
    }

    @Override
    public int create(Driver driver) {
        return jdbcTemplate.update(CREATE, driver.getLicenseNumber(), driver.getName(), driver.getSurname(),
                driver.getMiddleName(), driver.getEmail(), driver.getPhoneNumber());
    }

    @Override
    public int update(String id, Driver driver) {
        return jdbcTemplate.update(UPDATE, driver.getLicenseNumber(), driver.getName(), driver.getSurname(),
                driver.getMiddleName(), driver.getEmail(), driver.getPhoneNumber(), id);
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Driver> findBySurname(String surname) {
        return jdbcTemplate.query(FIND_BY_SURNAME, BeanPropertyRowMapper.newInstance(Driver.class), surname);
    }
}
