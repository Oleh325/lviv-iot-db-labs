package ua.lviv.iot.dblabs.dao.impl;

import ua.lviv.iot.dblabs.dao.CountryDAO;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.lviv.iot.dblabs.domain.Country;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class CountryDAOImpl implements CountryDAO {

    private static final String FIND_ALL = "SELECT * FROM country";
    private static final String CREATE = "INSERT country(name) VALUES (?)";
    private static final String UPDATE = "UPDATE country SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM country WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM country WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Country.class));
    }

    @Override
    public Optional<Country> findById(Integer id) {
        Optional<Country> country;
        try {
            country = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Country.class), id));
        } catch (EmptyResultDataAccessException e) {
            country = Optional.empty();
        }
        return country;
    }

    @Override
    public int create(Country country) {
        return jdbcTemplate.update(CREATE, country.getName());
    }

    @Override
    public int update(Integer id, Country country) {
        return jdbcTemplate.update(UPDATE, country.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
