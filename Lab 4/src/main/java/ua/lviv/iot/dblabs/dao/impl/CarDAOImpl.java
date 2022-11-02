package ua.lviv.iot.dblabs.dao.impl;

import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.dao.CarDAO;
import ua.lviv.iot.dblabs.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class CarDAOImpl implements CarDAO {
    private static final String FIND_ALL = "SELECT * FROM car";
    private static final String CREATE = "INSERT car(model, color, transmission_type, seats_count, has_ac, baggage_capacity_kg, " +
            "rent_cost_per_day_usd, fuel_type, additional_info) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE car SET model=?, color=?, transmission_type=?, seats_count=?, " +
            "has_ac=?, baggage_capacity_kg=?, rent_cost_per_day_usd=?, fuel_type=?, additional_info=? WHERE id=?";
    private static final String DELETE = "DELETE FROM car WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM car WHERE id=?";
    private static final String FIND_BY_MODEL = "SELECT * FROM car WHERE model=?";
    private static final String FIND_BY_FUEL_TYPE = "SELECT * FROM car WHERE fuel_type=?";
    private static final String FIND_BY_PARKING_ID = "SELECT * FROM car WHERE parking_id=?";
    private static final String FIND_IN_PRICE_RANGE = "SELECT * FROM book WHERE rent_cost_per_day_usd>=? AND rent_cost_per_day_usd=<?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Car.class));
    }

    @Override
    public Optional<Car> findById(Integer id) {
        Optional<Car> car;
        try {
            car = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Car.class), id));
        } catch (EmptyResultDataAccessException e) {
            car = Optional.empty();
        }
        return car;
    }

    @Override
    public int create(Car car) {
        return jdbcTemplate.update(CREATE, car.getModel(), car.getColor(), car.getTransmissionType(), car.getSeatsCount(),
                car.getHasAC(), car.getBaggageCapacityKG(), car.getRentCostPerDayUSD(), car.getFuelType(), car.getAdditionalInfo());
    }

    @Override
    public int update(Integer id, Car car) {
        return jdbcTemplate.update(UPDATE, car.getModel(), car.getColor(), car.getTransmissionType(), car.getSeatsCount(),
                car.getHasAC(), car.getBaggageCapacityKG(), car.getRentCostPerDayUSD(), car.getFuelType(), car.getAdditionalInfo(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


    @Override
    public List<Car> findByModel(String model) {
        return jdbcTemplate.query(FIND_BY_MODEL, BeanPropertyRowMapper.newInstance(Car.class), model);
    }

    @Override
    public List<Car> findByFuelType(String fuelType) {
        return jdbcTemplate.query(FIND_BY_FUEL_TYPE, BeanPropertyRowMapper.newInstance(Car.class), fuelType);
    }

    @Override
    public List<Car> findInPriceRange(Float from, Float to) {
        return jdbcTemplate.query(FIND_IN_PRICE_RANGE, BeanPropertyRowMapper.newInstance(Car.class), from, to);
    }

    @Override
    public List<Car> findByParkingId(Integer parkingId) {
        return jdbcTemplate.query(FIND_BY_PARKING_ID, BeanPropertyRowMapper.newInstance(Car.class), parkingId);
    }
}
