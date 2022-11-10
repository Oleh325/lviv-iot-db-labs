package ua.lviv.iot.dblabs.lab6.domain;

import ua.lviv.iot.dblabs.lab6.domain.enums.FuelType;
import ua.lviv.iot.dblabs.lab6.domain.enums.TransmissionType;


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
public class Car {

    private int id;

    @Basic
    @Column(name = "model", length = 45, nullable = false)
    private String model;

    @Basic
    @Column(name = "color", length = 45)
    private String color;

    @Basic
    @Column(name = "transmission_type", columnDefinition = "ENUM('manual','automatic')")
    private TransmissionType transmissionType;

    @Basic
    @Column(name = "seats_count", nullable = false)
    private int seatsCount;

    @Basic
    @Column(name = "has_ac", columnDefinition = "TINYINT(1)")
    private Byte hasAc;

    @Basic
    @Column(name = "baggage_capacity_kg", nullable = false)
    private double baggageCapacityKg;

    @Basic
    @Column(name = "rent_cost_per_day_usd", nullable = false)
    private double rentCostPerDayUsd;

    @Basic
    @Column(name = "fuel_type", columnDefinition = "ENUM('gas','petrol','diesel','electric','other')", nullable = false)
    private FuelType fuelType;

    @Basic
    @Column(name = "additional_info", length = 200)
    private String additionalInfo;

    private List<Rent> rents;

    private Parking parking;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmissionType() {
        return transmissionType.toString().toLowerCase();
    }
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = TransmissionType.valueOf(transmissionType.toUpperCase());
    }

    public int getSeatsCount() {
        return seatsCount;
    }
    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public Byte getHasAc() {
        return hasAc;
    }
    public void setHasAc(Byte hasAc) {
        this.hasAc = hasAc;
    }

    public double getBaggageCapacityKg() {
        return baggageCapacityKg;
    }
    public void setBaggageCapacityKg(double baggageCapacityKg) {
        this.baggageCapacityKg = baggageCapacityKg;
    }

    public double getRentCostPerDayUsd() {
        return rentCostPerDayUsd;
    }
    public void setRentCostPerDayUsd(double rentCostPerDayUsd) {
        this.rentCostPerDayUsd = rentCostPerDayUsd;
    }

    public String getFuelType() {
        return fuelType.toString().toLowerCase();
    }
    public void setFuelType(String fuelType) {
        this.fuelType = FuelType.valueOf(fuelType.toUpperCase());
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @OneToMany(mappedBy = "car")
    public List<Rent> getRents() {
        return rents;
    }
    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    @ManyToOne
    @JoinColumn(name = "parking_id", referencedColumnName = "id")
    public Parking getParking() {
        return parking;
    }
    public void setParking(Parking parking) {
        this.parking = parking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && seatsCount == car.seatsCount && Double.compare(car.baggageCapacityKg, baggageCapacityKg) == 0 && Double.compare(car.rentCostPerDayUsd, rentCostPerDayUsd) == 0 && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(transmissionType, car.transmissionType) && Objects.equals(hasAc, car.hasAc) && Objects.equals(fuelType, car.fuelType) && Objects.equals(additionalInfo, car.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, color, transmissionType, seatsCount, hasAc, baggageCapacityKg, rentCostPerDayUsd, fuelType, additionalInfo);
    }
}
