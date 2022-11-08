package ua.lviv.iot.dblabs.lab6.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Objects;

@Entity
public class Parking {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "location", length = 45, nullable = false)
    private String location;

    @Basic
    @Column(name = "type", length = 45)
    private String type;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "parking")
    private List<Car> cars;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
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
}
