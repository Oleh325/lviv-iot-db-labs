package ua.lviv.iot.dblabs.dao.impl;

import org.springframework.stereotype.Component;
import ua.lviv.iot.dblabs.dao.CarDAO;
import ua.lviv.iot.dblabs.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class CarDAOImpl implements CarDAO {
    private static final String FIND_ALL = "SELECT * FROM car";
    private static final String CREATE = "INSERT car(model, color, transmission_type, seats_count, has_ac, baggage_capacity_kg, " +
            "rent_cost_per_day_usd, fuel_type, additional_info, parking_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE car SET model=?, color=?, transmission_type=?, seats_count=?, " +
            "has_ac=?, baggage_capacity_kg=?, rent_cost_per_day_usd=?, fuel_type=?, additional_info=?, parking_id=? WHERE id=?";
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
        return jdbcTemplate.query(FIND_ALL, (rs, rowNum) -> getCarFromResultSet(rs));
    }

    @Override
    public Optional<Car> findById(Integer id) {
        Optional<Car> car;
        try {
            car = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    (rs, rowNum) -> getCarFromResultSet(rs), id));
        } catch (EmptyResultDataAccessException e) {
            car = Optional.empty();
        }
        return car;
    }

    @Override
    public int create(Car car) {
        return jdbcTemplate.update(CREATE, car.getModel(), car.getColor(), car.getTransmissionType(), car.getSeatsCount(),
                car.getHasAC(), car.getBaggageCapacityKG(), car.getRentCostPerDayUSD(), car.getFuelType(), car.getAdditionalInfo(), car.getParkingId());
    }

    @Override
    public int update(Integer id, Car car) {
        return jdbcTemplate.update(UPDATE, car.getModel(), car.getColor(), car.getTransmissionType(), car.getSeatsCount(),
                car.getHasAC(), car.getBaggageCapacityKG(), car.getRentCostPerDayUSD(), car.getFuelType(), car.getAdditionalInfo(), car.getParkingId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }


    @Override
    public List<Car> findByModel(String model) {
        return jdbcTemplate.query(FIND_BY_MODEL, (rs, rowNum) -> getCarFromResultSet(rs), model);
    }

    @Override
    public List<Car> findByFuelType(String fuelType) {
        return jdbcTemplate.query(FIND_BY_FUEL_TYPE, (rs, rowNum) -> getCarFromResultSet(rs), fuelType);
    }

    @Override
    public List<Car> findInPriceRange(Float from, Float to) {
        return jdbcTemplate.query(FIND_IN_PRICE_RANGE, (rs, rowNum) -> getCarFromResultSet(rs), from, to);
    }

    @Override
    public List<Car> findByParkingId(Integer parkingId) {
        return jdbcTemplate.query(FIND_BY_PARKING_ID, (rs, rowNum) -> getCarFromResultSet(rs), parkingId);
    }

    private Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getInt("id"),
                resultSet.getString("model"),
                resultSet.getString("color"),
                resultSet.getString("transmission_type"),
                resultSet.getInt("seats_count"),
                resultSet.getBoolean("has_ac"),
                resultSet.getFloat("baggage_capacity_kg"),
                resultSet.getFloat("rent_cost_per_day_usd"),
                resultSet.getString("fuel_type"),
                resultSet.getString("additional_info"),
                resultSet.getInt("parking_id")
        );
    }
}
