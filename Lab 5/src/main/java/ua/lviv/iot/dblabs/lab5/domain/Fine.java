package ua.lviv.iot.dblabs.lab5.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Fine {
    private int id;
    private String violationType;
    private Driver driver;

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
    @Column(name = "violation_type", length = 45, nullable = false)
    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fine fine = (Fine) o;
        return id == fine.id && Objects.equals(violationType, fine.violationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, violationType);
    }

    @ManyToOne
    @JoinColumn(name = "driver_license_number", referencedColumnName = "license_number", nullable = false)
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
