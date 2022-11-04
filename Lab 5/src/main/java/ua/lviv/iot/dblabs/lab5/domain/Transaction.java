package ua.lviv.iot.dblabs.lab5.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Transaction {
    private String id;
    private double totalUsd;
    private Rent rent;

    @Id
    @Column(name = "id", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "total_usd", nullable = false)
    public double getTotalUsd() {
        return totalUsd;
    }

    public void setTotalUsd(double totalUsd) {
        this.totalUsd = totalUsd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.totalUsd, totalUsd) == 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalUsd);
    }

    @OneToOne(mappedBy = "transaction")
    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
