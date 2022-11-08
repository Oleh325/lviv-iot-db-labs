package ua.lviv.iot.dblabs.lab6.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Basic
    @Column(name = "surname", length = 45, nullable = false)
    private String surname;

    @Basic
    @Column(name = "middlename", length = 45)
    private String middlename;

    @Basic
    @Column(name = "email", length = 50)
    private String email;

    @Basic
    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @Column(name = "city_id", nullable = false)
    private Integer cityId;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(middlename, person.middlename) && Objects.equals(email, person.email) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(cityId, person.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, middlename, email, phoneNumber, cityId);
    }
}
