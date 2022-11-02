package ua.lviv.iot.dblabs.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.lviv.iot.dblabs.dao.FineDAO;
import ua.lviv.iot.dblabs.domain.Fine;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class FineDAOImpl implements FineDAO {
    private static final String FIND_ALL = "SELECT * FROM fine";
    private static final String CREATE = "INSERT fine(violation_type, driver_license_number) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE fine SET violation_type=?, driver_license_number=? WHERE id=?";
    private static final String DELETE = "DELETE FROM fine WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM fine WHERE id=?";
    private static final String FIND_BY_DRIVER_LICENSE_NUMBER = "SELECT * FROM fine WHERE driver_license_number=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Fine> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Fine.class));
    }

    @Override
    public Optional<Fine> findById(Integer id) {
        Optional<Fine> fine;
        try {
            fine = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Fine.class), id));
        } catch (EmptyResultDataAccessException e) {
            fine = Optional.empty();
        }
        return fine;
    }

    @Override
    public int create(Fine fine) {
        return jdbcTemplate.update(CREATE, fine.getViolationType(), fine.getDriverLicenseNumber());
    }

    @Override
    public int update(Integer id, Fine fine) {
        return jdbcTemplate.update(UPDATE, fine.getViolationType(), fine.getDriverLicenseNumber(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Fine> findByDriverLicenseNumber(String driverLicenseNumber) {
        return jdbcTemplate.query(FIND_BY_DRIVER_LICENSE_NUMBER, BeanPropertyRowMapper.newInstance(Fine.class), driverLicenseNumber);
    }
}
