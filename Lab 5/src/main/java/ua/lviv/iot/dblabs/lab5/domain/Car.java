package ua.lviv.iot.dblabs.lab5.domain;

import ua.lviv.iot.dblabs.lab5.domain.enums.FuelType;
import ua.lviv.iot.dblabs.lab5.domain.enums.TransmissionType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {
    private int id;
    private String model;
    private String color;
    private TransmissionType transmissionType;
    private int seatsCount;
    private Byte hasAc;
    private double baggageCapacityKg;
    private double rentCostPerDayUsd;
    private FuelType fuelType;
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

    @Basic
    @Column(name = "model", length = 45, nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "color", length = 45)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "transmission_type", columnDefinition = "ENUM('manual','automatic')")
    public String getTransmissionType() {
        return transmissionType.toString().toLowerCase();
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = TransmissionType.valueOf(transmissionType.toUpperCase());
    }

    @Basic
    @Column(name = "seats_count", nullable = false)
    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    @Basic
    @Column(name = "has_ac", columnDefinition = "TINYINT(1)")
    public Byte getHasAc() {
        return hasAc;
    }

    public void setHasAc(Byte hasAc) {
        this.hasAc = hasAc;
    }

    @Basic
    @Column(name = "baggage_capacity_kg", nullable = false)
    public double getBaggageCapacityKg() {
        return baggageCapacityKg;
    }

    public void setBaggageCapacityKg(double baggageCapacityKg) {
        this.baggageCapacityKg = baggageCapacityKg;
    }

    @Basic
    @Column(name = "rent_cost_per_day_usd", nullable = false)
    public double getRentCostPerDayUsd() {
        return rentCostPerDayUsd;
    }

    public void setRentCostPerDayUsd(double rentCostPerDayUsd) {
        this.rentCostPerDayUsd = rentCostPerDayUsd;
    }

    @Basic
    @Column(name = "fuel_type", columnDefinition = "ENUM('gas','petrol','diesel','electric','other')", nullable = false)
    public String getFuelType() {
        return fuelType.toString().toLowerCase();
    }

    public void setFuelType(String fuelType) {
        this.fuelType = FuelType.valueOf(fuelType.toUpperCase());
    }

    @Basic
    @Column(name = "additional_info", length = 200)
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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
}
