package ua.lviv.iot.dblabs.lab5.domain;

import ua.lviv.iot.dblabs.lab5.domain.enums.PaymentType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Rent {
    private int id;
    private Timestamp dateOfRent;
    private Timestamp endDateOfRent;
    private PaymentType paymentType;
    private Transaction transaction;
    private Car car;
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
    @Column(name = "date_of_rent", columnDefinition = "DATETIME", nullable = false)
    public Timestamp getDateOfRent() {
        return dateOfRent;
    }

    public void setDateOfRent(Timestamp dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    @Basic
    @Column(name = "end_date_of_rent", columnDefinition = "DATETIME", nullable = false)
    public Timestamp getEndDateOfRent() {
        return endDateOfRent;
    }

    public void setEndDateOfRent(Timestamp endDateOfRent) {
        this.endDateOfRent = endDateOfRent;
    }

    @Basic
    @Column(name = "payment_type", columnDefinition = "ENUM('debit_card','cash','crypto')", nullable = false)
    public String getPaymentType() {
        return paymentType.toString().toLowerCase();
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = PaymentType.valueOf(paymentType.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id && Objects.equals(dateOfRent, rent.dateOfRent) && Objects.equals(endDateOfRent, rent.endDateOfRent) && Objects.equals(paymentType, rent.paymentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfRent, endDateOfRent, paymentType);
    }

    @OneToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "id", nullable = false)
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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