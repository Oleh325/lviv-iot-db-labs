package ua.lviv.iot.dblabs.lab6.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Objects;

@Entity
public class Fine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "violation_type", length = 45, nullable = false)
    private String violationType;

    @ManyToOne
    @JoinColumn(name = "driver_license_number", referencedColumnName = "license_number", nullable = false)
    private Driver driver;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getViolationType() {
        return violationType;
    }
    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
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
}
