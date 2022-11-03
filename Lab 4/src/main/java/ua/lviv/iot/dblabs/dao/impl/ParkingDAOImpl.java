package ua.lviv.iot.dblabs.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.lviv.iot.dblabs.dao.ParkingDAO;
import ua.lviv.iot.dblabs.domain.Parking;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class ParkingDAOImpl implements ParkingDAO {

    private static final String FIND_ALL = "SELECT * FROM parking";
    private static final String CREATE = "INSERT parking(location, type, city_id) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE parking SET location=?, type=?, city_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM parking WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM parking WHERE id=?";
    private static final String FIND_BY_CITY_ID = "SELECT * FROM parking WHERE city_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Parking> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Parking.class));
    }

    @Override
    public Optional<Parking> findById(Integer id) {
        Optional<Parking> parking;
        try {
            parking = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Parking.class), id));
        } catch (EmptyResultDataAccessException e) {
            parking = Optional.empty();
        }
        return parking;
    }

    @Override
    public int create(Parking parking) {
        return jdbcTemplate.update(CREATE, parking.getLocation(), parking.getType(), parking.getCityId());
    }

    @Override
    public int update(Integer id, Parking parking) {
        return jdbcTemplate.update(UPDATE, parking.getLocation(), parking.getType(), parking.getCityId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Parking> findByCityId(Integer cityId) {
        return jdbcTemplate.query(FIND_BY_CITY_ID, BeanPropertyRowMapper.newInstance(Parking.class), cityId);
    }
}
