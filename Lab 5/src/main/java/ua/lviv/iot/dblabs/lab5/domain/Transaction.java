package ua.lviv.iot.dblabs.lab5.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Transaction {

    @Id
    @Column(name = "id", length = 50)
    private String id;

    @Basic
    @Column(name = "total_usd", nullable = false)
    private double totalUsd;

    @OneToOne(mappedBy = "transaction")
    private Rent rent;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public double getTotalUsd() {
        return totalUsd;
    }
    public void setTotalUsd(double totalUsd) {
        this.totalUsd = totalUsd;
    }

    public Rent getRent() {
        return rent;
    }
    public void setRent(Rent rent) {
        this.rent = rent;
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
}
