package ua.lviv.iot.dblabs.lab5.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Driver {

    @Id
    @Column(name = "license_number", length = 15)
    private String licenseNumber;

    @Basic
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Basic
    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

    @Basic
    @Column(name = "middlename", length = 50, nullable = false)
    private String middlename;

    @Basic
    @Column(name = "email", length = 60)
    private String email;

    @Basic
    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "driver")
    private List<Fine> fines;

    @OneToMany(mappedBy = "driver")
    private List<Rent> rents;


    public String getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Fine> getFines() {
        return fines;
    }
    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    public List<Rent> getRents() {
        return rents;
    }
    public void setRents(List<Rent> rents) {
        this.rents = rents;
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
}
