package ua.lviv.iot.dblabs.lab5.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Driver {
    private String licenseNumber;
    private String name;
    private String surname;
    private String middlename;
    private String email;
    private String phoneNumber;
    private List<Fine> fines;
    private List<Rent> rents;

    @Id
    @Column(name = "license_number", length = 15)
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Basic
    @Column(name = "name", length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", length = 50, nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "middlename", length = 50, nullable = false)
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Basic
    @Column(name = "email", length = 60)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number", length = 13, nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(licenseNumber, driver.licenseNumber) && Objects.equals(name, driver.name) && Objects.equals(surname, driver.surname) && Objects.equals(middlename, driver.middlename) && Objects.equals(email, driver.email) && Objects.equals(phoneNumber, driver.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber, name, surname, middlename, email, phoneNumber);
    }

    @OneToMany(mappedBy = "driver")
    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    @OneToMany(mappedBy = "driver")
    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }
}
