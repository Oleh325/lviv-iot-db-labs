package ua.lviv.iot.dblabs.lab5.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Parking {
    private int id;
    private String location;
    private String type;
    private City city;
    private List<Car> cars;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "location", length = 45, nullable = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "type", length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking parking = (Parking) o;
        return id == parking.id && Objects.equals(location, parking.location) && Objects.equals(type, parking.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, type);
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "parking")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
